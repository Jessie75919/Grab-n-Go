<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${empty LoginOK}">
	<c:redirect url="/_02_login/login.jsp" />
</c:if>
<jsp:useBean id="orderBeans" class="_04_ShoppingCart.model.OrderDAO"
	scope="page" />


<title>訂單列表</title>
<style type="text/css">
#main {
	position: absolute;
	top: 110px;
	left: 210px;
}
</style>
</head>
<body style="background: #EBFFEB;">
	<!-- 引入共同的頁首 -->
	<jsp:include page="/fragment/top.jsp" />

	<TABLE border="2" bordercolor="blue">
		<tr>
			<th colspan="4" align="center">${LoginOKMemberBean.name}的訂單</th>
		</tr>
		<c:forEach var="anOrderBean" varStatus="stat"
			items="${orderBeans.allOrders}">
			<c:choose>
				<c:when
					test="${anOrderBean.userId==LoginOKMemberBean.userId}">
					
					<FORM action="List.do" method="post">
						<TR>
							<TD width="500px" align="center">訂單編號：${anOrderBean.orderNo}</TD>
							<TD width="500px" align="center">總金額：${anOrderBean.totalAmount}</TD>
							<TD width="500px" align="center">訂購日期：${anOrderBean.orderDate}</TD>
							<TD width="500px" align="center"><input type="hidden"
								value="${anOrderBean.orderNo}" name="OrderNo"> <input
								type="hidden" value="${anOrderBean.totalAmount}"
								name="totalAmount"> <input type="hidden"
								value="${anOrderBean.orderDate}" name="orderDate"> <input
								type="submit" value="詳細資料"></TD>
						</TR>
					</FORM>
				</c:when>
				
			</c:choose>

		</c:forEach>
		<tr>
			<td align="center" colspan="4"><a href="../index.jsp">回首頁</a></td>
		</tr>
	</TABLE>

</body>
</html>