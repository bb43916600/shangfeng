<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
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
	/* 标题 */
/* 	if($.trim($("#ct_title").val())==""){
		alert("系统标题(繁体)不能为空!");
		$("#ct_title").focus();
     	return;
	}
	if($.trim($("#en_title").val())==""){
		alert("系统标题(英文)不能为空!");
     	$("#en_title").focus();
     	return;
	}
	if($.trim($("#cn_title").val())==""){
		alert("系统标题(简体)不能为空!");
     	$("#cn_title").focus();
     	return;
	} */
	
	/* 内容 */
/* 	if($.trim($("#ct_content").val())==""){
		alert("公告内容(繁体)不能为空!");
		$("#ct_content").focus();
     	return;
	}
	if($.trim($("#en_content").val())==""){
		alert("公告内容(英文)不能为空!");
     	$("#en_content").focus();
     	return;
	}
	if($.trim($("#cn_content").val())==""){
		alert("公告内容(简体)不能为空!");
     	$("#cn_content").focus();
     	return;
	} */
	var id=$.trim($("#id").val());
	var dc = Form.getData(document.getElementById("addObject"));
	$.post("${ctx }/order/objectSave.action",dc.toJSON(),function(response){
		if(response==1){
			alert("提交成功!");
			$("#carType").attr("value","");
			$("#peopleNumber").attr("value","");
			$("#usetime").attr("value","");
			$("#tunnel").attr("value","");
			$("#useHour").attr("value","");
			$("#userName").attr("value","");
			/* $("#factoryDate").attr("value",""); */
			$("#userPhone").attr("value","");
			if(id!=0 && id!=""){
				ownerDialog.parentHelp.freshGrid();
				ownerDialog.close();
			}
		}else{
			alert("操作有误!");
		}
	},"json");
}

function outOrder(appUserId,orderNo){
	var isSure = confirm("确定要操作？");
	if(isSure==true){
		var dc = new DataCollection();
		dc.add("appUserId",appUserId);
		dc.add("orderNo",orderNo);
		var id=$.trim($("#id").val());
		AjaxServer.sendRequest("${ctx}/order/wapCancelOrder.action",dc.toJSON(),function(response){
			if(response==1){
				alert("操作成功!");
				if(id!=0 && id!=""){
					ownerDialog.parentHelp.freshGrid();
					ownerDialog.close();
				}
			}
			else {
				alert("操作有误!");
			}	
		});
	}
}
</script>
</head>
<body>
	<div class="right01"><img src="${ctx }/images/04.gif" /> 卡卡货车系统 &gt; <span>叫车订单录入</span></div>
	<div class="main">
	   <div class="bar"></div>
	   <form id="addObject" method="post">
	   <table width="80%" border="0" cellpadding="0" cellspacing="0" class="table">
	   		<input type="hidden" name="order.id" id="id" value="${order.id }">
	   		<input type="hidden" name="order.orderNo" id="orderNo" value="${order.orderNo }">
	      <tr>
	      	<td width="120" align="right">车类型:</td>
	      	<td><input type="text" class="inputClass" style="width:300px;" id="carType" name="order.carType" value="${order.carType }" /></td>
	      </tr>
	      <tr>
	      	<td width="120" align="right">跟车人数:</td>
	      	<td><input type="text" class="inputClass" style="width:300px;" id="peopleNumber" name="order.peopleNumber" value="${order.peopleNumber }" /></td>
	      </tr>
	      <tr>
	      	<td width="120" align="right">日期:</td>
	      	<td><input type="text" class="inputClass" style="width:300px;" id="usetime" name="order.usetime" value="${order.usetime }" /></td>
	      </tr>
		  <tr>
	      	<td width="120" align="right">隧道:</td>
	      	<td><input type="text" class="inputClass" style="width:300px;" id="tunnel" name="order.tunnel" value="${order.tunnel }" /></td>
	      </tr>
	      <tr>
	      	<td width="120" align="right">包钟:</td>
	      	<td>
	      		<%-- <input type="text" class="inputClass" style="width:300px;" id="useHour" name="order.useHour" value="${order.useHour }" /> --%>
	      		
	      		<%
	      		List timeList_cn = new ArrayList();
	    		timeList_cn.add("3");
	    		timeList_cn.add("3.5");
	    		timeList_cn.add("4");
	    		timeList_cn.add("4.5");
	    		timeList_cn.add("5");
	    		timeList_cn.add("5.5");
	    		timeList_cn.add("6");
	    		timeList_cn.add("6.5");
	    		timeList_cn.add("7");
	    		timeList_cn.add("7.5");
	    		timeList_cn.add("8");
	      		request.setAttribute("timeList", timeList_cn);
	      		%>
	      		<select id="useHour" name="order.useHour">
	      			<option value=""> -- 请选择  -- </option>
	      			<c:forEach items="${timeList_cn }" var="time">
	      				<option value="${time }">${time }</option>
	      			</c:forEach>
	      		</select>
	      	</td>
	      </tr>
	      <tr>
	      	<td width="120" align="right">用户名称:</td>
	      	<td><input type="text" class="inputClass" style="width:300px;" id="userName" name="order.userName" value="${order.userName }" /></td>
	      </tr>
	      <tr>
	      	<td width="120" align="right">联系号码:</td>
	      	<td><input type="text" class="inputClass" style="width:300px;" id="userPhone" name="order.userPhone" value="${order.userPhone }" /></td>
	      </tr>
	      <tr>
	        <td colspan="2">
	        	<input type="button" value=" 提 交 " class="buttonClass" onclick="addObject();"/>
	        	<c:if test="${order.id ne ''}">
	        		<input type="button" value=" 重新放出 " class="buttonClass" onclick="outOrder(${order.id },${order.orderNo });"/>
	        	</c:if>
	        </td>
	      </tr>
	    </table>
		</form>
	</div>
</body>
</html>