<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="${ctx }/js/jquery-1.6.1.min.js"></script>
<script type="text/javascript" src="${ctx }/js/chili-1.7.pack.js"></script>
<script type="text/javascript" src="${ctx }/js/jquery.easing.js"></script>
<script type="text/javascript" src="${ctx }/js/jquery.dimensions.js"></script>
<script type="text/javascript" src="${ctx }/js/jquery.accordion.js"></script>
<script language="javascript">
	jQuery().ready(function(){
		jQuery('#navigation').accordion({
			header: '.head',
			navigation1: true, 
			event: 'click',
			fillSpace: true,
			animated: 'bounceslide'
		});
	});
</script>
<style type="text/css">
<!--
body {
	margin:0px;
	padding:0px;
	font-size: 12px;
}
#navigation {
	margin:0px;
	padding:0px;
	width:147px;
}
#navigation a.head {
	cursor:pointer;
	background:url(${ctx }/images/main_34.gif) no-repeat scroll;
	display:block;
	font-weight:bold;
	margin:0px;
	padding:5px 0 5px;
	text-align:center;
	font-size:12px;
	text-decoration:none;
}
#navigation ul {
	border-width:0px;
	margin:0px;
	padding:0px;
	text-indent:0px;
}
#navigation li {
	list-style:none; display:inline;
}
#navigation li li a {
	display:block;
	font-size:12px;
	text-decoration: none;
	text-align:center;
	padding:3px;
}
#navigation li li a:hover {
	background:url(${ctx }/images/tab_bg.gif) repeat-x;
		border:solid 1px #adb9c2;
}
-->
</style>
</head>
<body>
<div  style="height:100%;">
  <ul id="navigation">
  	<c:forEach items="${menuKind }" var="menuKinds">
  		<li> <a class="head">${menuKinds[1] }</a>
  				<ul>
           		<c:forEach items="${menu }" var="menu" >
           			<c:if test="${menuKinds[0] eq  menu.menuKind.id}">
           				<li><a href="${ctx}${menu.url }" target="rightFrame">${menu.name }</a></li>
           			</c:if>
           		</c:forEach>
           		</ul>
    	</li>
  	</c:forEach>
    <li> <a class="head">华老专用模块</a>
      <ul>
        <li><a href="${ctx}/admin/crm/client-input.action" target="rightFrame">添加客户</a></li>
        <li><a href="${ctx}/crm/clientList.action" target="rightFrame">管理客户</a></li>
        <li><a href="${ctx}/admin/crm/product-input.action" target="rightFrame">添加产品</a></li>
        <li><a href="${ctx}/crm/productList.action" target="rightFrame">管理产品</a></li>
        <li><a href="${ctx}/crm/inputOrder.action" target="rightFrame">添加送货单</a></li>
        <li><a href="${ctx}/crm/orderMainList.action" target="rightFrame">管理送货单</a></li>
      </ul>
    </li>
    <li> <a class="head">版本信息</a>
      <ul>
        <li><a href="http://www.bb2004.com" target="_blank">BB2004</a></li>
      </ul>
    </li>
  </ul>
</div>
</body>
</html>
