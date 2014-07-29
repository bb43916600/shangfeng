package com.bb2004.service.authority;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.bb2004.dao.authority.RoleDao;
import com.bb2004.entity.authority.Role;



/**
 * @content 角色管理
 * @time 2012-03-27
 * @author BB2004
 */
@Component
@Transactional
public class RoleManage {

	private RoleDao roleDao;
	@Autowired
	public void setRoleDao(RoleDao roleDao) {this.roleDao = roleDao;}

	/**
	 * @content 获取对象
	 * @param id 
	 * @return Role
	 */
	public Role getRole(Long id){
		return roleDao.get(id);
	}
	
	/**
	 * @content 保存对象
	 * @param role 
	 */
	public void save(Role role){
		roleDao.save(role);
	}
	
	/**
	 * @content 查询所有角色
	 * @return List<Role>
	 */
	public List<Role> roleList(String name,int curpage,int pagesize){
		return roleDao.roleList(name, curpage, pagesize);
	}
	
	/**
	 * @content 删除
	 * @param id
	 */
	public void delete(Long id){
		roleDao.delete(id);
	}
	
	/**
	 * @content 查看roleList方法
	 * @param name 角色的名称
	 * @return count(r.id)
	 */
	public int roleListCount(String name){
		return roleDao.roleListCount(name);
	}
}
