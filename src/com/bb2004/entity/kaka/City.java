package com.bb2004.entity.kaka;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.bb2004.common.IdEntity;

/**
 * @conyent 城市表
 * @author longhuaxiong
 *
 */

@Entity
@Table(name="T_City")
public class City extends IdEntity {
	
	private Long areaId;//所在有区域ID
	private String ct_cityName;//城市名称（繁体）
	private String en_cityName;//城市名称 (英文)
	private String cn_cityName;//城市名称 (简体)
	private Date inputTime;//录入时间
	private Long initFlag;//备用字段1
	private String timeFlag;//备用字段2
	
	/** 所在区域ID  */
	public Long getAreaId() {
		return areaId;
	}
	/** 所在区域ID  */
	public void setAreaId(Long areaId) {
		this.areaId = areaId;
	}
	/** 城市名称（繁体）  */
	public String getCt_cityName() {
		return ct_cityName;
	}
	/** 城市名称（繁体）  */
	public void setCt_cityName(String ct_cityName) {
		this.ct_cityName = ct_cityName;
	}
	/** 城市名称（英文）  */
	public String getEn_cityName() {
		return en_cityName;
	}
	/** 城市名称（英文）  */
	public void setEn_cityName(String en_cityName) {
		this.en_cityName = en_cityName;
	}
	/** 城市名称（简体）  */
	public String getCn_cityName() {
		return cn_cityName;
	}
	/** 城市名称（简体）  */
	public void setCn_cityName(String cn_cityName) {
		this.cn_cityName = cn_cityName;
	}
	/** 录入时间  */
	public Date getInputTime() {
		return inputTime;
	}
	/** 录入时间  */
	public void setInputTime(Date inputTime) {
		this.inputTime = inputTime;
	}
	/** 备用字段1  */
	public Long getInitFlag() {
		return initFlag;
	}
	/** 备用字段1  */
	public void setInitFlag(Long initFlag) {
		this.initFlag = initFlag;
	}
	/** 备用字段2  */
	public String getTimeFlag() {
		return timeFlag;
	}
	/** 备用字段2  */
	public void setTimeFlag(String timeFlag) {
		this.timeFlag = timeFlag;
	}
	
}
