gddlapp.controller('yuegmjhCtrl', function($scope,$rootScope,$http,$log,$location) {
	$scope.yuegmjhTitle='月供煤计划维护'; 
	$scope.search = new Object();
	$scope.search.gongysb_id = -1; 
	$scope.search.date = timeStamp2String(1);
	$scope.search.zhuangt=0;
	
	function timeStamp2String(type){
	    var datetime = new Date();
	    var year = datetime.getFullYear();
	    var month = datetime.getMonth() + 1 < 10 ? "0" + (datetime.getMonth() + 1) : datetime.getMonth() + 1;
	    var date = datetime.getDate() < 10 ? "0" + datetime.getDate() : datetime.getDate();
	    var hour = datetime.getHours()< 10 ? "0" + datetime.getHours() : datetime.getHours();
	    var minute = datetime.getMinutes()< 10 ? "0" + datetime.getMinutes() : datetime.getMinutes();
	    var second = datetime.getSeconds()< 10 ? "0" + datetime.getSeconds() : datetime.getSeconds();
	    if(type == 1){
	    	return year + "-" + month;
	    }else{
	    	return year + "-" + month;
	    }
	    
	}
	$scope.refresh=function(){ 
		if($scope.search.gongysb_id == -1){
			alert("请选择供应商！")
		}else{
			$http.post('gongyspg/yuegmjh/getYuegmjhList',{condition : angular.toJson($scope.search)}).success(function(data) {
				if(data.length==0){
					alert("数据以提交！")
				}
				$scope.datalist =data
				if($scope.search.zhuangt!=0){
					$("button.fbtn").removeClass("btn-primary").removeClass("btn-danger").attr("disabled",true)
				}else{
					$("button.fbtn").attr("disabled",false)
					$("#delete").addClass("btn-danger")
					$("#submit").addClass("btn-primary")
					$("#save").addClass("btn-primary")
				}
			});
		}
    }
	$scope.save=function(){ 
		$http.post('gongyspg/yuegmjh/saveYuegmjh',{data : angular.toJson($scope.datalist),condition :angular.toJson($scope.search)}).success(function(data) {
		 alert("保存成功！");				
		})
	}
	$scope.addYuegmjh=function(){ 
		var gongysbid = $scope.search.gongysb_id 
		var date = $scope.search.date;
		$http.post('gongyspg/yuegmjh/addYuegmjh',{gongysbid:$scope.search.gongysb_id,date:$scope.search.date}).
		   success(function(data, status, headers, config) {
			   if(data[0]==1){
				   alert("生成成功！");	
			   }if(data[0]==5){
				   alert("请先录入合同");
			   }if(data[0]==0){
				   alert("生成失败！");		
			   }
			$scope.refresh();
	   });		
	}
	
	$scope.submitYuegmjh=function(){
		$http.post('gongyspg/yuegmjh/submitYuegmjh',{condition :angular.toJson($scope.search)}).
	       success(function(data, status, headers, config) {
	    	   alert("提交成功！")
	    	   $scope.refresh();
	    	   $("button.fbtn").removeClass("btn-primary").removeClass("btn-danger").attr("disabled",true)
	   });		
	}
	
	


	
	$scope.delYuegmjh=function(){
		var gongysbid = $scope.search.gongysb_id
		var date = $scope.search.date;
		if(gongysbid == -1 ){
			alert('请选择正确的供应商');
			return false;
		}else{
			$http.post('gongyspg/yuegmjh/delYuegmjh',{gongysbid:$scope.search.gongysb_id,date:$scope.search.date}).
		       success(function(data, status, headers, config) {
		    	   if(data[0]==0){
		    		   alert("删除失败！");	
		    	   }else{
		    		   alert("删除成功！");		
		    	   }
		    	$scope.refresh();
		   });				
		}
	}
})


