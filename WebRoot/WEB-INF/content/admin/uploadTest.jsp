<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>upload Test</title>
</head>
<body>
	<form action="${ctx }/Test/login3.action" method="POST" enctype="multipart/form-data">  
   		 文件标题：<input type="text" name="title" size="50"/><br/>  
       	 选择文件：<input type="file" name="upload" size="50"/><br/>  
		<input type="submit" value=" 上传 "/>          
    </form>  
</body>
</html>