package com.bb2004.entity.kaka;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.bb2004.common.IdEntity;

@Entity
@Table(name="T_PushMeassge")
public class PushMeassge extends IdEntity{
	private String message;//短信内容
	private int type;//推送的类型(0群推送 1交通推送  2系统通告推送)
	private Date inputTime;//录入时间
	private Long userid;//后台管理人员ID
	
	/**  推送内容  */
	public String getMessage() {
		return message;
	}
	/**  推送内容  */
	public void setMessage(String message) {
		this.message = message;
	}
	/**  推送类型  */
	public int getType() {
		return type;
	}
	/**  推送类型  */
	public void setType(int type) {
		this.type = type;
	}
	/**  推送时间  */
	public Date getInputTime() {
		return inputTime;
	}
	/**  推送时间  */
	public void setInputTime(Date inputTime) {
		this.inputTime = inputTime;
	}
	/**  后台管理人员ID  */
	public Long getUserid() {
		return userid;
	}
	/**  后台管理人员ID  */
	public void setUserid(Long userid) {
		this.userid = userid;
	}
}
