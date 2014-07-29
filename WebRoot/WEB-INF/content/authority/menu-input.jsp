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
function addMenu(){
	if($.trim($("#menuKindid").val())==""){
		alert("请选择所属栏目!");
     	return;
	}
	if($.trim($("#name").val())==""){
		alert("名称不能为空!");
     	$("#name").focus();
     	return;
	}
	if($.trim($("#url").val())==""){
		alert("地址栏不能为空!");
     	$("#url").focus();
     	return;
	}
	if($.trim($("#order").val())==""){
		alert("排序编码不能为空!");
     	$("#order").focus();
     	return;
	}
	var id=$.trim($("#id").val());
	var dc = Form.getData(document.getElementById("addMenu"));
	$.post("${ctx }/authority/saveMenu.action",dc.toJSON(),function(response){
		if(response==1){
			alert("添加成功!");
			$("#name").attr("value","");
			$("#url").attr("value","");
			$("#order").attr("value","");
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
	<div class="right01"><img src="${ctx }/images/04.gif" /> 权限管理 &gt; <span>菜单录入</span></div>
	<div class="main">
	   <div class="bar"></div>
	   <form id="addMenu" method="post">
	   <table width="80%" border="0" cellpadding="0" cellspacing="0" class="table">
	   		<input type="hidden" name="menu.id" id="id" value="${menu.id }">
	      <tr>
	        <td width="120" align="right">所属栏目:</td>
	        <td>
	        	<select name="menu.menuKind.id" id="menuKindid">
					<option value=""> -- 请选择 -- </option>
					<c:forEach items="${list }" var="menukind">
						<option value="${menukind[0] }" <c:if test="${menu.menuKind.id eq menukind[0] }">selected="selected"</c:if>>${menukind[1] }</option>
					</c:forEach>
				</select>
	        </td>
	      </tr>
	      <tr>
	      	<td width="120" align="right">菜单名称:</td>
	      	<td><input type="text" class="inputClass" style="width:300px;" id="name" name="menu.name" value="${menu.name }" /></td>
	      </tr>
	      <tr>
	      	<td width="120" align="right">菜单地址:</td>
	      	<td><input type="text" class="inputClass" style="width:300px;" id="url" name="menu.url" value="${menu.url }" /></td>
	      </tr>
	      <tr>
	      	<td width="120" align="right">排序编码:</td>
	      	<td><input type="text" class="inputClass" style="width:300px;" id="order" name="menu.order" value="${menu.order }" onkeyup="onlyNum(this);" /></td>
	      </tr>
	      <tr>
	        <td colspan="2"><input type="button" value="添加菜单" class="buttonClass" onclick="addMenu();"/></td>
	      </tr>
	    </table>
		</form>
	</div>
</body>
</html>