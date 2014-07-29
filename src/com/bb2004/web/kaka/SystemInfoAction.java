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
import com.bb2004.entity.authority.User;
import com.bb2004.entity.kaka.Fee;
import com.bb2004.entity.kaka.SystemInfo;
import com.bb2004.entity.kaka.Traffic;
import com.bb2004.service.kaka.SystemInfoManage;

/**
 * 系统公告
 * @author longhuaxiong
 *
 */

@Namespace("/systemInfo")
public class SystemInfoAction extends CrudActionSupport{
	private Long id;
	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}
	private List list;
	public List getList() {return list;}
	public void setList(List list) {this.list = list;}
	private String name;
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}
	
	private SystemInfo systemInfo;
	public SystemInfo getSystemInfo() {return systemInfo;}
	public void setSystemInfo(SystemInfo systemInfo) {this.systemInfo = systemInfo;}
	
	private SystemInfoManage systemInfoManage;
	@Autowired
	public void setSystemInfoManage(SystemInfoManage systemInfoManage) {this.systemInfoManage = systemInfoManage;}
	
	/**
	 * @content 进入编辑页面
	 * @return
	 */
	@Action(value="objectInput",results={@Result(name="input",location="../admin/kaka/systemInfo-input.jsp")})
	public String objectInput(){
		if(id!=null){
			systemInfo=systemInfoManage.getId(id);
		}else{
			systemInfo=new SystemInfo();
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
			if(systemInfo == null){
				return;
			}
			systemInfo.setInputTime(new Date());
			systemInfoManage.save(systemInfo);
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
			systemInfo = systemInfoManage.getId(id);
			if(systemInfo!=null) {
				systemInfoManage.delete(id);
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
		int countNumber = systemInfoManage.addHqlCount(systemInfo);
		List<SystemInfo> list=systemInfoManage.listObjecr(systemInfo, this.getPage(), this.getRows());
		super.pageMethod(countNumber);
		List gridModel = new ArrayList();
		for(int i=0;i<list.size();i++){
			Map<String, Object> cellMap = new HashMap<String, Object>();
			systemInfo=list.get(i);
			cellMap.put("id", systemInfo.getId());
			cellMap.put("ct_title", systemInfo.getCt_title());//标题
			cellMap.put("en_title", systemInfo.getEn_title());
			cellMap.put("cn_title", systemInfo.getCn_title());
			cellMap.put("ct_content", systemInfo.getCt_content());//内容
			cellMap.put("en_content", systemInfo.getEn_content());
			cellMap.put("cn_content", systemInfo.getCn_content());
			cellMap.put("inputTime", systemInfo.getInputTime().toString().substring(0, 10));
			cellMap.put("operation", "<a href=\'javascript:void(0);\' onclick=\'doUpdate("+systemInfo.getId()+")\' style=\'color:#090;margin-right:20px;\'>编辑</a><a href=\'javascript:void(0);\' onclick=\'doDelete("+systemInfo.getId()+")\' style=\'color:#F00;\'>删除</a>");
			gridModel.add(cellMap);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("gridModel", gridModel);
		map.put("rows", this.getRows());
		map.put("page", this.getPage());
		map.put("total", this.getTotal());
		map.put("record", this.getRecord());
		doResult(JSONObject.fromObject(map).toString(),"text/json;charset=UTF-8");
	}
	
//======================================APP============================================================

	/**
	 * @content 系统公告
	 * @throws Exception
	 */
	@Action(value="wapList",results={@Result(name="detail",location="../admin/kaka/systemInfo-detail.jsp")})
	public String wapList() throws Exception{
		String currentLanguage = request.getParameter("currentLanguage");
		List<SystemInfo> list=systemInfoManage.listObjecr(systemInfo, this.getPage(), this.getRows());
		if(list.size()>0){
			systemInfo = list.get(0);
			if(currentLanguage.equals("ct"))
				name = systemInfo.getCt_content();
			else if(currentLanguage.equals("en"))
				name = systemInfo.getEn_content();
			else
				name =systemInfo.getCn_content();
		}
		return "detail";
//		List<SystemInfo> list=systemInfoManage.listObjecr(systemInfo, this.getPage(), this.getRows());
//		List gridModel = new ArrayList();
//		for(int i=0;i<list.size();i++){
//			Map<String, Object> cellMap = new HashMap<String, Object>();
//			systemInfo=list.get(i);
//			cellMap.put("id", systemInfo.getId());
//			cellMap.put("ct_title", systemInfo.getCt_title());//标题
//			cellMap.put("en_title", systemInfo.getEn_title());
//			cellMap.put("cn_title", systemInfo.getCn_title());
//			cellMap.put("ct_content", systemInfo.getCt_content());//内容
//			cellMap.put("en_content", systemInfo.getEn_content());
//			cellMap.put("cn_content", systemInfo.getCn_content());
//			cellMap.put("inputTime", systemInfo.getInputTime().toString().substring(0, 10));
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
