<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link href="${ctx }/css/rest.css" type="text/css" rel="stylesheet" />
<link href="${ctx }/css/admin.css" type="text/css" rel="stylesheet" />
<style type="text/css">
.table {border:1px solid #DDDDDC;border-collapse:collapse; font-size:11pt;}
.table td{padding:2px 5px; text-align:left;}
label,input{padding:0;margin:0;}
label{margin-right:8px;}
</style>
<script type="text/javascript" src="${ctx }/js/zDialog.js"></script>
<script type="text/javascript" src="${ctx }/js/zDrag.js"></script>
<script type="text/javascript" src="${ctx }/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="${ctx }/js/common.js"></script>
<script type="text/javascript">
function addUser(){
	if($("input:radio[name='userid']:checked").val()==0){
		alert("请选择用户!");
     	return;
	}
	//var dc = Form.getData(document.getElementById("addUser"));
	var roleid=$("#roleid").val();
	var temp_userid=$("input:checkbox[name='userid']:checked");
	var userid="";
	for(var i=0;i<temp_userid.length;i++){
		if(i<temp_userid.length){
			userid+=temp_userid[i].value+",";
		}else{
			userid+=temp_userid[i].value
		}
	}
	$.post("${ctx }/authority/addUser.action",{roleid:roleid,userid:userid},function(response){
		if(response==1){
			
			ownerDialog.close();
		}else{
			alert("操作有误!");
		}
	},"json");
}
</script>
</head>
<body>
	<form action="${ctx }/authority/addUser.action" id="addUser" method="POST">
		<table cellpadding="0" cellspacing="0" class="table" border="1" style="margin:20px auto;" width="60%;">
			<tr>
				<th align="right" width="120"> 部门:</th>
				<td align="left" style="border-bottom:none;">${role.name }<input style="width:2px;" type="hidden" value="${role.id }" id="roleid" name="roleid"/></td>
			</tr>
			<tr>
				<th align="right"> 用户:</th>
				<td style="text-align:left;border-top:1px solid #DDDDDC;">
					<c:forEach items="${list}" var="user">
						<label><input type="checkbox" name="userid" value="${user.id }" <c:forEach items="${role.user }" var="roleuser"><c:if test="${roleuser.id eq user.id  }">checked="checked"</c:if></c:forEach> >
						${user.userAccount }
                        </label>
					</c:forEach>
				</td>
			</tr>
		</table>
        <p><a href="#" class="btn_azul" onclick="addUser();" style="margin:0 auto;float:none;">提交</a></p>  
    </form>  
</body>
</html>