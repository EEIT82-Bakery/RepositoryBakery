<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@page import="com.member.model.*"%>   
<%@page import="java.util.*" %>
<%@page import="org.apache.commons.codec.binary.Base64"%>
<%

	MemberService service = new MemberService();
	

 	List<MemberBean> lists = service.getAllMemBer();

 	pageContext.setAttribute("listWhoInvitedMe", lists);

// 	List<MemberBean> bean = service.

%>
	
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<style>
#mypicture{
	height: 50px;
	width: 50px;

}

</style>
</head>
<%@ include file="../fragment/css.jsp"%>
<body>

	<%@ include file="../fragment/main.jsp"%>

	
	
	


	<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
		<h1 class="page-header">會員維護</h1>
		<div class="row placeholders">
			<div class="col-xs-6 col-sm-3 placeholder">
				<form method="post" action="${pageContext.request.contextPath}/BackSelectServlet.do"">
				複合查詢:
				<br>
				會員編號:<input type="text" name="member_id" value="${searchMemMap.member_id[0]}">
				會員帳號:<input type="text" name="account" value="${searchMemMap.member_id[0]}"><br>
				會員姓名:<input type="text" name="username" value="${searchMemMap.member_id[0]}">
				會員狀態:
				<select name="status">
					<option value="" ${searchMemMap.status[0]=="" ?"selected" :""}>不限</option>
					<option value="2" ${searchMemMap.status[0]=="3" ?"selected" :""}>一般會員</option>
					<option value="3" ${searchMemMap.status[0]=="3" ?"selected" :""}>黑名單</option>
				</select>
				認證狀態:		
				<button type="button" class="reset">清除</button>
				<input type="submit" value="送出">
        		<input type="hidden" name="action" value="memberSearch">
			</form>
			
		
			</div>
			<div class="col-xs-6 col-sm-3 placeholder">
				
			
			
			</div>
			<div class="col-xs-6 col-sm-3 placeholder">
				
	
			</div>
			<div class="col-xs-6 col-sm-3 placeholder">
		
		
  
   <FORM METHOD="post" action='<c:url value="/BackSelectServlet.do?action=selectid"/>'>
        <b>以ID搜尋會員</b>
        <input type="text" name="member_id" value="${param.member_id}">
        <input type="submit" value="送出">
        <span>${errors.rey}${errors.noData}${errors.Null}</span>
<!--         <input type="hidden" name="action" value="selectid"> -->
    </FORM>
  
  

			</div>
		</div>
		<h2 class="sub-header">會員列表</h2>
		<div class="table-responsive">
			<table class="table table-striped">
				<thead>
					<tr>
						<th>會員編號</th>	
						<th>會員照片</th>			
						<th>會員帳號</th>
						<th>會員姓名</th>
						<th>會員暱稱</th>
						<th>會員信箱</th>
						<th>會員電話</th>
						<th>訂單數量</th>
						<th>xxx</th>
						<th>bbb</th>
					</tr>
				</thead>
				<tbody>
					
						<tr>
							<td><h6>${onelist.member_id}</h6></td>
							<td><img src="data:image/png;base64,${onelist.mpicture}" id="mypicture" class="img-circle" alt="個人照片" /></td>
							<td><h6>${onelist.account}</h6></td>
							<td><h6>${onelist.username}</h6></td>
							<td><h6>${onelist.nickname}</h6></td>
							<td><h6>${onelist.email}</h6></td>
							<td><h6>${onelist.phone}</h6></td>
							<td><h6><a href="DisplayArticle.do?articleId=${articleBean.articleId}">${aBean.order_math}</a></h6></td>
												
							<td><button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal")">詳細會員資料</button></td>
							<td><button type="submit" name="action" value="刪除">刪除</button></td>
							
						</tr>
				
				</tbody>
			</table>
			
		
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">&times;</span>
							<span class="sr-only">Close</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">會員資訊</h4>
					</div>
					
					<div class="modal-body">

						<br>
						<br> 
						生日:${param.aBean.username}
						<br>
						<br> 
						手機:${param.phone}
						<br>
						<br> 
						信箱:${param.email}
						<br>
						<br>
						 地址:${param.address}
						<br>

					</div>
					
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">返回</button>
						<button type="submit" class="btn btn-primary" name="action" value="update">確定</button>
					</div>
					
				</div>
			</div>
		</div>	
		
		</div>
	</div>







		<!-- Modal -->
	
<script src="../js/jquery-2.1.4.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>


<script type="text/javascript">
	$(document).ready(function(){
		$('.detail-info').click(function() {
			layer.open({
	    	    type: 1,
	    	    title: 'Member Information',
	    	    closeBtn: false,
	    	    area: ['auto'],
	    	    shadeClose: true,
	    	    content: $(this).closest('tr').find('table')
	    	});
		})
	})
</script>






	<div class="col-xs-offset-5"></div>
	<%@ include file="../fragment/js.jsp"%>
</body>
</html>