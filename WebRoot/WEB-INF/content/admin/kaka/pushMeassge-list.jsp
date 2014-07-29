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
<link href="${ctx}/datepicker/skin/WdatePicker.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="${ctx}/datepicker/WdatePicker.js"></script>
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
			url: "${ctx}/push/objectList.action",
	   		datatype: "json",
	   		mtype: "post",
	   		colNames:['id','推送内容',"推送类型",'推送时间'],
	    	colModel: [ 
				{name:'id',index:'id', width:60,hidden:true},
				{name:'message',index:'message', width:120,align:"center"},
				{name:'type',index:'type', width:120,align:"center"},
				{name:'inputTime',inputTime:'inputTime', width:90,align:"center"}
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
	  			"pushMeassge.inputTime": $("#qNum").val()+" 00:00:00",
	     	}
		}).trigger("reloadGrid");
	}
	
	function freshGrid(){
		$("#gridTable").jqGrid("setGridParam",{url:"${ctx}/push/objectList.action"}).trigger("reloadGrid");
	}
</script>
</head>
<body>
	<div class="right01"><img src="${ctx}/images/04.gif" /> 卡卡叫车系统 &gt; <span>推送列表</span></div>
	<div class="main">
   <div class="bar">
      <label style="margin-left:30px;">时间(${language}):</label><input type="text" class="inputClass" id="qNum" name="qNum" onfocus="WdatePicker({minDate:'%d/%M/%y',startDate:'%d/%M/%y',dateFmt:'yyyy-MM-dd',isShowClear:false,readOnly:true,alwaysUseStartDate:true})" />
      <input type="button" class="buttonClass" value="查&nbsp;&nbsp;询" onclick="doSearch();" style="margin-left:20px;" />
   </div>
   <div style="width:96%;height:auto; margin:20px auto;"> 
        <table id="gridTable"></table>
        <div id="gridPager"></div>  
    </div>
</body>
</html>