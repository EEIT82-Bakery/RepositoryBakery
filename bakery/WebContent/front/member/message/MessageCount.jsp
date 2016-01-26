<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>信箱 - 焙客栗工坊</title>
<%@ include file="../../fragment/css.jsp"%>

<style>
.forumtitle {
	height: 29px;
	background-color: #B22222;
	color: white;
}

<
style>.forumtitle {
	height: 29px;
	background-color: #B22222;
	color: white;
}

.display {
	padding: 0;
}

.display table {
	width: 100%;
	text-align: center;
}

.demoPadder {
	padding-top: 15px;
}

.btn-default {
	color: #007aff;
	background-color: #fff;
	border-color: #007aff;
}

.btn-default:hover, .btn-default:focus, .btn-default:active,
	.btn-default.active, .open>.btn-default.dropdown-toggle {
	color: #fff;
	background-color: #007aff;
	border-color: #007aff;
}
</style>
</head>
<body>
	<%@ include file="../../fragment/nav.jsp"%>


	<div class="row">
		<div class="col-xs-offset-2 col-xs-8">


			<p>
				<a
					href="${pageContext.request.contextPath}/MessageServlet.do?action=select&pages=1">
					<i class="glyphicon glyphicon-envelope"></i>我的信箱
				</a>/信件內容
			</p>





			<div class="btn-group btn-group-justified demoPadder" role="group"
				aria-label="Justified button group">
				<a
					href="${pageContext.request.contextPath}/MessageServlet.do?action=select&pages=1"
					class="btn btn-default">全部</a> <a
					href="${pageContext.request.contextPath}/MessageServlet.do?action=selecread&statu=1&pages=1"
					class="btn btn-default">未讀</a> <a
					href="${pageContext.request.contextPath}/MessageServlet.do?action=selecNoRead&pages=1"
					class="btn btn-default">已讀</a>
			</div>
			<div class="col-xs-12 display">
				<table>

					<tr>
						<td width="20%" style="background-color: #e5e5e5;">寄件人</td>
						<td width="40%" style="background-color: #e5e5e5;">信件標題</td>
						<td width="20%" style="background-color: #e5e5e5;">寄送時間</td>
					</tr>
					<tr>
						<%-- 						<td style="background-color:white">${bean.send_id}</td> --%>
						<td style="background-color: white">${bean.sendNickname}</td>
						<td style="background-color: white">${bean.msg_tit}</td>
						<td style="background-color: white"><fmt:formatDate
								value="${bean.msg_date}" pattern="yyyy-MM-dd HH:mm:ss" />
					</tr>
					<tr>
						<td colspan="3" style="background-color: #e5e5e5" align="left">文章內容</td>
					</tr>
				</table>
				<span style="background-color: gray;"></span>
				<div class="ui attached segment">
					<div class="jumbotron" style="background-color: #fefefe; min-height: 100px;">
					<p style="font-size:14px;">${bean.msg_cont}</p>
					</div>
				</div>
				<c:if test="${friendstatu.friendstatu==1}">
					<span>${message}</span>
				</c:if>
			</div>
		</div>
	</div>
</body>
</html>






