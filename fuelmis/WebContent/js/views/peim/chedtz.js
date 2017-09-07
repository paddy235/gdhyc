gddlapp.controller('chedtzCtrl', function($scope,$rootScope,$http,$log,$location) {
	$scope.chedtzTitle='车队通知';
	$scope.search = new Object();
	var d = new Date();
	var vYear = d.getFullYear();
	var vMon = d.getMonth() + 1;
	var vDay = d.getDate();
	$scope.search.shij = vYear+"年"+vMon+"月"+vDay+"日";
	$scope.search.riq = vYear+"-"+(vMon<10 ? "0" + vMon : vMon)+"-"+(vDay<10 ? "0" + vDay : vDay);
	$scope.refresh = function(){
		var riqtmp = $scope.search.riq;
		riqtmp = riqtmp.replace("-","年").replace("-","月") + "日";
		$scope.search.shij = riqtmp;
		$http.post('Cheddy/loadcheddytz',{riq:$scope.search.riq,yunscd_id:$scope.search.peimeidanwid}).success(function(data) {
			$scope.items = data;
		});
	}
//	{"data":[
//	         {"rowData":{"YUNSCDB_ID":1442214926550000,"CHEDMC":"额外人"},
//	        	 "rowDetails":[{"ID":1442233251053000,"CHEDMC":"额外人","MEIYMC":"同煤","CHES":15,"MINGC":"国电","BEIZ":null},{"ID":1442232352297001,"CHEDMC":"额外人","MEIYMC":"同煤","CHES":44,"MINGC":"红雁池","BEIZ":null}]}]}

	$http.post('Cheddy/getyunsdanw').success(function(data){
		$scope.YunscdComboList=data;
	});
	$scope.save = function(){
		$http.post('Cheddy/Cheddysave',{info:angular.toJson($scope.items)}).success(function(data) {
			alert(data);
			if(data != ""){
				$http.post('Cheddy/loadcheddytz',{riq:$scope.search.riq,yunscd_id:$scope.search.peimeidanwid}).success(function(data) {
					$scope.items = data;
				});
			}
		});
	}
	$scope.selectYunscd = function(){
		$http.post('Cheddy/loadcheddytz',{riq:$scope.search.riq,yunscd_id:$scope.search.peimeidanwid}).success(function(data){
			$scope.MeishanList=data;
		});
	}
});