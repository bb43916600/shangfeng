<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>角色-录入</title>
<link type="text/css" href="${ctx }/css/style.css" rel="stylesheet"  />
<script type="text/javascript" src="${ctx }/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="${ctx }/js/common.js"></script>
<script type="text/javascript">

function addRole(){
	if($.trim($("#name").val())==""){
		alert("角色名字不能为空!");
     	$("#name").focus();
     	return;
	}
	var id=$.trim($("#id").val());
	var dc = Form.getData(document.getElementById("addRole"));
	$.post("${ctx }/authority/saveRole.action",dc.toJSON(),function(response){
		if(response==1){
			alert("添加成功!");
			$("#name").attr("value","");
			if(id.length>0 && id!=""){
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
	<div class="right01"><img src="${ctx }/images/04.gif" /> 权限管理 &gt; <span>角色录入</span></div>
	<div class="main">
	   <div class="bar"></div>
	   <form id="addRole" method="post">
	   <table width="80%" border="0" cellpadding="0" cellspacing="0" class="table">
	   		<input type="hidden" name="role.id" id="id" value="${role.id }" />
	      <tr>
	      	<td width="120" align="right">角色名称:</td>
	      	<td><input type="text" class="inputClass" style="width:300px;" id="name" name="role.name" value="${role.name }" /></td>
	      </tr>
	      <tr>
	        <td colspan="2"><input type="button" value="添加角色" class="buttonClass" onclick="addRole();"/></td>
	      </tr>
	    </table>
		</form>
	</div>
</body>
</html>