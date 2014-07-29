package com.bb2004.entity.kaka;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.bb2004.common.IdEntity;

/**
 * @conyent 弹射记录表
 * @author longhuaxiong
 *
 */

@Entity
@Table(name="T_GiveUpOrder")
public class GiveUpOrder extends IdEntity {
	
	private Long appUserId;//前台货车司机ID
	private Long orderId;//订单ID
	private Date inputTime;//录入时间
	
	/** 前台货车司机ID **/
	public Long getAppUserId() {
		return appUserId;
	}
	/** 前台货车司机ID **/
	public void setAppUserId(Long appUserId) {
		this.appUserId = appUserId;
	}
	/** 订单ID **/
	public Long getOrderId() {
		return orderId;
	}
	/** 订单ID **/
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	/** 录入时间 **/
	public Date getInputTime() {
		return inputTime;
	}
	/** 录入时间 **/
	public void setInputTime(Date inputTime) {
		this.inputTime = inputTime;
	}
	
}
