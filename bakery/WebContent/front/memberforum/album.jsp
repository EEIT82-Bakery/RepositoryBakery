<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" defer="defer"> 
domReady(function() {
var instance1 = new ImageFlow();
instance1.init({
ImageFlowID:'minwtImageFlow'              //imageflow的ID名稱
	, startID: 0                          //啟始ID
	, startAnimation: true                //一開始動態效果
	, imageFocusMax: 3                    //左右的顯示張數
	, imageFocusM: 1                      //圖片的顯示比例
	, xStep: 150                          //圖片x軸間距
	, opacity: true                       //透明效果
	, opacityArray: [10,5,3,1]            //透明效果設定0~10
	, buttons: true                       //上下張的按鈕圖示
	, imagesHeight: 0.57                  //圖片高度位置
	, preloadImagesText: '圖片載入中...'  //圖片載入的提示文字
	, reflectionP: 0.3                    //圖片高度縮放比例
	,onClick: function() {window.open(this.url, '_blank');} //連結另開視窗
});
});
</script>
</head>
<body>
<div id="minwtImageFlow" class="imageflow"> 
    <img src="../img/1.jpg"       longdesc="http://www.minwt.com/"  alt="梅問題商品攝影-無敵鐵金鋼" title="無敵鐵金鋼" /> 
    <img src="../img/2.png"  longdesc="http://www.minwt.com/"  alt="梅問題商品攝影-雙人牌餐具" title="雙人牌餐具" /> 
    <img src="../img/3.png"          longdesc="http://www.minwt.com/"  alt="梅問題商品攝影-舒適刮鬍刀" title="舒適刮鬍刀" /> 
    <img src="../img/4.jpg"         longdesc="http://www.minwt.com/"  alt="梅問題商品攝影-水滴造型香水" title="水滴造型香水" /> 

</div>

<script>



</script>


</body>
</html>