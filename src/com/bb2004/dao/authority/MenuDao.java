package com.bb2004.dao.authority;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Component;

import com.bb2004.common.HibernateDao;
import com.bb2004.entity.authority.Menu;


@Component
public class MenuDao extends HibernateDao<Menu> {
	
	/**
	 * @content 查询所有菜单 或者条件查询
	 * @param name 菜单的名称
	 * @param name 菜单的url
	 * @return List<Menu>
	 */
	public List<Menu> menuList(String name,String url){
		StringBuffer hql=new StringBuffer(" from Menu m where 1=1 ");
		if(null !=name && !"".equals(name)){
			hql.append(" and m.name like '%"+name+"%'");
		}
		if(null !=url && !"".equals(url)){
			hql.append(" and m.url like '%"+url+"%'");
		}
		hql.append(" order by m.menuKind.order , m.order");
		Query query=this.getSession().createQuery(hql.toString());
		return query.list();
	}
	
	/**
	 * @content 查询某类型的所有菜单
	 * @param id 菜单类型ID
	 * @return
	 */
	public List<Object> findBymenuKindId(Long id){
		String hql=" select m.id from Menu m where m.menuKind.id=? ";
		Query query=this.getSession().createQuery(hql.toString());
		query.setLong(0, id);
		return query.list();
	}
	
	/**
	 * @content 查询某类型的所有菜单
	 * @param id 菜单类型ID
	 * @return
	 */
	public List<Menu> findBymenuKindMenu(Long id){
		String hql=" from Menu m where m.menuKind.id=? ";
		Query query=this.getSession().createQuery(hql.toString());
		query.setLong(0, id);
		return query.list();
	}
	
	/**
	 * @content 删除某些些指定的菜单
	 * @param id
	 */
	public void deleteInID(String id){
		String hql=" delete from Menu m where m.id in ("+id+") ";
		Query query=this.getSession().createQuery(hql.toString());
		query.executeUpdate();
	}
}
