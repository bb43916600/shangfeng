package com.bb2004.web.kaka;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import com.bb2004.entity.kaka.HolidayTime;
import com.bb2004.service.kaka.HolidayTimeManage;

/**
 * 节假日设置
 * @author longhuaxiong
 *
 */

@Namespace("/holidayTime")
public class HolidayTimeAction extends CrudActionSupport{
	private Long id;
	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}
	private List list;
	public List getList() {return list;}
	public void setList(List list) {this.list = list;}
	private String name;
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}
	
	private HolidayTime holidayTime;
	public HolidayTime getHolidayTime() {return holidayTime;}
	public void setHolidayTime(HolidayTime holidayTime) {this.holidayTime = holidayTime;}
	
	private HolidayTimeManage holidayTimeManage;
	@Autowired
	public void setHolidayTimeManage(HolidayTimeManage holidayTimeManage) {this.holidayTimeManage = holidayTimeManage;}
	
	/**
	 * @content 进入编辑页面
	 * @return
	 */
	@Action(value="objectInput",results={@Result(name="input",location="../admin/kaka/holidayTime-input.jsp")})
	public String objectInput(){
//		List<HolidayTime> list=holidayTimeManage.listObjecr(holidayTime, this.getPage(), this.getRows());
//		if(list.size() > 0){
//			holidayTime=list.get(0);
//		}else{
		holidayTime=new HolidayTime();
//		}
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
			if(holidayTime == null){
				return;
			}
			//计算差距天数
			long days = 1;
			if(holidayTime.getSettingTime()!=null && holidayTime.getEndTime()!=null){
				long time = holidayTime.getEndTime().getTime() - holidayTime.getSettingTime().getTime();
				days = time / 1000 / 60 / 60 / 24 % 365;
			}
			
			//遍历添加日期
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(holidayTime.getSettingTime());
			for (int i=0;i<=days;i++){
				HolidayTime holidayTime = new HolidayTime();
				holidayTime.setInputTime(new Date());
				holidayTime.setSettingTime(calendar.getTime());
				holidayTime.setRemark(this.holidayTime.getRemark());
				holidayTimeManage.save(holidayTime);
				//日期+1
				calendar.add(Calendar.DAY_OF_MONTH, 1);
			}
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
			holidayTime = holidayTimeManage.getId(id);
			if(holidayTime!=null) {
				holidayTimeManage.delete(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
			rspStatus="9";
		}
		doResult(rspStatus,"text/json;charset=UTF-8");
	}
	
	/**
	 * @content 查询所有对象
	 * @throws Exception
	 */
	@Action(value="objectList")
	public void objectList() throws Exception{
		int countNumber = holidayTimeManage.addHqlCount(holidayTime);
		List<HolidayTime> list=holidayTimeManage.listObjecr(holidayTime, this.getPage(), this.getRows());
		super.pageMethod(countNumber);
		List gridModel = new ArrayList();
		for(int i=0;i<list.size();i++){
			Map<String, Object> cellMap = new HashMap<String, Object>();
			holidayTime=list.get(i);
			cellMap.put("id", holidayTime.getId());
			cellMap.put("settingTime", holidayTime.getSettingTime().toString().substring(0, 10));
			cellMap.put("remark", holidayTime.getRemark());
			cellMap.put("inputTime", holidayTime.getInputTime().toString().substring(0, 10));
			cellMap.put("operation", "<a href=\'javascript:void(0);\' onclick=\'doDelete("+holidayTime.getId()+")\' style=\'color:#F00;\'>删除</a>");
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
