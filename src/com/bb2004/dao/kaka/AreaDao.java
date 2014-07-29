package com.bb2004.dao.kaka;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Component;

import com.bb2004.common.HibernateDao;
import com.bb2004.entity.authority.User;
import com.bb2004.entity.kaka.Area;

@Component
public class AreaDao extends HibernateDao<Area> {
	
	/**
	 * @content Object 查询
	 * @return
	 */
	public List<Area> listObjecr(Area area,int curpage,int pagesize){
		StringBuffer hql=addHql(area);
		Query query=this.getSession().createQuery(hql.toString());
		query.setFirstResult((curpage-1)*pagesize);
		query.setMaxResults(pagesize);
		return query.list();
	}
	
	/**
	 * @content 所有查询经过这层关卡
	 * @return hql;
	 */
	public StringBuffer addHql(Area area){
		StringBuffer hql=new StringBuffer(" from Area t where 1=1 ");
		if(area == null)
			return hql;
		if(area.getCt_areaName()!=null && !area.getCt_areaName().equals("")){
			hql.append(" and t.ct_areaName like '%"+area.getCt_areaName()+"%'");
		}
		if(area.getEn_areaName()!=null && !area.getEn_areaName().equals("")){
			hql.append(" and t.en_areaName like '%"+area.getEn_areaName()+"%'");
		}
		if(area.getCn_areaName()!=null && !area.getCn_areaName().equals("")){
			hql.append(" and t.cn_areaName like '%"+area.getCn_areaName()+"%'");
		}
		hql.append(" order by t.inputTime desc");
		return hql;
	}
	
}
