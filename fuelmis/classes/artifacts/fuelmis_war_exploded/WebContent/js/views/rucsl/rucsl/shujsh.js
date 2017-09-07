gddlapp.controller('shujshCtrl', function($scope,$rootScope,$http,$log,$location,$routeParams) {
	$scope.shujshTitle='数据审核';
	$scope.zhuangtList = [{"name":"未审核","value":0},{"name":"已审核","value":3}];
	//--------------------------------------日期定义--------------------------------------------------------------------------
	var begin=new Date();	
	var sDate= begin.format("yyyy-MM"+"-01");
	var end=new Date();
	new Date(end.setDate((new Date().getDate()-1)));
	var eDate=end.format("yyyy-MM-dd");
	$scope.search = {
		zhuangt : $routeParams.zhuangt==undefined?0:$routeParams.zhuangt,
		sDate : sDate,
		eDate : eDate,
		sSearch : ''
	}

	$http.post('rucsl/shulsh/getTableData?zhuangt='+$scope.search.zhuangt+'&sDate='+$scope.search.sDate+'&eDate='+$scope.search.eDate).
		success(function(data, status, headers, config) {
			$scope.dataList=data.data
	});
	
	$scope.searchData = function() {
		$http.post('rucsl/shulsh/getTableData?zhuangt='+$scope.search.zhuangt+'&sDate='+$scope.search.sDate+'&eDate='+$scope.search.eDate).
			success(function(data, status, headers, config) {
				$scope.dataList=data.data
		});
	};
	
	$scope.shenh=function(samcode){
		$scope.display=true;
		$http.post('rucsl/shulsh/shenh?id='+samcode).
	 		success(function(data, status, headers, config) {
	 		 if(data[0]>0){
	    		   var zhuangt = $scope.search.zhuangt;
	    		   var sDate = $scope.search.sDate;
	    		   var eDate = $scope.search.eDate;
	    		   $http.post('rucsl/shulsh/getTableData?zhuangt='+zhuangt+'&sDate='+sDate+'&eDate='+eDate).
	    				success(function(data, status, headers, config) {
	    					$scope.dataList=data.data
					   		$scope.display=false;
	    					alert("审核成功！");	
	    		   });
	    	   }else{
				 $scope.display=false;
	    		   alert("审核失败！");
	    	   }
	 	});
	};
	
	$scope.huit=function(samcode){
		//查出月结单编号
		$http.post('rucsl/shulsh/getJiesxxList',{samcode:samcode}).success(function(data) {
			jiesxxList=data;
			if(jiesxxList[0].YUEJSDBM==undefined){
				$http.post('jiesgl/rijssc/delRijsd', {//删除日结算单
					list : angular.toJson(jiesxxList)
				}).success(function(data) {
					$http.post('rucsl/shulsh/huit?id='+samcode).
					success(function(data, status, headers, config) {
						if(data[0]>0){
							alert("取消成功！");
							var zhuangt = $scope.search.zhuangt;
							var sDate = $scope.search.sDate;
							var eDate = $scope.search.eDate;
							$http.post('rucsl/shulsh/getTableData?zhuangt='+zhuangt+'&sDate='+sDate+'&eDate='+eDate).
							success(function(data, status, headers, config) {
								$scope.dataList=data.data
							});
						}else{
							alert("取消失败！");
						}
					});
				});
			}else {
				alert("已生成月结算单，不允许取消，结算单编号:" + jiesxxList[0].YUEJSDBM)
			}
		})
		
	};
	
	$scope.chakmx=function(samcode){
		$location.path('/shujsh_mx/'+samcode+'/'+$scope.search.zhuangt+'/'+$scope.search.sDate+'/'+$scope.search.eDate);
	}
})
.controller('shujsh_mxCtrl', function($scope,$rootScope,$http,$location,$routeParams) {
	$scope.shujsh_mxTitle='数据审核';
	
	oTable.fnReloadAjax('rucsl/shulsh/getTableData_MX?id='+$routeParams.id);
	 
	$scope.cancel=function(){
		$location.path('/shujsh/'+$routeParams.zhuangt+'/'+$routeParams.sDate+'/'+$routeParams.eDate);
	};
})