<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%--  <c:if test="${empty isLogin}"> --%>
<%-- 	<c:set var="memberAA" value="${pageContext.request.servletPath}" scope="session" /> --%>
<%-- 	<c:redirect url="/front/member/login/login.jsp" /> --%>
<%-- </c:if> --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link
	href="${pageContext.request.contextPath}/front/member/css/bootstrap.min.css"
	rel="stylesheet" />
<script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
<script
	src="http://netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>

<style type="text/css">
body {
	margin-top: 50px;
}

.glyphicon {
	margin-right: 10px;
}

.panel-body {
	padding: 0px;
}

.panel-body table tr td {
	padding-left: 15px
}

.panel-body .table {
	margin-bottom: 0px;
}
</style>

</head>
<body>
	<%@ include file="../member_fragment/topline.jsp"%>
	<div class="container">
		<div class="row">
			<div class="col-sm-3 col-md-3">
				<div class="panel-group" id="accordion">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h4 class="panel-title">
								<a data-toggle="collapse" data-parent="#accordion"
									href="#collapseOne"><span
									class="glyphicon glyphicon-folder-close"> </span>Content</a>
							</h4>
						</div>
						<div id="collapseOne" class="panel-collapse collapse in">
							<div class="panel-body">
								<table class="table">
									<tr>
										<td><span class="glyphicon glyphicon-pencil text-primary"></span><a
											href="http://www.jquery2dotnet.com">Articles</a></td>
									</tr>
									<tr>
										<td><span class="glyphicon glyphicon-flash text-success"></span><a
											href="http://www.jquery2dotnet.com">News</a></td>
									</tr>
									<tr>
										<td><span class="glyphicon glyphicon-file text-info"></span><a
											href="http://www.jquery2dotnet.com">Newsletters</a></td>
									</tr>
									<tr>
										<td><span
											class="glyphicon glyphicon-comment text-success"></span><a
											href="http://www.jquery2dotnet.com">Comments</a> <span
											class="badge">42</span></td>
									</tr>
								</table>
							</div>
						</div>
					</div>
					<div class="panel panel-default">
						<div class="panel-heading">
							<h4 class="panel-title">
								<a data-toggle="collapse" data-parent="#accordion"
									href="#collapseTwo"><span class="glyphicon glyphicon-th">
								</span>Modules</a>
							</h4>
						</div>
						<div id="collapseTwo" class="panel-collapse collapse">
							<div class="panel-body">
								<table class="table">
									<tr>
										<td><a href="#">Orders</a> <span
											class="label label-success">$ 320</span></td>
									</tr>
									<tr>
										<td><a href="#">Invoices</a></td>
									</tr>
									<tr>
										<td><a href="#">Shipments</a></td>
									</tr>
									<tr>
										<td><a href="#">Tex</a></td>
									</tr>
								</table>
							</div>
						</div>
					</div>
					<div class="panel panel-default">
						<div class="panel-heading">
							<h4 class="panel-title">
								<a data-toggle="collapse" data-parent="#accordion"
									href="#collapseThree"><span
									class="glyphicon glyphicon-user"> </span>Account</a>
							</h4>
						</div>
						<div id="collapseThree" class="panel-collapse collapse">
							<div class="panel-body">
								<table class="table">
									<tr>
										<td><a href="#">Change Password</a></td>
									</tr>
									<tr>
										<td><a href="#">Notifications</a> <span
											class="label label-info">5</span></td>
									</tr>
									<tr>
										<td><a href="#">Import/Export</a></td>
									</tr>
									<tr>
										<td><span class="glyphicon glyphicon-trash text-danger"></span><a
											href="http://www.jquery2dotnet.com" class="text-danger">
												Delete Account</a></td>
									</tr>
								</table>
							</div>
						</div>
					</div>
					<div class="panel panel-default">
						<div class="panel-heading">
							<h4 class="panel-title">
								<a data-toggle="collapse" data-parent="#accordion"
									href="#collapseFour"><span class="glyphicon glyphicon-file">
								</span>Reports</a>
							</h4>
						</div>
						<div id="collapseFour" class="panel-collapse collapse">
							<div class="panel-body">
								<table class="table">
									<tr>
										<td><span class="glyphicon glyphicon-usd"></span><a
											href="http://www.jquery2dotnet.com">Sales</a></td>
									</tr>
									<tr>
										<td><span class="glyphicon glyphicon-user"></span><a
											href="http://www.jquery2dotnet.com">Customers</a></td>
									</tr>
									<tr>
										<td><span class="glyphicon glyphicon-tasks"></span><a
											href="http://www.jquery2dotnet.com">Products</a></td>
									</tr>
									<tr>
										<td><span class="glyphicon glyphicon-shopping-cart"></span><a
											href="http://www.jquery2dotnet.com">Shopping Cart</a></td>
									</tr>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-sm-9 col-md-9">
				<div class="well">
					<h1>Accordion Menu With Icon</h1>
					Admin Dashboard Accordion Menu
				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript">
		
	</script>
</body>
</html>