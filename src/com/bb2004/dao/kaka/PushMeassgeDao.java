package com.bb2004.dao.kaka;

import java.text.SimpleDateFormat;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Component;

import com.bb2004.common.HibernateDao;
import com.bb2004.entity.kaka.PushMeassge;

@Component
public class PushMeassgeDao extends HibernateDao<PushMeassge> {
	
	/**
	 * @content Object 查询
	 * @return
	 */
	public List<PushMeassge> listObjecr(PushMeassge pushMeassge,int curpage,int pagesize){
		StringBuffer hql=addHql(pushMeassge);
		Query query=this.getSession().createQuery(hql.toString());
		query.setFirstResult((curpage-1)*pagesize);
		query.setMaxResults(pagesize);
		return query.list();
	}
	
	/**
	 * @content 所有查询经过这层关卡
	 * @return hql;
	 */
	public StringBuffer addHql(PushMeassge pushMeassge){
		StringBuffer hql=new StringBuffer(" from PushMeassge t where 1=1 ");
		if(pushMeassge == null){
			return hql;
		}
		if(pushMeassge.getInputTime()!=null && !pushMeassge.getInputTime().equals("")){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			hql.append(" and t.inputTime between '"+sdf.format(pushMeassge.getInputTime()).toString()+" 00:00:00' and '"+sdf.format(pushMeassge.getInputTime()).toString()+" 23:59:59'");
		}
		
		hql.append(" order by t.inputTime desc");
		return hql;
	}
	
	/**
	 * @content 查询所有总数
	 * @return hql;
	 */
	public int addHqlCount(PushMeassge pushMeassge){
		StringBuffer hql=new StringBuffer(" select count(t.id) from PushMeassge t where 1=1 ");
		if(pushMeassge!=null){
			if(pushMeassge.getInputTime()!=null && !pushMeassge.getInputTime().equals("")){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				hql.append(" and t.inputTime between '"+sdf.format(pushMeassge.getInputTime()).toString()+" 00:00:00' and '"+sdf.format(pushMeassge.getInputTime()).toString()+" 23:59:59'");
			}
		}
		Query query=this.getSession().createQuery(hql.toString());
		return Integer.valueOf(query.list().get(0).toString());
	}
	
}
