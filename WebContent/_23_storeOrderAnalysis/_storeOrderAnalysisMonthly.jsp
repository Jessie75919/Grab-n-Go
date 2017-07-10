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
				<h3>> 本月訂單統計</h3><br>
				 <span><h4>&nbsp&nbsp&nbsp請選擇欲查詢的月份：</h4></span>
				<!--選擇月份-->
				<div class="col-xs-3">
				<input style="display: none;" id="restUsername" name="restUsername" value="${StoreLoginOK['rest_username']}"/>
				<select id="monthSelector" class="form-control">
					<!-- <option value="January">一月</option>
					<option value="Feburary">二月</option>
					<option value="March">三月</option>
					<option value="April">四月</option>
					<option value="May">五月</option>
					<option value="June">六月</option>
					<option value="July">七月</option>
					<option value="August">八月</option>
					<option value="September">九月</option>
					<option value="October">十月</option>
					<option value="November">十一月</option>
					<option value="December">十二月</option> -->
				</select>&nbsp
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
				<li role="presentation" class="active"><a href="#">當月訂單統計</a></li>
				<li role="presentation"><a href="_storeOrderAnalysisYear.jsp">當年訂單統計</a></li>
			</ul>
		</div>
		<br>
		<!--圖表區塊, 動態產生-->
		<div>
			Chart goes here!
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
	<script type="text/javascript">
	var restUsername = document.getElementById("restUsername").value;
	
	var ms = document.getElementById("monthSelector");
	var d = new Date();
	var year = d.getFullYear();
	var currentMonth = d.getMonth();
	var months = [ "一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月",
			"十一月", "十二月" ];
	for (var i = 0; i < months.length; i++) {
		var op = document.createElement("option");
		op.value = i;
		op.text = months[i];
		ms.appendChild(op);
		if (i == currentMonth) {
			ms.value = i;
		}
	}
	ms.setAttribute("onchange", "getMonthlyOrders()");
	
	function getMonthlyOrders(){
		alert("選取的月份：" + ms.value);
		alert("年份: " + year);
		
		var xhr = new XMLHttpRequest();
		xhr.open("GET","../MonthlyRevenue.do?restUsername=" + restUsername + "&month=" + ms.value,true);
		xhr.send();
		xhr.onreadystatechange = function(){
			if( xhr.readyState == 4 && xhr.status == 200){
				alert("Got MonthlyRevenue.do!");
				//處理回應
				var monthlyOrders = JSON.parse(xhr.responseText);
				var table = document.getElementById("orderTableM");
				
				table.innerHTML = "<tr><th>訂購日期</th><th>營業總額</th></tr>";
				for( var i = 0; i < monthlyOrders.length; i++){
					
					  /* console.log("date = " + monthlyOrders[i].ord_pickuptime 
							  + ", itemTotalPrice = " + monthlyOrders[i].ord_totalPrice); */
					 var tr = document.createElement("tr");
					
					 var td1 = document.createElement("td");
					 td1.textContent = monthlyOrders[i].ord_pickuptime;
					 
					 var td2 = document.createElement("td");
					 td2.textContent = "$" + monthlyOrders[i].ord_totalPrice;
					 
					 tr.appendChild(td1);
					 tr.appendChild(td2);
					 
					 table.appendChild(tr);
					 
					
				}
			
			}
		}
	}
	
	</script>
	<script src="../js/storeMonthlyAnalysis.js"></script>
	</body>

</html>