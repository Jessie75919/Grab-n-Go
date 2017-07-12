var table = document.getElementById("orderTable");

//$("document").ready(function(){
//	var chartWidth = $(".chart").width();
//	alert("chartWidth: " + chartWidth);
//});

function getStoreSalesRankD(date) {
	var xhr = new XMLHttpRequest();
	xhr.open("GET","../SalesRankD.json?date=" + date + "&id=" + id, true);
	xhr.send();
	var rank = 0;
	var prevTotal = null;
	
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			alert(xhr.responseText);
			var dailySales = JSON.parse(xhr.responseText);
			table.innerHTML = "<tr><th>銷售排行</th><th>餐點名稱</th><th>餐點單價</th><th>銷售數量</th><th>銷售總額</th></tr>";
			var data =[];
			var maxTotal = 0;
			for(var i = 0; i < dailySales.length; i++){
				//塞銷售總額給data陣列
				var total = dailySales[i].item_price * dailySales[i].item_amount;
				if(i == 0){
					maxTotal = total;
					alert("maxTotal: " + maxTotal);
				}
				data.push(total);
				
				//塞資料給order table
				var tr = document.createElement("tr");
				
				var td1 = document.createElement("td");
				td1.textContent = getRank(total);
				
				var td2 = document.createElement("td");
				td2.setAttribute("nowrap", "");
				td2.textContent = dailySales[i].item_name;
				
				var td3 = document.createElement("td");
				td3.textContent = dailySales[i].item_price;
				
				var td4 = document.createElement("td");
				td4.textContent = dailySales[i].item_amount;
				
				var td5 = document.createElement("td");
				td5.textContent = total;
				
				tr.appendChild(td1);
				tr.appendChild(td2);
				tr.appendChild(td3);
				tr.appendChild(td4);
				tr.appendChild(td5);
				
				table.appendChild(tr);
				
			}
			//d3畫長條圖
			d3.select(".chart").selectAll("div").remove();
			d3.select(".chart").selectAll("div").data(data).enter().append("div")
			.style("width", function(d) {
				//限制最大寬度等於chart的寬度
				return d / maxTotal * $(".chart").width() + "px";
			}).text(function(d) {
				return '$' + d;
			}).attr("font-family", "Tahoma").attr("font-size", "20px");
		}
	}
	//計算排名
	function getRank(curTotal){
		
		if(prevTotal == curTotal){
			return rank;
		} else{
			prevTotal = curTotal;
			return rank += 1;
		}
	}
}