package com.bb2004.entity.kaka;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.bb2004.common.IdEntity;

/**
 * @conyent app用户表
 * @author longhuaxiong
 *
 */

@Entity
@Table(name="T_APPUser")
public class AppUser extends IdEntity {
	
	private String carNumber;//车牌号码
	private String phone;//手机号码
	private String userName;//用户昵称
	private String introducerPhone;//介绍人号码
	private String carType;//车种类
	private String vanCarType;//van仔车类型
	private String factoryDate;//出厂日期
	private String passWorld;//密码
	private String idCardImage;//身份证照片（第一次验证照片）
	private String carNumberImage;//车牌照片（第一次验证照片）
	private String driveCardImage;//行车证照片（第一次验证照片）
	private Date inputTime;//录入时间
	private Date lastTime;//最后次登陆时间
	private int state;//状态(0正常,1禁用)
	private int level;//没验证0，第一次验证为1，第二次为3,第一次验证失败为2
	private String deviceToken;//推送deviceToken
	private Long initFlag;//备用字段1
	private String timeFlag;//备用字段2
	
	
	/** 车牌号码  */
	public String getCarNumber() {
		return carNumber;
	}
	/** 车牌号码  */
	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}
	/** 手机号码  */
	public String getPhone() {
		return phone;
	}
	/** 手机号码  */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/** 称呼  */
	public String getUserName() {
		return userName;
	}
	/** 称呼  */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/** 介绍人手机号码  */
	public String getIntroducerPhone() {
		return introducerPhone;
	}
	/** 介绍人手机号码  */
	public void setIntroducerPhone(String introducerPhone) {
		this.introducerPhone = introducerPhone;
	}
	/** 车种类  */
	public String getCarType() {
		return carType;
	}
	/** 车种类  */
	public void setCarType(String carType) {
		this.carType = carType;
	}
	/** VAN车类型  */
	public String getVanCarType() {
		return vanCarType;
	}
	/** VAN车类型  */
	public void setVanCarType(String vanCarType) {
		this.vanCarType = vanCarType;
	}
	/** 出厂日期  */
	public String getFactoryDate() {
		return factoryDate;
	}
	/** 出厂日期  */
	public void setFactoryDate(String factoryDate) {
		this.factoryDate = factoryDate;
	}
	/** 密码  */
	public String getPassWorld() {
		return passWorld;
	}
	/** 密码  */
	public void setPassWorld(String passWorld) {
		this.passWorld = passWorld;
	}
	/** 身份证照片（第一次验证照片）  */
	public String getIdCardImage() {
		return idCardImage;
	}
	/** 身份证照片（第一次验证照片）  */
	public void setIdCardImage(String idCardImage) {
		this.idCardImage = idCardImage;
	}
	/** 车牌照片（第一次验证照片）  */
	public String getCarNumberImage() {
		return carNumberImage;
	}
	/** 车牌照片（第一次验证照片）  */
	public void setCarNumberImage(String carNumberImage) {
		this.carNumberImage = carNumberImage;
	}
	/** 行车证照片（第一次验证照片）  */
	public String getDriveCardImage() {
		return driveCardImage;
	}
	/** 行车证照片（第一次验证照片）  */
	public void setDriveCardImage(String driveCardImage) {
		this.driveCardImage = driveCardImage;
	}
	/** 录入时间  */
	public Date getInputTime() {
		return inputTime;
	}
	/** 录入时间  */
	public void setInputTime(Date inputTime) {
		this.inputTime = inputTime;
	}
	/** 最后次登陆时间  */
	public Date getLastTime() {
		return lastTime;
	}
	/** 最后次登陆时间  */
	public void setLastTime(Date lastTime) {
		this.lastTime = lastTime;
	}
	/** 状态(0正常,1禁用)  */
	public int getState() {
		return state;
	}
	/** 状态(0正常,1禁用)  */
	public void setState(int state) {
		this.state = state;
	}
	/** 等级（没验证0，第一次验证为1，第二次为3,第一次验证失败为2）  */
	public int getLevel() {
		return level;
	}
	/** 等级（没验证0，第一次验证为1，第二次为3,第一次验证失败为2）  */
	public void setLevel(int level) {
		this.level = level;
	}
	/** 推送deviceToken  */
	public String getDeviceToken() {
		return deviceToken;
	}
	/** 推送deviceToken  */
	public void setDeviceToken(String deviceToken) {
		this.deviceToken = deviceToken;
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
