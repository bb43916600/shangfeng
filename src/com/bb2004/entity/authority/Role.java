package com.bb2004.entity.authority;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.bb2004.common.IdEntity;
import com.bb2004.entity.authority.Menu;
import com.bb2004.entity.authority.User;

/**
 * @content 人员管理
 * @time 2012-03-27
 * @author BB2004
 */
@Entity
@Table(name = "WAP_ROLE")
public class Role extends IdEntity{
	private String name; //角色名称
	private Set<User> user;//用户
	private Set<Menu> menus;//菜单

	public Role() {
		super();
	}

	public Role(String name) {
		super();
		this.name = name;
	}
	
	/** 角色名称  */
	@Column(name="NAME")
	public String getName() {
		return name;
	}
	/** 角色名称  */
	public void setName(String name) {
		this.name = name;
	}
	/** 用户  */
	@ManyToMany(cascade={CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinTable(name="wap_user_role",joinColumns= @JoinColumn(name="role_id"),inverseJoinColumns= @JoinColumn(name="user_id"))
	public Set<User> getUser() {
		return user;
	}
	/** 用户  */
	public void setUser(Set<User> user) {
		this.user = user;
	}
	/** 菜单  */
	@ManyToMany(cascade=(CascadeType.ALL),fetch=FetchType.EAGER)
	@JoinTable(name="wap_role_menu",joinColumns={@JoinColumn(name="role_id")},inverseJoinColumns={@JoinColumn(name="menu_id")})
	@OrderBy("order")
	public Set<Menu> getMenus() {
		return menus;
	}
	/** 菜单  */
	public void setMenus(Set<Menu> menus) {
		this.menus = menus;
	}
}
