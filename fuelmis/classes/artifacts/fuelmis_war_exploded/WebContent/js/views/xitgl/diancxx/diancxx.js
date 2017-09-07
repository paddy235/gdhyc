gddlapp.controller('diancxxCtrl', function($scope,$rootScope,$http,$log,$location) {
	$scope.diancxxTitle='电厂信息';
	
	$scope.search = {
		diancid : 515
	}
	
	$scope.searchData = function() {
		var diancid = $scope.search.diancid;
		oTable.fnReloadAjax('diancxx/getAll/?dianc='+diancid);
	}
	
	$scope.addDiancxx=function(){
		var flag = "Add";
		$location.path('/modifyDiancxx/'+flag);
	}
	
	$scope.editDiancxx=function(){
		if($("#example input[type=checkbox]:checked").length<1){
			alert("请选择要修改的行！")
		}else{
			$("#example input[type=checkbox]").each(function(){
			    if(this.checked){
			    	var flag = "Update";
					$location.path('/modifyDiancxx/'+flag+"/"+$(this).attr("id"));
			    }
			}); 
		}
	}
})
.controller('diancxxModifyCtrl', function($scope,$rootScope,$http,$location,$routeParams) {
	$scope.modifyDiancxxTitle='';
	
	$scope.diancjbList = [{"name":"集团","value":1},{"name":"分公司","value":2},{"name":"电厂","value":3}];
	$scope.caiyfsList = [{"name":"人工","value":1},{"name":"机械","value":2}];
	$scope.jilfsList = [{"name":"过衡","value":1},{"name":"检尺","value":2}];
	
	$scope.diancxx = new Object();
	$scope.diancxx.xuh = null;
	$scope.diancxx.shengfb_id = null;
	$scope.diancxx.fuid = null;
	$scope.diancxx.zhuangjrl = null;
	$scope.diancxx.zuidkc = null;
	$scope.diancxx.zhengccb = null;
	$scope.diancxx.xianfhkc = null;
	$scope.diancxx.rijhm = null;
	$scope.diancxx.jingjcmsx = null;
	$scope.diancxx.jingjcmxx = null;
	$scope.diancxx.dongcmzb = null;
	$scope.diancxx.jiexx = null;
	$scope.diancxx.jiexnl = null;
	$scope.diancxx.jingjcml = null;
	$scope.diancxx.jib = null;
	$scope.diancxx.dianclbb_id = null;
	$scope.diancxx.wangjxxb_id = null;
	$scope.diancxx.shangjgsid = null;
	$scope.diancxx.ranlgs = null;
	$scope.diancxx.zuidikc = null;
	$scope.diancxx.dongjjjcmsx = null;	
	$scope.diancxx.dongjjjcmxx = null;	
	$scope.diancxx.xiajjjcmsx = null;	
	$scope.diancxx.xiajjjcmxx = null;
	$scope.diancxx.chumqcmzb = null;
	$scope.diancxx.xitzjh = null;
	$scope.diancxx.shihzjh = null;
	$scope.diancxx.chanqxzb_id = null;
	$scope.diancxx.lianxrb_id = null;
	$scope.diancxx.daoz = null;
	$scope.diancxx.daog = null;
	$scope.diancxx.jiangcqcmzb = null;
	$scope.diancxx.cangkb_id = null;
	
	if($routeParams.flag=="Add"){
		$scope.modifyDiancxxTitle='新增电厂信息';
		
		$http.post('common/getNextNum',{param1:"diancxxb",param2:"xuh"}).
			success(function(data, status, headers, config) {
				if(data){
					$scope.diancxx.xuh=data[0];
				}else{
					$scope.diancxx.xuh=null;
				}
		});
	}else if($routeParams.flag=="Update"){
		$scope.modifyDiancxxTitle='修改电厂信息';
		$http.post('diancxx/getOneById',{id:$routeParams.id}).
			success(function(data, status, headers, config) {
				if(data){
					$scope.diancxx=data[0];
					
					for(var i = 0;i < $scope.shengfList.length;i++){
						if($scope.shengfList[i].value==$scope.diancxx.shengfb_id){
							$scope.diancxx.shengfb_id = $scope.shengfList[i].value;
						}
					}
				}
		});
	}
	 
	$scope.saveDiancxx=function(){
		if($("#diancxxAdd_form").valid()){
			if($scope.diancxx.id==null){
				$http.post('common/checkNameExist',{param1:"diancxxb",param2:"mingc",param3:$scope.diancxx.mingc}).
				success(function(data, status, headers, config) {
					if(data){
						if(data[0]>0){
							alert("名称["+$scope.diancxx.mingc+"]已存在，不能使用!");
							return false;
						}else{
							$http.post('common/checkNameExist',{param1:"diancxxb",param2:"quanc",param3:$scope.diancxx.quanc}).
							success(function(data, status, headers, config) {
								if(data){
									if(data[0]>0){
										alert("全称["+$scope.diancxx.quanc+"]已存在，不能使用!");
										return false;
									}else{
										$http.post('diancxx/addDiancxx',{info:angular.toJson($scope.diancxx)}).
									       success(function(data, status, headers, config) {
									    	   if(data[0]==1){
									    		   alert("新增电厂信息成功！");
									    		   $location.path('/diancxx');
									    	   }else{
									    		   alert("新增电厂信息失败！");
									    	   }
									   });
									}
								}
							});
						}
					}
				});
			}else{
				$http.post('diancxx/updateDiancxx',{info:angular.toJson($scope.diancxx)}).
			       success(function(data, status, headers, config) {
			    	   if(data[0]==1){
			    		   alert("修改电厂信息成功！");
			    		   $location.path('/diancxx');
			    	   }else{
			    		   alert("修改电厂信息失败！");
			    	   }
			   });
			}	
		}
	}
	 
	$scope.cancel=function(){
		$location.path('/diancxx');
	};
})