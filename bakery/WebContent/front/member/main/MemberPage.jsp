<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ include file="../member_fragment/css.jsp"%>
<style>
#ac1 {
	height: 300px;
	width: 800px;
}

#ac2 {
	height: 300px;
	width: 800px;
}

#ac3 {
	height: 300px;
	width: 800px;
}

#m1, #m2, #m3 {
	width: 320px;
	height: 150px;
}

#memberstatu {
	height: 69px;
	width: 212.5px;
}

#mypicture {
	height: 100px;
	width: 100px;
}

input.upl {
	position: absolute;
	top: 0;
	margin: 0;
	border: solid transparent;
	opacity: 0;
	filter: alpha(opacity = 0);
	cursor: pointer;
	height: 260px;
	width: 299px;
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
		<%@ include file="../member_fragment/nav.jsp"%>
	<div class="container top">
		<!-----------------------------------------main----------------------------------------->
		<div class="row">
			<div class="col-xs-4">
				<div class="list-group">

					<div class="thumbnail">
						<center>
							<h5>會員:${isLogin.username}，您好</h5>
							<img src="${pageContext.request.contextPath}/DBGifReader.do?memberId=${isLogin.member_id}" id="mypicture" class="img-circle" alt="個人照片" />
							<hr>
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
							<hr>
							<button type="button" class="btn btn-warning" data-toggle="modal" data-target="#myModal">修改會員資料</button>
						</center>
					</div>
				</div>
			</div>
			<div class="col-xs-8">
				<div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
					<ol class="carousel-indicators">
						<li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
						<li data-target="#carousel-example-generic" data-slide-to="1"></li>
						<li data-target="#carousel-example-generic" data-slide-to="2"></li>
					</ol>
					<div class="carousel-inner">
						<div class="item active">
							<img class="slide-image" src="${pageContext.request.contextPath}/front/member/main/images/ac3.jpg" id="ac1" alt="">
						</div>
						<div class="item">
							<img class="slide-image" src="${pageContext.request.contextPath}/front/member/main/images/ac2.jpg" id="ac2" alt="">
						</div>

						<div class="item">
							<img class="slide-image" src="${pageContext.request.contextPath}/front/member/main/images/ac4.jpg" id="ac1" alt="">
						</div>
					</div>
					<a class="left carousel-control" href="#carousel-example-generic" data-slide="prev">
						<span class="glyphicon glyphicon-chevron-left"></span>
					</a>
					<a class="right carousel-control" href="#carousel-example-generic" data-slide="next">
						<span class="glyphicon glyphicon-chevron-right"></span>
					</a>
				</div>
			</div>
			<div class="col-xs-4"></div>
			<div class="col-xs-8">
				<br />
				<div class="col-xs-4">
					<div class="thumbnail">
						<img src="${pageContext.request.contextPath}/front/member/main/images/m1.jpg" id="m1" alt="">
						<div class="caption">
							<h4>交易紀錄</h4>
							<p>
								<a href="">交易明細查詢</a>
							</p>
							<br>
						</div>
					</div>
				</div>
				<div class="col-xs-4">
					<div class="thumbnail">
						<img src="${pageContext.request.contextPath}/front/member/main/images/m2.jpg" id="m2" alt="">
						<div class="caption">
							<h4>購物車/訂單</h4>
							<p>
								<a href="#">購物車</a>
							</p>
							<p>
								<a href="${pageContext.request.contextPath}/OrderMemberServlet.do">訂單查詢</a>
							</p>
						</div>
					</div>
				</div>
				<div class="col-xs-4">
					<div class="thumbnail">
						<img src="${pageContext.request.contextPath}/front/member/main/images/m3.png" id="m3" alt="">
						<div class="caption">
							<h4>我的帳戶</h4>

							<p>
								<a href="<c:url value='/front/member/main/CHGpassword.jsp' />">修改密碼</a>
							</p>
							<p>
								<a href="<c:url value="/homeindex.do">
								<c:param name="account" value="${isLogin.account}" />
								</c:url>">我的討論區頁面</a>
							</p>
						</div>
					</div>
				</div>
			</div>
		</div>
		<hr>
		<footer>
		<p>Copyright &copy; Your Website 2016</p>
		</footer>
		<div class="modal fade bs-example-modal-sm" id="myModal" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
			<div class="modal-dialog modal-sm">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">&times;</span> <span class="sr-only">Close</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">修改我的個人資訊</h4>
					</div>
					<form action="<c:url value='/front/member/main/member.do' />" method="post" enctype="multipart/form-data">
						<div class="modal-body">

							<div class="btn">
								<div>
									<img class="preview" style="width: 245px; height: 200px;" id="mypicc" name="mypicc" src="${pageContext.request.contextPath}/DBGifReader.do?memberId=${SelectMo.member_id}">
									<input type='file' class="upl" name="pic">
								</div>
							</div>
							<br>
							(點此修改圖片)
							<br>
							<hr>
							暱稱:
							<input type="text" name="nickname" value="${SelectMo.nickname}" />
							<span>${error.nickname}</span>
							<br>
							<br>
							手機:
							<input type="text" name="phone" value="${SelectMo.phone}" />
							<span>${error.phone}</span>
							<br>
							<br>
							信箱:
							<input type="text" name="email" value="${SelectMo.email}" />
							<span>${error.email}</span>
							<br>
							<br>
							地址:
							<input type="text" name="address" value="${SelectMo.address}" />
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

		<!-----------------------------------------main----------------------------------------->
	</div>
	<%@ include file="../../fragment/js.jsp"%>
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
