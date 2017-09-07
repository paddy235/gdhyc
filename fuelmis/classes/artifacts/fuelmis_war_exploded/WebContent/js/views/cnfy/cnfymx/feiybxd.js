gddlapp.controller('feiybxdCtrl', function($scope,$rootScope,$routeParams,$http,$log,$location) {
	$scope.cnfymxTitle='费用报销单查询';
	$http.post('zafjswh/getzaffybxdbidandrenyxx').success(function(data){
		$scope.search.xingm = data.xingm;
		$scope.search.department = data.department;
	});
	$http.post('zafjswh/getbxddata',{zaffybxd_id:$routeParams.id}).success(function(data){
		$scope.arr = data;
	});
	$http.post('zafjswh/getUserAndDept',{zaffybxd_id:$routeParams.id}).success(function(data){
		$scope.username = data.QUANC;
		$scope.department = data.BUM;
	});
	$scope.printPage = function() {
		$("#report").jqprint();
	}
	$scope.reback = function(){
		$location.path('/feiymx/'); 
	}

});