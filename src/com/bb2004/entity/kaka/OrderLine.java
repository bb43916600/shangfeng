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
@Table(name="T_OrderLine")
public class OrderLine extends IdEntity {
	
	private Long orderId;//订单ID
	private Long linePriceId;//线路ID
	private Date inputTime;//录入时间
	
	/** 订单ID  */
	public Long getOrderId() {
		return orderId;
	}
	/** 订单ID  */
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	/** 线路ID  */
	public Long getLinePriceId() {
		return linePriceId;
	}
	/** 线路ID  */
	public void setLinePriceId(Long linePriceId) {
		this.linePriceId = linePriceId;
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
