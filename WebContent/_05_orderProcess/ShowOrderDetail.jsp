<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:if test="${empty LoginOK}">
	<c:redirect url="/_02_login/login.jsp" />
</c:if>
<title>個人訂單明細</title>
<style type="text/css">
#main {
	position: absolute;
	top: 110px;
	left: 210px;
}
</style>
</head>
<body style="background: #EBFFEB;">
<jsp:include page="/fragment/top.jsp" />
<p />
<TABLE border="2" bordercolor="blue" style="background:#F0E4F4">
	<tr height='50'>
		<th align="center" colspan="7"><h3>${LoginOK.name}的訂單明細</h3></th>
	</tr>
	<tr height='36'>
		<td colspan="7">
		<table border='0'>
			<tr>
				<td align="Left" width="500px">出貨地址:${OrderBean.shippingAddress}</td>
				<td align="center" width="200px">訂購日期:${OrderBean.orderDate}</td>


				<td align="right" width="150px">訂單編號:${OrderBean.orderNo}</td>
			</tr>
		</table>
		</td>
	</tr>
	<tr height='36'>
		<th width="300px" align="center">書籍編號</th>
		<th width="1200px" align="center">書籍資訊</th>
		<th width="100px" align="center">單價</th>
		<th width="100px" align="center">數量</th>
		<th width="100px" align="center">總價</th>
		<th width="50px" align="center">折扣</th>
		<th width="100px" align="center">售價</th>
	</tr>
	<c:set var="subtotal" value="0" />
	<c:forEach var="aBean" varStatus="stat" items="${OrderBean.items}">
		<c:choose>
			<c:when test="${ stat.count % 2 == 0 }">
				<c:set var="aColor" value="#E6FFA0" />
			</c:when>
			<c:otherwise>
				<c:set var="aColor" value="#EBFFEB" />
			</c:otherwise>
		</c:choose>

        
		<tr bgColor="${aColor}" height='30'>
			<td  align="center">${aBean.bookId}</td>
			<td  align="left">${aBean.description}</td>
			<td  align="right">${aBean.unitPrice}&nbsp;</td>
			<td  align="right">${aBean.amount}&nbsp;</td>
			<td  align="right">${aBean.unitPrice*aBean.amount}&nbsp;</td>
			<td  align="center">${aBean.discount}&nbsp;</td>
			<td  align="right">
			
			<fmt:formatNumber	
				value="${aBean.unitPrice*aBean.discount*aBean.amount}"
				pattern="#,###,###" />元</td>
				
				
				<c:set var="subtotal" value="${ subtotal + aBean.unitPrice*aBean.discount*aBean.amount }" />
		</tr>


	</c:forEach>
	<tr height='30'>
		<TD align="center" rowspan="3" colspan="5">&nbsp;</TD>
		<TD width="300px" align="right">合　計</TD>
		<TD width="300px" align="right">
		<fmt:formatNumber value="${subtotal}" pattern="#,###,###" />元</TD>
	</tr>
		<tr height='30'>
		
		<TD width="300px" align="right">營業稅</TD>
		<c:set var="VAT" value="${subtotal*0.05 + 0.0001}"/>
		<TD width="300px" align="right"><fmt:formatNumber value="${VAT}" pattern="#,###,###" />元</TD>
	</tr>
	<tr height='40'>
		<TD width="300px" align="right">總金額</TD>
		<TD width="300px" align="right">
		<fmt:formatNumber value="${OrderBean.totalAmount}" pattern="#,###,###" />元</TD>
	</tr>
</TABLE>
<p/>
<center>
<a href="<c:url value='OrderList.jsp' />">回上一頁</a>&nbsp;&nbsp;<a href="<c:url value='../index.jsp' />">回首頁</a>
</center>
</body>
</html>



