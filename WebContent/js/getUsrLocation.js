// var container = document.getElementById("container");

// var lat = 0;
// var lng = 0;

// window.onload = function () {


//     if (navigator.geolocation) {
//         navigator.geolocation.getCurrentPosition
//             (successFunction, errorFunction);
//     }

//     //Get latitude and longitude;
//     function successFunction(position) {
//         lat = position.coords.latitude;
//         lng = position.coords.longitude;

//         //  alert("in successFunction");
//         setCookie("lat", lat);
//         setCookie("lng", lng);
//         //  alert("lat" + lat + ",  long" + long);

//         var xhr = new XMLHttpRequest();
//         xhr.open("GET", "SaveLocation.do?latitude=" + lat
//             + "&longitude=" + lng, false);
//         xhr.send();


//         localStorage['authorizedGeoLocation'] = 1;
//     }

//     function errorFunction() {
//         localStorage['authorizedGeoLocation'] = 0;
//         // alert("請允許我們知道你的位置才能替您選出附近的餐廳唷~");

//         delete_cookie('lat');
//         delete_cookie('lng');

//     }


//     var delete_cookie = function (name) {
//         document.cookie = name + '=;expires=Thu, 01 Jan 1970 00:00:01 GMT;';
//     };


//     function checkauthorizedGeoLocation() { // you can use this function to know if geoLocation was previously allowed
//         if (typeof localStorage['authorizedGeoLocation'] == "undefined" || localStorage['authorizedGeoLocation'] == "0")
//             return false;
//         else
//             return true;
//     }


// }

// function setCookie(name, value) {
//     var cookieName = name;
//     if (document.cookie.indexOf(cookieName) >= 0) {
//         var expD = new Date();
//         expD.setTime(expD.getTime() + (-1 * 24 * 60 * 60 * 1000));
//         var uexpires = "expires=" + expD.toUTCString();
//         document.cookie = cookieName + "=" + value + "; " + uexpires;
//     }
//     /*第二段*/
//     //設定cookie值
//     var d = new Date();
//     var exdays = 7;
//     //可以自行修改此段，將過期週期設為符合需求的格式
//     d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
//     var expires = "expires=" + d.toUTCString();
//     document.cookie = cookieName + "=" + value + "; " + expires;
// }



function createStoreList(store) {
    console.log(store.rest_name);

    var gridItem = document.createElement("div");
    gridItem.className = "gridItem";
    /*---------------------------------------------------------*/
    // picture HTML
    var figure = document.createElement("figure");
    /*---------------------------------------------------------*/
    var image = document.createElement("img");
    image.src = "GetImage?no=" + store.rest_id + "&type=coverImage";
    image.alt = store.rest_name;
    image.title = store.rest_name;
    /*---------------------------------------------------------*/
    var mask = document.createElement("div");
    mask.className = "mask";
    /*---------------------------------------------------------*/
    var link = document.createElement("a");
    link.href = "getStoreBean.do?id=" + store.rest_id;
    /*---------------------------------------------------------*/
    var text = document.createTextNode("觀看菜單");

    link.appendChild(text);
    mask.appendChild(link);
    figure.appendChild(image);
    figure.appendChild(mask);
    gridItem.appendChild(figure);

    /*---------------------------------------------------------*/
    // text HTML      
    var storeInfo = document.createElement("div");
    storeInfo.className = "storeInfo";
    /*---------------------------------------------------------*/
    var h2 = document.createElement("h2");
    /*---------------------------------------------------------*/
    var link2 = document.createElement("a");
    link2.href = "getStoreBean.do?id=" + store.rest_id;
    /*---------------------------------------------------------*/
    var text2 = document.createTextNode(store.rest_name);

    link2.appendChild(text2);
    h2.appendChild(link2);
    storeInfo.appendChild(h2);
    /*---------------------------------------------------------*/
    var star = document.createElement("div");
    star.className = "star";
    /*---------------------------------------------------------*/
    var i1 = document.createElement("i");
    i1.className = "icon-star on";
    var i2 = document.createElement("i");
    i2.className = "icon-star on";
    var i3 = document.createElement("i");
    i3.className = "icon-star on";
    var i4 = document.createElement("i");
    i4.className = "icon-star";
    var i5 = document.createElement("i");
    i5.className = "icon-star";

    star.appendChild(i1);
    star.appendChild(i2);
    star.appendChild(i3);
    star.appendChild(i4);
    star.appendChild(i5);
    storeInfo.appendChild(star);
    /*---------------------------------------------------------*/
    var dist = document.createElement("div");
    dist.className = "dist";
    /*---------------------------------------------------------*/
    var loc_i = document.createElement("i");
    loc_i.className = "icon-location";
    var addressText = store.rest_address;
    var areaText = addressText.substring(addressText.indexOf("市") + 1, addressText.indexOf("區") + 1);
    var textDist = document.createTextNode(areaText);

    dist.appendChild(loc_i);
    dist.appendChild(textDist);
    storeInfo.appendChild(dist);
    /*---------------------------------------------------------*/
    var tagOrder = document.createElement("div");
    tagOrder.className = "tagOrder";
    /*---------------------------------------------------------*/
    var tag = document.createElement("div");
    tag.className = "tag";
    /*---------------------------------------------------------*/
    var span = document.createElement("span");
    /*---------------------------------------------------------*/
    var textType = document.createTextNode(store.rest_type);

    span.appendChild(textType);
    tag.appendChild(span);
    tagOrder.appendChild(tag);
    /*---------------------------------------------------------*/
    var orderCount = document.createElement("div");
    orderCount.className = "orderCount";
    /*---------------------------------------------------------*/
    var textOrder = document.createTextNode("目前訂單");
    var spanCount = document.createElement("span");
    /*---------------------------------------------------------*/
    var textCount = document.createTextNode("1000");

    spanCount.appendChild(textCount);
    orderCount.appendChild(textOrder);
    orderCount.appendChild(spanCount);
    tagOrder.appendChild(orderCount);
    storeInfo.appendChild(tagOrder);
    gridItem.appendChild(storeInfo);

    /*---------------------------------------------------------*/
    container.appendChild(gridItem);


    // alert("hihihihihi");
}