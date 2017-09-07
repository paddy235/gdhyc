gddlapp.controller('shangccwCtrl', function($scope,$rootScope,$http,$log,$location) {
	$scope.search = new Object();
	//--------------------------------------日期定义--------------------------------------------------------------------------

	var begin=new Date();	
	var riq= begin.format("yyyy-MM");
//	$scope.jiesdhead=[{title:"结算单号"},{title:"供货人"},{title:"收款单位"},{title:"结算数量"},{title:"单价"},{title:"含税金额"},{title:"不含税金额"},{title:"税率"},{title:"税款"},
//	                  {title:"状态"}];
	//--------------------------------------------------页面数据初始化---------------------------------------------------------------------
	$scope.search = {
		riq : riq,
		leix:0
	};
	$scope.leixList=[{name:'结算单',value:0},{name:'暂估',value:1},{name:'耗用',value:2}]
	$scope.columnDefs = [{
		"sClass": "center",
		"mRender": function (oObj, sVal, row) {
			return '<input  type="checkbox" id="' + oObj + '" name="checkId"   />';
		},
		"bSortable": false,
		"aTargets": [0]
	}]
	$scope.refresh = function(){
		$http.post('kucgl/shangccw/getList',{search:angular.toJson($scope.search)}).success(function (data) {
			if($scope.search.leix==0){
				$scope.list=data;
			}else if($scope.search.leix==1){
				$scope.zanglist=data;
			}else{
				$scope.haoylist=data;
			}
//			$scope.thead=$scope.jiesdhead;
		}).error(function(data){
			alert("查询数据出错!");
		})
	}
	$scope.refresh()
	//---------------------------------------------上报财务----------------------------------------------------------------------------
	$scope.shangbcw=function(){
		var array=new Array();
		$('#example tbody input:checked').each(function(){
			array.push(this.id);
		})	
		$http.post('kucgl/shangccw/shangbcw',{ids:angular.toJson(array),search:angular.toJson($scope.search)}).success(function (data) {
			if(data.RSP_CODE=='0'){
				alert("上报财务成功!");
			}else{
				alert("上报财务失败!\n"+data.RSP_MSG);
				
			}		
		}).error(function(data){
			alert("上报数据出错!");
		})
	}
	//-----------------------------------------------------------------------------------------------------------------------
	$scope.change=function(){
		
	}
	$("#datepicker1").datepicker({
		format : 'yyyy-mm',
		minViewMode : 1,
		language : "zh-CN",
		autoclose : true
	})
})

