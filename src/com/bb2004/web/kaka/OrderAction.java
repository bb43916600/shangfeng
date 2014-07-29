package com.bb2004.web.kaka;

import java.io.BufferedReader;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.print.attribute.standard.Finishings;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

import com.bb2004.common.CrudActionSupport;
import com.bb2004.entity.kaka.AppUser;
import com.bb2004.entity.kaka.City;
import com.bb2004.entity.kaka.Fee;
import com.bb2004.entity.kaka.FnishOrder;
import com.bb2004.entity.kaka.GiveUpOrder;
import com.bb2004.entity.kaka.LinePrice;
import com.bb2004.entity.kaka.Order;
import com.bb2004.entity.kaka.OrderLine;
import com.bb2004.entity.kaka.OrderOtherPrice;
import com.bb2004.entity.kaka.OtherPrice;
import com.bb2004.service.kaka.AppUserManage;
import com.bb2004.service.kaka.CityManage;
import com.bb2004.service.kaka.LinePriceManage;
import com.bb2004.service.kaka.OrderLineManage;
import com.bb2004.service.kaka.OrderManage;
import com.bb2004.service.kaka.OrderOtherPriceManage;
import com.bb2004.service.kaka.OtherPriceManage;
import com.bb2004.service.kaka.PushManage;
import com.bb2004.util.Network;

@Namespace("/order")
public class OrderAction extends CrudActionSupport{
	private Long id;
	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}
	private List list;
	public List getList() {return list;}
	public void setList(List list) {this.list = list;}
	private String name;
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}
	
	private Order order;
	public Order getOrder() {return order;}
	public void setOrder(Order order) {this.order = order;}
	
	private OrderManage orderManage;
	@Autowired
	public void setOrderManage(OrderManage orderManage) {this.orderManage = orderManage;}
	private OrderLineManage orderLineManage;
	@Autowired
	public void setOrderLineManage(OrderLineManage orderLineManage) {this.orderLineManage = orderLineManage;}
	private CityManage cityManage;
	@Autowired
	public void setCityManage(CityManage cityManage) {this.cityManage = cityManage;}
	private OtherPriceManage otherPriceManage;
	@Autowired
	public void setOtherPriceManage(OtherPriceManage otherPriceManage) {this.otherPriceManage = otherPriceManage;}
	private OrderOtherPriceManage orderOtherPriceManage;
	@Autowired
	public void setOrderOtherPriceManage(OrderOtherPriceManage orderOtherPriceManage) {this.orderOtherPriceManage = orderOtherPriceManage;}
	private LinePriceManage linePriceManage;
	@Autowired
	public void setLinePriceManage(LinePriceManage linePriceManage) {this.linePriceManage = linePriceManage;}
	private AppUserManage appUserManage;
	@Autowired
	public void setAppUserManage(AppUserManage appUserManage) {this.appUserManage = appUserManage;}
	
	
	/**
	 * @content 进入编辑页面
	 * @return
	 */
	@Action(value="objectInput",results={@Result(name="input",location="../admin/kaka/order-input.jsp")})
	public String objectInput(){
		if(id!=null){
			order=orderManage.getId(id);
		}else{
			order=new Order();
		}
		return INPUT;
	}
	
	/**
	 * @content 添加Object
	 * @throws Exception
	 */
	@Action(value="objectSave")
	public void objectSave() throws Exception{
		String rspStatus = "1";
		try {
			if(order == null){
				return;
			}
			order.setOrderNo("#"+new Date().getTime());
			order.setInputTime(new Date());
			order.setUsetime(new Date());
			order.setOrderState(0);
			orderManage.save(order);
		} catch (Exception e) {
			e.printStackTrace();
			rspStatus = "9";
		}
		doResult(rspStatus,"text/json;charset=UTF-8");
	}
	
	/**
	 * content 删除对象
	 * @return
	 * @throws Exception
	 */
	@Action(value="objectDelete")
	public void objectDelete() throws Exception{
		String rspStatus = "1";
		try {
			order = orderManage.getId(id);
			if(order!=null) {
				orderManage.delete(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
			rspStatus="9";
		}
		doResult(rspStatus,"text/json;charset=UTF-8");
	}
	
	/**
	 * @content 查询用户
	 * @throws Exception
	 */
	@Action(value="objectList")
	public void objectList() throws Exception{
		int countNumber = orderManage.addHqlCount(order);
		List<Order> list=orderManage.listObjecr(order, this.getPage(), this.getRows());
		super.pageMethod(countNumber);
		List gridModel = new ArrayList();
		for(int i=0;i<list.size();i++){
			Map<String, Object> cellMap = new HashMap<String, Object>();
			order=list.get(i);
			cellMap.put("id", order.getId());
			cellMap.put("orderNo", order.getOrderNo());//线路ID
			cellMap.put("carType", order.getCarType());//车类型
			cellMap.put("peopleNumber", order.getPeopleNumber());//跟车人数
			cellMap.put("usetime", order.getUsetime().toString());//客户用车时间
			cellMap.put("tunnel", order.getTunnel());//隧道
			cellMap.put("tempLineString", order.getTempLineString());//线路，只作显示，减少数据库请求
			cellMap.put("otherPriceID", order.getOtherPriceID());//额外需求ID
			cellMap.put("useHour", order.getUseHour());//包车时间
			cellMap.put("userName", order.getUserName());//联系人
			cellMap.put("userPhone", order.getUserPhone());//联系电话
			cellMap.put("holidayPrice", order.getHolidayPrice());//节日额外加的费用
			cellMap.put("carPrice", order.getCarPrice());//车费
			cellMap.put("tip", order.getTip());//小费
			cellMap.put("orderPrice", order.getOrderPrice());//订单的总价钱
			
			String orderState = "";
			if(order.getOrderState() == 0)
				orderState = "未接";
			else if(order.getOrderState() == 1)
				orderState = "进行中";
			else if(order.getOrderState() == 2)
				orderState = "完成";
			else
				orderState = "撤销";
			
			cellMap.put("orderState", orderState);//订单状态 (0未接,1接,2完成)
			cellMap.put("inputTime", order.getInputTime().toString());//录入时间
			cellMap.put("operation", "<a href=\'javascript:void(0);\' onclick=\'doUpdate("+order.getId()+")\' style=\'color:#090;margin-right:20px;\'>编辑</a><a href=\'javascript:void(0);\' onclick=\'doDelete("+order.getId()+")\' style=\'color:#F00;\'>删除</a>");
			gridModel.add(cellMap);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("gridModel", gridModel);
		map.put("rows", this.getRows());
		map.put("page", this.getPage());
		map.put("total", this.getTotal());
		map.put("record", this.getRecord());
		doResult(JSONObject.fromObject(map).toString(),"text/json;charset=UTF-8");
	}
	
//======================================APP============================================================

	/**
	 * @content 所有订单
	 * @throws Exception
	 */
	@Action(value="wapList")
	public void wapList() throws Exception{
		//判断用户是否存在
		AppUser appUser = appUserManage.getId(id);
		if(appUser == null) {
			return;
		}
		List gridModel = new ArrayList();
		try {
			List<Order> list=orderManage.wapList(appUser.getId(),appUser.getCarType());
			//查询所有城市
			City city = new City();
			List cityList = cityManage.listObjecr(city, 0, 300);
			Map cityMap = new HashMap();
			for(int i=0;i<cityList.size();i++){
				city = (City)cityList.get(i);
				cityMap.put(city.getId(), city);
			}
			//遍历订单
			for(int i=0;i<list.size();i++){
				Map<String, Object> cellMap = new HashMap<String, Object>();
				order=list.get(i);
				cellMap.put("id", order.getId());
				
				//根据订单ID获取线路
				List tempOrderLineList = orderLineManage.findOrderLineByOderId(order.getId());
				//遍历线路
				List lineList = new ArrayList();
				Long areaId = 0l;
				for(int j = 0;j<tempOrderLineList.size();j++){
					Map lineMap = new HashMap(); 
					LinePrice tempLinePrice = (LinePrice)tempOrderLineList.get(j);
					lineMap.put("id", tempLinePrice.getId());
					//开始
					lineMap.put("startCityId", tempLinePrice.getStartCityId());
					lineMap.put("startObject", cityMap.get(tempLinePrice.getStartCityId()));
					if( j == 0 ){
						City tempCity = (City) cityMap.get(tempLinePrice.getStartCityId());
						areaId = tempCity.getAreaId();
					}
					
					//结束
					lineMap.put("endCityId", tempLinePrice.getEndCityId());
					lineMap.put("endObject", cityMap.get(tempLinePrice.getEndCityId()));
					lineList.add(lineMap);
				}
				cellMap.put("areaId", areaId);
				cellMap.put("orderLine", lineList);//订单线路
				cellMap.put("nightPrice", order.getNightPrice());//晚间加收
				cellMap.put("useHourPrice", order.getUseHourPrice());//包钟单价
				
				
				cellMap.put("carType", order.getCarType());//车类型
				cellMap.put("peopleNumber", order.getPeopleNumber());//跟车人数
				cellMap.put("usetime", order.getUsetime().toString().substring(0, 19));//客户用车时间
				cellMap.put("tunnel", order.getTunnel());//隧道
				
				//额外需求
				List orderOtherPriceList = orderOtherPriceManage.findOrderOtherPriceByOderId(order.getId());
				List otherList = new ArrayList();
				for(int j = 0;j<orderOtherPriceList.size();j++){
					Map otherMap = new HashMap();
					OtherPrice price = (OtherPrice)orderOtherPriceList.get(j);
					otherMap.put("ct_otherName", price.getCt_otherName());
					otherMap.put("en_otherName", price.getEn_otherName());
					otherMap.put("cn_otherName", price.getCn_otherName());
					
					otherMap.put("ct_remark", price.getCt_remark());
					otherMap.put("en_remark", price.getEn_remark());
					otherMap.put("cn_remark", price.getCn_remark());
					
					otherMap.put("id", price.getId());
					otherMap.put("price", price.getPrice());
					otherMap.put("icon", price.getIcon());
					otherMap.put("inputTime", price.getInputTime().toString());
					Map otherParentMap = new HashMap();
					if(price.getParentId()!=0){
						OtherPrice priceParent = otherPriceManage.getId(price.getParentId());
						otherParentMap.put("ct_otherName", priceParent.getCt_otherName());
						otherParentMap.put("en_otherName", priceParent.getEn_otherName());
						otherParentMap.put("cn_otherName", priceParent.getCn_otherName());
						
						otherParentMap.put("ct_remark", priceParent.getCt_remark());
						otherParentMap.put("en_remark", priceParent.getEn_remark());
						otherParentMap.put("cn_remark", priceParent.getCn_remark());
						
						otherParentMap.put("id", priceParent.getId());
						otherParentMap.put("price", priceParent.getPrice());
						otherParentMap.put("icon", priceParent.getIcon());
						otherParentMap.put("inputTime", priceParent.getInputTime().toString());
					}
					otherMap.put("parentId", otherParentMap);
					otherList.add(otherMap);
				}
				cellMap.put("otherPriceId", otherList);//额外需求ID
				cellMap.put("appUserId", order.getAppUserId());//订单所属用户
				cellMap.put("orderNo", order.getOrderNo());//订单号
				cellMap.put("tempLineString", order.getTempLineString());//线路，只作显示，减少数据库请求
				cellMap.put("useHour", order.getUseHour());//包车时间
				cellMap.put("userName", order.getUserName());//联系人
				cellMap.put("userPhone", order.getUserPhone());//联系电话
				cellMap.put("userPhoneCount", orderManage.countNumberFnishOrder(order.getUserPhone()));//统计这个用户完成订单的数量
				cellMap.put("holidayPrice", order.getHolidayPrice());//节日额外加的费用
				cellMap.put("carPrice", order.getCarPrice());//车费
				cellMap.put("tip", order.getTip());//小费
				cellMap.put("otherRemark", order.getOtherRemark());//额外费用备注
				cellMap.put("orderPrice", order.getOrderPrice());//订单的总价钱
				cellMap.put("orderState", order.getOrderState());//订单状态 (0未接,1接,2完成)
				cellMap.put("inputTime", order.getInputTime().toString().substring(0, 10));//录入时间
				gridModel.add(cellMap);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		doResult(JSONArray.fromObject(gridModel).toString(),"text/json;charset=UTF-8");
	}
	
	/**
	 * @content 订单详细
	 * @throws Exception
	 */
	@Action(value="wapOrderDetail")
	public void wapOrderDetail() throws Exception{
		Map<String, Object> cellMap = new HashMap<String, Object>();
		
		try {
			//城市
			City city = new City();
			List cityList = cityManage.listObjecr(city, 0, 300);
			Map cityMap = new HashMap();
			for(int i=0;i<cityList.size();i++){
				city = (City)cityList.get(i);
				cityMap.put(city.getId(), city);
			}
			
			order=orderManage.getId(id);
			if(order != null){
				cellMap.put("id", order.getId());
				
				cellMap.put("carType", order.getCarType());//车类型
				cellMap.put("peopleNumber", order.getPeopleNumber());//跟车人数
				cellMap.put("usetime", order.getUsetime().toString().substring(0, 10));//客户用车时间
				cellMap.put("tunnel", order.getTunnel());//隧道
				
				cellMap.put("useHour", order.getUseHour());//包车时间
				cellMap.put("userName", order.getUserName());//联系人
				cellMap.put("userPhone", order.getUserPhone());//联系电话
				cellMap.put("holidayPrice", order.getHolidayPrice());//节日额外加的费用
				cellMap.put("tempLineString", order.getTempLineString());//线路，只作显示，减少数据库请求
				cellMap.put("userPhoneCount", orderManage.countNumberFnishOrder(order.getUserPhone()));//统计这个用户完成订单的数量
				cellMap.put("carPrice", order.getCarPrice());//车费
				cellMap.put("tip", order.getTip());//小费
				cellMap.put("otherRemark", order.getOtherRemark());//额外费用备注
				cellMap.put("orderPrice", order.getOrderPrice());//订单的总价钱
				cellMap.put("orderState", order.getOrderState());//订单状态 (0未接,1接,2完成)
				cellMap.put("inputTime", order.getInputTime().toString().substring(0, 19));//录入时间
				
				//根据订单ID获取线路
				List tempOrderLineList = orderLineManage.findOrderLineByOderId(order.getId());
				//遍历线路
				List lineList = new ArrayList();
				Long areaId = 0l;
				for(int j = 0;j<tempOrderLineList.size();j++){
					Map lineMap = new HashMap(); 
					LinePrice tempLinePrice = (LinePrice)tempOrderLineList.get(j);
					lineMap.put("id", tempLinePrice.getId());
					//开始
					lineMap.put("startCityId", tempLinePrice.getStartCityId());
					lineMap.put("startObject", cityMap.get(tempLinePrice.getStartCityId()));
					if( j == 0 ){
						City tempCity = (City) cityMap.get(tempLinePrice.getStartCityId());
						areaId = tempCity.getAreaId();
					}
					//结束
					
					lineMap.put("endCityId", tempLinePrice.getEndCityId());
					lineMap.put("endObject", cityMap.get(tempLinePrice.getEndCityId()));
					lineList.add(lineMap);
				}
				cellMap.put("areaId", areaId);
				cellMap.put("orderLine", lineList);//订单线路
				
				//额外需求
				List orderOtherPriceList = orderOtherPriceManage.findOrderOtherPriceByOderId(order.getId());
				List otherList = new ArrayList();
				for(int j = 0;j<orderOtherPriceList.size();j++){
					Map otherMap = new HashMap();
					OtherPrice price = (OtherPrice)orderOtherPriceList.get(j);
					otherMap.put("ct_otherName", price.getCt_otherName());
					otherMap.put("en_otherName", price.getEn_otherName());
					otherMap.put("cn_otherName", price.getCn_otherName());
					
					otherMap.put("ct_remark", price.getCt_remark());
					otherMap.put("en_remark", price.getEn_remark());
					otherMap.put("cn_remark", price.getCn_remark());
					
					otherMap.put("id", price.getId());
					otherMap.put("price", price.getPrice());
					otherMap.put("icon", price.getIcon());
					otherMap.put("inputTime", price.getInputTime().toString());
					Map otherParentMap = new HashMap();
					if(price.getParentId()!=0){
						OtherPrice priceParent = otherPriceManage.getId(price.getParentId());
						otherParentMap.put("ct_otherName", priceParent.getCt_otherName());
						otherParentMap.put("en_otherName", priceParent.getEn_otherName());
						otherParentMap.put("cn_otherName", priceParent.getCn_otherName());
						
						otherParentMap.put("ct_remark", priceParent.getCt_remark());
						otherParentMap.put("en_remark", priceParent.getEn_remark());
						otherParentMap.put("cn_remark", priceParent.getCn_remark());
						
						otherParentMap.put("id", priceParent.getId());
						otherParentMap.put("price", priceParent.getPrice());
						otherParentMap.put("icon", priceParent.getIcon());
						otherParentMap.put("inputTime", priceParent.getInputTime().toString());
					}
					otherMap.put("parentId", otherParentMap);
					otherList.add(otherMap);
				}
				cellMap.put("otherPriceId", otherList);//额外需求ID
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		doResult(JSONArray.fromObject(cellMap).toString(),"text/json;charset=UTF-8");
	}
	
	/**
	 * @content 提交订单
	 * @throws Exception
	 */
	@Action(value="wapAddOrder")
	public void wapAddOrder() throws Exception{
		Map<String, Object> cellMap = new HashMap<String, Object>();
		String rspStatus = "1";
		try {
			order = new Order();
			HttpServletRequest request = ServletActionContext.getRequest();
			String cityName[] = getParameterValues("cityName");
			String otherPriceId[] = getParameterValues("otherPriceId");
			
			String userName = getParameter("userName");
			String userPhone = getParameter("userPhone");
			String tip = getParameter("tip");
			
			String carType = getParameter("carType");
			String peopleNumber = getParameter("peopleNumber");
			String tunnel = getParameter("tunnel");
			String useHour = getParameter("useHour");
			String usetime = getParameter("usetime");
			String otherRemark = getParameter("otherRemark");
			
			String currentLanguage = getParameter("currentLanguage");
			
			float carPriceCount = 0;//车费
			float holidayPirce = 0;//节假日费用
			float nightPrice = 0;//晚间费用
			float otherPriceCount = 0;//额外需求费用
			float orderCountPirce = 0;//订单总价钱费用
			float useHourPrice = 0;//包钟单价
			float useHourCount = 0;//包钟总价钱
			float isholiday = 0;
			
			//包钟费用获取
			if(useHour!=null && !useHour.equals("")) {
				Map<String,Float> map = orderManage.getBaoZhongPrice(usetime, carType, useHour);
				useHourCount = map.get("useTimeCount");//包钟总价钱
				useHourPrice = map.get("useTime");//包钟单价
				isholiday = map.get("isholiday");//是否是节假日
				orderCountPirce = useHourCount;
			}else {//正常费用获取
				//获取线路价钱费用
				carPriceCount = orderManage.getNormalPrice(cityName, carType,currentLanguage);
				//获取节假日费用
//				holidayPirce = orderManage.getHolidayPrice(usetime, carType);
				Map<String, Float> map = orderManage.getHolidayPrice(usetime, carType);
				nightPrice = map.get("nightPrice");
				holidayPirce = map.get("holidayPirce");
				//车价钱
				orderCountPirce = carPriceCount + holidayPirce + nightPrice;
			}
			
			//保存订单的额外需求
			if(otherPriceId!=null){
				if(otherPriceId.length == 1){
					otherPriceId = otherPriceId[0].split(",");
				}
				for (int i=0; i<otherPriceId.length;i++){
					OtherPrice otherPrice = otherPriceManage.getId(Long.valueOf(otherPriceId[i]));
					otherPriceCount = otherPrice.getPrice() + otherPriceCount;
				}
			}
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date userdate = sdf.parse(usetime);
			order.setOrderNo(orderManage.createOrderNo());//"#"+new Date().getTime()
			order.setInputTime(new Date());
			order.setOrderState(0);
			if(useHour!=null && !useHour.equals("")){
				order.setUseHour(Float.valueOf(useHour));
			}
			
			order.setUserName(URLDecoder.decode(userName, "utf-8"));
			int tempTip = 0;
			if(tip!=null && !tip.equals("")){
				tempTip = Integer.valueOf(tip);
				order.setTip(tempTip);
			}
			
			
			List personNumber = new ArrayList();
			personNumber.add("1");
			personNumber.add("2");
			personNumber.add("3");
			personNumber.add("4");
			personNumber.add("5");
			
			order.setUserPhone(userPhone);
			order.setUsetime(userdate);
			order.setCarType(URLDecoder.decode(carType, "utf-8"));
			order.setOtherRemark(otherRemark);
			if(personNumber.indexOf(peopleNumber) != -1){
				order.setPeopleNumber(Integer.valueOf(peopleNumber));
			}else {
				order.setPeopleNumber(0);
			}
			
			order.setTunnel(URLDecoder.decode(tunnel, "utf-8"));
			
			//价钱
			order.setCarPrice(carPriceCount);
			order.setHolidayPrice(holidayPirce);
			order.setNightPrice(nightPrice);//晚间加收
			order.setUseHourPrice(useHourPrice);//包车单价
			order.setOtherPrice(otherPriceCount);
			order.setOrderPrice(orderCountPirce+otherPriceCount+tempTip);//订单价钱+节假日费用+小费
			orderManage.savaOder(cityName, otherPriceId, order,currentLanguage,request);
		} catch (Exception e) {
			e.printStackTrace();
			rspStatus="9";
		}finally{
			cellMap.put("orderPrice", order.getOrderPrice());//订单总价格
			cellMap.put("nightPrice", order.getNightPrice());//晚间加收
			cellMap.put("useHourPrice", order.getUseHourPrice());//包钟单价
			cellMap.put("orderNo", order.getOrderNo());//订单号
			cellMap.put("tempLineString", order.getTempLineString());//线路，只作显示，减少数据库请求
			cellMap.put("orderState", order.getOrderState());//订单状态 (0未接,1接,2完成,3取消)
			cellMap.put("inputTime", order.getInputTime().toString().substring(0, 19));//录入时间
		}
		doResult(JSONObject.fromObject(cellMap).toString(),"text/json;charset=UTF-8");
	}
	
	
	/**
	 * @content 根据订单号返回订单的状态
	 * @throws Exception
	 */
	@Action(value="wapOrderNoState")
	public void wapOrderNoState() throws Exception{
		Map<String, Object> cellMap = new HashMap<String, Object>();
		HttpServletRequest request = ServletActionContext.getRequest();
		String orderNo[] = getParameterValues("orderNo");
		if(orderNo.length == 1){
			orderNo = orderNo[0].split(",");
		}
		String orderNoapp = "";
		if(orderNo.length<=0)
			return;
		for (int i=0;i<orderNo.length;i++){
			if(i == orderNo.length-1 )
				orderNoapp += "'"+orderNo[i]+"'";
			else 
				orderNoapp += "'"+orderNo[i]+"',";
		}
		try {
			list = orderManage.findArrayOrderState(orderNoapp);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(list.size()<=0)
				return;
			for(int i=0;i<list.size();i++){
				Object result[] = (Object [])list.get(i);
				cellMap.put((String) result[0], Integer.valueOf(result[1].toString()));
			}
		}
		doResult(JSONObject.fromObject(cellMap).toString(),"text/json;charset=UTF-8");
	}
	
	/**
	 * @content 根据订单号返回订单的状态
	 * @throws Exception
	 */
	@Action(value="wapRefreshOrder")
	public void wapRefreshOrder() throws Exception{
		List refreshList = new ArrayList();
		HttpServletRequest request = ServletActionContext.getRequest();
		String orderNo[] = getParameterValues("orderNo");
		if(orderNo.length == 1){
			orderNo = orderNo[0].split(",");
		}
		String orderNoapp = "";
		if(orderNo.length<=0)
			return;
		for (int i=0;i<orderNo.length;i++){
			if(i == orderNo.length-1 )
				orderNoapp += "'"+orderNo[i]+"'";
			else 
				orderNoapp += "'"+orderNo[i]+"',";
		}
		try {
			list = orderManage.findArrayOrderState(orderNoapp);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(list.size()<=0)
				return;
//			o.orderNo,o.orderState,o.driverName,o.driverPhone
			Map<String, Object> cellMap = new HashMap<String, Object>();
			for(int i=0;i<list.size();i++){
				Object result[] = (Object [])list.get(i);
				cellMap.put("orderNo", result[0].toString());
				cellMap.put("orderState", result[1].toString());
				cellMap.put("driverName", result[2]);
				cellMap.put("driverPhone", result[3]);
				refreshList.add(cellMap);
			}
		}
		doResult(JSONArray.fromObject(refreshList).toString(),"text/json;charset=UTF-8");
	}
	
	
	@Action(value="wapGetPrice")
	public void wapGetPrice() throws Exception{
		JSONObject objectJson = new JSONObject();
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			String cityName[] = getParameterValues("cityName");
			String otherPriceId[] = getParameterValues("otherPriceId");
			String carType = getParameter("carType");
			String usetime = getParameter("usetime");
			String useHour = getParameter("useHour");
			String currentLanguage = getParameter("currentLanguage");
			
			
			float carPriceCount = 0;//车费
			float holidayPirce = 0;//节假日费用
			float nightPrice = 0;//晚间费用
			float otherPriceCount = 0;//额外需求费用
			float orderCountPirce = 0;//订单总价钱费用
			float useHourPrice = 0;//包钟单价
			float useHourCount = 0;//包钟总价钱
			float isholiday = 0;
			
			//包钟费用获取
			if(useHour!=null && !useHour.equals("")) {
				Map<String,Float> map = orderManage.getBaoZhongPrice(usetime, carType, useHour);
				useHourCount = map.get("useTimeCount");//包钟总价钱
				useHourPrice = map.get("useTime");//包钟单价
				isholiday = map.get("isholiday");//是否是节假日
				orderCountPirce = useHourCount;
			}else {//正常费用获取
				//获取线路价钱费用
				carPriceCount = orderManage.getNormalPrice(cityName, carType,currentLanguage);
				//获取节假日费用
				Map<String, Float> map = orderManage.getHolidayPrice(usetime, carType);
				nightPrice = map.get("nightPrice");
				orderCountPirce = map.get("holidayPirce");
				//车价钱
				orderCountPirce = carPriceCount + holidayPirce + nightPrice;
				//晚间车费
//				nightPrice = orderManage.getNightPrice(usetime, carType);
			}
			
			//获取额外需求费用
			if(otherPriceId!=null){
				if(otherPriceId.length == 1){
					otherPriceId = otherPriceId[0].split(",");
				}
				for (int i=0; i<otherPriceId.length;i++){
					OtherPrice otherPrice = otherPriceManage.getId(Long.valueOf(otherPriceId[i]));
					otherPriceCount = otherPrice.getPrice() + otherPriceCount;
				}
			}
			objectJson.put("isholiday", isholiday);//是否是节假日
			objectJson.put("useHourCount", useHourCount);//包钟总价钱
			objectJson.put("useHourPrice", useHourPrice);//包钟单价
			objectJson.put("nightPrice", nightPrice);//晚间加收
			objectJson.put("carPriceCount", carPriceCount);//车费
			objectJson.put("otherPriceCount", otherPriceCount);//额外加收
			objectJson.put("holidayPirce", holidayPirce);//节假日费用
			objectJson.put("orderCountPirce", orderCountPirce + otherPriceCount);//订单费用+额外加收
		} catch (Exception e) {
			e.printStackTrace();
		}
		doResult(JSONObject.fromObject(objectJson).toString(),"text/json;charset=UTF-8");
	}
	
	/**
	 * @content 获取JSON
	 * @param request
	 * @return
	 */
	private String readJSONString(HttpServletRequest request){
        StringBuffer json = new StringBuffer();
        String line = null;
        try {
            BufferedReader reader = request.getReader();
            while((line = reader.readLine()) != null) {
                json.append(line);
            }
        }
        catch(Exception e) {
            System.out.println(e.toString());
        }
        return json.toString();
    }
	
	/**
	 * @content 接单
	 * @throws Exception
	 */
	@Action(value="wapOrderReceiving")
	public void wapOrderReceiving() throws Exception{
		JSONObject objectJson = new JSONObject();
		String rspStatus = "9";
		String appUserId = getParameter("appUserId");
		String orderNo = getParameter("orderNo");
		
		try {
			AppUser appUser = null;
			if(appUserId!=null && !appUserId.equals("")){
				appUser = appUserManage.getId(Long.valueOf(appUserId));
			}
			//如果当前用户不存在，如果用户状态等于1
			if(appUser == null || appUser.getState() == 1){
				rspStatus="3";
				return;
			}
			//没通过验证状态  0为提交审核  4审核中   2审核失败
			if(appUser.getLevel() == 0 || appUser.getLevel() == 4 || appUser.getLevel() == 2){
				rspStatus="4";
				return;
			}
			
			List ordering = orderManage.findbyAppuserIdAndOrderState(Long.valueOf(appUserId), 1);
			//判断用户是否能接单
			if(appUser.getLevel() == 1){
				if(ordering.size()>=1){
					rspStatus="5";
					return;
				}
			}else{
				if(ordering.size()>=3){
					rspStatus="6";
					return;
				}
			}
			
			order = orderManage.findByOrderNo(orderNo,0);
			//订单为null
			if(order == null || order.getAppUserId()!=null) {
				rspStatus="7";
				return;
			}
			//成功操作
			order.setOrderState(1);
			order.setAppUserId(Long.valueOf(appUserId));
			order.setReceivingTime(new Date());
			order.setDriverName(appUser.getUserName());//司机名称（用来展示给客户看）
			order.setDriverPhone(appUser.getPhone());//司机联系电话（用来展示给客户看）
			orderManage.save(order);
			rspStatus="1";
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			objectJson.put("state", rspStatus);
			doResult(JSONObject.fromObject(objectJson).toString(),"text/json;charset=UTF-8");
		}
	}
	
	/**
	 * @content 完成订单
	 * @throws Exception
	 */
	@Action(value="wapFnishOrder")
	public void wapFnishOrder() throws Exception{
		JSONObject objectJson = new JSONObject();
		String rspStatus = "9";
		
		String appUserId = getParameter("appUserId");
		String orderNo = getParameter("orderNo");
		
		try {
			order = orderManage.findByOrderNo(orderNo,1);
			AppUser appUser = null;
			if(appUserId!=null && !appUserId.equals("")){
				appUser = appUserManage.getId(Long.valueOf(appUserId));
			}
			//如果是禁用的用户
			if(appUser.getState() == 1){
				return;
			}
			//保存操作 事务
			if(order!=null && appUser!=null){
				if(appUser.getLevel()!=3 && appUser.getLevel()!=1)
					return;
				
				//历史订单
				FnishOrder fnishOrder = new FnishOrder();
				fnishOrder.setAppUserId(appUser.getId());
				fnishOrder.setOrderId(order.getId());
				fnishOrder.setInputTime(new Date());
				
				//设置订单完成状态
				order.setOrderState(2);

				orderManage.fnishSave(order, fnishOrder);
				rspStatus="1";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			objectJson.put("state", rspStatus);
		}
		doResult(JSONObject.fromObject(objectJson).toString(),"text/json;charset=UTF-8");
	}
	
	
	/**
	 * @content 取消订单
	 * @throws Exception
	 */
	@Action(value="wapCancelOrder")
	public void wapCancelOrder() throws Exception{
		JSONObject objectJson = new JSONObject();
		String rspStatus = "9";
		
		String appUserId = getParameter("appUserId");
		String orderNo = getParameter("orderNo");
		
		try {
			order = orderManage.findByOrderNo(orderNo,1);
			AppUser appUser = null;
			if(appUserId!=null && !appUserId.equals("")){
				appUser = appUserManage.getId(Long.valueOf(appUserId));
			}
			//如果是禁用的用户
			if(appUser.getState() == 1){
				return;
			}
			//保存操作 事务
			if(order!=null && appUser!=null){
				if(appUser.getLevel()!=3 && appUser.getLevel()!=1)
					return;
				
				//历史订单
				GiveUpOrder giveUpOrder = new GiveUpOrder();
				giveUpOrder.setAppUserId(appUser.getId());
				giveUpOrder.setOrderId(order.getId());
				giveUpOrder.setInputTime(new Date());
				
				//设置订单完成状态
				order.setOrderState(0);
				order.setAppUserId(null);
				order.setReceivingTime(null);

				orderManage.fnishSave(order, giveUpOrder);
				rspStatus="1";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			objectJson.put("state", rspStatus);
		}
		doResult(JSONObject.fromObject(objectJson).toString(),"text/json;charset=UTF-8");
	}
	
	/**
	 * @content 用户版取消订单
	 * @throws Exception
	 */
	@Action(value="wapUserCancelOrder")
	public void wapUserCancelOrder() throws Exception{
		JSONObject objectJson = new JSONObject();
		String rspStatus = "9";
		
		String orderNo = getParameter("orderNo");
		
		try {
			order = orderManage.findByOrderNo(orderNo);
			//保存操作 事务
			if(order!=null){
				//设置订单完成状态
				order.setOrderState(4);
				orderManage.save(order);
				rspStatus="1";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			objectJson.put("state", rspStatus);
		}
		doResult(JSONObject.fromObject(objectJson).toString(),"text/json;charset=UTF-8");
	}
	
	
	/**
	 * @content ajax使用
	 * @param rsp
	 * @param type
	 * @throws Exception
	 */
	private void doResult(String rsp,String type) throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		if(type==null || type.length()==0)
		    response.setCharacterEncoding("UTF-8");
		else
			response.setContentType(type); 
		response.setHeader("Cache-Control", "no-store, max-age=0, no-cache, must-revalidate");    
		response.addHeader("Cache-Control", "post-check=0, pre-check=0");   
		response.setHeader("Pragma", "no-cache"); 
		response.getWriter().write(rsp);
		response.getWriter().flush();
		response.getWriter().close();
	}
}
