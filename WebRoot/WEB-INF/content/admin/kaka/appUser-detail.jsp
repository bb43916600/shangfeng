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
function checkUser(id,level){
	var isSure = confirm("确定要操作？");
	if(isSure==true){
		var dc = new DataCollection();
		dc.add("id",id);
		dc.add("level",level);
		var id=$.trim($("#id").val());
		AjaxServer.sendRequest("${ctx}/appUser/updateLevel.action",dc.toJSON(),function(response){
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
	<div class="right01"><img src="${ctx }/images/04.gif" /> 卡卡货车系统 &gt; <span>系统公告录入</span></div>
	<div class="main">
	   <div class="bar"></div>
	   <form id="addObject" method="post">
	   <table width="80%" border="0" cellpadding="0" cellspacing="0" class="table">
	   		<input type="hidden" name="appUser.id" id="id" value="${appUser.id }">
	      <tr>
	      	<td width="120" align="right">车牌号码:</td>
	      	<td>${appUser.carNumber }</td>
	      </tr>
	      <tr>
	      	<td width="120" align="right">手机号码:</td>
	      	<td>${appUser.phone }</td>
	      </tr>
	      <tr>
	      	<td width="120" align="right">用户呢称:</td>
	      	<td>${appUser.userName }</td>
	      </tr>
		  <tr>
	      	<td width="120" align="right">介绍人号码:</td>
	      	<td>${appUser.introducerPhone }</td>
	      </tr>
	      <tr>
	      	<td width="120" align="right">车种类:</td>
	      	<td>${appUser.carType }</td>
	      </tr>
	      <tr>
	      	<td width="120" align="right">Van仔车类型:</td>
	      	<td>${appUser.vanCarType }</td>
	      </tr>
 	      <tr>
	      	<td width="120" align="right">出厂日期:</td>
	      	<td>${appUser.factoryDate }</td>
	      </tr>
	      <tr>
	      	<td width="120" align="right">身份证照片:</td>
	      	<td><img src="${ctx }/appUserImage/${appUser.idCardImage}" /></td>
	      </tr>
	      <tr>
	      	<td width="120" align="right">车牌照片:</td>
	      	<td><img src="${ctx }/appUserImage/${appUser.carNumberImage}" /></td>
	      </tr>
	      <tr>
	      	<td width="120" align="right">行车照片:</td>
	      	<td><img src="${ctx }/appUserImage/${appUser.driveCardImage}" /></td>
	      </tr>
	      <tr>
	        <td colspan="2">
	        	<c:if test="${appUser.level eq 4 }">
	        		<input type="button" value=" 通过 " class="buttonClass" onclick="checkUser(${appUser.id },1);"/>
	        		<input type="button" value=" 不通过 " class="buttonClass" onclick="checkUser(${appUser.id },2);"/>
	        	</c:if>
	        	<input type="button" value=" 最高级设置 " class="buttonClass" onclick="checkUser(${appUser.id },3);"/>
	        </td>
	      </tr>
	    </table>
		</form>
	</div>
</body>
</html>