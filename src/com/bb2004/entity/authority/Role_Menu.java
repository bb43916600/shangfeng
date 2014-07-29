package com.bb2004.entity.authority;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @content 角色和菜单关联表
 * @author 龙华雄
 * @time 2013-01-14
 *
 */

@Entity
@Table(name="wap_role_menu")
public class Role_Menu {
	private Long role_id;//角色ID
	private Long menu_id;//菜单ID
	
	/** 角色ID */
	@Id
	@Column(name="role_id")
	public Long getRole_id() {
		return role_id;
	}
	/** 角色ID */
	public void setRole_id(Long role_id) {
		this.role_id = role_id;
	}
	/** 菜单ID */
	@Column(name="menu_id")
	public Long getMenu_id() {
		return menu_id;
	}
	/** 菜单ID */
	public void setMenu_id(Long menu_id) {
		this.menu_id = menu_id;
	}
}
