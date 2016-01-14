<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.member.model.*" %>
<%@ page import="com.member.controller.*" %>


<%-- <% --%>
//     MemberService memSvc = new MemberService();
//     List<MemberBean> list = memSvc.getAllMem(account);
//     pageContext.setAttribute("list",list);
<%-- %> --%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>會員專區</title>
</head>
<body>
	
<%-- 	<form action="<c:url value='${pageContext.request.contextPath}/mobie/homeindex.do' />" method="post"> --%>

<%-- 	<c:if test="${MemberMoo.account ==}"> --%>
<%-- 		<c:set /> --%>
<%-- 	</c:if> --%>

			會員帳號${MemberMoo.account}
			會員姓名${MemberMoo.username}
			會員生日${MemberMoo.nickname}

	

	<h2></h2>
	</form>
</body>
</html>