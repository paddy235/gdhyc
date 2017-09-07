gddlapp.controller('pingffaCtrl', function($scope,$rootScope,$http,$log,$location) {
	$scope.pingffaTitle='评分方案维护';
	
	$scope.addPingffa=function(){
		var flag = "Add";
		$location.path('/addPingffa/'+flag);
	}
	
	$scope.editPingffa=function(){
		if($("#example input[type=checkbox]:checked").length<1){
			alert("请选择要修改的行！");
		}else{
			$("#example input[type=checkbox]").each(function(){
			    if(this.checked){
			    	var flag = "Update";
					$location.path('/addPingffa/'+flag+"/"+$(this).attr("id"));
			    }
			}); 
		}
	}
		
	$scope.delPingffa=function(){
		if($("#example input[type=checkbox]:checked").length<1){
			alert("请选择要删除的行！")
		}else{
			$('#myModal_Del').modal('show');
		}
	}
})


.controller('pingffaAddCtrl', function($scope,$rootScope,$http,$location,$routeParams) {	
	$scope.title='';
	$scope.pingffa = new Object();
	
	
	if($routeParams.flag=="Add"){
		$scope.title = '新增评分方案信息';
	}else if($routeParams.flag=="Update"){
		$scope.title ='修改评分方案信息';
		$http.post('gongyspg/pingffa/editPingffa',{id:$routeParams.id}).
			success(function(data, status, headers, config) {
				if(data){
					$scope.pingffa=data;
				}
		});
	}
	 
	$scope.savePingffa = function(){
		if($scope.pingffa.id==null){
			$http.post('gongyspg/pingffa/addPingffa',{info:angular.toJson($scope.pingffa)}).
		       success(function(data, status, headers, config) {
		    	   if(data[0]==1){
		    		   alert("新增评分方案信息成功！");
		    		   $location.path('/gongyspg/pingffa');
		    	   }else{
		    		   alert("新增评分方案信息失败！");
		    		   $location.path('/gongyspg/pingffa');
		    	   }
		    	   $location.path('/gongyspg/pingffa');
		   });
		}else{
			$http.post('gongyspg/pingffa/updatePingffa',{info:angular.toJson($scope.pingffa)}).
		       success(function(data, status, headers, config) {
		    	   if(data[0]==1){
		    		   alert("修改评分方案信息成功！");
		    		   $location.path('/gongyspg/pingffa');
		    	   }else{
		    		   alert("修改评分方案信息失败！");
		    		   $location.path('/gongyspg/pingffa');
		    	   }
		   });
		}
	}
	 
	$scope.cancel=function(){
		$location.path('/gongyspg/pingffa');
	};
})
