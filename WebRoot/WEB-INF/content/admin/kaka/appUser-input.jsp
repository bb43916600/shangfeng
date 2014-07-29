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
	/* 标题 */
	if($.trim($("#carNumber").val())==""){
		alert("车牌不能为空!");
     	$("#carNumber").focus();
     	return;
	}
 	if($.trim($("#phone").val())==""){
		alert("电话号码不能不为!");
		$("#phone").focus();
     	return;
	}
	
	if($.trim($("#passWorld").val())==""){
		alert("密码不能为空!");
     	$("#passWorld").focus();
     	return;
	}
	
	if($.trim($("#passWorld").val()) != $.trim($("#passWorld1").val())){
		alert("2次输入的密码不一样!");
     	$("#passWorld").focus();
     	return;
	} 
	
	/* 内容 */
/* 	if($.trim($("#ct_content").val())==""){
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
	} */
	var id=$.trim($("#id").val());
	var dc = Form.getData(document.getElementById("addObject"));
	$.post("${ctx }/appUser/objectSave.action",dc.toJSON(),function(response){
		if(response==1){
			alert("提交成功!");
			$("#carNumber").attr("value","");
			$("#phone").attr("value","");
			$("#userName").attr("value","");
			$("#introducerPhone").attr("value","");
			$("#carType").attr("value","");
			$("#vanCarType").attr("value","");
			/* $("#factoryDate").attr("value",""); */
			$("#passWorld").attr("value","");
			$("#passWorld1").attr("value","");
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
	<div class="right01"><img src="${ctx }/images/04.gif" /> 卡卡货车系统 &gt; <span>添加司机</span></div>
	<div class="main">
	   <div class="bar"></div>
	   <form id="addObject" method="post">
	   <table width="80%" border="0" cellpadding="0" cellspacing="0" class="table">
	   		<input type="hidden" name="appUser.id" id="id" value="${appUser.id }">
	   		<c:if test="${appUser.id eq null }">
	      <tr>
	      	<td width="120" align="right">车牌号码:</td>
	      	<td><input type="text" class="inputClass" style="width:300px;" id="carNumber" name="appUser.carNumber" value="${appUser.carNumber }" /></td>
	      </tr>
	      <tr>
	      	<td width="120" align="right">手机号码:</td>
	      	<td><input type="text" class="inputClass" style="width:300px;" id="phone" name="appUser.phone" value="${appUser.phone }" /></td>
	      </tr>
	      <tr>
	      	<td width="120" align="right">用户呢称:</td>
	      	<td><input type="text" class="inputClass" style="width:300px;" id="userName" name="appUser.userName" value="${appUser.userName }" /></td>
	      </tr>
		  <tr>
	      	<td width="120" align="right">介绍人号码:</td>
	      	<td><input type="text" class="inputClass" style="width:300px;" id="introducerPhone" name="appUser.introducerPhone" value="${appUser.introducerPhone }" /></td>
	      </tr>
	      <tr>
	      	<td width="120" align="right">车种类:</td>
	      	<td><input type="text" class="inputClass" style="width:300px;" id="carType" name="appUser.carType" value="${appUser.carType }" /></td>
	      </tr>
	      <tr>
	      	<td width="120" align="right">Van仔车类型:</td>
	      	<td><input type="text" class="inputClass" style="width:300px;" id="vanCarType" name="appUser.vanCarType" value="${appUser.vanCarType }" /></td>
	      </tr>
<%-- 	      <tr>
	      	<td width="120" align="right">出厂日期:</td>
	      	<td><input type="text" class="inputClass" style="width:300px;" id="factoryDate" name="appUser.factoryDate" value="${appUser.factoryDate }" /></td>
	      </tr> --%>
	      </c:if>
	      <c:if test="${appUser.id ne null }">
	      		<input type="hidden" class="inputClass" style="width:300px;" id="carNumber" name="appUser.carNumber" value="${appUser.carNumber }" />
	      		<input type="hidden" class="inputClass" style="width:300px;" id="phone" name="appUser.phone" value="${appUser.phone }" />
	      		<input type="hidden" class="inputClass" style="width:300px;" id="userName" name="appUser.userName" value="${appUser.userName }" />
	      		<input type="hidden" class="inputClass" style="width:300px;" id="introducerPhone" name="appUser.introducerPhone" value="${appUser.introducerPhone }" />
	      		<input type="hidden" class="inputClass" style="width:300px;" id="carType" name="appUser.carType" value="${appUser.carType }" />
	      		<input type="hidden" class="inputClass" style="width:300px;" id="vanCarType" name="appUser.vanCarType" value="${appUser.vanCarType }" />
	      </c:if>
	      <tr>
	      	<td width="120" align="right">密码:</td>
	      	<td><input type="text" class="inputClass" style="width:300px;" id="passWorld" name="appUser.passWorld"  /></td>
	      </tr>
	      <tr>
	      	<td width="120" align="right">确认密码:</td>
	      	<td><input type="text" class="inputClass" style="width:300px;" id="passWorld1"  /></td>
	      </tr>
	      <tr>
	        <td colspan="2"><input type="button" value=" 提 交 " class="buttonClass" onclick="addObject();"/></td>
	      </tr>
	    </table>
		</form>
	</div>
</body>
</html>