






var vv = document.getElementById("vv");
var title = document.getElementById("title");
var price = document.getElementById("price");
var desc = document.getElementById("desc");
var proImg = document.getElementById("proImg");

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
        }
    }
}