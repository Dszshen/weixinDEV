XXAPP.controller('PermissionListController', function($rootScope, $scope, settings) {
    $scope.$on('$viewContentLoaded', function() {
        // initialize core components
        App.initAjax();
    });
});