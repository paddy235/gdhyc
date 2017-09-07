gddlapp.controller('meikxxCtrl', function($scope,$rootScope,$http,$log,$location) {
	$scope.meikxxTitle='煤矿信息';

	$scope.addMeikxx=function(){
		var flag = "Add";
		$location.path('/modifyMeikxx/'+flag);
	}
	
	$scope.editMeikxx=function(){
		if($("#example input[type=checkbox]:checked").length<1){
			alert("请选择要修改的行！")
		}else{
			$("#example input[type=checkbox]").each(function(){
			    if(this.checked){
			    	var flag = "Update";
					$location.path('/modifyMeikxx/'+flag+"/"+$(this).attr("id"));
			    }
			}); 
		}
	}
	
	$scope.shezChez=function(){
		if($("#example input[type=checkbox]:checked").length<1){
			alert("请选择要设置车站的煤矿！")
		}else{
			$("#example input[type=checkbox]").each(function(){
			    if(this.checked){
					$location.path('/shezChez/'+$(this).attr("id"));
			    }
			}); 
		}
	}
	
	$scope.uploadFuj = function() {
		if($("#example input[type=checkbox]:checked").length<1){
			alert("请选择要添加附件的记录！");
		}else{
			$("#example input[type=checkbox]").each(function(){
			    if(this.checked){
			    	var id=$(this).attr("id");
				    $('#meikxx_id').val(id);
					$('#myModal').modal('show');
			    }
			});  
		}
	}
	
	$scope.guanlGongys=function(){
		if($("#example input[type=checkbox]:checked").length<1){
			alert("请选择要关联供应商的煤矿！")
		}else{
			$("#example input[type=checkbox]").each(function(){
			    if(this.checked){
					$location.path('/guanlGongys/'+$(this).attr("id"));
			    }
			}); 
		}
	}
})
.controller('meikxxModifyCtrl', function($scope,$rootScope,$http,$location,$routeParams) {
	$scope.modifyMeikxxTitle='';
	
	$scope.meikxx = new Object();
	
	$scope.leibList = [{"name":"地方","value":"1"},{"name":"统配","value":"2"}];
	$scope.leixList = [{"name":"煤","value":"1"}];
	
	$scope.meikxx.xuh = null;
	$scope.meikxx.bianm = null;
	$scope.meikxx.shiyzt = 1;
	$scope.meikxx.shengfb_id = null;
	$scope.meikxx.leib = null;
	$scope.meikxx.leix = null;
	$scope.meikxx.jihkjb_id = null;
	
	if($routeParams.flag=="Add"){
		$scope.modifyMeikxxTitle='新增煤矿信息';
		$http.post('common/getNextNum',{param1:"meikxxb",param2:"xuh"}).
			success(function(data, status, headers, config) {
				if(data){
					$scope.meikxx.xuh=data[0];
				}else{
					$scope.meikxx.xuh=null;
				}
		});
	}else if($routeParams.flag=="Update"){
		$scope.modifyMeikxxTitle='修改煤矿信息';
		$http.post('meikxx/getOne',{id:$routeParams.id}).
			success(function(data, status, headers, config) {
				if(data){
					$scope.meikxx=data[0];
					
					for(var i = 0;i < $scope.leixList.length;i++){
						if($scope.leixList[i].value==$scope.meikxx.leix){
							$scope.meikxx.leix = $scope.leixList[i].value;
						}
					}
					
					for(var i = 0;i < $scope.leibList.length;i++){
						if($scope.leibList[i].value==$scope.meikxx.leib){
							$scope.meikxx.leib = $scope.leibList[i].value;
						}
					}
					
					for(var i = 0;i < $scope.jihkjList2.length;i++){
						if($scope.jihkjList2[i].value==$scope.meikxx.jihkjb_id){
							$scope.meikxx.jihkjb_id = $scope.jihkjList2[i].value;
						}
					}
				}
		});
	}
	 
	$scope.saveMeikxx=function(){
		if($("#meikxxAdd_form").valid()){
			if($scope.meikxx.id==null){
				$http.post('common/checkNameExist',{param1:"meikxxb",param2:"mingc",param3:$scope.meikxx.mingc}).
				success(function(data, status, headers, config) {
					if(data){
						if(data[0]>0){
							alert("名称["+$scope.meikxx.mingc+"]已存在，不能使用!");
							return false;
						}else{
							$http.post('common/checkNameExist',{param1:"meikxxb",param2:"quanc",param3:$scope.meikxx.quanc}).
								success(function(data, status, headers, config) {
									if(data){
										if(data[0]>0){
											alert("全称["+$scope.meikxx.quanc+"]已存在，不能使用!");
											return false;
										}else{
											$http.post('meikxx/addMeikxx',{info:angular.toJson($scope.meikxx)}).
										       success(function(data, status, headers, config) {
										    	   if(data[0]==1){
										    		   alert("新增煤矿信息成功！");
										    		   $location.path('/meikxx');
										    	   }else{
										    		   alert("新增煤矿信息失败！");
										    	   }
										       });
										}
									}
								});
						}
					}
				});
			}else{
				$http.post('meikxx/updateMeikxx',{info:angular.toJson($scope.meikxx)}).
				success(function(data, status, headers, config) {
					if(data[0]==1){
						alert("修改煤矿信息成功！");
						$location.path('/meikxx');
					}else{
						alert("修改煤矿信息失败！");
					}
				});
			}
		}
	}
	 
	$scope.cancel=function(){
		$location.path('/meikxx');
	};
})
.controller('shezChezCtrl', function($scope,$rootScope,$http,$location,$routeParams) {
	$scope.shezChezTitle='设置车站';
	
	$('#meikxxb_id').val($routeParams.id);
	
	$http.post('meikxx/getKuangzglb',{meikxxb_id:$routeParams.id}).
		success(function(data, status, headers, config) {
			var i = 0;
			if(data&&data.length>0){
				$("#example input[type=checkbox]").each(function(){
				    if(this.id == data[i]){
				    	this.checked = true;
				    	i++;
				    }
				}); 
			}
		})
	
	$scope.saveKuangzglb = function(){
		var chezxxb_id = "";
		$("#example input[type=checkbox]").each(function(){
		    if(this.checked){
		    	chezxxb_id += ",";
		    	chezxxb_id += this.id;
		    }
		}); 
		if(chezxxb_id != ""){
			$http.post('meikxx/saveKuangzglb',{meikxxb_id:$('#meikxxb_id').val(),chezxxb_id:chezxxb_id}).
		       success(function(data, status, headers, config) {
		    	   if(data[0]!=0){
		    		   alert("设置车站成功！");
		    		   $location.path('/meikxx');
		    	   }else{
		    		   alert("设置车站失败！");
		    	   }
		   });
		}
	}
	
	$scope.cancel=function(){
		$location.path('/meikxx');
	};
})
.controller('guanlGongysCtrl', function($scope,$rootScope,$http,$location,$routeParams) {
	$scope.guanlGongysTitle='关联供应商';
	
	$('#meikxxb_id').val($routeParams.id);
	
	$http.post('meikxx/getGongysmkglb',{meikxxb_id:$routeParams.id}).
		success(function(data, status, headers, config) {
			var i = 0;
			if(data&&data.length>0){
				$("#example input[type=checkbox]").each(function(){
				    if(this.id == data[i]){
				    	this.checked = true;
				    	i++;
				    }
				}); 
			}
		})

	$scope.saveGongysmkglb = function(){
		var gongysb_id = "";
		$("#example input[type=checkbox]").each(function(){
		    if(this.checked){
		    	gongysb_id += ",";
		    	gongysb_id += this.id;
		    }
		}); 
		if(gongysb_id != ""){
			$http.post('meikxx/saveGongysmkglb',{meikxxb_id:$('#meikxxb_id').val(),gongysb_id:gongysb_id}).
		       success(function(data, status, headers, config) {
		    	   if(data[0]!=0){
		    		   alert("关联供应商成功！");
		    		   $location.path('/meikxx');
		    	   }else{
		    		   alert("关联供应商失败！");
		    	   }
		   });
		}
	}
	
	$scope.cancel=function(){
		$location.path('/meikxx');
	};
})