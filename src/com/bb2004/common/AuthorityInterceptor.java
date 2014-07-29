package com.bb2004.common;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

/**
 * @content 登录验证拦截器
 * @author BB2004
 */
public class AuthorityInterceptor extends MethodFilterInterceptor{

	@Override
	protected String doIntercept(ActionInvocation actioninvocation) throws Exception {
		Object user = ServletActionContext.getRequest().getSession().getAttribute("user");
		if(user != null){
			return actioninvocation.invoke(); //递归调用拦截器
		}else{
			System.out.println("拦住了!~~");
			return Action.LOGIN; //返回到登录页面
		}
	}
}
