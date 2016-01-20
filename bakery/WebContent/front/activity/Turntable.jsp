<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../fragment/css.jsp"%>
  <link href="${pageContext.request.contextPath}/front/Entrancepage/css/style2.css" rel="stylesheet" />
	   <link href="css/start.css" rel="stylesheet" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front/activity/js/buttons.js" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front/activity/css/buttons.css" />

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front/activity/css/Turnt.css" />

<script src="js/jquery-2.1.4.min.js"></script>

<script src="js/jQueryRotate.2.2.js"></script>
	    
	    <script>
	function rotateFunc(awards, angle, text) { //awards:奖项，angle:奖项对应的角度
		var $PlayFancy = $('#PlayFancy');
		var $startok = $('#startok');
		var $result = $('#result');
		var $Txt = $('#Txt');
		var $Btn = $('#result');
		$startok.stopRotate();
		$startok.rotate({ //循環
			angle : 0,
			duration : 5000,
			animateTo : angle + 1440, //angle是图片上各奖项对应的角度，1440是让指针固定旋转4圈
			callback : function() {
				$Txt.html(text);
				$result.show();
			}
		});
		$('#Btn').click(function() { //当点击按钮时，隐藏或显示元素：
			$('#result').hide(); //隐藏可见的result元素
		})
	}
	function dsd() {
		var data = [ 0, 1, 2, 3, 4, 5, 6, 7 ];
		data = data[Math.floor(Math.random() * data.length)]; //傳回小於或等於指定十進位數字的最大整數
		switch (data) {
		case 1:
			rotateFunc(1, 87, '恭喜您獲得了 <em>100</em> 元點數折價卷');
			break;
		case 2:
			rotateFunc(2, 43, '恭喜您獲得了 <em>300</em> 元點數折價卷');
			break;
		case 3:
			rotateFunc(3, 134, '恭喜您獲得了 <em>200</em> 元點數折價卷');
			break;
		case 4:
			rotateFunc(4, 177, '很遗憾，這次您沒中獎，繼續加油吧');
			break;
		case 5:
			rotateFunc(5, 223, '恭喜您獲得了 <em>300</em> 元點數折價卷');
			break;
		case 6:
			rotateFunc(6, 268, '恭喜您獲得了 <em>100</em> 元點數折價卷');
			break;
		case 7:
			rotateFunc(7, 316, '恭喜您獲得了 <em>200</em> 元點數折價卷');
			break;
		default:
			rotateFunc(0, 0, '很遺憾，這次您沒中獎，繼續加油吧');
		}
	}
</script>

<script>
	function Jiugongge_OK(){
		xmlHttp = new XMLHttpRequest();
		if (xmlHttp != null) {
			xmlHttp.open("GET","${pageContext.request.contextPath}/PointServlet50.do",
					true);
			xmlHttp.addEventListener("readystatechange", callback, false);
			xmlHttp.send(null);
		} else {
			alert("您的瀏覽器不支援Ajax的功能!!");
		}
		function callback() {
			if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
				var data = JSON.parse(xmlHttp.responseText);
				var myPoint = document.getElementById("tdPoint");
				myPoint.innerHTML = "客戶點數:" + data.point; //蓋掉點數
			}
		}
		dsd();
	}
</script>

</head>
<body>
	<!-----------------------------------------nav------------------------------------------>
	<%@ include file="../fragment/nav.jsp"%>
	<!-----------------------------------------nav------------------------------------------>
	<div class="container-fluid">
		<!-----------------------------------------main----------------------------------------->
		 <marquee behavior="alternate" bgcolor="#FF44AA">
		<img src="img/duck2.jpg" width="50" height="50">按下按鈕立即遊玩
		,遊戲每次扣50點 盡情遊玩吧
	</marquee>

	<div class="root">
		<div id="turn">
			<img src="img/turn.png">
		</div>
		<div id="start">
			<img src="img/start.png" id="startok">
		</div>
		<div class="result" id="result">
			<p id="Txt"></p>
			<a href="javascript:" id="Btn" title="關閉">關閉</a>
		</div>
		<div class="absolute">
			<table border='0' width='300' top='50'
				style="background: #F5EBFF; border-color: rgb(100, 100, 255)">
				<tr>
					<TD>客戶姓名:${isLogin.username}</TD>
					<TD id="tdPoint">客戶點數:${isLogin.point}</TD>
				<tr>
					<div class="ros">
						<form action="<c:url value='/PointServlet50.do'/>" method="get">
<!-- 														<input type="button" value="遊戲開始" name="ups" onclick="dsd()" -->
<!-- 															class="button button-3d button-royal"> -->
							<input type="button" value="遊戲開始" name="upd" onclick="Jiugongge_OK()"
								class="button button-3d button-royal">
						</form>
					</div>	
			</table>
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
