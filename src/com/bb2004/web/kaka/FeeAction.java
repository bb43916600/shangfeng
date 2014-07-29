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
import com.bb2004.entity.kaka.ExtraCharge;
import com.bb2004.entity.kaka.Fee;
import com.bb2004.service.kaka.FeeManage;

/**
 * 费用说明表
 * @author longhuaxiong
 *
 */

@Namespace("/fee")
public class FeeAction extends CrudActionSupport{
	private Long id;
	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}
	private List list;
	public List getList() {return list;}
	public void setList(List list) {this.list = list;}
	private String name;
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}
	
	private Fee fee;
	public Fee getFee() {return fee;}
	public void setFee(Fee fee) {this.fee = fee;}
	
	private FeeManage feeManage;
	@Autowired
	public void setFeeManage(FeeManage feeManage) {this.feeManage = feeManage;}
	
	/**
	 * @content 进入编辑页面
	 * @return
	 */
	@Action(value="objectInput",results={@Result(name="input",location="../admin/kaka/fee-input.jsp")})
	public String objectInput(){
		List<Fee> list=feeManage.listObjecr(fee, this.getPage(), this.getRows());
		if(list.size() > 0){
			fee=list.get(0);
		}else{
			fee=new Fee();
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
			if(fee == null){
				return;
			}
			fee.setInputTime(new Date());
			feeManage.save(fee);
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
			fee = feeManage.getId(id);
			if(fee!=null) {
				feeManage.delete(id);
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
		List<Fee> list=feeManage.listObjecr(fee, this.getPage(), this.getRows());
		List gridModel = new ArrayList();
		for(int i=0;i<list.size();i++){
			Map<String, Object> cellMap = new HashMap<String, Object>();
			fee=list.get(i);
			cellMap.put("id", fee.getId());
			cellMap.put("ct_areaName", fee.getCt_content());
			cellMap.put("en_areaName", fee.getEn_content());
			cellMap.put("cn_areaName", fee.getCn_content());
			cellMap.put("inputTime", fee.getInputTime().toString().substring(0, 10));
			cellMap.put("operation", "<a href=\'javascript:void(0);\' onclick=\'doUpdate("+fee.getId()+")\' style=\'color:#090;margin-right:20px;\'>编辑</a><a href=\'javascript:void(0);\' onclick=\'doDelete("+fee.getId()+")\' style=\'color:#F00;\'>删除</a>");
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
	 * @content 费用说明
	 * @throws Exception
	 */
	@Action(value="wapList",results={@Result(name="detail",location="../admin/kaka/fee-detail.jsp")})
	public String wapList() throws Exception{
		String currentLanguage = request.getParameter("currentLanguage");
		List<Fee> list=feeManage.listObjecr(fee, this.getPage(), this.getRows());
		if(list.size()>0){
			fee = list.get(0);
			if(currentLanguage.equals("ct"))
				name = fee.getCt_content();
			else if(currentLanguage.equals("en"))
				name = fee.getEn_content();
			else
				name =fee.getCn_content();
		}
		return "detail";
//		List gridModel = new ArrayList();
//		for(int i=0;i<list.size();i++){
//			Map<String, Object> cellMap = new HashMap<String, Object>();
//			fee=list.get(i);
//			cellMap.put("id", fee.getId());
//			cellMap.put("ct_content", fee.getCt_content());
//			cellMap.put("en_content", fee.getEn_content());
//			cellMap.put("cn_content", fee.getCn_content());
//			cellMap.put("inputTime", fee.getInputTime().toString().substring(0, 10));
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
