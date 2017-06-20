<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<header>
	<div id="showLeftPush" class="menuBtn">
		<i class="icon-menu" title="Menu"></i>
	</div>
	<div class="logo">
		<a href="indexA.jsp"><img src="../images/share/logo.svg"
			alt="Grab &amp; Go" title="Grab &amp; Go"></a>
	</div>
	<div class="rightBtn searchItem">
		<a href="#" title="搜尋"><i class="icon-search"></i></a>
	</div>
	<div class="rightBtn">
		<a href="_04_ShoppingCart/cart.htm" title="購物車"><i
			class="icon-bag"></i></a>
	</div>
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
				<li><a href="_06_member/member.htm">${LoginOK.memberId}</a></li>
				<li><a href="logout.do">登出</a></li>
				<!--已登入用這組 end-->
			</c:if>
		</ul>
	</div>
	</header>

</body>
</html>