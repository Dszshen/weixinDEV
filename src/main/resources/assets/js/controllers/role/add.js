XXAPP.controller('RoleAddController', ['$rootScope','$scope', '$http','$state', function ($rootScope,$scope, $http,$state) {
  $scope.editState = "add";
  if($state.current.name.indexOf('update')){
    $scope.editState = "update";
  }

  $scope.role = {
    "cn": "",
    "en": "",
    "state":true,
    "description": "",
    "roleGroup":"",
    "roleGroupDesc":""
  };

  $scope.isGroup = 'no';

  //获取角色分组信息
  /*$http({
    method:'get',
    url:'role/rolesGroupList?t='+new Date().getTime()
  }).then(function (resp, status, headers, config) {
    $scope.groupRolesList = resp.data.data;
  },function(resp, status, headers, config) {
  });*/

  //获取角色组信息
  $http({
    method: 'get',
    url: 'role/getRoleGroups?t=' + new Date().getTime(),
    params: {userId: 1}
  }).then(function (resp, status, headers, config) {
    $scope.roleGroups = resp.data.data;
    $scope.role.groupInfo = resp.data.data[0];
  }, function (resp, status, headers, config) {
  });


  //添加角色
  $scope.add = function(){
    var params = angular.copy($scope.role);
    params.state = $scope.role.state?1:0;
    delete params.groupInfo;
    if($scope.isGroup==="no"){
      params.roleGroup=$scope.role.groupInfo.groupId;
      params.roleGroupDesc=$scope.role.groupInfo.groupName;
    }


    console.log(params);

    $http({
      method:'post',
      url:'role/add',
      data:angular.toJson(params)
    }).success(function (resp) {
      console.log(resp);
    });
  };


}]);