XXAPP.controller('SystemSettingController',['$scope','$http','Notification',function($scope,$http,Notification) {

    //公司config
    $scope.companyInfo = {
        id:"e1eae6d3-6fff-42e7-a793-051aff817d9a",
        name:"",
        addr:"",
        employees:"",
        main_business:"",
        legal_person:"",
        tel:"",
        mobile_number:"",
        create_date:"",
        description:""
    };

    //安全config
    $scope.securityInfo = {
        id:"d8d5a656-2410-11e7-93ae-92361f002671",
        white_list:"",
        forbid_list:"",
        max_conn:""
    };

    $scope.emailInfo={
        id:"",
        model:"",
        smtp_server:"",
        port:"",
        account:"",
        password:"",
        receive_addr:""
    };

    $scope.config = {
        company:{
            add:function(){
              var params = $scope.companyInfo;
              $http({
                method:'post',
                url:'sys/base',
                data:angular.toJson($scope.companyInfo)
              }).then(function (resp) {
                console.log(resp);
                if(resp.flag="success") {
                  Notification.success({title: '新增公司', message: "新增公司信息成功", positionY: 'top', positionX: 'center'});
                }
              });
            },
            get:function(){
                $http({
                    method:'get',
                    url:'sys/base',
                    params:{id:$scope.companyInfo.id}
                }).then(function (resp) {
                    console.log(resp);
                    if(resp.status==200&&resp.data.flag=="success"){
                        $scope.companyInfo = resp.data.data[0];
                    }
                });
            },
            update:function(){
                var params = $scope.companyInfo;
                $http({
                    method:'PUT',
                    url:'sys/base',
                    data:angular.toJson($scope.companyInfo)
                }).then(function (resp) {
                    console.log(resp);
                    if(resp.flag="success") {
                        Notification.success({title: '更新公司信息', message: "更新公司信息成功", positionY: 'top', positionX: 'center'});
                    }
                });
            }
        },
        security:{
            get:function(){
                $http({
                    method:'get',
                    url:'sys/security',
                    params:{id:$scope.securityInfo.id}
                }).then(function (resp) {
                    console.log(resp);
                    $scope.securityInfo = resp.data.data[0];
                });
            },
            update:function(){
                var params = $scope.securityInfo;
                $http({
                    method:'PUT',
                    url:'sys/security',
                    data:angular.toJson($scope.securityInfo)
                }).then(function (resp) {
                    console.log(resp);
                  if(resp.flag="success") {
                    Notification.success({title: '安全设置更新', message: "安全设置更新成功", positionY: 'top', positionX: 'center'});
                  }
                });
            }
        },
        email:{

        }
    };

    //获取公司信息
    $scope.getCompanyInfo = function(){
        $http({
            method:'get',
            url:'sys/base',
            params:{id:$scope.companyInfo.id}
        }).success(function (resp) {
            $scope.companyInfo = resp.data[0];
            console.log(resp);
        });
    };

    $scope.config.company.get();
    $scope.config.security.get();

    //添加基本信息
    $scope.addCompanyInfo = function () {
        var params = $scope.companyInfo;
        console.log(params);

        $http({
            method:'post',
            url:'sys/base',
            data:angular.toJson($scope.companyInfo)
        }).success(function (resp) {
            console.log(resp);
        });
    };

    //更新基本信息
    $scope.updateCompanyInfo = function () {
        var params = $scope.companyInfo;
        console.log(params);

        $http({
            method:'PUT',
            url:'sys/base',
            data:angular.toJson($scope.companyInfo)
        }).success(function (resp) {
            console.log(resp);
        });
    };


}]);