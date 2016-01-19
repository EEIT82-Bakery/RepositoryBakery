<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登入後台系統</title>

    <!-- Bootstrap core CSS -->
    <link href="../../css/bootstrap.min.css" rel="stylesheet" />
    <!--external css-->
     <link href="../../font-awesome/css/font-awesome.css" rel="stylesheet" />

    <!-- Custom styles for this template -->
    <link href="../../css/style.css" rel="stylesheet" />
    <link href="../../css/style-responsive.css" rel="stylesheet" />
  </head>
  <body>
	  <div id="login-page">
	  	<div class="container">
		      <form class="form-login" action="../../backindex.html<"> 
		        <h2 class="form-login-heading">登入後台系統</h2>
		        <div class="login-wrap">
		            <input type="text" class="form-control" placeholder="帳號" autofocus>
		            <br>
		            <input type="password" class="form-control" placeholder="密碼">
                    <br />
		            <button class="btn btn-theme btn-block" type="submit"><i class="fa fa-lock"></i>登入</button>
		            <hr>
		        </div>
                  </form>
             </div>
         </div>
           <!-- js -->
    <script src="../../js/jquery.min.js"></script>
    <script src="../../js/bootstrap.min.js"></script>
    <script src="../../js/jquery-2.1.4.min.js"></script>
   

    <!-- You can use an image of whatever size. This script will stretch to fit in any screen size 背景圖片.-->
      <script src="../../js/jquery.backstretch.min.js"></script>
    <script>
        $.backstretch("../../images/login-bg.jpg", { speed: 100 });
    </script>
  </body>
</html>