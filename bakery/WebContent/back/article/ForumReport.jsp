<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="../fragment/css.jsp"%>
<link href="${pageContext.request.contextPath}/back/HtmlData/css/articletop.css" rel="stylesheet" />
<style>
.glyphicon {
    line-height: 1.42857143;
   }
</style>
</head>
<body>
	<%@ include file="../fragment/main.jsp"%>
	<div class="col-xs-10 main">
		<!-----------------------------------------main----------------------------------------->
		<h1>管理討論區</h1>
		<div class="row">
			<div class="col-xs-10">
				<!--文章種類-->
				<div class="col-xs-8 class">
					<a href="DispalyAllReport.do" <c:if test="${empty reportStatus}">class="active"</c:if>>所有主題</a>
					<a href="DispalyAllReport.do?reportStatus=1" <c:if test="${reportStatus == 1}">class="active"</c:if>>未處理</a>
					<a href="DispalyAllReport.do?reportStatus=2" <c:if test="${reportStatus == 2}">class="active"</c:if>>處理中</a>
					<a href="DispalyAllReport.do?reportStatus=3" <c:if test="${reportStatus == 3}">class="active"</c:if>>已處理</a>
				</div>
				<div class="col-xs-12 display">
					<table>
						<tr class="forumtitle">
							<td width='10%'>檢舉編號</td>
							<td width='20%'>檢舉人</td>
							<td width='10%'>文章編號</td>
							<td width='30%'>檢舉時間</td>
							<td width='30%'>處理狀態</td>
						</tr>
						<c:forEach varStatus="stVar" var="articleReport" items="${articleReport}">
							<!-- 用兩種顏色交替使用作為顯示商品資料的背景底色 -->
							<c:set var="rowColor" value="#F5DEB3" />
							<c:if test="${ stVar.count % 2 == 0 }">
								<c:set var="rowColor" value="#FFEFD5" />
							</c:if>
							<tr height='30' bgColor="${rowColor}">
								<td>${articleReport.articleReportId}</td>
								<td>${articleReport.member.account}</td>
								<td>${articleReport.articleId}</td>
								<td>${articleReport.reportDate}</td>
								<td>${articleReport.reportStatuName}</td>
							</tr>
						</c:forEach>
					</table>
				</div>
				<div class="col-xs-2"></div>
			</div>
		</div>
		<!-----------------------------------------main----------------------------------------->
	</div>
	<%@ include file="../fragment/js.jsp"%>
</body>
</html>