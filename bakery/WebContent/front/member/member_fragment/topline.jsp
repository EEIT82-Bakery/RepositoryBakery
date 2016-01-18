<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<style>
	.body {background-image:url(../images/Membertablebackground.jpg);}
   </style>

    <link href="${pageContext.request.contextPath}/front/member/css/non-responsive.css" rel="stylesheet" />
</head>
<body class="body">
	<div class="navbar navbar-default navbar-fixed-top">
		<div class="container-fluid">
			<div class="navbar-header">
			<a href="${pageContext.request.contextPath}/index.jsp">
				<img src="${pageContext.request.contextPath}/front/member/images/MemberTitle.png" height="50" />
				</a>
			</div>
			<a class="navbar-brand" href="#">Bakery</a>
			
		
			
			<div id="navbar" class="navbar-collapse collapse">
				<ul class="nav navbar-nav navbar-right">
					<c:if test="${empty isLogin}">
						<li><a href="<c:url value='../login/login.jsp'/>">登入</a></li>
					</c:if>
					<c:if test="${!empty isLogin}">
						<li><p>您好，${isLogin.username}</p></li>
						<li> <a href="<c:url value='../login/checklogout.jsp'/>">登出</a></li>
					</c:if>
					<c:if test="${empty isLogin}">
					<li><a href="../register/regis.jsp">會員註冊</a></li>
					</c:if>
					<li><a href="${pageContext.request.contextPath}/front/member/main/MemberPage.jsp">會員專區</a></li>
					<li><a href="#">購物車</a></li>
					<li><a href="#">聯絡我們</a></li>
					
					
				</ul>
		  
			</div>
			
			
			
			
		</div>
	</div>
	
	
</body>
</html>