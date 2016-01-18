<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
#bal{

	margin: 100px;

}


</style>
</head>
<body>
	<%@ include file="../member_fragment/nav.jsp"%>



<div id="bal">
	<form action="<c:url value='/front/member/login/login.do' />" method="post">
		<div>
			<h1>密碼查詢</h1>
		<label for="email">請輸入信箱</label> <input type="text"
				id="email" name="email">
			<span>${errors.email_error}</span>
		</div>
		<div>
			<label>請輸入帳號</label> <input type="text" id="account" name="account">
			<span>${errors.account_error}</span><br>
			<h3>${errors.account}</h3>
		</div>
	
		<input type="submit" name="action" value="select" />
	</form>
	
	</div>
	
	
	
</body>
</html>