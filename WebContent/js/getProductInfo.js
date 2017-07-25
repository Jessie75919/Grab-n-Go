

var vv = document.getElementById("vv");
var title = document.getElementById("title");
var price = document.getElementById("price");
var desc = document.getElementById("desc");
var proImg = document.getElementById("proImg");
var idKey = document.getElementById("idKey");
var itemName = document.getElementById("itemName");
var itemType = document.getElementById("itemType");
var itemPrice = document.getElementById("itemPrice");
var specialNeed = document.getElementById("specialNeed");
var count = document.getElementById("count");
var btn = document.getElementById("submit");
var currentStoreId = document.getElementById('rest').value;
var tabA;



// <start> 找尋點擊的那筆商品的資料 & 塞入跳出的訂購頁面
function clickMe(e) {
    // alert(e.id);
    var xhr = new XMLHttpRequest();
    xhr.open("GET", "../getOneProduct.do?proId=" + e.id, "true");
    xhr.send();
    
    tabA = e.parentNode.parentNode.parentNode.id;
    // alert('tab = ' + tabA);
    
    xhr.onreadystatechange = function () {
        if (xhr.readyState == 4 && xhr.status == 200) {
            var product = JSON.parse(xhr.responseText);
            proImg.src = "/_Grab_Go/_00_init/getImage?id=" + product.prod_id;
            title.innerText = product.prod_name;
            price.innerText = "$" + product.prod_price;
            desc.innerText = product.prod_desc;
            count.value = 1;
            itemType.value = tabA;
            specialNeed.value = "";
            idKey.value = e.id;
            itemName.value = product.prod_name;
            itemPrice.value = product.prod_price;
        }
    }
}
// <end> 找尋點擊的那筆商品的資料 & 塞入跳出的訂購頁面

$('#helper').click(function () { 
    $('#specialNeed').val("兩坨飯飯");
});
$('#proImg').click(function () { 
    $('#specialNeed').val("半糖、少冰");
});




window.onload = function () {

    // <start> 下定購物車之後回到下訂前的那個標籤 
    var anchor = document.getElementById("anchor").value;
    var info = anchor.split("|");
    var typeLast = info[0];
    var storeIdLast = info[1];

    // alert('achor = ' +anchor);
    // alert('typeLast = '+typeLast); 
    // alert('storeIdLast = '+storeIdLast); 
    // alert('currentStoreId = '+currentStoreId); 

    if (storeIdLast == currentStoreId) {
        if (anchor != "") {

            $('#tab1').css('dsiplay', 'none').removeClass('tab active').addClass('tab');
            $('#tab1A').removeClass('slick-slide slick-active active').addClass('slick-slide slick-active');


            $('#' + typeLast).css({
                display: "block"
            }).addClass('tab active');
            $('#' + typeLast + 'A').removeClass('slick-slide slick-active').addClass('slick-slide slick-active active');
        }

    }

    // <end> 下定購物車之後回到下訂前的那個標籤 


    // ========= 如果訂了A餐廳卻又下定了B餐廳跳出不可重複訂餐的訊息 <s>
    var msg = document.getElementById("showMsg").value;

    console.log(msg);
    if (msg == "" || msg == null) {
    } else {
        alert(msg);
        //        window.location.href = "_07_storePage/getOneRest.do?id=";
        msg = "";
        return;
    }
    // ========= 如果訂了A餐廳卻又下定了B餐廳跳出不可重複訂餐的訊息 <e>
}
