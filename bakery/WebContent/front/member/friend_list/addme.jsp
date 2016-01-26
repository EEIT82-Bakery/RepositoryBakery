<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>   
<%@ page import="com.friend.model.*"%>    
    
<%
	FriendService friendservice = new FriendService();
	Integer invitee_id = new Integer(request.getParameter("invitee_id"));
	List<FriendBean> lists = friendservice.getWhoInvitedMe(invitee_id, 0);
	pageContext.setAttribute("list", lists);

%>    
    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/front/HtmlData/css/semantic.min.css" rel="stylesheet" />

<style type="text/css">
.forumtitle {
	height: 29px;
	background-color: #e5e5e5;
	color: black;
}
.display {
	padding: 0;
}
.display table {
	width: 100%;
	text-align: center;
}
.demoPadder {
	padding-top: 15px;
}
.btn-default {
	color: #007aff;
	background-color: #fff;
	border-color: #007aff;
}
.btn-default:hover, .btn-default:focus, .btn-default:active,
	.btn-default.active, .open>.btn-default.dropdown-toggle {
	color: #fff;
	background-color: #007aff;
	border-color: #007aff;
}



.panel > .list-group .list-group-item:first-child {
    /*border-top: 1px solid rgb(204, 204, 204);*/
}
@media (max-width: 767px) {
    .visible-xs {
        display: inline-block !important;
    }
    .block {
        display: block !important;
        width: 100%;
        height: 1px !important;
    }
}
#back-to-bootsnipp {
    position: fixed;
    top: 10px; right: 10px;
}




.c-list {
    padding: 0px;
    min-height: 44px;
}
.title {
    display: inline-block;
    font-size: 1.7em;
    font-weight: bold;
    padding: 5px 15px;
}
ul.c-controls {
    list-style: none;
    margin: 0px;
    min-height: 44px;
}


ul.c-controls li {
    margin-top: 8px;
    float: left;
}

ul.c-controls li a {
    font-size: 1.7em;
    padding: 11px 10px 6px;   
}
ul.c-controls li a i {
    min-width: 24px;
    text-align: center;
}

ul.c-controls li a:hover {
    background-color: rgba(51, 51, 51, 0.2);
}

.c-toggle {
    font-size: 1.7em;
}

.name {
    font-size: 1.7em;
    font-weight: 700;
}

.c-info {
    padding: 5px 10px;
    font-size: 1.25em;
}
.styled-button-2 {
	-webkit-box-shadow:rgba(0,0,0,0.2) 0 1px 0 0;
	-moz-box-shadow:rgba(0,0,0,0.2) 0 1px 0 0;
	box-shadow:rgba(0,0,0,0.2) 0 1px 0 0;
	border-bottom-color:#333;
	border:1px solid #61c4ea;
	background-color:#7cceee;
	border-radius:5px;
	-moz-border-radius:5px;
	-webkit-border-radius:5px;
	color:#333;
	font-family:'Verdana',Arial,sans-serif;
	font-size:14px;
	text-shadow:#b2e2f5 0 1px 0;
	padding:5px
}
.styled-button-7 {
	background: #FF5DB1;
	background: -moz-linear-gradient(top,#FF5DB1 0%,#E94A86 100%);
	background: -webkit-gradient(linear,left top,left bottom,color-stop(0%,#FF5DB1),color-stop(100%,#E94A86));
	background: -webkit-linear-gradient(top,#FF5DB1 0%,#E94A86 100%);
	background: -o-linear-gradient(top,#FF5DB1 0%,#E94A86 100%);
	background: -ms-linear-gradient(top,#FF5DB1 0%,#E94A86 100%);
	background: linear-gradient(top,#FF5DB1 0%,#E94A86 100%);
	filter: progid: DXImageTransform.Microsoft.gradient( startColorstr='#FF5DB1', endColorstr='#E94A86',GradientType=0);
	padding:5px 7px;
	color:#fff;
	font-family:'Helvetica Neue',sans-serif;
	font-size:12px;
	border-radius:4px;
	-moz-border-radius:4px;
	-webkit-border-radius:4px;
	border:1px solid #E8124F
}
      

</style>
<%@ include file="../../fragment/css.jsp"%>
</head>
<body>
<%@ include file="../../fragment/nav.jsp"%>
<div class="ui massive message">
<h3 align="center">${fn:length(list)} 位使用者加您好友</h3>
</div>
 <div class="container">
        <div class="row">
			   <div >
			  	<a href="${pageContext.request.contextPath}/FriendListServlet.do?invited=${isLogin.member_id}&pages=1"
					class="positive ui button" ><i class="user icon"></i>全部好友</a>
		
			</div>  

		
			
		<div class="col-md-12">     
                <ul class="list-group" id="contact-list">
                <c:forEach var="list" items="${list}">
                    <li class="list-group-item">
                        <div class="col-xs-12 col-sm-3">
                            <img src="${pageContext.request.contextPath}/DBGifReader.do?memberId=${list.invite_id}" alt="Scott Stevens" style="height: 248px;width: 197px"/>
                        </div>
                        <div class="col-xs-12 col-sm-9">
                            <span class="name">${list.inviteAccount}(${list.inviteNickname})</span><br/>
                            <span class="name">想成為您的好友。</span>
                        </div>
                        <div  class="ui buttons">
                        <form method="post" action='<c:url value="/FriendServlet.controller"/>'>
                        <input type="submit" class="ui button" value="接受" /> 
                        	<input type="hidden" name="invite_id" value="${list.invite_id}">
                        	<input type="hidden" name="invitee_id" value="${isLogin.member_id}">
                        	<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
							<input type="hidden" name="action" value="agree">
                        </form>
                          <div class="or" data-text="or"></div>
                        <form method="post" action='<c:url value="/FriendServlet.controller"/>'>
                        <input type="submit" class="ui positive button" value="拒絕" /> 
                        	<input type="hidden" name="invite_id" value="${list.invite_id}">
                        	<input type="hidden" name="invitee_id" value="${isLogin.member_id}">
                        	<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
							<input type="hidden" name="action" value="noagree">
                       	</form>
                       	</div>
                        <div class="clearfix"></div>
                    </li>
                   </c:forEach>
                </ul>
            </div>
        </div>
    </div>
    
    <div id="cant-do-all-the-work-for-you" class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-sm">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                    <h4 class="modal-title" id="mySmallModalLabel">Ooops!!!</h4>
                </div>
                
            </div>



	</div>
</div>

</body>
</html>