<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>改個密碼</title>
</head>
<body>
<div>
	<form action="<c:url value='/front/member/main/ChangePassword.controller' />" method="post">
	<c:if test="${!empty isLogin}">
	帳號:<h4>${isLogin.account}</h4>
	</c:if>
	舊密碼:<input type="password" name="oldpassword" /> <br>
	<h5>${errors.oldpassword}</h5>
	新密碼:<input type="password" name="newpassword" />	<br>
	<h5>${errors.newpassword}</h5>
	確認新密碼:<input type="password" name="t_newpassword" /><br>
	<h5>${errors.newpassword}</h5>
	<input type="submit"  value="送出"/>
	<input type="hidden" name="action" value="ChangePassword" />

	
	</form>


</div>


</body>
</html>