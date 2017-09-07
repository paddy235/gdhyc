gddlapp.controller('rulrzCtrl', function($scope,$rootScope,$http,$log,$location) {
	$scope.rulrzTitle='入炉热值';
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
	$scope.search = {
		riq : year+'-'+month,
		diancid : 515
	};
	
	$http.post('yuebgl/yuebwh/rulrz/getAll').
	    success(function(data, status, headers, config) {
	    	$scope.rulrzList = data.data;
	});
	
	$http.post('yuebgl/yuebwh/rulrz/check').
	    success(function(data, status, headers, config) {
	    	if(data[0]==0){
	    		$('#createBtn').hide();
	    		$('#delBtn').hide();
	    		$('#saveBtn').hide();
	    	}
	});
	
	$scope.searchData = function(){
		var riq = $scope.search.riq;
		var dianc = $scope.search.diancid;
		$http.post('yuebgl/yuebwh/rulrz/getAll?riq='+riq+'&dianc='+dianc).
	    	success(function(data, status, headers, config) {
	    		$scope.rulrzList = data.data;
	    		//禁用input输入功能
//	    		$http.post('yuebgl/yuebsc/getYueb',{riq:riq,dianc:dianc}).success(function(data) {
//	    			console.log(data);
//	    			if(data[ymId].ZHUANGT==1){
//	    				$(".table td input").prop("disabled",true);
//	    				$(".table-toolbar button[id$='Btn']").prop("disabled",true);
//	    			}else{
//	    				$(".table-toolbar button[id$='Btn']").prop("disabled",false);
//	    				$(".table input").prop("disabled",false);
//	    			}
//	    		});
		});
		
	/*	$http.post('yuebgl/yuebwh/rulrz/check?riq='+riq+'&dianc='+dianc).
		    success(function(data, status, headers, config) {
		    	if(data[0]==0){
		    		$('#createBtn').hide();
		    		$('#delBtn').hide();
		    		$('#saveBtn').hide();
		    	}else{
		    		$('#createBtn').show();
		    		$('#delBtn').show();
		    		$('#saveBtn').show();
		    	}
		});*/
	}
	
	$scope.searchData();
	
	$scope.createData_Win = function(){
		$('#myModal_Create').modal('show');
	}
	
	$scope.createData = function(){
		var riq = $scope.search.riq;
		var dianc = $scope.search.diancid;
		$http.post('yuebgl/yuebwh/rulrz/createData',{riq:riq,dianc:dianc}).
			success(function(data, status, headers, config) {
			    if(data[0]==1){
			    	$('#myModal_Create').modal('hide');
			    	alert("生成数据成功！");
			    	$http.post('yuebgl/yuebwh/rulrz/getAll?riq='+riq+'&dianc='+dianc).
			    		success(function(data, status, headers, config) {
			    			$scope.rulrzList = data.data;
			    	});
			    }else{
			    	$('#myModal_Create').modal('hide');
			    	alert("生成数据失败！");
			    }
		});
		$('#myModal_Create').modal('hide');
	}
	
	$scope.delData_Win = function(){
		$('#myModal_Del').modal('show');
	}

	$scope.delData = function(){
		var riq = $scope.search.riq;
		var dianc = $scope.search.diancid;
		$http.post('yuebgl/yuebwh/rulrz/delData',{riq:riq,dianc:dianc}).
			success(function(data, status, headers, config) {
			    if(data[0]==1){
			    	$('#myModal_Del').modal('hide');
			    	alert("数据删除成功！");
			    	$http.post('yuebgl/yuebwh/rulrz/getAll?riq='+riq+'&dianc='+dianc).
			    		success(function(data, status, headers, config) {
			    			$scope.rulrzList = data.data;
			    	});
			    }else{
			    	$('#myModal_Del').modal('hide');
			    	alert("数据删除失败！");
			    }
		});
	}

	$scope.saveData = function(){
		var riq = $scope.search.riq;
		var dianc = $scope.search.diancid;
		$http.post('yuebgl/yuebwh/rulrz/saveData',{riq:riq,dianc:dianc,rulrzList:angular.toJson($scope.rulrzList)}).
	       success(function(data, status, headers, config) {
	    	   if(data[0]==1){
			    	alert("保存成功！");
			    	$http.post('yuebgl/yuebwh/rulrz/getAll?riq='+riq+'&dianc='+dianc).
			    		success(function(data, status, headers, config) {
			    			$scope.rulrzList = data.data;
			    	});
			    }else{
			    	alert("保存失败！");
			    }
	    });
	}
	
	$scope.printTable = function(){
		$("#example").jqprint();
	}
});