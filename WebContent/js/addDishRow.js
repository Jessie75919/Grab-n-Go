var addBtn = document.getElementById("addMenu");
var table = document.getElementById("menuTable");
var countVar = document.getElementById("count");
var form = document.getElementById("theForm");
var storeName = document.getElementById("storeName").value;
var count = 0;
var typeArr = [];

var xhr = new XMLHttpRequest();
//alert(storeName);
xhr.open("GET", "findProductType.do?name=" + storeName, true);
xhr.send();

xhr.onreadystatechange = function() {
	if (xhr.readyState == 4 && xhr.status == 200) {
		// alert("in AJAX");
		typeArr = JSON.parse(xhr.responseText);
		// alert(typeArr);
	}
}


function addBtnFunc() {
	count++;
	countVar.value = count;
	// alert("countVar.value = " +countVar.value);
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
	dishType.style.width = "150px";

	for (var i = 0; i < typeArr.length; i++) {
		var option = new Option(typeArr[i].prod_typeName);
		dishType.options[dishType.options.length] = option;
	}

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
	dishImage.setAttribute("id", "file" + count);

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




addBtn.onclick = 
function() {
	count++;
	countVar.value = count;
	// alert("countVar.value = " +countVar.value);
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
	dishType.style.width = "150px";

	for (var i = 0; i < typeArr.length; i++) {
		var option = new Option(typeArr[i].prod_typeName);
		dishType.options[dishType.options.length] = option;
	}

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
	dishImage.setAttribute("id", "file" + count);

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

$('#addHelper').click(function () { 
	for(var i=0;i<9;i++){
		addBtnFunc();
	}
});




$('#helper').click(function () { 
	$('#dishName1').val('大雞排');
	$('#dishName2').val('脆皮雞排');
	$('#dishName3').val('無骨雞排');
	$('#dishType1').val('主食');
	$('#dishType2').val('主食');
	$('#dishType3').val('主食');
	$('#dishPrice1').val('70');
	$('#dishPrice2').val('60');
	$('#dishPrice3').val('60');
	//-------------------------------------
	$('#dishName4').val('炸雞柳');
	$('#dishName5').val('檸檬雞');
	$('#dishName6').val('炸雞翅');
	$('#dishType4').val('配餐');
	$('#dishType5').val('配餐');
	$('#dishType6').val('配餐');
	$('#dishPrice4').val('40');
	$('#dishPrice5').val('50');
	$('#dishPrice6').val('35');
	//-------------------------------------
	$('#dishName7').val('甘梅薯條');
	$('#dishName8').val('甜不辣');
	$('#dishName9').val('杏鮑菇');
	$('#dishType7').val('點心');
	$('#dishType8').val('點心');
	$('#dishType9').val('點心');
	$('#dishPrice7').val('40');
	$('#dishPrice8').val('30');
	$('#dishPrice9').val('35');
	//-------------------------------------
});




function validateForm(event) {
	// alert("validateForm");
	event.preventDefault();
	var hasErr = false;
	var result = document.getElementById("showMsg");
	var errCount = 0;

	for (var i = 1; i <= count; i++) {
		var dishName = document.getElementById("dishName" + i);
		try {
			if (!dishName.value) {
				hasErr = true;
				alert('請刪除不必要的欄位喔~');
				errCount = 1;
				break;
			} 
		} catch (err) {
			errCount=0;
			// alert("not Found");
		}
	}

	if(errCount!=1){
		for (var i = 1; i <= count; i++) {
			var dishPrice = document.getElementById("dishPrice" + i);
			try {
				if (!dishPrice.value) {
					hasErr = true;
					alert('請記得填寫餐點價格喔');
					break;
				} 
			} catch (err) {
				// alert("not Found");
			}
		}

	}


	if (hasErr) {
		return false
	} else {
		form.submit();
	}

}
