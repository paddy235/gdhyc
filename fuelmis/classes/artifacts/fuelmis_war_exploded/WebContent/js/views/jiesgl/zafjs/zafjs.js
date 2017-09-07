gddlapp.controller('zafjswhzaCtrl', function($scope,$rootScope,$http,$log,$location) {
	//--------------------------------------日期定义--------------------------------------------------------------------------

	var begin=new Date();
	var end=new Date();
	new Date(begin.setMonth((new Date().getMonth())));
	var begintime= begin.format("yyyy-MM")+'-01';
	var endtime=end.format("yyyy-MM-dd");
	//-----------------------------------------------------------------------------------------------------------------------
	$scope.yzfxmList=[{name:'煤场管理费',value:7},{name:'二次倒运费',value:8},{name:'燃料管理服务费',value:9}];
	$http.post('jiesgl/zafjs/getZafjs').success(function(data){
		$scope.list=data;
	}).error(function(data){
		alert("查询数据出错!");
	});
	$scope.refresh=function(){
		$http.post('jiesgl/zafjs/getZafjs').success(function(data){
			$scope.list=data;
		}).error(function(data){
			alert("查询数据出错!");
		});
	}
	$scope.add=function(){
		$scope.list.unshift({
			KAISRQ:begintime,
			JIESRQ:endtime
		});
	}
	$scope.save=function(){
		$http.post('jiesgl/zafjs/saveZafjs',{data:angular.toJson($scope.list)}).success(function(data){
			alert("保存成功!");
			$scope.refresh();
		}).error(function(data){
			alert("保存失败!");
		})
	}
	 Array.prototype.remove = function (dx) {
         if (isNaN(dx) || dx > this.length) {
             return false;
         }
         this.splice(dx, 1);
     }
	$scope.del=function(i){
		
		if($scope.list[i].ID!=undefined){
			$http.post('jiesgl/zafjs/delZafjs',{id:$scope.list[i].ID}).success(function(data){
				alert("删除成功!");
			}).error(function(data){
				alert("删除失败!");
			})
		}
		$scope.list.remove(i);
	}
	$(".datepicker").live('focus', function() {
		$(this).datepicker({
			format : 'yyyy-mm-dd',
			minViewMode : 0,
			language : "zh-CN",
			autoclose : true
		}).on("hide", function(e) {
			$(".datepicker").unbind("hide");
	    });
	});

});