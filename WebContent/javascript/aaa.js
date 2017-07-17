(function ($) {
      $.fn.haha = function (settings) {
            var defaultSettings = {
                  bind: 'mouseover',
                  callback: function () {
                        $(this).animate({
                              opacity: 0.25,
                              left: '+=50',
                              height: 'toggle'
                        }, 3000, function () {
                              $("span").html('').append($(this).html() + '完成!').show().fadeOut(1000);
                        });
                  }
            };
            var _settings = $.extend(defaultSettings, settings);
            return this.each(function () {
                  $(this).bind(_settings.bind, _settings.callback);
            });
      }
})(jQuery);
