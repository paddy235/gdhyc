gddlapp.controller('fahbCtrl', function($scope,$rootScope,$http,$log,$location) {
    var date =new Date();
    $scope.search ={
        diancid:515,
        sdate:date.format("yyyy-MM"+"-01"),
        edate:date.format("yyyy-MM-dd")
    };
    $scope.hetmbList = new Array();
    $scope.update=function(){
        $http.post('gongys/fahb/updateFahb',{search:angular.toJson($scope.search)}).
        success(function(data, status, headers, config) {
            if(data){
                alert("生成成功");
            }
        });
    };

    $scope.refresh=function(){
        var date = $scope.search.date;
        oTable.fnReloadAjax('gongys/fahb/getFahbList/?date='+date);
    };
    $scope.refresh();
});