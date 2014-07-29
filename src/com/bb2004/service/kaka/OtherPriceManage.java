package com.bb2004.service.kaka;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.bb2004.dao.kaka.OtherPriceDao;
import com.bb2004.entity.kaka.OtherPrice;

@Component
@Transactional
public class OtherPriceManage {
	private OtherPriceDao otherPriceDao;
	@Autowired
	public void setOtherPriceDao(OtherPriceDao otherPriceDao) {
		this.otherPriceDao = otherPriceDao;
	}
	
	/**
	 * @content 保存
	 */
	public void save(Object object){
		otherPriceDao.save(object);
	}
	
	/**
	 * @content 根据ID删除
	 * @param id
	 */
	public void delete(Long id){
		otherPriceDao.delete(id);
	}
	
	/**
	 * @content 根据ID获取实体类
	 * @param id
	 */
	public OtherPrice getId(Long id){
		return otherPriceDao.get(id);
	}
	
	/**
	 * @content 查询所有子类
	 * @param parentId
	 * @return
	 */
	public List findbyParentId(Long parentId){
		return otherPriceDao.findbyParentId(parentId);
	}
	
	/**
	 * @content Object 查询
	 * @return
	 */
	public List<OtherPrice> listObjecr(OtherPrice otherPrice,int curpage,int pagesize){
		return otherPriceDao.listObjecr(otherPrice, curpage, pagesize);
	}

}
