window.onload = function () {


        if (navigator.geolocation) {
            navigator.geolocation.getCurrentPosition(showPosition);
        } else {
             alert("Geolocation is not supported by this browser.") ;
        }

    function showPosition(position) {
        alert("Latitude: " + position.coords.latitude) ;
        alert("Longitude: " + position.coords.longitude);
        var xhr = new XMLHttpRequest();
        xhr.open("GET","SaveLocation.do?latitude="+position.coords.latitude
                    +"&longitude="+position.coords.longitude,true);
        xhr.send();


        xhr.onreadystatechange = function(){
            if(xhr.readyState==4 && xhr.status==200){
                var msg = JSON.parse(xhr.responseText);
                alert(msg);

            }

        }

    }



}