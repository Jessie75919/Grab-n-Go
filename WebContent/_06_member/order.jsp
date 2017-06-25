<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <meta content="width=device-width, initial-scale=1" name="viewport">
    <title>訂購紀錄-Grab &amp; Go</title>
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
<jsp:useBean id="orderBeans" class="_05_orderProcess.model.OrderDAO" scope="page"/>
<c:set target="${orderBeans}" property="username" value="${LoginOK.memberId}"/>
<body id="top" class="cbp-spmenu-push">
 <jsp:include page="../_IncludeJsp/User_mainNav.jsp" />

    <nav class="cbp-spmenu cbp-spmenu-vertical cbp-spmenu-left" id="cbp-spmenu-s1">
        <!--已登入用這組 未登入的話這塊隱藏-->
        <div class="memberLogin">
            <figure><img src="../images/userImage/user_photo.jpg" alt="Photo" title="Photo"></figure>
            <p>JuiceKuo<a href="#">登出</a></p>
        </div>
        <!--已登入用這組 未登入的話這塊隱藏 end-->
        <ul>
            <li><a href="../_06_member/member.htm"><i class="icon-user"></i>檢視/編輯個人資料</a></li>
            <li><a href="../_06_member/order.htm"><i class="icon-list"></i>訂購紀錄</a></li>
            <li><a href="../_08_about/about.htm"><i class="icon-gg"></i>關於Grab &amp; Go</a></li>
        </ul>
        <!-- AddToAny BEGIN -->
        <div class="a2a_kit a2a_kit_size_32 a2a_default_style">
            <a class="a2a_button_facebook"></a>
            <a class="a2a_button_line"></a>
            <a class="a2a_button_google_plus"></a>
            <a class="a2a_button_twitter"></a>
            <a class="a2a_button_email"></a>
        </div>
        <script async src="https://static.addtoany.com/menu/page.js"></script>
        <!-- AddToAny END -->
    </nav>
    <div class="insideTitle">
        <h2>訂購紀錄</h2>
    </div>
    <main>
        <div class="brcame"><a href="../index.jsp">首頁</a> / 訂購紀錄</div>
        <section class="content">
          <div class="orderList head">
              <div class="order nNumber">訂單編號</div>
              <div class="order date">訂購日期</div>
              <div class="order store">店家</div>
              <div class="order price">應付金額</div>
              <div class="order status">訂單狀態</div>
              <div class="order detail">訂單內容</div>
          </div>
          <c:forEach var="anOrderBean" varStatus="statusX" items="${orderBeans.memberOrders}">
          <div class="orderList">
              <div class="order nNumber">${anOrderBean.ord_id}</div>
              <div class="order date">${anOrderBean.ord_time}</div>
              <div class="order store">${anOrderBean.rest_name}</div>
              <div class="order price">${anOrderBean.ord_totalPrice}</div>
              <div class="order status">${anOrderBean.ord_status}</div>
              <div class="order detail"><a href="order_detail.htm">檢視</a></div>
          </div>
          </c:forEach>
<!--           <div class="orderList"> -->
<!--               <div class="order nNumber">No201706040001</div> -->
<!--               <div class="order date">2017.06.04</div> -->
<!--               <div class="order store">Afternoon Tea</div> -->
<!--               <div class="order price">NT$1000</div> -->
<!--               <div class="order status">已取餐</div> -->
<!--               <div class="order detail"><a href="order_detail.htm">檢視</a></div> -->
<!--           </div> -->
<!--           <div class="orderList"> -->
<!--               <div class="order nNumber">No201706040001</div> -->
<!--               <div class="order date">2017.06.04</div> -->
<!--               <div class="order store">Afternoon Tea</div> -->
<!--               <div class="order price">NT$1000</div> -->
<!--               <div class="order status">處理中</div> -->
<!--               <div class="order detail"><a href="order_detail.htm">檢視</a></div> -->
<!--           </div> -->
<!--           <div class="orderList"> -->
<!--               <div class="order nNumber">No201706040001</div> -->
<!--               <div class="order date">2017.06.04</div> -->
<!--               <div class="order store">Afternoon Tea</div> -->
<!--               <div class="order price">NT$1000</div> -->
<!--               <div class="order status">尚未取餐</div> -->
<!--               <div class="order detail"><a href="order_detail.htm">檢視</a></div> -->
<!--           </div> -->
          <div>
          	<c:forEach var="anOrderBean" varStatus="statusX" items="${orderBeans.memberOrders}">
			<c:if test="${statusX.first}">
				<c:out value="<table border='1' cellspacing='5' cellpadding='5' >"
					escapeXml="false" />
				<tr bgcolor="CCCC00">
					<td colspan='5' ALIGN='CENTER'>訂單</td>
				</tr>
				<tr bgcolor="CCCC00">
					<th>訂單編號</th>
					<th>訂購日期</th>
					<th>店名</th>
					<th>應付金額</th>
					<th>訂單狀態</th>
				</tr>
			</c:if>
			<c:choose>
				<c:when test="${ statusX.count % 2 == 0 }">
					<c:set var="colorVar" value="99ddff" />
				</c:when>
				<c:otherwise>
					<c:set var="colorVar" value="88dd00" />
				</c:otherwise>
			</c:choose>

			<tr bgcolor="${colorVar}">
				<td>${anOrderBean.ord_id}</td>
				<td>${anOrderBean.ord_time}</td>
				<td>${anOrderBean.rest_name}</td>
				<td>${anOrderBean.ord_totalPrice}</td>
				<td>${anOrderBean.ord_status}</td>
			</tr>
			<c:if test="${statusX.last}">
				<c:out value="</table>" escapeXml="flase" />
			</c:if>
		</c:forEach>
          </div> 
        </section>
    </main>
    <!--搜尋-->
    <div class="search">
        <div class="searchBg"></div>
        <section class="searchContent">
            <h2>Hello! 今天想吃什麼呢?</h2>
            <div class="closeBtn"><i class="icon-close" title="關閉"></i></div>
            <form action="#" method="post">
                <div class="searchList">
                    <select name="foodKind" id="foodKind">
                           <option value="日式料理">日式料理</option>
                           <option value="中式料理">中式料理</option>
                           <option value="義式料理">義式料理</option>
                           <option value="早午餐/下午茶">早午餐/下午茶</option>
                           <option value="暗黑料理">暗黑料理</option>
                           <option value="台灣小吃">台灣小吃</option>
                       </select>
                </div>
                <div class="searchList">
                    <input type="text" name="storeName" id="storeName" placeholder="請輸入店家名稱">
                </div>
                <div class="searchList">
                    <input type="text" name="foodName" id="foodName" placeholder="請輸入料理名稱">
                </div>
                <div class="searchBtn">
                    <input name="reset" type="reset" id="reset" value="重填">
                    <input name="submit" type="submit" id="submit" value="搜尋">
                </div>
            </form>
        </section>
    </div>
    <!--搜尋 end-->
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