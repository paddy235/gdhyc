gddlapp.controller('zhilhjblCtrl', function($scope,$rootScope,$http,$log,$location) {
	$scope.diaodjh = new Object()
	$scope.search = new Object();
	$scope.search.diancid = 515; 
	$scope.search.gongys = -1;
	$scope.title='添加调度计划'
	$scope.index=0;
	//-------------------------------------------日期处理--------------------------------------------------
	var date = new Date();
	var year = date.getFullYear(); 
	var month = date.getMonth()+1<10?'0'+(date.getMonth()+1):date.getMonth()+1;
	var day = date.getDate()<10?'0'+date.getDate():date.getDate();

	$scope.search = {
		sDate : year+'-'+month+'-01',
		eDate : year+'-'+month+'-'+day,
		diancid : 515,
		gongysid:-1
	};
	//----------------------------------------------------------------------------------------------------
	$scope.generateJihdh=function(diaodjh){
		$http.post('diaoygl/diaodgl/generateJihdh', {
			riq : diaodjh.RIQ
		}).success(function(data) {
			diaodjh.JIHDH=data
		});
	}
	//----------------------------------------------------------------------------------------------------
	 $scope.columnDefs =[{
         "sClass": "center",
         "mRender": function(oObj, sVal) {
             return '<input type="checkbox" id="' + oObj + '" name="checkId" onclick="check(this)" />';
         },
         "bSortable": false,
         "aTargets": [0]
     }]
	//----------------------------------------------------------------------------------------------------
	$scope.load=function(){
		$http.post('yansgl/getZhilhjbl', {
			condition : angular.toJson($scope.search)
		}).success(function(data) { 
			$scope.dataList =data 
		});
	}
	$scope.load();

	
})
