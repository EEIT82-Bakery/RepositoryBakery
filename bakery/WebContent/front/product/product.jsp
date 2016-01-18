<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../fragment/css.jsp"%>
<link href="${pageContext.request.contextPath}/front/HtmlData/css/product.css" rel="stylesheet">
</head>
<body>
	<!-----------------------------------------nav------------------------------------------>
	<%@ include file="../fragment/nav.jsp"%>
	<!-----------------------------------------nav------------------------------------------>
	<div class="container-fluid">
		<!-----------------橫幅----------------->
		<div class="row">
			<div class="col-xs-12">
				<img src="${pageContext.request.contextPath}/front/HtmlData/images/product.jpg" style="width: 100%" />
			</div>
		</div>
		<!-----------------橫幅----------------->
		<!--------------------------------------產品側攔--------------------------------------->
		<div class="row">
			<div class="col-xs-3" style="padding-top: 10px;">
				<ul id="nav1" style="padding: 0;">
					<li>
						<a href="javascript:;">純手工蛋糕</a>
						<ul>
							<li>
								<a href="<%=request.getContextPath()%>/product2.controller?productTypeId=1&page=1">6吋蛋糕</a>
							</li>
							<li>
								<a href="<%=request.getContextPath()%>/product2.controller?productTypeId=2&page=1">8吋蛋糕</a>
							</li>
							<li>
								<a href="<%=request.getContextPath()%>/product2.controller?productTypeId=3&page=1">長條蛋糕</a>
							</li>
							<li>
								<a href="<%=request.getContextPath()%>/product2.controller?productTypeId=4&page=1">造型蛋糕</a>
							</li>
						</ul>
					</li>
					<li>
						<a href="<%=request.getContextPath()%>/product2.controller?productTypeId=5&page=1">過年專區</a>
					</li>
					<li>
						<a href="javascript:;">伴手禮盒</a>
						<ul>
							<li>
								<a href="<%=request.getContextPath()%>/product2.controller?productTypeId=6&page=1">手工餅乾</a>
							</li>
							<li>
								<a href="<%=request.getContextPath()%>/product2.controller?productTypeId=7&page=1">精緻禮盒</a>
							</li>
							<li>
								<a href="<%=request.getContextPath()%>/product2.controller?productTypeId=8&page=1">精緻糕點</a>
							</li>
						</ul>
					</li>
				</ul>
			</div>
			<!--------------------------------------產品側攔--------------------------------------->
			<!-----------------------------------------main----------------------------------------->
			<div class="col-xs-9">

				<c:forEach var="aBean" items="${productList}">
					<div class="col-xs-4">
					<form name="shoppingForm" action="Shopping.do" method="POST">
						<h4>${aBean.productName}</h4>
						<img src="<%=request.getContextPath()%>/DBGifReader.do?productId=${aBean.productId}" width="150px" height="150px">
						<h6>單價 :${aBean.productPrice}</h6>

					<div align="center">
								<input type="submit" name="Submit" value="放入購物車">
							</div>
							數量： 
							<input type="number"  id="quantity name="quantity" value=1 min="0" max="10" style="width: 36px">
							<input type="hidden" name="name" value="${aBean.productName}">
							<input type="hidden" name="price" value="${aBean.productPrice}">
							<input type="hidden" name="discount" value="${aBean.discount}">
							<input type="hidden" name="productTypeId" value="${aBean.productTypeId}">
							<input type="hidden" name="productId" value="${aBean.productId}">
							<input type="hidden" name="action" value="ADD">
						</form>

					</div>
				</c:forEach>
<!-- 				<input type="number" id="quantity" name="quantity" value=1 min="0" max="10" style="width: 36px"> -->
			</div>
			<c:if test="${not empty pageCount}">
				<div class="page">
					<ul class="page_ul">
						<!-- 顯示第幾頁 -->
						<c:forEach var="page" begin="1" end="${pageCount}">
							<li class="page_li">
								<a href="${pageContext.request.contextPath}/product2.controller?productTypeId=${proTypeId}&page=${page}">
									<c:out value="${page}" />
								</a>
							</li>
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
// 		function addShoppingItems(productName , productPrice , discount,productId){
// 			var quantity = document.getElementById("quantity").value;
// 			xmlHttp = new XMLHttpRequest();
// 			if (xmlHttp != null) {
// 				xmlHttp.open("POST", "${pageContext.request.contextPath}/Shopping.do", true);
// 				xmlHttp.addEventListener("readystatechange", callback, false);
// 				xmlHttp.setRequestHeader("Content-Type",
// 						"application/x-www-form-urlencoded");
// 				xmlHttp.send("action=ADD" + "&name=" + productName + "&price=" +productPrice + "&discount=" +discount 
// 						+ "&quantity=" + quantity + "&productId=" + productId);
				
// 			} else {
// 				alert("您的瀏覽器不支援Ajax的功能!!");
// 			}
// 			function callback() {
// 				if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
// 					//
// 				}
// 			}
// 		}
		
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







