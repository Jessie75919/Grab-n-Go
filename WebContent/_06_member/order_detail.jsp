<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <meta content="width=device-width, initial-scale=1" name="viewport">
    <title>訂購紀錄 | 詳細內容-Grab &amp; Go</title>
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
<jsp:useBean id="orderItemDAOBean" class="_05_orderProcess.model.OrderItemDAO"/>
<c:set target="${orderItemDAOBean}" property="ord_id" value="${param.ordId}"/>
<jsp:useBean id="orderBean" class="_05_orderProcess.model.OrderDAO"/>
<c:set target="${orderBean}" property="ord_id" value="${param.ordId}"/>
<c:set var="ob"  value="${orderBean.storeOrdersById}"/>
<body id="top" class="cbp-spmenu-push">
 <jsp:include page="../_IncludeJsp/User_mainNav.jsp" />
    <div class="insideTitle">
        <h2>訂購紀錄</h2>
    </div>
    <main>
        <div class="brcame"><a href="../index.jsp">首頁</a> / <a href="order.jsp">訂購紀錄</a> / 訂購內容</div>
        <section class="content">

            <div class="cartLeft">
                <h3>${param.restName}</h3>
                <!--菜單列表-->
                <c:forEach var="orderItemBean" items="${orderItemDAOBean.orderItemById}">
                <div class="foodList">
                    <figure><img src='${pageContext.servletContext.contextPath}/_00_init/getImage?id=${orderItemBean.prod_id}' alt="${orderItemBean.item_name}" title="${orderItemBean.item_name}"></figure>
                    <div class="foodInfo">
                        <h4>${orderItemBean.item_name}</h4>
                        <p>數量 ${orderItemBean.item_amount} <span class="price">NT$${orderItemBean.item_price}</span></p>
                        <p>特殊需求：${orderItemBean.item_note}</p>
                    </div>
                </div>
                </c:forEach>
                <!--菜單列表 end-->
                <div class="total">總計：NT$${ob.ord_totalPrice}元</div>
            </div>
            <div class="cartRight">
                <h3>訂購人資訊</h3>
                <!--訂購人資訊-->
                <div class="formList">
                    <div class="formTitle"><i class="icon-user"></i>訂購人</div>
                    <div class="formInfo noform">${ob.m_pickupname}</div>
                </div>
                <div class="formList">
                    <div class="formTitle"><i class="icon-phone"></i>聯絡電話</div>
                    <div class="formInfo noform">${ob.ord_tel}</div>
                </div>
                <div class="formList">
                    <div class="formTitle"><i class="icon-mail"></i>E-mail</div>
                    <div class="formInfo noform">${ob.ord_email}</div>
                </div>
                <div class="formList">
                    <div class="formTitle"><i class="icon-birth"></i>預計取餐時間</div>
                    <div class="formInfo noform">
                    <fmt:formatDate type = "both"  pattern="yyyy-MM-dd HH:mm" value ="${ob.ord_pickuptime}" />
                    </div>
                </div>
                <!--訂購人資訊 end-->
                <div class="formBtn">
                    <p><a href="../indexA.jsp">回首頁</a> <a href="order.jsp">回訂購紀錄列表</a></p>
                </div>
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
    <!-- form check-->
    <link rel="stylesheet" href="../javascript/validation/validationEngine.jquery.css">
    <script type="text/javascript" charset="utf-8" src="../javascript/validation/languages/jquery.validationEngine-zh_TW.js"></script>
    <script type="text/javascript" charset="utf-8" src="../javascript/validation/jquery.validationEngine.js"></script>
    <!-- form check end-->
    <!--share js-->
    <script src="../javascript/share.js"></script>
</body>
</html>