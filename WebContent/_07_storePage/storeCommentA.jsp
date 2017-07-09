<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>店家評價-Grab &amp; Go</title>
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
     	<jsp:useBean id="rating" class="_07_Rating.model.RestRatingBeanDAO" scope="session" />
       <c:set target="${rating}" property="rest_Id" value="${clickRest.rest_id}" />
</head>

<body id="top" class="cbp-spmenu-push">
   
   
    <jsp:include page="../_IncludeJsp/User_mainNav.jsp" />
    
   <!-- 店家資訊 -->
            <jsp:include page="../_IncludeJsp/StorePage_MainInfo.jsp" />
   
   
    <main>
        <div class="brcame"><a href="../index.jsp">首頁</a> / <a href="storePage.jsp">${clickRest.rest_name}&nbsp;<c:if test="${clickRest.rest_branch!='null'}">${clickRest.rest_branch}</c:if></a> / 店家評價</div>
        <section class="content">
            <div class="commentDes">
                <h1>店家評價</h1>
                <p>100% 真實評語,來自每位用心品嘗美食的饕客的親身體驗。</p>
            </div>
            <!--評語列表-->
            <c:set var="evaList" value="${rating.allRestEvaByRestId}"/>
            <c:forEach var="x"  items="${evaList}">
	            <div class="commentList">
	                <div class="userInfo">
	                    <figure><img src="${pageContext.servletContext.contextPath}/_00_init/getImageA?id=${x.m_username}&type=MEMBER"></figure>
	                    <p>${x.m_username}</p>
	                </div>
	                <div class="commentRight">
	                <time><i class="icon-time"></i>${x.eva_date}</time>
	                    <div class="star">
	                    	<c:set var="yy" value="${5-x.restEva_star}" />
                            <c:forEach begin="1" end="${x.restEva_star}" step="1">
                                <i class="icon-star on"></i>
                            </c:forEach>
                            <c:forEach begin="1" end="${yy}" step="1">
                                <i class="icon-star"></i>
                            </c:forEach>
                    	</div>
	                    <div class="commentInfo">${x.restEva_comment}</div>
	                </div>
	            </div>
            </c:forEach>
            
            
           
            <!--評語列表 end-->
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
    <!--share js-->
    <script src="../javascript/share.js"></script>
</body>

</html>