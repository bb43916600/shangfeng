package com.bb2004.entity.kaka;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.bb2004.common.IdEntity;

/**
 * @conyent 费用说明表
 * @author longhuaxiong
 *
 */

@Entity
@Table(name="T_SystemInfo")
public class SystemInfo extends IdEntity {
	
	private String ct_title;//标题(繁体)
	private String en_title;//标题(英文)
	private String cn_title;//标题(简体)

	private String ct_content;//内容(繁体)
	private String en_content;//内容(英文)
	private String cn_content;//内容(简体)
	
	private Date inputTime;//录入时间
	
	/** 标题(繁体)  */
	public String getCt_title() {
		return ct_title;
	}
	/** 标题(繁体)  */
	public void setCt_title(String ct_title) {
		this.ct_title = ct_title;
	}
	/** 标题(英文)  */
	public String getEn_title() {
		return en_title;
	}
	/** 标题(英文)  */
	public void setEn_title(String en_title) {
		this.en_title = en_title;
	}
	/** 标题(简体)  */
	public String getCn_title() {
		return cn_title;
	}
	/** 标题(简体)  */
	public void setCn_title(String cn_title) {
		this.cn_title = cn_title;
	}
	/** 内容(繁体)  */
	public String getCt_content() {
		return ct_content;
	}
	/** 内容(繁体)  */
	public void setCt_content(String ct_content) {
		this.ct_content = ct_content;
	}
	/** 内容(英文)  */
	public String getEn_content() {
		return en_content;
	}
	/** 内容(英文)  */
	public void setEn_content(String en_content) {
		this.en_content = en_content;
	}
	/** 内容(简体)  */
	public String getCn_content() {
		return cn_content;
	}
	/** 内容(简体)  */
	public void setCn_content(String cn_content) {
		this.cn_content = cn_content;
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
