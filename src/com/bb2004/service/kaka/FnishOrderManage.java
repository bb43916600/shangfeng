package com.bb2004.service.kaka;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.bb2004.dao.kaka.FnishOrderDao;
import com.bb2004.entity.authority.User;
import com.bb2004.entity.kaka.FnishOrder;

@Component
@Transactional
public class FnishOrderManage {
	private FnishOrderDao fnishOrderDao;
	@Autowired
	public void setFnishOrderDao(FnishOrderDao fnishOrderDao) {
		this.fnishOrderDao = fnishOrderDao;
	}
	
	/**
	 * @content 保存
	 * @param user
	 */
	public void save(Object object){
		fnishOrderDao.save(object);
	}
	
	/**
	 * @content 根据ID删除
	 * @param id
	 */
	public void delete(Long id){
		fnishOrderDao.delete(id);
	}
	
	/**
	 * @content 根据ID获取实体类
	 * @param id
	 * @return content
	 */
	public FnishOrder getId(Long id){
		return fnishOrderDao.get(id);
	}
	
	/**
	 * @content Object 查询
	 * @return
	 */
	public List<FnishOrder> listObjecr(FnishOrder fnishOrder,int curpage,int pagesize){
		return fnishOrderDao.listObjecr(fnishOrder, curpage, pagesize);
	}
	
	/**
	 * @content 查询所有历史订单
	 * @param appUserId
	 * @return
	 */
	public List findbyAppUserId(Long appUserId){
		return fnishOrderDao.findbyAppUserId(appUserId);
	}
	
	/**
	 * @content 查询所有总数
	 * @return hql;
	 */
	public int addHqlCount(FnishOrder fnishOrder){
		return fnishOrderDao.addHqlCount(fnishOrder);
	}

}
