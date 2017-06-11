XXAPP.controller('ApiOpenCtrl', ['$rootScope', '$scope', '$http','$sce', '$timeout',function($rootScope, $scope, $http,$sce, $timeout) {
    var md = window.markdownit();
    $http({method:"get",url:"/md/api/open/open.md?t="+new Date().getTime()}).then(function(data){
        var result = md.render(data.data);
        $scope.openApi= $sce.trustAsHtml(result);
    },function(error){
      console.log(error);
    });
}]);