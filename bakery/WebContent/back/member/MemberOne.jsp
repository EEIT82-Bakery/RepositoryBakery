<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script type="text/javascript" src="../HtmlData/js/jquery-2.1.4.min.js"></script>
<style>
#xxxx {
	width: 50%;
	height: 50%
}

#mypicture {
	width: 400px;
	height: 400px;
}
</style>
<%@ include file="../fragment/css.jsp"%>
</head>
<body>
	<%@ include file="../fragment/main.jsp"%>

	<div class="col-sm-10  col-sm-offset-10 col-sm-4 placeholder">
		<a href="javascript:history.go(-1)"> 返回上一頁</a>
	</div>
	<div class="col-sm-9 col-sm-offset-4 col-md-10 col-md-offset-2 main">

		<h1 class="page-header">會員詳細資料</h1>
		<div class="row">
			<div class="col-md-5">
				<img src="data:image/png;base64,${param.mpicture}" id="mypicture"
					class="img-rounded" alt="個人照片" />
					<h3>會員ID：${param.memberId}</h3>
					<h3>會員姓名：${param.username}</h3>
</div>
<div class="col-md-6">
				<table class="table table-striped">
					<thead>
						<tr>
							<th>訂單編號</th>
							<th>出貨日期</th>
							<th>付款狀態</th>
						</tr>
					</thead>
					
					<c:forEach var="Go" items="${aBean}">
					
						<tbody>
							<tr>
								<td>${Go.orderId}</td>
								<td>${Go.shipDate}</td>
								<c:if test="${Go.orderStaus==1}">
									<td style="color:red;">未付款</td>
								</c:if>
								<c:if test="${Go.orderStaus==2}">
									<td style="color:black;">已付款</td>
								</c:if>
								<c:if test="${Go.orderStaus==3}">
									<td style="color:blue;">已出貨</td>
								</c:if>
							</tr>
						</tbody>
						
					</c:forEach>
				</table>

			</div>

		</div>
		<%@ include file="../fragment/js.jsp"%>
</body>
</html>