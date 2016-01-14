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
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/front/HtmlData/css/jquery-ui.min.css">
<script
	src="${pageContext.request.contextPath}/front/HtmlData/js/jquery-2.1.4.min.js"></script>
<script
	src="${pageContext.request.contextPath}/front/HtmlData/js/jquery-ui.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style>
#count {
	float: left;
	margin-left: 750px;
}

#cherk {
	margin-left: 700px;
}

td {
	style: text-align:left;
	border-color: #FFBD32;
	border-style: ridge;
}
</style>
<title>購物訂單確認</title>
</head>
<body bgcolor="#FFFFFF">
	<%@ include file="../fragment/nav.jsp"%>
	<hr>
	<p>
	<div class="row" align="center">
		<FORM action="<c:url value='OrderServlet.do' />" method="POST">
			<TABLE border='1' width="810"
				style="background: #F5EBFF; border-color: rgb(100, 100, 255); border-style: outset;">
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
					<TD>日期：<input type="text" name="shipDate" id="datepick"
						readonly="readonly"> ${errors.orderDate}
					</TD>
				</TR>
				<TR>
					<TD colspan='3'>出貨地址： <Input style="background: #ECFFCD;"
						size="60" type="text" name="orderAddress"
						value="${isLogin.address}">
					</TD>
				</TR>
				<tr>
					<TD colspan='3'>
						<table border="1">
							<tr bgcolor="#999999">
								<th width="200">產品名稱</th>
								<th width="100">價格</th>
								<th width="100">折扣</th>
								<th width="100">數量</th>
							</tr>
							<c:forEach var="list" items="${shoppingcart}">
								<tr>
									<td width="200"><div align="center">
											<b>${list.productName}</b>
										</div></td>
									<td width="100"><div align="center">
											<b>${list.productPrice}</b>
										</div></td>
									<td width="100"><div align="center">
											<b>${list.discount}</b>
										</div></td>
									<td width="100"><div align="center">
											<b>${list.quantity}</b>
										</div></td>
								</tr>
								<input type="hidden" name="productId" value="${list.productId}">
							</c:forEach>
							<tr>
								<td colspan="4" align="right"><font color="red"><b>總金額：${amount}</b></font></td>
							</tr>
						</table>
						<div id="cherk">
							<input type="hidden" name="totalMmount" value="${amount}" /> <input
								type="submit" value="確認送出" />
						</div>
					</TD>
				</tr>

			</table>
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
