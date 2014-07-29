package com.bb2004.entity.kaka;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.bb2004.common.IdEntity;

/**
 * @conyent 额外需求表
 * @author longhuaxiong
 *
 */

@Entity
@Table(name="T_OtherPrice")
public class OtherPrice extends IdEntity {
	
	private String ct_otherName;//额外需求名称（繁体）
	private String en_otherName;//额外需求名称 (英文)
	private String cn_otherName;//额外需求名称 (简体)
	private String ct_remark;//额外需求备注（繁体）
	private String en_remark;//额外需求备注 (英文)
	private String cn_remark;//额外需求备注 (简体)
	private String icon;//图标
	private Long parentId;//父类ID
	private float price;//价钱
	private Date inputTime;//录入时间
	private Long initFlag;//备用字段1
	private String timeFlag;//备用字段2
	
	/** 额外需求名称（繁体） */
	public String getCt_otherName() {
		return ct_otherName;
	}
	/** 额外需求名称（繁体） */
	public void setCt_otherName(String ct_otherName) {
		this.ct_otherName = ct_otherName;
	}
	/** 额外需求名称（英文） */
	public String getEn_otherName() {
		return en_otherName;
	}
	/** 额外需求名称（英文） */
	public void setEn_otherName(String en_otherName) {
		this.en_otherName = en_otherName;
	}
	/** 额外需求名称（简体） */
	public String getCn_otherName() {
		return cn_otherName;
	}
	/** 额外需求名称（简体） */
	public void setCn_otherName(String cn_otherName) {
		this.cn_otherName = cn_otherName;
	}
	/** 额外需求备注（繁体） */
	public String getCt_remark() {
		return ct_remark;
	}
	/** 额外需求备注（繁体） */
	public void setCt_remark(String ct_remark) {
		this.ct_remark = ct_remark;
	}
	/** 额外需求备注（英文） */
	public String getEn_remark() {
		return en_remark;
	}
	/** 额外需求备注（英文） */
	public void setEn_remark(String en_remark) {
		this.en_remark = en_remark;
	}
	/** 额外需求备注（简体） */
	public String getCn_remark() {
		return cn_remark;
	}
	/** 额外需求备注（简体） */
	public void setCn_remark(String cn_remark) {
		this.cn_remark = cn_remark;
	}
	/** 父类ID */
	public Long getParentId() {
		return parentId;
	}
	/** 父类ID */
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	/** 价钱 */
	public float getPrice() {
		return price;
	}
	/** 价钱 */
	public void setPrice(float price) {
		this.price = price;
	}
	/** 图标 */
	public String getIcon() {
		return icon;
	}
	/** 图标 */
	public void setIcon(String icon) {
		this.icon = icon;
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
