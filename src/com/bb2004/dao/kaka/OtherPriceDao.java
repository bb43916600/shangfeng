package com.bb2004.dao.kaka;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Component;

import com.bb2004.common.HibernateDao;
import com.bb2004.entity.authority.User;
import com.bb2004.entity.kaka.OtherPrice;

@Component
public class OtherPriceDao extends HibernateDao<OtherPrice> {
	
	/**
	 * @content Object 查询
	 * @return
	 */
	public List<OtherPrice> listObjecr(OtherPrice otherPrice,int curpage,int pagesize){
		StringBuffer hql=addHql(otherPrice);
		Query query=this.getSession().createQuery(hql.toString());
		query.setFirstResult((curpage-1)*pagesize);
		query.setMaxResults(pagesize);
		return query.list();
	}
	
	/**
	 * @content 查询所有子类
	 * @param parentId
	 * @return
	 */
	public List findbyParentId(Long parentId){
		String hql = "from OtherPrice t where t.parentId = "+parentId;
		Query query=this.getSession().createQuery(hql.toString());
		return query.list();
	}
	
	/**
	 * @content 所有查询经过这层关卡
	 * @return hql;
	 */
	public StringBuffer addHql(OtherPrice otherPrice){
		StringBuffer hql=new StringBuffer(" from OtherPrice t where 1=1 ");
		if(otherPrice == null){
			hql.append(" order by t.inputTime desc");
			return hql;
		}
			
		if(otherPrice.getCt_otherName()!=null && !otherPrice.getCt_otherName().equals("")){
			hql.append(" and t.ct_otherName like '%"+otherPrice.getCt_otherName()+"%'");
		}
		if(otherPrice.getEn_otherName()!=null && !otherPrice.getEn_otherName().equals("")){
			hql.append(" and t.en_otherName like '%"+otherPrice.getEn_otherName()+"%'");
		}
		if(otherPrice.getCn_otherName()!=null && !otherPrice.getCn_otherName().equals("")){
			hql.append(" and t.cn_otherName like '%"+otherPrice.getCn_otherName()+"%'");
		}
		if(otherPrice.getParentId()!=null && otherPrice.getParentId()>=0){
			hql.append(" and t.parentId = "+otherPrice.getParentId());
		}
		hql.append(" order by t.inputTime desc");
		return hql;
	}
	
}
