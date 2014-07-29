package com.bb2004.service.kaka;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.bb2004.dao.kaka.AreaDao;
import com.bb2004.entity.authority.User;
import com.bb2004.entity.kaka.Area;

@Component
@Transactional
public class AreaManage {
	private AreaDao areaDao;
	@Autowired
	public void setAreaDao(AreaDao areaDao) {
		this.areaDao = areaDao;
	}
	
	/**
	 * @content 保存
	 */
	public void save(Object object){
		areaDao.save(object);
	}
	
	/**
	 * @content 根据ID删除
	 * @param id
	 */
	public void delete(Long id){
		areaDao.delete(id);
	}
	
	/**
	 * @content 根据ID获取实体类
	 * @param id
	 */
	public Area getId(Long id){
		return areaDao.get(id);
	}
	
	/**
	 * @content Object 查询
	 * @return
	 */
	public List<Area> listObjecr(Area area,int curpage,int pagesize){
		return areaDao.listObjecr(area, curpage, pagesize);
	}

}
