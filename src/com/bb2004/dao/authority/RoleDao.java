package com.bb2004.dao.authority;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.bb2004.common.HibernateDao;
import com.bb2004.entity.authority.Menu;
import com.bb2004.entity.authority.Role;


/**
 * @content 角色管理
 * @time 2012-03-27
 * @author BB2004
 */
@Repository
public class RoleDao extends HibernateDao<Role>{
	
	/**
	 * @content 查询所有角色 或者条件查询
	 * @param name 角色的名称
	 * @return List<Role>
	 */
	public List<Role> roleList(String name,int curpage,int pagesize){
		StringBuffer hql=new StringBuffer(" from Role r where 1=1 ");
		if(null !=name && !"".equals(name)){
			hql.append(" and r.name like '%"+name+"%'");
		}
		Query query=this.getSession().createQuery(hql.toString());
		query.setFirstResult((curpage-1)*pagesize);
		query.setMaxResults(pagesize);
		return query.list();
	}
	
	/**
	 * @content 查看roleList方法
	 * @param name 角色的名称
	 * @return count(r.id)
	 */
	public int roleListCount(String name){
		StringBuffer hql=new StringBuffer(" select count(r.id) from Role r where 1=1 ");
		if(null !=name && !"".equals(name)){
			hql.append(" and r.name like '%"+name+"%'");
		}
		Query query=this.getSession().createQuery(hql.toString());
		Object object=query.list().get(0);
		return Integer.parseInt(object.toString());
	}
}
