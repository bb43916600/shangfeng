package com.bb2004.web.feng;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

import com.bb2004.common.CrudActionSupport;
import com.bb2004.common.HibernateDao;
import com.bb2004.dao.AllDao;
import com.bb2004.entity.feng.About;
import com.bb2004.entity.feng.Activitie;
import com.bb2004.entity.feng.Camp;
import com.bb2004.entity.feng.CompanyInfo;
import com.bb2004.entity.feng.CompanyOutlook;
import com.bb2004.entity.feng.Concept;
import com.bb2004.entity.feng.Contact;
import com.bb2004.entity.feng.Executives;
import com.bb2004.entity.feng.Honor;
import com.bb2004.entity.feng.Industry;
import com.bb2004.entity.feng.Job;
import com.bb2004.entity.feng.JobContact;
import com.bb2004.entity.feng.Product;
import com.bb2004.entity.feng.StaffPresence;

/**
 * @content UserPortal
 * @author 龙华雄
 * @param <T>
 * @time 2014-07-24
 */
@Namespace("/userportal")
public class UserPortalAction<T> extends CrudActionSupport {
	private Long id;
	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}
	private String name;
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}
	private List list;
	public List getList() {return list;}
	public void setList(List list) {this.list = list;}
	private int type;
	public int getType() {return type;}
	public void setType(int type) {this.type = type;}
	
	private HibernateDao<T> hibernateDao;
	public HibernateDao<T> getHibernateDao() {return hibernateDao;}
	public void setHibernateDao(HibernateDao<T> hibernateDao) {this.hibernateDao = hibernateDao;}
	private AllDao<T> allDao;
	public AllDao<T> getAllDao() {return allDao;}
	public void setAllDao(AllDao<T> allDao) {this.allDao = allDao;}
	
	
	/**
	 * @content 关于
	 * @return
	 */
	@Action(value="wapIndex",results={@Result(name="input",location="/userPortal/index.jsp")})
	public String wapIndex(){
		list = new ArrayList();
		list.add("<li><span class=\"home\"><a href=\""+request.getContextPath()+"/userportal/wapIndex.action\">首 页</a></span></li>");
		list.add("<li><span><a href=\""+request.getContextPath()+"/userportal/wapAbout.action\">关于上风</a></span></li>");
		list.add("<li><span><a href=\""+request.getContextPath()+"/userportal/wapInvestor.action\">投资者关系</a></span></li>");
		list.add("<li><span><a href=\""+request.getContextPath()+"/userportal/wapIndustry.action\">产业介绍</a></span></li>");
		list.add("<li><span><a href=\""+request.getContextPath()+"/userportal/wapProduct.action\">产品信息</a></span></li>");
		list.add("<li><span><a href=\""+request.getContextPath()+"/userportal/wapConcept.action\">人力资源</a></span></li>");
		list.add("<li><span><a href=\""+request.getContextPath()+"/userportal/wapCompanyOutlook.action\">联系我们</a></span></li>");
		request.getSession().setAttribute("wapMenu", list);
		return INPUT;
	}
	
	//===================================关于上风===========================================================
	
	/**
	 * @content 关于
	 * @return
	 */
	@Action(value="wapAbout",results={@Result(name="input",location="/userPortal/about/index.jsp")})
	public String wapAbout(){
		List<About> list=hibernateDao.getAll(About.class);
		if(list.size() > 0){
			About about=list.get(0);
			request.setAttribute("about", about);
		}
		return INPUT;
	}
	
	/**
	 * @content 荣誉
	 * @return
	 */
	@Action(value="wapHonor",results={@Result(name="input",location="/userPortal/about/honor.jsp")})
	public String wapHonor(){
		int countNumber = allDao.listObjecrCount(" select count(t.id) from Honor t where 1= 1");
		StringBuffer hql = new StringBuffer(" from Honor t where 1= 1");
		super.pageMethod(countNumber);
		list = allDao.listObjecr(hql.toString(), this.getPage(), this.getRows());
		this.setPageurl("/userportal/wapHonor");
		return INPUT;
	}
	
	//===================================投资者关系===========================================================
	
	/**
	 * @content 投资者关系首页
	 */
	@Action(value="wapInvestor" ,results={@Result(name="input",location="/userPortal/investor/index.jsp")})
	public String wapInvestor() {
		list = allDao.listObjecr(" from CompanyInfo t where 1=1 order by t.inputTime desc", 0, 6);
		List list2 = allDao.listObjecr(" from InvestmentInfo t where 1=1 order by t.inputTime desc", 0, 6);
		List list3 = allDao.listObjecr(" from Executives t where 1=1 order by t.inputTime desc", 0, 6);
		Object object = null;
		if(list3.size()>0){
			object = list3.get(0);
		}
		request.setAttribute("object", object);
		request.setAttribute("list2", list2);
		return INPUT;
	}
	
	/**
	 * @content 公司公告
	 */
	@Action(value="wapCompanyInfo" ,results={@Result(name="input",location="/userPortal/investor/notice.jsp")})
	public String wapCompanyInfo() {
		int countNumber = allDao.listObjecrCount(" select count(t.id) from CompanyInfo t where 1= 1");
		StringBuffer hql = new StringBuffer(" from CompanyInfo t where 1= 1");
		super.pageMethod(countNumber);
		list = allDao.listObjecr(hql.toString(), this.getPage(), this.getRows());
		this.setPageurl("/userportal/wapCompanyInfo");
		return INPUT;
	}
	
	/**
	 * @content 高管
	 */
	@Action(value="wapExecutives" ,results={@Result(name="input",location="/userPortal/investor/manager.jsp")})
	public String wapExecutives() {
		int countNumber = allDao.listObjecrCount(" select count(t.id) from Executives t where 1= 1");
		StringBuffer hql = new StringBuffer(" from Executives t where 1= 1");
		super.pageMethod(countNumber);
		list = allDao.listObjecr(hql.toString(), this.getPage(), this.getRows());
		this.setPageurl("/userportal/wapExecutives");
		return INPUT;
	}
	
	/**
	 * @content 高管详细
	 */
	@Action(value="wapExecutivesDetail",results={@Result(name="input",location="/userPortal/investor/de_manager.jsp")})
	public String wapExecutivesDetail(){
		Executives executives = hibernateDao.get(Executives.class, id);
		request.setAttribute("object", executives);
		return INPUT;
	}
	
	/**
	 * @content 联系方式
	 * @return
	 */
	@Action(value="wapContact",results={@Result(name="input",location="/userPortal/investor/contact.jsp")})
	public String wapContact(){
		List<Contact> list=hibernateDao.getAll(Contact.class);
		if(list.size() > 0){
			Contact contact=list.get(0);
			request.setAttribute("object", contact);
		}
		return INPUT;
	}
	
	//===================================产业介绍===========================================================
	/**
	 * @content 产业
	 */
	@Action(value="wapIndustry" ,results={@Result(name="input",location="/userPortal/industry/gdwq.jsp")})
	public String wapIndustry() {
		list = allDao.listObjecr(" from Industry t where t.type=1", this.getPage(), this.getRows());
		List list2 = allDao.listObjecr(" from Industry t where t.type=2", this.getPage(), this.getRows());
		Industry industry = null;
		if(type==0){
			type = 1;
		}
		if(type == 1) {
			if(list.size()>0){
				industry = (Industry)list.get(0);
			}
		}else {
			if(list.size()>0){
				industry = (Industry)list2.get(0);
			}
		}
		request.setAttribute("list2", list2);
		request.setAttribute("object", industry);
		return INPUT;
	}
	
	/**
	 * @content 产业详细
	 */
	@Action(value="wapIndustryDetail",results={@Result(name="input",location="/userPortal/industry/fan_about.jsp")})
	public String wapIndustryDetail(){
		list = allDao.listObjecr(" from Industry t where t.type=1", this.getPage(), this.getRows());
		List list2 = allDao.listObjecr(" from Industry t where t.type=2", this.getPage(), this.getRows());
		Industry industry = hibernateDao.get(Industry.class, id);
		request.setAttribute("object", industry);
		request.setAttribute("list2", list2);
		return INPUT;
	}
	
	//===================================产品===========================================================
	/**
	 * @content 产品
	 */
	@Action(value="wapProduct" ,results={@Result(name="input",location="/userPortal/product/index.jsp")})
	public String wapProduct() {
		if(type<=0){
			type = 1;
		}
		int countNumber = allDao.listObjecrCount(" select count(t.id) from Product t where t.type="+type);
		StringBuffer hql = new StringBuffer(" from Product t where t.type="+type);
		super.pageMethod(countNumber);
		list = allDao.listObjecr(hql.toString(), this.getPage(), this.getRows());
		this.setPageurl("/userportal/wapProduct");
		return INPUT;
	}
	
	/**
	 * @content 高管详细
	 */
	@Action(value="wapProductDetail",results={@Result(name="input",location="/userPortal/product/detail.jsp")})
	public String wapProductDetail(){
		Product product = hibernateDao.get(Product.class, id);
		request.setAttribute("object", product);
		return INPUT;
	}
	
	//===================================人才===========================================================
	/**
	 * @content 理念
	 * @return
	 */
	@Action(value="wapConcept",results={@Result(name="input",location="/userPortal/hr/index.jsp")})
	public String wapConcept(){
		list=hibernateDao.getAll(Concept.class);
		if(list.size() > 0){
			Concept concept=(Concept)list.get(0);
			request.setAttribute("object", concept);
		}
		return INPUT;
	}
	
	/**
	 * @content 招聘
	 */
	@Action(value="wapJob" ,results={@Result(name="input",location="/userPortal/hr/job.jsp")})
	public String wapJob() {
		int countNumber = allDao.listObjecrCount(" select count(t.id) from Job t where 1= 1");
		StringBuffer hql = new StringBuffer(" from Job t where 1= 1");
		super.pageMethod(countNumber);
		list = allDao.listObjecr(hql.toString(), this.getPage(), this.getRows());
		this.setPageurl("/userportal/wapJob");
		return INPUT;
	}
	
	/**
	 * @content 招聘详细
	 */
	@Action(value="wapJobDetail",results={@Result(name="input",location="/userPortal/hr/de_job.jsp")})
	public String wapJobDetail(){
		Job job = hibernateDao.get(Job.class, id);
		request.setAttribute("object", job);
		return INPUT;
	}
	
	/**
	 * @content 联系方式
	 * @return
	 */
	@Action(value="wapJobContact",results={@Result(name="input",location="/userPortal/hr/index.jsp")})
	public String wapJobContact(){
		list=hibernateDao.getAll(JobContact.class);
		if(list.size() > 0){
			JobContact jobContact=(JobContact)list.get(0);
			request.setAttribute("object", jobContact);
		}
		return INPUT;
	}
	
	/**
	 * @content 活动
	 */
	@Action(value="wapActivitie" ,results={@Result(name="input",location="/userPortal/hr/activity.jsp")})
	public String wapActivitie() {
		int countNumber = allDao.listObjecrCount(" select count(t.id) from Activitie t where 1= 1");
		StringBuffer hql = new StringBuffer(" from Activitie t where 1= 1");
		super.pageMethod(countNumber);
		list = allDao.listObjecr(hql.toString(), this.getPage(), this.getRows());
		this.setPageurl("/userportal/wapActivitie");
		return INPUT;
	}
	
	/**
	 * @content 活动详细
	 */
	@Action(value="wapActivitieDetail",results={@Result(name="input",location="/userPortal/hr/de_job.jsp")})
	public String wapActivitieDetail(){
		Activitie activitie = hibernateDao.get(Activitie.class, id);
		request.setAttribute("object", activitie);
		return INPUT;
	}
	
	/**
	 * @content 风采
	 */
	@Action(value="wapStaffPresence" ,results={@Result(name="input",location="/userPortal/hr/mien.jsp")})
	public String wapStaffPresence() {
		int countNumber = allDao.listObjecrCount(" select count(t.id) from StaffPresence t where 1= 1");
		StringBuffer hql = new StringBuffer(" from StaffPresence t where 1= 1");
		super.pageMethod(countNumber);
		list = allDao.listObjecr(hql.toString(), this.getPage(), this.getRows());
		this.setPageurl("/userportal/wapStaffPresence");
		return INPUT;
	}
	
	/**
	 * @content 风采详细
	 */
	@Action(value="wapStaffPresenceDetail",results={@Result(name="input",location="/userPortal/hr/de_job.jsp")})
	public String wapStaffPresenceDetail(){
		StaffPresence staffPresence = hibernateDao.get(StaffPresence.class, id);
		request.setAttribute("object", staffPresence);
		return INPUT;
	}
	
	//===================================联系我们===========================================================
	
	/**
	 * @content 前台
	 * @return
	 */
	@Action(value="wapCompanyOutlook",results={@Result(name="input",location="/userPortal/contact/index.jsp")})
	public String wapCompanyOutlook(){
		list=hibernateDao.getAll(CompanyOutlook.class);
		if(list.size() > 0){
			CompanyOutlook companyOutlook=(CompanyOutlook)list.get(0);
			request.setAttribute("object", companyOutlook);
		}
		return INPUT;
	}
	
	/**
	 * @content 前台详细
	 */
	@Action(value="wapCompanyOutlookDetail",results={@Result(name="input",location="/userPortal/contact/index.jsp")})
	public String wapCompanyOutlookDetail(){
		list=hibernateDao.getAll(CompanyOutlook.class);
		CompanyOutlook companyOutlook = hibernateDao.get(CompanyOutlook.class, id);
		request.setAttribute("object", companyOutlook);
		return INPUT;
	}
	
	/**
	 * @content 营地
	 * @return
	 */
	@Action(value="wapCamp",results={@Result(name="input",location="/userPortal/contact/zj.jsp")})
	public String wapCamp(){
		list=hibernateDao.getAll(Camp.class);
		if(list.size() > 0){
			Camp camp=(Camp)list.get(0);
			request.setAttribute("object", camp);
		}
		return INPUT;
	}
	
	/**
	 * @content 营地详细
	 */
	@Action(value="wapCampDetail",results={@Result(name="input",location="/userPortal/contact/index.jsp")})
	public String wapCampDetail(){
		list=hibernateDao.getAll(CompanyOutlook.class);
		Camp camp = hibernateDao.get(Camp.class, id);
		request.setAttribute("object", camp);
		return INPUT;
	}
}
