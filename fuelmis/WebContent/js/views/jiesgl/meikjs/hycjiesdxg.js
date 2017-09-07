gddlapp.controller('hycmeikjsxg_new', function ($scope, $rootScope, $routeParams, $http, $location) {
    $scope.jiesdxgTitle = '结算单生成（红雁池）';
    //返回
    $scope.jiesdid = -1;
    $scope.data = {};
    /*
     * 计算
     */
    //输入：含税单价、结算量、税率
    $scope.changefirst = function () {
        $scope.data.MEIKJE = (Number($scope.data.JIESJG) * Number($scope.data.JIESSL) / (1 + Number($scope.data.SHUIL))).toFixed(2);
        $scope.data.JIAKHJ = Number($scope.data.BUKK) + Number($scope.data.MEIKJE);
        $scope.data.BUHSDJ = (Number($scope.data.MEIKJE) / (1 + Number($scope.data.SHUIL))).toFixed(2);
        if ($scope.data.BUKK != 0) {
            $scope.data.SHUIK = (Number($scope.data.JIAKHJ) * Number($scope.data.SHUIL)).toFixed(2);
            $scope.data.MEIKHJ = Number($scope.data.JIAKHJ) + Number($scope.data.SHUIK);
        } else {
            $scope.data.MEIKHJ = Number($scope.data.JIESJG) * Number($scope.data.JIESSL);
            $scope.data.SHUIK = Number($scope.data.MEIKHJ) - Number($scope.data.JIAKHJ).toFixed(2);
        }
    }
    //输入金额、补扣款
    $scope.changesecond = function () {
        $scope.data.JIAKHJ = Number($scope.data.MEIKJE) + Number($scope.data.BUKK);
        $scope.data.BUHSDJ = (Number($scope.data.MEIKJE) / (1 + Number($scope.data.SHUIL))).tofix(2);
        $scope.data.SHUIK = Number($scope.data.JIAKHJ) * Number(Number($scope.data.SHUIL));
        $scope.data.MEIKHJ = Number($scope.data.JIAKHJ) + Number($scope.data.SHUIK);
    }
    //输入税款
    $scope.changethird = function () {
        $scope.data.MEIKHJ = Number($scope.data.JIAKHJ) + Number($scope.data.SHUIK);
    }

    $scope.calculateMEIKHJ = function () {
        $scope.data.MEIKHJ = (parseFloat($scope.data.MEIKJE) + parseFloat($scope.data.SHUIK)).toFixed(2)
    }
    $scope.data.MEIKHJ = (parseFloat($scope.data.MEIKJE) + parseFloat($scope.data.SHUIK)).toFixed(2)
    $http.post('jiesgl/meikjs/getJiesdbh').success(function (data) {
        $scope.jiesdbhList = data;
    });
    /*
     * 查询结算单
     */
    $scope.searchData = function () {
        console.log($scope.jiesdid);
        $http.post('jiesgl/meikjs/getJiesdlist', {jiesbh: $scope.jiesdid}).success(function (data) {
            $scope.data = data;
        });
    }
    /*
     * 保存结算单
     */
    $scope.save = function () {
        $http.post('jiesgl/meikjs/hycSaveJiesd', {data: angular.toJson($scope.data)}).success(function (data) {
            alert("保存成功！结算单号：" + data);
            $scope.data.JIESBH = data;
        }).error(function (data) {
            alert("保存失败！");
        });
    }

    /*
     * 删除结算单
     */
    $scope.deleteByjiesdbh = function () {
        $http.post('jiesgl/meikjs/hycDeleteJiesdByJiesdbh', {jiesbh: $scope.data.JIESBH}).success(function (data) {
            if (data != "") {
                alert(data);
                $http.post('jiesgl/meikjs/getJiesdbh').success(function (data) {
                    $scope.jiesdbhList = data;
                    $scope.data = {};
                });
            } else {
                alert("删除失败！");
            }
        });
    }
})
    .controller('hycmeikjsxg_new1', function ($scope, $rootScope, $routeParams, $http, $location) {
        $scope.jiesdxgTitle = '结算单生成（红雁池）';
        //返回
        $scope.jiesdid = -1;
        $scope.data = {};

        /*
         * 计算
         */
        $scope.changefirst = function () {
            $scope.jiesd.MEIKHJ = (Number($scope.jiesd.JIESSL) * Number($scope.jiesd.HETJG)).toFixed(2);
            $scope.jiesd.JIAKHJ = ($scope.jiesd.MEIKHJ - $scope.jiesd.BUKK).toFixed(2);
            $scope.jiesd.ZONGHJ = $scope.jiesd.JIAKHJ + $scope.jiesd.YUNZFHJ;
            $scope.jiesd.MEIKJE = (Number($scope.jiesd.JIAKHJ) / 1.17).toFixed(2);
            $scope.jiesd.SHUIK = (Number($scope.jiesd.JIAKHJ) - Number($scope.jiesd.MEIKJE)).toFixed(2);
            $scope.jiesd.JIESJG = (Number($scope.jiesd.ZONGHJ) / Number($scope.jiesd.JIESSL)).toFixed(2);//小计有待完善
        }
        $scope.changesecond = function () {
            $scope.jiesd.JIAKHJ = ($scope.jiesd.MEIKHJ - $scope.jiesd.BUKK).toFixed(2);
            $scope.jiesd.MEIKJE = (Number($scope.jiesd.JIAKHJ) / 1.17).toFixed(2);
            $scope.jiesd.SHUIK = (Number($scope.jiesd.JIAKHJ) - Number($scope.jiesd.MEIKJE)).toFixed(2);
            $scope.jiesd.ZONGHJ = $scope.jiesd.JIAKHJ + $scope.jiesd.YUNZFHJ;
            $scope.jiesd.JIESJG = (Number($scope.jiesd.ZONGHJ) / Number($scope.jiesd.JIESSL)).toFixed(2);
        }
        $scope.changethird = function () {
            $scope.jiesd.SHUIK = ($scope.jiesd.JIAKHJ - $scope.jiesd.MEIKJE).toFixed(2);
        }

        $http.post('jiesgl/meikjs/getJiesdbh').success(function (data) {
            $scope.jiesdbhList = data;
        });
        /*
         * 查询结算单
         */
        $scope.searchData = function () {
            console.log($scope.jiesdid);
            if ($scope.jiesdid != -1) {
                $http.post('jiesgl/meikjs/getJiesdlist', {jiesbh: $scope.jiesdid}).success(function (data) {
                    $scope.jiesd = data;
                }).error(function (data) {
                    alert("查询结算单出错！");
                });
            }

        }
        /*
         * 保存结算单
         */
        $scope.save = function () {
            $http.post('jiesgl/meikjs/hycSaveJiesd', {data: angular.toJson($scope.jiesd)}).success(function (data) {

                    alert("保存成功！结算单号：" + data);
                    $scope.jiesd.JIESBH = data;

            }).error(function (data) {
                alert("保存失败!" );
            });
        }

        /*
         * 删除结算单
         */
        $scope.deleteByjiesdbh = function () {
            $http.post('jiesgl/meikjs/hycDeleteJiesdByJiesdbh', {jiesdbh: $scope.jiesdid}).success(function (data) {
                if (data != "") {
                    alert(data);
                    $http.post('jiesgl/meikjs/getJiesdbh').success(function (data) {
                        $scope.jiesdbhList = data;
                        $scope.data = null;
                    });
                } else {
                    alert("删除失败！");
                }
            });
        }
    })