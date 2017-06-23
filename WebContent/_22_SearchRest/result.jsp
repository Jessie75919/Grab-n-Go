<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查詢結果</title>
<%-- <jsp:useBean id="rtdao" class="_01_App_register.model.RestaurantTypeDAO" scope="page" /> --%>
</head>
<body>
	<div align="center">
		<c:forEach var="rest" varStatus="statusX" items="${Restaurants}">
			<c:if test="${statusX.first}">
				<c:out value="<table border='1' cellspacing='5' cellpadding='5' >"
					escapeXml="false" />
				<tr bgcolor="CCCC00">
					<td colspan='6' ALIGN='CENTER'>餐廳基本資料</td>
				</tr>
				<tr bgcolor="CCCC00">
					<th>餐廳ID</th>
					<th>餐廳種類</th>
					<th>餐廳名稱</th>
					<th>分店名</th>
					<th>地址</th>
					<th>電話</th>
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
				<td>${rest.rest_Id}</td>
				<td>${rest.rest_typeId}</td>
				<td>${rest.rest_name}</td>
				<td>${rest.rest_branch}</td>
				<td>${rest.rest_address}</td>
				<td>${rest.rest_phone}</td>
			</tr>
			<c:if test="${statusX.last}">
				<c:out value="</table>" escapeXml="flase" />
			</c:if>
		</c:forEach>
<%-- 		<c:forEach var="type" items="${rtdao.restaurantType}"> --%>
<%-- 			${type} <BR> --%>
<%-- 		</c:forEach> --%>
<%-- 			${rtdao.restaurantType[5]}<br> --%>
	</div>
</body>
</html>