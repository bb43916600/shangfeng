package com.bb2004.entity.authority;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @content 菜单和权限中间表
 * @author 龙华雄
 * @time 2013-01-14 
 */

@Entity
@Table(name="wap_menu_operation")
public class Menu_Operation {
	private Long menu_id;//菜单ID
	private Long operation_id;//权限ID
	
	/** 菜单ID  */
	@Id
	@Column(name="menu_id")
	public Long getMenu_id() {
		return menu_id;
	}
	/** 菜单ID  */
	public void setMenu_id(Long menu_id) {
		this.menu_id = menu_id;
	}
	/** 权限ID  */
	@Column(name="operation_id")
	public Long getOperation_id() {
		return operation_id;
	}
	/** 权限ID  */
	public void setOperation_id(Long operation_id) {
		this.operation_id = operation_id;
	}
}
