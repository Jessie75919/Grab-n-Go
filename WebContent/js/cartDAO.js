

function deleteF(proId, itemNote) {
    var cartLeft = document.getElementById("cartLeft");
    var wholeItem = document.getElementById("List" + proId);

    if (confirm("確認要刪除嗎??")) {

        var xhr_del = new XMLHttpRequest();
        xhr_del.open("GET", "ModifyOrderItem.do?cmd=del&proId=" + proId + "&itemNote=" + itemNote, true);
        xhr_del.send();

        xhr_del.onreadystatechange = function () {
            if (xhr_del.readyState == 4 && xhr_del.status == 200) {
                // alert("in AJAX");
                var txt = JSON.parse(xhr_del.responseText);
                // alert(txt);
                if (txt == "刪除成功") {
                    cartLeft.removeChild(wholeItem);
                }
            }
        }
    }
}

function modifyAmount(proId, itemNote) {
    var count = document.getElementById("count" + proId + itemNote).value;

    var xhr_mod = new XMLHttpRequest();
    xhr_mod.open("GET", "ModifyOrderItem.do?cmd=modAcount&proId=" + proId + "&itemNote=" + itemNote + "&count=" + count, true);
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

function modifyNote(e, proId, itemNote) {
    var newNote = e.value;
    // alert(e.value); // newNote
    // alert(itemNote); // oldNote
    // alert(proId);

    var notes = document.getElementsByClassName("note");
    // alert("previousElementSibling = "+notes[0].previousElementSibling.firstElementChild)


    var result = isExist(notes, newNote);



    // 如果有重複 -> 合併變成一欄
    if (result[0] == 1) {
        alert("find the same one!!, it's position = " + result[1]);

        var repeatNum = result[3];
        var delTarget = 0;
        for (var i = 0; i < repeatNum.length; i++) {

            if (result[1] != repeatNum[i]) {
                delTarget = i;
            }
        }

        notes[delTarget].parentNode.parentNode.parentNode.removeChild(e.parentNode.parentNode);

        var target = document.getElementById("count" + proId + itemNote)
        target.value = result[2];
        // e.previousElementSibling.firstElementChild.value  = result[2];
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

function isExist(notes, newNote) {
    var result = [];
    var allCount = 0;
    var ct = 0;
    result[0] = 0;
    var repeatNum = [];
    console.log("newNote = " + newNote);
    for (var i = 0; i < notes.length; i++) {
        console.log(i + " = " + notes[i].value);
        if (notes[i].value == newNote) {
            var countStr = notes[i].previousElementSibling.firstElementChild.value;
            count = parseInt(countStr);
            allCount += count;
            ct++;
            result[0] = 1;
            result[1] = i;
            result[2] = allCount;
            repeatNum.push(i);
            result[3] = repeatNum;
            // break;
        }
    }
    if (ct == 1) {
        // 扣除算到自己的那一次
        result[0] = 0;
    }
    console.log("count = " + allCount);
    return result;
}