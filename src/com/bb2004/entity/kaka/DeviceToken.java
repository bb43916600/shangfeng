package com.bb2004.entity.kaka;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.bb2004.common.IdEntity;

@Entity
@Table(name="T_DeviceToken")
public class DeviceToken extends IdEntity{
	private String deviceToken;//设备的deviceToken
	private Date inputTime;//录入时间

	/** 设备的deviceToken  */
	public String getDeviceToken() {
		return deviceToken;
	}
	/** 设备的deviceToken  */
	public void setDeviceToken(String deviceToken) {
		this.deviceToken = deviceToken;
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
