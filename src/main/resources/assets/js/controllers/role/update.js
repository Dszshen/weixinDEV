XXAPP.controller('RoleUpdateCtrl', ['$rootScope','$scope', '$http','$state','$uibModalInstance','Notification','roleInfo',
function ($rootScope,$scope, $http,$state,$uibModalInstance,Notification,roleInfo) {
  $scope.closeModal = function (data) {
    $uibModalInstance.close(data);
  };
  console.log(roleInfo);
  $scope.role = {
    id:roleInfo.id,
    cn: roleInfo.cn,
    en:roleInfo.en,
    state:roleInfo.state,
    description: roleInfo.description,
    groupInfo:{
      groupId:roleInfo.roleGroup,
      groupName:roleInfo.roleGroupDesc
    },
    roleGroup:roleInfo.roleGroup,
    roleGroupDesc:roleInfo.roleGroupDesc
  };

  $scope.isGroup = 'no';

  //获取角色组信息
  $http({
    method: 'get',
    url: 'role/getRoleGroups?t=' + new Date().getTime(),
    params: {userId: 1}
  }).then(function (resp, status, headers, config) {
    $scope.roleGroups = resp.data.data;
    angular.forEach($scope.roleGroups,function(item){
      if(item.groupId===$scope.role.groupInfo.groupId){
        $scope.role.groupInfo = item;
      }
    });
  }, function (resp, status, headers, config) {
  });


  //更新角色
  $scope.update = function(){
    var params = angular.copy($scope.role);
    params.state = $scope.role.state?1:0;
    delete params.groupInfo;
    if($scope.isGroup==="no"){
      params.roleGroup=$scope.role.groupInfo.groupId;
      params.roleGroupDesc=$scope.role.groupInfo.groupName;
    }

    //console.log(params);

    $http({
      method:'post',
      url:'role/update',
      data:angular.toJson(params)
    }).then(function (resp) {
      //console.log(resp);
      Notification.success({title: '角色更新', message: "角色更新成功", positionY: 'top', positionX: 'center'});
      $scope.closeModal(params);
    });
  };


}]);