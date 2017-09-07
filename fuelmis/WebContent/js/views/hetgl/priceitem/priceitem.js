gddlapp.controller('priceItemCtrl', function($scope,$rootScope,$http,$log,$location) {
	
	$scope.priceItemTitle='计价指标维护';
	
	$scope.addPriceItem=function(){
		var flag = "Add";
		$location.path('/addPriceItem/'+flag);
	}
	
	$scope.editPriceItem=function(){
		if($("#example input[type=checkbox]:checked").length<1){
			alert("请选择要修改的行！");
		}else{
			$("#example input[type=checkbox]").each(function(){
			    if(this.checked){
			    	var flag = "Update";
					$location.path('/addPriceItem/'+flag+"/"+$(this).attr("id"));
			    }
			}); 
		}
	}
	
	
	$scope.refresh=function(){
		var diancid = $scope.search.diancid;
		var strdate = $scope.search.strdate;
		var enddate = $scope.search.enddate;
		oTable.fnReloadAjax('priceItem/searchPriceItemList/?diancid='+diancid+'&strdate='+strdate+'&enddate='+enddate);		
	}
	
	
	
	$scope.delPriceItem=function(){
		if($("#example input[type=checkbox]:checked").length<1){
			alert("请选择要删除的行！")
		}else{
			$('#myModal_Del').modal('show');
		}
	}	
	
	
})



.controller('priceItemAddCtrl',function($scope,$rootScope,$http,$location,$routeParams) {
	$scope.priceItemTitle = '添加计价指标';
	$scope.priceItem = new Object(); 
	if($routeParams.flag=="Add"){
		$scope.priceItemTitle='新增计价指标信息';
		$scope.priceItem.zhuangt = 1;
	}else if($routeParams.flag=="Update"){
		$scope.priceItemTitle='修改计价指标信息';
		$http.post('hetgl/priceitem/editPriceItem',{id:$routeParams.id}).
			success(function(data, status, headers, config) {
				if(data){
					$scope.priceItem = data.priceItem;
				}
		});
	}
	
	$scope.cancel=function(){
		$location.path('/hetgl/priceitem');
	};	
	
	$scope.savePriceItem = function(){
		if($scope.priceItem.id==null){
		$http.post('hetgl/priceitem/addPriceItem',{info:angular.toJson($scope.priceItem)}).
	       success(function(data, status, headers, config) {
	    	   if(data[0]==1){
	    		   alert("新增计价指标成功！");
	    		   $location.path('/hetgl/priceitem');
	    	   }else{
	    		   alert("新增计价指标失败！");
	    		   $location.path('/hetgl/priceitem');
	    	   }
	    	   $location.path('/hetgl/priceitem');
	   });
	   }else{                      
			$http.post('hetgl/priceitem/updatePriceItem',{info:angular.toJson($scope.priceItem)}).			
		       success(function(data, status, headers, config) {
		    	   if(data[0]==1){
		    		   alert("修改计价指标成功！");
		    		   $location.path('/hetgl/priceitem');
		    	   }else{
		    		   alert("修改计价指标失败！");
		    		   $location.path('/hetgl/priceitem');
		    	   }
		   });
			
	   }
	}
	
})




