gddlapp.controller('diaodjhInfoCtrl', function($scope,$rootScope,$http,$location,$routeParams) {	
	$scope.search = new Object();
	$scope.search.diancid=515;	
	var date = new Date();
	$scope.search.strdate = timeStamp2String(2);
	$scope.search.enddate = timeStamp2String(1);
	$scope.title = "调度计划";
	
	
	$scope.searchData = function() {
		var diancid = $scope.search.diancid;
		var strdate = $scope.search.strdate;
		var enddate = $scope.search.enddate;
		oTable.fnReloadAjax('diaodjh/getDiaodjhinfoList/?diancid='+diancid+'&strdate='+strdate+'&enddate='+enddate);
	}
	
		
	function timeStamp2String(type){
	    var datetime = new Date();
	    var year = datetime.getFullYear();
	    var month = datetime.getMonth() + 1 < 10 ? "0" + (datetime.getMonth() + 1) : datetime.getMonth() + 1;
	    var date = datetime.getDate() < 10 ? "0" + datetime.getDate() : datetime.getDate();
	    var hour = datetime.getHours()< 10 ? "0" + datetime.getHours() : datetime.getHours();
	    var minute = datetime.getMinutes()< 10 ? "0" + datetime.getMinutes() : datetime.getMinutes();
	    var second = datetime.getSeconds()< 10 ? "0" + datetime.getSeconds() : datetime.getSeconds();
	    if(type == 1){
	    	return year + "-" + month + "-" + date;
	    }else{
	    	return year + "-" + month + "-"+"01"
	    }
	    
	}
	
})

.controller('diaodjhAddCtrl', function($scope,$rootScope,$http,$location,$routeParams) {	
	$scope.diaodjhTitle='';
	$scope.diaodjh = new Object();
	if($routeParams.flag=="Add"){
		$scope.diaodjhTitle='新增调度计划信息';
		$scope.diaodjh.zhuangt = 1;
	}else if($routeParams.flag=="Update"){
		$scope.diaodjhTitle='修改调度计划信息';
		$http.post('diaodjh/editDiaodjh',{id:$routeParams.id}).
			success(function(data, status, headers, config) {
				if(data){
					$scope.diaodjh=data;
				}
		});
	}
	 
	$scope.saveDiaodjh=function(){
		if($scope.diaodjh.id==null){
			$http.post('diaodjh/addDiaodjh',{info:angular.toJson($scope.diaodjh)}).
		       success(function(data, status, headers, config) {
		    	   if(data[0]==1){
		    		   alert("新增调度计划信息成功！");
		    		   $location.path('/diaodjh');
		    	   }else{
		    		   alert("新增调度计划信息失败！");
		    		   $location.path('/diaodjh');
		    	   }
		    	   $location.path('diaodjh');
		   });
		}else{
			$http.post('diaodjh/updateDiaodjh',{info:angular.toJson($scope.diaodjh)}).
		       success(function(data, status, headers, config) {
		    	   if(data[0]==1){
		    		   alert("修改调度计划信息成功！");
		    		   $location.path('/diaodjh');
		    	   }else{
		    		   alert("修改调度计划信息失败！");
		    		   $location.path('/diaodjh');
		    	   }
		   });
		}
	}
	 
	$scope.cancel=function(){
		$location.path('diaodjh');
	};
})
