<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.message.model.*"%>   
<%@page import="java.util.*" %>
<% 


	MessageService messageservice = new MessageService();
	MessageBean messagebean = new MessageBean();
	List<MessageBean> list = messageservice.selectMessage();
	pageContext.setAttribute("status", list);
	
	
	messagebean = messageservice.seletStatus();
	Integer status = messagebean.getMsg_state();
	
		pageContext.setAttribute("all", status);
	
		
		
		
		
		
		
		
%>


    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.css">
<script src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
<!-- <script src="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.js"></script> -->
<%@ include file="../../fragment/css.jsp"%>

<style>
ul#Navigation .active{
    border-color: white;
    border-left-color: black; 
    border-top-color: black;
    color: white; 
    background-color: #adadad;
}
#thead{
background-color:#FFDEBD;
    color: black;
    }

</style>
</head>
<body>


<%@ include file="../../fragment/nav.jsp"%>


<!-- <ul id="Navigation"> -->
<%-- 	<li><a href="${pageContext.request.contextPath}/MessageServlet.do?action=select&pages=1"  class="current-page">全部</a></li> --%>
<%-- 	<li><a href="${pageContext.request.contextPath}/MessageServlet.do?action=selecread&statu=1&pages=1" >信件:未讀</a></li> --%>
<%-- 	<li><a href="${pageContext.request.contextPath}/MessageServlet.do?action=selecNoRead&pages=1">信件:已讀</a></li> --%>
<!-- </ul> -->
		
		
<!-- <div data-role="controlgroup" data-type="horizontal"> -->
<%--   <a href="${pageContext.request.contextPath}/MessageServlet.do?action=select&pages=1" class="ui-btn">全部</a> --%>
<%--   <a href="${pageContext.request.contextPath}/MessageServlet.do?action=selecread&statu=1&pages=1" class="ui-btn">未讀</a> --%>
<%--   <a href="${pageContext.request.contextPath}/MessageServlet.do?action=selecNoRead&pages=1" class="ui-btn">已讀</a> --%>
<!-- </div>	 -->
				
<div data-role="page" id="pageone">
  <div data-role="header">
    <h1>我的信件</h1>
    <div data-role="navbar">
      <ul>
       <li><a href="${pageContext.request.contextPath}/MessageServlet.do?action=select&pages=1" class="ui-btn">全部</a></li>
  	   <li><a href="${pageContext.request.contextPath}/MessageServlet.do?action=selecread&statu=1&pages=1" class="ui-btn">未讀</a></li>
       <li><a href="${pageContext.request.contextPath}/MessageServlet.do?action=selecNoRead&pages=1" class="ui-btn">已讀</a></li>
      </ul>
    </div>
  </div>	
		
  
<div data-role="main" class="ui-content">									
	<table align="center" width="100% " >
		<thead id="thead">
		<tr>
			<td width="15%" style="padding-left:20px">寄件人</td>
			<td width="15%" style="padding-left:20px">收件人</td>
			<td width="40%" style="padding-left:20px">訊息標題</td>
			<td width="20%" style="padding-left:20px">寄送時間</td>
			<td width="10%" style="padding-left:20px">寄信狀態</td>
		</tr>	
	
		</thead>
		
		<tbody>
	
		<c:forEach var="Go" items="${list}">
		<tr>
			<td width="15%" style="padding-left:20px">${Go.sendAccount}</td>
			<td width="15%" style="padding-left:20px">
			<c:if test="${Go.read_id==isLogin.member_id}">
					<p>${Go.readAccount}</p>
			</c:if>
			</td>
			<td width="40%" style="padding-left:20px"><a href="${pageContext.request.contextPath}/MessageServlet.do?action=count&send_id=${Go.send_id}&read_id=${Go.read_id}&msg_date=${Go.msg_date}">${Go.msg_tit}</a></td>
			<td width="20%" style="padding-left:20px">${Go.mdate}</td>

			<td width="10%" style="padding-left:20px">
			 <c:if test="${Go.msg_state==1}">
		
		<span style="font-weight: bold;">未讀</span>
				</c:if>
			<c:if test="${Go.msg_state==2}">
			<span style="font-weight: bold;">已讀</span>
				
		</c:if>
		</td>
				</tr>
		</c:forEach>
	</tbody>
	
			
	</table>
		<c:if test="${not empty pageCount}">
				<c:forEach var="page" begin="1" end="${pageCount}">

					<a href="${pageContext.request.contextPath}/MessageServlet.do?action=select&pages=${page}">
						<c:out value="${page}" /></a>
				</c:forEach>
		</c:if>
	 </div>	
	 </div>
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


<script type="text/javascript">
$(document).ready(function() {
    var urlParts = window.location.pathname.split("/"),
        // get the last section of the url
        currentPage = urlParts[urlParts.length - 1];

    $("#navigation a[href*=" + currentPage + "]").addClass("current-page");
});
</script>


</body>
</html>