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
<script type="text/javascript" src="${ctx }/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="${ctx }/js/common.js"></script>
<script type="text/javascript" src="${ctx }/js/zDialog.js"></script>
<script type="text/javascript" src="${ctx }/js/zDrag.js"></script>
<script type="text/javascript">
function addOperation(){
	if($("input:radio[name='operationid']:checked").val()==0){
		alert("请选择权限!");
     	return;
	}
	var roleid=$("#roleid").val();
	var menuid=$("#menuid").val();
	var temp_operationid=$("input:checkbox[name='operationid']:checked");
	var operationid="";
	for(var i=0;i<temp_operationid.length;i++){
		if(i<temp_operationid.length){
			operationid+=temp_operationid[i].value+",";
		}else{
			operationid+=temp_operationid[i].value
		}
	}
	$.post("${ctx }/authority/addOperation.action",{roleid:roleid,operationid:operationid,menuid:menuid},function(response){
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
	<form action="" id="addOperation" method="post">
    <table cellpadding="0" cellspacing="0" class="table" border="1" style="margin:20px auto;" width="60%;">
       <tr>
          <th width="120">部门:</th>
          <td style="text-align:left;border-bottom:none;">${role.name }<input type="hidden" value="${role.id }" id="roleid" name="roleid"/></td>
       </tr>
       <tr>
          <th>菜单:</th>
          <td style="text-align:left;border-bottom:none;border-top:1px solid #DDDDDC;">
              <ul>
				<c:forEach items="${role.menus }" var="menus">
					<li>
						${menus.name } ${menus.url }
						<input type="hidden" value="${menus.id }" id="menuid" name="menuid"/>
					</li>
				</c:forEach>
			   </ul>
          </td>
       </tr>
       <tr>
		  <th>权限:</th>
		  <td style="text-align:left;border-bottom:none;border-top:1px solid #DDDDDC;">
             <c:forEach items="${list }" var="operation">
                <label><input type="checkbox" style="float:none;" name="operationid" value="${operation.id }" <c:forEach items="${menus }" var="menu"><c:forEach items="${menu.operations }" var="operations"><c:if test="${operations.id eq operation.id }">checked="checked"</c:if></c:forEach></c:forEach>/> ${operation.name }</label>
                ${operation.url }
             </c:forEach>
          </td>
	   </tr>
    </table>   
    <p><a href="#" class="btn_azul" onclick="addOperation();" style="margin:0 auto;float:none;">提交</a></p>  
    </form>
    
</body>
</html>