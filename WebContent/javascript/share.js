$(window).load(function () {
    var offset = 220;
    var duration = 500;
    var ww = window.innerWidth;
    /*header滾動*/
    if (ww > 1000) {
        $(window).scroll(function () {
            if ($(this).scrollTop() > 400) {
                $('.headBg').fadeIn(500);
            } else {
                $('.headBg').fadeOut(300);
            }
        });
    }

    /*gotop*/

    $(window).scroll(function () {
        if (jQuery(this).scrollTop() > offset) {
            $('.back-to-top').fadeIn(duration);
        } else {
            $('.back-to-top').fadeOut(duration);
        }
    });
    $('.back-to-top').click(function (event) {
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


    /*animation*/

    new WOW().init();

    /*nice select*/

})
