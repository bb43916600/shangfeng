package com.bb2004.service.kaka;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.bb2004.dao.kaka.QuestionDao;
import com.bb2004.entity.kaka.Question;

@Component
@Transactional
public class QuestionManage {
	private QuestionDao questionDao;
	@Autowired
	public void setQuestionDao(QuestionDao questionDao) {
		this.questionDao = questionDao;
	}
	
	/**
	 * @content 保存
	 * @param user
	 */
	public void save(Object object){
		questionDao.save(object);
	}
	
	/**
	 * @content 根据ID删除
	 * @param id
	 */
	public void delete(Long id){
		questionDao.delete(id);
	}
	
	/**
	 * @content 根据ID获取实体类
	 * @param id
	 * @return content
	 */
	public Question getId(Long id){
		return questionDao.get(id);
	}
	
	/**
	 * @content Object 查询
	 * @return
	 */
	public List<Question> listObjecr(Question question,int curpage,int pagesize){
		return questionDao.listObjecr(question, curpage, pagesize);
	}

}
