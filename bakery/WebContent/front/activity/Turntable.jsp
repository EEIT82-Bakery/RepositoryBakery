<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <title>幸運大轉盤</title>
   <link href="css/start.css" rel="stylesheet" />
    <style>
/*         .result { display: none; position: absolute; left:55px; top: 190px; width: 395px; height: 118px; background-color: rgba(0,0,0,0.75); filter: alpha(opacity=90)} */
/*         .result a { position: absolute; right: 5px; top: 5px; width: 25px; height: 25px; text-indent: -100px; background-image: url(img/close.png); overflow: hidden;} */
/*         .result p { padding: 45px 15px 0; font: 16px "Microsoft Yahei"; color: #fff; text-align: center;} */
/*         .result em { color: #ffea76; font-style: normal;} */

    </style>
</head>
<body>

<marquee   behavior="alternate" bgcolor="#FF44AA">
<img src="img/duck2.jpg" width="50" height="50">按下即及參加 ,遊戲每次扣50點 盡情遊玩吧
</marquee>
    
        <div class="root">
                <div id ="turn"><img src="img/turn.jpg" /></div>
                <div id ="start"><img src ="img/start.png" id ="startok" /></div>

            <div class="result" id="result">
            	<p id="Txt"></p>
		         <a href="javascript:" id="Btn" title="關閉">關閉</a> 
            </div>
            
        

         </div>
     <script src="js/jquery-2.1.4.min.js"></script>

    <script src="js/jQueryRotate.2.2.js"></script>
    <script>
        $(function () {
            var $startok = $('#startok');
            var $result = $('#result');
            var $Txt = $('#Txt');
            var $Btn = $('#result'); 
           

            $startok.click(function () {
                var data = [0, 1, 2, 3, 4, 5, 6, 7];
                data = data[Math.floor(Math.random() * data.length)]; //傳回小於或等於指定十進位數字的最大整數
                switch (data) {
                    case 1:
                        rotateFunc(1, 87, '恭喜您獲得了 <em>100</em> 元折價卷');
                        break;
                    case 2:
                        rotateFunc(2, 43, '恭喜您獲得了 <em>300</em> 元折價卷');
                        break;
                    case 3:
                        rotateFunc(3, 134, '恭喜您獲得了 <em>200</em> 元折價卷');
                        break;
                    case 4:
                        rotateFunc(4, 177, '很遗憾，這次您沒中獎，繼續加油吧');
                        break;
                    case 5:
                        rotateFunc(5, 223, '恭喜您獲得了 <em>300</em> 元折價卷');
                        break;
                    case 6:
                        rotateFunc(6, 268, '恭喜您獲得了 <em>100</em> 元折價卷');
                        break;
                    case 7:
                        rotateFunc(7, 316, '恭喜您獲得了 <em>200</em> 元折價卷');
                        break;
                    default:
                        rotateFunc(0, 0, '很遺憾，這次您沒中獎，繼續加油吧');
                }
            });
            var rotateFunc = function(awards,angle,text){       //awards:奖项，angle:奖项对应的角度
                $startok.stopRotate(); 						   	
                $startok.rotate({         //循環
                    angle: 0,
                    duration: 5000,
                    animateTo: angle + 1440,    //angle是图片上各奖项对应的角度，1440是让指针固定旋转4圈
                    callback: function () {
                        $Txt.html(text);  
                        $result.show();
                    }
                });
            };
            $Btn.click(function () {  //当点击按钮时，隐藏或显示元素：
                $result.hide();  //隐藏可见的result元素
            });
        });
        
        
    </script>
</body>
</html>