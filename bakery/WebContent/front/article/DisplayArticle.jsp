<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../fragment/css.jsp"%>
<%
	int i = 0;
%>
<link href="${pageContext.request.contextPath}/front/HtmlData/css/articletop.css" rel="stylesheet" />
<style>
.target-fix {
	position: absolute;
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
					<a href="${pageContext.request.contextPath}/front/forum/Forum.do">所有主題</a>
					<jsp:useBean id="articleClassSvc" scope="page" class="com.articleclass.model.ArticleClassService" />
					<c:forEach var="articleClass" items="${articleClassSvc.articleClass}">
						<a href="${pageContext.request.contextPath}/front/forum/Forum.do?ClassNo=${articleClass.articleClassNo}" class="${(articleClass.articleClassNo == Article.articleClassNo)?'active':''}">${articleClass.articleClassName}</a>
					</c:forEach>
				</div>
				<div class="col-xs-1 post">
					<form action="<c:url value='/front/forum/InsertArticle.do?type=2&articleId=${Article.articleId}'/>" method="post">
						<input type="hidden" name="title" value="${Article.articleTitle}" />
						<input type="submit" class="btn btn-default reArticle" value="回覆文章" />
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
					<img src="${pageContext.request.contextPath}/DBGifReader.do?memberId=${Article.member.member_id}" style="height: 200px; width: 100%" />
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
						<c:if test="${not empty isLogin.member_id && isLogin.member_id != Article.memberId && Article.hidden == 0}">
							<button type="button" data-toggle="modal" data-target="#ArticleModal">檢舉</button>
						</c:if>
					</div>
				</div>
				<div class="modal fade" id="ArticleModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">
									<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
								</button>
								<h4 class="modal-title" id="myModalLabel">檢舉文章</h4>
							</div>
							<div class="modal-body">
								<div class="form-group">
									<label for="person">檢舉人 :${isLogin.account}</label>
								</div>
								<div class="form-group">
									<label for="content">內容</label>
									<textarea class="form-control" rows="5" placeholder="請填寫檢舉內容" name="reportMsg"></textarea>
								</div>
							</div>
							<div class="modal-footer">
								<button type="button" data-dismiss="modal" onclick="addArticleReport(${Article.articleId},<%=i%>)" class="btn btn-primary">發送</button>
								<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
							</div>
						</div>
					</div>
				</div>
				<!--文章結束-->
				<br />
				<!--回文開始-->
				<!--標題-->
				<c:forEach var="reArticle" items="${reArticle}">
				<div style="display:none;"><%=i++%></div>
					<div class="col-xs-12 displaytitle">
						<a class="target-fix" name="${reArticle.reId + 1}F"></a>
						<p>#${reArticle.reId + 1} 回覆: ${Article.articleTitle}</p>
					</div>
					<!--個人資訊-->
					<div class="col-xs-2 profile">
						<div>
							<a href="${pageContext.request.contextPath}/homeindex.do?account=${reArticle.account}">${reArticle.account}</a>
							<span> (${reArticle.nickName})</span>
						</div>
						<img src="${pageContext.request.contextPath}/DBGifReader.do?memberId=${reArticle.member.member_id}" style="height: 200px; width: 100%;" />
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
							<c:if test="${not empty isLogin.member_id && isLogin.member_id != reArticle.memberId && reArticle.hidden == 0}">
								<button type="button" data-toggle="modal" data-target="#myModal<%=i%>">檢舉</button>
							</c:if>
						</div>
					</div>
					<!--回文結束-->
					<div class="modal fade" id="myModal<%=i%>" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal">
										<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
									</button>
									<h4 class="modal-title" id="myModalLabel">檢舉文章</h4>
								</div>
								<div class="modal-body">
									<div class="form-group">
										<label for="person">檢舉人 : ${isLogin.account}</label>
									</div>
									<div class="form-group">
										<label for="content">內容</label>
										<textarea class="form-control" rows="5" placeholder="請填寫檢舉內容" name="reportMsg"></textarea>
									</div>
								</div>
								<div class="modal-footer">
									<button type="button" data-dismiss="modal" onclick="addReArticleReport(${reArticle.id},<%=i%>)" class="btn btn-primary">發送</button>
									<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
								</div>
							</div>
						</div>
					</div>
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
		location.href = "${pageContext.request.contextPath}/front/forum/InsertArticle.do?type=3&articleId="+articleId;
	}
	function editReArticle(articleId , reId){
		location.href = "${pageContext.request.contextPath}/front/forum/InsertArticle.do?type=4&articleId="+articleId+"&reId="+reId;
	}
	function deleteArticle(articleId){
		if(confirm('確定刪除?')){
			location.href = "${pageContext.request.contextPath}/front/forum/DeleteArticle.do?type=1&articleId="+articleId;
		}
	}
	function deleteReArticle(articleId , reId){
		if(confirm('確定刪除?')){
			location.href = "${pageContext.request.contextPath}/front/forum/DeleteArticle.do?type=2&articleId="+articleId+"&reId="+reId;
		}
	}

	function addArticleReport(articleId,index){
		var reportMsg = document.getElementsByName("reportMsg")[index].value;
		xmlHttp = new XMLHttpRequest();
		if (xmlHttp != null) {
			xmlHttp.open("POST", "${pageContext.request.contextPath}/InsertReport.do", true);
			xmlHttp.addEventListener("readystatechange", callback, false);
			xmlHttp.setRequestHeader("Content-Type",
					"application/x-www-form-urlencoded")
			xmlHttp.send("articleId="+articleId +"&reportMsg=" + reportMsg);
		} else {
			alert("您得瀏覽器不支援Ajax的功能!!");
		}
		
		function callback() {
			if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
				alert(xmlHttp.responseText);
			}
		}
	}
	
	function addReArticleReport(Id,index){
		var reportMsg = document.getElementsByName("reportMsg")[index].value;
		xmlHttp = new XMLHttpRequest();
		if (xmlHttp != null) {
			xmlHttp.open("POST", "${pageContext.request.contextPath}/InsertReport.do", true);
			xmlHttp.addEventListener("readystatechange", callback, false);
			xmlHttp.setRequestHeader("Content-Type",
					"application/x-www-form-urlencoded")
			xmlHttp.send("Id="+Id+"&reportMsg="+reportMsg);
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
