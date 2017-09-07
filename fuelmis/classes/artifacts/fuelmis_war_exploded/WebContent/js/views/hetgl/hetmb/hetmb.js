gddlapp.controller('hetmbCtrl', function($scope,$rootScope,$http,$log,$location) {
	
	$scope.hetmbTitle='合同模板维护';
	$scope.search = new Object();
	$scope.search.diancid = 515;
	$scope.search.strdate = timeStamp2String(2);
	$scope.search.enddate = timeStamp2String(1);
	
	function timeStamp2String(type){
	    var datetime = new Date();
	    var year = datetime.getFullYear();
	    var month = datetime.getMonth() + 1 < 10 ? "0" + (datetime.getMonth() + 1) : datetime.getMonth() + 1;
	    var date = datetime.getDate() < 10 ? "0" + datetime.getDate() : datetime.getDate();
	    var hour = datetime.getHours()< 10 ? "0" + datetime.getHours() : datetime.getHours();
	    var minute = datetime.getMinutes()< 10 ? "0" + datetime.getMinutes() : datetime.getMinutes();
	    var second = datetime.getSeconds()< 10 ? "0" + datetime.getSeconds() : datetime.getSeconds();
	    if(type == 1){
	    	return year + "-" + month + "-" + date;
	    }else{
	    	return year + "-" + month + "-"+"01"
	    }
	    
	}	
	
	
	$scope.addHetmb=function(){
		var flag = "Add";
		$location.path('/addHetmb/'+flag);
	}
	$scope.testHetmb = function(){
		$location.path('/testHetmb');
	}	
	
	$scope.editHetmb=function(){
		if($("#example input[type=checkbox]:checked").length<1){
			alert("请选择要修改的行！");
		}else{
			$("#example input[type=checkbox]").each(function(){
			    if(this.checked){
					$location.path('/editHetmb/'+$(this).attr("id"));
			    }
			}); 
		}
	}
	
	
	$scope.refresh=function(){
		var diancid = $scope.search.diancid;
		var strdate = $scope.search.strdate;
		var enddate = $scope.search.enddate;
		oTable.fnReloadAjax('hetmb/searchHetmbList/?diancid='+diancid+'&strdate='+strdate+'&enddate='+enddate);		
	}
	
	
	
	$scope.delHetmb=function(){
		if($("#example input[type=checkbox]:checked").length<1){
			alert("请选择要删除的行！")
		}else{
			$('#myModal_Del').modal('show');
		}
	}	
	
	
})



.controller('hetmbAddCtrl',function($scope,$rootScope,$http,$location,$routeParams) {
	$scope.hetmbTitle = '添加合同模板';
	$scope.fujxx = new Object(); 

	if($routeParams.flag=="Add"){
		$scope.fujxxTitle='新增合同模板信息';
		$scope.fujxx.zhuangt = 1;
	}else if($routeParams.flag=="Update"){
		$scope.hetbeanTitle='修改合同模板信息';
		$http.post('hetgl/hetmb/editHetmb',{id:$routeParams.id}).
			success(function(data, status, headers, config) {
				if(data){
					$scope.fujxx = data.fujxx;
				}
		});
	}
	
	$scope.fjUpload=function(){
		 $scope.ajaxFileUpload('cangdbl');
	 };
	 
	 $scope.$on('fileUploadSuccess', function(event,data) {
			if(data.id){
//				多文件				
//				if($scope.fujLst){
//					$scope.fujLst.push(data);
//					$scope.$apply();
//				}else{
//					$scope.fujLst.push(data); 
//					$scope.$apply();
//				}
				$scope.fujxx.id = data.id;
			}
	  });
	 	 
		$scope.saveHetmb=function(){
			var fujxxid = $scope.fujxx.id;
			if(fujxxid == null || fujxxid == ''){
				alert("附件未保存请先点击上传");
			}else{
			$scope.fujxx.zhuangt = 1;
			$http.post('hetgl/hetmb/updateHetmb',{info:angular.toJson($scope.fujxx)}).			
		       success(function(data, status, headers, config) {
		    	   if(data[0]==1){
		    		   alert("新增合同模板信息成功！");
		    		   $location.path('hetgl/hetmb');
		    	   }else{
		    		   alert("新增合同模板信息失败！");
		    		   $location.path('hetgl/hetmb');
		    	   }
		   });
		}	
		}
		$scope.cancel = function (){
			$location.path('hetgl/hetmb');
		}
})

.controller('hetmbEditCtrl',function($scope,$rootScope,$http,$location,$routeParams) {
	$scope.fujxx = new Object(); 
	$scope.hetbeanTitle='修改合同模板信息';
		$http.post('hetgl/hetmb/editHetmb',{id:$routeParams.id}).
			success(function(data, status, headers, config) {
				if(data){
					$scope.fujxx = data.fujxx;
				}
	});
	
	$scope.saveHetmb=function(){
			$scope.fujxx.zhuangt = 1;
			$http.post('hetgl/hetmb/updateHetmb',{info:angular.toJson($scope.fujxx)}).			
		       success(function(data, status, headers, config) {
		    	   if(data[0]==1){
		    		   alert("修改合同模板成功！");
		    		   $location.path('hetgl/hetmb');
		    	   }else{
		    		   alert("修改合同模板失败！");
		    		   $location.path('hetgl/hetmb');
		    	   }
		   });	
		}
	$scope.cancel = function (){
			$location.path('hetgl/hetmb');
	}
})



