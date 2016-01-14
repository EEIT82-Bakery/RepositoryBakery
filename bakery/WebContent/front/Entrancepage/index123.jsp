<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <title>自己的輪播</title>
    <link href="css/style.css" rel="stylesheet" />

</head>
<body>
		 <div id="wrapper">
		  <div id="maincontent">
		    <div id="huadong">
		      <ul class="show_images_list">
		        <li class="show_images_list_li show_images_1" style="z-index: 15; top: 1px; left: 200px;">
			        <a href="../activity/Turntable.jsp" target="_blank">
			        	<img  class="show_images_1_img" src="img/011.png">
			        </a>
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
      <script src="js/jquery-2.1.4.min.js"></script>
	<script type="text/javascript" src="js/gallery.js"></script>
	    <script src="js/show.js"></script>
</body>
</html>