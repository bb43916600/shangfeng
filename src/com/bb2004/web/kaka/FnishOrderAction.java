package com.bb2004.web.kaka;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.bb2004.common.CrudActionSupport;
import com.bb2004.entity.authority.User;
import com.bb2004.entity.kaka.AppUser;
import com.bb2004.entity.kaka.City;
import com.bb2004.entity.kaka.FnishOrder;
import com.bb2004.entity.kaka.LinePrice;
import com.bb2004.entity.kaka.Order;
import com.bb2004.entity.kaka.OrderLine;
import com.bb2004.entity.kaka.OtherPrice;
import com.bb2004.service.kaka.AppUserManage;
import com.bb2004.service.kaka.CityManage;
import com.bb2004.service.kaka.FnishOrderManage;
import com.bb2004.service.kaka.OrderLineManage;
import com.bb2004.service.kaka.OrderManage;
import com.bb2004.service.kaka.OrderOtherPriceManage;
import com.bb2004.service.kaka.OtherPriceManage;

/**
 * 历史订单表
 * @author longhuaxiong
 *
 */

@Namespace("/fnishOrder")
public class FnishOrderAction extends CrudActionSupport{
	private Long id;
	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}
	private List list;
	public List getList() {return list;}
	public void setList(List list) {this.list = list;}
	private String name;
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}
	
	private FnishOrder fnishOrder;
	public FnishOrder getFnishOrder() {return fnishOrder;}
	public void setFnishOrder(FnishOrder fnishOrder) {this.fnishOrder = fnishOrder;}
	
	private FnishOrderManage fnishOrderManage;
	@Autowired
	public void setFnishOrderManage(FnishOrderManage fnishOrderManage) {this.fnishOrderManage = fnishOrderManage;}
	private AppUserManage appUserManage;
	@Autowired
	public void setAppUserManage(AppUserManage appUserManage) {this.appUserManage = appUserManage;}
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


	static {
		System.out.println("测试");
	}
	
	/**
	 * @content 进入编辑页面
	 * @return
	 */
	@Action(value="objectInput",results={@Result(name="input",location="../admin/kaka/fnishOrder-input.jsp")})
	public String objectInput(){
		if(id!=null){
			fnishOrder=fnishOrderManage.getId(id);
		}else{
			fnishOrder=new FnishOrder();
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
			if(fnishOrder == null){
				return;
			}
			fnishOrder.setInputTime(new Date());
			fnishOrderManage.save(fnishOrder);
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
			fnishOrder = fnishOrderManage.getId(id);
			if(fnishOrder!=null) {
				fnishOrderManage.delete(id);
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
		int countNumber = fnishOrderManage.addHqlCount(fnishOrder);
		List<FnishOrder> list=fnishOrderManage.listObjecr(fnishOrder, this.getPage(), this.getRows());
		super.pageMethod(countNumber);
		List gridModel = new ArrayList();
		for(int i=0;i<list.size();i++){
			Map<String, Object> cellMap = new HashMap<String, Object>();
			fnishOrder=list.get(i);
			cellMap.put("id", fnishOrder.getId());
			cellMap.put("appUserID", appUserManage.getId(fnishOrder.getAppUserId()).getUserName());
			cellMap.put("orderID", orderManage.getId(fnishOrder.getOrderId()));
			cellMap.put("inputTime", fnishOrder.getInputTime().toString().substring(0, 10));
			cellMap.put("operation", "<a href=\'javascript:void(0);\' onclick=\'doUpdate("+fnishOrder.getId()+")\' style=\'color:#090;margin-right:20px;\'>编辑</a><a href=\'javascript:void(0);\' onclick=\'doDelete("+fnishOrder.getId()+")\' style=\'color:#F00;\'>删除</a>");
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
	
//===========================================APP使用======================================================================
	/**
	 * @content 用户历史订单
	 * @throws Exception
	 */
	@Action(value="wapUserFnishOrder")
	public void wapUserFnishOrder() throws Exception{
		AppUser appUser= appUserManage.getId(id);
		if(appUser == null)
			return;
		List<Order> list=fnishOrderManage.findbyAppUserId(id);
		List gridModel = new ArrayList();
		
		//城市
		City city = new City();
		List cityList = cityManage.listObjecr(city, 0, 300);
		Map cityMap = new HashMap();
		for(int i=0;i<cityList.size();i++){
			city = (City)cityList.get(i);
			cityMap.put(city.getId(), city);
		}
		for(int i=0;i<list.size();i++){
			Map<String, Object> cellMap = new HashMap<String, Object>();
			Order order=(Order)list.get(i);
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
			cellMap.put("useHour", order.getUseHour());//包车时间
			cellMap.put("userName", order.getUserName());//联系人
			cellMap.put("userPhone", order.getUserPhone());//联系电话
			cellMap.put("holidayPrice", order.getHolidayPrice());//节日额外加的费用
			cellMap.put("carPrice", order.getCarPrice());//车费
			cellMap.put("tip", order.getTip());//小费
			cellMap.put("orderPrice", order.getOrderPrice());//订单的总价钱
			cellMap.put("orderState", order.getOrderState());//订单状态 (0未接,1接,2完成)
			cellMap.put("inputTime", order.getInputTime().toString().substring(0, 10));//录入时间
			gridModel.add(cellMap);
		}
		doResult(JSONArray.fromObject(gridModel).toString(),"text/json;charset=UTF-8");
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
