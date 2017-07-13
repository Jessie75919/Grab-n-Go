<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- 商家登出頁面 -->
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" type="text/css">
  <link rel="stylesheet" href="https://pingendo.github.io/templates/blank/theme.css" type="text/css"> 
  <link rel="stylesheet" href="../css/bootstrap.css" type="text/css">
  <title> Sign Out </title>
</head>
<c:remove var="StoreLoginOK" scope="session" />
<c:remove var="ShoppingCart" scope="session" />
<!-- 下列敘述設定變數funcName的值為OUT，top.jsp 會用到此變數 -->
<c:set var="funcName" value="OUT" scope="session"/>

<body style="font-family: 微軟正黑體, Microsoft JhengHei,arial,helvetica,sans-serif;">
  <div class="py-5 section text-center">
    <div class="container">
      <div class="row">
        <div class="col-md-12">
          <!-- <h1 class="text-primary">Logo</h1> -->
          <img width="30%" src="../images/share/logo.svg">
          <p class="lead"></p>
        </div>
      </div>
    </div>
  </div>
  <div class="row">
    <div class="col-md-12">
      <div class="container"></div>
    </div>
  </div>
  <div class="py-5 section">
    <div class="container">
      <div class="row">
        <div class="col-md-12">
        <div>
          <center>
            <img width="5%" src="../images/share/signOut.png">
          </center>
          <br>
        </div>
        
        <center>
        	  <h2 id="textSet" style="color: #9297a0;">親～ </h2>
          <h3 id="textSet" class="text-Warning" >已經成功登出囉！</h3><br>

						<p>
						
						<div id="textSet" class="btn btn-default btn-lg">
							<a style=" text-decoration: none;"
								href="../indexA.jsp">狠心離開</a>
						</div>
						<div id="textSet" class="btn btn-warning btn-lg">
							<a style="color: #fff; text-decoration: none;"
								href="StoreLogin.jsp">再次登入</a>
						</div>



<!-- 						<button type="button" class="btn btn-default">狠心離開</button> -->
<!--           <button type="button" class="btn btn-warning">再次登入</button> -->
        </p>
        <%
  session.invalidate();
%>

        
        
        </center>
        </div>
        <div class="col-md-6">
      </div>
    </div>
    <div class="container"></div>
  </div>
  <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js"></script>
  <script src="https://pingendo.com/assets/bootstrap/bootstrap-4.0.0-alpha.6.min.js"></script>
</body>

</html>