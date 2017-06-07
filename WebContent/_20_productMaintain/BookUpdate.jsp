<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${AppName}</title>
<link href="../style.css" rel="stylesheet" type="text/css" />
<jsp:useBean id="caBean" class="_03_listBooks.model.CompanyAccessBean" scope="page" />
<jsp:useBean id="bab"    class="_03_listBooks.model.BookAccessBean" scope="page" >
   <c:choose>
      <c:when test="${not empty param.BOOKID}">
         <jsp:setProperty name="bab" property='bookId' value='${param.BOOKID}' />
         <c:set var="bean" value='${bab.book}' scope='session' />
      </c:when>
      <c:otherwise>
      </c:otherwise>
   </c:choose> 
</jsp:useBean>


<script type="text/javascript">
function setFocus()
{
     document.getElementById("title").focus();
}

function confirmDelete() {
	if (confirm("確定刪除此項書籍資料(書號:${bean.bookNo})?") ) {
		document.forms[0].action="BookDelete.do?pageNo=${param.pageNo}&bookID=${bean.bookId}&bookNo=${bean.bookNo}" ;
		document.forms[0].method="POST";
		document.forms[0].submit();
	} else {
	}
}

function updateBook() {
    document.forms[0].action="BookUpdate.do?pageNo=${param.pageNo}" ;
	document.forms[0].method="POST";
	document.forms[0].submit();
}

</script>

</head>
<body onload="setFocus()" background="../images/bookMaintain.jpg">
<!-- 引入共同的頁首 -->  
<jsp:include page="/fragment/top.jsp" />
<div id='main'>
<center>
<c:choose>
   <c:when test="${not empty param.BOOKID }">
   <!-- 
     <c:out value="第一次"/>
      -->
     <c:set var="title" value='${bean.title}' />
     <c:set var="author" value='${bean.author}' />
     <c:set var="price" value='${bean.price}' />
     <c:set var="bookNo" value='${bean.bookNo}' />
     <c:set var="companyID" value='${bean.companyId}' />
   </c:when>
   <c:otherwise>
   <!-- 
     <c:out value="第二次"/>
      -->
     <c:set var="title" value='${bean.title}' />
     <c:set var="author" value='${bean.author}' />
     <c:set var="price" value='${bean.price}' />
     <c:set var="bookNo" value='${bean.bookNo}' />
     <c:set var="companyID" value='${bean.companyId}' />
   </c:otherwise>
</c:choose>
<div id="afterBookInsert">
     <font color='red' size='-1'>${ErrMsg.Exception}</font><br/>
  </div>
<div id="backToBookMaintainList">
     <a href="DisplayPageProducts?pageNo=${param.pageNo}">回維護首頁</a>    
</div>     
<form id="form1" name="form1" 
      method="post" action="BookUpdate.do" 
      enctype="multipart/form-data"
>
  
<table  class="table_color" width="608" border="2" align="center" 
        cellpadding="2" cellspacing="2" bordercolorlight="#FFFFFF" 
        bordercolordark="#330033">
        
   <tr height='40'>
       <td colspan="4" align="center" valign="bottom"> 
           <TABLE width="680" BORDER='0' style="background:#ffE4C4">
            <TR height='5' >
                <TD align='center' >&nbsp;</TD>
            </TR>
            <TR height='20' >
                <TD  align='center' >
                   <FONT color='#8000FA' size='+2' style="font-weight:900;" >
                      更新書籍資料
                   </FONT>
                </TD>
            </TR>
            <TR height='5' >
                <TD align='center' >&nbsp;</TD>
            </TR>
         </TABLE>
       </td>
   </tr>
   <tr height='36'>
      <td width="45" align="right" class="title_font">書名</td>
      <td colspan="3"> 
          <input name="title" class='InputClass' type="text" id="title" 
             value="${title}" size="50" />
          <font color='red' size='-1'>
              ${ErrMsg.errTitle}
          </font>
      </td>
   </tr>
   <tr height='36'>
      <td width="45" align="right" class="title_font">作者</td>
      <td width="312" >     
          <input name="author"  class='InputClass'  type="text" id="author" 
             value="${author}" size="26" />
          <font color='red' size='-1'>
             ${ErrMsg.errAuthor}
          </font>
      </td>
      <td width="52" align="right" class="title_font">價格</td>
      <td width="161">
          <input name="price"  class='InputClass' type="text" id="price" 
             value="${price}" size="4" />
          <font color='red' size='-1'>
             ${ErrMsg.errPrice}
          </font>
       </td>
   </tr>
   <tr height='36'>
      <td width="45" class="title_font" ><div align="right">書號</div></td>
      <td width="312">
          <input class='InputClass' name="bookNo" type="text" id="bookNo" 
             value="${bookNo}" size="6" />
          <font color='red' size='-1'>
             ${ErrMsg.errBookNo}
          </font>
      </td>
      <td width="52" class="title_font" ><div align="right">出版社</div></td>
      <td width="161">
            <c:set target="${caBean}" property="tagName" value='companyID' />
            <c:set target="${caBean}" property="selected" value='${companyID}' />
            ${caBean.selectTag}
          <font color='red' size='-1'>
              ${ErrMsg.errCompanyID}
          </font>
       </td>
    </tr>
   <tr height='120'>
      <td width="45" align="right" class="title_font">圖片</td>
      <td colspan='3'>
        <table border='0'>  
           <tr>
             <td><img height='120' width='96' 
                     src='${pageContext.servletContext.contextPath}/_00_init/getImage?id=${bean.bookId}&type=BOOK'  />
             </td>
             <td colspan='2'>
                 <input style="background:#FFFFFF" class='InputClass'  type="file" 
                      name="uploadFile" size="40" />
                 <font color='red' size='-1'>
                    ${ErrMsg.errPicture} 
                 </font>
             </td>
           </tr>
        </table>
      </td>
</tr>
<tr height='35'>
<td colspan='4' align='center'>
        <input name="bookID" type="hidden" id="bookID" value="${param.BOOKID }>" />        
        <input type="button" name="update" value="修改" onclick='updateBook()'/>
        &nbsp;&nbsp;&nbsp;
        <input type="button" name="delete" value="刪除" onclick="confirmDelete()" /> 
  </td>
  </tr>
</table>
  <div id="deleteMsg">
         ${ErrMsg.errDBMessage}		 
        <input type="hidden" name="pageNo" value="${param.pageNo}" />
  </div>
</form>
<p>&nbsp;</p>
</center>
</div>
</body>
</html>