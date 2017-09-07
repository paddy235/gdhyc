gddlapp.controller('ranmzgmxCtrl', function($scope, $rootScope,$routeParams, $http/*,$location*/) {
	$scope.Title = '燃煤暂估明细表';
	//--------------------------------------日期定义--------------------------------------------------------------------------

	var begin=new Date();
	var end=new Date();
	new Date(begin.setMonth((new Date().getMonth()-1)));
	var begintime= begin.format("yyyy-MM");
	var endtime=end.format("yyyy-MM");
	//-----------------------------------------------------------------------------------------------------------------------
	$scope.search = {
		riq:endtime
	};
	$scope.searchData = function() {
		$http.post('kucgl/kucbb/getRanmzg',{search:angular.toJson($scope.search)}).success(function(data) {
			document.getElementById("report").innerHTML = data[0].html;

		});
	};
	$scope.searchData();
	
	$scope.printPage = function() {
		$("#report").jqprint();
	}
})



