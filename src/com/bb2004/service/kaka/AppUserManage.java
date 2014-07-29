package com.bb2004.service.kaka;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.bb2004.dao.kaka.AppUserDao;
import com.bb2004.entity.authority.User;
import com.bb2004.entity.kaka.AppUser;

@Component
@Transactional
public class AppUserManage {
	private AppUserDao appUserDao;
	@Autowired
	public void setAppUserDao(AppUserDao appUserDao) {
		this.appUserDao = appUserDao;
	}
	
	/**
	 * @content 保存
	 */
	public void save(Object object){
		appUserDao.save(object);
	}
	
	/**
	 * @content 根据ID删除
	 * @param id
	 */
	public void delete(Long id){
		appUserDao.delete(id);
	}
	
	/**
	 * @content 根据ID获取实体类
	 * @param id
	 */
	public AppUser getId(Long id){
		return appUserDao.get(id);
	}
	
	/**
	 * @content Object 查询
	 * @return
	 */
	public List<AppUser> listObjecr(AppUser appUser,int curpage,int pagesize){
		return appUserDao.listObjecr(appUser, curpage, pagesize);
	}
	
	/**
	 * @content 根据用户的等级查询
	 * @param level
	 * @return
	 */
	public List findLevelUser(int level){
		return appUserDao.findLevelUser(level);
	}
	/**
	 * @content 统计用户的等级查询
	 * @param level
	 * @return
	 */
	public int findLevelUserCount(int level){
		return appUserDao.findLevelUserCount(level);
	}
	
	/**
	 * @content 统计某手机号码介绍人数
	 * @param phone
	 * @return
	 */
	public int countintroducerPhone(String phone){
		return appUserDao.countintroducerPhone(phone);
	}
	
	/**
	 * @content 查询所有总数
	 * @return hql;
	 */
	public int addHqlCount(AppUser appUser){
		return appUserDao.addHqlCount(appUser);
	}
}
