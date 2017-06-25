<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" type="text/css">
  <link rel="stylesheet" href="https://pingendo.github.io/templates/blank/theme.css" type="text/css">
  <link rel="stylesheet" href="../css/_storeOrderAnalysisDay.css" type="text/css">
  <!-- 載入圓餅圖js -->
	<script src="https://d3js.org/d3.v4.min.js"></script>
	<script src="//cdnjs.cloudflare.com/ajax/libs/d3/4.7.2/d3.min.js"></script>
	<script src="../js/d3pie.js"></script>
  <title>Welcome to GrabAndGo</title>
</head>
<!-- 商家登入成功畫面 -->
<!-- 帳務分析頁面/ 每日訂單統計(一併呈現當日餐點熱銷) -->
<body>
    <div class="container">
       <div class="row" >
         <div class="col-md-12 p-0"> 
        <!-- <div> -->
          <center>
          <img width="350px" src="../images/share/logo.svg">
          </center>
        <!-- </div> -->
         </div> 
       <!--</div>-->
      </div>
      </div>
      <div id="topBlock" class="topBlock">
        <h2>帳&nbsp&nbsp務&nbsp&nbsp分&nbsp&nbsp析</h2>
      </div> 
      <!-- <div class="row">
        
      </div>
    </div>
  </div> -->
  <div class="py-5">
    <div class="container">
      <div class="row">
        <!-- 店家profile -->
        <div class="col-md-3">
        <center>
         <!-- <img class="img-rounded" src="../images/restImage/af_logo.jpg"> -->
         <img  src='${pageContext.servletContext.contextPath}/_00_init/getImageA?id=${StoreLoginOK["rest_username"]}&type=restaurant&loc=logo' alt="Photo" title="Photo">
          <br>
         </center>
        </div>
        <div class="col-md-9">
          <!-- <div>
            <br>
            <h3 id="formHeading">本&nbsp&nbsp日&nbsp&nbsp訂&nbsp&nbsp單</h3>
          </div>
          <br>
          <div id="orderStatusHeading">
            待處理訂單
          </div>
        </div> -->
      </div>
      <div id="leftMenu" class="row">
        <!-- 左側列表 -->
        <jsp:include page="../_IncludeJsp/StoreLogin_Menu.jsp" />
        <!-- 表格開始 -->
        <div id="middleForm" class="col-md-9" >
        <!-- 圖表區塊 -->
          <div id="pieChart">
            <!-- 動態產生 -->
            Chart goes here!
            <img src="">
          </div>
          <span id="spanText">本日總金額:</span>
            <hr>
            
              <table id="orderTable">
                <tr>
                  <th>訂購日期</th>
                  <th>餐點種類</th>
                  <th>餐點名稱</th>
                  <th>銷售數量</th>
                  <th>銷售總額</th>
                </tr>
                <!-- 營業額統計係項 -->
                <tr>
                  <td nowrap="">2017/06/22 11:05:31</td>
                  <td>主餐</td>
                  <td>西班牙海鮮燉飯</td>
                  <td>100</td>
                  <td>$33000</td>
                </tr>

              </table>
            <hr> 
        </div>
      </div>
      <div class="row">
        
      </div>
    </div>
  </div>
  <!-- 圓餅圖 -->
  <script type="text/javascript">
  var pie = new d3pie("pieChart", {
	"header": {
		"title": {
			"text": "每日訂單統計",
			"fontSize": 22,
			"font": "Microsoft JhengHei"
		},
		"subtitle": {
			"text": "",
			"color": "#999999",
			"fontSize": 10,
			"font": "verdana"
		},
		"titleSubtitlePadding": 12
	},
	"footer": {
		"text": "統計日期：2017/06/22",
		"color": "#999999",
		"fontSize": 14,
		"font": "Microsoft JhengHei",
		"location": "bottom-center"
	},
	"size": {
		"canvasHeight": 400,
		"canvasWidth": 500,
		"pieOuterRadius": "100%"
	},
	"data": {
		"content": [
			{
				"label": "牛肉麵",
				"value": 8,
				"color": "#2383c1"
			},
			{
				"label": "排骨飯!",
				"value": 5,
				"color": "#64a61f"
			},
			{
				"label": "餛飩湯",
				"value": 2,
				"color": "#7b6788"
			},
			{
				"label": "油飯",
				"value": 3,
				"color": "#a05c56"
			},
			{
				"label": "雞腿飯",
				"value": 2,
				"color": "#961919"
			},
			{
				"label": "麻油雞",
				"value": 1,
				"color": "#d8d239"
			},
			{
				"label": "其他",
				"value": 3,
				"color": "#e98125"
			}
		]
	},
	"labels": {
		"outer": {
			"format": "label-percentage1",
			"pieDistance": 24
		},
		"inner": {
			"format": "value"
		},
		"mainLabel": {
			"font": "verdana"
		},
		"percentage": {
			"color": "black",
			"font": "verdana",
			"decimalPlaces": 0
		},
		"value": {
			"color": "#e1e1e1",
			"font": "verdana"
		},
		"lines": {
			"enabled": true,
			"color": "#cccccc"
		},
		"truncation": {
			"enabled": true
		}
	},
	"effects": {
		"pullOutSegmentOnClick": {
			"effect": "linear",
			"speed": 400,
			"size": 8
		}
	},
	"callbacks": {}
});
  </script>
  <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js"></script>
  <script src="https://pingendo.com/assets/bootstrap/bootstrap-4.0.0-alpha.6.min.js"></script>
</body>

</html>