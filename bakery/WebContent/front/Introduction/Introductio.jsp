<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<link rel="stylesheet" href="../Introduction/css/figurecaptions.css" />
<title>關於焙客栗簡介</title>
<style>
.testCenter{
	height: 461px;
	width: 615px;
	margin: 0 auto;
}
</style>
<%@ include file="../fragment/css.jsp"%>

<script src="http://libs.useso.com/js/jquery/1.11.0/jquery.min.js"></script>
<script src="../Introduction/js/TweenMax.min.js"></script>
<script src="../Introduction/js/figurecaptions.js"></script>
<script type="text/javascript">
		$(function(){
			$('#mushroom').addCaption({
			  fx: 'dualpanels',
			  caption: ' <h4>隨著時代的改變，消費者消費都漸漸朝向線上購買的方向，許多小麵包店、糕點店不像連鎖企業擁有足夠的資金外包製作網站，所以藉由這個專題，<br>我們想製作一個網站可以適用於各個店家，擁有很多功能且方便管理</a>'
			 });
		});
	</script>

</head>
<body>
	<!-----------------------------------------nav------------------------------------------>
	<%@ include file="../fragment/nav.jsp"%>
	<!-----------------------------------------nav------------------------------------------>
	<div class="container-fluid">
		<!-----------------------------------------main----------------------------------------->
		<div class="testCenter">
		<MARQUEE direction=up behavior=alternate width=500 height=40 >
		<MARQUEE style="WIDTH: 499px; HEIGHT: 25px;" direction=left behavior=alternate>
		<font color="#0066CC" size="4"><strong>焙客栗簡介</strong></font>
		</marquee>
		</marquee>
		<div class="htmleaf">
			<img id="mushroom" src="img/cooke.png" />
		</div>
		</div>
		<!-----------------------------------------main----------------------------------------->
		
		<!--------footer-------->
		<%@ include file="../fragment/footer.jsp"%>
		<!--------footer-------->
	</div>
	<%-- 	<%@ include file="../fragment/js.jsp"%> --%>
</body>
</html>
