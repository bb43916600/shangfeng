package com.bb2004.dao.authority;


import java.util.List;
import org.hibernate.Query;
import org.springframework.stereotype.Component;

import com.bb2004.common.HibernateDao;
import com.bb2004.entity.authority.MenuKind;

@Component
public class MenuKindDao extends HibernateDao<MenuKind>{
	
	/**
	 * @content 查询所有菜单类型 或者条件查询
	 * @return List<MenuKind>
	 */
	public List<Object> menukindList(){
		StringBuffer hql=new StringBuffer(" select * from WAP_MENUKIND m ");
		hql.append(" order by m.SEQENCING");
		Query query=this.getSession().createSQLQuery(hql.toString());
		return query.list();
	}
	
	/**
	 * @content 查询所有菜单类型 或者条件查询
	 * @param name 菜单类型的名称
	 * @return List<MenuKind>
	 */
	public List<Object> menukindList(String name){
		StringBuffer hql=new StringBuffer(" select * from WAP_MENUKIND m where m.MENU_KIND_ID is null ");
		if(null !=name && !"".equals(name)){
			hql.append(" and m.NAME like '%"+name+"%'");
		}
		hql.append(" order by m.SEQENCING");
		Query query=this.getSession().createSQLQuery(hql.toString());
		return query.list();
	}
	
	/**
	 * @content 首页查询所有栏目使用
	 * @return List<MenuKind>
	 */
	public List menukindIndex(){
		StringBuffer sql=new StringBuffer(" select m.* from WAP_MENUKIND m where m.MENU_KIND_ID is null ");
		sql.append(" order by m.SEQENCING");
		Query query=this.getSession().createSQLQuery(sql.toString()).addEntity("m",MenuKind.class);
		return query.list();
	}
	
	/**
	 * @content getMenuKind   原生SQL查询
	 * @param id 类型ID 
	 * @return MenuKind   原生SQL 查询出来再强转实体类
	 */
	public MenuKind getMenuKind(Long id){
		String sql="select m.* from WAP_MENUKIND m where m.id="+id+" order by m.SEQENCING ";
		Query query=this.getSession().createSQLQuery(sql.toString()).addEntity("m",MenuKind.class);
		if(query.list().size()>0){
			return (MenuKind)query.list().get(0);
		}else{
			return null;
		}
	}
	
	/**
	 * @content 根据名字查询栏目
	 * @return
	 */
	public MenuKind findByName(String name){
		String hql=" from MenuKind mk where mk.name=? ";
		Query query=this.getSession().createQuery(hql);
		query.setString(0, name);
		if(query.list().size()>0){
			return (MenuKind)query.list().get(0);
		}else{
			return null;
		}
	}
}
