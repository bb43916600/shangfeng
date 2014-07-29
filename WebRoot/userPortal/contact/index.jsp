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
<SCRIPT src="${ctx }/userPortal/js/jquery1.9.js" type="text/javascript"></SCRIPT>
<SCRIPT src="${ctx }/userPortal/js/jqnav.js" type="text/javascript"></SCRIPT>
<script src="${ctx }/userPortal/js/jsScrollbar.js" type="text/javascript"></script>
<script src="${ctx }/userPortal/js/jsScroller.js" type="text/javascript"></script>
<script type="text/javascript">
var scroller  = null;
var scrollbar = null;
window.onload = function () {
  scroller  = new jsScroller(document.getElementById("Scroller-1"), 186, 330);
  scrollbar = new jsScrollbar (document.getElementById("Scrollbar-Container"), scroller, false);
}

</script>
 
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
           	 <img src="${ctx }/userPortal/images/nban5.jpg" width="999" height="261"> </div>
            <!--nban end-->
			<div class="cls"></div>
			<!--nmain star-->
            <div class="nmain">
            <div class="right">
            	<div class="curr">
                	<span><a href="${ctx }/userportal/wapIndex.action" class="home">首页</a> > <i>各经营基地</i></span><h2>各经营基地<i>Each operating base</i></h2>
                </div>
                <div class="content">
                	<div class="about" style="padding-top:6px">
                    	${object.content }
					</div>
                </div>
            </div>
            	<div class="left">
                	<div class="sub_t">
               	    <img src="${ctx }/userPortal/images/sub_t6.jpg" width="229" height="54"> </div>
                    <div class="sub_m">
                   	  <ul class="sublist">
                        	<li class="hover"><a href="index.html">各经营基地</a>
                            <div class="subbox">
                            	<ul class="sub2list">
                            		<c:forEach items="${list }" var="objects">
                            			<li <c:if test="${objects.id eq object.id }">class="hover"</c:if>><a href="${ctx }/userportal/wapCompanyOutlookDetail.action?id=${objects.id }">·${objects.title }</a></li>
                            		</c:forEach>
                                </ul>
                            </div>
                            </li>
                           
                        </ul>
                        
                        <div class="left_contact">
                        	<img src="${ctx }/userPortal/images/tel.jpg" width="196" height="121">
                        	${object.content }
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
