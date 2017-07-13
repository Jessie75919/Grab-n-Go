var restUsername = document.getElementById("restUsername").value;
var ys = document.getElementById("yearSelected");
ys.setAttribute("onchange", "getYearOrders()");

function getYearOrders(){
	alert("商家選擇的年份：" + ys.value);
	//alert("!" + year.value);
	var xhr = new XMLHttpRequest();
	xhr.open("GET","../YearRevenue.do?restUsername=" + restUsername + "&yearVal=" + ys.value, true);
	xhr.send();
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			alert("Got YearRevenue.do!");
			var yearOrders = JSON.parse(xhr.responseText);
			var table = document.getElementById("orderTableY");
			table.innerHTML = "<tr><th>訂單月份</th><th>銷售總額</th></tr>";
			
			for(var i = 0; i < yearOrders.length; i++ ){
				console.log("orderMonth = " + yearOrders[i].ordPickuptime
						    + "revenue = " + yearOrders[i].ord_totalPrice);
//				if(yearOrders[i].ord_totalPrice != 0){
					var tr = document.getElementById("tr");
					var td1 = document.getElementById("td");
					td1.textContent = yearOrders[i].ordPickuptime;
					
					var td2 = document.getElementById("td");
					td2.textContent = "$" + yearOrders[i].ord_totalPrice;
					
					tr.appendChild(td1);
					tr.appendChild(td2);
					
					table.appendChild(tr);
//				}
			}
			
		}
	}
	
	//柱狀圖
	getChart();
}

function getChart(){
// 設定svg長寬
        var svg_width = 500;
        var svg_height = 300;
        //設定padding (長條之間的間隔)
        var bar_padding = 5;

        //需要視覺化的data
        var dataset = [30, 20, 10, 40, 45, 25, 15, 35, 18, 50, 30, 25];

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
                return svg_height - 5 ;
                //return svg_height- (d * 4) + 20
            })
            .attr("font-family", "Tahoma")
            .attr("font-size", "20px")
            .attr("fill", "white")
}