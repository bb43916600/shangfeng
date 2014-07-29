package com.bb2004.service.kaka;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.bb2004.dao.kaka.HolidayTimeDao;
import com.bb2004.entity.kaka.HolidayTime;

@Component
@Transactional
public class HolidayTimeManage {
	private HolidayTimeDao holidayTimeDao;
	@Autowired
	public void setHolidayTimeDao(HolidayTimeDao holidayTimeDao) {
		this.holidayTimeDao = holidayTimeDao;
	}
	
	/**
	 * @content 保存
	 * @param user
	 */
	public void save(Object object){
		holidayTimeDao.save(object);
	}
	
	/**
	 * @content 根据ID删除
	 * @param id
	 */
	public void delete(Long id){
		holidayTimeDao.delete(id);
	}
	
	/**
	 * @content 根据ID获取实体类
	 * @param id
	 * @return content
	 */
	public HolidayTime getId(Long id){
		return holidayTimeDao.get(id);
	}
	
	/**
	 * @content Object 查询
	 * @return
	 */
	public List<HolidayTime> listObjecr(HolidayTime holidayTime,int curpage,int pagesize){
		return holidayTimeDao.listObjecr(holidayTime, curpage, pagesize);
	}
	
	/**
	 * @content 查询所有总数
	 * @return hql;
	 */
	public int addHqlCount(HolidayTime holidayTime){
		return holidayTimeDao.addHqlCount(holidayTime);
	}

}
