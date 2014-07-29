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
	if($.trim($("#ct_title").val())==""){
		alert("系统标题(繁体)不能为空!");
		$("#ct_title").focus();
     	return;
	}
	if($.trim($("#en_title").val())==""){
		alert("系统标题(英文)不能为空!");
     	$("#en_title").focus();
     	return;
	}
	if($.trim($("#cn_title").val())==""){
		alert("系统标题(简体)不能为空!");
     	$("#cn_title").focus();
     	return;
	}
	
	/* 内容 */
	if($.trim($("#ct_content").val())==""){
		alert("公告内容(繁体)不能为空!");
		$("#ct_content").focus();
     	return;
	}
	if($.trim($("#en_content").val())==""){
		alert("公告内容(英文)不能为空!");
     	$("#en_content").focus();
     	return;
	}
	if($.trim($("#cn_content").val())==""){
		alert("公告内容(简体)不能为空!");
     	$("#cn_content").focus();
     	return;
	}
	var id=$.trim($("#id").val());
	var dc = Form.getData(document.getElementById("addObject"));
	$.post("${ctx }/systemInfo/objectSave.action",dc.toJSON(),function(response){
		if(response==1){
			alert("提交成功!");
			$("#ct_title").attr("value","");
			$("#en_title").attr("value","");
			$("#cn_title").attr("value","");
			
			$("#ct_content").val("");
			$("#en_content").val("");
			$("#cn_content").val("");
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
	<div class="right01"><img src="${ctx }/images/04.gif" /> 卡卡货车系统 &gt; <span>系统公告录入</span></div>
	<div class="main">
	   <div class="bar"></div>
	   <form id="addObject" method="post">
	   <table width="80%" border="0" cellpadding="0" cellspacing="0" class="table">
	   		<input type="hidden" name="systemInfo.id" id="id" value="${systemInfo.id }">
	      <tr>
	      	<td width="120" align="right">系统标题(繁体):</td>
	      	<td><input type="text" class="inputClass" style="width:300px;" id="ct_title" name="systemInfo.ct_title" value="${systemInfo.ct_title }" /></td>
	      </tr>
	      <tr>
	      	<td width="120" align="right">系统标题(英文):</td>
	      	<td><input type="text" class="inputClass" style="width:300px;" id="en_title" name="systemInfo.en_title" value="${systemInfo.en_title }" /></td>
	      </tr>
	      <tr>
	      	<td width="120" align="right">系统标题(简体):</td>
	      	<td><input type="text" class="inputClass" style="width:300px;" id="cn_title" name="systemInfo.cn_title" value="${systemInfo.cn_title }" /></td>
	      </tr>
	      <tr>
	      	<td width="120" align="right">公告内容(繁体):</td>
	      	<td><textarea  class="xheditor" style="width:800px; height: 200px;" id="ct_content" name="systemInfo.ct_content" >${systemInfo.ct_content }</textarea></td>
	      </tr>
	      <tr>
	      	<td width="120" align="right">公告内容(英文):</td>
	      	<td><textarea  class="xheditor" style="width:800px; height: 200px;" id="en_content" name="systemInfo.en_content" >${systemInfo.en_content }</textarea></td>
	      </tr>
	      <tr>
	      	<td width="120" align="right">公告内容(简体):</td>
	      	<td><textarea  class="xheditor" style="width:800px; height: 200px;" id="cn_content" name="systemInfo.cn_content" >${systemInfo.cn_content }</textarea></td>
	      </tr>
	      <tr>
	        <td colspan="2"><input type="button" value=" 提 交 " class="buttonClass" onclick="addObject();"/></td>
	      </tr>
	    </table>
		</form>
	</div>
</body>
</html>