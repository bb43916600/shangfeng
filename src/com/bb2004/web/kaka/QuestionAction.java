package com.bb2004.web.kaka;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.bb2004.common.CrudActionSupport;
import com.bb2004.entity.kaka.Fee;
import com.bb2004.entity.kaka.MoothFee;
import com.bb2004.entity.kaka.Question;
import com.bb2004.service.kaka.QuestionManage;

/**
 * 常见问题表
 * @author longhuaxiong
 *
 */

@Namespace("/question")
public class QuestionAction extends CrudActionSupport{
	private Long id;
	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}
	private List list;
	public List getList() {return list;}
	public void setList(List list) {this.list = list;}
	private String name;
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}
	
	private Question question;
	public Question getQuestion() {return question;}
	public void setQuestion(Question question) {this.question = question;}
	
	private QuestionManage questionManage;
	@Autowired
	public void setQuestionManage(QuestionManage questionManage) {this.questionManage = questionManage;}
	
	/**
	 * @content 进入编辑页面
	 * @return
	 */
	@Action(value="objectInput",results={@Result(name="input",location="../admin/kaka/question-input.jsp")})
	public String objectInput(){
		List<Question> list=questionManage.listObjecr(question, this.getPage(), this.getRows());
		if(list.size() > 0){
			question=list.get(0);
		}else{
			question=new Question();
		}
		return INPUT;
	}
	
	/**
	 * @content 添加Object
	 * @throws Exception
	 */
	@Action(value="objectSave")
	public void objectSave() throws Exception{
		String rspStatus = "1";
		try {
			if(question == null){
				return;
			}
			question.setInputTime(new Date());
			questionManage.save(question);
		} catch (Exception e) {
			e.printStackTrace();
			rspStatus = "9";
		}
		doResult(rspStatus,"text/json;charset=UTF-8");
	}
	
	/**
	 * content 删除对象
	 * @return
	 * @throws Exception
	 */
	@Action(value="objectDelete")
	public void objectDelete() throws Exception{
		String rspStatus = "1";
		try {
			question = questionManage.getId(id);
			if(question!=null) {
				questionManage.delete(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
			rspStatus="9";
		}
		doResult(rspStatus,"text/json;charset=UTF-8");
	}
	
	/**
	 * @content 查询用户
	 * @throws Exception
	 */
	@Action(value="objectList")
	public void objectList() throws Exception{
		List<Question> list=questionManage.listObjecr(question, this.getPage(), this.getRows());
		List gridModel = new ArrayList();
		for(int i=0;i<list.size();i++){
			Map<String, Object> cellMap = new HashMap<String, Object>();
			question=list.get(i);
			cellMap.put("id", question.getId());
			cellMap.put("ct_areaName", question.getCt_content());
			cellMap.put("en_areaName", question.getEn_content());
			cellMap.put("cn_areaName", question.getCn_content());
			cellMap.put("inputTime", question.getInputTime().toString().substring(0, 10));
			cellMap.put("operation", "<a href=\'javascript:void(0);\' onclick=\'doUpdate("+question.getId()+")\' style=\'color:#090;margin-right:20px;\'>编辑</a><a href=\'javascript:void(0);\' onclick=\'doDelete("+question.getId()+")\' style=\'color:#F00;\'>删除</a>");
			gridModel.add(cellMap);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("gridModel", gridModel);
		map.put("rows", this.getRows());
		map.put("page", request.getAttribute("currpage"));
		map.put("total", request.getAttribute("totalPage"));
		map.put("record", request.getAttribute("pagecount"));
		doResult(JSONObject.fromObject(map).toString(),"text/json;charset=UTF-8");
	}
	
//======================================APP============================================================

	/**
	 * @content 常见问题
	 * @throws Exception
	 */
	@Action(value="wapList",results={@Result(name="detail",location="../admin/kaka/question-detail.jsp")})
	public String wapList() throws Exception{
		String currentLanguage = request.getParameter("currentLanguage");
		List<Question> list=questionManage.listObjecr(question, this.getPage(), this.getRows());
		if(list.size()>0){
			question = list.get(0);
			if(currentLanguage.equals("ct"))
				name = question.getCt_content();
			else if(currentLanguage.equals("en"))
				name = question.getEn_content();
			else
				name =question.getCn_content();
		}
		return "detail";
//		List<Question> list=questionManage.listObjecr(question, this.getPage(), this.getRows());
//		List gridModel = new ArrayList();
//		for(int i=0;i<list.size();i++){
//			Map<String, Object> cellMap = new HashMap<String, Object>();
//			question=list.get(i);
//			cellMap.put("id", question.getId());
//			cellMap.put("ct_areaName", question.getCt_content());
//			cellMap.put("en_areaName", question.getEn_content());
//			cellMap.put("cn_areaName", question.getCn_content());
//			cellMap.put("inputTime", question.getInputTime().toString().substring(0, 10));
//			gridModel.add(cellMap);
//		}
//		doResult(JSONArray.fromObject(gridModel).toString(),"text/json;charset=UTF-8");
	}
	
	/**
	 * @content ajax使用
	 * @param rsp
	 * @param type
	 * @throws Exception
	 */
	private void doResult(String rsp,String type) throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		if(type==null || type.length()==0)
		    response.setCharacterEncoding("UTF-8");
		else
			response.setContentType(type); 
		response.setHeader("Cache-Control", "no-store, max-age=0, no-cache, must-revalidate");    
		response.addHeader("Cache-Control", "post-check=0, pre-check=0");   
		response.setHeader("Pragma", "no-cache"); 
		response.getWriter().write(rsp);
		response.getWriter().flush();
		response.getWriter().close();
	}
}
