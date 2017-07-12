<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
    <html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>會員註冊-Grab &amp; Go</title>
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
    </head>

    <body>
        <div class="loginLogo">
            <a href="../indexA.jsp"><img src="../images/share/logo.svg" alt="Grab &amp; Go" title="Grab &amp; Go"></a>
        </div>
        <main class="login">
            <h2>會員註冊</h2>
            <span class="wRed">*為必填</span>
            <form ENCTYPE="multipart/form-data" action="register.do" method="post" class="formcontent">
                <div class="loginList">
                    <input type="text" name="userId" id="userId" value="${param.userId}" placeholder="帳號*" class="validate[required] text-input">
                    <span id="accountlink" class="checkBtn">檢查帳號</span>
                    <span id="midResult"></span> <span>${MsgMap.errorIDEmpty}${MsgMap.errorIDDup}</span>
                </div>
                <div class="loginList">
                    <input type="password" name="pswd" id="pswd" value="${param.pswd}" placeholder="密碼*" class="validate[required] text-input">
                </div>
                <div class="loginList">
                    <input type="password" name="password2" id="password2" value="${param.password2}" placeholder="確認密碼*" class="validate[required,equals[pswd]] text-input">
                </div>
                <div class="loginList">
                    <input type="text" name="name" id="name" placeholder="姓名*" value="${param.name}" class="validate[required] text-input">
                </div>
                <div class="loginList">
                    <input type="text" name="address" id="address" value="${param.address}" placeholder="地址">
                </div>
                <div class="loginList">
                    <input type="text" name="tel" id="tel" placeholder="電話*" value="${param.tel}" class="validate[required,custom[phone]] text-input">
                </div>
                <div class="loginList">
                    <input type="text" name="eMail" id="eMail" placeholder="E-mail*" value="${param.eMail}" class="validate[required,custom[email]] text-input">
                </div>
                <div class="loginList">
                    <input type="date" name="birthday" id="birthday" value="${param.birthday}">
                    <font color="red" size="-1">${MsgMap.errorBirthday}</font>
                </div>
                <div class="loginList">
                    上傳個人照片 <input type="file" name="file1" id="file1">
                </div>
                <div class="loginList">
                    <input type="checkbox">我同意<a id="helper" href="#">隱私權政策</a>與<a href="#">服務條款</a>!
                </div>
                <div class="loginBtn">
                    <input name="reset" type="reset" id="reset" value="重填">
                    <input name="submit" type="submit" id="sub" value="註冊">
                    <p>已有帳號? <a href="../_02_login/loginA.jsp">立即登入</a></p>
                </div>
            </form>
        </main>
        <!--loading-->
        <div id="ttt" class="loading">
            <div class="loadDotted"></div>
            <div class="loadDotted"></div>
            <div class="loadDotted"></div>
        </div>
        <!--loading end-->
        <footer>
            <p>Copyright © Garb and Go All rights reserved.</p><a href="#" class="backToTop">TOP</a></footer>
        <!--main js-->
        <!--[if lt IE 8]><script type="text/javascript" src="../javascript/html5.js"></script><![endif]-->
        <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
        <!-- form check-->
        <link rel="stylesheet" href="../javascript/validation/validationEngine.jquery.css">
        <script type="text/javascript" charset="utf-8" src="../javascript/validation/languages/jquery.validationEngine-zh_TW.js"></script>
        <script type="text/javascript" charset="utf-8" src="../javascript/validation/jquery.validationEngine.js"></script>
        <!-- form check end-->
        <!--share js-->
        <script src="../js/userRegister.js"></script>
        <script src="../javascript/share.js"></script>
    </body>

    </html>
