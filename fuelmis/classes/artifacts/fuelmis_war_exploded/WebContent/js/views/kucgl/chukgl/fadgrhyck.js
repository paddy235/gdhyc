gddlapp.controller('fadgrhyckCtrl', function ($scope, $rootScope, $http, $log, $location) {
    // $scope.fadgrhyckTitle = '发电供热耗用出库';
    var begin = new Date();
    var end = new Date();
    $scope.search = {
        sDate: begin.format('yyyy-MM') + '-01',
        eDate: end.format('yyyy-MM-dd'),
        rukdbh: ''
    };

    $scope.searchData = function () {
        $http.post('kucgl/chukgl/getChukdList', {search: angular.toJson($scope.search)})
            .success(function (data) {
                $scope.chukdList = data
            }).error(function (data) {
            alert('查询出库单失败!')
        })

    };
    $scope.searchData();
    $scope.edit = function (args) {
        $location.path('/editFadgrhyck/' + args);
    };

    $scope.addRanlcgrk = function () {
        $location.path('/addFadgrhyck');
    }
}).controller('addFadgrhyckCtrl', function ($scope, $rootScope, $http, $log, $location, $routeParams) {
    $scope.addFadgrhyckTitle = '发电供热耗用出库';
    $scope.kucztList = [{"name": "未出库", "value": 0}, {"name": "已出库", "value": 1}];
//    $scope.chukd.ZHUANGT=0;
    $scope.chukdhList = [];
    $scope.load = function () {
        $http.post('kucgl/chukgl/getHaoyckmx', {rukdbh: $scope.chukd.CHUKDBH})
            .success(function (data) {
                $scope.chukd = data.chukd;
                $scope.chukdhList = data.chukdhList;
            }).error(function (data) {
            alert("获取耗用明细失败!")
        })
    };
    $http.post('kucgl/chukgl/getDangrjc')
        .success(function (data) {
            $scope.dangrjc = data;
            $scope.dangrjcList = [];
            n = data.length;
            for (var i = 0; i <= n; i = i + 3) {
                if (n % 3 == 0) {
                    $scope.dangrjcList.push({
                        first: data[i].KUCWZ + " " + data[i].KUCWL + " " + data[i].SHUL + ";\t",
                        second: data[i + 1].KUCWZ + " " + data[i + 1].KUCWL + " " + data[i + 1].SHUL + ";\t",
                        third: data[i + 2].KUCWZ + " " + data[i + 2].KUCWL + " " + data[i + 2].SHUL + ";"
                    });
                } else if (n % 3 == 2) {
                    $scope.dangrjcList.push({
                        first: data[i].KUCWZ + " " + data[i].KUCWL + " " + data[i].SHUL + ";\t",
                        second: data[i + 1].KUCWZ + " " + data[i + 1].KUCWL + " " + data[i + 1].SHUL + ";\t",
                        third: data[i + 2].KUCWZ + " " + data[i + 2].KUCWL + " " + data[i + 2].SHUL + ";"
                    });
                    if (i == n - 2) {
                        $scope.dangrjcList.push({
                            first: data[i].KUCWZ + " " + data[i].KUCWL + " " + data[i].SHUL + ";\t",
                            second: data[i + 1].KUCWZ + " " + data[i + 1].KUCWL + " " + data[i + 1].SHUL + ";\t",
                            third: " "
                        });
                    }
                } else if (n % 3 == 1) {
                    $scope.dangrjcList.push({
                        first: data[i].KUCWZ + " " + data[i].KUCWL + " " + data[i].SHUL + ";\t",
                        second: data[i + 1].KUCWZ + " " + data[i + 1].KUCWL + " " + data[i + 1].SHUL + ";\t",
                        third: data[i + 2].KUCWZ + " " + data[i + 2].KUCWL + " " + data[i + 2].SHUL + ";"
                    });
                    if (i == n - 2) {
                        $scope.dangrjcList.push({
                            first: data[i].KUCWZ + " " + data[i].KUCWL + " " + data[i].SHUL + ";\t",
                            second: " ",
                            third: " "
                        });
                    }
                }

            }

        }).error(function (data) {
        alert("获取当日结存失败!")
    })
    $scope.convertNumbers = function () {
        var arr = $scope.chukdhList;
        var zongl = 0;
        for (var i = 0; i < arr.length; i++) {
            if (arr[i].CHUKSL != null && arr[i].CHUKSL != "") {
                zongl += parseFloat(arr[i].CHUKSL);
            }
        }
        $scope.chukd.CHUKZSL = zongl;
    }

    $scope.add = function () {
        var obj = new Object();
        obj = {
            // tiaozje:0
        }
        $scope.chukdhList.push(obj);
    };

    var delArray = [];
    $scope.del = function (button) {
        var i = button.id;
        if ($scope.chukdhList[i].ID) {
            delArray.push($scope.chukdhList[i].ID)
        }
        $scope.chukdhList.splice(i, 1);
        return false;
    };
    $scope.getElementfromArray = function (array, key) {
        for (var i = 0; i < array.length; i++) {
            for (var p in array[i]) {
                if (array[i][p] == key) {
                    return array[i];
                }
            }
        }
    }
    $scope.save = function () {
        var temp = new Array();
        var chukdhArray = $scope.chukdhList;
        for (var i = 0; i < $scope.dangrjc.length; i++) {
            var sum = 0.0;
            for (var j = 0; j < chukdhArray.length; j++) {
                if (chukdhArray[j].KUCWZ == $scope.dangrjc[i].KUCWZ_ID
                    && chukdhArray[j].KUCWL == $scope.dangrjc[i].KUCWL_ID
                ) {
                    sum += parseFloat(chukdhArray[j].CHUKSL);
                } else {
                    temp.push(chukdhArray[j]);
                }
            }
            chukdhArray = temp;
            temp = new Array();
            if (sum > parseFloat($scope.dangrjc[i].SHUL)) {
                alert($scope.dangrjc[i].KUCWZ + "的" + $scope.dangrjc[i].KUCWL + "出库数量应小于当日结存");
                return;
            }
        }
        if (chukdhArray.length != 0) {
            alert($scope.getElementfromArray($scope.kucwzList, chukdhArray[0].KUCWZ).name + "的"
                + $scope.getElementfromArray($scope.kucwlList, chukdhArray[0].KUCWL).name + "出库数量应小于当日结存");
            return;
        }

        $http.post('kucgl/chukgl/saveChukd', {
            data: angular.toJson({
                chukd: $scope.chukd,
                chukdhList: $scope.chukdhList
            })
        }).success(function (data) {
            alert("保存成功");
            $scope.chukd.CHUKDBH = data;
            $scope.load();
        }).error(function (data) {
            alert("保存失败!")
        })

    };
    $scope.chuk = function () {
        $http.post('kucgl/chukgl/chuk', {
            data: angular.toJson({
                chukd: $scope.chukd,
                chukdhList: $scope.chukdhList
            })
        }).success(function (data) {
            alert("出库成功!");
        }).error(function (data) {
            alert("出库失败!")
        })
    }
    $scope.chex = function () {
        $http.post('kucgl/chukgl/chex', {chukdbh: $scope.chukd.CHUKDBH}).success(function (data) {
            alert("撤销成功!")
            $scope.load()
        }).error(function (data) {
            alert("撤销失败!")
        })
    }
    $scope.cancel = function () {
        $location.path('/fadgrhyck');
    };
}).controller('editFadgrhyckCtrl', function ($scope, $rootScope, $http, $log, $location, $routeParams) {
    $scope.addFadgrhyckTitle = '发电供热耗用出库';
    $scope.ro = true;
    $scope.kucztList = [{"name": "未出库", "value": 0}, {"name": "已出库", "value": 1}];
    $scope.load = function () {
        $http.post('kucgl/chukgl/getHaoyckmx', {rukdbh: $routeParams.rukdbh})
            .success(function (data) {
                $scope.chukd = data.chukd;
                $scope.chukdhList = data.chukdhList
            }).error(function (data) {
            alert("获取耗用明细失败!")
        })
    };
    $scope.load();
    $scope.convertNumbers = function () {
        var arr = $scope.chukdhList;
        var zongl = 0;
        for (var i = 0; i < arr.length; i++) {
            if (arr[i].CHUKSL != null && arr[i].CHUKSL != "") {
                zongl += parseFloat(arr[i].CHUKSL);
            }
        }
        $scope.chukd.RUKZSL = zongl;
    };


    $scope.add = function () {
        var obj = new Object();
        obj = {
            // tiaozje:0
        };
        $scope.chukdhList.push(obj);
    };


    var delArray = [];
    $scope.del = function (button) {
        var i = button.id;
        if ($scope.chukdhList[i].ID) {
            delArray.push($scope.chukdhList[i].ID)
        }
        $scope.chukdhList.splice(i, 1);
    };

    $scope.save = function () {
        $http.post('kucgl/chukgl/saveChukd', {
            data: angular.toJson({
                delChukdhId: delArray,
                chukd: $scope.chukd,
                chukdhList: $scope.chukdhList
            })
        }).success(function (data) {
                alert("保存成功");
                $scope.chukd.CHUKDBH = data;
            }
        ).error(function (data) {
            alert("保存失败!")
        })
    };

    $scope.chuk = function () {
        $http.post('kucgl/chukgl/chuk', {
            data: angular.toJson({
                chukd: $scope.chukd,
                chukdhList: $scope.chukdhList
            })
        }).success(function (data) {
            alert("出库成功!");
            $scope.load();
            // $('input').attr("disabled", true);
        }).error(function (data) {
            alert("出库失败!")
        })
    }

    $scope.chex = function () {
        $http.post('kucgl/chukgl/chex', {chukdbh: $scope.chukd.CHUKDBH}).success(function (data) {
            alert("撤销成功!")
            $scope.load()
        }).error(function (data) {
            alert("撤销失败!")
        })
    }

    $scope.cancel = function () {
        $location.path('/fadgrhyck');
    };
})