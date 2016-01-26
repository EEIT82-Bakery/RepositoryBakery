<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.orderlist.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.articleclass.model.*"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../fragment/css.jsp"%>
<%
	OrderListJNDIDAO top3 = new OrderListJNDIDAO();
	List<OrderListBean> topphoto = top3.selectTop3();
	pageContext.setAttribute("photo3", topphoto);
%>
<style>
.display {
	padding: 0;
}

.display table {
	width: 100%;
	text-align: center;
}

.forumtitle {
	height: 29px;
	background-color: #B22222;
	color: white;
}
/* Profile container */
.profile {
	margin: 20px 0;
}

/* Profile sidebar */
.profile-sidebar {
	padding: 20px 0 10px 0;
	background: #fff;
	min-height: 492px;
}

.profile-userpic img {
	float: none;
	margin: 0 auto;
	width: 180px;
	height: 197px;
	-webkit-border-radius: 50% !important;
	-moz-border-radius: 50% !important;
	border-radius: 50% !important;
}

.profile-usertitle {
	text-align: center;
	margin-top: 20px;
}

.profile-usertitle-name {
	color: #5a7391;
	font-size: 16px;
	font-weight: 600;
	margin-bottom: 7px;
}

.profile-usertitle-job {
	color: #5b9bd1;
	font-size: 12px;
	font-weight: 600;
	margin-bottom: 15px;
}

.profile-userbuttons {
	text-align: center;
	margin-top: 10px;
}

.profile-userbuttons .btn {
	text-transform: uppercase;
	font-size: 11px;
	font-weight: 600;
	padding: 6px 15px;
	margin-right: 5px;
}

.profile-userbuttons .btn1 {
	text-transform: uppercase;
	font-size: 11px;
	font-weight: 600;
	padding: 6px 15px;
	margin-right: 5px;
}

.profile-userbuttons .btn:last-child {
	margin-right: 0px;
}

.profile-usermenu {
	margin-top: 20px;
}

.profile-usermenu ul li {
	border-bottom: 1px solid #f0f4f7;
}

.profile-usermenu ul li:last-child {
	border-bottom: none;
}

.profile-usermenu ul li a {
	color: #93a3b5;
	font-size: 14px;
	font-weight: 400;
}

.profile-usermenu ul li a i {
	margin-right: 8px;
	font-size: 14px;
}

.profile-usermenu ul li a:hover {
	background-color: #fafcfd;
	color: #5b9bd1;
}

.profile-usermenu ul li.active {
	border-bottom: none;
}

.profile-usermenu ul li.active a {
	color: #5b9bd1;
	background-color: #f6f9fb;
	border-left: 2px solid #5b9bd1;
	margin-left: -2px;
}

/* Profile Content */
.profile-content {
	padding: 20px;
	background: #fff;
	min-height: 492px;
}
</style>
<link rel="stylesheet" type="text/css"
	href="<c:url value="front/HtmlData/css/custom.css"/>">
<link rel="stylesheet" type="text/css"
	href="<c:url value="front/HtmlData/css/demo.css"/>">
<link rel="stylesheet" type="text/css"
	href="<c:url value="front/HtmlData/css/slicebox.css"/>">
</head>
<body>
	<!-----------------------------------------nav------------------------------------------>
	<%@ include file="../fragment/nav.jsp"%>
	<!-----------------------------------------nav------------------------------------------>
	<div class="container-fluid">
		<!-----------------------------------------main----------------------------------------->
		<c:if test="${not empty member}">
			<div class="row profile">
				<div class="col-xs-3">
					<div class="profile-sidebar">
						<div class="profile-userpic">
							<img
								src="${pageContext.request.contextPath}/DBGifReader.do?memberId=${member.member_id}"
								class="img-responsive" alt="">
						</div>
						<div class="profile-usertitle">
							<div class="profile-usertitle-name">暱稱:${member.nickname}</div>
							<div class="profile-usertitle-job">
								帳號:
								<c:url value="/front/memberforum/2.jsp" var="path">
									<c:param name="accoun" value="${member.account}" />
									<c:param name="nickname" value="${member.nickname}" />
									<c:param name="sex" value="${member.sex}" />
									<c:param name="birth" value="${member.birth}" />
									<c:param name="phone" value="${member.phone}" />
									<c:param name="phone" value="${member.phone}" />
									<c:param name="phone" value="${member.phone}" />
								</c:url>
								<a
									href="${pageContext.request.contextPath}/homeindex.do?account=${member.account}">${member.account}</a>
							</div>
						</div>
						<div class="col-xs-12">
							<c:if test="${isLogin.account == member.account}">
								<div class="profile-usertitle-name">自我介紹：${isLogin.kanban}</div>
							</c:if>
							<c:if test="${isLogin.account!=member.account}">
								<div class="profile-usertitle-name">自我介紹：${member.kanban}</div>
							</c:if>

							<div class="profile-userbuttons">
								<div class="col-xs-6">
									<FORM METHOD="post"
										action="${pageContext.request.contextPath}/FriendServlet.controller">

										<c:if test="${isLogin.account != member.account}">
											<c:if test="${statu == -1 }">
												<button type="submit" class="btn btn-success btn-sm">加入好友</button>
											</c:if>
											<c:if test="${statu == 0}">
												<button type="button" class="btn btn-success btn-sm"
													${statu==0 ?"disabled" :""}>已邀請</button>
											</c:if>
											<c:if test="${statu == 1}">
												<button type="button" class="btn1 btn-success btn-sm"
													${statu==1 ?"disabled" :""}>已是好友</button>
											</c:if>
										</c:if>
										<input type="hidden" name="invite_id"
											value="${isLogin.member_id}"> <input type="hidden"
											name="invitee_id" value="${member.member_id}"> <input
											type="hidden" name="msgtitle" value="${isLogin.username}">
										<input type="hidden" name="msgcount"
											value="${isLogin.username}"> <input type="hidden"
											name="requestURL" value="<%=request.getServletPath()%>">
										<input type="hidden" name="action" value="addfriend">
									</FORM>
								</div>
								<div class="col-xs-6">
									<c:if test="${isLogin.member_id!=member.member_id}">
										<button class="btn btn-primary btn-sm" data-toggle="modal"
											data-target="#ModalMessage">
											<span class="glyphicon glyphicon-envelope"></span> 訊息
										</button>
									</c:if>
								</div>
							</div>
						</div>
						<div class="col-xs-12">
							<div class="profile-usermenu">
								<ul class="nav">
									<c:if test="${not empty isLogin}">
										<c:if test="${isLogin.member_id==member.member_id}">
											<li><a
												href="${pageContext.request.contextPath}/AllMemberServlet.do?account=${isLogin.account}">
													<i class="glyphicon glyphicon-user"></i> 個人訊息
											</a></li>
										</c:if>
									</c:if>
									<c:if test="${not empty isLogin}">
										<c:if test="${isLogin.member_id==member.member_id}">
											<li><a
												href="${pageContext.request.contextPath}/FriendListServlet.do?invited=${isLogin.member_id}&pages=1">
													<i class="glyphicon glyphicon-list-alt"></i> 我的好友名單
											</a></li>
										</c:if>
									</c:if>
									<c:if test="${isLogin.member_id!=member.member_id}">
										<li><a
											href="${pageContext.request.contextPath}/FriendServlet.controller?action=friendlist&invited=${member.member_id}&pages=1">
												<i class="glyphicon glyphicon-list-alt"></i> 好友名單
										</a></li>
									</c:if>
									<c:if test="${not empty isLogin}">
										<c:if test="${isLogin.member_id==member.member_id}">
											<li><a
												href="${pageContext.request.contextPath}/MessageServlet.do?action=select&pages=1">
													<i class="glyphicon glyphicon-envelope"></i>我的信箱
											</a></li>
										</c:if>
									</c:if>
								</ul>
							</div>
						</div>
					</div>

				</div>
				<div class="col-xs-7">
					<div class="profile-content">
						<c:forEach var="element" items="${ss}">
							<tr>
								<td>${element.username}</td>
								<td>${element.sex}</td>
								<td>${element.phone}</td>
								<td>${element.address}</td>
							</tr>
						</c:forEach>
						<div class="col-xs-12 display">
							<p>近期動態</p>
							<table>
								<tr class="forumtitle">
									<td width='20%'>文章總類</td>
									<td width='50%' style="text-align: left">標題</td>
									<td width='30%'>回覆/瀏覽</td>
								</tr>
								<c:forEach varStatus="stVar" var="articleBean"
									items="${displayArticle}">
									<!-- 用兩種顏色交替使用作為顯示商品資料的背景底色 -->
									<c:set var="rowColor" value="#F5DEB3" />
									<c:if test="${ stVar.count % 2 == 0 }">
										<c:set var="rowColor" value="#FFEFD5" />
									</c:if>
									<tr height='18' bgColor="${rowColor}">
										<td><c:if test="${articleBean.articleClassNo==1}">糕點推薦</c:if>
											<c:if test="${articleBean.articleClassNo==2}">糕點DIY</c:if> <c:if
												test="${articleBean.articleClassNo==3}">技巧分享</c:if></td>
										<td style="text-align: left"><a
											href="${pageContext.request.contextPath}/front/forum/DisplayArticle.do?articleId=${articleBean.articleId}">${articleBean.articleTitle}</a></td>
										<td>${articleBean.reArticleCount}/${articleBean.browserCount}<br />
											<a
											href="${pageContext.request.contextPath}/homeindex.do?account=${articleBean.author}">${articleBean.author}</a>
										</td>
									</tr>
								</c:forEach>
							</table>
						</div>
					</div>
				</div>
				<div class="col-xs-2">
					<table>
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
			</div>
		</c:if>
		<!-----------------------------------------main----------------------------------------->
		<!--------footer-------->
		<%@ include file="../fragment/footer.jsp"%>
		<!--------footer-------->
	</div>
	<div class="modal fade" id="ModalMessage" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<form method="post" action='<c:url value="/MessageServlet.do"/>'>
				<div class="modal-content">
					<div class="modal-header btn-primary">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">×</span><span class="sr-only">Close</span>
						</button>
						<h4 class="text-center" id="myModalLabel">發送訊息</h4>
					</div>
					<div class="modal-body">
						<br /> <br />
						<!-- input Sujet -->
						<div class="control-group">
							<label for="destinataire">訊息標題</label> <input name="msg_tit"
								type="text" class="form-control">
						</div>
						<br />
						<!-- TextArea Message -->
						<div class="control-group">
							<label for="destinataire">訊息內容</label>
							<textarea id="FormMessageMessage" name="msg_cont"
								class="form-control" rows="5"></textarea>
						</div>
						<br />
					</div>
					<div class="modal-footer">
						<div class="text-center">
							<button type="submit" class="btn btn-primary btn-lg">
								<span class="glyphicon glyphicon-send"></span> 送出
							</button>
							<input type="hidden" name="readid" value="${member.member_id}">
							<input type="hidden" name="action" value="addmsgg">

							<button type="button" class="btn btn-default btn-xs"
								data-dismiss="modal">取消</button>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
	<%@ include file="../fragment/js.jsp"%>
	<script
		src="${pageContext.request.contextPath}/front/HtmlData/js/jquery-2.1.4.min.js"></script>
	<script type="text/javascript"
		src="<c:url value="front/HtmlData/js/modernizr.custom.46884.js"/>"></script>
	<script type="text/javascript"
		src="<c:url value="front/HtmlData/js/jquery.slicebox.js"/>"></script>
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
