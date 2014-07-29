package com.bb2004.service.kaka;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.bb2004.dao.kaka.TrafficDao;
import com.bb2004.entity.kaka.Traffic;

@Component
@Transactional
public class TrafficManage {
	private TrafficDao trafficDao;
	@Autowired
	public void setTrafficDao(TrafficDao trafficDao) {
		this.trafficDao = trafficDao;
	}
	
	/**
	 * @content 保存
	 * @param user
	 */
	public void save(Object object){
		trafficDao.save(object);
	}
	
	/**
	 * @content 根据ID删除
	 * @param id
	 */
	public void delete(Long id){
		trafficDao.delete(id);
	}
	
	/**
	 * @content 根据ID获取实体类
	 * @param id
	 * @return content
	 */
	public Traffic getId(Long id){
		return trafficDao.get(id);
	}
	
	/**
	 * @content Object 查询
	 * @return
	 */
	public List<Traffic> listObjecr(Traffic traffic,int curpage,int pagesize){
		return trafficDao.listObjecr(traffic, curpage, pagesize);
	}

}
