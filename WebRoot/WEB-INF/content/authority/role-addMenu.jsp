<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link href="${ctx }/css/rest.css" type="text/css" rel="stylesheet" />
<link href="${ctx }/css/admin.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="${ctx }/js/zDialog.js"></script>
<script type="text/javascript" src="${ctx }/js/zDrag.js"></script>
<script type="text/javascript" src="${ctx }/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="${ctx }/js/common.js"></script>
<script type="text/javascript">
function addMenu(){
	if($("input:radio[name='menuid']:checked").val()==0){
		alert("Por favor, seleccione el usuario!");
     	return;
	}
	var roleid=$("#roleid").val();
	var temp_menuid=$("input:checkbox[name='menuid']:checked");
	var menuid="";
	for(var i=0;i<temp_menuid.length;i++){
		if(i<temp_menuid.length){
			menuid+=temp_menuid[i].value+",";
		}else{
			menuid+=temp_menuid[i].value;
		}
	}
	$.post("${ctx }/authority/addMenu.action",{roleid:roleid,menuid:menuid},function(response){
		if(response==1){
			ownerDialog.close();
		}else{
			alert("操作有误!");
		}
	},"json");
}
$(function(){
	$("tbody tr").hover(function(){
		$(this).addClass("classhover")
	},function(){
		$(this).removeClass("classhover")	
	});
});
</script>
<style type="text/css">
.table {border:1px solid #DDDDDC;border-collapse:collapse; font-size:11pt;}
.table tr td{padding:2px 5px; text-align:left;}
.classhover{background:#95CAFF;}
</style>
</head>
<body>
	<form action="${ctx }/authority/addMenu.action" method="post">
    <div id="topBar"><span style="float:right;margin:3px 10px 0 0;"><a href="#" class="btn_azul" onclick="addMenu();">提交</a></span>部门:${role.name }<input type="hidden" value="${role.id }" id="roleid" name="roleid"/></div>
    <div id="main">
		<ul>
			<c:forEach items="${menukindList }" var="menukind">
				<li>
					<table style="margin:0 auto;" cellpadding="0" cellspacing="0" class="table" border="1" width="96%">
                        <tr>
                           <th colspan="3" style="text-align:left;">${menukind[1] }</th>
                        </tr>
					<c:forEach items="${list}" var="menu">
						<c:if test="${menukind[0] eq  menu.menuKind.id}">
						<tr>
							<td width="30" style="text-align:center;"><input style="float:none;" type="checkbox" name="menuid" value="${menu.id }" <c:forEach items="${role.menus }" var="rolemenu"><c:if test="${rolemenu.id eq menu.id  }">checked="checked"</c:if></c:forEach> /></td>
							<td width="150" style="text-align:left;">${menu.name }</td>
							<td style="text-align:left;">${menu.url }</td>
						</tr>
						</c:if>
					</c:forEach>
					</table>
				</li>
			</c:forEach>
		</ul>
    </div>
    </form>  
</body>
</html>