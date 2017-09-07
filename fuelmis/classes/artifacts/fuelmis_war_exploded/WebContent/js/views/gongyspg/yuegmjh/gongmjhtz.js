gddlapp.controller('gongmjhtzCtrl', function($scope,$rootScope,$http,$log,$location) {
	$scope.gongmjhtzTitle='供煤计划调整';
	$scope.search = new Object();
	var date = new Date();
	var year = date.getFullYear();
	var month = date.getMonth()+1<10?'0'+(date.getMonth()+1):date.getMonth()+1;
	var day = date.getDate()<10?'0'+date.getDate():date.getDate();
	$scope.search = {
			Date : year+'-'+month,
			gongysb_id : -1
		
			
	};
	
	$(".datepicker0").live('focus', function() {
		$(this).datepicker({
			format : 'yyyy-mm-dd',
			minViewMode : 0,
			language : "zh-CN",
			autoclose : true
		});
	});
	
	
	$scope.getGongmjhtzAll=function(){
		 $http.post('gongyspg/gongmjhzl/getGongmjhtzAll',{condition:angular.toJson($scope.search)}).success(function(data) {
				$scope.list = data
			});
	 }
	
	$scope.getGongmjhtzAll();
	
	 $scope.updateGongmjh = function (){
		 $http.post("gongyspg/gongmjhzl/updateGongmjh",{data:angular.toJson($scope.list)}).success(function (data){
			 alert("保存成功！")
			 $scope.getGongmjhtzAll();
		 });
	 }
	 
	 $scope.gongmjhfb = function (){
		 $http.post("gongyspg/gongmjhzl/gongmjhFab",{data:angular.toJson($scope.list)}).success(function (data){
			 alert("发布成功！")
			 $scope.getGongmjhtzAll();
		 });
	 }
	
})


