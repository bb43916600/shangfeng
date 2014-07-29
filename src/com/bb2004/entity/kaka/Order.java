package com.bb2004.entity.kaka;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.bb2004.common.IdEntity;

/**
 * @conyent 订单表
 * @author longhuaxiong
 *
 */

@Entity
@Table(name="T_Order")
public class Order extends IdEntity {
	
	private Long appUserId;//关联用户
	private Date receivingTime;//接单时间
	private String carType;//车类型
	private String orderNo;//订单号
	private int peopleNumber;//跟车人数
	private Date usetime;//客户用车时间
	private String tunnel;//隧道
	private Long otherPriceID;//额外需求ID
	private float useHour;//包时间
	private String userName;//联系人
	private String userPhone;//联系号码
	private float holidayPrice;//节日额外加的费用
	private float nightPrice;//晚间加收
	private float useHourPrice;//包钟单价
	private float carPrice;//车费
	private float tip;//小费
	private float otherPrice;//额外加收
	private float orderPrice;//订单的总价钱
	private String tempLineString;//保存线路，不用每次去提取只作显示
	private String driverName;//司机名称 （用于显示使用，司机接单后，客户那边可以显示司机的信息）
	private String driverPhone;//司机电话 （用于显示使用，司机接单后，客户那边可以显示司机的信息）
	private int orderState;//订单状态 (0未接,1接,2完成,4取消)
	private String otherRemark;//额外需求备注
	private Date inputTime;//录入时间
	private Date startTime;//开始时间  只作查询使用，不保存到数据库
	private Date endTime;//结束时间    只作查询使用，不保存到数据库
	private Long initFlag;//备用字段1
	private String timeFlag;//备用字段2
	
	/** 司机ID  */
	public Long getAppUserId() {
		return appUserId;
	}
	/** 司机ID  */
	public void setAppUserId(Long appUserId) {
		this.appUserId = appUserId;
	}
	/** 接订单时间  */
	public Date getReceivingTime() {
		return receivingTime;
	}
	/** 接订单时间  */
	public void setReceivingTime(Date receivingTime) {
		this.receivingTime = receivingTime;
	}
	/** 订单号  */
	public String getOrderNo() {
		return orderNo;
	}
	/** 订单号  */
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	/** 车类型  */
	public String getCarType() {
		return carType;
	}
	/** 车类型  */
	public void setCarType(String carType) {
		this.carType = carType;
	}
	/** 跟车人数  */
	public int getPeopleNumber() {
		return peopleNumber;
	}
	/** 跟车人数  */
	public void setPeopleNumber(int peopleNumber) {
		this.peopleNumber = peopleNumber;
	}
	/** 客户用车时间  */
	public Date getUsetime() {
		return usetime;
	}
	/** 客户用车时间  */
	public void setUsetime(Date usetime) {
		this.usetime = usetime;
	}
	/** 隧道  */
	public String getTunnel() {
		return tunnel;
	}
	/** 隧道  */
	public void setTunnel(String tunnel) {
		this.tunnel = tunnel;
	}
	/** 额外需求ID  */
	public Long getOtherPriceID() {
		return otherPriceID;
	}
	/** 额外需求ID  */
	public void setOtherPriceID(Long otherPriceID) {
		this.otherPriceID = otherPriceID;
	}
	/** 包时间  */
	public float getUseHour() {
		return useHour;
	}
	/** 包时间  */
	public void setUseHour(float useHour) {
		this.useHour = useHour;
	}
	/** 联系人  */
	public String getUserName() {
		return userName;
	}
	/** 联系人  */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/** 联系电话  */
	public String getUserPhone() {
		return userPhone;
	}
	/** 联系电话  */
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	/** 节假日额外费用  */
	public float getHolidayPrice() {
		return holidayPrice;
	}
	/** 节假日额外费用  */
	public void setHolidayPrice(float holidayPrice) {
		this.holidayPrice = holidayPrice;
	}
	/** 车费  */
	public float getCarPrice() {
		return carPrice;
	}
	/** 车费  */
	public void setCarPrice(float carPrice) {
		this.carPrice = carPrice;
	}
	/** 小费  */
	public float getTip() {
		return tip;
	}
	/** 小费  */
	public void setTip(float tip) {
		this.tip = tip;
	}
	/** 订单总价格  */
	public float getOrderPrice() {
		return orderPrice;
	}
	/** 订单总价格  */
	public void setOrderPrice(float orderPrice) {
		this.orderPrice = orderPrice;
	}
	/** 订单状态(0未接,1接,2完成)  */
	public int getOrderState() {
		return orderState;
	}
	/** 订单状态(0未接,1接,2完成)  */
	public void setOrderState(int orderState) {
		this.orderState = orderState;
	}
	/** 录入时间  */
	public Date getInputTime() {
		return inputTime;
	}
	/** 录入时间  */
	public void setInputTime(Date inputTime) {
		this.inputTime = inputTime;
	}
	/** 开始时间 只作查询使用  不进行持久化（不和数据库打交道） */
	@Transient
	public Date getStartTime() {
		return startTime;
	}
	/** 开始时间 只作查询使用  不进行持久化（不和数据库打交道） */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	/** 结束时间 只作查询使用   不进行持久化（不和数据库打交道） */
	@Transient
	public Date getEndTime() {
		return endTime;
	}
	/** 结束时间 只作查询使用   不进行持久化（不和数据库打交道） */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	/** 额外费用  */
	public float getOtherPrice() {
		return otherPrice;
	}
	/** 额外费用  */
	public void setOtherPrice(float otherPrice) {
		this.otherPrice = otherPrice;
	}
	/** 额外需求备注  */
	public String getOtherRemark() {
		return otherRemark;
	}
	/** 额外需求备注  */
	public void setOtherRemark(String otherRemark) {
		this.otherRemark = otherRemark;
	}
	/** 保存线路，不用每次去提取只作显示 */
	public String getTempLineString() {
		return tempLineString;
	}
	/** 保存线路，不用每次去提取只作显示 */
	public void setTempLineString(String tempLineString) {
		this.tempLineString = tempLineString;
	}
	/** 司机名称 （用于显示使用，司机接单后，客户那边可以显示司机的信息）  */
	public String getDriverName() {
		return driverName;
	}
	/** 司机名称 （用于显示使用，司机接单后，客户那边可以显示司机的信息）  */
	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}
	/** 司机手机 （用于显示使用，司机接单后，客户那边可以显示司机的信息）  */
	public String getDriverPhone() {
		return driverPhone;
	}
	/** 司机手机 （用于显示使用，司机接单后，客户那边可以显示司机的信息）  */
	public void setDriverPhone(String driverPhone) {
		this.driverPhone = driverPhone;
	}
	/** 晚间加收  */
	public float getNightPrice() {
		return nightPrice;
	}
	/** 晚间加收  */
	public void setNightPrice(float nightPrice) {
		this.nightPrice = nightPrice;
	}
	/** 包车单价  */
	public float getUseHourPrice() {
		return useHourPrice;
	}
	/** 包车单价  */
	public void setUseHourPrice(float useHourPrice) {
		this.useHourPrice = useHourPrice;
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
