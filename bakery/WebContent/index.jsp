<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.orderlist.model.*"%>
<%@ page import="java.util.*"%>

<!DOCTYPE html>
<html>
<head>
<%@ include file="./front/fragment/css.jsp"%>
<%
	OrderListJNDIDAO top5 = new OrderListJNDIDAO();
	List<OrderListBean> topphoto = top5.selectTop5();
	pageContext.setAttribute("photo5", topphoto);
%>
<style>
.tabPanel {
	margin: 10px auto;
	width: 100%;
	height: 300px;
	position: relative;
	overflow: hidden;
}

.tabPanel img, .tabPanel a img {
	display: block;
	width: 100%;
	height: 300px;
}
td,tr{
border:3px #C29793 dashed 
}
.dot {
	position: absolute;
	bottom: 1px;
	left: 50%;
	z-index: 15;
	width: 60%;
	padding-left: 0;
	margin-left: -30%;
	text-align: center;
	list-style: none;
	cursor: default;
}

.dot li {
	display: inline-block;
	width: 10px;
	height: 10px;
	margin: 1px;
	text-indent: -999px;
	cursor: pointer;
	background-color: #AAAAAA;
	border: 1px solid #AAAAAA;
	border-radius: 10px;
}
</style>
<link rel="stylesheet" type="text/css"
	href="<c:url value="front/HtmlData/css/custom.css"/>">
<link rel="stylesheet" type="text/css"
	href="<c:url value="front/HtmlData/css/demo.css"/>">
<link rel="stylesheet" type="text/css"
	href="<c:url value="front/HtmlData/css/slicebox.css"/>">
</head>
<body>
	<!-----------------------------------------nav------------------------------------------>
	<%@ include file="../front/fragment/nav.jsp"%>

	<!-----------------------------------------nav------------------------------------------>
	<div class="container-fluid">
		<!-----------------------------------------輪播----------------------------------------->
		<div class="row">
			<div class="col-xs-12">
				<div class="center">
					<div class="tabPanel" id="box">
						<div class="tab-img" id="con1">
							<a href="#"> <img
								src="${pageContext.request.contextPath}/front/HtmlData/images/1.jpg" />
							</a>
						</div>
						<div class="tab-img" id="con2">
							<a href="#"> <img
								src="${pageContext.request.contextPath}/front/HtmlData/images/2.jpg" />
							</a>
						</div>
						<div class="tab-img" id="con3">
							<a href="#"> <img
								src="${pageContext.request.contextPath}/front/HtmlData/images/3.jpg" />
							</a>
						</div>

						<ol class="dot">
							<li id="tab1"></li>
							<li id="tab2"></li>
							<li id="tab3"></li>
						</ol>
					</div>
				</div>
				<table style="border: 3px #C29793 dashed;" 
					border='1' align="center">
					<tr>
						<td colspan="3" align="center"><b style="color:green;font-size:25px;">人氣商品</b></td>
					</tr>	
					<tr>
					<!-- 新增 -->
						<td>
							<div class="wrapper" style="width:250px" >
								<ul id="sb-slider" class="sb-slider">
									<c:forEach var="photo" items="${photo5}" varStatus="theCount">
										<li><a href="#" target="_blank"> <img
												src="<%=request.getContextPath()%>/OrderListReaderServlet.do?productId=${photo.productId}"
												alt="image1" />
										</a></li>
									</c:forEach>
								</ul>
								<div id="shadow" class="shadow"></div>
<!-- 								<div id="nav-arrows" class="nav-arrows"> -->
<!-- 									<a href="#">Next</a> <a href="#">Previous</a> -->
<!-- 								</div> -->
							</div>
						</td>
					<!-- 新增結束 -->
					</tr>
				</table>
			</div>
		</div>
		<!-----------------------------------------輪播----------------------------------------->
		<!-----------------------------------------main----------------------------------------->


		<!-----------------------------------------main----------------------------------------->
		<!--------footer-------->
		<%@ include file="./front/fragment/footer.jsp"%>
		<!--------footer-------->
	</div>
	<%-- 	<%@ include file="./front/fragment/js.jsp"%>\ --%>
	<script
		src="${pageContext.request.contextPath}/front/HtmlData/js/jquery-2.1.4.min.js"></script>
	<script type="text/javascript"
		src="<c:url value="front/HtmlData/js/modernizr.custom.46884.js"/>"></script>
	<script type="text/javascript"
		src="<c:url value="front/HtmlData/js/jquery.slicebox.js"/>"></script>
	<script>
		$(function() {
			$(function() {
				var Page = (function() {
					var $navArrows = $('#nav-arrows').hide(), $navOptions = $(
							'#nav-options').hide(), $shadow = $('#shadow')
							.hide(), slicebox = $('#sb-slider').slicebox({
						autoplay : true,
						onReady : function() {
							$navArrows.show();
							$navOptions.show();
							$shadow.show();
						},
						orientation : 'h',
						cuboidsCount : 3
					}), init = function() {
						initEvents();
					}, initEvents = function() {
						// add navigation events
						$navArrows.children(':first').on('click', function() {
							slicebox.next();
							slicebox.play();
							return false;
						});
						$navArrows.children(':last').on('click', function() {
							slicebox.previous();
							slicebox.play();
							return false;
						});
					};
					return {
						init : init
					};
				})();
				Page.init();
			});
		})
	</script>
	<script>
		window.onload = function() {
			var num = 1;
			var index = 3;
			var duration = 2000;
			//初始化
			document.getElementById("con1").style.display = "block";
			document.getElementById("tab1").style.backgroundColor = "black";
			//執行
			run();
			document.getElementById("box").onmouseover = stopRun;
			document.getElementById("box").onmouseout = run;

			for (var i = 1; i <= index; i++) {
				document.getElementById("tab" + i).onmouseover = show;
			}
			function autoShow() {
				for (var i = 1; i <= index; i++) {
					document.getElementById("con" + i).style.display = "none";
					document.getElementById("tab" + i).style.backgroundColor = "#AAAAAA";
				}
				num++;
				if (num > index) {
					num = 1;
				}
				document.getElementById("con" + num).style.display = "block";
				document.getElementById("tab" + num).style.backgroundColor = "black";
			}
			function show() {
				num = this.id.substr(3) - 1;
				autoShow();
			}
			function stopRun() {
				clearInterval(myInterval);
			}
			function run() {
				myInterval = setInterval(autoShow, duration);
			}
		}
	</script>
</body>
</html>
