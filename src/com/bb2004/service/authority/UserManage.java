package com.bb2004.service.authority;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.bb2004.dao.authority.UserDao;
import com.bb2004.entity.authority.User;

@Component
@Transactional
public class UserManage {
	private UserDao userDao;
	@Autowired
	public void setDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	/**
	 * @content 保存
	 * @param user
	 */
	public void save(User user){
		userDao.save(user);
	}
	
	/**
	 * @content 根据ID删除
	 * @param id
	 */
	public void delete(Long id){
		userDao.delete(id);
	}
	
	/**
	 * @content 根据ID获取实体类
	 * @param id
	 * @return content
	 */
	public User getId(Long id){
		return userDao.get(id);
	}
	
	/**
	 * @content 内容查询列表
	 * @param user 用户
	 * @return
	 */
	public List<User> listUser(String userAccount,int curpage,int pagesize){
		return userDao.listUser(userAccount, curpage, pagesize);
	}
	
	/**
	 * @content 根据帐号和密码查询用户 
	 * @param userAccount  帐号
	 * @param userPassWord MD5加密 
	 * @return
	 */
	public User findbyAccountPassWord(String userAccount,String userPassWord){
		return userDao.findbyAccountPassWord(userAccount, userPassWord);
	}
}
