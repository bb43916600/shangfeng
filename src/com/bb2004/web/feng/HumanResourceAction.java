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
import com.bb2004.entity.feng.Concept;
import com.bb2004.entity.feng.Activitie;
import com.bb2004.entity.feng.StaffPresence;
import com.bb2004.entity.feng.JobContact;
import com.bb2004.entity.feng.Job;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * @content 人力资源
 * @author 龙华雄
 * @param <T>
 * @time 2014-07-23
 */
@Namespace("/hr")
public class HumanResourceAction<T> extends CrudActionSupport{
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
	
	//理念
	private Concept concept;
	public Concept getConcept() {return concept;}
	public void setConcept(Concept concept) {this.concept = concept;}
	//招聘信息
	private Job job;
	public Job getJob() {return job;}
	public void setJob(Job job) {this.job = job;}
	//联系方式
	private JobContact jobContact;
	public JobContact getJobContact() {return jobContact;}
	public void setJobContact(JobContact jobContact) {this.jobContact = jobContact;}
	//重要活动
	private Activitie activitie;
	public Activitie getActivitie() {return activitie;}
	public void setActivitie(Activitie activitie) {this.activitie = activitie;}
	//员工风采
	private StaffPresence staffPresence;
	public StaffPresence getStaffPresence() {return staffPresence;}
	public void setStaffPresence(StaffPresence staffPresence) {this.staffPresence = staffPresence;}
	
	private HibernateDao<T> hibernateDao;
	public HibernateDao<T> getHibernateDao() {return hibernateDao;}
	public void setHibernateDao(HibernateDao<T> hibernateDao) {this.hibernateDao = hibernateDao;}
	
	private AllDao<T> allDao;
	public AllDao<T> getAllDao() {return allDao;}
	public void setAllDao(AllDao<T> allDao) {this.allDao = allDao;}
	
	
	//==================================================理念=======================================================================================
	/**
	 * @content 进入编辑页面
	 * @return
	 */
	@Action(value="conceptInput",results={@Result(name="input",location="../admin/feng/concept-input.jsp")})
	public String conceptInput(){
		List<Concept> list=hibernateDao.getAll(Concept.class);
		if(list.size() > 0){
			concept=list.get(0);
		}else{
			concept=new Concept();
		}
		return INPUT;
	}
	
	/**
	 * @content 添加Object
	 * @throws Exception
	 */
	@Action(value="conceptSave")
	public void conceptSave() throws Exception{
		String rspStatus = "1";
		try {
			if(concept == null){
				return;
			}
			concept.setInputTime(new Date());
			hibernateDao.save(concept);
		} catch (Exception e) {
			e.printStackTrace();
			rspStatus = "9";
		}
		doResult(rspStatus,"text/json;charset=UTF-8");
	}
	
	//==================================================招聘信息=======================================================================================
	
	@Action(value="jobInput",results={@Result(name="input",location="../admin/feng/job-input.jsp")})
	public String jobInput(){
		if(id!=null &&  id > 0){
			job=hibernateDao.get(Job.class, id);
		}else{
			job=new Job();
		}
		return INPUT;
	}
	
	/**
	 * @content 添加Object
	 * @throws Exception
	 */
	@Action(value="jobSave")
	public void jobSave() throws Exception{
		String rspStatus = "1";
		try {
			if(job == null){
				return;
			}
			job.setInputTime(new Date());
			hibernateDao.save(job);
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
	@Action(value="jobDelete")
	public void jobDelete() throws Exception{
		String rspStatus = "1";
		try {
			job = hibernateDao.get(Job.class, id);
			if(job!=null) {
				hibernateDao.delete(job);
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
	@Action(value="jobList")
	public void jobList() throws Exception{
		StringBuffer hql = new StringBuffer(" from Job t where 1= 1");
		if(name!=null && !name.trim().equals("")){
			hql.append(" and t.jobName like '%"+name+"%'");
		}
		List<Job> list=(List<Job>) allDao.listObjecr(hql.toString(), this.getPage(), this.getRows());
		List gridModel = new ArrayList();
		for(int i=0;i<list.size();i++){
			Map<String, Object> cellMap = new HashMap<String, Object>();
			job=list.get(i);
			cellMap.put("id", job.getId());
			cellMap.put("jobName", job.getJobName());
			cellMap.put("content", job.getContent());
			cellMap.put("inputTime", job.getInputTime().toString().substring(0, 10));
			cellMap.put("operation", "<a href=\'javascript:void(0);\' onclick=\'doUpdate("+job.getId()+")\' style=\'color:#090;margin-right:20px;\'>编辑</a><a href=\'javascript:void(0);\' onclick=\'doDelete("+job.getId()+")\' style=\'color:#F00;\'>删除</a>");
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
	
	//==================================================联系方式=======================================================================================
	
	/**
	 * @content 进入编辑页面
	 * @return
	 */
	@Action(value="jobContactInput",results={@Result(name="input",location="../admin/feng/jobContact-input.jsp")})
	public String jobContactInput(){
		List<JobContact> list=hibernateDao.getAll(JobContact.class);
		if(list.size() > 0){
			jobContact=list.get(0);
		}else{
			jobContact=new JobContact();
		}
		return INPUT;
	}
	
	/**
	 * @content 添加Object
	 * @throws Exception
	 */
	@Action(value="jobContactSave")
	public void jobContactSave() throws Exception{
		String rspStatus = "1";
		try {
			if(jobContact == null){
				return;
			}
			jobContact.setInputTime(new Date());
			hibernateDao.save(jobContact);
		} catch (Exception e) {
			e.printStackTrace();
			rspStatus = "9";
		}
		doResult(rspStatus,"text/json;charset=UTF-8");
	}
	
	//==================================================重要活动=======================================================================================
	
	@Action(value="activitieInput",results={@Result(name="input",location="../admin/feng/activitie-input.jsp")})
	public String activitieInput(){
		if(id!=null &&  id > 0){
			activitie=hibernateDao.get(Activitie.class, id);
		}else{
			activitie=new Activitie();
		}
		return INPUT;
	}
	
	/**
	 * @content 添加Object
	 * @throws Exception
	 */
	@Action(value="activitieSave",results={@Result(name="list",location="../admin/feng/activitie-list.jsp")})
	public String activitieSave() throws Exception{
		String rspStatus = "1";
		try {
			if(activitie == null){
				return "list";
			}
			activitie.setInputTime(new Date());
			//图片
			if(fileFileName!=null && !fileFileName.equals("")){
				String kind=fileFileName.substring(fileFileName.indexOf("."), fileFileName.length());
				String[] allow={".jpg",".gif",",jpeg",".png"};//允许上传的格式
				if(filterUpload(kind,allow)){
					activitie.setImages(uploadimage(file, kind, 200, 300));
				}
			}
			hibernateDao.save(activitie);
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
	@Action(value="activitieDelete")
	public void activitieDelete() throws Exception{
		String rspStatus = "1";
		try {
			activitie = hibernateDao.get(Activitie.class, id);
			if(activitie!=null) {
				hibernateDao.delete(activitie);
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
	@Action(value="activitieList")
	public void activitieList() throws Exception{
		StringBuffer hql = new StringBuffer(" from Activitie t where 1= 1");
		if(name!=null && !name.trim().equals("")){
			hql.append(" and t.title like '%"+name+"%'");
		}
		List<Activitie> list=(List<Activitie>) allDao.listObjecr(hql.toString(), this.getPage(), this.getRows());
		List gridModel = new ArrayList();
		for(int i=0;i<list.size();i++){
			Map<String, Object> cellMap = new HashMap<String, Object>();
			activitie=list.get(i);
			cellMap.put("id", activitie.getId());
			cellMap.put("title", activitie.getTitle());
			cellMap.put("content", activitie.getContent());
			cellMap.put("inputTime", activitie.getInputTime().toString().substring(0, 10));
			cellMap.put("operation", "<a href=\'javascript:void(0);\' onclick=\'doUpdate("+activitie.getId()+")\' style=\'color:#090;margin-right:20px;\'>编辑</a><a href=\'javascript:void(0);\' onclick=\'doDelete("+activitie.getId()+")\' style=\'color:#F00;\'>删除</a>");
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
	
	//==================================================员工风采=======================================================================================
	
	@Action(value="staffPresenceInput",results={@Result(name="input",location="../admin/feng/staffPresence-input.jsp")})
	public String staffPresenceInput(){
		if(id!=null &&  id > 0){
			staffPresence=hibernateDao.get(StaffPresence.class, id);
		}else{
			staffPresence=new StaffPresence();
		}
		return INPUT;
	}
	
	/**
	 * @content 添加Object
	 * @throws Exception
	 */
	@Action(value="staffPresenceSave",results={@Result(name="list",location="../admin/feng/staffPresence-list.jsp")})
	public String staffPresenceSave() throws Exception{
		String rspStatus = "1";
		try {
			if(staffPresence == null){
				return "list";
			}
			staffPresence.setInputTime(new Date());
			//图片
			if(fileFileName!=null && !fileFileName.equals("")){
				String kind=fileFileName.substring(fileFileName.indexOf("."), fileFileName.length());
				String[] allow={".jpg",".gif",",jpeg",".png"};//允许上传的格式
				if(filterUpload(kind,allow)){
					staffPresence.setImages(uploadimage(file, kind, 200, 300));
				}
			}
			hibernateDao.save(staffPresence);
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
	@Action(value="staffPresenceDelete")
	public void staffPresenceDelete() throws Exception{
		String rspStatus = "1";
		try {
			staffPresence = hibernateDao.get(StaffPresence.class, id);
			if(staffPresence!=null) {
				hibernateDao.delete(staffPresence);
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
	@Action(value="staffPresenceList")
	public void staffPresenceList() throws Exception{
		StringBuffer hql = new StringBuffer(" from StaffPresence t where 1= 1");
		if(name!=null && !name.trim().equals("")){
			hql.append(" and t.title like '%"+name+"%'");
		}
		List<StaffPresence> list=(List<StaffPresence>) allDao.listObjecr(hql.toString(), this.getPage(), this.getRows());
		List gridModel = new ArrayList();
		for(int i=0;i<list.size();i++){
			Map<String, Object> cellMap = new HashMap<String, Object>();
			staffPresence=list.get(i);
			cellMap.put("id", staffPresence.getId());
			cellMap.put("title", staffPresence.getTitle());
			cellMap.put("content", staffPresence.getContent());
			cellMap.put("inputTime", staffPresence.getInputTime().toString().substring(0, 10));
			cellMap.put("operation", "<a href=\'javascript:void(0);\' onclick=\'doUpdate("+staffPresence.getId()+")\' style=\'color:#090;margin-right:20px;\'>编辑</a><a href=\'javascript:void(0);\' onclick=\'doDelete("+staffPresence.getId()+")\' style=\'color:#F00;\'>删除</a>");
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
			fwr=new FileOutputStream(request.getSession().getServletContext().getRealPath("//staffPresenceImage")+"//"+start+kind);
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
