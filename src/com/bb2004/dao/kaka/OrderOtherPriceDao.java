package com.bb2004.dao.kaka;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Component;

import com.bb2004.common.HibernateDao;
import com.bb2004.entity.authority.User;
import com.bb2004.entity.kaka.OrderOtherPrice;

@Component
public class OrderOtherPriceDao extends HibernateDao<OrderOtherPrice> {
	
	/**
	 * @content Object 查询
	 * @return
	 */
	public List<OrderOtherPrice> listObjecr(OrderOtherPrice orderOtherPrice,int curpage,int pagesize){
		StringBuffer hql=addHql(orderOtherPrice);
		Query query=this.getSession().createQuery(hql.toString());
		query.setFirstResult((curpage-1)*pagesize);
		query.setMaxResults(pagesize);
		return query.list();
	}
	
	/**
	 * @content 根据订单ID查询额外
	 * @param orderId
	 * @return
	 */
	public List findOrderOtherPriceByOderId(Long orderId) {
		String hql=" select op from OrderOtherPrice ool,OtherPrice op where ool.orderOtherId = op.id and ool.orderId="+orderId;
		Query query=this.getSession().createQuery(hql);
		return query.list();
	}
	
	/**
	 * @content 所有查询经过这层关卡
	 * @return hql;
	 */
	public StringBuffer addHql(OrderOtherPrice orderOtherPrice){
		StringBuffer hql=new StringBuffer(" from OrderOtherPrice t where 1=1 ");
		if(orderOtherPrice == null)
			return hql;
		if(orderOtherPrice.getOrderId()!=null && orderOtherPrice.getOrderId()>0){
			hql.append(" and t.orderId = "+orderOtherPrice.getOrderId());
		}
		if(orderOtherPrice.getOrderOtherId()!=null && orderOtherPrice.getOrderOtherId()>0){
			hql.append(" and t.orderOtherId = "+orderOtherPrice.getOrderOtherId());
		}
		hql.append(" order by t.inputTime desc");
		return hql;
	}
	
}
