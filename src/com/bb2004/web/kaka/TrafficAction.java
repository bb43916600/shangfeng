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
import com.bb2004.entity.kaka.Traffic;
import com.bb2004.service.kaka.TrafficManage;

/**
 * 交通信息
 * @author longhuaxiong
 *
 */

@Namespace("/traffic")
public class TrafficAction extends CrudActionSupport{
	private Long id;
	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}
	private List list;
	public List getList() {return list;}
	public void setList(List list) {this.list = list;}
	private String name;
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}
	
	private Traffic traffic;
	public Traffic getTraffic() {return traffic;}
	public void setTraffic(Traffic traffic) {this.traffic = traffic;}
	
	private TrafficManage trafficManage;
	@Autowired
	public void setTrafficManage(TrafficManage trafficManage) {this.trafficManage = trafficManage;}
	
	/**
	 * @content 进入编辑页面
	 * @return
	 */
	@Action(value="objectInput",results={@Result(name="input",location="../admin/kaka/traffic-input.jsp")})
	public String objectInput(){
		List<Traffic> list=trafficManage.listObjecr(traffic, this.getPage(), this.getRows());
		if(list.size() > 0){
			traffic=list.get(0);
		}else{
			traffic=new Traffic();
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
			if(traffic == null){
				return;
			}
			traffic.setInputTime(new Date());
			trafficManage.save(traffic);
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
			traffic = trafficManage.getId(id);
			if(traffic!=null) {
				trafficManage.delete(id);
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
		List<Traffic> list=trafficManage.listObjecr(traffic, this.getPage(), this.getRows());
		List gridModel = new ArrayList();
		for(int i=0;i<list.size();i++){
			Map<String, Object> cellMap = new HashMap<String, Object>();
			traffic=list.get(i);
			cellMap.put("id", traffic.getId());
			cellMap.put("ct_areaName", traffic.getCt_content());
			cellMap.put("en_areaName", traffic.getEn_content());
			cellMap.put("cn_areaName", traffic.getCn_content());
			cellMap.put("inputTime", traffic.getInputTime().toString().substring(0, 10));
			cellMap.put("operation", "<a href=\'javascript:void(0);\' onclick=\'doUpdate("+traffic.getId()+")\' style=\'color:#090;margin-right:20px;\'>编辑</a><a href=\'javascript:void(0);\' onclick=\'doDelete("+traffic.getId()+")\' style=\'color:#F00;\'>删除</a>");
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
	 * @content 交通信息
	 * @throws Exception
	 */
	@Action(value="wapList",results={@Result(name="detail",location="../admin/kaka/traffic-detail.jsp")})
	public String wapList() throws Exception{
		String currentLanguage = request.getParameter("currentLanguage");
		List<Traffic> list=trafficManage.listObjecr(traffic, this.getPage(), this.getRows());
		if(list.size()>0){
			traffic = list.get(0);
			if(currentLanguage.equals("ct"))
				name = traffic.getCt_content();
			else if(currentLanguage.equals("en"))
				name = traffic.getEn_content();
			else
				name =traffic.getCn_content();
		}
		return "detail";
//		List<Traffic> list=trafficManage.listObjecr(traffic, this.getPage(), this.getRows());
//		List gridModel = new ArrayList();
//		for(int i=0;i<list.size();i++){
//			Map<String, Object> cellMap = new HashMap<String, Object>();
//			traffic=list.get(i);
//			cellMap.put("id", traffic.getId());
//			cellMap.put("ct_areaName", traffic.getCt_content());
//			cellMap.put("en_areaName", traffic.getEn_content());
//			cellMap.put("cn_areaName", traffic.getCn_content());
//			cellMap.put("inputTime", traffic.getInputTime().toString().substring(0, 10));
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
