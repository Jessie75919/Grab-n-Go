var restUsername = document.getElementById("restUsername").value;

//設定長寬
var width = 600, height = 240;
//取得SVG的物件
var s = d3.select('#lineChart'); 
//設定畫布範圍
s.attr({ 'width' : 680,'height' : 300,})	 

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
		xhr.open("GET","../MonthlyRevenue.do?restUsername=" + restUsername + "&month=" + ms.value + "&year=" + year,true);
		xhr.send();
		xhr.onreadystatechange = function(){
			if( xhr.readyState == 4 && xhr.status == 200){
				alert("Got MonthlyRevenue.do!");
				//處理回應
				var monthlyOrders = JSON.parse(xhr.responseText);
				var table = document.getElementById("orderTableM");
				
				table.innerHTML = "<tr><th>訂購日期</th><th>營業總額</th></tr>";
				
				//alert(monthlyOrders.length);
				var monthDays = monthlyOrders.length;
				var priceMax = Math.max(monthlyOrders.ord_totalPrice);
				
				for( var i = 0; i < monthDays ; i++){
					console.log("date = " + monthlyOrders[i].ord_pickuptime 
							  + ", itemTotalPrice = " + monthlyOrders[i].ord_totalPrice); 
					if( monthlyOrders[i].ord_totalPrice != 0){
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
//				for( var i = 0; i < monthlyOrders.length; i++){
//					
//					  /* console.log("date = " + monthlyOrders[i].ord_pickuptime 
//							  + ", itemTotalPrice = " + monthlyOrders[i].ord_totalPrice); */
//					 var tr = document.createElement("tr");
//					
//					 var td1 = document.createElement("td");
//					 td1.textContent = monthlyOrders[i].ord_pickuptime;
//					 
//					 var td2 = document.createElement("td");
//					 td2.textContent = "$" + monthlyOrders[i].ord_totalPrice;
//					 
//					 tr.appendChild(td1);
//					 tr.appendChild(td2);
//					 
//					 table.appendChild(tr);
//				}
				 //---------- 折線圖
				
				var parseDate = d3.time.format("%Y-%m-%d").parse;
			    //x資料的範圍
				var scaleX = d3.scale.linear().range([ 0, width ]).domain([ 1, monthDays]); 
				//Y的資料範圍
				var scaleY = d3.scale.linear().range([ height, 0 ]).domain([ 0, 1000]); 
				var data = [];
				for( var j=1; j<=monthDays; j++){
					data.push(
					{ x: j,  y:monthlyOrders[j-1].ord_totalPrice}		
					);
				}
				console.log(data)
				var line = d3.svg.line().x(function(d) {
					return scaleX(d.x);
				}).y(function(d) {
					return scaleY(d.y);
				});
				var axisX = d3.svg.axis().scale(scaleX)
				.ticks(20) //刻度大小
				.orient("bottom"); //X軸數字的位置

				var axisY = d3.svg.axis().scale(scaleY)
				.ticks(10) //刻度大小
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
					'font-size' : '12px'
				});
			
			}
		}
	}


// 折線圖 
		// 建立資料
//		var data = [ 
//			{ x : 1, y : 20 }, 
//			{ x : 2, y : 40 },
//			{ x : 3, y : 60 }, 
//			{ x : 4, y : 50 },
//			{ x : 5, y : 90 },
//			{ x : 6, y : 80 }, 
//			{ x : 7, y : 70 }, 
//			{ x : 8, y : 90 }, 
//			{ x : 9, y : 30 }, 
//			{ x : 10,y : 40 },
//			{ x : 11,y : 90 }, 
//			{ x : 12,y : 50 },
//			{ x : 13,y : 80 },
//		    { x : 14,y : 70 },
//			{ x : 15,y : 90 },
//			{ x : 16,y : 20 },
//			{ x : 17,y : 40 },
//			{ x : 18,y : 60 },
//			{ x : 19,y : 50 },
//		    { x : 20,y : 90 },
//		    { x : 21,y : 80 },
//		    { x : 22,y : 70 },
//		    { x : 23,y : 90 },
//		    { x : 24,y : 30 },
//		    { x : 25,y : 40 },
//			{ x : 26,y : 90 },
//			{ x : 27,y : 50 },
//			{ x : 28,y : 80 },
//			{ x : 29,y : 70 },
//			{ x : 30,y : 90 }
//			];
//
//		

//		var line = d3.svg.line()
//
//		.x(function(d) {
//
//			return scaleX(d.x);
//
//		}).y(function(d) {
//
//			return scaleY(d.y);
//
//		});
//
//		var axisX = d3.svg.axis()
//
//		.scale(scaleX)
//
//		.ticks(20) //刻度大小
//
//		.orient("bottom"); //X軸數字的位置
//
//		var axisY = d3.svg.axis()
//
//		.scale(scaleY)
//
//		.ticks(10) //刻度大小
//
//		.orient("left"); //Y軸數字的位置
//
//		s.append('path')
//
//		.attr({
//
//			'd' : line(data),
//
//			'stroke' : '#09c',
//
//			'fill' : 'none',
//
//			'transform' : 'translate(35,20)' //偏移
//
//		});
//
//		s.append('g')
//
//		.call(axisX)
//
//		.attr({
//
//			'fill' : 'none', //空心，但是字要另外處理
//
//			'stroke' : '#000',
//
//			'transform' : 'translate(35,' + (height + 20) + ')' //偏移
//
//		})
//
//		.selectAll('text') //字也會套用空心，另外處理
//
//		.attr({
//
//			'fill' : '#000',
//
//			'stroke' : 'none',
//
//		}).style({
//
//			'font-size' : '11px'
//
//		});
//
//		s.append('g')
//
//		.call(axisY)
//
//		.attr({
//
//			'fill' : 'none',
//
//			'stroke' : '#000',
//
//			'transform' : 'translate(35,20)'
//
//		})
//
//		.selectAll('text')
//
//		.attr({
//
//			'fill' : '#000',
//
//			'stroke' : 'none',
//
//		}).style({
//
//			'font-size' : '11px'
//
//		});