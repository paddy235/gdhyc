gddlapp.controller('meikdqCtrl', function($scope,$rootScope,$http,$log,$location) {
	$scope.meikdqTitle='煤矿地区';

	$scope.addMeikdq=function(){
		var flag = "Add";
		$location.path('/modifyMeikdq/'+flag);
	}
	
	$scope.editMeikdq=function(){
		if($("#example input[type=checkbox]:checked").length<1){
			alert("请选择要修改的行！")
		}else{
			$("#example input[type=checkbox]").each(function(){
			    if(this.checked){
			    	var flag = "Update";
					$location.path('/modifyMeikdq/'+flag+"/"+$(this).attr("id"));
			    }
			}); 
		}
	}
})
.controller('meikdqModifyCtrl', function($scope,$rootScope,$http,$location,$routeParams) {
	$scope.modifyMeikdqTitle='';
	
	$scope.meikdq = new Object();
	
	$scope.meikdq.xuh = null;
	$scope.meikdq.zhuangt = 1;
	$scope.meikdq.shengfb_id = null;
	
	if($routeParams.flag=="Add"){
		$scope.modifyMeikdqTitle='新增煤矿地区';
		
		$http.post('common/getNextNum',{param1:"meikdqb",param2:"xuh"}).
			success(function(data, status, headers, config) {
			if(data){
				$scope.meikdq.xuh=data[0];
			}else{
				$scope.meikdq.xuh=null;
			}
		});
		
		$http.post('common/getNextNum',{param1:"meikdqb",param2:"bianm"}).
			success(function(data, status, headers, config) {
			if(data){
				$scope.meikdq.bianm=data[0];
			}else{
				$scope.meikdq.xuh=null;
			}
		});
	}else if($routeParams.flag=="Update"){
		$scope.modifyMeikdqTitle='修改煤矿地区';
		$http.post('meikdq/getOne',{id:$routeParams.id}).
			success(function(data, status, headers, config) {
				if(data){
					$scope.meikdq=data[0];
					
					for(var i = 0;i < $scope.shengfList.length;i++){
						if($scope.shengfList[i].value==$scope.meikdq.shengfb_id){
							$scope.meikdq.shengfb_id = $scope.shengfList[i].value;
						}
					}
				}
		});
	}
	 
	$scope.saveMeikdq=function(){
		if($("#meikdqAdd_form").valid()){
			if($scope.meikdq.id==null){
				$http.post('common/checkNameExist',{param1:"meikdqb",param2:"mingc",param3:$scope.meikdq.mingc}).
				success(function(data, status, headers, config) {
					if(data){
						if(data[0]>0){
							alert("名称["+$scope.meikdq.mingc+"]已存在，不能使用!");
							return false;
						}else{
							$http.post('common/checkNameExist',{param1:"meikdqb",param2:"quanc",param3:$scope.meikdq.quanc}).
							success(function(data, status, headers, config) {
								if(data){
									if(data[0]>0){
										alert("全称["+$scope.meikdq.quanc+"]已存在，不能使用!");
										return false;
									}else{
										$http.post('meikdq/addMeikdq',{info:angular.toJson($scope.meikdq)}).
										success(function(data, status, headers, config) {
											if(data[0]==1){
												  alert("新增煤矿地区成功！");
												  $location.path('/meikdq');
											}else{
												  alert("新增煤矿地区失败！");
											}
										});
									}
								}
							});
						}
					}
				});
			}else{
				$http.post('meikdq/updateMeikdq',{info:angular.toJson($scope.meikdq)}).
				success(function(data, status, headers, config) {
					if(data[0]==1){
						alert("修改煤矿地区成功！");
						$location.path('/meikdq');
					}else{
						alert("修改煤矿地区失败！");
					}
				});
			}
		}
	}
	 
	$scope.cancel=function(){
		$location.path('/meikdq');
	};
})