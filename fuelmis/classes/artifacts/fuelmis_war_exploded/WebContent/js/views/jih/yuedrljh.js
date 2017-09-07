gddlapp.controller('yuedrljhCtrl', function($scope,$rootScope,$http,$log,$location) {
	$scope.yuedrljhTitle='月度入炉计划查询';
	$scope.search = new Object();
	$scope.search.diancid = 515;
	var d = new Date();
	var vYear = d.getFullYear();
	var vMon = d.getMonth() + 1;
	$scope.search.riq = vYear+"-"+(vMon<10 ? "0" + vMon : vMon);
	$http.post('yuedranlzfjh/yuedranlzfjhcx',{riq:$scope.search.riq,diancid:$scope.search.diancid}).success(function(data) {
		document.getElementById("report").innerHTML = data;
	});
	$scope.refresh = function(){
		$http.post('yuedranlzfjh/yuedranlzfjhcx',{riq:$scope.search.riq,diancid:$scope.search.diancid}).success(function(data){
			document.getElementById("report").innerHTML = data;
		});
	}
	
	$scope.printPage = function() {
		$("#report").jqprint();
	}
	

});