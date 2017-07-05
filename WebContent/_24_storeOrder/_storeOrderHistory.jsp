<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<!--載入icon-->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"
	type="text/css">
<!--載入Bootstrap-->
<link rel="stylesheet" href="../css/bootstrap.min.css">
<link rel="stylesheet" href="../css/_storeIndex.css">
<title>Welcome to GrabAndGo</title>
</head>
<!--商家登入成功-->
<!--歷史訂單查詢頁面-->

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
				<h3>> 歷史訂單查詢</h3>
				<!-- 訂單搜尋 -->
				<span><h4>請選擇欲查詢的訂單月份：</h4></span> <br>
				<form class="form-inline">
					<!--月份搜尋-->
					<select id="monthSelector">
						<!--                             <option value="January">一月</option> -->
						<!--                             <option value="Feburary">二月</option> -->
						<!--                             <option value="March">三月</option> -->
						<!--                             <option value="April">四月</option> -->
						<!--                             <option value="May">五月</option> -->
						<!--                             <option value="June">六月</option> -->
						<!--                             <option value="July">七月</option> -->
						<!--                             <option value="August">八月</option> -->
						<!--                             <option value="September">九月</option> -->
						<!--                             <option value="October">十月</option> -->
						<!--                             <option value="November">十一月</option> -->
						<!--                             <option value="December">十二月</option>                       -->

					</select>&nbsp
					
					<div class="input-group">
						<div class="input-group-addon">
							<i class="fa fa-search" aria-hidden="true"></i>
						</div>
						<input type="text" class="form-control" id="pickupname" placeholder="顧客姓名"/>
						<input style="display: none;" type="text" placeholder="讓按下enter後不自動submit"/> 
					</div>
<!-- 					<input type="submit" class="btn btn-primary" value="搜尋"></input> -->
				<span  class="btn btn-primary" onclick="getMonthlyOrders()">搜尋</span>
				</form>
			</div>
		</div>
	</section>

	<!--左側列表-->
	<section id="leftMenu" class="container"> <jsp:include
		page="../_IncludeJsp/_storeMenuTest.jsp" /> <!-- 搜尋結果顯示表格開始 -->
	<div id="middleForm" class="col-md-9">
		<span><h4>搜尋結果：</h4></span>
		<hr style="color: rgb(213, 141, 60);">
		<!--</div>-->
		<!--<div class="orderTable" >-->
		<table id="orderTable">
<!-- 			<tr> -->
<!-- 				<th>訂購日期</th> -->
<!-- 				<th>取餐顧客</th> -->
<!-- 				<th>訂單編號</th> -->
<!-- 				<th>總金額</th> -->
<!-- 				<th>訂單狀態</th> -->
<!-- 			</tr> -->
<!-- 			<tr> -->
<!-- 				<td>2017/05/19 11:05:31</td> -->
<!-- 				<td>王小明</td> -->
<!-- 				<td><a href="_storeOrderDetails.jsp">XX001</a></td> -->
<!-- 				<td>$250</td> -->
<!-- 				<td>已付款</td> -->
<!-- 			</tr> -->
			
		</table>
		<hr>
	</div>
	</div>
	</div>
	<div class="row"></div>
	</div>
	</div>
	</section>
	
	<script type="text/javascript">
		var id = ${StoreLoginOK.rest_id};
	</script>
	<script src="../js/storeHistoryOrders.js"></script>
</body>

</html>