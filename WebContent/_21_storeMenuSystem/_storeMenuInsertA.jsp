<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <!--載入icon-->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" type="text/css">
    <!--載入Bootstrap-->
    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <link rel="stylesheet" href="../css/_storeIndex.css">
    <title>GrabAndGo Menu System</title>
</head>
<!-- 商家菜單管理 -->
<!-- 新增餐點項目 -->
<body>
    <!--logo-->
    <header>
        <div class="logoArea">
            <img src="../images/share/logo.svg" alt="">
        </div>
        <!--進入區塊-->
        <div class="topTitle">
            <h2 id="addHelper">菜單管理</h2>
        </div>
    </header>
    <!--店家profile-->
    <section class="container">
        <div class="row">
           <!-- 店家profile -->
		<jsp:include page="../_IncludeJsp/Store_Profile.jsp" />
            <div class="col-md-9">
                <div>
                    <h3 id="helper"> 新增餐點項目</h3>
                </div>
            </div>
    </section>

    <!--左側列表-->
    <section id="leftMenu" class="container">
    	 <jsp:include page="../_IncludeJsp/_storeMenuTest.jsp" />
            <!-- 新增餐點項目開始 -->
				<div id="middleForm" class="col-md-9">
        <div>${MsgMap.noData}</div>
       	<div id="showMsg">${MsgMap.NeedOne}${MsgOK.OK}</div> 
        <form ENCTYPE="multipart/form-data" id="theForm" action="addNewDish.do" 
        method="post" class="formcontent" onsubmit="return validateForm(event);">
            <hr>
            <input id="count" name="countAA" value="0" style="display:none;">
            <input id="storeId" name="storeId" value="${StoreLoginOK['rest_id']}" style="display:none;">
            <input id="storeName" name="storeName" value="${StoreLoginOK['rest_name']}" style="display:none;">
              <table id="menuTable">
                <tr>
                  <th></th>
                  <th>餐點名稱</th>
                  <th>餐點種類</th>
                  <th>餐點簡介</th>
                  <th>價格</th>
                  <th>餐點圖片</th>
                </tr>
              
              </table>
            <hr> 
            <div id="insertButton">
            <span id="addMenu"><img src="../images/share/plus.png"></span>
              <input type="submit" name="Submit" value="確認表單" class="btn btn-default">
            <div>
            </form>
        </div>
      </div>
        <div class="row">

        </div>
        </div>
        </div>
    </section>
    	<script type="text/javascript"
		src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
    <!-- 載入新增餐點js -->
	<script src="../js/addDishRow.js"></script>
</body>

</html>