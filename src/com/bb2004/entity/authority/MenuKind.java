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
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.bb2004.common.IdEntity;
import com.bb2004.entity.authority.MenuKind;


@Entity
@Table(name="WAP_MENUKIND")
public class MenuKind extends IdEntity{
	private String name;//类型的名字
	private int order;//排序
	private Set<MenuKind> menuKinds;//菜单的类型
	
	public MenuKind() {
		super();
	}
	
	public MenuKind(String name) {
		super();
		this.name = name;
	}
	
	public MenuKind(String name, String order) {
		super();
		this.name = name;
		this.order = Integer.parseInt(order);
	}
	
	/** 类型的名字  */
	@Column(name="NAME")
	public String getName() {
		return name;
	}
	/** 类型的名字  */
	public void setName(String name) {
		this.name = name;
	}
	/** 排序  */
	@Column(name="SEQENCING")
	public int getOrder() {
		return order;
	}
	/** 排序  */
	public void setOrder(int order) {
		this.order = order;
	}
	/** 菜单的类型  */
	@OneToMany(cascade={CascadeType.ALL},fetch=FetchType.EAGER)
	@JoinColumn(name="MENU_KIND_ID")//,mappedBy="menuKinds"
	@OrderBy("order")
	public Set<MenuKind> getMenuKinds() {
		return menuKinds;
	}
	/** 菜单的类型  */
	public void setMenuKinds(Set<MenuKind> menuKinds) {
		this.menuKinds = menuKinds;
	}
}
