package com.bb2004.service.kaka;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.bb2004.dao.kaka.OrderDao;
import com.bb2004.entity.authority.User;
import com.bb2004.entity.kaka.FnishOrder;
import com.bb2004.entity.kaka.GiveUpOrder;
import com.bb2004.entity.kaka.HolidayPrice;
import com.bb2004.entity.kaka.HolidayTime;
import com.bb2004.entity.kaka.LinePrice;
import com.bb2004.entity.kaka.Order;
import com.bb2004.entity.kaka.OrderLine;
import com.bb2004.entity.kaka.OrderOtherPrice;
import com.bb2004.entity.kaka.OtherPrice;

@Component
@Transactional
public class OrderManage {
	private OrderDao orderDao;
	private LinePriceManage linePriceManage;
	@Autowired
	public void setLinePriceManage(LinePriceManage linePriceManage) {this.linePriceManage = linePriceManage;}
	private OtherPriceManage otherPriceManage;
	@Autowired
	public void setOtherPriceManage(OtherPriceManage otherPriceManage) {this.otherPriceManage = otherPriceManage;}
	private OrderLineManage orderLineManage;
	@Autowired
	public void setOrderLineManage(OrderLineManage orderLineManage) {this.orderLineManage = orderLineManage;}
	private OrderOtherPriceManage orderOtherPriceManage;
	@Autowired
	public void setOrderOtherPriceManage(OrderOtherPriceManage orderOtherPriceManage) {this.orderOtherPriceManage = orderOtherPriceManage;}
	private FnishOrderManage fnishOrderManage;
	@Autowired
	public void setFnishOrderManage(FnishOrderManage fnishOrderManage) {this.fnishOrderManage = fnishOrderManage;}
	private GiveUpOrderManage giveUpOrderManage;
	@Autowired
	public void setGiveUpOrderManage(GiveUpOrderManage giveUpOrderManage) {this.giveUpOrderManage = giveUpOrderManage;}
	private HolidayTimeManage holidayTimeManage;
	@Autowired
	public void setHolidayTimeManage(HolidayTimeManage holidayTimeManage) {this.holidayTimeManage = holidayTimeManage;}
	private PushManage pushManage;
	@Autowired
	public void setPushManage(PushManage pushManage) {this.pushManage = pushManage;}
	private HolidayPriceManage holidayPriceManage;
	@Autowired
	public void setHolidayPriceManage(HolidayPriceManage holidayPriceManage) {this.holidayPriceManage = holidayPriceManage;}

	@Autowired
	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}
	
	/**
	 * @content 保存
	 */
	public void save(Object object){
		orderDao.save(object);
	}
	
	/**
	 * @content 根据ID删除
	 * @param id
	 */
	public void delete(Long id){
		orderDao.delete(id);
	}
	
	/**
	 * @content 根据ID获取实体类
	 * @param id
	 */
	public Order getId(Long id){
		return orderDao.get(id);
	}
	
	/**
	 * @content Object 查询
	 * @return
	 */
	public List<Order> listObjecr(Order order,int curpage,int pagesize){
		return orderDao.listObjecr(order, curpage, pagesize);
	}
	
	/**
	 * @content 根据订单ID查询订单的状态
	 * @param orderNo
	 * @return
	 */
	public List findArrayOrderState(String orderNo) {
		return orderDao.findArrayOrderState(orderNo);
	}
	
	/**
	 * app所有订单
	 * @return
	 */
	public List wapList(Long id,String carType){
		return orderDao.wapList(id,carType);
	}
	
	/**
	 * @content 更新订单状态
	 * @param appUserId
	 * @param orderNo
	 * @param orderState
	 */
	public void upDateState(Long appUserId,String orderNo) {
		orderDao.upDateState(appUserId, orderNo);
	}
	
	/**
	 * @content 根据订单号查询订单
	 * @param orderNo
	 * @return
	 */
	public Order findByOrderNo(String orderNo,int orderState) {
		return orderDao.findByOrderNo(orderNo,orderState);
	}
	
	/**
	 * @content 根据订单号查询订单
	 * @param orderNo
	 * @return
	 */
	public Order findByOrderNo(String orderNo) {
		return orderDao.findByOrderNo(orderNo);
	}
	
	/**
	 * @content 保存订单
	 * @param cityName
	 * @param otherPriceId
	 * @param order
	 */
	@Transactional
	public void  savaOder(String cityName[],String otherPriceId[],Order order,String currentLanguage,HttpServletRequest request) {
		//保存订单，然后获取ID后面使用
		Date nowDate = new Date();
		order.setInputTime(nowDate);
		save(order);
		//保存订单线路
		StringBuffer pushLine = new StringBuffer();
		for(int i=1;i<cityName.length;i++){
			LinePrice linePrice= linePriceManage.findLinePriceByStartCityNameAndEndCityName(cityName[i-1], cityName[i],currentLanguage);
			if(linePrice == null){
				System.out.println(cityName[i-1]+"========="+cityName[i]);
			}
			if(i==1){
				pushLine.append(cityName[i-1]+" > "+cityName[i]);
			}else {
				pushLine.append(" > "+cityName[i]);
			}
			OrderLine orderLine = new OrderLine();
			orderLine.setOrderId(order.getId());
			orderLine.setInputTime(nowDate);
			orderLine.setLinePriceId(linePrice.getId());//线路ID
			linePriceManage.save(orderLine);
		}
		order.setTempLineString(pushLine.toString());
		//保存订单的额外需求
		if(otherPriceId!=null){
			for (int i=0; i<otherPriceId.length;i++){
				OtherPrice otherPrice = otherPriceManage.getId(Long.valueOf(otherPriceId[i]));
				if(otherPrice !=null){
					OrderOtherPrice orderOtherPrice = new OrderOtherPrice();
					orderOtherPrice.setInputTime(nowDate);
					orderOtherPrice.setOrderId(order.getId());
					orderOtherPrice.setOrderOtherId(otherPrice.getId());
					orderOtherPriceManage.save(orderOtherPrice);
				}
			}
		}
		//推送
//		pushManage.pushMessage(request, pushLine.toString(), 1, "default",9);
	}
	
	/**
	 * @content 获取非包车的节假日费用
	 * @param usetime
	 * @throws ParseException
	 */
	@SuppressWarnings("deprecation")
	public Map getHolidayPrice (String usetime,String carType) throws ParseException{
		Map<String, Float> price = new HashMap<String, Float>();
		Map<String, Integer> nineMap = new HashMap<String, Integer>();
		nineMap.put("van", 45);
		nineMap.put("5.5", 95);
		nineMap.put("9", 175);
		
		Map<String, Integer> zeroMap = new HashMap<String, Integer>();
		zeroMap.put("van", 95);
		zeroMap.put("5.5", 195);
		zeroMap.put("9", 355);
		
		//节假日
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = sdf.parse(usetime);
		
		float nightPrice = 0;//晚间费用
		float holidayPirce = 0;//节假日费用
		
		boolean isAdd = false;
		
		//大于等于晚上9点，小于等于早上9点
		if(date.getHours()>=21 && date.getHours()<=23){
			if(carType.toLowerCase().contains("van"))
				nightPrice = Float.valueOf(nineMap.get("van").toString());
			else if(carType.contains("5.5"))
				nightPrice = Float.valueOf(nineMap.get("5.5").toString());
			else if(carType.contains("9"))
				nightPrice = Float.valueOf(nineMap.get("9").toString());
			isAdd = true;
		}else if(date.getHours()>=0 && date.getHours()<=6){
			if(carType.toLowerCase().contains("van"))
				nightPrice = Float.valueOf(zeroMap.get("van").toString());
			else if(carType.contains("5.5"))
				nightPrice = Float.valueOf(zeroMap.get("5.5").toString());
			else if(carType.contains("9"))
				nightPrice = Float.valueOf(zeroMap.get("9").toString());
			isAdd = true;
		}
		
		//获取是否节假日
		HolidayTime holidayTime= new HolidayTime();
		holidayTime.setSettingTime(sdf.parse(usetime.substring(0, 10)+" 00:00:00"));
		List list = holidayTimeManage.listObjecr(holidayTime, 0, 1000);
		
		//如果是正常用作时间，另外今天是节假日
		if(isAdd == false && list.size()>0){
			System.out.println("走进了添加节假日价钱："+holidayPirce);
			HolidayPrice holidayPrice = holidayPriceManage.listObjecr(null, 0, 1).get(0);
			if(carType.toLowerCase().contains("van"))
				holidayPirce = holidayPirce + holidayPrice.getVanHolidayPrice();
			else if(carType.contains("5.5"))
				holidayPirce = holidayPirce + holidayPrice.getSmallTruckHolidayPrice();
			else
				holidayPirce = holidayPirce + holidayPrice.getBigTruckHolidayPrice();
			System.out.println("添加完的价钱："+holidayPirce);
		}
		price.put("nightPrice", nightPrice);
		price.put("holidayPirce", holidayPirce);
		return price;
	}
	
	/**
	 * @throws ParseException 
	 * @content 获取晚间费用
	 */
	public float getNightPrice(String usetime,String carType) throws ParseException {
		Map<String, Integer> nineMap = new HashMap<String, Integer>();
		nineMap.put("van", 45);
		nineMap.put("5.5", 95);
		nineMap.put("9", 175);
		
		Map<String, Integer> zeroMap = new HashMap<String, Integer>();
		zeroMap.put("van", 95);
		zeroMap.put("5.5", 195);
		zeroMap.put("9", 355);
		
		//节假日
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = sdf.parse(usetime);
		float holidayPirce = 0;
		
		boolean isAdd = false;
		
		//大于等于晚上9点，小于等于早上9点
		if(date.getHours()>=21 && date.getHours()<=23){
			if(carType.toLowerCase().contains("van"))
				holidayPirce = Float.valueOf(nineMap.get("van").toString());
			else if(carType.contains("5.5"))
				holidayPirce = Float.valueOf(nineMap.get("5.5").toString());
			else if(carType.contains("9"))
				holidayPirce = Float.valueOf(nineMap.get("9").toString());
			isAdd = true;
		}else if(date.getHours()>=0 && date.getHours()<=6){
			if(carType.toLowerCase().contains("van"))
				holidayPirce = Float.valueOf(zeroMap.get("van").toString());
			else if(carType.contains("5.5"))
				holidayPirce = Float.valueOf(zeroMap.get("5.5").toString());
			else if(carType.contains("9"))
				holidayPirce = Float.valueOf(zeroMap.get("9").toString());
			isAdd = true;
		}
		return holidayPirce;
	}
	
	/**
	 * @throws ParseException 
	 * @content 获取包钟价钱
	 */
	public Map getBaoZhongPrice (String usetime,String carType,String useHour) throws ParseException{
		Map<String, Float> price = new HashMap<String, Float>();
		Map<String, Integer> normalMap = new HashMap<String, Integer>();
		normalMap.put("van", 110);
		normalMap.put("5.5", 170);
		normalMap.put("9", 250);
		
		Map<String, Integer> holidayMap = new HashMap<String, Integer>();
		holidayMap.put("van", 135);
		holidayMap.put("5.5", 220);
		holidayMap.put("9", 320);
		
		//节假日
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = sdf.parse(usetime);
		
		float holidayPirce = 0;
		float useTime = 0;
		float isholiday = 0;

		//获取是否节假日
		HolidayTime holidayTime= new HolidayTime();
		holidayTime.setSettingTime(sdf.parse(usetime.substring(0, 10)+" 00:00:00"));
		List list = holidayTimeManage.listObjecr(holidayTime, 0, 1000);
		
		//大于等于晚上9点，小于等于早上9点
		if((date.getHours()>=21 || date.getHours()<=6) || list.size()>0){
			if(carType.toLowerCase().contains("van")) {
				holidayPirce = Float.valueOf(holidayMap.get("van").toString()) * Float.valueOf(useHour);
				useTime = Float.valueOf(holidayMap.get("van").toString());
			}else if(carType.contains("5.5")) {
				holidayPirce = Float.valueOf(holidayMap.get("5.5").toString()) * Float.valueOf(useHour);
				useTime = Float.valueOf(holidayMap.get("5.5").toString());
			}else if(carType.contains("9")) {
				holidayPirce = Float.valueOf(holidayMap.get("9").toString()) * Float.valueOf(useHour);
				useTime = Float.valueOf(holidayMap.get("9").toString());
			}
			isholiday = 1;
		}else{
			if(carType.toLowerCase().contains("van")) {
				holidayPirce = Float.valueOf(normalMap.get("van").toString()) * Float.valueOf(useHour);
				useTime = Float.valueOf(normalMap.get("van").toString());
			}else if(carType.contains("5.5")) {
				holidayPirce = Float.valueOf(normalMap.get("5.5").toString()) * Float.valueOf(useHour);
				useTime = Float.valueOf(normalMap.get("5.5").toString());
			}else if(carType.contains("9")) {
				holidayPirce = Float.valueOf(normalMap.get("9").toString()) * Float.valueOf(useHour);
				useTime = Float.valueOf(normalMap.get("9").toString());
			}
			isholiday = 0;
		}
		price.put("useTimeCount", holidayPirce);
		price.put("useTime", useTime);
		price.put("isholiday", isholiday);
		return price;
	}
	
	/**
	 * @content 计算正常下单的价钱
	 * @param cityName
	 * @param carType
	 * @return
	 */
	public float getNormalPrice(String[] cityName,String carType,String currentLanguage){
		//获取线路价钱费用
		float templinePrice = 0;
		float orderCountPirce = 0;
		if(cityName.length == 1){
			cityName = cityName[0].split(",");
		}
		for(int i=1;i<cityName.length;i++){
			if(cityName[i].equals(""))
				continue;
			LinePrice linePrice= linePriceManage.findLinePriceByStartCityNameAndEndCityName(cityName[i-1], cityName[i],currentLanguage);
			if(linePrice == null){
				linePrice = linePriceManage.findLinePriceByStartCityNameAndEndCityName(cityName[i], cityName[i-1],currentLanguage);
				if(linePrice == null){
					System.out.println("不存在订单："+cityName[i-1]+"====="+cityName[i]);
				}
			}
			templinePrice += linePrice.getPrice();
		}
		
		if(carType.toLowerCase().contains("van"))
			orderCountPirce = templinePrice;//线路费用        Van仔车型
		else if(carType.toLowerCase().contains("5.5"))
			orderCountPirce = templinePrice * 2 + 30;//线路费用 * 2 + 30       5.5吨车型
		else
			orderCountPirce = (templinePrice * 2 + 30) * 1.8f;//(线路费用*2 + 30)*1.8     9吨车型
		
		return orderCountPirce;
	}
	
	/**
	 * @content 生成订单号
	 * 
	 */
	public String createOrderNo(){
		Calendar cal=Calendar.getInstance();//使用日历类
		int year=cal.get(Calendar.YEAR);//得到年
		int month=cal.get(Calendar.MONTH)+1;//得到月，因为从0开始的，所以要加1
		int day=cal.get(Calendar.DAY_OF_MONTH);//得到天
		
		//查询数据库获取今天最新的一个订单的No 截取后4位 强转+1
		int maxNumber = orderDao.findMaxOrderNo("#"+year+month+day) + 1;
		//再转换成字符串获取字符串的长度进行追加成订单号
		String maxString = String.valueOf(maxNumber);
		if(maxString.length() == 4)
			return "#"+year+month+day+maxString;
		else if(maxString.length() == 3)
			return "#"+year+month+day+"0"+maxString;
		else if(maxString.length() == 2)
			return "#"+year+month+day+"00"+maxString;
		else
			return "#"+year+month+day+"000"+maxString;
	}
	
	/**
	 * @content 根据用户的ID和订单的状态查询订单
	 * @param appUserId
	 * @param orderState
	 * @return
	 */
	public List findbyAppuserIdAndOrderState(Long appUserId,int orderState){
		return orderDao.findbyAppuserIdAndOrderState(appUserId, orderState);
	}
	
	/**
	 * @content 完成订单事务
	 * @param order
	 * @param fnishOrder
	 */
	@Transactional
	public void fnishSave(Order order,FnishOrder fnishOrder) {
		fnishOrderManage.save(fnishOrder);
		save(order);
	}
	
	/**
	 * @content 取消订单事务
	 * @param order
	 * @param giveUpOrder
	 */
	@Transactional
	public void fnishSave(Order order,GiveUpOrder giveUpOrder) {
		giveUpOrderManage.save(giveUpOrder);
		save(order);
	}
	
	/**
	 * @content 统计订单的数量和总价钱
	 * @param toDay
	 * @return
	 */
	public List findbyToDayOrder(String toDay,int state,Long appUserId){
		return orderDao.findbyToDayOrder(toDay, state, appUserId);
	}
	
	/**
	 * @content 查询所有总数
	 * @return hql;
	 */
	public int addHqlCount(Order order){
		return orderDao.addHqlCount(order);
	}
	
	/**
	 * @content 根据用户的手机号码统计，用户一共完成了几次单
	 * @param userPhone
	 * @return
	 */
	public int countNumberFnishOrder(String userPhone){
		return orderDao.countNumberFnishOrder(userPhone);
	}

}
