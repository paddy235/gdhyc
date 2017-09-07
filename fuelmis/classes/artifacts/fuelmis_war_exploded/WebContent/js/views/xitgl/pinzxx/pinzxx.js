gddlapp.controller('pinzxxCtrl', function($scope,$rootScope,$http,$log,$location) {
	$scope.pinzxxTitle='品种信息';

	$scope.addPinzxx=function(){
		var flag = "Add";
		$location.path('/modifyPinzxx/'+flag);
	}
	
	$scope.editPinzxx=function(){
		if($("#example input[type=checkbox]:checked").length<1){
			alert("请选择要修改的行！")
		}else{
			$("#example input[type=checkbox]").each(function(){
			    if(this.checked){
			    	var flag = "Update";
					$location.path('/modifyPinzxx/'+flag+"/"+$(this).attr("id"));
			    }
			}); 
		}
	}
	
	$scope.delPinzxx=function(){
		if($("#example input[type=checkbox]:checked").length<1){
			alert("请选择要删除的行！")
		}else{
			$('#myModal_Del').modal('show');
		}
	}
})
.controller('pinzxxModifyCtrl', function($scope,$rootScope,$http,$location,$routeParams) {
	$scope.modifyPinzxxTitle='';
	
	$scope.pinzxx = new Object();
	
	$scope.pinzxx.xuh = null;
	$scope.pinzxx.zhuangt = 1;
	$scope.pinzxx.leib = 1;
	
	if($routeParams.flag=="Add"){
		$scope.modifyPinzxxTitle='新增品种信息';
		$http.post('common/getNextNum',{param1:"pinzb",param2:"xuh"}).
			success(function(data, status, headers, config) {
				if(data){
					$scope.pinzxx.xuh=data[0];
				}else{
					$scope.pinzxx.xuh=null;
				}
		});
	}else if($routeParams.flag=="Update"){
		$scope.modifyPinzxxTitle='修改品种信息';
		$http.post('pinz/getOne',{id:$routeParams.id}).
			success(function(data, status, headers, config) {
				if(data){
					$scope.pinzxx=data[0];
				}
		});
	}
	 
	$scope.savePinzxx=function(){
		if($("#pinzAdd_form").valid()){
			if($scope.pinzxx.id==null){
				$http.post('common/checkNameExist',{param1:"pinzb",param2:"mingc",param3:$scope.pinzxx.mingc}).
				success(function(data, status, headers, config) {
					if(data){
						if(data[0]>0){
							alert("名称["+$scope.pinzxx.mingc+"]已存在，不能使用!");
							return false;
						}else{
							$http.post('pinz/addPinzxx',{info:angular.toJson($scope.pinzxx)}).
								success(function(data, status, headers, config) {
							    if(data[0]==1){
							    	alert("新增品种信息成功！");
							    	$location.path('/pinzxx');
							    }else{
							    	alert("新增品种信息失败！");
							    }
							});
						}
					}
				});
			}else{
				$http.post('pinz/updatePinzxx',{info:angular.toJson($scope.pinzxx)}).
					success(function(data, status, headers, config) {
						if(data[0]==1){
							alert("修改品种信息成功！");
							$location.path('/pinzxx');
						}else{
							alert("修改品种信息失败！");
						}
					});
			}
		}
	}
	 
	$scope.cancel=function(){
		$location.path('/pinzxx');
	};
})