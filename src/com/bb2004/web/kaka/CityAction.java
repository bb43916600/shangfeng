package com.bb2004.web.kaka;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.bb2004.common.CrudActionSupport;
import com.bb2004.common.PageAll;
import com.bb2004.entity.kaka.Area;
import com.bb2004.entity.kaka.City;
import com.bb2004.service.kaka.AreaManage;
import com.bb2004.service.kaka.CityManage;

/**
 * 城市表
 * @author longhuaxiong
 *
 */

@Namespace("/city")
public class CityAction extends CrudActionSupport{
	private Long id;
	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}
	private List list;
	public List getList() {return list;}
	public void setList(List list) {this.list = list;}
	private String name;
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}
	
	private City city;
	public City getCity() {return city;}
	public void setCity(City city) {this.city = city;}
	
	private CityManage cityManage;
	@Autowired
	public void setCityManage(CityManage cityManage) {this.cityManage = cityManage;}
	private AreaManage areaManage;
	@Autowired
	public void setAreaManage(AreaManage areaManage) {this.areaManage = areaManage;}
	
	
	/**
	 * @content 进入编辑页面
	 * @return
	 */
	@Action(value="objectInput",results={@Result(name="input",location="../admin/kaka/city-input.jsp")})
	public String objectInput(){
		list = areaManage.listObjecr(null, 0, 15);
		if(id!=null){
			city=cityManage.getId(id);
		}else{
			city=new City();
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
			if(city == null){
				return;
			}
			city.setInputTime(new Date());
			cityManage.save(city);
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
			city = cityManage.getId(id);
			if(city!=null) {
				cityManage.delete(id);
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
		list = areaManage.listObjecr(null, 0, 15);
		Map areaMap = new HashMap();
		for(int i=0;i<list.size();i++) {
			Area area = (Area)list.get(i);
			areaMap.put(area.getId(), area.getCn_areaName());
		}
		int countNumber = cityManage.addHqlCount(city);
		List<City> list=cityManage.listObjecr(city, this.getPage(), this.getRows());
		super.pageMethod(countNumber);
		List gridModel = new ArrayList();
		for(int i=0;i<list.size();i++){
			Map<String, Object> cellMap = new HashMap<String, Object>();
			city=list.get(i);
			cellMap.put("id", city.getId());
			cellMap.put("ct_cityName", city.getCt_cityName());
			cellMap.put("en_cityName", city.getEn_cityName());
			cellMap.put("cn_cityName", city.getCn_cityName());
			cellMap.put("areaId", areaMap.get(city.getAreaId()));
			cellMap.put("inputTime", city.getInputTime().toString().substring(0, 10));
			cellMap.put("operation", "<a href=\'javascript:void(0);\' onclick=\'doUpdate("+city.getId()+")\' style=\'color:#090;margin-right:20px;\'>编辑</a><a href=\'javascript:void(0);\' onclick=\'doDelete("+city.getId()+")\' style=\'color:#F00;\'>删除</a>");
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
	 * @content 所有城市
	 * @throws Exception
	 */
	@Action(value="wapList")
	public void wapList() throws Exception{
		list = areaManage.listObjecr(null, 0, 1000);
		Map areaMap = new HashMap();
		for(int i=0;i<list.size();i++) {
			Area area = (Area)list.get(i);
			areaMap.put(area.getId(), area);
		}
		List<City> list=cityManage.listObjecr(city, 0, 1000);
		List gridModel = new ArrayList();
		for(int i=0;i<list.size();i++){
			Map<String, Object> cellMap = new HashMap<String, Object>();
			city=list.get(i);
			cellMap.put("id", city.getId());
			cellMap.put("ct_cityName", city.getCt_cityName());
			cellMap.put("en_cityName", city.getEn_cityName());
			cellMap.put("cn_cityName", city.getCn_cityName());
			cellMap.put("areaId", city.getAreaId());
			cellMap.put("areaName", areaMap.get(city.getAreaId()));
			cellMap.put("inputTime", city.getInputTime().toString().substring(0, 10));
			gridModel.add(cellMap);
		}
		doResult(JSONArray.fromObject(gridModel).toString(),"text/json;charset=UTF-8");
	}
	
	/**
	 * @content 所有城市
	 * @throws Exception
	 */
	@Action(value="wapCityName")
	public void wapCityName() throws Exception{
		list = areaManage.listObjecr(null, 0, 1000);
		Map areaMap = new HashMap();
		for(int i=0;i<list.size();i++) {
			Area area = (Area)list.get(i);
			areaMap.put(area.getId(), area);
		}
		HttpServletRequest request = ServletActionContext.getRequest();
		String ct_cityName = request.getParameter("ct_cityName");
		String en_cityName = request.getParameter("en_cityName");
		String cn_cityName = request.getParameter("cn_cityName");
		city = new City();
		
		if(ct_cityName!=null && !ct_cityName.equals(""))
			city.setCt_cityName(ct_cityName);
		else if(en_cityName!=null && !en_cityName.equals(""))
			city.setEn_cityName(en_cityName);
		else if(cn_cityName!=null && !cn_cityName.equals(""))
			city.setCn_cityName(cn_cityName);
		
		List<City> list=cityManage.listObjecr(city, 0, 1000);
		List gridModel = new ArrayList();
		for(int i=0;i<list.size();i++){
			Map<String, Object> cellMap = new HashMap<String, Object>();
			city=list.get(i);
			cellMap.put("id", city.getId());
			cellMap.put("ct_cityName", city.getCt_cityName());
			cellMap.put("en_cityName", city.getEn_cityName());
			cellMap.put("cn_cityName", city.getCn_cityName());
			cellMap.put("areaId", city.getAreaId());
			cellMap.put("areaName", areaMap.get(city.getAreaId()));
			cellMap.put("inputTime", city.getInputTime().toString().substring(0, 10));
			gridModel.add(cellMap);
		}
		doResult(JSONArray.fromObject(gridModel).toString(),"text/json;charset=UTF-8");
	}
	
	/**
	 * @content 所有城市
	 * @throws Exception
	 */
	@Action(value="wapAreaCity")
	public void wapAreaCity() throws Exception{
		Area findarea = new Area();
		HttpServletRequest request = ServletActionContext.getRequest();
		String ct_areaName = request.getParameter("ct_areaName");
		String en_areaName = request.getParameter("en_areaName");
		String cn_areaName = request.getParameter("cn_areaName");
		
		if(ct_areaName!=null && !ct_areaName.equals(""))
			findarea.setCt_areaName(ct_areaName);
		else if(en_areaName!=null && !en_areaName.equals(""))
			findarea.setEn_areaName(en_areaName);
		else if(cn_areaName!=null && !cn_areaName.equals(""))
			findarea.setCn_areaName(cn_areaName);
		
		
		List<Area> areaList=areaManage.listObjecr(findarea, this.getPage(), this.getRows());
		if(areaList.size() <= 0)
			return;
		Area nowArea = areaList.get(0);
		
		list = areaManage.listObjecr(null, 0, 1000);
		Map areaMap = new HashMap();
		for(int i=0;i<list.size();i++) {
			Area area = (Area)list.get(i);
			areaMap.put(area.getId(), area);
		}
		city = new City();
		city.setAreaId(nowArea.getId());
		List<City> list=cityManage.listObjecr(city, 0, 1000);
		List gridModel = new ArrayList();
		for(int i=0;i<list.size();i++){
			Map<String, Object> cellMap = new HashMap<String, Object>();
			city=list.get(i);
			cellMap.put("id", city.getId());
			cellMap.put("ct_cityName", city.getCt_cityName());
			cellMap.put("en_cityName", city.getEn_cityName());
			cellMap.put("cn_cityName", city.getCn_cityName());
			cellMap.put("areaId", city.getAreaId());
			cellMap.put("areaName", areaMap.get(city.getAreaId()));
			cellMap.put("inputTime", city.getInputTime().toString().substring(0, 10));
			gridModel.add(cellMap);
		}
		doResult(JSONArray.fromObject(gridModel).toString(),"text/json;charset=UTF-8");
	}
	
	/**
	 * @content 获取隧道和包钟
	 * @throws Exception
	 */
	@Action(value="wapTimeAndTunnel")
	public void wapTimeAndTunnel() throws Exception{
		
		Map<String, Object> cellMap = new HashMap<String, Object>();
		Map timeMap = new HashMap();
		//简体
		List timeList_cn = new ArrayList();
		timeList_cn.add("3");
		timeList_cn.add("3.5");
		timeList_cn.add("4");
		timeList_cn.add("4.5");
		timeList_cn.add("5");
		timeList_cn.add("5.5");
		timeList_cn.add("6");
		timeList_cn.add("6.5");
		timeList_cn.add("7");
		timeList_cn.add("7.5");
		timeList_cn.add("8");
		
		//繁体
		List timeList_ct = new ArrayList();
		timeList_ct.add("3");
		timeList_ct.add("3.5");
		timeList_ct.add("4");
		timeList_ct.add("4.5");
		timeList_ct.add("5");
		timeList_ct.add("5.5");
		timeList_ct.add("6");
		timeList_ct.add("6.5");
		timeList_ct.add("7");
		timeList_ct.add("7.5");
		timeList_ct.add("8");
		
		//英文
		List timeList_en = new ArrayList();
		timeList_en.add("3");
		timeList_en.add("3.5");
		timeList_en.add("4");
		timeList_en.add("4.5");
		timeList_en.add("5");
		timeList_en.add("5.5");
		timeList_en.add("6");
		timeList_en.add("6.5");
		timeList_en.add("7");
		timeList_en.add("7.5");
		timeList_en.add("8");
		
		timeMap.put("ct_time", timeList_ct);
		timeMap.put("en_time", timeList_en);
		timeMap.put("cn_time", timeList_cn);
		
		
		Map tunnelMap = new HashMap();
		//隧道(简体)
		List tunnelList_cn = new ArrayList();
		tunnelList_cn.add("无所谓");
		tunnelList_cn.add("红隧");
		tunnelList_cn.add("西隧");
		tunnelList_cn.add("东隧");
		tunnelList_cn.add("狮子山隧道");
		tunnelList_cn.add("大老山隧道");
		tunnelList_cn.add("尖山隧道");
		tunnelList_cn.add("大榄隧道");
		tunnelList_cn.add("香港仔隧道");
		tunnelList_cn.add("将军澳隧道");
		
		//隧道（繁体）
		List tunnelList_ct = new ArrayList();
		tunnelList_ct.add("無所謂");
		tunnelList_ct.add("紅隧");
		tunnelList_ct.add("西隧");
		tunnelList_ct.add("東隧");
		tunnelList_ct.add("獅子山隧道");
		tunnelList_ct.add("大老山隧道");
		tunnelList_ct.add("尖山隧道");
		tunnelList_ct.add("大榄隧道");
		tunnelList_ct.add("香港仔隧道");
		tunnelList_ct.add("將軍澳隧道");
		
		//隧道（英文）
		List tunnelList_en = new ArrayList();
		tunnelList_en.add("Don't Care");
		tunnelList_en.add("Cross Harbour");
		tunnelList_en.add("Western Harbour");
		tunnelList_en.add("Eastern Harbour");
		tunnelList_en.add("Lion Rock");
		tunnelList_en.add("Tates Cairn");
		tunnelList_en.add("Eagles Nest");
		tunnelList_en.add("Tai Lam");
		tunnelList_en.add("Aberdeen");
		tunnelList_en.add("Tseung Kwan O");
		
		List tipList = new ArrayList();
		tipList.add("10");
		tipList.add("20");
		tipList.add("30");
		tipList.add("40");
		tipList.add("50");
		tipList.add("60");
		tipList.add("70");
		tipList.add("80");
		tipList.add("90");
		tipList.add("100");
		
		tunnelMap.put("ct_tunnel", tunnelList_ct);//繁体
		tunnelMap.put("en_tunnel", tunnelList_en);//英文
		tunnelMap.put("cn_tunnel", tunnelList_cn);//简体
		
		cellMap.put("time", timeMap);
		cellMap.put("tunnel", tunnelMap);
		cellMap.put("tip", tipList);
		
		doResult(JSONObject.fromObject(cellMap).toString(),"text/json;charset=UTF-8");
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
