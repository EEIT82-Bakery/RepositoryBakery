<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../fragment/css.jsp"%>
<link
	href="${pageContext.request.contextPath}/front/HtmlData/css/product.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/front/HtmlData/css/checkoutforeach3.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/front/HtmlData/css/alertify.core.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/front/HtmlData/css/alertify.default.css" />
<link
	href="${pageContext.request.contextPath}/front/product/css/main.css"
	rel="stylesheet" />
</head>
<script
	src="${pageContext.request.contextPath}/front/HtmlData/js/jquery-2.1.4.min.js"></script>
<script
	src="${pageContext.request.contextPath}/front/HtmlData/js/alertify.min.js"></script>
<script src=""></script>
<body>
	<!-----------------------------------------nav------------------------------------------>
	<%@ include file="../fragment/nav.jsp"%>
	<!-----------------------------------------nav------------------------------------------>
	<div class="container-fluid">
		<!-----------------橫幅----------------->
		<div class="row">
			<div class="col-xs-12">
				<img
					src="${pageContext.request.contextPath}/front/HtmlData/images/product.jpg"
					style="width: 100%" />
			</div>
		</div>
		<!-----------------橫幅----------------->
		<!--------------------------------------產品側攔--------------------------------------->
		<div class="row">
			<div class="col-xs-3" style="padding-top: 10px;">
				<ul id="nav1" style="padding: 0;">
					<li><a href="javascript:;">純手工蛋糕</a>
						<ul>
							<li><a
								href="<%=request.getContextPath()%>/product2.controller?productTypeId=1&page=1">8吋蛋糕</a>
							</li>
							<li><a
								href="<%=request.getContextPath()%>/product2.controller?productTypeId=2&page=1">疊層蛋糕</a>
							</li>

						</ul></li>
					<li><a
						href="<%=request.getContextPath()%>/product2.controller?productTypeId=3&page=1">過年專區</a>
					</li>
					<li><a href="javascript:;">伴手禮盒</a>
						<ul>
							<li><a
								href="<%=request.getContextPath()%>/product2.controller?productTypeId=4&page=1">小蛋糕</a>
							</li>
							<li><a
								href="<%=request.getContextPath()%>/product2.controller?productTypeId=5&page=1">長條蛋糕</a>
							</li>
							<li><a
								href="<%=request.getContextPath()%>/product2.controller?productTypeId=6&page=1">手工餅乾</a>
							</li>
						</ul></li>
				</ul>
			</div>
			<!--------------------------------------產品側攔--------------------------------------->
			<!-----------------------------------------main----------------------------------------->
			<div class="col-xs-9">
				<br />
				<div class="bluetable">
					<table>
						<tr>
							<c:if test="${bean.discount==0.1}">
								<td><div align="right" style="color: red; font-size: 25px">
										<b>此商品打一折</b>
									</div></td>
							</c:if>
							<c:if test="${bean.discount==0.2}">
								<td><div align="right" style="color: red; font-size: 25px">
										<b>此商品打二折</b>
									</div></td>
							</c:if>
							<c:if test="${bean.discount==0.3}">
								<td><div align="right" style="color: red; font-size: 25px">
										<b>此商品打三折</b>
									</div></td>
							</c:if>
							<c:if test="${bean.discount==0.4}">
								<td><div align="right" style="color: red; font-size: 25px">
										<b>此商品打四折</b>
									</div></td>
							</c:if>
							<c:if test="${bean.discount==0.5}">
								<td><div align="right" style="color: red; font-size: 25px">
										<b>此商品打五折</b>
									</div></td>
							</c:if>
							<c:if test="${bean.discount==0.6}">
								<td><div align="right" style="color: red; font-size: 25px">
										<b>此商品打六折</b>
									</div></td>
							</c:if>
							<c:if test="${bean.discount==0.7}">
								<td><div align="right" style="color: red; font-size: 25px">
										<b>此商品打七折</b>
									</div></td>
							</c:if>
							<c:if test="${bean.discount==0.8}">
								<td>
									<div align="right" style="color: red; font-size: 25px">
										<b>此商品打八折</b>
									</div>
								</td>
							</c:if>
							<c:if test="${bean.discount==0.9}">
								<td>
									<div align="right" style="color: red; font-size: 25px">
										<b>此商品打九折</b>
									</div>
								</td>
							</c:if>
						</tr>
						<tr>
							<td style="color: #6b3402; font-size: 30px;">${bean.productName}</td>
						</tr>
						<tr>
							<td>
								<div class="row" >
									<div class="col-xs-6">
										<img src="<%=request.getContextPath()%>/DBGifReader.do?productId=${bean.productId}"	width="220px" height="220px" style="margin-left:25%;"/>
									</div>
									<div class="col-xs-6">
										<h4>本商品規格：<br />${bean.productStatus}</h4>
									</div>
									<div class="col-xs-6">
										<h4>原價：${bean.productPrice}</h4>
									</div>
									<div class="col-xs-6">
										<h4>數量： <input type="number" name="quantity" value=1 min="1"
										max="10" style="width: 36px">
										<button class="success" onclick="addShoppingItems('${bean.productName}' , '${bean.productPrice}' , '${bean.discount}','${bean.productId}')">放入購物車</button></h4>
									</div>
								</div>
							</td>
						</tr>
					</table>
				</div>
			</div>
			<!-- 產品描述內容 -->
			<!-- end -->
		</div>
		<!-----------------------------------------main----------------------------------------->
		<!--------footer-------->
		<%@ include file="../fragment/footer.jsp"%>
		<!--------footer-------->
	</div>

	<%@ include file="../fragment/js.jsp"%>
	<script>
		function Content(productId) {
			xmlHttp = new XMLHttpRequest();
			if (xmlHttp != null) {
				xmlHttp.open("POST",
						"${pageContext.request.contextPath}/ProductDetil.do",
						true);
				xmlHttp.addEventListener("readystatechange", callback, false);
				xmlHttp.setRequestHeader("Content-Type",
						"application/x-www-form-urlencoded")
				xmlHttp.send("productId=" + productId);
			} else {
				alert("您得瀏覽器不支援Ajax的功能!!");
			}
			function callback() {
				if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
					var product = JSON.parse(xmlHttp.responseText);
					document.getElementById("myimg").src = "data:image/jpeg;base64,"
							+ product.Picture;
					document.getElementById("myModalLabel").innerText = product.ProductName;
					document.getElementById("myStatus").innerText = product.Status;
					$('#product').modal('show');
				}
			}
		}
	</script>
	<script>
		function reset() {
			$("#toggleCSS");
			alertify.set({
				labels : {
					ok : "OK",
					cancel : "Cancel"
				},
				delay : 2500,
				cbuttonReverse : false,
				buttonFocus : "ok"
			});
		}
		function addShoppingItems(productName, productPrice, discount,
				productId) {
			var quantity = document.getElementsByName("quantity")[0].value;
			if (quantity > 10) {
				alert("產品數量不能超過10個");
			} else if (quantity < 1) {
				alert("購買數量最少1個");
			} else {
				xmlHttp = new XMLHttpRequest();
				if (xmlHttp != null) {
					xmlHttp.open("POST",
							"${pageContext.request.contextPath}/Shopping.do",
							true);
					xmlHttp.addEventListener("readystatechange", callback,
							false);
					xmlHttp.setRequestHeader("Content-Type",
							"application/x-www-form-urlencoded");
					xmlHttp.send("action=ADD" + "&name=" + productName
							+ "&price=" + productPrice + "&discount="
							+ discount + "&quantity=" + quantity
							+ "&productId=" + productId);
				} else {
					alert("您的瀏覽器不支援Ajax的功能!!");
				}
				function callback() {
					if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
						var result = xmlHttp.responseText;
						if (result == null || result == "") {
							reset();
							alertify.success(productName + "已經加入" + quantity
									+ "個到購物車了");
						} else {
							alert(result);
						}
					}
				}
			}
		}
		$(document).ready(function(e) {
			var curr = null;
			$('#nav1>li>a').click(function() {
				//假如點選的不是已開啟, 則收合全部再展開目前點選的
				if ($(this).parent('li').index() != curr) {
					$('#nav1 ul').slideUp(800);
					$(this).next().slideDown(1000);
					curr = $(this).parent('li').index();
				} else {
					$(this).next().slideUp(800);
					curr = null;
				}
			});
		});
	</script>
</body>
</html>







