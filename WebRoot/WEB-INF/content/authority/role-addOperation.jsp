<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>upload Test</title>
<link href="${ctx }/css/rest.css" type="text/css" rel="stylesheet" />
<link href="${ctx }/css/admin.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="${ctx }/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript">
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
label,input{padding:0;margin:0;}
label{margin-right:8px;}
</style>
</head>
<body>
    <div id="topBar">部门:${role.name }<input type="hidden" value="${role.id }" id="roleid" name="roleid"/></div>
	<div id="main">
    <ul>
		<c:forEach items="${menukindList }" var="menukind">
			<li>
				<table style="margin:0 auto;" cellpadding="0" cellspacing="0" class="table" border="1" width="96%">
                     <tr>
                        <th colspan="2" style="text-align:left;">${menukind[1] }</th>
                       <!-- <th width="190">操作</th>-->
                     </tr>
				<c:forEach items="${role.menus }" var="menus">
					<c:if test="${menukind[0] eq  menus.menuKind.id}">
						<tr>
							<td width="180"><a href="${ctx }/authority/findOperation.action?menuid=${menus.id }&roleid=${role.id }">${menus.name }</a></td>
							<td><a href="${ctx }/authority/findOperation.action?menuid=${menus.id }&roleid=${role.id }">${menus.url }</a></td>
						    <!--<td width="180">
                                <label><input type="checkbox">Update</label>
                                <label><input type="checkbox">Add</label>
                                <label><input type="checkbox">Delete</label>
                            </td>-->
                        </tr>
					</c:if>
				</c:forEach>
				</table>
			</li>
		</c:forEach>
	</ul>
    </div>
</body>
</html>