gddlapp.controller('gongysCtrl', function($scope,$rootScope,$http,$log,$location) {
	$scope.gongysTitle='供应商';

	$scope.addGongys=function(){
		var flag = "Add";
		$location.path('/modifyGongys/'+flag);
	}
	
	$scope.editGongys=function(){
		if($("#example input[type=checkbox]:checked").length<1){
			alert("请选择要修改的行！")
		}else{
			$("#example input[type=checkbox]").each(function(){
			    if(this.checked){
			    	var flag = "Update";
					$location.path('/modifyGongys/'+flag+"/"+$(this).attr("id"));
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
				    $('#gongys_id').val(id);
					$('#myModal').modal('show');
			    }
			});  
		}
	}
	
	$scope.switchGongys = function(param){
		if($("#example input[type=checkbox]:checked").length<1){
			alert("请选择操作的记录！");
		}else{
			$("#example input[type=checkbox]").each(function(){
			    if(this.checked){
			    	var id=$(this).attr("id");
			    	$http.post('gongys/switchGongys',{info:param,id:id}).
					success(function(data, status, headers, config) {
					    if(data[0]==1){
					    	if(param==1){
					    		alert("供应商启用成功！");
					    	}else{
					    		alert("供应商停用成功！");
					    	}
					    	oTable.fnReloadAjax('gongys/getAll');
					    }else{
					    	if(param==1){
					    		alert("供应商启用失败！");
					    	}else{
					    		alert("供应商停用失败！");
					    	}
					    	oTable.fnReloadAjax('gongys/getAll');
					    }
					});
			    }
			});  
		}
	}
})
.controller('gongysModifyCtrl', function($scope,$rootScope,$http,$location,$routeParams) {
	$scope.modifyGongysTitle='';
	
	$scope.gongys = new Object();
	
	$scope.gongys.xuh = null;
	$scope.gongys.bianm = null;
	$scope.gongys.meikxxb_id = null;
	$scope.gongys.shengfb_id = null;
	$scope.gongys.rongqgx = null;
	$scope.gongys.xiny = null;
	$scope.gongys.shifss = null;
	
	if($routeParams.flag=="Add"){
		$scope.modifyGongysTitle='新增供应商信息';
		$http.post('common/getNextNum',{param1:"gongysb",param2:"xuh"}).
			success(function(data, status, headers, config) {
				if(data){
					$scope.gongys.xuh=data[0];
				}else{
					$scope.gongys.xuh=null;
				}
		});
	}else if($routeParams.flag=="Update"){
		$scope.modifyGongysTitle='修改供应商信息';
		$http.post('gongys/getOne',{id:$routeParams.id}).
			success(function(data, status, headers, config) {
				if(data){
					$scope.gongys=data[0];
					
					for(var i = 0;i < $scope.shengfList.length;i++){
						if($scope.shengfList[i].value==$scope.gonygs.shengfb_id){
							$scope.gonygs.shengfb_id = $scope.shengfList[i].value;
						}
					}
					
					for(var i = 0;i < $scope.meikxxList.length;i++){
						if($scope.meikxxList[i].value==$scope.gonygs.meikxxb_id){
							$scope.gonygs.meikxxb_id = $scope.meikxxList[i].value;
						}
					}
				}
		});
	}
	 
	$scope.saveGongys=function(){
		if($("#gongysAdd_form1").valid()){
			if($scope.gongys.id==null){
				$http.post('common/checkNameExist',{param1:"gongysb",param2:"mingc",param3:$scope.gongys.mingc}).
				success(function(data, status, headers, config) {
					if(data){
						if(data[0]>0){
							alert("名称["+$scope.gongys.mingc+"]已存在，不能使用!");
							return false;
						}else{
							if($scope.gongys.bianm==null){
								$scope.gongys.bianm = $('#bianm').val();
							}
							
							$http.post('common/checkNameExist',{param1:"gongysb",param2:"quanc",param3:$scope.gongys.quanc}).
								success(function(data, status, headers, config) {
									if(data){
										if(data[0]>0){
											alert("全称["+$scope.gongys.quanc+"]已存在，不能修改!");
											return false;
										}else{
											$http.post('gongys/addGongys',{info:angular.toJson($scope.gongys)}).
											success(function(data, status, headers, config) {
											    if(data[0]==1){
											    	alert("新增供应商信息成功！");
											    	$location.path('/gongys');
											    }else{
											    	alert("新增供应商信息失败！");
											    }
											});
										}
									}
								});
						}
					}
				});
			}else{
				if($scope.gongys.bianm==null){
					$scope.gongys.bianm = $('#bianm').val();
				}
				
				$http.post('gongys/updateGongys',{info:angular.toJson($scope.gongys)}).
				success(function(data, status, headers, config) {
					if(data[0]==1){
						alert("修改供应商信息成功！");
						$location.path('/gongys');
					}else{
						alert("修改供应商信息失败！");
					}
				});
			}
		}
	}
	
	$scope.getBianm=function(){
		var bianm = makePy($('#mingc').val());
		$('#bianm').val(bianm);
	}
	 
	$scope.cancel=function(){
		$location.path('/gongys');
	};
})