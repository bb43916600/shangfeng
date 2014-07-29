package com.bb2004.dao.kaka;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Component;

import com.bb2004.common.HibernateDao;
import com.bb2004.entity.kaka.Rules;

@Component
public class RulesDao extends HibernateDao<Rules> {
	
	/**
	 * @content Object 查询
	 * @return
	 */
	public List<Rules> listObjecr(Rules rules,int curpage,int pagesize){
		StringBuffer hql=addHql(rules);
		Query query=this.getSession().createQuery(hql.toString());
		query.setFirstResult((curpage-1)*pagesize);
		query.setMaxResults(pagesize);
		return query.list();
	}
	
	/**
	 * @content 所有查询经过这层关卡
	 * @return hql;
	 */
	public StringBuffer addHql(Rules rules){
		StringBuffer hql=new StringBuffer(" from Rules t where 1=1 ");
		if(rules == null)
			return hql;
		if(rules.getCt_content()!=null && !rules.getCt_content().equals("")){
			hql.append(" and t.ct_content like '%"+rules.getCt_content()+"%'");
		}
		if(rules.getEn_content()!=null && !rules.getEn_content().equals("")){
			hql.append(" and t.en_content like '%"+rules.getEn_content()+"%'");
		}
		if(rules.getCn_content()!=null && !rules.getCn_content().equals("")){
			hql.append(" and t.cn_content like '%"+rules.getCn_content()+"%'");
		}
		hql.append(" order by t.inputTime desc");
		return hql;
	}
	
}
