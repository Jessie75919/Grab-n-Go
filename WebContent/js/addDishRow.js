var addBtn = document.getElementById("addMenu");
var table = document.getElementById("menuTable");
var countVar = document.getElementById("count");
var insertBtn = document.getElementById("insertButton");
var form = document.getElementById("theForm");
var count = 0;

addBtn.onclick = function () {
    count++;
    countVar.value = count;
//    alert("countVar.value = " +countVar.value);

    // alert("KFG");

    var tr = document.createElement("tr");

    var td1 = document.createElement("td");
    var dishName = document.createElement("input");
    dishName.setAttribute("type", "text");
    dishName.setAttribute("name", "dishName" + count);
    dishName.setAttribute("id", "dishName" + count);


    var td2 = document.createElement("td");
    var dishType = document.createElement("input");
    dishType.setAttribute("type", "text");
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

    td1.appendChild(dishName);
    td2.appendChild(dishType);
    td3.appendChild(dishDesc);
    td4.appendChild(dishPrice);
    td5.appendChild(dishImage);

    tr.appendChild(td1);
    tr.appendChild(td2);
    tr.appendChild(td3);
    tr.appendChild(td4);
    tr.appendChild(td5);

    table.appendChild(tr);

}


function validateForm(e) {
    e.preventDefault();
    var hasErr = false;
    form.submit();



}