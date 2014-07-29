package com.bb2004.dao.kaka;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Component;

import com.bb2004.common.HibernateDao;
import com.bb2004.entity.kaka.City;
import com.sun.org.apache.xml.internal.utils.IntVector;

@Component
public class CityDao extends HibernateDao<City> {
	
	/**
	 * @content Object 查询
	 * @return
	 */
	public List<City> listObjecr(City city,int curpage,int pagesize){
		StringBuffer hql=addHql(city);
		Query query=this.getSession().createQuery(hql.toString());
		query.setFirstResult((curpage-1)*pagesize);
		query.setMaxResults(pagesize);
		return query.list();
	}
	
	/**
	 * @content 所有查询经过这层关卡
	 * @return hql;
	 */
	public StringBuffer addHql(City city){
		StringBuffer hql=new StringBuffer(" from City t where 1=1 ");
		if(city == null)
			return hql;
		if(city.getCt_cityName()!=null && !city.getCt_cityName().equals("")){
			hql.append(" and t.ct_cityName like '%"+city.getCt_cityName()+"%'");
		}
		if(city.getEn_cityName()!=null && !city.getEn_cityName().equals("")){
			hql.append(" and t.en_cityName like '%"+city.getEn_cityName()+"%'");
		}
		if(city.getCn_cityName()!=null && !city.getCn_cityName().equals("")){
			hql.append(" and t.cn_cityName like '%"+city.getCn_cityName()+"%'");
		}
		hql.append(" order by t.inputTime desc");
		return hql;
	}
	
	/**
	 * @content 查询所有总数
	 * @return hql;
	 */
	public int addHqlCount(City city){
		StringBuffer hql=new StringBuffer(" select count(t.id) from City t where 1=1 ");
		if(city!=null){
			if(city.getCt_cityName()!=null && !city.getCt_cityName().equals("")){
				hql.append(" and t.ct_cityName like '%"+city.getCt_cityName()+"%'");
			}
			if(city.getEn_cityName()!=null && !city.getEn_cityName().equals("")){
				hql.append(" and t.en_cityName like '%"+city.getEn_cityName()+"%'");
			}
			if(city.getCn_cityName()!=null && !city.getCn_cityName().equals("")){
				hql.append(" and t.cn_cityName like '%"+city.getCn_cityName()+"%'");
			}
		}
		
		Query query=this.getSession().createQuery(hql.toString());
		return Integer.valueOf(query.list().get(0).toString());
	}
	
	/**
	 * @content 根据城市名称查询城市
	 * @return City;
	 */
	public City getCityByName(City city){
		StringBuffer hql=new StringBuffer(" from City t where 1=1 ");
		if(city == null)
			return null;
		if(city.getCt_cityName()!=null && !city.getCt_cityName().equals("")){
			hql.append(" and t.ct_cityName = '"+city.getCt_cityName()+"'");
		}
		if(city.getEn_cityName()!=null && !city.getEn_cityName().equals("")){
			hql.append(" and t.en_cityName = '"+city.getEn_cityName()+"'");
		}
		if(city.getCn_cityName()!=null && !city.getCn_cityName().equals("")){
			hql.append(" and t.cn_cityName = '"+city.getCn_cityName()+"'");
		}
		hql.append(" order by t.inputTime desc");
		Query query=this.getSession().createQuery(hql.toString());
		if(query.list().size()>0){
			return (City)query.list().get(0);
		}else {
			return null;
		}
	}
	
	/**
	 * 
	 * @param startName 开始名称
	 * @param endName 结束名称
	 * @return
	 */
	public List getStartEndLine(String startName,String endName){
		if(startName==null || startName.trim().equals("") || endName==null || endName.trim().equals(""))
			return new ArrayList();
		String hql =  "from City t where t.ct_cityName in ('"+startName+"','"+endName+"') or t.en_cityName in ('"+startName+"','"+endName+"') or t.cn_cityName in ('"+startName+"','"+endName+"')";
		Query query=this.getSession().createQuery(hql);
		return query.list();
	}
	
}
