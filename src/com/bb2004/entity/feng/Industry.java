package com.bb2004.entity.feng;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.bb2004.common.IdEntity;

/**
 * @content 产业介绍
 * @author longhuaxiong
 * @time 2014-7-23
 */

@Entity
@Table(name="T_Industry")
public class Industry extends IdEntity{
	private String title;//标题
	private int type;//类型
	private String content;//内容
	private Date inputTime;//录入时间
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getInputTime() {
		return inputTime;
	}
	public void setInputTime(Date inputTime) {
		this.inputTime = inputTime;
	}

}
