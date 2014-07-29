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
	if($.trim($("#ct_areaName").val())==""){
		alert("包月与商务合作说明(繁体)不能为空!");
		$("#ct_areaName").focus();
     	return;
	}
	if($.trim($("#en_areaName").val())==""){
		alert("包月与商务合作说明(英文)不能为空!");
     	$("#en_areaName").focus();
     	return;
	}
	if($.trim($("#cn_areaName").val())==""){
		alert("包月与商务合作说明(简体)不能为空!");
     	$("#cn_areaName").focus();
     	return;
	}
	var id=$.trim($("#id").val());
	var dc = Form.getData(document.getElementById("addObject"));
	$.post("${ctx }/moothFee/objectSave.action",dc.toJSON(),function(response){
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
	<div class="right01"><img src="${ctx }/images/04.gif" /> 卡卡货车系统 &gt; <span>包月与商务合作说明录入</span></div>
	<div class="main">
	   <div class="bar"></div>
	   <form id="addObject" method="post">
	   <table width="80%" border="0" cellpadding="0" cellspacing="0" class="table">
	   		<input type="hidden" name="moothFee.id" id="id" value="${moothFee.id }">
	      <tr>
	      	<td width="120" align="right">包月与商务合作(繁体):</td>
	      	<td><textarea  class="xheditor" style="width:800px; height: 200px;" id="ct_areaName" name="moothFee.ct_content" >${moothFee.ct_content }</textarea></td>
	      </tr>
	      <tr>
	      	<td width="120" align="right">包月与商务合作(英文):</td>
	      	<td><textarea  class="xheditor" style="width:800px; height: 200px;" id="en_areaName" name="moothFee.en_content" >${moothFee.en_content }</textarea></td>
	      </tr>
	      <tr>
	      	<td width="120" align="right">包月与商务合作(简体):</td>
	      	<td><textarea  class="xheditor" style="width:800px; height: 200px;" id="cn_areaName" name="moothFee.cn_content" >${moothFee.cn_content }</textarea></td>
	      </tr>
	      <tr>
	        <td colspan="2"><input type="button" value=" 提 交 " class="buttonClass" onclick="addObject();"/></td>
	      </tr>
	    </table>
		</form>
	</div>
</body>
</html>