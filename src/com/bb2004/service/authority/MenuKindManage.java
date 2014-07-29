package com.bb2004.service.authority;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.bb2004.dao.authority.MenuKindDao;
import com.bb2004.entity.authority.MenuKind;



@Component
@Transactional
public class MenuKindManage {
	private MenuKindDao menuKindDao;
	@Autowired
	public void setMenuKindDao(MenuKindDao menuKindDao) {
		this.menuKindDao = menuKindDao;
	}
	
	/** 保存  */
	public void save(MenuKind menuKind){
		menuKindDao.save(menuKind);
	}
	
	/** 删除  */
	public void delete(Long id){
		menuKindDao.delete(id);
	}
	
	/** getID  */
	public MenuKind getId(Long id){
		return menuKindDao.get(id);
	}
	
	public void deleteMenuKind(MenuKind menuKind){
		menuKindDao.delete(menuKind);
	}
	
	/**
	 * @content 查询所有菜单类型 或者条件查询
	 * @param name 菜单类型的名称
	 * @return List<MenuKind>
	 */
	public List<Object> menukindList(String name){
		return menuKindDao.menukindList(name);
	}
	
	/**
	 * @content 查询所有菜单类型 或者条件查询
	 * @return List<MenuKind>
	 */
	public List<Object> menukindList(){
		return menuKindDao.menukindList();
	}
	
	/**
	 * @content getMenuKind   原生SQL查询
	 * @param id 类型ID 
	 * @return MenuKind  原生SQL 查询出来再强转实体类
	 */
	public MenuKind getMenuKind(Long id){
		return menuKindDao.getMenuKind(id);
	}
	
	/**
	 * @content 首页查询所有栏目使用
	 * @return List<MenuKind>
	 */
	public List menukindIndex(){
		return menuKindDao.menukindIndex();
	}
	
	/**
	 * @content 根据名字查询栏目
	 * @return
	 */
	public MenuKind findByName(String name){
		return menuKindDao.findByName(name);
	}
}
