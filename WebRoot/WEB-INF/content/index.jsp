<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>后台首页</title>
<link href="${ctx }/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx }/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="${ctx }/js/zDialog.js"></script>
<script type="text/javascript" src="${ctx }/js/zDrag.js"></script>
<script type="text/JavaScript"> 
var id=function(id) {
   return document.getElementById(id);
}

function show_menuB(numB){
	for(j=0;j<100;j++){
		 if(j!=numB){
			if(id('Bli0'+j)){
		  id('Bli0'+j).style.display='none';
		  id('Bf0'+j).style.background='url(images/01.gif)';
		}
		 }
	}
	if(id('Bli0'+numB)){   
		if(id('Bli0'+numB).style.display=='block'){
		  id('Bli0'+numB).style.display='none';
		 id('Bf0'+numB).style.background='url(images/01.gif)';
		}else {
		  id('Bli0'+numB).style.display='block';
		  id('Bf0'+numB).style.background='url(images/02.gif)';
		}
	}
}


var temp=0;
function show_menuC(){
		if (temp==0){
		 document.getElementById('LeftBox').style.display='none';
	  	 document.getElementById('RightBox').style.marginLeft='0';
		 document.getElementById('Mobile').style.background='url(images/center.gif)';

		 temp=1;
		}else{
		document.getElementById('RightBox').style.marginLeft='222px';
	   	document.getElementById('LeftBox').style.display='block';
		document.getElementById('Mobile').style.background='url(images/center0.gif)';

	   temp=0;
			}
	 }

function quit(){
	if(confirm("你确定要退出？")==true){
		$.post("${ctx }/user/logout.action",function(result){
			if(result==1){
				window.location.href='${ctx }/login.jsp';
			}else{
				alert("操作有误");
			}
		})
	}	
}

$(function(){
   var height=$("#RightBox").height()-4;
   $("#LeftBox").height(height);
   $("#li010").height(1000);
   
   var childList=$('input[id^=child]');
	for(var i=0;i<childList.length;i++){
		var aList=$("#"+childList[i].id+" a");
		if(aList.length>0){
			$("#"+childList[i].className+"").show();
		}
	}
});

$(window).bind('resize', winResize);
function winResize() {
   var height=$("#RightBox").height()-4;
   $("#LeftBox").height(height);
   $("#li010").height(1000);
}
</script>
</head>

<body>
<div class="header">
	<div class="header03"></div>
	<div class="header01"></div>
	<div class="header02">管理系统</div>
</div>
<div class="left" id="LeftBox">
	<div class="left01">
		<div class="left01_right"></div>
		<div class="left01_left"></div>
		<div class="left01_c">
		<c:forEach items="${user.role }" var="role">
			${role.name }
		</c:forEach>
		：${user.userAccount}
		</div>
	</div>
	<c:forEach items="${menuKind }" var="kind" varStatus="bb">
	<div class="left02" ><!-- style="display: none;" id="father${bb.index+1}" -->
		<div class="left02top">
			<div class="left02top_right"></div>
			<div class="left02top_left"></div>
			<div class="left02top_c">${kind.name }</div>
		</div>
		<div class="left02down">
		<c:forEach items="${kind.menuKinds}" var="menuKinds" varStatus="index">
			
			<div class="left02down01" ><a  onclick="show_menuB(${bb.count }${index.index })" href="javascript:;">
			<div id="Bf0${bb.count }${index.index }" class="left02down01_img"></div>${menuKinds.name }</a></div>
			<div class="left02down01_xia noneBox" id="Bli0${bb.count }${index.index }" <c:if test="${bb.count eq 1 and index.index eq 0 }">style="display:block;"</c:if>>
				<ul>
					<c:forEach items="${menu }" var="menu">
					<c:if test="${menuKinds.id eq  menu.menuKind.id}">
						<li><a href="${ctx }${menu.url }" target="frame_a">&middot;${menu.name }</a></li>
					</c:if>
					</c:forEach>
				</ul>
			</div>
		</c:forEach>
		</div>
	</div>
	</c:forEach>
    
	<div class="left01">
		<div class="left03_right"></div>
		<div class="left01_left"></div>
		<div class="left03_c"><a href="javascript:void(0);" style="color:#03F;" onclick="quit()">安全退出</a></div>
	</div>
</div>
<div class="rrcc" id="RightBox">
	<div class="center" id="Mobile" onclick="show_menuC()"></div>
	<div class="right" id="li010">
        <iframe width=100% height="100%" name="frame_a" id="frame_a" frameborder="0" scrolling="no" src="${ctx }/admin/kaka/appUser-check.action"></iframe>
	</div>
</div>

</body>
</html>