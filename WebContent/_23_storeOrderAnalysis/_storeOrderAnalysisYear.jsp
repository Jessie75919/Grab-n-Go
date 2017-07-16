<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" type="text/css">
  <link rel="stylesheet" href="../css/_storeOrderAnalysisDay.css">
  <link rel="stylesheet" href="../css/_storeIndex.css">
  <link rel="stylesheet" href="../css/bootstrap.min.css">
  <!-- 載入d3.js -->
  <script src="http://d3js.org/d3.v3.min.js"></script>
  <script src="../javascript/jquery-3.2.1.min.js"></script>
  <script>
  $(document).ready(function(){
		  $("#yearSelected option").eq(1).prop("selected",true);
		  getYearOrders(ys.value);
  }); 
  </script>
  <title>Welcome to GrabAndGo</title>
</head>
<!--商家帳務分析-->
<!--年度營業額統計，以長條圖顯示-->
<body>
  <!--logo-->
  <header>
    <div class="logoArea">
      <img src="../images/share/logo.svg" alt="">
    </div>
    <!--進入區塊-->
    <div class="topTitle">
      <h2>帳務分析</h2>
    </div>
  </header>
  <!--店家profile-->
  <section class="container">
    <div class="row">
     <!-- 店家profile -->
		<jsp:include page="../_IncludeJsp/Store_Profile.jsp" />
      <div class="col-md-9">
        <div>
          <h3>> 年度訂單統計</h3>
          <span><h4>請選擇欲查看的年份:</h4></span>
          <input style="display: none;" type="text" name="restUsername" id="restUsername" value="${StoreLoginOK['rest_username']}"/>
          <div class="form-inline">
          <select id="yearSelected" name="yearSelector" class="form-control">
          	<option value="2016">2016</option>
          	<option value="2017">2017</option>
          	<option value="2018">2018</option>
          </select>
          </div>
        </div>
      </div>
  </section>
  <!--左側列表-->
  <section id="leftMenu" class="container">
  	<jsp:include page="../_IncludeJsp/_storeMenuTest.jsp"/>
      <!-- 表格開始 -->
      <div id="middleForm" class="col-md-9">
        <!--帳務分析按鈕區塊-->
        <br>
        <div>
          <ul class="nav nav-pills nav-justified">
            <li role="presentation"><a href="_storeOrderAnalysisDay.jsp">當日訂單統計</a></li>
            <li role="presentation"><a href="_storeOrderAnalysisMonthly.jsp">當月訂單統計</a></li>
            <li role="presentation" class="active"><a href="#">當年訂單統計</a></li>
          </ul>
        </div>
        <br>
        <!-- loading icon //預設dispay:none -->
		<div id="spinnerArea">
		<img id="spinner" src="${pageContext.servletContext.contextPath}/images/storeSpinner.gif"
		style="display: none;">
		</div>
        <!--圖表區塊, 動態產生-->
        <div id="barChart" style="margin-left: 18%;">
        </div>
        <hr>

        <table id="orderTableY">
          <!-- <tr>
            <th>訂單月份</th>
            <th>餐點種類</th>
            <th>餐點名稱</th>
            <th>銷售數量</th>
            <th>營業總額</th>
          </tr>
          日營業額統計細項
          <tr>
            <td nowrap="">2017/05/19 11:05:31</td>
            <td>主餐</td>
            <td>西班牙海鮮燉飯</td>
            <td>100</td>
            <td>$33000</td>
          </tr> -->
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
  
    <!--載入js-->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="../js/bootstrap.min.js"></script>
    <script src="../js/storeYearAnalysis.js"></script>

</body>

</html>