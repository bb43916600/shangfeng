<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<div class="page_number">
<span>共有 <s:property value="#request.pagecount"/> 条记录，当前第 <s:property value="#request.currpage"/>/<s:property value="#request.totalPage"/> 页</span>
        
    <!--如果 有上一页 -->
    <s:if test="#request.currpage>1">
    	<a href="<s:property value="#request.pagename"/>?currpage=1<s:property value="#request.sta"/>">首页</a>
    	<a href="<s:property value="#request.pagename"/>?currpage=<s:property value="#request.currpage-1"/><s:property value="#request.sta"/>">上一页</a>
    </s:if>
    <!-- 如果 有下一页 -->
    <s:if test="#request.currpage*1<#request.totalPage*1">
    	<a href="<s:property value="#request.pagename"/>?currpage=<s:property value="#request.currpage+1"/><s:property value="#request.sta"/>">下一页</a>
    	<a href="<s:property value="#request.pagename"/>?currpage=<s:property value="#request.totalPage"/><s:property value="#request.sta"/>">尾页</a>
    </s:if>
  <input name="txtgoPageNo" id="txtgoPageNo" type="text" size="6" style="height:14px; width:20px; border:1px solid #999999;color: red;font-size:11px"  /> 
  <a id="goPage" href="javascript:goPage('<s:property value="#request.sta"/>');">转</a>
	                  
 </div>  
        
<script type="text/javascript">
 function goPage(sta)
 {
    var pageNO = document.getElementById("txtgoPageNo").value;
 	if(isNaN(pageNO)||pageNO==""||pageNO>${totalPage}||pageNO==0)
       {
           alert("请正确填写您要到达的页面！");
           document.getElementById("txtgoPageNo").select();
           document.getElementById("txtgoPageNo").value=${currpage};
           document.getElementById("txtgoPageNo").focus();
       }else{
 			window.location.href="${pagename}?currpage="+pageNO+sta;
 		}
 }
</script>

