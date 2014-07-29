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
	var id=$.trim($("#id").val());
	if(id!=0 && id!=""){
		ownerDialog.parentHelp.freshGrid();
		ownerDialog.close();
	}
	
}
</script>
</head>
<body>
	<div class="right01"><img src="${ctx }/images/04.gif" /> 上风实业 &gt; <span>荣誉录入</span></div>
	<div class="main">
	   <div class="bar"></div>
	   <form method="post" id="bbForm"  action="${ctx }/about/honorSave.action" enctype="multipart/form-data" onsubmit="return addObject();" >
	   <table width="80%" border="0" cellpadding="0" cellspacing="0" class="table">
	   		<input type="hidden" name="honor.id" id="id" value="${honor.id }">
	      <tr>
	      	<td width="120" align="right">荣誉名称:</td>
	      	<td><input type="text" class="inputClass" style="width:300px;" id="title" name="honor.title" value="${honor.title }" /></td>
	      </tr>
	      <c:if test="${honor.id eq null }">
	      <tr>
        	<td align="right">荣誉图片：</td>
        	<td><input type="file" class="formFile" style="width:310px;margin-left:5px;" name="file" id="file" value="${honor.images }" /></td>
      	  </tr>
      	  </c:if>
      	  <c:if test="${honor.id ne null }">
	       <input type="hidden" name="honor.images" id="images" value="${honor.images }" />
	     </c:if>
	      <tr>
	      	<td width="120" align="right">荣誉介绍:</td>
	      	<td><textarea  class="xheditor" style="width:100%; height: 200px;" id="content" name="honor.content" >${honor.content }</textarea></td>
	      </tr>
	      <tr>
	        <td colspan="2"><input type="submit" value="提  交" class="buttonClass" /></td> <!-- <input type="button" value=" 提 交 " class="buttonClass" onclick="addObject();"/> -->
	      </tr>
	    </table>
		</form>
	</div>
</body>
</html>