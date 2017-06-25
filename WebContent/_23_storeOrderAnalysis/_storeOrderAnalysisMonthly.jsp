<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <!--載入bootstrap 效果-->
    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <link rel="stylesheet" href="../css/_storeOrderAnalysisMonthly.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" type="text/css">
    <!-- 載入 d3.js -->
    <script src="http://d3js.org/d3.v3.min.js"></script>
    <title>Welcome to GrabAndGo</title>
</head>
<!--商家帳務分析-->
<!--每月營業額統計-->

<body>
    <!--Logo-->
    <header>
        <div class="logoImg">
        <img src="../images/share/logo.svg" alt="">
        </div>
    </header>
    <div class="topBlock">
        <h2>帳&nbsp&nbsp務&nbsp&nbsp分&nbsp&nbsp析</h2>
    </div>
    <!--店家profile-->
    <section class="container">
        <div class="row">
            <!-- 店家profile -->
            <div class="col-md-3">
                <center>
                    <!-- <img class="img-rounded" src="../images/restImage/af_logo.jpg"> -->
                    <img src='${pageContext.servletContext.contextPath}/_00_init/getImageA?id=${StoreLoginOK["rest_username"]}&type=restaurant&loc=logo'
                        alt="Photo" title="Photo">
                    <br>
                </center>
            </div>
            <div class="col-md-9">
            </div>
    </section>
    <section class="container">
        <div id="leftMenu" class="row">
        <!-- 左側列表 -->
        <jsp:include page="../_IncludeJsp/StoreLogin_Menu.jsp" />
            <!-- 表格開始 -->
            <div id="middleForm" class="col-md-9">
                <div>
                    <h3>本月訂單統計</h3>
                </div>
                <!-- 圖表區塊 -->
                <div id="barChart">
                    <!-- 動態產生 -->
                </div>
                <span id="spanText">本月總金額:</span>
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
    </section>

<!--長條圖 js-->
    <script type="text/javascript">
        // 設定svg長寬
        var svg_width = 500;
        var svg_height = 200;
        //設定padding (長條之間的間隔)
        var bar_padding = 5;

        //需要視覺化的data
        var dataset = [30, 20, 10, 40, 45, 25, 15, 35, 18];

        //開始設定svg
        var svg = d3.select("body").selectAll("#barChart")
            .append("svg")
            .attr("width", svg_width)
            .attr("height", svg_height);

        //呈現data
        //.data(dataset)
        //要呈現的data是dataset裡的數值
        //.enter()像是過濾器的功用
        //宣告 x, y座標
        // .attr("x", function (d, i), d-> 30; i-> 0
        //設定長條圖中值的寬高
        //.attr("width", svg_width / dataset.length)
        //設定顏色, rgb顏色跟著值做改變
        //.attr("fill", function(d)
        svg.selectAll("rect")
            .data(dataset)
            .enter()
            .append("rect")
            .attr("x", function (d, i) {
                // body...
                return i * (svg_width / dataset.length);
            })
            .attr("y", function (d) {
                // body...
                return svg_height - (d * 4);
            })
            .attr("width", svg_width / dataset.length - bar_padding)
            .attr("height", function (d) {
                // body...
                return d * 4;
            })
            .attr("fill", function (d) {
                return "rgb(" + d * 5 + ",0 , 0)";
            })

        //將文字顯示在長條圖上 
        //設定x, y 的座標-> 目的是告訴文字的顯示位置
        svg.selectAll("text")
            .data(dataset)
            .enter()
            .append("text")
            .text(function (d) {
                return d;
            })
            .attr("text-anchor", "middle")
            .attr("x", function (d, i) {
                return i * (svg_width / dataset.length) + (svg_width / dataset.length - bar_padding) / 2;
            })
            .attr("y", function (d) {
                return svg_height - (d * 4) + 20;
            })
            .attr("font-family", "Tahoma")
            .attr("font-size", "20px")
            .attr("fill", "white")

    </script>
    <!--載入js-->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="../js/bootstrap.min.js"></script>

</body>

</html>