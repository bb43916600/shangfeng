package com.bb2004.web;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

import com.bb2004.common.CrudActionSupport;
import com.bb2004.entity.authority.Menu;
import com.bb2004.entity.authority.Role;
import com.bb2004.entity.authority.User;
import com.bb2004.service.authority.MenuKindManage;
import com.bb2004.service.authority.UserManage;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @time 2012-03-27
 * @content 后台用户
 * @author 龙华雄
 */
@Namespace("/user")
public class UserAction extends CrudActionSupport{
	private Long id;
	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}
	private User user;
	public User getUser() {return user;}
	public void setUser(User user) {this.user = user;}
	//验证码
	private String verifyCode;
	public String getVerifyCode() {return verifyCode;}
	public void setVerifyCode(String verifyCode) {this.verifyCode = verifyCode;}
	private UserManage userManage;
	@Autowired
	public void setUserManage(UserManage userManage) {this.userManage = userManage;}
	private MenuKindManage menuKindManage;
	@Autowired
	public void setMenuKindManage(MenuKindManage menuKindManage) {this.menuKindManage = menuKindManage;}
	
	@Action(value="input")
	public String input(){
		user=userManage.getId(id);
		return SUCCESS;
	}
	
	@Action(value="userreg",results={@Result(name="login",location="../blogs/article!list.action",type="redirect")})
	public String userreg(){
		 Md5PasswordEncoder md5=new Md5PasswordEncoder();
		 String pwd = md5.encodePassword(user.getUserAccount(), user.getUserPassWord());//前面是密码，后面是用户登录的邮箱
		 user.setUserPassWord(pwd);
		 user.setUserStatus("0");//默认状态为0，1为禁用
		 userManage.save(user);
		return "login";
	}
	
	public String welcome(){
		return "login";
	}
	
	//用户登陆
	@Action(value="login")//,results={@Result(name="admin",location="../index.jsp",type="redirect")}
	public void login() throws Exception{
		String rspStatus="1";
		try {
			String verCode = (String) ServletActionContext.getRequest().getSession().getAttribute("_MP_AUTHKEY");
			//MD5加密
			Md5PasswordEncoder md5=new Md5PasswordEncoder();
			String pwd = md5.encodePassword(user.getUserAccount(), user.getUserPassWord());//前面是用户账号，后面是密码
			// 获取用户信息
			user = userManage.findbyAccountPassWord(user.getUserAccount(), pwd);
			//验证码判断
			if (verCode == null || !verCode.equals(verifyCode)) {
				rspStatus = "7";
			}else if(user == null){
				rspStatus = "8";
			}else{
				ActionContext ac = ActionContext.getContext();
				ac.getSession().put("user", user);
				ac.getSession().put("menu", getMenu(user));
				ac.getSession().put("menuKind", menuKindManage.menukindIndex());
			}
			doResult(rspStatus,"text/json;charset=UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @content 获取菜单
	 * @param user 用户
	 * @return
	 */
	public Set<Menu> getMenu(User user){
		Set<Menu> set=new HashSet<Menu>();
		if(user.getRole().size()>0){
			Iterator<Role> iterator=user.getRole().iterator();
			while(iterator.hasNext()){
				Role role=iterator.next();
				Iterator<Menu> itMenu=role.getMenus().iterator();
				while(itMenu.hasNext()){
					Menu menu=itMenu.next();
					set.add(menu);
				}
			}
		}
		System.out.println(set.size());
		return set;
	}
	
	/**
	 * @content 用户退出后台
	 * @throws Exception
	 */
	@Action(value="logout")
	public void logout() throws Exception{
		String rspStatus="1";
		try {
			ActionContext ac = ActionContext.getContext();
			ac.getSession().put("user", null);
		} catch (Exception e) {
			e.printStackTrace();
			rspStatus="9";
		}
		doResult(rspStatus,"text/json;charset=UTF-8");
	}
	
	/**
	 * @content ajax使用
	 * @param rsp
	 * @param type
	 * @throws Exception
	 */
	private void doResult(String rsp,String type) throws Exception{
		if(type==null || type.length()==0)
		    response.setCharacterEncoding("UTF-8");
		else
			response.setContentType(type); 
		response.setHeader("Cache-Control", "no-store, max-age=0, no-cache, must-revalidate");    
		response.addHeader("Cache-Control", "post-check=0, pre-check=0");   
		response.setHeader("Pragma", "no-cache"); 
		response.getWriter().write(rsp);
		response.getWriter().flush();
		response.getWriter().close();
	}
}