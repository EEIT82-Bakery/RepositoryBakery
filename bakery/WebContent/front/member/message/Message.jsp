<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core"%>
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
	background-color: #e5e5e5;
	color: black;
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
			<h1>我的信件</h1>
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
					<tr class="forumtitle">
						<td width="20%">寄件人</td>
						<td width="40%">訊息標題</td>
						<td width="20%">寄送時間</td>
						<td width="20%">寄信狀態</td>
					</tr>
					<c:forEach varStatus="stVar" var="Go" items="${list}">
						<!-- 用兩種顏色交替使用作為顯示商品資料的背景底色 -->
						<c:set var="rowColor" value="#F5DEB3" />
						<c:if test="${ stVar.count % 2 == 0 }">
							<c:set var="rowColor" value="#FFEFD5" />
						</c:if>
						<tr height='18' bgColor="${rowColor}">
							<td style="height: 30px;">${Go.sendAccount}</td>
							<td><a href="${pageContext.request.contextPath}/MessageServlet.do?action=count&Msg_id=${Go.msg_id}">${Go.msg_tit}</a></td>
							<td>${Go.mdate}</td>

							<td><c:if test="${Go.msg_state==1}">

									<span style="font-weight: bold;">未讀</span>
								</c:if> <c:if test="${Go.msg_state==2}">
									<span style="font-weight: bold;">已讀</span>

								</c:if></td>
						</tr>
					</c:forEach>
				</table>
			</div>

			<c:if test="${not empty pageCount}">
				<c:forEach var="page" begin="1" end="${pageCount}">

					<a
						href="${pageContext.request.contextPath}/MessageServlet.do?action=select&pages=${page}">
						<c:out value="${page}" />
					</a>
				</c:forEach>
			</c:if>
		</div>
	</div>
</body>
</html>
