gddlapp.controller('ribgsCtrl', function($scope,$rootScope,$http,$log,$location) {
	$scope.ribgsTitle='日报估收';

	var date = new Date();
	var year = date.getFullYear();
	var month = date.getMonth()+1<10?'0'+(date.getMonth()+1):date.getMonth()+1;
	var day = date.getDate()<10?'0'+date.getDate():date.getDate();

	$scope.search = {
		sDate : year+'-'+month+'-'+day,
		diancid : 515
	}

	$http.post('diaoygl/ribgl/getAll2',{diancxxb_id:$scope.search.diancid,riq:$scope.search.sDate}).
	success(function(data, status, headers, config) {
		$scope.ribList = data;
	});

	$scope.searchData = function() {
		$http.post('diaoygl/ribgl/getAll2',{diancxxb_id:$scope.search.diancid,riq:$scope.search.sDate}).
		success(function(data, status, headers, config) {
			$scope.ribList = data;
			for(var i=0;i<data.length;i++){
				if(data[i].ZHUANGT==1){
					$(".table td input").prop("disabled",true);
					$(".table-toolbar button[id$='Btn']").prop("disabled",true);
					break
				}else{
					$(".table-toolbar button[id$='Btn']").prop("disabled",false);
					$(".table input").prop("disabled",false);
				}
			}
		});
	}

	$scope.createData = function() {
		document.getElementById('tt').innerHTML = $scope.search.sDate;
		$('#myModal_Del').modal('show');
	}

	$scope.create = function() {
		$('#myModal_Del').modal('hide');
		$http.post('diaoygl/ribgl/createData2',{diancxxb_id:$scope.search.diancid,riq:$scope.search.sDate}).
		success(function(data, status, headers, config) {
			if(data[0]>0){
				$http.post('diaoygl/ribgl/getAll2',{diancxxb_id:$scope.search.diancid,riq:$scope.search.sDate}).
				success(function(data, status, headers, config) {
					$scope.ribList = data;
				});
			}else{
				alert($scope.search.sDate+"数据不完整，请补全！");
			}
		});
	};

	$scope.save = function() {
		var flag = true;
		for(var i = 0;i<$scope.ribList.length;i++){
			if($scope.ribList[i].REZ==0){
				flag = false;
			}else{
				flag = true;
			}
		}

		if(flag){
			$http.post('diaoygl/ribgl/save2',{strList:angular.toJson($scope.ribList)}).
			success(function(data, status, headers, config) {
				$http.post('diaoygl/ribgl/getAll2',{diancxxb_id:$scope.search.diancid,riq:$scope.search.sDate}).
				success(function(data, status, headers, config) {
					$scope.ribList = data;
				});
			});
		}else{
			alert("质量数据还未生成，请进行核对！");
		}
	};
	$scope.changeMeij = function(){
		var meij = 0;
		var yunj = 0;
		var laiml = 0;
		var length = $scope.ribList.length-1;
		for(var i =0; i<length;i++){
			meij += Number($scope.ribList[i].MEIJ);
			yunj += Number($scope.ribList[i].YUNJ);
			laiml += Number($scope.ribList[i].LAIMSL);
		}
		$scope.ribList[length].MEIJ = meij;
		$scope.ribList[length].YUNJ = Number(yunj/laiml).toFixed(2);
		//$scope.ribList[length].LAIMSL = laiml;
	};
	$scope.changeVal = function(args) {
		var flag = true;
		for(var i = 0;i<$scope.ribList.length;i++){
			if($scope.ribList[i].REZ==0){
				flag = false;
			}else{
				flag = true;
			}
		}

		if(flag){
			$http.post('diaoygl/ribgl/save2',{strList:angular.toJson($scope.ribList)}).
			success(function(data, status, headers, config) {
				$http.post('diaoygl/ribgl/getAll2',{diancxxb_id:$scope.search.diancid,riq:$scope.search.sDate}).
				success(function(data, status, headers, config) {
					$scope.ribList = data;
				});
			});
		}
	}

	//上传
	$scope.RibgsShangc = function (){
		$http.post("diaoygl/ribgl/getRibgsShangc",{search:angular.toJson($scope.search)}).success(function (data){
			alert("上传成功！");
			$scope.searchData();
		});
	}
})