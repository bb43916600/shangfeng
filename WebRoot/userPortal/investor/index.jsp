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
           	 <img src="${ctx }/userPortal/images/nban4.jpg" width="999" height="261"> </div>
            <!--nban end-->
			<div class="cls"></div>
			<!--nmain star-->
            <div class="nmain">
            <div class="right">
            	<div class="curr">
                	<span><a href="${ctx }/userportal/wapIndex.action" class="home">首页</a> > <i>投资者关系</i></span><h2>投资者关系<i>Investor Relations</i></h2>
                </div>
                <div class="content">
                	<div class="investor">
                    <div class="investor_r">
                    	<h2><a href="notice.html">公司公告</a></h2>
                      <ul class="investor_r_list">
                      	<c:forEach items="${list }" var="objects">
                      		<li><a href="#">·${objects.title }</a></li> <%-- ${ctx }/userportal/wapInvestor.action?id=${objects.id } --%>
                      	</c:forEach>
                        </ul>
                        <h2><a href="#">实时资讯</a></h2>
                        <ul class="investor_r_list">
                            <c:forEach items="${list2 }" var="objects">
                      		<li><a href="#">·${objects.title }</a></li>
                      	</c:forEach>
                        </ul>
                      <h2><a href="${ctx }/userportal/wapExecutivesDetail.action?id=${object.id }">${object.title }</a></h2>
                         <p><img src="${ctx }/executivesImage/${object.images }" width="194" height="109"></p>
                         <p align="center" style=" line-height:44px">${object.content }</p>
                    </div>
                    	<div class="investor_l">
                    		<img src="http://image.sinajs.cn/newchart/min/n/sz000967.gif" width="528" height="319">
               				<img src="http://image.sinajs.cn/newchart/monthly/n/sz000967.gif" width="528" height="319">
               			</div>
                    </div>
                </div>
            </div>
            	<div class="left">
                	<div class="sub_t">
               	    <img src="${ctx }/userPortal/images/sub_t5.jpg" width="229" height="54"> </div>
                    <div class="sub_m">
                   	  <ul class="sublist">
                        	<li><a href="${ctx }/userportal/wapCompanyInfo.action">公司公告</a></li>
                            <li><a href="${ctx }/userportal/wapExecutives.action">高管信息</a></li>
                            <li><a href="#">实时资讯</a></li>
                            <li><a href="#">财务指标</a></li>
                            <li><a href="${ctx }/userportal/wapContact.action">投资者联系方式</a></li>
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
