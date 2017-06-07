<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>發生錯誤</title>
<style type="text/css">
#main {
	position:absolute;
	top: 90px ;
	left: 180px ;
	}
</style>



</head>
<body >
<c:set var="funcName" value="CHE" scope="session"/>
<jsp:include page="/fragment/top.jsp" />
<P >
<div id='main'>

發生錯誤：${Errors}
<Table  width='580'  style="background:#E5FFCD;border-style:double;border-color:#EF02A4;">
     <TR height='250'>
        <TD width="580" align="CENTER"><A href="../_02_login/login.jsp">重新登入</A>
        </TD>         
     </TR>
</Table>
</div>         
</body>
</html>