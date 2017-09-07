gddlapp.controller('chepbtmpCtrl', function($scope,$rootScope,$http,$log,$location) {
	$scope.chepbtmpTitle='车批表数据';
	$scope.search = new Object();
	$scope.search.diancid = 515;
	$scope.search.strdate = timeStamp2String(2);
	$scope.search.enddate = timeStamp2String(1);
	$scope.chepbtmpList = new Array();
	
	
	$http.post('rucslys/chepbtmp/getChepbtmpList').
    success(function(data, status, headers, config) {
    	$scope.chepbtmpList = data.data;
    });
	
	
	
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
	
	$scope.update=function(){
		$http.post('rucslys/chepbtmp/updateData',{info:angular.toJson($scope.chepbtmpList)}).
	       success(function(data, status, headers, config) {
	    	   alert(data);
	    	   alert(data[0])
//	    	   if(data[0]==1){
//	    		   alert("新增合同发货订单信息成功！");
//	    		   $location.path('/hetgl/caigddb');
//	    	   }else{
//	    		   alert("新增合同发货订单信息失败！");
//	    		   $location.path('/hetgl/caigddb');
//	    	   }
	    	   $location.path('/hetgl/caigddb');
	   });
	}
	
	$scope.editChepbtmp=function(){
		if($("#example input[type=checkbox]:checked").length<1){
			alert("请选择要修改的行！");
		}else{
			$("#example input[type=checkbox]").each(function(){
			    if(this.checked){
			    	var flag = "Update";
					$location.path('/addChepbtmp/'+flag+"/"+$(this).attr("id"));
			    }
			}); 
		}
	}
	
	
	$scope.refresh=function(){
		var diancid = $scope.search.diancid;
		var strdate = $scope.search.strdate;
		var enddate = $scope.search.enddate;		
		$http.post('rucslys/chepbtmp/getChepbtmpListInfo/?diancid='+diancid+'&strdate='+strdate+'&enddate='+enddate).
	    success(function(data, status, headers, config) {
	    	$scope.chepbtmpList = data;
	    });
		oTable.fnReloadAjax('rucslys/chepbtmp/searchChepbtmpList/?diancid='+diancid+'&strdate='+strdate+'&enddate='+enddate);		
	}
	
})