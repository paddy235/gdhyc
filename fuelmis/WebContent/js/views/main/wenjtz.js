gddlapp.controller('wenjtzCtrl', function($scope,$rootScope,$routeParams, $http/*,$location*/) {
    //--------------------------------------日期定义--------------------------------------------------------------------------
    var date=new Date();
    $scope.search = {
        riq:date.format('yyyy-MM')
    };
    //-----------------------------------------------------------------------------------------------------------------------
    $scope.searchData = function() {
        $http.post('zonghzs/wenjtz/getWenjList',{riq:$scope.search.riq}).success(function(data) {
            $scope.wenjList=data;
        });
    };
    $scope.searchData();
    $scope.wenjtz=function (id) {
        $http.post('zonghzs/wenjtz/getWenjmx',{id:id}).success(function(data) {
            $scope.wenjmx=data;
            $scope.myTost=true;
        });
    };
    $scope.close=function(){
        $scope.myTost=false;
    }
    $scope.loadfuj=function (url) {
        window.open(url)
    }
    $("#datepicker").datepicker({
        format : 'yyyy-mm',
        minViewMode : 1,
        language : "zh-CN",
        autoclose : true,
        orientation: "top"
    });
});

