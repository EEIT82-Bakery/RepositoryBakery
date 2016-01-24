<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.orderlist.model.*"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<%
	OrderListJNDIDAO top3 = new OrderListJNDIDAO();
	List<OrderListBean> topphoto = top3.selectTop3();
	pageContext.setAttribute("photo3", topphoto);
%>
<%@ include file="../fragment/css.jsp"%>
<link
	href="${pageContext.request.contextPath}/front/HtmlData/css/articletop.css"
	rel="stylesheet" />
<style>
</style>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/front/HtmlData/css/custom.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/front/HtmlData/css/demo.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/front/HtmlData/css/slicebox.css">
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
					<a href="${pageContext.request.contextPath}/front/forum/Forum.do"
						<c:if test="${empty param.ClassNo}">class="active"</c:if>>所有主題</a>
					<jsp:useBean id="articleClassSvc" scope="page"
						class="com.articleclass.model.ArticleClassService" />
					<c:forEach var="articleClassBean"
						items="${articleClassSvc.articleClass}">
						<a
							href="${pageContext.request.contextPath}/front/forum/Forum.do?ClassNo=${articleClassBean.articleClassNo}"
							class="${(articleClassBean.articleClassNo == param.ClassNo)?'active':''}">${articleClassBean.articleClassName}</a>
					</c:forEach>
				</div>
				<div class="col-xs-3 FindArticle center-block">
					<form action="<c:url value='/front/forum/SearchArticle.do' />"
						method="post">
						<div class="input-group">
							<input type="text" class="form-control" id="FindArticle"
								name="articleTitle" placeholder="搜尋文章"
								value="${param.articleTitle}"> <span
								class="input-group-btn">
								<button class="btn btn-default" type="submit">
									<span class="glyphicon glyphicon-search" aria-hidden="true"></span>
								</button>
							</span>
						</div>
					</form>
				</div>
				<div class="col-xs-1 post">

					<a
						href="${pageContext.request.contextPath}/front/article/InsertArticle.jsp?type=1">發表文章</a>
				</div>
				<div class="col-xs-12 display">
					<table>
						<tr class="forumtitle">
							<td width='15%'>文章總類</td>
							<td width='40%' style="text-align: left">標題</td>
							<td width='20%'>回覆/瀏覽</td>
							<td width='20%'>最後回覆</td>
						</tr>
						<c:forEach varStatus="stVar" var="articleBean"
							items="${displayArticle}">
							<!-- 用兩種顏色交替使用作為顯示商品資料的背景底色 -->
							<c:set var="rowColor" value="#F5DEB3" />
							<c:if test="${ stVar.count % 2 == 0 }">
								<c:set var="rowColor" value="#FFEFD5" />
							</c:if>
							<tr height='18' bgColor="${rowColor}">
								<td><a
									href="${pageContext.request.contextPath}/front/forum/Forum.do?ClassNo=${articleBean.articleClassNo}">${articleBean.articleClassName}</a></td>
								<td style="text-align: left"><a
									href="${pageContext.request.contextPath}/front/forum/DisplayArticle.do?articleId=${articleBean.articleId}">${articleBean.articleTitle}</a></td>
								<td>${articleBean.reArticleCount}/${articleBean.browserCount}<br />
									<a
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
				<!-- 人氣商品 -->
				<table style="border: 3px #C29793 dashed;" border='1'>
					<tr>
						<td colspan="3" align="center"><b
							style="color: green; font-size: 25px;">人氣商品</b></td>
					</tr>
					<tr>
						<!-- 新增 -->
						<td>
							<div class="wrapper" style="width: 200px">
								<ul id="sb-slider" class="sb-slider">
									<c:forEach var="photo" items="${photo3}" varStatus="theCount">
										<li><img
											src="<%=request.getContextPath()%>/OrderListReaderServlet.do?productId=${photo.productId}"
											alt="image1" /></li>
									</c:forEach>
								</ul>
								<div id="shadow" class="shadow"></div>
								<div id="nav-arrows" class="nav-arrows">
									<a href="#">Next</a> <a href="#">Previous</a>
								</div>
							</div>
						</td>
						<!-- 新增結束 -->
					</tr>
				</table>
		</div>
		<!-----------------------------------------main----------------------------------------->
		<!--------footer-------->
		<%@ include file="../fragment/footer.jsp"%>
		<!--------footer-------->
	</div>
	<%@ include file="../fragment/js.jsp"%>
	<script
		src="${pageContext.request.contextPath}/front/HtmlData/js/jquery-2.1.4.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/front/HtmlData/js/modernizr.custom.46884.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/front/HtmlData/js/jquery.slicebox.js"></script>
	<script>
		$(function() {
			$(function() {
				var Page = (function() {
					var $navArrows = $('#nav-arrows').hide(), $navOptions = $(
							'#nav-options').hide(), $shadow = $('#shadow')
							.hide(), slicebox = $('#sb-slider').slicebox({
						autoplay : true,
						onReady : function() {
							$navArrows.show();
							$navOptions.show();
							$shadow.show();
						},
						orientation : 'h',
						cuboidsCount : 3
					}), init = function() {
						initEvents();
					}, initEvents = function() {
						// add navigation events
						$navArrows.children(':first').on('click', function() {
							slicebox.next();
							slicebox.play();
							return false;
						});
						$navArrows.children(':last').on('click', function() {
							slicebox.previous();
							slicebox.play();
							return false;
						});
					};
					return {
						init : init
					};
				})();
				Page.init();
			});
		})
	</script>
	<script>
		window.onload = function() {
			var num = 1;
			var index = 3;
			var duration = 2000;
			function autoShow() {
				for (var i = 1; i <= index; i++) {
					document.getElementById("con" + i).style.display = "none";
					document.getElementById("tab" + i).style.backgroundColor = "#AAAAAA";
				}
				num++;
				if (num > index) {
					num = 1;
				}
				document.getElementById("con" + num).style.display = "block";
				document.getElementById("tab" + num).style.backgroundColor = "black";
			}
			function show() {
				num = this.id.substr(3) - 1;
				autoShow();
			}
			function stopRun() {
				clearInterval(myInterval);
			}
			function run() {
				myInterval = setInterval(autoShow, duration);
			}
		}
	</script>
</body>
</html>
