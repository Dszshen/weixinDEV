var Script = function () {

//    tool tips

    $('.tooltips').tooltip();

//    popovers

    $('.popovers').popover();

//    bxslider

    $('.bxslider').show();
    $('.bxslider').bxSlider({
        minSlides: 4,
        maxSlides: 4,
        slideWidth: 276,
        slideMargin: 20
    });

    var initNav = function(){
        var currUrlPath = window.location.pathname;
        var setNavActive = function(currPath){
            $(".nav.navbar-nav li>a").each(function(item){
                var currHref = $(this).attr("href");
                //console.info($(this)[0],currHref);
                //console.log($(this).parent());
                $(this).parent().removeClass("active");
                if(currPath==currHref){
                    $(this).parent().addClass("active");
                }
            });
        };
        setNavActive(currUrlPath);
    };

    initNav();
}();