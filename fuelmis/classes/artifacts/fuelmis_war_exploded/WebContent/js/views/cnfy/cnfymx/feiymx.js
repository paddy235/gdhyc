gddlapp.controller('cnfymxCtrl', function($scope,$rootScope,$http,$log,$location) {
	$scope.cnfymxTitle='厂内费用明细查询';
	$scope.search = new Object();
	$scope.search.diancid = 515;
	var d = new Date();
	var vYear = d.getFullYear();
	var vMon = d.getMonth() + 1;
	$scope.search.riq = vYear+"-"+(vMon<10 ? "0" + vMon : vMon);
	$http.post('cnfymx/cnfymxcx',{riq:$scope.search.riq,diancid:$scope.search.diancid}).success(function(data) {
		document.getElementById("report").innerHTML = data;
	});
	$scope.refresh = function(){
		$http.post('cnfymx/cnfymxcx',{riq:$scope.search.riq,diancid:$scope.search.diancid}).success(function(data){
			document.getElementById("report").innerHTML = data;
		});
	}
	$scope.select = function() {
	}
	$scope.printPage = function() {
		$("#report").jqprint();
	}
	$('a').live('click',function(){
		var id = $(this).attr("id");
		var flag = $(this).attr("name")
		if(flag=="feiybxd"){
			$http.post('cnfymx/cnfymxcx',{riq:$scope.search.riq,diancid:$scope.search.diancid}).success(function(data){
				$location.path('/feiybxddisplay/'+id); 
			});
		}
	});
	

});