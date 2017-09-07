gddlapp.controller('diaoyjhCtrl', function($scope,$rootScope,$http,$log,$location) {
	$scope.diaoyjhTitle='调运计划';
	$scope.search = new Object();
	var d = new Date();
	var vYear = d.getFullYear();
	var vMon = d.getMonth() + 1;
	var vDay = d.getDate();
	$scope.search.shij = vYear+"年"+vMon+"月"+vDay+"日";
	$scope.search.riq = vYear+"-"+(vMon<10 ? "0" + vMon : vMon)+"-"+(vDay<10 ? "0" + vDay : vDay);
	$http.post('peimei/getyunsdw',{riq:$scope.search.riq}).success(function(data) {
		$scope.yunsdwlist = data;
	});
	$http.post('peimei/getmeic',{riq:$scope.search.riq}).success(function(data) {
		$scope.meiclist = data;
	});
	$scope.refresh = function(){
//		$scope.search.shij = $scope.search.riq;
		var riqtmp = $scope.search.riq;
		riqtmp = riqtmp.replace("-","年").replace("-","月") + "日";
		$scope.search.shij = riqtmp;
		$http.post('peimei/diaoysearch',{riq:$scope.search.riq}).success(function(data) {
			$scope.items = data;
		});
	}
	$http.post('peimei/diaoysearch',{riq:$scope.search.riq}).success(function(data) {
		$scope.items = data;
	});
	$scope.checkit = function(target){
		$scope.search.checkid = target.getAttribute("id");
	}
	$scope.add = function(){
		var jsons = {"DIANCXXB_ID_CN":"","DIANCXXB_ID":"","CHES":"","DIAOYL":"","YUNSCDB_ID_CN":"","YUNSCDB_ID":"","ID":0};
		var index = $scope.search.checkid;
		var length = Number($scope.items.data[index].rowDetails.length);
		$scope.items.data[index].rowDetails[length] = jsons;
	}
	$scope.save = function(){
		var rebackdata = {"data":[]};
		var index = $scope.search.checkid;
		rebackdata.data[0] = $scope.items.data[index];
		$http.post('peimei/diaoyjhsave',{riq:$scope.search.riq,info:angular.toJson(rebackdata)}).success(function(data) {
			alert(data);
			if(data != ""){
				$http.post('peimei/diaoysearch',{riq:$scope.search.riq}).success(function(data) {
					$scope.items = data;
				});
			}
		});
	}
});