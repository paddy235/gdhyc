gddlapp.controller('gongmjhcxCtrl', function($scope,$rootScope,$http,$log,$location) {
	$scope.yuegmjhTitle='供煤计划查询';
	$scope.search = new Object();
	$scope.search.gongysb_id = -1; 
	$scope.search.date = timeStamp2String(1);
	
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
	//------------------------------------------初始化table插件------------------------------------------------------------------
	 $scope.columnDefs =  [{
								"sClass" : "center",
								"mRender" : function(oObj, sVal) {
									return '<input type="checkbox" id="'+oObj+ '" name="checkId" onclick="check(this)" />';
								},
								"bSortable" : false,
								"aTargets" : [ 0 ]
							},
							{
								"sClass" : "center",
								"targets" : [ 1, 2, 3 ]
							}]
	 //---------------------------------------------------------------------------------------------------------------------------
	$scope.refresh=function(){
		var gongysbid = $scope.search.gongysb_id
		var date = $scope.search.date;
		$http.post('gongyspg/yuegmjh/searchYuegmjhList?gongysbid='+gongysbid+'&date='+date).success(function(data) {
	  			$scope.yuegmjh =data.data
	  			if(data.zhuangt==1||$scope.search.gongysb_id==-1){
	  				$("button.fbtn").removeClass("btn-primary").removeClass("btn-danger").attr("disabled",true)
	  			}else{
	  				$("button.fbtn").attr("disabled",false)
	  				$("#generate").addClass("btn-primary")
	  				$("#delete").addClass("btn-danger")
	  				$("#submit").addClass("btn-primary")
	  			}
	 	}) 
	}
	$scope.refresh();
	/*
	$scope.addGongmjh=function(){
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
	
	$scope.updateGongmjh=function(){
		var gongysbid = $scope.search.gongysb_id
		var date = $scope.search.date;
		$http.post('gongyspg/yuegmjh/updateYuegmjh',{gongysbid:$scope.search.gongysb_id,date:$scope.search.date}).
	       success(function(data, status, headers, config) {
	    	   alert("提交成功！")
	    	   $scope.refresh();
	    	   $("button.fbtn").removeClass("btn-primary").removeClass("btn-danger").attr("disabled",true)
	   });		
	}
	
	


	
	$scope.delGongmjh=function(){
		var gongysbid = $scope.search.gongysb_id
		var date = $scope.search.date;
		if(gongysbid == -1 ){
			alert('请选择正确的供应商');
			return false;
		}else{
			$http.post('gongyspg/yuegmjh/delYuegmjh',{gongysbid:$scope.search.gongysb_id,date:$scope.search.date}).
		       success(function(data, status, headers, config) {
		    	   if(data[0]==1){
		    		   alert("删除失败！");	
		    	   }else{
		    		   alert("删除成功！");		
		    	   }
		    	$scope.refresh();
		   });				
		}
	}*/
})


