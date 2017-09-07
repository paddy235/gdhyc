gddlapp.controller('zhibqkCtrl', function($scope,$http,$log,$location) {
	$scope.zhibqkTitle='指标情况';
	var date = new Date();
	var year = date.getFullYear();
	var month = date.getMonth();
	if(month<10){
		if(month==0){
			year-=1;
			month=12;
		}else{
			month='0'+date.getMonth();
		}	
	}
	var ymId=5;
	$scope.search = {
		riq : year+'-'+month,
		diancid : 515
	};
	
	$http.post('yuebgl/yuebwh/zhibqk/getAll').
	    success(function(data, status, headers, config) {
	    	$scope.zhibqkList = data.data;
	});
	


	$scope.searchData = function(){
		var riq = $scope.search.riq;
		var dianc = $scope.search.diancid;
		$http.post('yuebgl/yuebwh/zhibqk/getAll?riq='+riq+'&dianc='+dianc).
	    	success(function(data, status, headers, config) {
	    		$scope.zhibqkList = data.data;
	    		//禁用input输入功能
	    		$http.post('yuebgl/yuebsc/getYueb',{riq:riq,dianc:dianc}).success(function(data) {
	    			if(data[ymId].ZHUANGT==1){
	    				$(".table td input").prop("disabled",true);
	    				$(".table-toolbar button[id$='Btn']").prop("disabled",true);
	    			}else{
	    				$(".table-toolbar button[id$='Btn']").prop("disabled",false);
	    				$(".table input").prop("disabled",false);
	    			}
	    		});
		});
		
	}
	
	$scope.delData_Win = function(){
		$('#myModal_Del').modal('show');
	}

	 $scope.saveData = function () {
	        var riq = $scope.search.riq;
	        var dianc = $scope.search.diancid;
	        var id = $scope.zhibqkList[0].yuezbId;
	        $http.post('yuebgl/yuebwh/zhibqk/saveData', {
	            riq: riq,
	            dianc: dianc,
	            yuezbId: id,
	            zhibqkList: angular.toJson($scope.zhibqkList)
	        }).success(function (data, status, headers, config) {
	            $http.post('yuebgl/yuebwh/zhibqk/getAll?riq=' + riq + '&dianc=' + dianc).success(function (data, status, headers, config) {
	                $scope.zhibqkList = data.data;
	                $http.post('yuebgl/yuebwh/zhibqk/updateAll', {
	                    riq: riq,
	                    dianc: dianc,
	                    zhibqkList: angular.toJson($scope.zhibqkList)
	                }).success(function (data, status, headers, config) {
	                    if (data == -1) {
	                        alert("保存失败！");
	                    } else {

	                        $http.post('yuebgl/yuebwh/zhibqk/saveLeij?riq=' + riq + '&dianc=' + dianc).success(function (data, status, headers, config) {
	                            alert("保存成功！");
	                            $scope.searchData();
	                        });

	                    }
	                });

	            });
	        });

	    }
	
	$scope.delData = function(){
		var riq = $scope.search.riq;
		var dianc = $scope.search.diancid;
		$http.post('yuebgl/yuebwh/zhibqk/delData',{riq:riq,dianc:dianc}).
			success(function(data, status, headers, config) {
			    if(data>=1){
			    	$('#myModal_Del').modal('hide');
			    	alert("数据删除成功！");
			    	$http.post('yuebgl/yuebwh/zhibqk/getAll?riq='+riq+'&dianc='+dianc).
			    		success(function(data, status, headers, config) {
			    			$scope.zhibqkList = data.data;
			    	});
			    	
			    }else{
			    	$('#myModal_Del').modal('hide');
			    	alert("数据删除失败！");
			    }
		});
	}


	
	$scope.printTable = function(){
		$("#example").jqprint();
	}
});