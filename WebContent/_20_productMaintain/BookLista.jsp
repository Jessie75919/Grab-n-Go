<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<?php 
/* 
  // 程式功能: 
  //  1. 顯示所有書籍(商品)資訊，書籍資訊以分頁的方式顯示，每頁顯示四筆書籍。
  //     使用者可以按下『第一頁』、『前一頁』、『下一頁』與『最後頁』逐頁地
  //     瀏覽商品。
  //  2. 提供『新增書籍』資料、『修改書籍資料』與『刪除某本書籍』的功能。
  //     
  //  3. 當使用者要新增某本書籍資料時，直接按下右上角『新增書籍』的超連結便可
  //     進入『新增書籍資料』的畫面(由BookInsert.php提供)，輸入新的書籍資料。
  //     當使用者要修改某本書籍資料時，可直接點選該本書籍的『書名』超連結，便
         可進入『更新書籍資料』的畫面(由BookUpdate.php提供)，可對該本書籍進行
         資料修改或刪除紀錄。
*/
/*
   有些資料屬於動態內容，不應該被瀏覽器暫存到硬碟上的記憶體。
   避免瀏覽器或Proxy Server暫存(Cache)資料，我們可在程式中使用下列敘述
*/

session_start(); 
?>
<?php require_once('../Connections/conn.php'); ?>
<?php
$currentPage = $_SERVER["PHP_SELF"];
$maxRows = 4;    // 每頁顯示四筆記錄
$pageNum = 0;    // 將要顯示哪一頁的資料(0表示第一頁)

// 設定一個SESSION變數BookListMaxRows，內容為每頁至多顯示之記錄數，
// 其他程式需要此資料。
$_SESSION['BookListMaxRows'] = $maxRows ;

if (isset($_GET['pageNum'])) {
  $pageNum = $_GET['pageNum'];
}
$startRow = $pageNum * $maxRows;    // 算出將要顯示的分頁是由哪一筆開始(0表示第一筆)

mysql_select_db($database_conn, $conn);
$query_all_records  = "SELECT b.*, bc.name  FROM book b join bookcompany bc on b.companyID = bc.id";

// 如果外界有透過GET方法傳入totalRows(表格的總紀錄筆數)
if (isset($_GET['totalRows'])) {
  $totalRows = $_GET['totalRows'];
} else {
  // 否則到資料庫讀取符合條件的結果集: $all_Recordset1
  $all_Recordset1 = mysql_query($query_all_records);
  // 取出$all_Recordset1內的紀錄筆數
  $totalRows = mysql_num_rows($all_Recordset1);
}
// 計算有幾頁(Page) 0 表示有1頁，1 表示有2頁，
// 例如：有15筆記錄，每頁3筆, 總共5頁($totalPages的值為4)
$totalPages = ceil($totalRows/$maxRows)-1;  // 

$queryString_Recordset1 = "&totalRows=$totalRows";
?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <title>Hold不住購物商城</title>
  <link href="../style.css" rel="stylesheet" type="text/css" />
</head>
<body  background="../img/bookMaintain.jpg" >
  <h3 align="center">瀏覽書籍</h3>
  <hr />
  <br />
  <p />
<table  class="table_color" width="720" border="2" align="center" cellpadding="2" cellspacing="2" bordercolorlight="#FFFFFF" bordercolordark="#330033">
  <tr height='32' class="title_font">
    <td width="60"><div align="center">流水號</div></td>
    <td width="220"><div align="center">書名</div></td>
    
    <td width="100"><div align="center">作者</div></td>
   
    <td width="42"><div align="center">單價</div></td>
     
    <td width="80"><div align="center">出版社</div></td>
    
    <td width="64"><div align="center">圖片</div></td>
    
    <td width="80"><div align="center">書號</div></td>
  </tr>
  <?php 
     $query_limit_Recordset1 = $query_all_records  . " LIMIT " . $startRow . "," . $maxRows;
       
     // 由資料庫中讀取符合條件的所有記錄，放入變數$Recordset1內
     $Recordset1 = mysql_query($query_limit_Recordset1, $conn) or die(mysql_error());
      
     // 從資料集取出一筆記錄內的資料，再將其轉換為陣列，索引值只能是字串(關聯索引)。
     while ($row_Recordset1 = mysql_fetch_assoc($Recordset1))  { //此迴圈取出放在陣列$row_Recordset1內的資料  ?>
       <tr>  
         <td><?php echo $row_Recordset1['bookID']; ?></td> 
         <td><a href="BookUpdate.php?bookID=<?php echo $row_Recordset1['bookID']; ?>"><?php echo $row_Recordset1['title']; ?></a></td>
         <td><?php echo $row_Recordset1['author']; ?></td>
         <td><?php echo $row_Recordset1['price']; ?></td>
         <td width="100"><?php echo substr($row_Recordset1['name'], 0, 12); ?></td>
         <td>
     <!--
      <img src="img/smallPic/<?php //echo $row_Recordset1['image']; ?>" width="48" height="64" alt="" />
      -->
     <!--  
      <img src="此屬性可以是一張圖片的URL或是一個可以送回一張圖片的PHP程式,
                需要傳入圖片的識別鍵值(即圖片所屬紀錄的Primary Key)
      " ...>
     -->
            <img src="BookCoverImage.php?searchKey=<?php echo $row_Recordset1['bookID']; ?>" width="48" height="64" alt="" />
         </td>
         <td ><?php echo $row_Recordset1['bookNo']; ?></td>
       </tr> 
      <?php } 	?>
</table>
<p/>
<div id="bpaging">
<table border="0">
  <tr>
    <td width='76'><?php if ($pageNum > 0) { // Show if not first page ?>
        <div id="blfirst">
        <!-- 第一頁 -->
           <a href="<?php echo("$currentPage?pageNum=0$queryString_Recordset1"); ?>">第一頁</a>         
        </div>
        <?php } // Show if not first page ?>
    </td>
    <td width='76'><?php if ($pageNum > 0) { // Show if not first page ?>
        <div id="blprev">
        <!-- 前一頁 -->       
           <a href="<?php
                       $pm =  max(0, $pageNum - 1);  // $pm 最小為 0
                       echo("$currentPage?pageNum=$pm$queryString_Recordset1"); 
                    ?>">前一頁
           </a>                    
        </div>
        <?php }  ?>
    </td>
    <td width='76'><?php if ($pageNum < $totalPages) { // Show if not last page ?>
        <div id="blnext">
        <!-- 下一頁 -->
           <a href="<?php 
                      $pm =  min($totalPages, $pageNum + 1);  // $pm 最大為 $totalPages           
                      echo ("$currentPage?pageNum=$pm$queryString_Recordset1"); 
                    ?>">下一頁
           </a>
        </div>
        <?php }  ?>
    </td>
    <td width='76'><?php if ($pageNum < $totalPages) { // 如果目前顯示的不是最後一頁  ?>
        <div id="bllast">
        <!-- 最後頁 -->
           <a href="<?php 
                 echo ("$currentPage?pageNum=$totalPages$queryString_Recordset1"); ?>">最後頁
           </a>
        </div>
          <?php }  ?>
    </td>
  </tr>
</table>
</div>

<div id="insert">
       <a href="BookInsert.php">新增紀錄</a>          
</div>
   <!-- 顯示執行的結果  -->      
<div id="message">
    <?php 
        if (isset($_SESSION['Book_Message'])) {
			echo  $_SESSION['Book_Message'];
			unset($_SESSION['Book_Message']);				
		} 
    ?>
</div>		  
</body>
</html>
<?php
   mysql_free_result($Recordset1);
?>