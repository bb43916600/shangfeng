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
	/* 名称 */
	if($.trim($("#ct_otherName").val())==""){
		alert("额外需求名称(繁体)不能为空!");
		$("#ct_otherName").focus();
     	return;
	}
	if($.trim($("#en_otherName").val())==""){
		alert("额外需求名称(英文)不能为空!");
     	$("#en_otherName").focus();
     	return;
	}
	if($.trim($("#cn_otherName").val())==""){
		alert("额外需求名称(简体)不能为空!");
     	$("#cn_otherName").focus();
     	return;
	}
	/* 备注 */
	/* if($.trim($("#ct_remark").val())==""){
		alert("备注(繁体)不能为空!");
		$("#ct_remark").focus();
     	return;
	}
	if($.trim($("#en_remark").val())==""){
		alert("备注(英文)不能为空!");
     	$("#en_remark").focus();
     	return;
	}
	if($.trim($("#cn_remark").val())==""){
		alert("备注(简体)不能为空!");
     	$("#cn_remark").focus();
     	return;
	} */
	var id=$.trim($("#id").val());
	var dc = Form.getData(document.getElementById("addObject"));
	$.post("${ctx }/otherPrice/objectSave.action",dc.toJSON(),function(response){
		if(response==1){
			alert("提交成功!");
			/* 名称 */
			$("#ct_otherName").attr("value","");
			$("#en_otherName").attr("value","");
			$("#cn_otherName").attr("value","");
			/* 备注 */
			$("#ct_remark").attr("value","");
			$("#en_remark").attr("value","");
			$("#cn_remark").attr("value","");
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
	<div class="right01"><img src="${ctx }/images/04.gif" /> 卡卡货车系统 &gt; <span>额外需求录入</span></div>
	<div class="main">
	   <div class="bar"></div>
	   <form id="addObject" method="post">
	   <table width="80%" border="0" cellpadding="0" cellspacing="0" class="table">
	   		<input type="hidden" name="otherPrice.id" id="id" value="${otherPrice.id }">
	   	  <tr>
	      	<td width="120" align="right">父类:</td>
	      	<td>
	      		<select name="otherPrice.parentId" id="parentId">
					<option value=""> -- 请选择 -- </option>
					<c:forEach items="${list }" var="parent">
						<option value="${parent.id }" <c:if test="${otherPrice.id eq parent.id }">selected="selected"</c:if>>${parent.cn_otherName }</option>
					</c:forEach>
				</select>
	      	</td>
	      </tr>
	      <tr>
	      	<td width="120" align="right">额外需求名称(繁体):</td>
	      	<td><input type="text" class="inputClass" style="width:300px;" id="ct_otherName" name="otherPrice.ct_otherName" value="${otherPrice.ct_otherName }" /></td>
	      </tr>
	      <tr>
	      	<td width="120" align="right">额外需求名称(英文):</td>
	      	<td><input type="text" class="inputClass" style="width:300px;" id="en_otherName" name="otherPrice.en_otherName" value="${otherPrice.en_otherName }" /></td>
	      </tr>
	      <tr>
	      	<td width="120" align="right">额外需求名称(简体):</td>
	      	<td><input type="text" class="inputClass" style="width:300px;" id="cn_otherName" name="otherPrice.cn_otherName" value="${otherPrice.cn_otherName }" /></td>
	      </tr>
	      
	      <tr>
	      	<td width="120" align="right">备注(繁体):</td>
	      	<td><input type="text" class="inputClass" style="width:300px;" id="ct_remark" name="otherPrice.ct_remark" value="${otherPrice.ct_remark }" /></td>
	      </tr>
	      <tr>
	      	<td width="120" align="right">备注(英文):</td>
	      	<td><input type="text" class="inputClass" style="width:300px;" id="en_remark" name="otherPrice.en_remark" value="${otherPrice.en_remark }" /></td>
	      </tr>
	      <tr>
	      	<td width="120" align="right">备注(简体):</td>
	      	<td><input type="text" class="inputClass" style="width:300px;" id="cn_remark" name="otherPrice.cn_remark" value="${otherPrice.cn_remark }" /></td>
	      </tr>
	      <tr>
	      	<td width="120" align="right">额外需求价格:</td>
	      	<td><input type="text" class="inputClass" style="width:300px;" id="price" name="otherPrice.price" value="${otherPrice.price }" /></td>
	      </tr>
	      <tr>
	        <td colspan="2"><input type="button" value=" 提 交 " class="buttonClass" onclick="addObject();"/></td>
	      </tr>
	    </table>
		</form>
	</div>
</body>
</html>