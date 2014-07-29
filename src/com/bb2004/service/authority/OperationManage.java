package com.bb2004.service.authority;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.bb2004.dao.authority.OperationDao;
import com.bb2004.entity.authority.Operation;


@Component
@Transactional
public class OperationManage {
	private OperationDao operationDao;
	@Autowired
	public void setOperationDao(OperationDao operationDao) {
		this.operationDao = operationDao;
	}
	
	/** 保存  */
	public void save(Operation operation){
		operationDao.save(operation);
	}
	
	/** 删除  */
	public void delete(Long id){
		operationDao.delete(id);
	}
	
	/** getOperation  */
	public Operation get(Long id){
		return operationDao.get(id);
	}
	
	/**
	 * @content 查询所有菜单 或者条件查询
	 * @param name 权限的名称
	 * @param name 权限的url
	 * @return List<Operation>
	 */
	public List<Operation> operationList(String name,String url){
		return operationDao.operationList(name, url);
	}
}
