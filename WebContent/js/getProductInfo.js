

var vv = document.getElementById("vv");
var title = document.getElementById("title");
var price = document.getElementById("price");
var desc = document.getElementById("desc");
var proImg = document.getElementById("proImg");
var idKey = document.getElementById("idKey");
var specialNeed = document.getElementById("specialNeed");
var count = document.getElementById("count");
var btn = document.getElementById("submit");

var stroage = localStorage;
var iNo = 0 ;
var iName,iPrice,iQty,iSpecialNeed;

function clickMe(e) {
    // alert(e.id);
    var xhr = new XMLHttpRequest();
    xhr.open("GET","../getOneProduct.do?proId="+e.id,"true");
    xhr.send();

    xhr.onreadystatechange = function(){
        if(xhr.readyState==4 && xhr.status==200){
            var product = JSON.parse(xhr.responseText);
            proImg.src="/_Grab_Go/_00_init/getImage?id="+product.prod_id;
            title.innerText = product.prod_name;
            price.innerText = "$" + product.prod_price;
            desc.innerText =  product.prod_desc;
            idKey.value = e.id;
        }
        btn.onclick = function(){
            iQty = count.value;
            console.log("iQty=" + iQty);
            iSpecialNeed = specialNeed.value;
            console.log("iSpecialNeed=" + iSpecialNeed);
            ++iNo;
            var item = new Item(iNo,e.id,product.prod_name, product.prod_price,iQty,iSpecialNeed );
            alert( JSON.stringify(item))
            
            var text += JSON.stringify(item);

            localStorage.setItem = ( 'aa' , text);
            
            var retrieve = localStorage.getItem('aa');

            alert("retrieve = " + JSON.parse(retrieve));
        };

    }
}





function Item(no,proId,itemName,itemPrice,itemQty,itemNeed) {
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