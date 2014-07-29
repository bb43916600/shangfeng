package com.bb2004.entity.kaka;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.bb2004.common.IdEntity;

@Entity
@Table(name="T_OverTime")
public class OverTime extends IdEntity{
	private int vanStartTime;//Van仔开始时间
	private int vanEndTime;//结束时间
	private float vanPrice;//价钱
	
	private int smallTruckStartTime;//5.5吨卡车开始时间
	private int smallTruckEndTime;//5.5吨卡车结束时间
	private float smallTruckPrice;//5.5吨卡车价钱
	
	private int bigTruckStartTime;//9吨卡车开始时间
	private int bigTruckEndTime;//9吨卡车结束时间
	private float bigTruckPrice;//9吨卡车价钱
	
	/** Van仔开始时间  */
	public int getVanStartTime() {
		return vanStartTime;
	}
	/** Van仔开始时间  */
	public void setVanStartTime(int vanStartTime) {
		this.vanStartTime = vanStartTime;
	}
	/** Van结束时间  */
	public int getVanEndTime() {
		return vanEndTime;
	}
	/** Van结束时间  */
	public void setVanEndTime(int vanEndTime) {
		this.vanEndTime = vanEndTime;
	}
	/** Van价钱  */
	public float getVanPrice() {
		return vanPrice;
	}
	/** Van价钱  */
	public void setVanPrice(float vanPrice) {
		this.vanPrice = vanPrice;
	}
	/** 5.5吨卡车开始时间  */
	public int getSmallTruckStartTime() {
		return smallTruckStartTime;
	}
	/** 5.5吨卡车开始时间  */
	public void setSmallTruckStartTime(int smallTruckStartTime) {
		this.smallTruckStartTime = smallTruckStartTime;
	}
	/** 5.5吨卡车结束时间  */
	public int getSmallTruckEndTime() {
		return smallTruckEndTime;
	}
	/** 5.5吨卡车结束时间  */
	public void setSmallTruckEndTime(int smallTruckEndTime) {
		this.smallTruckEndTime = smallTruckEndTime;
	}
	/** 5.5吨价钱  */
	public float getSmallTruckPrice() {
		return smallTruckPrice;
	}
	/** 5.5吨价钱  */
	public void setSmallTruckPrice(float smallTruckPrice) {
		this.smallTruckPrice = smallTruckPrice;
	}
	/** 9吨开始时间  */
	public int getBigTruckStartTime() {
		return bigTruckStartTime;
	}
	/** 9吨开始时间  */
	public void setBigTruckStartTime(int bigTruckStartTime) {
		this.bigTruckStartTime = bigTruckStartTime;
	}
	/** 9吨结束时间  */
	public int getBigTruckEndTime() {
		return bigTruckEndTime;
	}
	/** 9吨结束时间  */
	public void setBigTruckEndTime(int bigTruckEndTime) {
		this.bigTruckEndTime = bigTruckEndTime;
	}
	/** 9吨价钱  */
	public float getBigTruckPrice() {
		return bigTruckPrice;
	}
	/** 9吨价钱  */
	public void setBigTruckPrice(float bigTruckPrice) {
		this.bigTruckPrice = bigTruckPrice;
	}
	
}
