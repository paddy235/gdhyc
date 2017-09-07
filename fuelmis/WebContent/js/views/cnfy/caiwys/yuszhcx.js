gddlapp.controller('yuszhcxCtrl', function($scope, $rootScope,$routeParams, $http,$location) {
	$scope.yuszhcxTitle = '预算综合查询';
	$http.post('yuedyussq/getzaf').success(function(data){ $scope.zafList=data;});
	$scope.search = new Object();
	$scope.search.zafid = -1;
	$scope.search.diancid = 515;
	var d = new Date();
	var vYear = d.getFullYear();
	$scope.search.nianf = vYear;
	var oTable = $('#example').dataTable({
		"processing" : true,
		"ordering" : false,
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
		"sScrollX": "100%",
		"sScrollXInner": "130%",
		"bScrollCollapse": true
	});
	oTable.fnReloadAjax('yuszhsearch/getzonghcxdata?diancid='+$scope.search.diancid+'&nianf='+$scope.search.nianf+'&zafid='+$scope.search.zafid);
	$scope.refresh = function(){
		oTable.fnReloadAjax('yuszhsearch/getzonghcxdata?diancid='+$scope.search.diancid+'&nianf='+$scope.search.nianf+'&zafid='+$scope.search.zafid);
	}
	 $("#datepicker").datepicker({
			format : 'yyyy',
			minViewMode : 2,
			language : "zh-CN",
			autoclose : true
		});
})
