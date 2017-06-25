




window.onload = function () {


    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(successFunction, errorFunction);
    }

    //Get latitude and longitude;
    function successFunction(position) {
        var lat = position.coords.latitude;
        var long = position.coords.longitude;

        var xhr = new XMLHttpRequest();
        xhr.open("GET", "SaveLocation.do?latitude=" + position.coords.latitude
            + "&longitude=" + position.coords.longitude, true);
        xhr.send();

        xhr.onreadystatechange = function () {
            if (xhr.readyState == 4 && xhr.status == 200) {
                var msg = JSON.parse(xhr.responseText);
                alert(msg);

            }

        }

        localStorage['authorizedGeoLocation'] = 1;
    }

    function errorFunction() {
        localStorage['authorizedGeoLocation'] = 0;
        alert("請允許我們知道你的位置才能替您選出附近的餐廳唷~");
    }

    function checkauthorizedGeoLocation() { // you can use this function to know if geoLocation was previously allowed
        if (typeof localStorage['authorizedGeoLocation'] == "undefined" || localStorage['authorizedGeoLocation'] == "0")
            return false;
        else
            return true;
    }




}