package com.bb2004.dao.authority;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Component;

import com.bb2004.common.HibernateDao;
import com.bb2004.entity.authority.Role_Menu;
import com.bb2004.dao.authority.Role_MenuDao;

@Component
public class Role_MenuDao extends HibernateDao<Role_MenuDao>{
	
	/**
	 * @content 查询角色里的某菜单
	 * @param id 菜单ID
	 * @return
	 */
	public List<Role_Menu> findbyMenuId(String id){
		String hql=" from Role_Menu rm where rm.menu_id in ("+id+") ";
		Query query=this.getSession().createQuery(hql);
		return query.list();
	}
	
	/**
	 * @content 删除某菜单
	 * @param id 菜单ID
	 * @return
	 */
	public void deleteByMenuId(String id){
		String hql=" delete from Role_Menu rm where rm.menu_id in ("+id+") ";
		Query query=this.getSession().createQuery(hql);
		query.executeUpdate();
	}
	
	/**
	 * @content 删除某菜单
	 * @param id 角色ID
	 * @return
	 */
	public void deleteByRoleId(String id){
		String hql=" delete from Role_Menu rm where rm.role_id in ("+id+") ";
		Query query=this.getSession().createQuery(hql);
		query.executeUpdate();
	}
}
