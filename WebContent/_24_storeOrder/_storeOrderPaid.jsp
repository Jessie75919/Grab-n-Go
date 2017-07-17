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
    <title>已付款訂單|本日訂單-Grab &amp; Go</title>
    <jsp:useBean id="orderBeans" class="_05_orderProcess.model.OrderDAO" scope="page"/>
	<c:set target="${orderBeans}" property="restUsername" value="${StoreLoginOK['rest_username']}"/>
</head>
<!--商家登入成功-->
<!--已付款訂單頁面-->
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
		<jsp:include page="../_IncludeJsp/Store_Profile.jsp" />
            <div class="col-md-9">
                <div>
                    <h3>> 已付款訂單</h3>
                    <!-- 本日訂單搜尋 -->
                    <jsp:include page="../_IncludeJsp/StoreOrder_search.jsp"/>
                    <!-- <span><h4>請輸入欲查詢訂單的顧客姓名：</h4></span>
                    <form class="form-inline">
                            <div class="input-group">
                                <div class="input-group-addon"><i class="fa fa-search" aria-hidden="true"></i></div>
                                <input type="search" class="form-control" id="" placeholder="顧客姓名">
                            </div>
                        <input type="submit" class="btn btn-primary" value="搜尋"></input>
                    </form> -->
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
                        <li role="presentation"><a href="_storeOrderUnpaid.jsp">已完成訂單</a></li>
                        <li role="presentation" class="active"><a href="#">已付款訂單</a></li>
                    </ul>

                </div>
                <hr>
                <!--</div>-->
            <!--<div class="orderTable" >-->
                <table id="orderTable">
                    <tr>
                        <th>訂購日期</th>
                        <th>取餐顧客</th>
                        <th>訂單編號</th>
                        <th>總金額</th>
                        <th>訂單狀態</th>
                        <th>&nbspAction</th>
                    </tr>
                    <!-- 每筆訂單資訊, 預設一頁顯示15筆 -->
                    <c:forEach var="anOrderBean" items="${orderBeans.storeOrdersPaid}">
                    <tr>
                        <td nowrap=""><fmt:formatDate type = "both" pattern="yyyy-MM-dd HH:mm" value="${anOrderBean.ord_time}"/></td>
                        <td>${anOrderBean.m_pickupname}</td>
                        <td>
                        <input style="display:none;" id="${anOrderBean.ord_id}" name="ordId" value="${anOrderBean.ord_id}">
                        <a href="_storeOrderDetails.jsp?ord_id=${anOrderBean.ord_id}&ord_totalPrice=${anOrderBean.ord_totalPrice}">${anOrderBean.ord_id}</a></td>
                        <td>$${anOrderBean.ord_totalPrice}</td>
                        <td>${anOrderBean.ord_status}</td>
                        <td><input id="${anOrderBean.ord_id}" onclick="clickMe(${anOrderBean.ord_id})" type="button" class="btn btn-default" data-toggle="modal" data-target=".bs-example-modal-lg" value="檢視明細"></td>
                        <%-- <td id="cancelB"><a href="_storeOrderDetails.jsp?ord_id=${anOrderBean.ord_id}&ord_totalPrice=${anOrderBean.ord_totalPrice}">檢視明細</a></td> --%>
                    </tr>
                    </c:forEach>
                </table>
                <hr>
            <!-- 訂單明細  -->
             <div id="detailsModal" class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
                    <div class="modal-dialog modal-md" role="document">
                       <!--  popup 內容  -->
                        <div class="modal-content">
                            <!-- popup header --> 
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                <h4 class="modal-title">訂單明細</h4>
                            </div>
                            <div id="detailsContent" style="padding: 30px;">
                                <table id="tableD">
                                    <tr>
                                        <th>顧客名稱</th>
                                        <th>餐點名稱</th>
                                        <th>餐點編號</th>
                                        <th>備註</th>
                                        <th>數量</th>
                                        <th>單價</th>
                                        <th>Subtotal</th>
                                    </tr>
                                	 </table>
                                 <span><h4>總金額：</h4></span> 
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">

        </div>
        </div>
        </div>
    </section>
    
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="../js/bootstrap.min.js"></script>
    <script>
    //var orderId = document.getElementById("ordId").value;
/*     $(function() {
        $('#detailsModal').on('show.bs.modal', */
     var detailsTable = document.getElementById("tableD");
        function clickMe(e) {
        	//alert("target = " + e);
        	table.innerHTML = 
        }
        	
       /*  })
    }); */
</script>
</body>

</html>