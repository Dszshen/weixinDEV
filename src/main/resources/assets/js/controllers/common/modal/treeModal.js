XXAPP.controller('TreeModalCtrl',function($rootScope, $scope,$uibModal,$uibModalInstance,treeModal, settings) {
    //treeModal 页面所需数据
    $scope.treeModal={
        header:treeModal.header || "树列表",
        title:treeModal.title || "树列表",
        treeData:"",
        treeConfig:""
    };



    $scope.$on('$viewContentLoaded', function() {
        // initialize core components
        App.initAjax();
    });

    //关闭模态框
    $scope.cancel=function(){
        $uibModalInstance.dismiss('cancel');
    };

    //jstree 数据
    $scope.treeData = [
        { id : '1', parent : '#', text : '根节点1', state: { opened: true} },
        { id : '2', parent : '#', text : '根节点2', state: { opened: true} },
        { id : '3', parent : '1', text : '孩子节点1', state: { opened: true} },
        { id : '4', parent : '1', text : '孩子节点2' , state: { opened: true}}
    ];

    /*$http.get("/assets/md/database/system.md?t="+$rootScope.nowTime).success(function(data){

    }).error(function(error){
        console.log(error);
    });*/

    //jstree的 相关配置
    $scope.treeConfig={
        'plugins': ["wholerow", "checkbox", "types"],
        'core': {
            "themes" : {
                "responsive": false
            },
            'data': [{
                "text": "Same but with checkboxes",
                "children": [{
                    "text": "initially selected",
                    "state": {
                        "selected": true
                    }
                }, {
                    "text": "custom icon",
                    "icon": "fa fa-warning icon-state-danger"
                }, {
                    "text": "initially open",
                    "icon" : "fa fa-folder icon-state-default",
                    "state": {
                        "opened": true
                    },
                    "children": ["Another node"]
                }, {
                    "text": "custom icon",
                    "icon": "fa fa-warning icon-state-warning"
                }, {
                    "text": "disabled node",
                    "icon": "fa fa-check icon-state-success",
                    "state": {
                        "disabled": true
                    }
                }]
            },
                "And wholerow selection"
            ]
        },
        "types" : {
            "default" : {
                "icon" : "fa fa-folder icon-state-warning icon-lg"
            },
            "file" : {
                "icon" : "fa fa-file icon-state-warning icon-lg"
            }
        }
    };



});