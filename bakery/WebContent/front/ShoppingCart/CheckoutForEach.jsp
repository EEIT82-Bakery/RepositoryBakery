<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="java.util.*" %>
<%@ page import="com.product.model.ProductBean" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<%@ include file="../fragment/css.jsp"%>
<link
	href="${pageContext.request.contextPath}/front/HtmlData/css/product.css"
	rel="stylesheet">
 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <style>
 #count{
    float: left;
    margin-left: 750px;
 }
 
 #cherk{
    position: relative;
    left: 700px;
}
td{
style:text-align:left;
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


<FORM action="<c:url value='ProcessOrder.do' />" method="POST">
<div class="row" align="center">
<TABLE border='1' width="810" style="background:#F5EBFF; border-color:rgb( 100, 100, 255);border-style: outset;">
      <TR >
         <TD>會員編號：${LoginOK.memberId}</TD>
         <TD>客戶姓名：${LoginOK.name}</TD>
         <TD>訂單日期：<fmt:formatDate value="" pattern="yyyy-MM-dd"/></TD>
      </TR>
      <TR>
         <TD colspan='3'>會員地址：${LoginOK.address}</TD>
      </TR>
      <TR>
         <TD colspan='3'>
           出貨地址：<Input style="background:#ECFFCD;" size="60" type="text" 
                      name="ShippingAddress" value="${LoginOK.address}">
         </TD>
      </TR>
      <TR>
         <TD colspan='3'>
           統一編號：<Input style="background:#ECFFCD;" size="10" type="text" 
                      name="BNO" value="12345678">
         </TD>
      </TR>
      <TR>
         <TD colspan='3'>
           發票抬頭：<Input style="background:#ECFFCD;" size="50" type="text" 
                      name="InvoiceTitle" value="人間文摘股份有限公司台灣分公司" >
         </TD>
      </TR>
    
      <TR>
         <TD colspan='3'>
<table border="1" width="600">
	<tr bgcolor="#999999">
		<th width="200">產品名稱</th>
		<th width="100">價格</th>
		<th width="100">折扣</th>
		<th width="100">數量</th>
	</tr>
	<c:forEach var="list" items="${shoppingcart}">	
	<tr>	
		<td width="200"><div align="center"><b>${list.productName}</b></div></td>
		<td width="100"><div align="center"><b>${list.productPrice}</b></div></td>
		<td width="100"><div align="center"><b>${list.discount}</b></div></td>
		<td width="100"><div align="center"><b>${list.quantity}</b></div></td>	
	</tr>

	</c:forEach>
	<tr>											
		<td colspan="4" align="right"><font color="red" ><b>總金額：${amount}</b></font></td>
	</tr>
</table>
	<div id="cherk">
	<a href="#">
	<input type="button" value="確認送出"/>
	</a>
	</div>
</div>
</FORM>

<%@ include file="../fragment/footer.jsp"%>
<%@ include file="../fragment/js.jsp"%>
</body>
</html>
