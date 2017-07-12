
//alert("helllo~")

var idcheck = document.getElementById("accountlink");
var account = document.getElementById("userId");


$(document).ready(function () {
	$('#ttt').hide();
	$("form").submit(function (e) {
		$('#ttt').show();
		// alert("Submitted");
	});
});



$('#helper').click(function () { 
	$('#userId').val("GrabnGo");
	$('#pswd').val("1111");
	$('#password2').val("1111");
	$('#name').val("LuLuMi");
	$('#address').val("台北科技大學-光華館");
	$('#tel').val("0911123456");
	$('#eMail').val("javazh005@gmail.com");
	$('#birthday').val("1986-07-26");
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