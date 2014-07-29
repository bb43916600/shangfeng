package com.bb2004.entity.kaka;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.bb2004.common.IdEntity;

@Entity
@Table(name="T_HolidayTime")
public class HolidayTime extends IdEntity{
	private Date settingTime;//节假日设置
	private Date endTime;//结束时间
	private String remark;//备注
	private Date inputTime;//录入时间
	
	/** 节假日设置  */
	public Date getSettingTime() {
		return settingTime;
	}
	/** 节假日设置  */
	public void setSettingTime(Date settingTime) {
		this.settingTime = settingTime;
	}
	/** 录入时间  */
	public Date getInputTime() {
		return inputTime;
	}
	/** 录入时间  */
	public void setInputTime(Date inputTime) {
		this.inputTime = inputTime;
	}
	/** 结束时间 不进行持久化（不和数据库打交道） */
	@Transient
	public Date getEndTime() {
		return endTime;
	}
	/** 结束时间 不进行持久化（不和数据库打交道） */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	/** 备注  */
	public String getRemark() {
		return remark;
	}
	/** 备注  */
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
