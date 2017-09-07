gddlapp.controller('meiccpCtrl', function($scope,$rootScope,$http,$log,$location) {
	$scope.meiccpTitle='煤场掺配调用单';
	$scope.search = new Object();
	var d = new Date();
	var vYear = d.getFullYear();
	var vMon = d.getMonth() + 1;
	var vDay = d.getDate();
	$scope.search.shij = vYear+"年"+vMon+"月"+vDay+"日";
	$scope.search.riq = vYear+"-"+(vMon<10 ? "0" + vMon : vMon)+"-"+(vDay<10 ? "0" + vDay : vDay);
	$http.post('meiccp/getpeimeidw').success(function(data) {
		$scope.peimdwlist = data;
	});
	$http.post('meiccp/getmeiyuan').success(function(data) {
		$scope.meiyuanlist = data;
	});
	$http.post('meiccp/getmeishan').success(function(data) {
		$scope.xiemmslist = data;
	});
	
	$scope.refresh = function(){
		$http.post('meiccp/loadmeiccp',{riq:$scope.search.riq,peimeidanwid:$scope.search.peimeidanwid}).success(function(data) {
			$scope.items = data;
		});
	}
	$scope.checkit = function(target){
		$scope.search.checkid = target.getAttribute("id");
	}
	$scope.add = function(){
		var index = $scope.search.checkid;
		var length = Number($scope.items.data[index].rowDetails.length);
		var jsons = {"DIANCXXB_ID_CN":"","DIANCXXB_ID":$scope.items.data[index].rowData.ID,"MEIYB_ID_CN":"","MEIYB_ID":"","CHES":"","DAOCLYG":"","SHIJDCL":"","MEISB_ID_CN":"","MEISB_ID":"","RIQ":$scope.search.riq,"ID":0};
		$scope.items.data[index].rowDetails[length] = jsons;
	}
	$scope.save = function(){
		var rebackdata = {"data":[]};
		var index = $scope.search.checkid;
		rebackdata.data[0] = $scope.items.data[index];
		$http.post('meiccp/meiccpsave',{riq:$scope.search.riq,info:angular.toJson(rebackdata)}).success(function(data) {
			alert(data);
			if(data != ""){
				$http.post('meiccp/loadmeiccp',{riq:$scope.search.riq,peimeidanwid:$scope.search.peimeidanwid}).success(function(data) {
					$scope.items = data;
				});
			}
		});
	}
	$scope.delete = function(target, rowID, index){
		var id = target.getAttribute("id");
		var s = true;
		if(id>0){
			$http.post('meiccp/delmeiccp',{id:id}).success(function(data) {
				alert(data);
				if(data=="删除失败！！！"){
					s = false;
				}
			});
		}
		if(s){
			for(var i=0;i<$scope.items.data.length;i++){
				if($scope.items.data[i].rowData.ID==rowID){
					$scope.items.data[i].rowDetails.splice(index,1);
				}
			}
		}
	}
});