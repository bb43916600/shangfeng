package com.bb2004.service.kaka;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.bb2004.dao.kaka.ExtraChargeDao;
import com.bb2004.entity.kaka.ExtraCharge;

@Component
@Transactional
public class ExtraChargeManage {
	private ExtraChargeDao extraChargeDao;
	@Autowired
	public void setExtraChargeDao(ExtraChargeDao extraChargeDao) {
		this.extraChargeDao = extraChargeDao;
	}
	
	/**
	 * @content 保存
	 * @param user
	 */
	public void save(Object object){
		extraChargeDao.save(object);
	}
	
	/**
	 * @content 根据ID删除
	 * @param id
	 */
	public void delete(Long id){
		extraChargeDao.delete(id);
	}
	
	/**
	 * @content 根据ID获取实体类
	 * @param id
	 * @return content
	 */
	public ExtraCharge getId(Long id){
		return extraChargeDao.get(id);
	}
	
	/**
	 * @content Object 查询
	 * @return
	 */
	public List<ExtraCharge> listObjecr(ExtraCharge extraCharge,int curpage,int pagesize){
		return extraChargeDao.listObjecr(extraCharge, curpage, pagesize);
	}

}
