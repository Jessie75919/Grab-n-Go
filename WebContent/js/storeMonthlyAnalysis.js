var restUsername = document.getElementById("restUsername").value;
//設定長寬
var width = 680, height = 264;
//取得SVG的物件
var s = d3.select('#lineChart'); 
//設定畫布範圍
s.attr({ 'width' : 700,'height' : 330,})	 

	var ms = document.getElementById("monthSelector");
	var d = new Date();
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
//	年
	var ys = document.getElementById("yearSelector");
	var year = d.getFullYear();
	var years = ["2015","2016","2017","2018"];
	for (var y = 0; y < years.length; y++) {
		var yp = document.createElement("option");
		yp.value = y;
		yp.text = years[y];
		ys.appendChild(yp);
		if (years[y] == year) {
			ys.value = y;
		}
	}

	var loadIcon = document.getElementById("spinner");
	function getMonthlyOrders(){
		//alert("選取的月份：" + ms.value);
		//alert("年份: " + year);
//		var msStr =  year + "-" + ms.value;
//		alert("傳回字串：" + msStr);
		var xhr = new XMLHttpRequest();
		xhr.open("GET","../MonthlyRevenue.do?restUsername=" + restUsername + "&month=" + ms.value + "&year=" + year,true);
		xhr.send();
		loadIcon.style.display="inline";
		xhr.onreadystatechange = function(){
			if( xhr.readyState == 4 && xhr.status == 200){
				loadIcon.style.display="none";
				//alert("Got MonthlyRevenue.do!");
				//處理回應
				var monthlyOrders = JSON.parse(xhr.responseText);
				var table = document.getElementById("orderTableM");
				var monthDays = monthlyOrders.length;
				table.innerHTML = "<tr><th>訂購日期</th><th>營業總額</th></tr>";
				
				//alert(monthlyOrders.length);

				for( var i = 0; i < monthDays ; i++){
					console.log("date = " + monthlyOrders[i].ordPickuptime 
							  + ", itemTotalPrice = " + monthlyOrders[i].ord_totalPrice); 
					if( monthlyOrders[i].ord_totalPrice != 0){
						 var tr = document.createElement("tr");
						 var td1 = document.createElement("td");
						 td1.textContent = monthlyOrders[i].ordPickuptime;
						 
						 var td2 = document.createElement("td");
						 td2.textContent = "$" + monthlyOrders[i].ord_totalPrice;
						 
						 tr.appendChild(td1);
						 tr.appendChild(td2);
						 
						 table.appendChild(tr);
					}
				}

				// -----------清空折線圖
				// d3.select("svg").remove();
				d3.selectAll("svg > *").remove();

				// -----------清空折線圖 end
				//---------- 折線圖開始
				
				var parseDate = d3.time.format("%Y-%m-%d").parse;
			    //x資料的範圍
				var scaleX = d3.scale.linear().range([ 0, width]).domain([ 1, monthDays]); 
				//Y的資料範圍
				var scaleY = d3.scale.linear().range([ height, 0 ]).domain([ 0, 3000]);
				var data = [];
				for( var j=1; j<=monthDays; j++){
					data.push(
					{ x: j,  y:monthlyOrders[j-1].ord_totalPrice}		
					);
				}

				console.log(data)

				// 畫面生成
				var line = d3.svg.line().x(function(d) {
					return scaleX(d.x);
				}).y(function(d) {
					return scaleY(d.y);
				});
				var axisX = d3.svg.axis().scale(scaleX)
				.ticks(30) //刻度大小
				.orient("bottom"); //X軸數字的位置

				var axisY = d3.svg.axis().scale(scaleY)
				.ticks(5) //刻度大小
				.orient("left"); //Y軸數字的位置
				
				s.append('path')
				.attr({
					'd' : line(data),
					'stroke' : '#09c',
					'stroke-width': '2px',
					'fill' : 'none',
					'transform' : 'translate(35,20)' //偏移
				});

				s.append('g').call(axisX)
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
					'font-size' : '12px'
				});

				s.append('g').call(axisY)
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
					'font-size' : '10px'
				});

			}
		}
	}