<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"
	type="text/css">
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/_storeOrderAnalysisDay.css">
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/_storeIndex.css">
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/bootstrap.min.css">
<!-- 載入圓餅圖js -->
<script src="https://d3js.org/d3.v4.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/d3/4.7.2/d3.min.js"></script>
<script src="${pageContext.servletContext.contextPath}/js/d3pie.js"></script>
<!-- 載入datePicker -->
<link rel="stylesheet"
	href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.0/jquery-ui.min.js"></script>
<script>
	$(document).ready(function() {
		$("#datepicker").datepicker({ 
			dateFormat: "yy-mm-dd",
			onSelect: function(date){
				//alert(date);
				getDailyRevenue(date);
			}
		});
		//預設值取當日
		$("#datepicker").datepicker("setDate", new Date());
		//alert($("#datepicker").val());
		getDailyRevenue($("#datepicker").val());
	});
</script>
<title>Welcome to GrabAndGo</title>
</head>
<!-- 商家登入成功畫面 -->
<!-- 帳務分析頁面/ 每日訂單統計(一併呈現當日餐點熱銷) -->
<%-- <jsp:useBean id="orderBeans" class="_05_orderProcess.model.OrderItemDAO"/> --%>
<body>
	<!--logo-->
	<header>
	<div class="logoArea">
		<img src="${pageContext.servletContext.contextPath}/images/share/logo.svg" alt="">
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
				<h3>> 本日訂單統計</h3>
				<!-- 指定日期 -->
					<span><h4>請選擇欲查詢的日期：</h4></span>
					<!-- <div id="dateSelector"></div> -->
					<!-- <form class="form-inline" action="../DailyRevenue.do" method="get"> -->
					<div class="form-inline">
					<div class="input-group">
						<div class="input-group-addon"><i class="fa fa-calendar" aria-hidden="true"></i></div>
						<input type="text" id="datepicker" name="datepicker" class="form-control" value="" /> 
						<input style="display: none;" type="text" name="restUsername" id="restUsername" value="${StoreLoginOK['rest_username']}"/>
					</div>
				<input type="submit" id="submit" name="submit" class="btn btn-primary" value="清除"/>
				<!-- </form> -->
				</div>
			</div>
		</div>
	</section>

	<!--左側列表-->
	<section id="leftMenu" class="container"> 
	<jsp:include page="../_IncludeJsp/_storeMenuTest.jsp" /> 
	<!-- 表格開始 -->
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
		<div id="pieChart"></div>
		<hr>
		<!-- <div id="resultArea"></div> -->
		<table id="orderTable">
			<!-- <tr>
				<th>訂購日期</th>
				<th>餐點種類</th>
				<th>餐點名稱</th>
				<th>銷售數量</th>
				<th>銷售總額</th>
			</tr> -->
			<!-- 日營業額統計細項 -->
			<%-- <c:forEach  var="dtBean" items="${orderBeans.orderItemsByDate}">
			<tr>
				<td nowrap=""><fmt:formatDate type = "both" pattern="yyyy-MM-dd" value="${dtBean.ordPickuptime}"/></td>
				<td>${dtBean.item_name}</td>
				<td>${dtBean.item_name}</td>
				<td>${dtBean.item_amount}</td>
				<td>＄${dtBean.item_price}</td>
			</tr>
			</c:forEach> --%>
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

