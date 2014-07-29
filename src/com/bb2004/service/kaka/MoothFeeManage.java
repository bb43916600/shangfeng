package com.bb2004.service.kaka;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.bb2004.dao.kaka.MoothFeeDao;
import com.bb2004.entity.kaka.MoothFee;

@Component
@Transactional
public class MoothFeeManage {
	private MoothFeeDao moothFeeDao;
	@Autowired
	public void setMoothFeeDao(MoothFeeDao moothFeeDao) {
		this.moothFeeDao = moothFeeDao;
	}
	
	/**
	 * @content 保存
	 * @param user
	 */
	public void save(Object object){
		moothFeeDao.save(object);
	}
	
	/**
	 * @content 根据ID删除
	 * @param id
	 */
	public void delete(Long id){
		moothFeeDao.delete(id);
	}
	
	/**
	 * @content 根据ID获取实体类
	 * @param id
	 * @return content
	 */
	public MoothFee getId(Long id){
		return moothFeeDao.get(id);
	}
	
	/**
	 * @content Object 查询
	 * @return
	 */
	public List<MoothFee> listObjecr(MoothFee moothFee,int curpage,int pagesize){
		return moothFeeDao.listObjecr(moothFee, curpage, pagesize);
	}

}
