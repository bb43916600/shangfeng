package com.bb2004.common;


import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionSupport;



@SuppressWarnings("serial")
public abstract class CrudActionSupport extends ActionSupport {

	
	/** 进行增删改操作后,以redirect方式重新打开action默认页的result名.*/
	protected HttpServletRequest request =ServletActionContext.getRequest();
	protected HttpServletResponse response =ServletActionContext.getResponse();
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	/***********和jqGrid组件相关的参数属性*******************/
	// 每页中现实的记录数
	private Integer rows = 15;
	private Integer page = 1;// 当前页码
	private Integer total = 0;// 总页数
	private Integer record = 0;// 总记录数
	private String sord;// 排序的方式
	private String sidx;// 用于排序的列名
	private String search;// 是否是用于查询的请求
	private String pageurl;//URL条件
	
	public String getPageurl() {
		return pageurl;
	}
	public void setPageurl(String pageurl) {
		this.pageurl = pageurl;
	}
	public Integer getRows() {
		return rows;
	}
	public void setRows(Integer rows) {
		this.rows = rows;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public Integer getRecord() {
		return record;
	}
	public void setRecord(Integer record) {
		this.record = record;
	}
	public String getSord() {
		return sord;
	}
	public void setSord(String sord) {
		this.sord = sord;
	}
	public String getSidx() {
		return sidx;
	}
	public void setSidx(String sidx) {
		this.sidx = sidx;
	}
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
	
	/**
	 * 解决android客户端乱码问题
	 * @param key
	 * @return
	 */
	public String getParameter(String key){
		String param = request.getParameter(key);
		try {
			if(param!=null&&param.length()>0){
				return URLDecoder.decode(param, "utf-8");
			}else{
				return param;
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return param;
		}
	}
	
	/**
	 * 解决android客户端乱码问题
	 * @param key
	 * @return
	 */
	public String[] getParameterValues(String key){
		String [] params = request.getParameterValues(key);
		if(params!=null && params.length==1){		// 长度为1
			try{
				if(params[0]!=null&&params[0].length()>0){
					String tmp = URLDecoder.decode(params[0], "utf-8");
					return tmp.split(",");
				}else{
					return params;
				}
			}catch(UnsupportedEncodingException e){
				return params;
			}
		}else{
			return params;
		}
		
	}
	
	/**
	 * @content 不带条件分页
	 * @param pagename 方法名称
	 * @param listcount 总数
	 * @param pagesize 显示多少条
	 * @param curpage 当前页数
	 */
	public void pageMethod(int listCount){
		try {
			if(listCount%rows>0){
				total = listCount/rows+1;
			}else{
				total = listCount/rows;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
