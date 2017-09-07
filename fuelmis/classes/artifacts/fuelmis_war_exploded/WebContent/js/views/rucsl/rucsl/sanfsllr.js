gddlapp.controller('sanfsllrCtrl', function($scope,$rootScope,$http,$log,$location) {
	//-----------------------------------初始化页面---------------------------------------------
	$scope.search = new Object();
	$scope.search.GONGYSB_ID=-1;

	//------------------------------------------日期定义----------------------------------------------------
	var d = new Date();
	var vYear = d.getFullYear();
	var vMon = d.getMonth() + 1;
	var vDate = d.getDate();
	$scope.search.riq = vYear + "-"+ (vMon < 10 ? "0" + vMon : vMon) + "-"+ (vDate < 10 ? "0" + vDate : vDate);
	//--------------------------------------加载数据--------------------------------------------------------
	
	$scope.load = function() {
		$http({
			method : 'POST',	
			params:{
				riq: $scope.search.riq
			},
			url:'yansgl/rucsl/getGongysList'
		}).success(function(data) {
			$scope.gongysList=data;
		});
		//查数据
		$http({
			method : 'POST',
			params:{
				condition:angular.toJson($scope.search)
			},
			url:'yansgl/rucsl/getChep'
		}).success(function(data) {
			$scope.list=data;
		});
	}
	$scope.load();  
	var s=1;
	$scope.sort=function(target){ 
		var id=$(target).attr("id");
			s*=-1;
			var result;
			$scope.list.sort(function(a,b){
				if(a[id]>b[id]){
					result=1*s;
				}else if(a[id]<b[id]){
					result=-1*s;
				}else{
					result=0;
				}
				return result
			});
	};	
	$scope.save = function() {
			$http.post('yansgl/rucsl/updateChep', {
				data : angular.toJson($scope.list)
			}).success(function(data) {
				alert("保存成功");
				$scope.load(); 
			});
	}
	$scope.focusValue = function(target){
		$(target).addClass("warning") 
	};
	$scope.blurValue = function(target,row){ 	
		row["CHAE"]=(row["JINGZ"]-row["SANFL"]).toFixed(2)
		$(target).removeClass("warning")
	};
	$("#datepicker").datepicker({
		format : 'yyyy-mm-dd',
		minViewMode : 0,
		language : "zh-CN",
		autoclose : true
	});
})
