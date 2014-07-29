package com.bb2004.service.kaka;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.bb2004.dao.kaka.OrderLineDao;
import com.bb2004.entity.authority.User;
import com.bb2004.entity.kaka.OrderLine;

@Component
@Transactional
public class OrderLineManage {
	private OrderLineDao orderLineDao;
	@Autowired
	public void setOrderLineDao(OrderLineDao orderLineDao) {
		this.orderLineDao = orderLineDao;
	}
	
	/**
	 * @content 保存
	 */
	public void save(Object object){
		orderLineDao.save(object);
	}
	
	/**
	 * @content 根据ID删除
	 * @param id
	 */
	public void delete(Long id){
		orderLineDao.delete(id);
	}
	
	/**
	 * @content 根据ID获取实体类
	 * @param id
	 */
	public OrderLine getId(Long id){
		return orderLineDao.get(id);
	}
	
	/**
	 * @content Object 查询
	 * @return
	 */
	public List<OrderLine> listObjecr(OrderLine orderLine,int curpage,int pagesize){
		return orderLineDao.listObjecr(orderLine, curpage, pagesize);
	}
	
	/**
	 * @content 根据订单ID查询线路
	 * @param orderId
	 * @return
	 */
	public List findOrderLineByOderId(Long orderId) {
		return orderLineDao.findOrderLineByOderId(orderId);
	}

}
