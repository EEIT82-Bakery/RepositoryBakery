<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<nav class="navbar navbar-fixed-top" role="navigation">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="" href="${pageContext.request.contextPath}/index.jsp">
				<img src="${pageContext.request.contextPath}/front/HtmlData/images/logo.png" height="50">
			</a>
		</div>
		<ul class="nav navbar-nav navbar-right ">
			<c:if test="${empty isLogin}">
				<li>
					<a href="${pageContext.request.contextPath}/front/member/login/login.jsp">
						<span class="glyphicon glyphicon-log-in" aria-hidden="true">登入</span>
					</a>
				</li>
			</c:if>
				<c:if test="${!empty isLogin}">
			
					<li>
						<img style="width: 40px; height: 40px;" src="data:image/png;base64,${isLogin.mpicture}" />
					</li>
					<li>
						<h6>${isLogin.username}</h6>
					</li>
				
			</c:if>
			<c:if test="${empty isLogin}">
				<li>
					<a href="${pageContext.request.contextPath}/front/member/register/regis.jsp">
						<span class="glyphicon glyphicon-user" aria-hidden="true">註冊</span>
					</a>
				</li>
			</c:if>
			<c:if test="${!empty isLogin}">
				<li>
					<a href="${pageContext.request.contextPath}/front/member/login/checklogout.jsp">
						<span class="glyphicon glyphicon-user" aria-hidden="true">登出</span>
					</a>
				</li>
			</c:if>
			<li>
				<a href="${pageContext.request.contextPath}/front/member/main/MemberPage.jsp">
					<span class="glyphicon glyphicon-tower" aria-hidden="true"></span>
					會員中心
				</a>
			</li>
			<li>
				<a href="${pageContext.request.contextPath}/index.jsp">
					<span class="glyphicon glyphicon-home" aria-hidden="true">首頁</span>
				</a>
			</li>
			<li>
				<a href="${pageContext.request.contextPath}/front/ShoppingCart/Cart.jsp">
					<span class="glyphicon glyphicon-shopping-cart" aria-hidden="true">購物車</span>
				</a>
			</li>
			<li>
				<a href="#">
					<span class="glyphicon glyphicon-envelope" data-toggle="modal" data-target="#myModal">聯絡我們</span>
				</a>
			</li>
		</ul>
	</div>
	<div class="container-fluid">
		<div id="navbar2" class="row col-xs-12">
			<div class="col-xs-2">
				<a href="#">關於焙客栗</a>
			</div>
			<div class="col-xs-2">
				<a href="${pageContext.request.contextPath}/product2.controller?page=1">產品介紹</a>
			</div>
			<div class="col-xs-2">
				<a href="#">訂單查詢</a>
			</div>
			<div class="col-xs-2">
				<a href="${pageContext.request.contextPath}/front/map/map.html">地圖資訊</a>
			</div>
			<div class="col-xs-2">
				<a href="${pageContext.request.contextPath}/Forum.do">討論區</a>
			</div>
			<div class="col-xs-2">
				<a href="${pageContext.request.contextPath}/front/Entrancepage/Entrancepage1.jsp">活動專區</a>
			</div>
		</div>
	</div>
</nav>
<div class="modal fade" id="myModal" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="H1">
					感謝您拜訪焙客栗工坊網站，如果您有任何聯絡事項，<br /> 歡迎填寫下列表單， 我們將會用最快的速度與您聯絡。
				</h4>
			</div>
			<form action="<c:url value='/ContectUsServelt.do'/>" method="get">
				<div class="modal-body">
					<div class="form-group">
						<label for="person">連絡人</label>
						<input type="text" class="form-control" id="Text1" maxlength="10" placeholder="姓名" name="name"/>
					</div>
					<div class="form-group">
						<label for="email">E-mail</label>
						<input type="email" class="form-control" id="email" maxlength="30" placeholder="ex.laxsoivcv@gmail.com" name="email"/>
					</div>
					<div class="form-group">
						<label for="phone">連絡電話</label>
						<input type="text" class="form-control" id="Text" maxlength="15" placeholder="請填寫連絡電話或手機" name="Text"/>
					</div>
					<div class="form-group">
						<label for="content">內容</label>
						<textarea class="form-control" rows="5" placeholder="請填寫詢問內容" name="citus"></textarea>
					</div>
				</div>
				<div class="modal-footer">
					<input type="submit" class="btn btn-primary" value="發送" />
				</div>
			</form>
		</div>
	</div>
</div>



