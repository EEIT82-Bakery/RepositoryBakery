<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../fragment/css.jsp"%>
<link href="${pageContext.request.contextPath}/front/HtmlData/css/articletop.css" rel="stylesheet" />
<style>
.target-fix {
	position:absolute;
	margin-top: -91px;
}
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
				<div class="col-xs-11 class">
				<a class="target-fix" name="1F"></a>
					<a href="Forum.do">所有主題</a>
					<jsp:useBean id="articleSvc" scope="page" class="com.article.model.ArticleService" />
					<c:forEach var="articleClass" items="${articleSvc.articleClass}">
						<a href="Forum.do?ClassNo=${articleClass.articleClassNo}" class="${(articleClass.articleClassNo == Article.articleClassNo)?'active':''}">${articleClass.articleClassName}</a>
					</c:forEach>
				</div>
				<div class="col-xs-1 post">
					<form action="<c:url value='/InsertArticle.do?type=2&articleId=${Article.articleId}'/>" method="post">
						<input type="hidden" name="title" value="${Article.articleTitle}" /> <input type="submit" class="btn btn-default reArticle" value="回覆文章" />
					</form>
				</div>
				<!--文章開始-->
				<!--標題-->
				<div class="col-xs-12 displaytitle">
					<p>#1 ${Article.articleTitle}</p>
				</div>
				<!--個人資訊-->
				<div class="col-xs-2 profile">
					<div>
						<a href="${pageContext.request.contextPath}/homeindex.do?account=${Article.author}">${Article.author}</a>
						<span> (${Article.nickName})</span>
					</div>
					<img src="data:image/png;base64,${Article.picture}" style="height: 200px; width: 100%" />
				</div>
				<!--時間&內容-->
				<div class="col-xs-10">
					<div class="top">
						<span>發表於：${Article.articleMakeDate}</span>
					</div>
					<!--內容-->
					<div class="content">${Article.content}</div>
				</div>
				<div class="col-xs-12 articleEnd">
					<div class="col-xs-2"></div>
					<div class="col-xs-10 ed">
						<c:if test="${isLogin.member_id == Article.memberId && Article.hidden == 0}">
							<button onclick="editArticle(${Article.articleId})">編輯</button>
							<button onclick="deleteArticle(${Article.articleId})">刪除</button>
						</c:if>
						<c:if test="${isLogin.member_id != Article.memberId && Article.hidden == 0}">
							<button onclick="">檢舉</button>
							<button onclick="addArticleCollection(${Article.articleId})">收藏</button>
						</c:if>
					</div>
				</div>
				<!--文章結束-->
				<br />

				<!--回文開始-->
				<!--標題-->
				<c:forEach var="reArticle" items="${reArticle}">
					<div class="col-xs-12 displaytitle">
					<a class="target-fix" name="${reArticle.reId + 1}F"></a>
						<p>#${reArticle.reId + 1} 回覆: ${Article.articleTitle}</p>
					</div>
					<!--個人資訊-->
					<div class="col-xs-2 profile">
						<div>
							<a href="${pageContext.request.contextPath}/homeindex.do?account=${reArticle.account}">${reArticle.account}</a>
							<span> (${Article.nickName})</span>
						</div>
						<img src="data:image/png;base64,${reArticle.picture}" style="height: 200px; width: 100%;" />
					</div>
					<!--時間&內容-->
					<div class="col-xs-10">
						<div class="top">
							<span>發表於：${reArticle.reArticleMakeDate}</span>
						</div>
						<!--內容-->
						<div class="content">${reArticle.reContent}</div>
					</div>
					<div class="col-xs-12 articleEnd">
						<div class="col-xs-2"></div>
						<div class="col-xs-10 ed">
							<c:if test="${isLogin.member_id == reArticle.memberId && reArticle.hidden == 0}">
								<button onclick="editReArticle(${Article.articleId},${reArticle.reId})">編輯</button>
								<button onclick="deleteReArticle(${Article.articleId},${reArticle.reId})">刪除</button>
							</c:if>
							<c:if test="${isLogin.member_id != reArticle.memberId && reArticle.hidden == 0}">
								<button onclick="">檢舉</button>
							</c:if>
						</div>
					</div>
					<!--回文結束-->
				</c:forEach>
			</div>
			<div class="col-xs-2">未定</div>
		</div>
		<!-----------------------------------------main----------------------------------------->
		<!--------footer-------->
		<%@ include file="../fragment/footer.jsp"%>
		<!--------footer-------->
	</div>
	<%@ include file="../fragment/js.jsp"%>
	<script>
	function editArticle(articleId){
		location.href = "${pageContext.request.contextPath}/InsertArticle.do?type=3&articleId="+articleId;
	}
	function editReArticle(articleId , reId){
		location.href = "${pageContext.request.contextPath}/InsertArticle.do?type=4&articleId="+articleId+"&reId="+reId;
	}
	function deleteArticle(articleId){
		location.href = "${pageContext.request.contextPath}/DeleteArticle.do?type=1&articleId="+articleId;
	}
	function deleteReArticle(articleId , reId){
		location.href = "${pageContext.request.contextPath}/DeleteArticle.do?type=2&articleId="+articleId+"&reId="+reId;
	}
	
	function addArticleCollection(articleId){
		xmlHttp = new XMLHttpRequest();
		if (xmlHttp != null) {
			xmlHttp.open("POST", "${pageContext.request.contextPath}/InsertArticleCollection.do", true);
			xmlHttp.addEventListener("readystatechange", callback, false);
			xmlHttp.setRequestHeader("Content-Type",
					"application/x-www-form-urlencoded")
			xmlHttp.send("articleId="+articleId);
		} else {
			alert("您得瀏覽器不支援Ajax的功能!!");
		}
		
		function callback() {
			if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
				alert(xmlHttp.responseText);
			}
		}
	}
	</script>
</body>
</html>
