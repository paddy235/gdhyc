gddlapp.controller('yunschedCtrl', function($scope, $rootScope,$routeParams, $http,$location) {
	$scope.yunschedTitle = '运输车队信息维护';
	$scope.search = new Object();
	var oTable = $('#example').dataTable({
		"processing" : true,
		"showRowNumber": true,
		"language" : {
			"sLengthMenu" : "每页显示 _MENU_条",
			"sZeroRecords" : "没有找到符合条件的数据",
			"sProcessing" : "数据加载中...",
			"sInfo" : "当前第 _START_ - _END_ 条　共计 _TOTAL_ 条",
			"sInfoEmpty" : "没有记录",
			"sInfoFiltered" : "(从 _MAX_ 条记录中过滤)",
			"sSearch" : "搜索：",
			"oPaginate" : {
				"sFirst" : "首页",
				"sPrevious" : "前一页",
				"sNext" : "后一页",
				"sLast" : "尾页"
			}
		},
		"aoColumnDefs": [{
            "sClass": "center",
            "mRender": function(oObj, sVal) {
            	 return '<input type="checkbox" id="' + oObj + '" name="checkId" onclick="check(this)" />';
            },
            "bSortable": false,
            "aTargets": [0]
        },{
              "targets": [4],
              "visible": false
            }]
	});
	$("#datepicker").datepicker({
			format : 'yyyy',
			minViewMode : 2,
			language : "zh-CN",
			autoclose : true
	});
	oTable.fnReloadAjax('peimeixinxiwh/yunscdlist');
	$scope.changestate = function(){
		 $.post("peimeixinxiwh/changeyunscdstate",//异步处理动态页面
			     {id:$('input[name="checkId"]:checked').attr("id")},
			     function(data){//data为反回值，function进行反回值处理
			    	 oTable.fnReloadAjax('peimeixinxiwh/yunscdlist');
		});
   }
	$scope.addfeiyxmfl = function(){
		$scope.addtitle ="添加运输车队信息";
		$location.path('/addorupdateyunscdweih/0/'+$scope.addtitle); 
	}
	
	$scope.updatefeiyxmfl = function(){
		$scope.updatetitle ="修改运输车队信息";
		$scope.search.id = $('input[name="checkId"]:checked').attr("id");
		$location.path('/addorupdateyunscdweih/'+$scope.search.id+'/'+$scope.updatetitle); 
	}
})
.controller('addorupdateyunscdCtrl', function($scope, $rootScope,$routeParams, $http,$location) {
	$scope.addorupdateyunscdTitle = $routeParams.title;
	$scope.fenlkjbean = new Object();
	if($routeParams.id != 0){
		$http.post('peimeixinxiwh/getYunscdById',{id:$routeParams.id}).success(function(data){
			$scope.fenlkjbean.CHEDMC =data.CHEDMC;
			$scope.fenlkjbean.ID =data.ID;
		});
	}else{
		$scope.fenlkjbean.ID = $routeParams.id;
	}
	$scope.cancel = function(){
		$location.path('/yunscdweih'); 
	}
	$scope.saveFenyxm = function(){
		$http.post('peimeixinxiwh/yunscdadd',{info:angular.toJson($scope.fenlkjbean)}).success(function(data){
			alert(data);
			$location.path('/yunscdweih');
		});
	}
	
});
