<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <!--載入icon-->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" type="text/css">
    <!--載入Bootstrap-->
    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <link rel="stylesheet" href="../css/_storeIndex.css">

    <title>GrabAndGo Order Details</title>
</head>
<!--點選訂單明細後的頁面-->
<!--商家訂單明細頁面-->

<body>
    <!--logo-->
    <header>
        <div class="logoArea">
            <img src="image/logo.svg" alt="">
        </div>
        <!--進入區塊-->
        <div class="topTitle">
            <h2>本日訂單</h2>
        </div>
    </header>
    <!--店家profile-->
    <section class="container">
        <div class="row">
            <!-- 店家profile -->
            <div class="col-md-3">
                <center>
                    <!-- <img class="img-rounded" src="../images/restImage/af_logo.jpg"> -->
                    <img src='${pageContext.servletContext.contextPath}/_00_init/getImageA?id=${StoreLoginOK["rest_username"]}&type=restaurant&loc=logo'
                        alt="Photo" title="Photo">
                    <br>
                </center>
            </div>
            <div class="col-md-9">
                <div>
                    <h3>> 訂單明細</h3>
                    <h4>訂單編號：XX001</h4>
                </div>
            </div>
    </section>

    <!--左側列表-->
    <section id="leftMenu" class="container">
        <div class="row">
            <div class="col-md-3">
                <div id="topName"> R_name </div>
                <ul id="leftMenu" class="list-group">
                    <li class="list-group-item"><i class="fa fa-user-circle-o fa-fw"></i>Profile</li>
                    <li class="list-group-item">
                        <a href="#">修改個人資料</a>
                    </li>
                    <li class="list-group-item">
                        <a href="#">登出</a>
                    </li>
                    <li class="list-group-item"><i class="fa fa-file-text-o fa-fw"></i>本日訂單</li>
                    <li class="list-group-item">
                        <a href="#">待處理訂單</a>
                    </li>
                    <li class="list-group-item">
                        <a href="#">已完成訂單</a>
                    </li>
                    <li class="list-group-item">
                        <a href="#">已付款訂單</a>
                    </li>
                    <li class="list-group-item">
                        <a href="#">查詢訂單</a>
                    </li>
                    <li class="list-group-item"><i class="fa fa-hand-o-right fa-fw"></i>歷史訂單</li>
                    <li class="list-group-item">
                        <a href="#">歷史訂單查詢</a>
                    </li>
                    <li class="list-group-item">
                        <a href="#">餐點熱銷排行</a>
                    </li>
                    <li class="list-group-item"><i class="fa fa-pencil-square-o fa-fw"></i>菜單管理</li>
                    <li class="list-group-item">
                        <a href="#">新增餐點項目</a>
                    </li>
                    <li class="list-group-item">
                        <a href="#">修改餐點項目</a>
                    </li>
                    <li class="list-group-item"><i class="fa fa-usd fa-fw"></i>帳務分析</li>
                    <li class="list-group-item">
                        <a href="#">營業額統計</a>
                    </li>
                </ul>
            </div>
            <!-- 表格開始 -->
            <div id="middleForm" class="col-md-9">
                <!--訂單狀態按鈕區塊-->
                <div>
                    <!--<ul class="nav nav-tabs nav-justified">-->
                    <ul class="nav nav-pills nav-justified">
                        <li role="presentation"><a href="../_02_storeLogin/_storeIndex.jsp">待處理訂單</a></li>
                        <li role="presentation"><a href="_storeOrderUnpaid.jsp">已完成訂單</a></li>
                        <li role="presentation"><a href="_storeOrderPaid.jsp">已付款訂單</a></li>
                    </ul>

                </div>
                <hr>
                <!--</div>-->
            <!--<div class="orderTable" >-->
                <table id="orderTable">
                    <tr>
                        <th>顧客名稱</th>
                        <th>餐點名稱</th>
                        <th>餐點編號</th>
                        <th>備註</th>
                        <th>數量</th>
                        <th>單價</th>
                        <th>Subtotal</th>
                    </tr>
                    <!-- 訂單明細-->
                    <tr>
                        <td>王小明</td>
                        <td>雞腿飯</td>
                        <td>A001</td>
                        <td></td>
                        <td>1</td>
                        <td>$90</td>
                        <td>$90</td>
                    </tr>
                    <tr>
                        <td></td>
                        <td>紅燒牛肉麵</td>
                        <td>A002</td>
                        <td>三個都不加蔥</td>
                        <td>1</td>
                        <td>$120</td>
                        <td>$360</td>
                    </tr>
                </table>
                <hr>
                <h4 class="totalPrice">總金額：＄450</h4>
            </div>
            <div class="checkOutBtn" id="checkOut">
             <input class="btn btn-primary" type="submit" value="顧客結帳">
            </div>           
        </div>
        </div>
        <div class="row">

        </div>
        </div>
        </div>
    </section>

</body>

</html>