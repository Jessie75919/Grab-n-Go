<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>加入會員</title>
<style type="text/css">

body {
	background-attachment: fixed;
	background-color: #EBFFEB;
	background-repeat: no-repeat;
	background-position: 20px 50px;
}
.myBorder {
	color:#FFFF-2;
	border: thin dotted #FFFFFF;
}
h1 {
	font-family: "標楷體", "新細明體", sans-serif;
	font-size: 24px;
}
.formBkgnd {
	color: #FFFFFF;
	background-color: #666666;
}
label {
	float:left;
	width:150px;
	font-weight:bold;
	color:#000000;
	margin-top:10px;
	margin-bottom:2px;
	margin-right:10px;
	text-align: right;
}

br {
	clear:both;
}
.fieldWidth {
    margin-top:10px;
	margin-bottom: 2px;
	width: 200px;
	background:#F6E4-27;
	font-size:1.1em;
}
/* 設定字體大小 */
.fontSize {
	font-size:1.1em;
}

#main {
    position:relative;
	left:70px;
	width:600px;
	height:543px;	
	top: 0px;
	z-index:2;
	font-size:0.-2em; 
}
/* 主要內容的區塊 */
#content {
  width: 700px ;
  margin-left: auto ;
  margin-right: auto ;
}
/* 設定傳送鈕的樣式 */
#submit {
	width:64px;
	height:30px;
	font-size:1.2em
	color:#FFFFFF;
	margin-right:1.5em;
	border-width:2px;
	border-color: #FFEDAF #B2A268 #B2A268 #FFEDAF;
	background:#A-2A-2A-2;
}
/* 設定取消鈕的樣式 */
#cancel {
	width:64px;
	height:30px;
	font-size:1.2em
	color:#ffffff;
	border-width:2px;
	border-color: #FFEDAF #B2A268 #B2A268 #FFEDAF;
	background:#a-2a-2a-2;
}

#errorMsg {
    position:relative;
    top:0px; 
    left:0px;    
	color:#FF0000;
	font-size:0.8em;
}

</style>

</head>
<body onLoad="setFocusToUserId()" >
<c:set var="funcName" value="REG" scope="session"/>
<!-- 引入共同的頁首 -->
<!--<jsp:include page="/fragment/top.jsp" />-->
  <div id="content"> 
  <Table width="700" border='2' cellspacing="0" bgColor='#E7CDFF'>
     <TR height="60" >
         <TD>
         <TABLE cellspacing="1" >
         <TR>
             <TD width="680" colspan='3' align="center" >
                 <Font color="#006600" size='5' face="標楷體">${AppName}</Font>
             </TD>
         </TD>
         </TR>
         <TR>
             <TD width="240" ></TD>
             <TD width="200"  align="center">
                 <Font color="#006600" size='4' face="標楷體">加入會員</Font>
             </TD>
         <!-- 此區塊顯示程式執行後的訊息 -->
             <TD width="240" aligh="left"><font size="-1" color="#FF0000">
                 ${MsgMap.InsertNG}${MsgMap.errorSaveData}</font>
             </TD>
         </TR>         
         </TABLE>
         </TD>
     </TR>
     <TR><TD colspan="3">


<!--  ENCTYPE="multipart/form-data"  -->
  <form method="POST" action="<c:url value='StoreRegister.do' />"  id="register.do" >
  <!--<fieldset style='display: inline-block;'>-->
      
      <label class="fontSize" >帳號：</label> 
      <input type="text" name="mid" value="${param.mid}" id="mid"  class="fieldWidth" style="width: 180px;">
      <a href='#' id='accountlink' style='font-size: 10pt;'>檢查帳號</a>
      <span id="midResult" style="heigth:10px;" ></span>
     
      <br/>
     
      <label class="fontSize" >密碼：</label>
      <input type="password" name="password" value="${param.password}" id="password" class="fieldWidth" style="width: 200px;">
      <span id="passwordResult" style="heigth:10px;" ></span>
     
      <br/>
      
      <label class="fontSize" >密碼確認：</label>
      <input type="password" name="password2" value="${param.password2}" id="password2"   class="fieldWidth" style="width: 200px;">
      <span id="password2Result" style="heigth:10px;" ></span>
     
      <br/>
      
      <label class="fontSize" >店家名稱：</label>
      <input type="text" name="StoreName" value="${param.StoreName}" id="StoreName"  class="fieldWidth" style="width: 180px;">
      <span id="StoreNameResult" style="heigth:10px;" ></span>
      
      <br/>
      
      <label class="fontSize" >分店：</label>
      <input type="text" name="branch" value="${param.branch}" id="branch"   class="fieldWidth" style="width: 180px;">
      
      <br/>
      
      <label class="fontSize" >地址：</label>
      <input type="text" name="address" value="${param.address}" id="address"  class="fieldWidth" style="width: 320px;">
      <span id="addressResult" style="heigth:10px;" ></span>
     
      <br/>
      
      <label class="fontSize" >電話：</label>
      <input type="text"  name="tel" value="${param.tel}" id="tel"    class="fieldWidth" style="width: 120px;">
      <span id="telResult" style="heigth:10px;" ></span>
     
      <br/>
      
      <label class="fontSize" >電子郵件：</label>
	  <input type="text"  name="eMail" value="${param.eMail}" id="eMail"   class="fieldWidth" style="width: 300px;">
      <span id="eMailResult" style="heigth:10px;" ></span>
      
      <br/>
      
      <label class="fontSize" >店家負責人：</label>
      <input type="text" name="owner" value="${param.owner}" id="owner"   class="fieldWidth" style="width: 180px;">
      <span id="ownerResult" style="heigth:10px;" ></span>
     
      <br/>
      
      <label class="fontSize" >官方網站：</label>
      <input type="text" name="url" value="${param.url}" id="url"  class="fieldWidth" style="width: 180px;">
      

      <input type="text" name="langitude" style="display:none;" value="" id="langitude">
      <input type="text" name="latitude" style="display:none;" value="" id="latitude">
      
      <br/>
      
      <!--<label class="fontSize" >主頁面橫幅圖片：</label>
      <Input Type="file" size="40" class="fieldWidth" style="width: 480px;" id="mainBanner"  name="mainBanner"><BR>
      
      <br/>
      
      <label class="fontSize" >LOGO圖片：</label>
      <Input Type="file" size="40" class="fieldWidth" style="width: 480px;" id="logo"   name="logo"><BR>
      
      <br/>
      
      <label class="fontSize" >首頁顯示圖片：</label>
      <Input Type="file" size="40" class="fieldWidth" style="width: 480px;" id="coverImg"  name="coverImg"><BR>-->
      
      <br/>
      
      <div id="btnArea" align="center">
      	<button id='holyshit'>送出</button>
<!--          <input type="submit" name="holyshit" id="holyshit" value="註冊"/> -->
         <input type="reset" name="cancel" id="cancel" value="重填">
      </div>
      <br/>
</form>
<!--</fieldset>-->
</TD>
</TR>
</Table>
	   </div>
	<script src="../../js/jquery-2.2.3.min.js"></script>
    <!-- 	Here is the zone of javascript -->
    <script type="text/javascript"
	src="http://maps.google.com/maps/api/js?libraries=places&key=AIzaSyARdaz9WnSdDiJopjiTUy59yhcR8Cfn_7k"></script>
    
    <script>
        //由<body>的onLoad事件處理函數觸發此函數
        function setFocusToUserId() {
            document.forms[0].mid.focus(); // 將游標放在mid欄位內
        }
        
        
        // address to LatLng -------------------------------------
        function addressToLanlng(addr) {
        	var geocoder = new google.maps.Geocoder();
        	geocoder.geocode({"address":addr},
                function(results,status){
                    if(status == google.maps.GeocoderStatus.OK){
                        langitude.value = results[0].geometry.location.lng();
                        latitude.value = results[0].geometry.location.lat();
                        alert(langitude.value);
                        alert(latitude.value);
                    }
                }
        	);
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
            
            idcheck.onclick = function () {
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

            
            //----------------------------------------------------------

            // address.onblur = function(){
            // 		alert("address.nodeValue = " + address.nodeValue);
            //        addressToLanlng(address.nodeValue);
            // }

            sendBtn.onclick = function () {
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
                	if(checkRepeatUser(account.nodeValue)){
                        alert(checkRepeatUser(account.nodeValue));
                		passwordResult.innerHTML = "";
                	}else{
                        hasErr = true;
                    }
                }

                //------------------------------

                if (!password.value) {
                    passwordResult.innerHTML = "<font color = 'red' size ='-2'>請輸入密碼</font>";
                    hasErr = true;
                } else passwordResult.innerHTML = "";

                //------------------------------
                if (!password2.value) {
                    password2Result.innerHTML = "<font color = 'red' size ='-2'>請輸入確認密碼</font>";
                    hasErr = true;
                } else if (!(password2.value == password.value)) {
                    password2Result.innerHTML = "<font color = 'red' size ='-2'>與之前密碼不相符</font>";
                    hasErr = true;
                } else password2Result.innerHTML = "";

                //------------------------------
                if (!StoreName.value) {
                    StoreNameResult.innerHTML = "<font color = 'red' size ='-2'>請輸入店家名稱</font>";
                    hasErr = true;
                } else StoreNameResult.innerHTML = "";

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
                } else telResult.innerHTML = "";

                //------------------------------
                if (!eMail.value) {
                    eMailResult.innerHTML = "<font color = 'red' size ='-2'>請輸入店家E-mail</font>";
                    hasErr = true;
                } else eMailResult.innerHTML = "";

                //------------------------------
                if (!owner.value) {
                    ownerResult.innerHTML = "<font color = 'red' size ='-2'>請輸入店家負責人</font>";
                    hasErr = true;
                } else ownerResult.innerHTML = "";

                //------------------------------
                if (hasErr) {
                    return false;
                }

                //----------------------------------------------------------

                // client send a request to server
                var xhr1 = new XMLHttpRequest();
                xhr1.open("POST", "StoreRegister.do", true);
                xhr1.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
                alert("mid=" + account + "&password=" + password + "&password=" + +"&StoreName=" + StoreName + "&branch=" + branch + 
                        "&address=" + address + "&tel=" + tel + "&eMail=" + eMail + "&owner=" + owner + "&url=" + url + 
                        "&langitude=" + langitude + "&latitude=" + latitude);
                
                xhr1.send(
                    "mid=" + account + "&password=" + password + "&password=" + +"&StoreName=" + StoreName + "&branch=" + branch + 
                    "&address=" + address + "&tel=" + tel + "&eMail=" + eMail + "&owner=" + owner + "&url=" + url + 
                    "&langitude=" + langitude + "&latitude=" + latitude);


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

            checkId_xhr.onreadystatechange = function () {
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

    </script>
</body>
</html>