<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8">
    <!-- This file has been downloaded from Bootsnipp.com. Enjoy! -->
    <title>Easy Sticky Footer - Bootsnipp.com</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="http://netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css" rel="stylesheet">
    <style type="text/css">
    

	.body {background-image:url(../images/Membertablebackground.jpg);}

    
    .navbar-login
{
    width: 305px;
    padding: 10px;
    padding-bottom: 0px;
}

.navbar-login-session
{
    padding: 10px;
    padding-bottom: 0px;
    padding-top: 0px;
}

.icon-size
{
    font-size: 87px;
}
.nav-navbar-nav{
	height: 80px;
}
.dropdown-toggle{
	background-color: white;

}        
    </style>
    <script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
    <script src="http://netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
</head>
<body>

	<div class="navbar navbar-default navbar-fixed-top" role="navigation">
		<div class="container">
			<div class="navbar-header">
				<a href="${pageContext.request.contextPath}/index.jsp"> <img
					src="${pageContext.request.contextPath}/front/member/images/MemberTitle.png"
					height="50" />
				</a>
			</div>

			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-collapse">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a href="${pageContext.request.contextPath}/index.jsp" class="navbar-brand">首頁</a>
			</div>
			<div class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li><a href="${pageContext.request.contextPath}/front/member/main/MemberPage.jsp">會員首頁</a></li>
					
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown">會員資訊 <span class="caret"></span>
					</a>
						<ul class="dropdown-menu">
							<li><a href="<c:url value='/front/member/main/CHGpassword.jsp' />">修改密碼</a></li>
							<li><a href="#">訂單查詢</a></li>
							<li> <a href="${pageContext.request.contextPath}/front/member/main/member.do?action=select&id=${isLogin.member_id}">會員資訊</a></li>
						</ul></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
				
					<li><a href="#">聯絡我們</a></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown"> <span><c:if
									test="${!empty isLogin}">

									<span><img style="width: 20px; height: 20px;"
										class="img-circle"
										src="data:image/png;base64,${isLogin.mpicture}" /></span>
								</c:if> </span>  <strong>${isLogin.username}</strong> <span
							class="glyphicon glyphicon-chevron-down"></span>

					</a>


						<ul class="dropdown-menu">
							<li>
								<div class="navbar-login">
									<div class="row">
										<div class="col-md-4">
											<p class="text-center">
												<c:if test="${!empty isLogin}">

													<span><img
														style="width: 87px; height: 87px; top: 0%;"
														src="data:image/png;base64,${isLogin.mpicture}" /></span>
												</c:if>
										</div>

										<div class="col-md-8">

											<p class="text-left">
												<strong>您好，${isLogin.username}</strong>
											</p>
											<p class="text-left small">${isLogin.email}</p>
											<p class="text-left smll">
												<a href="${pageContext.request.contextPath}/front/ShoppingCart/Cart.jsp" class="btn btn-info btn-lg"> <span
													class="glyphicon glyphicon-shopping-cart"></span>購物車
												</a>
											</p>

											<p class="text-left">
												<c:if test="${empty isLogin}">
													<li><a href="<c:url value='../login/login.jsp'/>"
														class="btn btn-primary btn-block btn-sm">登入</a></li>
												</c:if>
												<c:if test="${!empty isLogin}">
													<li><a
														href="<c:url value='../login/checklogout.jsp' />"
														class="btn btn-primary btn-block btn-sm">登出</a></li>
												</c:if>
											</p>

										</div>
									</div>
								</div>
							</li>
						</ul>
				</ul>
			</div>
		</div>
	</div>










	<script type="text/javascript">

</script>
</body>
</html>
