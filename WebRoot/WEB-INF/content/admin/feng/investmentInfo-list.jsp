<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>菜单管理</title>
<link type="text/css" href="${ctx }/css/style.css" rel="stylesheet"  />
<link media="screen" href="${ctx }/css/redmond/jquery-ui-1.8.21.custom.css" rel="stylesheet" type="text/css" />
<link media="screen" href="${ctx}/js/jqgrid/css/ui.jqgrid.css" rel="stylesheet" type="text/css" />
<style type="text/css">
.table td,th{ font-size:14px;}
</style>
<script type="text/javascript" src="${ctx }/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="${ctx }/js/common.js"></script>
<script type="text/javascript" src="${ctx }/js/jquery-ui-1.8.1.custom.min.js"></script>
<script type="text/javascript" src="${ctx }/js/jqgrid/js/jquery.jqGrid.min.js"></script>
<script type="text/javascript" src="${ctx }/js/jqgrid/js/i18n/grid.locale-en.js"></script>
<script type="text/javascript" src="${ctx }/js/zDialog.js"></script>
<script type="text/javascript" src="${ctx }/js/zDrag.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$("#gridTable").jqGrid({
			url: "${ctx}/investment/investmentInfoList.action",
	   		datatype: "json",
	   		mtype: "post",
	   		colNames:['id','标题','内容','录入时间', '操作'],
	    	colModel: [ 
				{name:'id',index:'id', width:60,hidden:true},
				{name:'title',index:'title', width:120,align:"center"},
				{name:'content',index:'content', width:120,align:"center"},
				{name:'inputTime',index:'inputTime', width:90,align:"center"},
				{name:'operation',index:'Operating', width:120, align:"center"}	
			],
			autowidth: true,
			rowNum: 15,
			rowList: [15,30,50],
			viewrecords: true,
			jsonReader: {  
				root: "gridModel",
				page: "page",
				total: "total", 
				records: "record",
				repeatitems: false
			},
			pager: "#gridPager"
		});
		
		$("#gridTable").setGridWidth($(document.body).width()-60);
		$("#gridTable").setGridHeight($(document.body).height()-153);
		$("#gbox_gridTable").css("margin","0 auto");
	});
	
	$(window).bind('resize', winResize);
	function winResize() {
		$("#gridTable").setGridWidth($(window).width()-20);
		$("#gridTable").setGridHeight($(window).height()-180);
	}
	
	function doSearch(){
		var page = $("#gridTable").getGridParam("page");
		var rows = $("#gridTable").getGridParam("rows");
		$("#gridTable").jqGrid("setGridParam",{
			page: page,
			rows: rows, 
	  		postData:{
	  			"name": $("#qNum").val(),
	     	}
		}).trigger("reloadGrid");
	}
	
	function doUpdate(id){
		var diag =new Dialog();
		diag.Width = 700;
		diag.Height = 420;
		diag.parentHelp=window;
		diag.Title = "编辑";
		diag.URL = "${ctx}/investment/investmentInfoInput.action?id="+id;
		diag.show();
	}
	
	function doDelete(id){
		var isSure = confirm("确定要删除？");
		if(isSure==true){
			var dc = new DataCollection();
			dc.add("id",id);
			AjaxServer.sendRequest("${ctx}/investment/investmentInfoDelete.action",dc.toJSON(),function(response){
				if(response==1){
					alert("删除成功!");
					$("#gridTable").jqGrid("setGridParam",{url:"${ctx}/investment/investmentInfoList.action"}).trigger("reloadGrid");
				}
				else {
					alert("操作有误!");
				}	
			});
		}
	}
	
	function freshGrid(){
		$("#gridTable").jqGrid("setGridParam",{url:"${ctx}/investment/investmentInfoList.action"}).trigger("reloadGrid");
	}
</script>
</head>
<body>
	<div class="right01"><img src="${ctx}/images/04.gif" /> 上风实业 &gt; <span>实时资讯列表</span></div>
	<div class="main">
   <div class="bar">
      <label style="margin-left:30px;">标题:</label><input type="text" class="inputClass" id="qNum" name="qNum" />
      <input type="button" class="buttonClass" value="查&nbsp;&nbsp;询" onclick="doSearch();" style="margin-left:20px;" />
   </div>
   <div style="width:96%;height:auto; margin:20px auto;"> 
        <table id="gridTable"></table>
        <div id="gridPager"></div>  
    </div>
</body>
</html>