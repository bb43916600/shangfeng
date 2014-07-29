package com.bb2004.entity.feng;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.bb2004.common.IdEntity;

@Entity
@Table(name="T_Product")
public class Product extends IdEntity{
	private String title;//标题
	private int type;
	private String content;//图片介绍
	private String images;//产品图片
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
	public String getImages() {
		return images;
	}
	public void setImages(String images) {
		this.images = images;
	}
	public Date getInputTime() {
		return inputTime;
	}
	public void setInputTime(Date inputTime) {
		this.inputTime = inputTime;
	}
}
