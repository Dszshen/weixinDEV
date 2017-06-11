/* 快捷菜单 */
XXAPP.controller('QuickSidebarController', ['$scope','$timeout', function($scope,$timeout) {
    $scope.$on('$includeContentLoaded', function() {
        $timeout(function(){
            QuickSidebar.init(); // init quick sidebar
        }, 2000);
    });
}]);