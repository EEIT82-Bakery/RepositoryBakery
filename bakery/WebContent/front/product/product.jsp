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
								href="<%=request.getContextPath()%>/product2.controller?productTypeId=1&page=1">6吋蛋糕</a>
							</li>
							<li><a
								href="<%=request.getContextPath()%>/product2.controller?productTypeId=2&page=1">8吋蛋糕</a>
							</li>
							<li><a
								href="<%=request.getContextPath()%>/product2.controller?productTypeId=3&page=1">長條蛋糕</a>
							</li>
							<li><a
								href="<%=request.getContextPath()%>/product2.controller?productTypeId=4&page=1">造型蛋糕</a>
							</li>
						</ul></li>
					<li><a
						href="<%=request.getContextPath()%>/product2.controller?productTypeId=5&page=1">過年專區</a>
					</li>
					<li><a href="javascript:;">伴手禮盒</a>
						<ul>
							<li><a
								href="<%=request.getContextPath()%>/product2.controller?productTypeId=6&page=1">手工餅乾</a>
							</li>
							<li><a
								href="<%=request.getContextPath()%>/product2.controller?productTypeId=7&page=1">精緻禮盒</a>
							</li>
							<li><a
								href="<%=request.getContextPath()%>/product2.controller?productTypeId=8&page=1">精緻糕點</a>
							</li>
						</ul></li>
				</ul>
			</div>
			<!--------------------------------------產品側攔--------------------------------------->
			<!-----------------------------------------main----------------------------------------->
			<div class="col-xs-9">
				<h3></h3>
				<c:forEach var="aBean" items="${productList}" varStatus="theCount">
					<div class="col-xs-4">
						<h4>${aBean.productName}</h4>
						<a href="javascript:Content('${aBean.productId}')"><img
							src="<%=request.getContextPath()%>/DBGifReader.do?productId=${aBean.productId}"
							width="150px" height="150px" /></a>
						<h6>單價 :${aBean.productPrice}</h6>
						<div>
							數量： <input type="number" id="number${theCount.count}"
								name="quantity" value=1 min="0" max="10" style="width: 36px">
							<button class="success"
								onclick="addShoppingItems('${aBean.productName}' , '${aBean.productPrice}' , '${aBean.discount}','${aBean.productId}','${theCount.count}')">放入購物車</button>
						</div>
					</div>
				</c:forEach>
			</div>
			<!-- 產品描述 -->
			<div class="modal fade" id="product" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">
								<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
							</button>
							<span>產品名稱:</span><span class="modal-title" id="myModalLabel"></span>
						</div>
						<div class="modal-body">
							<img src="" id="myimg" width="150" height="150" /> <span>產品描述:</span><span
								id="myStatus"></span>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">關閉</button>
						</div>
					</div>
				</div>
			</div>
			<!-- end -->
			<c:if test="${not empty pageCount}">
				<div class="page">
					<ul class="page_ul">
						<!-- 顯示第幾頁 -->
						<c:forEach var="page" begin="1" end="${pageCount}">
							<li class="page_li"><a
								href="${pageContext.request.contextPath}/product2.controller?productTypeId=${proTypeId}&page=${page}">
									<c:out value="${page}" />
							</a></li>
						</c:forEach>
					</ul>
				</div>
			</c:if>
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
					document.getElementById("myimg").src = "data:image/jpeg;base64,"+ product.Picture;
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
				productId, count) {
			var quantity = document.getElementsByName("quantity")[count - 1].value;
			xmlHttp = new XMLHttpRequest();
			if (xmlHttp != null) {
				xmlHttp.open("POST",
						"${pageContext.request.contextPath}/Shopping.do", true);
				xmlHttp.addEventListener("readystatechange", callback, false);
				xmlHttp.setRequestHeader("Content-Type",
						"application/x-www-form-urlencoded");
				xmlHttp.send("action=ADD" + "&name=" + productName + "&price="
						+ productPrice + "&discount=" + discount + "&quantity="
						+ quantity + "&productId=" + productId);
			} else {
				alert("您的瀏覽器不支援Ajax的功能!!");
			}

			function callback() {
				if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
					reset();
					alertify
							.success(productName + "已經加入" + quantity + "個到購物車了");
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







