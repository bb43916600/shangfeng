package com.bb2004.service.authority;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.bb2004.dao.authority.Menu_OperationDao;
import com.bb2004.entity.authority.Menu_Operation;

@Component
@Transactional
public class Menu_OperationManage {
	private Menu_OperationDao menu_OperationDao;
	@Autowired
	public void setMenu_OperationDao(Menu_OperationDao menu_OperationDao) {
		this.menu_OperationDao = menu_OperationDao;
	}
	
	/**
	 * @content 查询某菜单的权限
	 * @param id 菜单ID
	 * @return
	 */
	public List<Menu_Operation> findbyMenuId(String id){
		return menu_OperationDao.findbyMenuId(id);
	}
	
	/**
	 * @content 删除某菜单的权限
	 * @param id 菜单ID
	 * @return
	 */
	public void deletebyMenuId(String id){
		menu_OperationDao.deletebyMenuId(id);
	}
	
	/**
	 * @content 删除某菜单的权限
	 * @param id 权限ID
	 * @return
	 */
	public void deletebyOperationId(Long id){
		menu_OperationDao.deletebyOperationId(id);
	}
}