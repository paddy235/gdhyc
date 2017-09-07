gddlapp.controller('yuebsjpzCtrl', function ($scope, $rootScope, $http, $log, $location) {
    var date = new Date();
    // date.setMonth(date.getMonth() - 1);
    var riq = date.format('yyyy-MM');
    //查询
    $scope.search = function () {
        $http.post('yuebgl/yuebwh/getYuebsjpzList').success(function (data) {
            $scope.yuebsjpzList = data;
        });
    };
    $scope.search();

//删除
    $scope.delete = function (i) {
        var id=$scope.yuebsjpzList[i].ID;
        $scope.yuebsjpzList.splice(i, 1);
        $http.post('yuebgl/yuebwh/deleteYuebsjpz', {id: id}).success(function (data, status, headers, config) {
            alert("数据删除成功！");
        }).error(function () {
            alert("数据删除失败！");
        });
    };
//保存
    $scope.save = function () {
        $http.post('yuebgl/yuebwh/saveYuebsjpz', {data: angular.toJson($scope.yuebsjpzList)}).success(function (data, status, headers, config) {
            if (status == 200) {
                alert("保存成功！");
                $scope.search();
            } else {
                alert("保存失败！");
            }
        });
    };
    $scope.add = function () {
        $scope.yuebsjpzList.unshift({YUEBJQ:riq,YUEBKSRQ:date.format('yyyy-MM'+'-01'),YUEBJZRQ:date.format('yyyy-MM-dd')});
    };
    $(".datepicker0").live('focus', function () {
        $(this).datepicker({
            format: 'yyyy-mm-dd',
            minViewMode: 0,
            language: "zh-CN",
            autoclose: true,
            orientation:'top'
        });
    });
    $(".datepicker1").live('focus', function () {
        $(this).datepicker({
            format: 'yyyy-mm',
            minViewMode: 1,
            language: "zh-CN",
            autoclose: true,
            orientation:'top'
        });
    });
});