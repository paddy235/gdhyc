gddlapp.controller('yunzfcgrkCtrl', function($scope,$rootScope,$http,$log,$location) {
	$scope.yunzfcgrkTitle='运杂费采购入库';
	var date = new Date();
	var year = date.getFullYear();
	var month = date.getMonth()+1<10?'0'+(date.getMonth()+1):date.getMonth()+1;
	var day = date.getDate()<10?'0'+date.getDate():date.getDate();
	
	$scope.search = {
		sDate : year+'-'+month+'-01',
		eDate : year+'-'+month+'-'+day,
		rukdbh:''
	}
	

	
	$scope.searchData = function() {
		$http.post('kucgl/rukgl/yunzfrk/getAll',{serach:angular.toJson($scope.search)})
		.success(function(data) {
	    	$scope.rukdAllList = data.data;
		}).error(function(data){
			alert("查询数据出错!")
		});
	}
	
	$scope.edit=function(args){
		$location.path('/addYunzfcgrk/'+args);
	}
	
	$scope.addRanlcgrk=function(){
		$location.path('/addYunzfcgrk');
	}
}).controller('addYunzfcgrkCtrl', function($scope,$rootScope,$http,$log,$location,$routeParams) {
	$scope.addYunzfcgrkTitle='运杂费采购入库';
	
	$scope.kucztList = [{"name":"未入库","value":-1},{"name":"已入库","value":1}];
	
	$http.post('kucgl/rukgl/yunzfrk/getRanlcgrk_WRK_MX',{rukdbh:$routeParams.rukdbh}).
	    success(function(data, status, headers, config) {
	    	$scope.ranlcgrkList = data.list;
	    	
	    	$scope.ranlrkd = {
	    		rukdbh : data.rukdbh,
	    		ruksl : data.zongsl,
	    		jine : data.zongje,
	    		zhuangt : data.zhuangt,
	    		kuczz : data.kuczz,
	    		huoz : data.huoz,
	    		yewlx : "运杂费采购入库"
	    	}
		});
	
	$http.post('kucgl/rukgl/yunzfrk/check',{rukdbh:$routeParams.rukdbh}).
	    success(function(data, status, headers, config) {
	    	if(data[0]>0){
	    		$("#yansmx").hide();
	    		$("#save").hide();
	    		$("#ruk").hide();
	    		$("#chex").hide();
	    		$("#caigdd").hide();
	    		$("select").attr("disabled","disabled"); 
	    	}
		});
	
	$scope.ranlrkd = {
		zhuangt : -1,
		kuczz : 515,
		huoz : 515,
		yewlx : "运杂费采购入库"
	}
	
	$scope.getYansmx=function(){
		$location.path('/yansmx_yunzf');
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
				$http.post('kucgl/rukgl/yunzfrk/save_yunzf',{rukdbh:$scope.ranlrkd.rukdbh,kuczz:$scope.ranlrkd.kuczz,huoz:$scope.ranlrkd.huoz,rukdList:angular.toJson($scope.ranlcgrkList),yewlx:2}).
			       success(function(data, status, headers, config) {
			    	if(data[0]>0){
			    		alert("保存成功");
			    		$http.post('kucgl/rukgl/yunzfrk/getRanlcgrk_WRK_MX',{rukdbh:$scope.ranlrkd.rukdbh}).
				    	    success(function(data, status, headers, config) {
				    	    	$scope.ranlcgrkList = data.list;
				    	    	
				    	    	$scope.ranlrkd = {
				    		    		rukdbh : data.rukdbh,
				    		    		ruksl : data.zongsl,
				    		    		jine : data.zongje,
				    		    		zhuangt : data.zhuangt,
				    		    		kuczz : data.kuczz,
				    		    		huoz : data.huoz,
				    		    		yewlx : "运杂费采购入库"
				    		    	}
				    		});
			    	}else{
			    		alert("保存失败");
			    	}
			    });
			}else{
				alert("信息填写不完整，不能进行保存！");
			}
		}else{
			alert("信息填写不完整，不能进行保存！");
		}
	};
	
	$scope.ruk=function(){
		$http.post('kucgl/rukgl/yunzfrk/ruk',{rukdbh:$scope.ranlrkd.rukdbh}).
	       success(function(data, status, headers, config) {
	    	if(data[0]>0){
	    		alert("入库成功");

	    		$http.post('kucgl/rukgl/yunzfrk/getRanlcgrk_WRK_MX',{rukdbh:$scope.ranlrkd.rukdbh}).
	    	    success(function(data, status, headers, config) {
	    	    	$scope.ranlcgrkList = data.list;
	    	    	
	    	    	$scope.ranlrkd = {
	    		    		rukdbh : data.rukdbh,
	    		    		ruksl : data.zongsl,
	    		    		jine : data.zongje,
	    		    		zhuangt : data.zhuangt,
	    		    		kuczz : data.kuczz,
	    		    		huoz : data.huoz,
	    		    		yewlx : "运杂费采购入库"
	    		    	}
	    		});
	    		
	    		$http.post('kucgl/rukgl/yunzfrk/check',{rukdbh:$scope.ranlrkd.rukdbh}).
	    	    success(function(data, status, headers, config) {
	    	    	if(data[0]>0){
	    	    		$("#yansmx").hide();
	    	    		$("#save").hide();
	    	    		$("#ruk").hide();
	    	    		$("#chex").hide();
	    	    		$("#caigdd").hide();
	    	    		$("select").attr("disabled","disabled"); 
	    	    	}
	    		});
	    	}else{
	    		alert("入库失败");
	    	}
	    });
	};
	
	$scope.chex=function(){
		$http.post('kucgl/rukgl/yunzfrk/chex',{rukdbh:$scope.ranlrkd.rukdbh}).
	       success(function(data, status, headers, config) {
	    	if(data[0]>0){
	    		alert("撤销成功");

	    		$http.post('kucgl/rukgl/yunzfrk/getRanlcgrk_WRK_MX',{rukdbh:$scope.ranlrkd.rukdbh}).
	    	    success(function(data, status, headers, config) {
	    	    	$scope.ranlcgrkList = data.list;
	    	    	
	    	    	$scope.ranlrkd = {
	    		    		rukdbh : data.rukdbh,
	    		    		ruksl : data.zongsl,
	    		    		jine : data.zongje,
	    		    		zhuangt : data.zhuangt,
	    		    		kuczz : data.kuczz,
	    		    		huoz : data.huoz,
	    		    		yewlx : "运杂费采购入库"
	    		    	}
	    		});
	    	}else{
	    		alert("撤销失败");
	    	}
	    });
	};
	
	$scope.editCaigdd=function(){
		var rukdbh = $scope.ranlrkd.rukdbh;
    	$location.path('/changeCaigdd_yunzf/'+rukdbh);
	}
	
	$scope.cancel=function(){
		$location.path('/yunzfcgrk');
	};
}).controller('yansmx_yunzfCtrl', function($scope,$rootScope,$http,$log,$location) {
	$scope.yansmx_yunzfTitle='运杂费采购入库验收明细';
	
	var date = new Date();
	var year = date.getFullYear();
	var month = date.getMonth()+1<10?'0'+(date.getMonth()+1):date.getMonth()+1;
	var day = date.getDate()<10?'0'+date.getDate():date.getDate();
	
	$scope.search = {
		sDate : year+'-'+month+'-01',
		eDate : year+'-'+month+'-'+day
	}
	
	$scope.searchData = function() {
		var sDate = $scope.search.sDate;
		var eDate = $scope.search.eDate;
		oTable.fnReloadAjax('kucgl/rukgl/yunzfrk/getYansmx?yewlx=2&sDate='+sDate+"&eDate="+eDate);
	}
	
	$scope.queren=function(){
		var chepb_id = '';
		if($("#example input[name='checkId']:checked").length<1){
			alert("请选择验收明细行！");
		}else{
			$("#example input[name='checkId']").each(function(){
			    if(this.checked){
			    	chepb_id = $(this).attr("id");
			    }
			});
			$http.post('kucgl/rukgl/yunzfrk/yunzfcgrk',{ids:chepb_id}).
		       success(function(data, status, headers, config) {
		    	   if(data[0]!=""&&data[0]!=null){
			    		$location.path('/addYunzfcgrk/'+data[0]);
		    	   }else{
		    		   alert("添加验收明细信息失败！");
		    	   }
		    });
		}
	};
	
	$scope.cancel=function(){
		$location.path('/addYunzfcgrk');
	};
}).controller('changeCaigdd_yunzfCtrl', function($scope,$rootScope,$http,$log,$location,$routeParams) {
	$scope.changeCaigdd_yunzfTitle='选择采购订单';
	
	$scope.queren=function(){
		if($("#example input[type=checkbox]:checked").length<1){
			alert("请选择采购订单！")
		}else{
			$("#example input[type=checkbox]").each(function(){
			    if(this.checked){
			    	$http.post('kucgl/rukgl/yunzfrk/editCaigdd',{rukdbh:$routeParams.rukdbh,caigdd_id:$(this).attr("id"),yewlx:2}).
				       success(function(data, status, headers, config) {
				    	   if(data[0]!=""&&data[0]!=null){
					    		$location.path('/addYunzfcgrk/'+data[0]);
				    	   }else{
				    		   alert("修改采购订单失败！");
				    	   }
				    });
			    }
			}); 
		}
	};
	
	$scope.cancel=function(){
		$location.path('/addYunzfcgrk/'+$routeParams.rukdbh);
	};
})

function chakmx(oObj){
	oTable2.fnReloadAjax('kucgl/rukgl/yunzfrk/getYansmxMX?yewlx=2&samcode='+oObj);
	$('#myModal_View').modal('show');
};