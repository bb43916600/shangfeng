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


</head>

<body>
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
                	<span><a href="${ctx }/userportal/wapIndex.action" class="home">首页</a> > <i>重大活动</i></span><h2>重大活动<i>Major events</i></h2>
                </div>
                <div class="content">
                	<div class="about">
                    	<div class="activity">
                        	
                        	<c:forEach items="${list }" begin="0" end="0" var="objects" varStatus="index">
                        		<span class="r">
                        		<p><i>${objects.inputTime }</i><h2>${objects.title }</h2></p>
                            <p>${objects.content }<a href="detail.html">更多>></a></p>
                            </span>
                        	<span class="l"><a href="detail.html"><img src="${ctx }/activitieImage/${objects.images }" width="224" height="150"></a></span>
                        	</c:forEach>
                        </div>
                        <ul class="activity_list">
                        	<c:forEach items="${list }" begin="1" var="objects" varStatus="index">
                        		<li><a href="#"><i>${objects.inputTime }</i>${objects.title }</a></li>
                        	</c:forEach>
                        	<!-- 
                            <li><a href="#"><i>2014-06-25</i>提升执行力，再造上风</a></li>
                            <li><a href="#"><i>2014-06-25</i>提升执行力，再造上风</a></li>
                            <li><a href="#"><i>2014-06-25</i>提升执行力，再造上风</a></li>
                            <li><a href="#"><i>2014-06-25</i>提升执行力，再造上风</a></li>
                            <li><a href="#"><i>2014-06-25</i>提升执行力，再造上风</a></li>
                            <li><a href="#"><i>2014-06-25</i>提升执行力，再造上风</a></li>
                            <li><a href="#"><i>2014-06-25</i>提升执行力，再造上风</a></li>
                            <li><a href="#"><i>2014-06-25</i>提升执行力，再造上风</a></li>
                            <li><a href="#"><i>2014-06-25</i>提升执行力，再造上风</a></li>
                            <li><a href="#"><i>2014-06-25</i>提升执行力，再造上风</a></li>
                             -->
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
                            <li class="hover"><a href="${ctx }/userportal/wapActivitie.action">重大活动</a></li>
                            <li><a href="${ctx }/userportal/wapStaffPresence.action">员工风采</a></li>
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
