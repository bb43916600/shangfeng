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
                	<span><a href="${ctx }/userportal/wapIndex.action" class="home">首页</a> > <i>招聘信息</i></span><h2>招聘信息<i>Jobs</i></h2>
                </div>
                <div class="content">
                	<div class="about de_job" style="padding-top:25px">
                    

                    	<table width="100%" border="0" class="job_table1">
 <tr>
    <td class="first">${object.jobName}</td>

  </tr>
  <tr>
    <td>所属部门：${object.department}</td>
   
  </tr>
  <tr>
    <td>工作地点：${object.site}</td>
    
  </tr>
  <tr>
    <td>招聘人数：${object.peopleNumber}人</td>

  </tr>
  <tr>
    <td>招聘时间：${object.startTime} - ${object.endTime}</td>
    
  </tr>
  <tr>
    <td>
    	职位描述：<br>
		${object.content}
    </td>
    
  </tr>
 
</table>
<p class="p">简历投递邮箱：${object.email}</p>

                        
                    </div>
                </div>
            </div>
            	<div class="left">
                	<div class="sub_t">
               	    <img src="${ctx }/userPortal/images/sub_t4.jpg" width="229" height="54"> </div>
                    <div class="sub_m">
                   	  <ul class="sublist">
                        	<li><a href="index.html">人才理念</a></li>
                            <li class="hover"><a href="job.html">招聘信息</a></li>
                            <li><a href="contact.html">联系方式</a></li>
                            <li><a href="activity.html">重大活动</a></li>
                            <li><a href="mien.html">员工风采</a></li>
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
