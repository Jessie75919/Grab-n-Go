
    	var tableDetails = document.getElementById("tableD");
    	
        function clickMe(e) {
        	//alert("target = " + e);
        	var xhr = new XMLHttpRequest();
        	xhr.open("GET","../AppendOrderDetail.json?id=" + e, true);
        	xhr.send();
        	
        	xhr.onreadystatechange = function(){
        		if( xhr.readyState == 4 && xhr.status == 200){
        			//alert("Got AppendOrderDetail.json!");
        			var paidOrders = JSON.parse(xhr.responseText);
        			//alert(JSON.stringify(paidOrders));
        			tableDetails.innerHTML = "<tr><th>顧客名稱</th><th>餐點名稱</th><th>餐點編號</th><th>備註</th><th>數量</th><th>單價</th><th>Subtotal</th></tr>";
        			//var tr = document.createElement("tr");
         			for(var i = 0; i < paidOrders.length ; i++ ){
            			var tr = document.createElement("tr");
            			
            			var td1 = document.createElement("td");
    					td1.textContent = paidOrders[i].m_pickupname;
    					
    					var td2 = document.createElement("td");
    					td2.setAttribute("nowrap", "");
    					td2.textContent = paidOrders[i].item_name;
    					
    					var td3 = document.createElement("td");
    					td3.textContent = paidOrders[i].prod_id;
    					
    					var td4 = document.createElement("td");
    					td4.setAttribute("nowrap", "");
    					td4.textContent = paidOrders[i].item_note;
    					
    					var td5 = document.createElement("td");
    					td5.textContent = paidOrders[i].item_amount;
    					
    					var td6 = document.createElement("td");
    					td6.textContent = "$" + paidOrders[i].item_price;
    					
    					var td7 = document.createElement("td");
    					td7.textContent = "$" + paidOrders[i].item_price * paidOrders[i].item_amount;
    					
    					tr.appendChild(td1);
    					tr.appendChild(td2);
    					tr.appendChild(td3);
    					tr.appendChild(td4);
    					tr.appendChild(td5);
    					tr.appendChild(td6);
    					tr.appendChild(td7);
    					
    					tableDetails.appendChild(tr);
         			}
        		}
        		
        	}
        }
        	