gddlapp.controller('yuedcaigjhCtrl', function($scope,$rootScope,$http,$log,$location) {
	$scope.yuedcaigjhTitle='月度采购计划查询';
	$scope.search = new Object();
	var d = new Date();
	var vYear = d.getFullYear();
	var vMon = d.getMonth() + 1;
	$scope.search.riq = vYear+"-"+(vMon<10 ? "0" + vMon : vMon);
	$scope.search.diancid = 515;
	$http.post('yuedcaigjh/yuedcaigjhcx',{riq:$scope.search.riq,diancid:$scope.search.diancid}).success(function(data) {
		document.getElementById("report").innerHTML = data;
	});
	$scope.refresh = function(){
		$http.post('yuedcaigjh/yuedcaigjhcx',{riq:$scope.search.riq,diancid:$scope.search.diancid}).success(function(data){
			document.getElementById("report").innerHTML = data;
		});
	}
	
	$scope.printPage = function() {
		$("#report").jqprint();
	}
	

});