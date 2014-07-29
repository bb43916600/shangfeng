package com.bb2004.service.kaka;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.bb2004.dao.kaka.OrderOtherPriceDao;
import com.bb2004.entity.authority.User;
import com.bb2004.entity.kaka.OrderOtherPrice;

@Component
@Transactional
public class OrderOtherPriceManage {
	private OrderOtherPriceDao orderOtherPriceDao;
	@Autowired
	public void setOrderOtherPriceDao(OrderOtherPriceDao orderOtherPriceDao) {
		this.orderOtherPriceDao = orderOtherPriceDao;
	}
	
	/**
	 * @content 保存
	 */
	public void save(Object object){
		orderOtherPriceDao.save(object);
	}
	
	/**
	 * @content 根据ID删除
	 * @param id
	 */
	public void delete(Long id){
		orderOtherPriceDao.delete(id);
	}
	
	/**
	 * @content 根据ID获取实体类
	 * @param id
	 */
	public OrderOtherPrice getId(Long id){
		return orderOtherPriceDao.get(id);
	}
	
	/**
	 * @content Object 查询
	 * @return
	 */
	public List<OrderOtherPrice> listObjecr(OrderOtherPrice orderOtherPrice,int curpage,int pagesize){
		return orderOtherPriceDao.listObjecr(orderOtherPrice, curpage, pagesize);
	}
	
	/**
	 * @content 根据订单ID查询线路
	 * @param orderId
	 * @return
	 */
	public List findOrderOtherPriceByOderId(Long orderId) {
		return orderOtherPriceDao.findOrderOtherPriceByOderId(orderId);
	}

}
