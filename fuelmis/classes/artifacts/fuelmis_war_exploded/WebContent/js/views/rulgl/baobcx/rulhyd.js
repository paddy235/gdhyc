gddlapp.controller('rulhydCtrl', function($scope,$rootScope,$http,$log,$location) {
	$scope.rulhydTitle='化验报告单';
	var date = new Date();
	var year = date.getFullYear();
	var month = date.getMonth()+1<10?'0'+(date.getMonth()+1):date.getMonth()+1;
	var day = date.getDate()+1<10?'0'+(date.getDate()):date.getDate();
	
	$scope.search = {
			diancid : 515,
			riq : year+'-'+month+'-'+day,
			banz : $scope.rulbzList[0].value,
			jiz : $scope.ruljzList[0].value
		};
	
	$http.post('rulgl/baobcx/getRulhyd').success(function(data) {
		document.getElementById("report").innerHTML = data[0].html;
	});

	
	
	$scope.searchData = function() {
		$http.post('rulgl/baobcx/getRulhyd',
				{riq:$scope.search.riq,dianc:$scope.search.diancid,rulbz:$scope.search.banz,jiz:$scope.search.jiz}
			).success(function(data) {
				document.getElementById("report").innerHTML = data[0].html;
		});
	}
	
	$scope.printPage = function() {
		$("#report").jqprint();
	}
	

});