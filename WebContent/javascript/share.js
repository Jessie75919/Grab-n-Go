$(window).load(function () {
    var offset = 220;
    var duration = 500;
    var ww = window.innerWidth;

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

    /*搜尋*/
    $(document).ready(function () {
        $('.searchItem').click(function () {
            $('.search').fadeIn();
            return false;
        });
        $('.closeBtn, .searchBg').click(function () {
            $('.search').fadeOut();
            return false;
        });


    });

})
