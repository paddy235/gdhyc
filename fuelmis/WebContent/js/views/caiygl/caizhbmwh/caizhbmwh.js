gddlapp.controller('caizhbmwhCtrl', function($scope, $rootScope, $routeParams, $http, $location) {
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
	$scope.search.riq = vYear + "-" + (vMon < 10 ? "0" + vMon : vMon) + "-" + (vDate < 10 ? "0" + vDate : vDate);
	$("#datepicker").datepicker({
		format : 'yyyy-mm-dd',
		minViewMode : 0,
		language : "zh-CN",
		autoclose : true
	});

	$scope.load = function() {
		$http.post('caiygl/caizhbmwh/getBianm', {
			diancid : $scope.diancid,
			riq : $scope.search.riq,
		}).success(function(data) {
			$scope.list = data;
		});
	}

	$scope.load();
	$scope.checkEmpty = function() {
		//console.log("into check empty!");
		//制样编码化验编码不能为空
		isEmpty=false;
		//console.log("ok");
		$(".zhiybm").each(function(){
			if($(this).val().trim()==""){
				isEmpty=true;
				return false;
			}
		})
		$(".huaybm").each(function(){
			if($(this).val().trim()==""){
				isEmpty=false;
				return false;
			}
		})
		return isEmpty;
	}
	$scope.checkRepeat=function(target){
		//console.log("into check repeat!");
		$(target).css('color', '#000000');
		var isRepeat = false;
		var dates=$(target);
		var n = dates.length;
		var tempExchangVal;
		for(var i=0;i<n;i++){
			for (j = 0; j < n-i-1; j++) {
				if (($(dates[j]).val()).trim() >($(dates[j+1]).val()).trim()) {
					tempExchangVal = dates[j];
					dates[j] = dates[j + 1];
					dates[j + 1] = tempExchangVal;
				}
			}
		}
		for (var i = 0; i < n-1; i++) {
			if (($(dates[i]).val()).trim() == ($(dates[i+1]).val()).trim()) {
				$(dates[i]).css('color', '#ff0000');
				$(dates[i + 1]).css('color', '#ff0000');
				isRepeat = true;
			} else {
				//$(dates[i + 1]).css('color', '#000000');	
			}
		}
		return isRepeat;
	}
	$scope.check=function(target){
		flag=true;
		if($scope.checkEmpty()==true){
			alert("编码不能为空!");
			flag=false;
			$("#save").removeClass("btn-success");
			$("#save").attr('disabled', true);
		}else if($scope.checkRepeat(target)==true){
			alert("含有重复项!");	
			flag=false;
			$("#save").removeClass("btn-success");
			$("#save").attr('disabled', true);
		}else{
			$("#save").addClass("btn-success");
			$("#save").attr('disabled', false);
		}
		return flag;
	}
	$scope.save = function() {
		if($scope.check(".zhiybm")==true&&$scope.check(".huaybm")==true){
			$http.post('caiygl/caizhbmwh/updateBianm', {
				data : angular.toJson($scope.list)
			}).success(function(data) {
				alert("保存成功");
				$scope.load();
			});
		}
	}
	$scope.refresh=function(){
		$scope.load();
	}
	$scope.generate=function(){
		$("#save").addClass("btn-success");
		$("#save").attr('disabled', false);
		$http.post('caiygl/caizhbmwh/generateBianm', {
			data : angular.toJson($scope.list),
			riq:$scope.search.riq
		}).success(function(data) {
			$scope.list = data;
			$("#save").addClass("btn-success");
			$("#save").attr('disabled', false);
		});
	}
});