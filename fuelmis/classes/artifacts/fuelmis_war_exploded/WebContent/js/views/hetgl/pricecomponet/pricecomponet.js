gddlapp.controller('priceComponetCtrl', function ($scope, $rootScope, $http, $log, $location) {
    $scope.priceComponetTitle = '计价组件维护';
    $scope.priceComponet = new Object();
    $scope.itemId = 0;
    $scope.addPriceComponet = function () {
        var flag = "Add";
        $location.path('/addPriceComponet/' + flag);
    }

    $scope.editPriceComponet = function () {
        var checkbox = $("#example input[type=checkbox]:checked")[0]
        if (checkbox) {
            var flag = "Update";
            $location.path('/addPriceComponet/' + flag + "/" + checkbox.id);
        } else {
            alert("请选择要修改的行！");
        }
    }


    $scope.refresh = function () {
        var diancid = $scope.search.diancid;
        var strdate = $scope.search.strdate;
        var enddate = $scope.search.enddate;
        oTable.fnReloadAjax('priceComponet/searchPriceComponetList/?diancid=' + diancid + '&strdate=' + strdate + '&enddate=' + enddate);
    }


    $scope.delPriceComponet = function () {
        if ($("#example input[type=checkbox]:checked").length < 1) {
            alert("请选择要删除的行！")
        } else {
            $('#myModal_Del').modal('show');
        }
    }

    $scope.addRelation = function () {
        if ($("#example input[type=checkbox]:checked").length < 1) {
            alert("请选择数据！")
        } else {
            $("#example input[type=checkbox]").each(function () {
                if (this.checked) {
                    var id = $(this).attr("id");
                    $http.post('hetgl/pricecomponet/editPriceComponet', {id: id}).success(function (data, status, headers, config) {
                        if (data) {
                            $scope.priceComponet = data.priceComponent;
                            $('#priceItemModal').modal('show');
                        }
                    });
                }
            });
        }
    }

    $scope.toAddRelation = function () {
        var flag = "Update";
        //$location.path('/addPriceComponet/'+flag+"/"+$(this).attr("id"));
        var componetId = $scope.priceComponet.id;
        var traUrl = $scope.priceComponet.url;
        var itemId = $scope.itemId;
        var url = traUrl + '/' + itemId + '/' + componetId;
        $location.path(url);
    }


})


    .controller('priceComponetAddCtrl', function ($scope, $rootScope, $http, $location, $routeParams) {
        $scope.priceComponetTitle = '添加计价组件';
        $scope.priceComponet = new Object();
        if ($routeParams.flag == "Add") {
            $scope.priceComponetTitle = '新增计价组件信息';
            $scope.priceComponet.status = 1;
        } else if ($routeParams.flag == "Update") {
            $scope.priceComponetTitle = '修改计价组件信息';
            $http.post('hetgl/pricecomponet/editPriceComponet', {id: $routeParams.id}).success(function (data, status, headers, config) {
                if (data) {
                    $scope.priceComponet = data.priceComponent;
                }
            });
        }

        $scope.cancel = function () {
            $location.path('/hetgl/pricecomponet');
        };

        $scope.savePriceComponet = function () {
            if ($scope.priceComponet.id == null) {
                $http.post('hetgl/pricecomponet/addPriceComponet', {info: angular.toJson($scope.priceComponet)}).success(function (data, status, headers, config) {
                    if (data[0] == 1) {
                        alert("新增计价组件成功！");
                        $location.path('/hetgl/pricecomponet');
                    } else {
                        alert("新增计价组件失败！");
                        $location.path('/hetgl/pricecomponet');
                    }
                    $location.path('/hetgl/pricecomponet');
                });
            } else {
                $http.post('hetgl/pricecomponet/updatePriceComponet', {info: angular.toJson($scope.priceComponet)}).success(function (data, status, headers, config) {
                    if (data[0] == 1) {
                        alert("修改计价组件成功！");
                        $location.path('/hetgl/pricecomponet');
                    } else {
                        alert("修改计价组件失败！");
                        $location.path('/hetgl/pricecomponet');
                    }
                });

            }
        }

    })


    .controller('priceComponetRelationCtrl', function ($scope, $rootScope, $http, $location, $routeParams) {
        $scope.priceComponetTitle = '添加组件关系';
        var itemId = $routeParams.priceItem;
        var componetId = $routeParams.id;

        $scope.priceComponet = new Object();

        $scope.priceItem = new Object();


        $scope.priceQalityRangeList = new Array();

        $http.post('hetgl/pricecomponet/editPriceComponet', {id: componetId}).success(function (data, status, headers, config) {
            if (data) {
                $scope.priceComponet = data.priceComponent;
            }
        });

        $http.post('hetgl/priceitem/editPriceItem', {id: itemId}).success(function (data, status, headers, config) {
            if (data) {
                $scope.priceItem = data.priceItem;
            }
        });


        $scope.deleteQalityRange = function (qalityRange) {
            $.each($scope.priceQalityRangeList, function (i) {
                if (this == qalityRange) {
                    $scope.priceQalityRangeList.splice(i, 1);
                    return false;
                }
            });
        }

        $scope.addQalityRange = function () {
            var obj = new Object();
            obj.status = 1;
            obj.price_component_id = $routeParams.id;
            obj.quality_item_id = $routeParams.priceItem;
            $scope.priceQalityRangeList.push(obj);
        }

        $scope.saveQalityRange = function () {
            $http.post('hetgl/priceqalityrange/addPriceQalityRanges', {info: angular.toJson($scope.priceQalityRangeList)}).success(function (data, status, headers, config) {
                if (data[0] == 1) {
                    alert("新增热值按质计价组件成功！");
                    $location.path('/hetgl/priceqalityrange');
                } else {
                    alert("新增热值按质计价组件失败！");
                    $location.path('/hetgl/priceqalityrange');
                }
            });
        }

    })



