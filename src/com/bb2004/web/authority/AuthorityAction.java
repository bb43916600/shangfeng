package com.bb2004.web.authority;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

import com.bb2004.common.CrudActionSupport;
import com.bb2004.entity.authority.Menu;
import com.bb2004.entity.authority.MenuKind;
import com.bb2004.entity.authority.Operation;
import com.bb2004.entity.authority.Role;
import com.bb2004.entity.authority.User;
import com.bb2004.service.authority.MenuKindManage;
import com.bb2004.service.authority.MenuManage;
import com.bb2004.service.authority.Menu_OperationManage;
import com.bb2004.service.authority.OperationManage;
import com.bb2004.service.authority.RoleManage;
import com.bb2004.service.authority.Role_MenuManage;
import com.bb2004.service.authority.UserManage;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @content 权限管理Action
 * @author 龙华雄
 * @time 2013-05-03
 */
@Namespace("/authority")
public class AuthorityAction extends CrudActionSupport{
	private Long id;
	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}
	private Menu menu;
	public Menu getMenu() {return menu;}
	public void setMenu(Menu menu) {this.menu = menu;}
	private MenuKind menuKind;
	public MenuKind getMenuKind() {return menuKind;}
	public void setMenuKind(MenuKind menuKind) {this.menuKind = menuKind;}
	private Operation operation;
	public Operation getOperation() {return operation;}
	public void setOperation(Operation operation) {this.operation = operation;}
	private Role role;
	public Role getRole() {return role;}
	public void setRole(Role role) {this.role = role;}
	private User user2;
	public User getUser() {return user2;}
	public void setUser(User user2) {this.user2 = user2;}
	private List list;
	public List getList() {return list;}
	public void setList(List list) {this.list = list;}
	private UserManage userManage;
	@Autowired
	public void setUserManage(UserManage userManage) {this.userManage = userManage;}
	private RoleManage roleManager;
	@Autowired
	public void setRoleManager(RoleManage roleManager) {this.roleManager = roleManager;}
	private MenuManage menuManager;
	@Autowired
	public void setMenuManager(MenuManage menuManager) {this.menuManager = menuManager;}
	private OperationManage operationManager;
	@Autowired
	public void setOperationManager(OperationManage operationManager) {this.operationManager = operationManager;}
	private MenuKindManage menuKindManager;
	@Autowired
	public void setMenuKindManager(MenuKindManage menuKindManager) {this.menuKindManager = menuKindManager;}
	private Role_MenuManage role_MenuManager;
	@Autowired
	public void setRole_MenuManager(Role_MenuManage role_MenuManager) {this.role_MenuManager = role_MenuManager;}
	private Menu_OperationManage menu_OperationManager;
	@Autowired
	public void setMenu_OperationManager(Menu_OperationManage menu_OperationManager) {this.menu_OperationManager = menu_OperationManager;}
	
	/**
	 * @content 添加用户
	 * @return
	 * @throws Exception 
	 */
	@Action(value="saveUser")
	public void saveUser() throws Exception{
		String rspStatus = "1";
		try {
			Md5PasswordEncoder md5=new Md5PasswordEncoder();
			
			String userAccount=request.getParameter("userAccount");
			String userPassWord=request.getParameter("userPassWord");
			String userNickname=request.getParameter("userNickname");
			String userPhone=request.getParameter("userPhone");
			String userIDCard=request.getParameter("userIDCard");
			String pwd = md5.encodePassword(userAccount, userPassWord);//前面是密码，后面是用户登录的邮箱
			if(id!=null){
				user2=userManage.getId(id);
				user2.setUserLastTime(new Date());
			}else{
				user2=new User();
				user2.setUserInsertTime(new Date());
				user2.setUserStatus("0");//默认状态为0，1为禁用
			}
			user2.setUserPassWord(pwd);
			user2.setUserAccount(userAccount);
			user2.setUserNickname(userNickname);
			user2.setUserPhone(userPhone);
			user2.setUserIDCard(userIDCard);
			user2.setUserLastTime(new Date());
			userManage.save(user2);
		} catch (Exception e) {
			e.printStackTrace();
			rspStatus="9";
		}
		doResult(rspStatus,"text/json;charset=UTF-8");
	}
	
	/**
	 * @content 用户查询列表
	 * @return  struts2 截拦器不支持jsp页面，所以通过中间action跳转jsp页面
	 */
	@Action(value="user-list",results={@Result(name="list",location="../authority/user-list.jsp")})
	public String user_list(){return "list";}
	
	/**
	 * @content 查询用户
	 * @throws Exception
	 */
	@Action(value="userList")
	public void userList() throws Exception{
		String name=request.getParameter("name");
		List<User> list=userManage.listUser(name, this.getPage(), this.getRows());
		List gridModel = new ArrayList();
		for(int i=0;i<list.size();i++){
			Map<String, Object> cellMap = new HashMap<String, Object>();
			User user=list.get(i);
			cellMap.put("id", user.getId());
			cellMap.put("userAccount", user.getUserAccount());
			cellMap.put("userPhone", user.getUserPhone());
			cellMap.put("userIDCard", user.getUserIDCard());
			cellMap.put("inserttime", user.getUserInsertTime().toString().substring(0, 10));
			cellMap.put("userStatus", user.getUserStatus());
			cellMap.put("operación", "<a href=\'javascript:void(0);\' onclick=\'doUpdate("+user.getId()+")\' style=\'color:#090;margin-right:20px;\'>编辑</a><a href=\'javascript:void(0);\' onclick=\'doDelete("+user.getId()+")\' style=\'color:#F00;\'>删除</a>");
			gridModel.add(cellMap);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("gridModel", gridModel);
		map.put("rows", this.getRows());
		map.put("page", request.getAttribute("currpage"));
		map.put("total", request.getAttribute("totalPage"));
		map.put("record", request.getAttribute("pagecount"));
		doResult(JSONObject.fromObject(map).toString(),"text/json;charset=UTF-8");
	}
	
	@Action(value="userInput",results={@Result(name="input",location="../authority/user-input.jsp")})
	public String userInput(){
		if(id!=null){
			user2=userManage.getId(id);
		}else{
			user2=new User();
		}
		return INPUT;
	}
	
	@Action(value="deleteUser")
	public void deleteSubscription() throws Exception{
		String rspStatus = "1";
		try {
			userManage.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
			rspStatus = "9";
		}
		doResult(rspStatus,"text/json;charset=UTF-8");
	}
	
	/**
	 * @content 权限查询页面
	 * @return
	 * @throws Exception 
	 * ,results={@Result(name="list",location="../authority/operation-list.jsp")}
	 */
	@Action(value="operationList")
	public void operationList() throws Exception{
		String name=request.getParameter("name");
		String url=request.getParameter("url");
		List<Operation> list=operationManager.operationList(name,url);
		List gridModel = new ArrayList();
		for(int i=0;i<list.size();i++){
			Map<String, Object> cellMap = new HashMap<String, Object>();
			Operation operation=list.get(i);
			cellMap.put("id", operation.getId());
			cellMap.put("name", operation.getName());
			cellMap.put("operación", "<a href=\'javascript:void(0);\' onclick=\'updateOperation("+operation.getId()+")\' style=\'color:#090;margin-right:20px;\'>编辑</a><a href=\'javascript:void(0);\' onclick=\'deleteOperation("+operation.getId()+")\' style=\'color:#F00;\'>删除</a>");
			gridModel.add(cellMap);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("gridModel", gridModel);
		map.put("rows", this.getRows());
		map.put("page", request.getAttribute("currpage"));
		map.put("total", request.getAttribute("totalPage"));
		map.put("record", request.getAttribute("pagecount"));
		doResult(JSONObject.fromObject(map).toString(),"text/json;charset=UTF-8");
	}
	
	/**
	 * @content 添加权限
	 * @return
	 * @throws Exception 
	 * ,results={@Result(name="list",location="operationList.action",type="redirect")}
	 */
	@Action(value="saveOperation")
	public void saveOperation() throws Exception{
		String rspStatus = "1";
		try {
			operationManager.save(operation);
		} catch (Exception e) {
			e.printStackTrace();
			rspStatus="9";
		}
		doResult(rspStatus,"text/json;charset=UTF-8");
	}
	
	/**
	 * @content 添加权限页面
	 * @return
	 */
	@Action(value="operation-input",results={@Result(name="input",location="../authority/operation-input.jsp")})
	public String inputOperation(){
		if(id!=null){
			operation=operationManager.get(id);
		}else{
			operation=new Operation();
		}
		return "input";
	}
	
	/**
	 * @content 删除权限
	 * @return
	 * @throws Exception
	 * ,results={@Result(name="list",location="operationList.action",type="redirect")}
	 */
	@Action(value="deleteOperation")
	public void deleteOperation() throws Exception{
		String rspStatus = "1";
		try {
			//删除权限关联的菜单
			menu_OperationManager.deletebyOperationId(id);
			operationManager.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
			rspStatus="9";
		}
		doResult(rspStatus,"text/json;charset=UTF-8");
	}
	
	/**
	 * @content 添加菜单类型
	 * @return
	 * @throws Exception
	 * ,results={@Result(name="list",location="menukindList.action",type="redirect")} 
	 */
	@Action(value="saveMenuKind")
	public void saveMenuKind() throws Exception{
		String rspStatus = "1";
		try {
			String menukindid=request.getParameter("menukindid");
			if(id!=null && !id.equals("")){
				menuKind=menuKindManager.getId(id);
			}
			Set<MenuKind> menuKindSet=new HashSet<MenuKind>();
			MenuKind selectmenuKind=null;
			if(!menukindid.equals("") && menukindid!=null){
				selectmenuKind=menuKindManager.getId(Long.valueOf(menukindid));
				menuKindSet.add(menuKind);
				selectmenuKind.getMenuKinds().addAll(menuKindSet);
				menuKindManager.save(selectmenuKind);
			}else{
				menuKindManager.save(menuKind);
			}
		} catch (Exception e) {
			e.printStackTrace();
			rspStatus="9";
		}
		doResult(rspStatus,"text/json;charset=UTF-8");
	}
	
	/**
	 * @content 菜单分类显示页面
	 * @return
	 * @throws Exception 
	 * ,results={@Result(name="list",location="../authority/menukind-list.jsp")}
	 */
	@Action(value="menukindList")
	public void menukindList() throws Exception{
		String name=request.getParameter("name");
		List<Object> list=menuKindManager.menukindList(name);
		List gridModel = new ArrayList();
		for(int i=0;i<list.size();i++){
			Map<String, Object> cellMap = new HashMap<String, Object>();
			Object[] object=(Object[]) list.get(i);
			cellMap.put("id", object[0].toString());
			cellMap.put("name", object[1].toString());
			cellMap.put("operación", "<a href=\'javascript:void(0);\' onclick=\'updateMenukind("+object[0].toString()+")\' style=\'color:#090;margin-right:20px;\'>编辑</a><a href=\'javascript:void(0);\' onclick=\'deleteMenukind("+object[0].toString()+")\' style=\'color:#F00;\'>删除</a>");
			gridModel.add(cellMap);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("gridModel", gridModel);
		map.put("rows", this.getRows());
		map.put("page", request.getAttribute("currpage"));
		map.put("total", request.getAttribute("totalPage"));
		map.put("record", request.getAttribute("pagecount"));
		doResult(JSONObject.fromObject(map).toString(),"text/json;charset=UTF-8");
	}
	
	/**
	 * @content 菜单分类输入页面
	 * @return
	 */
	@Action(value="menukindInput",results={@Result(name="input",location="../authority/menukind-input.jsp")})
	public String menukindInput(){
		if(id!=null){
			menuKind=menuKindManager.getMenuKind(id);
		}else{
			menuKind=new MenuKind();
		}
		list=menuKindManager.menukindList(null);
		return "input";
	}
	
	/**
	 * @content 删除菜单分类（这里不使用任何级联，直接用原生SQL语句操作中间表）1、删除角色关联的的菜单，2、删除菜单关联的权限，3、删除菜单 4、删除分类
	 * @return
	 * @throws Exception
	 * ,results={@Result(name="list",location="menukindList.action",type="redirect")}
	 */
	@Action(value="deleteMenukind")
	public void deleteMenukind() throws Exception{
		String rspStatus = "1";
		try {
			if(id!=null && !id.equals("")){
				menuKind=menuKindManager.getId(id);
				recursionDeleteMenukind(menuKind);
				//删除类型
				menuKindManager.delete(Long.valueOf(id));
			}
		} catch (Exception e) {
			e.printStackTrace();
			rspStatus="9";
		}
		doResult(rspStatus,"text/json;charset=UTF-8");
	}
	
	/**
	 * @content 递归算法，删除栏目
	 * @param menuKind
	 */
	public void recursionDeleteMenukind(MenuKind menuKind){
		//查询当前栏目所拥有的菜单
		List<Object> menuid=menuManager.findBymenuKindId(menuKind.getId());
		if(menuid.size()>0){
			String tempmenuid=menuid.toString().substring(1,menuid.toString().length()-1);
			//删除角色所关联的菜单
			role_MenuManager.deleteByMenuId(tempmenuid);
			//删除菜单关联的权限
			menu_OperationManager.deletebyMenuId(tempmenuid);
			//删除菜单
			menuManager.deleteInID(tempmenuid);
		}
		//如果还有子类，那么继续遍历子类，调用自身继续删，直接删到没有为止
		if(menuKind.getMenuKinds().size()>0){
			Iterator<MenuKind> it=menuKind.getMenuKinds().iterator();
			while(it.hasNext()){
				MenuKind recursion=it.next();
				recursionDeleteMenukind(recursion);
			}
		}
	}
	
	/**
	 * @content 菜单查询
	 * @return
	 * @throws Exception 
	 * ,results={@Result(name="list",location="../authority/menu-list.jsp")}
	 */
	@Action(value="menuList")
	public void menuList() throws Exception{
		String name=request.getParameter("name");
		String url=request.getParameter("url");
		List<Menu> list=menuManager.menuList(name,url);
		List gridModel = new ArrayList();
		for(int i=0;i<list.size();i++){
			Map<String, Object> cellMap = new HashMap<String, Object>();
			Menu menu=list.get(i);
			cellMap.put("id", menu.getId());
			cellMap.put("name", menu.getMenuKind().getName());
			cellMap.put("submenu", menu.getName());
			cellMap.put("url", menu.getUrl());
			cellMap.put("Operating", "<a href=\'javascript:void(0);\' onclick=\'updateMenu("+menu.getId()+")\' style=\'color:#090;margin-right:20px;\'>编辑</a><a href=\'javascript:void(0);\' onclick=\'deleteMenu("+menu.getId()+")\' style=\'color:#F00;\'>删除</a>");
			gridModel.add(cellMap);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("gridModel", gridModel);
		map.put("rows", this.getRows());
		map.put("page", request.getAttribute("currpage"));
		map.put("total", request.getAttribute("totalPage"));
		map.put("record", request.getAttribute("pagecount"));
		doResult(JSONObject.fromObject(map).toString(),"text/json;charset=UTF-8");
	}
	
	/**
	 * @content 菜单输入页面
	 * @return
	 */
	@Action(value="menuinput",results={@Result(name="input",location="../authority/menu-input.jsp")})
	public String menuinput(){
		if(id!=null){
			menu=menuManager.getId(id);
		}else{
			menu=new Menu();
		}
		list=menuKindManager.menukindList();
		return "input";
	}
	
	
	/**
	 * @content 删除菜单
	 * @return
	 * @throws Exception
	 * ,results={@Result(name="list",location="menuList.action",type="redirect")}
	 */
	@Action(value="deleteMenu")
	public void deleteMenu() throws Exception{
		String rspStatus = "1";
		try {
			String id=request.getParameter("id");
			if(id!=null && !id.equals("")){
				//删除角色所关联的菜单
				role_MenuManager.deleteByMenuId(id);
				//删除菜单关联的权限
				menu_OperationManager.deletebyMenuId(id);
				//删除菜单
				menuManager.delete(Long.valueOf(id));
			}else{
				rspStatus = "9";
			}
		} catch (Exception e) {
			e.printStackTrace();
			rspStatus = "9";
		}
		doResult(rspStatus,"text/json;charset=UTF-8");
	}
	
	/**
	 * @content 添加菜单
	 * @throws Exception
	 */
	@Action(value="saveMenu")
	public void saveMenu() throws Exception{
		String rspStatus = "1";
		try {
			menuManager.save(menu);
		} catch (Exception e) {
			e.printStackTrace();
			rspStatus = "9";
		}
		doResult(rspStatus,"text/json;charset=UTF-8");
	}
	
	/**
	 * @throws Exception 
	 * @content 添加角色
	 */
	@Action(value="saveRole")//}
	public void saveRole() throws Exception{
		String rspStatus = "1";
		try {
			roleManager.save(role);
		} catch (Exception e) {
			e.printStackTrace();
			rspStatus = "9";
		}
		doResult(rspStatus,"text/json;charset=UTF-8");
	}
	
	/**
	 * content 删除角色
	 * @return
	 * @throws Exception
	 * ,results={@Result(name="list",location="list.action",type="redirect")}
	 */
	@Action(value="deleteRole")
	public void deleteRole() throws Exception{
		String rspStatus = "1";
		try {
			//删除角色所关联的菜单
			role_MenuManager.deleteByRoleId(id.toString());
			roleManager.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
			rspStatus="9";
		}
		doResult(rspStatus,"text/json;charset=UTF-8");
	}
	
	/**
	 * @content 添加角色页面
	 * @return
	 */
	@Action(value="inputRole",results={@Result(name="input",location="../authority/role-input.jsp")})
	public String inputRole(){
		if(id!=null && id>0){
			role=roleManager.getRole(id);
		}else{
			role=new Role();
		}
		return "input";
	}
	
	/**
	 * @content 查询所有角色
	 * @return
	 * @throws Exception 
	 */
	@Action(value="roleList")//,results={@Result(name="list",location="../authority/role-list.jsp")}
	public void roleList() throws Exception{
		String name=request.getParameter("name");
		List<Role> list=roleManager.roleList(name, this.getPage(), this.getRows());
		int count=roleManager.roleListCount(name);
		List gridModel = new ArrayList();
		for(int i=0;i<list.size();i++){
			Map<String, Object> cellMap = new HashMap<String, Object>();
			Role role=list.get(i);
			cellMap.put("id", role.getId());
			cellMap.put("papel", role.getName());
			cellMap.put("usuario", "<a style=\'color:#0C3;\' href=\'#\' onclick=\'addpapel("+role.getId()+");\'>添加用户</a>");
			cellMap.put("menu", "<a href=\'#\' style=\'color:#F60;\' onclick=\'addmenu("+role.getId()+");\'>添加菜单</a>");
//			cellMap.put("permisos", "<a href=\'#\' style=\'color:#0CF;\' onclick=\'addpermisos("+role.getId()+");\'>添加权限</a>");
			cellMap.put("Operating", "<a href=\'javascript:void(0);\' onclick=\'updateRole("+role.getId()+")\' style=\'color:#090;margin-right:20px;\'>编辑</a><a href=\'javascript:void(0);\' onclick=\'deleteRole("+role.getId()+")\' style=\'color:#F00;\'>删除</a>");
			gridModel.add(cellMap);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("gridModel", gridModel);
		map.put("rows", this.getRows());
		map.put("page", request.getAttribute("currpage"));
		map.put("total", request.getAttribute("totalPage"));
		map.put("record", request.getAttribute("pagecount"));
		doResult(JSONObject.fromObject(map).toString(),"text/json;charset=UTF-8");
	}
	
	/**
	 * @content 角色添加用户页面
	 * @return
	 */
	@Action(value="setRole",results={@Result(name="addUser",location="../authority/role-addUser.jsp")})
	public String setRole(){
		String roleid=request.getParameter("roleid");
		Role role=roleManager.getRole(Long.valueOf(roleid));
		List list=userManage.listUser(null,1, 15);
		request.setAttribute("role", role);
		request.setAttribute("list", list);
		return "addUser";
	}
	
	/**
	 * @content 角色添加菜单页面
	 * @return
	 */
	@Action(value="addMenuDetail",results={@Result(name="addMenu",location="../authority/role-addMenu.jsp")})
	public String addMenuDetail(){
		String roleid=request.getParameter("roleid");
		Role role=roleManager.getRole(Long.valueOf(roleid));
		List<Object> menukindList=menuKindManager.menukindList();
		List list=menuManager.menuList(null,null);
		request.setAttribute("role", role);
		request.setAttribute("list", list);
		request.setAttribute("menukindList", menukindList);
		return "addMenu";
	}
	
	
	/**
	 * @content 角色添加菜单页面
	 * @return
	 */
	@Action(value="addOperationDetail",results={@Result(name="addOperation",location="../authority/role-addOperation.jsp")})
	public String addOperationDetail(){
		String roleid=request.getParameter("roleid");
		Role role=roleManager.getRole(Long.valueOf(roleid));
		List list=operationManager.operationList(null,null);
		List<Object> menukindList=menuKindManager.menukindList();
		request.setAttribute("role", role);
		request.setAttribute("list", list);
		request.setAttribute("menukindList", menukindList);
		return "addOperation";
	}
	
	/**
	 * @content 某角色的某菜单权限查询
	 * @return
	 * @throws Exception
	 */
	@Action(value="findOperation",results={@Result(name="addOperation",location="../authority/role-addOperation2.jsp")})
	public String findOperation() throws Exception{
		String menuid=request.getParameter("menuid");
		String roleid=request.getParameter("roleid");
		Menu menu=menuManager.getId(Long.valueOf(menuid));
		Role role=roleManager.getRole(Long.valueOf(roleid));
		List list=operationManager.operationList(null,null);
		request.setAttribute("list", list);
		Set set=new HashSet();
		set.add(menu);
		role.getMenus().retainAll(set);
		request.setAttribute("menus", role.getMenus());
		request.setAttribute("role", role);
		return "addOperation";
	}
	
	/**
	 * @content 角色添加用户
	 * @return
	 * @throws Exception 
	 * ,results={@Result(name="list",location="list.action",type="redirect")}
	 */
	@Action(value="addOperation")
	public void addOperation() throws Exception{
		String rspStatus = "1";
		try {
			String roleid=request.getParameter("roleid");
			String menuid=request.getParameter("menuid");
			Menu menu=menuManager.getId(Long.valueOf(menuid));
			Role role=roleManager.getRole(Long.valueOf(roleid));
			String operationid[]=request.getParameter("operationid").split(",");
			Set<Operation> set=new HashSet<Operation>();
			if(!operationid[0].equals("")){
				for(int i=0;i<operationid.length;i++){
					Operation operation=operationManager.get(Long.valueOf(operationid[i]));
					if(operation!=null){
						set.add(operation);
					}
				}
			}
			
			if(role!=null){
				Iterator<Menu> iterator=role.getMenus().iterator();
				while(iterator.hasNext()){
					Menu menu2=iterator.next();
					if(menu2.getId()==menu.getId()){
						menu2.setOperations(set);
					}
				}
				roleManager.save(role);
			}
		} catch (Exception e) {
			e.printStackTrace();
			rspStatus="9";
		}
		doResult(rspStatus,"text/json;charset=UTF-8");
	}
	
	/**
	 * @content 角色页面添加权限
	 * @return
	 * @throws Exception 
	 * ,results={@Result(name="list",location="list.action",type="redirect")}
	 */
	@Action(value="addMenu")
	public void addMenu() throws Exception{
		String rspStatus = "1";
		try {
			String roleid=request.getParameter("roleid");
			Role role=roleManager.getRole(Long.valueOf(roleid));
			String menuid[]=request.getParameter("menuid").split(",");
			Set<Menu> set=new HashSet<Menu>();
			if(!menuid[0].equals("")){
				for(int i=0;i<menuid.length;i++){
					Menu menu=menuManager.getId(Long.valueOf(menuid[i]));
					if(menu!=null){
						set.add(menu);
					}
				}
			}
			if(role!=null){
				role.setMenus(set);
				roleManager.save(role);
			}
		} catch (Exception e) {
			e.printStackTrace();
			rspStatus="9";
		}
		doResult(rspStatus,"text/json;charset=UTF-8");
	}
	
	/**
	 * @content 角色添加用户
	 * @return
	 * @throws Exception 
	 * ,results={@Result(name="list",location="list.action",type="redirect")}
	 */
	@Action(value="addUser")
	public void addUser() throws Exception{
		String rspStatus = "1";
		try {
			String roleid=request.getParameter("roleid");
			Role role=roleManager.getRole(Long.valueOf(roleid));
			String userid[]=request.getParameter("userid").split(",");
			Set<User> set=new HashSet<User>();
			if(!userid[0].equals("")){
				for(int i=0;i<userid.length;i++){
					User user=userManage.getId(Long.valueOf(userid[i]));
					if(user!=null){
						set.add(user);
					}
				}
			}
			
			if(role!=null){
				role.setUser(set);
				roleManager.save(role);
			}
		} catch (Exception e) {
			e.printStackTrace();
			rspStatus="9";
		}
		doResult(rspStatus,"text/json;charset=UTF-8");
	}
	
	/**
	 * @content 角色查询列表
	 * @return  struts2 截拦器不支持jsp页面，所以通过中间action跳转jsp页面
	 */
	@Action(value="role-list",results={@Result(name="list",location="../authority/role-list.jsp")})
	public String role_list(){return "list";}
	
	/**
	 * @content 角色添加
	 * @return  struts2 截拦器不支持jsp页面，所以通过中间action跳转jsp页面
	 */
	@Action(value="role-input",results={@Result(name="input",location="../authority/role-input.jsp")})
	public String role_input(){return "input";}
	
	
	/**
	 * @content 菜单查询列表
	 * @return  struts2 截拦器不支持jsp页面，所以通过中间action跳转jsp页面
	 */
	@Action(value="menu-list",results={@Result(name="list",location="../authority/menu-list.jsp")})
	public String menu_list(){return "list";}
	
	/**
	 * @content 权限查询列表
	 * @return  struts2 截拦器不支持jsp页面，所以通过中间action跳转jsp页面
	 */
	@Action(value="operation-list",results={@Result(name="list",location="../authority/operation-list.jsp")})
	public String operation_list(){return "list";}
	
	/**
	 * @content 栏目查询列表
	 * @return  struts2 截拦器不支持jsp页面，所以通过中间action跳转jsp页面
	 */
	@Action(value="menukind-list",results={@Result(name="list",location="../authority/menukind-list.jsp")})
	public String menukind_list(){return "list";}
	
	/**
	 * @content ajax使用
	 * @param rsp
	 * @param type
	 * @throws Exception
	 */
	private void doResult(String rsp,String type) throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
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
