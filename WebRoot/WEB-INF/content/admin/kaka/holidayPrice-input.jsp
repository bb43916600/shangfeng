<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>菜单-录入</title>
<link type="text/css" href="${ctx }/css/style.css" rel="stylesheet"  />
<script type="text/javascript" src="${ctx }/js/jquery-1.4.2.min.js" ></script>
<script type="text/javascript" src="${ctx}/xheditor-1.1.14/xheditor-1.1.14-zh-cn.min.js"></script>
<script type="text/javascript" src="${ctx }/js/common.js" ></script>
<script type="text/javascript" src="${ctx }/js/zDialog.js"></script>
<script type="text/javascript" src="${ctx }/js/zDrag.js"></script>
<script type="text/javascript">
function addObject(){
	/* if($.trim($("#ct_areaName").val())==""){
		alert("费用说明(繁体)不能为空!");
		$("#ct_areaName").focus();
     	return;
	}
	if($.trim($("#en_areaName").val())==""){
		alert("费用说明(英文)不能为空!");
     	$("#en_areaName").focus();
     	return;
	}
	if($.trim($("#cn_areaName").val())==""){
		alert("费用说明(简体)不能为空!");
     	$("#cn_areaName").focus();
     	return;
	} */
	var id=$.trim($("#id").val());
	var dc = Form.getData(document.getElementById("addObject"));
	$.post("${ctx }/holidayPrice/objectSave.action",dc.toJSON(),function(response){
		if(response==1){
			alert("提交成功!");
		}else{
			alert("操作有误!");
		}
	},"json");
}
</script>
</head>
<body>
	<div class="right01"><img src="${ctx }/images/04.gif" /> 卡卡货车系统 &gt; <span>费用说明录入</span></div>
	<div class="main">
	   <div class="bar"></div>
	   <form id="addObject" method="post">
	   <table width="80%" border="0" cellpadding="0" cellspacing="0" class="table">
	   		<input type="hidden" name="holidayPrice.id" id="id" value="${holidayPrice.id }">
	      <%-- <tr>
	      	<td  align="right">Van开始时间:</td>
	      	<td>
	      		<select name="holidayPrice.vanStartTime" id="vanStartTime">
					<option value=""> -- 请选择 -- </option>
					<c:forEach items="${list }" var="time">
						<option value="${time }" <c:if test="${time eq holidayPrice.vanStartTime }">selected="selected"</c:if>>${time }</option>
					</c:forEach>
				</select>
	      	</td>
	      	
	      	<td  align="right">Van结束时间:</td>
	      	<td>
	      		<select name="holidayPrice.vanEndTime" id="vanEndTime">
					<option value=""> -- 请选择 -- </option>
					<c:forEach items="${list }" var="time">
						<option value="${time }">${time }</option>
					</c:forEach>
				</select>
	      	</td>
	      	
	      	<td  align="right">Van价钱:</td>
	      	<td><input type="text" class="inputClass" style="width:300px;" id="userName" name="holidayPrice.vanPrice" value="${holidayPrice.vanPrice }" /></td>
	      </tr>
	      
	      <tr>
	      	<td  align="right">5.5吨货车开始时间:</td>
	      	<td>
	      		<select name="holidayPrice.smallTruckStartTime" id="smallTruckStartTime">
					<option value=""> -- 请选择 -- </option>
					<c:forEach items="${list }" var="time">
						<option value="${time }">${time }</option>
					</c:forEach>
				</select>
	      	</td>
	      	
	      	<td  align="right">5.5吨货车结束时间:</td>
	      	<td>
	      		<select name="holidayPrice.smallTruckEndTime" id="smallTruckEndTime">
					<option value=""> -- 请选择 -- </option>
					<c:forEach items="${list }" var="time">
						<option value="${time }">${time }</option>
					</c:forEach>
				</select>
	      	</td>
	      	
	      	<td  align="right">5.5吨货车价钱:</td>
	      	<td><input type="text" class="inputClass" style="width:300px;" id="smallTruckPrice" name="holidayPrice.smallTruckPrice" value="${holidayPrice.smallTruckPrice }" /></td>
	      </tr>
	      
	      <tr>
	      	<td  align="right">9吨货车开始时间:</td>
	      	<td>
	      		<select name="holidayPrice.bigTruckStartTime" id="bigTruckStartTime">
					<option value=""> -- 请选择 -- </option>
					<c:forEach items="${list }" var="time">
						<option value="${time }">${time }</option>
					</c:forEach>
				</select>
	      	</td>
	      	
	      	<td  align="right">9吨货车结束时间:</td>
	      	<td>
	      		<select name="holidayPrice.bigTruckEndTime" id="bigTruckEndTime">
					<option value=""> -- 请选择 -- </option>
					<c:forEach items="${list }" var="time">
						<option value="${time }">${time }</option>
					</c:forEach>
				</select>
	      	</td>
	      	
	      	<td  align="right">9吨货车价钱:</td>
	      	<td><input type="text" class="inputClass" style="width:300px;" id="bigTruckPrice" name="holidayPrice.bigTruckPrice" value="${holidayPrice.bigTruckPrice }" /></td>
	      </tr>	     --%>  
	      
	      <tr>
	      	<td colspan="1" align="left">Van仔节假日价钱:</td>
	      	<td colspan="5"><input type="text" class="inputClass" style="width:300px;" id="vanHolidayPrice" onkeyup="this.value=this.value.replace(/\D/g,'')" name="holidayPrice.vanHolidayPrice" value="${holidayPrice.vanHolidayPrice }" /></td>
	      </tr>
	      <tr>
	      	<td colspan="1" align="left">5.5吨货车节假日价钱:</td>
	      	<td colspan="5"><input type="text" class="inputClass" style="width:300px;" id="smallTruckHolidayPrice" onkeyup="this.value=this.value.replace(/\D/g,'')" name="holidayPrice.smallTruckHolidayPrice" value="${holidayPrice.smallTruckHolidayPrice }" /></td>
	      </tr>
	      <tr>
	      	<td colspan="1" align="left">9吨货车节假日价钱:</td>
	      	<td colspan="5"><input type="text" class="inputClass" style="width:300px;" id="bigTruckHolidayPrice" onkeyup="this.value=this.value.replace(/\D/g,'')" name="holidayPrice.bigTruckHolidayPrice" value="${holidayPrice.bigTruckHolidayPrice }" /></td>
	      </tr>
	      
	      <tr>
	        <td colspan="6"><input type="button" value=" 提 交 " class="buttonClass" onclick="addObject();"/></td>
	      </tr>
	    </table>
		</form>
	</div>
</body>
</html>