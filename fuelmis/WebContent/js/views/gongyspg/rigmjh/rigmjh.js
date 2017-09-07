gddlapp.controller('rigmjhCtrl', function($scope, $rootScope, $http, $log,$location) {
	$scope.rigmjhTitle = '日供煤计划维护';
	$scope.search = new Object();
	$scope.array = new Array();
	$scope.delArray = new Array();
	$scope.search.gongysb_id = -1;
	//--------------------------------------日期定义--------------------------------------------------------------------------

	var begin=new Date();
	var end=new Date();
	//new Date(begin.setMonth((new Date().getMonth()-1)));
	var begintime= begin.format("yyyy-MM");
	var endtime=end.format("yyyy-MM-dd");
	//-----------------------------------------------------------------------------------------------------------------------
	$scope.strdate = begintime+'-01';
	$scope.enddate = endtime;
	$scope.zhuangt = 0;
	$scope.fabrq = "来煤日期";
	$scope.gongysb_id = -1;
 
	$scope.fabrqList = [ {
		fabrq : '来煤日期'
	}, {
		fabrq : '发布日期'
	} ];

	var d = new Date();
	var vYear = d.getFullYear();
	var vMon = d.getMonth() + 1;
	var vDate = d.getDate();

	$scope.riq = vYear + "-" + (vMon < 10 ? "0" + vMon : vMon) + "-"
			+ (vDate < 10 ? "0" + vDate : vDate);

	// 查询
	$scope.refresh = function() {
		var gongysb_id = $scope.gongysb_id
		var zhuangt = $scope.zhuangt;
		var fabrq = $scope.fabrq
		var strdate = $scope.strdate;
		var enddate = $scope.enddate

		$http.post(
				'gongyspg/rigmjh/searchRigmjhList?gongysb_id=' + gongysb_id
						+ '&zhuangt=' + zhuangt + '&fabrq=' + fabrq
						+ '&strdate=' + strdate + '&enddate=' + enddate)
				.success(function(data) {
					$scope.list = data;
					$scope.array = [];
				})
	}
	$scope.refresh();
	// 添加
	$scope.addRigmjh = function() {
		var obj = new Object();
		obj = {
			RIQ : $scope.riq,
			JIHML : 0,
			TIAOZL : 0,
			FABRQ : $scope.riq
		}
		$scope.array.push(obj);
	}
	Array.prototype.remove = function(dx) {
		if (isNaN(dx) || dx > this.length) {
			return false;
		}
		this.splice(dx, 1);
	}

	// 删除添加行
	$scope.del = function(target) {
		var $td = $(target).parent();
		id = $td.attr("id");
		c = $td.attr('class');
		if (c == "update") {
			$scope.delArray.push($scope.list.listArray[id].ID);
			$scope.list.listArray.remove(id);
		} else {
			$scope.array.remove(id);
		}
	}
	// 保存
	$scope.saveRigmjh = function() {
		for (var i = 0; i < $scope.list.listArray.length; i++) {
			$http.post('gongyspg/rigmjh/updateRigmjh', {
				data : angular.toJson($scope.list.listArray[i])
			}).success(function(data) {
				$scope.refresh();
				// alert("保存成功");
			});
		}
		for (var i = 0; i < $scope.array.length; i++) {
			$http.post('gongyspg/rigmjh/insertRigmjh', {
				data : angular.toJson($scope.array[i])
			}).success(function(data) {
				$scope.refresh();
			});
		}

		alert("保存成功");

	}

	/* 发布操作 */
	$scope.publishRigmjh = function() {
		$scope.fabArray = []
		$("input[type=checkbox]:checked").not("#selectAll").each(
				function() {
					if ($scope.list.listArray[$(this).attr("id")].ID != null) {
						$scope.fabArray.push($scope.list.listArray[$(this)
								.attr("id")].ID);

					}
				})
		if ($scope.fabArray.length <= 0) {
			alert("请选择要发布的行！");
		} else {
			$http({
				method : 'POST',
				url : 'gongyspg/rigmjh/publishRigmjh',
				data : angular.toJson($scope.fabArray),
				headers : {
					'Content-Type' : 'application/json'
				}
			}).success(function(data) {
				$scope.refresh();
				alert("发布成功！");
			});

		}
	}

	/* 删除操作 */
	$scope.delRigmjh = function() {
		$scope.fabArray = []
		$("input[type=checkbox]:checked").not("#selectAll").each(
				function() {
					if ($scope.list.listArray[$(this).attr("id")].ID != null) {
						$scope.fabArray.push($scope.list.listArray[$(this)
								.attr("id")].ID);

					}
				})
		if ($scope.fabArray.length <= 0) {
			alert("请选择要删除的行！");
		} else {
			$http({
				method : 'POST',
				url : 'gongyspg/rigmjh/delRigmjh',
				data : angular.toJson($scope.fabArray),
				headers : {
					'Content-Type' : 'application/json'
				}
			}).success(function(data) {
				$scope.refresh();
				alert("删除成功！");
			});

		}
	}

})
