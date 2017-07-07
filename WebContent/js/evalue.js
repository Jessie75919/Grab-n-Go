var EvaRestName;
var EvaOrd_id;
// var EvaUserName;
var score = document.getElementById("score");
// var comment =document.getElementById("comment");
// var submit = document.getElementById("vvv");

function clikeMe(e) {

    // alert(e.title);
    var parent = e.parentNode.parentNode;
    // alert(parent.className);
    var getOrdId = parent.firstElementChild.innerText;
    // alert(getOrdId);
    var getRestId = parent.firstElementChild.nextSibling.nextSibling.nextSibling.nextSibling.value;
    // alert("id = "+getRestId);

    EvaRestId = document.getElementById("EvaRestId");
    EvaRestId.value = getRestId;
    // alert(EvaRestId.value);
    EvaOrd_id = document.getElementById("EvaOrd_id");
    EvaOrd_id.value = getOrdId;
    // alert(EvaOrd_id.value);
    score.value = 0 ;

}


var starArr = document.querySelectorAll('.icon-star');

for (var i = 0; i < 5; i++) {
    starArr[i].addEventListener('click', function () {
        for (var j = 0; j < 5; j++) {
            starArr[j].className = "icon-star";
        }
        // alert(this.id);
        score.value = this.id;

        for (var j = 0; j < score.value; j++) {
            starArr[j].className = "icon-star on";
        }

        // alert(score.value);
    });
}


$('#reset').bind('click', function () {
    for (var j = 0; j < 5; j++) {
        starArr[j].className = "icon-star";
    }
});

function words_deal() {
	var curLength = $("#comment").val().length;
	if (curLength > 100) {
		var num = $("#comment").val().substr(0, 75);
		$("#comment").val(num);
		alert("貼心小提醒 : 評論不可以超過75個字喔");
	} else {
		$("#textCount").text(5 - $("#TextArea1").val().length);
	}
}



function validate() {
    // alert(score.value);
    if(parseInt(score.value)==0 ){
         alert("stop1");
        $('#msg').html("貼心小提醒 : 請記得填寫評分喔");
         return false;
    }
    
}

// var resetBtn = document.querySelector('#reset');
// resetBtn.addEventListener('click',function () {
//      for (var j = 0; j < 5; j++) {
//             starArr[j].className = "icon-star";
//         }
// });


// submit.onclick = function () {
//     alert("hihi");
//     var scoreValue = score.value;
//     var commentValue = comment.value;


//     var xhr = new XMLHttpRequest();
//     xhr.open("GET","rating.do?EvaRestName="+EvaRestName.value+"&EvaOrd_id="+EvaOrd_id.value+"&EvaUserName="+EvaUserName+"&score="+scoreValue+"&comment="+commentValue,true);
//     xhr.send();

// }