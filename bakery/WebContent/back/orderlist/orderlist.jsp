<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.order.model.*"%>
<%@ page import="java.util.*"%>
<%
	OrderService orderService = new OrderService();
	List<OrderBean> list = orderService.selectList();
    pageContext.setAttribute("list",list);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="../fragment/css.jsp"%>
<style>

#nav1 {
	list-style-type: none;
}

</style>
</head>
<body>
	<%@ include file="../fragment/main.jsp"%>
	<!-----------------------------------------main----------------------------------------->
	<div class="col-xs-10 main">
		<h1 class="page-header">管理訂單</h1>
<ul>
<li id="nav1">
	<FORM METHOD="post" action='<c:url value="/OrderSelect.do"/>'>
        <b>輸入訂單編號</b>
        <input type="hidden" name="action" value="select_id">
        <input type="text" name="orderid">
        <input type="submit" value="送出">
        <span>${errors.orderId}${errors.orderIdNoData}</span>
        
</FORM>
</li>
</ul>
	<br/>
	<div class="CSSTableGenerator" >
   <table width="100%" style="table-layout:fixed;">
  <tr>
   		<th>會員ID</th>
		<th>訂單ID</th>
		<th>收件人姓名</th>
		<th>出貨日期</th>
		<th>狀態</th>	
		<th>修改</th>
		<th>刪除</th>
	</tr>
	<%@ include file="page1.file" %>
	<c:forEach var="aBean" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr align='center' valign='middle'>
			<td>${aBean.memberId}</td>
			<td><a href="${pageContext.request.contextPath}/OrderListServlet.do?orderId=${aBean.orderId}">${aBean.orderId}</a></td>
			<td>${aBean.orderName}</td>
			<td>${aBean.shipDate}
			<c:if test="${aBean.orderStaus==1}">
			<td style="color:red;">未付款</td>
			</c:if>
			<c:if test="${aBean.orderStaus==2}" >
			<td style="color:black;">已付款</td>
			</c:if>
			<c:if test="${aBean.orderStaus==3}" >
			<td style="color:blue;">已出貨</td>
			</c:if>
			
			<td>
			<FORM METHOD="post" action='<c:url value="/OrderSelect.do"/>'>
			<input type="hidden" name="action" value="update">
			 <input type="hidden" name="orderIdu" value="${aBean.orderId}">
			 <input type="hidden" name="orderSatus" value="${aBean.orderStaus}">
			 <input type="hidden" name="whichPage" value="${param.whichPage}">
			 <input type="submit" value="修改" onClick="return(confirm('您確定要修改此出貨狀態嗎??'))">
				</FORM>
			</td>
				
			<td>
			<FORM METHOD="post" action='<c:url value="/OrderSelect.do"/>'>
			<input type="hidden" name="action" value="delete">
			 <input type="hidden" name="orderIdd" value="${aBean.orderId}">
			 <input type="hidden" name="whichPage" value="${param.whichPage}">
			 <input type="submit" value="刪除" onClick="return(confirm('您確定要刪除此訂單嗎??'))">
			 </FORM>
			</td>
		</tr>
			</c:forEach>
				<%@ include file="page2.file" %>
	</table>
	 
	</div>
	</div>
	
	<!-----------------------------------------main----------------------------------------->
	<%@ include file="../fragment/js.jsp"%>
</body>
</html>