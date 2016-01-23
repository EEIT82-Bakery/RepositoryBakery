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
					<tr>
						<td width="20%">寄件人</td>
						<td width="40%">信件標題</td>
						<td width="20%">寄送時間</td>
					</tr>
					<tr>
						<td>${bean.send_id}</td>
						<td>${bean.msg_tit}</td>
						<td><fmt:formatDate value="${bean.msg_date}"
								pattern="yyyy-MM-dd HH:mm:ss" />
					</tr>
				</table>
<%-- 				<c:if test="${friendstatu.friendstatu==1}"> --%>
<!-- 					<p>已成為好友</p> -->
<%-- 				</c:if> --%>
<%-- 				<c:if test="${empty friendstatu.friendstatu}"> --%>
<!-- 					<p>已拒絕好友</p> -->
<%-- 				</c:if> --%>


				<c:if test="${friendstatu.friendstatu==0}">
					<h5 class="ui top attached header">信件內容</h5>
					<div class="ui attached segment">
						<pre>${bean.msg_cont} <a
								href="${pageContext.request.contextPath}/FriendServlet.controller?action=agree&invite_id=${bean.send_id}&invitee_id=${bean.read_id}&msg_date=${bean.msg_date}"
								onclick="$('>form',this).submit()">接受</a>/<a
								href="${pageContext.request.contextPath}/FriendServlet.controller?action=noagree&invite_id=${bean.send_id}&invitee_id=${bean.read_id}&msg_date=${bean.msg_date}"
								onclick="$('>form',this).submit()">拒絕</a>
						</pre>
						
					</div>
					</c:if>
					<c:if test="${friendstatu.friendstatu==1}">
					<h5>信件內容:</h5>
					
			
					<div class="ui attached segment">
						<pre>
						${bean.msg_cont}
						</pre>
					</div>	
					</c:if>
				
				<c:if test="${friendstatu.friendstatu==1}">
					<span>${message}</span>
				</c:if>
			</div>
		</div>
	</div>
</body>
</html>






