package com.bb2004.service.authority;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.bb2004.dao.authority.Role_MenuDao;
import com.bb2004.entity.authority.Role_Menu;

@Component
@Transactional
public class Role_MenuManage {
	private Role_MenuDao role_MenuDao;
	@Autowired
	public void setRole_MenuDao(Role_MenuDao role_MenuDao) {
		this.role_MenuDao = role_MenuDao;
	}
	
	/**
	 * @content 查询角色里的某菜单
	 * @param id 菜单ID
	 * @return
	 */
	public List<Role_Menu> findbyMenuId(String id){
		return role_MenuDao.findbyMenuId(id);
	}
	
	/**
	 * @content 删除某菜单
	 * @param id 菜单ID
	 * @return
	 */
	public void deleteByMenuId(String id){
		role_MenuDao.deleteByMenuId(id);
	}
	
	/**
	 * @content 删除某菜单
	 * @param id 角色ID
	 * @return
	 */
	public void deleteByRoleId(String id){
		role_MenuDao.deleteByRoleId(id);
	}
}
