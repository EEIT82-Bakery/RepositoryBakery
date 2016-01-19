<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@page import="com.membergrade.model.MemberGradeService"%>   
<%@page import="com.membergrade.model.MemberGradeBean"%>  
<%@page import="com.member.model.*"%>   

   
 <%
 	HttpSession httpsession = request.getSession();
 	MemberBean memberbean = (MemberBean)httpsession.getAttribute("isLogin");
 	if(memberbean!=null){
 		int  status = memberbean.getStatus();
 		MemberGradeService mgservice = new MemberGradeService();
 		MemberGradeBean membergradebeab = mgservice.getStatus(status);
 		pageContext.setAttribute("membergradebeab", membergradebeab);
 	}
 
 %>
 
   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
<title>Main</title>
    <link href="${pageContext.request.contextPath}/front/member/css/bootstrap.min.css" rel="stylesheet" />

<style>
#ac1 {
	height: 300px;
	width: 800px;
}

#ac2 {
	height: 300px;
	width: 800px;
}

#ac3 {
	height: 300px;
	width: 800px;
}
#m1 {
	width: 320px;
	height: 150px;
}

#m2 {
	width: 320px;
	height: 150px;
}

#m3 {
	width: 320px;
 	height: 150px;
}

#m4 {
	width: 320px;
 	height: 150px;
}
#m5 {
	width: 320px;
 	height: 150px;
}

#memberstatu{
	height:69px;
 	width: 212.5px;

}
#mypicture{
	height: 100px;
	width: 100px;

}


</style>

</head>
<body>
 <%@ include file="../member_fragment/topline.jsp"%>
 
 
 <div class="container">
        <div class="row">
            <div class="col-md-3">         
                <div class="list-group">              
<%--                     <a href="#" class="list-group-item"><h3>${membergradebeab.permname}</h3> --%>
<%--                     <c:if test="${membergradebeab.status ==1}"> --%>
<!--                     	<img src="./images/h1.jpg" style="height: 50px;width: 50px;" /> -->
<%--                     </c:if> --%>
<%--                     <c:if test="${membergradebeab.status ==2}"> --%>
<!--                     	<img src="./images/h2.jpg" style="height: 50px;width: 50px;" /> -->
<%--                     </c:if> --%>
<%--                     <c:if test="${membergradebeab.status ==3}"> --%>
<!--                     	<img src="./images/h1.jpg" style="height: 50px;width: 50px;" /> -->
<%--                     </c:if></a> --%>
					<center>
					<a href="#" class="list-group-item">
					<h5>會員:${isLogin.username}，您好</h5>
					<img src="data:image/png;base64,${isLogin.mpicture}" id="mypicture" class="img-circle" alt="個人照片" />
					</a>
					</center> 
                </div>      
                  <div class="list-group">
<!--                   <p class="lead">本月會員等級:</p> -->
              		<img src="${pageContext.request.contextPath}/front/member/main/images/會員分級.png" id="memberstatu"/>
                    <a href="#" class="list-group-item"><h3>${membergradebeab.permname}
                    <c:if test="${membergradebeab.status ==1}">
                    	<img src="./images/h1.jpg" style="height: 50px;width: 50px;" />
                    </c:if>
                    <c:if test="${membergradebeab.status ==2}">
                    	<img src="./images/h2.jpg" style="height: 50px;width: 50px;" />
                    </c:if>
                    <c:if test="${membergradebeab.status ==3}">
                    	<img src="./images/h1.jpg" style="height: 50px;width: 50px;" />
                    </c:if></h3></a>    
                </div>
            </div>   
            <div class="col-md-9">
                <div class="row carousel-holder">
                    <div class="col-md-12">
                        <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
                            <ol class="carousel-indicators">
                                <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
                                <li data-target="#carousel-example-generic" data-slide-to="1"></li>
                                <li data-target="#carousel-example-generic" data-slide-to="2"></li>
                            </ol>
                            <div class="carousel-inner">
                                <div class="item active">
                                    <img class="slide-image" src="${pageContext.request.contextPath}/front/member/main/images/ac3.jpg" id="ac1" alt="">
                                </div>
                                <div class="item">
                                    <img class="slide-image" src="${pageContext.request.contextPath}/front/member/main/images/ac2.jpg" id="ac2" alt="">
                                </div>
                             
                                <div class="item">
                                    <img class="slide-image" src="${pageContext.request.contextPath}/front/member/main/images/ac4.jpg" id="ac1" alt="">
                                </div>
                            </div>
                            <a class="left carousel-control" href="#carousel-example-generic" data-slide="prev">
                                <span class="glyphicon glyphicon-chevron-left"></span>
                            </a>
                            <a class="right carousel-control" href="#carousel-example-generic" data-slide="next">
                                <span class="glyphicon glyphicon-chevron-right"></span>
                            </a>
                        </div>
                    </div>
                </div>            
                <div class="row">            
                    <div class="col-sm-4 col-lg-4 col-md-4">
                        <div class="thumbnail">
                            <img src="${pageContext.request.contextPath}/front/member/main/images/m2.jpg" id="m2" alt="">
                            <div class="caption">
                                <h4>紅利／優惠
                                </h4>
                                <p><a href="#">折價卷數量</a></p>
                                <p><a href="#">折價卷使用紀錄</a></p>
                            </div>
                        </div>
                    </div>
                     <div class="col-sm-4 col-lg-4 col-md-4">
                        <div class="thumbnail">
                            <img src="${pageContext.request.contextPath}/front/member/main/images/m6.jpg" id="m1" alt="">
                            <div class="caption">
                                <h4>交易紀錄</h4>
                                <p><a href="">交易明細查詢</a></p>
                                <br>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-4 col-lg-4 col-md-4">
                        <div class="thumbnail">
                            <img src="${pageContext.request.contextPath}/front/member/main/images/m3.jpg" id="m3" alt="">
                            <div class="caption">
                                <h4>購物車/訂單 </h4>
                                <p><a href="#">購物車</a></p>
                                <p><a href="${pageContext.request.contextPath}/OrderMemberServlet.do">訂單查詢</a></p>
                                <p><a href="#">修改訂單</a></p>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-4 col-lg-4 col-md-4">
                        <div class="thumbnail">
                            <img src="${pageContext.request.contextPath}/front/member/main/images/m4.png" id="m4" alt="">
                            <div class="caption">
                                <h4>我的帳戶</h4>
<%--                                 <p>	<a href="<c:url value="/front/member/main/member.do" > --%>
<%-- 								<c:param name="id" value="${isLogin.member_id}" /> --%>
<%-- 									</c:url>">會員資料查詢/修改</a>	</p> --%>
									
								<p><a href="<c:url value='/front/member/main/CHGpassword.jsp' />">修改密碼</a></p>
								<p>
								 <a href="${pageContext.request.contextPath}/front/member/main/member.do?action=select&id=${isLogin.member_id}">修改會員資訊</a>
								</p>
								
								<p> <a href="<c:url value="/homeindex.do">
								<c:param name="account" value="${isLogin.account}" />
								</c:url>">我的討論區頁面</a></p>								
                            </div>
                        </div>
                    </div>
                    
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="container">
        <hr>
        <footer>
            <div class="row">
                <div class="col-lg-12">
                    <p>Copyright &copy; Your Website 2016</p>
                </div>
            </div>
        </footer>
    </div>
    	${ok.InsertOK}<BR>
 <BR>  
 <% // 顯示MsgOK.InsertOK後，就要立刻移除，以免每次回到首 頁都會顯示新增成功的訊息
    session.removeAttribute("ok");  
 %>
    
    <script src="js/jquery.js"></script>
    <script src="js/bootstrap.min.js"></script>

</body>

</html>