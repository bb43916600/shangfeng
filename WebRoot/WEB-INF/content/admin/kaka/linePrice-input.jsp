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
	if($.trim($("#startCityId").val())==""){
		alert("开始城市不能为空!");
		$("#startCityId").focus();
     	return;
	}
	if($.trim($("#endCityId").val())==""){
		alert("结束城市不能为空!");
     	$("#endCityId").focus();
     	return;
	}
	if($.trim($("#price").val())==""){
		alert("价钱不能为空!");
     	$("#price").focus();
     	return;
	}
	var id=$.trim($("#id").val());
	var dc = Form.getData(document.getElementById("addObject"));
	$.post("${ctx }/linePrice/objectSave.action",dc.toJSON(),function(response){
		if(response==1){
			alert("提交成功!");
			$("#startCityId").attr("value","");
			$("#endCityId").attr("value","");
			$("#price").attr("value","");
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
	<div class="right01"><img src="${ctx }/images/04.gif" /> 卡卡货车系统 &gt; <span>线路录入</span></div>
	<div class="main">
	   <div class="bar"></div>
	   <form id="addObject" method="post">
	   <table width="80%" border="0" cellpadding="0" cellspacing="0" class="table">
	   		<input type="hidden" name="linePrice.id" id="id" value="${linePrice.id }">
	      <tr>
	      	<td width="120" align="right">开始城市:</td>
	      	<td><input type="text" class="inputClass" style="width:300px;" id="startCityId" name="linePrice.startCityId" value="${linePrice.startCityId }" /></td>
	      </tr>
	      <tr>
	      	<td width="120" align="right">结束城市:</td>
	      	<td><input type="text" class="inputClass" style="width:300px;" id="endCityId" name="linePrice.endCityId" value="${linePrice.endCityId }" /></td>
	      </tr>
	      <tr>
	      	<td width="120" align="right">价钱:</td>
	      	<td><input type="text" class="inputClass" style="width:300px;" id="price" name="linePrice.price" value="${linePrice.price }" /></td>
	      </tr>
	      <tr>
	        <td colspan="2"><input type="button" value=" 提 交 " class="buttonClass" onclick="addObject();"/></td>
	      </tr>
	    </table>
		</form>
	</div>
</body>
</html>