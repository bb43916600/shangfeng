package com.bb2004.dao.kaka;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Component;

import com.bb2004.common.HibernateDao;
import com.bb2004.entity.authority.User;
import com.bb2004.entity.kaka.City;
import com.bb2004.entity.kaka.LinePrice;

@Component
public class LinePriceDao extends HibernateDao<LinePrice> {
	
	/**
	 * @content Object 查询
	 * @return
	 */
	public List<LinePrice> listObjecr(LinePrice linePrice,int curpage,int pagesize){
		StringBuffer hql=addHql(linePrice);
		Query query=this.getSession().createQuery(hql.toString());
		query.setFirstResult((curpage-1)*pagesize);
		query.setMaxResults(pagesize);
		return query.list();
	}
	
	/**
	 * @content 根据线路的开始和结束ID查询线路
	 * @param startCityId
	 * @param endCityId
	 * @return
	 */
	public LinePrice findLinePriceByStartCityIdAndEndCityId(Long startCityId,Long endCityId){
		String hql = " from LinePrice t where t.startCityId = "+startCityId+" and  t.endCityId = "+endCityId;
		Query query=this.getSession().createQuery(hql);
		if(query.list().size()>0){
			return (LinePrice)query.list().get(0);
		}else {
			return null;
		}
	}
	
	/**
	 * @content 根据线路的开始和结束名称查询线路
	 * @param startCityId
	 * @param endCityId
	 * @return
	 */
	public LinePrice findLinePriceByStartCityNameAndEndCityName(String startCityName,String endCityName,String currentLanguage){
		String hql = " from LinePrice t where t.startCityId = (select c.id from City c where c."+currentLanguage+"_cityName = '"+startCityName+"') and  t.endCityId = (select c.id from City c where c."+currentLanguage+"_cityName = '"+endCityName+"')  ";
		
		Query query=this.getSession().createQuery(hql);
		if(query.list().size()>0){
			return (LinePrice)query.list().get(0);
		}else {
			return null;
		}
	}
	
	/**
	 * @content 所有查询经过这层关卡
	 * @return hql;
	 */
	public StringBuffer addHql(LinePrice linePrice){
		StringBuffer hql=new StringBuffer(" from LinePrice t where 1=1 ");
		if(linePrice == null)
			return hql;
		hql.append(" order by t.inputTime desc");
		return hql;
	}
	
	/**
	 * @content 查询所有总数
	 * @return hql;
	 */
	public int addHqlCount(LinePrice linePrice){
		StringBuffer hql=new StringBuffer(" select count(t.id) from LinePrice t where 1=1 ");
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
