<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="icon" href="/bakery/front/HtmlData/images/logo-icon.ico" />
<title>焙客栗工坊</title>
<style>
body {
	margin: 0;
	padding: 0;
	background: #faf1e0;
	font-family: \5FAE\8EDF\6B63\9ED1\9AD4, Microsoft JhengHei, MingLiU,
		sans-serif;
}

.frame {
	max-width: 500px;
	min-width: 300px;
	min-height: 200px;
	text-align: center;
	margin: 0 auto;
	background: #fff;
	border-radius: 10px;
	margin-top: 60px;
	position: relative;
	border:1px solid #B22222;
}

.frame i {
	width: 100px;
	height: 50px;
	display: block;
	position: absolute;
	top: 0;
	left: 0;
}

h1 {
	background: #B22222;
	height: 50px;
	width: 100%;
	margin-top: 0px;
	display: block;
	font-size: 1.5em;
	color: #fff;
	line-height: 50px;
	-webkit-border-top-left-radius: 10px;
	-webkit-border-top-right-radius: 10px;
	-moz-border-radius-topleft: 10px;
	-moz-border-radius-topright: 10px;
	border-top-left-radius: 10px;
	border-top-right-radius: 10px;
}

button {
	width: 200px;
	height: 35px;
	background: #e2e2e2;
	border: none;
	margin: 0 auto 25px auto;
	border-radius: 10px;
	-moz-border-radius: 10px;
	-webkit-border-radius: 10px;
	font-size: 1em;
	cursor: pointer;
}

button:hover, button:active, button:focus {
	background: #89D9F7;
}

p {
	width: 96%;
	margin: 35px auto;
	font-size: 1em;
	line-height: 1.7em;
	word-break: break-all;
	word-wrap: break-word;
}

@media screen and (max-device-width: 480px) {
	body {
		background: #fff;
	}
	.frame {
		margin-top: 0;
		border-radius: 0px;
		-webkit-border-radius: 0px;
		-moz-border-radius: 0px;
	}
	h1 {
		border-radius: 0px;
		-webkit-border-radius: 0px;
		-moz-border-radius: 0px;
	}
}
</style>
</head>
<body>
	<div class="frame">
		<h1>
			<i></i> 訊息
		</h1>
		<p>尚未登入，請先登入</p>
		<p></p>
		<a href="${pageContext.request.contextPath}/front/member/login/login.jsp">
			<button id="btn">確定</button>
		</a>
	</div>
</body>
</html>