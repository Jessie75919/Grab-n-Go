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
  <link rel="stylesheet" href="../css/_storeFontDefault.css" type="text/css">
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
            <h3 id="textSet">菜&nbsp&nbsp單&nbsp&nbsp管&nbsp&nbsp理</h3>
          </div>
          <div id="formHeading">
            刪除修改餐點項目
          </div>
        </div>
      </div>
      <div id="leftMenu" class="row">
        <!-- 左側列表 -->
        <jsp:include page="../_IncludeJsp/StoreLogin_Menu.jsp" />
        <!-- 訂單表格開始 -->
        <div id="middleForm" class="col-md-9">
          <input id="count" name="countAA" value="0" style="display:none;">
            <input id="storeId" name="storeId" value="${StoreLoginOK['rest_id']}" style="display:none;">
             <input id="storeName" name="storeName" value="${StoreLoginOK['rest_name']}" style="display:none;">
        <form>
            <hr>
              <table id="menuTable">
                <tr>
                  <th></th>
                  <th>餐點名稱</th>
                  <th>餐點種類</th>
                  <th>餐點簡介</th>
                  <th>價格</th>
                  <th>餐點圖片</th>
                </tr>

				<div class='dialog' style='display: none'>
					<div style='border: 1px solid blue; padding: 12px;'>
						<span class='m'></span> 
						<input type='button' value='Yes' /> 
						<input type='button' value='No' />
					</div>
				</div>
	
				</table>
            <hr> 
           <div id="insertButton">
							<input
								type="submit" name="Submit" value="確定修改"
								class="btn btn-default">
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
  <script src="../js/editDish.js"></script>
</body>

</html>