<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" type="text/css">
  <link rel="stylesheet" href="https://pingendo.github.io/templates/blank/theme.css" type="text/css">
  <link rel="stylesheet" href="../css/_storeIndexAfterLogin.css" type="text/css">
  <title>Welcome to GrabAndGo</title>
</head>
<!-- 商家登入成功畫面 -->
<!-- 待處理訂單頁面 -->
<body>
 <div class="py-5">
   <!--  <div class="container">
      <div class="row"> -->
        <!-- <div class="col-md-12"> -->
        <div>
          <center>
          <img width="350px" src="../images/share/logo.svg">
          </center>
        </div>
        <!-- </div> -->
      <!-- </div>
      </div> -->
      </div>
      <div id="topBlock" class="topBlock">
      	<h2>本&nbsp&nbsp日&nbsp&nbsp訂&nbsp&nbsp單</h2>
      </div> 
      <!-- <div class="row">
        
      </div>
    </div>
  </div> -->
  <div class="py-5">
    <div class="container">
      <div class="row">
        <!-- 店家profile -->
        <div class="col-md-3">
        <center>
         <!-- <img class="img-rounded" src="../images/restImage/af_logo.jpg"> -->
         <img  src='${pageContext.servletContext.contextPath}/_00_init/getImageA?id=${StoreLoginOK["rest_username"]}&type=restaurant&loc=logo' alt="Photo" title="Photo">
          <br>
         </center>
        </div>
        <div class="col-md-9">
          <!-- <div>
          	<br>
          	<h3 id="formHeading">本&nbsp&nbsp日&nbsp&nbsp訂&nbsp&nbsp單</h3>
          </div>
          <br>
          <div id="orderStatusHeading">
          	待處理訂單
          </div>
        </div> -->
      </div>
      <div id="leftMenu" class="row">
        <!-- 左側列表 -->
        <jsp:include page="../_IncludeJsp/StoreLogin_Menu.jsp" />
        <!-- 訂單表格開始 -->
        <div id="middleForm" class="col-md-9" >
        <!-- 按鈕區塊 -->
          <div>
            <ul class="nav nav-pills">
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
                <!-- 每筆訂單資訊, 預設一頁顯示15筆 -->
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
                <tr>
                  <td nowrap="">2017/05/19 11:05:31</td>
                  <td>A002</td>
                  <td>李小妹</td>
                  <td><a href="#">XX003</a></td>
                  <td>$450</td>
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
                <tr>
                  <td nowrap="">2017/05/19 11:05:31</td>
                  <td>A002</td>
                  <td>李小妹</td>
                  <td><a href="#">XX003</a></td>
                  <td>$450</td>
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
                <tr>
                  <td nowrap="">2017/05/19 11:05:31</td>
                  <td>A002</td>
                  <td>李小妹</td>
                  <td><a href="#">XX003</a></td>
                  <td>$450</td>
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
      <div class="row">
        
      </div>
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