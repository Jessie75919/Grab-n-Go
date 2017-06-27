<%@page import="_03_Product.model.ProductType"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>店家資訊-Grab &amp; Go</title>
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
    <link href="../css_web/slick.css" rel="stylesheet" type="text/css">
    <jsp:useBean id="prodType" class="_03_Product.model.ProductTypeDAO" scope="session"></jsp:useBean>
    <c:set target="${prodType}" property="restNameA" value="${param.restName}"/>
    <jsp:useBean id="product" class="_03_Product.model.ProductDAO" scope="session"/>
<%--     <c:set target="${product}" property="" value="" /> --%>
</head>

<body id="top" class="cbp-spmenu-push">
     <jsp:include page="../_IncludeJsp/User_mainNav.jsp" />
     
    <div class="insideTitle">
        <figure>
        <img src="${pageContext.servletContext.contextPath}/_00_init/getImageA?id=${param.restUser}&type=restaurant&loc=main" alt="店家資訊" title="店家資訊">
        </figure>
        <!--店家資訊-->
        <div class="storeTitle">
            <div class="storeLogo"><img src="${pageContext.servletContext.contextPath}/_00_init/getImageA?id=${param.restUser}&type=restaurant&loc=logo" alt="${param.restName}" title="${param.restName}"></div>
            <div class="storeRight">
                <h3>${param.restName}<span>${param.restBranch}</span></h3>
                <div class="star"><i class="icon-star on"></i> <i class="icon-star on"></i> <i class="icon-star on"></i> <i class="icon-star"></i> <i class="icon-star"></i></div>
                <p><i class="icon-location"></i>${param.restAddr}</p>
                <p><i class="icon-phone"></i>${param.restPhone}</p>
                <div class="tagOrder">
                    <div class="tag"><span>${param.restType}</span></div>
                    <div class="orderCount">目前訂單<span>999</span></div>
                </div>
            </div>
        </div>
        <!--店家資訊 end-->
    </div>
    <main>
        <div class="brcame"><a href="../indexA.jsp">首頁</a> / ${param.restName}&nbsp;${param.restBranch}</div>
        <section class="content">
            <div class="tabs animated-fade">
                <div class="tabArea">
                    <ul class="tabLinks slider">
                   	<c:forEach var="foodType" items="${prodType.allProductType}" varStatus='vs'>
                		<li <c:if test="${vs.first}"> class ="active"</c:if>><a href="#tab${foodType}">${foodType}</a></li>
                   	</c:forEach>
                    </ul>
                </div>
                
                <div class="tab-content">
                    <div id="tab0" class="tab active">
                        <div class="storeMenuList">
                            <figure><img src="../images/productImageTest/af_menu_pic001.jpg" alt="香烤雞腿佐十品溫野菜" title="香烤雞腿佐十品溫野菜">
                                <div class="mask"><a href="#">我要訂餐</a></div>
                            </figure>
                            <div class="storeMenuName"><a href="#">香烤雞腿佐十品溫野菜</a> </div>
                            <div class="storeMenuDes">雞腿肉以香辛料醃漬一天入味後，烘烤為金黃多汁烤雞，搭配十種野菜，先煎炒再拌入特製巴薩米克酒醋醬，溫熱酸香滋味引誘夏季食慾。</div>
                            <div class="storeMenuPrice">$360</div>
                        </div>
                        
                        
                        
                        <div class="storeMenuList">
                            <figure><img src="../images/productImageTest/af_menu_pic001.jpg" alt="香烤雞腿佐十品溫野菜" title="香烤雞腿佐十品溫野菜">
                                <div class="mask"><a href="#">我要訂餐</a></div>
                            </figure>
                            <div class="storeMenuName"><a href="#">香烤雞腿佐十品溫野菜</a> </div>
                            <div class="storeMenuDes">雞腿肉以香辛料醃漬一天入味後，烘烤為金黃多汁烤雞，搭配十種野菜，先煎炒再拌入特製巴薩米克酒醋醬，溫熱酸香滋味引誘夏季食慾。</div>
                            <div class="storeMenuPrice">$360</div>
                        </div>
                        <div class="storeMenuList">
                            <figure><img src="../images/productImageTest/af_menu_pic001.jpg" alt="香烤雞腿佐十品溫野菜" title="香烤雞腿佐十品溫野菜">
                                <div class="mask"><a href="#">我要訂餐</a></div>
                            </figure>
                            <div class="storeMenuName"><a href="#">香烤雞腿佐十品溫野菜</a> </div>
                            <div class="storeMenuDes">雞腿肉以香辛料醃漬一天入味後，烘烤為金黃多汁烤雞，搭配十種野菜，先煎炒再拌入特製巴薩米克酒醋醬，溫熱酸香滋味引誘夏季食慾。</div>
                            <div class="storeMenuPrice">$360</div>
                        </div>
                        <div class="storeMenuList">
                            <figure><img src="../images/productImageTest/af_menu_pic001.jpg" alt="香烤雞腿佐十品溫野菜" title="香烤雞腿佐十品溫野菜">
                                <div class="mask"><a href="#">我要訂餐</a></div>
                            </figure>
                            <div class="storeMenuName"><a href="#">香烤雞腿佐十品溫野菜</a> </div>
                            <div class="storeMenuDes">雞腿肉以香辛料醃漬一天入味後，烘烤為金黃多汁烤雞，搭配十種野菜，先煎炒再拌入特製巴薩米克酒醋醬，溫熱酸香滋味引誘夏季食慾。</div>
                            <div class="storeMenuPrice">$360</div>
                        </div>
                        <div class="storeMenuList">
                            <figure><img src="../images/productImageTest/af_menu_pic001.jpg" alt="香烤雞腿佐十品溫野菜" title="香烤雞腿佐十品溫野菜">
                                <div class="mask"><a href="#">我要訂餐</a></div>
                            </figure>
                            <div class="storeMenuName"><a href="#">香烤雞腿佐十品溫野菜</a> </div>
                            <div class="storeMenuDes">雞腿肉以香辛料醃漬一天入味後，烘烤為金黃多汁烤雞，搭配十種野菜，先煎炒再拌入特製巴薩米克酒醋醬，溫熱酸香滋味引誘夏季食慾。</div>
                            <div class="storeMenuPrice">$360</div>
                        </div>


                    </div>
                    <div id="tab1" class="tab">
                        <div class="storeMenuList">
                            <figure><img src="../images/productImageTest/af_menu_pic002.jpg" alt="香烤雞腿佐十品溫野菜" title="香烤雞腿佐十品溫野菜">
                                <div class="mask"><a href="#">我要訂餐</a></div>
                            </figure>
                            <div class="storeMenuName"><a href="#">香烤雞腿佐十品溫野菜</a> </div>
                            <div class="storeMenuDes">雞腿肉以香辛料醃漬一天入味後，烘烤為金黃多汁烤雞，搭配十種野菜，先煎炒再拌入特製巴薩米克酒醋醬，溫熱酸香滋味引誘夏季食慾。</div>
                            <div class="storeMenuPrice">$360</div>
                        </div>
                        <div class="storeMenuList">
                            <figure><img src="../images/productImageTest/af_menu_pic002.jpg" alt="香烤雞腿佐十品溫野菜" title="香烤雞腿佐十品溫野菜">
                                <div class="mask"><a href="#">我要訂餐</a></div>
                            </figure>
                            <div class="storeMenuName"><a href="#">香烤雞腿佐十品溫野菜</a> </div>
                            <div class="storeMenuDes">雞腿肉以香辛料醃漬一天入味後，烘烤為金黃多汁烤雞，搭配十種野菜，先煎炒再拌入特製巴薩米克酒醋醬，溫熱酸香滋味引誘夏季食慾。</div>
                            <div class="storeMenuPrice">$360</div>
                        </div>
                        <div class="storeMenuList">
                            <figure><img src="../images/productImageTest/af_menu_pic002.jpg" alt="香烤雞腿佐十品溫野菜" title="香烤雞腿佐十品溫野菜">
                                <div class="mask"><a href="#">我要訂餐</a></div>
                            </figure>
                            <div class="storeMenuName"><a href="#">香烤雞腿佐十品溫野菜</a> </div>
                            <div class="storeMenuDes">雞腿肉以香辛料醃漬一天入味後，烘烤為金黃多汁烤雞，搭配十種野菜，先煎炒再拌入特製巴薩米克酒醋醬，溫熱酸香滋味引誘夏季食慾。</div>
                            <div class="storeMenuPrice">$360</div>
                        </div>
                    </div>
                    <div id="tab2" class="tab">
                        <div class="storeMenuList">
                            <figure><img src="../images/productImageTest/af_menu_pic003.jpg" alt="香烤雞腿佐十品溫野菜" title="香烤雞腿佐十品溫野菜">
                                <div class="mask"><a href="#">我要訂餐</a></div>
                            </figure>
                            <div class="storeMenuName"><a href="#">香烤雞腿佐十品溫野菜</a> </div>
                            <div class="storeMenuDes">雞腿肉以香辛料醃漬一天入味後，烘烤為金黃多汁烤雞，搭配十種野菜，先煎炒再拌入特製巴薩米克酒醋醬，溫熱酸香滋味引誘夏季食慾。</div>
                            <div class="storeMenuPrice">$360</div>
                        </div>
                        <div class="storeMenuList">
                            <figure><img src="../images/productImageTest/af_menu_pic003.jpg" alt="香烤雞腿佐十品溫野菜" title="香烤雞腿佐十品溫野菜">
                                <div class="mask"><a href="#">我要訂餐</a></div>
                            </figure>
                            <div class="storeMenuName"><a href="#">香烤雞腿佐十品溫野菜</a> </div>
                            <div class="storeMenuDes">雞腿肉以香辛料醃漬一天入味後，烘烤為金黃多汁烤雞，搭配十種野菜，先煎炒再拌入特製巴薩米克酒醋醬，溫熱酸香滋味引誘夏季食慾。</div>
                            <div class="storeMenuPrice">$360</div>
                        </div>
                        <div class="storeMenuList">
                            <figure><img src="../images/productImageTest/af_menu_pic003.jpg" alt="香烤雞腿佐十品溫野菜" title="香烤雞腿佐十品溫野菜">
                                <div class="mask"><a href="#">我要訂餐</a></div>
                            </figure>
                            <div class="storeMenuName"><a href="#">香烤雞腿佐十品溫野菜</a> </div>
                            <div class="storeMenuDes">雞腿肉以香辛料醃漬一天入味後，烘烤為金黃多汁烤雞，搭配十種野菜，先煎炒再拌入特製巴薩米克酒醋醬，溫熱酸香滋味引誘夏季食慾。</div>
                            <div class="storeMenuPrice">$360</div>
                        </div>
                    </div>
                    <div id="tab3" class="tab">
                        <div class="storeMenuList">
                            <figure><img src="../images/productImageTest/af_menu_pic002.jpg" alt="香烤雞腿佐十品溫野菜" title="香烤雞腿佐十品溫野菜">
                                <div class="mask"><a href="#">我要訂餐</a></div>
                            </figure>
                            <div class="storeMenuName"><a href="#">香烤雞腿佐十品溫野菜</a> </div>
                            <div class="storeMenuDes">雞腿肉以香辛料醃漬一天入味後，烘烤為金黃多汁烤雞，搭配十種野菜，先煎炒再拌入特製巴薩米克酒醋醬，溫熱酸香滋味引誘夏季食慾。</div>
                            <div class="storeMenuPrice">$360</div>
                        </div>
                        <div class="storeMenuList">
                            <figure><img src="../images/productImageTest/af_menu_pic002.jpg" alt="香烤雞腿佐十品溫野菜" title="香烤雞腿佐十品溫野菜">
                                <div class="mask"><a href="#">我要訂餐</a></div>
                            </figure>
                            <div class="storeMenuName"><a href="#">香烤雞腿佐十品溫野菜</a> </div>
                            <div class="storeMenuDes">雞腿肉以香辛料醃漬一天入味後，烘烤為金黃多汁烤雞，搭配十種野菜，先煎炒再拌入特製巴薩米克酒醋醬，溫熱酸香滋味引誘夏季食慾。</div>
                            <div class="storeMenuPrice">$360</div>
                        </div>
                        <div class="storeMenuList">
                            <figure><img src="../images/productImageTest/af_menu_pic002.jpg" alt="香烤雞腿佐十品溫野菜" title="香烤雞腿佐十品溫野菜">
                                <div class="mask"><a href="#">我要訂餐</a></div>
                            </figure>
                            <div class="storeMenuName"><a href="#">香烤雞腿佐十品溫野菜</a> </div>
                            <div class="storeMenuDes">雞腿肉以香辛料醃漬一天入味後，烘烤為金黃多汁烤雞，搭配十種野菜，先煎炒再拌入特製巴薩米克酒醋醬，溫熱酸香滋味引誘夏季食慾。</div>
                            <div class="storeMenuPrice">$360</div>
                        </div>
                    </div>

                </div>

            </div>

        </section>
    </main>
    <!--店家訂購內容-->
    <div class="menuDetail">
        <div class="searchBg"></div>
        <section class="searchContent">
            <div class="closeBtn"><i class="icon-close" title="關閉"></i></div>
            <form action="#" method="post">
                <figure><img src="../images/productImageTest/af_menu_pic001.jpg" alt="香烤松阪豬青醬燉飯" title="香烤松阪豬青醬燉飯"></figure>
                <div class="menuInfo">
                    <h4>香烤松阪豬青醬燉飯</h4>
                    <p class="wRed">$360</p>
                    <p>松阪豬以特調醬汁醃漬入味，芫荽等香料及蜂蜜風味提點，香烤後 的獨特脆口感，搭配濃郁蘿勒風味燉飯，佐彩椒及蕃茄，點綴莫札 瑞拉起士清爽享用。
                    </p>
                    <p><textarea placeholder="請輸入特殊需求，例如: 大辣*1，不辣*3" rows="3" name="else" id="else"></textarea></p>
                    <div class="menuCount">數量<input type="number" name="count" id="count" value="1" min="1"></div>
                    <div class="addBtn"><input name="submit" type="submit" id="submit" value="加入購物車"></div>
                </div>
            </form>
        </section>
    </div>
    <!--店家訂購內容 end-->
    <!--搜尋-->
    <div class="search">
        <div class="searchBg"></div>
        <section class="searchContent">
            <h2>Hello! 今天想吃什麼呢?</h2>
            <div class="closeBtn"><i class="icon-close" title="關閉"></i></div>
            <form action="#" method="post">
                <div class="searchList">
                    <select name="foodKind" id="foodKind">
                           <option value="日式料理">日式料理</option>
                           <option value="中式料理">中式料理</option>
                           <option value="義式料理">義式料理</option>
                           <option value="早午餐/下午茶">早午餐/下午茶</option>
                           <option value="暗黑料理">暗黑料理</option>
                           <option value="台灣小吃">台灣小吃</option>
                       </select>
                </div>
                <div class="searchList">
                    <input type="text" name="storeName" id="storeName" placeholder="請輸入店家名稱">
                </div>
                <div class="searchList">
                    <input type="text" name="foodName" id="foodName" placeholder="請輸入料理名稱">
                </div>
                <div class="searchBtn">
                    <input name="reset" type="reset" id="reset" value="重填">
                    <input name="submit" type="submit" id="submit" value="搜尋">
                </div>
            </form>
        </section>
    </div>
    <!--搜尋 end-->
    <footer>
        <figure><img src="../images/share/logo.svg" alt="Grab &amp; Go" title="Grab &amp; Go"></figure>
        <p>Copyright © Garb and Go All rights reserved.</p><a href="#" class="backToTop">TOP</a></footer>
    <!--main js-->
    <!--[if lt IE 8]><script type="text/javascript" src="javascript/html5.js"></script><![endif]-->
    <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
    <!--nav-->
    <script type="text/javascript" src="../javascript/classie.js"></script>
    <!--silder-->
    <script src="../javascript/slick.js"></script>
    <script>
        $(function() {
            $(".tabLinks").slick({
                dots: false,
                infinite: false,
                slidesToShow: 10,
                slidesToScroll: 1,
                responsive: [{
                    breakpoint: 1680,
                    settings: {
                        slidesToShow: 8,
                    }
                }, {
                    breakpoint: 1280,
                    settings: {
                        slidesToShow: 6,
                    }
                }, {
                    breakpoint: 900,
                    settings: {
                        slidesToShow: 4,
                    }
                }, {
                    breakpoint: 600,
                    settings: {
                        slidesToShow: 3,
                    }
                }, {
                    breakpoint: 300,
                    settings: {
                        slidesToShow: 2,
                    }
                }]
            });
        });
    </script>
    <!--tabs-->
    <script src="../javascript/tabs.js"></script>
    <!--share js-->
    <script src="../javascript/share.js"></script>
    <script src="../js/readProduct.js"></script>
</body>

</html>