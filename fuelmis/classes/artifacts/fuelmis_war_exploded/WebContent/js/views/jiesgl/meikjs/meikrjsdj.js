gddlapp.controller('meikrjsdanjCtrl', function($scope, $rootScope,$routeParams, $http,$location) {
	$scope.meikrjsdanjTitle = '煤款日结算单据';
	
	$http.post('jiesgl/jiesdxg/getJiesd',{jiesbh:$routeParams.id}).success(function(data) {
		$scope.data = data;
	});
	
	$scope.save = function(){
		$http.post('jiesgl/jiesdxg/save',{old_jiesbh:$routeParams.id,new_jiesbh:$scope.data.JIESBH}).success(function(data) {
			if(data[0]==1){
				alert("保存成功！");
			}else{
				alert("保存失败！");
			}
		});
	}
        
    $scope.reback = function(){
    	$location.path("meikrjs");
    }
})
