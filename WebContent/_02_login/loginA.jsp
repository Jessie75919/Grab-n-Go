<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

        <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
        <html>

        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <meta content="width=device-width, initial-scale=1" name="viewport">
            <title>Grab &amp; Go</title>
            <meta name="keywords" content="Grab &amp; Go, 訂餐, 帶著就走, 上班族" />
            <meta name="description" content="短短的午休時間您受夠了在水深火熱中跟人家相爭排隊買午餐嗎? Grab &amp; Go 預約訂餐系統讓您輕鬆帶著走。" />
            <meta name="author" content="Grab &amp; Go">
            <meta name="copyright" content="Grab &amp; Go">
            <meta name="robots" content="index, follow">
            <meta name="GOOGLEBOT" content="index, follow">
            <meta name="distribution" content="GLOBAL">
            <meta property="og:title" content="Grab &amp; Go " />
            <meta property="og:description" content="短短的午休時間您受夠了在水深火熱中跟人家相爭排隊買午餐嗎? Grab &amp; Go 預約訂餐系統讓您輕鬆帶著走。" />
            <meta property="og:site_name" content="Grab &amp; Go" />
            <meta property="og:type" content="website" />
            <meta property="og:url" content="http://www.chewchew.com.tw" />
            <meta property="og:image" content="http://lovegreenfood.com/gg/fb.jpg" />
            <link rel="image_src" href="http://lovegreenfood.com/gg/fb.jpg" />
            <link rel="SHORTCUT ICON" href="../images/favicon.ico" />
            <link rel="icon" href="../images/favicon.ico" type="image/ico" />
            <!--main css-->
            <link href="../css_web/default.css" rel="stylesheet" type="text/css" />
            <link href="https://file.myfontastic.com/JgbKu4HBhSiTuUxrtB7R5d/icons.css" rel="stylesheet">
            <link href="../css_web/component.css" rel="stylesheet" type="text/css" />
            <script src='https://www.google.com/recaptcha/api.js'></script>
            <!--animation-->
            <link href="../css_web/animate.css" rel="stylesheet" type="text/css" />
        </head>

        <body>

            <!-- 下列敘述設定變數funcName的值為LOG，top.jsp 會用到此變數 -->
            <c:set var="funcName" value="LOG" scope="session" />
            <c:if test="${ ! empty sessionScope.timeOut }">
                <!-- 表示使用逾時，重新登入 -->
                <c:set var="msg" value="<font color='red'>${sessionScope.timeOut}</font>" />
            </c:if>
            <div class="loginLogo">
                <a href="../indexA.jsp"><img src="../images/share/logo.svg" alt="Grab &amp; Go" title="Grab &amp; Go"></a>
            </div>
            <main class="login">
                <div class="googleBtn"><a href="#"><i class="icon-google"></i>GOOGLE 登入</a></div>
                <h2>會員帳號登入</h2>
                <form action="<c:url value='userLogin.do' />" method="POST" name="loginForm" class="formcontent">
                    <div class="loginList">
                        <input type="text" name="userId" id="userId" placeholder="帳號" value="${sessionScope.user}" class="validate[required] text-input">
                    </div>
                    <div class="loginList">
                        <input type="password" name="pswd" id="pswd" placeholder="密碼" value="${sessionScope.password}" class="validate[required] text-input">
                    </div>
                    <!-- 機器人驗證 -->
                    <div class="loginList">
                        <div class="g-recaptcha" data-sitekey="6LcHbCUUAAAAADtEowUF3Hhswm8p3tb_hrI5AOHA"></div>
                    </div>
                    <div class="loginList">
                        <input type="checkbox" name="rememberMe" <c:if test='${sessionScope.rememberMe==true}'> checked='checked'
                        </c:if>
                        value="true">記住此帳號!
                    </div>
                    <span class="wRed">${ErrorMsgKey.LoginError}</span>
                    <div class="loginBtn">
                        <input name="reset" type="reset" id="reset" value="重填">
                        <input name="submit" type="submit" id="submit" value="登入">
                        <p><a href="forget_passwordA.jsp">忘記密碼</a> | <a href="../_01_register/registerA.jsp">註冊新會員</a></p>
                    </div>
                </form>
            </main>
            <footer>
                <p>Copyright © Garb and Go All rights reserved.</p><a href="#" class="backToTop">TOP</a></footer>
            <!--main js-->
            <!--[if lt IE 8]><script type="text/javascript" src="../javascript/html5.js"></script><![endif]-->
            <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
            <!--animation js-->
            <script type="text/javascript" src="../javascript/wow.js"></script>
            <!-- form check-->
            <link rel="stylesheet" href="../javascript/validation/validationEngine.jquery.css">
            <script type="text/javascript" charset="utf-8" src="../javascript/validation/languages/jquery.validationEngine-zh_TW.js"></script>
            <script type="text/javascript" charset="utf-8" src="../javascript/validation/jquery.validationEngine.js"></script>
            <script>


            </script>
            <!-- form check end-->
            <!--share js-->
            <script src="../javascript/share.js"></script>


        </body>

        </html>
