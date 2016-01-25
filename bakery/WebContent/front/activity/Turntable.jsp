<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../fragment/css.jsp"%>
<style>
.turntable {
	width: 500px;
	height: 400px;
	position: relative;
	top: -40px;
	margin: 50px auto
}

.pointer {
	width: 16px;
	height: 320px;
	position: absolute;
	top: 104px;
	left: 101px;
}

.result {
	display: none;
	position: absolute;
	left: 55px;
	top: 190px;
	width: 395px;
	height: 118px;
	background-color: rgba(0, 0, 0, 0.75);
	filter: alpha(opacity = 90)
}

.result a {
	position: absolute;
	right: 5px;
	top: 5px;
	width: 25px;
	height: 25px;
	text-indent: -100px;
	/* 	background-image: url(img/close.png); */
	overflow: hidden;
}

.result p {
	padding: 45px 15px 0;
	font: 16px "Microsoft Yahei";
	color: #fff;
	text-align: center;
}

.result em {
	color: #ffea76;
	font-style: normal;
}
</style>
</head>
<body>
	<!-----------------------------------------nav------------------------------------------>
	<%@ include file="../fragment/nav.jsp"%>
	<!-----------------------------------------nav------------------------------------------>
	<div class="container-fluid">
		<!-----------------------------------------main----------------------------------------->

		<div class="row">
			<div class="col-xs-1"></div>
			<div class="col-xs-6">
				<div class="turntable">
					<div class="turn">
						<img src="img/turn.png">
					</div>
					<div class="pointer">
						<img src="img/start.png" id="startok">
					</div>
					<div class="result" id="result">
						<img src="img/close.png" style="float: right" />
						<p id="Txt"></p>
						<a href="javascript:" id="Btn" title="關閉">關閉</a>
					</div>
				</div>
			</div>
			<div class="col-xs-5">
				<marquee behavior="alternate" bgcolor="#FF44AA">
					<img src="img/duck2.jpg" width="50" height="50">按下按鈕立即遊玩,遊戲每次扣50點
					盡情遊玩吧
				</marquee>
				<table
					style="margin-top: 50px; margin-left:100px; width: 300px; height: 200px; text-align: center">
					<tr>
						<td width="50%">會員姓名 : ${isLogin.username}</td>
						<td width="50%">會員點數 : <span id="myPoint">${isLogin.point}</span></td>
					<tr>
					<tr>
						<td colspan="2"><input type="button" value="遊戲開始" id="start"
							onclick="turntableStart()"
							class="btn btn-danger btn-lg btn-block" /></td>
					</tr>
				</table>
			</div>
		</div>
		<!-----------------------------------------main----------------------------------------->
		<!--------footer-------->
		<%@ include file="../fragment/footer.jsp"%>
		<!--------footer-------->
	</div>
	<%@ include file="../fragment/js.jsp"%>
	<script src="js/jQueryRotate.2.2.js"></script>

	<script>
		var myPoint = document.getElementById("myPoint");
		var data = null;
		function turntableStart() {
			document.getElementById('start').disabled = true;
			var point = myPoint.innerHTML;
			point = point - 50;
			if (point > 0) {
				myPoint.innerHTML = point; //畫面扣除點數
				startTurn();
			} else {
				document.getElementById('start').disabled = false;
				alert("點數不夠喔");
			}
		}
		function startTurn() {
			var data = [ 0, 1, 2, 3, 4, 5, 6, 7 ];
			data = data[Math.floor(Math.random() * data.length)]; //傳回小於或等於指定十進位數字的最大整數
			switch (data) {
			case 1:
				rotateFunc(50, 43, '恭喜您獲得了 <em>50</em> 點');
				break;
			case 2:
				rotateFunc(0, 87, '很遗憾，這次您沒中獎，繼續加油吧');
				break;
			case 3:
				rotateFunc(75, 134, '恭喜您獲得了 <em>75</em> 點');
				break;
			case 4:
				rotateFunc(0, 177, '很遗憾，這次您沒中獎，繼續加油吧');
				break;
			case 5:
				rotateFunc(25, 223, '恭喜您獲得了 <em>25</em> 點');
				break;
			case 6:
				rotateFunc(0, 268, '很遗憾，這次您沒中獎，繼續加油吧');
				break;
			case 7:
				rotateFunc(75, 316, '恭喜您獲得了 <em>75</em> 點');
				break;
			default:
				rotateFunc(0, 0, '很遺憾，這次您沒中獎，繼續加油吧');
			}
		}
		function rotateFunc(point, angle, text) { //awards:獎項，angle:獎項對應的角度
			point = point - 50;
			callServlet(point);
			var $PlayFancy = $('#PlayFancy');
			var $startok = $('#startok');
			var $result = $('#result');
			var $Txt = $('#Txt');
			var $Btn = $('#result');
			$startok.stopRotate();
			$startok.rotate({ //循環
				angle : 0,
				duration : 5000,
				animateTo : angle + 1440, //angle是圖片上各獎項對應的角度，1440是指針固定旋轉4圈
				callback : function() {
					$(document).click(function() {
						$('#result').hide();
					});
					$Txt.html(text);
					$result.show();
					document.getElementById('start').disabled = false;
					myPoint.innerHTML = data.point; //蓋掉點數
				}
			});

		}

		function callServlet(point) {
			xmlHttp = new XMLHttpRequest();
			if (xmlHttp != null) {
				xmlHttp.open("POST",
						"${pageContext.request.contextPath}/PointServlet.do",
						true);
				xmlHttp.addEventListener("readystatechange", callback, false);
				xmlHttp.setRequestHeader("Content-Type",
						"application/x-www-form-urlencoded")
				xmlHttp.send("point=" + point);
			} else {
				alert("您的瀏覽器不支援Ajax的功能!!");
			}
			function callback() {
				if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
					data = JSON.parse(xmlHttp.responseText);
				}
			}
		}
	</script>
</body>
</html>
