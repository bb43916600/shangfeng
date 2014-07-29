<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>权限-录入</title>
<link type="text/css" href="${ctx }/css/style.css" rel="stylesheet"  />
<script type="text/javascript" src="${ctx }/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="${ctx }/js/common.js"></script>
<script type="text/javascript" src="${ctx }/js/zDialog.js"></script>
<script type="text/javascript" src="${ctx }/js/zDrag.js"></script>
<script type="text/javascript">
	function checkform(){
		if($.trim($("#name").val())==""){
			alert("权限名称不能为空!");
	     	$("#name").focus();
	     	return;
		}
		var dc = Form.getData(document.getElementById("saveOperation"));
		$.post("${ctx }/authority/saveOperation.action",dc.toJSON(),function(response){
			if(response==1){
				alert("添加成功!");
				$("#name").attr("value","");
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
	<div class="right01"><img src="${ctx }/images/04.gif" /> 权限管理 &gt; <span>权限录入</span></div>
	<div class="main">
	   <div class="bar"></div>
	   <form id="saveOperation" method="post">
	   <table width="80%" border="0" cellpadding="0" cellspacing="0" class="table">
	   		<input type="hidden" name="operation.id" id="id" value="${operation.id }"/>
	      <tr>
	      	<td width="120" align="right">权限名称:</td>
	      	<td><input type="text" class="inputClass" style="width:300px;" id="name" name="operation.name" value="${operation.name }" /></td>
	      </tr>
	      <tr>
	        <td colspan="2"><input type="button" value="添加权限" class="buttonClass" onclick="checkform();"/></td>
	      </tr>
	    </table>
		</form>
	</div>
</body>
</html>