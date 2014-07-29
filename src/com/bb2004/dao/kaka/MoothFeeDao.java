package com.bb2004.dao.kaka;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Component;

import com.bb2004.common.HibernateDao;
import com.bb2004.entity.kaka.MoothFee;

@Component
public class MoothFeeDao extends HibernateDao<MoothFee> {
	
	/**
	 * @content Object 查询
	 * @return
	 */
	public List<MoothFee> listObjecr(MoothFee fee,int curpage,int pagesize){
		StringBuffer hql=addHql(fee);
		Query query=this.getSession().createQuery(hql.toString());
		query.setFirstResult((curpage-1)*pagesize);
		query.setMaxResults(pagesize);
		return query.list();
	}
	
	/**
	 * @content 所有查询经过这层关卡
	 * @return hql;
	 */
	public StringBuffer addHql(MoothFee fee){
		StringBuffer hql=new StringBuffer(" from MoothFee t where 1=1 ");
		if(fee == null)
			return hql;
		if(fee.getCt_content()!=null && !fee.getCt_content().equals("")){
			hql.append(" and t.ct_content like '%"+fee.getCt_content()+"%'");
		}
		if(fee.getEn_content()!=null && !fee.getEn_content().equals("")){
			hql.append(" and t.en_content like '%"+fee.getEn_content()+"%'");
		}
		if(fee.getCn_content()!=null && !fee.getCn_content().equals("")){
			hql.append(" and t.cn_content like '%"+fee.getCn_content()+"%'");
		}
		hql.append(" order by t.inputTime desc");
		return hql;
	}
	
}
