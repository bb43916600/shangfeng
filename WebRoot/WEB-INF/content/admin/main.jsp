<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<frameset rows="127,*,11" frameborder="no" border="0" framespacing="0">
	<frame src="${ctx }/admin/top.action" name="topFrame" scrolling="no" noresize="noresize" id="topFrame" />
	<frame src="${ctx }/admin/center.action" name="mainFrame" id="mainFrame" />
	<frame src="${ctx }/admin/down.action" name="bottomFrame" scrolling="no" noresize="noresize" id="bottomFrame" />
</frameset>
<noframes>
<body>
</body>
</noframes>
</html>