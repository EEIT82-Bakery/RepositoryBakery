<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<c:forEach var="element" items="${ss}">

	<tr>
		<td>${element.username}</td>
		<td>${element.sex}</td>
		<td>${element.phone}</td>
		<td>${element.address}</td>
	</tr>
	</c:forEach>
	
</body>
</html>