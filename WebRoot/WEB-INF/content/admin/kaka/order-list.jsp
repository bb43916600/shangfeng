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
			url: "${ctx}/order/objectList.action",
	   		datatype: "json",
	   		mtype: "post",
	   		colNames:['id','订单号','车类型','跟车人数','用车时间','联系电话','联系人','小费','车价钱','总价钱','状态','录入时间', '操作'],
	    	colModel: [ 
				{name:'id',index:'id', width:60,hidden:true},
				{name:'orderNo',index:'orderNo', width:180,align:"center"},
				{name:'carType',index:'carType', width:120,align:"center"},
				{name:'peopleNumber',index:'peopleNumber', width:120,align:"center"},
				{name:'usetime',index:'usetime', width:120,align:"center"},
				{name:'userPhone',index:'userPhone', width:120,align:"center"},
				{name:'userName',index:'userName', width:120,align:"center"},
				{name:'tip',index:'tip', width:120,align:"center"},
				{name:'carPrice',index:'carPrice', width:120,align:"center"},
				{name:'orderPrice',index:'orderPrice', width:120,align:"center"},
				{name:'orderState',index:'orderState', width:90,align:"center"},
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
	  			"order.orderNo": $("#qNum").val(),
	  			"order.startTime": $("#startTime").val(),
	  			"order.endTime": $("#endTime").val(),
	     	}
		}).trigger("reloadGrid");
	}
	
	//刷新
	function refreshAction() {
		var page = 1;
		var rows = 15;
		$("#gridTable").jqGrid("setGridParam",{
			page: page,
			rows: rows, 
	  		
		}).trigger("reloadGrid");
	}
	
	function doUpdate(id){
		var diag =new Dialog();
		diag.Width = 700;
		diag.Height = 420;
		diag.parentHelp=window;
		diag.Title = "编辑";
		diag.URL = "${ctx}/order/objectInput.action?id="+id;
		diag.show();
	}
	
	function doDelete(id){
		var isSure = confirm("确定要删除？");
		if(isSure==true){
			var dc = new DataCollection();
			dc.add("id",id);
			AjaxServer.sendRequest("${ctx}/order/objectDelete.action",dc.toJSON(),function(response){
				if(response==1){
					alert("删除添加!");
					$("#gridTable").jqGrid("setGridParam",{url:"${ctx}/order/objectList.action"}).trigger("reloadGrid");
				}
				else {
					alert("操作有误!");
				}	
			});
		}
	}
	
	function freshGrid(){
		$("#gridTable").jqGrid("setGridParam",{url:"${ctx}/order/objectList.action"}).trigger("reloadGrid");
	}
</script>
</head>
<body>
	<div class="right01"><img src="${ctx}/images/04.gif" /> 卡卡叫车系统 &gt; <span>叫车订单</span></div>
	<div class="main">
   <div class="bar">
      <label style="margin-left:30px;">订单号:</label><input type="text" class="inputClass" id="qNum" name="qNum" />
      <input type="text" class="Wdate" id="startTime" name="startTime"  style="width:180px; height: 25px;"  onfocus="WdatePicker({startDate:'%d/%M/%y',dateFmt:'yyyy-MM-dd',isShowClear:false,readOnly:true,alwaysUseStartDate:true})"/>
	      		到
	  <input type="text" class="Wdate" id="endTime" name="endTime"  style="width:180px; height: 25px;"  onfocus="WdatePicker({startDate:'%d/%M/%y',dateFmt:'yyyy-MM-dd',isShowClear:false,readOnly:true,alwaysUseStartDate:true})"/>
      <input type="button" class="buttonClass" value="查&nbsp;&nbsp;询" onclick="doSearch();" style="margin-left:20px;" />
        <input type="button" class="buttonClass" value="刷&nbsp;&nbsp;新" onclick="refreshAction();" style="margin-left:20px;" />
   </div>
   <div style="width:96%;height:auto; margin:20px auto;"> 
        <table id="gridTable"></table>
        <div id="gridPager"></div>  
    </div>
</body>
</html>