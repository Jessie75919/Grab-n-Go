<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <title> WELCOME GrabAndGo</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" type="text/css">
  <link rel="stylesheet" href="../css/Signup.css" type="text/css"> 
  
</head>

<body onLoad="setFocusToUserId()">
<div id="content">
  <div class="py-5 section text-center">
    <div class="container">
      <div class="row">
      <!-- Logo -->
        <div class="col-md-12">
        		<img width="400" src="../images/share/logo.svg">
        	
         <!--  <h1 class="text-gray-dark">Logo</h1> -->
        </div>
      </div>
    </div>
  </div>
  <!-- 顯示程式執行後的訊息 -->
  <span><font size="-1" color="FF0000"> ${MsgMap.InsertNG}${MsgMap.errorSaveData}> </font>
  </span>
</div>
  <div class="py-5">
    <div class="container">
      <div class="row">
        <div class="col-md-6"></div>
        <div class="col-md-6"></div>
      </div>
      <div class="row">
        <div class="col-md-4"></div>
        <div class="col-md-4">
        <!-- 註冊介面開始 -->
          <form method="POST" id="register.do" class="w-100">
            <button type="submit" class="btn btn-block text-gray-dark btn-primary my-2">Sign up with Google</button>
            <!-- 註冊帳號 -->
            <div class="form-group my-0">
            	 <label>Username</label>
              <input type="text" name="mid" value = "${param.mid}" id="mid" class="form-control" placeholder=""> 
            	  <a href='#' id='accountLink' style='font-size: 10pt;'> check username availablity</a>
            <span id="midResult" style = "height:10px;" ></span>
            </div>
            <!-- 註冊密碼 -->
            <div class="form-group my-0"> <label>Password</label>
              <input type="password" name= "password" value= "${param.password}" id="password" class="form-control" placeholder="Password"> 
            	  <span id = "passwordResult" style = "height:10px;"></span>
            </div>
            <!-- 註冊Email -->
            <div class="form-group my-0"> <label>Email</label>
              <input type="email" name="eMail" value="${param.eMail}" id="eMail" class="form-control" placeholder="example@gmail.com"> 
            	  <span id="eMailResult" style = "height:10px;"></span>
            </div>
            <!-- 驗整碼確認 -->
            <div class="form-group w-75 my-0"><label class="w-100">I am not Robot! Enter the text:</label>
              <input type="text" name="verf" value="${param.verf}" id="verf" class="form-control" placeholder="Text"> 
            <!-- 驗整碼輸入結果 -->
            	<span id="verfResult" style = "height:10px;"></span>
            </div>
            
            <!-- 註冊送出按鈕 -->
            <button type="submit" class="btn btn-block my-2 text-white btn-success">Sign up</button>
          </form>
          <!-- 錯誤訊息顯示 -->
          <font size="-1" color="#FF0000">${msgErr.idRepeat}${magErr.errorIDDup}</font>
        </div>    
        <!-- 餐廳資訊 -->
        <div class="col-md-4">
          <h2 class="my-2">Restaurant Info</h2>
          <form method="POST" id="register.do" class="w-100">
          	<!-- 餐廳名稱 -->
            <div class="form-group"> <label>Name of Restaurant</label>
              <input type="text" name="StoreName" value="${param.StoreName}" id="StoreName" class="form-control" placeholder=""> 
              <span id="StoreNameResult" style="height:10px;"></span>
            </div>
            <!-- 餐廳類型 -->
            <div class="form-group">
            <label name= "StoreType" id="StoreType">Cuisine type</label>
	            <select class="form-control">
	            		<option value="台式餐廳">台式餐廳</option>
	            		<option value="日式餐廳">日式餐廳</option>
	            		<option value="韓式餐廳">韓式餐廳</option>
	            		<option value="美式餐廳">美式餐廳</option>
	            		<option value="義式餐廳">義式餐廳</option>
	            		<option value="法式餐廳">法式餐廳</option>
	            		<option value="泰式餐廳">泰式餐廳</option>
	            		<option value="越式餐廳">越式餐廳</option>
	            	</select>
            	</div>
            	<!-- 餐廳分店 -->
            <div class="form-group"> <label>Store Branch</label>
              <input type="text" name="branch" value="${param.branch}" id="branch" class="form-control" placeholder="Password"> 
            </div>
            	<!-- 餐廳地址 -->
            <div class="form-group"><label>Address</label>
              <input type="text" name="address" value="${param.address}" id="address" class="form-control" placeholder="">
            	  <span id="addressResult" style="height:10px;"></span>
            </div>
            <!-- 餐廳聯絡電話 -->
            <div class="form-group"><label>Contact phone number</label>
              <input type="text" name="tel" value="${param.tel}" id="tel" class="form-control" placeholder="Text">
            	  <span id="telResult" style="height:10px;"></span>
            </div>
            <!-- 餐廳負責人 -->
            <div class="form-group"><label>Owner of Restaurant</label>
              <input type="text" name="owner" value="${param.owner}" id="owner" class="form-control" placeholder="MR.  /  MS.">
              <span id="ownerResult" style="height:10px;"></span>
            </div>
            <!-- 餐廳網站link -->
            <div class="form-group"><label>Official Website</label>
              <input type="text" name="url" value="${param.url}" id="url" class="form-control" placeholder="http://">
            </div>
          </form>
          
          <!-- 經緯度 -->
          <input type="text" name="langitude" style="display:none;" value="" id="langitude">
          <input type="text" name="latitude" style="display:none;" value="" id="latitude">
        </div>
      </div>
      <div class="row">
        <div class="col-md-12"></div>
      </div>
    </div>
  </div>
  
  <script type="text/javascript"
		  src="http://maps.google.com/maps/api/js?libraries=places&key=AIzaSyARdaz9WnSdDiJopjiTUy59yhcR8Cfn_7k"></script>
  <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js"></script>
  <script src="https://pingendo.com/assets/bootstrap/bootstrap-4.0.0-alpha.6.min.js"></script>
  <script type="text/javascript">
  <!-- 由<body>的onLoad事件處理函數觸發此函數 -->
  
  function setFocusToUserId() {
  	document.forms[0].mid.focus(); // 將游標放在mid欄位內
  }
  
//address to LatLng -------------------------------------
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
  var StoreType = document.getElementById("StoreType");
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
//   		addressToLanlng(address.value);
  		// mainBanner = document.getElementById("mainBanner");
  		// logo = document.getElementById("logo");
  		// coverImg = document.getElementById("coverImg");
  		//-----------------------

  		if (!account.value) {
  			midResult.innerHTML = "<font color = 'red' size ='-2'>請輸入帳號</font>";
  			hasErr = true;
  		} else {
  			// if (checkRepeatUser(account.value)==true) {
            //     console.log("checkRepeatUser(account.value)" + checkRepeatUser(account.value));
  			// 	alert("after checkRepeatUser");
  			// 	alert(checkRepeatUser(account.value));
  				midResult.innerHTML = "";
  			//     console.log("hasErr : account" + hasErr );
  			// } else {
            //     console.log("checkRepeatUser(account.value) = false" );
  			// }
  		}

  		//------------------------------

  		if (!password.value) {
  			passwordResult.innerHTML = "<font color = 'red' size ='-2'>請輸入密碼</font>";
  			hasErr = true;
  		} else
  			passwordResult.innerHTML = "";
            console.log("hasErr : password" + hasErr );
  		//------------------------------
  		if (!password2.value) {
  			password2Result.innerHTML = "<font color = 'red' size ='-2'>請輸入確認密碼</font>";
  			hasErr = true;
  		} else if (!(password2.value == password.value)) {
  			password2Result.innerHTML = "<font color = 'red' size ='-2'>與之前密碼不相符</font>";
  			hasErr = true;
  		} else
            console.log("hasErr : password2" + hasErr );
  			password2Result.innerHTML = "";
  		//------------------------------
  		if (!StoreName.value) {
  			StoreNameResult.innerHTML = "<font color = 'red' size ='-2'>請輸入店家名稱</font>";
  			hasErr = true;
  		} else
            console.log("hasErr : StoreName" + hasErr );
  			StoreNameResult.innerHTML = "";
  		//------------------------------
  		if (!address.value) {
  			addressResult.innerHTML = "<font color = 'red' size ='-2'>請輸入店家住址</font>";
  			hasErr = true;
  		} else {
            console.log("hasErr : address" + hasErr );
  			addressResult.innerHTML = "";
  		}
  		//------------------------------
  		if (!tel.value) {
  			telResult.innerHTML = "<font color = 'red' size ='-2'>請輸入店家電話</font>";
  			hasErr = true;
  		} else
            console.log("hasErr : tel" + hasErr );
  			telResult.innerHTML = "";
  		//------------------------------
  		if (!eMail.value) {
  			eMailResult.innerHTML = "<font color = 'red' size ='-2'>請輸入店家E-mail</font>";
  			hasErr = true;
  		} else
            console.log("hasErr : eMail" + hasErr );
  			eMailResult.innerHTML = "";
  		//------------------------------
  		if (!owner.value) {
  			ownerResult.innerHTML = "<font color = 'red' size ='-2'>請輸入店家負責人</font>";
  			hasErr = true;
  		} else
            console.log("hasErr : owner" + hasErr );
  			ownerResult.innerHTML = "";
  		//------------------------------
  		if (hasErr) {
  			return false;
  		}
  	}

  	//----------------------------------------------------------
  	account.value = "Jessie75919";
  	password.value = "a1234";
  	password2.value = "a1234";
  	StoreName.value = "subway";
  	address.value = "台北市中山區長安東路二段92號1樓";
  	tel.value = "(02)2517-7667";
  	eMail.value = "jessie75919@gmail.com";
  	owner.value = "謝阿哲";
  	url.value = "http://www.twsubway.com/tw/subway_store_findastore.php";

  }
	</script>
</body>

</html>