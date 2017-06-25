<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"
	type="text/css">
<link rel="stylesheet"
	href="https://pingendo.github.io/templates/blank/theme.css"
	type="text/css">
<link rel="stylesheet" href="../css/_storeMenuProdType.css"
	type="text/css">
<link rel="stylesheet" href="../css/_storeFontDefault.css"
	type="text/css">
<title>GrabAndGo Menu System</title>
</head>
<!-- 菜單管理 新增餐點類型頁面 -->
<body>
	<div class="py-5">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<center>
						<img width="350px" src="../images/share/logo.svg">
						
					</center>
				</div>
			</div>
			<div class="row">
				<!-- <div id="formHeading" class="col-md-12">
          <br>
          <h3>本日訂單</h3>
        </div> -->
			</div>
		</div>
	</div>
	<div class="py-5">
		<div class="container">
			<div class="row">
				<!-- 店家profile -->
				<div class="col-md-3">
					<center>
						 <img src='${pageContext.servletContext.contextPath}/_00_init/getImageA?id=${StoreLoginOK["rest_username"]}&type=restaurant&loc=logo' alt="Photo" title="Photo">
						<br>
					</center>
				</div>
				<div class="col-md-9">
					<div id="formHeading">
						<br>
						<h3 id="textSet">菜&nbsp&nbsp單&nbsp&nbsp管&nbsp&nbsp理</h3>
					</div>
					
<!-- 					<div id="formHeading">新增餐點項目前需先建立餐點類別呦～～</div> -->
				</div>
			</div>
			<div id="leftMenu" class="row">
				<!-- 左側列表 -->
				<jsp:include page="../_IncludeJsp/StoreLogin_Menu.jsp" />

				<!-- 新增餐點類型開始 -->
				<div id="middleForm" class="col-md-9">
				<div id="showMsg">${MsgMap.NeedOne}${MsgOK.OK}</div> 
					  <form id="theForm" action="addNewDishType.do" 
        method="post" class="formcontent" onsubmit="return validateForm(event);">
						<hr>
						<input id="count" name="countAA" value="0" style="display:none;">
						<table id="productTypeTable">
							<tr>
								<th></th>
								<th>建立餐點類別</th>
							</tr>
						</table>
						<hr>
						<div id="insertButton">
							<span id="addType"><img src="../images/share/plus.png"></span> 
							<input
								type="submit" name="Submit" value="新增餐點類別"
								class="btn btn-default">
							<div>
					</form>
				</div>
			</div>
			<div class="row"></div>
		</div>
	</div>
	<script type="text/javascript">
		
	</script>
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js"></script>
	<script
		src="https://pingendo.com/assets/bootstrap/bootstrap-4.0.0-alpha.6.min.js"></script>
	<script src="../js/addDishType.js"></script>
</body>

</html>