gddlapp.controller('meizxxCtrl', function($scope,$rootScope,$http,$log,$location) {
	$scope.meizxxTitle='煤种信息';
	
	$scope.addMeizxx=function(){
		var flag = "Add";
		$location.path('/modifyMeizxx/'+flag);
	}
	
	$scope.editMeizxx=function(){
		if($("#example input[type=checkbox]:checked").length<1){
			alert("请选择要修改的行！")
		}else{
			$("#example input[type=checkbox]").each(function(){
			    if(this.checked){
			    	var flag = "Update";
					$location.path('/modifyMeizxx/'+flag+"/"+$(this).attr("id"));
			    }
			}); 
		}
	}
	
	$scope.delMeizxx=function(){
		if($("#example input[type=checkbox]:checked").length<1){
			alert("请选择要删除的行！")
		}else{
			$('#myModal_Del').modal('show');
		}
	}
})
.controller('meizxxModifyCtrl', function($scope,$rootScope,$http,$location,$routeParams) {
	$scope.modifyMeizxxTitle='';
	
	$scope.meizxx = new Object();
	
	$scope.meizxx.xuh = null;
	
	if($routeParams.flag=="Add"){
		$scope.modifyMeizxxTitle='新增煤种信息';
		$http.post('common/getNextNum',{param1:"meizb",param2:"xuh"}).
			success(function(data, status, headers, config) {
				if(data){
					$scope.meizxx.xuh=data[0];
				}else{
					$scope.meizxx.xuh=null;
				}
		});
	}else if($routeParams.flag=="Update"){
		$scope.modifyMeizxxTitle='修改煤种信息';
		$http.post('meiz/getOne',{id:$routeParams.id}).
			success(function(data, status, headers, config) {
				if(data){
					$scope.meizxx=data[0];
				}
		});
	}
	
	$scope.getBianm=function(){
		var bianm = makePy($('#mingc').val());
		$('#bianm').val(bianm);
	}
	 
	$scope.saveMeizxx=function(){
		if($("#meizAdd_form").valid()){
			if($scope.meizxx.id==null){
				$http.post('common/checkNameExist',{param1:"meizb",param2:"mingc",param3:$scope.meizxx.mingc}).
				success(function(data, status, headers, config) {
					if(data){
						if(data[0]>0){
							alert("名称["+$scope.meizxx.mingc+"]已存在，不能使用!");
							return false;
						}else{
							if($scope.meizxx.bianm==null){
								$scope.meizxx.bianm = $('#bianm').val();
							}
							
							$http.post('meiz/addMeizxx',{info:angular.toJson($scope.meizxx)}).
								success(function(data, status, headers, config) {
								if(data[0]==1){
									alert("新增煤种信息成功！");
									$location.path('/meizxx');
								}else{
									alert("新增煤种信息失败！");
								}
							});
						}
					}
				});
			}else{
				if($scope.meizxx.bianm==null){
					$scope.meizxx.bianm = $('#bianm').val();
				}
				
				$http.post('meiz/updateMeizxx',{info:angular.toJson($scope.meizxx)}).
					success(function(data, status, headers, config) {
					if(data[0]==1){
						alert("修改煤种信息成功！");
						$location.path('/meizxx');
					}else{
						alert("修改煤种信息失败！");
					}
				});
			}
		}
	}
	 
	$scope.cancel=function(){
		$location.path('/meizxx');
	};
})