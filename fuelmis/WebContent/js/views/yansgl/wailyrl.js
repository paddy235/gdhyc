gddlapp.controller('wailyrlCtrl', function($scope,$rootScope,$http,$log,$location) {
	$scope.title = "外来样维护";
	
	var date = new Date();
	var year = date.getFullYear();
	var month = date.getMonth()+1<10?'0'+(date.getMonth()+1):date.getMonth()+1;
	var day = date.getDate()<10?'0'+date.getDate():date.getDate();
	
	$scope.search = {
		sDate : year+'-'+month+'-'+day
	};
	
	 $scope.getWailyrlAll=function(){
		 $http.post('yansgl/getWailyrlAll',{riq:$scope.search.sDate}).success(function(data) {
				$scope.list = data
			/*	//  添加序号，如果data的长度为0 ， 序号就从0开始，否则 取最后一条数据的ID
				if(data.length == 0){
					$scope.max=0;
				}else{
					$scope.max = $scope.list[$scope.list.length - 1].ID;
				}
				//每查询一下吧array清空，保证下次添加不会出现重复
				$scope.array = [];
			});*/
	 	});
	 }
		 $scope.getWailyrlAll();
	 
		/*//添加 
		$scope.add=function (){
			$scope.max = $scope.max + 1;    // 以ID最大值加1
			var obj = new Object();  //新创建一个object
			obj = {
					ID : $scope.max    //  赋值     
			}
			
			$scope.array.push(obj);   //吧赋值存入array
		}
		
		//保存
	 $scope.updatejihb = function (){
		 $http.post("jihnz/updateJihb",{data:angular.toJson($scope.list)}).success(function (data){
			 
		 });
		 
		 $http.post("jihnz/insertJihb",{data:angular.toJson($scope.array)}).success(function (data){
			 $scope.jihnzAll();
		 });
		 
		 alert('保存成功！');
		 
	 }	
	 
	 //删除
	 $scope.deleteJihb=function (){
		if($("#xz input[type=checkbox]:checked").length<1){
			alert("请选择要删除的行");
		}else{
			$('#myModal_Del').modal('show');
		} 
		 
	 }
	 
	 
	 $scope.findJihb=function (){
		 if($scope.rq.kaisrq=='' || $scope.rq.jiesrq ==''){
			 alert("日期不能为空！")
		 }else if($scope.rq.kaisrq > $scope.rq.jiesrq){
				alert("开始日期不能大于结束日期!")
		 }else{
			 $http.post("jihnz/findJihb",{kaisrq:$scope.rq.kaisrq,jiesrq:$scope.rq.jiesrq}).success(function (data){
				 $scope.list = [];
				 $scope.list = data
				 $scope.search = [];
				 $scope.search={id:$scope.list[0].ID,mingc:$scope.list[0].MINGC,danw:$scope.list[0].DANW}
				  alert("查询成功!")
			 }); 
		 }
	 }*/
	 
	 
	 
	 
	 
	 
});