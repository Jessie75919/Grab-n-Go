<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript">
function cancelOrder() {
	if (confirm("確定取消此份訂單 ? ") ) {
		// 接收此資料的Servlet會使用 finalDecision 參數的值
		document.forms[0].finalDecision.value = "CANCEL";
		return true;
	} else {
		return false;
	}
}
function reconfirmOrder() {
	if (confirm("確定送出此份訂單 ? ") ) {
		// 接收此資料的Servlet會使用 finalDecision 參數的值
		document.forms[0].finalDecision.value = "ORDER";
		return true;
	} else {
		return false;
	}
}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions"  %>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 取得今天的日期，今天的日期應當在最後確認時才取得 -->
<jsp:useBean   id="today"  class="java.util.Date" scope="session"/> 
<title>訂單明細資訊確認</title>
</head>
<body style="background:#EBFFEB;">
<c:set var="funcName" value="CHE" scope="session"/>
<jsp:include page="/fragment/top.jsp" />
<BR>
請確認下列訊息：
<CENTER>

<FORM action="<c:url value='ProcessOrder.do' />" method="POST">
   <TABLE border='1' width="810" style="background:#F5EBFF; border-color:rgb( 100, 100, 255);border-style: outset;">
      <TR >
         <TD style="text-align:left; border-color: #FFBD32; border-style: ridge;">會員編號：${LoginOK.memberId}</TD>
         <TD style="text-align:left; border-color: #FFBD32; border-style: ridge;">客戶姓名：${LoginOK.name}</TD>
         <TD style="text-align:left; border-color: #FFBD32; border-style: ridge;">訂單日期：<fmt:formatDate value="${today}" pattern="yyyy-MM-dd"/></TD>
      </TR>
      <TR>
         <TD colspan='3' style="text-align:left; border-color: #FFBD32; border-style: ridge;">會員地址：${LoginOK.address}</TD>
      </TR>
      <TR>
         <TD colspan='3' style="text-align:left; border-color: #FFBD32; border-style: ridge;">
           出貨地址：<Input style="background:#ECFFCD;" size="60" type="text" 
                      name="ShippingAddress" value="${LoginOK.address}">
         </TD>
      </TR>
      <TR>
         <TD colspan='3' style="text-align:left; border-color: #FFBD32; border-style: ridge;">
           統一編號：<Input style="background:#ECFFCD;" size="10" type="text" 
                      name="BNO" value="12345678">
         </TD>
      </TR>
      <TR>
         <TD colspan='3' style="text-align:left; border-color: #FFBD32; border-style: ridge;">
           發票抬頭：<Input style="background:#ECFFCD;" size="50" type="text" 
                      name="InvoiceTitle" value="人間文摘股份有限公司台灣分公司" >
         </TD>
      </TR>
    
      <TR>
         <TD colspan='3'>
         
   <TABLE border='1' style="background:#FFE7CD; border-color:rgb( 100, 100, 255); " >
      
     <TR><TH style="text-align:center;font-size: 12pt;" width="350">書籍名稱</TH>
         <TH style="text-align:center;font-size: 12pt;" width="80">作者</TH>
         <TH style="text-align:center;font-size: 12pt;" width="80">出版社</TH>
         <TH style="text-align:center;font-size: 12pt;" width="80">單價</TH>
         <TH style="text-align:center;font-size: 12pt;" width="60">數量</TH>
         <TH style="text-align:center;font-size: 12pt;" width="110">小計</TH></TR>
     
     <c:forEach varStatus="vs" var="anEntry" items="${ShoppingCart.content}">
                                                    
        <TR height='16'>
          <TD style="text-align:left  ;font-size: 11pt;">${anEntry.value.title}</TD>
          <TD style="text-align:center;font-size: 11pt;">${fn:substring(anEntry.value.author, 0, 3)}</TD>
          <TD style="text-align:center;font-size: 11pt;">${fn:substring(anEntry.value.companyName, 0, 2)}</TD>
          <TD style="text-align:right ;font-size: 11pt;"><fmt:formatNumber value="${anEntry.value.price * anEntry.value.discount }" pattern="#,###" />元</TD>
          <TD style="text-align:right ;font-size: 11pt;"> ${anEntry.value.qty}   </TD>
          <TD style="text-align:right ;font-size: 11pt;"><fmt:formatNumber value="${anEntry.value.price * anEntry.value.discount * anEntry.value.qty}" pattern="#,###,###" />元</TD>
        </TR>
     </c:forEach>
     
        <TR height='16'>
          <TD style="text-align:right;font-size: 11pt;" colspan='5' >合計金額：</TD>
          <TD style="text-align:right;font-size: 11pt;" ><fmt:formatNumber value="${ShoppingCart.subtotal}" pattern="#,###,###" />元</TD>
                  
        </TR>
        <TR>
          <TD colspan='5' style="text-align:right;font-size: 11pt;" >營業稅：</TD>
          <c:set var="VAT" value="${ShoppingCart.subtotal*0.05 + 0.0001}"/>
          <TD style="text-align:right;font-size: 11pt;" > <fmt:formatNumber value="${VAT}" pattern="#,###,###" />元</TD>
                   
        </TR>
        <TR>
          <TD colspan='5' style="text-align:right;font-size: 11pt;" >總計金額：</TD>
          <TD style="text-align:right;font-size: 11pt;color:#AA0200;" ><fmt:formatNumber value="${ShoppingCart.subtotal + VAT }" pattern="#,###,###" />元</TD>
                   
        </TR>
   </TABLE>
 
         </TD>
      </TR>
 
   </TABLE><P/>
   <INPUT TYPE="hidden" name="finalDecision"  value="">   
   <INPUT TYPE="SUBMIT" name="OrderBtn"  value="確定送出" onclick="return reconfirmOrder();">
   <INPUT TYPE="SUBMIT" name="CancelBtn" value="取消訂單" onclick="return cancelOrder();">
</FORM>
</CENTER>
</body>
</html>