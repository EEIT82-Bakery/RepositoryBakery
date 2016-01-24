<%@page import="com.product.model.ProductBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.product.model.*"%>
<%
	ProductBean productBean = (ProductBean) request.getAttribute("productBean"); //EmpServlet.java (Concroller), 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="../fragment/css.jsp"%>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/front/HtmlData/css/jquery-ui.min.css">
<script
	src="${pageContext.request.contextPath}/front/HtmlData/js/jquery-2.1.4.min.js"></script>
<script
	src="${pageContext.request.contextPath}/front/HtmlData/js/jquery-ui.min.js"></script>
</head>
<script type="text/javascript">
function clearForm() {
	var inputs = document.getElementsByTagName("input");
	for(var i=0; i<inputs.length; i++) {
		if(inputs[i].type=="text") {
			inputs[i].value="";
		}
	}
}
</script>
<body>
	<%@ include file="../fragment/main.jsp"%>
	<div class="col-xs-10 main">
		<h1 class="page-header">管理產品</h1>
		<div class="row producttable">
		<div class="col-xs-8">
			<form action='<c:url value="/ProductInsert.do"/>'
				method="POST" enctype="multipart/form-data">
				<div class="CSSTableGenerator">
				<table>				
						<tr>
							<th colspan="3">新增產品資料</th>
						</tr>
				
					<tr>
						<td>產品名稱 :</td>
						<td><input type="text" name="productName"
							value="${param.productName}" size="40"><br/>
							${errors.productName}
							</td>
						
					</tr>
					<tr>
						<td>產品材料描述 :</td>
						
						<td>
						<textarea name="productStatus" style="width:40%;height:150px;resize: none;">${param.productStatus}</textarea><br/>
<!-- 						<input type="text" name="productStatus" -->
<%-- 							value="${param.productStatus}" size="100"><br/> --%>
							${errors.productStatus}
							</td>				
					</tr>
					<tr>
						<td>價格 :</td>
						<td><input type="text" name="productPrice"
							value="${param.productPrice}" size="40"><br/>
							${errors.productPrice}
							</td>	
					</tr>
					<tr>
						<td>產品圖片 :</td>
						<td><input Type="file" name="mainPhoto"
							value="${param.mainPhoto}" size="40"><br/>
						${errors.mainPhoto}</td>
					</tr>
					<tr>
						<td>折扣 :</td>
						<td><input type="text" name="discount"
							value="${param.discount}" size="40"><br/>
						${errors.discount}</td>
					</tr>
					<tr>
						<td>日期 :</td>
						<td><input type="text" name="productDate" id="datepick"
						readonly="readonly"><br/>
						${errors.productDate}</td>
					</tr>
										<tr>
						<td>產品類型 :</td>
						<td>
						<jsp:useBean id="aBean" scope="page" class="com.product.model.ProductService"></jsp:useBean>						
						<select size="1" name="proTypeId">
							<c:forEach var="productVO" items="${aBean.selectType}">
								<option value="${productVO.productTypeId}">${productVO.productType}</option>
							</c:forEach>
						</select>
							${errors.productTypeId}</td>
					</tr>
					
					<tr>	
						<td colspan="2" align="center">
						<input type="submit" name="prodaction" value="Insert" onClick="return(confirm('你確定要新增此商品資訊??'))">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="button" value="Clear" onclick="clearForm()"></td>
					</tr>
				</table>
				</div>
				</form>
				
				</div>
			
		</div>
	</div>
<%-- 	<%@ include file="../fragment/js.jsp"%> --%>
<script>
		$(function() {
			$("#datepick").datepicker({
				dateFormat : 'yy-mm-dd',
			});
		});
	</script>
</body>
</html>