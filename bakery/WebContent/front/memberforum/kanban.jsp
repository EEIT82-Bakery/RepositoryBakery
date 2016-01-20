<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
/* Profile container */
.profile {
	margin: 20px 0;
}

/* Profile sidebar */
.profile-sidebar {
	padding: 20px 0 10px 0;
	background: #fff;
	min-height: 492px;
}

.profile-userpic img {
	float: none;
	margin: 0 auto;
	width: 140px;
	height: 197px;
	-webkit-border-radius: 50% !important;
	-moz-border-radius: 50% !important;
	border-radius: 50% !important;
}

.profile-usertitle {
	text-align: center;
	margin-top: 20px;

	
}

.profile-usertitle-name {
	color: #5a7391;
	font-size: 16px;
	font-weight: 600;
	margin-bottom: 7px;
}

.profile-usertitle-job {
	color: #5b9bd1;
	font-size: 12px;
	font-weight: 600;
	margin-bottom: 15px;
}

.profile-userbuttons {
	text-align: center;
	margin-top: 10px;
}

.profile-userbuttons .btn {
	text-transform: uppercase;
	font-size: 11px;
	font-weight: 600;
	padding: 6px 15px;
	margin-right: 5px;
}

.profile-userbuttons .btn:last-child {
	margin-right: 0px;
}

.profile-usermenu {
	margin-top: 20px;
	

}

.profile-usermenu ul li {
	border-bottom: 1px solid #f0f4f7;
}

.profile-usermenu ul li:last-child {
	border-bottom: none;
}

.profile-usermenu ul li a {
	color: #93a3b5;
	font-size: 14px;
	font-weight: 400;
}

.profile-usermenu ul li a i {
	margin-right: 8px;
	font-size: 14px;
}

.profile-usermenu ul li a:hover {
	background-color: #fafcfd;
	color: #5b9bd1;
}

.profile-usermenu ul li.active {
	border-bottom: none;
}

.profile-usermenu ul li.active a {
	color: #5b9bd1;
	background-color: #f6f9fb;
	border-left: 2px solid #5b9bd1;
	margin-left: -2px;
}

/* Profile Content */
.profile-content {
	padding: 20px;
	background: #fff;
	min-height: 492px;
}

p { 
padding: 0 0 1em; 
} 
.message_list { 
list-style: none; 
margin: 0; 
padding: 0; 
width: 383px; 
} 
.message_list li { 
padding: 0; 
margin: 0; 
background: url(images/message-bar.gif) no-repeat; 
} 
.message_head { 
padding: 5px 10px; 
cursor: pointer; 
position: relative; 
} 
.message_head .timestamp { 
color: #666666; 
font-size: 95%; 
position: absolute; 
right: 10px; 
top: 5px; 
} 
.message_head cite { 
font-size: 100%; 
font-weight: bold; 
font-style: normal; 
} 
.message_body { 
padding: 5px 10px 15px; 
} 
.collapse_buttons { 
text-align: right; 
border-top: solid 1px #e4e4e4; 
padding: 5px 0; 
width: 383px; 
} 
.collapse_buttons a { 
margin-left: 15px; 
float: right; 
} 
.show_all_message { 
background: url(images/tall-down-arrow.gif) no-repeat right center; 
padding-right: 12px; 
} 
.show_recent_only { 
display: none; 
background: url(images/tall-up-arrow.gif) no-repeat right center; 
padding-right: 12px; 
} 
.collpase_all_message { 
background: url(images/collapse-all.gif) no-repeat right center; 
padding-right: 12px; 
color: #666666;
}


 .slide_toggle {
            text-align: center;
            cursor: pointer;
            font-weight: bold;
            /* 收合提示字串區塊, 還可加入自訂 CSS 效果 */
            line-height: 170%;
            background-color: #eed;
            border-radius: 7px;
        }

            .slide_toggle + div {
                display: none;
                margin-top: 10px;
                /* 隱藏註解文字區塊, 請加入自訂 CSS 效果 */
            }
	

</style>
</head>
<body>
<%@ include file="../fragment/css.jsp"%>
<%@ include file="../fragment/nav.jsp"%>

<div class="container-fluid">
		<!-----------------------------------------main----------------------------------------->
		<c:if test="${not empty kanban}">
			<div class="row profile">
				<div class="col-xs-3">
					<div class="profile-sidebar">
						<div class="profile-userpic">
							<img src="data:image/png;base64,${kanban.mpicture}" class="img-responsive" alt="">
						</div>
						<div class="profile-usertitle">
							<div class="profile-usertitle-name">${kanban.nickname}</div>
							<div class="profile-usertitle-job">
								<c:url value="/front/memberforum/2.jsp" var="path">
									<c:param name="accoun" value="${kanban.account}" />
									<c:param name="nickname" value="${kanban.nickname}" />
									<c:param name="sex" value="${kanban.sex}" />
									<c:param name="birth" value="${kanban.birth}" />
									<c:param name="phone" value="${kanban.phone}" />
									<c:param name="phone" value="${kanban.phone}" />
									<c:param name="phone" value="${kanban.phone}" />
								</c:url>
								<a href="${pageContext.request.contextPath}/homeindex.do?account=${member.account}">${member.account}</a>			
							</div>
						</div>
						<!-- END SIDEBAR USER TITLE -->
						<!-- SIDEBAR BUTTONS -->
						<div class="profile-userbuttons">
							<button type="button" class="btn btn-success btn-sm">加入好友</button>
						</div>
						<!-- END SIDEBAR BUTTONS -->
						<!-- SIDEBAR MENU -->
						<div class="profile-usermenu">
							<ul class="nav">							
								<li>
								<a href="${pageContext.request.contextPath}/AllMemberServlet.do?account=${kanban.account}">
										<i class="glyphicon glyphicon-user"></i> 個人訊息</a>
								</li>
								<li><a href="#">
										<i class="glyphicon glyphicon-book"></i> 相簿
									</a></li>
								<li><a href="#">
										<i class="glyphicon glyphicon-list-alt"></i> 好友名單
									</a></li>
								<li>
								<a href="<c:url value="/AllMemberServlet.do" >
								<c:param name="account" value="${kanban.account}" />
									</c:url>">
									<i class="glyphicon glyphicon-list-alt"></i> 收藏文章
								</a>
								</li>
								
								<li>
								<a href="<c:url value="/AllMemberServlet.do">
								<c:param name="action" value="insert" />
								</c:url>">
								<i class="glyphicon glyphicon-list-alt"></i> 收藏文章
								</a>
								</li>
								
								
							</ul>
						</div>
						<!-- END MENU -->
					</div>
				</div>
				<div class="col-xs-7">
					<div class="profile-content">
						<c:forEach var="element" items="${ss}">
							<tr>
								<td>${element.username}</td>
								<td>${element.sex}</td>
								<td>${element.phone}</td>
								<td>${element.address}</td>
							</tr>
						</c:forEach>
						
						<div class="col-md-4">
							<p>個人心情留言</p>
					</div>					
					<div class="col-md-4 .col.md.offset-4">
					  <form action="<c:url value='/kanban.do'/>"  method="post">
						<c:if test="${member.account  == isLogin.account}">
						<a href="${pageContext.request.contextPath}/front/article/InsertArticle.jsp?type=1">發表文章</a>
						<input type="button" name="action" value="add" />
						<input  type="button" name="action" value="修改" />
						<input type="button" name="action" value="刪除" />
						</c:if>
						</form>
					</div>
					<br>
					<div class="col-md-10">
						
					 		
										<c:forEach var="xxx" items="${list}">
								
										
										   <div class="slide_toggle">標題:${xxx.title}</div>
										 	
    										<div>
    										<img src="data:image/png;base64,${xxx.mphoto}" class="img-responsive" alt="">
    										${xxx.detail}
    										
    										
    										</div>		
										</c:forEach>
									
								


						
									
						</div>
						
						
						<jsp:include page="insert.jsp" />
						
						
						
					<div class="col-md-2">				
					</div>
					</div>
				</div>
				<div class="col-xs-2">
				這裡是444`````````````````````````
				</div>
			</div>
		</c:if>
		
		
 <script>
    $(document).ready(function(){

    $(".message_list .message_body:gt(0)").hide();

    $(".message_list li:gt(4)").hide();
    $(".message_head").click(function(){
    $(this).next(".message_body").slideToggle(500)
    return false;
    });

    //收起所有消息
    $(".collpase_all_message").click(function(){
    $(".message_body").slideUp(500)
    return false;
    });

    //顯示所有消息
    $(".show_all_message").click(function(){
    $(this).hide()
    $(".show_recent_only").show()
    $(".message_list li:gt(4)").slideDown()
    return false;
    });

    //只顯示最近的消息
    $(".show_recent_only").click(function(){
    $(this).hide()
    $(".show_all_message").show()
    $(".message_list li:gt(4)").slideUp()
    return false;
    });

    });
    </script>
		
		    <script>
$(".slide_toggle").click(function(){
$(this).next().slideToggle();
});
    </script>
		
		
		
		
		
		

		<!-----------------------------------------main----------------------------------------->
		<!--------footer-------->
		<%@ include file="../fragment/footer.jsp"%>
		<!--------footer-------->
	</div>
	<%@ include file="../fragment/js.jsp"%>
<!-- 		 <script src="http://code.jquery.com/jquery-1.11.1.min.js"></script> -->
	<!--     <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script> -->
	

	
	
	
	

	

	
</body>
</html>