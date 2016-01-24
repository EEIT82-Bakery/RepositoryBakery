<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<% int t = 0; %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>信箱 - 焙客栗工坊</title>
<link href="${pageContext.request.contextPath}/front/HtmlData/css/semantic.min.css" rel="stylesheet" />
<%@ include file="../../fragment/css.jsp"%>
<style>
.forumtitle {
	height: 29px;
	background-color: #e5e5e5;
	color: black;
}
.display {
	padding: 0;
}
.display table {
	width: 100%;
	text-align: center;
}
.demoPadder {
	padding-top: 15px;
}
.btn-default {
	color: #007aff;
	background-color: #fff;
	border-color: #007aff;
}
.btn-default:hover, .btn-default:focus, .btn-default:active,
	.btn-default.active, .open>.btn-default.dropdown-toggle {
	color: #fff;
	background-color: #007aff;
	border-color: #007aff;
}
input[type=submit]{              
                 position: absolute;
                 right: 20px;
                 background: #09c;
                 color: #fff;
                 font-family: tahoma,geneva,algerian;
                 height: 30px;
                 -webkit-border-radius: 15px;
                 -moz-border-radius: 15px;
                 border-radius: 15px;
                 border: 1px solid #999;
             }
             
#friendpicture  {
	width: 130px;
	height: 190px;
}        
</style>
</head>
<body>
	<%@ include file="../../fragment/nav.jsp"%>
	<div class="row">
		<div class="col-xs-offset-2 col-xs-8">
			<h1>我的信件</h1>
		<form action="${pageContext.request.contextPath}/delete.do?action=delete" method="post">	
					<input type="hidden" name="page" value="${page}">				
					<input type="submit" value="刪除信件"
						onclick="return confirm('確定刪除?')" id="button"/> <br /><span> ${delete.error}</span><br />
			<div class="btn-group btn-group-justified demoPadder" role="group"
				aria-label="Justified button group">
				<a href="${pageContext.request.contextPath}/MessageServlet.do?action=select&pages=1"
					class="btn btn-default">全部</a> <a
					href="${pageContext.request.contextPath}/MessageServlet.do?action=selecread&statu=1&pages=1"
					class="btn btn-default">未讀</a> <a
					href="${pageContext.request.contextPath}/MessageServlet.do?action=selecNoRead&pages=1"
					class="btn btn-default">已讀</a>
			</div>
	
			<div class="col-xs-12 display">
			
					<table bgcolor="orange">
					<tr class="forumtitle">
						<td width="7%"><input type="checkbox" id="checkBoxAll"></td>
						<td width="15%"></td>
						<td width="20%">寄件人</td>
						<td width="35%">訊息標題</td>
						<td width="15%">寄送時間</td>
						<td width="8%">寄信狀態</td>
					</tr>
					<c:forEach varStatus="stVar" var="Go" items="${list}">
						<!-- 用兩種顏色交替使用作為顯示商品資料的背景底色 -->
						<c:set var="rowColor" value="#fefefe" />
						<c:if test="${ stVar.count % 2 == 0 }">
							<c:set var="rowColor" value="#f0ece9" />
						</c:if>
						<tr height='18' bgColor="${rowColor}">
							<td><input type="checkbox" class="chkCheckBoxId" value="${Go.msg_id}" name="msg_id"></td>
							<td><img class="img-responsive img-circle" src="${pageContext.request.contextPath}/DBGifReader.do?memberId=${Go.send_id}" id="friendpicture" alt="個人照片"/></td>
							<td style="height: 30px;">${Go.sendAccount}(${Go.sendNickname})</td>
							<td><a href="${pageContext.request.contextPath}/MessageServlet.do?action=count&Msg_id=${Go.msg_id}">${Go.msg_tit}</a></td>
							<td>${Go.mdate}</td>
							<td><c:if test="${Go.msg_state==1}">
									<span style="font-weight: bold;">未讀</span>
								</c:if> <c:if test="${Go.msg_state==2}">
									<span style="font-weight: bold;">已讀</span>
								</c:if></td>
							</tr>
					</c:forEach>
				</table>
		
			</div>
			<c:if test="${not empty pageCount}">
			
				<c:forEach var="page" begin="1" end="${pageCount}">
					<a href="${pageContext.request.contextPath}/MessageServlet.do?action=select&pages=${page}">
						<c:out value="${page}" />
					</a>
				</c:forEach>
				
			</c:if>
				</form>
		</div>
		<%@ include file="../../fragment/footer.jsp"%>
	</div>
	<%@ include file="../../fragment/js.jsp"%>
	
	<script type="text/javascript">
	$(document).ready(function() {
		
		$('#checkBoxAll').click(function(){
			if($(this).is(":checked"))
			   $('.chkCheckBoxId').prop('checked',true);
				else
			   $('.chkCheckBoxId').prop('checked',false);
				
			});
		
		$("#button").click(function(){
			var check=$("input[name='msg_id']:checked").length;//判斷有多少個方框被勾選
			if(check==0){
				alert("您尚未勾選任何項目");
				return false;//不要提交表單
			}else{
				alert("你勾選了"+check+"個項目");
				return true;//提交表單
				}
			});
		});
	</script>
</body>
</html>
