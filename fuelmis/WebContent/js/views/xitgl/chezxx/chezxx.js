gddlapp.controller('chezxxCtrl', function($scope,$rootScope,$http,$log,$location) {
	$scope.chezxxTitle='车站信息';

	$scope.addChezxx=function(){
		var flag = "Add";
		$location.path('/modifyChezxx/'+flag);
	}
	
	$scope.editChezxx=function(){
		if($("#example input[type=checkbox]:checked").length<1){
			alert("请选择要修改的行！")
		}else{
			$("#example input[type=checkbox]").each(function(){
			    if(this.checked){
			    	var flag = "Update";
					$location.path('/modifyChezxx/'+flag+"/"+$(this).attr("id"));
			    }
			}); 
		}
	}
	
	$scope.delChezxx=function(){
		if($("#example input[type=checkbox]:checked").length<1){
			alert("请选择要删除的行！")
		}else{
			$('#myModal_Del').modal('show');
		}
	}
})
.controller('chezxxModifyCtrl', function($scope,$rootScope,$http,$location,$routeParams) {
	$scope.modifyChezxxTitle='';
	
	$scope.chezlbList = [{"name":"车站","value":"1"},{"name":"港口","value":"2"}];
	
	$scope.chezxx = new Object();
	
	$scope.chezxx.xuh = null;
	$scope.chezxx.lujxxb_id = null;
	
	if($routeParams.flag=="Add"){
		$scope.modifyChezxxTitle='新增车站信息';
		$http.post('common/getNextNum',{param1:"chezxxb",param2:"bianm"}).
			success(function(data, status, headers, config) {
				if(data){
					$scope.chezxx.bianm=data[0];
				}else{
					$scope.chezxx.bianm=null;
				}
		});
		$http.post('common/getNextNum',{param1:"chezxxb",param2:"xuh"}).
			success(function(data, status, headers, config) {
				if(data){
					$scope.chezxx.xuh=data[0];
				}else{
					$scope.chezxx.xuh=null;
				}
		});
	}else if($routeParams.flag=="Update"){
		$scope.modifyChezxxTitle='修改车站信息';
		$http.post('chezxx/getOne',{id:$routeParams.id}).
			success(function(data, status, headers, config) {
				if(data){
					$scope.chezxx=data[0];
					
					for(var i = 0;i < $scope.lujList.length;i++){
						if($scope.lujList[i].value==$scope.chezxx.lujxxb_id){
							$scope.chezxx.lujxxb_id = $scope.lujList[i].value;
						}
					}
					
					for(var i = 0;i < $scope.chezlbList.length;i++){
						if($scope.chezlbList[i].value==$scope.chezxx.leib){
							$scope.chezxx.leib = $scope.chezlbList[i].value;
						}
					}
				}
		});
	}
	 
	$scope.saveChezxx=function(){
		if($("#chezAdd_form").valid()){
			if($scope.chezxx.id==null){
				$http.post('common/checkNameExist',{param1:"chezxxb",param2:"mingc",param3:$scope.chezxx.mingc}).
				success(function(data, status, headers, config) {
					if(data){
						if(data[0]>0){
							alert("名称["+$scope.chezxx.mingc+"]已存在，不能使用!");
							return false;
						}else{
							$http.post('common/checkNameExist',{param1:"chezxxb",param2:"quanc",param3:$scope.chezxx.quanc}).
							success(function(data, status, headers, config) {
								if(data){
									if(data[0]>0){
										alert("全称["+$scope.chezxx.quanc+"]已存在，不能使用!");
										return false;
									}else{
										$http.post('chezxx/addChezxx',{info:angular.toJson($scope.chezxx)}).
									       success(function(data, status, headers, config) {
									    	   if(data[0]>0){
									    		   alert("新增车站信息成功！");
									    		   $location.path('/chezxx');
									    	   }else{
									    		   alert("新增车站信息失败！");
									    	   }
									   });
									}
								}
							});
						}
					}
				});
			}else{
				$http.post('chezxx/updateChezxx',{info:angular.toJson($scope.chezxx)}).
				success(function(data, status, headers, config) {
					if(data[0]==1){
						alert("修改车站信息成功！");
				    	$location.path('/chezxx');
				    }else{
				    	alert("修改车站信息失败！");
				    }
			   });
			}
		}
	}
	 
	$scope.cancel=function(){
		$location.path('/chezxx');
	};
})