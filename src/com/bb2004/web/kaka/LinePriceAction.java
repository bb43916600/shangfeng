package com.bb2004.web.kaka;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.bb2004.common.CrudActionSupport;
import com.bb2004.entity.kaka.City;
import com.bb2004.entity.kaka.Fee;
import com.bb2004.entity.kaka.LinePrice;
import com.bb2004.service.kaka.CityManage;
import com.bb2004.service.kaka.LinePriceManage;

@Namespace("/linePrice")
public class LinePriceAction extends CrudActionSupport{
	private Long id;
	public static int countNumber = 0;
	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}
	private List list;
	public List getList() {return list;}
	public void setList(List list) {this.list = list;}
	private String startName;
	private String endName;
	public String getStartName() {return startName;}
	public void setStartName(String startName) {this.startName = startName;}
	public String getEndName() {return endName;}
	public void setEndName(String endName) {this.endName = endName;}
	private LinePrice linePrice;
	public LinePrice getLinePrice() {return linePrice;}
	public void setLinePrice(LinePrice linePrice) {this.linePrice = linePrice;}
	
	private LinePriceManage linePriceManage;
	@Autowired
	public void setLinePriceManage(LinePriceManage linePriceManage) {this.linePriceManage = linePriceManage;}
	private CityManage cityManage;
	@Autowired
	public void setCityManage(CityManage cityManage) {this.cityManage = cityManage;}
	
	
	/**
	 * @content 进入编辑页面
	 * @return
	 */
	@Action(value="objectInput",results={@Result(name="input",location="../admin/kaka/linePrice-input.jsp")})
	public String objectInput(){
		if(id!=null){
			linePrice=linePriceManage.getId(id);
		}else{
			linePrice=new LinePrice();
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
			if(linePrice == null){
				return;
			}
			linePrice.setInputTime(new Date());
			linePriceManage.save(linePrice);
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
			linePrice = linePriceManage.getId(id);
			if(linePrice!=null) {
				linePriceManage.delete(id);
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
		int countNumber = linePriceManage.addHqlCount(linePrice);
		List<LinePrice> list=linePriceManage.listObjecr(linePrice, this.getPage(), this.getRows());
		super.pageMethod(countNumber);
		List gridModel = new ArrayList();
		for(int i=0;i<list.size();i++){
			Map<String, Object> cellMap = new HashMap<String, Object>();
			linePrice=list.get(i);
			cellMap.put("id", linePrice.getId());
			cellMap.put("startCityId", cityManage.getId(linePrice.getStartCityId()).getCn_cityName());
			cellMap.put("endCityId", cityManage.getId(linePrice.getEndCityId()).getCn_cityName());
			cellMap.put("price", linePrice.getPrice());
			cellMap.put("inputTime", linePrice.getInputTime().toString().substring(0, 10));
			cellMap.put("operation", "<a href=\'javascript:void(0);\' onclick=\'doUpdate("+linePrice.getId()+")\' style=\'color:#090;margin-right:20px;\'>编辑</a><a href=\'javascript:void(0);\' onclick=\'doDelete("+linePrice.getId()+")\' style=\'color:#F00;\'>删除</a>");
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
	
//	/**
//	 * @content 所有线路
//	 * @throws Exception
//	 */
//	@Action(value="wapList")
//	public void wapList() throws Exception{
//		List<LinePrice> list=linePriceManage.listObjecr(linePrice, this.getPage(), this.getRows());
//		List gridModel = new ArrayList();
//		for(int i=0;i<list.size();i++){
//			Map<String, Object> cellMap = new HashMap<String, Object>();
//			linePrice=list.get(i);
//			cellMap.put("id", linePrice.getId());
//			cellMap.put("startCityId", cityManage.getId(linePrice.getStartCityId()).getCn_cityName());
//			cellMap.put("endCityId", cityManage.getId(linePrice.getEndCityId()).getCn_cityName());
//			cellMap.put("price", linePrice.getPrice());
//			cellMap.put("inputTime", linePrice.getInputTime().toString().substring(0, 10));
//			gridModel.add(cellMap);
//		}
//		doResult(JSONArray.fromObject(gridModel).toString(),"text/json;charset=UTF-8");
//	}
	
	/**
	 * @content 根据开始名称和结束名称获取线路
	 * @throws Exception
	 */
	@Action(value="wapGetLine")
	public void wapGetLine() throws Exception{
		List<City> list=cityManage.getStartEndLine(startName, endName);
		if(list.size()!=1)
			return;
		City startCity = list.get(0);
		City endCity = list.get(0);
		linePrice = linePriceManage.findLinePriceByStartCityIdAndEndCityId(startCity.getId(), endCity.getId());
		Map<String, Object> cellMap = new HashMap<String, Object>();
		if(linePrice!=null){
			cellMap.put("id", linePrice.getId());
			cellMap.put("startCityId", linePrice.getStartCityId());
			cellMap.put("startName_cn", startCity.getCn_cityName());
			cellMap.put("startName_ct", startCity.getCt_cityName());
			cellMap.put("startName_en", startCity.getEn_cityName());
			
			cellMap.put("endCityId", linePrice.getEndCityId());
			cellMap.put("endName_cn", endCity.getCn_cityName());
			cellMap.put("endName_ct", endCity.getCt_cityName());
			cellMap.put("endName_en", endCity.getEn_cityName());
		}
		doResult(JSONObject.fromObject(cellMap).toString(),"text/json;charset=UTF-8");
	}
	
//	@Action(value="wapGetLine")
//	public void wapGetLine() throws Exception{
//		startName = "大帽山";
//		endName = "大帽山";
//		List<City> list=cityManage.getStartEndLine(startName, endName);
//		if(list.size()!=1)
//			return;
//		City startCity = list.get(0);
//		City endCity = list.get(0);
//		linePrice = linePriceManage.findLinePriceByStartCityIdAndEndCityId(startCity.getId(), endCity.getId());
//		Map<String, Object> cellMap = new HashMap<String, Object>();
//		if(linePrice!=null){
//			cellMap.put("id", linePrice.getId());
//			cellMap.put("startCityId", linePrice.getStartCityId());
//			cellMap.put("startName_cn", startCity.getCn_cityName());
//			cellMap.put("startName_ct", startCity.getCt_cityName());
//			cellMap.put("startName_en", startCity.getEn_cityName());
//			
//			cellMap.put("endCityId", linePrice.getEndCityId());
//			cellMap.put("endName_cn", endCity.getCn_cityName());
//			cellMap.put("endName_ct", endCity.getCt_cityName());
//			cellMap.put("endName_en", endCity.getEn_cityName());
//		}
//		doResult(JSONObject.fromObject(cellMap).toString(),"text/json;charset=UTF-8");
//	}
	
	/** 
     * 從excel文件中讀取所有的內容 
     *  
     * @param file 
     *            excel文件 
     * @return excel文件的內容 
     */
//	@Action(value="readExcel")
//    public void readExcel() {
//		
//		String excelCity[] = {"九龙-九龙MAC","九龙-新界MAC","香港-九龙MAC","香港-香港MAC","香港-新界MAC","新界-新界MAC"};
//		System.out.println("测试下数量是否想家"+countNumber+"===="+excelCity[countNumber]);
//        StringBuffer sb = new StringBuffer();  
//        Workbook wb = null;  
//        try {  
//            // 构造Workbook（工作薄）对象
//            wb = Workbook.getWorkbook(new File("/Users/longhuaxiong/Desktop/Flappy Bird/扫描/完成/"+excelCity[countNumber]+".xls"));  
//        } catch (BiffException e) {  
//            e.printStackTrace();  
//        } catch (IOException e) {  
//            e.printStackTrace();  
//        }  
//        countNumber ++;
//        if (wb == null)  
//            return;  
//        
//        // 获得了Workbook对象之后，就可以通过它得到Sheet（工作表）对象了  
//        Sheet[] sheet = wb.getSheets();
//        if (sheet != null && sheet.length > 0) {  
//            //得到当前工作表的总行数  
//            int rowNum = sheet[0].getRows();
//            if(rowNum <= 0)
//            	return;
//            Cell[] topCity = sheet[0].getRow(0);
//            for (int i = 1; i < rowNum; i++) {  
//                // 得到当前行的所有单元格
//                Cell[] cells = sheet[0].getRow(i);
//                if (cells != null && cells.length > 0) {  
//                    // 对每个单元格进行循环 
//                	String leftCity = cells[0].getContents();
//                	City leftTempCity = new City();
//                	leftTempCity.setCn_cityName(leftCity);
//                	if(cityManage.getCityByName(leftTempCity)==null){
//                		System.out.println("不存在城市（left边）："+leftCity);
//                		continue;
//                	}else {
//                		leftTempCity = cityManage.getCityByName(leftTempCity);
//                	}
//                    for (int j = 1; j < cells.length; j++) {  
//                        // 读取当前单元格的值 
//                        String cellValue = cells[j].getContents();
//                        City topTempCity = new City();
//                        topTempCity.setCn_cityName(topCity[j].getContents());
//                        if(cityManage.getCityByName(topTempCity)==null && i==1){
//                        	System.out.println("不存在城市（top边）："+topCity[j].getContents());
//                    		continue;
//                        }
//                        if(cityManage.getCityByName(topTempCity)!=null){
//                        	try {
//                        		topTempCity = cityManage.getCityByName(topTempCity);
//                            	linePrice = new LinePrice();
//                            	linePrice.setStartCityId(leftTempCity.getId());
//                            	linePrice.setEndCityId(topTempCity.getId());
//                            	linePrice.setPrice(Integer.parseInt(cellValue));//价钱
//                            	linePrice.setInputTime(new Date());
//                            	linePriceManage.save(linePrice);
//							} catch (Exception e) {
//								System.out.println(leftCity+"======"+cellValue+"====="+topCity[j].getContents());
//							}
//                        	
//                        }
////                        System.out.println(leftCity+"======"+cellValue+"====="+topCity[j].getContents());
//                    }
//                }
//            }  
//        }
//        // 最后关闭资源，释放内存  
//        wb.close();  
//    }
	
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
