package com.bb2004.entity.authority;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.bb2004.common.IdEntity;
import com.bb2004.entity.authority.Menu;


@Entity
@Table(name="WAP_OPERATION")
public class Operation extends IdEntity{
	private String name;//动作名字
	private Set<Menu> menu;//菜单
	private String url;
	
	/** 动作名字  */
	public String getName() {
		return name;
	}
	/** 动作名字  */
	public void setName(String name) {
		this.name = name;
	}
	/** 菜单  */
	@ManyToMany(cascade={CascadeType.ALL},mappedBy="operations")
	public Set<Menu> getMenu() {
		return menu;
	}
	/** 菜单  */
	public void setMenu(Set<Menu> menu) {
		this.menu = menu;
	}
	/** 操作url  */
	public String getUrl() {
		return url;
	}
	/** 操作url  */
	public void setUrl(String url) {
		this.url = url;
	}
}
