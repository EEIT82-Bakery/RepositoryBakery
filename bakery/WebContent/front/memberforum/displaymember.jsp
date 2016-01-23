<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../fragment/css.jsp"%>
<style>
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
							<img src="data:image/png;base64,${member.mpicture}" class="img-responsive" alt="">
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
								<a href="${pageContext.request.contextPath}/homeindex.do?account=${member.account}">${member.account}</a>
							</div>
						</div>
						<div class="profile-userbuttons">
							<FORM METHOD="post" action="${pageContext.request.contextPath}/FriendServlet.controller">
								<c:if test="${isLogin.account == member.account}">
									<h1>${isLogin.kanban}</h1>
								</c:if>
								<c:if test="${isLogin.account != member.account}">
									<c:if test="${statu == -1 }">
										<button type="submit" class="btn btn-success btn-sm">加入好友</button>
									</c:if>
									<c:if test="${statu == 0}">
										<button type="button" class="btn btn-success btn-sm" ${statu==0 ?"disabled" :""}>已送出邀請</button>
									</c:if>
									<c:if test="${statu == 1}">
										<button type="button" class="btn1 btn-success btn-sm" ${statu==1 ?"disabled" :""}>已經是好友</button>
									</c:if>
								</c:if>
								<input type="hidden" name="invite_id" value="${isLogin.member_id}">
								<input type="hidden" name="invitee_id" value="${member.member_id}">
								<input type="hidden" name="msgtitle" value="${isLogin.username}">
								<input type="hidden" name="msgcount" value="${isLogin.username}">
								<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
								<input type="hidden" name="action" value="addfriend">
							</FORM>
						</div>
						<div class="profile-usermenu">
							<ul class="nav">
								<c:if test="${not empty isLogin}">
									<c:if test="${isLogin.member_id==member.member_id}">
										<li><a href="${pageContext.request.contextPath}/AllMemberServlet.do?account=${member.account}">
												<i class="glyphicon glyphicon-user"></i> 個人訊息
											</a></li>
									</c:if>
								</c:if>
								<c:if test="${not empty isLogin}">
									<c:if test="${isLogin.member_id==member.member_id}">
										<li><a href="${pageContext.request.contextPath}/FriendListServlet.do?invited=${isLogin.member_id}&pages=1">
												<i class="glyphicon glyphicon-list-alt"></i> 我的好友名單
											</a></li>
									</c:if>
								</c:if>
								<c:if test="${isLogin.member_id!=member.member_id}">
									<li><a href="${pageContext.request.contextPath}/FriendServlet.controller?action=friendlist&invited=${member.member_id}&pages=1">
											<i class="glyphicon glyphicon-list-alt"></i> 好友名單
										</a></li>
								</c:if>
								<c:if test="${not empty isLogin}">
									<c:if test="${isLogin.member_id==member.member_id}">
										<li><a href="${pageContext.request.contextPath}/MessageServlet.do?action=select&pages=1" target="_blank">
												<i class="glyphicon glyphicon-envelope"></i>我的信箱
											</a></li>
									</c:if>
								</c:if>

								<%-- 								<c:if test="${isLogin.member_id!=member.member_id}"> --%>
								<!-- 									<li><a -->
								<%-- 										href="${pageContext.request.contextPath}/MessageServlet.do?action=add&readid=${member.member_id}" --%>
								<!-- 										class="btn btn-primary btn-lg" data-toggle="modal" -->
								<!-- 										data-target="#ModalMessage"> <i -->
								<!-- 											class="glyphicon glyphicon-envelope"></i>發送訊息 -->
								<!-- 									</a></li> -->
								<%-- 								</c:if> --%>
								<c:if test="${statu==1	}">
								<c:if test="${isLogin.member_id!=member.member_id}">
									<li>
										<button class="btn btn-link btn-lg" data-toggle="modal" data-target="#ModalMessage">
											<span class="glyphicon glyphicon-envelope"></span> 訊息
										</button>

									</li>
								</c:if>
								</c:if>

							</ul>
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
						<div class="col-md-4">
							<p>我的個人資訊</p>
						</div>
						<div class="col-md-4 .col.md.offset-4">
							<form action="<c:url value='/MessageServlet.do'/>" method="post">
								<c:if test="${member.account  == isLogin.account}">
									<input type="button" name="action" value="新增" />
									<input type="button" name="action" value="修改" />
									<input type="button" name="action" value="刪除" />
								</c:if>
							</form>
						</div>
						<br>
						<div class="col-md-10">
							<p>我的狀態:${member.kanban}</p>
						</div>
						<div class="col-md-2"></div>
					</div>
				</div>
				<div class="col-xs-2">這裡是銷售排行`````````````````````````</div>
			</div>
		</c:if>
		<!-----------------------------------------main----------------------------------------->
		<!--------footer-------->
		<%@ include file="../fragment/footer.jsp"%>
		<!--------footer-------->
	</div>
	<div class="modal fade" id="ModalMessage" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
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
						<br />
						<br />
						<!-- input Sujet -->
						<div class="control-group">
							<label for="destinataire">訊息標題</label>
							<input name="msg_tit" type="text" class="form-control">
						</div>
						<br />
						<!-- TextArea Message -->
						<div class="control-group">
							<label for="destinataire">訊息內容</label>
							<textarea id="FormMessageMessage" name="msg_cont" class="form-control" rows="5"></textarea>
						</div>
						<br />
					</div>
					<div class="modal-footer">
						<div class="text-center">
							<button type="submit" class="btn btn-primary btn-lg">
								<span class="glyphicon glyphicon-send"></span> 送出
							</button>
							<input type="hidden" name="readid" value="${member.member_id}">
							<input type="hidden" name="action" value="addmsg">

							<button type="button" class="btn btn-default btn-xs" data-dismiss="modal">取消</button>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
	<%@ include file="../fragment/js.jsp"%>
</body>
</html>
