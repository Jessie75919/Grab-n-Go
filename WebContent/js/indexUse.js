


window.onload = function () {
    $('#ttt').hide();

    $('#validMsg').css("display", "none");
    var status = sessionStorage.getItem("valid")
    // alert('usrname=' + $('#usrName').val());
    // alert('status :' + status);
    if ($('#usrName').val() != null) {
        $.getJSON("checkValidate.do", { user: $('#usrName').val() },
            function (data, textStatus, jqXHR) {
                if (data == "NotValid") {
                    if (status == null) {
                        // alert('msg = ' + data);
                        // alert('user = ' + $('#usrName').val());
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
                            "z-index": "99999"
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
                }
            });
    }
}



function closePanel() {
    $('#validMsg').fadeOut()
    validReminder = 1;
    sessionStorage.valid = "nextTime"
}





function readMsg(user) {
    // alert('uesr = ' + user);

    $.getJSON("setReaded.do", { name: user },
        function (data, textStatus, jqXHR) {
            // alert('data=' + data);
            if (data == "Readed") {
                $('#msgCount').css('display', 'none');
            }
        }
    );

}

function readMsgA(user) {
    // alert('uesr = ' + user);

    $.getJSON("../setReaded.do", { name: user },
        function (data, textStatus, jqXHR) {
            // alert('data=' + data);
            if (data == "Readed") {
                $('#msgCount').css('display', 'none');
            }
        }
    );


}


$('#validBtn').on('click', function () {
    $('#ttt').show();
   
});


