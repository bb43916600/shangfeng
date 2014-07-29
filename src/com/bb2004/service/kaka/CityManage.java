package com.bb2004.service.kaka;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.bb2004.dao.kaka.CityDao;
import com.bb2004.entity.kaka.City;

@Component
@Transactional
public class CityManage {
	private CityDao cityDao;
	@Autowired
	public void setCityDao(CityDao cityDao) {
		this.cityDao = cityDao;
	}
	
	/**
	 * @content 保存
	 */
	public void save(Object object){
		cityDao.save(object);
	}
	
	/**
	 * @content 根据ID删除
	 * @param id
	 */
	public void delete(Long id){
		cityDao.delete(id);
	}
	
	/**
	 * @content 根据ID获取实体类
	 * @param id
	 */
	public City getId(Long id){
		return cityDao.get(id);
	}
	
	/**
	 * @content Object 查询
	 * @return
	 */
	public List<City> listObjecr(City city,int curpage,int pagesize){
		return cityDao.listObjecr(city, curpage, pagesize);
	}
	
	/**
	 * @content 根据城市名称查询城市
	 * @return City;
	 */
	public City getCityByName(City city){
		return cityDao.getCityByName(city);
	}
	
	/**
	 * 
	 * @param startName 开始名称
	 * @param endName 结束名称
	 * @return
	 */
	public List getStartEndLine(String startName,String endName){
		return cityDao.getStartEndLine(startName, endName);
	}
	
	/**
	 * @content 查询所有总数
	 * @return hql;
	 */
	public int addHqlCount(City city){
		return cityDao.addHqlCount(city);
	}

}
