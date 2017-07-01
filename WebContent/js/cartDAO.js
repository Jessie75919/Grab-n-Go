

function deleteF(proId, itemNote) {
    var cartLeft = document.getElementById("cartLeft");
    var wholeItem = document.getElementById("List"+proId);

	if (confirm("確認要刪除嗎??")) {

		var xhr_del = new XMLHttpRequest();
		xhr_del.open("GET", "ModifyOrderItem.do?cmd=del&proId=" + proId + "&itemNote=" + itemNote, true);
		xhr_del.send();

		xhr_del.onreadystatechange = function () {
			if (xhr_del.readyState == 4 && xhr_del.status == 200) {
				// alert("in AJAX");
				var txt = JSON.parse(xhr_del.responseText);
				// alert(txt);
                if(txt == "刪除成功"){
                    cartLeft.removeChild(wholeItem);
                }
			}
		}
	} 
}

function modify(proId,itemNote) {
    var count = document.getElementById("count"+proId+itemNote).value;

    var xhr_mod = new XMLHttpRequest();
		xhr_mod.open("GET", "ModifyOrderItem.do?cmd=modAcount&proId=" + proId + "&itemNote=" + itemNote+"&count="+count, true);
		xhr_mod.send();

        /*
		xhr_mod.onreadystatechange = function () {
			if (xhr_mod.readyState == 4 && xhr_mod.status == 200) {
				// alert("in AJAX");
				var txt = JSON.parse(xhr_mod.responseText);
				// alert(txt);
                if(txt == "修改成功"){
                    
                }
			}
		}
        */
}

function modifyNote(e,proId,itemNote) {
    var newNote = e.value;
    alert(e.value); // newNote
    alert(itemNote); // oldNote
    alert(proId);

    var notes = document.getElementsByClassName("note");

    var result = isExist(notes,newNote);
    if(result[0]==1){
        // 找到新的包含 <textarea> 的count值
        var newCount = e.parentNode.firstChild.nextSibling.nextSibling.firstChild.value;
        alert(newCount)
        // 找到包含相同備註的那個<textarea> 的count值 ，並且加上新的數量
        var oldCount = notes[result[1]].previousSibling.firstChild.value += newCount;
        alert(oldCount)

    }




    // var items = document.getElementsByName("prod_"+proId+itemNote);
    // console.log("items.length = "+items.length);
    // if(items.length!=1){
    //     var fistCount = items[0].firstChild.nextSibling.nextSibling.firstChild.value;
    //     alert(fistCount)
    //     var secondCount = items[0].firstChild.nextSibling.nextSibling.firstChild.value;
    //     alert(secondCount)

    //     var totalCount = fistCount + secondCount;
    //     alert(totalCount);
    // }

    // var xhr_mod = new XMLHttpRequest();
	// 	xhr_mod.open("GET", "ModifyOrderItem.do?cmd=modNote&proId=" + proId + "&itemNote=" + itemNote+"&newNote="+newNote, true);
	// 	xhr_mod.send();

}

function isExist(notes,newNote) {
    var result = [] ;
    result[0] = 0;
    for(var i=0;i<notes.length;i++){
        if(notes[i].value == newNote){
            result[0] = 1;
            result[1] = i;
            break;
        }
    }
    return result;
}