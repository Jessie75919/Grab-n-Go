<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<!-- 商家本日訂單 -->
<!-- 查詢訂單功能 -->
<body>

<!-- 訂單搜尋 -->
                    <span><h4>請輸入欲查詢訂單的顧客姓名：</h4></span>
                    <form class="form-inline" action="${pageContext.servletContext.contextPath}/_02_storeLogin/SearchOrder.do" method="get">
                            <div class="input-group">
                                <div class="input-group-addon"><i class="fa fa-search" aria-hidden="true"></i></div>
                                <input type="text" class="form-control" name= "mPickupName" id="mPickupName" placeholder="顧客姓名">
                           		<input style="display: none;" type="text" name="restUsername" id="restUsername" value="${StoreLoginOK['rest_username']}">
                            </div>											                   
                        <input type="submit" id="submit" name="submit" class="btn btn-primary" value="搜尋"></input>
                    </form>

</body>
</html>