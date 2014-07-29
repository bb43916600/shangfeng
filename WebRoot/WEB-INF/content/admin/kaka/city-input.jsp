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
	if($.trim($("#areaId").val())==""){
		alert("所在区域不能为空!");
		$("#areaId").focus();
     	return;
	}
	if($.trim($("#ct_cityName").val())==""){
		alert("地区名称(繁体)不能为空!");
		$("#ct_cityName").focus();
     	return;
	}
	if($.trim($("#en_cityName").val())==""){
		alert("地区名称(英文)不能为空!");
     	$("#en_cityName").focus();
     	return;
	}
	if($.trim($("#cn_cityName").val())==""){
		alert("地区名称(简体)不能为空!");
     	$("#cn_cityName").focus();
     	return;
	}
	var id=$.trim($("#id").val());
	var dc = Form.getData(document.getElementById("addObject"));
	$.post("${ctx }/city/objectSave.action",dc.toJSON(),function(response){
		if(response==1){
			alert("提交成功!");
			$("#ct_cityName").attr("value","");
			$("#en_cityName").attr("value","");
			$("#cn_cityName").attr("value","");
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
	   		<input type="hidden" name="city.id" id="id" value="${city.id }">
	   	  <tr>
	      	<td width="120" align="right">区域:</td>
	      	<td>
	      		<select name="city.areaId" id="areaId">
					<option value=""> -- 请选择 -- </option>
					<c:forEach items="${list }" var="area">
						<option value="${area.id }" <c:if test="${city.areaId eq area.id }">selected="selected"</c:if>>${area.cn_areaName }</option>
					</c:forEach>
				</select>
	      	</td>
	      </tr>
	      <tr>
	      	<td width="120" align="right">城市名称(繁体):</td>
	      	<td><input type="text" class="inputClass" style="width:300px;" id="ct_cityName" name="city.ct_cityName" value="${city.ct_cityName }" /></td>
	      </tr>
	      <tr>
	      	<td width="120" align="right">城市名称(英文):</td>
	      	<td><input type="text" class="inputClass" style="width:300px;" id="en_cityName" name="city.en_cityName" value="${city.en_cityName }" /></td>
	      </tr>
	      <tr>
	      	<td width="120" align="right">城市名称(简体):</td>
	      	<td><input type="text" class="inputClass" style="width:300px;" id="cn_cityName" name="city.cn_cityName" value="${city.cn_cityName }" /></td>
	      </tr>
	      <tr>
	        <td colspan="2"><input type="button" value=" 提 交 " class="buttonClass" onclick="addObject();"/></td>
	      </tr>
	    </table>
		</form>
	</div>
</body>
</html>