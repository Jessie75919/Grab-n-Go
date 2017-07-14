<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <!--載入icon-->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" type="text/css">
    <!--載入Bootstrap-->
    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <link rel="stylesheet" href="../css/_storeIndex.css">
    <script src="../javascript/jquery-3.2.1.min.js"></script>
    <title>待處理訂單|本日訂單-Grab &amp; Go</title>
</head>
<!-- 商家已登入頁面 -->
<!-- 待處理訂單頁面 -->

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
<jsp:useBean id="orderBeans" class="_05_orderProcess.model.OrderDAO"/>
<!-- 商家 -->
<c:set target="${orderBeans}" property="restUsername" value="${StoreLoginOK['rest_username']}"/>
    <!--店家profile-->
    <section class="container">
        <div class="row">
            <!-- 店家profile -->
            <div class="col-md-3">
                <center>
                    <!-- <img class="img-rounded" src="../images/restImage/af_logo.jpg"> -->
                    <a href="${pageContext.servletContext.contextPath}/_07_storePage/getOneRest.do?id=${StoreLoginOK['rest_id']}">
                    <img src='${pageContext.servletContext.contextPath}/_00_init/getImageA?id=${StoreLoginOK["rest_username"]}&type=restaurant&loc=logo'
                        alt="Photo" title="Photo">
                        </a>
                    <br>
                </center>
            </div>
            <div class="col-md-9">
                <div>
                    <h3>> 待處理訂單</h3>
                    <!-- 訂單搜尋 -->
                    <span><h4>請輸入欲查詢訂單的顧客姓名：</h4></span>
                    <form class="form-inline" action="SearchOrder.do" method="get">
                            <div class="input-group">
                                <div class="input-group-addon"><i class="fa fa-search" aria-hidden="true"></i></div>
                                <input type="text" class="form-control" name= "mPickupName" id="mPickupName" placeholder="顧客姓名">
                           		<input style="display: none;" type="text" name="restUsername" id="restUsername" value="${StoreLoginOK['rest_username']}">
                            </div>											                   
                        <input type="submit" id="submit" name="submit" class="btn btn-primary" value="搜尋"></input>
                    </form>
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
                     	<li role="presentation"><a href="../_24_storeOrder/_storeOrderSearchResult.jsp">訂單查詢</a></li>
                        <li id="ordInProgress" role="presentation" class="active"><a href="#">待處理訂單</a></li>
                        <li role="presentation"><a href="../_24_storeOrder/_storeOrderUnpaid.jsp">已完成訂單</a></li>
                        <li role="presentation"><a href="../_24_storeOrder/_storeOrderPaid.jsp">已付款訂單</a></li>
                    </ul>

                </div>
                <hr>
                <!--</div>-->
           <!--  <div id="orderResult" ></div> -->
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
                    <!-- 顯示訂單資訊 -->
                    <!-- 取得訂單 -->
                   <c:forEach var="anOrderBean" items="${orderBeans.storeOrdersInPgogress}">
                    <tr>
                        <td nowrap=""><fmt:formatDate type = "both" pattern="yyyy-MM-dd HH:mm" value="${anOrderBean.ord_time}"/></td>
                        <td  nowrap=""><fmt:formatDate type = "both" pattern="yyyy-MM-dd HH:mm" value="${anOrderBean.ord_pickuptime}"/></td>
                        <td >${anOrderBean.m_pickupname}</td>
                        <td >
                            <input style="display:none;" id="${anOrderBean.ord_id}" name="ordId" value="${anOrderBean.ord_id}">
                            <a href="../_24_storeOrder/_storeOrderDetails.jsp?ord_id=${anOrderBean.ord_id}&ord_totalPrice=${anOrderBean.ord_totalPrice}&ordStatus=${anOrderBean.ord_status}">${anOrderBean.ord_id}</a>
                        </td>
                        <td > $${anOrderBean.ord_totalPrice}</td>
                        <td >
                            <input style="display:none;" id="ordStatus" name="ordStatus" value="${anOrderBean.ord_status}">
                            <a href="../_24_storeOrder/_storeOrderUnpaid.jsp?" id="ABC" onClick="updateOrdStatus(${anOrderBean.ord_id})">${anOrderBean.ord_status}</a>
                        </td>
                        <td id="cancelB"><a href="_storeIndex.jsp" onClick="ordCancel(${anOrderBean.ord_id})">取消訂單</a></td>
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
    var ordStatus = document.getElementById("ordStatus").value;
    // var restUsername = document.getElementById("restUsername").value;
     
 	/* 訂單狀態改變 */
    		function updateOrdStatus(tt){
                alert('target='+tt);

    			if(confirm("餐點完成了嗎？ 可以等待顧客來取餐囉 ~")){
    				//訂單已完成
    				var xhr = new XMLHttpRequest();
    				xhr.open('GET','../updateOrderStatus.do?ordId=' + tt + '&ordStatus=' + ordStatus,true);
    				xhr.send();
    	             //alert("餐點確定完成！")
    	         }else{
    	             alert("餐點尚未完成")
    	         }
    		} 
    		
    	/* 取消訂單 */
    	function ordCancel(cancelBtn){
    		if(confirm("確定取消此筆訂單嗎？")){
    			//var cancelBtn = 
    			alert('cancel order #: ' + cancelBtn);
    			var xhr = new XMLHttpRequest();
    			xhr.open('GET','CancelOrder.do?ordId=' + cancelBtn,true);
    			xhr.send();
    			alert(" 訂單已取消囉！")	
    		}else{
    			alert("請繼續完成訂單呦 ~")
    		}
    	}
    
    </script>

</body>

</html>