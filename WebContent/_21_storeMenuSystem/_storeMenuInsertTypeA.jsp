<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<!--載入icon-->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"
	type="text/css">
<!--載入Bootstrap-->
<link rel="stylesheet" href="../css/bootstrap.min.css">
<link rel="stylesheet" href="../css/_storeIndex.css">
<title>GrabAndGo Menu System</title>
</head>
<!-- 商家菜單管理 -->
<!-- 新增餐點類別 -->
<body>
	<!--logo-->
	<header>
	<div class="logoArea">
		<img src="../images/share/logo.svg" alt="">
	</div>
	<!--進入區塊-->
	<div class="topTitle">
		<h2>菜單管理</h2>
	</div>
	</header>
	<!--店家profile-->
	<section class="container">
	<div class="row">
		<!-- 店家profile -->
		<jsp:include page="../_IncludeJsp/Store_Profile.jsp" />
		<div class="col-md-9">
			<div>
				<h3>> 新增餐點類別</h3>
			</div>
		</div>
	</section>

	<!--左側列表-->
	<section id="leftMenu" class="container"> <jsp:include
		page="../_IncludeJsp/_storeMenuTest.jsp" /> <!-- 新增餐點類型開始 -->
	<div id="middleForm" class="col-md-9">
		<div id="showMsg">${MsgMap.NeedOne}${MsgOK.OK}</div>
		<form id="theForm" action="addNewDishType.do" method="post"
			class="formcontent" onsubmit="return validateForm(event);">
			<hr>
			<input id="count" name="countAA" value="0" style="display: none;">
			<table id="productTypeTable">
				<tr>
					<!-- 								<th></th> -->
					<th>建立餐點類別</th>
				</tr>
			</table>
			<hr>
			<div id="insertButton">
				<span id="addType"><img src="../images/share/plus.png"></span>
				<input type="submit" name="Submit" value="新增餐點類別"
					class="btn btn-default">
			</div>
		</form >
		
		<!-- 顯示已新增的餐點 -->
		<form id="typeUpdate" action="updateDishType.do" method="post" onsubmit="return validateUpdateForm(e)">
		<div id="resultArea" style="margin-top: 30%; font-weight: bold;">
		 <input id="countUpdate" name="countBB" value="0" style="display:none;">
		<input id="restName" type="hidden" value="${StoreLoginOK['rest_name']}">
		<input id="updateList" name="updateList" value="0" style="display:none;">
			<h4>已新增的餐點類別：</h4>
			<div id="showAA">${MsgMap.NeedOneUpdate}${MsgOK.UpdateOK}</div> 
		</div>
		</form>
		
	</div>
	</div>
	<div class="row"></div>
	</div>
	</div>
	</section>
	<!-- 載入新增餐點js -->
	<script type="text/javascript"
		src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
	
	<script src="../js/addDishType.js"></script>
</body>

</html>