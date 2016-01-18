<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<nav class="navbar navbar navbar-fixed-top" role="navigation">
	<div class="container-fluid">
		<div class="navbar-header">
			<a href="bakerystage.jsp">
				<img src="${pageContext.request.contextPath}/back/HtmlData/images/logo1.png" width="212" height="50" alt="" />
			</a>
		</div>
		<div id="navbar">
			<ul class="nav navbar-nav navbar-right">
				<li>
					<a href="${pageContext.request.contextPath}/back/login/login.jsp">登入</a>
				</li>
			</ul>
		</div>
	</div>
</nav>
<div class="container-fluid">
	<div class="row">
		<div class="col-xs-2 sidebar">
			<ul class="nav nav-sidebar">
				<li>
					<img src="${pageContext.request.contextPath}/back/HtmlData/images/logo.png" width="150" height="150" />
				</li>
				<li>
					<a href="#">管理廣告</a>
				</li>
				<li>
					<a href="${pageContext.request.contextPath}/back/product/ProductSelectAll.jsp">管理商品</a>
				</li>
				<li>
					<a href="${pageContext.request.contextPath}/back/orderlist/orderlist.jsp">管理訂單</a>
				</li>
				<li>
					<a href="${pageContext.request.contextPath}/back/article/ForumManage.jsp" data-toggle="collapse" data-target="#demo" class="collapsed" aria-expanded="false">管理討論區 </a>
					<ul id="demo" class="collapse" aria-expanded="false" style="height: 0px;">
						<li>
							<a href="#">管理種類</a>
						</li>
						<li>
							<a href="#">管理文章</a>
						</li>
					</ul>
				</li>
				<li>
					<a href="#">維護員工資料</a>
				</li>
				<li>
			<a href="${pageContext.request.contextPath}/BackAllMember.do?pages=1">維護會員權限</a>
				</li>
			</ul>
		</div>
	</div>
</div>
