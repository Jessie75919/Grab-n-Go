//var id = ${StoreLoginOK.rest_id};
//建立月份下拉選單
var ms = document.getElementById("monthSelector");
var d = new Date();
var currentMonth = d.getMonth();
var months = [ "一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月",
		"十一月", "十二月" ];
var table = document.getElementById("orderTable");
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

// 格式化Json回傳的日期字串
function formatDate(date) {
	var year = date.getFullYear();
	var month = (date.getMonth() < 9) ? "0" + (date.getMonth() + 1) : date
			.getMonth();
	var day = (date.getDate() < 10) ? "0" + date.getDate() : date.getDate();
	var hour = (date.getHours() < 10) ? "0" + date.getHours() : date.getHours();
	var minute = (date.getMinutes() < 10) ? "0" + date.getMinutes() : date
			.getMinutes();
	return year + "-" + month + "-" + day + " " + hour + ":" + minute;
}

//取得每月訂單功能
function getMonthlyOrders() {
	var name = document.getElementById("pickupname").value;
	// alert(id);
	// alert(ms.value);
	//alert(name);
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "../MonthlyOrders.json?id=" + id + "&month=" + ms.value + "&name=" + name,
			true);
	xhr.send();
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			// alert(xhr.responseText);
			var monthlyOrders = JSON.parse(xhr.responseText);
			table.innerHTML = "<tr><th>訂購日期</th><th>取餐顧客</th><th>訂單編號</th><th>總金額</th><th>訂單狀態</th></tr>";
			$("#result").html(monthlyOrders.length + "筆訂單");
			if (monthlyOrders.length == 0) {
				//alert("沒東西啦");
			} else {
				//alert(monthlyOrders.length);
				for (var j = 0; j < monthlyOrders.length; j++) {
					var tr = document.createElement("tr");
					var toggleTr = document.createElement("tr");
					//toggleTr.id = "inTr" + monthlyOrders[j].ord_id;
					var toggleTd = document.createElement("td");
					toggleTd.setAttribute("colspan", "5");
					toggleTd.innerHTML = "<div style='display:none; background-color: #ffe0b3;'></div>"
					
					tr.id = monthlyOrders[j].ord_id;
					var td1 = document.createElement("td");
					td1.textContent = formatDate(new Date(
							monthlyOrders[j].ord_time));
					// alert(monthlyOrders[j].ord_time);
					
					var td2 = document.createElement("td");
					td2.textContent = monthlyOrders[j].m_pickupname;

					var td3 = document.createElement("td");
					td3.textContent = monthlyOrders[j].ord_id;

					var td4 = document.createElement("td");
					td4.textContent = monthlyOrders[j].ord_totalPrice;

					var td5 = document.createElement("td");
					td5.textContent = monthlyOrders[j].ord_status;

					var div = document.createElement("div");

					tr.appendChild(td1);
					tr.appendChild(td2);
					tr.appendChild(td3);
					tr.appendChild(td4);
					tr.appendChild(td5);
					
					toggleTr.appendChild(toggleTd);
					
					table.appendChild(tr);
					table.appendChild(toggleTr);
					//alert($("tr")[j].id);
				}
			}
		}
	}

}
getMonthlyOrders();

//展開訂單明細功能
$(document).ready(function(){
	$("table").click(function(e){
		e.stopPropagation();
		//alert(e.target.nodeName);
		//alert(this.nodeName);
		if(e.target.nodeName == "TD"){
			
			var $target = $(e.target);//jQuery object
			if($target.closest("tr").next().find("div:empty").length != 0){
				
				var xhr2 = new XMLHttpRequest();
				xhr2.open("GET", "../AppendOrderDetail.json?id=" + $target.closest("tr").attr("id"), true);
				xhr2.send();
				
				xhr2.onreadystatechange = function(){
					if (xhr2.readyState == 4 && xhr2.status == 200) {
						//alert(xhr2.responseText);
						var orderDetails = JSON.parse(xhr2.responseText);
						var detailTable = document.createElement("table");
						var headTr = document.createElement("tr");
						headTr.innerHTML = "<tr><th>餐點編號</th><th>餐點名稱</th><th>備註</th><th>數量</th><th>單價</th><th>Subtotal</th></tr>";
						detailTable.appendChild(headTr);
						
						for(k = 0; k < orderDetails.length; k++){
							var detailTr = document.createElement("tr");
							
							var detailTd1 = document.createElement("td");
							detailTd1.textContent = orderDetails[k].prod_id;
							
							var detailTd2 = document.createElement("td");
							detailTd2.textContent = orderDetails[k].item_name;
							detailTd2.setAttribute("nowrap", "");
							
							var detailTd3 = document.createElement("td");
							detailTd3.textContent = orderDetails[k].item_note;
							
							var detailTd4 = document.createElement("td");
							detailTd4.textContent = orderDetails[k].item_amount;
							
							var detailTd5 = document.createElement("td");
							detailTd5.textContent = orderDetails[k].item_price;
							
							var detailTd6 = document.createElement("td");
							detailTd6.textContent = orderDetails[k].item_price * orderDetails[k].item_amount;
							
							detailTr.appendChild(detailTd1);
							detailTr.appendChild(detailTd2);
							detailTr.appendChild(detailTd3);
							detailTr.appendChild(detailTd4);
							detailTr.appendChild(detailTd5);
							detailTr.appendChild(detailTd6);
							
							detailTable.appendChild(detailTr);
						}
						
						
						
						
						$target.closest("tr").next().find("div:empty").append(detailTable);
						$target.closest("tr").next().find("div").slideToggle("slow");
					}
				}
			} else{
				$target.closest("tr").next().find("div").slideToggle("slow");
			}
			
		} else{
			//alert("nothing happened...");
		}
	});
	
});

