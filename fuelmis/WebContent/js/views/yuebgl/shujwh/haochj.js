gddlapp.controller('haochjCtrl', function($scope,$rootScope,$http,$log,$location) {
	var date = new Date();
	date.setMonth(date.getMonth()-1);
	var riq=date.format('yyyy-MM');

	/*]
	[
	]\
	 */
	var ymId=2;
	$scope.oldValue=0;
	$scope.search = {
		riq : riq,
		diancid : 515
	};
	//查询
	$scope.searchData = function(){
		var riq = $scope.search.riq;
		var dianc = $scope.search.diancid;
		$http.post('yuehc/yueshchjb/getYueshchjbList',{riq:riq,dianc:dianc}).
	    success(function(data, status, headers, config) {
	    	$scope.haochjList = data.data;
	    	// for(var i=0;i<$scope.haochjList.length;i++){
    		// 	if($scope.haochjList[i].fenx=='累计'){
    		// 		$scope.haochjList[i].fady=eval('('+$scope.haochjList[i].fady+')'+"-"+'('+$scope.haochjList[i-1].fady+')')
    		// 		$scope.haochjList[i].gongry=eval('('+$scope.haochjList[i].gongry+')'+"-"+'('+$scope.haochjList[i-1].gongry+')')
    		// 		$scope.haochjList[i].qith=eval('('+$scope.haochjList[i].qith+')'+"-"+'('+$scope.haochjList[i-1].qith+')')
    		// 		$scope.haochjList[i].sunh=eval('('+$scope.haochjList[i].sunh+')'+"-"+'('+$scope.haochjList[i-1].sunh+')')
    		// 		$scope.haochjList[i].diaocl=eval('('+$scope.haochjList[i].diaocl+')'+"-"+'('+$scope.haochjList[i-1].diaocl+')')
    		// 		$scope.haochjList[i].shuifctz=eval('('+$scope.haochjList[i].shuifctz+')'+"-"+'('+$scope.haochjList[i-1].shuifctz+')')
    		// 		$scope.haochjList[i].runxcs=eval('('+$scope.haochjList[i].runxcs+')'+"-"+'('+$scope.haochjList[i-1].runxcs+')')
    		// 	}
    		// }
	    	//禁用input输入功能
    		$http.post('yuebgl/yuebsc/getYueb',
                {search:angular.toJson({riq:riq,dianc:dianc})}).success(function(data) {
    			if(data[ymId].ZHUANGT==1){
    				$(".table td input").prop("disabled",true);
    				$(".table-toolbar button[id$='Btn']").prop("disabled",true);
    			}else{
    				$(".table-toolbar button[id$='Btn']").prop("disabled",false);
    				$(".table input").prop("disabled",false);
    			}
    		})
	    }).error(function (data) {
            alert("查询失败");
        })
	};
	
	$scope.searchData();
	
	$scope.createData_Win = function(){
		$('#myModal_Create').modal('show');
	};
	//生成
	$scope.createData = function(){
		var riq = $scope.search.riq;
		var dianc = $scope.search.diancid;
		$http.post('yuehc/yueshchjb/addYueshchjb',{riq:riq,dianc:dianc}).
			success(function(data, status, headers, config) {
			    if(status==200){
			    	$('#myModal_Create').modal('hide');
			    	alert("生成数据成功！");
			    	$scope.searchData()
			    }else{
			    	$('#myModal_Create').modal('hide');
			    	alert("生成数据失败！");
			    }
		});
		$('#myModal_Create').modal('hide');
	};
	
	$scope.delData_Win = function(){
		$('#myModal_Del').modal('show'); 
	};
//删除
	$scope.delData = function(){
		var riq = $scope.search.riq;
		var dianc = $scope.search.diancid;
		$http.post('yuehc/yueshchjb/delYueshchjb',{riq:riq,dianc:dianc}).
			success(function(data, status, headers, config) {
			    if(status==200){
			    	$('#myModal_Del').modal('hide');
			    	alert("数据删除成功！");
			    	$scope.searchData()
			    }else{
			    	$('#myModal_Del').modal('hide');
			    	alert("数据删除失败！");
			    }
		});
	};
//保存
	$scope.saveData = function(){
		var riq = $scope.search.riq;
		var dianc = $scope.search.diancid;
		$http.post('yuebgl/yuebwh/haochj/saveData',{data:angular.toJson($scope.haochjList)}).
        success(function(data, status, headers, config) {
            if(status==200){
                alert("保存成功！");
                $scope.searchData()
            }else{
                alert("保存失败！");
            }
        });
	};
	
	$scope.printTable = function(){
		$("#example").jqprint();
	}
});