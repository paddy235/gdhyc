gddlapp.controller('rijhzltbCtrl', function($scope,$rootScope,$http,$log,$location) {
	$scope.gongmjhtzTitle='日计划质量填报';
	$scope.search = new Object();
	var date = new Date();
	var year = date.getFullYear();
	var month = date.getMonth()+1<10?'0'+(date.getMonth()+1):date.getMonth()+1;
	var day = date.getDate()<10?'0'+date.getDate():date.getDate();
	$scope.search = {
			Date : year+'-'+month+'-'+day,
			mingc : -1,
			zhuangt : 0
	};
	
	$(".datepicker0").live('focus', function() {
		$(this).datepicker({
			format : 'yyyy-mm-dd',
			minViewMode : 0,
			language : "zh-CN",
			autoclose : true
		});
	});
	$(".datepicker1").live('focus', function() {
		$(this).datepicker({
			format : 'yyyy-mm-dd',
			minViewMode : 0,
			language : "zh-CN",
			autoclose : true
		});
	});
	
	
	
	$scope.getRijhzltbAll=function(){
		 $http.post('gongyspg/rijhzltb/getRijhzltbAll',{jihrq: $scope.search.Date,danw:$scope.search.mingc,zhuangt:$scope.search.zhuangt}).success(function(data) {
				$scope.list = data
			});
	 }
	 
	
	 $scope.insertRijhzltb = function (){
		 /*if($scope.list[0].ZHUANGT==0){
			 alert("请先审核！")
		 }else{
			 $http.post("gongyspg/rijhzltb/insertRijhzltb",{data:angular.toJson($scope.list)}).success(function (data){
				 alert("保存成功！")
			 });
		 }*/
		 if($scope.list[0].ZHUANGT==0 || $scope.list[0].ZHUANGT==null){
			 $http.post("gongyspg/rijhzltb/insertRijhzltb",{data:angular.toJson($scope.list)}).success(function (data){
				 alert("保存成功！")
			 });
		 }else if($scope.list[0].ZHUANGT==1){
			 alert("数据已经审核不可修改！");
		 }
		
	 }
	 
	 $scope.updateRijhzltb = function (){
		 if($scope.list[0].ZHUANGT==0){
			 $http.post("gongyspg/rijhzltb/updateRijhzltb",{data:angular.toJson($scope.list)}).success(function (data){
				 alert("审核成功!")
			 });
		 }else{
			alert("审核失败,已经审核无需在审核！")
		 }
	 }
	 
})


