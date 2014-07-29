package com.bb2004.dao.kaka;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Component;

import com.bb2004.common.HibernateDao;
import com.bb2004.entity.authority.User;
import com.bb2004.entity.kaka.OrderLine;

@Component
public class OrderLineDao extends HibernateDao<OrderLine> {
	
	/**
	 * @content Object 查询
	 * @return
	 */
	public List<OrderLine> listObjecr(OrderLine orderLine,int curpage,int pagesize){
		StringBuffer hql=addHql(orderLine);
		Query query=this.getSession().createQuery(hql.toString());
		query.setFirstResult((curpage-1)*pagesize);
		query.setMaxResults(pagesize);
		return query.list();
	}
	
	/**
	 * @content 根据订单ID查询线路
	 * @param orderId
	 * @return
	 */
	public List findOrderLineByOderId(Long orderId) {
		String hql=" select lp from OrderLine ol,LinePrice lp where ol.linePriceId = lp.id and ol.orderId="+orderId;
		Query query=this.getSession().createQuery(hql);
		return query.list();
	}
	
	/**
	 * @content 所有查询经过这层关卡
	 * @return hql;
	 */
	public StringBuffer addHql(OrderLine orderLine){
		StringBuffer hql=new StringBuffer(" from OrderLine t where 1=1 ");
		if(orderLine == null)
			return hql;
		if(orderLine.getOrderId()!=null && orderLine.getOrderId()>0){
			hql.append(" and t.orderId = "+orderLine.getOrderId());
		}
		if(orderLine.getLinePriceId()!=null && orderLine.getLinePriceId()>0){
			hql.append(" and t.linePriceId = "+orderLine.getLinePriceId());
		}
		hql.append(" order by t.inputTime desc");
		return hql;
	}
	
}
