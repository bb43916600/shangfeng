package com.bb2004.dao.authority;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Component;

import com.bb2004.common.HibernateDao;
import com.bb2004.entity.authority.User;

@Component
public class UserDao extends HibernateDao<User> {
	/**
	 * @content 用户查询列表
	 * @param user
	 * @return
	 */
	public List<User> listUser(String userAccount,int curpage,int pagesize){
		StringBuffer hql=addHql(userAccount);
		Query query=this.getSession().createQuery(hql.toString());
		query.setFirstResult((curpage-1)*pagesize);
		query.setMaxResults(pagesize);
		return query.list();
	}
	
	/**
	 * @content 所有查询经过这层关卡
	 * @param user 用户
	 * @return hql;
	 */
	public StringBuffer addHql(String userAccount){
		StringBuffer hql=new StringBuffer(" from User u where 1=1 ");
		if(userAccount!=null && !userAccount.equals("")){
			hql.append(" and u.userAccount like '%"+userAccount+"%'");
		}
		return hql;
	}
	
	/**
	 * @content 根据帐号和密码查询用户 
	 * @param userAccount  帐号
	 * @param userPassWord MD5加密 
	 * @return
	 */
	public User findbyAccountPassWord(String userAccount,String userPassWord){
		String hql=" from User u where u.userAccount=? and u.userPassWord=? and u.userStatus=0 ";
		Query query=this.getSession().createQuery(hql.toString());
		query.setString(0, userAccount);
		query.setString(1, userPassWord);
		if(query.list().size()>0){
			return (User)query.list().get(0);
		}else{
			return null;
		}
	}
}
