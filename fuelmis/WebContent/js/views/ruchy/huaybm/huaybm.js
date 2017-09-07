gddlapp.controller('huaybmCtrl', function($scope,$rootScope,$http,$log,$location) {
	//-----------------------------------初始化页面---------------------------------------------
	$scope.parameter = new Object();
	$scope.parameter.height=28;
	$scope.parameter.dimension=0.5;
	$scope.parameter.showText="true";
	$scope.port="COM3";
	//------------------------------------加载数据--------------------------------------------------------
	$scope.scan = function() {
		$("#scan").removeClass("btn-primary");
		$("#scan").attr('disabled', true);
		$http.post('ruchy/huaysh/startScan').
		success(function(data, status, headers, config) {
			//$scope.img=data;
		});
	}
	$scope.load = function() {
		$("#scan").removeClass("btn-primary");
		$("#scan").attr('disabled', true);
		$scope.img="ruchy/huaysh/overScan?rnd="+ Math.random() ;
		$scope.hauybmBar="ruchy/huaysh/getHuaybmBar?rnd="+ Math.random()+"&parameter="+angular.toJson($scope.parameter);
		$http.post('ruchy/huaysh/getPorts').
		success(function(data, status, headers, config) {
			$scope.list=data;
		});
	}
	$scope.load();
	$scope.printPage = function() {
		$("#report").jqprint();
	}
	
})
