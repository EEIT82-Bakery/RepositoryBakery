<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


    
   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>測試頁</title>
</head>
<body>

<table>
	<tr>
		<td>帳號 : </td>
		<td><input type="text" name="account" value="${param.account}"></td>
		<td>${errors.account}</td>
	</tr>
	<tr>
		<td>暱稱 : </td>
		<td><input type="text" name="nickname" value="${param.nickname}"></td>
		<td></td>
	</tr>

	<tr>
		<td>性別 : </td>
		<td><input type="text" name="sex" value="${param.sex}"></td>
		<td></td>
	</tr>
	<tr>
		<td>生日 : </td>
		<td><input type="text" name="birth" value="${param.birth}"></td>
		<td></td>
	</tr>
	<tr>
		<td>電話: </td>
		<td><input type="text" name="phone" value="${param.phone}"></td>
		<td>${errors.phone}</td>
	</tr>
	<tr>
		<td>
			<input type="submit" name="action" value="Select">
		</td>
	</tr>
</table>



</body>
</html>