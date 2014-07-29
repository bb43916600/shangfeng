package com.bb2004.service.kaka;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.bb2004.dao.kaka.DeviceTokenDao;
import com.bb2004.entity.kaka.DeviceToken;

@Component
@Transactional
public class DeviceTokenManage {
	private DeviceTokenDao deviceTokenDao;
	@Autowired
	public void setDeviceTokenDao(DeviceTokenDao deviceTokenDao) {
		this.deviceTokenDao = deviceTokenDao;
	}
	
	/**
	 * @content 保存
	 */
	public void save(Object object){
		deviceTokenDao.save(object);
	}
	
	/**
	 * @content 根据ID删除
	 * @param id
	 */
	public void delete(Long id){
		deviceTokenDao.delete(id);
	}
	
	/**
	 * @content 根据ID获取实体类
	 * @param id
	 */
	public DeviceToken getId(Long id){
		return deviceTokenDao.get(id);
	}
	
	/**
	 * @content Object 查询
	 * @return
	 */
	public List<String> listObjecr(DeviceToken deviceToken,int curpage,int pagesize){
		return deviceTokenDao.listObjecr(deviceToken, curpage, pagesize);
	}
}
