<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../fragment/css.jsp"%>
  <link href="${pageContext.request.contextPath}/front/Entrancepage/css/style2.css" rel="stylesheet" />
	    <script src="js/jquery-2.1.4.min.js"></script>
	    
	    <script src="bootstrap.min.js"></script>
<script src="js/jquery-2.1.4.min.js"></script>
<link href="css/bootstrap.min.css" rel="stylesheet" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front/activity/js/buttons.js" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front/activity/css/buttons.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front/activity/css/Jiugongge_OK..css" />
	
	<script>
	function roll() {
		lottery.times += 1;
		lottery.roll();
		if (lottery.times > lottery.cycle + 10
				&& lottery.prize == lottery.index) {
			clearTimeout(lottery.timer);
			lottery.prize = -1;
			lottery.times = 0;
			click = false;
		} else {
			if (lottery.times < lottery.cycle) {
				lottery.speed -= 10;
			} else if (lottery.times == lottery.cycle) {
				var index = Math.random() * (lottery.count) | 0;
				lottery.prize = index;
			} else {
				if (lottery.times > lottery.cycle + 10
						&& ((lottery.prize == 0 && lottery.index == 7) || lottery.prize == lottery.index + 1)) {
					lottery.speed += 110;
					lottery.stop(lottery.prize);
				} else {
					lottery.speed += 20;
				}
			}
			if (lottery.speed < 40) {
				lottery.speed = 40;
			}
			;
			lottery.timer = setTimeout(roll, lottery.speed);
		}
		return false;
	}

	var click = false;

	var prizes = [ "恭喜您獲得了 <em>IPhone6(16GB)</em>", "恭喜您獲得了 <em>100元折價卷</em>",
			"恭喜您獲得了 <em>侏羅紀 T-SHIRT</em>", "恭喜您獲得了 <em>200元折價卷</em>",
			"恭喜您獲得了 <em>300元折價卷</em>", "恭喜您獲得了 <em>樂高模型</em>",
			"恭喜您獲得了 <em>玩命關頭7 T-SHIRT</em>", "感謝參與 <em>銘謝惠顧</em>" ];

	$(function() {
		var $startok = $('#startok');

	});
	var lottery = {
		index : -1,
		count : 0,
		timer : 0,
		speed : 20,
		times : 0,
		cycle : 50,
		prize : -1,
		init : function(id) {
			if ($("#" + id).find(".lottery-unit").length > 0) {
				$lottery = $("#" + id);
				$units = $lottery.find(".lottery-unit");
				this.obj = $lottery;
				this.count = $units.length;
				$lottery.find(".lottery-unit-" + this.index).addClass("active");
			}
			;
		},
		roll : function() {
			var index = this.index;
			var count = this.count;
			var lottery = this.obj;
			$(lottery).find(".lottery-unit-" + index).removeClass("active");
			index += 1;
			if (index > count - 1) {
				index = 0;
			}
			;

			$(lottery).find(".lottery-unit-" + index).addClass("active");
			this.index = index;
			return false;
		},
		stop : function(index) {
			this.prize = index;
			$('#result').show();
			$('#Txt').html(prizes[this.prize]);
			return false;
		}
	};

	window.onload = function() {
		lottery.init('lottery');
		$('#Btn').click(function() {
			$('#result').hide();

		})
	}
	function dsd() {
		$('#result').hide();
		if (click) {
			return false;
		} else {
			lottery.speed = 100;
			roll();
			click = true;
			return false;
		}
		$('#Btn').click(function() {
			$('#result').hide();

		})
	}
</script>
</head>
<body>
	<!-----------------------------------------nav------------------------------------------>
	<%@ include file="../fragment/nav.jsp"%>
	<!-----------------------------------------nav------------------------------------------>
	<div class="container-fluid">
		<!-----------------------------------------main----------------------------------------->
		<marquee behavior="alternate" bgcolor="#33CCFF	">
		<img src="img/duck.jpg" width="50" height="50">按下立即抽獎 ,遊戲每次扣100點
		盡情遊玩吧
	</marquee>

	<div id="lottery">
		<table>
			<tr>
				<td class="lottery-unit lottery-unit-0"><img src="img/0.jpg">
					<div class="mask"></div></td>
				<td class="lottery-unit lottery-unit-1"><img src="img/1.jpg">
					<div class="mask"></div></td>
				<td class="lottery-unit lottery-unit-2"><img src="img/2.jpg">
					<div class="mask"></div></td>
			</tr>
			<tr>
				<td class="lottery-unit lottery-unit-3"><img src="img/3.jpg">
					<div class="mask"></div></td>
				<td class="king"><img src="img/lottery1.jpg" id="startok">
					<div class="startok" /></div></td>
				<td class="lottery-unit lottery-unit-4"><img src="img/4.jpg">
					<div class="mask"></div></td>
			</tr>
			<tr>
				<td class="lottery-unit lottery-unit-5"><img src="img/5.jpg">
					<div class="mask"></div></td>
				<td class="lottery-unit lottery-unit-6"><img src="img/6.jpg">
					<div class="mask"></div></td>
				<td class="lottery-unit lottery-unit-7"><img src="img/7.jpg">
					<div class="mask"></div></td>
			</tr>
		</table>
	</div>
	<div class="lottery">
		<div class="result" id="result">
			<p id="Txt"></p>
			<a href="javascript:" id="Btn" title="關閉">關閉</a>
		</div>
	</div>


		<div class="absolute">
			<form action="<c:url value='/PointServlet.do '/>" method="get">
				<!-- 			<input type="button" value="遊戲開始" name="upd" onclick="dsd()" -->
				<!-- 				class="button button-3d button-action button-pil"> -->
				<input type="submit" value="遊戲開始"  onclick="dsd()"
					class="button button-3d button-royal">
					
			</form>
			<table border='1' width='200' top='50'
				style="background: #F5EBFF; border-color: rgb(100, 100, 255); margin-top: 8px;">
				<tr>
					<TD>客戶姓名:${isLogin.username}</TD>
					<TD>客戶點數:${isLogin.point}</TD>
					<TD>客戶點數:${param.upd}</TD>
				<tr>
			</table>

		</div>

		<!-----------------------------------------main----------------------------------------->
		<!--------footer-------->
		<%@ include file="../fragment/footer.jsp"%>
		<!--------footer-------->
	</div>
	
<%-- 	<%@ include file="../fragment/js.jsp"%> --%>
</body>
</html>
