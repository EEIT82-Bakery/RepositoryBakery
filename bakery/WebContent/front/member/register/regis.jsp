<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
    <meta charset="utf-8">
    <!-- This file has been downloaded from Bootsnipp.com. Enjoy! -->
    <title>Mix &amp; Match Register - Bootsnipp.com</title>
  <%@ include file="../member_fragment/css.jsp"%>
    <style type="text/css">
.colorgraph {
  height: 5px;
  border-top: 0;
  background: #c4e17f;
  border-radius: 5px;
  background-image: -webkit-linear-gradient(left, #c4e17f, #c4e17f 12.5%, #f7fdca 12.5%, #f7fdca 25%, #fecf71 25%, #fecf71 37.5%, #f0776c 37.5%, #f0776c 50%, #db9dbe 50%, #db9dbe 62.5%, #c49cde 62.5%, #c49cde 75%, #669ae1 75%, #669ae1 87.5%, #62c2e4 87.5%, #62c2e4);
  background-image: -moz-linear-gradient(left, #c4e17f, #c4e17f 12.5%, #f7fdca 12.5%, #f7fdca 25%, #fecf71 25%, #fecf71 37.5%, #f0776c 37.5%, #f0776c 50%, #db9dbe 50%, #db9dbe 62.5%, #c49cde 62.5%, #c49cde 75%, #669ae1 75%, #669ae1 87.5%, #62c2e4 87.5%, #62c2e4);
  background-image: -o-linear-gradient(left, #c4e17f, #c4e17f 12.5%, #f7fdca 12.5%, #f7fdca 25%, #fecf71 25%, #fecf71 37.5%, #f0776c 37.5%, #f0776c 50%, #db9dbe 50%, #db9dbe 62.5%, #c49cde 62.5%, #c49cde 75%, #669ae1 75%, #669ae1 87.5%, #62c2e4 87.5%, #62c2e4);
  background-image: linear-gradient(to right, #c4e17f, #c4e17f 12.5%, #f7fdca 12.5%, #f7fdca 25%, #fecf71 25%, #fecf71 37.5%, #f0776c 37.5%, #f0776c 50%, #db9dbe 50%, #db9dbe 62.5%, #c49cde 62.5%, #c49cde 75%, #669ae1 75%, #669ae1 87.5%, #62c2e4 87.5%, #62c2e4);
}
input.upl {
	position: absolute;
	top: 0;
	margin: 0;
	border: solid transparent;
	opacity: 0;
	filter: alpha(opacity = 0);
	cursor: pointer;
	height: 260px;
	width: 299px;
}

.progress {
	position: relative;
	margin-left: 100px;
	margin-top: -24px;
	width: 200px;
	padding: 1px;
	border-radius: 3px;
	display: none
}

.bar {
	background-color: green;
	display: block;
	width: 0%;
	height: 20px;
	border-radius: 3px;
}

.percent {
	position: absolute;
	height: 20px;
	display: inline-block;
	top: 3px;
	left: 2%;
	color: #fff
}

.files {
	height: 22px;
	line-height: 22px;
	margin: 10px 0
}

.delimg {
	margin-left: 20px;
	color: #090;
	cursor: pointer
}
    </style>
</head>
<body>
<%@ include file="../member_fragment/nav.jsp"%>
<div class="container">

<div class="row">
    <div class="col-xs-12 col-sm-8 col-md-6 col-sm-offset-2 col-md-offset-3">
		<form id="fm" action="<c:url value='/front/member/regitser/regis.do' />" method="POST" enctype="multipart/form-data">
			<h2>加入會員 </h2>
			<hr class="colorgraph">
			<div class="row">
				<div class="col-xs-12 col-sm-6 col-md-6">
					<div class="form-group">
                   
                         <input type="text" name="m_account" id="user_name" class="form-control input-lg" placeholder="帳號" tabindex="1">
                        <font size="-1" color="#FF0000">${ErrorMsg.x_account}</font>
                        
					</div>
				</div>
				<div class="col-xs-12 col-sm-6 col-md-6">
					<div class="form-group">
						<input type="text" name="m_name" id="name" class="form-control input-lg" placeholder="姓名" tabindex="2" value="${param.m_name}">
						<font size="-1" color="#FF0000">${ErrorMsg.x_m_name}</font>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-xs-12 col-sm-6 col-md-6">
					<div class="form-group">
					<input type="password" name="m_password" id="password" class="form-control input-lg" placeholder="密碼" tabindex="5">            
					</div>
				</div>
				<div class="col-xs-12 col-sm-6 col-md-6">
					<div class="form-group">
						<input type="password" name="m_pwd" id="re_password" class="form-control input-lg" placeholder="確認密碼" tabindex="6">
					<font size="-1" color="#FF0000">${ErrorMsg.x_password}</font>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-xs-12 col-sm-6 col-md-6">
					<div class="form-group">
						<select id="year" name="year"></select>年
						<select id="month"name="month"></select>月
                		<select id="date" name="day"></select>日
					<font size="-1" color="#FF0000">${ErrorMsg.x_bday}</font>          
					</div>
				</div>
				<div class="col-xs-12 col-sm-6 col-md-6">
					<div class="form-group">
						<input type="radio" name="m_gender" id="male" value="M" />男
						<input type="radio" name="m_gender" id="female" class="ml20" value="F" />女
						<font size="-1" color="#FF0000">${ErrorMsg.x_m_gender}</font>
					</div>
				</div>
			</div>
			
			<div class="form-group">
				<input type="email" name="m_email" id="m_email" class="form-control input-lg" placeholder="電子郵件" tabindex="3">
					<font size="-1" color="#FF0000">${ErrorMsg.x_m_email}</font>
			</div>
			<div class="form-group">
				<input type="text" name="phone" id="phone" class="form-control input-lg" value="${param.phone}"  placeholder="連絡電話" tabindex="3">
					<div style="color: #FF0000; font-size: 60%; display: inline">${ErrorMsg.x_e_phone}</div>
			</div>
			<div class="form-group">
				<input type="text" name="m_address" id="email" class="form-control input-lg" value="${param.mAddress}" placeholder="聯絡地址" tabindex="4">
				<span id="m_city_message" class="errorMsg">${ErrorMsg.m_m}</span>
			</div>
			<div class="row">
				<div class="col-xs-12 col-sm-6 col-md-6">
					<div class="form-group">
						 <input type="text" name="nickname" id="nickname" class="form-control input-lg" value="${param.m_nickname}" placeholder="暱稱" tabindex="1">
							<span id="m_nickname" class="errorMsg">${ErrorMsg.m_nick}</span>
					</div>
				</div>
				<div class="col-xs-12 col-sm-6 col-md-6">
					<div class="form-group">
					<input type="text" name="kanban" id="m_kanban"
									class="form-control input-lg" value="${param.kanban}"
									placeholder="自我介紹" tabindex="1"> <span id="m_kanban"
									class="errorMsg" style="color: #FF0000; font-size: 60%; display: inline">${ErrorMsg.m_kanban}</span>
					
			
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-xs-12 col-sm6 col-md-6">
					<div class="btn">
								<div>
									<img class="preview" style="width: 245px; height: 200px;" id="mypicc" name="m_pic" src="${pageContext.request.contextPath}/front/member/images/picture.jpg" />
									<input type='file' class="upl" name="m_pic">
									<br>
									<span>請點此上傳大頭照</span>
								</div>
							</div>
					</div>
						<div class="col-xs-12 col-sm6 col-md-6">
						</div>
			
			</div>
			
			<div class="row">
				
				
					
							<div class="col-xs-4 col-sm-3 col-md-3">
					<span class="button-checkbox">
						<button type="button" class="btn" data-color="info" tabindex="7">我同意</button>
                        <input type="checkbox" name="t_and_c" id="t_and_c" class="hidden" value="1">
					</span>
				</div>
			<div class="col-xs-8 col-sm-9 col-md-9">
					 接受<a href="#" data-toggle="modal" data-target="#t_and_c_m">會員服務條款</a> .
				</div>
			</div>
			
			<hr class="colorgraph">
			<div class="row">
				<div class="col-xs-12 col-md-6"><input type="submit" name="submit" value="註冊"   id="submit" class="btn btn-primary btn-block btn-lg" tabindex="7"></div>		
				<div class="col-xs-12 col-md-6"><input type="reset" name="cancel" id="cancel" value="重填" class="btn btn-primary" /></div>	
			</div>
		</form>
	</div>
</div>
</div>
<!-- Modal -->
<div class="modal fade" id="t_and_c_m" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
				<h4 class="modal-title" id="myModalLabel">閱讀會員條款</h4>
			</div>
		<div class="modal-body">
		<p class="text-danger">為保障您的權益,請於註冊使用服務前，詳細閱覽以下規定。一旦你註冊為會員或使用所提供之任何服務，即表示您同意並遵守以下所有規定。</p>
			<h4 class="text-primary">遵守相關規定</h4>
				<p>您了解網站，專為網友提供網路視訊聊天業務。您於註冊成為會員後，即可使用網站之服務。當您使用網站服務時，即表示您同意並遵守該等網站之會員規範及當地之法律規定。</p></li>
			<h4 class="text-primary">服務簡介</h4>
				<p>係透過網際網路提供您各項網路資訊服務。您必須自行配備上網所需的各項電腦設備，以及負擔接上網際網路的費用及電話費用。<br>
					基於所提供的各項服務，您同意於註冊時提供詳實的個人資料，並隨時更新過時或已變更之個人資料。<br>
					如果您提供的個人資料不實，得隨時終止您會員的資格及使用各項服務之資格。</p></li>
			<h4 class="text-primary">會員規範的修改</h4>
				<p>本站有權隨時修改本規範，將於修改時，於首頁公告修改的事實。如果你不同意修改的內容，請勿繼續使用服務。如果你繼續使用服務，則表示您同意並接受本規範之任何修改。</p></li>
			<h4 class="text-primary">服務的停止與更改</h4>
				<p>有權隨時停止或更改各項服務的內容，並無需事先通知您。無論任何情形，就停止或更改服務所可能致生之困擾、不便、損害，不負任何責任。</p></li>
			<h4 class="text-primary">隱私保護</h4>
				<p>尊重您個人隱私是的基本政策。因此，除法律或因相關主管機關要求外，在未獲您授權時，不會公開您的姓名、地址、電子郵件信箱，以及其他依法受保護的個人資訊。<br>
					您基於提供之各項服務，您同意於法律許可的範圍內，允許之關係企業或合作夥伴，於必要範圍內，得使用您的個人資訊，以提供您其他服務。<br>
					您同意得就您的個人資料作成會員統計資料。如該統計資料，不涉及揭露任何會員的個人身份，您同意並允許為任何合法的使用。<br>
					您同意，於下列情形發生時，得依法公開您的個人資訊：</p>
                    <ol>
						<li>因應法律及相關主管機關要求。</li>
						<li>為保障之財產及權益。</li>
						<li>或在緊急情況下為保障會員或公眾人身安全時。</li>
					</ol>
                    
			<h4 class="text-primary">您的名稱及密碼</h4>
				<p>您註冊後，將得到一個會員名稱及密碼，您就您名稱及密碼之保存安全負全責。 您同意您不會將會員名稱轉讓或出借他人使用。若您發現您的名稱遭人非法使用或有任何異常破壞使用安全的情形時，您應立即通知。</p></li>
			<h4 class="text-primary">擔保責任免除</h4>
				<p>就各項服務，不負任何明示或默示的擔保責任。不保證各項服務的穩定、安全、無誤、及不中斷。您明示承擔使用本服務的風險及可能致生的任何損害。<br>
					對於透過本網站銷售的商品交易過程及商品本身，不負任何擔保責任。您了解您透過所購得的商品或服務，全由商品或服務提供人負全責，若有任何瑕疵或擔保責任，均與無關。</p></li>
			<h4 class="text-primary">賠償責任限制</h4>
				<p>對於您使用各項服務、或無法使用各項服務所致生的任何直接、間接、衍生、或特別損害，不負任何賠償責任。若您使用之服務，係有對價者，僅就您所付的對價範圍內，負賠償責任。<br><br>
					上述賠償責任限制，若依法為不得限制者，則該等限制規定將不適用於您。</p>
			<h4 class="text-primary">您的義務與責任</h4>
				<p>您對您公布於或透過傳輸的一切內容負全責。<br>
					您承諾遵守中華民國相關法規及一切國際網際網路規定與慣例。若您使用台灣地區以外之網站，您同意遵守各該網站當地之法令及網路慣例。<br>
					您同意並保證不公布或傳送任何毀謗、不實、威脅、不雅、猥褻、不法、或侵害他人智慧財產權的資訊於上。<br>
					您同意您不會於上從事廣告或販賣商品行為。<br>
					就您的行為是否符合本規範，有最終決定權。若決定您的行為違反本規範或任何法令，您同意得隨時停止您使用服務。</p>
			<h4 class="text-primary">補償</h4>
				<p>您同意補償、其母公司、子公司、關係企業、受僱人、及一切相關人員，因您違反相關法令或本規範所致生之一切損害及責任。</p>
			<h4 class="text-primary">結束服務</h4>
				<p>您或均有權終止您的會員資格。您若欲終止您的會員資格，須以書面通知。<br>
					有權隨時結束
					</p>
					</div>
					</div>
					</div>
					</div>
					
		<%@ include file="../../fragment/js.jsp"%>			
<script>

$(function() {
	function format_float(num, pos) {
		var size = Math.pow(10, pos);
		return Math.round(num * size) / size;
	}
	function preview(input) {
		if (input.files && input.files[0]) {
			var reader = new FileReader();
			reader.onload = function(e) {
				$('.preview').attr('src', e.target.result);
				var KB = format_float(e.total / 1024, 2);
			}
			reader.readAsDataURL(input.files[0]);
		}
	}
	$("body").on("change", ".upl", function() {
		preview(this);
	})
})

</script>
<script>
window.onload = function () {
    document.getElementById("month").onchange = chDay;
    document.getElementById("year").onchange = chDay;
    document.getElementById("date").onchange = chWord;

    createOption(1950, 2015, 'year');
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
			</body>
			