<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>菜单-录入</title>
<link type="text/css" href="${ctx }/css/style.css" rel="stylesheet"  />
<link href="${ctx}/datepicker/skin/WdatePicker.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="${ctx}/datepicker/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx }/js/jquery-1.4.2.min.js" ></script>
<script type="text/javascript" src="${ctx }/js/common.js" ></script>
<script type="text/javascript" src="${ctx }/js/zDialog.js"></script>
<script type="text/javascript" src="${ctx }/js/zDrag.js"></script>
<script type="text/javascript">
function addObject(){
	if($.trim($("#settingTime").val())==""){
		alert("设置日期不能为空!");
		$("#settingTime").focus();
     	return;
	}
	var id=$.trim($("#id").val());
	var dc = Form.getData(document.getElementById("addObject"));
	$.post("${ctx }/holidayTime/objectSave.action",dc.toJSON(),function(response){
		if(response==1){
			alert("提交成功!");
			$("#settingTime").attr("value","");
			$("#remark").attr("value","");
			$("#endTime").attr("value","");
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
	<div class="right01"><img src="${ctx }/images/04.gif" /> 卡卡货车系统 &gt; <span>节假日设置</span></div>
	<div class="main">
	   <div class="bar"></div>
	   <form id="addObject" method="post">
	   <table width="80%" border="0" cellpadding="0" cellspacing="0" class="table">
	   		<input type="hidden" name="holidayTime.id" id="id" value="${holidayTime.id }">
	      <tr>
	      	<td width="120" align="right">节假日日期设置:</td>
	      	<td>
	      		<input type="text" class="Wdate" id="settingTime" name="holidayTime.settingTime" value="${holidayTime.settingTime }" style="width:180px;"  onfocus="WdatePicker({minDate:'%d/%M/%y',startDate:'%d/%M/%y',dateFmt:'yyyy-MM-dd',isShowClear:false,readOnly:true,alwaysUseStartDate:true})"/>
	      		到
	      		<input type="text" class="Wdate" id="endTime" name="holidayTime.endTime" value="${holidayTime.settingTime }" style="width:180px;"  onfocus="WdatePicker({minDate:'%d/%M/%y',startDate:'%d/%M/%y',dateFmt:'yyyy-MM-dd',isShowClear:false,readOnly:true,alwaysUseStartDate:true})"/>
	      	</td>
	      </tr>
	      <tr>
	      	<td width="120" align="right">备注:</td>
	      	<td>
	      		<textarea rows="20" cols="60" id="remark" name="holidayTime.remark"></textarea>
	      	</td>
	      </tr>
	      <tr>
	        <td colspan="2"><input type="button" value=" 提 交 " class="buttonClass" onclick="addObject();"/></td>
	      </tr>
	    </table>
		</form>
	</div>
</body>
</html>