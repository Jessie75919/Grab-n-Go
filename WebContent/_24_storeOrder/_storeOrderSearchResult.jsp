<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
    <script src="../javascript/jquery.min_2.1.1.js"></script>
    <title>查詢訂單|本日訂單-Grab &amp; Go</title>
    <style type="text/css">
    	.new{
    		background-color: #ffb84d;
    	}
    </style>
</head>
<!-- 商家已登入頁面 -->
<!-- 查詢訂單, 顯示結果頁面 -->

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
<%-- <c:set target="${orderBeans}" property="mPickupName" value="${param.mPickupName}"/> --%>
    <!--店家profile-->
    <section class="container">
        <div class="row">
          <!-- 店家profile -->
		<jsp:include page="../_IncludeJsp/Store_Profile.jsp" />
            <div class="col-md-9">
                <div>
                    <h3>> 查詢訂單</h3>
                    <!-- 訂單搜尋 -->
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
    		<jsp:include page="../_IncludeJsp/_storeMenuTest.jsp" />
            <!-- 表格開始 -->
            <div id="middleForm" class="col-md-9">
                <!--訂單狀態按鈕區塊-->
                <div>
                    <!--<ul class="nav nav-tabs nav-justified">-->
                    <ul class="nav nav-pills nav-justified">
                     	<li role="presentation" class="active"><a href="#">查詢訂單</a></li>
                        <li role="presentation"><a href="../_02_storeLogin/_storeIndex.jsp">待處理訂單</a></li>
                        <li role="presentation"><a href="../_24_storeOrder/_storeOrderUnpaid.jsp">已完成訂單</a></li>
                        <li role="presentation"><a href="../_24_storeOrder/_storeOrderPaid.jsp">已付款訂單</a></li>
                    </ul>
                </div>
                <br>
                <span><h4>搜尋結果：</h4></span>
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
                    <!-- 顯示訂單資訊 -->
                    <!-- 取得訂單 -->
                   <c:forEach var="orderSearch" items="${mPickupName}">
                    <tr id="${orderSearch.ord_id}">
                        <td nowrap=""><fmt:formatDate type = "both" pattern="yyyy-MM-dd HH:mm" value="${orderSearch.ord_time}"/></td>
                        <td nowrap=""><fmt:formatDate type = "both" pattern="yyyy-MM-dd HH:mm" value="${orderSearch.ord_pickuptime}"/></td>
                        <td>${orderSearch.m_pickupname}</td>
                        <td><a href="../_24_storeOrder/_storeOrderDetails.jsp?ord_id=${orderSearch.ord_id}&ord_totalPrice=${orderSearch.ord_totalPrice}">${orderSearch.ord_id}</a></td>
                        <td> $${orderSearch.ord_totalPrice}</td>
                        <td><a href="#">${orderSearch.ord_status}</a></td>
                        <td id="cancelB"><a href="../_24_storeOrder/_storeOrderSearchResult.jsp" onclick="ordCancel(${orderSearch.ord_id})">取消訂單</a></td>
                        <td id="${orderSearch.isRead}" style="color: red;"></td>
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
	<script type="text/javascript">
		$(document).ready(function(){
			//頁面載入後幫未讀的訂單加上class="new"
			var $td = $("#orderTable tr td:last-child");
			//alert($td.length);
			for(i = 0;i < $td.length; i++){
			     if($td[i].id == 0){
			    	 $td[i].parentNode.setAttribute("class", "new");
			    	 $td[i].textContent = "New!";
			     }
			}
			
			$("#orderTable a").click(function(){
				if($(this).closest("tr").attr("class") == "new"){
					readOrder($(this).closest("tr").attr("id"));
				} else{
					alert("已讀");
				}
				});
			
			function readOrder(id){
				$.get("../ReadOrderServlet?ordId=" + id, function(data, status){
			        //alert("Data: " + data + "\nStatus: " + status);
			    });
			}
		});
		
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