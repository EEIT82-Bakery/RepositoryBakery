<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>404. That’s an error.</h3> 
	<p>The requested URL <%=request.getAttribute(RequestDispatcher.ERROR_REQUEST_URI) %> was not found on this server. That’s all we know.</p>
</body>
</html>