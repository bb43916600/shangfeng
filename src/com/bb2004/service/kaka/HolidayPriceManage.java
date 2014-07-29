package com.bb2004.service.kaka;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.bb2004.dao.kaka.HolidayPriceDao;
import com.bb2004.entity.kaka.HolidayPrice;

@Component
@Transactional
public class HolidayPriceManage {
	private HolidayPriceDao holidayPriceDao;
	@Autowired
	public void setHolidayPriceDao(HolidayPriceDao holidayPriceDao) {
		this.holidayPriceDao = holidayPriceDao;
	}
	
	/**
	 * @content 保存
	 */
	public void save(Object object){
		holidayPriceDao.save(object);
	}
	
	/**
	 * @content 根据ID删除
	 * @param id
	 */
	public void delete(Long id){
		holidayPriceDao.delete(id);
	}
	
	/**
	 * @content 根据ID获取实体类
	 * @param id
	 */
	public HolidayPrice getId(Long id){
		return holidayPriceDao.get(id);
	}
	
	/**
	 * @content Object 查询
	 * @return
	 */
	public List<HolidayPrice> listObjecr(HolidayPrice holidayPrice,int curpage,int pagesize){
		return holidayPriceDao.listObjecr(holidayPrice, curpage, pagesize);
	}
}
