gddlapp.controller('pingffaxzbCtrl', function($scope,$rootScope,$http,$log,$location) {
	$scope.pingffaxzbTitle='评分方案细则维护';
	
	$scope.addPingffaxzb=function(){
		var flag = "Add";
		$location.path('/addPingffaxzb/'+flag);
	}
	
	$scope.editPingffaxzb=function(){
		if($("#example input[type=checkbox]:checked").length<1){
			alert("请选择要修改的行！");
		}else{
			$("#example input[type=checkbox]").each(function(){
			    if(this.checked){
			    	var flag = "Update";
					$location.path('/addPingffaxzb/'+flag+"/"+$(this).attr("id"));
			    }
			}); 
		}
	}
		
	$scope.delPingffaxzb=function(){
		if($("#example input[type=checkbox]:checked").length<1){
			alert("请选择要删除的行！")
		}else{
			$('#myModal_Del').modal('show');
		}
	}
})


.controller('pingffaxzbAddCtrl', function($scope,$rootScope,$http,$location,$routeParams) {	
	$scope.pingffaxzbTitle='';
	$scope.pingffaxzb = new Object();
	
	$http.post('common/getComboPingffa').
	success(function(data, status, headers, config) {
		 $scope.pingffaList = data;
	});
	
	$http.post('common/getAllKaohzb').
	success(function(data, status, headers, config) {
		$scope.kaohzbList=data
	});	
	
	if($routeParams.flag=="Add"){
		$scope.pingffaxzbTitle='新增评分方案细则信息';
	}else if($routeParams.flag=="Update"){
		$scope.pingffaxzbTitle='修改评分方案细则信息';
		$http.post('gongyspg/pingffaxzb/editPingffaxzb',{id:$routeParams.id}).
			success(function(data, status, headers, config) {
				if(data){
					$scope.pingffaxzb=data;					
				}
		});
	}
	 
	$scope.savePingffaxzb = function(){
		if($scope.pingffaxzb.id==null){
			$http.post('gongyspg/pingffaxzb/addPingffaxzb',{info:angular.toJson($scope.pingffaxzb)}).
		       success(function(data, status, headers, config) {
		    	   if(data[0]==1){
		    		   alert("新增评分方案细则信息成功！");
		    		   $location.path('/gongyspg/pingffaxzb');
		    	   }else{
		    		   alert("新增评分方案细则信息失败！");
		    		   $location.path('/gongyspg/pingffaxzb');
		    	   }
		    	   $location.path('/gongyspg/pingffaxzb');
		   });
		}else{
			$http.post('gongyspg/pingffaxzb/updatePingffaxzb',{info:angular.toJson($scope.pingffaxzb)}).
		       success(function(data, status, headers, config) {
		    	   if(data[0]==1){
		    		   alert("修改评分方案细则信息成功！");
		    		   $location.path('/gongyspg/pingffaxzb');
		    	   }else{
		    		   alert("修改评分方案细则信息失败！");
		    		   $location.path('/gongyspg/pingffaxzb');
		    	   }
		   });
		}
	}
	 
	$scope.cancel=function(){
		 $location.path('/gongyspg/pingffaxzb');
	};
})
