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
	if($.trim($("#jobName").val())==""){
		alert("职位名称不能为空!");
		$("#jobName").focus();
     	return;
	}
	/* 内容 */
	if($.trim($("#content").val())==""){
		alert("内容不能为空!");
		$("#content").focus();
     	return;
	}
	
	var id=$.trim($("#id").val());
	var dc = Form.getData(document.getElementById("addObject"));
	$.post("${ctx }/hr/jobSave.action",dc.toJSON(),function(response){
		if(response==1){
			alert("提交成功!");
			$("#jobName").attr("value","");
			$("#content").val("");
			if(id!=0 && id!=""){
				ownerDialog.parentHelp.freshGrid();
				ownerDialog.close();
			}
		}else{
			alert("操作有误!");
		}
	},"json");
}
</script>
</head>
<body>
	<div class="right01"><img src="${ctx }/images/04.gif" /> 上风实业 &gt; <span>新闻录入</span></div>
	<div class="main">
	   <div class="bar"></div>
	   <form id="addObject" method="post">
	   <table width="80%" border="0" cellpadding="0" cellspacing="0" class="table">
	   		<input type="hidden" name="job.id" id="id" value="${job.id }">
	      <tr>
	      	<td width="120" align="right">职位名称:</td>
	      	<td><input type="text" class="inputClass" style="width:300px;" id="jobName" name="job.jobName" value="${job.jobName }" /></td>
	      </tr>
	      <tr>
	      	<td width="120" align="right">职位要求描述:</td>
	      	<td><textarea  class="xheditor" style="width:100%; height: 200px;" id="content" name="job.content" >${job.content }</textarea></td>
	      </tr>
	      <tr>
	        <td colspan="2"><input type="button" value=" 提 交 " class="buttonClass" onclick="addObject();"/></td>
	      </tr>
	    </table>
		</form>
	</div>
</body>
</html>