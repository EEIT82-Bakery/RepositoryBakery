<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


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
	<%
		int i = 0;
	%>
	<!-----------------------------------------nav------------------------------------------>
	<div class="container-fluid">
		<!-----------------------------------------main----------------------------------------->
		<img src="${pageContext.request.contextPath}/back/HtmlData/images/logo.png" width="100px" height="100px">
		<font size="+3">目前您購物車的內容如下：</font>
		<p>
		<table id="shoppingCart" border="1" width="740">
			<tr bgcolor="#999999">
				<th width="200">產品</th>
				<th width="100">金額</th>
				<th width="100">折扣</th>
				<th width="120">數量</th>
				<th width="120"></th>
			</tr>

			<c:forEach var="order" items="${shoppingcart}">
				<tr>
					<td><div align="center">
							<b>${order.productName}</b>
						</div></td>
					<td><div align="center">
							<b>${order.productPrice}</b>
						</div></td>
					<td><div align="center">
							<b>${order.discount}</b>
						</div></td>
					<td><div align="center">
							<b>${order.quantity}</b>
						</div></td>
					<td><div align="center">
							<button class="del" onclick="deleteShoppingItems(<%=i++%>)">刪除</button>
						</div></td>
				</tr>
			</c:forEach>
		</table>
		<p>
		<form id="sender" name="checkoutForm" action='<c:url value="/Shopping.do"/>' method="POST">
			<input type="hidden" name="action" value="CHECKOUT">
			<input type="submit" value="付款結帳">
		</form>

		<a href="${pageContext.request.contextPath}/product2.controller?productTypeId=1&page=1" id="a">
			<input type="button" value="繼續購物">
		</a>
		<!-----------------------------------------main----------------------------------------->
		<!--------footer-------->
		<%@ include file="../fragment/footer.jsp"%>
		<!--------footer-------->
	</div>
	<script>
	
	if(window.name != "Cart"){ 
		location.reload(); 
		window.name = "Cart"; 
	} 
	else{ 
		window.name = ""; 
	} 
	 
		function deleteShoppingItems(del) {			
			xmlHttp = new XMLHttpRequest();
			if (xmlHttp != null) {
				xmlHttp.open("POST","${pageContext.request.contextPath}/Shopping.do", true);
				xmlHttp.addEventListener("readystatechange", callback, false);
				xmlHttp.setRequestHeader("Content-Type",
						"application/x-www-form-urlencoded")
				xmlHttp.send("action=DELETE" + "&del=" + del);

			} else {
				alert("您得瀏覽器不支援Ajax的功能!!");
			}
			function callback() {
				if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
					var orderItems = JSON.parse(xmlHttp.responseText);
		            var myTable = document.getElementById("shoppingCart");
		            var rowCount = myTable.rows.length;
		            for(var i = 2; i <= rowCount; i++){
		            	myTable.deleteRow(1);
		            }
					for (var i=0,max=orderItems.length;i<max;i++){
		            	var cell1 = $("<td></td>").html("<div align='center'><b>"+orderItems[i].ProductName+"</b></div>");
		            	var cell2 = $("<td></td>").html("<div align='center'><b>"+orderItems[i].Price+"</b></div>");
		            	var cell3 = $("<td></td>").html("<div align='center'><b>"+orderItems[i].Discount+"</b></div>");
		            	var cell4 = $("<td></td>").html("<div align='center'><b>"+orderItems[i].Quantity+"</b></div>");
		            	var cell5 = $("<td></td>").html("<div align='center'><div align='center'><button class='del' onclick='deleteShoppingItems("+i+")'>刪除</button></div></div>");
		            	var row = $("<tr></tr>").append([cell1,cell2,cell3,cell4,cell5]);
		            	$(myTable).append(row);	
					}
				}
			}
		}

	</script>
	<%@ include file="../fragment/js.jsp"%>

</body>
</html>
