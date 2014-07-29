package com.bb2004.dao.kaka;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Component;

import com.bb2004.common.HibernateDao;
import com.bb2004.entity.kaka.Order;

@Component
public class OrderDao extends HibernateDao<Order> {
	
	/**
	 * @content Object 查询
	 * @return
	 */
	public List<Order> listObjecr(Order order,int curpage,int pagesize){
		StringBuffer hql=addHql(order);
		Query query=this.getSession().createQuery(hql.toString());
		query.setFirstResult((curpage-1)*pagesize);
		query.setMaxResults(pagesize);
		return query.list();
	}
	
	/**
	 * @content 根据订单ID查询订单的状态
	 * @param orderNo
	 * @return
	 */
	public List findArrayOrderState(String orderNo) {
		String hql=" select o.orderNo,o.orderState,o.driverName,o.driverPhone from Order o where o.orderNo in ("+orderNo+")";
		Query query=this.getSession().createQuery(hql);
		return query.list();
	}
	
	/**
	 * @content 查询最大的OrderNo
	 * @param orderNo
	 */
	public int findMaxOrderNo(String orderNo) {
		String hql= "select t.orderNo from Order t where t.orderNo like '"+orderNo+"%' order by t.inputTime desc";
		Query query=this.getSession().createQuery(hql);
		int maxNumber = 0;
		if(query.list().size()>0){
			String tenmpString = query.list().get(0).toString();
			maxNumber = Integer.valueOf(tenmpString.substring(tenmpString.length()-4));
		}
		return maxNumber;
	}
	
	/**
	 * @content content APP显示所有没接的订单
	 * @return
	 */
	public List wapList(Long id,String carType){
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		String today = sdf.format(new Date());
		//and t.usetime >= '"+today+"'  之前只查今天的订单
		
		//车型
		if(carType.toLowerCase().contains("van")) 
			carType = "'Van仔','Van'";
		else if(carType.contains("5.5"))
			carType = "'5.5 ton truck','5.5吨货车','5.5噸貨車'";
		else 
			carType = "'9噸貨車','9 ton truck','9吨货车'";
		
		String hql = " from Order t where (t.orderState = 0  and carType in ("+carType+"))  or (t.appUserId = "+id+" and t.orderState = 1) order by t.inputTime desc";
		Query query=this.getSession().createQuery(hql);
		return query.list();
	}
	
	/**
	 * @content 更新订单状态
	 * @param appUserId
	 * @param orderNo
	 * @param orderState
	 */
	public void upDateState(Long appUserId,String orderNo) {
		String hql=" UPDATE Order SET orderState = 1  where appUserId = "+appUserId+" and orderNo = '"+orderNo+"'";
		Query query=this.getSession().createQuery(hql);
		query.executeUpdate();
	}
	
	/**
	 * @content 根绝订单号码查询订单
	 * @param orderNo
	 */
	public Order findByOrderNo(String orderNo,int orderState) {
		String hql = " from Order t where t.orderNo = '"+ orderNo +"' and t.orderState = "+orderState+"";
		Query query=this.getSession().createQuery(hql);
		if(query.list().size()>0){
			return (Order)query.list().get(0);
		}else{
			return null;
		}
	}
	
	/**
	 * @content 根绝订单号码查询订单
	 * @param orderNo
	 */
	public Order findByOrderNo(String orderNo) {
		String hql = " from Order t where t.orderNo = '"+ orderNo +"'";
		Query query=this.getSession().createQuery(hql);
		if(query.list().size()>0){
			return (Order)query.list().get(0);
		}else{
			return null;
		}
	}
	
	/**
	 * @content 根据用户的手机号码统计，用户一共完成了几次单
	 * @param userPhone
	 * @return
	 */
	public int countNumberFnishOrder(String userPhone){
		String hql = "select count(t.id) from Order t where t.userPhone = '"+userPhone+"' and t.orderState = 2 ";
		Query query=this.getSession().createQuery(hql);
		return Integer.valueOf(query.list().get(0).toString());
	}
	
	/**
	 * @content 统计订单的数量和总价钱
	 * @param toDay
	 * @return
	 */
	public List findbyToDayOrder(String toDay,int state,Long appUserId){
		StringBuffer hql = new StringBuffer("select count(t.id),sum(t.orderPrice) from Order t where t.appUserId = "+appUserId+" and t.orderState = "+state+""); 
		if(toDay!=null && !toDay.equals("")){
			hql.append(" and t.inputTime between '"+toDay+" 00:00:00' and '"+toDay+" 23:59:59'");
		}
		Query query=this.getSession().createQuery(hql.toString());
		return query.list();
	}
	
	/**
	 * @content 根据用户的ID和订单的状态查询订单
	 * @param appUserId
	 * @param orderState
	 * @return
	 */
	public List findbyAppuserIdAndOrderState(Long appUserId,int orderState){
		String hql = " from Order t where t.appUserId = "+ appUserId +" and t.orderState = "+orderState+"";
		Query query=this.getSession().createQuery(hql);
		return query.list();
	}
	
	
	/**
	 * @content 所有查询经过这层关卡
	 * @return hql;
	 */
	public StringBuffer addHql(Order order){
		StringBuffer hql=new StringBuffer(" from Order t where 1=1 ");
		if(order == null) {
			hql.append(" order by t.inputTime desc");
			return hql;
		}
			
		if(order.getCarType()!=null && !order.getCarType().equals("")){
			hql.append(" and t.carType like '%"+order.getCarType()+"%'");
		}
		if(order.getOrderNo()!=null && !order.getOrderNo().equals("")){
			hql.append(" and t.orderNo like '"+order.getOrderNo()+"%'");
		}
		if(order.getUserName()!=null && !order.getUserName().equals("")){
			hql.append(" and t.userName like '%"+order.getUserName()+"%'");
		}
		if(order.getUserPhone()!=null && !order.getUserPhone().equals("")){
			hql.append(" and t.userPhone like '%"+order.getUserPhone()+"%'");
		}
		if(order.getStartTime()!=null || order.getEndTime()!=null){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			if(order.getStartTime()!=null && order.getEndTime()!=null){
				hql.append(" and t.inputTime between '"+sdf.format(order.getStartTime())+" 00:00:00' and '"+sdf.format(order.getEndTime())+" 23:59:59'");
			}else if(order.getStartTime()!=null){
				hql.append(" and t.inputTime >= '"+sdf.format(order.getStartTime())+" 00:00:00'");
			}
		}
		
		hql.append(" order by t.inputTime desc");
		return hql;
	}
	
	/**
	 * @content 查询所有总数
	 * @return hql;
	 */
	public int addHqlCount(Order order){
		StringBuffer hql=new StringBuffer(" select count(t.id) from Order t where 1=1 ");
		if(order!=null){
			if(order.getCarType()!=null && !order.getCarType().equals("")){
				hql.append(" and t.carType like '%"+order.getCarType()+"%'");
			}
			if(order.getOrderNo()!=null && !order.getOrderNo().equals("")){
				hql.append(" and t.orderNo like '"+order.getOrderNo()+"%'");
			}
			if(order.getUserName()!=null && !order.getUserName().equals("")){
				hql.append(" and t.userName like '%"+order.getUserName()+"%'");
			}
			if(order.getUserPhone()!=null && !order.getUserPhone().equals("")){
				hql.append(" and t.userPhone like '%"+order.getUserPhone()+"%'");
			}
			if(order.getStartTime()!=null || order.getEndTime()!=null){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				if(order.getStartTime()!=null && order.getEndTime()!=null){
					hql.append(" and t.inputTime between '"+sdf.format(order.getStartTime())+" 00:00:00' and '"+sdf.format(order.getEndTime())+" 23:59:59'");
				}else if(order.getStartTime()!=null){
					hql.append(" and t.inputTime >= '"+sdf.format(order.getStartTime())+" 00:00:00'");
				}
			}
		}
		Query query=this.getSession().createQuery(hql.toString());
		return Integer.valueOf(query.list().get(0).toString());
	}
	
}
