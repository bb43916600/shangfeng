package com.bb2004.dao.kaka;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Component;

import com.bb2004.common.HibernateDao;
import com.bb2004.entity.authority.User;
import com.bb2004.entity.kaka.GiveUpOrder;
import com.bb2004.entity.kaka.LinePrice;

@Component
public class GiveUpOrderDao extends HibernateDao<GiveUpOrder> {
	
	/**
	 * @content Object 查询
	 * @return
	 */
	public List<GiveUpOrder> listObjecr(GiveUpOrder giveUpOrder,int curpage,int pagesize){
		StringBuffer hql=addHql(giveUpOrder);
		Query query=this.getSession().createQuery(hql.toString());
		query.setFirstResult((curpage-1)*pagesize);
		query.setMaxResults(pagesize);
		return query.list();
	}
	
	/**
	 * @content 查询所有弹单记录
	 * @param appUserId
	 * @return
	 */
	public List findbyAppUserId(Long appUserId){
		String hql = "select o from GiveUpOrder g,Order o  where g.orderId = o.id and g.appUserId = "+appUserId;
		Query query=this.getSession().createQuery(hql);
		return query.list();
	}
	
	/**
	 * @content统计用户的弹单数量
	 * @param appUserId
	 * @return
	 */
	public int findbyAppUser(Long appUserId){
		String hql = " select count(t.id) from GiveUpOrder t where t.appUserId ="+appUserId;
		Query query=this.getSession().createQuery(hql.toString());
		return Integer.valueOf(query.list().get(0).toString());
	}
	
	/**
	 * @content 所有查询经过这层关卡
	 * @return hql;
	 */
	public StringBuffer addHql(GiveUpOrder giveUpOrder){
		StringBuffer hql=new StringBuffer(" from GiveUpOrder t where 1=1 ");
		if(giveUpOrder == null)
			return hql;
		if(giveUpOrder.getAppUserId()!=null && !giveUpOrder.getAppUserId().equals("")){
			hql.append(" and t.appUserId = "+giveUpOrder.getAppUserId());
		}
		if(giveUpOrder.getOrderId()!=null && !giveUpOrder.getOrderId().equals("")){
			hql.append(" and t.orderId = "+giveUpOrder.getOrderId());
		}
		hql.append(" order by t.inputTime desc");
		return hql;
	}
	
	/**
	 * @content 查询所有总数
	 * @return hql;
	 */
	public int addHqlCount(GiveUpOrder giveUpOrder){
		StringBuffer hql=new StringBuffer(" select count(t.id) from GiveUpOrder t where 1=1 ");
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
