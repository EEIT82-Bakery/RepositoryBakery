$(document).ready(function() {
    $(".menubar").click(function(){
        $(".menu").slideToggle("slow");
    });

    $(window).resize(function() {
        if ($(window).width() > 899) {
            $(".menu").css('display','');
        }
    });
    
    $("#img-upload").change(function(){
        readImgURL(this);
    });
    
    $(".img-upload1").change(function(){
        readImgURL1(this);
    });
    
    $(".add-loc").click(function(e){ //on add input button click
        e.preventDefault();
        $(this).parents('#loc-area').append("<div class='fields'><div class='five wide field'><select name='shop_area' class='select-loc'>" +
        		"<option value=''>請選擇</option><option value='北部'>北部</option><option value='中部'>中部</option><option value='南部'>南部</option>" +
        		"<option value='東部'>東部</option><option value='外島'>外島</option></select></div><div class='ten wide field'>" +
        		"<input type='text' name='shop_addr' size='50' disabled style='margin-top:0'></div><div class='one wide field'>" +
        		"<a class='ui mini black circular label remove-loc'><i class='fa fa-times'></i></a></div></div>"); //add input box
    });
    
    $(".add-loc").parents("#loc-area").on("click",".remove-loc", function(e){ //user click on remove text
        e.preventDefault(); $(this).parent().parent().remove();
    })
    
    $(".shop-form").on("change", ".select-loc", function() {
    	if ( $(this).val()=="" ) {
    		$(this).parent().next().children().attr("disabled", true);
    	} else {
    		$(this).parent().next().children().attr("disabled", false);
    	}
    });
    
    $('select.dropdown').dropdown();
});

function readImgURL(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = function (e) {
            ($(input).parents('form').find('.img-preview')).attr('src', e.target.result);
        }
        reader.readAsDataURL(input.files[0]);
    }
}

function readImgURL1(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = function (e) {
            ($(input).parents('form').find('.img-preview1')).attr('src', e.target.result);
        }
        reader.readAsDataURL(input.files[0]);
    }
}