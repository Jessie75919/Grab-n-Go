<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" type="text/css">
  <link rel="stylesheet" href="../css/_signup.css" type="text/css">
  <script src='https://www.google.com/recaptcha/api.js'></script>
  <title>Welcome GrabAndGo</title>
</head>
<jsp:useBean id="rtd" class="_01_Store_register.model.RestaurantTypeDAO" scope="page"/>
<body onload="setFocusToUserId()">
  <div class="py-5 section text-center">
    <div class="container">
      <div class="row">
        <div class="col-md-12">
          <!-- <h1 class="text-primary">Logo</h1> -->
          <img width="400" src="../images/share/logo.svg">
        </div>
      </div>
    </div>
  </div>
  <div class="py-5">
    <div class="container">
      <div class="row">
        <div class="col-md-12">
          <h1 class="text-center my-0"> Sign up As Restaurant </h1>
          <br> </div>
      </div>
      <div class="row">
        <div class="col-md-12">
          <!-- 註冊介面開始 -->
          <!-- <div> </div> -->
          <form  method="POST" action="<c:url value='StoreRegister.do' />"  id="theForm" 
          		onsubmit="return validateForm(event);">
            <div id="formLeft">
              <!-- Sign up with google -->
              <!--               <button type="submit" class="btn text-gray-dark btn-primary">Sign up with Google</button> -->
              <!--               <hr> -->
              <!-- 註冊帳號 -->
              <div class="form-group m-0"> <label>Username</label>
                <input type="text" name="mid" value="${param.mid}" id="mid" class="form-control" placeholder="Username">
                <a href="#" id="accountlink" style="font-size: 10pt;"> check username availablity</a> 
                <span id="midResult" style="height:10px;"></span> </div>
              <!-- 註冊密碼 -->
              <div class="form-group my-0"> <label>Password</label>
                <input type="password" name="password" value="${param.password}" id="password" class="form-control" 
                placeholder="Password"> <span id="passwordResult" style="height:10px;"></span> </div>
              <!-- 確認密碼 -->
              <div class="form-group m-0"> <label>Re-type password</label>
                <input type="text" name="password2" value="${param.password2}" id="password2" class="form-control" placeholder="">
                <span id="password2Result" style="height:10px;"></span> </div>
              <!-- 驗證碼 -->
              <div class="form-group">
                <br>
                <div class="g-recaptcha" data-sitekey="6LcHbCUUAAAAADtEowUF3Hhswm8p3tb_hrI5AOHA">
                </div>
                <!-- 驗整碼輸入結果 --><span id="verfResult" style="height:10px;"></span> 
              </div>
              <!-- 顯示程式執行後的訊息 -->
              <div>
                <font size="-1" color="FF0000"> ${MsgMap.InsertNG}${MsgMap.errorSaveData} </font>
              </div>
              <!-- 錯誤訊息顯示 -->
              <div>
                <font size="-1" color="#FF0000"> ${msgErr.idRepeat}${magErr.errorIDDup} </font>
              </div>
            </div>
            <div id="formRight">
              <!--               </div> -->
              <div class="form-group mx-2"> <label>Name of Restaurant</label>
                <input type="text" name="StoreName" value="${param.StoreName}" id="StoreName" class="form-control" placeholder=""> 
                <span id="StoreNameResult" style="height:10px;"></span> </div>
              <!-- 餐廳類型 -->
              <div class="form-group mx-2" > <label >Cuisine type</label>
             <c:set target="${rtd}" property="tagName" value="StoreType"/>
             
             
             
                ${rtd.selectTag}



<!--                <select class="form-control" name="StoreType"  id="StoreType"> -->
<!--                   <option value="台式餐廳">台式餐廳</option> -->
<!--                   <option value="日式餐廳">日式餐廳</option> -->
<!--                   <option value="韓式餐廳">韓式餐廳</option> -->
<!--                   <option value="美式餐廳">美式餐廳</option> -->
<!--                   <option value="義式餐廳">義式餐廳</option> -->
<!--                   <option value="法式餐廳">法式餐廳</option> -->
<!--                   <option value="泰式餐廳">泰式餐廳</option> -->
<!--                   <option value="越式餐廳">越式餐廳</option> -->
<!--                 </select> </div> -->
              <!-- 餐廳分店 -->
              <div class="form-group mx-2"> <label>Store Branch</label>
                <input type="text" name="branch" value="${param.branch}" id="branch" class="form-control" placeholder=""> </div>
              <!-- 餐廳地址 -->
              <div class="form-group mx-2"><label>Address</label>
                <input type="text" name="address" value="${param.address}" id="address" class="form-control" placeholder=""> 
                <span id="addressResult" style="height:10px;"></span> </div>
              <!-- 餐廳Email -->
              <div class="form-group mx-2"><label>Email</label>
                <input type="text" name="eMail" value="${param.email}" id="eMail" class="form-control" placeholder="MR.  /  MS."> 
                <span id="eMailResult" style="height:10px;"></span> </div>  
              <!-- 餐廳聯絡電話 -->
              <div class="form-group mx-2"><label>Contact phone number</label>
                <input type="text" name="tel" value="${param.tel}" id="tel" class="form-control" placeholder=""> 
                <span id="telResult" style="height:10px;"></span> </div>
              <!-- 餐廳負責人 -->
              <div class="form-group mx-2"><label>Owner of Restaurant</label>
                <input type="text" name="owner" value="${param.owner}" id="owner" class="form-control" placeholder="MR.  /  MS."> 
                <span id="ownerResult" style="height:10px;"></span> </div>
              <!-- 餐廳網站link -->
              <div class="form-group px-2"><label>Official Website</label>
                <input type="text" name="url" value="${param.url}" id="url" class="form-control" placeholder="http://"> </div>
              <!-- 註冊提交按鈕 -->
              <!--<button type="submit" class="btn btn-primary mx-2 btn-block">Sign up</button> -->
            </div>
            <div id="formButtom">
              <center>
                <button type="submit" class="btn mx-2 btn-block w-50 btn-warning">Sign up</button>
              </center>
            </div>
          </form>
              <div id="gygy" >點我點我</div>
          <!-- 經緯度 -->
          <input type="text" name="langitude" style="display:none;" value="" id="langitude">
          <input type="text" name="latitude" style="display:none;" value="" id="latitude"> </div>
      </div>
    </div>
  </div>
  <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js"></script>
  <script src="https://pingendo.com/assets/bootstrap/bootstrap-4.0.0-alpha.6.min.js"></script>
  <!-- google 機器人驗證 -->

  
<script type="text/javascript"
		src="http://maps.google.com/maps/api/js?libraries=places&key=AIzaSyARdaz9WnSdDiJopjiTUy59yhcR8Cfn_7k"></script>
		
<script src="../js/StoreRegister.js" ></script>
</body>

</html>