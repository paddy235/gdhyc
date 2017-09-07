gddlapp.controller('licCtrl', function($scope,$rootScope,$http,$log,$location) {
	$scope.licTitle='里程维护';
	
	$scope.search = {
		diancid : 515
	}
	
	$scope.searchData = function() {
		var diancid = $scope.search.diancid;
		oTable.fnReloadAjax('lic/getAll/?dianc='+diancid);
	}
	
	$scope.addLic=function(){
		var flag = "Add";
		$location.path('/modifyLic/'+flag);
	}
	
	$scope.editLic=function(){
		if($("#example input[type=checkbox]:checked").length<1){
			alert("请选择要修改的行！")
		}else{
			$("#example input[type=checkbox]").each(function(){
			    if(this.checked){
			    	var flag = "Update";
					$location.path('/modifyLic/'+flag+"/"+$(this).attr("id"));
			    }
			}); 
		}
	}
	
	$scope.delLic=function(){
		if($("#example input[type=checkbox]:checked").length<1){
			alert("请选择要删除的行！")
		}else{
			$('#myModal_Del').modal('show');
		}
	}
})
.controller('licModifyCtrl', function($scope,$rootScope,$http,$location,$routeParams) {
	$scope.modifyLicTitle='';
	
	$scope.lic = new Object();
	
	$scope.lic.faz_id = null;
	$scope.lic.daoz_id = null;
	$scope.lic.liclxb_id = null;
	$scope.lic.zhi = null;
	$scope.lic.meikxxb_id = null;
	
	if($routeParams.flag=="Add"){
		$scope.modifyLicTitle='新增里程信息';
	}else if($routeParams.flag=="Update"){
		$scope.modifyLicTitle='修改里程信息';
		$http.post('lic/getOne',{id:$routeParams.id}).
			success(function(data, status, headers, config) {
				if(data){
					$scope.lic=data[0];
					
					for(var i = 0;i < $scope.chezList.length;i++){
						if($scope.chezList[i].value==$scope.lic.faz_id){
							$scope.lic.faz_id = $scope.chezList[i].value;
						}
					}
					
					for(var i = 0;i < $scope.chezList.length;i++){
						if($scope.chezList[i].value==$scope.lic.daoz_id){
							$scope.lic.daoz_id = $scope.chezList[i].value;
						}
					}
					
					for(var i = 0;i < $scope.liclxList.length;i++){
						if($scope.liclxList[i].value==$scope.lic.liclx){
							$scope.lic.liclx = $scope.liclxList[i].value;
						}
					}
				}
		});
	}
	 
	$scope.saveLic=function(){
		if($("#licAdd_form").valid()){
			if($scope.lic.id==null){
				$http.post('lic/addLic',{info:angular.toJson($scope.lic)}).
			       success(function(data, status, headers, config) {
			    	   if(data[0]==1){
			    		   alert("新增里程信息成功！");
			    		   $location.path('/lic');
			    	   }else{
			    		   alert("新增里程信息失败！");
			    	   }
			   });
			}else{
				$http.post('lic/updateLic',{info:angular.toJson($scope.lic)}).
			       success(function(data, status, headers, config) {
			    	   if(data[0]==1){
			    		   alert("修改里程信息成功！");
			    		   $location.path('/lic');
			    	   }else{
			    		   alert("修改里程信息失败！");
			    	   }
			   });
			}
		}
	}
	 
	$scope.cancel=function(){
		$location.path('/lic');
	};
})