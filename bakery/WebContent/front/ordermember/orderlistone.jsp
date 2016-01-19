<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../fragment/css.jsp"%>
<link
	href="${pageContext.request.contextPath}/back/HtmlData/css/product.css"
	rel="stylesheet" />
</head>
<body>
	<!-----------------------------------------nav------------------------------------------>
	<%@ include file="../fragment/nav.jsp"%>
	<!-----------------------------------------nav------------------------------------------>
	<div class="container-fluid">
		<!-----------------橫幅----------------->

		<div class="row">
			<div class="col-xs-12">
				<img
					src="${pageContext.request.contextPath}/front/HtmlData/images/orderlist.jpg"
					style="width: 100%" />
<br/><br/><br/>
				<!-----------------橫幅----------------->
				<!--------------------------------------產品側攔--------------------------------------->

				<!--------------------------------------產品側攔--------------------------------------->
				<!-----------------------------------------main----------------------------------------->

				<div class="col-md-3">
<div class="CSSTableGenerator" style="margin-left:25%;">
		<table border='1' bordercolor='#CCCCFF' width="100%" style="table-layout:fixed;">

			<c:forEach var="aBean" items="${beans}">
				<tr>
					<td><h4>會員ID：${aBean.memberId}</h4></td>
				</tr>
				<tr>
					<td><h4>訂單編號：${aBean.orderId}</h4></td>
				</tr>
				<tr>
					<td><h4>收件人姓名：${aBean.orderName}</h4></td>
				</tr>
				<tr>
					<td><h4>收件人電話：${aBean.orderPhone}</h4></td>
				</tr>
				<tr>
					<td><h4>收件地址：${aBean.orderAddress}</h4></td>
				</tr>
				<tr>
					<td><h4>總金額：${aBean.totalAmount}</h4></td>
				</tr>
				<tr>
					<td><h4>出貨日期：${aBean.shipDate}</h4></td>
				</tr>
				<tr>
					<c:if test="${aBean.orderStaus==1}">
							<td style="color:red;"><h4>出貨狀態：未付款</h4></td>
						</c:if> <c:if test="${aBean.orderStaus==2}">
							<td style="color: black;"><h4>出貨狀態：已付款</h4></td>
						</c:if> <c:if test="${aBean.orderStaus==3}">
							<td style="color: blue;"><h4>出貨狀態：已出貨</h4></td>
						</c:if>
				</tr>
			</c:forEach>
		
		</table>
		</div>
</div>
<div class="col-md-1">
</div>
<div class="col-md-4">
<div class="CSSTableGenerator" style="margin-left:25%;" >
<table style="text-align:center;table-layout:fixed">
<tr>
<td colspan="2">訂購清單</td>
</tr>

<tr>
<th style="text-align:center"><h4>產品名稱</h4></th>
<th style="text-align:center"><h4>訂購數量</h4></th>
</tr>

<jsp:useBean id="productSvc" scope="page" class="com.product.model.ProductService" />
			<c:forEach var="Bean1" items="${bean1}">
				<tr>
					<td> <c:forEach var="productVO" items="${productSvc.allProducts}">
                             <c:if test="${Bean1.productId==productVO.productId}">
	                               <h4>${productVO.productName}</h4>
                             </c:if>
                      </c:forEach></td>				
					<td><h4>${Bean1.count}</h4></td>
				</tr>
				
			</c:forEach>	
		</table>
		</div>
</div>
			</div>
		</div>

		<!-----------------------------------------main----------------------------------------->
		<!--------footer-------->
		<%@ include file="../fragment/footer.jsp"%>
	</div>
</body>
</html>







