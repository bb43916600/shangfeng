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
import com.bb2004.service.kaka.MoothFeeManage;

/**
 * 包月和商务合作说明表
 * @author longhuaxiong
 *
 */

@Namespace("/moothFee")
public class MoothFeeAction extends CrudActionSupport{
	private Long id;
	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}
	private List list;
	public List getList() {return list;}
	public void setList(List list) {this.list = list;}
	private String name;
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}
	
	private MoothFee moothFee;
	public MoothFee getMoothFee() {return moothFee;}
	public void setMoothFee(MoothFee moothFee) {this.moothFee = moothFee;}
	
	private MoothFeeManage moothFeeManage;
	@Autowired
	public void setMoothFeeManage(MoothFeeManage moothFeeManage) {this.moothFeeManage = moothFeeManage;}
	
	/**
	 * @content 进入编辑页面
	 * @return
	 */
	@Action(value="objectInput",results={@Result(name="input",location="../admin/kaka/moothFee-input.jsp")})
	public String objectInput(){
		List<MoothFee> list=moothFeeManage.listObjecr(moothFee, this.getPage(), this.getRows());
		if(list.size() > 0){
			moothFee=list.get(0);
		}else{
			moothFee=new MoothFee();
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
			if(moothFee == null){
				return;
			}
			moothFee.setInputTime(new Date());
			moothFeeManage.save(moothFee);
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
			moothFee = moothFeeManage.getId(id);
			if(moothFee!=null) {
				moothFeeManage.delete(id);
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
		List<MoothFee> list=moothFeeManage.listObjecr(moothFee, this.getPage(), this.getRows());
		List gridModel = new ArrayList();
		for(int i=0;i<list.size();i++){
			Map<String, Object> cellMap = new HashMap<String, Object>();
			moothFee=list.get(i);
			cellMap.put("id", moothFee.getId());
			cellMap.put("ct_areaName", moothFee.getCt_content());
			cellMap.put("en_areaName", moothFee.getEn_content());
			cellMap.put("cn_areaName", moothFee.getCn_content());
			cellMap.put("inputTime", moothFee.getInputTime().toString().substring(0, 10));
			cellMap.put("operation", "<a href=\'javascript:void(0);\' onclick=\'doUpdate("+moothFee.getId()+")\' style=\'color:#090;margin-right:20px;\'>编辑</a><a href=\'javascript:void(0);\' onclick=\'doDelete("+moothFee.getId()+")\' style=\'color:#F00;\'>删除</a>");
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
	 * @content 包月和商务合作
	 * @throws Exception
	 */
	@Action(value="wapList",results={@Result(name="detail",location="../admin/kaka/moothFee-detail.jsp")})
	public String wapList() throws Exception{
		String currentLanguage = request.getParameter("currentLanguage");
		List<MoothFee> list=moothFeeManage.listObjecr(moothFee, this.getPage(), this.getRows());
		if(list.size()>0){
			moothFee = list.get(0);
			if(currentLanguage.equals("ct"))
				name = moothFee.getCt_content();
			else if(currentLanguage.equals("en"))
				name = moothFee.getEn_content();
			else
				name =moothFee.getCn_content();
		}
		return "detail";
//		List<MoothFee> list=moothFeeManage.listObjecr(moothFee, this.getPage(), this.getRows());
//		List gridModel = new ArrayList();
//		for(int i=0;i<list.size();i++){
//			Map<String, Object> cellMap = new HashMap<String, Object>();
//			moothFee=list.get(i);
//			cellMap.put("id", moothFee.getId());
//			cellMap.put("ct_content", moothFee.getCt_content());
//			cellMap.put("en_content", moothFee.getEn_content());
//			cellMap.put("cn_content", moothFee.getCn_content());
//			cellMap.put("inputTime", moothFee.getInputTime().toString().substring(0, 10));
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
