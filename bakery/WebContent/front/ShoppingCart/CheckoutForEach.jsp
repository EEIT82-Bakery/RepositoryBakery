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


<title>�ʪ��q��T�{</title>
</head>
<body>
	<%@ include file="../fragment/nav.jsp"%>
	<p>
	<div class="row" align="center">

		<FORM action="<c:url value='OrderServlet.do' />" method="POST">
			<div class="bluetable">
				<TABLE>
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
						<TD>����G<input type="text" style="background: #ECFFCD;"
							name="shipDate" id="datepick" readonly="readonly"><b style="color:red">�����q�ʻݩ󤭤ѫe�U��C</b>
							${errors.orderDate}
						</TD>
					</TR>
					<TR>
						<TD>�X�f�a�}�G <Input style="background: #ECFFCD;" size="60"
							type="text" name="orderAddress" value="${isLogin.address}">
						</TD>
					</TR>
					<tr>
						<TD style="text-align: right;"><input type="hidden"
							name="totalMmount" value="${amount}" /> <input type="hidden"
							name="action" value="insert"> <input type="submit"
							value="�T�{�e�X" style="margin-right: 50px;" /></TD>
					</tr>

				</table>
			</div>
			<hr>
			<br />
			<div class="redtable">
				<table>
					<tr>
						<td>���~�W��</td>
						<td>����</td>
						<td>�馩</td>
						<td>�ƶq</td>
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
								<td><div align="center"><b>�@��</b>
									</div></td>
									</c:if>
									<c:if test="${list.discount==0.2}">
								<td><div align="center"><b>�G��</b>
									</div></td>
									</c:if>
									<c:if test="${list.discount==0.3}">
								<td><div align="center"><b>�T��</b>
									</div></td>
									</c:if>
									<c:if test="${list.discount==0.4}">
								<td><div align="center"><b>�|��</b>
									</div></td>
									</c:if>
									<c:if test="${list.discount==0.5}">
								<td><div align="center"><b>����</b>
									</div></td>
									</c:if>
									<c:if test="${list.discount==0.6}">
								<td><div align="center"><b>����</b>
									</div></td>
									</c:if>
									<c:if test="${list.discount==0.7}">
								<td><div align="center"><b>�C��</b>
									</div></td>
									</c:if>
									<c:if test="${list.discount==0.8}">
								<td><div align="center"><b>�K��</b>
									</div></td>
									</c:if>
									<c:if test="${list.discount==0.9}">
								<td><div align="center"><b>�E��</b>
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
							style="color: red; font-size: 18px; algin: right;">�`���B�G${amount}</b></td>
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
