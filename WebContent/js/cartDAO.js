


function deleteF(proId, itemNote) {
   // alert(me.id);
	var selDishName = delBtn.parentNode.nextSibling.firstChild.value;
	var selDishType = delBtn.parentNode.nextSibling.nextSibling.firstChild.value;
	// alert(selDishName);
	// alert(selDishType);
	var txt;
	var r = confirm("確認要刪除嗎??");
	if (r == true) {

		var xhr_del = new XMLHttpRequest();
		xhr_del.open("GET", "delProduct.do?dishName=" + selDishName + "&dishType=" + selDishType, true);
		xhr_del.send();

		xhr_del.onreadystatechange = function () {
			if (xhr_del.readyState == 4 && xhr_del.status == 200) {
				// alert("in AJAX");
				txt = JSON.parse(xhr_del.responseText);
				alert(tex);
			}
		}

		table.removeChild(delBtn.parentNode.parentNode);

	} else {
		// txt = "You pressed Cancel!";
	}
}