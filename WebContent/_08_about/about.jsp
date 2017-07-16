<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
    <html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>關於我們-Grab &amp; Go</title>
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
        <link href="../css_web/styles.css" rel="stylesheet">
        <link href="../css_web/component.css" rel="stylesheet" type="text/css" />
        <link href="../css_web/animate.css" rel="stylesheet" type="text/css" />
    </head>

    <body id="top" class="cbp-spmenu-push">
        <jsp:include page="../_IncludeJsp/User_mainNav.jsp" />

        <div class="insideTitle">
            <h2>關於我們</h2>
        </div>
        <main>
            <div class="brcame"><a href="../index.jsp">首頁</a> / 關於我們</div>
            <section class="content">
                <div class="memberAccount wow zoomIn">
                    <h3>Food ordering-service platform</h3>
                    <p>It provides the services to help users decide the dish today and place their orders in advance. </p>
                    <p>In the meantime, the restaurant will start preparing customers' meals. All you have to do is go to the restaurant "grab" your lunchbox and "go". that's why we name it grab and go.</p>
                </div>
            </section>
        </main>
        <!--搜尋-->
        <jsp:include page="../_IncludeJsp/User_search.jsp" />
        <!--訊息-->
        <jsp:include page="../_IncludeJsp/User_NotificationPanel.jsp" />
        <footer>
            <figure><img src="../images/share/logo.svg" alt="Grab &amp; Go" title="Grab &amp; Go"></figure>
            <p>Copyright © Garb and Go All rights reserved.</p><a href="#" class="backToTop">TOP</a></footer>
        <!--main js-->
        <!--[if lt IE 8]><script type="text/javascript" src="javascript/html5.js"></script><![endif]-->
        <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
        <!--nav-->
        <script type="text/javascript" src="../javascript/classie.js"></script>
        <!--animation js-->
        <script type="text/javascript" src="../javascript/wow.js"></script>
        <!--loading js-->
        <script>
            $(window).load(function() {
                $("#loading").fadeOut(500);
            })

        </script>
        <!--SmoothScroll js-->
        <script type="text/javascript" src="../javascript/SmoothScroll_v1.2.1.js"></script>
        <!--share js-->
        <script src="../javascript/share.js"></script>
    </body>

    </html>
