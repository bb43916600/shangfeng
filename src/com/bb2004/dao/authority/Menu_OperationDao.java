package com.bb2004.dao.authority;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Component;

import com.bb2004.common.HibernateDao;
import com.bb2004.entity.authority.Menu_Operation;

@Component
public class Menu_OperationDao extends HibernateDao<Menu_Operation> {
	
	/**
	 * @content 查询某菜单的权限
	 * @param id 菜单ID
	 * @return
	 */
	public List<Menu_Operation> findbyMenuId(String id){
		String hql=" from Menu_Operation mo where mo.menu_id in ("+id+") ";
		Query query=this.getSession().createQuery(hql);
		return query.list();
	}
	
	/**
	 * @content 删除某菜单的权限
	 * @param id 菜单ID
	 * @return
	 */
	public void deletebyMenuId(String id){
		String hql=" delete from Menu_Operation mo where mo.menu_id in ("+id+") ";
		Query query=this.getSession().createQuery(hql);
		query.executeUpdate();
	}
	
	/**
	 * @content 删除某菜单的权限
	 * @param id 权限ID
	 * @return
	 */
	public void deletebyOperationId(Long id){
		String hql=" delete from Menu_Operation mo where mo.operation_id =? ";
		Query query=this.getSession().createQuery(hql);
		query.setLong(0, id);
		query.executeUpdate();
	}
}
