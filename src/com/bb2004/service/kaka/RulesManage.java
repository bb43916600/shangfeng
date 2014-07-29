package com.bb2004.service.kaka;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.bb2004.dao.kaka.RulesDao;
import com.bb2004.entity.kaka.Rules;

@Component
@Transactional
public class RulesManage {
	private RulesDao rulesDao;
	@Autowired
	public void setRulesDao(RulesDao rulesDao) {
		this.rulesDao = rulesDao;
	}
	
	/**
	 * @content 保存
	 * @param user
	 */
	public void save(Object object){
		rulesDao.save(object);
	}
	
	/**
	 * @content 根据ID删除
	 * @param id
	 */
	public void delete(Long id){
		rulesDao.delete(id);
	}
	
	/**
	 * @content 根据ID获取实体类
	 * @param id
	 * @return content
	 */
	public Rules getId(Long id){
		return rulesDao.get(id);
	}
	
	/**
	 * @content Object 查询
	 * @return
	 */
	public List<Rules> listObjecr(Rules rules,int curpage,int pagesize){
		return rulesDao.listObjecr(rules, curpage, pagesize);
	}

}
