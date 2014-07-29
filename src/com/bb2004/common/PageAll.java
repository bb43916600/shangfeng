package com.bb2004.common;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;

/**
 * @content 分页方法
 * @author 龙华雄
 *
 */

public class PageAll {
	
	/**
	 * @content 不带条件分页
	 * @param request 
	 * @param pagename 方法名称
	 * @param listcount 总数
	 * @param pagesize 显示多少条
	 * @param curpage 当前页数
	 */
	public static void pageMethod(HttpServletRequest request,String pagename,int listcount,int pagesize,int curpage){
		int totalpage = 0;
		try {
			if(listcount%pagesize>0){
				totalpage = listcount/pagesize+1;
			}else{
				totalpage = listcount/pagesize;
			}
			request.setAttribute("pagecount", listcount);//总条
		    request.setAttribute("currpage",curpage);//当前页数
			request.setAttribute("totalPage",totalpage);//总页数
			request.setAttribute("pagename", pagename);//当前页面名称
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @content 带条件分页
	 * @param request
	 * @param pagename 方法名称
	 * @param listcount 总数
	 * @param pagesize 显示多少条
	 * @param curpage 当前页数
	 * @param sta 查询条件
	 */
	public static void pageMethod(HttpServletRequest request,String pagename,int listcount,int pagesize,int curpage,String sta){
		int totalpage = 0;
		try {
			if(listcount%pagesize>0){
				totalpage = listcount/pagesize+1;
			}else{
				totalpage = listcount/pagesize;
			}
			if(listcount==0){
				listcount=1;
			}
			request.setAttribute("pagecount", listcount);//总条数
			request.setAttribute("currpage",curpage);//当前页数
			request.setAttribute("totalPage",totalpage);//总页数
			request.setAttribute("pagename", pagename);//当前页面名称
			request.setAttribute("sta", sta);//是否有条件的查询	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	 
	/**
	 * @content 输出流分页
	 * @param out
	 * @param listcount 总数
	 * @param pagesize 显示多少条
	 * @param curpage 当前页数
	 */
	public static void pageMethod(PrintWriter out,Long listcount,int pagesize,int curpage){
		int totalpage = 0;
		try {
			if(listcount%pagesize>0){
				totalpage = (int) (listcount/pagesize+1);
			}else{
				totalpage = (int) (listcount/pagesize);
			}
			out.write("<pagecount>"+listcount+"</pagecount>");//总条
			out.write("<currpage>"+curpage+"</currpage>");//当前
			out.write("<totalPage>"+totalpage+"</totalPage>");//总页
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
