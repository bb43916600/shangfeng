package com.bb2004.entity.kaka;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.bb2004.common.IdEntity;

/**
 * @conyent 订单线路表
 * @author longhuaxiong
 *
 */

@Entity
@Table(name="T_OrderOtherPrice")
public class OrderOtherPrice extends IdEntity {
	
	private Long orderId;//订单ID
	private Long orderOtherId;//线路ID
	private Date inputTime;//录入时间
	
	/** 订单ID  */
	public Long getOrderId() {
		return orderId;
	}
	/** 订单ID  */
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	/** 额外ID  */
	public Long getOrderOtherId() {
		return orderOtherId;
	}
	/** 额外ID  */
	public void setOrderOtherId(Long orderOtherId) {
		this.orderOtherId = orderOtherId;
	}
	/** 录入时间  */
	public Date getInputTime() {
		return inputTime;
	}
	/** 录入时间  */
	public void setInputTime(Date inputTime) {
		this.inputTime = inputTime;
	}
	
}
