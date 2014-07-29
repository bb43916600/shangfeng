package com.bb2004.entity.kaka;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.bb2004.common.IdEntity;

/**
 * @conyent 线路价钱表
 * @author longhuaxiong
 *
 */

@Entity
@Table(name="T_LinePrice")
public class LinePrice extends IdEntity {
	
	private Long startCityId;//开始ID
	private Long endCityId;//结束ID
	private float price;//价钱
	private Date inputTime;//录入时间
	
	/** 开始ID  */
	public Long getStartCityId() {
		return startCityId;
	}
	/** 开始ID  */
	public void setStartCityId(Long startCityId) {
		this.startCityId = startCityId;
	}
	/** 结束ID  */
	public Long getEndCityId() {
		return endCityId;
	}
	/** 结束ID  */
	public void setEndCityId(Long endCityId) {
		this.endCityId = endCityId;
	}
	/** 价钱  */
	public float getPrice() {
		return price;
	}
	/** 价钱  */
	public void setPrice(float price) {
		this.price = price;
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
