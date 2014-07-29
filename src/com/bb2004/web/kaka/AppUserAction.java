package com.bb2004.web.kaka;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

import com.bb2004.common.CrudActionSupport;
import com.bb2004.entity.kaka.AppUser;
import com.bb2004.entity.kaka.SystemInfo;
import com.bb2004.service.kaka.AppUserManage;
import com.bb2004.service.kaka.GiveUpOrderManage;
import com.bb2004.service.kaka.OrderManage;
import com.bb2004.service.kaka.SystemInfoManage;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

@Namespace("/appUser")
public class AppUserAction extends CrudActionSupport{
	private Long id;
	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}
	private int level;
	public void setLevel(int level) {this.level = level;}
	private List list;
	public List getList() {return list;}
	public void setList(List list) {this.list = list;}
	private String name;
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}
	
	private AppUser appUser;
	public AppUser getAppUser() {return appUser;}
	public void setAppUser(AppUser appUser) {this.appUser = appUser;}
	
	private AppUserManage appUserManage;
	@Autowired
	public void setAppUserManage(AppUserManage appUserManage) {this.appUserManage = appUserManage;}
	private SystemInfoManage systemInfoManage;
	@Autowired
	public void setSystemInfoManage(SystemInfoManage systemInfoManage) {this.systemInfoManage = systemInfoManage;}
	private GiveUpOrderManage giveUpOrderManage;
	@Autowired
	public void setGiveUpOrderManage(GiveUpOrderManage giveUpOrderManage) {this.giveUpOrderManage = giveUpOrderManage;}
	private OrderManage orderManage;
	@Autowired
	public void setOrderManage(OrderManage orderManage) {this.orderManage = orderManage;}

	
	// 封装上传文件名的属性
	private File file;
	public File getFile() {return file;}
	public void setFile(File file) {this.file = file;}
    private String fileFileName;
	public String getFileFileName() {return fileFileName;}
	public void setFileFileName(String fileFileName) {this.fileFileName = fileFileName;}
	
	private File file1;
	public File getFile1() {return file1;}
	public void setFile1(File file1) {this.file1 = file1;}
	private String file1FileName;
	public String getFile1FileName() {return file1FileName;}
	public void setFile1FileName(String file1FileName) {this.file1FileName = file1FileName;}
	
	private File file2;
	public File getFile2() {return file2;}
	public void setFile2(File file2) {this.file2 = file2;}
	private String file2FileName;
	public String getFile2FileName() {return file2FileName;}
	public void setFile2FileName(String file2FileName) {this.file2FileName = file2FileName;}
	
	/**
	 * @content 进入编辑页面
	 * @return
	 */
	@Action(value="objectInput",results={@Result(name="input",location="../admin/kaka/appUser-input.jsp")})
	public String objectInput(){
		if(id!=null){
			appUser=appUserManage.getId(id);
		}else{
			appUser=new AppUser();
		}
		return INPUT;
	}
	
	/**
	 * @content 进入详细页面
	 * @return
	 */
	@Action(value="objectDetail",results={@Result(name="detail",location="../admin/kaka/appUser-detail.jsp")})
	public String objectDetail(){
		if(id!=null){
			appUser=appUserManage.getId(id);
		}
		return "detail";
	}
	
	/**
	 * @content 添加Object
	 * @throws Exception
	 */
	@Action(value="objectSave")
	public void objectSave() throws Exception{
		String rspStatus = "1";
		try {
			if(appUser == null){
				return;
			}
			appUser.setInputTime(new Date());
			appUser.setFactoryDate("出厂年份");
			appUser.setLastTime(new Date());
			appUser.setLevel(0);
			appUser.setState(0);
			Md5PasswordEncoder md5=new Md5PasswordEncoder();
			//手机加密码声称MD5码
			appUser.setPassWorld(md5.encodePassword(appUser.getPhone(), appUser.getPassWorld()));
			appUserManage.save(appUser);
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
			appUser = appUserManage.getId(id);
			if(appUser!=null) {
				appUserManage.delete(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
			rspStatus="9";
		}
		doResult(rspStatus,"text/json;charset=UTF-8");
	}
	
	
	/**
	 * 禁用和恢复用户
	 * @throws Exception
	 */
	@Action(value="updateState")
	public void updateState() throws Exception{
		String rspStatus = "1";
		try {
			appUser = appUserManage.getId(id);
			if(appUser == null)
				return;
			if(appUser.getState() == 0){
				appUser.setState(1);//禁用
			}else{
				appUser.setState(0);//恢复正常
			}
			appUserManage.save(appUser);
		} catch (Exception e) {
			e.printStackTrace();
			rspStatus="9";
		}
		doResult(rspStatus,"text/json;charset=UTF-8");
	}
	
	/**
	 * 禁用和恢复用户
	 * @throws Exception
	 */
	@Action(value="updateLevel")
	public void updateLevel() throws Exception{
		String rspStatus = "1";
		try {
			appUser = appUserManage.getId(id);
			if(appUser == null)
				return;
			appUser.setLevel(level);
			appUserManage.save(appUser);
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
		int countNumber = appUserManage.addHqlCount(appUser);
		List<AppUser> list=appUserManage.listObjecr(appUser, this.getPage(), this.getRows());
		super.pageMethod(countNumber);
		List gridModel = new ArrayList();
		
		Map levelMap = new HashMap();
		levelMap.put(0, "未验证");
		levelMap.put(1, "部分验证");
		levelMap.put(2, "验证失败");
		levelMap.put(3, "完全验证");
		
		for(int i=0;i<list.size();i++){
			Map<String, Object> cellMap = new HashMap<String, Object>();
			appUser=list.get(i);
			cellMap.put("id", appUser.getId());
			cellMap.put("carNumber", appUser.getCarNumber());//车牌号码
			cellMap.put("phone", appUser.getPhone());//手机号码
			cellMap.put("userName", appUser.getUserName());//用户名称
			cellMap.put("introducerPhone", appUser.getIntroducerPhone());//介绍人号码
			cellMap.put("carType", appUser.getCarType());//车类型
			cellMap.put("vanCarType", appUser.getVanCarType());//van仔车的类型
			cellMap.put("factoryDate", appUser.getFactoryDate());//出厂日期
			cellMap.put("carType", appUser.getCarType());//车类型
			cellMap.put("idCardImage", appUser.getIdCardImage());//身份证照片
			cellMap.put("carNumberImage", appUser.getCarNumberImage());//车牌照片
			cellMap.put("driveCardImage", appUser.getDriveCardImage());//行车证照片
			cellMap.put("state", appUser.getState()==0?"正常":"禁用");//状态
			cellMap.put("level", levelMap.get(appUser.getLevel()));//验证等级
			cellMap.put("inputTime", appUser.getInputTime().toString().substring(0, 10));//录入时间
			cellMap.put("lastTime", appUser.getLastTime().toString().substring(0, 10));//最后次登陆时间
			cellMap.put("operation", "<a href=\'javascript:void(0);\' onclick=\'doUpdate("+appUser.getId()+")\' style=\'color:#090;margin-right:20px;\'>更改密码</a><a href=\'javascript:void(0);\' onclick=\'doDelete("+appUser.getId()+")\' style=\'color:#F00;\'>"+(appUser.getState()==1?"恢复":"禁用")+"</a>");
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
	 * @content 查询用户
	 * @throws Exception
	 */
	@Action(value="checkList")
	public void checkList() throws Exception {
		int countNumber = appUserManage.findLevelUserCount(4);
		List<AppUser> list=appUserManage.findLevelUser(4);
		super.pageMethod(countNumber);
		List gridModel = new ArrayList();
		
		Map levelMap = new HashMap();
		levelMap.put(0, "未验证");
		levelMap.put(1, "部分验证");
		levelMap.put(2, "验证失败");
		levelMap.put(3, "完全验证");
		
		for(int i=0;i<list.size();i++){
			Map<String, Object> cellMap = new HashMap<String, Object>();
			appUser=list.get(i);
			cellMap.put("id", appUser.getId());
			cellMap.put("carNumber", appUser.getCarNumber());//车牌号码
			cellMap.put("phone", appUser.getPhone());//手机号码
			cellMap.put("userName", appUser.getUserName());//用户名称
			cellMap.put("introducerPhone", appUser.getIntroducerPhone());//介绍人号码
			cellMap.put("carType", appUser.getCarType());//车类型
			cellMap.put("vanCarType", appUser.getVanCarType());//van仔车的类型
			cellMap.put("factoryDate", appUser.getFactoryDate());//出厂日期
			cellMap.put("carType", appUser.getCarType());//车类型
			cellMap.put("idCardImage", appUser.getIdCardImage());//身份证照片
			cellMap.put("carNumberImage", appUser.getCarNumberImage());//车牌照片
			cellMap.put("driveCardImage", appUser.getDriveCardImage());//行车证照片
			cellMap.put("state", appUser.getState()==0?"正常":"禁用");//状态
			cellMap.put("level", levelMap.get(appUser.getLevel()));//验证等级
			cellMap.put("inputTime", appUser.getInputTime().toString().substring(0, 10));//录入时间
			cellMap.put("lastTime", appUser.getLastTime().toString().substring(0, 10));//最后次登陆时间
			cellMap.put("operation", "<a href=\'javascript:void(0);\' onclick=\'doUpdate("+appUser.getId()+")\' style=\'color:#090;margin-right:20px;\'>审核用户</a>");
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
	
	@Action(value="wapjsonTest1")
	public void wapjsonTest1() throws Exception{
		JSONObject jsonObject = new JSONObject();
		try {
			Map map = request.getParameterMap();
			Iterator<?> it = map.keySet().iterator();
			while(it.hasNext()){
				String key = (String)it.next();
				String[] values = (String[])map.get(key);
				jsonObject.accumulate(key, values[0]);
			}

			String userName = jsonObject.getString("userName");
			String password = jsonObject.getString("password");
			jsonObject.clear();       // 清空jsonObjec中的数据
			jsonObject.put("userName", userName);
			jsonObject.put("password", password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		doResult(jsonObject.toString(),"text/json;charset=UTF-8");
	}
	
	@Action(value="wapjsonTest2")
	public void wapjsonTest2() throws Exception{
		System.out.println("当前编码格式："+request.getCharacterEncoding());
		System.out.println(request.getContentType());
		System.out.println(request.getParameter("测试"+"userName"));
//		request.setCharacterEncoding("GB2312"); 
		String jsonString =  readJSONString(request);
		JSONObject jsonObject = JSONObject.fromObject(jsonString);
		System.out.println(jsonObject.get("userName"));
		System.out.println("userName:"+new String(jsonObject.get("userName").toString().getBytes("utf-8"), "gb2312"));
		System.out.println("password:"+jsonObject.get("password"));
		doResult(jsonObject.toString(),"text/json;charset=UTF-8");
	}
	
	private String readJSONString(HttpServletRequest request){
        StringBuffer json = new StringBuffer();
        String line = null;
        try {
        	System.out.println("当前编码格式2："+request.getCharacterEncoding());
    		System.out.println(request.getContentType());
            BufferedReader reader = request.getReader();
            while((line = reader.readLine()) != null) {
            	System.out.println("JSON:"+line);
                json.append(line);
            }
        }
        catch(Exception e) {
            System.out.println(e.toString());
        }
        return json.toString();
    }
	
	@Action(value="wapAddUser")
	public void wapAddUser() throws Exception{
		JSONObject objectJson = new JSONObject();
		String rspStatus = "1";
		try {
			String passWorld = request.getParameter("passWorld");
			String carNumber = request.getParameter("carNumber");
			String userName = request.getParameter("userName");
			String phone = request.getParameter("phone");
			String introducerPhone = request.getParameter("introducerPhone");
			String carType = request.getParameter("carType");
			String vanCarType = request.getParameter("vanCarType");
			String factoryDate = request.getParameter("factoryDate");
			String deviceToken = request.getParameter("deviceToken");
			
			appUser = new AppUser();
			appUser.setInputTime(new Date());
			appUser.setLastTime(new Date());
			if(deviceToken!=null && !deviceToken.equals("")){
				deviceToken = deviceToken.substring(1, deviceToken.length()-1);
				deviceToken = deviceToken.replaceAll(" ", "");
				appUser.setDeviceToken(deviceToken);
			}
			appUser.setLevel(0);
			appUser.setState(0);
			appUser.setCarNumber(carNumber);
			appUser.setPhone(phone);
			appUser.setIntroducerPhone(introducerPhone);
			appUser.setCarType(carType);
			appUser.setVanCarType(vanCarType);
			appUser.setFactoryDate(factoryDate);
			appUser.setUserName(userName);
			Md5PasswordEncoder md5=new Md5PasswordEncoder();
			//车牌号码和密码进行MD5码
			appUser.setPassWorld(md5.encodePassword("bb2004", passWorld));
			appUserManage.save(appUser);
			
		} catch (Exception e) {
			e.printStackTrace();
			rspStatus="9";
		}finally{
			objectJson.put("state", appUser.getState());
			objectJson.put("level", appUser.getLevel());
			objectJson.put("inputTime", appUser.getInputTime());
		}
		doResult(JSONObject.fromObject(objectJson).toString(),"text/json;charset=UTF-8");
	}
	
	/**
	 * @conetnt 手机用户登陆
	 * @throws Exception
	 */
	@Action(value="wapLogin")
	public void wapLogin() throws Exception{
		JSONObject objectJson = new JSONObject();
		List<AppUser> list = null;
		List systemList = null;
		try {
			String phone = request.getParameter("carNumber");
			String passWorld = request.getParameter("passWorld");
			
			Md5PasswordEncoder md5=new Md5PasswordEncoder();
			appUser = new AppUser();
			appUser.setLevel(-1);
			appUser.setPassWorld(md5.encodePassword("bb2004", passWorld));
			appUser.setPhone(phone);
//			appUser.setCarNumber(carNumber);
			list = appUserManage.listObjecr(appUser, this.getPage(), this.getRows());
			systemList = systemInfoManage.listObjecr(null, 0, 15);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(list.size() <= 0)
				return;
			//更新最后次登陆时间
			AppUser appUser = list.get(0);
			appUserManage.save(appUser);
			
			appUser.setLastTime(new Date());
			objectJson.put("id", appUser.getId());//ID
			objectJson.put("carNumber", appUser.getCarNumber());//车牌号码
			objectJson.put("phone", appUser.getPhone());//手机号码
			objectJson.put("userName", appUser.getUserName());//用户名称
			objectJson.put("introducerPhone", appUser.getIntroducerPhone());//介绍人号码
			objectJson.put("carType", appUser.getCarType());//车类型
			objectJson.put("vanCarType", appUser.getVanCarType());//van仔车的类型
			objectJson.put("factoryDate", appUser.getFactoryDate());//出厂日期
			objectJson.put("carType", appUser.getCarType());//车类型
			objectJson.put("idCardImage", appUser.getIdCardImage());//身份证照片
			objectJson.put("carNumberImage", appUser.getCarNumberImage());//车牌照片
			objectJson.put("driveCardImage", appUser.getDriveCardImage());//行车证照片
			objectJson.put("state", appUser.getState());//状态
			objectJson.put("level", appUser.getLevel());//验证等级
			objectJson.put("inputTime", appUser.getInputTime().toString().substring(0, 10));//录入时间
			objectJson.put("lastTime", appUser.getLastTime().toString().substring(0, 10));//最后次登陆时间
			
			if(systemList == null)
				return;
			SystemInfo systemInfo = (SystemInfo)systemList.get(0);
			objectJson.put("systemid", systemInfo.getId());
			objectJson.put("ct_title", systemInfo.getCt_title());
			objectJson.put("en_title", systemInfo.getEn_title());
			objectJson.put("cn_title", systemInfo.getCn_title());
			
			objectJson.put("ct_content", systemInfo.getCt_content());
			objectJson.put("en_content", systemInfo.getEn_content());
			objectJson.put("cn_content", systemInfo.getCn_content());
			
		}
		doResult(JSONObject.fromObject(objectJson).toString(),"text/json;charset=UTF-8");
	}
	
	/**
	 * @conetnt 手机用户登陆
	 * @throws Exception
	 */
	@Action(value="wapPhoneCheck")
	public void wapPhoneCheck() throws Exception{
		String rspStatus = "0";
		JSONObject objectJson = new JSONObject();
		List<AppUser> list = null;
		try {
			String phone = request.getParameter("phone");
			appUser = new AppUser();
			appUser.setPhone(phone);
			list = appUserManage.listObjecr(appUser, this.getPage(), this.getRows());
			if(list.size()>0){
				rspStatus = "1";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			objectJson.put("state", rspStatus);
		}
		doResult(JSONObject.fromObject(objectJson).toString(),"text/json;charset=UTF-8");
	}
	
	/**
	 * @content 图片上传
	 * @throws Exception
	 */
	@Action(value="wapImageUpload")
	public void wapImageUpload() throws Exception {
		String rspStatus = "9";
		JSONObject objectJson = new JSONObject();
		try {
			String idcard = "";
			String carnumber = "";
			String driveCard = "";
			if(file!=null && file1!=null && file2!=null){
				carnumber = uploadimage(file, ".jpg", 300, 300);
				idcard = uploadimage(file1, ".jpg", 300, 300);
				driveCard = uploadimage(file2, ".jpg", 300, 300);
			}
			appUser = appUserManage.getId(id);
			//没验证0，第一次验证为1，第二次为3,第一次验证失败为2,验证状态为4
			if(appUser!=null && !idcard.equals("") && !carnumber.equals("") && !driveCard.equals("")){
				appUser.setLevel(4);
				appUser.setCarNumberImage(carnumber);
				appUser.setIdCardImage(idcard);
				appUser.setDriveCardImage(driveCard);
				appUserManage.save(appUser);
				rspStatus = "1";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			objectJson.put("state", rspStatus);
		}
		doResult(JSONObject.fromObject(objectJson).toString(),"text/json;charset=UTF-8");
	}
	
	@Action(value="wapCount")
	public void wapCount() throws Exception{
		String appUserId = request.getParameter("appUserId");
		String phone = request.getParameter("phone");
		JSONObject objectJson = new JSONObject();
		
		try {
			if(appUserId == null || appUserId.equals(""))
				return;
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Map countMap = new HashMap();
			
			//统计今天订单
			String toDay = sdf.format(new Date());
			List toDayOrder = orderManage.findbyToDayOrder(toDay, 2, Long.valueOf(appUserId));
			Object today[] = (Object[])toDayOrder.get(0);
			objectJson.put("toDayCountNumber", today[0]);
			System.out.println("======="+today[1]==null+"==1111=="+today[1]+"=2222===");
			objectJson.put("toDayCountPrice", today[1]==null?"0":today[1]);
			
			//统计所有订单
			List allOrderCount = orderManage.findbyToDayOrder(null, 2, Long.valueOf(appUserId));
			Object all[] = (Object[])allOrderCount.get(0);
			objectJson.put("allCountNumber", all[0]);
			objectJson.put("allCountPrice", all[1]);
			
			//统计弹单数量
			int giveUpCount =giveUpOrderManage.findbyAppUser(Long.valueOf(appUserId));
			objectJson.put("giveUpNumber", giveUpCount);
			
			//统计介绍人数量
			int phoneCount = 0;
			if(phone!=null && !phone.equals("")){
				phoneCount = appUserManage.countintroducerPhone(phone);
			}
			objectJson.put("introducerNumber", phoneCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		doResult(JSONObject.fromObject(objectJson).toString(),"text/json;charset=UTF-8");
	}
	
	/**
	 * @content 等比例压缩图片
	 * @param file 要压缩的文件
	 * @param kind 压缩文件的后序
	 * @param _width 等比例压缩的宽
	 * @param _height 等比例压缩的高
	 * @return currentTimeMillis+图片格式   简单点就是一个图片名称
	 */
	public String uploadimage(File file,String kind,double _width,double _height){
		long start = (long) System.currentTimeMillis();
		 FileInputStream fis=null;
	     FileOutputStream fwr=null;
	     int b=0;
	     try {
	    	fis=new FileInputStream(file);
			Image image=ImageIO.read(fis);
			double width=image.getWidth(null)/_width; //4
			double height=image.getHeight(null)/_height; //4
			double max =width>height?width:height;
			int newwidth = (int)(image.getWidth(null)/max);
			int newheight = (int)(image.getHeight(null)/max);
			BufferedImage tag=new BufferedImage(newwidth,newheight,BufferedImage.TYPE_INT_RGB);
			tag.getGraphics().drawImage(image.getScaledInstance(newwidth, newheight, Image.SCALE_SMOOTH), 0, 0, null); 
			fwr=new FileOutputStream(request.getSession().getServletContext().getRealPath("//appUserImage")+"//"+start+kind);
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(fwr);   
			encoder.encode(tag);   

			fis.close();
			fwr.close();
			long end = (long) System.currentTimeMillis(); // 结束时间
			
			System.out.println(start+"==========="+end);
			long bb= end-start;
			System.out.println("总共用了：" +bb+ "毫秒");   
		} catch (Exception e) {
			e.printStackTrace();
		}
	     return start+kind;
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
