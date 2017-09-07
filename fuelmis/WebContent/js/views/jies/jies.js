gddlapp.controller('jiesCtrl',function($scope, $rootScope, $routeParams, $http, $location) {
	$scope.jiesTitle = '发票维护';
	$scope.search = new Object();
	$scope.search.meikxxbid = -1;
	$scope.search.jiesbid=-1;
//------------------------------------------日期定义----------------------------------------------------
	var d = new Date();
	var vYear = d.getFullYear();
	var vMon = d.getMonth() + 1;
	var vDate = d.getDate();
	$scope.search.qisrq = vYear + "-"+ (vMon < 10 ? "0" + vMon : vMon) + "-"+ (vDate < 10 ? "0" + vDate : vDate);
	$scope.search.zhongzrq=$scope.search.qisrq;
//====================================================================================================	
	$scope.refresh= function() {
		$scope.search.zhongzrq=$scope.search.qisrq;
		oTable.fnReloadAjax('jies/getFapxx?condition='+angular.toJson($scope.search));
	}
	$scope.load= function() {
		$scope.search.jiesbid=-1;
		$http.post('jies/getJiesdhList', {
			id : $scope.search.meikxxbid
		}).success(function(data) {
			$scope.jiesdhList = data;
			$scope.refresh();
		});
	}
	$scope.load();	
//------------------------------------------------页面加载时初始化按键--------------------------------------	
	$("#updatefap").removeClass("btn-info");
	$("#updatefap").attr('disabled', true);
	// $("#fujsc").removeClass("btn-inverse");
	// $("#fujsc").attr('disabled', true);

// ----------------------------------文件上传按钮的弹出菜单功能---------------------------------------
	$scope.uploadFuj = function() {
		// if ($("#example input[type=checkbox]:checked").length < 1) {
		// 	alert("请选择要添加附件的记录！");
		// } else {
		// 	$("#example input[type=checkbox]").each(function() {
		// 		if (this.checked) {
		// 			var id = $(this).attr("id");
		// 			// alert(id);
		// 			$('#pandxx_id').val(id);
					$('#myModal').modal('show');
					load();// 加载选择图片按键
				// }
		// 	});
		// }
	}
	
// -------------------------------添加发票按钮的跳转----------------------------------------------------
	$scope.addjies = function() {	
		$location.path('/jiesAdd');
	}
//---------------------------修改发票按钮的跳转-------------------------------------------------------
	$scope.updatefap = function() {
		$scope.updatetitle = "修改发票信息";
		$scope.search.id = $('input[name="checkId"]:checked').attr("id");// 获取要修改的发票ID
		$location.path('/fapUpdate/' + $scope.search.id + '/'+ $scope.updatetitle);
	}
/*******************************************************************************************************
 * 日期选择
 */
	
	$("#datepicker").datepicker({
		format : 'yyyy-mm-dd',
		minViewMode : 0,
		language : "zh-CN",
		autoclose : true
	});

})
.controller('jiesaddCtrl',function($scope, $rootScope, $routeParams, $http, $location) {
	$scope.addranlzfTitle = $routeParams.title;
	$scope.fapbean = new Object();
	$scope.fapbean.MEIKXXB_ID=-1;
	
	// 读取当前登陆用户
	$http.post('common/getCurrentUser').success(
			function(data, status, headers, config) {
				$scope.fapbean.LURR = data[0].mingc;
			}); 

	// 获取当前日期
	var d = new Date();
	var vYear = d.getFullYear();
	var vMon = d.getMonth() + 1;
	var vDate = d.getDate();
	$scope.fapbean.RIQ = vYear + "-" + (vMon < 10 ? "0" + vMon : vMon)+ "-" + (vDate < 10 ? "0" + vDate : vDate);

	// 当煤矿简称下拉框改变是查询结算单号
	$scope.load= function() {		
		$http.post('jies/getJiesdhList', {
			id : $scope.fapbean.MEIKXXB_ID
		}).success(function(data) {
			$scope.jiesdhList = data;
			data[0].name="";
			$scope.fapbean.JIESB_ID=data[1].value;
		});
	}
	$scope.load();	
	// 从添加页面返回到查询页面
	$scope.cancel = function() {
		$location.path('/jiesGetAll'); 
	}
	
	//添加日期选择插件
	$("#datepicker").datepicker({
		format : 'yyyy-mm-dd',
		minViewMode : 0,
		language : "zh-CN",
		autoclose : true
	});
	//保存发票信息------------------------------------------------------------------------------------------------------
	$scope.saveFap = function() {
		$http.post('jies/addFap', {
			info : angular.toJson($scope.fapbean)
		}).success(function(data) {
			alert("保存成功");						
			$location.path('/jiesGetAll/'
					+ $scope.fapbean.MEIKXXB_ID + '/'
					+ $scope.fapbean.JIESB_ID + '/'
					+ $scope.fapbean.FAPRQ);	
		});
	}
})
.controller('jiesupdateCtrl',function($scope, $rootScope, $routeParams, $http, $location) {
	$scope.fapbean = new Object();
	$scope.jiesdhList = new Object();
	
	// 当煤矿简称下拉框改变是查询结算单号
	// $scope.chaxjsdh = function() {
	// 	$http.post('jies/getJiesb', {
	// 		id : $('#meik').val()
	// 	}).success(function(data) {
	// 		$scope.jiesdhList = data;
	// 	});
	//
	// }
	// 当煤矿简称下拉框改变是查询结算单号
	$scope.chaxjsdh= function() {
		$http.post('jies/getJiesdhList', {
			id : $scope.fapbean.MEIKXXB_ID
		}).success(function(data) {
			$scope.jiesdhList = data;
			data[0].name="";
			$scope.fapbean.JIESB_ID=data[1].value;
		});
	}
	// 当加载更新页面时将要修改的发票信息加载到当前页面
	$http.post('jies/getFapById', {
		id : $routeParams.id
	}).success(function(data) {
		$scope.fapbean=data
		// alert($scope.fapbean.MEIKXXB_ID);
		$scope.chaxjsdh()
	});
	
	// 从更新页面返回到查询页面
	$scope.cancel = function() {
		$location.path('/jiesGetAll');
	}
	
	//添加日期插件
	$("#datepicker").datepicker({
		format : 'yyyy-mm-dd',
		minViewMode : 0,
		language : "zh-CN",
		autoclose : true
	});
	
	//执行更新操作
	$scope.updateFap = function() {
		$http.post('jies/updateFap', {
			info : angular.toJson($scope.fapbean)
		}).success(
				function(data) {
					$location.path('/jiesGetAll/'
							+ $scope.fapbean.MEIKXXB_ID + '/'
							+ $scope.fapbean.JIESB_ID + '/'
							+ $scope.fapbean.FAPRQ);
				});				
	}
})
.controller('fapcxCtrl',function($scope, $rootScope, $routeParams, $http, $location) {
	$scope.jiesTitle = '发票维护';
	$scope.search = new Object();
	$scope.search.meikxxbid = -1;
	$scope.search.jiesbid=-1;
//-----------------------------------初始化日期-------------------------------------------------------------------------
	var d = new Date();
	var vYear = d.getFullYear();
	var vMon = d.getMonth() + 1;
	var vDate = d.getDate();
	$scope.search.qisrq = vYear + "-" + (vMon < 10 ? "0" + vMon : vMon)+ "-01";
	$scope.search.zhongzrq = vYear + "-"+ (vMon < 10 ? "0" + vMon : vMon) + "-"+ (vDate < 10 ? "0" + vDate : vDate);			
/**********************************************************************************************************************
 * 按条件查询
 */
	$scope.refresh= function() {
		oTable.fnReloadAjax('jies/getFap?condition='+angular.toJson($scope.search));
	}
	$scope.load= function() {
		$scope.search.jiesbid=-1;
		$http.post('jies/getJiesdhList', {
			id : $scope.search.meikxxbid
		}).success(function(data) {
			$scope.jiesdhList = data;
			oTable.fnReloadAjax('jies/getFap?condition='+angular.toJson($scope.search));
		});
	}
	$scope.load();			
/************************************************************************************************************************
 *显示日期
 */
	$("#datepicker").datepicker({
		format : 'yyyy-mm-dd',
		minViewMode : 0,
		language : "zh-CN",
		autoclose : true
	});
	$("#datepicker1").datepicker({
		format : 'yyyy-mm-dd',
		minViewMode : 0,
		language : "zh-CN",
		autoclose : true
	});
});
