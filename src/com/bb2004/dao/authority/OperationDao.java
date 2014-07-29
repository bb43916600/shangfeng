package com.bb2004.dao.authority;

import java.util.List;
import org.hibernate.Query;
import org.springframework.stereotype.Component;

import com.bb2004.common.HibernateDao;
import com.bb2004.entity.authority.Operation;

@Component
public class OperationDao extends HibernateDao<Operation>{
	
	/**
	 * @content 查询所有菜单 或者条件查询
	 * @param name 权限的名称
	 * @param name 权限的url
	 * @return List<Operation>
	 */
	public List<Operation> operationList(String name,String url){
		StringBuffer hql=new StringBuffer(" from Operation o where 1=1 ");
		if(null !=name && !"".equals(name)){
			hql.append(" and o.name like '%"+name+"%'");
		}
		if(null !=url && !"".equals(url)){
			hql.append(" and o.url like '%"+url+"%'");
		}
		Query query=this.getSession().createQuery(hql.toString());
		return query.list();
	}
}
