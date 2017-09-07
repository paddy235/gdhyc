gddlapp.controller('haocmxCtrl', function ($scope, $rootScope, $http, $log, $location) {
    var date = new Date();
    date.setMonth(date.getMonth() - 1);
    $scope.search = {
        riq: date.format('yyyy-MM'),
        diancid: 515
    };
    $('table tbody td input').focus(function () {
        this.addClass("warning");
    }).blur(function () {
        this.removeClass("warning");
    }).addClass("editorable");
    $http.post('yuebgl/yuebwh/haocmx/check').success(function (data, status, headers, config) {
        if (data[0] == 0) {
            $('#createBtn').hide();
            $('#delBtn').hide();
            $('#saveBtn').hide();
        }
    });
//-----------------------------------------------------------------------------------------------------------------------
    /**
     * 查询数据
     */
    $scope.searchData = function () {
        var riq = $scope.search.riq;
        var dianc = $scope.search.diancid;
        $http.post('yuebgl/yuebwh/haocmx/getAll?riq=' + riq + '&dianc=' + dianc).success(function (data, status, headers, config) {
            $scope.haocmxList = data.data;
            $scope.zhuangt = $scope.haocmxList[2].ZHUANGT;

        }).error(function (data) {
            alert("查询出错")
        });
        $http.post('yuebgl/yuebwh/haocmx/check?riq=' + riq + '&dianc=' + dianc).success(function (data, status, headers, config) {
            if (data[0] == 0) {
                $('#createBtn').hide();
                $('#delBtn').hide();
                $('#saveBtn').hide();
            } else {
                $('#createBtn').show();
                $('#delBtn').show();
                $('#saveBtn').show();
            }
        });
    };

    $scope.searchData();
//-----------------------------------------------------------------------------------------------------------------------
    $scope.createData_Win = function () {
        $('#myModal_Create').modal('show');
    };
    /**
     * 生成数据
     */
    $scope.createData = function () {
        var riq = $scope.search.riq;
        var dianc = $scope.search.diancid;
        $http.post('yuebgl/yuebwh/haocmx/createData', {
            riq: riq,
            dianc: dianc
        }).success(function (data, status, headers, config) {
            if (data == 1) {
                $('#myModal_Create').modal('hide');
                alert("生成数据成功！");
                $scope.searchData();
            } else {
                $('#myModal_Create').modal('hide');
                alert("生成数据失败！");
            }
        });
        $('#myModal_Create').modal('hide');
    };

    $scope.delData_Win = function () {
        $('#myModal_Del').modal('show');
    };
//----------------------------------------------------------------------------------------------------------------------
    /**
     * 删除数据
     */
    $scope.delData = function () {
        var riq = $scope.search.riq;
        var dianc = $scope.search.diancid;
        $http.post('yuebgl/yuebwh/haocmx/delData', {
            riq: riq,
            dianc: dianc
        }).success(function (data, status, headers, config) {
            if (parseInt(data[0]) > 0) {
                $('#myModal_Del').modal('hide');
                alert("数据删除成功！");
                $http.post('yuebgl/yuebwh/haocmx/getAll?riq=' + riq + '&dianc=' + dianc).success(function (data, status, headers, config) {
                    $scope.haocmxList = data.data;
                });
            } else {
                $('#myModal_Del').modal('hide');
                alert("数据删除失败！");
            }
        }).error(function (data) {
            alert('删除过程中出现异常!');
        });
    };
//----------------------------------------------------------------------------------------------------------------------
    /**
     * 保存数据
     */
    $scope.saveData = function () {
        var fadyHej = 0;
        var gongryHej = 0;
        var qityHej = 0;
        var d = $scope.haocmxList;
        for (var i = 0; i < d.length; i++) {
            if (d[i].FENX == '本月') {
                fadyHej = eval(fadyHej + "+" + '(' + d[i].FADY + ')')
                gongryHej = eval(gongryHej + "+" + '(' + d[i].GONGRY + ')')
                qityHej = eval(qityHej + "+" + '(' + d[i].QITH + ')')
            }
        }
        // if (fadyHej != d[0].FADY) {
        //     alert("本月发电耗明细之和与合计不一致！请进行修改。")
        // } else if (gongryHej != d[0].GONGRY) {
        //     alert("本月供热耗明细之和与合计不一致！请进行修改。")
        // } else if (qityHej != d[0].QITH) {
        //     alert("本月其它耗明细之和与合计不一致！请进行修改。")
        // } else {
            var riq = $scope.search.riq;
            var dianc = $scope.search.diancid;
            $http.post('yuebgl/yuebwh/haocmx/saveData', {
                riq: riq,
                dianc: dianc,
                haocmxList: angular.toJson($scope.haocmxList)
            }).success(function (data, status, headers, config) {
                if (data[0] == 1) {
                    alert("保存成功！");
                    $scope.searchData();
                } else {
                    alert("保存失败！");
                }
            }).error(function (data) {
                alert("保存过程中出现异常!");
            });
        // }

    };

    $scope.printTable = function () {
        $("#example").jqprint();
    }
});