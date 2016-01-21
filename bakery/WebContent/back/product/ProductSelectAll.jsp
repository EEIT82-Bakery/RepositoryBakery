<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.product.model.*"%>
<%@ page import="java.util.*"%>
<%
	ProductService empSvc = new ProductService();
    List<ProductBean> list = empSvc.getAllProducts();
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
#buttonRight{
	float: right;
}
</style>
</head>
<body>
	<%@ include file="../fragment/main.jsp"%>
	<!-----------------------------------------main----------------------------------------->
	<div class="col-xs-10 main">
	<a href="<%=request.getContextPath()%>/back/product/ProductInsert.jsp"><input type="button" value="新增產品" id="buttonRight"></a>
		<h1 class="page-header">管理產品</h1> 
		
<ul>
  <li id="nav1">
   <FORM METHOD="post" action='<c:url value="/ProductSelectOneServlet.do"/>'>
        <b>輸入產品名稱</b>
        <input type="text" name="productName">
        <input type="submit" value="送出">
        <span>${errors.productName}${errors.productNoData}${errors.productNullData}</span>
        <input type="hidden" name="action" value="getOne_For_Display">
    </FORM>
  </li>
  
</ul>
<br/>
<div class="CSSTableGenerator" >
<table width="100%">
	<tr>
		<th>產品ID</th>
		<th>產品名稱</th>
		<th>產品種類</th>
		<th width="20%">產品描述</th>
		<th>價格</th>
		<th>產品圖片</th>
		<th>折扣</th>
		<th>日期</th>
		<th>修改</th>
		<th>刪除</th>
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="aBean" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr align='center' valign='middle'>
			<td>${aBean.productId}</td>
			<td>${aBean.productName}</td>
			<td>${aBean.productType}</td>
			<td>${aBean.productStatus}</td>
			<td>${aBean.productPrice}</td> 
			<td><img src="<%=request.getContextPath()%>/DBGifReader.do?productId=${aBean.productId}" width="30px" height="30px"></td>
			<td>${aBean.discount}</td>
			<td>${aBean.productDate}</td>
			<td>
			  <FORM METHOD="post" action='<c:url value="/ProductUpdateServlet.do"/>'>
			     <input type="submit" value="修改">
			     <input type="hidden" name="productId" value="${aBean.productId}">
			     <input type="hidden" name="whichPage" value="${param.whichPage}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" action='<c:url value="/ProductDeleteServlet.do"/>'>
			    <input type="submit" value="刪除">
			     <input type="hidden" name="whichPage" value="${param.whichPage}">
			    <input type="hidden" name="productId" value="${aBean.productId}"><span>${errors.deleteNo}</span>
			    <input type="hidden" name="action"value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
	<%@ include file="page2.file" %>
</table>

	</div>
	<!-----------------------------------------main----------------------------------------->
	</div>
	
	<%@ include file="../fragment/js.jsp"%>
</body>
</html>