<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert</title>





</head>
<body>


	
	
	
	
	
	
	
	<h1>12</h1>
	
	
	
	
	
	
	
	
	<table border="1">
		<thead>	
		<tr>
			<td>寄件人</td>
			<td>收件人</td>
			<td>訊息標題</td>
			<td>寄送時間</td>
			<td>寄信狀態</td>
		</tr>
		

		<c:forEach var="Go" items="${list}">
		<tr>
			<td>${Go.send_id}</td>
			<td>${Go.read_id}</td>
			<td><a href="http://tw.yahoo.com" name="action">123</a></td>
			
			<td>${Go.msg_date}</td>

			<td>
			 <c:if test="${Go.msg_state==1}">
		
		<span style="font-weight: bold;">未讀</span>
				</c:if>
			<c:if test="${Go.msg_state==2}">
			<span style="font-weight: bold;">已讀</span>
				
		</c:if>
		</td>
				</tr>
		</c:forEach>
	
		</thead>
			
	</table>
	
	
	
	
		<c:if test="${not empty pageCount}">
				<c:forEach var="page" begin="1" end="${pageCount}">

					<a href="${pageContext.request.contextPath}/MessageServlet.do?action=select&pages=${page}">
						<c:out value="${page}" /></a>
				</c:forEach>
		</c:if>
	
	
	
	
	
	
	
	
<%-- <c:choose> --%>
	
<%-- 	<c:when test="${IsMyFriend==0}"> --%>
<!-- 		<form method="post" -->
<%-- 			action="<%=request.getContextPath()%>/Friend_ListServlet"> --%>
<!-- 			<div class="ui primary mini labeled icon button" -->
<!-- 				onclick="$(this).parent().submit()"> -->
<!-- 				<i class="add user icon"></i>加入好友 -->
<!-- 			</div> -->
<%-- 			<input type="hidden" name="invite_id" value="${memberUser.mem_id}"> --%>
<%-- 			<input type="hidden" name="invitee_id" value="${memberVO.mem_id}"> --%>
<%-- 			<input type="hidden" name="msg_tit" value="${memberUser.mem_name}"> --%>
<%-- 			<input type="hidden" name="msg_cont" value="${memberUser.mem_name}"> --%>
<!-- 			<input type="hidden" name="requestURL" -->
<%-- 				value="<%=request.getServletPath()%>"> <input type="hidden" --%>
<!-- 				name="action" value="AddNewfriend"> -->
<!-- 		</form> -->
<%-- 	</c:when> --%>

<%-- </c:choose> --%>

</body>
</html>