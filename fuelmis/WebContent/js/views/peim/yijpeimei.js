gddlapp.controller('yijpeimeiCtrl', function($scope, $rootScope,$routeParams, $http,$location) {
	$scope.yijpeimeiTitle = '一级配煤';
	$scope.search = new Object();
	var d = new Date();
	var vYear = d.getFullYear();
	var vMon = d.getMonth() + 1;
	var vDay = d.getDate();
	$scope.search.riq = vYear+"-"+(vMon<10 ? "0" + vMon : vMon)+"-"+(vDay<10 ? "0" + vDay : vDay);
	var oTable = $('#example').dataTable({
		"ordering" : false,
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
		}
	});
	oTable.fnReloadAjax('peimeisearch/selectyijpeimei?riq='+ $scope.search.riq);
	$scope.refresh = function(){
		oTable.fnReloadAjax('peimeisearch/selectyijpeimei?riq='+ $scope.search.riq);
	}
	 $("#datepicker").datepicker({
			format : 'yyyy-mm-dd',
			minViewMode : 0,
			language : "zh-CN",
			autoclose : true
		});
	
});
