<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" type="text/css">
  <link rel="stylesheet" href="https://pingendo.github.io/templates/blank/theme.css" type="text/css">
  <link rel="stylesheet" href="../css/_storeMenuProdType.css" type="text/css" >
  <link rel="stylesheet" href="../css/_storeFontDefault.css" type="text/css" >
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
          <img class="img-rounded" src="../images/restImage/af_logo.jpg">
          <br>
         </center>
        </div>
        <div class="col-md-9">
          <div id="formHeading">
            <br>
            <h3 id="textSet">菜&nbsp&nbsp單&nbsp&nbsp管&nbsp&nbsp理</h3>
          </div>
          <div id="formHeading">
            新增餐點項目前需先建立餐點類別呦～～
          </div>
        </div>
      </div>
      <div id="leftMenu" class="row">
        <!-- 左側列表 -->
        <jsp:include page="../_IncludeJsp/StoreLogin_Menu.jsp" />
        <!-- <div class="col-md-3">
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
        </div> -->
        <!-- 新增餐點類型開始 -->
        <div id="middleForm" class="col-md-9">
        <form>
            <hr>
              <table id="productTypeTable">
                <tr>
                  <th>建立餐點類別</th>
                  <th></th>
                </tr>
                <tr>
                  <td>1.</td>
                  <td><input type="text" name="" ></td>
                </tr>
                <tr>
                  <td>2.</td>
                  <td><input type="text" name="" ></td>
                </tr>
                <tr>
                  <td>3.</td>
                  <td><input type="text" name="" ></td>
                </tr>
                <tr>
                  <td>4.</td>
                  <td><input type="text" name="" ></td>
                </tr>
                <tr>
                  <td>5.</td>
                  <td><input type="text" name="" ></td>
                </tr>
                <tr>
                  <td>6.</td>
                  <td><input type="text" name="" ></td>
                </tr>
                <tr>
                  <td>7.</td>
                  <td><input type="text" name="" ></td>
                </tr>
                <tr>
                  <td>8.</td>
                  <td><input type="text" name="" ></td>
                </tr>
                <tr>
                  <td>9.</td>
                  <td><input type="text" name="" ></td>
                </tr>
                <tr>
                  <td>10.</td>
                  <td><input type="text" name="" ></td>
                </tr>
              
              
              </table>
            <hr> 
            <div id="insertButton">
              <input type="submit" name="Submit" value="新增餐點類別" class="btn btn-default">
            <div>
            </form>
        </div>
      </div>
      <div class="row">
        
      </div>
    </div>
  </div>
  <script type="text/javascript">
  </script>
  <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js"></script>
  <script src="https://pingendo.com/assets/bootstrap/bootstrap-4.0.0-alpha.6.min.js"></script>
</body>

</html>