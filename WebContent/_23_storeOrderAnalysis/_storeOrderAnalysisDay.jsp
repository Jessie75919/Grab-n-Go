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
<!-- 載入圓餅圖js -->
<script src="https://d3js.org/d3.v4.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/d3/4.7.2/d3.min.js"></script>
<script src="../js/d3pie.js"></script>
<title>Welcome to GrabAndGo</title>
</head>
<!-- 商家登入成功畫面 -->
<!-- 帳務分析頁面/ 每日訂單統計(一併呈現當日餐點熱銷) -->

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
		<div class="col-md-3">
			<center>
				<!-- <img class="img-rounded" src="../images/restImage/af_logo.jpg"> -->
				<img
					src='${pageContext.servletContext.contextPath}/_00_init/getImageA?id=${StoreLoginOK["rest_username"]}&type=restaurant&loc=logo'
					alt="Photo" title="Photo"> <br>
			</center>
		</div>
		<div class="col-md-9">
			<div>
				<h3>> 本日訂單統計</h3>
				<!-- 指定日期 -->
				<div class="col-xs-4">
					<span><h4>請選擇欲查詢的日期：</h4></span>
					<div id="dateSelector"></div>
					<form action="" method="">
						<select class="form-control" name="">
							<option value="2017-06-01">2017-06-01</option>
						</select>
					</form>
				</div>
			</div>
	</section>

	<!--左側列表-->
	<section id="leftMenu" class="container"> <jsp:include
		page="../_IncludeJsp/_storeMenuTest.jsp" /> <!-- 表格開始 -->
	<div id="middleForm" class="col-md-9">
		<!--帳務分析按鈕區塊-->
		<br>
		<div>
			<!--<ul class="nav nav-tabs nav-justified">-->
			<ul class="nav nav-pills nav-justified">
				<li role="presentation" class="active"><a href="#">當日訂單統計</a></li>
				<li role="presentation"><a
					href="_storeOrderAnalysisMonthly.jsp">當月訂單統計</a></li>
				<li role="presentation"><a href="_storeOrderAnalysisYear.jsp">當年訂單統計</a></li>
			</ul>
		</div>
		<br>
		<!--圖表區塊, 動態產生-->
		<div id="pieChart">Chart goes here!</div>
		<hr>

		<table id="orderTable">
			<tr>
				<th>訂購日期</th>
				<th>餐點種類</th>
				<th>餐點名稱</th>
				<th>銷售數量</th>
				<th>銷售總額</th>
			</tr>
			<!-- 日營業額統計細項 -->
			<tr>
				<td nowrap="">2017/05/19 11:05:31</td>
				<td>主餐</td>
				<td>西班牙海鮮燉飯</td>
				<td>100</td>
				<td>$33000</td>
			</tr>
		</table>
		<hr>
	</div>
	</div>
	</div>
	<div class="row"></div>
	</div>
	</div>
	</section>
<!-- 圓餅圖 js -->
  <script src="../js/storeDailyAnalysis.js"></script>
</body>

</html>

