/* 所有页面的路由跳转 */
XXAPP.config(['$stateProvider', '$urlRouterProvider', 'NotificationProvider', '$locationProvider', function ($stateProvider, $urlRouterProvider, NotificationProvider, $locationProvider) {
  $locationProvider.hashPrefix('');
  //通知样式配置
  NotificationProvider.setOptions({
    delay: 3000,
    startTop: 33,
    startRight: 10,
    verticalSpacing: 20,
    horizontalSpacing: 20,
    positionX: 'center',
    positionY: 'top'
    //templateUrl: "portal-notification.html"
  });

  // 如果没有匹配到url请求就跳转到欢迎页
  $urlRouterProvider.otherwise("/index");

  //封装$stateProvider.state方法
  var state = function (name, url, ctrl, tmpl, abs, data, resolve) {
    var state = {};

    if (url) {
      if (abs) {
        state.url = url;
      } else {
        state.url = url;
      }
    }
    if (ctrl) {
      state.controller = ctrl;
    }
    if (tmpl) {
      if (tmpl.indexOf('/') === 0) {
        state.templateUrl = tmpl;
      } else {
        state.template = tmpl;
      }
    }
    if (abs) {
      state.abstract = true;
    }
    if (data) {
      state.data = data;
    }
    if (resolve) {
      state.resolve = resolve;
    }
    $stateProvider.state(name, state);
  };

  var lazyLoadFiles = function ($ocLazyLoad, name, css, cssFiles, jsId, jsFiles) {
    return {};
  };

  state('index', '/index', 'IndexController', '/pages/index.html', false, {pageTitle: '欢迎页'});

  //----------------------------------------------系统管理  start----------------------------------------
  //系统管理-->>>系统参数设置
  state('sys', '/system', 'SystemController', '<ui-view></ui-view>', true);
  state('sys.setting', '/setting', 'SystemSettingController', '/pages/system/setting.html', false, {pageTitle: '系统参数设置'});

  /*用户中心*/
  state('user.profile', '/profile', 'UserProfileController', '/pages/user/personal/profile.html', false, {pageTitle: '个人信息'});

  state('weixin', '/weixin', '', '<ui-view></ui-view>', true);
  state('weixin.menu', '/menu', 'weixinMenuCtrl', '/pages/weixin/menu.html', false, {pageTitle: '微信菜单管理'});
  state('weixin.message', '/message', 'weixinMessageCtrl', '/pages/weixin/message.html', false, {pageTitle: '微信消息管理'});
  state('weixin.image', '/image', 'weixinImageCtrl', '/pages/weixin/image.html', false, {pageTitle: '微信图片管理'});

}]);