gddlapp.controller('yuedjhzbycCtrl', function($scope, $rootScope,$routeParams, $http,$location) {
	$scope.search = {
		diancid:515,
		riq:new Date().format('yyyy-MM')
	};
	$scope.math = Math;
	$scope.DECODE=function(a,b,c,d){
		if(a==b){
			return c;
		}else{
			return d;
		}
	};
	$scope.ROUND=function(a,b){
		if(b==0){
			return Math.round(a);
		}else{
			return Math.round(a*10*b)/(10*b);
		}
	}
	$scope.refresh = function(){
		$http.post('jihgl/yuejhlr/getYuedjhzbycList',{search:angular.toJson($scope.search)}).success(function(data){
			$scope.jihList = data;
			angular.forEach($scope.jihList, function (jih, i) {
				if(jih.ZIDM!=null){
					var zidm=angular.uppercase(jih.ZIDM);
					$scope[zidm]=jih.ZHI;
				}
				if(jih.GONGS!=null){
					jih.GONGS=angular.uppercase(jih.GONGS);
				}
			});
		}).error(function (data) {
			alert("查询数据出错!");
		});
	};
	$scope.refresh();
	$scope.copyranlzfjh = function(){
		if($scope.jihList.length!=0){
			var a =confirm($scope.search.riq+"月计划已经存在，你确定要替换吗？？？");
			if(a){
				var str =$scope.search.riq;
				str = str.replace(/-/g,"/");
				var date = new Date(str);
				date.setMonth(date.getMonth()-1);
				var riq=date.format("yyyy-MM");
				$http.post('jihgl/yuejhlr/getYuedjhzbycList',{search:angular.toJson({diancid:$scope.search.diancid,riq:riq})}).success(function(data){
					$scope.jihList = data;
					//----更新公式计算所需变量值-----
					angular.forEach($scope.jihList, function (jih, i) {
						jih.SANJ_ZT=0;
						if(jih.ZIDM!=null){
							var zidm=angular.uppercase(jih.ZIDM);
							$scope[zidm]=jih.ZHI;
						}
						if(jih.GONGS!=null){
							jih.GONGS=angular.uppercase(jih.GONGS);
						}
					});
					$scope.save();
				}).error(function (data) {
					alert("查询数据出错!");
				});
			}
		}
	};
	$scope.del = function(){
		var a =confirm("你确定要删除此条记录?");
		if(a){
			$http.post("jihgl/yuejhlr/zhibycdel",{search:angular.toJson($scope.search)})
				.success(function(data){//data为反回值，function进行反回值处理
					alert("删除成功!");
					$scope.refresh();
				}).error(function (data) {
				alert("删除失败!");
			});
		}else{
			return false;
		}
	};
	$scope.save =function(){
		$http.post("jihgl/yuejhlr/saveYuedjhzbycList",
			{search:angular.toJson($scope.search),data:angular.toJson($scope.jihList)})
			.success(function(data){//data为反回值，function进行反回值处理
				alert("保存成功!");
				$scope.refresh();
			}).error(function (data) {
			alert("保存失败!");
		});
	};
	$scope.calculate=function(row){
		 $scope[row.ZIDM]=row.ZHI;
	};
	$("#datepicker").datepicker({
		format : 'yyyy-mm',
		minViewMode : 1,
		language : "zh-CN",
		autoclose : true
	});
});

