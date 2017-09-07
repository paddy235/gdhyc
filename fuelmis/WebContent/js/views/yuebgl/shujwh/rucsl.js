gddlapp.controller('rucslCtrl', function($scope,$rootScope,$http,$log,$location) {
	$scope.rucslTitle='入厂数量';
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
	var ymId=0;
	$scope.search = {
		riq : year+'-'+month,
		diancid : 515
	};
	
	$http.post('yuebgl/yuebwh/rucsl/getAll').
	    success(function(data, status, headers, config) {
	    	$scope.rucslList = data.data;
	});
	
	$http.post('yuebgl/yuebwh/rucsl/check').
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
		$http.post('yuebgl/yuebwh/rucsl/getAll?riq='+riq+'&dianc='+dianc).
	    	success(function(data, status, headers, config) {
	    		var d=data.data
	    		$scope.rucslList  = []
	    		var j=0
	    		if(d.length!=0){
	    			for(var i=0;i<d.length;i++){
		    			if(d[i].ID==-1){
		    				$scope.rucslList[j]=d[i]
		    				j++
		    			}else if(d[i].FENX=='累计'&&d[i].JINGZ!=0){
		    				$scope.rucslList[j]=d[i-1]
		    				j++
		    				$scope.rucslList[j]=d[i]
		    				j++
		    				i++
		    			}else{

						}
	    			}
	    		}
	    		//禁用input输入功能
	    		$http.post('yuebgl/yuebsc/getYueb',{riq:riq,dianc:dianc}).success(function(data) {
	    			//如果以后页面名称能对应上后即可取消掉页面id，循环data【object】中的MINGC是否等于页面名称即可
//	    			for(var i=0;i<data.length;i++){
//	    				data[i].MINGC
//	    			}
	    			if(data[ymId].ZHUANGT==1){
	    				//$(".table td input").prop("disabled",true);
	    				$(".table-toolbar button[id$='Btn']").prop("disabled",true);
	    			}else{
	    				$(".table-toolbar button[id$='Btn']").prop("disabled",false);
	    			//	$(".table input").prop("disabled",false);
	    			}
	    		});
		});
		
		$http.post('yuebgl/yuebwh/rucsl/check?riq='+riq+'&dianc='+dianc).
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
		});
	}
	
	$scope.searchData();
	
	$scope.createData_Win = function(){
		$('#myModal_Create').modal('show');
	}
	
	$scope.createData = function(){
		var riq = $scope.search.riq;
		var dianc = $scope.search.diancid;
		$http.post('yuebgl/yuebwh/rucsl/createData',{riq:riq,dianc:dianc}).
			success(function(data, status, headers, config) {
			    if(data[0]==1){
			    	$('#myModal_Create').modal('hide');
			    	alert("生成数据成功！");
			    	$scope.searchData();
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
		$http.post('yuebgl/yuebwh/rucsl/delData',{riq:riq,dianc:dianc}).
			success(function(data, status, headers, config) {
			    if(data[0]==1){
			    	$('#myModal_Del').modal('hide');
			    	alert("数据删除成功！");
			    	$http.post('yuebgl/yuebwh/rucsl/getAll?riq='+riq+'&dianc='+dianc).
			    		success(function(data, status, headers, config) {
			    			$scope.rucslList = data.data;
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
		$http.post('yuebgl/yuebwh/rucsl/saveData',{riq:riq,dianc:dianc,rucslList:angular.toJson($scope.rucslList)}).
	       success(function(data, status, headers, config) {
	    	   if(data[0]==1){
			    	alert("保存成功！");
			    	$http.post('yuebgl/yuebwh/rucsl/getAll?riq='+riq+'&dianc='+dianc).
			    		success(function(data, status, headers, config) {
			    			$scope.rucslList = data.data;
			    	});
			    }else{
			    	alert("保存失败！");
			    }
	    });
	}
	
	$scope.printTable = function(){
		$("#example").jqprint();
	}
	// $scope.$watch($scope.rucslList,function(newValue,oldValue, scope){
    //
	// 	console.log(newValue);
    //
	// 	console.log(oldValue);
    //
	// },true);
});