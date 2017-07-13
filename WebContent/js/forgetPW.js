


var hasErr = 1

$("#eMail").blur(function () { 
    $.getJSON("checkEailExist.do", { email:$("#eMail").val()},
        function (data, textStatus, jqXHR) {
            if(data==0){
                $('#checkMail').html("找不到這個Email喔");
            }else{
                $('#checkMail').html("");
                hasErr = 0;
            }
        }
    );
});


$('form').submit(function (e) { 
    if (hasErr==1) {
        e.preventDefault();
    }else{
        return;
    }
});


$(document).ready(function () {
      $('#ttt').hide();
});



$('#sub').on('click', function () {
    $('#ttt').show();

});
