var container = document.getElementById("container");

var lat = 0;
var lng = 0;



window.onload = function () {
    $('#ttt').hide();

    $('#validMsg').css("display", "none");
    var status = sessionStorage.getItem("valid")
    // alert('usrname=' + $('#usrName').val());
    // alert('status :' + status);
    if ($('#usrName').val() != null) {
        $.getJSON("checkValidate.do", { user: $('#usrName').val(), mode: 1 },
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
                }
            });
    }


    if (navigator.geolocation) {

        navigator.geolocation.getCurrentPosition
            (function successFunction(position) {
                lat = position.coords.latitude;
                lng = position.coords.longitude;
                var accu = position.coords.accuracy;

                //  alert("in successFunction");
                setCookie("lat", lat);
                setCookie("lng", lng);
                alert("lat" + lat + ",  lng" + lng);
                alert("accu = " + accu);
                var xhr = new XMLHttpRequest();
                xhr.open("GET", "SaveLocation.do?latitude=" + lat
                    + "&longitude=" + lng, true);
                xhr.send();



                localStorage['authorizedGeoLocation'] = 1;
            }, function errorFunction() {
                localStorage['authorizedGeoLocation'] = 0;
                // alert("請允許我們知道你的位置才能替您選出附近的餐廳唷~");

                delete_cookie('lat');
                delete_cookie('lng');
            }, { maximumAge: 600, timeout: 5000, enableHighAccuracy: true });

    }



    var delete_cookie = function (name) {
        document.cookie = name + '=;expires=Thu, 01 Jan 1970 00:00:01 GMT;';
    };


    function checkauthorizedGeoLocation() { // you can use this function to know if geoLocation was previously allowed
        if (typeof localStorage['authorizedGeoLocation'] == "undefined" || localStorage['authorizedGeoLocation'] == "0")
            return false;
        else
            return true;
    }


}


function setCookie(name, value) {
    var cookieName = name;
    if (document.cookie.indexOf(cookieName) >= 0) {
        var expD = new Date();
        expD.setTime(expD.getTime() + (-1 * 24 * 60 * 60 * 1000));
        var uexpires = "expires=" + expD.toUTCString();
        document.cookie = cookieName + "=" + value + "; " + uexpires;
    }
    /*第二段*/
    //設定cookie值
    var d = new Date();
    var exdays = 7;
    //可以自行修改此段，將過期週期設為符合需求的格式
    d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
    var expires = "expires=" + d.toUTCString();
    document.cookie = cookieName + "=" + value + "; " + expires;
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


