<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" type="text/css">
  <link rel="stylesheet" href="https://pingendo.github.io/templates/blank/theme.css" type="text/css">
  <link rel="stylesheet" href="../css/_storeMenuEditDelete.css" type="text/css">
  <title>GrabAndGo Menu System</title>
</head>
<!-- 菜單管理 刪除修改頁面 -->
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
          <img class="img-rounded" src="../images/restImage/af_logo.jpg">
          <br>
         </center>
        </div>
        <div class="col-md-9">
          <div id="formHeading">
            <br>
            <h3>菜&nbsp&nbsp單&nbsp&nbsp管&nbsp&nbsp理</h3>
          </div>
          <div id="formHeading">
            刪除修改餐點項目
          </div>
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
        <div id="middleForm" class="col-md-9">
        <form>
            <hr>
              <table id="menuTable">
                <tr>
                  <th>&nbsp</th>
                  <th>&nbsp</th>
                  <th>餐點名稱</th>
                  <th>餐點種類</th>
                  <th>餐點簡介</th>
                  <th>價格</th>
                  <th>餐點圖片</th>
                </tr>
                <tr>
                  <td><i id="editButton" class="fa fa-pencil-square-o"></i></td>
                  <td><i class="fa fa-minus-square"></i></td>
                  <td><input type="text" name="" ></td>
                  <td><input type="text" name=""></td>
                  <td><input type="text" name="" placeholder="optional"></td>
                  <td><input type="text" name=""></td>
                  <td><input type="file" name=""></td>
                </tr>
                <tr>
                  <td><i class="fa fa-pencil-square-o"></i></td>
                  <td><i class="fa fa-minus-square"></i></td>
                  <td><input type="text" name="" ></td>
                  <td><input type="text" name=""></td>
                  <td><input type="text" name="" placeholder="optional"></td>
                  <td><input type="text" name=""></td>
                  <td><input type="file" name=""></td>
                </tr>
               <tr>
                  <td><i class="fa fa-pencil-square-o"></i></td>
                  <td><i class="fa fa-minus-square"></i></td>
                  <td><input type="text" name="" ></td>
                  <td><input type="text" name=""></td>
                  <td><input type="text" name="" placeholder="optional"></td>
                  <td><input type="text" name=""></td>
                  <td><input type="file" name=""></td>
                </tr>
              
              </table>
            <hr> 
            <div id="insertButton">
              <input type="submit" name="Submit" value="" class="btn btn-default">
            <div>
            </form>
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