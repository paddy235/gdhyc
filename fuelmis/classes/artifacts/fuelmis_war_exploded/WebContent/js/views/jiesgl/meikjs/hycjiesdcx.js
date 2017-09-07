gddlapp.controller('hycjiesdcxCtrl', function($scope,$rootScope,$http,$log,$location) {
	$scope.jiesdcxTitle='结算单查询';
	var begin=new Date();
	var sDate= begin.format("yyyy-MM"+"-01");
	var end=new Date();
	var eDate=end.format("yyyy-MM-dd");
	$scope.search = {
		sDate : sDate,
		eDate : eDate,
		chaxlx : 1,
		gongys:-1,
		jiesbh:'-1'
	};
	$scope.chaxlxList = [{"name":"结算单","value":1},{"name":"验收明细","value":2},{"name":"过衡单","value":3},{"name":"单批次明细","value":4}];
	$scope.selJiesbh = function(){
		$http.post('jiesgl/jiesdcx/getAllJiesbh',{search:angular.toJson($scope.search)}).success(function(data) {
			$scope.jiesbhList = data;
				$scope.search.jiesbh='-1'
		});
	}
	$scope.selJiesbh();
	$scope.refresh = function() {
		$http.post('jiesgl/jiesdcx/gethycJiesd',{search:angular.toJson($scope.search)}).success(function(data) {
			$scope.jiesd = data;
		});
	}
	$scope.searchData = function() {
		$scope.selJiesbh();		
		if($scope.search.jiesbh=='-1'){
			//alert("请选择结算编号！");
			return false;
		}
		$scope.refresh();
	}
	$scope.printPage = function() {
		$("#report").jqprint();
	}

});