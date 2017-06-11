XXAPP.controller('DatabaseApiController', function($scope) {
    var md = window.markdownit();
    $http.get(CONTEXT+"/assets/md/database/database.md?t="+currTime).success(function(data){
        var result = md.render(data);
        $scope.database= $sce.trustAsHtml(result);


    }).error(function(error){
        console.log(error);
    });

});