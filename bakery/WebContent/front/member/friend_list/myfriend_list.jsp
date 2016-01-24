<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>焙客栗工坊</title>
<link href="${pageContext.request.contextPath}/front/HtmlData/css/semantic.min.css" rel="stylesheet" />
<style type="text/css">
#t-cards {
	padding-top: 80px;
	padding-bottom: 80px;
}

/********************************/
/*          Panel cards         */
/********************************/
.panel.panel-card {
	position: relative;
	border: none;
	overflow: hidden;
}

.panel.panel-card .panel-heading {
	position: relative;
	z-index: 2;
	height: 120px;
	border-bottom-color: #fff;
	overflow: hidden;
	-webkit-transition: height 600ms ease-in-out;
	transition: height 600ms ease-in-out;
}

.panel.panel-card .panel-heading img {
	position: absolute;
	top: 50%;
	left: 50%;
	z-index: 1;
	width: 120%;
	-webkit-transform: translate3d(-50%, -50%, 0);
	transform: translate3d(-50%, -50%, 0);
}

.panel.panel-card .panel-heading button {
	position: absolute;
	top: 10px;
	right: 15px;
	z-index: 3;
}

.panel.panel-card .panel-figure {
	position: absolute;
	top: auto;
	left: 50%;
	z-index: 3;
	width: 70px;
	height: 70px;
	background-color: #fff;
	border-radius: 50%;
	-webkit-box-shadow: 0 0 0 3px #fff;
	box-shadow: 0 0 0 3px #fff;
	-webkit-transform: translate3d(-50%, -50%, 0);
	transform: translate3d(-50%, -50%, 0);
}

.panel.panel-card .panel-figure img {
	opacity: 1;
	-webkit-transition: opacity 400ms ease-in-out;
	transition: opacity 400ms ease-in-out;
}

.panel.panel-card .panel-figure img:hover {
	opacity: .75;
}

.panel.panel-card .panel-body {
	padding-top: 40px;
	padding-bottom: 20px;
	-webkit-transition: padding 400ms ease-in-out;
	transition: padding 400ms ease-in-out;
}

.panel.panel-card .panel-thumbnails {
	padding: 0 15px 20px;
}

.panel-thumbnails .thumbnail {
	width: 60px;
	max-width: 100%;
	margin: 0 auto;
	background-color: #fff;
}

#friendpicture {
	width: 73px;
	height: 73px;
}
#btn{
	width:50px;
	height:50px;
}
form{
    float:left;
}
</style>
<%@ include file="../../fragment/css.jsp"%>
</head>
<body>
	<%@ include file="../../fragment/nav.jsp"%>
    <div class="container">
        <div class="row">
          <p class="bg-info">我的好友列表</p>
			<div class="ui action input">
				<input type="text" placeholder="Search...">
				<button class="ui button">Search</button>
			</div>

			<div class="ui large buttons">
           <a href="${pageContext.request.contextPath}/FriendListServlet.do?invited=${isLogin.member_id}&pages=1"
					class="ui button" id="submit_demo1">全部好友</a> <div class="or"></div>
			<form method="post" action='<c:url value="/FriendServlet.controller"/>'>
					<input type="submit" class="ui button" id="submit_demo2" value="好友邀請">
								<input type="hidden" name="invitee_id" value="${isLogin.member_id}">
								<input type="hidden" name="action" value="Select_addme">
					</form>
				  </div>
			</div>  
     
        <hr>
         <c:if test="${empty list}">
          <p>目前無好友</p>
        </c:if>
     
     
        <c:forEach varStatus="stVar" var="Go" items="${list}">
       
        
         	
             <div class="col-sm-6 col-md-3">
                <div class="panel panel-default panel-card">
                    <div class="panel-heading">
                        <img src="https://unsplash.imgix.net/12/barley.jpg?q=75&fm=jpg&s=f39de5ca1970a13dbe6af6c86b3c47ec" />
                        <button class="btn btn-primary btn-sm" role="button">Follow</button>
                    </div>
                    <div class="panel-figure">
                        <img class="img-responsive img-circle" src="${pageContext.request.contextPath}/DBGifReader.do?memberId=${Go.invitee_id}" id="friendpicture" alt="個人照片"/>
                    </div>
                    <div class="panel-body text-center">
                        <h4 class="panel-header"><a href="${pageContext.request.contextPath}/homeindex.do?account=${Go.inviteeAccount}">(帳號:)${Go.inviteeAccount}</a></h4>
                        <small>A short description goes here.</small>
                    </div>
                    <div class="panel-thumbnails">
                        <div class="row">
                            <div class="col-xs-6">
                                <div class="thumbnail">
                                    <img src="http://placemi.com/mzwlj/60x60" />
                                </div>
                            </div>
                            <div class="col-xs-6">
                                <div class="thumbnail">
                                <form action="${pageContext.request.contextPath}/FriendServlet.controller" method="post">
                                <button type="submit" class="btn btn-danger" id="btn" onclick="return confirm('確定刪除好友嗎?')">刪除</button>
                                <input type="hidden" name="invite_id" value="${isLogin.member_id}">
                                <input type="hidden" name="invitee_id" value="${Go.invitee_id}">
                                <input type="hidden" name="page" value="${page}">
                                <input type="hidden" name="action" value="deletefriend">
                                </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>   
    		</div>
		   	 </c:forEach>
	  		 	 </div>
   		 	</div>
  <c:if test="${not empty list}">
   	  <div class="container">
        <div class="row">
   	   <div class="col-sm-6- col-md-6">
   	 <c:if test="${not empty pageCount}">
				<c:forEach var="page" begin="1" end="${pageCount}">
				<c:if test="${not empty isLogin.member_id}">
					<a href="${pageContext.request.contextPath}/FriendListServlet.do?invited=${isLogin.member_id}&pages=${page}">
						<b style="font-size:2em;">${page}</b>
						</a>&nbsp;&nbsp;&nbsp;
					</c:if>
<%-- 					<c:if test="${empty isLogin.member_id}"> --%>
<%-- 					<a href="${pageContext.request.contextPath}/FriendServlet.controller?action=friendlist&invited=${member.member_id}&pages=${page}"> --%>
<%-- 						<b style="font-size:2em;">${page}</b> --%>
<!-- 						</a>&nbsp;&nbsp;&nbsp; -->
<%-- 					</c:if> --%>
				</c:forEach>
				</c:if>
   			 </div>
   		 </div>
   	 </div>
   	    </c:if>
<!-- </section> -->
</body>
</html>
