<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!-- 最新編譯和最佳化的 CSS -->
<link href="../css/main.css" rel="stylesheet" />
<link href="../css/bootstrap.min.css" rel="stylesheet" />
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" />
<style type="text/css">
#mypicture {
	width: 100px;
	height: 100 px;
	border: 3px solid silver;
	padding: 1px;
}
</style>

</head>
<body>
	<!-- 	<ol> -->
	<%-- 		<c:if test="${!empty isLogin}"> --%>
	<%-- 		<c:if test="${not empty iii}"> --%>
	<!-- 				<li> -->
	<%-- 				<img style="width:50px;height:50px;" src="data:image/png;base64,${iii}" /> --%>
	<!-- 				</li> -->
	<!-- 				<li> -->
	<%-- 				<h6>會員姓名${isLogin.username}</h6> --%>
	<!-- 				</li> -->
	<%-- 				</c:if> --%>
	<%-- 			</c:if> --%>
	<!-- 			</ol> -->

	<center>

		<c:if test="${not empty iii}">
			<img src="data:image/png;base64,${iii}" id="mypicture" class="img-circle" alt="個人照片" />
		</c:if>
		<br>
		<c:if test="${!empty isLogin}">
		會員姓名:${isLogin.username}<br>
		會員暱稱:${isLogin.nickname}<br>
			<c:if test="${not empty sex}">
			性別:${sex}<br>
			</c:if>
			生日:${isLogin.birth}
			<br>
			地址:${isLogin.address}	
			<br>
			信箱:${isLogin.email}
			<br>
			手機號碼:${isLogin.phone}
		</c:if>
		<br> <br>
		<button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal">修改會員資料</button>

		<!-- Modal -->
		
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">&times;</span>
							<span class="sr-only">Close</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">修改我的資訊</h4>
					</div>
					<form action="<c:url value='/front/member/myimformation/test.controller' />" method="post">
					<div class="modal-body">

						
						暱稱:<input type="text" name="nickname" value="${isLogin.nickname}" />
						<br>
						<br> 
						生日:<input type="text" name="birth" value="${isLogin.birth}" />
						<br>
						<br> 
						手機:<input type="text" name="phone" value="${isLogin.phone}" />
						<br>
						<br> 
						信箱:<input type="text" name="email" value="${isLogin.email}" />
						<br>
						<br>
						 地址:<input type="text" name="address" value="${isLogin.address}" />
						<br>

					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">返回</button>
						<button type="submit" class="btn btn-primary" name="action" value="update">確定</button>
					</div>
					</form>
				</div>
			</div>
		</div>
	</center>
	<script src="../js/jquery-2.1.4.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</body>
</html>