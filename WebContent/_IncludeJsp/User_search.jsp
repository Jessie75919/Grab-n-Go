<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <jsp:useBean id="rt" class="_22_searchRest.model.RestDAO" scope="page" />
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="search">
		<div class="searchBg"></div>
		<section class="searchContent">
		<div>${errMsg.nothing}</div>
		<h2>Hello! 今天想吃什麼呢?</h2>
		<div class="closeBtn">
			<i class="icon-close" title="關閉"></i>
		</div>
		<form action="${pageContext.servletContext.contextPath}/SearchRestServlet" method="post">
			<div class="searchList">
				<c:set target="${rt}" property="tagName" value='foodKind' />
				${rt.selectTag}<br>

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
		</section>
	</div>
</body>
</html>