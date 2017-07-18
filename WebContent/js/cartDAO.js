var total = document.getElementById("subtotal");

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
                    var counts = document.getElementsByClassName("count");
                    if (counts.length == 0) {
                        var xhr_delSession = new XMLHttpRequest();
                        xhr_delSession.open("GET", "ModifyOrderItem.do?cmd=delOrderRest&proId=0&itemNote=", true);
                        xhr_delSession.send();

                        xhr_delSession.onreadystatechange = function () {

                            if (xhr_delSession.readyState == 4 && xhr_delSession.status == 200) {
                                var msg = JSON.parse(xhr_delSession.responseText)
                                if (msg == "sessionClear") {
                                    window.location.href = "../indexA.jsp";
                                    return;

                                }
                            }
                        }

                    }
                    getSubtotal();
                }
            }
        }

    }
}

function modifyAmount(proId, itemNote) {
    var count = document.getElementById("count" + proId + itemNote).value;
    getSubtotal();
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


    // result [0]是否重複  / [1]重複的位置 / [2]重複加起來的值 / [3]出現重複的全部位置
    var result = isExist(notes, newNote);

    // 如果有重複 -> 合併變成一欄
    if (result[0] == 1) {
        // alert("find the same one!!, it's position = " + result[1]);
        var repeatNum = result[3]; //出現重複的全部位置
        notes[repeatNum[1]].parentNode.parentNode.parentNode.removeChild(e.parentNode.parentNode);
        notes[repeatNum[0]].previousElementSibling.firstElementChild.value = result[2]; // 數量

        // 更新數量
        var newCount = result[2]; // 數量
        var note = notes[repeatNum[0]].value; // 備註
        // var proId = notes[repeatNum[0]].previousElementSibling.firstElementChild.id;

        var xhr_mod = new XMLHttpRequest();
        xhr_mod.open("GET", "ModifyOrderItem.do?cmd=modAcount&proId=" + proId + "&itemNote=" + note + "&count=" + newCount, true);
        xhr_mod.send();

        // 刪除有舊的備註那個品項
        var xhr_del = new XMLHttpRequest();
        xhr_del.open("GET", "ModifyOrderItem.do?cmd=del&proId=" + proId + "&itemNote=" + itemNote, true);
        xhr_del.send();

        getSubtotal();
    } else {
        // e.previousElementSibling.firstElementChild.value  = result[2];
        var xhr_modNote = new XMLHttpRequest();
        xhr_modNote.open("GET", "ModifyOrderItem.do?cmd=modNote&proId=" + proId + "&itemNote=" + itemNote + "&newNote=" + newNote, true);
        xhr_modNote.send();

        getSubtotal();
    }
}

function getSubtotal() {
    var counts = document.getElementsByClassName("count");
    var prices = document.getElementsByClassName("price");
    var subtotal = 0;
    for (var i = 0; i < counts.length; i++) {
        priceStr = prices[i].innerText;
        price = priceStr.substring(3);
        subtotal += (counts[i].value * price);
    }

    total.innerText = "總計：NT$" + subtotal + "元";
}


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
    return result;
}

window.onload = function () {
    $('#validMsg').css("display", "none");
    $('#ttt').hide();

    $('#validBth').click(function () {
        $('#ttt').show();
    });


}



function validCheck(event) {

    event.preventDefault();
    var hasErr = true;


    var user = $('#username').val();

    var xhr = new XMLHttpRequest();
    xhr.open("GET", "../checkValidate.do?mode=1&user=" + user, true);
    xhr.send();

    xhr.onreadystatechange = function () {
        if (xhr.readyState == 4 && xhr.status == 200) {
            var data = JSON.parse(xhr.responseText);
            // alert('data = ' + data);
            if (data == "NotValid") {
                $('#validMsg').css({
                    "color": "#fff",
                    "text-align": "center",
                    "background": "rgba(235,80,60,0.8)",
                    "width": "600px",
                    "padding": "20px",
                    "position": "fixed",
                    "top": "120px",
                    "left": "50%",
                    "border-radius": "5px",
                    "margin-left": "-300px",
                    "z-index": "99999"
                }).fadeIn();
                $('#validMsg a').css({
                    "text-decoration": "none",
                    "color": "rgb(235,80,60)",
                    "border-radius": "20px",
                    "margin": "10px 2px 0px 2px",
                    "display": "inline-block",
                    "width": "100px",
                    "padding": "3px 10px",
                    "background": "#fff"
                });

                hasErr = true;
            } else {
                hasErr = false;
            }
        }
    }



    setTimeout(function () {
        // alert('hasErr = ' + hasErr);
        if (hasErr) {
            return false;
        } else {
            var form = document.getElementById("theForm");
            form.submit();
        }
    }, 100);



}






function closePanel() {
    $('#validMsg').fadeOut();
}


