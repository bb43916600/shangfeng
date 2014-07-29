<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="" />
<meta name="description" content="" />
<meta name="author" content="xiaohe" />
<title></title>
<link rel="stylesheet" type="text/css" href="${ctx }/userPortal/css/style.css" />
<!--[if IE 6]>
<script src="js/DD_belatedPNG.js"></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->

<script src="${ctx }/userPortal/js/jquery1.9.js"></script>
<script src="${ctx }/userPortal/js/jquery.SuperS.js"></script>

</head>

<body>
	<div class="bg_t"></div>
    <div class="bg_c">
    		<div class="wrap">
            	<!-- head -->
            		 <jsp:include page="head.jsp" flush="true"/> 
                <!-- head end -->
                
           
                			<div id="slideBox" class="slideBox">
			<div class="hd">
			
			</div>
			<div class="bd">
				<ul>
					<li><a href="#" target="_blank"><img src="${ctx }/userPortal/images/ban1.jpg" /></a></li>
					<li><a href="#" target="_blank"><img src="${ctx }/userPortal/images/ban2.jpg" /></a></li>
					<li><a href="#" target="_blank"><img src="${ctx }/userPortal/images/ban3.jpg" /></a></li>
				</ul>
			</div>

			<a class="prev" href="javascript:void(0)"></a>
			<a class="next" href="javascript:void(0)"></a>

		</div>
<div class="cls"></div>

<div class="indextab">
	   <div class="indextab_box">
       				<div class="l"><a href="${ctx }/userportal/wapIndustry.action?type=2"><img src="${ctx }/userPortal/images/pic1.jpg" width="100" height="100"></a></div>
                    <div class="r">
                    	<h2><a href="${ctx }/userportal/wapIndustry.action?type=2">风机产业</a></h2>
                        <p>公司拥有风机研究所和风机检测实验室...<a href="${ctx }/userportal/wapIndustry.action?type=2" class="red">[详情]</a></p>
                    </div>
       
       </div>

</div>

<div class="indextab last">
	   <div class="indextab_box ">
       				<div class="l"><a href="${ctx }/userportal/wapIndustry.action?type=1"><img src="${ctx }/userPortal/images/pic2.jpg" width="100" height="100"></a></div>
                    <div class="r">
                    	<h2><a href="${ctx }/userportal/wapIndustry.action?type=1">电磁线产业</a></h2>
                        <p>公司拥有风机研究所和风机检测实验室...<a href="${ctx }/userportal/wapIndustry.action?type=1" class="red">[详情]</a></p>
                    </div>
       
       </div>

</div>

<div class="indextab2">
<div class="indextab_box2">
  <a href="${ctx }/userportal/wapInvestor.action"><img src="http://image.sinajs.cn/newchart/min/n/sz000967.gif" width="371" height="151"></a></div>

</div>

	
                
                
                <!-- foot  -->
                <div class="cls"></div>
                <div class="foot">
                	<!-- foot -->
            		 	<jsp:include page="foot.jsp" flush="true"/>
                	<!-- foot end -->
                </div>
               
                
                
            
            </div>
    
    </div>
    <div class="bg_f"></div>



	 	<script type="text/javascript">
		jQuery(".slideBox").slide({mainCell:".bd ul",effect:"leftLoop",autoPlay:true});
		</script>
                
	
</body>
</html>
