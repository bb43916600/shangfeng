package com.bb2004.service.kaka;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import javapns.devices.Device;
import javapns.devices.implementations.basic.BasicDevice;
import javapns.notification.AppleNotificationServerBasicImpl;
import javapns.notification.PushNotificationManager;
import javapns.notification.PushNotificationPayload;
import javapns.notification.PushedNotification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.bb2004.dao.kaka.PushMeassgeDao;
import com.bb2004.entity.kaka.PushMeassge;

@Component
@Transactional
public class PushManage {
	
	private DeviceTokenManage deviceTokenManage;
	@Autowired
	public void setDeviceTokenManage(DeviceTokenManage deviceTokenManage) {this.deviceTokenManage = deviceTokenManage;}
	private PushMeassgeDao pushMeassgeDao;
	@Autowired
	public void setPushMeassgeDao(PushMeassgeDao pushMeassgeDao) {this.pushMeassgeDao = pushMeassgeDao;}
	
	/**
	 * @content 保存
	 */
	public void save(Object object){
		pushMeassgeDao.save(object);
	}
	
	/**
	 * @content 根据ID删除
	 * @param id
	 */
	public void delete(Long id){
		pushMeassgeDao.delete(id);
	}
	
	/**
	 * @content 根据ID获取实体类
	 * @param id
	 */
	public PushMeassge getId(Long id){
		return pushMeassgeDao.get(id);
	}
	
	/**
	 * @content Object 查询
	 * @return
	 */
	public List<PushMeassge> listObjecr(PushMeassge pushMeassge,int curpage,int pagesize){
		return pushMeassgeDao.listObjecr(pushMeassge, curpage, pagesize);
	}
	
	public String pushMessage(HttpServletRequest request,String pushString,int badge,String sound,int type) {
		String rspStatus = "1";
	    
	    //添加群发
	    List<String> tokens = deviceTokenManage.listObjecr(null, 0, 2000);
	    if(tokens.size()<=0)
	    	return "9";
	    
	    String certificatePath = request.getSession().getServletContext().getRealPath("/DriverPushKey.p12");
	    String certificatePassword = "b317130724";//此处注意导出的证书密码不能为空因为空密码会报错
	    boolean sendCount = false;
	
	    try{
	        PushNotificationPayload payLoad = new PushNotificationPayload();
	        payLoad.addAlert(pushString); // 消息内容
	        payLoad.addBadge(badge); // iphone应用图标上小红圈上的数值
            payLoad.addSound(sound);//铃音
            payLoad.addCustomDictionary("type", type);
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
	    }catch (Exception e){
	        e.printStackTrace();
	        rspStatus = "9";
	    }
	    return rspStatus;
	}
	
	/**
	 * @content 查询所有总数
	 * @return hql;
	 */
	public int addHqlCount(PushMeassge pushMeassge){
		return pushMeassgeDao.addHqlCount(pushMeassge);
	}
}
