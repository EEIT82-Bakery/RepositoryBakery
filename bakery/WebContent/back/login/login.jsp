<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="../fragment/css.jsp"%>
</head>
<body>

	<div align="center">
	<img src="${pageContext.request.contextPath}/back/HtmlData/images/logo.png" width="200">
		<!-----------------------------------------main----------------------------------------->
		<h1 class="page-header">後台登入系統</h1>
		<div class="col-xs-2">
		</div>
		<div class="col-xs-3">
		</div>
<div class="col-xs-2">
		<FORM METHOD="post" action='<c:url value="/front/member/login/login2.do" />'>
		<input type="text" class="form-control" placeholder="帳號" autofocus name="account">
		${errors.account_error}
		${errors.account}
		${errorsmap.e_account}
		<br> <input type="password" class="form-control" placeholder="密碼" name="password">
		${errorsmap.e_password}
		${errorsmap.LoginError}
		${errorsmap.LoginStatus}
		<button class="btn btn-theme btn-block" type="submit" name="action" value="login">
			<i class="fa fa-lock"></i>登入
		</button>
		</FORM>
</div>
		<!-----------------------------------------main----------------------------------------->
	</div>

</body>
</html>