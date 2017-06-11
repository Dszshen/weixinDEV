XXAPP.controller('RolePermissionAddCtrl',['$rootScope', '$scope','$state','$uibModal','$uibModalInstance', function($rootScope, $scope,$state,$uibModal,$uibModalInstance) {
    $scope.cancel = function(){
        $uibModalInstance.dismiss();
    };
}]);