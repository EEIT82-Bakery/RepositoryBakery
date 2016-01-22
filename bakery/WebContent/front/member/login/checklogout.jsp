<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="../member_fragment/css.jsp"%>
<style>
body {
	font-family: \5FAE\8EDF\6B63\9ED1\9AD4, Microsoft JhengHei, MingLiU,
		sans-serif;
	margin: 0;
}

#dd {
	max-width: 500px;
	min-width: 300px;
	min-height: 200px;
	text-align: center;
	margin: 0 auto;
	background: #fff;
	margin-top: 60px;
	position: relative;
	background-color: #F0F0F0;
}

h1 {
	background: #dcc95d;
	height: 50px;
	display: block;
	font-size: 1.5em;
	color: #fff;
	line-height: 50px;
}

#logout {
	width: 96%;
	margin: 35px auto;
	font-size: 1em;
	line-height: 1.7em;
	word-break: break-all;
	word-wrap: break-word;
}

#mypicture {
	width: 100px;
	height: 100 px;
	border: 3px solid silver;
	padding: 1px;
}
</style>

</head>
<body>
	<div id="dd">
		<h1>bakery</h1>
		<div id="logout">
			<c:if test="${!empty isLogin}">
				<c:if test="${not empty isLogin}">
					<img src="data:image/png;base64,${isLogin.mpicture}" id="mypicture" class="img-circle" alt="個人照片" />
				</c:if>
				<h5>${isLogin.username},您確定要登出嗎?</h5>
			</c:if>
			<p>
				點此
				<a href="javascript:history.go(-1)"> 返回 </a>
				繼續使用
			</p>
			<form name="frm" method="post" action="<c:url value='/front/member/login/login.do' /> ">
				<input type="hidden" name="action" value="logout"/>
				<input type="submit" class="btn btn-Default" value="登出"/>
			</form>
			<br/>
		</div>
	</div>
	<script>
		function logout() {
			document.frm.submit()
		}
	</script>
</body>
</html>