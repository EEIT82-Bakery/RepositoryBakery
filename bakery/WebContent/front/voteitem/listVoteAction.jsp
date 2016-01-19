<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.Voteaction.model.*"%>
<% 
		VoteActionService vas= new VoteActionService();
		List<VoteActionBean> list=vas.selectall();
		pageContext.setAttribute("list",list);
%>

<!DOCTYPE html>
<html>
<head>
<%@ include file="../fragment/css.jsp"%>
<style>
.tab1 {
	width: 80%;
	border: 2PX solid;
	text-align: center;
	margin: 0 auto;
}

tr, td {
	border: 2PX solid;
	text-align: center;
}
</style>

</head>
	<!-----------------------------------------nav------------------------------------------>
	<%@ include file="../fragment/nav.jsp"%>
	<!-----------------------------------------nav------------------------------------------>
	<div class="container-fluid">
		<!-----------------------------------------main----------------------------------------->

<h4>活動項目</h4>
<div class="row">
<div class="col-xs-9">

	<table id="tab1" class="table table-hover">
		<tr>
		<td>編號</td>
		<td>活動標題</td>
		<td style="text-align:left">活動內容</td>
		<td>開始日期</td>
		<td>結束日期</td>
		
		</tr>
	
	<c:forEach var="vote" items="${list}" >
		<tr>
		<td>${vote.voteId}</td>
		<td><a href="login.jsp">${vote.voteTitle}</a></td>
		<td style="text-align:left">${vote.voteDescribe}</td>
		<td>${vote.voteStartDate}</td>
		<td>${vote.voteEndDate}</td>
		</tr>
		</c:forEach>
	</table>
			<input type="button" value="到首頁" onClick="javascript:window.open('#')"> 
			<input type="button" value="連絡我們" onClick="javascript:window.open('#')"> 
			<img src="#"style="width: 100%; height: 100%;" alt="" />
		</div>
<div class="col-xs-3">


</div>

</div>


		<!-----------------------------------------main----------------------------------------->
		<!--------footer-------->
		<%@ include file="../fragment/footer.jsp"%>
		<!--------footer-------->
	</div>
	<%@ include file="../fragment/js.jsp"%>
</body>
</html>
