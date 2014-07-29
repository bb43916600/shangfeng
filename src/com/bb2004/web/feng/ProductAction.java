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
import com.bb2004.entity.feng.Product;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * @content 产品
 * @author 龙华雄
 * @param <T>
 * @time 2014-07-24
 */
@Namespace("/product")
public class ProductAction<T> extends CrudActionSupport{
	private Long id;
	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}
	private String name;
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}
	private int type;
	public int getType() {return type;}
	public void setType(int type) {this.type = type;}
	//上传
	private File file;
	public File getFile() {return file;}
	public void setFile(File file) {this.file = file;}
    private String fileFileName;
	public String getFileFileName() {return fileFileName;}
	public void setFileFileName(String fileFileName) {this.fileFileName = fileFileName;}
	
	//荣誉
	private Product product;
	public Product getProduct() {return product;}
	public void setProduct(Product product) {this.product = product;}
	
	private HibernateDao<T> hibernateDao;
	public HibernateDao<T> getHibernateDao() {return hibernateDao;}
	public void setHibernateDao(HibernateDao<T> hibernateDao) {this.hibernateDao = hibernateDao;}
	
	private AllDao<T> allDao;
	public AllDao<T> getAllDao() {return allDao;}
	public void setAllDao(AllDao<T> allDao) {this.allDao = allDao;}
		
	
	//==================================================荣誉=======================================================================================
	
	@Action(value="productInput",results={@Result(name="input",location="../admin/feng/product-input.jsp")})
	public String productInput(){
		if(id!=null &&  id > 0){
			product=hibernateDao.get(Product.class, id);
		}else{
			product=new Product();
			product.setType(type);
		}
		return INPUT;
	}
	
	/**
	 * @content 添加Object
	 * @throws Exception
	 */
	@Action(value="productSave",results={@Result(name="list",location="../admin/feng/product-list.jsp")})
	public String productSave() throws Exception{
		String rspStatus = "1";
		try {
			if(product == null){
				return "list";
			}
			product.setInputTime(new Date());
			//图片
			if(fileFileName!=null && !fileFileName.equals("")){
				String kind=fileFileName.substring(fileFileName.indexOf("."), fileFileName.length());
				String[] allow={".jpg",".gif",",jpeg",".png"};//允许上传的格式
				if(filterUpload(kind,allow)){
					product.setImages(uploadimage(file, kind, 0, 0));
				}
			}
			hibernateDao.save(product);
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
	@Action(value="productDelete")
	public void productDelete() throws Exception{
		String rspStatus = "1";
		try {
			product = hibernateDao.get(Product.class, id);
			if(product!=null) {
				hibernateDao.delete(product);
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
	@Action(value="productList")
	public void productList() throws Exception{
		StringBuffer hql = new StringBuffer(" from Product t where 1= 1");
		if(type>0){
			hql.append(" and t.type="+type);
		}
		if(name!=null && !name.trim().equals("")){
			hql.append(" and t.title like '%"+name+"%'");
		}
		List<Product> list=(List<Product>) allDao.listObjecr(hql.toString(), this.getPage(), this.getRows());
		List gridModel = new ArrayList();
		for(int i=0;i<list.size();i++){
			Map<String, Object> cellMap = new HashMap<String, Object>();
			product=list.get(i);
			cellMap.put("id", product.getId());
			cellMap.put("title", product.getTitle());
			cellMap.put("content", product.getContent());
			cellMap.put("inputTime", product.getInputTime().toString().substring(0, 10));
			cellMap.put("operation", "<a href=\'javascript:void(0);\' onclick=\'doUpdate("+product.getId()+")\' style=\'color:#090;margin-right:20px;\'>编辑</a><a href=\'javascript:void(0);\' onclick=\'doDelete("+product.getId()+")\' style=\'color:#F00;\'>删除</a>");
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
			fwr=new FileOutputStream(request.getSession().getServletContext().getRealPath("//productImage")+"//"+start+kind);
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
