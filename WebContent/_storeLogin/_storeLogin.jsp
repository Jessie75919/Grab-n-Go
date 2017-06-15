<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" type="text/css">
  <link rel="stylesheet" href="https://pingendo.github.io/templates/blank/theme.css" type="text/css"> 
  <title>Welcome to GrabAndGo</title>
  <script src='https://www.google.com/recaptcha/api.js'></script>
  <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js"></script>
  <script src="https://pingendo.com/assets/bootstrap/bootstrap-4.0.0-alpha.6.min.js"></script>
  <script type="text/javascript">
    //由<body>的onLoad事件處理函數觸發此函數
    function setFocusToUserId(){   
       document.forms[0].userId.focus();   // 將游標放在userId欄位內
    }
</script>
</head>

<body onLoad="setFocusToUserId()">
  <div class="py-5 section text-center">
    <div class="container">
      <div class="row">
        <div class="col-md-12">
          <!-- <h1 class="text-primary">Logo</h1> -->
          <img width="400" src="../images/share/logo.svg">
          <p class="lead"></p>
        </div>
      </div>
    </div>
  </div>
  <div class="py-5">
    <div class="container">
      <div class="row">
        <div class="col-md-4">
        </div>
        <div>
          <!-- 下列敘述設定變數funcName的值為LOG，top.jsp 會用到此變數 -->
          <c:set var="funcName" value="LOG" scope="session"/>
          <c:set var="msg" value="登入" />
          <c:if test="${ ! empty sessionScope.timeOut }" > <!-- 表示使用逾時，重新登入 -->
             <c:set var="msg" value="<font color='red'>${sessionScope.timeOut}</font>" />
          </c:if>
        </div>
        <div class="col-lg-4">
          <form class="" action="<c:url value='login.do' />" method="POST" name="loginForm">
            <!-- <button type="submit" class="btn btn-block text-gray-dark btn-default m-0">Sign in with Google&nbsp;
              <br> 
            </button> -->
            <div class="form-group my-1"> 
              <label>Username / E-mail</label>
              <input type="text" name="userId" value="${sessionScope.user}"class="form-control" placeholder="Enter username / e-mail"> 
            </div>
            <div class="form-group my-1"> 
              <label>Password</label>
              <input type="password" name="pswd" value="${sessionScope.password}" class="form-control" placeholder="Password"> 
            </div>
            <!-- 機器人驗證 -->
            <div class="form-group my-1">
              <br>
              <div class="g-recaptcha" data-sitekey="6LcHbCUUAAAAADtEowUF3Hhswm8p3tb_hrI5AOHA">
              </div>
              <!-- <label>I am not Robot! Enter the text:&nbsp;</label>
              <input type="text" class="form-control" placeholder="">  -->
            </div>
            <div class="form-check"> 
              <label class="form-check-label">
            	  <input class="form-check-input" type="checkbox" name="rememberMe"
	             <c:if test='${sessionScope.rememberMe==true}'>
		                  checked='checked'
		         </c:if> 
	           value="true">&nbsp;Remember me</label>
            </div>

            <button type="submit" class="btn btn-block my-1 btn-warning">Sign in</button>
            <input type="submit" value="Sign up" class="btn btn-block my-2 btn-default"
                  href='_01_storeRegister_Joanna/_storeRegister.jsp'>
          </form>
        </div>
        <div class="col-md-4 w-25"></div>
      </div>
    </div>
  </div>
</body>
</html>