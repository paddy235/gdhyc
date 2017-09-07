gddlapp.controller('banzxxCtrl', function($scope, $rootScope, $routeParams, $http, $location) {
	$scope.search = new Object();
	$scope.search.meikxxbid = -1;
	$scope.array = new Array();
	$scope.DIANCXXB_ID = 515; 
/*******************************************************************************
 * 加载数据***************************************************
 * 向后台发送请求查询班组信息以jsonArray形式返回
 */
	$scope.load = function() {
		$http.post('baseSet/getBanz').success(function(data) {
			$scope.list = data;
			if(data.length==0){
				$scope.max=0;
			}else{
				$scope.max = $scope.list[$scope.list.length - 1].XUH;
			}
		
			$scope.array = [];
		});
	}
	//--------------测试代码：公式替换值并计算--------------------
//	function calculator(o, formular){
//		o={"a":1,"b":2,"c":3}
//		formular="a*b+c+a" 
//		var g=formular
//		for(key in o){
//			var reg=new RegExp(key,"g"); //创建正则RegExp对象  
//			var value=o[key]
//			g=g.replace(reg,value);
//		}
//		$scope.result=eval(g);
//	}
//	文字排序那个你可以试试这个，网上找了一下localeCompare()本地排序的方法，好像是根据操作系统的语言来排序的
//	 var str=["出红","都秀","吧鑫","啊王"];
//	    str=str.sort(function compare(a,b){
//	        return a.localeCompare(b);
//	    } );
//	    console.log(str);
	$scope.load();
	$scope.add = function() {
		$scope.max = $scope.max + 1;
		var obj = new Object();
		obj = {
				ZHUANGT : "启用",
				DIANCXXB_ID:$scope.DIANCXXB_ID,
				XUH : $scope.max
		}
		$scope.array.push(obj);
	}
	$scope.on = function(target) {
		var $input = $(target).parent();
		id = $input.attr("id");
		c = $input.attr('class');
		if (c == "update") {
			$scope.list[id].ZHUANGT = "启用";
		} else {
			$scope.array[id].ZHUANGT = "启用";
		}
	}
	$scope.off = function(target) {
		var $input = $(target).parent();
		id = $input.attr("id");
		c = $input.attr('class');
		if (c == "update") {
			$scope.list[id].ZHUANGT = "停用";
		} else {
			$scope.array[id].ZHUANGT = "停用";
		}
	}
	$scope.save = function() {
		for (var i = 0; i < $scope.list.length; i++) {
			$http.post('baseSet/updateBanz', {
				data : angular.toJson($scope.list[i])
			}).success(function(data) {
				// alert("保存成功");
			});
		}
		for (var i = 0; i < $scope.array.length; i++) {
			$http.post('baseSet/insertBanz', {
				data : angular.toJson($scope.array[i])
			}).success(function(data) {
				// alert("保存成功");

				$scope.load();

			});
		}
		alert("保存成功");

	}

}).controller('jizxxCtrl', function($scope, $rootScope, $routeParams, $http, $location) {
	$scope.search = new Object();
	$scope.search.meikxxbid = -1;
	$scope.array = new Array();
	$scope.DIANCXXB_ID = 515;
//*******************************************************************************
 //* 加载数据***************************************************
// * 向后台发送请求查询班组信息以jsonArray形式返回
// *//*
	$scope.load = function() {
		$http.post('baseSet/getJiz').success(function(data) {
			$scope.list = data;
			if(data.length==0){
				$scope.max=0;
			}else{
				$scope.max = $scope.list[$scope.list.length - 1].XUH;
			}
			$scope.array = [];
		});
	}

	$scope.load();
	$scope.add = function() {
		$scope.max = $scope.max + 1;
		var obj = new Object();
		obj = {
				ZHUANGT : "启用",
				DIANCXXB_ID:$scope.DIANCXXB_ID,
				XUH : $scope.max
		}
		$scope.array.push(obj);
	}
	$scope.on = function(target) {
		var $input = $(target).parent();
		id = $input.attr("id");
		c = $input.attr('class');
		if (c == "update") {
			$scope.list[id].ZHUANGT = "启用";
		} else {
			$scope.array[id].ZHUANGT = "启用";
		}
	}
	$scope.off = function(target) {
		var $input = $(target).parent();
		id = $input.attr("id");
		c = $input.attr('class');
		if (c == "update") {
			$scope.list[id].ZHUANGT = "停用";
		} else {
			$scope.array[id].ZHUANGT = "停用";
		}
	}
	//查重
 /* $scope.checkKuczzmc = function(target) {
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
	}*/
	$scope.save = function() {
		for (var i = 0; i < $scope.list.length; i++) {
			$http.post('baseSet/updateJiz', {
				data : angular.toJson($scope.list[i])
			}).success(function(data) {
				// alert("保存成功");
			});
		}
		for (var i = 0; i < $scope.array.length; i++) {
			$http.post('baseSet/insertJiz', {
				data : angular.toJson($scope.array[i])
			}).success(function(data) {
				// alert("保存成功");

				$scope.load();

			});
		}
		alert("保存成功");

	}

});

