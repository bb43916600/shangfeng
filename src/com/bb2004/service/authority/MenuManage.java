package com.bb2004.service.authority;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.bb2004.dao.authority.MenuDao;
import com.bb2004.entity.authority.Menu;


@Component
@Transactional
public class MenuManage {
	private MenuDao menuDao;
	@Autowired
	public void setMenuDao(MenuDao menuDao) {this.menuDao = menuDao;}
	
	
	/** 保存  */
	public void save(Menu menu){
		menuDao.save(menu);
	}
	
	/** 保存  */
	public Menu getId(Long id){
		return menuDao.get(id);
	}
	
	/** 删除  */
	public void delete(Long id){
		menuDao.delete(id);
	}
	
	/**
	 * @content 查询所有菜单 或者条件查询
	 * @param name 菜单的名称
	 * @param name 菜单的url
	 * @return List<Menu>
	 */
	public List<Menu> menuList(String name,String url){
		return menuDao.menuList(name, url);
	}
	
	/**
	 * @content 查询某类型的所有菜单
	 * @param id 菜单类型ID
	 * @return
	 */
	public List<Object> findBymenuKindId(Long id){
		return menuDao.findBymenuKindId(id);
	}
	
	/**
	 * @content 查询某类型的所有菜单
	 * @param id 菜单类型ID
	 * @return
	 */
	public List<Menu> findBymenuKindMenu(Long id){
		return menuDao.findBymenuKindMenu(id);
	}
	
	/**
	 * @content 删除某些些指定的菜单
	 * @param id
	 */
	public void deleteInID(String id){
		menuDao.deleteInID(id);
	}
}
