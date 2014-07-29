package com.bb2004.web.feng;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

import com.bb2004.common.CrudActionSupport;
import com.bb2004.common.HibernateDao;
import com.bb2004.dao.AllDao;
import com.bb2004.entity.feng.CompanyInfo;
import com.bb2004.entity.feng.Contact;
import com.bb2004.entity.feng.Contact;
import com.bb2004.entity.feng.Executives;
import com.bb2004.entity.feng.Executives;
import com.bb2004.entity.feng.InvestmentInfo;
import com.bb2004.entity.feng.InvestmentInfo;
import com.bb2004.entity.feng.CompanyInfo;
import com.bb2004.entity.kaka.SystemInfo;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * @content 投资者关系
 * @author 龙华雄
 * @param <T>
 * @time 2014-07-23
 */
@Namespace("/investment")
public class InvestmentAction<T> extends CrudActionSupport {
	private Long id;
	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}
	private String name;
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}
	//上传
	private File file;
	public File getFile() {return file;}
	public void setFile(File file) {this.file = file;}
    private String fileFileName;
	public String getFileFileName() {return fileFileName;}
	public void setFileFileName(String fileFileName) {this.fileFileName = fileFileName;}
	
	//投资者信息
	private InvestmentInfo investmentInfo;
	public InvestmentInfo getInvestmentInfo() {return investmentInfo;}
	public void setInvestmentInfo(InvestmentInfo investmentInfo) {this.investmentInfo = investmentInfo;}
	//高管
	private Executives executives;
	public Executives getExecutives() {return executives;}
	public void setExecutives(Executives executives) {this.executives = executives;}
	//投资联系方式
	private Contact contact;
	public Contact getContact() {return contact;}
	public void setContact(Contact contact) {this.contact = contact;}
	//公司公告
	private CompanyInfo companyInfo;
	public CompanyInfo getCompanyInfo() {return companyInfo;}
	public void setCompanyInfo(CompanyInfo companyInfo) {this.companyInfo = companyInfo;}
	
	private HibernateDao<T> hibernateDao;
	public HibernateDao<T> getHibernateDao() {return hibernateDao;}
	public void setHibernateDao(HibernateDao<T> hibernateDao) {this.hibernateDao = hibernateDao;}
	
	private AllDao<T> allDao;
	public AllDao<T> getAllDao() {return allDao;}
	public void setAllDao(AllDao<T> allDao) {this.allDao = allDao;}
	
	//==================================================投资信息=======================================================================================
	
	@Action(value="investmentInfoInput",results={@Result(name="input",location="../admin/feng/investmentInfo-input.jsp")})
	public String investmentInfoInput(){
		if(id!=null &&  id > 0){
			investmentInfo=hibernateDao.get(InvestmentInfo.class, id);
		}else{
			investmentInfo=new InvestmentInfo();
		}
		return INPUT;
	}
	
	/**
	 * @content 添加Object
	 * @throws Exception
	 */
	@Action(value="investmentInfoSave")
	public void investmentInfoSave() throws Exception{
		String rspStatus = "1";
		try {
			if(investmentInfo == null){
				return;
			}
			investmentInfo.setInputTime(new Date());
			hibernateDao.save(investmentInfo);
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
	@Action(value="investmentInfoDelete")
	public void investmentInfoDelete() throws Exception{
		String rspStatus = "1";
		try {
			investmentInfo = hibernateDao.get(InvestmentInfo.class, id);
			if(investmentInfo!=null) {
				hibernateDao.delete(investmentInfo);
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
	@Action(value="investmentInfoList")
	public void investmentInfoList() throws Exception{
		StringBuffer hql = new StringBuffer(" from InvestmentInfo t where 1= 1");
		if(name!=null && !name.trim().equals("")){
			hql.append(" and t.title like '%"+name+"%'");
		}
		List<InvestmentInfo> list=(List<InvestmentInfo>) allDao.listObjecr(hql.toString(), this.getPage(), this.getRows());
		List gridModel = new ArrayList();
		for(int i=0;i<list.size();i++){
			Map<String, Object> cellMap = new HashMap<String, Object>();
			investmentInfo=list.get(i);
			cellMap.put("id", investmentInfo.getId());
			cellMap.put("title", investmentInfo.getTitle());
			cellMap.put("content", investmentInfo.getContent());
			cellMap.put("inputTime", investmentInfo.getInputTime().toString().substring(0, 10));
			cellMap.put("operation", "<a href=\'javascript:void(0);\' onclick=\'doUpdate("+investmentInfo.getId()+")\' style=\'color:#090;margin-right:20px;\'>编辑</a><a href=\'javascript:void(0);\' onclick=\'doDelete("+investmentInfo.getId()+")\' style=\'color:#F00;\'>删除</a>");
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
	
	//==================================================高管=======================================================================================
	
		@Action(value="executivesInput",results={@Result(name="input",location="../admin/feng/executives-input.jsp")})
		public String executivesInput(){
			if(id!=null &&  id > 0){
				executives=hibernateDao.get(Executives.class, id);
			}else{
				executives=new Executives();
			}
			return INPUT;
		}
		
		/**
		 * @content 添加Object
		 * @throws Exception
		 */
		@Action(value="executivesSave",results={@Result(name="list",location="../admin/feng/executives-list.jsp")})
		public String executivesSave() throws Exception{
			String rspStatus = "1";
			try {
				if(executives == null){
					return "list";
				}
				executives.setInputTime(new Date());
				//图片
				if(fileFileName!=null && !fileFileName.equals("")){
					String kind=fileFileName.substring(fileFileName.indexOf("."), fileFileName.length());
					String[] allow={".jpg",".gif",",jpeg",".png"};//允许上传的格式
					if(filterUpload(kind,allow)){
						executives.setImages(uploadimage(file, kind, 200, 300));
					}
				}
				hibernateDao.save(executives);
			} catch (Exception e) {
				e.printStackTrace();
				rspStatus = "9";
			}
			return "list";
		}
		
		/**
		 * content 删除对象
		 * @return
		 * @throws Exception
		 */
		@Action(value="executivesDelete")
		public void executivesDelete() throws Exception{
			String rspStatus = "1";
			try {
				executives = hibernateDao.get(Executives.class, id);
				if(executives!=null) {
					hibernateDao.delete(executives);
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
		@Action(value="executivesList")
		public void executivesList() throws Exception{
			StringBuffer hql = new StringBuffer(" from Executives t where 1= 1");
			if(name!=null && !name.trim().equals("")){
				hql.append(" and t.title like '%"+name+"%'");
			}
			List<Executives> list=(List<Executives>) allDao.listObjecr(hql.toString(), this.getPage(), this.getRows());
			List gridModel = new ArrayList();
			for(int i=0;i<list.size();i++){
				Map<String, Object> cellMap = new HashMap<String, Object>();
				executives=list.get(i);
				cellMap.put("id", executives.getId());
				cellMap.put("title", executives.getTitle());
				cellMap.put("content", executives.getContent());
				cellMap.put("inputTime", executives.getInputTime().toString().substring(0, 10));
				cellMap.put("operation", "<a href=\'javascript:void(0);\' onclick=\'doUpdate("+executives.getId()+")\' style=\'color:#090;margin-right:20px;\'>编辑</a><a href=\'javascript:void(0);\' onclick=\'doDelete("+executives.getId()+")\' style=\'color:#F00;\'>删除</a>");
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
		
		//==================================================投资者联系方式=======================================================================================
		
		/**
		 * @content 进入编辑页面
		 * @return
		 */
		@Action(value="contactInput",results={@Result(name="input",location="../admin/feng/contact-input.jsp")})
		public String contactInput(){
			List<Contact> list=hibernateDao.getAll(Contact.class);
			if(list.size() > 0){
				contact=list.get(0);
			}else{
				contact=new Contact();
			}
			return INPUT;
		}
		
		/**
		 * @content 添加Object
		 * @throws Exception
		 */
		@Action(value="contactSave")
		public void contactSave() throws Exception{
			String rspStatus = "1";
			try {
				if(contact == null){
					return;
				}
				contact.setInputTime(new Date());
				hibernateDao.save(contact);
			} catch (Exception e) {
				e.printStackTrace();
				rspStatus = "9";
			}
			doResult(rspStatus,"text/json;charset=UTF-8");
		}
		
		//==================================================公司公告=======================================================================================
		
		@Action(value="companyInfoInput",results={@Result(name="input",location="../admin/feng/companyInfo-input.jsp")})
		public String companyInfoInput(){
			if(id!=null &&  id > 0){
				companyInfo=hibernateDao.get(CompanyInfo.class, id);
			}else{
				companyInfo=new CompanyInfo();
			}
			return INPUT;
		}
		
		/**
		 * @content 添加Object
		 * @throws Exception
		 */
		@Action(value="companyInfoSave")
		public void companyInfoSave() throws Exception{
			String rspStatus = "1";
			try {
				if(companyInfo == null){
					return;
				}
				companyInfo.setInputTime(new Date());
				hibernateDao.save(companyInfo);
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
		@Action(value="companyInfoDelete")
		public void companyInfoDelete() throws Exception{
			String rspStatus = "1";
			try {
				companyInfo = hibernateDao.get(CompanyInfo.class, id);
				if(companyInfo!=null) {
					hibernateDao.delete(companyInfo);
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
		@Action(value="companyInfoList")
		public void companyInfoList() throws Exception{
			StringBuffer hql = new StringBuffer(" from CompanyInfo t where 1= 1");
			if(name!=null && !name.trim().equals("")){
				hql.append(" and t.title like '%"+name+"%'");
			}
			List<CompanyInfo> list=(List<CompanyInfo>) allDao.listObjecr(hql.toString(), this.getPage(), this.getRows());
			List gridModel = new ArrayList();
			for(int i=0;i<list.size();i++){
				Map<String, Object> cellMap = new HashMap<String, Object>();
				companyInfo=list.get(i);
				cellMap.put("id", companyInfo.getId());
				cellMap.put("title", companyInfo.getTitle());
				cellMap.put("content", companyInfo.getContent());
				cellMap.put("inputTime", companyInfo.getInputTime().toString().substring(0, 10));
				cellMap.put("operation", "<a href=\'javascript:void(0);\' onclick=\'doUpdate("+companyInfo.getId()+")\' style=\'color:#090;margin-right:20px;\'>编辑</a><a href=\'javascript:void(0);\' onclick=\'doDelete("+companyInfo.getId()+")\' style=\'color:#F00;\'>删除</a>");
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
		
		/**
		 * @content 格式上传过滤
		 */
		public Boolean filterUpload(String kind,String[] allow){
			//允许上传的格式
			boolean whether=false;
			for(int i=0;i<allow.length;i++){
				if(kind.equals(allow[i].toUpperCase())||kind.equals(allow[i].toLowerCase())){
					whether=true;
					break;
				}
			}
			return whether;
		}
		
		
	/**
	 * @content 等比例压缩图片
	 * @param file 要压缩的文件
	 * @param kind 压缩文件的后序
	 * @param _width 等比例压缩的宽    宽和高都为0为不压缩
	 * @param _height 等比例压缩的高    宽和高都为0为不压缩
	 * @return currentTimeMillis+图片格式   简单点就是一个图片名称
	 */
	public String uploadimage(File file,String kind,double _width,double _height){
		long start = (long) System.currentTimeMillis();
		 FileInputStream fis=null;
	     FileOutputStream fwr=null;
	     try {
	    	fis=new FileInputStream(file);
			Image image=ImageIO.read(fis);
			int newwidth=0;
			int newheight=0;
			//是否需要等比例
			if(_width>0 && _height>0){
				double width = image.getWidth(null)/_width;
				double height = image.getHeight(null)/_height;
				double max =width>height?width:height;
				newwidth = (int)(image.getWidth(null)/max);
				newheight = (int)(image.getHeight(null)/max);
			}else {
				newwidth = image.getWidth(null);
				newheight = image.getHeight(null);
			}
			BufferedImage tag=new BufferedImage(newwidth,newheight,BufferedImage.TYPE_INT_RGB);
			tag.getGraphics().drawImage(image.getScaledInstance(newwidth, newheight, Image.SCALE_SMOOTH), 0, 0, null); 
			fwr=new FileOutputStream(request.getSession().getServletContext().getRealPath("//executivesImage")+"//"+start+kind);
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(fwr);   
			encoder.encode(tag);   

			fis.close();
			fwr.close();   
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
