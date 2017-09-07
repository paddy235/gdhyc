gddlapp.controller('huayyjshCtrl', function($scope,$rootScope,$http,$log,$location) {
	$scope.huayyjshTitle='一级审核';
	
	$scope.search = {
		diancid : 515
	}
	
	$scope.searchData = function() {
		var diancid = $scope.search.diancid;
		oTable.fnReloadAjax('ruchy/huaysh/getTableData?diancid='+diancid);
	}
	
	$scope.shenh=function(){
		if($("#example input[type=checkbox]:checked").length<1){
			alert("请选择要审核的数据！")
		}else{
			$("#example input[type=checkbox]").each(function(){
			    if(this.checked){
			    	$http.post('ruchy/huaysh/updateZT?id='+$(this).attr("id")+"&zhuangt=5").
					 	success(function(data, status, headers, config) {
					 		 if(data[0]==1){
					    		   alert("审核成功！");
					    		   var diancid = $scope.search.diancid;
					    		   oTable.fnReloadAjax('ruchy/huaysh/getTableData?diancid='+diancid);
					    	   }else{
					    		   alert("审核失败！");
					    	   }
					 });
			    }
			}); 
		}
	}
	
	$scope.huit=function(){
		if($("#example input[type=checkbox]:checked").length<1){
			alert("请选择要回退的数据！")
		}else{
			$("#example input[type=checkbox]").each(function(){
			    if(this.checked){
			    	$http.post('ruchy/huaysh/updateZT?id='+$(this).attr("id")+"&zhuangt=2").
				 		success(function(data, status, headers, config) {
				 		 if(data[0]==1){
				    		   alert("回退成功！");
				    		   var diancid = $scope.search.diancid;
				    		   oTable.fnReloadAjax('ruchy/huaysh/getTableData?diancid='+diancid);
				    	   }else{
				    		   alert("回退失败！");
				    	   }
				 	});
			    }
			}); 
		}
	}
})