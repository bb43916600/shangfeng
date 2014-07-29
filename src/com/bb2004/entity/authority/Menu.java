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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.bb2004.common.IdEntity;
import com.bb2004.entity.authority.MenuKind;
import com.bb2004.entity.authority.Operation;
import com.bb2004.entity.authority.Role;

/**
 * @time 2012-12-28
 * @author 龙华雄
 * @content 菜单
 */

@Entity
@Table(name="WAP_MENU")
public class Menu extends IdEntity{
	private String name;//名字
	private String url;//地址
	private int order;//菜单排序
	private MenuKind menuKind;//所属类型
	private Set<Role> roles;//用户角色
	private Set<Operation> operations;//增删改查动作
	
	/** 菜单名字  */
	@Column(name="NAME")
	public String getName() {
		return name;
	}
	/** 菜单名字  */
	public void setName(String name) {
		this.name = name;
	}
	/** 菜单URL  */
	@Column(name="URL")
	public String getUrl() {
		return url;
	}
	/** 菜单URL  */
	public void setUrl(String url) {
		this.url = url;
	}
	/** 菜单排序  */
	@Column(name="SEQENCING")
	public int getOrder() {
		return order;
	}
	/** 菜单排序  */
	public void setOrder(int order) {
		this.order = order;
	}
	/** 菜单类型 */
	@OneToOne(cascade={CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinColumn(name="MENU_KIND_ID")
	public MenuKind getMenuKind() {
		return menuKind;
	}
	/** 菜单类型 */
	public void setMenuKind(MenuKind menuKind) {
		this.menuKind = menuKind;
	}
	/** 角色  */
	@ManyToMany(cascade={CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH},mappedBy="menus")
	public Set<Role> getRoles() {
		return roles;
	}
	/** 角色  */
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	/** 操作权限  */
	@ManyToMany(cascade={CascadeType.ALL},fetch=FetchType.EAGER)
	@JoinTable(name="wap_menu_operation",joinColumns={@JoinColumn(name="menu_id")},inverseJoinColumns={@JoinColumn(name="operation_id")})
	public Set<Operation> getOperations() {
		return operations;
	}
	/** 操作权限  */
	public void setOperations(Set<Operation> operations) {
		this.operations = operations;
	}
}
