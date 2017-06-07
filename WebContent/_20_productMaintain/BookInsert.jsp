<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${AppName}</title>
<link href="../style.css" rel="stylesheet" type="text/css" />
<jsp:useBean class="_03_listBooks.model.CompanyAccessBean" id="caBean">
    
</jsp:useBean>
<script type="text/javascript">
function setFocus(fld) {
     document.getElementById(fld).focus();
}
</script>

</head>
<body onload="setFocus('title')" background="../images/bookMaintain.jpg">
<!-- 引入共同的頁首 -->  
<jsp:include page="/fragment/top.jsp" />
<div id='main'>
<center>
  <div id="afterBookInsert">
     <font color='red' size='-1'>${successMsg.success}${ErrMsg.Exception}</font><br/>
  </div>
  <div id="backToBookMaintainList">
      <a href="DisplayPageProducts">回維護首頁</a>   
  </div>
<!-- 上傳檔案時<form>標籤的 enctype屬性必須是 "multipart/form-data" -->
<!-- 而且method屬性必須是 "post" -->
<form  id="form1" name="form1" method="post" 
       action="BookInsert.do"  enctype="multipart/form-data" >
  
<table  class="table_color" width="680" border="2" align="center" cellpadding="2" cellspacing="2" bordercolorlight="#FFFFFF" bordercolordark="#330033">
   <tr height='40'>
       <td colspan="4" align="center" valign="bottom"> 
           <TABLE width="680" BORDER='0' style="background:#ffE4C4">
            <TR height='5' >
                <TD align='center' >&nbsp;</TD>
            </TR>
            <TR height='20' >
                <TD  align='center' >
                   <FONT color='#8000FA' size='+2' style="font-weight:900;" >
                      新增書籍資料
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
              value="${requestScope.title}" size="50" />
           <font color='red' size='-1'>
              ${ErrMsg.errTitle}
           </font>
       </td>
    </tr>
    <tr height='36'>
       <td width="45" align="right" class="title_font">作者</td>
       <td width="350" >
           <input name="author"  class='InputClass'  type="text" id="author" 
              value="${requestScope.author}" size="26" />
           <font color='red' size='-1'>
              ${ErrMsg.errAuthor}
           </font>
       </td>
       <td width="64" align="right" class="title_font">價格</td>
       <td width="210">
           <input name="price"  class='InputClass' type="text" id="price" 
              value="${requestScope.price}" size="5" />
           <font color='red' size='-1'>
              ${ErrMsg.errPrice}
           </font>
       </td>
    </tr>
    <tr height='36'>
        <td width="45" class="title_font" ><div align="right">書號</div></td>
        <td width="350">
            <input class='InputClass' name="bookNo" type="text" id="bookNo" 
               value="${requestScope.bookNo}" size="6" />
            <Font color='red' size='-1'>
               ${ErrMsg.errBookNo}
            </Font>
        </td>
        <td width="64" class="title_font" ><div align="right">出版社</div></td>
        <td width="161">
            <c:set target="${caBean}" property="tagName" value='companyID' />
            <c:if test="${ not empty requestScope.cID}">
               <c:set target="${caBean}" property="selected" value='${requestScope.cID}' />
            </c:if>
            ${caBean.selectTag }
            <font color='red' size='-1'>
                ${ErrMsg.errCompanyID}
            </font>
        </td>
    </tr>
    <tr height='36'>
        <td width="45" align="right" class="title_font">圖片</td>
        <td colspan="3">
            <input style="background:#FFFFFF" class='InputClass'  type="file" 
            name="uploadFile" size="40" />
            <font color='red' size='-1'>${ErrMsg.errPicture}</font></td>
    </tr>
    <tr height="36" >
      <td height="61" colspan="6" align="center">
          
         <input type="submit" name="Submit" value="新增" />
      </td>
    </tr>
  </table>
</form>
</center>
</div>
<p>&nbsp;</p>
<c:remove var="ErrMsg" scope='session'/>
</body>
</html>