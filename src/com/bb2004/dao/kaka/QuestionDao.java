package com.bb2004.dao.kaka;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Component;

import com.bb2004.common.HibernateDao;
import com.bb2004.entity.kaka.Question;

@Component
public class QuestionDao extends HibernateDao<Question> {
	
	/**
	 * @content Object 查询
	 * @return
	 */
	public List<Question> listObjecr(Question question,int curpage,int pagesize){
		StringBuffer hql=addHql(question);
		Query query=this.getSession().createQuery(hql.toString());
		query.setFirstResult((curpage-1)*pagesize);
		query.setMaxResults(pagesize);
		return query.list();
	}
	
	/**
	 * @content 所有查询经过这层关卡
	 * @return hql;
	 */
	public StringBuffer addHql(Question question){
		StringBuffer hql=new StringBuffer(" from Question t where 1=1 ");
		if(question == null)
			return hql;
		if(question.getCt_content()!=null && !question.getCt_content().equals("")){
			hql.append(" and t.ct_content like '%"+question.getCt_content()+"%'");
		}
		if(question.getEn_content()!=null && !question.getEn_content().equals("")){
			hql.append(" and t.en_content like '%"+question.getEn_content()+"%'");
		}
		if(question.getCn_content()!=null && !question.getCn_content().equals("")){
			hql.append(" and t.cn_content like '%"+question.getCn_content()+"%'");
		}
		hql.append(" order by t.inputTime desc");
		return hql;
	}
	
}
