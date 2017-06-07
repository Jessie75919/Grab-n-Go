<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<c:if test="${empty LoginOK}">
	<c:redirect url="/_02_login/login.jsp" />
</c:if>
<jsp:useBean id="orderBeans" class="_04_ShoppingCart.model.OrderDAO"
	scope="page" />
<title>訂單列表</title>
</head>
<body style="background: #EBFFEB;">
<c:set var="funcName" value="ORD" scope="session"/>
	<!-- 引入共同的頁首 -->
	<jsp:include page="/fragment/top.jsp" />
    <p/>
    <center>
	<table border="2" bordercolor="blue" style="background:#EFEFFB;">

		<tr height='50'>
			<th   colspan="4" align="center">${LoginOK.name}的訂購紀錄</th>
		</tr>
		<tr height='36' >
			<th>訂單編號</th>
			<th>訂購日期</th>
			<th>總金額</th>
			<th>送貨地址</th>
		</tr>
		<c:forEach var="anOrderBean" varStatus="stat" 	items="${orderBeans.allOrders}">
				<c:if test="${anOrderBean.userId==LoginOK.memberId}">
						<TR height='30'>
							<TD width="86" align="center">
							    <a  href='<c:url value='orderDetail.do?memberId=${LoginOK.memberId}&orderNo=${anOrderBean.orderNo}' />'>
							         ${anOrderBean.orderNo}
							    </a>
							  </TD>
							<TD width="100" align="center">${anOrderBean.orderDate}</TD>
							<TD width="80" align="right">${anOrderBean.totalAmount}</TD>
							<TD width="400" align="left">&nbsp;${anOrderBean.shippingAddress}</TD>
							<!--
							<%-- 
							<TD width="100" align="center"><input type="hidden"
								value="${anOrderBean.orderNo}" name="OrderNo"> <input
								type="hidden" value="${anOrderBean.totalAmount}"
								name="totalAmount"> <input type="hidden"
								value="${anOrderBean.orderDate}" name="orderDate"> <input
								type="submit" value="詳細資料"></TD>
								--%>
  						 -->
						</TR>
				</c:if>

			

		</c:forEach>
		<tr height='36'>
			<td align="center" colspan="4"><a href="<c:url value='../index.jsp' />">回首頁</a></td>
		</tr>
	</TABLE>
	</center>
	<!--
		<%-- 
		<c:forEach var="anOrderBean" varStatus="stat"
			items="${orderBeans.allOrders}">
			<c:choose>
				<c:when test="${ stat.count % 2 == 0 }">
					<c:set var="aColor" value="#556677" />
				</c:when>
				<c:otherwise>
					<c:set var="aColor" value="#AA0077" />
				</c:otherwise>
			</c:choose>
			<TR bgColor="${aColor}">
				<TD>${anOrderBean.orderNo}</TD>
				<TD>${anOrderBean.userId}</TD>
				<TD>${anOrderBean.totalAmount}</TD>
				<TD>${anOrderBean.orderDate}</TD>
			</TR>
		</c:forEach>
	</TABLE>
	--%>
	 -->
</body>
</html>