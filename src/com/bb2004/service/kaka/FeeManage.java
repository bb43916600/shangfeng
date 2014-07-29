package com.bb2004.service.kaka;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.bb2004.dao.kaka.FeeDao;
import com.bb2004.entity.kaka.Fee;

@Component
@Transactional
public class FeeManage {
	private FeeDao feeDao;
	@Autowired
	public void setFeeDao(FeeDao feeDao) {
		this.feeDao = feeDao;
	}
	
	/**
	 * @content 保存
	 * @param user
	 */
	public void save(Object object){
		feeDao.save(object);
	}
	
	/**
	 * @content 根据ID删除
	 * @param id
	 */
	public void delete(Long id){
		feeDao.delete(id);
	}
	
	/**
	 * @content 根据ID获取实体类
	 * @param id
	 * @return content
	 */
	public Fee getId(Long id){
		return feeDao.get(id);
	}
	
	/**
	 * @content Object 查询
	 * @return
	 */
	public List<Fee> listObjecr(Fee fee,int curpage,int pagesize){
		return feeDao.listObjecr(fee, curpage, pagesize);
	}

}
