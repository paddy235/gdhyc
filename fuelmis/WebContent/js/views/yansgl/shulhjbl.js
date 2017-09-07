gddlapp.controller('shulhjblCtrl', function($scope,$rootScope,$http,$log,$location) {
	$scope.title = "数量痕迹保留";
	$scope.search = {
		pich : '',
		chph : ''
	};
	
	 $scope.getShulhjblAll=function(){
		 $http.post('yansgl/getHenjblAll',{search:angular.toJson($scope.search)}).success(function(data) {
				$scope.list = data
	 	});
	 }
});