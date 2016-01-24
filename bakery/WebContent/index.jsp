<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.orderlist.model.*"%>
<%@ page import="java.util.*"%>

<!DOCTYPE html>
<html>
<head>
<%@ include file="../front/fragment/css.jsp"%>
<%
	OrderListJNDIDAO top3 = new OrderListJNDIDAO();
	List<OrderListBean> topphoto = top3.selectTop3();
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
<link rel="stylesheet" type="text/css"
	href="<c:url value="front/HtmlData/css/index.css"/>">
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
				<div class="CSSTableGenerator">
                <table>
                    <tr>
                    <td>人氣商品</td>
                    <td>產品名稱</td>
                    <td>內容描述</td>
                    </tr>
                    <c:forEach var="photo" items="${photo3}" varStatus="theCount">
                    <tr>
					 <td width="200px"><img width="30px" src="<%=request.getContextPath()%>/front/HtmlData/images/crown.png">
					 <span style="font-size:20px;">第${theCount.count}名</span><img src="<%=request.getContextPath()%>/OrderListReaderServlet.do?productId=${photo.productId}"
							width="50px" height="50px" /></td>
                    <td width="120px">${photo.productName}</td>
                   	<td>${photo.productStatus}</td>
                    </tr>
                    </c:forEach>
                </table>
                
            </div>


		<!-----------------------------------------輪播----------------------------------------->
		<!-----------------------------------------main----------------------------------------->
<br/>

		<!-----------------------------------------main----------------------------------------->
		<!--------footer-------->
		<%@ include file="./front/fragment/footer.jsp"%>
		<!--------footer-------->
	           </div>
	</div>
	</div>
		<%@ include file="./front/fragment/js.jsp"%>
	<script
		src="${pageContext.request.contextPath}/front/HtmlData/js/jquery-2.1.4.min.js"></script>
	<script type="text/javascript"
		src="<c:url value="front/HtmlData/js/modernizr.custom.46884.js"/>"></script>
	<script type="text/javascript"
		src="<c:url value="front/HtmlData/js/jquery.slicebox.js"/>"></script>
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
