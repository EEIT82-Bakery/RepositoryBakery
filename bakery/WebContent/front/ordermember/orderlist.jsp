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

				<div class="col-xs-8">
					<div class="CSSTableGenerator" style="margin-left:25%;">
						<table border="1" style="table-layout: fixed" >
							<tr>
								<th>訂單ID</th>
								<th>收件人姓名</th>
								<th>出貨日期</th>
								<th>付款狀態</th>
							</tr>
							<c:forEach var="aBean" items="${listOrder}">
								<tr align='center' valign='middle'>
									<td><a href="${pageContext.request.contextPath}/OrderListServlet2.do?orderId=${aBean.orderId}">${aBean.orderId}</a></td>
									<td>${aBean.orderName}</td>
									<td>${aBean.shipDate}<c:if test="${aBean.orderStaus==1}">
											<td style="color: red;">未付款</td>
										</c:if> <c:if test="${aBean.orderStaus==2}">
											<td style="color: black;">已付款</td>
										</c:if> <c:if test="${aBean.orderStaus==3}">
											<td style="color: blue;">已出貨</td>
										</c:if>
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







