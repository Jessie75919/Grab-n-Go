$(document).ready(function() {
	var idArray = [];
	$("table input:even").each(function() {
		idArray.push($(this).attr("id"));
	});
	
//	setInterval(function(){
//		$("#orderTable tr").removeClass("new");
//		syncOrder();
//	}, 10000);

	
	$("#clickme").click(function(){
		$("#orderTable tr").removeClass("new");
		syncOrder();
//		$("#countdown").text(new Date());
	});
});


//格式化json的日期字串
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

function syncOrder() {
	
	var idArray = [];
	$("table input:even").each(function() {
		idArray.push($(this).attr("id"));
	});
	alert(idArray);
	
	//jQuery ajax
	$.post("SyncOrders.json",
		    {
				arr:JSON.stringify(idArray),
				id:restId
		    }
		    ,
		    function(data, status){
		    	for(var i = 0; i < data.length; i++){
		    		alert("Data: " + data[i].m_pickupname);
		    		alert("insertAfterChild: " + data[i].insertIndex);
		    		var tr = document.createElement("tr");
		    		tr.setAttribute("class", "new");
		    		
		    		var td1 = document.createElement("td");
		    		td1.setAttribute("nowrap", "");
		    		td1.innerHTML = formatDate(new Date(data[i].ord_time));
		    		
		    		var td2 = document.createElement("td");
		    		td2.setAttribute("nowrap", "");
		    		td2.innerHTML = formatDate(new Date(data[i].ord_pickuptime));
		    		
		    		var td3 = document.createElement("td");
		    		td3.innerHTML = data[i].m_pickupname;
		    		
		    		var td4 = document.createElement("td");
		    		var input = document.createElement("input");
		    		input.style.display = "none";
		    		input.name = "ordId";
		    		input.id = data[i].ord_id;
		    		input.value = data[i].ord_id;
		    		var a = document.createElement("a");
		    		a.href = "../_24_storeOrder/_storeOrderDetails.jsp?ord_id=" + data[i].ord_id + "&ord_totalPrice=" + data[i].ord_totalPrice + "&ordStatus=" + data[i].ord_status;
		    		a.textContent = data[i].ord_id;
		    		td4.appendChild(input);
		    		td4.appendChild(a);
		    		
		    		var td5 = document.createElement("td");
		    		td5.textContent = "$" + data[i].ord_totalPrice;
		    		
		    		var td6 = document.createElement("td");
		    		var input2 = document.createElement("input");
		    		input2.style.display = "none";
		    		input2.name = "ordStatus";
		    		input2.id = "ordStatus";
		    		input2.value = data[i].ord_status;
		    		var a2 = document.createElement("a");
		    		a2.href = "_storeIndex.jsp";
		    		a2.id = "ABC";
		    		a2.setAttribute("onclick", "updateOrdStatus(" + data[i].ord_id + ")");
		    		a2.textContent = data[i].ord_status;
		    		td6.appendChild(input2);
		    		td6.appendChild(a2);
		    		
		    		var td7 = document.createElement("td");
		    		td7.id = "cancelB";
		    		var a3 = document.createElement("a");
		    		a3.href = "_storeIndex.jsp";
		    		a3.setAttribute("onclick", "ordCancel(" + data[i].ord_id + ")");
		    		a3.textContent = "取消訂單";
		    		td7.appendChild(a3);
		    		
		    		tr.appendChild(td1);
		    		tr.appendChild(td2);
		    		tr.appendChild(td3);
		    		tr.appendChild(td4);
		    		tr.appendChild(td5);
		    		tr.appendChild(td6);
		    		tr.appendChild(td7);
		    		
		    		$(tr).insertAfter("table tr:nth-child(" + data[i].insertIndex + ")");
		    	}
		    });

}