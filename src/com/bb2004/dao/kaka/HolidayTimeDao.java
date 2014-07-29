package com.bb2004.dao.kaka;

import java.text.SimpleDateFormat;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Component;

import com.bb2004.common.HibernateDao;
import com.bb2004.entity.kaka.HolidayTime;

@Component
public class HolidayTimeDao extends HibernateDao<HolidayTime> {
	
	/**
	 * @content Object 查询
	 * @return
	 */
	public List<HolidayTime> listObjecr(HolidayTime holidayTime,int curpage,int pagesize){
		StringBuffer hql=addHql(holidayTime);
		Query query=this.getSession().createQuery(hql.toString());
		query.setFirstResult((curpage-1)*pagesize);
		query.setMaxResults(pagesize);
		return query.list();
	}
	
	/**
	 * @content 所有查询经过这层关卡
	 * @return hql;
	 */
	public StringBuffer addHql(HolidayTime holidayTime){
		StringBuffer hql=new StringBuffer(" from HolidayTime t where 1=1 ");
		if(holidayTime == null)
			return hql;
		if(holidayTime.getSettingTime()!=null && !holidayTime.getSettingTime().equals("")){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			hql.append(" and t.settingTime = '"+sdf.format(holidayTime.getSettingTime())+"'");
		}
		hql.append(" order by t.inputTime desc");
		return hql;
	}
	
	/**
	 * @content 查询所有总数
	 * @return hql;
	 */
	public int addHqlCount(HolidayTime holidayTime){
		StringBuffer hql=new StringBuffer(" select count(t.id) from HolidayTime t where 1=1 ");
//		if(city!=null){
//			if(city.getCt_cityName()!=null && !city.getCt_cityName().equals("")){
//				hql.append(" and t.ct_cityName like '%"+city.getCt_cityName()+"%'");
//			}
//			if(city.getEn_cityName()!=null && !city.getEn_cityName().equals("")){
//				hql.append(" and t.en_cityName like '%"+city.getEn_cityName()+"%'");
//			}
//			if(city.getCn_cityName()!=null && !city.getCn_cityName().equals("")){
//				hql.append(" and t.cn_cityName like '%"+city.getCn_cityName()+"%'");
//			}
//		}
		Query query=this.getSession().createQuery(hql.toString());
		return Integer.valueOf(query.list().get(0).toString());
	}
	
}
