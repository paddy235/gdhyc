gddlapp.controller('changePasswordCtrl', function($scope, $rootScope, $http,$location, $routeParams) {
	$scope.modifyRenyxxTitle = '';

	$scope.renyxx = new Object();

	$http.post('renyxx/getOne', {
		id : $routeParams.id
	}).success(function(data, status, headers, config) {
		if (data) {
			$scope.renyxx = data[0];
		}
	});

	$scope.saveRenyxx= function(valid) {
		$http.post('renyxx/updateRenyxx', {
			info : angular.toJson($scope.renyxx)
		}).success(function(data, status, headers, config) {
			if (data[0] == 1) {
				alert("修改人员信息成功！");
			} else {
				alert("修改人员信息失败！");
			}
		});
	}
})
