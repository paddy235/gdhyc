gddlapp.controller('rlhetmbCtrl', function($scope,$rootScope,$http,$log,$location) {
	
	$scope.rlhetmbTitle='合同模板维护';
	
	$scope.addRlhetmb=function(){
		var flag = "Add";
		$location.path('/addRlhtmb/'+flag);
	}
	
	$scope.editRlhetmb=function(){
		if($("#example input[type=checkbox]:checked").length<1){
			alert("请选择要修改的行！");
		}else{
			$("#example input[type=checkbox]").each(function(){
			    if(this.checked){
			    	var flag = "Update";
					$location.path('/addRlhtmb/'+flag+"/"+$(this).attr("id"));
			    }
			}); 
		}
	}
	
	
	$scope.refresh=function(){
		var diancid = $scope.search.diancid;
		var strdate = $scope.search.strdate;
		var enddate = $scope.search.enddate;
		oTable.fnReloadAjax('rlhetmb/searchRlhetmbList/?diancid='+diancid+'&strdate='+strdate+'&enddate='+enddate);		
	}
	
	
	
	$scope.delRlhetmb=function(){
		if($("#example input[type=checkbox]:checked").length<1){
			alert("请选择要删除的行！")
		}else{
			$('#myModal_Del').modal('show');
		}
	}	
	
	$scope.toHetmb=function(){
		if($("#example input[type=checkbox]:checked").length<1){
			alert("请选择要预览的合同模板");
		}else{
			$("#example input[type=checkbox]").each(function(){
			    if(this.checked){
			    	var id = $(this).attr("id");
					$http.post('hetgl/rlhtmb/editHetmb',{id:id}).
					success(function(data, status, headers, config) {
						if(data){
							var hetmb = data.hetmb;
							var url = hetmb.mublj;
							//$('#mub').attr("href",url);
							document.getElementById('mub').href=url;
							document.getElementById('mub').click();
						}
					});		
			    }
			}); 			
		}
	}
	
})



.controller('rlhetmbAddCtrl',function($scope,$rootScope,$http,$location,$routeParams) {
	$scope.rlhetmbTitle = '添加合同模板';
	$scope.hetmb = new Object(); 
	if($routeParams.flag=="Add"){
		$scope.hetmbTitle='新增合同模板信息';
		$scope.hetmb.zhuangt = 1;
	}else if($routeParams.flag=="Update"){
		$scope.hetmbTitle='修改合同模板信息';
		$http.post('hetgl/rlhtmb/editHetmb',{id:$routeParams.id}).
			success(function(data, status, headers, config) {
				if(data){
					$scope.hetmb = data.hetmb;
				}
		});
	}
	
	$scope.cancel=function(){
		$location.path('/hetgl/rlhtmb');
	};	
	
	$scope.saveRlhetmb = function(){
		if($scope.hetmb.id==null){
		$http.post('hetgl/rlhtmb/addHetmb',{info:angular.toJson($scope.hetmb)}).
	       success(function(data, status, headers, config) {
	    	   if(data[0]==1){
	    		   alert("新增合同模板成功！");
	    		   $location.path('/hetgl/rlhtmb');
	    	   }else{
	    		   alert("新增合同模板失败！");
	    		   $location.path('/hetgl/rlhtmb');
	    	   }
	    	   $location.path('/hetgl/rlhtmb');
	   });
	   }else{                      
			$http.post('hetgl/rlhtmb/updateHetmb',{info:angular.toJson($scope.hetmb)}).			
		       success(function(data, status, headers, config) {
		    	   if(data[0]==1){
		    		   alert("修改合同模板成功！");
		    		   $location.path('/hetgl/rlhtmb');
		    	   }else{
		    		   alert("修改合同模板失败！");
		    		   $location.path('/hetgl/rlhtmb');
		    	   }
		   });
			
	   }
	}
	
})




