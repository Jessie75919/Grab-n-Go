<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" type="text/css">
  <link rel="stylesheet" href="https://pingendo.github.io/templates/blank/theme.css" type="text/css">
  <link rel="stylesheet" href="../css/_storeIndexLoginOK.css" type="text/css">
  <title>Welcome to GrabAndGo</title>
</head>
<!-- 商家登入成功畫面 -->
<!-- 待處理訂單頁面 -->
<body>
  <div class="py-5">
    <div class="container">
      <div class="row">
        <div class="col-md-12">
<!--           <h1 class="text-primary text-center">Logo</h1> -->
			<center>
				<img width="400" src="../images/share/logo.svg">
			</center>
        </div>
      </div>
    </div>
  </div>
  <div class="py-5">
    <div class="container">
      <div class="row">
        <!-- 店家profile -->
        <div class="col-md-4">
          <img class="img-rounded" src="../images/restImage/af_logo.jpg">
          <br> </div>
        <div class="col-md-8">
          <div></div>
          <div> </div>
        </div>
      </div>
      <div class="row">
        <!-- 左側列表 -->
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
        <!-- 訂單表格開始 -->
        <div id="middleForm" class="col-md-9" >
        <!-- 按鈕區塊 -->
          <div id="leftButtoms">
            <ul id="leftOrderButtoms">
              <li id="leftOrderButtomGroup"><a href="#">待處理訂單</a></li>
              <li id="leftOrderButtomGroup"><a href="#">已完成訂單</a></li>
              <li id="leftOrderButtomGroup"><a href="#">已付款訂單</a></li>
            </ul>
          </div>
            <hr>
              <table id="orderTable">
                <tr>
                  <th>訂購日期</th>
                  <th>顧客編號</th>
                  <th>取餐顧客</th>
                  <th>訂單編號</th>
                  <th>總金額</th>
                  <th>訂單狀態</th>
                  <th>Action</th>
                </tr>
                <!-- 每筆訂單資訊 -->
                <tr>
                  <td nowrap="">2017/05/19 11:05:31</td>
                  <td>A001</td>
                  <td>王小明</td>
                  <td><a href="#">XX001</a></td>
                  <td>$250</td>
                  <td>處理中</td>
                  <td id="cancelB"><a href="#" onclick="orderCancel">取消訂單</a></td>
                </tr>
                <tr>
                  <td nowrap="">2017/05/19 11:05:31</td>
                  <td>A002</td>
                  <td>張小茶</td>
                  <td><a href="#">XX002</a></td>
                  <td>$300</td>
                  <td>處理中</td>
                  <td><a href="#">取消訂單</a></td>
                </tr>
                <tr>
                  <td nowrap="">2017/05/19 11:05:31</td>
                  <td>A002</td>
                  <td>李小妹</td>
                  <td><a href="#">XX003</a></td>
                  <td>$450</td>
                  <td>處理中</td>
                  <td><a href="#">取消訂單</a></td>
                </tr>

              </table>
            <hr> 
        </div>
      </div>
      <div class="row"></div>
    </div>
  </div>
  <script type="text/javascript">

    // 觸發取消訂單事件
    function orderCancel(){
          var string = '確定取消此筆訂單嗎？';
          confirm(string);
        }
        
    

  </script>
  <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js"></script>
  <script src="https://pingendo.com/assets/bootstrap/bootstrap-4.0.0-alpha.6.min.js"></script>
</body>

</html>