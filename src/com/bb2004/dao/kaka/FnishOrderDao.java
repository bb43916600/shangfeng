package com.bb2004.dao.kaka;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Component;

import com.bb2004.common.HibernateDao;
import com.bb2004.entity.authority.User;
import com.bb2004.entity.kaka.FnishOrder;
import com.bb2004.entity.kaka.LinePrice;

@Component
public class FnishOrderDao extends HibernateDao<FnishOrder> {
	
	/**
	 * @content Object 查询
	 * @return
	 */
	public List<FnishOrder> listObjecr(FnishOrder fnishOrder,int curpage,int pagesize){
		StringBuffer hql=addHql(fnishOrder);
		Query query=this.getSession().createQuery(hql.toString());
		query.setFirstResult((curpage-1)*pagesize);
		query.setMaxResults(pagesize);
		return query.list();
	}
	
	/**
	 * @content 查询所有历史订单
	 * @param appUserId
	 * @return
	 */
	public List findbyAppUserId(Long appUserId){
		String hql = "select o from FnishOrder f,Order o  where f.orderId = o.id and f.appUserId = "+appUserId;
		Query query=this.getSession().createQuery(hql);
		return query.list();
	}
	
	/**
	 * @content 所有查询经过这层关卡
	 * @return hql;
	 */
	public StringBuffer addHql(FnishOrder fnishOrder){
		StringBuffer hql=new StringBuffer(" from FnishOrder t where 1=1 ");
		if(fnishOrder == null)
			return hql;
		if(fnishOrder.getAppUserId()!=null && !fnishOrder.getAppUserId().equals("")){
			hql.append(" and t.appUserId = "+fnishOrder.getAppUserId());
		}
		if(fnishOrder.getOrderId()!=null && !fnishOrder.getOrderId().equals("")){
			hql.append(" and t.orderId = "+fnishOrder.getOrderId());
		}
		hql.append(" order by t.inputTime desc");
		return hql;
	}
	
	/**
	 * @content 查询所有总数
	 * @return hql;
	 */
	public int addHqlCount(FnishOrder fnishOrder){
		StringBuffer hql=new StringBuffer(" select count(t.id) from FnishOrder t where 1=1 ");
		if(fnishOrder!=null){
			if(fnishOrder.getAppUserId()!=null && !fnishOrder.getAppUserId().equals("")){
				hql.append(" and t.appUserId = "+fnishOrder.getAppUserId());
			}
			if(fnishOrder.getOrderId()!=null && !fnishOrder.getOrderId().equals("")){
				hql.append(" and t.orderId = "+fnishOrder.getOrderId());
			}
		}
		Query query=this.getSession().createQuery(hql.toString());
		return Integer.valueOf(query.list().get(0).toString());
	}
	
}
