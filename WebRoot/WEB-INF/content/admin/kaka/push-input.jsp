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
	if($.trim($("#pushString").val())==""){
		alert("推送的内容不能为空!");
		$("#pushString").focus();
     	return;
	}
	$("#pushBtn").attr("disabled",true);
	var id=$.trim($("#id").val());
	var dc = Form.getData(document.getElementById("addObject"));
	$.post("${ctx }/push/pushAll.action",dc.toJSON(),function(response){
		if(response==1){
			alert("推送成功!");
			$("#pushString").attr("value","");
		}else{
			alert("推送失败!");
		}
		$("#pushBtn").attr("disabled",false);
	},"json");
}
</script>
</head>
<body>
	<div class="right01"><img src="${ctx }/images/04.gif" /> 卡卡货车系统 &gt; <span>线路录入</span></div>
	<div class="main">
	   <div class="bar"></div>
	   <form id="addObject" method="post">
	   <table width="80%" border="0" cellpadding="0" cellspacing="0" class="table">
	   <tr>
	      	<td width="120" align="right">推送类型:</td>
	      	<td>
	      	<%String type[] = new String [2];
	      		type[0]="交通推送";
	      		type[1]="系统通知推送";
				request.setAttribute("type", type);
			%>
	      		<select name="type" id="type">
					<option value="0"> -- 请选择 -- </option>
					<c:forEach items="${type}" var="type" varStatus="index">
						<option value="${index.count}">${type }</option>
					</c:forEach>
				</select>
	      	</td>
	      </tr>
	      <tr>
	      	<td width="120" align="right">推送的内容:</td>
	      	<td><input type="text" class="inputClass" style="width:500px;" id="pushString" name="pushString"  /></td>
	      </tr>
	      <tr>
	        <td colspan="2"><input type="button" value=" 提 交 " class="buttonClass" id="pushBtn" onclick="addObject();"/></td>
	      </tr>
	    </table>
		</form>
	</div>
</body>
</html>