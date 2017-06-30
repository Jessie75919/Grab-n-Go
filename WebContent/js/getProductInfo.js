

var vv = document.getElementById("vv");
var title = document.getElementById("title");
var price = document.getElementById("price");
var desc = document.getElementById("desc");
var proImg = document.getElementById("proImg");
var idKey = document.getElementById("idKey");
var itemName = document.getElementById("itemName");
var itemPrice = document.getElementById("itemPrice");
var specialNeed = document.getElementById("specialNeed");
var count = document.getElementById("count");
var btn = document.getElementById("submit");

var stroage = localStorage;
var iNo = 0;
var iName, iPrice, iQty, iSpecialNeed;

function clickMe(e) {
    // alert(e.id);
    var xhr = new XMLHttpRequest();
    xhr.open("GET", "../getOneProduct.do?proId=" + e.id, "true");
    xhr.send();

    xhr.onreadystatechange = function () {
        if (xhr.readyState == 4 && xhr.status == 200) {
            var product = JSON.parse(xhr.responseText);
            proImg.src = "/_Grab_Go/_00_init/getImage?id=" + product.prod_id;
            title.innerText = product.prod_name;
            price.innerText = "$" + product.prod_price;
            desc.innerText = product.prod_desc;
            count.value = 1;
            specialNeed.value ="";
            idKey.value = e.id;
            itemName.value = product.prod_name;
            itemPrice.value = product.prod_price;
        }
    }
        // btn.onclick = function () {
        //     var xhr1 = new XMLHttpRequest();
        //     xhr1.open("GET", "../addItem.do?proId=" + e.id, "true");
        //     xhr1.send();
        // };
}

window.onload = function () {
    var msg = document.getElementById("showMsg").value;
    console.log(msg);
    if(msg=="" || msg==null){
    }else{
        alert(msg);
        msg = "";
    }
}


/*  
function Item(no, proId, itemName, itemPrice, itemQty, itemNeed) {
    this.no = no;
    this.proId = proId;
    this.itemName = itemName;
    this.itemPrice = itemPrice;
    this.itemQty = itemQty;
    this.itemNeed = itemNeed;
    this.subTotalCompute = function () {
        return this.itemQty * this.itemPrice;
    };
};
*/