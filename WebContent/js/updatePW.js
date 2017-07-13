
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
    $('#validMsg a').css({
        "text-decoration": "none",
        "color": "rgb(235,80,60)",
        "border-radius": "20px",
        "margin": "10px 2px 0px 2px",
        "display": "inline-block",
        "width": "100px",
        "padding": "3px 10px",
        "background": "#fff"
    });
}


$('#oldPWInput').blur(function () {
    if ($('#oldPWInput').val() != $('#oldPW').val()) {
        $('#newPW').hide();
        $('#newPW2').hide();
        showMgs();
        $('#validMsg').html('您的舊密碼不正確喔~').delay(1000).fadeOut();
        hasErr = 1;
    }else{
        hasErr = 0;
        $('#newPW').show();
        $('#newPW2').show();
    }
});



$(document).ready(function () {
    $('#validMsg').hide();
    $('#newPW').hide();
    $('#newPW2').hide();
});


function closePanel() {
    $('#validMsg').fadeOut()
}

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
