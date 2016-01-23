<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../fragment/css.jsp"%>
<style>
#map {
	height: 400px;
}
</style>
</head>
<body>
	<!-----------------------------------------nav------------------------------------------>
	<%@ include file="../fragment/nav.jsp"%>
	<!-----------------------------------------nav------------------------------------------>
	<div class="container-fluid">
		<!-----------------橫幅----------------->
		<div class="row">
			<div class="col-xs-12">
				<img src="../HtmlData/images/map.jpg" style="width: 100%" />
			</div>
		</div>
		<!-----------------橫幅----------------->
		<!-----------------------------------------main----------------------------------------->
		<div class="row">
			<div class="col-xs-11 col-xs-offset-1" style="padding: 10px">
				<div>
					<!-- Supply a default place ID for a place in Brooklyn, New York. -->
					<input id="address" type="text" value="25.033493,121.564101">
					<input id="submit" type="button" value="定位">
				</div>
			</div>
			<div class="col-xs-6">
				<div id="map"></div>
			</div>
			<div class="col-xs-6">
				地理位置<br />
				<p>台北市106大安區和平東路二段106號11樓 (科技大樓)</p>
				<br /> 交通資訊<br />
				<p>1. 台北捷運站名: 文湖線科技大樓站</p>
				<br />
				<p>2. 公車站名: 科技大樓站</p>
				<br />
				<p>3. 公車路線有3、15、18、52、72、74、235、278正、284、敦化幹線、和平幹線公車等。</p>
				<br />
			</div>
		</div>
		<!-----------------------------------------main----------------------------------------->
		<!--------footer-------->
		<%@ include file="../fragment/footer.jsp"%>
		<!--------footer-------->
	</div>
	<%@ include file="../fragment/js.jsp"%>
	<script src="../HtmlData/js/map.js"></script>
	<script src="https://maps.googleapis.com/maps/api/js?callback=initMap"
		async defer></script>
</body>
</html>
