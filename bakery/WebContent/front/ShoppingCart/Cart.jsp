<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product.model.ProductBean"%>

<% int i = 0; %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../fragment/css.jsp"%>
<link href="${pageContext.request.contextPath}/front/HtmlData/css/product.css" rel="stylesheet">
<style>
#sender {
	float: right;
	margin-right: 700px;
}

#a {
	float: right;
	margin-right: 200px;
}
</style>

</head>
<body>
	<!-----------------------------------------nav------------------------------------------>
	<%@ include file="../fragment/nav.jsp"%>
	<!-----------------------------------------nav------------------------------------------>
	<div class="container-fluid">
		<!-----------------------------------------main----------------------------------------->
<%Vector<ProductBean> buylist = (Vector<ProductBean>) session.getAttribute("shoppingcart");%>
<%if (buylist != null && (buylist.size() > 0)) {%>

<img src="${pageContext.request.contextPath}/back/HtmlData/images/logo.png"  width="100px" height="100px"> <font size="+3">目前您購物車的內容如下：</font><p>

<table border="1" width="740">
	<tr bgcolor="#999999">
		<th width="200">產品</th><th width="100">金額</th><th width="100">折扣</th>
		<th width="120">數量</th><th width="120"></th>
	</tr>
	
	<%
	 for (int index = 0; index < buylist.size(); index++) {
		 ProductBean order = buylist.get(index);
	%>
	<tr>
		<td width="200"><div align="center"><b><%=order.getProductName()%></b></div></td>
		<td width="100"><div align="center"><b><%=order.getProductPrice()%></b></div></td>
		<td width="100"><div align="center"><b><%=order.getDiscount()%></b></div></td>
		<td width="100"><div align="center"><b><%=order.getQuantity()%></b></div></td>
		<td width="100"><div align="center">
          <form name="deleteForm" action='<c:url value="/Shopping.do"/>' method="POST">
              <input type="hidden" name="action" value="DELETE">
              <input type="hidden" name="del" value="<%= index %>">
              <input type="submit" value="刪除"></div>
        </td></form>
	</tr>
	<%}%>
</table>
<p>
          <form id="sender" name="checkoutForm" action='<c:url value="/Shopping.do"/>' method="POST">
              <input type="hidden" name="action"	value="CHECKOUT"> 
              <input type="submit" value="付款結帳">
          </form>
          
          <a href="${pageContext.request.contextPath}/product2.controller?productTypeId=1&page=1" id="a">
          	<input type="button" value="繼續購物">
          </a>
<%}%>
		<!-----------------------------------------main----------------------------------------->
		<!--------footer-------->
		<%@ include file="../fragment/footer.jsp"%>
		<!--------footer-------->
	</div>
	<%@ include file="../fragment/js.jsp"%>
<!-- 	<script> -->
<!-- // 		function deleteShoppingItems(del , thisDel) {			 -->
<!-- // 			xmlHttp = new XMLHttpRequest(); -->
<!-- // 			if (xmlHttp != null) { -->
<!-- // 				xmlHttp.open("POST", -->
<%-- // 						"${pageContext.request.contextPath}/Shopping.do", true); --%>
<!-- // 				xmlHttp.addEventListener("readystatechange", callback, false); -->
<!-- // 				xmlHttp.setRequestHeader("Content-Type", -->
<!-- // 						"application/x-www-form-urlencoded") -->
<!-- // 				xmlHttp.send("action=DELETE" + "&del=" + del); -->

<!-- // 			} else { -->
<!-- // 				alert("您得瀏覽器不支援Ajax的功能!!"); -->
<!-- // 			} -->
<!-- // 			function callback() { -->
<!-- // 				if (xmlHttp.readyState == 4 && xmlHttp.status == 200) { -->
<!-- // 					$(function(){ -->
<!-- // 						$(thisDel).parents("tr").remove(); -->
<!-- // 					}); -->
<!-- // 				} -->
<!-- // 			} -->
<!-- // 		}	 -->
<!-- 	</script> -->
</body>
</html>
