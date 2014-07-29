<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>栏目-录入</title>
<link type="text/css" href="${ctx }/css/style.css" rel="stylesheet"  />
<script type="text/javascript" src="${ctx }/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="${ctx }/js/common.js"></script>
<script type="text/javascript">
	function checkform(){
		if($.trim($("#userAccount").val())==""){
			alert("帐号不能为空！");
	     	$("#userAccount").focus();
	     	return;
		}
		if($.trim($("#userPassWord").val())==""){
			alert("密码不能为空！");
	     	$("#userPassWord").focus();
	     	return;
		}
		if($.trim($("#userPassWord1").val())==""){
			alert("密码不能为空！");
	     	$("#userPassWord1").focus();
	     	return;
		}
		if($.trim($("#userPassWord").val())<=6){
			alert("密码的成都必须大于6位！");
	     	$("#userPassWord").focus();
	     	return;
		}
		if($.trim($("#userPassWord").val())!=$.trim($("#userPassWord1").val())){
			alert("两次输入的密码不一样！");
	     	$("#userPassWord").focus();
	     	return;
		}
		if($.trim($("#userPhone").val())==""){
			alert("用户手机号码不能为空！！");
	     	$("#userPhone").focus();
	     	return;
		}
		if($.trim($("#userIDCard").val())==""){
			alert("身份证不能为空！");
	     	$("#userIDCard").focus();
	     	return;
		}
		/* if($.trim($("#userIDCard").val())<18 ||$.trim($("#userIDCard").val())>18){
			alert("身份证的长度为18位，请正确录入！");
	     	$("#userIDCard").focus();
	     	return;
		} */
		var id=$.trim($("#id").val());
		var dc = Form.getData(document.getElementById("saveUser"));
		$.post("${ctx }/authority/saveUser.action",dc.toJSON(),function(response){
			if(response==1){
				alert("添加成功!");
				$("#userAccount").attr("value","");
				$("#userPassWord").attr("value","");
				$("#userPassWord1").attr("value","");
				$("#userNickname").attr("value","");
				$("#userPhone").attr("value","");
				$("#userIDCard").attr("value","");
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
	<div class="right01"><img src="${ctx }/images/04.gif" /> 权限管理 &gt; <span>新建用户</span></div>
	<div class="main">
	   <div class="bar"></div>
	   <form id="saveUser" method="post">
	   <table width="80%" border="0" cellpadding="0" cellspacing="0" class="table">
	   		<input type="hidden" name="id" id="id" value="${user2.id }"/>
	      <tr>
	      	<td width="120" align="right">用户名称:</td>
	      	<td><input type="text" class="inputClass" style="width:300px;" id="userAccount" name="userAccount" value="${user2.userAccount }" /></td>
	      </tr>
	      <tr>
	      	<td width="120" align="right">用户密码:</td>
	      	<td><input type="password" class="inputClass" style="width:300px;" id="userPassWord" name="userPassWord" value="${user2.userPassWord }" /></td>
	      </tr>
	      <tr>
	      	<td width="120" align="right">确认密码:</td>
	      	<td><input type="password" class="inputClass" style="width:300px;" id="userPassWord1" name="userPassWord1" /></td>
	      </tr>
	      <tr>
	      	<td width="120" align="right">用户昵称:</td>
	      	<td><input type="text" class="inputClass" style="width:300px;" id="userNickname" name="userNickname" value="${user2.userNickname }" /></td>
	      </tr>
	      <tr>
	      	<td width="120" align="right">用户电话:</td>
	      	<td><input type="text" class="inputClass" style="width:300px;" onkeyup="onlyNum(this);" id="userPhone" name="userPhone" value="${user2.userPhone }" /></td>
	      </tr>
	      <tr>
	      	<td width="120" align="right">用户身份证:</td>
	      	<td><input type="text" class="inputClass" style="width:300px;" id="userIDCard" name="userIDCard" value="${user2.userIDCard }" /></td>
	      </tr>
	      <tr>
	        <td colspan="2"><input type="button" value="提  交" class="buttonClass" onclick="checkform();"/></td>
	      </tr>
	    </table>
		</form>
	</div>
</body>
</html>