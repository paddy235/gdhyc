gddlapp.controller('yuebscCtrl', function ($scope, $rootScope, $http, $log, $location) {
    var date = new Date();
    $scope.search = {
        riq: date.format('yyyy-MM'),
        diancid: 515
    };
    $scope.load = function () {
        $http.post('yuebgl/yuebsc/getYueb', {
            search:angular.toJson($scope.search)
        }).success(function (data) {
            $scope.yuebList = data;
        })
    };
    $scope.load();

    $scope.searchData = function () {
        $scope.load()
    };

    $scope.submitYueb = function () {
        $http.post('yuebgl/yuebsc/yuebsc', {
            search:angular.toJson($scope.search),
            data:angular.toJson($scope.yuebList)
        }).success(function (data) {
            alert("上报成功！");
            $scope.load();
        }).error(function (data) {
            alert("上报失败！");
        });
    }
});