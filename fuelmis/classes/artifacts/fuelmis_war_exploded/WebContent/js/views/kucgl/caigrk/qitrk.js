gddlapp.controller('qitrkCtrl', function($scope,$rootScope,$http,$log,$location) {
	$scope.qitrkTitle='其他入库';
	var date = new Date();
	var year = date.getFullYear();
	var month = date.getMonth()+1<10?'0'+(date.getMonth()+1):date.getMonth()+1;
	var day = date.getDate()<10?'0'+date.getDate():date.getDate();
	
	$scope.search = {
		sDate : year+'-'+month+'-01',
		eDate : year+'-'+month+'-'+day,
		rukdbh:''
	}

//	$http.post('kucgl/caigrk/getAll?yewlx=2,3,4,5').
//	    success(function(data, status, headers, config) {
//	    	$scope.rukdAllList = data.data;
//		});
	
	$scope.searchData = function() {
//		var sDate = $scope.search.sDate;
//		var eDate = $scope.search.eDate;
//		var rukdbh = $scope.search.rukdbh;
//		$http.post('kucgl/caigrk/getAll?sDate='+sDate+'&eDate='+eDate+'&rukdbh='+rukdbh+"&yewlx=3,4,5").
//	    success(function(data, status, headers, config) {
//	    	$scope.rukdAllList = data.data;
//		});
		$http.post('kucgl/rukgl/qitrk/getQitrkList',{search:angular.toJson($scope.search)}).
	    success(function(data, status, headers, config) {
	    	$scope.rukdAllList = data;
		}).error(function(data){
			alert("查询数据出错!");
		});
	}
	$scope.searchData();
	$scope.edit=function(args){
		$location.path('/editQitrk/'+args);
	}
	
	$scope.addRanlcgrk=function(){
		$location.path('/addQitrk');
	}
}).controller('addQitrkCtrl', function($scope,$rootScope,$http,$log,$location,$routeParams) {
	$scope.addQitrkTitle='其他入库';
	
	$scope.kucztList = [{"name":"未入库","value":-1},{"name":"已入库","value":1}];
	
	if($routeParams.rukdbh!=""&&$routeParams.rukdbh!=null){
		$http.post('kucgl/rukgl/qitrk/getRanlcgrk_WRK_MX2',{rukdbh:$routeParams.rukdbh}).
		    success(function(data, status, headers, config) {
		    	$scope.ranlcgrkList = data.list;
		    	
		    	$scope.ranlrkd = {
		    		rukdbh : data.rukdbh,
		    		ruksl : data.zongsl,
		    		jine : data.zongje,
		    		zhuangt : data.zhuangt,
		    		kuczz : data.kuczz,
		    		huoz : data.huoz,
		    		yewlx : data.yewlx
		    	}
			});
		
		$http.post('kucgl/caigrk/check',{rukdbh:$routeParams.rukdbh}).
		    success(function(data, status, headers, config) {
		    	if(data[0]>0){
		    		$("#add").hide();
		    		$("#save").hide();
		    		$("#ruk").hide();
		    		$("#chex").hide();
		    		$("select").attr("disabled","disabled");
		    	}
			});
	}else{
		$http.post('kucgl/caigrk/getQitrk').
		    success(function(data, status, headers, config) {
		    	$scope.ranlcgrkList = data.list;
		    	
		    	$scope.ranlrkd = {
		    		rukdbh : data.rukdbh,
		    		zhuangt : -1,
		    		kuczz : 0,
		    		huoz : 515,
		    		yewlx : 3
		    	}
			});
	}
	
	$scope.convertNumbers=function(){
		var arr = $scope.ranlcgrkList;
		var zongl = 0;
		for(var i = 0;i<arr.length;i++){
			if(arr[i].shul!=null&&arr[i].shul!=""){
				zongl += parseFloat(arr[i].shul);
			}
		}
		$scope.ranlrkd.ruksl = zongl;
	}
	
	$scope.convertMoney=function(){
		var arr = $scope.ranlcgrkList;
		var zonge = 0;
		for(var i = 0;i<arr.length;i++){
			var j = 0;
			var t = 0;
			if(arr[i].jine!=null&&arr[i].jine!=""){
				zonge += parseFloat(arr[i].jine);
				j = parseFloat(arr[i].jine);
			}
			if(arr[i].tiaozje!=null&&arr[i].tiaozje!=""){
				zonge += parseFloat(arr[i].tiaozje);
				t = parseFloat(arr[i].tiaozje);
			}
			
			$scope.ranlcgrkList[i].zongje = parseFloat(j)+parseFloat(t);
		}
		$scope.ranlrkd.jine = zonge;
	}
	
	$scope.add=function(){
		var obj = new Object();
		obj={shul:0,jine:0,tiaozje: 0}
		$scope.ranlcgrkList.push(obj);
	}
	
	$scope.del = function(fencsl) {
		$.each($scope.ranlcgrkList, function(i){
	    	if(this==fencsl){
	    		$scope.ranlcgrkList.splice(i,1);
	    		return false;
	    	}
		});
	}
	
	$scope.save=function(){
		if($scope.ranlrkd.kuczz!=0&&$scope.ranlrkd.kuczz!=""&&$scope.ranlrkd.kuczz!=null){
			var flag = true;
			for(var i=0;i<$scope.ranlcgrkList.length;i++){
				if($scope.ranlcgrkList[i].kucwz==0||$scope.ranlcgrkList[i].kucwz==""||$scope.ranlcgrkList[i].kucwz==null){
					flag = false;
				}
				if($scope.ranlcgrkList[i].kucwl==0||$scope.ranlcgrkList[i].kucwl==""||$scope.ranlcgrkList[i].kucwl==null){
					flag = false;
				}
			}
			if(flag){
				$http.post('kucgl/rukgl/qitrk/saveQitrk',{data:angular.toJson({rukd:$scope.ranlrkd,rukdList:$scope.ranlcgrkList})})
				.success(function(data){
					alert("保存成功!");
				}).error(function(data){
					alert("保存失败！");
				})
			}else{
				alert("信息填写不完整，不能进行保存！");
			}
		}else{
			alert("信息填写不完整，不能进行保存！");
		}
	};
	
	$scope.ruk=function(){
		$http.post('kucgl/rukgl/qitrk/ruk',{rukdbh:$scope.ranlrkd.rukdbh})
		.success(function(data){
			alert("入库成功!");
			$("#add").hide();
			$("#save").hide();
			$("#ruk").hide();
			$("#chex").hide();
			$("select").attr("disabled", "disabled");
		}).error(function(data){
			alert("入库失败!");			

		})

	};
	
	$scope.chex=function(){
		$http.post('kucgl/caigrk/chex',{rukdbh:$scope.ranlrkd.rukdbh}).
	       success(function(data, status, headers, config) {
	    	if(data[0]>0){
	    		alert("撤销成功");

	    		$http.post('kucgl/caigrk/getRanlcgrk_WRK_MX2',{rukdbh:$scope.ranlrkd.rukdbh}).
	    	    success(function(data, status, headers, config) {
	    	    	$scope.ranlcgrkList = data.list;
	    	    	
	    	    	$scope.ranlrkd = {
	    		    		rukdbh : data.rukdbh,
	    		    		ruksl : data.zongsl,
	    		    		jine : data.zongje,
	    		    		zhuangt : data.zhuangt,
	    		    		kuczz : data.kuczz,
	    		    		huoz : data.huoz,
	    		    		yewlx : data.yewlx
	    		    	}
	    		});
	    		$location.path('/qitrk');
	    	}else{
	    		alert("撤销失败");
	    	}
	    });
	};
	
	$scope.cancel=function(){
		$location.path('/qitrk');
	};
}).controller('editQitrkCtrl', function($scope,$rootScope,$http,$log,$location,$routeParams) {
	$scope.addQitrkTitle='其他入库';
	
	$scope.kucztList = [{"name":"未入库","value":-1},{"name":"已入库","value":1}];
	
	if($routeParams.rukdbh!=""&&$routeParams.rukdbh!=null){
		$http.post('kucgl/caigrk/getRanlcgrk_WRK_MX2',{rukdbh:$routeParams.rukdbh}).
		    success(function(data, status, headers, config) {
		    	$scope.ranlcgrkList = data.list;
		    	
		    	$scope.ranlrkd = {
		    		rukdbh : data.rukdbh,
		    		ruksl : data.zongsl,
		    		jine : data.zongje,
		    		zhuangt : data.zhuangt,
		    		kuczz : data.kuczz,
		    		huoz : data.huoz,
		    		yewlx : data.yewlx
		    	}
			});
		
		$http.post('kucgl/caigrk/check',{rukdbh:$routeParams.rukdbh}).
		    success(function(data, status, headers, config) {
		    	if(data[0]>0){
		    		$("#add").hide();
		    		$("#save").hide();
		    		$("#ruk").hide();
		    		$("#chex").hide();
		    		$("select").attr("disabled","disabled");
		    	}
			});
	}else{
		$http.post('kucgl/caigrk/getQitrk').
		    success(function(data, status, headers, config) {
		    	$scope.ranlcgrkList = data.list;
		    	
		    	$scope.ranlrkd = {
		    		rukdbh : data.rukdbh,
		    		zhuangt : -1,
		    		kuczz : 0,
		    		huoz : 515,
		    		yewlx : 3
		    	}
			});
	}
	
	$scope.convertNumbers=function(){
		var arr = $scope.ranlcgrkList;
		var zongl = 0;
		for(var i = 0;i<arr.length;i++){
			if(arr[i].shul!=null&&arr[i].shul!=""){
				zongl += parseFloat(arr[i].shul);
			}
		}
		$scope.ranlrkd.ruksl = zongl;
	}
	
	$scope.convertMoney=function(){
		var arr = $scope.ranlcgrkList;
		var zonge = 0;
		for(var i = 0;i<arr.length;i++){
			var j = 0;
			var t = 0;
			if(arr[i].jine!=null&&arr[i].jine!=""){
				zonge += parseFloat(arr[i].jine);
				j = parseFloat(arr[i].jine);
			}
			if(arr[i].tiaozje!=null&&arr[i].tiaozje!=""){
				zonge += parseFloat(arr[i].tiaozje);
				t = parseFloat(arr[i].tiaozje);
			}
			
			$scope.ranlcgrkList[i].zongje = parseFloat(j)+parseFloat(t);
		}
		$scope.ranlrkd.jine = zonge;
	}
	
	$scope.add=function(){
		var obj = new Object();
		obj={shul:0,jine:0,tiaozje: 0}
		$scope.ranlcgrkList.push(obj);
	}
	
	$scope.del = function(fencsl) {
		$.each($scope.ranlcgrkList, function(i) {
			if (this == fencsl) {
				$scope.ranlcgrkList.splice(i, 1);
				if (fencsl.id) {
					$http.post('kucgl/rukgl/qitrk/deleteYunzf', {
						id : fencsl.id
					}).success(function(data) {
						alert("删除成功!")
					}).error(function(data) {
						alert("删除失败!");
					})
				}
				return false;
			}
		});

	}
	
	$scope.save=function(){
		if($scope.ranlrkd.kuczz!=0&&$scope.ranlrkd.kuczz!=""&&$scope.ranlrkd.kuczz!=null){
			var flag = true;
			for(var i=0;i<$scope.ranlcgrkList.length;i++){
				if($scope.ranlcgrkList[i].kucwz==0||$scope.ranlcgrkList[i].kucwz==""||$scope.ranlcgrkList[i].kucwz==null){
					flag = false;
				}
				if($scope.ranlcgrkList[i].kucwl==0||$scope.ranlcgrkList[i].kucwl==""||$scope.ranlcgrkList[i].kucwl==null){
					flag = false;
				}
			}
			if(flag){
				$http.post('kucgl/rukgl/qitrk/saveQitrk',{data:angular.toJson({rukd:$scope.ranlrkd,rukdList:$scope.ranlcgrkList})})
				.success(function(data){
					alert("保存成功!");
				}).error(function(data){
					alert("保存失败！");
				})
			}else{
				alert("信息填写不完整，不能进行保存！");
			}
		}else{
			alert("信息填写不完整，不能进行保存！");
		}
	};
	$scope.ruk=function(){
		$http.post('kucgl/rukgl/qitrk/ruk',{rukdbh:$scope.ranlrkd.rukdbh})
		.success(function(data){
			alert("入库成功!");
			$("#add").hide();
			$("#save").hide();
			$("#ruk").hide();
			$("#chex").hide();
			$("select").attr("disabled", "disabled");
		}).error(function(data){
			alert("入库失败!");			

		})

	};
	
	$scope.chex=function(){
		$http.post('kucgl/caigrk/chex',{rukdbh:$scope.ranlrkd.rukdbh}).
	       success(function(data, status, headers, config) {
	    	if(data[0]>0){
	    		alert("撤销成功");
		$http.post('kucgl/caigrk/getRanlcgrk_WRK_MX2',{rukdbh:$scope.ranlrkd.rukdbh}).
	    	    success(function(data, status, headers, config) {
	    	    	$scope.ranlcgrkList = data.list;
	    	    	
	    	    	$scope.ranlrkd = {
	    		    		rukdbh : data.rukdbh,
	    		    		ruksl : data.zongsl,
	    		    		jine : data.zongje,
	    		    		zhuangt : data.zhuangt,
	    		    		kuczz : data.kuczz,
	    		    		huoz : data.huoz,
	    		    		yewlx : data.yewlx
	    		    	}
	    		});
	    		$location.path('/qitrk');
	    	}else{
	    		alert("撤销失败");
	    	}
	    });
	};
	
	$scope.cancel=function(){
		$location.path('/qitrk');
	};
})