gddlapp.controller('feiyxmflpageCtrl', function($scope, $rootScope,$routeParams, $http,$location) {
	$scope.feiyxmflTitle = '费用项目分类';
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
            "targets": [2,3,5,6]      

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
	oTable.fnReloadAjax('feiyxmfl/getAllData');
	$scope.changestate = function(){
			 $.post("feiyxmfl/changestate",//异步处理动态页面
 				     {id:$('input[name="checkId"]:checked').attr("id")},
 				     function(data){//data为反回值，function进行反回值处理
 				    	$("#changestate").removeClass("btn-primary");
 						$("#changestate").attr('disabled',true);
 						$("#updatefeiyxmfl").removeClass("btn-primary");
 						$("#updatefeiyxmfl").attr('disabled',true);
 				    	oTable.fnReloadAjax('feiyxmfl/getAllData');
 			});
   }
	$scope.addfeiyxmfl = function(){
		$scope.addtitle ="添加费用项目分类";
		console.log("son of bitch");
		$location.path('/feiyxmflAdd/0/'+$scope.addtitle); 
	}
	
	$scope.updatefeiyxmfl = function(){
		$scope.updatetitle ="修改费用项目分类";
		$scope.search.id = $('input[name="checkId"]:checked').attr("id");
		$location.path('/feiyxmflUpdate/'+$scope.search.id+'/'+$scope.updatetitle); 
	}
})
.controller('addorupdatefeiyxmflCtrl', function($scope, $rootScope,$routeParams, $http,$location) {
	$scope.addorupdatefeiyxmflTitle = $routeParams.title;
	$http.post('feiyxmfl/getfeilkj').success(function(data){ $scope.fenlkjList=data;});
	$scope.fenlkjbean = new Object();
	if($routeParams.id != 0){
		$http.post('feiyxmfl/getFeiyxmflById',{id:$routeParams.id}).success(function(data){
			$scope.fenlkjbean.FENLKJ = data.FENLKJ_ID;
			$scope.fenlkjbean.MINGC =data.MINGC;
			$scope.fenlkjbean.SHUOM =data.SHUOM;
			$scope.fenlkjbean.ID =data.ID;
		});
	}else{
		$scope.fenlkjbean.ID = $routeParams.id;
	}
	$scope.cancel = function(){
		console.log("fuck");
		$location.path('/feiyxmflcx'); 
	}
	$scope.saveFenyxm = function(){
		if($scope.fenlkjbean.ID==0){
			$http.post('feiyxmfl/feiyxmfladd',{info:angular.toJson($scope.fenlkjbean)}).success(function(data){
				alert(data);
				$location.path('/feiyxmflcx');
			});
		}else{
			$http.post('feiyxmfl/feiyxmflupdate',{info:angular.toJson($scope.fenlkjbean)}).success(function(data){
				alert(data);
				$location.path('/feiyxmflcx');
			});
		}
	}
	
});
