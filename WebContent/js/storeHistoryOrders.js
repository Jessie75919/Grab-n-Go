//var id = ${StoreLoginOK.rest_id};
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

function getMonthlyOrders() {
	var name = document.getElementById("pickupname").value;
	// alert(id);
	// alert(ms.value);
	alert(name);
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "../MonthlyOrders.json?id=" + id + "&month=" + ms.value + "&name=" + name,
			true);
	xhr.send();
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			// alert(xhr.responseText);
			var monthlyOrders = JSON.parse(xhr.responseText);
			table.innerHTML = "<tr><th>訂購日期</th><th>取餐顧客</th><th>訂單編號</th><th>總金額</th><th>訂單狀態</th></tr>";
			if (monthlyOrders.length == 0) {
				alert("沒東西啦");
			} else {
				for (var j = 0; j < monthlyOrders.length; j++) {
					var tr = document.createElement("tr");

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

					table.appendChild(tr);
				}
			}
		}
	}

}
getMonthlyOrders();