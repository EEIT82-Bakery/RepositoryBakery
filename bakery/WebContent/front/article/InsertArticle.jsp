<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>

<html>
<head>
<%@ include file="../fragment/css.jsp"%>
<style>
.form-article {
	width: 625px;
	margin: 0 auto;
	margin-top: 20px;
	position: relative;
}

#form-class {
	width: 120px;
	display: inline;
	margin-bottom: 10px;
}

#form-title {
	width: 500px;
	display: inline;
	margin-bottom: 10px;
}

#cke_articleContent {
	width: 625px;
	margin-bottom: 10px;
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
}

.frame i {
	width: 100px;
	height: 50px;
	display: block;
	position: absolute;
	top: 0;
	left: 0;
}

.frame h1 {
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

.frame button {
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

.frame button:hover, .frame button:active, .frame button:focus {
	background: #89D9F7;
}

.frame p {
	width: 96%;
	margin: 35px auto;
	font-size: 1em;
	line-height: 1.7em;
	word-break: break-all;
	word-wrap: break-word;
}


</style>
</head>
<body>
	<!-----------------------------------------nav------------------------------------------>
	<%@ include file="../fragment/nav.jsp"%>
	<!-----------------------------------------nav------------------------------------------>
	<div class="container-fluid">
		<!-----------------------------------------main----------------------------------------->
		
		<c:if test="${param.type eq '1' || param.type eq '3'}">
			<form id="article" class="form-article" method="post"
				action="<c:url value='/InsertArticle.do?type=${param.type}&articleId=${param.articleId}' />">
				<p id="articleType"></p>
				<div class="form-group">
					<select id="form-class" name="articleClassNo" class="form-control">
						<jsp:useBean id="articleClassSvc" scope="page" class="com.articleclass.model.ArticleClassService"/>
						<option value='0'>請選擇種類</option>
						<c:forEach var="articleClass" items="${articleClassSvc.articleClass}">
							<option value="${articleClass.articleClassNo}" ${(articleClass.articleClassNo == articleBean.articleClassNo)?'selected':'' }>
								${articleClass.articleClassName}</option>
						</c:forEach>
					</select>
					<input type="text" id="form-title" name="articleTitle" class="form-control" placeholder="標題"
						value="${articleBean.articleTitle}" />
					<textarea id="articleContent" name="content" class="ckeditor" cols="50" rows="10">${articleBean.content}</textarea>
					<input type="submit" class="btn btn-default" value="發表文章" />
					<button id="celArticle" class="btn btn-default">取消發文</button>
				</div>
			</form>
		</c:if>

		<c:if test="${param.type eq '2' || param.type eq '4'}">
			<form id="article" class="form-article" method="post"
				action="<c:url value='/InsertArticle.do?type=${param.type}&articleId=${param.articleId}&reId=${param.reId}' />">
				<p id="articleType"></p>
				<div class="form-group">
					<p>RE:${articleTitle} ${reArticleBean.articleTitle}</p>
					<textarea id="articleContent" name="content" class="ckeditor" cols="50" rows="10">${reArticleBean.reContent}</textarea>
					<input type="submit" class="btn btn-default" value="發表文章" />
					<button id="celArticle" class="btn btn-default">取消發文</button>
				</div>
			</form>
		</c:if>

		<c:if test="${param.type ne '1' && param.type ne '2' && param.type ne '3' && param.type ne '4'}">
			<div class="frame">
				<h1>
					<i></i> 訊息
				</h1>
				<p>此頁面錯誤</p>
				<p></p>
				<a href="javascript:history.back()">
					<button id="btn">返回上一頁</button>
				</a>
			</div>
		</c:if>
		<!-----------------------------------------main----------------------------------------->
		<!--------footer-------->
		<%@ include file="../fragment/footer.jsp"%>
		<!--------footer-------->
	</div>
	<%@ include file="../fragment/js.jsp"%>
	<script>
		window.onload = function() {	
			
			var temp = 0;
			var type = ${param.type};
			if (type == 1) {
				document.getElementById('articleType').innerHTML = '發表文章';
			} else if (type == 2) {
				document.getElementById('articleType').innerHTML = '回覆文章';
			} else if (type == 3 || type == 4) {
				document.getElementById('articleType').innerHTML = '編輯文章';
			}
			
			document.getElementById("celArticle").onclick = function (){
				if(confirm('你確定要取消發文?')){
					history.back()
					return false;					
				}
				return false;
			};
			
			document.getElementById("article").onsubmit = function (){
				
				<c:if test="${param.type eq '1' || param.type eq '3'}">
				var articleClassNo = document.getElementsByName("articleClassNo")[0].value;
				var articleTitle = document.getElementsByName("articleTitle")[0].value;
				if(articleClassNo == 0){
					alert('請選擇文章分類');
					return false;
				}
				if(articleTitle.trim() === ''){
					alert('請填標題');
					return false;
				}
				</c:if>
				var content = RemoveHTML(CKEDITOR.instances.articleContent.getData());
				if(content.trim() == ''){
					alert('請填寫內容');
					return false;
				}
				if(temp == 1){
					alert('處理中，請稍候');
					return false;
				}
				if(confirm('確定發表?')){
					temp = 1;
					return true;
				}
				return false;
			};
			
		    function RemoveHTML(articleContent) {
		        return articleContent.replace(/<(?!img).*?>/ig, "");
		    }

		}
	</script>
	<script src="${pageContext.request.contextPath}/front/HtmlData/js/ckeditor/ckeditor.js"></script>
</body>
</html>
