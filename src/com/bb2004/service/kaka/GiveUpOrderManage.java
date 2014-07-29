package com.bb2004.service.kaka;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.bb2004.dao.kaka.GiveUpOrderDao;
import com.bb2004.entity.authority.User;
import com.bb2004.entity.kaka.GiveUpOrder;

@Component
@Transactional
public class GiveUpOrderManage {
	private GiveUpOrderDao giveUpOrderDao;
	@Autowired
	public void setGiveUpOrderDao(GiveUpOrderDao giveUpOrderDao) {
		this.giveUpOrderDao = giveUpOrderDao;
	}
	
	/**
	 * @content 保存
	 * @param user
	 */
	public void save(Object object){
		giveUpOrderDao.save(object);
	}
	
	/**
	 * @content 根据ID删除
	 * @param id
	 */
	public void delete(Long id){
		giveUpOrderDao.delete(id);
	}
	
	/**
	 * @content 根据ID获取实体类
	 * @param id
	 * @return content
	 */
	public GiveUpOrder getId(Long id){
		return giveUpOrderDao.get(id);
	}
	
	/**
	 * @content Object 查询
	 * @return
	 */
	public List<GiveUpOrder> listObjecr(GiveUpOrder giveUpOrder,int curpage,int pagesize){
		return giveUpOrderDao.listObjecr(giveUpOrder, curpage, pagesize);
	}
	
	/**
	 * @content统计用户的弹单数量
	 * @param appUserId
	 * @return
	 */
	public int findbyAppUser(Long appUserId){
		return giveUpOrderDao.findbyAppUser(appUserId);
	}
	
	/**
	 * @content 查询所有弹单记录
	 * @param appUserId
	 * @return
	 */
	public List findbyAppUserId(Long appUserId){
		return giveUpOrderDao.findbyAppUserId(appUserId);
	}
	
	/**
	 * @content 查询所有总数
	 * @return hql;
	 */
	public int addHqlCount(GiveUpOrder giveUpOrder){
		return giveUpOrderDao.addHqlCount(giveUpOrder);
	}
}
