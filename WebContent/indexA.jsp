<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<jsp:useBean id="SYSTEM" class="_00_init.GlobalService"
	scope="application" />
<jsp:useBean id="rating" class="_07_Rating.model.RestRatingBeanDAO" scope="session" />


<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta content="width=device-width, initial-scale=1" name="viewport">
<title>Grab &amp; Go</title>
<meta name="keywords" content="Grab &amp; Go, 訂餐, 帶著就走, 上班族" />
<meta name="description"
	content="短短的午休時間您受夠了在水深火熱中跟人家相爭排隊買午餐嗎? Grab &amp; Go 預約訂餐系統讓您輕鬆帶著走。" />
<meta name="author" content="Grab &amp; Go">
<meta name="copyright" content="Grab &amp; Go">
<meta name="robots" content="index, follow">
<meta name="GOOGLEBOT" content="index, follow">
<meta name="distribution" content="GLOBAL">
<meta property="og:title" content="Grab &amp; Go " />
<meta property="og:description"
	content="短短的午休時間您受夠了在水深火熱中跟人家相爭排隊買午餐嗎? Grab &amp; Go 預約訂餐系統讓您輕鬆帶著走。" />
<meta property="og:site_name" content="Grab &amp; Go" />
<meta property="og:type" content="website" />
<meta property="og:url" content="http://www.chewchew.com.tw" />
<meta property="og:image" content="http://lovegreenfood.com/gg/fb.jpg" />
<link rel="image_src" href="http://lovegreenfood.com/gg/fb.jpg" />
<link rel="SHORTCUT ICON" href="images/favicon.ico" />
<link rel="icon" href="images/favicon.ico" type="image/ico" />
<!--main css-->
<link href="css_web/default.css" rel="stylesheet" type="text/css" />
<link
	href="https://file.myfontastic.com/JgbKu4HBhSiTuUxrtB7R5d/icons.css"
	rel="stylesheet">
<link href="css_web/component.css" rel="stylesheet" type="text/css" />
<!--輪播 css-->
<link href="css_web/slick.css" rel="stylesheet" type="text/css">
<!--animation-->
<link href="css_web/animate.css" rel="stylesheet" type="text/css" />

<jsp:useBean id="rt" class="_22_searchRest.model.RestDAO" scope="page" />
</head>

<body id="top" class="cbp-spmenu-push">
	<header>
	<div id="showLeftPush" class="menuBtn">
		<i class="icon-menu" title="Menu"></i>
	</div>
	<div class="storeBtn">
		<a href="_02_storeLogin/StoreLogin.jsp">我是店家</a>
	</div>
	<div class="logo">
		<a href="indexA.jsp"><img src="images/share/logo.svg"
			alt="Grab &amp; Go" title="Grab &amp; Go"></a>
	</div>
	<div class="rightBtn searchItem">
		<a href="#" title="搜尋"><i class="icon-search"></i></a>
	</div>
	<c:if test="${! empty cart}">
		<div class="rightBtn">
			<a href="_04_ShoppingCart/cartA.jsp" title="購物車"><i
				class="icon-bag"></i></a> <span>${cart.itemNumber}</span>
		</div>
	</c:if>
	<div class="account">
		<ul>
			<!--未登入用這組-->
			<c:if test="${empty LoginOK}">
				<li><a href="_02_login/loginA.jsp">登入</a></li>
			</c:if>
			<!-- 			<li><a href="_02_login/loginA.jsp">登入</a></li> -->
			<!--未登入用這組 end-->
			<!--已登入用這組-->
			<c:if test="${! empty LoginOK}">
				<li><a href="_06_member/memberA.jsp">${LoginOK.memberId}</a></li>
				<li><a href="logout.do">登出</a></li>
				<!--已登入用這組 end-->
			</c:if>
		</ul>
	</div>
	</header>
	<nav class="cbp-spmenu cbp-spmenu-vertical cbp-spmenu-left"
		id="cbp-spmenu-s1"> <!--已登入用這組 未登入的話這塊隱藏--> <c:if
		test="${!empty LoginOK}">
		<div class="memberLogin">
			<figure> <img
				src='${pageContext.servletContext.contextPath}/_00_init/getImageA?id=${LoginOK.memberId}&type=MEMBER'
				alt="Photo" title="Photo"> </figure>
			<p>
				${LoginOK.memberId}<a href="logout.do">登出</a>
			</p>
		</div>
	</c:if> <!--已登入用這組 未登入的話這塊隱藏 end-->
	<ul>
		<li><a href="_06_member/memberA.jsp"><i class="icon-user"></i>檢視/編輯個人資料</a></li>
		<li><a href="_06_member/order.jsp"><i class="icon-list"></i>訂購紀錄</a></li>
		<li><a href="_08_about/about.htm"><i class="icon-gg"></i>關於Grab
				&amp; Go</a></li>
	</ul>
	<!-- AddToAny BEGIN --> <!-- 	<div class="a2a_kit a2a_kit_size_32 a2a_default_style"> -->
	<!-- 		<a class="a2a_button_facebook"></a> <a class="a2a_button_line"></a> <a -->
	<!-- 			class="a2a_button_google_plus"></a> <a class="a2a_button_twitter"></a> -->
	<!-- 		<a class="a2a_button_email"></a> --> <!-- 	</div> --> <!-- 	<script async src="https://static.addtoany.com/menu/page.js"></script> -->
	<!-- AddToAny END --> </nav>
	<div class="mainBanner">
		<h1 class="slider wow fadeInDown" data-wow-delay="0.3s">
			What do you want<br> <span class="wBig">TO EAT</span><br> <span
				class="wSmall">today?</span><br> <span class="wRed">今天想吃什麼</span>
		</h1>
		<!--main banner 輪播-->
		<div class="banner slider wow fadeIn">
			<div>
				<img src="images/banner/main_banner20170610001.jpg"
					alt="Grab &amp; Go" title="Grab &amp; Go">
			</div>
			<div>
				<img src="images/banner/main_banner20170610002.jpg"
					alt="Grab &amp; Go" title="Grab &amp; Go">
			</div>
		</div>
		<!--main banner 輪播 end-->
	</div>
	<main> <!--標語--> <section class="content bgRed">
	<h2 class="slogan slider wow fadeIn" data-wow-delay="0.5s">
		"短短的午休時間您受夠了在水深火熱中跟人家相爭排隊買午餐嗎? <br>Grab &amp; Go 預約訂餐系統讓您輕鬆帶著走。"<br>
		<%-- 		${cookie.lat.value}<br> --%>
		<%-- 		${cookie.lng.value} --%>
	</h2>
	</section> <!--標語 end--> <!--瀑布流--> <c:set var="x" value="${stList}" /> <section
		class="grid slider wow fadeIn" id="container"> <!-- 	// CALL get_Rest(25.0483199,121.5344137); -->
	<c:forEach var="restaurant" items="${x}" varStatus='vs'>
		<div class="gridItem">
			<figure> <img
				src="${pageContext.servletContext.contextPath}/_00_init/getImageA?id=${restaurant.rest_username}&type=restaurant&loc=cover"
				alt="${restaurant.rest_name}" title="${restaurant.rest_name}">
			<div class="mask">
				<a
					href="${pageContext.servletContext.contextPath}/_07_storePage/getOneRest.do?id=${restaurant.rest_id}">觀看菜單</a>
			</div>
			</figure>
			<div class="storeInfo">
				<h2>
					<a
						href="${pageContext.servletContext.contextPath}/_07_storePage/getOneRest.do?id=${restaurant.rest_id}">${restaurant.rest_name}</a>
				</h2>
				<!-- 			有亮的星星class要加上on -->
				<c:set target="${rating}" property="rest_Id" value="${restaurant.rest_id}" />
				<div class="star">
				<c:set var="x" value="${5-rating.restEva}"/>
                <c:forEach begin="1" end="${rating.restEva}" step="1">
                 	<i class="icon-star on"></i> 
                </c:forEach>
                 <c:forEach begin="1" end="${x}" step="1">
               		 <i class="icon-star"></i> 
                </c:forEach>
<!-- 					<i class="icon-star on"></i> <i class="icon-star on"></i> <i -->
<!-- 						class="icon-star on"></i> <i class="icon-star"></i> <i -->
<!-- 						class="icon-star"></i> -->
				</div>
				<div class="dist">
					<c:set var="string1" value="${restaurant.rest_address}" />
					<c:set var="string2" value="${fn:substring(string1,3,6)}" />
					<i class="icon-location"></i>${string2}
				</div>
				<div class="tagOrder">
					<div class="tag">
						<span>${restaurant.rest_type}</span>
					</div>
					<div class="orderCount">
						目前訂單<span>999</span>
					</div>
				</div>
			</div>
		</div>
	</c:forEach> </section> </main>
	<!--搜尋-->
	<div class="search">
		<div class="searchBg"></div>
		<section class="searchContent">
		<div>${errMsg.nothing}</div>
		<h2>Hello! 今天想吃什麼呢?</h2>
		<div class="closeBtn">
			<i class="icon-close" title="關閉"></i>
		</div>
		<form name="searchForm" action="_22_SearchRest/SearchRestServlet"
			onsubmit="return validateForm()" method="get">
			<div style="margin-bottom: 15px;">
				<span id="noCondition" class="wRed"></span>
			</div>
			<div class="searchList">
				<c:set target="${rt}" property="tagName" value='foodKind' />
				${rt.selectTag} <br>

			</div>
			<div class="searchList">
				<input type="text" name="storeName" id="storeName"
					placeholder="請輸入店家名稱">
			</div>
			<div class="searchList">
				<input type="text" name="foodName" id="foodName"
					placeholder="請輸入料理名稱">
			</div>
			<div class="searchBtn">
				<input name="reset" type="reset" id="reset" value="重填"> <input
					name="submit" type="submit" id="submit" value="搜尋">
			</div>
		</form>
		<script>
			function validateForm() {
				var x = document.forms["searchForm"].foodKind.value;
				var y = document.forms["searchForm"]["storeName"].value;
				var z = document.forms["searchForm"]["foodName"].value;

				if ((!x || /^\s*$/.test(x)) && (!y || /^\s*$/.test(y))
						&& (!z || /^\s*$/.test(z))) {
					noCondition.innerHTML = "請至少給一個條件吧(╬ﾟдﾟ)"
					return false;
				}
			}
		</script> </section>
	</div>
	<!--搜尋 end-->
	<footer> <figure> <img src="images/share/logo.svg"
		alt="Grab &amp; Go" title="Grab &amp; Go"></figure>
	<p>Copyright © Garb and Go All rights reserved.</p>
	<a href="#" class="backToTop">TOP</a></footer>

	<!--main js-->
	<!--[if lt IE 8]><script type="text/javascript" src="javascript/html5.js"></script><![endif]-->
	<script type="text/javascript"
		src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
	<!--nav-->
	<script type="text/javascript" src="javascript/classie.js"></script>
	<!--animation js-->
	<script type="text/javascript" src="javascript/wow.js"></script>
	<!--silder-->
	<script src="javascript/slick.js"></script>
	<script>
		$(function() {
			$('.banner').slick({
				dots : true,
				arrows : false,
				infinite : true,
				slidesToShow : 1,
				slidesToScroll : 1,
			});
		});
	</script>
	<!--瀑布流-->
	<script src="javascript/masonry.pkgd.min.js"></script>
	<script src="javascript/imagesloaded.pkgd.min.js"></script>
	<script>
		var $grid = $('.grid').imagesLoaded(function() {
			$grid.masonry({
				itemSelector : '.gridItem',
				percentPosition : true,
			});
		});
	</script>
	<!--share js-->
	<script src="javascript/share.js"></script>
	<!-- 取得使用者位置 -->
	<script src="js/getUsrLocation.js"></script>
</body>

</html>
