XXAPP.controller('ApiDevCtrl', ['$rootScope', '$scope', '$http','$sce', '$timeout',function($rootScope, $scope, $http,$sce, $timeout) {
    var markDown = window.markdownit();
    $http({method:"get",url:"/md/api/dev/dev.md?t="+new Date().getTime()}).then(function(data){
        var result = markDown.render(data.data);
        $scope.devApi= $sce.trustAsHtml(result);
    },function(error){
      console.log(error);
    });

  $scope.$on('$viewContentLoaded', function() {
    $('#menu').metisMenu();
  });

}]);