package com.bb2004.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Component;

import com.bb2004.common.HibernateDao;
import com.bb2004.entity.kaka.AppUser;

@Component
public class AllDao<T> extends HibernateDao<T> {
	
	/**
	 * @content Object 查询
	 * @return
	 */
	public List<T> listObjecr(String hql,int curpage,int pagesize){
		Query query=this.getSession().createQuery(hql);
		query.setFirstResult((curpage-1)*pagesize);
		query.setMaxResults(pagesize);
		return query.list();
	}
	
	public int listObjecrCount(String hql){
		Query query=this.getSession().createQuery(hql);
		return Integer.valueOf(query.list().get(0).toString());
	}
}
