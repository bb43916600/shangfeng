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

<SCRIPT src="${ctx }/userPortal/js/jquery1.9.js" type="text/javascript"></SCRIPT>
<script src="${ctx }/userPortal/js/jqnav.js"></script>

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
           	 <img src="${ctx }/userPortal/images/nban2.jpg" width="999" height="261"> </div>
            <!--nban end-->
			<div class="cls"></div>
			<!--nmain star-->
            <div class="nmain">
            <div class="right">
            	<div class="curr">
                	<span>
                		<a href="${ctx }/userportal/wapIndex.action" class="home">首页</a> > 
                		<i class="i">电磁线产业</i> > 
                		<i>广东威奇电工材料有限公司</i>
                	</span>
                	<h2>电磁线产业<i>Magnet wire industry</i></h2>
                </div>
                <div class="content">
                	<div class="about" style="color:#595959">
                    	<p align="center" class="f24">${object.title }</p>
                        <br>
                        <p class="em">${object.content }</p>

                    </div>
                </div>
            </div>
            	<div class="left">
                	<div class="sub_t">
               	    <img src="${ctx }/userPortal/images/sub_t3.jpg" width="229" height="54"> </div>
                    <div class="sub_m">
                   	  <ul class="sublist">
                        	<li <c:if test="${type eq 1 }">class="hover"</c:if>><a href="javascript:;">电磁线产业</a>
                            <div class="subbox">
                            	<ul class="sub2list">
                            		<c:forEach items="${list }" var="objects" varStatus="index">
                            			<li <c:if test="${objects.id eq object.id }">class="hover" </c:if>><a href="${ctx }/userportal/wapIndustryDetail.action?id=${objects.id}">·${objects.title}</a></li>
                            		</c:forEach>
                                </ul>
                            </div>
                            </li>
                            <li <c:if test="${type eq 2 }">class="hover"</c:if>><a href="javascript:;">风机产业</a>
                            <div class="subbox">
                            	<ul class="sub2list">
                            		<c:forEach items="${list2 }" var="objects" varStatus="index">
                            			<li <c:if test="${objects.id eq object.id }">class="hover" </c:if>><a href="${ctx }/userportal/wapIndustryDetail.action?id=${objects.id}">·${objects.title}</a></li>
                            		</c:forEach>
                                </ul>
                            </div>
                            </li>
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
