<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<!--     <link rel="stylesheet" href="../css/bootstrap.min.css"> -->
<link rel="stylesheet" href="../css/_storeIndex.css">
<link rel="stylesheet" href="../css/_storeProfileEdit.css"
	type="text/css">
<title>GrabAndGo Menu System</title>
</head>
<!-- 商家已登入 -->
<!-- 修改餐廳個人資料頁面 -->
<body>
	<!--logo-->
	<header>
	<div class="logoArea">
		<img src="../images/share/logo.svg" alt="">
	</div>
	<!--進入區塊-->
	<div class="topTitle">
		<h2>PROFILE</h2>
	</div>
	</header>
	<!--店家profile-->
	<section class="container">
	<div class="row">
		<!-- 店家profile -->
		<div class="col-md-3">
			<center>
				<!-- <img class="img-rounded" src="../images/restImage/af_logo.jpg"> -->
				<img
					src='${pageContext.servletContext.contextPath}/_00_init/getImageA?id=${StoreLoginOK["rest_username"]}&type=restaurant&loc=logo'
					alt="Photo" title="Photo"> <br>
			</center>
		</div>
		<div class="col-md-9">
			<div>
				<h3>> 餐廳帳戶資料修改</h3>
			</div>
		</div>
	</section>

	<!--左側列表-->
	<section id="leftMenu" class="container"> <jsp:include
		page="../_IncludeJsp/_storeMenuTest.jsp" /> <!-- 新增餐點項目開始 -->
	<div id="profileArea" class="col-md-9">
		<!-- <form> -->
		<div style="font-size: 14px; color: blue">${MsgOK.UpdateOk}${MsgOK.UpdateFail}</div>
		<div id="titleP">
			<h4>
				My Account <br>
			</h4>
			<hr>
		</div>
		<form method="POST" class="formcontent" ENCTYPE="multipart/form-data"
			action="<c:url value='storeUpdate.do' />">
			<div id="profileArea">
				<!-- 會員帳號 -->
				<div class="form-group">
					<label for="inputPassword3">Username : </label> <input type="text"
						name="username" value="${StoreLoginOK['rest_username']}"
						id="username" class="form-control" readonly>
				</div>
				<!--             <div class="form-group"> -->
				<!--               <label for="inputEmail3" class="col-sm-2 control-label">Username : </label> -->
				<!--               <div class="col-sm-10"> -->
				<!--                 <input name="mid" value="${param.mid}" id="mid" type="text" class="form-control" > -->
				<!--                  -->
				<%--                 <p class="form-control-static">${sessionScope.user}</p> --%>
				<!--               </div> -->
				<!--             </div> -->

				<!-- 密碼  -->
				<div class="form-group">
					<label for="inputPassword3">Password : </label> <input
						type="password" name="password"
						value="${sessionScope.StorePassword}" id="password"
						class="form-control  validate[required] text-input">
				</div>
				<div id="titleP">
					<br>
					<h4>Restaurant details</h4>
					<hr>
				</div>
				<!-- 餐廳名稱 -->
				<div class="form-group">
					<label>餐廳名稱：</label> <input type="text" name="StoreName"
						value="${StoreLoginOK['rest_name']}" id="StoreName"
						class="form-control" readonly>
				</div>
				<!-- 餐廳類別 -->
				<div class="form-group">
					<label>餐廳類別：</label> <input type="text" name="StoreType"
						value="${StoreLoginOK['rest_type']}" id="StoreType"
						class="form-control" readonly>
				</div>
				<!-- 餐廳分店 -->
				<div class="form-group">
					<label>餐廳分店：</label> <input type="text" name="branch"
						value="${StoreLoginOK['rest_branch']}" id="branch"
						class="form-control" readonly>
				</div>
				<!-- 餐廳負責人 -->
				<div class="form-group">
					<label>店家負責人：</label> <input type="text" name="owner"
						value="${StoreLoginOK['rest_owner']}" id="owner"
						class="form-control" readonly>
				</div>
				<!-- 餐廳地址 -->
				<div class="form-group">
					<label>餐廳地址：</label> <input type="text" name="address"
						value="${StoreLoginOK['rest_address']}" id="address"
						class="form-control validate[required] text-input">
				</div>
				<!-- 餐廳電話 -->
				<div class="form-group">
					<label>聯絡電話：</label> <input type="text" name="tel"
						value="${StoreLoginOK['rest_phone']}" id="tel"
						class="form-control validate[required,custom[phone]] text-input">
				</div>
			</div>
			<!-- E-mail -->
			<div class="form-group">
				<label>E-mail：</label> <input type="text" name="eMail"
					value="${StoreLoginOK['rest_email']}" id="eMail"
					class="form-control validate[required, custom[email]] text-input">
			</div>
			<!-- 官方網站 -->
			<div class="form-group">
				<label>官方網站：</label> <input type="text" name="url"
					value="${StoreLoginOK['rest_url']}" id="url" class="form-control">
				<input type="text" name="langitude" style="display: none;" value=""
					id="langitude"> <input type="text" name="latitude"
					style="display: none;" value="" id="latitude">
			</div>
			<!-- 照片檔案 -->
			<div class="form-group">
				<label class="col-sm-2 control-label">主頁橫幅圖：</label>
				<div class="col-sm-10">
					<input type="file" size="40" id="mainBanner" name="mainBanner"
						class="exampleInputFile"> <BR> <span
						style="color: red; font-size: 12px;">${MsgMap.errorPicture}</span>
				</div>
			</div>

			<div class="form-group">
				<label class="col-sm-2 control-label">Logo 圖片：</label>
				<div class="col-sm-10">
					<input type="file" size="40" id="logo" name="logo"
						class="exampleInputFile"> <BR> <span
						style="color: red; font-size: 12px;">${MsgMap.errorPicture}</span>
				</div>
			</div>

			<div class="form-group">
				<label class="col-sm-2 control-label">首頁圖片：</label>
				<div class="col-sm-10">
					<input type="file" size="40" iid="coverImg" name="coverImg"
						class="exampleInputFile"> <BR> <span
						style="color: red; font-size: 12px;">${MsgMap.errorPicture}</span>
				</div>
			</div>
			<!-- 按鈕 -->
			<div class="form-group">
				<!-- <div class="col-sm-offset-2 col-sm-10"> -->
				<div id="btnS">
					<input type="submit" name="submit" id="holyshit"
						value="save changes" class="btn btn-warning">
				</div>
				<div id="btnC">
					<input type="reset" name="cancel" id="cancel" value="cancel"
						class="btn btn-default">
				</div>
			</div>
	</div>
	</form>
	</div>
	</div>
	</div>
	<div class="row"></div>
	</div>
	</div>
	</section>
	<!--form check -->
	<link rel="stylesheet"
		href="../javascript/validation/validationEngine.jquery.css">
	<script type="text/javascript" charset="utf-8"
		src="../javascript/validation/languages/jquery.validationEngine-zh_TW.js"></script>
	<script type="text/javascript" charset="utf-8"
		src="../javascript/validation/jquery.validationEngine.js"></script>
	<!-- form check end-->
	<script src="../javascript/share.js"></script>
</body>

</html>