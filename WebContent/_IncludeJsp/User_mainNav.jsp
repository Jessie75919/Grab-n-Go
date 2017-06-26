<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<header>
        <div id="showLeftPush" class="menuBtn"><i class="icon-menu" title="Menu"></i></div>
        <div class="storeBtn"><a href="../_02_storeLogin/StoreLogin.jsp">我是店家</a></div>
        <div class="logo">
            <a href="../indexA.jsp"><img src="../images/share/logo.svg" alt="Grab &amp; Go" title="Grab &amp; Go"></a>
        </div>
        <div class="rightBtn searchItem"><a href="#" title="搜尋"><i class="icon-search"></i></a></div>
        <div class="rightBtn"><a href="../_04_ShoppingCart/cart.htm" title="購物車"><i class="icon-bag"></i></a></div>
        <div class="account">
		<ul>
			<!--未登入用這組-->
			<c:if test="${empty LoginOK}">
				<li><a href="../_02_login/loginA.jsp">登入</a></li>
			</c:if>
<!-- 			<li><a href="_02_login/loginA.jsp">登入</a></li> -->
			<!--未登入用這組 end-->
			<!--已登入用這組-->
			<c:if test="${! empty LoginOK}">
				<li><a href="../_06_member/memberA.jsp">${LoginOK.memberId}</a></li>
				<li><a href="../logout.do">登出</a></li>
				<!--已登入用這組 end-->
			</c:if>
		</ul>
	</div>
    </header>
    <nav class="cbp-spmenu cbp-spmenu-vertical cbp-spmenu-left"
			id="cbp-spmenu-s1"> 
			<!--已登入用這組 未登入的話這塊隱藏-->
	<c:if test="${!empty LoginOK}">
		<div class="memberLogin">
			<figure>
			<img src='${pageContext.servletContext.contextPath}/_00_init/getImageA?id=${LoginOK.memberId}&type=MEMBER' alt="Photo" title="Photo">
			</figure>
			<p>
				${LoginOK.memberId}<a href="../logout.do">登出</a>
			</p>
		</div>
	</c:if>
	<!--已登入用這組 未登入的話這塊隱藏 end-->
	<ul>
		<li><a href="_06_member/memberA.jsp"><i class="icon-user"></i>檢視/編輯個人資料</a></li>
		<li><a href="_06_member/order.jsp"><i class="icon-list"></i>訂購紀錄</a></li>
		<li><a href="_08_about/about.htm"><i class="icon-gg"></i>關於Grab
				&amp; Go</a></li>
	</ul>
	<!-- AddToAny BEGIN -->
<!-- 	<div class="a2a_kit a2a_kit_size_32 a2a_default_style"> -->
<!-- 		<a class="a2a_button_facebook"></a> <a class="a2a_button_line"></a> <a -->
<!-- 			class="a2a_button_google_plus"></a> <a class="a2a_button_twitter"></a> -->
<!-- 		<a class="a2a_button_email"></a> -->
<!-- 	</div> -->
<!-- 	<script async src="https://static.addtoany.com/menu/page.js"></script> -->
	<!-- AddToAny END --> </nav>
</body>
</html>