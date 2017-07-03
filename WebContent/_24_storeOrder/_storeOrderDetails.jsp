<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
    <jsp:useBean id="orderDetailsDAOBean" class="_05_orderProcess.model.OrderItemDAO" />
    <c:set target="${orderDetailsDAOBean}" property="ord_id" value="${param.ord_id}"></c:set>

    <title>GrabAndGo Order Details</title>
</head>
<!--點選訂單明細後的頁面-->
<!--商家訂單明細頁面-->

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
    <!--店家profile-->
    <section class="container">
        <div class="row">
            <!-- 店家profile -->
            <div class="col-md-3">
                <center>
                    <!-- <img class="img-rounded" src="../images/restImage/af_logo.jpg"> -->
                    <img src='${pageContext.servletContext.contextPath}/_00_init/getImageA?id=${StoreLoginOK["rest_username"]}&type=restaurant&loc=logo'
                        alt="Photo" title="Photo">
                    <br>
                </center>
            </div>
            <div class="col-md-9">
                <div>
                    <h3>> 訂單明細</h3>
                    <h4 style="font-weight: bolder;">訂單編號：${param.ord_id}</h4>
                </div>
            </div>
    </section>

    <!--左側列表-->
    <section id="leftMenu" class="container">
        <jsp:include page="../_IncludeJsp/_storeMenuTest.jsp" />
            <!-- 表格開始 -->
            <div id="middleForm" class="col-md-9">
                <!--訂單狀態按鈕區塊-->
                <div>
                    <!--<ul class="nav nav-tabs nav-justified">-->
                    <ul class="nav nav-pills nav-justified">
                        <li role="presentation"><a href="../_02_storeLogin/_storeIndex.jsp">待處理訂單</a></li>
                        <li role="presentation"><a href="_storeOrderUnpaid.jsp">已完成訂單</a></li>
                        <li role="presentation"><a href="_storeOrderPaid.jsp">已付款訂單</a></li>
                    </ul>

                </div>
                <hr>
                <!--</div>-->
            <!--<div class="orderTable" >-->
                <table id="orderTable">
                    <tr>
                        <th>取餐顧客</th>
                        <th>餐點名稱</th>
                        <th>餐點編號</th>
                        <th>備註</th>
                        <th>數量</th>
                        <th>單價</th>
                        <th>Subtotal</th>
                    </tr>
                    <!-- 訂單明細-->
                    <c:forEach  var="orderDetailsBean" items="${orderDetailsDAOBean.orderDetailsForStore}">
                    <tr>
                        <td>${orderDetailsBean.m_pickupname}</td>
                        <td>${orderDetailsBean.item_name}</td>
                        <td>${orderDetailsBean.prod_id}</td>
                        <td>${orderDetailsBean.item_note}</td>
                        <td>${orderDetailsBean.item_amount}</td>
                        <td>${orderDetailsBean.item_price}</td>
                        <td>${orderDetailsBean.item_price}</td>
                    </tr>
                    </c:forEach>
                </table>
                <hr>
                <h4 class="totalPrice">總金額：＄${param.totalPrice}</h4>
            </div>
            <div id="checkOut" style="display: inline;">
            <!--  <input class="btn btn-primary" type="submit" value="顧客結帳"> -->
            <a href="_storeOrderPaid.jsp" class="btn btn-primary">顧客結帳</a>
            <a href="../_02_storeLogin/_storeIndex.jsp" class="btn btn-primary">回本日訂單</a>
            </div>           
        </div>
        </div>
        <div class="row">

        </div>
        </div>
        </div>
    </section>

</body>

</html>