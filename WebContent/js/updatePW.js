
var hasErr = 0;

function showMgs() {
    $('#validMsg').css({
        "color": "#fff",
        "text-align": "center",
        "background": "rgba(235,80,60,0.8)",
        "width": "600px",
        "padding": "20px",
        "position": "fixed",
        "top": "120px",
        "left": "50%",
        "border-radius": "5px",
        "margin-left": "-300px",
        "z-index": "9999"
    }).fadeIn();

}


$('#oldPWInput').blur(function () {

    // 從LoginOK的MemberBean取到的PW已經被MD5加密過了，所以把輸入的密碼直接丟後端在經過MD5加密之後做對比，傳回結果
    $.getJSON("../ValidatePW.do", { userId: $('#userId').val(), pwInput: $('#oldPWInput').val() },
        function (data, textStatus, jqXHR) {
            if (data == 0) {
                $('#newPW').hide();
                $('#newPW2').hide();
                showMgs();
                $('#validMsg').html('您的舊密碼不正確喔~').delay(1000).fadeOut();
                hasErr = 1;
            } else {
                hasErr = 0;
                $('#newPW').show();
                $('#newPW2').show();
            }
        }
    );



    // if ($('#oldPWInput').val() != $('#oldPW').val()) {
    //     $('#newPW').hide();
    //     $('#newPW2').hide();
    //     showMgs();
    //     $('#validMsg').html('您的舊密碼不正確喔~').delay(1000).fadeOut();
    //     hasErr = 1;
    // } else {
    //     hasErr = 0;
    //     $('#newPW').show();
    //     $('#newPW2').show();
    // }
});



$(document).ready(function () {
    $('#validMsg').hide();
    $('#newPW').hide();
    $('#newPW2').hide();
});



$('form').submit(function (e) {
    if ($('#newPW').val() != $('#newPW2').val()) {
        showMgs();
        $('#validMsg').html('您的新密碼內容不相符喔~').delay(1000).fadeOut();
        hasErr = 1;
    }

    if (hasErr == 1) {
        e.preventDefault();
    } else {
        return;
    }

});
