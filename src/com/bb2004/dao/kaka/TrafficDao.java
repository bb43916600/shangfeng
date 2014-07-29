package com.bb2004.dao.kaka;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Component;

import com.bb2004.common.HibernateDao;
import com.bb2004.entity.kaka.Traffic;

@Component
public class TrafficDao extends HibernateDao<Traffic> {
	
	/**
	 * @content Object 查询
	 * @return
	 */
	public List<Traffic> listObjecr(Traffic traffic,int curpage,int pagesize){
		StringBuffer hql=addHql(traffic);
		Query query=this.getSession().createQuery(hql.toString());
		query.setFirstResult((curpage-1)*pagesize);
		query.setMaxResults(pagesize);
		return query.list();
	}
	
	/**
	 * @content 所有查询经过这层关卡
	 * @return hql;
	 */
	public StringBuffer addHql(Traffic traffic){
		StringBuffer hql=new StringBuffer(" from Traffic t where 1=1 ");
		if(traffic == null)
			return hql;
		if(traffic.getCt_content()!=null && !traffic.getCt_content().equals("")){
			hql.append(" and t.ct_content like '%"+traffic.getCt_content()+"%'");
		}
		if(traffic.getEn_content()!=null && !traffic.getEn_content().equals("")){
			hql.append(" and t.en_content like '%"+traffic.getEn_content()+"%'");
		}
		if(traffic.getCn_content()!=null && !traffic.getCn_content().equals("")){
			hql.append(" and t.cn_content like '%"+traffic.getCn_content()+"%'");
		}
		hql.append(" order by t.inputTime desc");
		return hql;
	}
	
}
