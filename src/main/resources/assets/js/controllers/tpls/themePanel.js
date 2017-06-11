/* 主题样式 */
XXAPP.controller('ThemePanelController', ['$scope', function($scope) {
    $scope.$on('$includeContentLoaded', function() {
        Theme.init(); // init theme panel
    });
}]);