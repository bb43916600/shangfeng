<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>菜单-录入</title>
<link type="text/css" href="${ctx }/css/style.css" rel="stylesheet"  />
<script type="text/javascript" src="${ctx }/js/jquery-1.4.2.min.js" ></script>
<script type="text/javascript" src="${ctx}/xheditor-1.1.14/xheditor-1.1.14-zh-cn.min.js"></script>
<script type="text/javascript" src="${ctx }/js/common.js" ></script>
<script type="text/javascript" src="${ctx }/js/zDialog.js"></script>
<script type="text/javascript" src="${ctx }/js/zDrag.js"></script>
<script type="text/javascript">
function addObject(){
	/* 标题 */
	if($.trim($("#title").val())==""){
		alert("标题不能为空!");
		$("#title").focus();
     	return;
	}
	/* 内容 */
	if($.trim($("#content").val())==""){
		alert("内容不能为空!");
		$("#content").focus();
     	return;
	}
	
}
</script>
</head>
<body>
	<div class="right01"><img src="${ctx }/images/04.gif" /> 上风实业 &gt; <span>高管录入</span></div>
	<div class="main">
	   <div class="bar"></div>
	   <form method="post" id="bbForm"  action="${ctx }/investment/executivesSave.action" enctype="multipart/form-data" onsubmit="return addObject();" >
	   <table width="80%" border="0" cellpadding="0" cellspacing="0" class="table">
	   		<input type="hidden" name="executives.id" id="id" value="${executives.id }">
	      <tr>
	      	<td width="120" align="right">标题:</td>
	      	<td><input type="text" class="inputClass" style="width:300px;" id="title" name="executives.title" value="${executives.title }" /></td>
	      </tr>
	      <c:if test="${executives.id eq null }">
	      <tr>
        	<td align="right">高管头像：</td>
        	<td><input type="file" class="formFile" style="width:310px;margin-left:5px;" name="file" id="file" value="${executives.images }" /></td>
      	  </tr>
      	  </c:if>
      	  <c:if test="${executives.id ne null }">
	       <input type="hidden" name="executives.images" id="images" value="${executives.images }" />
	     </c:if>
	      <tr>
	      	<td width="120" align="right">高管介绍:</td>
	      	<td><textarea  class="xheditor" style="width:100%; height: 200px;" id="content" name="executives.content" >${executives.content }</textarea></td>
	      </tr>
	      <tr>
	        <td colspan="2"><input type="submit" value="提  交" class="buttonClass" /> <input type="button" value=" 提 交 " class="buttonClass" onclick="addObject();"/></td>
	      </tr>
	    </table>
		</form>
	</div>
</body>
</html>