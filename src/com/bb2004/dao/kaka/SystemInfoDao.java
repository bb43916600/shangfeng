package com.bb2004.dao.kaka;

import java.text.SimpleDateFormat;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Component;

import com.bb2004.common.HibernateDao;
import com.bb2004.entity.authority.User;
import com.bb2004.entity.kaka.PushMeassge;
import com.bb2004.entity.kaka.SystemInfo;

@Component
public class SystemInfoDao extends HibernateDao<SystemInfo> {
	
	/**
	 * @content Object 查询
	 * @return
	 */
	public List<SystemInfo> listObjecr(SystemInfo systemInfo,int curpage,int pagesize){
		StringBuffer hql=addHql(systemInfo);
		Query query=this.getSession().createQuery(hql.toString());
		query.setFirstResult((curpage-1)*pagesize);
		query.setMaxResults(pagesize);
		return query.list();
	}
	
	/**
	 * @content 所有查询经过这层关卡
	 * @return hql;
	 */
	public StringBuffer addHql(SystemInfo systemInfo){
		StringBuffer hql=new StringBuffer(" from SystemInfo t where 1=1 ");
		if(systemInfo == null)
			return hql;
		if(systemInfo.getCt_title()!=null && !systemInfo.getCt_title().equals("")){
			hql.append(" and t.ct_title like '%"+systemInfo.getCt_title()+"%'");
		}
		if(systemInfo.getEn_title()!=null && !systemInfo.getEn_title().equals("")){
			hql.append(" and t.en_title like '%"+systemInfo.getEn_title()+"%'");
		}
		if(systemInfo.getCn_title()!=null && !systemInfo.getCn_title().equals("")){
			hql.append(" and t.cn_title like '%"+systemInfo.getCn_title()+"%'");
		}
		if(systemInfo.getCt_content()!=null && !systemInfo.getCt_content().equals("")){
			hql.append(" and t.ct_content like '%"+systemInfo.getCt_content()+"%'");
		}
		if(systemInfo.getEn_content()!=null && !systemInfo.getEn_content().equals("")){
			hql.append(" and t.en_content like '%"+systemInfo.getEn_content()+"%'");
		}
		if(systemInfo.getCn_content()!=null && !systemInfo.getCn_content().equals("")){
			hql.append(" and t.cn_content like '%"+systemInfo.getCn_content()+"%'");
		}
		hql.append(" order by t.inputTime desc");
		return hql;
	}
	
	/**
	 * @content 查询所有总数
	 * @return hql;
	 */
	public int addHqlCount(SystemInfo systemInfo){
		StringBuffer hql=new StringBuffer(" select count(t.id) from SystemInfo t where 1=1 ");
		if(systemInfo!=null){
			if(systemInfo.getCt_title()!=null && !systemInfo.getCt_title().equals("")){
				hql.append(" and t.ct_title like '%"+systemInfo.getCt_title()+"%'");
			}
			if(systemInfo.getEn_title()!=null && !systemInfo.getEn_title().equals("")){
				hql.append(" and t.en_title like '%"+systemInfo.getEn_title()+"%'");
			}
			if(systemInfo.getCn_title()!=null && !systemInfo.getCn_title().equals("")){
				hql.append(" and t.cn_title like '%"+systemInfo.getCn_title()+"%'");
			}
			if(systemInfo.getCt_content()!=null && !systemInfo.getCt_content().equals("")){
				hql.append(" and t.ct_content like '%"+systemInfo.getCt_content()+"%'");
			}
			if(systemInfo.getEn_content()!=null && !systemInfo.getEn_content().equals("")){
				hql.append(" and t.en_content like '%"+systemInfo.getEn_content()+"%'");
			}
			if(systemInfo.getCn_content()!=null && !systemInfo.getCn_content().equals("")){
				hql.append(" and t.cn_content like '%"+systemInfo.getCn_content()+"%'");
			}
		}
		Query query=this.getSession().createQuery(hql.toString());
		return Integer.valueOf(query.list().get(0).toString());
	}
	
}
