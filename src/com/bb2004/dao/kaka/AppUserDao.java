package com.bb2004.dao.kaka;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Component;

import com.bb2004.common.HibernateDao;
import com.bb2004.entity.kaka.AppUser;

@Component
public class AppUserDao extends HibernateDao<AppUser> {
	
	/**
	 * @content Object 查询
	 * @return
	 */
	public List<AppUser> listObjecr(AppUser appUser,int curpage,int pagesize){
		StringBuffer hql=addHql(appUser);
		Query query=this.getSession().createQuery(hql.toString());
		query.setFirstResult((curpage-1)*pagesize);
		query.setMaxResults(pagesize);
		return query.list();
	}
	
	/**
	 * @content 根据用户的等级查询
	 * @param level
	 * @return
	 */
	public List findLevelUser(int level){
		String hql=" from AppUser t where t.level = "+level;
		Query query=this.getSession().createQuery(hql);
		return query.list();
	}
	
	/**
	 * @content 统计用户的等级查询
	 * @param level
	 * @return
	 */
	public int findLevelUserCount(int level){
		String hql=" select count(t.id) from AppUser t where t.level = "+level;
		Query query=this.getSession().createQuery(hql);
		return Integer.valueOf(query.list().get(0).toString());
	}
	
	/**
	 * @content 统计某手机号码介绍人数
	 * @param phone
	 * @return
	 */
	public int countintroducerPhone(String phone){
		String hql = "select count(t.id) from AppUser t where t.introducerPhone = '"+phone+"'";
		Query query=this.getSession().createQuery(hql);
		return Integer.valueOf(query.list().get(0).toString());
	}
	
	/**
	 * @content 所有查询经过这层关卡
	 * @return hql;
	 */
	public StringBuffer addHql(AppUser appUser){
		StringBuffer hql=new StringBuffer(" from AppUser t where 1=1 ");
		if(appUser == null)
			return hql;
		if(appUser.getCarNumber()!=null && !appUser.getCarNumber().equals("")){
			hql.append(" and t.carNumber = '"+appUser.getCarNumber()+"'");
		}
		if(appUser.getPassWorld()!=null && !appUser.getPassWorld().equals("")){
			hql.append(" and t.passWorld = '"+appUser.getPassWorld()+"'");
		}
		if(appUser.getPhone()!=null && !appUser.getPhone().equals("")){
			hql.append(" and t.phone = '"+appUser.getPhone()+"'");
		}
		if(appUser.getUserName()!=null && !appUser.getUserName().equals("")){
			hql.append(" and t.userName like '%"+appUser.getUserName()+"%'");
		}
		if(appUser.getIntroducerPhone()!=null && !appUser.getIntroducerPhone().equals("")){
			hql.append(" and t.introducerPhone like '%"+appUser.getIntroducerPhone()+"%'");
		}
		hql.append(" order by t.inputTime desc");
		return hql;
	}
	
	/**
	 * @content 查询所有总数
	 * @return hql;
	 */
	public int addHqlCount(AppUser appUser){
		StringBuffer hql=new StringBuffer(" select count(t.id) from AppUser t where 1=1 ");
		if(appUser!=null){
			if(appUser.getCarNumber()!=null && !appUser.getCarNumber().equals("")){
				hql.append(" and t.carNumber = '"+appUser.getCarNumber()+"'");
			}
			if(appUser.getPassWorld()!=null && !appUser.getPassWorld().equals("")){
				hql.append(" and t.passWorld = '"+appUser.getPassWorld()+"'");
			}
			if(appUser.getPhone()!=null && !appUser.getPhone().equals("")){
				hql.append(" and t.phone like '%"+appUser.getPhone()+"%'");
			}
			if(appUser.getUserName()!=null && !appUser.getUserName().equals("")){
				hql.append(" and t.userName like '%"+appUser.getUserName()+"%'");
			}
			if(appUser.getIntroducerPhone()!=null && !appUser.getIntroducerPhone().equals("")){
				hql.append(" and t.introducerPhone like '%"+appUser.getIntroducerPhone()+"%'");
			}
		}
		Query query=this.getSession().createQuery(hql.toString());
		return Integer.valueOf(query.list().get(0).toString());
	}
	
}
