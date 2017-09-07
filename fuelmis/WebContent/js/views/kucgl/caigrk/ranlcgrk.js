gddlapp.filter('trustHtml', function ($sce) {
    return function (input) {
        return $sce.trustAsHtml(input);
    }
});

gddlapp.controller('ranlcgrkCtrl', function($scope,$rootScope,$http,$log,$location) {
	$scope.ranlcgrkTitle='燃料采购入库';
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
		$http.post('kucgl/rukgl/ranlcgrk/getAll',{search:angular.toJson($scope.search)}).
	    success(function(data) {
	    	$scope.rukdAllList = data.data;
		}).error(function(data){
			alert("查询数据出错!");
		})
	}
	$scope.searchData();
	$scope.edit=function(args){
		$location.path('/addRanlcgrk/'+args);
	}
	
	$scope.addRanlcgrk=function(){
		$location.path('/addRanlcgrk');
	}
}).controller('addRanlcgrkCtrl', function($scope,$rootScope,$http,$log,$location,$routeParams) {
	$scope.addRanlcgrkTitle='燃料采购入库';
	
	$scope.kucztList = [{"name":"未入库","value":-1},{"name":"已入库","value":1}];
	
	$http.post('kucgl/rukgl/ranlcgrk/getRanlcgrk_WRK_MX',{rukdbh:$routeParams.rukdbh}).
	    success(function(data, status, headers, config) {
	    	$scope.ranlcgrkList = data.list;
	    	
	    	$scope.ranlrkd = {
	    		rukdbh : data.rukdbh,
	    		ruksl : data.zongsl,
	    		jine : data.zongje,
	    		zhuangt : data.zhuangt,
	    		kuczz : data.kuczz,
	    		huoz : data.huoz,
	    		yewlx : "燃料采购入库"
	    	}
		});
	
	$http.post('kucgl/rukgl/ranlcgrk/check',{rukdbh:$routeParams.rukdbh}).
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
		yewlx : "燃料采购入库"
	};
	
	$scope.getYansmx=function(){
		$location.path('/yansmx');
	};
	
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
				$http.post('kucgl/rukgl/ranlcgrk/save',{rukdbh:$scope.ranlrkd.rukdbh,kuczz:$scope.ranlrkd.kuczz,huoz:$scope.ranlrkd.huoz,rukdList:angular.toJson($scope.ranlcgrkList)}).
			       success(function(data, status, headers, config) {
			    	if(data[0]>0){
			    		alert("保存成功");
			    		$http.post('kucgl/rukgl/getRanlcgrk_WRK_MX',{rukdbh:$scope.ranlrkd.rukdbh}).
				    	    success(function(data, status, headers, config) {
				    	    	$scope.ranlcgrkList = data.list;
				    	    	
				    	    	$scope.ranlrkd = {
				    		    		rukdbh : data.rukdbh,
				    		    		ruksl : data.zongsl,
				    		    		jine : data.zongje,
				    		    		zhuangt : data.zhuangt,
				    		    		kuczz : data.kuczz,
				    		    		huoz : data.huoz,
				    		    		yewlx : "燃料采购入库"
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
		$http.post('kucgl/rukgl/ranlcgrk/ruk',{rukdbh:$scope.ranlrkd.rukdbh}).
	       success(function(data, status, headers, config) {
	    	if(data[0]>0){
	    		alert("入库成功");

	    		$http.post('kucgl/rukgl/getRanlcgrk_WRK_MX',{rukdbh:$scope.ranlrkd.rukdbh}).
	    	    success(function(data, status, headers, config) {
	    	    	$scope.ranlcgrkList = data.list;
	    	    	
	    	    	$scope.ranlrkd = {
	    		    		rukdbh : data.rukdbh,
	    		    		ruksl : data.zongsl,
	    		    		jine : data.zongje,
	    		    		zhuangt : data.zhuangt,
	    		    		kuczz : data.kuczz,
	    		    		huoz : data.huoz,
	    		    		yewlx : "燃料采购入库"
	    		    	}
	    		});
	    		
	    		$http.post('kucgl/rukgl/check',{rukdbh:$scope.ranlrkd.rukdbh}).
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
		$http.post('kucgl/rukgl/ranlcgrk/chex',{rukdbh:$scope.ranlrkd.rukdbh}).
	       success(function(data, status, headers, config) {
	    	if(data[0]>0){
	    		alert("撤销成功");

	    		$http.post('kucgl/rukgl/getRanlcgrk_WRK_MX',{rukdbh:$scope.ranlrkd.rukdbh}).
	    	    success(function(data, status, headers, config) {
	    	    	$scope.ranlcgrkList = data.list;
	    	    	
	    	    	$scope.ranlrkd = {
	    		    		rukdbh : data.rukdbh,
	    		    		ruksl : data.zongsl,
	    		    		jine : data.zongje,
	    		    		zhuangt : data.zhuangt,
	    		    		kuczz : data.kuczz,
	    		    		huoz : data.huoz,
	    		    		yewlx : "燃料采购入库"
	    		    	}
	    		});
	    	}else{
	    		alert("撤销失败");
	    	}
	    });
	};
	
	$scope.editCaigdd=function(){
		var rukdbh = $scope.ranlrkd.rukdbh;
    	$location.path('/changeCaigdd/'+rukdbh);
	}
	
	$scope.cancel=function(){
		$location.path('/ranlcgrk');
	};
}).controller('yansmxCtrl', function($scope,$rootScope,$http,$log,$location) {
	$scope.yansmxTitle='燃料采购入库验收明细';
	
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
			$http.post('kucgl/caigrk/ranlcgrk',{ids:chepb_id}).
		       success(function(data, status, headers, config) {
		    	   if(data[0]!=""&&data[0]!=null){
			    		$location.path('/addRanlcgrk/'+data[0]);
		    	   }else{
		    		   alert("添加验收明细信息失败！");
		    	   }
		    });
		}
	};
	
	$scope.cancel=function(){
		$location.path('/addRanlcgrk');
	};
}).controller('changeCaigddCtrl', function($scope,$rootScope,$http,$log,$location,$routeParams) {
	$scope.changeCaigddTitle='选择采购订单';
	
	$scope.queren=function(){
		if($("#example input[type=checkbox]:checked").length<1){
			alert("请选择采购订单！")
		}else{
			$("#example input[type=checkbox]").each(function(){
			    if(this.checked){
			    	$http.post('kucgl/caigrk/editCaigdd',{rukdbh:$routeParams.rukdbh,caigdd_id:$(this).attr("id"),yewlx:1}).
				       success(function(data, status, headers, config) {
				    	   if(data[0]!=""&&data[0]!=null){
					    		$location.path('/addRanlcgrk/'+data[0]);
				    	   }else{
				    		   alert("修改采购订单失败！");
				    	   }
				    });
			    }
			}); 
		}
	};
	
	$scope.cancel=function(){
		$location.path('/addRanlcgrk/'+$routeParams.rukdbh);
	};
})

function chakmx(oObj){
	oTable2.fnReloadAjax('kucgl/caigrk/getYansmxMX?yewlx=1&samcode='+oObj);
	$('#myModal_View').modal('show');
};