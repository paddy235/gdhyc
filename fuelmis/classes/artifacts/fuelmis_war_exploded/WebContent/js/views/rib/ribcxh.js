gddlapp.controller('ribcxCtrl', function($scope,$rootScope,$http,$log,$location) {
	$scope.ribcxTitle='日报查询';
	$scope.search = new Object();
	$scope.baobleixlist =[{ "name":"按单位汇总", "value":1},{ "name":"按煤源地区汇总",  "value":2},{ "name":"周报",  "value":3},{ "name":"日平均库存",  "value":4}];
	$scope.search.baobleix = 1;
	$scope.search.diancid = 515;
	var d = new Date();
	var vYear = d.getFullYear();
	var vMon = d.getMonth() + 1;
	var vDay = d.getDate();
	var kaisriq = vYear+"-"+(vMon<10 ? "0" + vMon : vMon)+"-01";
	var jiesriq = vYear+"-"+(vMon<10 ? "0" + vMon : vMon)+"-"+(vDay<10 ? "0" + vDay : vDay);
	$scope.search.kaisriq = kaisriq;
	$scope.search.jiezriq = jiesriq;
	$http.post('rib/ribcx',{kaisriq:$scope.search.kaisriq,jiezriq:$scope.search.jiezriq,diancid:$scope.search.diancid,baobleix:$scope.search.baobleix}).success(function(data) {
		document.getElementById("report").innerHTML = data;
	});
	$scope.refresh = function(){
		$http.post('rib/ribcx',{kaisriq:$scope.search.kaisriq,jiezriq:$scope.search.jiezriq,diancid:$scope.search.diancid,baobleix:$scope.search.baobleix}).success(function(data){
			document.getElementById("report").innerHTML = data;
		});
	}
	
	$scope.printPage = function() {
		$("#report").jqprint();
	}
	

});