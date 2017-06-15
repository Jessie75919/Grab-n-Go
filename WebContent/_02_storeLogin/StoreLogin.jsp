<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="true" import="_00_init.GlobalService"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<c:set var="funcName" value="LOG" scope="session"/>
<c:set var="msg" value="登入" />
<c:if test="${ ! empty sessionScope.timeOut }" > <!-- 表示使用逾時，重新登入 -->
   <c:set var="msg" value="<font color='red'>${sessionScope.timeOut}</font>" />
</c:if>
<%
	System.out.print(session.getAttribute("user"));
	

%>


<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
 <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" type="text/css">
  <link rel="stylesheet" href="https://pingendo.github.io/templates/blank/theme.css" type="text/css"> 
  <title>Welcome to GrabAndGo</title>
  <script src='https://www.google.com/recaptcha/api.js'></script>
  <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js"></script>
  <script src="https://pingendo.com/assets/bootstrap/bootstrap-4.0.0-alpha.6.min.js"></script>
</head>

<body>
	<div class="py-5 section text-center">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<img width="400" src="../images/share/logo.svg">
				</div>
			</div>
		</div>
	</div>
	<div class="py-5">
		<div class="container">
			<div class="row">
				<div class="col-md-4"></div>
				<div class="col-lg-4">
					<form method="POST" action="Storelogin.do"
						id="theForm" onsubmit="return validateForm(event);">
						<div class="form-group my-1">
							<label>Username </label> <input type="text" id="usr"
								name="username" value="${sessionScope.user}" class="form-control">
							<span id="usrRes" style = "height:10px;"></span>	
						</div>
						<div class="form-group my-1">
							<label>Password</label> 
							<input type="password" id="pw"
							name="password" class="form-control" value="${sessionScope.password}" >
							<span id="pwRes" style = "height:10px;"></span>		
						</div>
						<div>
							<font size="-1" color="FF0000">
								${ErrorMsgKey.LoginError} </font>
						</div>
						 <!-- 機器人驗證 -->
            <div class="form-group my-1">
              <br>
              <div class="g-recaptcha" data-sitekey="6LcHbCUUAAAAADtEowUF3Hhswm8p3tb_hrI5AOHA">
              </div>
              <!-- <label>I am not Robot! Enter the text:&nbsp;</label>
              <input type="text" class="form-control" placeholder="">  -->
						<div class="form-check">
							<label class="form-check-label"> 
							<input	name="rememberMe" class="form-check-input" type="checkbox" id="rm"
								<c:if test="${cookie.rm.value==true}">checked='checked'</c:if>
								value="true" >&nbsp;Remember me
							</label>
						</div>
						<button type="submit" id="signInBtn"
							class="btn btn-block my-1 btn-warning">Sign in</button>
					</form>
						<button type="submit" class="btn btn-primary btn-block my-2"
							href='../_01_register/_register.jsp'>Sign up</button>
				</div>
				<div class="col-md-4 w-25"></div>
			</div>
		</div>
	</div>
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js"></script>
	<script
		src="https://pingendo.com/assets/bootstrap/bootstrap-4.0.0-alpha.6.min.js"></script>
	<script src="../js/StoreLogin.js"></script>
</body>

</html>
