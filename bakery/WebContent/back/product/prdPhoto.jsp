<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.product.model.*"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="../fragment/css.jsp"%>
</head>
<body>
	<%@ include file="../fragment/main.jsp"%>
	<!-----------------------------------------main----------------------------------------->
	<div class="col-xs-10 main">
		<h1 class="page-header">首頁</h1>
	
    <FORM  ACTION="<c:url value='/ReadPhoto.do' />" METHOD="post">
        <b>輸入產品名稱</b>
        <input type="text" name="productName">
        <input type="submit" value="送出">
    </FORM>
<table border='1' bordercolor='#CCCCFF' width="100%" style="table-layout:fixed">
	<tr>
		<th>產品ID</th>
		<th>產品圖片</th>
	</tr>
	<c:forEach var="photo" items="${photos}">
		<tr align='center' valign='middle'>
			<td>
			<c:if test="${not empty photo.photo1}">
			<img src="data:image/png;base64,${photo.photo1}" width="30px" height="30px">
			</c:if>
			<c:if test="${empty photo.photo1}">
			<img src="<%=request.getContextPath()%>/back/HtmlData/images/logo.png" width="30px" height="30px">
			</c:if>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/ProductPhotoServlet" enctype="multipart/form-data">
			     <input type="file">
			     <input type="submit" value="新增" name="photo">
			     <input type="submit" value="刪除" name="photo">
			     <input type="hidden" value="${photo.product_id}" name="productId">
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