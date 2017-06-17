
alert("helllo~")

var idcheck = document.getElementById("accountlink");
var account = document.getElementById("userId");

idcheck.onclick = function () {
		var applyAcc = account.value;
		if (!applyAcc) {
			midResult.innerHTML = "<font color='blue' size='-2'>請輸入帳號</font>";
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
					midResult.innerHTML = "<font color = 'green' size ='-2'>帳號可以使用喔</font>";
				} else if (result == "NO") {
					midResult.innerHTML = "<font color = 'red' size ='-2'>帳號有人使用囉</font>";
					hasErr = true;
				}
			}
		}
	};