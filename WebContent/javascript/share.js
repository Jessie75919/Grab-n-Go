$(window).load(function () {
    var offset = 220;
    var duration = 500;
    var ww = window.innerWidth;

    /*gotop*/

    $(window).scroll(function () {
        if (jQuery(this).scrollTop() > offset) {
            $('.backToTop').fadeIn(duration);
        } else {
            $('.backToTop').fadeOut(duration);
        }
    });
    $('.backToTop').click(function (event) {
        event.preventDefault();
        $('html, body').animate({
            scrollTop: 0
        }, duration);
        return false;
    });

    /*menu*/
    var menuLeft = document.getElementById('cbp-spmenu-s1'),
        showLeftPush = document.getElementById('showLeftPush'),
        body = document.body;
    showLeftPush.onclick = function () {
        classie.toggle(this, 'active');
        classie.toggle(body, 'cbp-spmenu-push-toright');
        classie.toggle(menuLeft, 'cbp-spmenu-open');
        disableOther('showLeftPush');
    };

    function disableOther(button) {
        if (button !== 'showRightPush') {
            classie.toggle(showRightPush, 'disabled');
        }
    }

    /*搜尋*/
    $('.searchItem').click(function () {
        $('.search').fadeIn();
        return false;
    });
    $('.closeBtn, .searchBg').click(function () {
        $('.search').fadeOut();
        return false;
    });
    
    /*評價*/
    $('.order.rate a').click(function () {
    	$('.rating').fadeIn();
    	return false;
    });
    $('.closeBtn, .searchBg').click(function () {
    	$('.rating').fadeOut();
    	return false;
    });
    
    /*店家訂購內容*/
    $('.storeMenuList .mask, .storeMenuList .storeMenuName a').click(function () {
        $('.menuDetail').fadeIn();
        return false;
    });
    $('.closeBtn, .searchBg').click(function () {
        $('.menuDetail').fadeOut();
        return false;
    });
    
    /*animation*/
    new WOW().init();
})


$(document).ready(function () {
    /*form check*/
    $(".formcontent").validationEngine("attach", {
        promptPosition: "topLeft"
    });
    
});
