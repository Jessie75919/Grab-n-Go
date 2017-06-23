var addBtn = document.getElementById("addType");
var table = document.getElementById("productTypeTable");
var countVar = document.getElementById("count");
var form = document.getElementById("theForm");
var count = 0;

addBtn.onclick = function () {
    count++;
    countVar.value = count;

    var tr = document.createElement("tr");
    tr.setAttribute("id", "tr" + count);

    var tda = document.createElement("td");
    var showCot = document.createElement("span");
    var t = document.createTextNode(count);
    showCot.appendChild(t);
    tda.appendChild(showCot);
    tr.appendChild(tda);

    var td0 = document.createElement("td");
    var deleBtn = document.createElement("i");
    deleBtn.setAttribute("class", "fa fa-minus-square");
    deleBtn.setAttribute("name", "deleBtn" + count);
    deleBtn.setAttribute("id", "deleBtn" + count);
    deleBtn.setAttribute("onClick", "deleteRow(this)");


    var td1 = document.createElement("td");
    var typeName = document.createElement("input");
    typeName.setAttribute("type", "text");
    typeName.setAttribute("name", "typeName" + count);
    typeName.setAttribute("id", "typeName" + count);

    td0.appendChild(deleBtn);
    td1.appendChild(typeName);

    tr.appendChild(td0);
    tr.appendChild(td1);

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
        var typeName = document.getElementById("typeName" + i);
        try {
            if (!typeName.value) {
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

