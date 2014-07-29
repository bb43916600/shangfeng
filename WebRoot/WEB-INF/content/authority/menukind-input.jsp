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
		if($.trim($("#name").val())==""){
			alert("名称不能为空！");
	     	$("#name").focus();
	     	return;
		}
		if($.trim($("#order").val())==""){
			alert("排序编号不能为空!");
	     	$("#order").focus();
	     	return;
		}
		var id=$.trim($("#id").val());
		var dc = Form.getData(document.getElementById("saveMenuKind"));
		$.post("${ctx }/authority/saveMenuKind.action",dc.toJSON(),function(response){
			if(response==1){
				alert("添加成功!");
				$("#menukindid").attr("value","");
				$("#name").attr("value","");
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
	<div class="right01"><img src="${ctx }/images/04.gif" /> 权限管理 &gt; <span>栏目录入</span></div>
	<div class="main">
	   <div class="bar"></div>
	   <form id="saveMenuKind" method="post">
	   <table width="80%" border="0" cellpadding="0" cellspacing="0" class="table">
	   		<input type="hidden" name="menuKind.id" id="id" value="${menuKind.id }"/>
	      <tr>
	        <td width="120" align="right">所属栏目:</td>
	        <td>
	        	<select name="menukindid" id="menukindid">
					<option value=""> --  请选择    -- </option>
					<c:forEach items="${list }" var="menukinds">
						<c:if test="${menuKind.id ne menukinds[0] }">
							<option value="${menukinds[0] }" <c:if test="${menuKind.id eq menukinds[0] }">selected="selected"</c:if>>${menukinds[1] }</option>
						</c:if>
					</c:forEach>
				</select>
	        </td>
	      </tr>
	      <tr>
	      	<td width="120" align="right">栏目名称:</td>
	      	<td><input type="text" class="inputClass" style="width:300px;" id="name" name="menuKind.name" value="${menuKind.name }" /></td>
	      </tr>
	      <tr>
	      	<td width="120" align="right">排序编码:</td>
	      	<td><input type="text" class="inputClass" style="width:300px;" id="order" name="menuKind.order" value="${menuKind.order }" onkeyup="onlyNum(this);" /></td>
	      </tr>
	      <tr>
	        <td colspan="2"><input type="button" value="添加栏目" class="buttonClass" onclick="checkform();"/></td>
	      </tr>
	    </table>
		</form>
	</div>
</body>
</html>