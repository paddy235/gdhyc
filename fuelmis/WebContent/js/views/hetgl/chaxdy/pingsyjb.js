gddlapp.controller('pingsyjbCtrl', function($scope,$rootScope,$http,$log,$location) {
	$scope.pingsyjbTitle='评审意见表';
	var date = new Date();
	var year = date.getFullYear();
	var month = date.getMonth()+1<10?'0'+(date.getMonth()+1):date.getMonth()+1;
	
	$scope.search = {
		riq : year+'-'+month,
		hetbh : ''
	};
	
	$http.post('hetgl/chaxdy/getHetbh',{riq:$scope.search.riq}).success(function(data) {
		$scope.search.hetbh = '';
		$scope.hetbhList = data;
	});
	
	$scope.searchData = function() {
		if($scope.search.hetbh==null||$scope.search.hetbh==""){
			alert("请选择合同编号！");
			return false;
		}
		$http.post('hetgl/chaxdy/getPingsyjb',{hetb_id:$scope.search.hetbh}).success(function(data) {
			document.getElementById("report").innerHTML = data[0].html;
		});
	}
	$scope.loadHetbh = function(){
		$http.post('hetgl/chaxdy/getHetbh',{riq:$scope.search.riq}).success(function(data) {
			$scope.search.hetbh = '';
			$scope.hetbhList = data;
		});
	}
	$scope.printPage = function() {
		$("#report").jqprint();
	}
});