/* 全局设置 */
XXAPP.factory('settings', ['$rootScope', function($rootScope) {
    // supported languages
    var settings = {
        layout: {
            pageSidebarClosed: false, // sidebar menu state
            pageContentWhite: true, // set page content layout
            pageBodySolid: false, // solid body color state
            pageAutoScrollOnLoad: 1000 // auto scroll to top on page load
        },
        assetsPath: CONTEXT+'/assets'
        //globalPath: '../assets/global',
        //layoutPath: '../assets/layouts/layout',
    };

    //当前时间
    //$rootScope.nowTime = new Date().getTime();
    $rootScope.settings = settings;

    return settings;
}]);