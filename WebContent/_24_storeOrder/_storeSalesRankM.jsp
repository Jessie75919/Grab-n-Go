<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
    <!-- 載入 d3.js -->
    <script src="http://d3js.org/d3.v3.min.js"></script>
    <script	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
    <style>
        .chart{
            margin-top: 30px;
        }
        .chart div{
            /*font: 10px sans-serif;*/
            background-color: grey;
            text-align: right;
            padding: 3px;
            margin-top: 8px;
            color:white;
        }    
    </style>
    <title>餐點熱銷排行(月)|歷史訂單-Grab &amp; Go</title>
</head>
<!--商家餐點熱銷排行 （月）-->
<!--以橫向長條圖顯示-->

<body>
    <!--logo-->
    <header>
        <div class="logoArea">
            <img src="../images/share/logo.svg" alt="">
        </div>
        <!--進入區塊-->
        <div class="topTitle">
            <h2>歷史訂單</h2>
        </div>
    </header>
    <!--店家profile-->
    <section class="container">
        <div class="row">
           <!-- 店家profile -->
		<jsp:include page="../_IncludeJsp/Store_Profile.jsp" />
            <div class="col-md-9">
                <div>
                    <h3>> 餐點熱銷排行</h3><br>
                    <!-- 欲查詢的日期 -->
                    <form class="form-inline">
                        <span><h4>請選擇欲查詢的月份：</h4></span>
                        <select id="monthSelector">
                                
                        </select>
                    </form>
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
                        <li role="presentation"><a href="_storeSalesRankD.jsp">餐點熱銷排行（單位：日）</a></li>
                        <li role="presentation" class="active"><a href="">餐點熱銷排行（單位：月）</a></li>
                    </ul>
                </div>
                <!--圖表區塊, 動態產生-->
                <div class="chart">
                
                </div>
                <hr>
                <!--</div>-->
                <!--<div class="orderTable" >-->
                <table id="orderTable">
                    <tr>
                        <th>銷售排行</th>
                        <th>餐點名稱</th>
                        <th>餐點單價</th>
                        <th>銷售數量</th>
                        <th>銷售總額</th>
                    </tr>
                    <!-- 每筆訂單資訊, 預設一頁顯示15筆 -->
                    <tr>
                        <td>1</td>
                        <td>火雞胸肉潛艇堡</td>
                        <td>$109</td>
                        <td>100</td>
                        <td>$10900</td>
                    </tr>

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
		var id = ${StoreLoginOK.rest_id};
	</script>
    <script src="../js/storeSalesRankM.js"></script>
</body>

</html>