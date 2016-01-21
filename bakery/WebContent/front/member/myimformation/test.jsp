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
<title>焙客栗工坊</title>
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

.btn {
	position: relative;
	overflow: hidden;
	margin-right: 4px;
	display: inline-block;
	*display: inline;
	padding: 4px 10px 4px;
	font-size: 14px;
	line-height: 18px;
	*line-height: 20px;
	color: #fff;
	text-align: center;
	vertical-align: middle;
	cursor: pointer;
	border: 1px solid #cccccc;
	border-color: #e6e6e6 #e6e6e6 #bfbfbf;
	border-bottom-color: #b3b3b3;
	-webkit-border-radius: 4px;
	-moz-border-radius: 4px;
	border-radius: 4px;
}

.btn input {
	position: absolute;
	top: 0;
	right: 0;
	margin: 0;
	border: solid transparent;
	opacity: 0;
	filter: alpha(opacity = 0);
	cursor: pointer;
	height: 200px;
	width: 300px;
}

.progress {
	position: relative;
	margin-left: 100px;
	margin-top: -24px;
	width: 200px;
	padding: 1px;
	border-radius: 3px;
	display: none
}

.bar {
	background-color: green;
	display: block;
	width: 0%;
	height: 20px;
	border-radius: 3px;
}

.percent {
	position: absolute;
	height: 20px;
	display: inline-block;
	top: 3px;
	left: 2%;
	color: #fff
}

.files {
	height: 22px;
	line-height: 22px;
	margin: 10px 0
}

.delimg {
	margin-left: 20px;
	color: #090;
	cursor: pointer
}

#div1{

	
}

</style>




</head>
<body>
<%@ include file="../member_fragment/nav.jsp"%>


	<center>
		
			<img src="data:image/png;base64,${SelectMo.mpicture}" id="mypicture" class="img-circle" alt="個人照片" />
		
	<br>
<!-- 			SelectMo -->


	<div id="div1">
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
			</div>
		
			
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
						<h4 class="modal-title" id="myModalLabel">修改我的個人資訊</h4>
					</div>
					<form action="<c:url value='/front/member/main/member.do' />" method="post" enctype="multipart/form-data">
					<div class="modal-body">

							<div class="btn">
								<div>
									<img class="preview" style="width: 306px; height: 206px;"
										id="mypicc" name="mypicc"
										src="data:image/png;base64,${SelectMo.mpicture}">
									<input type='file' class="upl" name="pic">
								</div>
							</div>
							<br>
							(點此修改圖片)
		<br>
		<hr>
						暱稱:<input type="text" name="nickname" value="${SelectMo.nickname}" /> 
						 <span>${error.nickname}</span>
						<br>
						<br> 
						手機:<input type="text" name="phone" value="${SelectMo.phone}" />
						 <span>${error.phone}</span>
						<br>
						<br> 
						信箱:<input type="text" name="email" value="${SelectMo.email}" />
						 <span>${error.email}</span>
						<br>
						<br>
						 地址:<input type="text" name="address" value="${SelectMo.address}" />
						 <span>${error.address}</span>
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
		<script>
		$(function() {

			function format_float(num, pos) {
				var size = Math.pow(10, pos);
				return Math.round(num * size) / size;
			}
			function preview(input) {
				if (input.files && input.files[0]) {
					var reader = new FileReader();
					reader.onload = function(e) {
						$('.preview').attr('src', e.target.result);
						var KB = format_float(e.total / 1024, 2);
					}
					reader.readAsDataURL(input.files[0]);
				}
			}
			$("body").on("change", ".upl", function() {
				preview(this);
			})
		})
	</script>

	
	
</body>
</html>
