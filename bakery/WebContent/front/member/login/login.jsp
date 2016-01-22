<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ include file="../member_fragment/css.jsp"%>
<link href="../member_fragment/css/login.css" rel="stylesheet" />
</head>
<body>
	<%@ include file="../member_fragment/nav.jsp"%>
	<div class="container-fluid">
		<!-----------------------------------------main----------------------------------------->
		<div class="row">
			<div class="col-xs-9">
				<jsp:include page="/front/member/member_fragment/menu.jsp" />
			</div>
			<div class="col-xs-3">
				<form class="form-signin mg-btm" action="<c:url value="/front/member/login/login.do"/>" method="post">
					<h3 class="heading-desc">登入會員</h3>
					<div>
						<div class="main">
							<input type="text" name="account" class="form-control">
							<div>
								<p style="height: 20px; color: red;">${errorsmap.e_account}</p>
							</div>
							<input type="password" name="password" class="form-control" id="exampleInputPassword1">
							<div>
								<p style="height: 20px; color: red;">${errorsmap.e_password}</p>
								<p style="height: 25px; color: red;">${errorsmap.LoginError}</p>
							</div>
							<div class="col-xs-8 cx">
								<input type="checkbox" id="cx" name="check" value="記住帳號" />
								記住我
							</div>
							<div class="col-xs-4">
								<button type="submit" class="btn btn-large" name="action" value="login">登入</button>
							</div>
						</div>
					</div>
					<div class="social-box">
						<div class="row mg-btm">
							<div class="col-xs-12">
								<a href="#" class="btn btn-primary btn-block">
									<i class="icon-facebook"></i>Facebook 登入
								</a>
							</div>
						</div>
						<div class="row">
							<div class="col-xs-12">
								<a href="#" class="btn btn-info btn-block">
									<i class="icon-twitter"></i>Twitter 登入
								</a>
							</div>
						</div>
					</div>
					<div class="login-footer">
						<div class="row">
							<div class="col-xs-6">
								<div class="left-section">
									<a href="${pageContext.request.contextPath}/front/member/login/selectPassword.jsp">忘記密碼嗎?</a>
									<br>
									<a href="#">註冊新帳號 </a>
								</div>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	<!-----------------------------------------main----------------------------------------->
	</div>
	<%@ include file="../../fragment/js.jsp"%>
</body>
</html>
