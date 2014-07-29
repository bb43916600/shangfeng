package com.bb2004.web.feng;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

import com.bb2004.common.CrudActionSupport;
import com.bb2004.common.HibernateDao;
import com.bb2004.dao.AllDao;
import com.bb2004.entity.feng.Industry;
import com.bb2004.entity.feng.Industry;

/**
 * @content 产业
 * @author 龙华雄
 * @param <T>
 * @time 2014-07-23
 */
@Namespace("/industry")
public class IndustryAction<T> extends CrudActionSupport {
	private Long id;
	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}
	private String name;
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}
	private int type;
	public int getType() {return type;}
	public void setType(int type) {this.type = type;}
	
	//产业
	private Industry industry;
	public Industry getIndustry() {return industry;}
	public void setIndustry(Industry industry) {this.industry = industry;}
	
	private HibernateDao<T> hibernateDao;
	public HibernateDao<T> getHibernateDao() {return hibernateDao;}
	public void setHibernateDao(HibernateDao<T> hibernateDao) {this.hibernateDao = hibernateDao;}
	private AllDao<T> allDao;
	public AllDao<T> getAllDao() {return allDao;}
	public void setAllDao(AllDao<T> allDao) {this.allDao = allDao;}
	
	//==================================================产业=======================================================================================
	
	@Action(value="industryInput",results={@Result(name="input",location="../admin/feng/industry-input.jsp")})
	public String industryInput(){
		if(id!=null &&  id > 0){
			industry=hibernateDao.get(Industry.class, id);
		}else{
			industry=new Industry();
			industry.setType(type);
		}
		return INPUT;
	}
	
	/**
	 * @content 添加Object
	 * @throws Exception
	 */
	@Action(value="industrySave")
	public void industrySave() throws Exception{
		String rspStatus = "1";
		try {
			if(industry == null){
				return;
			}
			industry.setInputTime(new Date());
			hibernateDao.save(industry);
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
	@Action(value="industryDelete")
	public void industryDelete() throws Exception{
		String rspStatus = "1";
		try {
			industry = hibernateDao.get(Industry.class, id);
			if(industry!=null) {
				hibernateDao.delete(industry);
			}
		} catch (Exception e) {
			e.printStackTrace();
			rspStatus="9";
		}
		doResult(rspStatus,"text/json;charset=UTF-8");
	}
	
	/**
	 * @content 查询用户
	 * @throws Exceptio
	 * 
	 */
	@Action(value="industryList")
	public void industryList() throws Exception{
		StringBuffer hql = new StringBuffer(" from Industry t where 1= 1");
		if(type>0){
			hql.append(" and t.type="+type);
		}
		if(name!=null && !name.trim().equals("")){
			hql.append(" and t.title like '%"+name+"%'");
		}
		List<Industry> list=(List<Industry>) allDao.listObjecr(hql.toString(), this.getPage(), this.getRows());
		List gridModel = new ArrayList();
		for(int i=0;i<list.size();i++){
			Map<String, Object> cellMap = new HashMap<String, Object>();
			industry=list.get(i);
			cellMap.put("id", industry.getId());
			cellMap.put("title", industry.getTitle());
			cellMap.put("content", industry.getContent());
			cellMap.put("inputTime", industry.getInputTime().toString().substring(0, 10));
			cellMap.put("operation", "<a href=\'javascript:void(0);\' onclick=\'doUpdate("+industry.getId()+")\' style=\'color:#090;margin-right:20px;\'>编辑</a><a href=\'javascript:void(0);\' onclick=\'doDelete("+industry.getId()+")\' style=\'color:#F00;\'>删除</a>");
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
