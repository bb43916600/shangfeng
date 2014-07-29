package com.bb2004.dao.kaka;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Component;

import com.bb2004.common.HibernateDao;
import com.bb2004.entity.kaka.DeviceToken;

@Component
public class DeviceTokenDao extends HibernateDao<DeviceToken> {
	
	/**
	 * @content Object 查询
	 * @return
	 */
	public List<String> listObjecr(DeviceToken deviceToken,int curpage,int pagesize){
		StringBuffer hql=addHql(deviceToken);
		Query query=this.getSession().createQuery(hql.toString());
		query.setFirstResult((curpage-1)*pagesize);
		query.setMaxResults(pagesize);
		return query.list();
	}
	
	
	/**
	 * @content 所有查询经过这层关卡
	 * @return hql;
	 */
	public StringBuffer addHql(DeviceToken deviceToken){
		StringBuffer hql=new StringBuffer(" select t.deviceToken from DeviceToken t where 1=1 ");
		if(deviceToken == null)
			return hql;
		if(deviceToken.getDeviceToken()!=null && !deviceToken.getDeviceToken().equals("")){
			hql.append(" and t.deviceToken = '"+deviceToken.getDeviceToken()+"'");
		}
		hql.append(" order by t.inputTime desc");
		return hql;
	}
	
}
