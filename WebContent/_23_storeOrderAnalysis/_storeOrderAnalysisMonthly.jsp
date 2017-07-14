<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"
	type="text/css">
<link rel="stylesheet" href="../css/_storeOrderAnalysisDay.css">
<link rel="stylesheet" href="../css/_storeIndex.css">
<link rel="stylesheet" href="../css/bootstrap.min.css">
<!-- 載入d3.js -->
<script src="http://d3js.org/d3.v3.min.js"></script>
<script src="../javascript/jquery-3.2.1.min.js"></script>
  <script>
$(document).ready(function(){
		  getMonthlyOrders();
  }); 
  </script>
<title>Welcome to GrabAndGo</title>
</head>
<!-- 商家登入成功畫面 -->
<!-- 帳務分析頁面/ 當月訂單統計(以折線圖呈現) -->

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
				<h3>> 本月訂單統計</h3><br>
				 <span><h4>&nbsp&nbsp&nbsp請選擇欲查詢的月份：</h4></span>
				<!--選擇月份-->
				<div class="col-xs-6">
				<div class="form-inline">
				<input style="display: none;" id="restUsername" name="restUsername" value="${StoreLoginOK['rest_username']}"/>
				<select id="yearSelector" class="form-control"></select>
				<select id="monthSelector" class="form-control">
					<!-- <option value="01">一月</option>
					<option value="02">二月</option>
					<option value="03">三月</option>
					<option value="04">四月</option>
					<option value="05">五月</option>
					<option value="06">六月</option>
					<option value="07">七月</option>
					<option value="08">八月</option>
					<option value="09">九月</option>
					<option value="10">十月</option>
					<option value="11">十一月</option>
					<option value="12">十二月</option> -->
				</select>&nbsp
				</div>
				</div>
			</div>
		</div>
	</section>

	<!--左側列表-->
	<section id="leftMenu" class="container"> <jsp:include
		page="../_IncludeJsp/_storeMenuTest.jsp" /> <!-- 表格開始 -->
	<div id="middleForm" class="col-md-9">
		<!--日期選擇器-->
		<div>
			<!-- 請選擇月份: -->
			<!-- <input type="text" id="datepickerTest"> -->
		</div>
		<!--帳務分析按鈕區塊-->
		<br>
		<div>
			<!--<ul class="nav nav-tabs nav-justified">-->
			<ul class="nav nav-pills nav-justified">
				<li role="presentation"><a href="_storeOrderAnalysisDay.jsp">當日訂單統計</a></li>
				<li role="presentation" class="active"><a href="_storeOrderAnalysisMonthly.jsp">當月訂單統計</a></li>
				<li role="presentation"><a href="_storeOrderAnalysisYear.jsp">當年訂單統計</a></li>
			</ul>
		</div>
		<br>
		<!--圖表區塊, 動態產生-->
		<div style="margin-left: 20px;">
			<svg id="lineChart"></svg>
		</div>
		<hr>

		<table id="orderTableM">
			<!-- <tr>
				<th>訂單日期</th>
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
	<div class="row"></div>
	</div>
	</div>
	</section>

	<script src="../js/storeMonthlyAnalysis.js"></script>
	</body>

</html>