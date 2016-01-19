<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<table border="1">
		<thead>	
		<tr>
			<td>寄件人</td>
			<td>收件人</td>
			<td>訊息標題</td>
			<td>寄送時間</td>
			<td>寄信狀態</td>
		</tr>
		
		<tr>
			<td>1</td>
			<td>1</td>
			<td>1</td>
			<td>1</td>
			<td>1</td>

		</tr>
		</thead>
	
	
	
	</table>
<c:choose>
	
	<c:when test="${IsMyFriend==0}">
		<form method="post"
			action="<%=request.getContextPath()%>/Friend_ListServlet">
			<div class="ui primary mini labeled icon button"
				onclick="$(this).parent().submit()">
				<i class="add user icon"></i>加入好友
			</div>
			<input type="hidden" name="invite_id" value="${memberUser.mem_id}">
			<input type="hidden" name="invitee_id" value="${memberVO.mem_id}">
			<input type="hidden" name="msg_tit" value="${memberUser.mem_name}">
			<input type="hidden" name="msg_cont" value="${memberUser.mem_name}">
			<input type="hidden" name="requestURL"
				value="<%=request.getServletPath()%>"> <input type="hidden"
				name="action" value="AddNewfriend">
		</form>
	</c:when>

</c:choose>

</body>
</html>