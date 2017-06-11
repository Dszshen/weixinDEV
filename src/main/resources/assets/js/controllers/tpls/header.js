/* 页头 */
XXAPP.controller('HeaderController', ['$scope', function($scope) {
    $scope.$on('$includeContentLoaded', function() {
        Layout.initHeader(); // init header
        App.initAjax();
    });
}]);