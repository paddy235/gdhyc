gddlapp.controller('ranmzhrbb', function($scope,$rootScope,$http,$log,$location) {
	$scope.ribcxTitle='日报查询';
	$scope.search = {};
	$scope.baobleixlist =[{ "name":"按单位汇总", "value":1},{ "name":"按煤源地区汇总",  "value":2},{ "name":"周报",  "value":3},{ "name":"日平均库存",  "value":4}];
	$scope.search.baobleix = 1;
	$scope.search.diancid = 515;
	var d = new Date();
	$scope.search.kaisriq = d.format('yyyy-MM-dd');
	$scope.search.jiezriq = d.format('yyyy-MM-dd');;
	$scope.refresh = function(){
        $http.post('ranm/ribcx',{condition:angular.toJson($scope.search)}).success(function(data) {
            document.getElementById("report").innerHTML = data;
        }).error(function (data) {
			alert("查询失败"+data);
        });
	};
    $scope.refresh();
	$scope.printPage = function() {
		$("#report").jqprint();
	}
	

});