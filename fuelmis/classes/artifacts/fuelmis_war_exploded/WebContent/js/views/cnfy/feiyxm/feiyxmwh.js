gddlapp.controller('feiyxmwhCtrl', function($scope, $rootScope,$routeParams, $http,$location) {
	$scope.feiyxmwhTitle = '费用项目维护';
	$scope.search = new Object();
	var oTable = $('#example').dataTable({
		"processing" : true,
		"language" : {
			"sLengthMenu" : "每页显示 _MENU_条",
			"sZeroRecords" : "没有找到符合条件的数据",
			"sProcessing" : "数据加载中...",
			"sInfo" : "当前第 _START_ - _END_ 条　共计 _TOTAL_ 条",
			"sInfoEmpty" : "没有记录",
			"sInfoFiltered" : "(从 _MAX_ 条记录中过滤)",
			"sSearch" : "搜索：",
			"oPaginate" : {
				"sFirst" : "首页",
				"sPrevious" : "前一页",
				"sNext" : "后一页",
				"sLast" : "尾页"
			}
		},
//		"sScrollX": "100%",
//		"sScrollXInner": "120%",
//		"bScrollCollapse": true,
		"aoColumnDefs": [{
            "sClass": "center",
            "mRender": function(oObj, sVal) {
            	 return '<input type="checkbox" id="' + oObj + '" name="checkId" onclick="check(this)" />';
            },
            "bSortable": false,
            "aTargets": [0]
        },
        {  
        	 "sClass": "center",
            "targets": [2,3,4,6,7,8,9,10]      

        },
        {  
        	"targets": [1] ,
       	 "sClass": "right"      

       }
		
		]
	});
	$("#datepicker").datepicker({
			format : 'yyyy',
			minViewMode : 2,
			language : "zh-CN",
			autoclose : true
	});
	oTable.fnReloadAjax('feiyxmwh/getAllData');
	$scope.changestate = function(){
			 $.post("feiyxmwh/changestate",//异步处理动态页面
 				     {id:$('input[name="checkId"]:checked').attr("id")},
 				     function(data){//data为反回值，function进行反回值处理
 				    	$("#changestate").removeClass("btn-primary");
 						$("#changestate").attr('disabled',true);
 						$("#updatefeiyxmwh").removeClass("btn-primary");
 						$("#updatefeiyxmwh").attr('disabled',true);
 				    	oTable.fnReloadAjax('feiyxmwh/getAllData');
 			});
   }
	$scope.addfeiyxmwh = function(){
		$scope.addtitle ="添加费用项目维护";
		$location.path('/feiyxmwhAdd/0/'+$scope.addtitle); 
	}
	
	$scope.updatefeiyxmwh = function(){
		$scope.updatetitle ="修改费用项目维护";
		$scope.search.id = $('input[name="checkId"]:checked').attr("id");
		$location.path('/feiyxmwhUpdate/'+$scope.search.id+'/'+$scope.updatetitle); 
	}
})
.controller('addorupdatefeiyxmwhCtrl', function($scope, $rootScope,$routeParams, $http,$location) {
	$scope.addorupdatefeiyxmwhTitle = $routeParams.title;
	$scope.feiyxmwh = new Object();
	$http.post('feiyxmsq/getfeiyxmfl').success(function(data){ $scope.feiyxmflList=data;});
	$http.post('feiyxmsq/getFeiyxmsx').success(function(data){ $scope.feiyxmsxList=data;});
	if($routeParams.id != 0){
		$http.post('feiyxmwh/getfeiyxmwhById',{id:$routeParams.id}).success(function(data){
			$scope.feiyxmwh.MINGC =data.MINGC;
			$scope.feiyxmwh.SHUOM =data.SHUOM;
			$scope.feiyxmwh.DANW = data.DANW;
			$scope.feiyxmwh.GUANLFL = data.GUANLFL;
			$scope.feiyxmwh.CAIWFL = data.CAIWFL;
			$scope.feiyxmwh.FEIYXMSX = data.FEIYXMSX_ID;
			$scope.feiyxmwh.FEIYXMFLID = data.FEIYXMFL_ID;
			$scope.feiyxmwh.ID =data.ID;
		});
	}else{
		$scope.feiyxmwh.ID = $routeParams.id;
	}
	$scope.cancel = function(){
		$location.path('/feiyxmwh'); 
	}
	$scope.saveFenyxm = function(){
		if($scope.feiyxmwh.ID==0){
			$http.post('feiyxmwh/feiyxmwhadd',{info:angular.toJson($scope.feiyxmwh)}).success(function(data){
				alert(data);
				$location.path('/feiyxmwh');
			});
		}else{
			$http.post('feiyxmwh/feiyxmwhupdate',{info:angular.toJson($scope.feiyxmwh)}).success(function(data){
				alert(data);
				$location.path('/feiyxmwh');
			});
		}
	}
	
});
