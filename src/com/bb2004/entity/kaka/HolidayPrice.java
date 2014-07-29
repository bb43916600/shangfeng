package com.bb2004.entity.kaka;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.bb2004.common.IdEntity;

@Entity
@Table(name="T_HolidayPrice")
public class HolidayPrice extends IdEntity{
	
	private float vanHolidayPrice;//Van节假日价钱
	private float smallTruckHolidayPrice;//5.5吨卡车节假日价钱
	private float bigTruckHolidayPrice;//9吨卡车节假日价钱
	
	/** Van节假日价钱  */
	public float getVanHolidayPrice() {
		return vanHolidayPrice;
	}
	/** Van节假日价钱  */
	public void setVanHolidayPrice(float vanHolidayPrice) {
		this.vanHolidayPrice = vanHolidayPrice;
	}
	/** 5.5吨节假日价钱  */
	public float getSmallTruckHolidayPrice() {
		return smallTruckHolidayPrice;
	}
	/** 5.5吨节假日价钱  */
	public void setSmallTruckHolidayPrice(float smallTruckHolidayPrice) {
		this.smallTruckHolidayPrice = smallTruckHolidayPrice;
	}
	/** 9吨节假日价钱  */
	public float getBigTruckHolidayPrice() {
		return bigTruckHolidayPrice;
	}
	/** 9吨节假日价钱  */
	public void setBigTruckHolidayPrice(float bigTruckHolidayPrice) {
		this.bigTruckHolidayPrice = bigTruckHolidayPrice;
	}
	
}
