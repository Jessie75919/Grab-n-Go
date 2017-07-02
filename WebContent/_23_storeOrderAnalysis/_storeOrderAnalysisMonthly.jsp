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
				 <span>請選擇月份：</span>
				<!--選擇月份-->
				<select name="">
					<option value="January">一月</option>
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
					<option value="December">十二月</option>
				</select>&nbsp
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

		<table id="orderTable">
			<tr>
				<th>訂單日期</th>
				<!--<th>餐點種類</th>
            <th>餐點名稱</th>
            <th>銷售數量</th>-->
				<th>營業總額</th>
			</tr>
			<!-- 日營業額統計細項 -->
			<tr>
				<td nowrap="">2017/05/19 11:05:31</td>
				<!--<td>主餐</td>
            <td>西班牙海鮮燉飯</td>
            <td>100</td>-->
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

	<script type="text/javascript">
		// 折線圖 
		// 建立資料
		var data = [ {
			x : 1,
			y : 20
		}, {
			x : 2,
			y : 40
		}, {
			x : 3,
			y : 60
		}, {
			x : 4,
			y : 50
		}, {
			x : 5,
			y : 90
		},

		{
			x : 6,
			y : 80
		}, {
			x : 7,
			y : 70
		}, {
			x : 8,
			y : 90
		}, {
			x : 9,
			y : 30
		}, {
			x : 10,
			y : 40
		},

		{
			x : 11,
			y : 90
		}, {
			x : 12,
			y : 50
		}, {
			x : 13,
			y : 80
		}, {
			x : 14,
			y : 70
		}, {
			x : 15,
			y : 90
		},

		{
			x : 16,
			y : 20
		}, {
			x : 17,
			y : 40
		}, {
			x : 18,
			y : 60
		}, {
			x : 19,
			y : 50
		}, {
			x : 20,
			y : 90
		},

		{
			x : 21,
			y : 80
		}, {
			x : 22,
			y : 70
		}, {
			x : 23,
			y : 90
		}, {
			x : 24,
			y : 30
		}, {
			x : 25,
			y : 40
		},

		{
			x : 26,
			y : 90
		}, {
			x : 27,
			y : 50
		}, {
			x : 28,
			y : 80
		}, {
			x : 29,
			y : 70
		}, {
			x : 30,
			y : 90
		}

		];
		// 設定長寬
		var width = 600, height = 240;

		var scaleX = d3.scale.linear()

		.range([ 0, width ])

		.domain([ 1, 30 ]); //x資料的範圍

		var scaleY = d3.scale.linear()

		.range([ height, 0 ])

		.domain([ 0, 100 ]); //Y的資料範圍

		var s = d3.select('#lineChart'); //取得SVG的物件

		s.attr({

			'width' : 680, //設定畫布範圍

			'height' : 300,

		})

		//  .style({

		//       'border':'1px dotted #aaa'

		//     });

		var line = d3.svg.line()

		.x(function(d) {

			return scaleX(d.x);

		}).y(function(d) {

			return scaleY(d.y);

		});

		var axisX = d3.svg.axis()

		.scale(scaleX)

		.ticks(20) //刻度大小

		.orient("bottom"); //X軸數字的位置

		var axisY = d3.svg.axis()

		.scale(scaleY)

		.ticks(10) //刻度大小

		.orient("left"); //Y軸數字的位置

		s.append('path')

		.attr({

			'd' : line(data),

			'stroke' : '#09c',

			'fill' : 'none',

			'transform' : 'translate(35,20)' //偏移

		});

		s.append('g')

		.call(axisX)

		.attr({

			'fill' : 'none', //空心，但是字要另外處理

			'stroke' : '#000',

			'transform' : 'translate(35,' + (height + 20) + ')' //偏移

		})

		.selectAll('text') //字也會套用空心，另外處理

		.attr({

			'fill' : '#000',

			'stroke' : 'none',

		}).style({

			'font-size' : '11px'

		});

		s.append('g')

		.call(axisY)

		.attr({

			'fill' : 'none',

			'stroke' : '#000',

			'transform' : 'translate(35,20)'

		})

		.selectAll('text')

		.attr({

			'fill' : '#000',

			'stroke' : 'none',

		}).style({

			'font-size' : '11px'

		});
	</script>
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js"></script>
</body>

</html>