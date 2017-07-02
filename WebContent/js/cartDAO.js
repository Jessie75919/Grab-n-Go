

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
    // alert(e.value); // newNote
    // alert(itemNote); // oldNote
    // alert(proId);

    var notes = document.getElementsByClassName("note");
    // alert("previousElementSibling = "+notes[0].previousElementSibling.firstElementChild)


    var result = isExist(notes,newNote);



    // 如果有重複 -> 合併變成一欄
    if(result[0]==1){
        alert("find the same one!!, it's position = " + result[1] );
        // 找到新的包含 <textarea> 的count值

        var newCountStr = e.previousElementSibling.firstElementChild.value;
        var newCount = parseInt(newCountStr);
        alert("newCount ="+newCount);
        // 找到包含相同備註的那個<textarea> 的count值 ，並且加上新的數量
        var oldCountStr = notes[result[1]].previousElementSibling.firstElementChild.value;
        alert("oldCountStr = "+oldCountStr);
        var oldCount = parseInt(oldCountStr) ;
        oldCount += newCount ;
        alert("oldCount = "+oldCount);
        oldCountStr.value = oldCount;
        // e.parentNode.parentNode.parentNode.removeChild(e.parentNode.parentNode);
    }

    // var xhr_mod = new XMLHttpRequest();
	// 	xhr_mod.open("GET", "ModifyOrderItem.do?cmd=modNote&proId=" + proId + "&itemNote=" + itemNote+"&newNote="+newNote, true);
	// 	xhr_mod.send();



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


}

// 如何扣除自己之後再判斷是否有重複 ??

function isExist(notes,newNote) {
    var result = [];
    var allCount = 0;
    result[0] = 0;
        console.log("newNote = " +newNote);
    for(var i=0;i<notes.length;i++){
        console.log(i+" = " +notes[i].value);
        if(notes[i].value == newNote){
            var countStr = notes[i].previousElementSibling.firstElementChild.value;
            count = parseInt(countStr);
            allCount+=count;
            result[0] = 1;
            result[1] = i;
            result[2] = allCount;
            // break;
        }
    }
    console.log("count = "+allCount);
    return result;
}