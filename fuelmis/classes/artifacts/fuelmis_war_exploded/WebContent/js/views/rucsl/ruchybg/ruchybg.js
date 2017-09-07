gddlapp.controller('ruchybgCtrl', function($scope,$rootScope,$http,$log,$location) {
	$scope.ruchybgTitle='化验报告';
	var date = new Date();
	var year = date.getFullYear();
	var month = date.getMonth()+1<10?'0'+(date.getMonth()+1):date.getMonth()+1;
	var day = date.getDate()+1<10?'0'+(date.getDate()):date.getDate();
	
	$scope.search = {
			diancid : 515,
			riq : year+'-'+month+'-'+day,
			sDate : year+'-'+month+'-01',
			eDate : year+'-'+month+'-'+day,
			huaybh:-1
		};
	
	$scope.selJiesbh = function(){
		var sDate = $scope.search.sDate;
		var eDate = $scope.search.eDate;
		$http.post('ruchybg/getHuaybh',{sDate:$scope.search.sDate,eDate:$scope.search.eDate}).success(function(data) {
			$scope.huaybhList=data;
		});
	}
	$scope.selJiesbh();
	$scope.searchData = function() {
		$scope.selJiesbh();
		var huaybh = $scope.search.huaybh;
		
		if(huaybh==null||huaybh==""){
			alert("请选择化验编号！");
			return false;
		}
		$http.post('ruchybg/getAllHuaybg',{huaybh:huaybh,dianc:$scope.search.diancid}).success(function(data) {
			document.getElementById("report").innerHTML = data[0].html;
			$("#reportpage1 table:eq(0)").css(
					"width","85%"
			);
		});
	}
	$scope.searchData();
		
	$scope.printPage = function() {
		$("#report").jqprint();
	}
	

});