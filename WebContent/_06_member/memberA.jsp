<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
        <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
        <html>

        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>檢視個人資料-Grab &amp; Go</title>
            <meta name="keywords" content="Grab &amp; Go, 訂餐, 帶著就走, 上班族" />
            <meta name="description" content="短短的午休時間您受夠了在水深火熱中跟人家相爭排隊買午餐嗎? Grab &amp; Go 預約訂餐系統讓您輕鬆帶著走。" />
            <meta name="author" content="Grab &amp; Go">
            <meta name="copyright" content="Grab &amp; Go">
            <meta name="robots" content="index, follow">
            <meta name="GOOGLEBOT" content="index, follow">
            <meta name="distribution" content="GLOBAL">
            <meta property="og:title" content="Grab &amp; Go " />
            <meta property="og:description" content="短短的午休時間您受夠了在水深火熱中跟人家相爭排隊買午餐嗎? Grab &amp; Go 預約訂餐系統讓您輕鬆帶著走。" />
            <meta property="og:site_name" content="Grab &amp; Go" />
            <meta property="og:type" content="website" />
            <meta property="og:url" content="http://www.chewchew.com.tw" />
            <meta property="og:image" content="http://lovegreenfood.com/gg/fb.jpg" />
            <link rel="image_src" href="http://lovegreenfood.com/gg/fb.jpg" />
            <link rel="SHORTCUT ICON" href="../images/favicon.ico" />
            <link rel="icon" href="../images/favicon.ico" type="image/ico" />
            <!--main css-->
            <link href="../css_web/default.css" rel="stylesheet" type="text/css" />
            <link href="https://file.myfontastic.com/JgbKu4HBhSiTuUxrtB7R5d/icons.css" rel="stylesheet">
            <link href="../css_web/component.css" rel="stylesheet" type="text/css" />
        </head>

        <body id="top" class="cbp-spmenu-push">

            <!--   主選單 & 旁邊抽屜 -->
            <jsp:include page="../_IncludeJsp/User_mainNav.jsp" />


            <div class="insideTitle">
                <h2>檢視個人資料</h2>
            </div>
            <main>
                <div class="brcame"><a href="../index.jsp">首頁</a> / 檢視個人資料</div>
                <section class="content">
                    <div class="memberAccount">
                        <div class="memberLeft">
                            <figure>
                                <img src='${pageContext.servletContext.contextPath}/_00_init/getImageA?id=${LoginOK.memberId}&type=MEMBER' alt="Photo" title="Photo">
                            </figure>
                        </div>
                        <div class="memberRight">
                            <div class="memberInfo">
                                <h3>${LoginOK.memberId}</h3>
<!--                                 <div class="star"><i class="icon-star on"></i> <i class="icon-star on"></i> <i class="icon-star on"></i> <i class="icon-star"></i> <i class="icon-star"></i></div> -->
                            </div>
                            <div class="formList">
                                <div class="formTitle"><i class="icon-user"></i>姓名</div>
                                <div class="formInfo">${LoginOK.name}</div>
                            </div>
                            <div class="formList">
                                <div class="formTitle"><i class="icon-location"></i>地址</div>
                                <div class="formInfo">${LoginOK.address}</div>
                            </div>
                            <div class="formList">
                                <div class="formTitle"><i class="icon-phone"></i>電話</div>
                                <div class="formInfo">${LoginOK.phone}</div>
                            </div>
                            <div class="formList">
                                <div class="formTitle"><i class="icon-mail"></i>E-mail</div>
                                <div class="formInfo">${LoginOK.email}</div>
                            </div>
                            <div class="formList">
                                <div class="formTitle"><i class="icon-birth"></i>生日</div>
                                <div class="formInfo">${LoginOK.birthday}</div>
                            </div>
                            <div class="formList">
                                <div class="formTitle"><i class="icon-check"></i>認證狀態</div>
                                <c:if test="${LoginOK.m_validate==0}">
                                    <div class="formInfo">未認證
                                        <a id="validBtn" href="../sendValidMail.do?mode=1&user=${LoginOK.memberId}">點我認證</a>
                                    </div>
                                </c:if>
                                <c:if test="${LoginOK.m_validate==1}">
                                    <div class="formInfo">已認證</div>
                                </c:if>
                            </div>
                        </div>
                    </div>
                </section>
            </main>
            <!--搜尋-->
            <jsp:include page="../_IncludeJsp/User_search.jsp" />
            <!--訊息-->
            <jsp:include page="../_IncludeJsp/User_NotificationPanel.jsp" />

            <!--loading-->
            <div id="ttt" class="loading">
                <div class="loadDotted"></div>
                <div class="loadDotted"></div>
                <div class="loadDotted"></div>
            </div>
            <!--loading end-->

            <footer>
                <figure><img src="../images/share/logo.svg" alt="Grab &amp; Go" title="Grab &amp; Go"></figure>
                <p>Copyright © Garb and Go All rights reserved.</p><a href="#" class="backToTop">TOP</a></footer>
            <!--main js-->
            <!--[if lt IE 8]><script type="text/javascript" src="javascript/html5.js"></script><![endif]-->
            <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
            <!--nav-->
            <script type="text/javascript" src="../javascript/classie.js"></script>
            <!--share js-->
            <script src="../javascript/share.js"></script>
            <script src="../js/indexUse.js"></script>
        </body>

        </html>
