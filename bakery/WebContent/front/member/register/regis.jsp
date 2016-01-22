<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>註冊</title>
<script src="${pageContext.request.contextPath}/front/member/js/ie-emulation-modes-warning.js"></script>
<link href="${pageContext.request.contextPath}/front/member/css/main.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/front/member/css/bootstrap.min.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/front/member/css/non-responsive.css" rel="stylesheet" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/front/member/css/wizard.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/front/member/css/semantic.min.css">

<script type="text/javascript" src="${pageContext.request.contextPath}/front/member/js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/front/member/js/index.js"></script>


<style>
#tableregis {
	width: 100px;
	height: 700px;
	margin: 0 auto;
	background-image: url();
}



</style>
</head>
<body>
	<%@include file="/front/member/member_fragment/nav.jsp"%>
<div class="container-fluid">
	<div class="row" style="padding-top:50px;">
		<div class="col-xs-12 ">
			<ol class="breadcrumb">
				<li><a href="../index.jsp">首頁</a></li>
				<li><a href="Member.jsp">會員專區</a></li>
				<li class="active">會員註冊</li>
			</ol>
		</div>
	</div>
	<div class="row">
		<div class="col-xs-offset-4 col-xs-4">
			<form id="fm" action="<c:url value='/front/member/regitser/regis.do' />" method="POST" enctype="multipart/form-data">
				<fieldset>
					<legend>會員註冊 </legend>
					<label for="account">帳號：</label>
					<input id="name" name="m_account" type="text" size="30"  id="myID" />
					<font size="-1" color="#FF0000">${ErrorMsg.x_account}</font>
					 <img src="../images/ajax-loader.gif" id="img5" width="18" height="18">
					<div id="div1"></div>
					<span id="Span5" class="errorMsg"></span>
					<label for="password">密碼：</label>
					<input id="password" type="password" name="m_password" />
					<label for="re_password">確認密碼：</label>
					<input id="re_password" type="password" name="m_pwd" />
					<font size="-1" color="#FF0000">${ErrorMsg.x_password}</font>
				</fieldset>
				<fieldset>
					<legend>個人資料</legend>
					<label for="name">姓名：</label>
					<input type="text" name="m_name" id="name" size="10" value="${param.m_name}" />
					<font size="-1" color="#FF0000">${ErrorMsg.x_m_name}</font>
					<label for="sex"><span class="red">*</span>性別：</label>
					<input type="radio" name="m_gender" id="male" value="M" />男
					<input type="radio" name="m_gender" id="female" class="ml20" value="F" />女
					<font size="-1" color="#FF0000">${ErrorMsg.x_m_gender}</font>
					<label for="birth">*出生日期：</label>


						<select id="year" name="year"></select>年
						<select id="month"name="month"></select>月
                		<select id="date"name="day"></select>日
		

					<font size="-1" color="#FF0000">${ErrorMsg.x_bday}</font>
					<label><span>*</span>電子郵件：</label>
					<input type="text" name="m_email" id="m_email" class="mr6" size="30" value="" />
					<input type="button" name="chechEmail" id="chechEmail" class="btn" value="確認是否重覆" />
					<font size="-1" color="#FF0000">${ErrorMsg.x_m_email}</font>
					<label><span class="red">*</span>連絡電話：</label>
					<!--         <input type="radio" name="m_tel_day_type" id="home" checked="checked" value="2" />住家 -->
					<!-- 				                     <input type="radio" name="m_tel_day_type" id="company" value="1" />手機 -->
					<input type="text" name="phone" id="phone" size="16" value="${param.phone}" />
					<div style="color: #FF0000; font-size: 60%; display: inline">${ErrorMsg.x_e_phone}</div>
					<label><span class="red">*</span>連絡地址：</label>
					<input name="m_address" value="${param.mAddress}" type="text" size="54">
					<%--          <div style="color:#FF0000; font-size:x-small; display: inline">${ErrorMsg.m_m}</div> --%>
					<span id="m_city_message" class="errorMsg">${ErrorMsg.m_m}</span>
				</fieldset>
				<fieldset>
					<legend>大頭貼</legend>
					<label class="fontSize">上傳照片：</label>
					<div class="ui two column grid">
						<div class="four wide column">
							<img class="img-preview ui small rounded image" src="${pageContext.request.contextPath}/front/member/images/picture.jpg" />
						</div>
						<div class="eight wide column" style="vertical-align: bottom;">
							<div>
								<input type="file" name="m_pic" id="img-upload">
							</div>
						</div>
					</div>
					<label for="nickname">暱稱：</label>
					<input type="text" name="nickname" id="nickname" size="10" value="${param.m_nickname}" />
					<span id="m_nickname" class="errorMsg">${ErrorMsg.m_nick}</span>
					
				
					<hr>
					<div id="btnArea">
						<input type="submit" name="submit" id="submit" value="儲存" />
						<input type="reset" name="cancel" id="cancel" value="重填" />
					</div>
				</fieldset>
			</form>
		</div>
	</div>
</div>



	<script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
	<script src="${pageContext.request.contextPath}/front/member/js/ie10-viewport-bug-workaround.js"></script>
	<script src="${pageContext.request.contextPath}/front/member/js/formToWizard.js"></script>

	<script type="text/javascript">
$("#myID").attr('placeholder', '<spring:message code="myCode"/>');

</script>

        <script type="text/javascript">
        window.onload = function () {
            document.getElementById("month").onchange = chDay;
            document.getElementById("year").onchange = chDay;
            document.getElementById("date").onchange = chWord;

            createOption(1950, 2020, 'year');
            createOption(1, 12, 'month');
            createOption(1, 31, 'date');
        }
        function createOption(iBeg, iLen, idSelect) {
            for (var i = iBeg; i <= iLen; i++) {
                var opt = window.document.createElement("option");
                var txt = document.createTextNode(i);
                opt.setAttribute("value", i);
                opt.appendChild(txt);
                document.getElementById(idSelect).appendChild(opt);
            }
        }
        function chDay() {
            var theYear = document.getElementById('year').value;
            var theMonth = document.getElementById('month').value;
            var theDate = new Date(theYear, theMonth, 0).getDate()
            document.getElementById('date').innerHTML = "";
            createOption(1, theDate, 'date');
            chWord();
        }
        function chWord() {
            var theYear = document.getElementById('year').value;
            var theMonth = document.getElementById('month').value;
            var theDate = document.getElementById('date').value;
         
        }
	</script>

	<script>
		$(function() {
			$('#fm').formToWizard({
				submitButton : 'btnSend'
			});
		});
	</script>
		<script>
	var btn = document.getElementById("name");
	btn.addEventListener("blur",load);
	
	var xxx =null;
 	function load(){
//  		var myDiv = document.getElementById("div1");
//  		myDiv.innerHTML="Hello,Ajax!!";

	xxx = new XMLHttpRequest();
	if(xxx!=null){
	xxx.addEventListener("readystatechange",callback);
	xxx.open("post","AccountCheck.jsp",true);
	xxx.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xxx.send("m_account="+document.getElementById("name").value);
	
	
	function callback(){
		
		if(xxx.readyState==1){
			document.getElementById("img5").style.display ="inline";
		}
		if(xxx.readyState==4){
			document.getElementById("img5").style.display ="none";
		if(xxx.status==200){
			var data  = xxx.responseText;
			
			var myDiv = document.getElementById("div1");
			myDiv.innerHTML="<h3>"+data+"</h3>";
			}else{
				alert(xxx.status+","+xxx.statusText);
			}
		}
			
	}
	}
	
	}
			</script>	
	


</body>
</html>