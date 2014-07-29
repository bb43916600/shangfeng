package com.bb2004.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javapns.back.PushNotificationManager;
import javapns.back.SSLConnectionHelper;
import javapns.data.Device;
import javapns.data.PayLoad;



public class Test {

	public static void main(String[] args) throws ParseException {
//		orderID();
//		System.out.println(new Date().getHours());
//		jsonAndMap();
//		String enc = System.getProperty("file.encoding");
		Date date = new Date(); 

		System.out.println(date);
	}
	
	
	public static void jsonAndMap() {
		
		JSONObject json = new JSONObject();
		json.put("userName", "龙华雄");
		json.put("age", 27);
		json.put("height", 1.75);
		json.put("mistress", new ArrayList());
		json.put("dog", new HashMap());
		json.put("wife", new BB2004());
		System.out.println("json格式："+json);
		
		BB2004 bb2004 = new BB2004();
		bb2004.setUserName("龙华雄");
		bb2004.setAge(27);
		bb2004.setHeight(17.5f);
		bb2004.setMistress(new ArrayList());
		bb2004.setDog(new HashMap());
		bb2004.setWife(new BB2004());
		System.out.println("实体类格式："+bb2004.toString());
		
		JSONArray jsonArray = new JSONArray();
		jsonArray.add("1111");
		jsonArray.add("2222");
		jsonArray.add("3333");
		jsonArray.add("4444");
		jsonArray.add(1);
		System.out.println("JsonArray格式："+jsonArray);
		
		List list = new ArrayList();
		list.add("aaaaa");
		list.add("bbbbb");
		list.add("ccccc");
		list.add("ddddd");
		list.add(2);
		
		System.out.println("Array格式："+list);
	}
	
	//组合订单ID
	public static void  orderID(){
		System.out.println("#"+new Date().getTime());
//		 Random random = new Random();
//		 Temp=;
//         for(int i = 0; i < 10;i++) {
//             System.out.println();
//         }
		phone();
	}
	
	//手机推送测试
	public static void phone(){
		try
        {
            //从客户端获取的deviceToken，在此为了测试简单，写固定的一个测试设备标识。
//			Iphone :   216d9ed4 caf242b1 a6675e03 6e9d1ed4 fee443ae 8e735d01 41fd41e5 2d441062
//			JCO: f9aa9834 538fd4b8 8a75a100 ae3b1075 6781cd98 d57c3c5f 838009f4 8b502126
			String deviceToken = "216d9ed4 caf242b1 a6675e03 6e9d1ed4 fee443ae 8e735d01 41fd41e5 2d441062";
            //定义消息模式
            PayLoad payLoad = new PayLoad();
            payLoad.addAlert("this is test!");
            payLoad.addBadge(1);//消息推送标记数，小红圈中显示的数字。
            payLoad.addSound("default");
            //注册deviceToken
            PushNotificationManager pushManager = PushNotificationManager.getInstance();
            pushManager.addDevice("iPhone", deviceToken);
            //连接APNS
            String host = "gateway.sandbox.push.apple.com";
            int port = 2195;

            String certificatePath = "/Users/longhuaxiong/Desktop/pushKey.p12";//前面生成的用于JAVA后台连接APNS服务的*.p12文件位置

            String certificatePassword = "123456";//p12文件密码。
            pushManager.initializeConnection(host, port, certificatePath,certificatePassword, SSLConnectionHelper.KEYSTORE_TYPE_PKCS12);
            //发送推送
            Device client = pushManager.getDevice("iPhone");
            pushManager.sendNotification(client, payLoad);
            //停止连接APNS
            pushManager.stopConnection();
            //删除deviceToken
            pushManager.removeDevice("iPhone");
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
	}
}
