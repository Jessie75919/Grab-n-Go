var addBtn = document.getElementById("addMenu");
var table = document.getElementById("menuTable");
var countVar = document.getElementById("count");
var form = document.getElementById("theForm");
var usename = document.getElementById("storeUserName").value;
var count = 0;
var typeArr = [];

var xhr = new XMLHttpRequest();
alert(usename);
xhr.open("GET", "findProductType.do?id=" + usename, true);
xhr.send();

xhr.onreadystatechange = function () {
    alert("in AJAX");
    if (xhr.readyState == 4 && xhr.status == 200) {
        typeArr = JSON.parse(xhr.responseText);
        for (type in typeArr) {
            var type = [types.prod_typeName, types.rest_name];
            alert("type= " +type);
            typeArr.push(type); 
        }

    }
}




addBtn.onclick = function () {
    count++;
    countVar.value = count;
    //    alert("countVar.value = " +countVar.value);
    // alert("KFG");

    var tr = document.createElement("tr");
    tr.setAttribute("id", "tr" + count);

    // var tda = document.createElement("td");
    // var showCot = document.createElement("span");
    // var t = document.createTextNode(count);
    // showCot.appendChild(t);
    // tda.appendChild(showCot);
    // tr.appendChild(tda);

    var td0 = document.createElement("td");
    var deleBtn = document.createElement("i");
    // var deleBtn = document.createElement("span");
    // var t = document.createTextNode("Delete");
    // deleBtn.appendChild(t);
    deleBtn.setAttribute("class", "fa fa-minus-square");
    deleBtn.setAttribute("name", "deleBtn" + count);
    deleBtn.setAttribute("id", "deleBtn" + count);
    deleBtn.setAttribute("onClick", "deleteRow(this)");


    var td1 = document.createElement("td");
    var dishName = document.createElement("input");
    dishName.setAttribute("type", "text");
    dishName.setAttribute("name", "dishName" + count);
    dishName.setAttribute("id", "dishName" + count);


    var td2 = document.createElement("td");
    var dishType = document.createElement("select");
    dishType.setAttribute("name", "dishType" + count);
    dishType.setAttribute("id", "dishType" + count);


    var td3 = document.createElement("td");
    var dishDesc = document.createElement("input");
    dishDesc.setAttribute("type", "text");
    dishDesc.setAttribute("placeholder", "optional")
    dishDesc.setAttribute("name", "dishDesc" + count);
    dishDesc.setAttribute("id", "dishDesc" + count);

    var td4 = document.createElement("td");
    var dishPrice = document.createElement("input");
    dishPrice.setAttribute("type", "text");
    dishPrice.setAttribute("name", "dishPrice" + count);
    dishPrice.setAttribute("id", "dishPrice" + count);

    var td5 = document.createElement("td");
    var dishImage = document.createElement("input");
    dishImage.setAttribute("type", "file");
    dishImage.setAttribute("name", "file" + count);



    td0.appendChild(deleBtn);
    td1.appendChild(dishName);
    td2.appendChild(dishType);
    td3.appendChild(dishDesc);
    td4.appendChild(dishPrice);
    td5.appendChild(dishImage);

    tr.appendChild(td0);
    tr.appendChild(td1);
    tr.appendChild(td2);
    tr.appendChild(td3);
    tr.appendChild(td4);
    tr.appendChild(td5);

    table.appendChild(tr);

}


function deleteRow(me) {
    // alert("AAAA");
    // alert(me.id);

    var delBtn = document.getElementById(me.id);
    table.removeChild(delBtn.parentNode.parentNode);

}


function validateForm(event) {
    // alert("validateForm");
    event.preventDefault();
    var hasErr = false;
    var result = document.getElementById("showMsg");

    for (var i = 1; i <= count; i++) {
        var dishName = document.getElementById("dishName" + i);
        try {
            if (!dishName.value) {
                hasErr = true;
                result.innerHTML = "<span>貼心小提醒 : 請刪除不必要的欄位喔~</span>";
            } else {
                result.innerHTML = "";
            }
        } catch (err) {
            // alert("not Found");
        }
    }

    if (hasErr) {
        return false
    } else {
        form.submit();
    }

}

