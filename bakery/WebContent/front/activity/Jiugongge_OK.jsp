<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>九宮格輪盤</title>
<script src="bootstrap.min.js"></script>
<script src="js/jquery-2.1.4.min.js"></script>    
<link href="css/bootstrap.min.css" rel="stylesheet" />

<style>
#lottery{width:570px;height:510px;margin:0px auto;border:4px solid #ba1809;}
#lottery table{background-color:yellow;}
#lottery table td{position:relative;width:190px;height:170px;text-align:center;color:#333;}
#lottery table td img{display:block;width:190px;height:170px;}
#lottery table td.active .mask{display:block;}
        
.result { display: none; position: absolute; left:55px; top: 190px; width: 395px; height: 118px; background-color: rgba(0,0,0,0.75); filter: alpha(opacity=90)}
.result a { position: absolute; right: 5px; top: 5px; width: 25px; height: 25px; text-indent: -100px; background-image: url(img/close.png); overflow: hidden;}
.result p { padding: 45px 15px 0; font: 16px "Microsoft Yahei"; color: #fff; text-align: center;}
.result em { color: #ffea76; font-style: normal;}

	.absolute {position: absolute;top: 120px;left:50px; width: 200px; height: 200px;}
  
.mask {
    width: 100%;
    height: 100%;
    position: absolute;
    left: 0;
    top: 0;
    background-color:rgba(252,311,4,0.5);
    display: none
}
	
 #startok{cursor:pointer}  /*滑鼠屬標的圖案*/
</style>
<script>


function roll() {
    lottery.times += 1;
    lottery.roll();
    if (lottery.times > lottery.cycle + 10 && lottery.prize == lottery.index) {
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
            if (lottery.times > lottery.cycle + 10 && ((lottery.prize == 0 && lottery.index == 7) || lottery.prize == lottery.index + 1)) {
                lottery.speed += 110;
				lottery.stop(lottery.prize);
            } else {
                lottery.speed += 20;
            }
        }
        if (lottery.speed < 40) {
            lottery.speed = 40;
        };
        lottery.timer = setTimeout(roll, lottery.speed);
    }
    return false;
}

var click = false;




// window.onload = function  () {
//     lottery.init('lottery');
//     $("#startok").click(function () {
// 	$('#result').hide(); 
//         if (click) {
//             return false;
//         } else {           
//             lottery.speed = 100;
//             roll();   
//             click = true; 
//             return false;
//         }
//     });
//     $('#Btn').click(function () { 
//     	$('#result').hide(); 
        
//     })
// };

var prizes = ["恭喜您獲得了 <em>IPhone6(16GB)</em>",
              "恭喜您獲得了 <em>100元折價卷</em>",
              "恭喜您獲得了 <em>侏羅紀 T-SHIRT</em>",
              "恭喜您獲得了 <em>200元折價卷</em>",
              "恭喜您獲得了 <em>300元折價卷</em>",
              "恭喜您獲得了 <em>樂高模型</em>",
              "恭喜您獲得了 <em>玩命關頭7 T-SHIRT</em>",
              "感謝參與 <em>銘謝惠顧</em>"];

    $(function () {
        var $startok = $('#startok');
    
    });
    var lottery = {
        index: -1,    
        count: 0,    
        timer: 0,   
        speed: 20,    
        times: 0,    
        cycle: 50,    
        prize: -1,    
        init: function (id) {
            if ($("#" + id).find(".lottery-unit").length > 0) {
                $lottery = $("#" + id);
                $units = $lottery.find(".lottery-unit");
                this.obj = $lottery;
                this.count = $units.length;
                $lottery.find(".lottery-unit-" + this.index).addClass("active");
            };
        },
        roll: function() {
            var index = this.index;
            var count = this.count;
            var lottery = this.obj;
            $(lottery).find(".lottery-unit-" + index).removeClass("active");
            index += 1;
            if (index > count - 1) {
                index = 0;
            };
                
            $(lottery).find(".lottery-unit-" + index).addClass("active");
            this.index = index;
            return false;
        },
        stop: function (index) {
            this.prize = index;
			$('#result').show();
			$('#Txt').html(prizes[this.prize]);
            return false;
        }
    };
    


    window.onload = function  () {
    	 lottery.init('lottery');
    	  $('#Btn').click(function () { 
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
    	        $('#Btn').click(function () { 
    	        	$('#result').hide(); 
    	            
    	        })
    	}
    	   



</script>
</head>

<body >
<marquee behavior="alternate" bgcolor="#33CCFF	">
<img src="img/duck.jpg" width="50" height="50">按下立即抽獎 ,遊戲每次扣100點 盡情遊玩吧
</marquee>

<div id ="lottery">
        <table>
        <tr>
            <td class="lottery-unit lottery-unit-0"><img src="img/0.jpg"><div class="mask"></div></td>
            <td class="lottery-unit lottery-unit-1"><img src="img/1.jpg"><div class="mask"></div></td>
            <td class="lottery-unit lottery-unit-2"><img src="img/2.jpg"><div class="mask"></div></td>
        </tr>
        <tr>
            <td class="lottery-unit lottery-unit-3"><img src="img/3.jpg"><div class="mask"></div></td>
            <td class="king"> <img src ="img/lottery1.jpg" id ="startok"><div class="startok" /></div></td>
            <td class="lottery-unit lottery-unit-4"><img src="img/4.jpg"><div class="mask"></div></td>
        </tr>
        <tr>
            <td class="lottery-unit lottery-unit-5"><img src="img/5.jpg"><div class="mask"></div></td>
            <td class="lottery-unit lottery-unit-6"><img src="img/6.jpg"><div class="mask"></div></td>
            <td class="lottery-unit lottery-unit-7"><img src="img/7.jpg"><div class="mask"></div></td>
        </tr>
    </table>
    </div>
     <div class="lottery">
     <div class="result" id="result">
            	<p id="Txt"></p>
            	 <a href="javascript:" id="Btn" title="關閉">關閉</a> 
            </div>
            </div>
        
        
 
                <div class="absolute id ="absolute" >  
                <table border='1' width='200' top='50'  style="background:#F5EBFF; border-color:rgb( 100, 100, 255)">
           <tr>
           		<TD>客戶姓名:${isLogin.username}</TD>
           		<TD>客戶點數:${isLogin.point}</TD>
           <tr>
       <form action="<c:url value='/PointServlet.do '/>" method="get">
            <input type="button" value="遊戲開始" name="upd" onclick="dsd()" >
<!--             <input type="submit" value="遊戲開始" name="upd" onclick="xxx()" > -->
     </form>          
 		</table> 
   			 </div>
      
       

</body>
</html>