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
import com.bb2004.entity.kaka.OtherPrice;
import com.bb2004.service.kaka.OtherPriceManage;

/**
 * 额外需求表
 * @author longhuaxiong
 *
 */

@Namespace("/otherPrice")
public class OtherPriceAction extends CrudActionSupport{
	private Long id;
	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}
	private List list;
	public List getList() {return list;}
	public void setList(List list) {this.list = list;}
	private String name;
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}
	
	private OtherPrice otherPrice;
	public OtherPrice getOtherPrice() {return otherPrice;}
	public void setOtherPrice(OtherPrice otherPrice) {this.otherPrice = otherPrice;}
	
	private OtherPriceManage otherPriceManage;
	@Autowired
	public void setOtherPriceManage(OtherPriceManage otherPriceManage) {this.otherPriceManage = otherPriceManage;}
	
	/**
	 * @content 进入编辑页面
	 * @return
	 */
	@Action(value="objectInput",results={@Result(name="input",location="../admin/kaka/otherPrice-input.jsp")})
	public String objectInput(){
		list = otherPriceManage.listObjecr(null, 0, 15);
		if(id!=null){
			otherPrice=otherPriceManage.getId(id);
		}else{
			otherPrice=new OtherPrice();
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
			if(otherPrice == null){
				return;
			}
			if(otherPrice.getParentId()==null || otherPrice.getParentId() < 0)
				otherPrice.setParentId(0L);
			otherPrice.setInputTime(new Date());
			otherPriceManage.save(otherPrice);
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
			otherPrice = otherPriceManage.getId(id);
			if(otherPrice!=null) {
				otherPriceManage.delete(id);
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
		//查询所有的父类，放进MAP里，通过KEY来对比 避免后期频繁请求数据库
		OtherPrice allotherPrice = new OtherPrice();
		allotherPrice.setParentId(0L);
		List<OtherPrice> allotherPriceList = otherPriceManage.listObjecr(allotherPrice, 0, 15);
		Map allMap = new HashMap();
		for(int i=0;i<allotherPriceList.size();i++){
			allotherPrice = allotherPriceList.get(i);
			allMap.put(allotherPrice.getId(), allotherPrice.getCn_otherName());
		}
		
		List<OtherPrice> list=otherPriceManage.listObjecr(otherPrice, this.getPage(), this.getRows());
		List gridModel = new ArrayList();
		for(int i=0;i<list.size();i++){
			Map<String, Object> cellMap = new HashMap<String, Object>();
			otherPrice=list.get(i);
			cellMap.put("id", otherPrice.getId());
			cellMap.put("parentId", otherPrice.getParentId());
			cellMap.put("parentName", allMap.get(otherPrice.getParentId()));
			cellMap.put("ct_otherName", otherPrice.getCt_otherName());
			cellMap.put("en_otherName", otherPrice.getEn_otherName());
			cellMap.put("cn_otherName", otherPrice.getCn_otherName());
			
			cellMap.put("ct_remark", otherPrice.getCt_remark());
			cellMap.put("en_remark", otherPrice.getEn_remark());
			cellMap.put("cn_remark", otherPrice.getCn_remark());
			cellMap.put("price", otherPrice.getPrice());
			cellMap.put("inputTime", otherPrice.getInputTime().toString().substring(0, 10));
			cellMap.put("operation", "<a href=\'javascript:void(0);\' onclick=\'doUpdate("+otherPrice.getId()+")\' style=\'color:#090;margin-right:20px;\'>编辑</a><a href=\'javascript:void(0);\' onclick=\'doDelete("+otherPrice.getId()+")\' style=\'color:#F00;\'>删除</a>");
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
	 * @content 所有额外需求
	 * @throws Exception
	 */
	@Action(value="wapList")
	public void wapList() throws Exception{
		//查询所有的父类，放进MAP里，通过KEY来对比 避免后期频繁请求数据库
		OtherPrice allotherPrice = new OtherPrice();
		allotherPrice.setParentId(0L);
		List<OtherPrice> allotherPriceList = otherPriceManage.listObjecr(allotherPrice, 0, 15);
		Map allMap = new HashMap();
		for(int i=0;i<allotherPriceList.size();i++){
			allotherPrice = allotherPriceList.get(i);
			allMap.put(allotherPrice.getId(), allotherPrice.getCn_otherName());
		}
		
		otherPrice = new OtherPrice();
		otherPrice.setParentId(0L);
		List<OtherPrice> list=otherPriceManage.listObjecr(otherPrice, this.getPage(), this.getRows());
		List gridModel = new ArrayList();
		
		for(int i=0;i<list.size();i++){
			Map<String, Object> cellMap = new HashMap<String, Object>();
			otherPrice=list.get(i);
			cellMap.put("id", otherPrice.getId());
			cellMap.put("parentId", otherPrice.getParentId());
			cellMap.put("parentName", allMap.get(otherPrice.getParentId()));
			cellMap.put("ct_otherName", otherPrice.getCt_otherName());
			cellMap.put("en_otherName", otherPrice.getEn_otherName());
			cellMap.put("cn_otherName", otherPrice.getCn_otherName());
			
			cellMap.put("ct_remark", otherPrice.getCt_remark());
			cellMap.put("en_remark", otherPrice.getEn_remark());
			cellMap.put("cn_remark", otherPrice.getCn_remark());
			cellMap.put("price", otherPrice.getPrice());
			cellMap.put("icon", otherPrice.getIcon());//
			System.out.println("===测试每一个ICON是否正确=="+otherPrice.getIcon());
			cellMap.put("inputTime", otherPrice.getInputTime().toString().substring(0, 10));
			
			OtherPrice tempother = new OtherPrice();
			tempother.setParentId(otherPrice.getId());
			List tempSubList = otherPriceManage.listObjecr(tempother, this.getPage(), this.getRows());
			System.out.println(otherPrice.getId()+"========"+tempSubList.size());
			List subList = new ArrayList();
			for(int j=0;j<tempSubList.size();j++){
				Map<String, Object> cellMap2 = new HashMap<String, Object>();
				OtherPrice tempOtherPrice = (OtherPrice)tempSubList.get(j);
				cellMap2.put("id", tempOtherPrice.getId());
				cellMap2.put("parentId", tempOtherPrice.getParentId());
				cellMap2.put("parentName", allMap.get(tempOtherPrice.getParentId()));
				cellMap2.put("ct_otherName", tempOtherPrice.getCt_otherName());
				cellMap2.put("en_otherName", tempOtherPrice.getEn_otherName());
				cellMap2.put("cn_otherName", tempOtherPrice.getCn_otherName());
				
				cellMap2.put("ct_remark", tempOtherPrice.getCt_remark());
				cellMap2.put("en_remark", tempOtherPrice.getEn_remark());
				cellMap2.put("cn_remark", tempOtherPrice.getCn_remark());
				cellMap2.put("icon", tempOtherPrice.getIcon());
				cellMap2.put("price", tempOtherPrice.getPrice());
				cellMap2.put("inputTime", tempOtherPrice.getInputTime().toString().substring(0, 10));
				subList.add(cellMap2);
			}
			cellMap.put(""+otherPrice.getId()+"", subList);
			cellMap.put("hasChild", subList.size()>0?true:false);
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
