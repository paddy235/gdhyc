gddlapp.controller('gongmjhzlCtrl', function($scope,$rootScope,$http,$log,$location) {
	$scope.gongmjhzlTitle='供煤计划质量维护';
	
	$scope.search = new Object();
	$scope.search.gongysb_id =-1;
	$scope.search.date = timeStamp2String(1);
	function timeStamp2String(type){
	    var datetime = new Date();
	    var year = datetime.getFullYear();
	    var month = datetime.getMonth() + 1 < 10 ? "0" + (datetime.getMonth() + 1) : datetime.getMonth() + 1;
	    var date = datetime.getDate() < 10 ? "0" + datetime.getDate() : datetime.getDate();
	    var hour = datetime.getHours()< 10 ? "0" + datetime.getHours() : datetime.getHours();
	    var minute = datetime.getMinutes()< 10 ? "0" + datetime.getMinutes() : datetime.getMinutes();
	    var second = datetime.getSeconds()< 10 ? "0" + datetime.getSeconds() : datetime.getSeconds();
	    if(type == 1){
	    	return year + "-" + month + "-" + date;
	    }else{
	    	return year + "-" + month + "-"+"01"
	    }
	    
	}
	
	
	$scope.addGongmjhzl=function(){
		var flag = "Add";
		$location.path('/addGongmjhzl/'+flag);
	}
	
	
	$scope.refresh = function(){								
		var gongysbid = $scope.search.gongysb_id
		var date = $scope.search.date;
		oTable.fnReloadAjax('gongyspg/gongmjhzl/searchRigmjhList/?gongysid='+gongysbid+'&date='+date);	
	}
	
	
	
	$scope.editGongmjhzl=function(){
		if($("#example input[type=checkbox]:checked").length<1){
			alert("请选择要修改的行！");
		}else{
			$("#example input[type=checkbox]").each(function(){
			    if(this.checked){
			    	var flag = "Update";
					$location.path('/addGongmjhzl/'+flag+"/"+$(this).attr("id"));
			    }
			}); 
		}
	}
		

})


.controller('gongmjhzlAddCtrl', function($scope,$rootScope,$http,$location,$routeParams) {	
	$scope.title='';
	$scope.gongmjhzl = new Object();
	if($routeParams.flag=="Add"){
		$scope.title='新增供煤计划质量信息';
	}else if($routeParams.flag=="Update"){
		$scope.title='修改供煤计划质量信息';
		$http.post('gongyspg/gongmjhzl/editGongmjhzl',{id:$routeParams.id}).
			success(function(data, status, headers, config) {
				if(data){
					$scope.gongmjhzl=data;					
				}
		});
	}
	 
	$scope.saveGongmjhzl=function(){
		if($scope.gongmjhzl.id==null){
			$http.post('gongyspg/gongmjhzl/addGongmjhzl',{info:angular.toJson($scope.gongmjhzl)}).
		       success(function(data, status, headers, config) {
		    	   if(data[0]==1){
		    		   alert("新增供煤计划质量信息成功！");
		    		   $location.path('/gongyspg/gongmjhzl');
		    	   }else{
		    		   alert("新增供煤计划质量信息失败！");
		    		   $location.path('/gongyspg/gongmjhzl');
		    	   }
		    	   $location.path('/gongyspg/gongmjhzl');
		   });
		}else{
			$http.post('gongyspg/gongmjhzl/updateGongmjhzl',{info:angular.toJson($scope.gongmjhzl)}).
		       success(function(data, status, headers, config) {
		    	   if(data[0]==1){
		    		   alert("修改供煤计划质量信息成功！");
		    		   $location.path('/gongyspg/gongmjhzl');
		    	   }else{
		    		   alert("修改供煤计划质量信息失败！");
		    		   $location.path('/gongyspg/gongmjhzl');
		    	   }
		   });
		}
	}
	 
	$scope.cancel=function(){
		$location.path('gongmjhzl');
	};
})
