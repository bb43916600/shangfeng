package com.bb2004.entity.kaka;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.bb2004.common.IdEntity;

/**
 * @conyent 区域表
 * @author longhuaxiong
 *
 */

@Entity
@Table(name="T_Area")
public class Area extends IdEntity {
	
	private String ct_areaName;//地区名称（繁体）
	private String en_areaName;//地区名称 (英文)
	private String cn_areaName;//地区名称 (简体)
	private Date inputTime;//录入时间
	private Long initFlag;//备用字段1
	private String timeFlag;//备用字段2
	
	/** 地区名称（繁体）  */
	public String getCt_areaName() {
		return ct_areaName;
	}
	/** 地区名称（繁体）  */
	public void setCt_areaName(String ct_areaName) {
		this.ct_areaName = ct_areaName;
	}
	/** 地区名称（英文）  */
	public String getEn_areaName() {
		return en_areaName;
	}
	/** 地区名称（英文）  */
	public void setEn_areaName(String en_areaName) {
		this.en_areaName = en_areaName;
	}
	/** 地区名称（简体）  */
	public String getCn_areaName() {
		return cn_areaName;
	}
	/** 地区名称（简体）  */
	public void setCn_areaName(String cn_areaName) {
		this.cn_areaName = cn_areaName;
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
