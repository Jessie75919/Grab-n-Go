
//由<body>的onLoad事件處理函數觸發此函數
function setFocusToUserId() {
	document.forms[0].mid.focus(); // 將游標放在mid欄位內
}

// address to LatLng -------------------------------------
function addressToLanlng(addr) {
	var geocoder = new google.maps.Geocoder();
	geocoder.geocode({
		"address" : addr
	}, function(results, status) {
		if (status == google.maps.GeocoderStatus.OK) {
			langitude.value = results[0].geometry.location.lng();
			latitude.value = results[0].geometry.location.lat();
			alert(langitude.value);
			alert(latitude.value);
		}
	});
	callback();
	return false ;
}

// checkReapeatUser -------------------------------------

//----------------------------------------------------------
var sendBtn = document.getElementById("holyshit");
var resetBtn = document.getElementById("cancel");
var idcheck = document.getElementById("accountlink");
var account = document.getElementById("mid");
var password = document.getElementById("password");
var password2 = document.getElementById("password2");
var StoreName = document.getElementById("StoreName");
var branch = document.getElementById("branch");
var address = document.getElementById("address");
var tel = document.getElementById("tel");
var eMail = document.getElementById("eMail");
var owner = document.getElementById("owner");
var url = document.getElementById("url");
var langitude = document.getElementById("langitude");
var latitude = document.getElementById("latitude");

window.onload = function() {
	//----------------------------------------------------------

	idcheck.onclick = function() {
		var applyAcc = account.value;
		addressToLanlng(address.value);
		alert(address.value);
		if (!applyAcc) {
			div.innerHTML = "<font color='blue' size='-2'>請輸入帳號</font>";
			return;
		}
		var checkId_xhr = new XMLHttpRequest();
		checkId_xhr.open("GET", "Idcheck.do?id=" + applyAcc, true);
		checkId_xhr.send();

		//----------------------------

		checkId_xhr.onreadystatechange = function() {
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

	//----------------------------------------------------------


	sendBtn.onclick = function() {
		var hasErr = false;
		var midResult = document.getElementById("midResult");
		var passwordResult = document.getElementById("passwordResult");
		var password2Result = document.getElementById("password2Result");
		var StroeNameResult = document.getElementById("StroeNameResult");
		var addressResult = document.getElementById("addressResult");
		var telResult = document.getElementById("telResult");
		var eMailResult = document.getElementById("eMailResult");
		var ownerResult = document.getElementById("ownerResult");
		addressToLanlng(address.value);
		// mainBanner = document.getElementById("mainBanner");
		// logo = document.getElementById("logo");
		// coverImg = document.getElementById("coverImg");
		//-----------------------

		if (!account.value) {
			midResult.innerHTML = "<font color = 'red' size ='-2'>請輸入帳號</font>";
			hasErr = true;
		} else {
			if (checkRepeatUser(account.nodeValue)) {
				alert(checkRepeatUser(account.nodeValue));
				passwordResult.innerHTML = "";
			} else {
				hasErr = true;
			}
		}

		//------------------------------

		if (!password.value) {
			passwordResult.innerHTML = "<font color = 'red' size ='-2'>請輸入密碼</font>";
			hasErr = true;
		} else
			passwordResult.innerHTML = "";
		//------------------------------
		if (!password2.value) {
			password2Result.innerHTML = "<font color = 'red' size ='-2'>請輸入確認密碼</font>";
			hasErr = true;
		} else if (!(password2.value == password.value)) {
			password2Result.innerHTML = "<font color = 'red' size ='-2'>與之前密碼不相符</font>";
			hasErr = true;
		} else
			password2Result.innerHTML = "";
		//------------------------------
		if (!StoreName.value) {
			StoreNameResult.innerHTML = "<font color = 'red' size ='-2'>請輸入店家名稱</font>";
			hasErr = true;
		} else
			StoreNameResult.innerHTML = "";
		//------------------------------
		if (!address.value) {
			addressResult.innerHTML = "<font color = 'red' size ='-2'>請輸入店家住址</font>";
			hasErr = true;
		} else {
			addressResult.innerHTML = "";

			addressToLanlng(address.value);
		}
		//------------------------------
		if (!tel.value) {
			telResult.innerHTML = "<font color = 'red' size ='-2'>請輸入店家電話</font>";
			hasErr = true;
		} else
			telResult.innerHTML = "";
		//------------------------------
		if (!eMail.value) {
			eMailResult.innerHTML = "<font color = 'red' size ='-2'>請輸入店家E-mail</font>";
			hasErr = true;
		} else
			eMailResult.innerHTML = "";
		//------------------------------
		if (!owner.value) {
			ownerResult.innerHTML = "<font color = 'red' size ='-2'>請輸入店家負責人</font>";
			hasErr = true;
		} else
			ownerResult.innerHTML = "";
		//------------------------------
		if (hasErr) {
			return false;
		}
		//----------------------------------------------------------

			// client send a request to server
			var xhr1 = new XMLHttpRequest();
			xhr1.open("POST", "StoreRegister.do", true);
			xhr1.setRequestHeader("Content-Type","application/x-www-form-urlencoded");


			alert("mid=" + account + "&password=" + password + "&password="
					+ +"&StoreName=" + StoreName + "&branch=" + branch
					+ "&address=" + address + "&tel=" + tel + "&eMail=" + eMail
					+ "&owner=" + owner + "&url=" + url + "&langitude="
					+ langitude + "&latitude=" + latitude);

			xhr1.send("mid=" + account + "&password=" + password + "&password="
					+ +"&StoreName=" + StoreName + "&branch=" + branch
					+ "&address=" + address + "&tel=" + tel + "&eMail=" + eMail
					+ "&owner=" + owner + "&url=" + url + "&langitude="
					+ langitude + "&latitude=" + latitude);
			
			


		// server responses clinet
		// xhr1.onreadystatechange = function(){

		//     // the situation needs to handle with
		//     if(xhr1.readyState == 4 && xhr1.status ==200){
		//         // got package from JSON and tranform to JS obj
		//         result = JSON.parse(xhr1.responseText);

		//     }

		// }    
	}

	//----------------------------------------------------------
	account.value = "Jessie75919";
	password.value = "a1234";
	password2.value = "a1234";
	StoreName.value = "subway";
	address.value = "台北市中山區長安東路二段92號1樓";
	tel.value = "(02)2517-7667";
	eMail.value = "jessie75919@gmail.com";
	owner.value = "Jessie75919";
	url.value = "http://www.twsubway.com/tw/subway_store_findastore.php";

}




function checkRepeatUser(usrname) {
	alert("here is checkRepeatUser");
	if (!usrname) {
		div.innerHTML = "<font color='blue' size='-2'>請輸入帳號</font>";
		return;
	}
	var checkId_xhr = new XMLHttpRequest();
	checkId_xhr.open("GET", "Idcheck.do?id=" + usrname, true);
	checkId_xhr.send();

	//----------------------------

	checkId_xhr.onreadystatechange = function() {
		if (checkId_xhr.readyState == 4 && checkId_xhr.status == 200) {
			var result = JSON.parse(checkId_xhr.responseText);
			if (result == "OK") {
				midResult.innerHTML = "<font color = 'green' size ='-2'>帳號可以使用喔</font>";
				return true;
			} else if (result == "NO") {
				midResult.innerHTML = "<font color = 'red' size ='-2'>帳號有人使用囉</font>";
				hasErr = true;
				return false;
			}
		}

	}
	return false;
}

//----------------------------------------------------------