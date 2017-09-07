gddlapp.controller('meishanCtrl', function($scope, $rootScope,$routeParams, $http,$location) {
	$scope.meishanTitle = '煤山信息维护';
	$scope.search = new Object();
	var oTable = $('#example').dataTable({
		"processing" : true,
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
        }]
	});
	$("#datepicker").datepicker({
			format : 'yyyy',
			minViewMode : 2,
			language : "zh-CN",
			autoclose : true
	});
	oTable.fnReloadAjax('peimeixinxiwh/meishanlist');
	$scope.addfeiyxmfl = function(){
		$scope.addtitle ="添加煤山信息";
		$location.path('/addorupdatemeishanweih/0/'+$scope.addtitle); 
	}
	
	$scope.updatefeiyxmfl = function(){
		$scope.updatetitle ="修改煤山信息";
		$scope.search.id = $('input[name="checkId"]:checked').attr("id");
		$location.path('/addorupdatemeishanweih/'+$scope.search.id+'/'+$scope.updatetitle); 
	}
})
.controller('addorupdatemeishanCtrl', function($scope, $rootScope,$routeParams, $http,$location) {
	$scope.addorupdatemeishanTitle = $routeParams.title;
	$scope.fenlkjbean = new Object();
	if($routeParams.id != 0){
		$http.post('peimeixinxiwh/getMeishanById',{id:$routeParams.id}).success(function(data){
			$scope.fenlkjbean.MEISMC =data.MEISMC;
			$scope.fenlkjbean.DIANCXXB_ID =data.DIANCXXB_ID;
			$scope.fenlkjbean.DIANCXXB_ID_CN =data.DIANCXXB_ID_CN;
			$scope.fenlkjbean.MEICMC =data.MEICMC;
			$scope.fenlkjbean.QNET_AR =data.QNET_AR;
			$scope.fenlkjbean.S =data.S;
			$scope.fenlkjbean.V =data.V;
			$scope.fenlkjbean.ID =data.ID;
		});
	}else{
		$scope.fenlkjbean.ID = $routeParams.id;
	}
	$scope.cancel = function(){
		$location.path('/meishanweih'); 
	}
	$scope.saveFenyxm = function(){
		$http.post('peimeixinxiwh/meishanadd',{info:angular.toJson($scope.fenlkjbean)}).success(function(data){
			alert(data);
			$location.path('/meishanweih');
		});
	}
	
});
