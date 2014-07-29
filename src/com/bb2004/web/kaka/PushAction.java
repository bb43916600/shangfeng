package com.bb2004.web.kaka;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javapns.devices.Device;
import javapns.devices.implementations.basic.BasicDevice;
import javapns.notification.AppleNotificationServerBasicImpl;
import javapns.notification.PushNotificationManager;
import javapns.notification.PushNotificationPayload;
import javapns.notification.PushedNotification;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.bb2004.common.CrudActionSupport;
import com.bb2004.entity.authority.User;
import com.bb2004.entity.kaka.Area;
import com.bb2004.entity.kaka.City;
import com.bb2004.entity.kaka.PushMeassge;
import com.bb2004.entity.kaka.SystemInfo;
import com.bb2004.entity.kaka.Traffic;
import com.bb2004.service.kaka.AppUserManage;
import com.bb2004.service.kaka.DeviceTokenManage;
import com.bb2004.service.kaka.PushManage;
import com.bb2004.service.kaka.SystemInfoManage;
import com.bb2004.service.kaka.TrafficManage;
import com.opensymphony.xwork2.ActionContext;

@Namespace("/push")
public class PushAction extends CrudActionSupport{
	private List list;
	public List getList() {return list;}
	public void setList(List list) {this.list = list;}
	//推送的内容
	private String pushString;
	public String getPushString() {return pushString;}
	public void setPushString(String pushString) {this.pushString = pushString;}
	private String patientPush;
	public String getPatientPush() {return patientPush;}
	//推送的类型
	private int type;
	public int getType() {return type;}
	public void setType(int type) {this.type = type;}
	private PushMeassge pushMeassge;
	public PushMeassge getPushMeassge() {return pushMeassge;}
	public void setPushMeassge(PushMeassge pushMeassge) {this.pushMeassge = pushMeassge;}
	
	private DeviceTokenManage deviceTokenManage;
	@Autowired
	public void setDeviceTokenManage(DeviceTokenManage deviceTokenManage) {this.deviceTokenManage = deviceTokenManage;}
	private AppUserManage appUserManage;
	@Autowired
	public void setAppUserManage(AppUserManage appUserManage) {this.appUserManage = appUserManage;}
	private PushManage pushManage;
	@Autowired
	public void setPushManage(PushManage pushManage) {this.pushManage = pushManage;}
	private TrafficManage trafficManage;
	@Autowired
	public void setTrafficManage(TrafficManage trafficManage) {this.trafficManage = trafficManage;}
	private SystemInfoManage systemInfoManage;
	@Autowired
	public void setSystemInfoManage(SystemInfoManage systemInfoManage) {this.systemInfoManage = systemInfoManage;}
	
	/**
	 * @content 添加Object
	 * @throws Exception
	 */
	@Action(value="objectSave")
	public void objectSave() throws Exception{
		String rspStatus = "1";
		try {
			if(pushMeassge == null){
				return;
			}
			pushMeassge.setInputTime(new Date());
			pushManage.save(pushMeassge);
		} catch (Exception e) {
			e.printStackTrace();
			rspStatus = "9";
		}
		doResult(rspStatus,"text/json;charset=UTF-8");
	}
	
	/**
	 * @content 查询
	 * @throws Exception
	 */
	@Action(value="objectList")
	public void objectList() throws Exception{
		int countNumber = pushManage.addHqlCount(pushMeassge);
		List<PushMeassge> list=pushManage.listObjecr(pushMeassge, this.getPage(), this.getRows());
		super.pageMethod(countNumber);
		List gridModel = new ArrayList();
		for(int i=0;i<list.size();i++){
			Map<String, Object> cellMap = new HashMap<String, Object>();
			pushMeassge=list.get(i);
			cellMap.put("id", pushMeassge.getId());
			cellMap.put("message", pushMeassge.getMessage());//内容
			
			//推送类型
			String pushType ="";
			if(pushMeassge.getType() == 0)
				pushType = "群发推送";
			else if(pushMeassge.getType() == 0)
				pushType = "交通推送";
			else
				pushType = "系统通告推送";
			
			cellMap.put("type", pushType);//类型
			cellMap.put("inputTime", pushMeassge.getInputTime().toString());//推送时间
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
	
	/**
	 * @content 推送消息给所有人
	 * @return
	 * @throws Exception 
	 */
	@Action(value="pushAll")
	public void pushAll() throws Exception{
	    int badge = 1;//图标小红圈的数值
	    String sound = "default";//铃音
	    //交通推送
	    if(type == 1){
	    	Traffic traffic = new Traffic();
	    	List<Traffic> list=trafficManage.listObjecr(traffic, this.getPage(), this.getRows());
			if(list.size() > 0){
				traffic=list.get(0);
				pushString = traffic.getCt_content();
			}
	    }else if(type == 2){//提供公告推送
	    	SystemInfo systemInfo = new SystemInfo();
	    	List<SystemInfo> list=systemInfoManage.listObjecr(systemInfo, this.getPage(), this.getRows());
	    	if(list.size() > 0){
	    		systemInfo=list.get(0);
	    		pushString = systemInfo.getCt_content();
	    	}
	    }
	    String rspStatus = pushManage.pushMessage(request, pushString, badge, sound,type);
	    
	    //如果发送成功，那么就保存
	    if(rspStatus.equals("1")){
	    	pushMeassge = new PushMeassge();
	    	pushMeassge.setMessage(pushString);
	    	pushMeassge.setType(type);
	    	pushMeassge.setInputTime(new Date());
	    	Object userObject = ServletActionContext.getRequest().getSession().getAttribute("user");
	    	if(userObject!= null) {
	    		User user = (User)userObject;
	    		pushMeassge.setUserid(user.getId());
	    	}
	    	pushManage.save(pushMeassge);
	    }
	    doResult(rspStatus,"text/json;charset=UTF-8");
	}
	
//	public void patientPush() throws Exception{
//		HttpServletRequest request =ServletActionContext.getRequest();
//		String rspStatus = "1";
//	    int badge = 1;//图标小红圈的数值
//	    String sound = "default";//铃音
//	    
//	    //添加群发
//	    if(patientPush.equals(""))
//	    	return;
//	    list = appUserManage.findByArrayId(patientPush.substring(0,patientPush.length()-1));
//	    
//	    String certificatePath = request.getSession().getServletContext().getRealPath("/pushKey.p12");
//	    String certificatePassword = "123456";//此处注意导出的证书密码不能为空因为空密码会报错
//	    boolean sendCount = false;
//	
//	    try{
//	        PushNotificationPayload payLoad = new PushNotificationPayload();
//	        payLoad.addAlert(pushString); // 消息内容
//	        payLoad.addBadge(badge); // iphone应用图标上小红圈上的数值
//            payLoad.addSound(sound);//铃音
//            payLoad.addCustomDictionary("bbString", "String");
//            payLoad.addCustomDictionary("bbint", 30);
//            //payLoad.addCustomDictionary("自定义List", tokens);
//	        PushNotificationManager pushManager = new PushNotificationManager();
//	        //true：表示的是产品发布推送服务 false：表示的是产品测试推送服务
//	        pushManager.initializeConnection(new AppleNotificationServerBasicImpl(certificatePath, certificatePassword, false));
//	        List<PushedNotification> notifications = new ArrayList<PushedNotification>();
//	     // 发送push消息
//	        if (sendCount){
//	            Device device = new BasicDevice();
//	            device.setToken(list.get(0).toString());
//	            PushedNotification notification = pushManager.sendNotification(device, payLoad, true);
//	            notifications.add(notification);
//	        }
//	        else{
//	            List<Device> device = new ArrayList<Device>();
//	            for (int i=0;i<list.size();i++){
//	            	String token = (String)list.get(i);
//	                device.add(new BasicDevice(token));
//	            }
//	            notifications = pushManager.sendNotifications(payLoad, device);
//	        }
//            
//	        List<PushedNotification> failedNotifications = PushedNotification.findFailedNotifications(notifications);
//	        List<PushedNotification> successfulNotifications = PushedNotification.findSuccessfulNotifications(notifications);
//	        int failed = failedNotifications.size();
//	        int successful = successfulNotifications.size();
//	        pushManager.stopConnection();
//	    }catch (Exception e){
//	        e.printStackTrace();
//	        rspStatus = "9";
//	    }
//	    doResult(rspStatus,"text/json;charset=UTF-8");
//	}
	
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
