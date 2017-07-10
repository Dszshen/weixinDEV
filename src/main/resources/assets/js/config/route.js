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

  state('index', '/index', 'IndexController', '/pages/index.html', false, {pageTitle: '欢迎页'}, {
    deps: ['$ocLazyLoad', function ($ocLazyLoad) {
      return $ocLazyLoad.load({
        name: 'XXAPP',
        insertBefore: '#ng_load_plugins_before', // load the above css files before a LINK element with this ID. Dynamic CSS files must be loaded between core and theme css files
        files: [
          CONTEXT + '/assets/libs/morrisjs/morris.css',
          CONTEXT + '/assets/libs/morrisjs/morris.min.js',
          CONTEXT + '/assets/libs/raphael/raphael-min.js',
          CONTEXT + '/assets/libs/jquery-sparkline/dist/jquery.sparkline.min.js',
          CONTEXT + '/assets/scripts/pages/dashboard.min.js'
        ]
      });
    }]
  });

  //----------------------------------------------系统管理  start----------------------------------------
  //系统管理-->>>系统参数设置
  state('sys', '/system', 'SystemController', '<ui-view></ui-view>', true);
  state('sys.setting', '/setting', 'SystemSettingController', '/pages/system/setting.html', false, {pageTitle: '系统参数设置'});
  state('sys.info', '/info', 'SystemInfoController', '/pages/system/info.html', false, {pageTitle: 'XX公司信息'});

  //系统管理-->>>用户管理
  state('user', '/user', 'UserController', '<ui-view></ui-view>', true);
  state('user.list', '/list', 'UserListController', '/pages/system/manager/list.html', false, {pageTitle: '用户列表'}, {
    deps: ['$ocLazyLoad', function ($ocLazyLoad) {
      return $ocLazyLoad.load([{
        name: 'userListCss',
        insertBefore: '#css_lazyload',
        files: [
          '/assets/libs/DataTables/datatables.min.css',
          '/assets/libs/DataTables/plugins/bootstrap/datatables.bootstrap.css'
        ]
      }, {
        name: 'userListJs',
        insertBefore: '#js_lazyload',
        files: [
          '/assets/libs/DataTables/datatables.all.min.js',
          "/assets/libs/jstree/dist/jstree.min.js",
          '/assets/scripts/table-datatables-managed.js'
        ]
      }]);
    }]
  });


  state('user.add', '/add', 'UserAddController', '/pages/system/manager/add.html', false, {pageTitle: '增加用户'});
  state('user.detail', '/detail', 'UserDetailController', '/pages/system/manager/detail.html', false, {pageTitle: '用户资料'});

  /*用户中心*/
  state('user.profile', '/profile', 'UserProfileController', '/pages/user/personal/profile.html', false, {pageTitle: '个人信息'});

  //系统管理-->>>角色管理
  state('role', '/role', 'RoleController', '<ui-view></ui-view>', true);
  state('role.list', '/list', 'RoleListController', '/pages/role/list.html', false, {pageTitle: '角色列表'}, {
    deps: ['$ocLazyLoad', function ($ocLazyLoad) {
      return $ocLazyLoad.load([{
        name: 'roleList',
        insertBefore: '#ng_load_plugins_before',
        files: [
          CONTEXT + '/assets/libs/angular-ui-switch/angular-ui-switch.css',
          CONTEXT + '/assets/libs/DataTables/datatables.min.css',
          CONTEXT + '/assets/libs/DataTables/plugins/bootstrap/datatables.bootstrap.css',
          CONTEXT + '/assets/libs/DataTables/datatables.all.min.js',
          //CONTEXT+'/assets/libs/angular-bootstrap-switch/dist/angular-bootstrap-switch.min.js',
          CONTEXT + '/assets/scripts/table-datatables-managed.js'
        ]
      }]);
    }]
  });
  state('role.add', '/add', 'RoleAddController', '/pages/role/add.html', false, {pageTitle: '增加角色'},{
    deps: ['$ocLazyLoad', function ($ocLazyLoad) {
      return $ocLazyLoad.load([{
        name: 'addRole',
        insertBefore: '#ng_load_plugins_before',
        files: [
          CONTEXT + '/assets/libs/angular-ui-switch/angular-ui-switch.css'
        ]
      }]);
    }]
  });
  state('role.detail', '/detail', 'RoleDetailController', '/pages/role/detail.html', false, {pageTitle: '角色详情'});


  //系统管理-->>>权限管理
  state('permission', '/permission', 'PermissionController', '<ui-view></ui-view>', true);
  state('permission.list', '/list', 'PermissionListController', '/pages/permission/list.html', false, {pageTitle: '权限列表'}, {
    deps: ['$ocLazyLoad', function ($ocLazyLoad) {
      return $ocLazyLoad.load([{
        name: 'permissionManage',
        insertBefore: '#ng_load_plugins_before',
        files: [
          CONTEXT + '/assets/libs/DataTables/datatables.min.css',
          CONTEXT + '/assets/libs/DataTables/plugins/bootstrap/datatables.bootstrap.css',
          CONTEXT + '/assets/libs/DataTables/datatables.all.min.js',
          CONTEXT + '/assets/scripts/table-datatables-managed.js'
        ]
      }]);
    }]
  });
  state('permission.add', '/add', 'PermissionAddController', '/pages/permission/add.html', false, {pageTitle: '增加权限'});


  //----------------------------------------------房产信息管理  start--------------------------------------------
  state('house', '/house', 'HouseController', '<ui-view></ui-view>', true);
  state('house.add', '/add', 'HouseAddController', '/pages/house/add.html', false, {pageTitle: '新增房产信息'});
  state('house.list', '/list', 'HouseListController', '/pages/house/list.html', false, {pageTitle: '房产信息'}, {
    deps: ['$ocLazyLoad', function ($ocLazyLoad) {
      return $ocLazyLoad.load([{
        name: 'houseList',
        insertBefore: '#ng_load_plugins_before',
        files: [
          CONTEXT + '/assets/libs/DataTables/datatables.min.css',
          CONTEXT + '/assets/libs/DataTables/plugins/bootstrap/datatables.bootstrap.css',
          CONTEXT + '/assets/libs/DataTables/datatables.all.min.js',
          CONTEXT + '/assets/scripts/table-datatables-managed.js'
        ]
      }]);
    }]
  });
  //产权变更
  state('house.changeTitle', '/changeTitle', 'HouseChangeTitleController', '/pages/house/changeTitle.html', false, {pageTitle: '房屋产权变更'});
  state('house.drawing', '/drawing', 'HouseDrawingController', '/pages/house/drawing.html', false, {pageTitle: '房间状态图示'});



}]);