<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.message.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%
	HttpSession session2 = request.getSession();
	MemberBean memberbean = (MemberBean) session2.getAttribute("isLogin");
	Integer invite_id = memberbean.getMember_id();

%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" href="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.css">
<script src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
<script src="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.js"></script>

</head>
<body>

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
					<table>
						<thead>
							<tr>
								<th>寄件人</th>
								<th>收信人</th>
								<th>信件標題</th>
								<th>發信時間</th>
							</tr>
						</thead>
					<tr>
						<td>${bean.send_id}</td>
						<td>${bean.read_id}</td>
						<td>${bean.msg_tit}</td>
						<td>
							<fmt:formatDate value="${bean.msg_date}" pattern="yyyy-MM-dd HH:mm:ss"/><!-- 改時間格式 -->
						</td>
					</tr>
					</table>
				
					<h5 class="ui top attached header">信件內容</h5>
					<div class="ui attached segment"><pre>${bean.msg_cont} <a href="${pageContext.request.contextPath}/FriendServlet.controller?action=agree&invite_id=${bean.send_id}&invitee_id=${bean.read_id}&msg_date=${bean.msg_date}" onclick="$('>form',this).submit()">接受</a>/<a href="#" >拒絕</a></pre></div>
			
			
		</div>
			</div>	
</body>
</html>