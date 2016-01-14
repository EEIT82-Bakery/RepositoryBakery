<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="../js/ie-emulation-modes-warning.js"></script>
<link href="../css/main.css" rel="stylesheet" />
<link href="../css/bootstrap.min.css" rel="stylesheet" />
<!-- <link href="../css/non-responsive.css" rel="stylesheet" /> -->
<link href="../css/login.css" rel="stylesheet" />
<script src="../js/jquery-2.1.4.js"></script>
</head>
<body>
<%@ include file="../member_fragment/topline.jsp"%>


<table>
	<tr>
		<td>ID : </td>
		<td><input type="text" name="account" id="account"></td>
		<td><div id="acc"></div></td>
		
	</tr>
	<tr>
		<td>PWD : </td>
		<td><input type="text" name="password" id="pwd"></td>
		<td><div id="pwd"></div></td>
	</tr>
	<tr>
		<td>　</td>
		<td align="right"><input type="submit" name="action" id="login" >登入</td>
		
	</tr>
</table>

<script>

// 	window.onload = function() {

// 		document.getElementById("islogin").onsubmit = function (){
// 			var account = document.getElementsByName("account")[0].value;
// 			var password = document.getElementsByName("password")[0].value;
// 			if(account.trim()==''){
// 				alert('請輸入帳號');
// 			if(password.trim()==''){
// 				alert('請輸入密碼');
// 			}
			
// 		}
		
	
// 		};
// 	}

	 $(document).ready(function(){
                        $('#login').click(function()
                        {   
                            var user=$('#account').val();
                            var pwd=$('#pwd').val();
                            $.ajax({
                                type: "POST",
                                url:"IsLoginServlet",   // this is my servlet
                                data:{"account":account,"password":pwd},
                                success: function (data) {
                                	if(data=='True'){
                                		 $(location).attr('href','index.jsp');
                                    }else{
                                        alert('Fail....');
                                	}
                                }
                            });                                
                        });
                    });


</script>
						
</body>
</html>