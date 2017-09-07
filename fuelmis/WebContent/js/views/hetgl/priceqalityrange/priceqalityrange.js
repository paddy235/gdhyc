gddlapp.controller('priceQalityRangeCtrl', function($scope,$rootScope,$http,$log,$location) {
	
	$scope.priceQalityRangeTitle='计价指标维护';
	
	$scope.addPriceQalityRange=function(){
		var flag = "Add";
		$location.path('/addPriceQalityRange/'+flag);
	}
	
	$scope.editPriceQalityRange=function(){
		if($("#example input[type=checkbox]:checked").length<1){
			alert("请选择要修改的行！");
		}else{
			$("#example input[type=checkbox]").each(function(){
			    if(this.checked){
			    	var flag = "Update";
					$location.path('/addPriceQalityRange/'+flag+"/"+$(this).attr("id"));
			    }
			}); 
		}
	}
	
	
	$scope.refresh=function(){
		var diancid = $scope.search.diancid;
		var strdate = $scope.search.strdate;
		var enddate = $scope.search.enddate;
		oTable.fnReloadAjax('priceQalityRange/searchPriceQalityRangeList/?diancid='+diancid+'&strdate='+strdate+'&enddate='+enddate);		
	}
	
	
	
	$scope.delPriceQalityRange=function(){
		if($("#example input[type=checkbox]:checked").length<1){
			alert("请选择要删除的行！")
		}else{
			$('#myModal_Del').modal('show');
		}
	}	
	
	$scope.changeInfo = function(){
		$('#myModal_Info').modal('show');
	}
	
		
	
// bs版本为3的时候	
	$("#myModal_Del").on("hidden.bs.modal", function() {
	    $(this).removeData("bs.modal");
	});
	
//	$("#myModal_Del").on("hidden", function() {
//	    $(this).removeData("modal");
//	});
	
	
})



.controller('priceQalityRangeAddCtrl',function($scope,$rootScope,$http,$location,$routeParams) {
	$scope.priceQalityRangeTitle = '添加计价指标';
	$scope.priceQalityRange = new Object(); 
	if($routeParams.flag=="Add"){
		$scope.priceQalityRangeTitle='新增计价指标信息';
		$scope.priceQalityRange.zhuangt = 1;
	}else if($routeParams.flag=="Update"){
		$scope.priceQalityRangeTitle='修改计价指标信息';
		$http.post('hetgl/priceqalityrange/editPriceQalityRange',{id:$routeParams.id}).
			success(function(data, status, headers, config) {
				if(data){
					$scope.priceQalityRange = data.priceQalityRange;
				}
		});
	}
	
	$scope.cancel=function(){
		$location.path('/hetgl/priceqalityrange');
	};	
	
	$scope.savePriceQalityRange = function(){
		if($scope.priceQalityRange.id==null){
		$http.post('hetgl/priceqalityrange/addPriceQalityRange',{info:angular.toJson($scope.priceQalityRange)}).
	       success(function(data, status, headers, config) {
	    	   if(data[0]==1){
	    		   alert("新增计价指标成功！");
	    		   $location.path('/hetgl/priceqalityrange');
	    	   }else{
	    		   alert("新增计价指标失败！");
	    		   $location.path('/hetgl/priceqalityrange');
	    	   }
	    	   $location.path('/hetgl/priceqalityrange');
	   });
	   }else{                      
			$http.post('hetgl/priceqalityrange/updatePriceQalityRange',{info:angular.toJson($scope.priceQalityRange)}).			
		       success(function(data, status, headers, config) {
		    	   if(data[0]==1){
		    		   alert("修改计价指标成功！");
		    		   $location.path('/hetgl/priceqalityrange');
		    	   }else{
		    		   alert("修改计价指标失败！");
		    		   $location.path('/hetgl/priceqalityrange');
		    	   }
		   });
			
	   }
	}
	
})




