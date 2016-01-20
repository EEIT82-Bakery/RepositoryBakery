<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="../fragment/css.jsp"%>
</head>
<body>
	<%@ include file="../fragment/main.jsp"%>
	<!-----------------------------------------main----------------------------------------->
	<div class="col-xs-10 main">
		<h1 class="page-header">管理訂單</h1>
		<br/>
<div class="col-md-3">
<div class="CSSTableGenerator" >
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
<div class="CSSTableGenerator" >
<table style="text-align:center;table-layout:fixed"  width="100%">

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
					<td><h4>${Bean1.quantity}</h4></td>
				</tr>
				
			</c:forEach>	
		</table>
		</div>
</div>
	</div>


	<!-----------------------------------------main----------------------------------------->
	<%@ include file="../fragment/js.jsp"%>
</body>
</html>