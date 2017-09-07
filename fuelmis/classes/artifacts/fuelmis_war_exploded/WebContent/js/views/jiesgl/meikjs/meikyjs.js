gddlapp.controller('meikyjsCtrl', function ($scope, $rootScope, $routeParams, $http, $location) {
        $scope.meikyjsTitle = '煤款月结算';
        var date = new Date();
        var year = date.getFullYear();
        var month = date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1;
        var day = date.getDate() < 10 ? '0' + date.getDate() : date.getDate();

        $scope.search = {
            sDate: year + '-' + month + '-01',
            eDate: year + '-' + month + '-' + day,
            GONGYSB_ID: -1,
            PINZB_ID: -1
        }
        $scope.refresh = function () {
            $http.post('jiesgl/meikjs/getYuejsdList', {
                condition: angular.toJson($scope.search)
            }).success(function (data) {
                $scope.yuejsList = data.data;
            });
        }
        $scope.refresh();
        $scope.getGongysList = function () {
            $http.post('jiesgl/meikjs/getGongysList', {
                condition: angular.toJson($scope.search)
            }).success(function (data) {
                $scope.gongyslist = data;
            });
            $scope.getPinzList();
            $scope.search.GONGYSB_ID = -1;
            $scope.search.PINZB_ID = -1;
            $scope.refresh();
        }
        $scope.getPinzList = function () {
            $http.post('jiesgl/meikjs/getPinzList', {
                condition: angular.toJson($scope.search)
            }).success(function (data) {
                $scope.pinzlist = data;
            });
            $scope.search.PINZB_ID = -1;
            $scope.refresh();
        }
        $scope.getGongysList();
        $scope.getPinzList();

        $scope.create = function () {
            $http.post('jiesgl/meikjs/create', {condition: angular.toJson($scope.search)}).success(function (data, status, headers, config) {
            	console.log(data);
                if (data[0] != "" && data[0] != null) {
                    $scope.refresh();
                } else {
                    alert("生成失败！");
                }
            });
        }
    })
    .controller('jiesglZafjsCtrl', function ($scope, $rootScope, $routeParams, $http, $location) {
        // $scope.meikyjsTitle = '煤款月结算';
        var date = new Date();
        var year = date.getFullYear();
        var month = date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1;
        var day = date.getDate() < 10 ? '0' + date.getDate() : date.getDate();
//----------------------------------------------------------------------------------------------------
        $scope.columnDefs = [{
            "sClass": "center",
            "mRender": function (oObj, sVal, row) {
                return '<input  type="checkbox" id="' + oObj + '" name="checkId"   />';
            },
            "bSortable": false,
            "aTargets": [0]
        }]

        //----------------------------------------------------------------------------------------------------
        $scope.search = {
            sDate: year + '-' + month + '-01',
            eDate: year + '-' + month + '-' + day,
            GONGYSB_ID: -1,
            PINZB_ID: -1,
            DIANCXXB_ID: 515,
            YEWLX: -1
        }

        $http.post('jiesgl/meikjs/getYewlxList').success(function (data) {
            $scope.yewlxList = data;
        });

        $scope.refresh = function () {
            $http.post('jiesgl/meikjs/getZafjsList', {
                search: angular.toJson($scope.search)
            }).success(function (data) {
                $scope.zafjsList = data;
            });
        }
        $scope.jies = function () {
            var checkboxes = $("#example tbody input:checked")
            var rukdh = "'-1'"
            angular.forEach(checkboxes, function (data, index, array) {
                rukdh += ",'" + data.id + "'"
            })
            $http.post('jiesgl/meikjs/zafJies', {
                data: angular.toJson(new Object({rukdh: rukdh}))
            }).success(function (data) {
                alert('结算成功')
                $location.path('/jiesgl/meikjs/zafjswh')
            });
        }
    })
    .controller('jiesglZafjswhCtrl', function ($scope, $rootScope, $routeParams, $http, $location) {
        // $scope.meikyjsTitle = '煤款月结算';
        var date = new Date();
        var year = date.getFullYear();
        var month = date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1;
        var day = date.getDate() < 10 ? '0' + date.getDate() : date.getDate();
        var select=false;

        $scope.search = {
            sDate: year + '-' + month + '-01',
            eDate: year + '-' + month + '-' + day,
            GONGYSB_ID: -1,
            PINZB_ID: -1,
            DIANCXXB_ID: 515,
            YEWLX: -1
        }

        $http.post('jiesgl/meikjs/getYewlxList').success(function (data) {
            $scope.yewlxList = data;
        });

        $scope.refresh = function () {
            $http.post('jiesgl/meikjs/getZafjswhList', {
                search: angular.toJson($scope.search)
            }).success(function (data) {
                $scope.zafjswhList = data;
            });
        }
        $scope.add = function () {
            if (!$scope.zafjswhList) {
                $scope.zafjswhList = []
            }
            $scope.zafjswhList.push(new Object({XUH: 1, JIFZL: 0, DANJ: 0, YINGJJE: 0, SHIFJE: 0}))
        };
        $scope.selectAll=function(){
        	if(!select){
        		$scope.hasSelect=true;
        		select=true;
        	}else{
        		select=false;
        		$scope.hasSelect=false;
        	}

        }
        $scope.save = function () {
            $http.post('jiesgl/meikjs/saveZafjswhList', {
                    search: angular.toJson($scope.search),
                    data: angular.toJson($scope.zafjswhList)
                }
            ).success(function (data) {
            	console.log(data);
            })
        };
        $scope.back = function () {
            $location.path('/jiesgl/meikjs/zafjs')
        };
        $scope.delete = function () {
            var checkboxes = $("#example tbody input:checked")
            var ids= "-1"
            angular.forEach(checkboxes, function (data, index, array) {
                ids += "," + data.id
            });
            $http.post('jiesgl/meikjs/deleteZafjs', {
                data: angular.toJson(new Object({ids: ids}))
            }).success(function (data) {
                alert('删除成功!')
            }).error(function (data) {
                alert('删除失败!')
            });

        }
    })

