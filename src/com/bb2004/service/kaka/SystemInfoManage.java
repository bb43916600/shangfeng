package com.bb2004.service.kaka;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.bb2004.dao.kaka.SystemInfoDao;
import com.bb2004.entity.authority.User;
import com.bb2004.entity.kaka.SystemInfo;

@Component
@Transactional
public class SystemInfoManage {
	private SystemInfoDao systemInfoDao;
	@Autowired
	public void setSystemInfoDao(SystemInfoDao systemInfoDao) {
		this.systemInfoDao = systemInfoDao;
	}
	
	/**
	 * @content 保存
	 */
	public void save(Object object){
		systemInfoDao.save(object);
	}
	
	/**
	 * @content 根据ID删除
	 * @param id
	 */
	public void delete(Long id){
		systemInfoDao.delete(id);
	}
	
	/**
	 * @content 根据ID获取实体类
	 * @param id
	 */
	public SystemInfo getId(Long id){
		return systemInfoDao.get(id);
	}
	
	/**
	 * @content Object 查询
	 * @return
	 */
	public List<SystemInfo> listObjecr(SystemInfo systemInfo,int curpage,int pagesize){
		return systemInfoDao.listObjecr(systemInfo, curpage, pagesize);
	}
	
	/**
	 * @content 查询所有总数
	 * @return hql;
	 */
	public int addHqlCount(SystemInfo systemInfo){
		return systemInfoDao.addHqlCount(systemInfo);
	}

}
