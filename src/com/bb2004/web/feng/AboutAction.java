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
import org.springframework.beans.factory.annotation.Autowired;

import com.bb2004.common.CrudActionSupport;
import com.bb2004.common.HibernateDao;
import com.bb2004.dao.AllDao;
import com.bb2004.entity.feng.About;
import com.bb2004.entity.feng.Honor;
import com.bb2004.entity.feng.News;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * @content 关于Action
 * @author 龙华雄
 * @param <T>
 * @time 2014-07-23
 */
@Namespace("/about")
public class AboutAction<T> extends CrudActionSupport{
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
	
	//关于
	private About about;
	public About getAbout() {return about;}
	public void setAbout(About about) {this.about = about;}
	//新闻
	private News news;
	public News getNews() {return news;}
	public void setNews(News news) {this.news = news;}
	
	//荣誉
	private Honor honor;
	public Honor getHonor() {return honor;}
	public void setHonor(Honor honor) {this.honor = honor;}
	
	private HibernateDao<T> hibernateDao;
	public HibernateDao<T> getHibernateDao() {return hibernateDao;}
	public void setHibernateDao(HibernateDao<T> hibernateDao) {this.hibernateDao = hibernateDao;}
	
	private AllDao<T> allDao;
	public AllDao<T> getAllDao() {return allDao;}
	public void setAllDao(AllDao<T> allDao) {this.allDao = allDao;}
	
	
	/**
	 * @content 进入编辑页面
	 * @return
	 */
	@Action(value="aboutInput",results={@Result(name="input",location="../admin/feng/about-input.jsp")})
	public String aboutInput(){
		List<About> list=hibernateDao.getAll(About.class);
		if(list.size() > 0){
			about=list.get(0);
		}else{
			about=new About();
		}
		return INPUT;
	}
	
	/**
	 * @content 添加Object
	 * @throws Exception
	 */
	@Action(value="aboutSave")
	public void aboutSave() throws Exception{
		String rspStatus = "1";
		try {
			if(about == null){
				return;
			}
			about.setInputTime(new Date());
			hibernateDao.save(about);
		} catch (Exception e) {
			e.printStackTrace();
			rspStatus = "9";
		}
		doResult(rspStatus,"text/json;charset=UTF-8");
	}
	
	
	//==================================================新闻=======================================================================================
	
	@Action(value="newsInput",results={@Result(name="input",location="../admin/feng/news-input.jsp")})
	public String newsInput(){
		if(id!=null &&  id > 0){
			news=hibernateDao.get(News.class, id);
		}else{
			news=new News();
		}
		return INPUT;
	}
	
	/**
	 * @content 添加Object
	 * @throws Exception
	 */
	@Action(value="newsSave")
	public void newsSave() throws Exception{
		String rspStatus = "1";
		try {
			if(news == null){
				return;
			}
			news.setInputTime(new Date());
			hibernateDao.save(news);
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
	@Action(value="newsDelete")
	public void newsDelete() throws Exception{
		String rspStatus = "1";
		try {
			news = hibernateDao.get(News.class, id);
			if(news!=null) {
				hibernateDao.delete(news);
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
	@Action(value="newsList")
	public void newsList() throws Exception{
		StringBuffer hql = new StringBuffer(" from News t where 1= 1");
		if(name!=null && !name.trim().equals("")){
			hql.append(" and t.title like '%"+name+"%'");
		}
		List<News> list=(List<News>) allDao.listObjecr(hql.toString(), this.getPage(), this.getRows());
		List gridModel = new ArrayList();
		for(int i=0;i<list.size();i++){
			Map<String, Object> cellMap = new HashMap<String, Object>();
			news=list.get(i);
			cellMap.put("id", news.getId());
			cellMap.put("title", news.getTitle());
			cellMap.put("content", news.getContent());
			cellMap.put("inputTime", news.getInputTime().toString().substring(0, 10));
			cellMap.put("operation", "<a href=\'javascript:void(0);\' onclick=\'doUpdate("+news.getId()+")\' style=\'color:#090;margin-right:20px;\'>编辑</a><a href=\'javascript:void(0);\' onclick=\'doDelete("+news.getId()+")\' style=\'color:#F00;\'>删除</a>");
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
	
	//==================================================荣誉=======================================================================================
	
	@Action(value="honorInput",results={@Result(name="input",location="../admin/feng/honor-input.jsp")})
	public String honorInput(){
		if(id!=null &&  id > 0){
			honor=hibernateDao.get(Honor.class, id);
		}else{
			honor=new Honor();
		}
		return INPUT;
	}
	
	/**
	 * @content 添加Object
	 * @throws Exception
	 */
	@Action(value="honorSave",results={@Result(name="list",location="../admin/feng/honor-list.jsp")})
	public String honorSave() throws Exception{
		String rspStatus = "1";
		try {
			if(honor == null){
				return "list";
			}
			honor.setInputTime(new Date());
			//图片
			if(fileFileName!=null && !fileFileName.equals("")){
				String kind=fileFileName.substring(fileFileName.indexOf("."), fileFileName.length());
				String[] allow={".jpg",".gif",",jpeg",".png"};//允许上传的格式
				if(filterUpload(kind,allow)){
					honor.setImages(uploadimage(file, kind, 0, 0));
				}
			}
			hibernateDao.save(honor);
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
	@Action(value="honorDelete")
	public void honorDelete() throws Exception{
		String rspStatus = "1";
		try {
			honor = hibernateDao.get(Honor.class, id);
			if(honor!=null) {
				hibernateDao.delete(honor);
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
	@Action(value="honorList")
	public void honorList() throws Exception{
		StringBuffer hql = new StringBuffer(" from Honor t where 1= 1");
		if(name!=null && !name.trim().equals("")){
			hql.append(" and t.title like '%"+name+"%'");
		}
		List<Honor> list=(List<Honor>) allDao.listObjecr(hql.toString(), this.getPage(), this.getRows());
		List gridModel = new ArrayList();
		for(int i=0;i<list.size();i++){
			Map<String, Object> cellMap = new HashMap<String, Object>();
			honor=list.get(i);
			cellMap.put("id", honor.getId());
			cellMap.put("title", honor.getTitle());
			cellMap.put("content", honor.getContent());
			cellMap.put("inputTime", honor.getInputTime().toString().substring(0, 10));
			cellMap.put("operation", "<a href=\'javascript:void(0);\' onclick=\'doUpdate("+honor.getId()+")\' style=\'color:#090;margin-right:20px;\'>编辑</a><a href=\'javascript:void(0);\' onclick=\'doDelete("+honor.getId()+")\' style=\'color:#F00;\'>删除</a>");
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
			fwr=new FileOutputStream(request.getSession().getServletContext().getRealPath("//honorImage")+"//"+start+kind);
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
