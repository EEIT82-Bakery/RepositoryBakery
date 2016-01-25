<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product.model.ProductBean"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<%@ include file="../fragment/css.jsp"%>
<link
	href="${pageContext.request.contextPath}/front/HtmlData/css/product.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/front/HtmlData/css/checkoutforeach.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/front/HtmlData/css/checkoutforeach2.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/front/HtmlData/css/jquery-ui.min.css">
	<link rel="stylesheet"
	href="${pageContext.request.contextPath}/front/HtmlData/css/jquery-ui.structure.css">
		<link rel="stylesheet"
	href="${pageContext.request.contextPath}/front/HtmlData/css/jquery-ui.theme.min.css">
<script
	src="${pageContext.request.contextPath}/front/HtmlData/js/jquery-2.1.4.min.js"></script>
<script
	src="${pageContext.request.contextPath}/front/HtmlData/js/jquery-ui.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


<title>購物訂單確認</title>
</head>
<body>
	<%@ include file="../fragment/nav.jsp"%>
	<p>
	<div class="row" align="center">

		<FORM action="<c:url value='OrderServlet.do' />" method="POST">
			<div class="bluetable">
				<TABLE>
					<TR>
						<TD>收件人姓名：<Input style="background: #ECFFCD;" size="30"
							type="text" name="orderName" value="${isLogin.username}"></TD>
					</TR>
					<TR>
						<TD>收件人電話： <Input style="background: #ECFFCD;" size="20"
							type="text" name="orderPhone" value="${isLogin.phone}">
						</TD>
					</TR>
					<TR>
						<TD>日期：<input type="text" style="background: #ECFFCD;"
							name="shipDate" id="datepick" readonly="readonly"><b style="color:red">網路訂購需於五天前下單。</b>
							${errors.orderDate}
						</TD>
					</TR>
					<TR>
						<TD>出貨地址： <Input style="background: #ECFFCD;" size="60"
							type="text" name="orderAddress" value="${isLogin.address}">
						</TD>
					</TR>
					<tr>
						<TD style="text-align: right;"><input type="hidden"
							name="totalMmount" value="${amount}" /> <input type="hidden"
							name="action" value="insert"> <input type="submit"
							value="確認送出" style="margin-right: 50px;" /></TD>
					</tr>

				</table>
			</div>
			<hr>
			<br />
			<div class="redtable">
				<table>
					<tr>
						<td>產品名稱</td>
						<td>價格</td>
						<td>折扣</td>
						<td>數量</td>
					</tr>
					<c:forEach var="list" items="${shoppingcart}">
						<tr>
							<td><div align="center">
									<b>${list.productName}</b>
								</div></td>
							<td><div align="center">
									<b>${list.productPrice}</b>
								</div></td>
							<c:if test="${list.discount==0.1}">
								<td><div align="center"><b>一折</b>
									</div></td>
									</c:if>
									<c:if test="${list.discount==0.2}">
								<td><div align="center"><b>二折</b>
									</div></td>
									</c:if>
									<c:if test="${list.discount==0.3}">
								<td><div align="center"><b>三折</b>
									</div></td>
									</c:if>
									<c:if test="${list.discount==0.4}">
								<td><div align="center"><b>四折</b>
									</div></td>
									</c:if>
									<c:if test="${list.discount==0.5}">
								<td><div align="center"><b>五折</b>
									</div></td>
									</c:if>
									<c:if test="${list.discount==0.6}">
								<td><div align="center"><b>六折</b>
									</div></td>
									</c:if>
									<c:if test="${list.discount==0.7}">
								<td><div align="center"><b>七折</b>
									</div></td>
									</c:if>
									<c:if test="${list.discount==0.8}">
								<td><div align="center"><b>八折</b>
									</div></td>
									</c:if>
									<c:if test="${list.discount==0.9}">
								<td><div align="center"><b>九折</b>
									</div></td>
									</c:if>
							<td><div align="center">
									<b>${list.quantity}</b>
								</div></td>
						</tr>
						<input type="hidden" name="productId" value="${list.productId}">
					</c:forEach>
					<tr>
						<td colspan="4" style="text-align: right;"><b
							style="color: red; font-size: 18px; algin: right;">總金額：${amount}</b></td>
					</tr>

				</table>
			</div>
		</FORM>
	</div>
	<%@ include file="../fragment/footer.jsp"%>
	<script>
		$(function() {
			$("#datepick").datepicker({
				dateFormat : 'yy-mm-dd',
				minDate : '5d',
				maxDate : '25d'
			});
		});
	</script>
</body>
</html>
