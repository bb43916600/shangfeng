<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<div class="head">
                         <div class="logo"><a href="${ctx }/userportal/wapIndex.action"><img src="${ctx }/userPortal/images/logo.jpg" width="356" height="58"></a></div>
                         
                         <div class="head_top">
                          <a href="#" class="a1">董事长信箱</a>
                          <a href="#" class="a1">董秘信箱</a> | 
                           <a href="#">OA系统登录</a>  |    <a href="#">公司邮箱登录</a>
                         </div>
                         
                         <div class="cls"></div>
                         
                         <div class="nav">
                         <ul class="navlist">
                         	<c:forEach items="${wapMenu }" var="objects">
                         		${objects }
                         	</c:forEach>
                         </ul>
                         
                         </div>
                         
                         
                     
                     </div>