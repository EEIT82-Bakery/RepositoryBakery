<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
    <script  src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
<style>
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
</style>

</head>
<body>
		<div>
		
		
		<form action="<c:url value='/Kanban.do?memberid=${isLogin.member_id}' />" method="post" enctype="multipart/form-data">
			主題<input type="text" name="title" value="title" /><br>
			內容<textarea rows="10" cols="40" name="detail"></textarea>
			
			<div class="btn">
			<div>
				<img class="preview" style="width: 306px; height: 206px;" id="mypicc" name="mypicc" src="">
				<input type='file' class="upl" name="mypic">
			</div>
			</div>
			<input type="submit" name="action" value="add">
		</form>
	</div>



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