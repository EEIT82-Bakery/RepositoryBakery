<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>改個密碼</title>

<style>
	#h{
	margin: 30px;
	
	}
</style>
</head>
<body>
	<%@ include file="../member_fragment/nav.jsp"%>





<h4 id="h">修改密碼</h4>



<form class="form-horizontal" action="<c:url value='/front/member/main/ChangePassword.controller' />" method="post">
<fieldset>
<!-- Form Name -->
<legend>更改密碼</legend>

<!-- Password input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="piCurrPass">現在的密碼</label>
  <div class="col-md-4">
    <input id="piCurrPass" name="oldpassword"  type="password" placeholder="" class="form-control input-md" required="">
    	<h5>${errors.oldpassword}</h5>
  </div>
</div>

<!-- Password input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="piNewPass">新密碼</label>
  <div class="col-md-4">
    <input id="piNewPass" name="newpassword" type="password" placeholder="" class="form-control input-md" required="">
    	<h5>${errors.newpassword}</h5>
  </div>
</div>

<!-- Password input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="piNewPassRepeat">確認新密碼</label>
  <div class="col-md-4">
    <input id="piNewPassRepeat"  name="t_newpassword" type="password" placeholder="" class="form-control input-md" required="">
    <h5>${errors.newpassword}</h5>
  </div>
</div>

<!-- Button (Double) -->
<div class="form-group">
  <label class="col-md-4 control-label" for="bCancel"></label>
  <div class="col-md-8">
    <input type="reset" id="bCancel" name="bCancel" class="btn btn-danger" />
    <input type="submit"  value="送出" class="btn btn-success"/>
	<input type="hidden" name="action" value="ChangePassword"  />
  </div>
</div>

</fieldset>
</form>

<script type="text/javascript">

</script>




</div>


</body>
</html>