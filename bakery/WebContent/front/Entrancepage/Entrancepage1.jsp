<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../fragment/css.jsp"%>
  <link href="${pageContext.request.contextPath}/front/Entrancepage/css/style2.css" rel="stylesheet" />
	    <script src="js/jquery-2.1.4.min.js"></script>
</head>
<body>
	<!-----------------------------------------nav------------------------------------------>
	<%@ include file="../fragment/nav.jsp"%>
	<!-----------------------------------------nav------------------------------------------>
	<div class="container-fluid">
		<!-----------------------------------------main----------------------------------------->
		  <div id="wrapper">
		  <div id="maincontent">
		    <div id="huadong">
		      <ul class="show_images_list">
		        <li class="show_images_list_li show_images_1" style="z-index: 15; top: 1px; left: 200px;">
			        <a href="/bakery/PointServlet50.do" target="_blank">
			        	<img  class="show_images_1_img" src="img/011.png">
			        </a>
			        <input type="hidden" name="s1" value="${isLogin.username}"><!-- e1的呼叫 -->
		        </li>
		        <li class="show_images_list_li show_images_2" style="z-index: 10; top: 20px; left: 0px;">
			        <a href="#" target="_blank">
			        	<img class="show_images_2_img" src="img/014.png">
			        </a>
		        </li>
		        <li class="show_images_list_li show_images_3" style="z-index: 5; top: 20px; left: 459px;">
			        <a href="../activity/Jiugongge_OK.jsp" target="_blank">
			        	<img class="show_images_3_img" src="img/012.png">
			        </a>
			        <input type="hidden" name="s1" value="${isLogin.username}"> <!-- e1的呼叫 -->
		        </li>
		        <li class="show_images_list_li show_images_4" style="z-index: 0; top: 20px; left: 200px;">
			        <a href="#" target="_blank">
			        	<img class="show_images_4_img" src="img/013.png">
			        </a>
		        </li>
		      </ul>
		      
		    </div>
		    <div class="btn"> 
			    <a class="btn1" rel="1" ></a> 
			    <a class="btn2" rel="2" ></a> 
			    <a class="btn3" rel="3" ></a> 
			    <a class="btn4" rel="4" ></a> 
		    </div>
		  </div>
		</div>
		<script type="text/javascript" src="js/gallery.js"></script>
	    <script src="js/show.js"></script>
		<!-----------------------------------------main----------------------------------------->
		<!--------footer-------->
		<%@ include file="../fragment/footer.jsp"%>
		<!--------footer-------->
	</div>
	
<%-- 	<%@ include file="../fragment/js.jsp"%> --%>
</body>
</html>
