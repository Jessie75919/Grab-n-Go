


var hasErr = 1

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



// $("#eMail").blur(function () { 
//     $.getJSON("checkEailExist.do", { email:$("#eMail").val()},
//         function (data, textStatus, jqXHR) {
//             if(data==0){
//                 showMgs();
//                 $('#validMsg').html('找不到這個信箱喔~').delay(1000).fadeOut();
//             }else{
//                 hasErr = 0;
//             }
//         }
//     );
// });


$('form').submit(function (e) { 
    if (hasErr==1) {
        e.preventDefault();
    }else{
        $('#ttt').show();
        return;
    }
});


$(document).ready(function () {
      $('#ttt').hide();
      $('#validMsg').hide();
});



$('#sub').on('click', function () {
    $.getJSON("checkEailExist.do", { email:$("#eMail").val()},
    function (data, textStatus, jqXHR) {
        if(data==0){
            showMgs();
            $('#validMsg').html('找不到這個信箱喔~').delay(1000).fadeOut();
        }else{
            hasErr = 0;
        }
    }
);
});
