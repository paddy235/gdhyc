gddlapp.controller('niandjhtjCtrl', function($scope,$rootScope,$http,$log,$location) {
	$scope.niandjhtjTitle='年度计划提交';
	$scope.search = new Object();
	var d = new Date();
	var vYear = d.getFullYear();
	$scope.search.year = vYear;
	$scope.search.diancid = 515;
	$http.post('niandjh/niandjhrefresh',{year:$scope.search.year,diancid:$scope.search.diancid}).success(function(data) {
		document.getElementById("report").innerHTML = data;
	});
	$http.post('niandjh/getShenpState',{year:$scope.search.year,diancid:$scope.search.diancid}).success(function(data) {
		if(data=="1"||data=="2"){
			$("#niandjhtj").removeClass("btn-primary");
			$("#niandjhtj").attr('disabled',true);
		}
	});
	$scope.refresh = function(){
		$http.post('niandjh/niandjhrefresh',{year:$scope.search.year,diancid:$scope.search.diancid}).success(function(data){
			document.getElementById("report").innerHTML = data;
		});
		$http.post('niandjh/getShenpState',{year:$scope.search.year,diancid:$scope.search.diancid}).success(function(data) {
			if(data=="1"||data=="2"){
				$("#niandjhtj").removeClass("btn-primary");
				$("#niandjhtj").attr('disabled',true);
			}else{
				$("#niandjhtj").addClass("btn-primary");
				$("#niandjhtj").removeAttr("disabled");
			}
		});
	}
	$scope.submit = function(){
		$scope.isDisplay=true;
		$('#myModal_Edit').modal('show');
		$http.post('niandjh/niandjhtj',{year:$scope.search.year,diancid:$scope.search.diancid}).success(function(data){
			$scope.isDisplay=false;
			if(data=="流程启动成功!"){
				alert("提交成功!");
				$("#niandjhtj").removeClass("btn-primary");
				$("#niandjhtj").attr('disabled',true);
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