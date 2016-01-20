<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.orderlist.model.*"%>
<%@ page import="java.util.*"%>

<!DOCTYPE html>
<html>
<head>
<%@ include file="./front/fragment/css.jsp"%>
<% 
OrderListJNDIDAO top3= new OrderListJNDIDAO();
List<OrderListBean> topphoto=top3.selectTop3();
pageContext.setAttribute("photo3", topphoto);
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
</head>
<body>
	<!-----------------------------------------nav------------------------------------------>
	<%@ include file="./front/fragment/nav.jsp"%>
	<!-----------------------------------------nav------------------------------------------>
	<div class="container-fluid">
		<!-----------------------------------------輪播----------------------------------------->
		<div class="row">
			<div class="col-xs-12">
				<div class="center">
					<div class="tabPanel" id="box">
						<div class="tab-img" id="con1">
							<a href="#">
								<img src="${pageContext.request.contextPath}/front/HtmlData/images/1.jpg" />
							</a>
						</div>
						<div class="tab-img" id="con2">
							<a href="#">
								<img src="${pageContext.request.contextPath}/front/HtmlData/images/2.jpg" />
							</a>
						</div>
						<div class="tab-img" id="con3">
							<a href="#">
								<img src="${pageContext.request.contextPath}/front/HtmlData/images/3.jpg" />
							</a>
						</div>
						
						<ol class="dot">
							<li id="tab1"></li>
							<li id="tab2"></li>
							<li id="tab3"></li>
						</ol>
					</div>
				</div>
						<table style="border:3px #FFD382 dashed;" cellpadding="10" border='1' align="center">
						<tr>
									<td colspan="3" align="center"><h1>人氣商品</h1></td>							
						</tr>
						<tr>
						<c:forEach var="photo" items="${photo3}" >
						<td>
						<img src="<%=request.getContextPath()%>/OrderListReaderServlet.do?productId=${photo.productId}" width="250px" height="250px">
						</td>
						</c:forEach>
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
	<%@ include file="./front/fragment/js.jsp"%>
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
