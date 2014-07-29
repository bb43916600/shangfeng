//package com.bb2004.common;
//
//
//import java.sql.Timestamp;
//
//import org.apache.struts2.ServletActionContext;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.After;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//
//import com.bb2004.entity.authority.User;
//import com.bb2004.entity.log.Log;
//import com.bb2004.common.HibernateDao;
//
//
//
///**
// * @content 使用AspectJ实现登录登出日志AOP
// * @author BB2004
// */
//@Aspect
//public class LogAspect {
//
//	@Autowired
//	@Qualifier("hibernateDao")
//	private HibernateDao hibernateDao;
//	
//	/**
//	 * @content 登录增强
//	 * @param joinPoint 切入点
//	 */
//	@After("execution(* com.bb2004.web.user.UserAction.login(..))")
//	public void afterLoginLog(JoinPoint joinPoint) throws Throwable{
//		User user = (User)ServletActionContext.getRequest().getSession().getAttribute("user");
//		if(user != null){
//			saveLog(user,joinPoint); //保存日志
//		}
//	}
//	
//	/**
//	 * @content 登出增强
//	 * @param joinPoint 切入点
//	 */
//	@Before("execution(* com.bb2004.web.user.UserAction.login(..))")
//	public void beforeLogoutLog(JoinPoint joinPoint) throws Throwable{
//		User user = (User)ServletActionContext.getRequest().getSession().getAttribute("user");
//		if(user != null){
//			saveLog(user,joinPoint); //保存日志
//		}
//	}
//	
//	/**
//	 * @content 保存日志
//	 * @param user 登录人员
//	 * @param joinPoint 切入点
//	 */
//	private void saveLog(User user, JoinPoint joinPoint){
//		Log log = new Log();
//		log.setLog_name(user.getUserAccount()); //登录名
//		log.setLog_methodname(joinPoint.getSignature().getName()); //切入方法
//		log.setLog_time(new Timestamp(System.currentTimeMillis())); //时间戳
//		hibernateDao.save(log);
//	}
//}
