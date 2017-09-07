gddlapp.controller('kuczzCtrl', function($scope, $rootScope, $routeParams, $http, $location) {
	// $scope.jiesTitle = '发票维护';
	$scope.search = new Object();
	$scope.search.meikxxbid = -1;
	$scope.kuczzArray = new Array();
	$scope.kuczzBean = new Object();

	// 启用按钮置灰
	$("#on").removeClass("btn-primary");
	$("#on").attr('disabled', true);
	$scope.load = function() {
		$http.post('kucgl/getKuczz').success(function(data) {
			$scope.kuczzList = data;
			$scope.kuczzArray = [];
		});
	}

	$scope.load();
	$scope.add = function() {

		var obj = new Object();
		obj = {
			name : "",
			value : {
				ZHUANGT : "启用"
			}
		}
		$scope.kuczzArray.push(obj);
	}
	$scope.on = function(target) {
		$("#save").addClass("btn-success");
		$("#save").attr('disabled', false);
		var $input = $(target).parent();
		id = $input.attr("id");
		c = $input.attr('class');
		if (c == "update") {
			$scope.kuczzList[id].value.ZHUANGT = "启用";
		} else {
			$scope.kuczzArray[id].value.ZHUANGT = "启用";
		}
	}
	$scope.off = function(target) {
		$("#save").addClass("btn-success");
		$("#save").attr('disabled', false);
		var $input = $(target).parent();
		id = $input.attr("id");
		c = $input.attr('class');
		if (c == "update") {
			$scope.kuczzList[id].value.ZHUANGT = "停用";
		} else {
			$scope.kuczzArray[id].value.ZHUANGT = "停用";
		}
	}
	$scope.checkKuczzmc = function(target) {
		var flag = false;
		kuczzmc = $(target).parent().parent().siblings().children().children(".kuczzmc");
		for (var i = 0; i < kuczzmc.length; i++) {
			if ($(kuczzmc[i]).val() == $(target).val()) {
				$(target).css('color', '#ff0000');
				$(kuczzmc[i]).css('color', '#ff0000');

				flag = true;// 重复标志位
			}
		}
		if (flag == false) {
			for (var i = 0; i < kuczzmc.length; i++) {
				$(target).css('color', '#000000');
				$(kuczzmc[i]).css('color', '#000000');
			}
		} else {
			alert("库存组织名称不能重复");
		}
	}
	$scope.checkKuczzdm = function(target) {
		var flag = false;
		kuczzdm = $(target).parent().parent().siblings().children().children(".kuczzdm");
		for (var i = 0; i < kuczzdm.length; i++) {
			if ($(kuczzdm[i]).val() == $(target).val()) {
				$(target).css('color', '#ff0000');
				$(kuczzdm[i]).css('color', '#ff0000');

				flag = true;// 重复标志位
			}
		}
		if (flag == false) {
			for (var i = 0; i < kuczzdm.length; i++) {
				$(target).css('color', '#000000');
				$(kuczzdm[i]).css('color', '#000000');
			}
		} else {
			alert("库存组织代码不能重复");
		}
	}
	$scope.save = function() {
		// 检查库存组织名称是否重复
		var flag = true;
		for (var i = 0; i < $scope.kuczzList.length; i++) {
			// console.log($scope.kuczzList[i].value);
			$http.post('kucgl/update', {
				kuczzUpdate : angular.toJson($scope.kuczzList[i].value)
			}).success(function(data) {
				// alert("保存成功");
			});
		}
		for (var i = 0; i < $scope.kuczzArray.length; i++) {
			if ($scope.kuczzArray[i].value.KUCZZDM != undefined && $scope.kuczzArray[i].value.KUCZZMC != undefined && $scope.kuczzArray[i].value.DIANC_ID != undefined) {
				$http.post('kucgl/insert', {
					kuczzInsert : angular.toJson($scope.kuczzArray[i].value)
				}).success(function(data) {
					// alert("保存成功");F

					$scope.load();

				});
			} else {
				alert("添加项有空位！");
				flag = false;
				break;
			}

		}
		if (flag == true) {
			alert("保存成功");
		}
		
//		$http.post('kucgl/txt', {
//			txt : $("#form1").html()
//		}).success(function(data) {
//
//		});
	}

}).controller('kucwzCtrl', function($scope, $rootScope, $routeParams, $http, $location) {
	// $scope.jiesTitle = '发票维护';
	$scope.search = new Object();
	$scope.search.meikxxbid = -1;
	$scope.kucwzArray = new Array();
	$scope.kucwzBean = new Object();
	$scope.textShow = true;
	// var table = $('#example').DataTable();
	$scope.load = function() {
		$http.post('kucgl/getKucwz').success(function(data) {
			$scope.kucwzList = data;
			$scope.kucwzArray = [];
		});
	}

	$scope.load();
	$scope.checkWuzbm = function(target) {
		var flag = false;
		wuzbm = $(target).parent().parent().siblings().children().children(".wuzbm");
		for (var i = 0; i < wuzbm.length; i++) {
			if ($(wuzbm[i]).val() == $(target).val()) {
				$(target).css('color', '#ff0000');
				$(wuzbm[i]).css('color', '#ff0000');

				flag = true;// 重复标志位
			}
		}
		if (flag == false) {
			for (var i = 0; i < wuzbm.length; i++) {
				$(target).css('color', '#000000');
				$(wuzbm[i]).css('color', '#000000');
			}
		} else {
			alert("物种编码不能重复");
		}
	}
	$scope.add = function() {
		var obj = new Object();
		obj = {
			name : "",
			value : {
				ZHUANGT : "启用",
				DIANC_ID : ""
			}
		}
		$scope.kucwzArray.push(obj);
		$scope.kucwzList.push(obj);
		console.log($scope.kucwzList);
	}
	$scope.on = function(target) {
		$("#save").addClass("btn-success");
		$("#save").attr('disabled', false);
		var id = "<%=session.getAttribute('user')%>";
		// console.log(id);
		var $input = $(target).parent();
		id = $input.attr("id");
		c = $input.attr('class');
		if (c == "update") {
			$scope.kucwzList[id].value.ZHUANGT = "启用";
		} else {
			$scope.kucwzArray[id].value.ZHUANGT = "启用";
		}
	}
	$scope.off = function(target) {
		$("#save").addClass("btn-success");
		$("#save").attr('disabled', false);
		var $input = $(target).parent();
		id = $input.attr("id");
		c = $input.attr('class');
		if (c == "update") {
			$scope.kucwzList[id].value.ZHUANGT = "停用";
		} else {
			$scope.kucwzArray[id].value.ZHUANGT = "停用";
		}
	}
	$scope.toInput = function(target) {
		$scope.textShow = false;
	}
	$scope.toText = function(target) {
		$scope.textShow = true;
	}
	$scope.save = function() {
		var flag = true;
		if ($scope.kucwzList != undefined) {
			for (var i = 0; i < $scope.kucwzList.length; i++) {
				$http.post('kucgl/updateKucwz', {
					kucwzUpdate : angular.toJson($scope.kucwzList[i].value)
				}).success(function(data) {
					// alert("保存成功");
				});
			}
		}
		for (var i = 0; i < $scope.kucwzArray.length; i++) {
			if ($scope.kucwzArray[i].value.WUZBM != undefined && $scope.kucwzArray[i].value.MIAOS != undefined && $scope.kucwzArray[i].value.WUZMC != undefined && $scope.kucwzArray[i].value.DIANC_ID != undefined) {
				$http.post('kucgl/insertKucwz', {
					kucwzInsert : angular.toJson($scope.kucwzArray[i].value)
				}).success(function(data) {
					// alert("保存成功");
					$scope.load();
				});

			} else {
				flag = false;
				alert("添加项有空位");
				break;
			}

		}
		if (flag == true) {
			alert("保存成功");
		}

	}

}).controller('kuaijqdyCtrl', function($scope, $rootScope, $routeParams, $http, $location) {

	// 保存按钮置灰
	$scope.isSave=false;
	$scope.load = function() {
		$http.post('kucgl/getKuaijqdy').success(function(data) {
			$scope.kuaijqdyList = data;
			$scope.max = $scope.kuaijqdyList[0].SHUNXH;
		}).error(function (data) {
			alert("查询数据出错!");
		});
	}
	$scope.load();
	$scope.add = function() {
		$scope.max = $scope.max + 1;
		var obj =  {
			name : "",
			ZHUANGT : "启用",
			DIANC_ID: 515,
			SHUNXH : $scope.max
		};

		$scope.kuaijqdyList.unshift(obj);
	};

	$scope.save = function() {
		var flag = true;
		for (var i = 0; i < $scope.kuaijqdyList.length; i++) {
			if ($scope.kuaijqdyList[i].KUAIJRQ != undefined && $scope.kuaijqdyList[i].KAISRQ != undefined && $scope.kuaijqdyList[i].DIANC_ID != undefined && $scope.kuaijqdyList[i].JIESRQ != undefined) {

			} else {
				flag = false;
				alert("添加项有空位!");
				return;
			}
		}
		if (flag == true) {
			$http.post('kucgl/saveKuaijqdy', {
				data : angular.toJson($scope.kuaijqdyList)
			}).success(function(data) {
				alert("保存成功!");
				$scope.isSave=false;
				$scope.load();
			}).error(function(data){
				alert("保存失败!");
			});
		}
	}


	
	
	$scope.check = function() {
		$(".kuaijq").css('color', '#000000');
		var isRepeat = false;
		var dates = $(".kuaijq");
		var datesOdered = dates;
		var i = datesOdered.length, j;
		var tempExchangVal;
		while (i > 0) {
			for (j = 0; j < i - 1; j++) {
				if ($(datesOdered[j]).val() > $(datesOdered[j + 1]).val()) {
					tempExchangVal = datesOdered[j];
					datesOdered[j] = datesOdered[j + 1];
					datesOdered[j + 1] = tempExchangVal;
				}
			}
			i--;
		}
		var n = datesOdered.length;
		for (var i = 0; i < n; i++) {
			if ($(datesOdered[i]).val() == $(datesOdered[i + 1]).val()) {
				$(datesOdered[i]).css('color', '#ff0000');
				$(datesOdered[i + 1]).css('color', '#ff0000');
				isRepeat = true;
			} else {
				$(datesOdered[i + 1]).css('color', '#000000');
			}
		}
		if (isRepeat == true) {
			$scope.isSave=false;
			alert("含有重复的会计期!");
		} else {
			$scope.isSave=true;
		}

	};

	$(".datepicker").live('focus', function() {
		$(this).datepicker({
			format : 'yyyy-mm-dd',
			minViewMode : 0,
			language : "zh-CN",
			autoclose : true,
			orientation: 'top'
		}).on("hide", function(e) {
			$(".datepicker").unbind("hide");
			$scope.isSave=true;
		});
	});
	$(".datepicker0").live('focus', function() {
		$(".datepicker0").unbind("hide");
		$(this).datepicker({
			format : 'yyyy-mm',
			minViewMode : 1,
			language : "zh-CN",
			autoclose : true,
			orientation: 'top'
		}).on("change", function(e) {
			$(".datepicker0").unbind("hide");
			$scope.check();
	    });
	});

}).controller('guanlCtrl', function($scope, $rootScope, $routeParams, $http, $location) {

	$scope.search = new Object();
	$scope.search.meikxxbid = -1;
	$scope.kuaijqdyArray = new Array();
	$scope.kucwzBean = new Object();

	// 保存按钮置灰
	$("#save").removeClass("btn-success");
	$("#save").attr('disabled', true);

	$scope.load = function() {
		$http.post('kucgl/getKuaijqList').success(function(data) {
			$scope.kuaijqList = data;
		});
		$http.post('kucgl/getKuczzList').success(function(data) {
			$scope.kuczzList = data;
		});
		$http.post('kucgl/getGuanl').success(function(data) {
			$scope.guanlList = data;
		}).error(function(data){
			alert("查询数据失败!");
		});

	}
	$scope.load();	
	$scope.save = function() {
		//查询空白
		var flag = true;
		// 遍历填入的每行会计确认单
		//for (var i = 0; i < $scope.guanlList.length; i++) {
		$http.post('kucgl/saveGuanl', {
			data : angular.toJson($scope.guanlList)
		}).success(function(data) {
			alert("保存成功!");
		}).error(function(data){
			alert("保存失败!")
		});
	}
	$scope.check = function(target) {
		$("#save").addClass("btn-success");
		$("#save").attr('disabled', false);
//		$(".kuaijq").css('color', '#000000');
//		$(".kuczz").css('color', '#000000');
//		var isRepeat = false;
//		var kuaijqDates = $(".kuaijq");
//		var kuczzDates=$(".kuczz");
//		
//		var datesOdered = new Object();
//		datesOdered = {kuaijqDates:kuaijqDates,kuczzDates:kuczzDates};
//		var i = datesOdered.kuaijqDates.length, j;
//		var tempExchangVal;
//		while (i > 0) {
//			for (j = 0; j < i - 1; j++) {
//				if (($(datesOdered.kuaijqDates[j]).val()+$(datesOdered.kuczzDates[j]).val()) >( $(datesOdered.kuaijqDates[j+1]).val()+$(datesOdered.kuczzDates[j+1]).val())) {
//					tempExchangVal = datesOdered.kuaijqDates[j];
//					datesOdered.kuaijqDates[j] = datesOdered.kuaijqDates[j + 1];
//					datesOdered.kuaijqDates[j + 1] = tempExchangVal;
//					
//					tempExchangVal = datesOdered.kuczzDates[j];
//					datesOdered.kuczzDates[j] = datesOdered.kuczzDates[j + 1];
//					datesOdered.kuczzDates[j + 1] = tempExchangVal;
//				}
//			}
//			i--;
//		}
//		var n = datesOdered.kuaijqDates.length;
////		console.log(datesOdered);
//		for (var i = 0; i < n; i++) {
//			if (($(datesOdered.kuaijqDates[i]).val()+$(datesOdered.kuczzDates[i]).val()) == ( $(datesOdered.kuaijqDates[i+1]).val()+$(datesOdered.kuczzDates[i+1]).val())) {
//				$(datesOdered.kuaijqDates[i]).css('color', '#ff0000');
//				$(datesOdered.kuaijqDates[i + 1]).css('color', '#ff0000');
//				$(datesOdered.kuczzDates[i]).css('color', '#ff0000');
//				$(datesOdered.kuczzDates[i + 1]).css('color', '#ff0000');
//				isRepeat = true;
//			} else {
//				$(datesOdered.kuaijqDates[i + 1]).css('color', '#000000');
//				$(datesOdered.kuczzDates[i + 1]).css('color', '#000000');
//			}
//		}
//	
//		if (isRepeat == true) {
//			$("#save").removeClass("btn-success");
//			$("#save").attr('disabled', true);
//			alert("含有重复项!");
//		} else {
//			$("#save").addClass("btn-success");
//			$("#save").attr('disabled', false);
//		}
//
	}
	$scope.add = function() {
//		angular.forEach($scope.guanlList, function(data,index,array){
//			$scope.guanlList[index].ZHUANGT = "停用";
//		});
//		$("#save").removeClass("btn-success");
//		$("#save").attr('disabled', true);
		$("#save").addClass("btn-success");
		$("#save").attr('disabled', false);
		var obj = new Object();
		obj = {
			ZHUANGT : "启用",
			KUCZZ_ID:14473277164810
		};
		$scope.guanlList.push(obj);
		$scope.guanlList.unshift(obj);	
		$scope.guanlList.pop();
	}
	$scope.yuemgz=function(o){
		$scope.isDisplay=true;
		$http.post('kucgl/yuemgz',{data:angular.toJson({KUAIJQ_ID:o.KUAIJQ_ID,KUCZZ:o.KUCZZ_ID})})
		.success(function (data) {
			alert("关帐成功!");
			$scope.save();
			$scope.isDisplay=false;
		}).error(function (data) {
			$scope.isDisplay=false;
			alert("关帐失败!");
		})
	};
	$scope.clear=function(o){
		$scope.isDisplay=true;
		$http.post('kucgl/clear',{data:angular.toJson({KUAIJQ_ID:o.KUAIJQ_ID,KUCZZ:o.KUCZZ_ID})})
			.success(function (data) {
				alert("清楚成功!");
				$scope.isDisplay=false;
				o.ZHUANGT = "启用";
				$scope.save();
			}).error(function (data) {
			$scope.isDisplay=false;
			alert("清楚失败!");
		})
	};

		$scope.on = function(i) {
		$("#save").addClass("btn-success");
		$("#save").attr('disabled', false);
		var o=$scope.guanlList[i];
		var kuczzId=$scope.guanlList[i].KUCZZ_ID;
		$.each($scope.guanlList,function(k,j){
			if(j.KUCZZ_ID==kuczzId&&j.ZHUANGT=="启用"){
				var c=window.confirm("确认吗?")
				if(o.ID&&c){
					$scope.yuemgz(o);
					j.ZHUANGT="停用"
					$scope.clear(o);
					$scope.yuemgz(j);
					return;
				}
			}
		});
		$scope.clear(o);
	}
	$scope.off = function(i) {
		$("#save").addClass("btn-success");
		$("#save").attr('disabled', false);
		var o=$scope.guanlList[i];
		if(o.ID&&window.confirm("确认吗?")){
			$scope.yuemgz(o);
		}
		o.ZHUANGT = "停用";
	}


		//}
		
		//查空
//		for (var i = 0; i < $scope.guanlList.length; i++) {
//			if ($scope.guanlArray[i].KUAIJQ_ID != undefined && $scope.guanlArray[i].KUCZZ_ID != undefined) {
//				
//			} else {
//				alert("添加项有空位！");
//				flag = false;
//				break;
//			}
//		}
	}

).controller('weizCtrl', function($scope, $rootScope, $routeParams, $http, $location) {
	$scope.weizArray = new Array();
	// 启用按钮置灰
	$("#on").removeClass("btn-primary");
	$("#on").attr('disabled', true);
	// 停用按钮置灰
	$("#off").removeClass("btn-primary");
	$("#off").attr('disabled', true);

	$scope.load = function() {
		$http.post('kucgl/getKuczzList').success(function(data) {
			$scope.kuczzList = data;
			// console.log(data);
		});
		$http.post('kucgl/getWeiz').success(function(data) {
			$scope.weizList = data;
			$scope.weizArray = [];
		});

	}
	$scope.load();
	$scope.add = function() {
		// $scope.max=$scope.max+1;
		var obj = new Object();
		obj = {
			ZHUANGT : "启用",
		}

		$scope.weizArray.push(obj);
	}
	$scope.on = function(target) {
		$("#save").addClass("btn-success");
		$("#save").attr('disabled', false);
		var $input = $(target).parent();
		id = $input.attr("id");
		c = $input.attr('class');
		if (c == "update") {
			$scope.weizList[id].ZHUANGT = "启用";
		} else {
			$scope.weizArray[id].ZHUANGT = "启用";
		}
	}
	$scope.off = function(target) {
		$("#save").addClass("btn-success");
		$("#save").attr('disabled', false);
		var $input = $(target).parent();
		id = $input.attr("id");
		c = $input.attr('class');
		if (c == "update") {
			$scope.weizList[id].ZHUANGT = "停用";
		} else {
			$scope.weizArray[id].ZHUANGT = "停用";
		}



	}

	$scope.save = function() {
		// 非空判断
		var flag = true;
		// 遍历填入的每行会计确认单
		for (var i = 0; i < $scope.weizList.length; i++) {

			$http.post('kucgl/updateWeiz', {
				weizUpdate : angular.toJson($scope.weizList[i])
			}).success(function(data) {
				// alert("保存成功");
			});
		}
		for (var i = 0; i < $scope.weizArray.length; i++) {
			if ($scope.weizArray[i].KUCWZDM != undefined && $scope.weizArray[i].DIANC_ID != undefined && $scope.weizArray[i].KUCWZMS != undefined && $scope.weizArray[i].FUID != undefined && $scope.weizArray[i].KUCZZ_ID != undefined) {
				$http.post('kucgl/insertWeiz', {
					weizInsert : angular.toJson($scope.weizArray[i])
				}).success(function(data) {
					// alert("保存成功");
					$scope.load();
				});
			} else {
				alert("添加项有空位！");
				flag = false;
				break;
			}

		}
		if (flag != false) {
			alert("保存成功");

		}
	}

}).controller('kucftszCtrl', function($scope, $rootScope, $routeParams, $http, $location) {

	$scope.load = function() {
		$http.post('kucgl/getKucftList').success(function(data) {
			for(var k in data){
				data[k].BAIFB=data[k].BAIFB*100+"%";
			}
			$scope.kucftList = data;
		})
	}
	
	$scope.load();
	
	$scope.save = function() {
		var dataTj=$scope.kucftList;
		var sumbfb=0;
		for(var k in dataTj){
			dataTj[k].BAIFB=parseInt(dataTj[k].BAIFB)/100;
			sumbfb+=dataTj[k].BAIFB;
		}
		if(sumbfb==1){
			$http.post('kucgl/saveKucftList', {
				data: angular.toJson(dataTj)
			}).success(function (data) {
				alert("保存成功");
				$scope.load();
			});
		}else{
			alert("合计项不足或超过100%,请重新分配百分比");
			for(var j in dataTj){
				dataTj[j].BAIFB=dataTj[j].BAIFB*100+"%";
			}
		}
	}	
});









