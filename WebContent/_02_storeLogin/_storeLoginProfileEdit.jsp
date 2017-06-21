<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
        <html>

        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1">
            <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" type="text/css">
            <link rel="stylesheet" href="https://pingendo.github.io/templates/blank/theme.css" type="text/css">
            <link rel="stylesheet" href="../css/_storeLoginProfileEdit.css" type="text/css">
            <title>GrabAndGo Edit Profile</title>
        </head>
        <!-- 餐廳 修改帳戶資料 -->

        <body>
            <div class="py-5">
                <div class="container">
                    <div class="row">
                        <div class="col-md-12">
                            <center>
                                <a href="_storeIndexAfterLogin.jsp"><img width="350" src="../images/share/logo.svg"></a>
                            </center>
                            <div id="topHeading">
                                <h4>餐廳帳戶資料修改</h4>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="py-5">
                <div class="container">
                    <div class="row">
                        <!-- 店家profile -->
                        <div class="col-md-4">
                            <img src='${pageContext.servletContext.contextPath}/_00_init/getImageA?id=${StoreLoginOK["rest_username"]}&type=restaurant&loc=logo' alt="Photo" title="Photo">
                            <br>
                        </div>
                        <div class="col-md-8">
                            <div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <!-- 左側列表 -->
                        <jsp:include page="../_IncludeJsp/StoreLogin_Menu.jsp" />
                        <!-- 個人資料修改表格開始 -->
                        <div id="profileArea" class="col-md-9">
                            <!-- <form> -->
                            <div style="font-size:14px; color:blue">${MsgOK.UpdateOk}${MsgOK.UpdateFail}</div>
                            <div id="titleP">
                                <h5>My Account <br></h5>
                                <hr>
                            </div>
                            <form method="POST" class="formcontent" ENCTYPE="multipart/form-data" action="<c:url value='storeUpdate.do' />" >
                                <!-- 會員帳號 -->
                                <div class="form-group">
                                    <label for="inputPassword3" class="col-sm-2 control-label">Username : </label>
                                    <div class="col-sm-10">
                                        <input type="text" name="username" value="${StoreLoginOK['rest_username']}" id="username" class="form-control" readonly>
                                    </div>
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
                                        <label for="inputPassword3" class="col-sm-2 control-label">Password : </label>
                                        <div class="col-sm-10">
                                            <input type="password" name="password" value="${sessionScope.password}" id="password" class="form-control  validate[required] text-input">
                                        </div>
                                    </div>
                                    <div id="titleP">
                                        <br>
                                        <h5>Restaurant details <br></h5>
                                        <hr>
                                    </div>
                                    <!-- 餐廳名稱 -->
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">餐廳名稱：</label>
                                        <div class="col-sm-10">
                                            <input type="text" name="StoreName" value="${StoreLoginOK['rest_name']}" id="StoreName" class="form-control" readonly>
                                        </div>
                                    </div>
                                    <!-- 餐廳類別 -->
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">餐廳類別：</label>
                                        <div class="col-sm-10">
                                            <input type="text" name="StoreType" value="${StoreLoginOK['rest_type']}" id="StoreType" class="form-control" readonly>
                                        </div>
                                    </div>
                                    <!-- 餐廳分店 -->
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">餐廳分店：</label>
                                        <div class="col-sm-10">
                                            <input type="text" name="branch" value="${StoreLoginOK['rest_branch']}" id="branch" class="form-control" readonly>
                                        </div>
                                    </div>
                                    <!-- 餐廳負責人 -->
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">店家負責人</label>
                                        <div class="col-sm-10">
                                            <input type="text" name="owner" value="${StoreLoginOK['rest_owner']}" id="owner" class="form-control" readonly>
                                        </div>
                                    </div>
                                    <!-- 餐廳地址 -->
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">餐廳地址：</label>
                                        <div class="col-sm-10">
                                            <input type="text" name="address" value="${StoreLoginOK['rest_address']}" id="address" class="form-control validate[required] text-input">
                                        </div>
                                    </div>

                                    <!-- 餐廳電話 -->
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">聯絡電話：</label>
                                        <div class="col-sm-10">
                                            <input type="text" name="tel" value="${StoreLoginOK['rest_phone']}" id="tel" class="form-control validate[required,custom[phone]] text-input">
                                        </div>
                                    </div>
                                    <!-- E-mail -->
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">E-mail：</label>
                                        <div class="col-sm-10">
                                            <input type="text" name="eMail" value="${StoreLoginOK['rest_email']}" id="eMail" class="form-control validate[required, custom[email]] text-input">
                                        </div>
                                    </div>
                                    <!-- 官方網站 -->
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">官方網站：</label>
                                        <div class="col-sm-10">
                                            <input type="text" name="url" value="${StoreLoginOK['rest_url']}" id="url" class="form-control">
                                        </div>
                                        <input type="text" name="langitude" style="display:none;" value="" id="langitude">
                                        <input type="text" name="latitude" style="display:none;" value="" id="latitude">
                                    </div>
                                    <!-- 照片檔案 -->
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">主頁橫幅圖：</label>
                                        <div class="col-sm-10">
                                            <input type="file" size="40" id="mainBanner" name="mainBanner" class="exampleInputFile">
                                            <BR>
                                            <span style="color:red; font-size:12px;">${MsgMap.errorPicture}</span>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">Logo 圖片：</label>
                                        <div class="col-sm-10">
                                            <input type="file" size="40" id="logo" name="logo" class="exampleInputFile">
                                            <BR>
                                            <span style="color:red; font-size:12px;">${MsgMap.errorPicture}</span>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">首頁圖片：</label>
                                        <div class="col-sm-10">
                                            <input type="file" size="40" iid="coverImg" name="coverImg" class="exampleInputFile">
                                            <BR>
                                            <span style="color:red; font-size:12px;">${MsgMap.errorPicture}</span>
                                        </div>
                                    </div>
                                    <!-- 按鈕 -->
                                    <div class="form-group">
                                        <!-- <div class="col-sm-offset-2 col-sm-10"> -->
                                        <div id="btnS">
                                            <input type="submit" name="submit" id="holyshit" value="save changes" class="btn btn-warning">
                                        </div>
                                        <div id="btnC">
                                            <input type="reset" name="cancel" id="cancel" value="cancel" class="btn btn-default">
                                        </div>
                                    </div>
                            </form>
                        </div>
                    </div>
                </div>
                <div class="row"></div>
            </div>
            </div>
            <script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js"></script>
            <script src="https://pingendo.com/assets/bootstrap/bootstrap-4.0.0-alpha.6.min.js"></script>
            <!--form check -->
            <link rel="stylesheet" href="../javascript/validation/validationEngine.jquery.css">
            <script type="text/javascript" charset="utf-8" src="../javascript/validation/languages/jquery.validationEngine-zh_TW.js"></script>
            <script type="text/javascript" charset="utf-8" src="../javascript/validation/jquery.validationEngine.js"></script>
            <!-- form check end-->
            <script src="../javascript/share.js"></script>
        </body>

        </html>
