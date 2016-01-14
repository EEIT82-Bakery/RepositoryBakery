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
<title>�ʪ��q��T�{</title>
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
					<TD>����H�m�W�G<Input style="background: #ECFFCD;" size="30"
						type="text" name="orderName" value="${isLogin.username}"></TD>
				</TR>
				<TR>
					<TD>����H�q�ܡG <Input style="background: #ECFFCD;" size="20"
						type="text" name="orderPhone" value="${isLogin.phone}">
					</TD>
				</TR>
				<TR>
					<TD>����G<input type="text" name="shipDate" id="datepick"
						readonly="readonly"> ${errors.orderDate}
					</TD>
				</TR>
				<TR>
					<TD colspan='3'>�X�f�a�}�G <Input style="background: #ECFFCD;"
						size="60" type="text" name="orderAddress"
						value="${isLogin.address}">
					</TD>
				</TR>
				<tr>
					<TD colspan='3'>
						<table border="1">
							<tr bgcolor="#999999">
								<th width="200">���~�W��</th>
								<th width="100">����</th>
								<th width="100">�馩</th>
								<th width="100">�ƶq</th>
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
								<td colspan="4" align="right"><font color="red"><b>�`���B�G${amount}</b></font></td>
							</tr>
						</table>
						<div id="cherk">
							<input type="hidden" name="totalMmount" value="${amount}" /> <input
								type="submit" value="�T�{�e�X" />
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
