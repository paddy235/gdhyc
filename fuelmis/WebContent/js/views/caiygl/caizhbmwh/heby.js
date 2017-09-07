gddlapp.controller('hebyCtrl', function($scope, $rootScope, $routeParams, $http, $location) {
	$scope.search = new Object();
	$scope.search.meikxxbid = -1;
	$scope.array = new Array(); 
	$scope.diancid = 515;
	$("#save").removeClass("btn-success");
	$("#save").attr('disabled', true);
	// ------------------------------------------日期定义-------------------------------------------
	var d = new Date();
	var vYear = d.getFullYear();
	var vMon = d.getMonth() + 1;
	var vDate = d.getDate();
	$scope.riq = vYear + "-" + (vMon < 10 ? "0" + vMon : vMon) + "-" + (vDate < 10 ? "0" + vDate : vDate);
	$("#datepicker").datepicker({
		format : 'yyyy-mm-dd',
		minViewMode : 0,
		language : "zh-CN",
		autoclose : true
	});

	$scope.load = function() {
		$http.post('caiygl/heby/getSamcodeList', {
			riq : $scope.riq
		}).success(function(data) {
			$scope.samcodeList = data;
			if(data.length!=0){
				$scope.caiybh = data[0].VALUE;
			}
			$http.post('caiygl/heby/getList', {
				riq : $scope.riq
			}).success(function(data) {
				$scope.list = data;
			});
		});

	}
	$scope.load();

	$scope.converge = function() {
		var j=0
		$scope.newList=[]
		for(var i=0;i<$scope.list.length;i++){
			if($scope.list[i].check==true){
				$scope.list[i].SAMCODE=$scope.caiybh;
				$scope.newList[j++]=$scope.list[i]
			}
		}
		$http.post('caiygl/heby/updateList', {
			data : angular.toJson($scope.newList)
		}).success(function(data) {
			alert("合并成功！");
			$scope.load();
		});
	}
	$scope.cancel = function() {
		var j=0
		$scope.newList=[] 
		for(var i=0;i<$scope.list.length;i++){
			if($scope.list[i].check==true){
				$scope.newList[j]=$scope.list[i];
			}
		}
		if($scope.newList.length!=0){
			$http.post('caiygl/heby/updateCancelList', {
				data : angular.toJson($scope.newList)
			}).success(function(data) {
				alert("撤销成功！");
				$scope.load();
			});
		}else{
			alert("请选择至少一行！");
		}
	};
	$("#datepicker").datepicker({
		format : 'yyyy-mm-dd',
		minViewMode : 0,
		language : "zh-CN",
		autoclose : true
	});
});