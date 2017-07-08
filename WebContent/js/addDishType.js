var addBtn = document.getElementById("addType");
var table = document.getElementById("productTypeTable");
var countVar = document.getElementById("count");
var countUpdate = document.getElementById("countUpdate");
var form = document.getElementById("theForm");
var count = 0;
var resultArea = document.getElementById('resultArea');

addBtn.onclick = function () {
    count++;
    countVar.value = count;

    var tr = document.createElement("tr");
    tr.setAttribute("id", "tr" + count);

    var tda = document.createElement("td");
    var showCot = document.createElement("span");
    var t = document.createTextNode(count);
    showCot.appendChild(t);
    tda.appendChild(showCot);
    tr.appendChild(tda);

    var td0 = document.createElement("td");
    var deleBtn = document.createElement("i");
    deleBtn.setAttribute("class", "fa fa-minus-square");
    deleBtn.setAttribute("name", "deleBtn" + count);
    deleBtn.setAttribute("id", "deleBtn" + count);
    deleBtn.setAttribute("onClick", "deleteRow(this,1)");


    var td1 = document.createElement("td");
    var typeName = document.createElement("input");
    typeName.setAttribute("type", "text");
    typeName.setAttribute("name", "typeName" + count);
    typeName.setAttribute("id", "typeName" + count);

    td0.appendChild(deleBtn);
    td1.appendChild(typeName);

    tr.appendChild(td0);
    tr.appendChild(td1);

    table.appendChild(tr);

}

$('document').ready(function () {

    $.getJSON("findProductType.do", {name:$('#restName').val()},
        function (data, textStatus, jqXHR) {
            
            var countInner = 0;
            // var table = $("<table id='myTable' style='width:100%'></table>")
            // $("<tr><td style='padding: 20px'>No.</td><td style='padding: 20px'>餐點類別</td></tr>").appendTo(table).appendTo($('#resultArea'));

            var tableX = document.createElement('table');
            tableX.id = 'tableX';
            var tr_fix = document.createElement('tr');
            var td_fix1 = document.createElement('td');
            var title1 = document.createTextNode('  ')
            // td_fix1.style.padding = '20px';
            td_fix1.align = 'center';

            td_fix1.appendChild(title1);

            var td_fix2 = document.createElement('td');
            var title2 = document.createTextNode('餐點類別')
            td_fix2.align = 'center';
            // td_fix2.style.padding = '20px';

            td_fix2.appendChild(title2);
            tableX.appendChild(td_fix1);
            tableX.appendChild(td_fix2);

            resultArea.appendChild(tableX);

            for (var i = 0; i < data.length; i++) {
                countInner++;
                var tr = document.createElement('tr');
                var td0 = document.createElement("td");
                td0.align = 'center';
                var deleBtn = document.createElement("i");
                deleBtn.setAttribute("class", "fa fa-minus-square");
				deleBtn.setAttribute("name", "deleBtn" + countInner);
				deleBtn.setAttribute("id", "deleBtn" + countInner);
				deleBtn.style.width = "100px";
				deleBtn.setAttribute("onClick", "deleteRow(this,2)");
                // deleBtn.appendTo(td0);
                td0.appendChild(deleBtn);

                var td1 = document.createElement("td");
                td1.align = 'center';
				var dishType = document.createElement("input");
				dishType.setAttribute("type", "text");
				dishType.setAttribute("name", "dishType" + countInner);
				dishType.setAttribute("id", "dishType" + countInner);
				dishType.setAttribute("value", data[i].prod_typeName);
				dishType.style.width = "150px";
				dishType.setAttribute("onchange", "modeifiedMark(this)");
                td1.appendChild(dishType);
                
                
                var td2 = document.createElement("td");
                td1.align = 'center';
				var typeNo = document.createElement("input");
				typeNo.setAttribute("type", "hidden");
				typeNo.setAttribute("name", "typeNo" + countInner);
				typeNo.setAttribute("id", "typeNo" + countInner);
				typeNo.setAttribute("value", data[i].prod_type_no);
				typeNo.setAttribute("onchange", "modeifiedMark(this)");
                td2.appendChild(typeNo);

                
                tr.appendChild(td0);
                tr.appendChild(td1);
                tr.appendChild(td2);
                tableX.appendChild(tr);

                countUpdate.value = countInner;
            }


            var submitBtn = document.createElement('input');
            submitBtn.type = 'submit';
            submitBtn.value = '修改餐點類別';
            submitBtn.className = 'btn btn-default';
            resultArea.appendChild(submitBtn);
            // alert('get Data');


            // alert(data);
        }
    );

});

var marks = [];
var updateList = document.getElementById('updateList');

function modeifiedMark(mark) {
	// alert("Hello ?")
	// alert(mark.id);

	var type = document.getElementById(mark.id);
	var markedTr = type.parentNode.parentNode;
	// alert("markedTr = "+markedTr.id);
	var updateTypeId = markedTr.firstChild.nextSibling.nextSibling.firstChild.value;
	// alert("updateTypeId = "+updateTypeId);

	marks.push(updateTypeId);
	// console.log("marks = " + marks);

	// get unique element
	function onlyUnique(value, index, self) {
		return self.indexOf(value) === index;
	}
    
	unique = marks.filter(onlyUnique);
	// alert("unique = " + unique);

	updateList.value = unique.toString();
	// alert("updateList = "+updateList.value);

}





function deleteRow(me,mode) {
    // alert("AAAA");
    // alert(me.id);

    var delBtn = document.getElementById(me.id);
    if(mode == 1){
        table.removeChild(delBtn.parentNode.parentNode);

    }else if(mode == 2){
        var idNumber = me.id.substring(me.id.lastIndexOf('n')+1)
        // alert('id =' + idNumber);
        var delType = $('#dishType'+idNumber).val();
        // alert('delType = ' + delType);
        if(confirm(' 確定要刪除 ? ')){

            $.getJSON("delProductType.do", {name:delType},
                function (data, textStatus, jqXHR) {
                    // alert('msg='+data);
                    if(data=="deleteSuccess"){
                        var tableX = document.getElementById('tableX');
                        tableX.removeChild(delBtn.parentNode.parentNode);
                        // alert('刪除成功');
                    }
                }
            );
        }
    }

}

var formX = document.getElementById('typeUpdate');

function validateUpdateForm(event) {
    // alert("validateForm");
    event.preventDefault();
    var hasErr = false;

    for (var i = 1; i <= count; i++) {
        var dishType = document.getElementById("dishType" + i);
        try {
            if (!dishType.value) {
                hasErr = true;
                alert('貼心小提醒 : 請刪除不必要的欄位喔~');
                break;
            } else {
            }
        } catch (err) {
        }
    }

    if (hasErr) {
        return false
    } else {
        formX.submit();
    }

}

function validateForm(event) {
    // alert("validateForm");
    event.preventDefault();
    var hasErr = false;

    for (var i = 1; i <= count; i++) {
        var typeName = document.getElementById("typeName" + i);
        try {
            if (!typeName.value) {
                hasErr = true;
                alert('貼心小提醒 : 請刪除不必要的欄位喔~');
                break;
            } else {
            }
        } catch (err) {
        }
    }

    if (hasErr) {
        return false
    } else {
        form.submit();
    }

}

