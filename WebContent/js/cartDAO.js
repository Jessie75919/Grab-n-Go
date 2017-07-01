


function deleteF(proId, itemNote) {

    var cartLeft = document.getElementById("cartLeft");
    var wholeItem = document.getElementById("List"+proId);

	var txt;
	if (confirm("確認要刪除嗎??")) {

		var xhr_del = new XMLHttpRequest();
		xhr_del.open("GET", "ModifyOrderItem.do?cmd=del&proId=" + proId + "&itemNote=" + itemNote, true);
		xhr_del.send();

		// xhr_del.onreadystatechange = function () {
		// 	if (xhr_del.readyState == 4 && xhr_del.status == 200) {
		// 		// alert("in AJAX");
		// 		txt = JSON.parse(xhr_del.responseText);
		// 		alert(tex);
		// 	}
		// }

        cartLeft.removeChild(wholeItem);

	} 
}