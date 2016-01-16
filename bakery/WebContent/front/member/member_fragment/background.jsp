<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>背景</title>
	<link rel="stylesheet" type="text/css" href="css/normalize.css" />
	<link rel="stylesheet" type="text/css" href="css/default.css">
	<link href="${pageContext.request.contextPath}/front/member/css/bootstrap.min.css" rel="stylesheet" />
	<style type="text/css">
		/*Lets start with the cloud formation rather*/

		/*The container will also serve as the SKY*/

		*{ margin: 0; padding: 0;}

		body {
			/*To hide the horizontal scroller appearing during the animation*/
			/*overflow: hidden;*/
		}

		#clouds{
			padding: 100px 0;
			background: #bdd7f5;
			background-image: -webkit-gradient(linear, left top, left bottom, from(#cdeeff), to(#66ffff));
			background: -linear-gradient(top, #c9dbe9 0%, #fff 100%);
			background: -moz-linear-gradient(top, #c9dbe9 0%, #fff 100%);
		}

		/*Time to finalise the cloud shape*/
		.cloud {
			width: 200px; height: 60px;
			background: #fff;
			
			border-radius: 200px;
			-moz-border-radius: 200px;
			-webkit-border-radius: 200px;
			
			position: relative; 
		}

		.cloud:before, .cloud:after {
			content: '';
			position: absolute; 
			background: #fff;
			width: 100px; height: 80px;
			position: absolute; top: -15px; left: 10px;
			
			border-radius: 100px;
			-moz-border-radius: 100px;
			-webkit-border-radius: 100px;
			
			-webkit-transform: rotate(30deg);
			transform: rotate(30deg);
			-moz-transform: rotate(30deg);
		}

		.cloud:after {
			width: 120px; height: 120px;
			top: -55px; left: auto; right: 15px;
		}

		/*Time to animate*/
		.x1 {
			-webkit-animation: moveclouds 15s linear infinite;
			-moz-animation: moveclouds 15s linear infinite;
			-o-animation: moveclouds 15s linear infinite;
		}

		/*variable speed, opacity, and position of clouds for realistic effect*/
		.x2 {
			left: 200px;
			
			-webkit-transform: scale(0.6);
			-moz-transform: scale(0.6);
			transform: scale(0.6);
			opacity: 0.6; /*opacity proportional to the size*/
			
			/*Speed will also be proportional to the size and opacity*/
			/*More the speed. Less the time in 's' = seconds*/
			-webkit-animation: moveclouds 25s linear infinite;
			-moz-animation: moveclouds 25s linear infinite;
			-o-animation: moveclouds 25s linear infinite;
		}

		.x3 {
			left: -250px; top: -200px;
			
			-webkit-transform: scale(0.8);
			-moz-transform: scale(0.8);
			transform: scale(0.8);
			opacity: 0.8; /*opacity proportional to the size*/
			
			-webkit-animation: moveclouds 20s linear infinite;
			-moz-animation: moveclouds 20s linear infinite;
			-o-animation: moveclouds 20s linear infinite;
		}

		.x4 {
			left: 470px; top: -250px;
			
			-webkit-transform: scale(0.75);
			-moz-transform: scale(0.75);
			transform: scale(0.75);
			opacity: 0.75; /*opacity proportional to the size*/
			
			-webkit-animation: moveclouds 18s linear infinite;
			-moz-animation: moveclouds 18s linear infinite;
			-o-animation: moveclouds 18s linear infinite;
		}

		.x5 {
			left: -150px; top: -150px;
			
			-webkit-transform: scale(0.8);
			-moz-transform: scale(0.8);
			transform: scale(0.8);
			opacity: 0.8; /*opacity proportional to the size*/
			
			-webkit-animation: moveclouds 20s linear infinite;
			-moz-animation: moveclouds 20s linear infinite;
			-o-animation: moveclouds 20s linear infinite;
		}

		@-webkit-keyframes moveclouds {
			0% {margin-left: 1000px;}
			100% {margin-left: -1000px;}
		}
		@-moz-keyframes moveclouds {
			0% {margin-left: 1000px;}
			100% {margin-left: -1000px;}
		}
		@-o-keyframes moveclouds {
			0% {margin-left: 1000px;}
			100% {margin-left: -1000px;}
		}
	</style>
</head>
<body>
	<%@include file="/front/member/member_fragment/topline.jsp"%>


	<article class="htmleaf-container">
		<header class="htmleaf-header">
			<h1>zzzzz</span></h1>
			<div class="htmleaf-links">
				<a class="htmleaf-icon icon-htmleaf-home-outline" href="http://www.htmleaf.com/" title="jQuery之家" target="_blank"><span> jQuery之家</span></a>
				<a class="htmleaf-icon icon-htmleaf-arrow-forward-outline" href="http://www.htmleaf.com/css3/css3donghua/201510102655.html" title="返回下载页" target="_blank"><span> 返回下载页</span></a>
			</div>
		</header>
		<div id="clouds">
			<div class="cloud x1"></div>
			<div class="cloud x2"></div>
			<div class="cloud x3"></div>
			<div class="cloud x4"></div>
			<div class="cloud x5"></div>
		</div>

	</article>
	
</body>
</html>