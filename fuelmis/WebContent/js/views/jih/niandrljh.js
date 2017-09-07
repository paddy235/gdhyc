gddlapp.controller('niandrljhCtrl', function($scope,$rootScope,$http,$log,$location) {
	$scope.niandrljhTitle='年度入炉计划查询';
	$scope.search = new Object();
	var d = new Date();
	var vYear = d.getFullYear();
	$scope.search.year = vYear;
	$scope.search.diancid = 515;
	$http.post('niandranlzfjh/niandranlzfjhcx',{year:$scope.search.year,diancid:$scope.search.diancid}).success(function(data) {
		document.getElementById("report").innerHTML = data;
	});
	$scope.refresh = function(){
		$http.post('niandranlzfjh/niandranlzfjhcx',{year:$scope.search.year,diancid:$scope.search.diancid}).success(function(data){
			document.getElementById("report").innerHTML = data;
		});
	}
	
	$scope.printPage = function() {
		$("#report").jqprint();
	}
	

});