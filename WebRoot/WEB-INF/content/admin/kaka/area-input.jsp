<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>菜单-录入</title>
<link type="text/css" href="${ctx }/css/style.css" rel="stylesheet"  />
<script type="text/javascript" src="${ctx }/js/jquery-1.4.2.min.js" ></script>
<script type="text/javascript" src="${ctx }/js/common.js" ></script>
<script type="text/javascript" src="${ctx }/js/zDialog.js"></script>
<script type="text/javascript" src="${ctx }/js/zDrag.js"></script>
<script type="text/javascript">
function addObject(){
	if($.trim($("#ct_areaName").val())==""){
		alert("地区名称(繁体)不能为空!");
		$("#ct_areaName").focus();
     	return;
	}
	if($.trim($("#en_areaName").val())==""){
		alert("地区名称(英文)不能为空!");
     	$("#en_areaName").focus();
     	return;
	}
	if($.trim($("#cn_areaName").val())==""){
		alert("地区名称(简体)不能为空!");
     	$("#cn_areaName").focus();
     	return;
	}
	var id=$.trim($("#id").val());
	var dc = Form.getData(document.getElementById("addObject"));
	$.post("${ctx }/area/objectSave.action",dc.toJSON(),function(response){
		if(response==1){
			alert("提交成功!");
			$("#ct_areaName").attr("value","");
			$("#en_areaName").attr("value","");
			$("#cn_areaName").attr("value","");
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
	<div class="right01"><img src="${ctx }/images/04.gif" /> 卡卡货车系统 &gt; <span>地区录入</span></div>
	<div class="main">
	   <div class="bar"></div>
	   <form id="addObject" method="post">
	   <table width="80%" border="0" cellpadding="0" cellspacing="0" class="table">
	   		<input type="hidden" name="area.id" id="id" value="${area.id }">
	      <tr>
	      	<td width="120" align="right">地区名称(繁体):</td>
	      	<td><input type="text" class="inputClass" style="width:300px;" id="ct_areaName" name="area.ct_areaName" value="${area.ct_areaName }" /></td>
	      </tr>
	      <tr>
	      	<td width="120" align="right">地区名称(英文):</td>
	      	<td><input type="text" class="inputClass" style="width:300px;" id="en_areaName" name="area.en_areaName" value="${area.en_areaName }" /></td>
	      </tr>
	      <tr>
	      	<td width="120" align="right">地区名称(简体):</td>
	      	<td><input type="text" class="inputClass" style="width:300px;" id="cn_areaName" name="area.cn_areaName" value="${area.cn_areaName }" /></td>
	      </tr>
	      <tr>
	        <td colspan="2"><input type="button" value=" 提 交 " class="buttonClass" onclick="addObject();"/></td>
	      </tr>
	    </table>
		</form>
	</div>
</body>
</html>