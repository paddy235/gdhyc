gddlapp.controller('liclxCtrl', function($scope,$rootScope,$http,$log,$location) {
	$scope.liclxTitle='里程类型';
	
	$scope.addLiclx=function(){
		var flag = "Add";
		$location.path('/modifyLiclx/'+flag);
	}
	
	$scope.editLiclx=function(){
		if($("#example input[type=checkbox]:checked").length<1){
			alert("请选择要修改的行！")
		}else{
			$("#example input[type=checkbox]").each(function(){
			    if(this.checked){
			    	var flag = "Update";
					$location.path('/modifyLiclx/'+flag+"/"+$(this).attr("id"));
			    }
			}); 
		}
	}
	
	$scope.delLiclx=function(){
		if($("#example input[type=checkbox]:checked").length<1){
			alert("请选择要删除的行！")
		}else{
			$('#myModal_Del').modal('show');
		}
	}
})
.controller('liclxModifyCtrl', function($scope,$rootScope,$http,$location,$routeParams) {
	$scope.modifyLiclxTitle='';
	
	$scope.liclx = new Object();
	
	$scope.liclx.xuh = null;
	
	if($routeParams.flag=="Add"){
		$scope.modifyLiclxTitle='新增里程类型';
		
		$http.post('common/getNextNum',{param1:"liclxb",param2:"xuh"}).
		success(function(data, status, headers, config) {
			if(data){
				$scope.liclx.xuh=data[0];
			}else{
				$scope.liclx.xuh=null;
			}
		});
	}else if($routeParams.flag=="Update"){
		$scope.modifyLiclxTitle='修改里程类型';
		$http.post('liclx/getOne',{id:$routeParams.id}).
			success(function(data, status, headers, config) {
				if(data){
					$scope.liclx=data[0];
				}
		});
	}
	 
	$scope.saveLiclx=function(){
		if($("#liclxAdd_form").valid()){
			if($scope.liclx.id==null){
				$http.post('common/checkNameExist',{param1:"liclxb",param2:"mingc",param3:$scope.liclx.mingc}).
				success(function(data, status, headers, config) {
					if(data){
						if(data[0]>0){
							alert("名称["+$scope.liclx.mingc+"]已存在，不能使用!");
							return false;
						}else{
							$http.post('liclx/addLiclx',{info:angular.toJson($scope.liclx)}).
								success(function(data, status, headers, config) {
									if(data[0]==1){
										alert("新增里程类型成功！");
										$location.path('/liclx');
									}else{
										alert("新增里程类型失败！");
										$location.path('/modifyLiclx');
									}
							});
						}
					}
				});
			}else{
				$http.post('liclx/updateLiclx',{info:angular.toJson($scope.liclx)}).
					success(function(data, status, headers, config) {
					if(data[0]==1){
						alert("修改里程类型成功！");
						$location.path('/liclx');
					}else{
						alert("修改里程类型失败！");
						$location.path('/modifyLiclx');
					}
				});
			}
		}
	}
	 
	$scope.cancel=function(){
		$location.path('/liclx');
	};
})