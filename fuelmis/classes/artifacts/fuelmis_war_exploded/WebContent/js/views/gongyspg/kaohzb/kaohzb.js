gddlapp.controller('kaohzbCtrl', function($scope,$rootScope,$http,$log,$location) {
	$scope.kaohzbTitle='考核指标维护';
	
	$scope.addKaohzb=function(){
		var flag = "Add";
		$location.path('/addKaohzb/'+flag);
	}
	
	$scope.editKaohzb=function(){
		if($("#example input[type=checkbox]:checked").length<1){
			alert("请选择要修改的行！");
		}else{
			$("#example input[type=checkbox]").each(function(){
			    if(this.checked){
			    	var flag = "Update";
					$location.path('/addKaohzb/'+flag+"/"+$(this).attr("id"));
			    }
			}); 
		}
	}
		
	$scope.delKaohzb=function(){
		if($("#example input[type=checkbox]:checked").length<1){
			alert("请选择要删除的行！")
		}else{
			$('#myModal_Del').modal('show');
		}
	}
})


.controller('kaohzbAddCtrl', function($scope,$rootScope,$http,$location,$routeParams) {	
	$scope.title='';
	$scope.kaohzb = new Object();
	if($routeParams.flag=="Add"){
		$scope.title='新增考核指标信息';
	}else if($routeParams.flag=="Update"){
		$scope.title='修改考核指标信息';
		$http.post('gongyspg/kaohzb/editKaohzb',{id:$routeParams.id}).
			success(function(data, status, headers, config) {
				if(data){
					$scope.kaohzb=data;					
				}
		});
	}
	 
	$scope.saveKaohzb=function(){
		if($scope.kaohzb.id==null){
			$http.post('gongyspg/kaohzb/addKaohzb',{info:angular.toJson($scope.kaohzb)}).
		       success(function(data, status, headers, config) {
		    	   if(data[0]==1){
		    		   alert("新增考核指标信息成功！");
		    		   $location.path('/gongyspg/kaohzb');
		    	   }else{
		    		   alert("新增考核指标信息失败！");
		    		   $location.path('/gongyspg/kaohzb');
		    	   }
		    	   $location.path('/gongyspg/kaohzb');
		   });
		}else{
			$http.post('gongyspg/kaohzb/updateKaohzb',{info:angular.toJson($scope.kaohzb)}).
		       success(function(data, status, headers, config) {
		    	   if(data[0]==1){
		    		   alert("修改考核指标信息成功！");
		    		   $location.path('/gongyspg/kaohzb');
		    	   }else{
		    		   alert("修改考核指标信息失败！");
		    		   $location.path('/gongyspg/kaohzb');
		    	   }
		   });
		}
	}
	 
	$scope.cancel=function(){
		$location.path('/gongyspg/kaohzb');
	};
})
