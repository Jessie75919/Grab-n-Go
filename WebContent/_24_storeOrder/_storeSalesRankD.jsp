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
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"
	type="text/css">
<!--載入Bootstrap-->
<link rel="stylesheet" href="../css/bootstrap.min.css">
<link rel="stylesheet" href="../css/_storeIndex.css">
<!-- 載入 d3.js -->
<script src="http://d3js.org/d3.v3.min.js"></script>
<!-- 載入datePicker -->
<link rel="stylesheet"
	href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.0/jquery-ui.min.js"></script>

<style>
.chart {
	margin-top: 30px;
}

.chart div {
	/*font: 10px sans-serif;*/
	background-color: grey;
	text-align: right;
	padding: 3px;
	margin-top: 8px;
	color: white;
}
</style>
<%--     <jsp:useBean id="ordItemBeans" class="_05_orderProcess.model.OrderItemDAO"/> --%>
<title>餐點熱銷排行(日)|歷史訂單-Grab &amp; Go</title>
</head>
<!--商家餐點熱銷排行 （日）-->
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
				<h3>> 餐點熱銷排行</h3>
				<br>
				<!-- 指定日期 -->
				<span><h4>請選擇欲查詢的日期：</h4></span>
				<!-- <div id="dateSelector"></div> -->
				<form class="form-inline" action="SalesRankDaily.do" method="get">
					<div class="input-group">
						<div class="input-group-addon">
							<i class="fa fa-calendar" aria-hidden="true"></i>
						</div>
						<input id="datepicker" name="datepicker" type="text"
							class="form-control" /> <input style="display: none;"
							type="text" name="restUsername" id="restUsername"
							value="${StoreLoginOK['rest_username']}" />
					</div>
					<!-- 				<input type="submit" id="submit" name="submit" class="btn btn-primary" value="確定"/> -->
<!-- 					<span class="btn btn-primary" id='comfirm'>確定</span> -->
				</form>
			</div>
		</div>
	</section>
	<!--左側列表-->
	<section id="leftMenu" class="container"> <jsp:include
		page="../_IncludeJsp/_storeMenuTest.jsp" /> <!-- 表格開始 -->
	<div id="middleForm" class="col-md-9">
		<!--訂單狀態按鈕區塊-->
		<div>
			<!--<ul class="nav nav-tabs nav-justified">-->
			<ul class="nav nav-pills nav-justified">
<!-- 				<li role="presentation" class="active"><a href="#">餐點熱銷排行（單位：日）</a></li> -->
<!-- 				<li role="presentation"><a href="_storeSalesRankM.jsp">餐點熱銷排行（單位：月）</a></li> -->
				<li style="padding:5px;" role="presentation" class="active"><input class="btn btn-default btn-lg btn-block" type="button" value="餐點熱銷排行（單位：日）"></li>
                <li style="padding:5px;" role="presentation"><input class="btn btn-primary btn-lg btn-block" type="submit" value="餐點熱銷排行（單位：月）"></li>
				
			</ul>
		</div>
		<!--圖表區塊, 動態產生-->
		<div class="chart"></div>
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
			<%--                     <c:forEach var="anOrderBean"  items="${ordItemBeans.salesRankD}"> --%>
			<!--                     <tr> -->
			<!--                         <td>1</td> -->
			<%--                         <td>${anOrderBean.item_name}</td> --%>
			<%--                         <td>${anOrderBean.item_price}</td> --%>
			<%--                         <td>${anOrderBean.item_amount}</td> --%>
			<%--                         <td>${anOrderBean.item_amount * anOrderBean.item_price}</td> --%>
			<!--                     </tr> -->
			<%--                     </c:forEach> --%>
		</table>
		<hr>
	</div>
	</div>
	</div>
	<div class="row"></div>
	</div>
	</div>
	</section>
	<script>
		$(document).ready(function() {
			$("#datepicker").datepicker({
				dateFormat : "yy-mm-dd",
				onSelect : function(date) {
					alert(date);
					getStoreSalesRankD(date);
				}
			});

			$("#datepicker").datepicker("setDate", new Date());
			alert($("#datepicker").val());
			getStoreSalesRankD($("#datepicker").val());
		});
	</script>
	<script type="text/javascript">
// 		var data = [ 365, 303, 281, 168, 80, 25 ];

// 		d3.select(".chart").selectAll("div").data(data).enter().append("div")
// 				.style("width", function(d) {
// 					return d + "px";
// 				}).text(function(d) {
// 					return '$' + d;
// 				}).attr("font-family", "Tahoma").attr("font-size", "20px");
	</script>
	<script type="text/javascript">
		var id = ${StoreLoginOK.rest_id};
		$("li.active").click(function(){
			$(this).siblings().toggleClass("active");
		});
// 		alert(id);

// 		$(document).ready(function() {
// 			$('#comfirm').click(function() {
// 				alert($( "#datepicker" ).val());
// 			});
// 		});
	</script>
	<script src="../js/storeSalesRankD.js"></script>
</body>

</html>