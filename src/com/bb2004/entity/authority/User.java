package com.bb2004.entity.authority;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.bb2004.common.IdEntity;
import com.bb2004.entity.authority.Role;

@Entity
@Table(name="WAP_USER")
public class User extends IdEntity{
	private String userAccount;//账户
	private String userPassWord;//密码
	private Date userInsertTime;//插入时间
	private Date userLastTime;//最后次更新时间
	private String userStatus;//状态  默认是0
	private String userNickname;//昵称
	private String userPhone;//用户电话
	private String userIDCard;//身份证
	private Set<Role> role;//用户角色
	
	/** 账户  */
	public String getUserAccount() {
		return userAccount;
	}
	/** 账户  */
	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}
	/** 密码  */
	public String getUserPassWord() {
		return userPassWord;
	}
	/** 密码  */
	public void setUserPassWord(String userPassWord) {
		this.userPassWord = userPassWord;
	}
	/** 插入时间  */
	public Date getUserInsertTime() {
		return userInsertTime;
	}
	/** 插入时间  */
	public void setUserInsertTime(Date userInsertTime) {
		this.userInsertTime = userInsertTime;
	}
	/** 最后次登录时间   */
	public Date getUserLastTime() {
		return userLastTime;
	}
	/** 最后次登录时间   */
	public void setUserLastTime(Date userLastTime) {
		this.userLastTime = userLastTime;
	}
	/** 用户状态，0为正常  1为禁用   */
	public String getUserStatus() {
		if(userStatus.equals("0")){
			return "正常";
		}else{
			return "禁用";
		}
	}
	/** 用户状态，0为正常  1为禁用   */
	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}
	/** 昵称  */
	public String getUserNickname() {
		return userNickname;
	}
	/** 昵称  */
	public void setUserNickname(String userNickname) {
		this.userNickname = userNickname;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	public String getUserIDCard() {
		return userIDCard;
	}
	public void setUserIDCard(String userIDCard) {
		this.userIDCard = userIDCard;
	}
	/** 角色   */
	@ManyToMany(cascade={CascadeType.ALL},fetch=FetchType.EAGER)  
    @JoinTable(name="wap_user_role",joinColumns={@JoinColumn(name="user_id")},inverseJoinColumns={@JoinColumn(name="role_id")})
	public Set<Role> getRole() {
		return role;
	}
	/** 角色   */
	public void setRole(Set<Role> role) {
		this.role = role;
	}
}
