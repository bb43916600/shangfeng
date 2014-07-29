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
import com.bb2004.entity.feng.CompanyOutlook;
import com.bb2004.entity.feng.Camp;
import com.bb2004.entity.feng.Camp;
import com.bb2004.entity.feng.CompanyOutlook;
import com.bb2004.entity.feng.News;

/**
 * @content 联系我们
 * @author 龙华雄
 * @param <T>
 * @time 2014-07-24
 */
@Namespace("/contactus")
public class ContactusAction<T> extends CrudActionSupport {
	private Long id;
	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}
	private String name;
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}
	
	//公司前台
	private CompanyOutlook companyOutlook;
	public CompanyOutlook getCompanyOutlook() {return companyOutlook;}
	public void setCompanyOutlook(CompanyOutlook companyOutlook) {this.companyOutlook = companyOutlook;}
	//各地经营地
	private Camp camp;
	public Camp getCamp() {return camp;}
	public void setCamp(Camp camp) {this.camp = camp;}
	
	private HibernateDao<T> hibernateDao;
	public HibernateDao<T> getHibernateDao() {return hibernateDao;}
	public void setHibernateDao(HibernateDao<T> hibernateDao) {this.hibernateDao = hibernateDao;}
	
	private AllDao<T> allDao;
	public AllDao<T> getAllDao() {return allDao;}
	public void setAllDao(AllDao<T> allDao) {this.allDao = allDao;}
	
	//==================================================理念=======================================================================================
		/**
		 * @content 进入编辑页面
		 * @return
		 */
	@Action(value="companyOutlookInput",results={@Result(name="input",location="../admin/feng/companyOutlook-input.jsp")})
	public String companyOutlookInput(){
		if(id!=null &&  id > 0){
			companyOutlook=hibernateDao.get(CompanyOutlook.class, id);
		}else{
			companyOutlook=new CompanyOutlook();
		}
		return INPUT;
	}
	
	/**
	 * @content 添加Object
	 * @throws Exception
	 */
	@Action(value="companyOutlookSave")
	public void companyOutlookSave() throws Exception{
		String rspStatus = "1";
		try {
			if(companyOutlook == null){
				return;
			}
			companyOutlook.setInputTime(new Date());
			hibernateDao.save(companyOutlook);
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
	@Action(value="companyOutlookDelete")
	public void companyOutlookDelete() throws Exception{
		String rspStatus = "1";
		try {
			companyOutlook = hibernateDao.get(CompanyOutlook.class, id);
			if(companyOutlook!=null) {
				hibernateDao.delete(companyOutlook);
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
	@Action(value="companyOutlookList")
	public void companyOutlookList() throws Exception{
		StringBuffer hql = new StringBuffer(" from CompanyOutlook t where 1= 1");
		if(name!=null && !name.trim().equals("")){
			hql.append(" and t.title like '%"+name+"%'");
		}
		List<CompanyOutlook> list=(List<CompanyOutlook>) allDao.listObjecr(hql.toString(), this.getPage(), this.getRows());
		List gridModel = new ArrayList();
		for(int i=0;i<list.size();i++){
			Map<String, Object> cellMap = new HashMap<String, Object>();
			companyOutlook=list.get(i);
			cellMap.put("id", companyOutlook.getId());
			cellMap.put("title", companyOutlook.getTitle());
			cellMap.put("content", companyOutlook.getContent());
			cellMap.put("inputTime", companyOutlook.getInputTime().toString().substring(0, 10));
			cellMap.put("operation", "<a href=\'javascript:void(0);\' onclick=\'doUpdate("+companyOutlook.getId()+")\' style=\'color:#090;margin-right:20px;\'>编辑</a><a href=\'javascript:void(0);\' onclick=\'doDelete("+companyOutlook.getId()+")\' style=\'color:#F00;\'>删除</a>");
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
	
	//==================================================各经营基地=======================================================================================
	/**
	 * @content 进入编辑页面
	 * @return
	 */
	@Action(value="campInput",results={@Result(name="input",location="../admin/feng/camp-input.jsp")})
	public String campInput(){
		if(id!=null &&  id > 0){
			camp=hibernateDao.get(Camp.class, id);
		}else{
			camp=new Camp();
		}
		return INPUT;
	}
	
	/**
	 * @content 添加Object
	 * @throws Exception
	 */
	@Action(value="campSave")
	public void campSave() throws Exception{
		String rspStatus = "1";
		try {
			if(camp == null){
				return;
			}
			camp.setInputTime(new Date());
			hibernateDao.save(camp);
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
	@Action(value="campDelete")
	public void campDelete() throws Exception{
		String rspStatus = "1";
		try {
			camp = hibernateDao.get(Camp.class, id);
			if(camp!=null) {
				hibernateDao.delete(camp);
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
	@Action(value="campList")
	public void campList() throws Exception{
		StringBuffer hql = new StringBuffer(" from Camp t where 1= 1");
		if(name!=null && !name.trim().equals("")){
			hql.append(" and t.title like '%"+name+"%'");
		}
		List<Camp> list=(List<Camp>) allDao.listObjecr(hql.toString(), this.getPage(), this.getRows());
		List gridModel = new ArrayList();
		for(int i=0;i<list.size();i++){
			Map<String, Object> cellMap = new HashMap<String, Object>();
			camp=list.get(i);
			cellMap.put("id", camp.getId());
			cellMap.put("title", camp.getTitle());
			cellMap.put("content", camp.getContent());
			cellMap.put("inputTime", camp.getInputTime().toString().substring(0, 10));
			cellMap.put("operation", "<a href=\'javascript:void(0);\' onclick=\'doUpdate("+camp.getId()+")\' style=\'color:#090;margin-right:20px;\'>编辑</a><a href=\'javascript:void(0);\' onclick=\'doDelete("+camp.getId()+")\' style=\'color:#F00;\'>删除</a>");
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
