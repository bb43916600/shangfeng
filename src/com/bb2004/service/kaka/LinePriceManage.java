package com.bb2004.service.kaka;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.bb2004.dao.kaka.LinePriceDao;
import com.bb2004.entity.authority.User;
import com.bb2004.entity.kaka.LinePrice;

@Component
@Transactional
public class LinePriceManage {
	private LinePriceDao linePriceDao;
	@Autowired
	public void setLinePriceDao(LinePriceDao linePriceDao) {
		this.linePriceDao = linePriceDao;
	}
	
	/**
	 * @content 保存
	 */
	public void save(Object object){
		linePriceDao.save(object);
	}
	
	/**
	 * @content 根据ID删除
	 * @param id
	 */
	public void delete(Long id){
		linePriceDao.delete(id);
	}
	
	/**
	 * @content 根据ID获取实体类
	 * @param id
	 */
	public LinePrice getId(Long id){
		return linePriceDao.get(id);
	}
	
	/**
	 * @content Object 查询
	 * @return
	 */
	public List<LinePrice> listObjecr(LinePrice linePrice,int curpage,int pagesize){
		return linePriceDao.listObjecr(linePrice, curpage, pagesize);
	}
	
	/**
	 * @content 根据线路的开始和结束ID查询线路
	 * @param startCityId
	 * @param endCityId
	 * @return
	 */
	public LinePrice findLinePriceByStartCityIdAndEndCityId(Long startCityId,Long endCityId){
		return linePriceDao.findLinePriceByStartCityIdAndEndCityId(startCityId, endCityId);
	}
	
	/**
	 * @content 根据线路的开始和结束名称查询线路
	 * @param startCityId
	 * @param endCityId
	 * @return
	 */
	public LinePrice findLinePriceByStartCityNameAndEndCityName(String startCityName,String endCityName,String currentLanguage){
		return linePriceDao.findLinePriceByStartCityNameAndEndCityName(startCityName, endCityName,currentLanguage);
	}
	
	/**
	 * @content 查询所有总数
	 * @return hql;
	 */
	public int addHqlCount(LinePrice linePrice){
		return linePriceDao.addHqlCount(linePrice);
	}

}
