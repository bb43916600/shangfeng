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
import com.bb2004.entity.kaka.HolidayPrice;
import com.bb2004.service.kaka.HolidayPriceManage;

/**
 * 节假日费用设置
 * @author longhuaxiong
 *
 */

@Namespace("/holidayPrice")
public class HolidayPriceAction extends CrudActionSupport{
	private Long id;
	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}
	private List list;
	public List getList() {return list;}
	public void setList(List list) {this.list = list;}
	private String name;
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}
	
	private HolidayPrice holidayPrice;
	public HolidayPrice getHolidayPrice() {return holidayPrice;}
	public void setHolidayPrice(HolidayPrice holidayPrice) {this.holidayPrice = holidayPrice;}
	
	private HolidayPriceManage holidayPriceManage;
	@Autowired
	public void setHolidayPriceManage(HolidayPriceManage holidayPriceManage) {this.holidayPriceManage = holidayPriceManage;}
	
	/**
	 * @content 进入编辑页面
	 * @return
	 */
	@Action(value="objectInput",results={@Result(name="input",location="../admin/kaka/holidayPrice-input.jsp")})
	public String objectInput(){
//		list = new ArrayList();
//		for(int i=0;i<24;i++){
//			list.add(i);
//		}
		List<HolidayPrice> list=holidayPriceManage.listObjecr(holidayPrice, this.getPage(), this.getRows());
		if(list.size() > 0){
			holidayPrice=list.get(0);
		}else{
			holidayPrice=new HolidayPrice();
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
			if(holidayPrice == null){
				return;
			}
			holidayPriceManage.save(holidayPrice);
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
			holidayPrice = holidayPriceManage.getId(id);
			if(holidayPrice!=null) {
				holidayPriceManage.delete(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
			rspStatus="9";
		}
		doResult(rspStatus,"text/json;charset=UTF-8");
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
