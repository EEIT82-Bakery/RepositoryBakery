<%@page import="com.sun.xml.internal.bind.v2.schemagen.xmlschema.Import"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.member.model.*" %>
<%@ page import="com.member.controller.*" %>
    <% 

    MemberBean bean = (MemberBean) request.getAttribute("SelectMo");
    
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="../css/main.css" rel="stylesheet" />
<link href="../css/bootstrap.min.css" rel="stylesheet" />
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" />
<style type="text/css">
#mypicture {
	width: 100px;
	height: 100 px;
	border: 3px solid silver;
	padding: 1px;
}
</style>

</head>
<body>
<%@ include file="../member_fragment/nav.jsp"%>


	<center>
		
			<img src="data:image/png;base64,${SelectMo.mpicture}" id="mypicture" class="img-circle" alt="個人照片" />
		
	<br>
<!-- 			SelectMo -->
		會員姓名:${SelectMo.username}
		<br>
		會員暱稱:${SelectMo.nickname} 
		<br>
		
			性別:${SelectMo.sex}
			<br>
			生日:${SelectMo.birth}
			<br>
			地址:${SelectMo.address}
			<br>
			信箱:${SelectMo.email}
			<br>
			手機號碼:${SelectMo.phone}
<br> <br>
		<button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal">修改會員資料</button>

		<!-- Modal -->
		
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">&times;</span>
							<span class="sr-only">Close</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">修改我的資訊</h4>
					</div>
					<form action="<c:url value='/front/member/myimformation/test.controller' />" method="post">
					<div class="modal-body">

				
						暱稱:<input type="text" name="nickname" value="${SelectMo.nickname}" />
						<br>
						<br> 
						生日:<input type="text" name="birth" value="${SelectMo.birth}" />
						<br>
						<br> 
						手機:<input type="text" name="phone" value="${SelectMo.phone}" />
						<br>
						<br> 
						信箱:<input type="text" name="email" value="${SelectMo.email}" />
						<br>
						<br>
						 地址:<input type="text" name="address" value="${SelectMo.address}" />
						<br>

					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">返回</button>
						<button type="submit" class="btn btn-primary" name="action" value="update">確定</button>
					</div>
					</form>
				</div>
			</div>
		</div>
	</center>
	<script src="../js/jquery-2.1.4.min.js"></script>
	
	

	
	
</body>
</html>