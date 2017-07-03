<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <meta content="width=device-width, initial-scale=1" name="viewport">
    <title>搜尋結果-Grab &amp; Go</title>
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
</head>

<body id="top" class="cbp-spmenu-push">
    <jsp:include page="../_IncludeJsp/User_mainNav.jsp" />
   
    <div class="insideTitle">
        <h2>搜尋結果</h2>
    </div>
    <main>
        <div class="brcame"><a href="../index.jsp">首頁</a> / 搜尋結果</div>
        <section class="content">

            <h4>以下為您的搜尋結果：共${resultSize}筆</h4>

            <!--瀑布流-->
            <section class="grid slider wow fadeIn" id="container">
            	<c:forEach var="rest" items="${Restaurants}">
                <div class="gridItem">
                    <figure><img src='${pageContext.servletContext.contextPath}/GetImage?no=${rest.rest_Id}&type=coverImage' alt="${rest.rest_name}" title="photo">
                        <div class="mask"><a href="${pageContext.servletContext.contextPath}/_07_storePage/getOneRest.do?id=${rest.rest_Id}">觀看菜單</a></div>
                    </figure>
                    <div class="storeInfo">
                        <h2><a href="${pageContext.servletContext.contextPath}/_07_storePage/getOneRest.do?id=${rest.rest_Id}">${rest.rest_name}</a></h2>
                        <!--有亮的星星class要加上on-->
                        <div class="star"><i class="icon-star on"></i> <i class="icon-star on"></i> <i class="icon-star on"></i> <i class="icon-star"></i> <i class="icon-star"></i></div>
                        <div class="dist"><i class="icon-location"></i>${rest.rest_branch}</div>
                        <div class="tagOrder">
                            <div class="tag"><span>${rest.rest_typeId}</span></div>
                            <div class="orderCount">目前訂單<span>999</span></div>
                        </div>
                    </div>
                </div>
                </c:forEach>
            </section>
            <!--瀑布流 end-->
        </section>
    </main>
    <!--搜尋-->
     <jsp:include page="../_IncludeJsp/User_search.jsp" />
    <footer>
        <figure><img src="../images/share/logo.svg" alt="Grab &amp; Go" title="Grab &amp; Go"></figure>
        <p>Copyright © Garb and Go All rights reserved.</p><a href="#" class="backToTop">TOP</a></footer>
    <!--main js-->
    <!--[if lt IE 8]><script type="text/javascript" src="javascript/html5.js"></script><![endif]-->
    <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
    <!--nav-->
    <script type="text/javascript" src="../javascript/classie.js"></script>
    <!--瀑布流-->
    <script src="../javascript/masonry.pkgd.min.js"></script>
    <script src="../javascript/imagesloaded.pkgd.min.js"></script>
    <script>
        var $grid = $('.grid').imagesLoaded(function() {
            $grid.masonry({
                itemSelector: '.gridItem',
                percentPosition: true,
            });
        });
include
    </script>
    <!--share js-->
    <script src="../javascript/share.js"></script>
</body>
</html>