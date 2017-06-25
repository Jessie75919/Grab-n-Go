
var lat = 0;
var long = 0;
window.onload = function () {


    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(successFunction, errorFunction);
    } else {
       

    }

    //Get latitude and longitude;
    function successFunction(position) {
        lat = position.coords.latitude;
        long = position.coords.longitude;

        localStorage['authorizedGeoLocation'] = 1;

        var xhr = new XMLHttpRequest();
        xhr.open("GET", "SaveLocation.do?latitude=" + lat
            + "&longitude=" + long, true);
        xhr.send();

        xhr.onreadystatechange = function () {
            if (xhr.readyState == 4 && xhr.status == 200) {
                var msg = JSON.parse(xhr.responseText);
                alert(msg);
            }

        }
    }

    function errorFunction() {
        localStorage['authorizedGeoLocation'] = 0;
         var xhr = new XMLHttpRequest();
        xhr.open("GET", "SaveLocation.do?latitude=" + lat
            + "&longitude=" + long, true);
        xhr.send();
        alert("請允許我們知道你的位置才能替您選出附近的餐廳唷~");
    }

    function checkauthorizedGeoLocation() { // you can use this function to know if geoLocation was previously allowed
        if (typeof localStorage['authorizedGeoLocation'] == "undefined" || localStorage['authorizedGeoLocation'] == "0")
            return false;
        else
            return true;
    }






}