gddlapp.controller('jizCtrl', function($scope,$rootScope,$http,$log,$location) {
	$scope.jizTitle='机组维护';
	
	$scope.search = {
		diancid : 515
	}
	
	$scope.searchData = function() {
		var diancid = $scope.search.diancid;
		oTable.fnReloadAjax('jiz/getAll/?dianc='+diancid);
	}

	$scope.addJiz=function(){
		var flag = "Add";
		$location.path('/modifyJiz/'+flag);
	}
	
	$scope.editJiz=function(){
		if($("#example input[type=checkbox]:checked").length<1){
			alert("请选择要修改的行！")
		}else{
			$("#example input[type=checkbox]").each(function(){
			    if(this.checked){
			    	var flag = "Update";
					$location.path('/modifyJiz/'+flag+"/"+$(this).attr("id"));
			    }
			}); 
		}
	}
	
	$scope.delJiz=function(){
		if($("#example input[type=checkbox]:checked").length<1){
			alert("请选择要删除的行！")
		}else{
			$('#myModal_Del').modal('show');
		}
	}
})
.controller('jizModifyCtrl', function($scope,$rootScope,$http,$location,$routeParams) {
	$scope.modifyJizTitle='';
	
	var date = new Date();
	var year = date.getFullYear();
	var month = date.getMonth()+1<10?'0'+(date.getMonth()+1):date.getMonth()+1;
	var date = date.getDate()<10?'0'+(date.getDate()):date.getDate();
	
	$scope.jiz = new Object();
	
	$scope.jiz.xuh = null;
	$scope.jiz.toucrq = year+"-"+month+"-"+date;
	$scope.jiz.jizurl = null;
	$scope.jiz.rijhm = null;
	$scope.jiz.meihl = null;
	$scope.jiz.jihdl = null;
	
	if($routeParams.flag=="Add"){
		$scope.modifyJizTitle='新增机组信息';
		
		$http.post('common/getNextNum',{param1:"jizb",param2:"xuh"}).
		success(function(data, status, headers, config) {
			if(data){
				$scope.jiz.xuh=data[0];
			}else{
				$scope.jiz.xuh=null;
			}
		});
	}else if($routeParams.flag=="Update"){
		$scope.modifyJizTitle='修改机组信息';
		$http.post('jiz/getOne',{id:$routeParams.id}).
			success(function(data, status, headers, config) {
				if(data){
					$scope.jiz=data[0];
				}
		});
	}
	 
	$scope.saveJiz=function(){
		if($("#jizAdd_form").valid()){
			if($scope.jiz.id==null){
				$http.post('jiz/addJiz',{info:angular.toJson($scope.jiz)}).
			       success(function(data, status, headers, config) {
			    	   if(data[0]==1){
			    		   alert("新增机组信息成功！");
			    		   $location.path('/jiz');
			    	   }else{
			    		   alert("新增机组信息失败！");
			    	   }
			   });
			}else{
				$http.post('jiz/updateJiz',{info:angular.toJson($scope.jiz)}).
			       success(function(data, status, headers, config) {
			    	   if(data[0]==1){
			    		   alert("修改机组信息成功！");
			    		   $location.path('/jiz');
			    	   }else{
			    		   alert("修改机组信息失败！");
			    	   }
			   });
			}
		}
	}
	 
	$scope.cancel=function(){
		$location.path('/jiz');
	};
})