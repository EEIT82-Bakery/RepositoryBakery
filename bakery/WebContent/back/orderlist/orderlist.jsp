<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
		<h1>管理訂單</h1>
	<FORM METHOD="post" action='<c:url value="/OrderServlet.do"/>'>
        <b>輸入訂單編號</b>
        <input type="text" name="orderid">
        <input type="hidden" name="action" value="select_id">
        <input type="submit" value="送出">
        <span>${errors.orderId}${errors.orderIdNoData}</span>
        
</FORM>
	
   <table border='1' bordercolor='#CCCCFF' width="100%">
   <tr>
		<th>訂單ID</th>
		<th>收件人名子</th>
		<th>取消日期</th>
		<th>會員ID</th>
		<th>狀態</th>	
		<th>修改</th>
		<th>刪除</th>
	</tr>
	
	<c:forEach var="aBean" items="${aBean}">
		<tr align='center' valign='middle'>
			<td>${aBean.orderId}</td>
			<td>${aBean.orderName}</td>
			<td>${aBean.cancelDate}</td>
			<td>${aBean.memberId}</td>
			<c:if test="${aBean.orderStaus==1}">
			<td>未付款</td>
			</c:if>
			<c:if test="${aBean.orderStaus==2}" >
			<td>已付款</td>
			</c:if>
			<c:if test="${aBean.orderStaus==3}" >
			<td>已出貨</td>
			</c:if>
			
			<td>
			<FORM METHOD="post" action='<c:url value="/OrderServlet.do"/>'>
			<input type="hidden" name="action" value="update">
			 <input type="hidden" name="orderIdu" value="${aBean.orderId}">
			 <input type="hidden" name="orderSatus" value="${aBean.orderStaus}">
			 <input type="submit" value="修改">
				</FORM>
			</td>
				
			<td>
			<FORM METHOD="post" action='<c:url value="/OrderServlet.do"/>'>
			<input type="hidden" name="action" value="delete">
			 <input type="hidden" name="orderIdd" value="${aBean.orderId}">
			 <input type="submit" value="刪除">
			 </FORM>
			</td>
		</tr>
			</c:forEach>
	</table>
	 
	</div>
	
	
	<!-----------------------------------------main----------------------------------------->
	<%@ include file="../fragment/js.jsp"%>
</body>
</html>