<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<style>
#mypicture{
	height: 50px;
	width: 50px;

}

</style>
</head>
<%@ include file="../fragment/css.jsp"%>
<body>

	<%@ include file="../fragment/main.jsp"%>




	<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
		<h1 class="page-header">會員維護</h1>
		<div class="row placeholders">
			<div class="col-xs-6 col-sm-3 placeholder">
				
			
		
			</div>
			<div class="col-xs-6 col-sm-3 placeholder">
				
			
			
			</div>
			<div class="col-xs-6 col-sm-3 placeholder">
				
	
			</div>
			<div class="col-xs-6 col-sm-3 placeholder">
		
			
			</div>
		</div>
		<h2 class="sub-header">會員列表</h2>
		<div class="table-responsive">
			<table class="table table-striped">
				<thead>
					<tr>
						<th>會員編號</th>	
						<th>會員照片</th>			
						<th>會員帳號</th>
						<th>會員姓名</th>
						<th>會員暱稱</th>
						<th>會員信箱</th>
						<th>會員電話</th>
						<th>訂單數量</th>
						<th>xxx</th>
						<th>bbb</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="aBean" items="${bean}">
						<tr>
							<td><h6>${aBean.member_id}</h6></td>
							<td><img src="data:image/png;base64,${aBean.mpicture}" id="mypicture" class="img-circle" alt="個人照片" /></td>
							<td><h6>${aBean.account}</h6></td>
							<td><h6>${aBean.username}</h6></td>
							<td><h6>${aBean.nickname}</h6></td>
							<td><h6>${aBean.email}</h6></td>
							<td><h6>${aBean.phone}</h6></td>
							<td><h6>${aBean.order_math}</h6>
							<td><button type="submit" name="action" value="詳細資訊">詳細資訊</button></td>
							<td><button type="submit" name="action" value="刪除">刪除</button></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<c:if test="${not empty pageCount}">
				<c:forEach var="page" begin="1" end="${pageCount}">

					<a href="${pageContext.request.contextPath}/BackAllMember.do?pages=${page}">
						<c:out value="${page}" /></a>
				</c:forEach>
			</c:if>
		</div>
	</div>



	<div class="col-xs-offset-5"></div>
	<%@ include file="../fragment/js.jsp"%>
</body>
</html>