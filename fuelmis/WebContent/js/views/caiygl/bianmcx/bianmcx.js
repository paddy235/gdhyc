gddlapp.controller('bianmcxCtrl', function($scope,$rootScope,$http,$log,$location) {
	$scope.bianmcxTitle='编码查询';
	$scope.search = new Object();
	// ------------------------------------------日期定义-------------------------------------------
	var d = new Date();
	var vYear = d.getFullYear();
	var vMon = d.getMonth() + 1;
	var vDate = d.getDate();
//	$scope.search.qisrq = vYear + "-" + (vMon < 10 ? "0" + vMon : vMon) + "-01";
	$scope.search.date = vYear + "-" + (vMon < 10 ? "0" + vMon : vMon) + "-" + (vDate < 10 ? "0" + vDate : vDate);
	$("#datepicker1").datepicker({
		format : 'yyyy-mm-dd',
		minViewMode : 0,
		language : "zh-CN",
		autoclose : true
	});
	$http.post('caiygl/bianmcx/getTableInfo').success(function(data) {
		document.getElementById("report").innerHTML = data;
	});
	
	$scope.refresh = function(){								
		$http.post('caiygl/bianmcx/getTableInfo',{date:$scope.search.date}).success(function(data){
			document.getElementById("report").innerHTML = data;
		});			
	}
	
	$scope.printPage = function() {
		$("#report").jqprint();
	}
	

});