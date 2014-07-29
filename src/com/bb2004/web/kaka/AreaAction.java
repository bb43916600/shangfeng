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
import com.bb2004.entity.kaka.Area;
import com.bb2004.service.kaka.AreaManage;

/**
 * 区域表
 * @author longhuaxiong
 *
 */

@Namespace("/area")
public class AreaAction extends CrudActionSupport{
	private Long id;
	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}
	private List list;
	public List getList() {return list;}
	public void setList(List list) {this.list = list;}
	private String name;
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}
	
	private Area area;
	public Area getArea() {return area;}
	public void setArea(Area area) {this.area = area;}
	
	private AreaManage areaManage;
	@Autowired
	public void setAreaManage(AreaManage areaManage) {this.areaManage = areaManage;}
	
	/**
	 * @content 进入编辑页面
	 * @return
	 */
	@Action(value="objectInput",results={@Result(name="input",location="../admin/kaka/area-input.jsp")})
	public String objectInput(){
		if(id!=null){
			area=areaManage.getId(id);
		}else{
			area=new Area();
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
			if(area == null){
				return;
			}
			area.setInputTime(new Date());
			areaManage.save(area);
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
			area = areaManage.getId(id);
			if(area!=null) {
				areaManage.delete(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
			rspStatus="9";
		}
		doResult(rspStatus,"text/json;charset=UTF-8");
	}
	
	/**
	 * @content 查询
	 * @throws Exception
	 */
	@Action(value="objectList")
	public void objectList() throws Exception{
		List<Area> list=areaManage.listObjecr(area, this.getPage(), this.getRows());
		List gridModel = new ArrayList();
		for(int i=0;i<list.size();i++){
			Map<String, Object> cellMap = new HashMap<String, Object>();
			area=list.get(i);
			cellMap.put("id", area.getId());
			cellMap.put("ct_areaName", area.getCt_areaName());
			cellMap.put("en_areaName", area.getEn_areaName());
			cellMap.put("cn_areaName", area.getCn_areaName());
			cellMap.put("inputTime", area.getInputTime().toString().substring(0, 10));
			cellMap.put("operation", "<a href=\'javascript:void(0);\' onclick=\'doUpdate("+area.getId()+")\' style=\'color:#090;margin-right:20px;\'>编辑</a><a href=\'javascript:void(0);\' onclick=\'doDelete("+area.getId()+")\' style=\'color:#F00;\'>删除</a>");
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
	 * @content 所有区域
	 * @throws Exception
	 */
	@Action(value="wapList")
	public void wapList() throws Exception{
		List<Area> list=areaManage.listObjecr(area, this.getPage(), this.getRows());
		List gridModel = new ArrayList();
		for(int i=0;i<list.size();i++){
			Map<String, Object> cellMap = new HashMap<String, Object>();
			area=list.get(i);
			cellMap.put("id", area.getId());
			cellMap.put("ct_areaName", area.getCt_areaName());
			cellMap.put("en_areaName", area.getEn_areaName());
			cellMap.put("cn_areaName", area.getCn_areaName());
			cellMap.put("inputTime", area.getInputTime().toString().substring(0, 10));
			gridModel.add(cellMap);
		}
		doResult(JSONArray.fromObject(gridModel).toString(),"text/json;charset=UTF-8");
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
