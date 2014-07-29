package com.bb2004.dao.kaka;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Component;

import com.bb2004.common.HibernateDao;
import com.bb2004.entity.kaka.HolidayPrice;

@Component
public class HolidayPriceDao extends HibernateDao<HolidayPrice> {
	
//	/**
//	 * @content Object 查询
//	 * @return
//	 */
	public List<HolidayPrice> listObjecr(HolidayPrice holidayPrice,int curpage,int pagesize){
		StringBuffer hql=addHql(holidayPrice);
		Query query=this.getSession().createQuery(hql.toString());
		query.setFirstResult((curpage-1)*pagesize);
		query.setMaxResults(pagesize);
		return query.list();
	}
//	
//	
//	/**
//	 * @content 所有查询经过这层关卡
//	 * @return hql;
//	 */
	public StringBuffer addHql(HolidayPrice holidayPrice){
		StringBuffer hql=new StringBuffer(" from HolidayPrice t where 1=1 ");
		if(holidayPrice == null)
			return hql;
//		hql.append(" order by t.inputTime desc");
		return hql;
	}
	
}
