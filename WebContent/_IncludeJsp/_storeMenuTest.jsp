<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
</head>
<!-- include 商家左側列表 -->
<body>
<div class="row">
            <div class="col-md-3">
                <div id="topName"> ${StoreLoginOK['rest_username']} </div>
                <ul id="leftMenu" class="list-group">
                    <li class="list-group-item"><i class="fa fa-user-circle-o fa-fw"></i>Profile</li>
                    <li class="list-group-item">
                        <a href="../_02_storeLogin/_storeLoginProfileEditA.jsp">修改個人資料</a>
                    </li>
                    <li class="list-group-item">
                        <a href="/_Grab_Go/_02_storeLogin/_storeLogOut.jsp">登出</a>
                    </li>
                    <li class="list-group-item"><i class="fa fa-file-text-o fa-fw"></i>本日訂單</li>
                    <li class="list-group-item">
                        <a href="../_02_storeLogin/_storeIndex.jsp">待處理訂單</a>
                    </li>
                    <li class="list-group-item">
                        <a href="../_24_storeOrder/_storeOrderUnpaid.jsp">已完成訂單</a>
                    </li>
                    <li class="list-group-item">
                        <a href="../_24_storeOrder/_storeOrderPaid.jsp">已付款訂單</a>
                    </li>
                    <!-- 查詢訂單功能放入本日訂單頁面 -->
                    <!-- <li class="list-group-item">
                        <a href="#">查詢訂單</a>
                    </li> -->
                    <li class="list-group-item"><i class="fa fa-hand-o-right fa-fw"></i>歷史訂單</li>
                    <li class="list-group-item">
                        <a href="../_24_storeOrder/_storeOrderHistory.jsp">歷史訂單查詢</a>
                    </li>
                    <!-- 餐點熱銷排行一併和日營業總額呈現 -->
                    <!-- <li class="list-group-item">
                        <a href="#">餐點熱銷排行</a>
                    </li> -->
                    <li class="list-group-item"><i class="fa fa-pencil-square-o fa-fw"></i>菜單管理</li>
                    <li class="list-group-item">
                        <a href="../_21_storeMenuSystem/_storeMenuInsertTypeA.jsp">新增餐點類別</a>
                    </li>
                    <li class="list-group-item">
                        <a href="../_21_storeMenuSystem/_storeMenuInsertA.jsp">新增餐點項目</a>
                    </li>
                    <li class="list-group-item">
                        <a href="../_21_storeMenuSystem/_storeMenuEditDeleteA.jsp">修改餐點項目</a>
                    </li>
                    <li class="list-group-item"><i class="fa fa-usd fa-fw"></i>帳務分析</li>
                    <li class="list-group-item">
                        <a href="../_23_storeOrderAnalysis/_storeOrderAnalysisDay.jsp">營業額統計</a>
                    </li>
                </ul>
            </div>

</body>
</html>