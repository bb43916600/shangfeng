package com.bb2004.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javapns.notification.PushedNotification;
import javapns.devices.Device;
import javapns.devices.implementations.basic.BasicDevice;
import javapns.notification.AppleNotificationServerBasicImpl;
import javapns.notification.PushNotificationManager;
import javapns.notification.PushNotificationPayload;
import javapns.notification.PushedNotification;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MainApnsSend {
	public static void main(String[] args) throws Exception {
		String deviceToken = "6c2fdcc617fa84483670cf3e4fe3cdc2b64d7a2bf911ef40b84de27fa7c1d29f";
		String ipad = "55f77e9e68c5cfdcb841ff9eb6db0920f54546e0ee1a41b5b6754e786703d5a8";					  
	    String alert = "我的push测试";//push的内容
	    int badge = 1;//图标小红圈的数值
	    String sound = "default";//铃音
	    
	    //添加群发
	    List<String> tokens = new ArrayList<String>();
	    tokens.add(deviceToken);
	    tokens.add(ipad);
	    
	    String certificatePath = "/Users/longhuaxiong/Desktop/kakaVan/DriverPushKey.p12";
	    String certificatePassword = "b317130724";//此处注意导出的证书密码不能为空因为空密码会报错
	    boolean sendCount = false;
	
	    try
	    {
	        PushNotificationPayload payLoad = new PushNotificationPayload();
	        payLoad.addAlert(alert); // 消息内容
	        payLoad.addBadge(badge); // iphone应用图标上小红圈上的数值
            payLoad.addSound(sound);//铃音
	        PushNotificationManager pushManager = new PushNotificationManager();
	        //true：表示的是产品发布推送服务 false：表示的是产品测试推送服务
	        pushManager.initializeConnection(new AppleNotificationServerBasicImpl(certificatePath, certificatePassword, false));
	        List<PushedNotification> notifications = new ArrayList<PushedNotification>();
	        // 发送push消息
	        if (sendCount){
	            Device device = new BasicDevice();
	            device.setToken(tokens.get(0));
	            PushedNotification notification = pushManager.sendNotification(device, payLoad, true);
	            notifications.add(notification);
	        }
	        else{
	            List<Device> device = new ArrayList<Device>();
	            for (String token : tokens){
	                device.add(new BasicDevice(token));
	            }
	            notifications = pushManager.sendNotifications(payLoad, device);
	        }
	        List<PushedNotification> failedNotifications = PushedNotification.findFailedNotifications(notifications);
	        List<PushedNotification> successfulNotifications = PushedNotification.findSuccessfulNotifications(notifications);
	        int failed = failedNotifications.size();
	        int successful = successfulNotifications.size();
	        pushManager.stopConnection();
	    }
	    catch (Exception e){
	        e.printStackTrace();
	    }
	}
}
