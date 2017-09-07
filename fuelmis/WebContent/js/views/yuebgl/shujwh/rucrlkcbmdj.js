gddlapp.controller('rucrlkcbmdj1', function($scope,$rootScope,$http,$log,$location) {
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
	};
	$http.post('rucrlkcbmdj/biaomdj/getAll').
	    success(function(data, status, headers, config) {
	    	$scope.rucslList = data.data;
	});
	$http.post('rucrlkcbmdj/biaomdj/check').
	    success(function(data, status, headers, config) {
	    	if(data[0]==0){
	    		$('#createBtn').hide();
	    		$('#delBtn').hide();
	    		$('#saveBtn').hide();
	    	}
	});
   /* $scope.searchData = function() {
	 $http.post('rucrlkcbmdj/getAll',{condition:angular.toJson($scope.search)}).success(function(data) {
	 document.getElementById("report").innerHTML = data[0].html;

	 // var totalPage = data[0].pageCount;
	 //
	 // if(totalPage>1){
	 //     for(var i = 2;i <= totalPage;i++){
	 //         $('#reportpage'+i).css('display','none');
	 //     }
	 // }
	 });*/
	$scope.searchData = function(){
		var riq = $scope.search.riq;
		//var dianc = $scope.search.diancid;

		$http.post('rucrlkcbmdj/biaomdj/getAll?riq='+riq).
	    	success(function(data, status, headers, config) {
            $scope.rucslList=data.data;

	    		//禁用input输入功能
	    		/*$http.post('rucrlkcbmdj/getYueb',{riq:riq,dianc:dianc}).success(function(data) {
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
	    		});*/
		});
		$http.post('rucrlkcbmdj/biaomdj/check?riq='+riq).
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

		$http.post('rucrlkcbmdj/biaomdj/createData',{riq:riq}).
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
		//var dianc = $scope.search.diancid;
		$http.post('rucrlkcbmdj/biaomdj/delData',{riq:riq}).
			success(function(data, status, headers, config) {
			    if(data[0]==1){
			    	$('#myModal_Del').modal('hide');
			    	alert("数据删除成功！");
			    	$http.post('rucrlkcbmdj/biaomdj/getAll?riq='+riq).
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
		//var dianc = $scope.search.diancid;
		$http.post('rucrlkcbmdj/biaomdj/saveData',{riq:riq,rucslList:angular.toJson($scope.rucslList)}).
	       success(function(data, status, headers, config) {
	    	   if(data[0]==1){
			    	alert("保存成功！");
			    	$http.post('rucrlkcbmdj/biaomdj/getAll?riq='+riq).
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
});