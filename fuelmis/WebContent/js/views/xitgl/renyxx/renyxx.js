gddlapp.controller('renyxxCtrl', function($scope,$rootScope,$http,$log,$location) {
	$scope.renyxxTitle='人员信息维护';
	
	$scope.addRenyxx=function(){
		var flag = "Add";
		$location.path('/modifyRenyxx/'+flag);
	}
	
	$scope.editRenyxx=function(){
		if($("#example input[type=checkbox]:checked").length<1){
			alert("请选择要修改的行！")
		}else{
			$("#example input[type=checkbox]").each(function(){
			    if(this.checked){
			    	var flag = "Update";
					$location.path('/modifyRenyxx/'+flag+"/"+$(this).attr("id"));
			    }
			}); 
		}
	}
	
	$scope.delRenyxx=function(){
		if($("#example input[type=checkbox]:checked").length<1){
			alert("请选择要删除的行！")
		}else{
			$('#myModal_Del').modal('show');
		}
	}
	
	$scope.resetPassword = function(){
		if($("#example input[type=checkbox]:checked").length<1){
			alert("请选择要操作的行！")
		}else{
			$('#myModal_Reset').modal('show');
		}
	}
	
	$scope.shezqx = function(){
		if($("#example input[type=checkbox]:checked").length<1){
			alert("请选择要操作的行！")
		}else{
			$("#example input[type=checkbox]").each(function(){
			    if(this.checked){
					$location.path('/shezqx/'+$(this).attr("id"));
			    }
			}); 
		}
	}
})
.controller('renyxxModifyCtrl', function($scope,$rootScope,$http,$location,$routeParams) {
	$scope.modifyRenyxxTitle='';
	
	$scope.renyxx = new Object();
	
	if($routeParams.flag=="Add"){
		$scope.modifyRenyxxTitle='新增人员信息';
		$scope.renyxx.zhuangt = 1;
		$scope.renyxx.xingb = 1;
	}else if($routeParams.flag=="Update"){
		$scope.modifyRenyxxTitle='修改人员信息';
		$http.post('renyxx/getOne',{id:$routeParams.id}).
			success(function(data, status, headers, config) {
				if(data){
					$scope.renyxx=data[0];
				}
		});
	}
	 
	$scope.saveRenyxx=function(){
		if($("#renyAdd_form").valid()){
			if($scope.renyxx.id==null){
				$http.post('common/checkNameExist',{param1:"renyxxb",param2:"mingc",param3:$scope.renyxx.mingc}).
				success(function(data, status, headers, config) {
					if(data){
						if(data[0]>0){
							alert("名称["+$scope.renyxx.mingc+"]已存在，不能使用!");
							return false;
						}else{
							$http.post('renyxx/addRenyxx',{info:angular.toJson($scope.renyxx)}).
						       success(function(data, status, headers, config) {
						    	   if(data[0]==1){
						    		   alert("新增人员信息成功！");
						    		   $location.path('/renyxx');
						    	   }else{
						    		   alert("新增人员信息失败！");
						    	   }
						   });
						}
					}
				});
			}else{
				$http.post('renyxx/updateRenyxx',{info:angular.toJson($scope.renyxx)}).
			       success(function(data, status, headers, config) {
			    	   if(data[0]==1){
			    		   alert("修改人员信息成功！");
			    		   $location.path('/renyxx');
			    	   }else{
			    		   alert("修改人员信息失败！");
			    	   }
			   });
			}	
		}
	}
	 
	$scope.cancel=function(){
		$location.path('/renyxx');
	};
})
.controller('shezqxCtrl', function($scope,$rootScope,$http,$location,$routeParams) {
	$scope.shezqxTitle='权限设置';
	
	var id = $routeParams.id;
	
	$("#renyxxb_id").val(id);

	$scope.saveQuanx=function(){
		var treeObj = $.fn.zTree.getZTreeObj("tree");
	    var nodes = treeObj.getCheckedNodes(true);
	    var arr = new Array();
	    var j = 0;
	    for (var i = 0; i < nodes.length; i++) {
	    	if(nodes[i].id!=-1){
	    		arr[j] = nodes[i].id;
	    		j++;
	    	}
	    }
	    $http.post('renyxx/shezQuanx',{id:id,ziy:arr}).
	       success(function(data, status, headers, config) {
	    	   if(data[0]==1){
	    		   alert("权限修改成功！");
	    		   $location.path('/renyxx');
	    	   }else{
	    		   alert("权限修改失败！");
	    	   }
	   });
	}
	 
	$scope.cancel=function(){
		$location.path('/renyxx');
	};
})