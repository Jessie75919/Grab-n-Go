
//alert("helllo~")

var idcheck = document.getElementById("accountlink");
var account = document.getElementById("userId");


$(document).ready(function () {
	$('#ttt').hide();
});



// $('#sub').on('submit', function (e) {
	
// });

$("#sub").submit(function(e){
	$('#ttt').show();
  alert("Submitted");
});



idcheck.onclick = function () {
		var applyAcc = account.value;
		if (!applyAcc) {
			midResult.innerHTML = "請輸入帳號";
			return;
		}
		var checkId_xhr = new XMLHttpRequest();
		checkId_xhr.open("GET", "userIdcheck.do?id=" + applyAcc, true);
		checkId_xhr.send();

		//----------------------------

		checkId_xhr.onreadystatechange = function () {
			if (checkId_xhr.readyState == 4 && checkId_xhr.status == 200) {
				var result = JSON.parse(checkId_xhr.responseText);
				if (result == "OK") {
					midResult.innerHTML = "帳號可以使用喔";
				} else if (result == "NO") {
					midResult.innerHTML = "帳號有人使用囉";
					hasErr = true;
				}
			}
		}
	};