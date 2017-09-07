gddlapp.controller('yuedjhtjCtrl', function($scope,$rootScope,$http,$log,$location) {
	$scope.yuedjhtjTitle='月度计划提交';
	$scope.search = new Object();
	$scope.search.diancid = 515;
	var d = new Date();
	var vYear = d.getFullYear();
	var vMon = d.getMonth() + 1;
	$scope.search.riq = vYear+"-"+(vMon<10 ? "0" + vMon : vMon);
	$http.post('yuedjh/yuedjhrefresh',{riq:$scope.search.riq,diancid:$scope.search.diancid}).success(function(data) {
		document.getElementById("report").innerHTML = data;
	});
	$http.post('yuedjh/getShenpState',{riq:$scope.search.riq,diancid:$scope.search.diancid}).success(function(data) {
		if(data=="1"||data=="2"){
			$("#yuedjhtj").removeClass("btn-primary");
			$("#yuedjhtj").attr('disabled',true);
		}
	});
	$scope.refresh = function(){
		$http.post('yuedjh/yuedjhrefresh',{riq:$scope.search.riq,diancid:$scope.search.diancid}).success(function(data){
			document.getElementById("report").innerHTML = data;
		});
		$http.post('yuedjh/getShenpState',{riq:$scope.search.riq,diancid:$scope.search.diancid}).success(function(data) {
			if(data=="1"||data=="2"){
				$("#yuedjhtj").removeClass("btn-primary");
				$("#yuedjhtj").attr('disabled',true);
			}else{
				$("#yuedjhtj").addClass("btn-primary");
				$("#yuedjhtj").removeAttr("disabled");
			}
		});
	}
	$scope.submit = function(){
		$scope.isDisplay=true;
		$http.post('yuedjh/yuedjhtj',{riq:$scope.search.riq,diancid:$scope.search.diancid}).success(function(data){
			$scope.isDisplay=false;
			if(data="流程启动成功!"){
				alert("提交成功!");
				$("#yuedjhtj").removeClass("btn-primary");
				$("#yuedjhtj").attr('disabled',true);
			}
			alert(data);
		}).error(function(){
			$scope.isDisplay=false;
			alert("提交失败!");
		});
		
	}
	$scope.printPage = function() {
		$("#report").jqprint();
	}
	

});