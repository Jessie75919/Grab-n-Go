jQuery(document).ready(function () {
    // Standard
    jQuery('.tabs.standard .tabArea a').on('click', function (e) {
        var currentAttrValue = jQuery(this).attr('href');

        // Show/Hide Tabs
        jQuery('.tabs ' + currentAttrValue).show().siblings().hide();

        // Change/remove current tab to active
        jQuery(this).parent('li').addClass('active').siblings().removeClass('active');

        e.preventDefault();
        return false;
    });

    // Animated Fade
    jQuery('.tabs.animated-fade .tabArea a').on('click', function (e) {
        var currentAttrValue = jQuery(this).attr('href');

        // Show/Hide Tabs
        jQuery('.tabs ' + currentAttrValue).fadeIn(400).siblings().hide();

        // Change/remove current tab to active
        jQuery(this).parent('li').addClass('active').siblings().removeClass('active');

        e.preventDefault();
        return false;
    });

    // Animated Slide 1
    jQuery('.tabs.animated-slide-1 .tabArea a').on('click', function (e) {
        var currentAttrValue = jQuery(this).attr('href');

        // Show/Hide Tabs
        jQuery('.tabs ' + currentAttrValue).siblings().slideUp(400);
        jQuery('.tabs ' + currentAttrValue).delay(400).slideDown(400);

        // Change/remove current tab to active
        jQuery(this).parent('li').addClass('active').siblings().removeClass('active');

        e.preventDefault();
        return false;
    });

    // Animated Slide 2
    jQuery('.tabs.animated-slide-2 .tabArea a').on('click', function (e) {
        var currentAttrValue = jQuery(this).attr('href');

        // Show/Hide Tabs
        jQuery('.tabs ' + currentAttrValue).slideDown(400).siblings().slideUp(400);

        // Change/remove current tab to active
        jQuery(this).parent('li').addClass('active').siblings().removeClass('active');

        e.preventDefault();
        return false;
    });
});