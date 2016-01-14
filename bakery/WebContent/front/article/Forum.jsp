<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../fragment/css.jsp"%>
<link href="${pageContext.request.contextPath}/front/HtmlData/css/articletop.css" rel="stylesheet" />
<style>
</style>
</head>
<body>
	<!-----------------------------------------nav------------------------------------------>
	<%@ include file="../fragment/nav.jsp"%>
	<!-----------------------------------------nav------------------------------------------>
	<div class="container-fluid">
		<!-----------------------------------------main----------------------------------------->
		<div class="row">
			<div class="col-xs-10">
				<!--文章種類-->
				<div class="col-xs-8 class">
					<a href="Forum.do" <c:if test="${empty param.ClassNo}">class="active"</c:if>>所有主題</a>
					<jsp:useBean id="articleSvc" scope="page" class="com.article.model.ArticleService" />
					<c:forEach var="articleClassBean" items="${articleSvc.articleClass}">
						<a href="Forum.do?ClassNo=${articleClassBean.articleClassNo}"
							class="${(articleClassBean.articleClassNo == param.ClassNo)?'active':''}">${articleClassBean.articleClassName}</a>
					</c:forEach>
				</div>
				<div class="col-xs-3 FindArticle center-block">
					<form action="<c:url value='/SearchArticle.do' />" method="post">
						<div class="input-group">
							<input type="text" class="form-control" id="FindArticle" name="articleTitle" placeholder="搜尋文章" value="${param.articleTitle}">
							<span class="input-group-btn">
								<button class="btn btn-default" type="submit">
									<span class="glyphicon glyphicon-search" aria-hidden="true"></span>
								</button>
							</span>
						</div>
					</form>
				</div>
				<div class="col-xs-1 post">

					<a href="${pageContext.request.contextPath}/front/article/InsertArticle.jsp?type=1">發表文章</a>
				</div>
				<div class="col-xs-12 display">
					<table>
						<tr class="forumtitle">
							<td width='15%'>文章總類</td>
							<td width='40%' style="text-align: left">標題</td>
							<td width='20%'>回覆/瀏覽</td>
							<td width='20%'>最後回覆</td>
						</tr>
						<c:forEach varStatus="stVar" var="articleBean" items="${displayArticle}">
							<!-- 用兩種顏色交替使用作為顯示商品資料的背景底色 -->
							<c:set var="rowColor" value="#F5DEB3" />
							<c:if test="${ stVar.count % 2 == 0 }">
								<c:set var="rowColor" value="#FFEFD5" />
							</c:if>
							<tr height='18' bgColor="${rowColor}">
								<td><a href="Forum.do?ClassNo=${articleBean.articleClassNo}">${articleBean.articleClassName}</a></td>
								<td style="text-align: left"><a href="DisplayArticle.do?articleId=${articleBean.articleId}">${articleBean.articleTitle}</a></td>
								<td>${articleBean.reArticleCount}/${articleBean.browserCount}<br /> <a
										href="${pageContext.request.contextPath}/homeindex.do?account=${articleBean.author}">${articleBean.author}</a>
								</td>
								<td>${articleBean.reArticleMakeDate}<br /> <a
										href="${pageContext.request.contextPath}/homeindex.do?account=${articleBean.reAuthor}">${articleBean.reAuthor}</a></td>
							</tr>
						</c:forEach>
					</table>
					<%@ include file="./include/page.jsp"%>
				</div>
				<div class="col-xs-2"></div>
			</div>
		</div>
		<!-----------------------------------------main----------------------------------------->
		<!--------footer-------->
		<%@ include file="../fragment/footer.jsp"%>
		<!--------footer-------->
	</div>
	<%@ include file="../fragment/js.jsp"%>
</body>
</html>
