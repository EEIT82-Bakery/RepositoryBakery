<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<nav class="navbar navbar navbar-fixed-top" role="navigation">
	<div class="container-fluid">
		<div class="navbar-header">
			<a href="${pageContext.request.contextPath}/back/product/ProductSelectAll.jsp">
				<img src="${pageContext.request.contextPath}/back/HtmlData/images/logo1.png" width="212" height="50" alt="" />
			</a>
		</div>
		<div id="navbar">
			<ul class="nav navbar-nav navbar-right">
			<c:if test="${empty loginback}">
				<li>
					<a href="${pageContext.request.contextPath}/back/login/login.jsp">登入</a>
				</li>
				</c:if>
				<c:if test="${not empty loginback}">
					<a href="${pageContext.request.contextPath}/front/member/login/login2.do?action=logout">登出</a>
				</c:if>
			</ul>
		</div>
	</div>
</nav>
<div class="container-fluid">
	<div class="row">
		<div class="col-xs-2 sidebar">
			<ul class="nav nav-sidebar">
				<li>
					<img src="${pageContext.request.contextPath}/back/HtmlData/images/logo.png" width="150" height="150" />
				</li>
				<li>
					<a href="${pageContext.request.contextPath}/back/product/ProductSelectAll.jsp">管理商品</a>
				</li>
				<li>
					<a href="${pageContext.request.contextPath}/back/orderlist/orderlist.jsp">管理訂單</a>
				</li>
				<li>
					<a href="${pageContext.request.contextPath}/DispalyAllReport.do">管理討論區 </a>
				</li>
				<li>
					<a href="${pageContext.request.contextPath}/BackAllMember.do?pages=1">維護會員權限</a>
				</li>
			</ul>
		</div>
	</div>
</div>
