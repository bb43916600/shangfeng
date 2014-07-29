package com.bb2004.web.kaka;

import java.util.Date;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.bb2004.common.CrudActionSupport;
import com.bb2004.entity.kaka.DeviceToken;
import com.bb2004.service.kaka.DeviceTokenManage;

@Namespace("/deviceToken")
public class DeviceTokenAction extends CrudActionSupport{
	private Long id;
	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}
	private List list;
	public List getList() {return list;}
	public void setList(List list) {this.list = list;}
	private String token;
	public String getToken() {return token;}
	public void setToken(String token) {this.token = token;}
	
	private DeviceToken deviceToken;
	public DeviceToken getDeviceToken() {return deviceToken;}
	public void setDeviceToken(DeviceToken deviceToken) {this.deviceToken = deviceToken;}
	
	private DeviceTokenManage deviceTokenManage;
	@Autowired
	public void setDeviceTokenManage(DeviceTokenManage deviceTokenManage) {this.deviceTokenManage = deviceTokenManage;}
	
	@Action(value="wapAddDeviceToken")
	public void wapAddDeviceToken() throws Exception{
		if(token!=null && !token.equals("")){
			token = token.substring(1, token.length()-1);
			token = token.replaceAll(" ", "");
		}
		
		//如果存在，那么就不保存到数据库里
		deviceToken = new DeviceToken();
		deviceToken.setDeviceToken(token);
		list = deviceTokenManage.listObjecr(deviceToken,  this.getPage(), this.getRows());
		if(list.size() > 0 )
			return;
		deviceToken.setInputTime(new Date());
		deviceTokenManage.save(deviceToken);
	}
}
