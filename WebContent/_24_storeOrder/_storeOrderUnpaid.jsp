<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <!--載入icon-->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" type="text/css">
    <!--載入Bootstrap-->
    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <link rel="stylesheet" href="../css/_storeIndex.css">
    <title>已完成訂單|本日訂單-Grab &amp; Go</title>
</head>
<!--商家登入成功-->
<!--已完成訂單頁面-->
<body>
    <!--logo-->
    <header>
        <div class="logoArea">
            <img src="../images/share/logo.svg" alt="">
        </div>
        <!--進入區塊-->
        <div class="topTitle">
            <h2>本日訂單</h2>
        </div>
    </header>
<jsp:useBean id="orderBeans" class="_05_orderProcess.model.OrderDAO" scope="page"/>
<c:set target="${orderBeans}" property="restUsername" value="${StoreLoginOK['rest_username']}"/>
    <!--店家profile-->
    <section class="container">
        <div class="row">
           <!-- 店家profile -->
		<jsp:include page="../_IncludeJsp/Store_Profile.jsp" />
            <div class="col-md-9">
                <div>
                    <h3>> 已完成訂單</h3>
                    <!-- <span><h4>請輸入欲查詢訂單的顧客姓名：</h4></span> -->
                    <!-- 本日訂單搜尋 -->
                    <jsp:include page="../_IncludeJsp/StoreOrder_search.jsp"/>
                </div>
            </div>
    </section>

    <!--左側列表-->
    <section id="leftMenu" class="container">
    		<jsp:include page="../_IncludeJsp/_storeMenuTest.jsp"/>
            <!-- 表格開始 -->
            <div id="middleForm" class="col-md-9">
                <!--訂單狀態按鈕區塊-->
                <div>
                    <!--<ul class="nav nav-tabs nav-justified">-->
                    <ul class="nav nav-pills nav-justified">
                    		<li role="presentation"><a href="_storeOrderSearchResult.jsp">訂單查詢</a></li>
                        <li role="presentation"><a href="../_02_storeLogin/_storeIndex.jsp">待處理訂單</a></li>
                        <li role="presentation" class="active"><a href="#">已完成訂單</a></li>
                        <li role="presentation"><a href="_storeOrderPaid.jsp">已付款訂單</a></li>
                    </ul>

                </div>
                <hr>
                <!--</div>-->
            <!--<div class="orderTable" >-->
                <table id="orderTable">
                    <tr>
                        <th>訂購日期</th>
                        <th>取餐時間</th>
                        <th>取餐顧客</th>
                        <th>訂單編號</th>
                        <th>總金額</th>
                        <th>訂單狀態</th>
                        <th>Action</th>
                    </tr>
            		<c:forEach var="anOrderBean" items="${orderBeans.storeOrdersUnpaid}">
                    <tr>
                        <td nowrap=""><fmt:formatDate type = "both" pattern="yyyy-MM-dd HH:mm" value="${anOrderBean.ord_time}"/></td>
                        <td nowrap=""><fmt:formatDate type = "both" pattern="yyyy-MM-dd HH:mm" value="${anOrderBean.ord_pickuptime}"/></td>
                        <td>${anOrderBean.m_pickupname}</td>
                        <td><input style="display:none;" id="${anOrderBean.ord_id}" name="ordId" value="${anOrderBean.ord_id}"><a href="../_24_storeOrder/_storeOrderDetails.jsp?ord_id=${anOrderBean.ord_id}&ord_totalPrice=${anOrderBean.ord_totalPrice}">${anOrderBean.ord_id}</a></td>
                        <td>$${anOrderBean.ord_totalPrice}</td>
                        <td><input style="display:none;" id="ordStatus" name="ordStatus" value="${anOrderBean.ord_status}"><a href="_storeOrderPaid.jsp?" onClick="updateOrdStatus(${anOrderBean.ord_id})">${anOrderBean.ord_status}</a></td>
                        <td><a href="_storeOrderPaid.jsp" onClick="updateOrdStatus(${anOrderBean.ord_id})">結帳</a></td>
                    </tr>
                   </c:forEach> 
                </table>
                <hr>
            </div>
        </div>
        </div>
        <div class="row">

        </div>
        </div>
        </div>
    </section>
    <script>
   /*  var ordId = document.getElementById("ordId").value; */
    var ordStatus = document.getElementById("ordStatus").value;
    
 	/* 訂單狀態改變 */
    		function updateOrdStatus(ordId){
 		alert("ordId #:" + ordId);
    			if(confirm("結帳嗎？")){
    				//訂單已完成
    				var xhr = new XMLHttpRequest();
    				xhr.open('GET','../updateOrderStatus.do?ordId=' + ordId + '&ordStatus=' + ordStatus,true);
    				xhr.send();
    	             alert("顧客已付款囉！")
    	         }else{
    	             alert("顧客尚未付款呦～")
    	         }
    		} 
    
    </script>

</body>

</html>