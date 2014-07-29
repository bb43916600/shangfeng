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
<script src="${ctx }/userPortal/js/DD_belatedPNG.js"></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<script type="text/javascript" src="${ctx }/userPortal/js/jquery1.9.js"></script>

<script>
	$(function(){
	
		$('.videogo').click(function(){
			$('.bgdiv').show();
			$('.videodiv').show();
			
		$(".videodiv").css({
			left:($("body").width()-$(".videodiv").width())/2-20+"px",
			top:($(window).height()-$(".videodiv").height())/2+$(window).scrollTop()+"px"
		});
		
		var aa=$(this);
		$('#imgimg')[0].src=aa[0].rel;
		$('#xqcon')[0].innerHTML=aa.children("i")[0].innerHTML;
		});
		
	
	
		$('.close').click(function(){
			$('.bgdiv').hide();
			$('.videodiv').hide();
			$('#imgimg')[0].src="";
			
			
			
			
		});		
	});
</script>

</head>

<body>
<div class="bgdiv"></div>
<div class="videodiv">
    <div class="video1">
    <img src="${ctx }/userPortal/images/de_mein_pic1.jpg" width="696" height="444" id="imgimg"> 

    
    
    <div class="cls"></div>
    <span id="xqcon">上风高科企业荣誉</span>
        <div class="close"><img src="${ctx }/userPortal/images/close.gif" width="40" height="40"></div>
   </div>

 
 </div>

	<div class="bg_t"></div>
    <div class="bg_c">
    		<div class="wrap">
            	<!-- head -->
            		 <jsp:include page="../head.jsp" flush="true"/>
                <!-- head end -->
                
           <!--nban star-->
           <div class="nban">
           	 <img src="${ctx }/userPortal/images/nban3.jpg" width="999" height="261"> </div>
            <!--nban end-->
			<div class="cls"></div>
			<!--nmain star-->
            <div class="nmain">
            <div class="right">
            	<div class="curr">
                	<span><a href="${ctx }/userportal/wapIndex.action" class="home">首页</a> > <i>员工风采</i></span><h2>员工风采<i>Employee</i></h2>
                </div>
                <div class="content">
                	<div class="about">
                    	<ul class="honor_list mien">
                    		<c:forEach items="${list }" var="objects" varStatus="index">
                    			<li <c:if test="${index.count%3 eq 0 }"> class="last" </c:if>>
                    				<a href="javascript:void(0);" class="videogo" rel="${ctx }/staffPresenceImage/${objects.images}">
	                    				<img src="${ctx }/staffPresenceImage/${objects.images}">
	                    				<i>${objects.title }</i>
                    				</a>
                    			</li>
                    		</c:forEach>
                        	<%-- 
                            <li><a href="javascript:void(0);" class="videogo" rel="${ctx }/userPortal/images/mien_pic2.jpg"><img src="${ctx }/userPortal/images/mien_pic2.jpg"><i>2013年成功收购上虞专用风机公司</i></a></li>
                            <li class="last"><a href="javascript:void(0);" class="videogo" rel="${ctx }/userPortal/images/mien_pic3.jpg"><img src="${ctx }/userPortal/images/mien_pic3.jpg"><i>2013年成功收购上虞专用风机公司</i></a></li>
                            <li><a href="javascript:void(0);" class="videogo" rel="${ctx }/userPortal/images/de_mein_pic1.jpg"><img src="${ctx }/userPortal/images/mien_pic4.jpg"><i>大学生新员工参观车间</i></a></li>
                            <li><a href="javascript:void(0);" class="videogo" rel="${ctx }/userPortal/images/de_mein_pic1.jpg"><img src="${ctx }/userPortal/images/mien_pic5.jpg"><i>大学生新员工培训总结会后合影</i></a></li>
                            <li class="last"><a href="javascript:void(0);" class="videogo" rel="${ctx }/userPortal/images/de_mein_pic1.jpg"><img src="${ctx }/userPortal/images/mien_pic6.jpg"><i>大学生新员工拓展活动青春飞扬</i></a></li>
                            <li><a href="javascript:void(0);" class="videogo" rel="${ctx }/userPortal/images/de_mein_pic1.jpg"><img src="${ctx }/userPortal/images/mien_pic7.jpg"><i>非财务经理的财务管理培训</i></a></li>
                            <li><a href="javascript:void(0);" class="videogo" rel="${ctx }/userPortal/images/de_mein_pic1.jpg"><img src="${ctx }/userPortal/images/mien_pic8.jpg"><i>非财务经理的财务管理培训</i></a></li>
                            <li class="last"><a href="javascript:void(0);" class="videogo" rel="${ctx }/userPortal/images/de_mein_pic1.jpg"><img src="${ctx }/userPortal/images/mien_pic9.jpg"><i>非财务经理的财务管理培训</i></a></li> --%>
                        </ul>
                        <div class="cls"></div>
                        <!-- 分页开始 -->
                        <div class="page">
                        	<i>共${total }页  第${page }/${total }页</i>
	                        <c:if test="${page > 1}">
						    	<a href="${ctx }${pageurl }.action?page=${page-1 }">上一页</a>
						    </c:if>
                            <span>
                            	<c:forEach begin="1" end="${total }" varStatus="index">
                            		<c:if test="${index.index eq page}">
                            			<a class="hover" href="${ctx }${pageurl }.action?page=${index.index }">${index.index }</a>
                            		</c:if>
                            		<c:if test="${index.index ne page}">
                            			<a href="${ctx }${pageurl }.action?page=${index.index }">${index.index }</a>
                            		</c:if>
                            	</c:forEach>
                            </span>
	                        <c:if test="${page < total}">
	                        	<a href="${ctx }${pageurl }.action?page=${page+1 }">下一页</a>
						    </c:if>
                        </div>
                        <!-- 分页结束 -->
</div>
                </div>
            </div>
            	<div class="left">
                	<div class="sub_t">
               	    <img src="${ctx }/userPortal/images/sub_t4.jpg" width="229" height="54"> </div>
                    <div class="sub_m">
                   	  <ul class="sublist">
                        	<li><a href="${ctx }/userportal/wapConcept.action">人才理念</a></li>
                            <li><a href="${ctx }/userportal/wapJob.action">招聘信息</a></li>
                            <li><a href="${ctx }/userportal/wapJobContact.action">联系方式</a></li>
                            <li><a href="${ctx }/userportal/wapActivitie.action">重大活动</a></li>
                            <li class="hover"><a href="${ctx }/userportal/wapStaffPresence.action">员工风采</a></li>
                        </ul>
                        
                        <div class="left_contact">
                        <img src="${ctx }/userPortal/images/tel.jpg" width="196" height="121">
                        销售总监：赵福荣（轨道交通<br>
电话： +86-575-8236-1607<br>
传真： +86-575-8236-1609<br>
邮箱： sfzfr@ifirst.com.cn<br>
地址： 中国.浙江省上虞市上浦镇开<br>
<em></em>发区<br>
邮编： 312375
                        </div>
                    </div>
                    <div class="sub_b"></div>
                </div>
            </div>
            <!--nmain end-->







	
                
                
                <!-- foot  -->
                <div class="cls"></div>
                <div class="foot">
                	<!-- foot -->
            		 	<jsp:include page="../foot.jsp" flush="true"/>
                	<!-- foot end -->
                </div>
               
                
                
            
            </div>
    
    </div>
    <div class="bg_f"></div>


	
    
</body>
</html>
