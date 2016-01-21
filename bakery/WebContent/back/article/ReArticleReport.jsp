<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="../fragment/css.jsp"%>
<link href="${pageContext.request.contextPath}/back/HtmlData/css/articletop.css" rel="stylesheet" />
<style>
.glyphicon {
	line-height: 1.42857143;
}

.ccc {
	margin: 10px;
}

.reportBtn {
	border: 1px #B22222 solid;
	text-decoration: none;
	color: white;
	background-color: #B22222;
	padding-top: 5px;
	padding-right: 10px;
	padding-bottom: 5px;
	padding-left: 10px;
	margin-right: 20px;
}

.reportBtn:hover {
	border: 1px #C29793 solid;
	color: black;
	text-decoration: none;
	background-color: #C29793;
	padding-top: 5px;
	padding-right: 10px;
	padding-bottom: 5px;
	padding-left: 10px;
	margin-right: 20px;
}
</style>
</head>
<body>
	<%@ include file="../fragment/main.jsp"%>
	<div class="col-xs-10 main">
		<!-----------------------------------------main----------------------------------------->
		<%
			int i = 1;
		%>
		<h1 class="page-header">管理檢舉回文</h1>
		<div class="row">
			<div class="col-xs-12 ccc">
				<a href="DispalyAllReport.do" class="reportBtn">檢舉文章</a>
				<a href="DispalyAllReReport.do" class="reportBtn">檢舉回文</a>
			</div>
			<div class="col-xs-10">
				<!--文章種類-->
				<div class="col-xs-8 class">
					<a href="DispalyAllReReport.do" <c:if test="${empty reportStatus}">class="active"</c:if>>所有主題</a>
					<a href="DispalyAllReReport.do?reportStatus=1" <c:if test="${reportStatus == 1}">class="active"</c:if>>未處理</a>
					<a href="DispalyAllReReport.do?reportStatus=2" <c:if test="${reportStatus == 2}">class="active"</c:if>>已處理</a>
				</div>
				<div class="col-xs-12 display">
					<table>
						<tr class="forumtitle">
							<td width='10%'>檢舉編號</td>
							<td width='20%'>檢舉人</td>
							<td width='10%'>回文編號</td>
							<td width='30%'>檢舉時間</td>
							<td width='20%'>處理狀態</td>
							<td width='10%'></td>
						</tr>
						<c:forEach varStatus="stVar" var="reArticleReport" items="${reArticleReport}">
							<!-- 用兩種顏色交替使用作為顯示商品資料的背景底色 -->
							<c:set var="rowColor" value="#F5DEB3" />
							<c:if test="${ stVar.count % 2 == 0 }">
								<c:set var="rowColor" value="#FFEFD5" />
							</c:if>
							<tr height='30' bgColor="${rowColor}">
								<td>${reArticleReport.reReportId}</td>
								<td>${reArticleReport.member.account}</td>
								<td>${reArticleReport.id}</td>
								<td><fmt:formatDate value="${reArticleReport.reportDate}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
								<td>${reArticleReport.reportStatuName}</td>
								<td><button type="button" data-toggle="modal" data-target="#myModal<%=++i%>">詳細資訊</button></td>
							</tr>
							<div class="modal fade" id="myModal<%=i%>" tabindex="-1" role="dialog" aria-labelledby="myModalLabel>" aria-hidden="true">
								<div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal">
												<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
											</button>
											<h4 class="modal-title" id="myModalLabel">檢舉明細</h4>
										</div>
										<div class="modal-body">
											<label for="person">檢舉人 : ${reArticleReport.member.account}</label>
											<br />
											<label for="content">檢舉內容 : ${reArticleReport.reportMsg}</label>
											<br />
											<label for="content">檢舉時間 : <fmt:formatDate value="${reArticleReport.reportDate}" pattern="yyyy-MM-dd HH:mm:ss" /></label>
											<br />
											<hr />
											<label for="content">文章編號: ${reArticleReport.reArticle.id}</label>
											<br />
											<label for="content">回文內容: ${reArticleReport.reArticle.reContent}</label>
											<br />
											<label for="content"><a href="${pageCotext.request.contextPath}/bakery/DisplayArticle.do?articleId=${reArticleReport.reArticle.articleId}" target=_blank>看完整文章</a></label>
											<br />
										</div>
										<div class="modal-footer">
											<form action="<c:url value='/Blockade.do'/>" method="post">
												<input type="hidden" name="Id" value="${reArticleReport.id}" />
												<c:if test="${reArticleReport.reportStatus == 1}">
													<button type="submit" class="btn btn-primary">封鎖文章</button>
												</c:if>
												<c:if test="${reArticleReport.reArticle.hidden == 2}">
													<button type="submit" class="btn btn-primary" disabled>已封鎖</button>
												</c:if>
												<button type="reset" class="btn btn-default" data-dismiss="modal">關閉</button>
											</form>
										</div>
									</div>
								</div>
							</div>
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