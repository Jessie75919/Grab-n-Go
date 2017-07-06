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

        alert(score.value);
    })
}


// submit.onclick = function () {
//     alert("hihi");
//     var scoreValue = score.value;
//     var commentValue = comment.value;


//     var xhr = new XMLHttpRequest();
//     xhr.open("GET","rating.do?EvaRestName="+EvaRestName.value+"&EvaOrd_id="+EvaOrd_id.value+"&EvaUserName="+EvaUserName+"&score="+scoreValue+"&comment="+commentValue,true);
//     xhr.send();

// }