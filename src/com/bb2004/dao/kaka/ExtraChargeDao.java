package com.bb2004.dao.kaka;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Component;

import com.bb2004.common.HibernateDao;
import com.bb2004.entity.kaka.ExtraCharge;

@Component
public class ExtraChargeDao extends HibernateDao<ExtraCharge> {
	
	/**
	 * @content Object 查询
	 * @return
	 */
	public List<ExtraCharge> listObjecr(ExtraCharge extraCharge,int curpage,int pagesize){
		StringBuffer hql=addHql(extraCharge);
		Query query=this.getSession().createQuery(hql.toString());
		query.setFirstResult((curpage-1)*pagesize);
		query.setMaxResults(pagesize);
		return query.list();
	}
	
	/**
	 * @content 所有查询经过这层关卡
	 * @return hql;
	 */
	public StringBuffer addHql(ExtraCharge extraCharge){
		StringBuffer hql=new StringBuffer(" from ExtraCharge t where 1=1 ");
		if(extraCharge == null)
			return hql;
		if(extraCharge.getCt_content()!=null && !extraCharge.getCt_content().equals("")){
			hql.append(" and t.ct_content like '%"+extraCharge.getCt_content()+"%'");
		}
		if(extraCharge.getEn_content()!=null && !extraCharge.getEn_content().equals("")){
			hql.append(" and t.en_content like '%"+extraCharge.getEn_content()+"%'");
		}
		if(extraCharge.getCn_content()!=null && !extraCharge.getCn_content().equals("")){
			hql.append(" and t.cn_content like '%"+extraCharge.getCn_content()+"%'");
		}
		hql.append(" order by t.inputTime desc");
		return hql;
	}
	
}
