gddlapp.controller('yunsdwCtrl', function($scope,$rootScope,$http,$log,$location) {
	$scope.yunsdwTitle='运输单位';
	
	$scope.search = {
		diancid : 515
	}

	$scope.searchData = function() {
		var diancid = $scope.search.diancid;
		oTable.fnReloadAjax('yunsdw/getAll/?dianc='+diancid);
	}
	
	$scope.addYunsdw=function(){
		var flag = "Add";
		$location.path('/modifyYunsdw/'+flag);
	}
	
	$scope.editYunsdw=function(){
		if($("#example input[type=checkbox]:checked").length<1){
			alert("请选择要修改的行！")
		}else{
			$("#example input[type=checkbox]").each(function(){
			    if(this.checked){
			    	var flag = "Update";
					$location.path('/modifyYunsdw/'+flag+"/"+$(this).attr("id"));
			    }
			}); 
		}
	}
})
.controller('yunsdwModifyCtrl', function($scope,$rootScope,$http,$location,$routeParams) {
	$scope.modifyYunsdwTitle='';
	
	$scope.yunsdw = new Object();
	
	$scope.yunsdw.faz_id = null;
	$scope.yunsdw.daoz_id = null;
	$scope.yunsdw.yunsdwlxb_id = null;
	$scope.yunsdw.zhi = null;
	$scope.yunsdw.meikxxb_id = null;
	
	if($routeParams.flag=="Add"){
		$scope.modifyYunsdwTitle='新增运输单位';
		
		$http.post('common/getNextNum',{param1:"yunsdwb",param2:"bianm"}).
		success(function(data, status, headers, config) {
			if(data){
				$scope.yunsdw.bianm=data[0];
			}else{
				$scope.yunsdw.bianm=null;
			}
		});
	}else if($routeParams.flag=="Update"){
		$scope.modifyYunsdwTitle='修改运输单位';
		$http.post('yunsdw/getOne',{id:$routeParams.id}).
			success(function(data, status, headers, config) {
				if(data){
					$scope.yunsdw=data[0];
				}
		});
	}
	 
	$scope.saveYunsdw=function(){
		if($("#yunsdwAdd_form").valid()){
			if($scope.yunsdw.id==null){
				$http.post('common/checkNameExist',{param1:"yunsdwb",param2:"mingc",param3:$scope.yunsdw.mingc}).
				success(function(data, status, headers, config) {
					if(data){
						if(data[0]>0){
							alert("名称["+$scope.yunsdw.mingc+"]已存在，不能使用!");
							return false;
						}else{
							$http.post('common/checkNameExist',{param1:"yunsdwb",param2:"quanc",param3:$scope.yunsdw.quanc}).
								success(function(data, status, headers, config) {
									if(data){
										if(data[0]>0){
											alert("全称["+$scope.yunsdw.quanc+"]已存在，不能使用!");
											return false;
										}else{
											$http.post('yunsdw/addYunsdw',{info:angular.toJson($scope.yunsdw)}).
											       success(function(data, status, headers, config) {
											    	   if(data[0]==1){
											    		   alert("新增运输单位信息成功！");
											    		   $location.path('/yunsdw');
											    	   }else{
											    		   alert("新增运输单位信息失败！");
											    	   }
											   });
										}
									}
								});
						}
					}
				});
			}else{
				$http.post('yunsdw/updateYunsdw',{info:angular.toJson($scope.yunsdw)}).
					success(function(data, status, headers, config) {
					if(data[0]==1){
						alert("修改运输单位信息成功！");
						$location.path('/yunsdw');
					}else{
						alert("修改运输单位信息失败！");
						$location.path('/modifyYunsdw');
					}
				});
			}
		}
	}
	 
	$scope.cancel=function(){
		$location.path('/yunsdw');
	};
})