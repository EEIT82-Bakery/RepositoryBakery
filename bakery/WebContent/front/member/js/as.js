var signupError;


    checkDist();
    $('#m_city').change(checkDist);
    $('#m_dist').change(function () {
        $('#m_postal').val($(this).find(':selected').attr('rel'));
    });
    $('#register').click(registerValidate);
    $('#registerBtn').click(checkEvent);
    $('[name=chechEmail]').click(checkAccount);

    $('[name=m_email]').keyup(function () {
        if (email) {
            if (email != $(this).val()) {
                $('#m_email_message').html('請檢查帳號');
            } else {
                $('#m_email_message').html('');
            }
        }
    });

});

function checkDist() {
    num = $('#m_city').find(':selected').attr('rel');
    $('#m_dist').empty();
    $.post(lang + '/member/ajaxgetdist', { 'num': num }, function (data) {
        if (data.success) {
            dist_html = '';
            $.each(data.data, function (index, value) {
                if (index > 0) {
                    temp_value = value.slice(0, -3);
                    postal = value.slice(-3);
                } else {
                    temp_value = value;
                    postal = 0;
                }
                is_select = '';
                if (temp_value == dist) {
                    is_select = 'selected="selected";';
                }
                dist_html += '<option value="' + temp_value + '" rel="' + postal + '" ' + is_select + '>' + temp_value + '</option>';
            });
            $('#m_dist').append(dist_html);
        }
    }, 'json');
}

function registerValidate() {
    $('.passMsg').text('');
    if ($('#m_displayname').val() == '') {
        $('#m_displayname_message').html('請輸入姓名');
    } else {
        $('#m_displayname_message').html('');
    }
    if ($('#checkMemberInfo').length != 1) {
        if ($('#m_email').val() == '') {
            $('#m_email_message').html('請輸入Email');
        } else if (!/^([a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4})*$/.test($('#m_email').val())) {
            $('#m_email_message').html('Email格式填寫不正確');
        } else {
            $('#m_email_message').html('');
        }
    }
    if ($('#checkMemberInfo').length != 1) {
        if ($('#m_password').val() == '') {
            $('#m_password_message').html('請輸入密碼');
        } else if ($('#m_password').val().length < 6) {
            $('#m_password_message').html('密碼不得少於六個字');
        } else {
            $('#m_password_message').html('');
        }
        if ($('#re_password').val() == '') {
            $('#re_password_message').html('請輸入確認密碼');
        } else if ($('#re_password').val() != $('#m_password').val()) {
            $('#re_password_message').html('密碼輸入不一致');
        } else {
            $('#re_password_message').html('');
        }
    }
    if ($('#year').val() == '' || $('#month').val() == '' || $('#day').val() == '') {
        $('#date_message').html('日期填寫不完全');
    } else if (!/^(19|20)?[0-9]{2}$/.test($('#year').val()) || $('#year').val() < 1911 || !/^(0?[1-9]|1[012])$/.test($('#month').val()) || !/^(0?[1-9]|[12][0-9]|3[01])$/.test($('#day').val())) {
        $('#date_message').html('日期格式填寫不正確');
    } else {
        $('#date_message').html('');
    }
    if ($('#m_tel_day').val() == '') {
        $('#m_tel_day_message').html('請輸入連絡電話');
    } else {
        $('#m_tel_day_message').html('');
    }
    if ($('#m_mobile').val() == '') {
        $('#m_mobile_message').html('請輸入行動電話');
    } else if (!/^09[0-9]{8}$/.test($('#m_mobile').val())) {
        $('#m_mobile_message').html('行動電話格式填寫不正確');
    } else {
        $('#m_mobile_message').html('');
    }
    if ($('#m_city option:first').prop('selected') == true || $('#m_dist option:first').prop('selected') == true || $('#m_postal').val() == '') {
        $('#m_city_message').html('請填寫詳細地址');
    } else {
        $('#m_city_message').html('');
    }
    if ($('#m_address').val() == '') {
        $('#m_address_message').html('請填寫詳細地址');
    } else {
        $('#m_address_message').html('');
    }
    if ($('#m_displayname_message').html() == '' && $('#m_email_message').html() == '' && $('#m_password_message').html() == '' && $('#re_password_message').html() == '' && $('#date_message').html() == '' && $('#m_tel_day_message').html() == '' && $('#m_mobile_message').html() == '' && $('#m_city_message').html() == '' && $('#m_address_message').html() == '') {

        if ($('#m_email_message').text() == '') {
            $('#signupForm').submit();
        } else {
            alert('請先確認帳號是否重覆!');
        }

    }
}

function checkEvent() {
    if ($('#agreeBox').prop('checked') == false) {
        $('#mcs_container .errorMsg').html('尚未勾選同意條款');
    } else {
        $('#mcs_container .errorMsg').html('');
        $('#mcs_container,#wrapperClip').animate({ 'opacity': '0' }, 500, function () {
            $('#wrapperClip').remove();
            $('#mcs_container .errorMsg').html('');
            $('#mcs_container').hide();
        });
    }
}

function mCustomScrollbars() {
    $("#mcs_container").mCustomScrollbar("vertical", 400, "easeOutCirc", 1.05, "auto", "yes", "yes", 10);
}

function checkAccount() {
    if ($('[name=m_email]').val() != '') {
        $.post(lang + '/member/ajaxcheckaccount', { 'email': $('[name=m_email]').val() }, function (data) {
            if (data.success) {
                $('#m_email_message').text(data.msg);
            } else {
                $('#m_email_message').text('');
                $('.passMsg').text('此帳號可使用');
            }
        }, 'json');
    } else {
        $('#m_email_message').text('請輸入Email');
    }
}

function closeBlockEvent() {
    location.href = baseUrl + lang + '/default';
}