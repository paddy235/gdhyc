gddlapp.controller('yustssqCtrl', function($scope, $rootScope,$routeParams, $http,$location) {
	$scope.yustzsqTitle = '预算调整申请';
	$http.post('yuedyussq/getzaf').success(function(data){ $scope.zafList=data;});
	$scope.search = new Object();
	$scope.search.zafid = -1;
	$scope.search.diancid = 515;
	if($routeParams.zafid != null){
		$scope.search.zafid = Number($routeParams.zafid);
	}
	if($routeParams.diancid != null){
		$scope.search.diancid = Number($routeParams.diancid);
	}
	var oTable = $('#example').dataTable({
		"processing" : true,
		'ajax' : 'yuedcaigjh/getCaigList',
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
		'scrollX': 200,
		'scrollCollapse': true,
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
         "targets": [2,3]      

         },
         {  
         	"targets": [1,4] ,
        	 "sClass": "right"      

        }
		
		]
	});
	oTable.fnReloadAjax('yutzsq/yustzcx?zafid='+$scope.search.zafid+'&diancid='+$scope.search.diancid);
	$scope.refresh = function(){
	    oTable.fnReloadAjax('yutzsq/yustzcx?zafid='+$scope.search.zafid+'&diancid='+$scope.search.diancid);
	}
	$scope.addyuedcgjh = function(){
		$scope.addtitle ="添加预算调整申请";
		$location.path('/yustzsqAdd/'+$scope.search.zafid+'/'+$scope.search.diancid+'/0/'+$scope.addtitle); 
	}
	$scope.submit = function(){
		var id = "";
		 id = $('input[name="checkId"]:checked').attr("id");
		 if(id==""){
			 alert("请选择一项提交！！！");
		 }else{
			 $http.post('yutzsq/submitToGuod',{id:id}).success(function(data){
					alert(data);
				});
		 }
	}
	$scope.delyustz = function(){
		var id = "";
		 id = $('input[name="checkId"]:checked').attr("id");
		 if(id==""){
			 alert("请选择一项进行删除！！！");
		 }else{
			 var a =confirm("你确定要删除此条记录?");
			 if(a){
				 $.post("yutzsq/delyustz",//异步处理动态页面
	  				     {id:id},//获取类名为"name"文本的值，以NAME异步传值
	  				     function(data){//data为反回值，function进行反回值处理
	  				    	$("#delyustz").removeClass("btn-primary");
	  						$("#delyustz").attr('disabled',true);
	  						$("#updateyustz").removeClass("btn-primary");
	  						$("#updateyustz").attr('disabled',true);
	  						var zafid = $scope.search.zafid;
				   	        var diancid= $scope.search.diancid;
				   	    	oTable.fnReloadAjax('yutzsq/yustzcx?zafid='+zafid+'&diancid='+diancid);
	  				        alert(data);//获得得反回值后，将其填入到类名为"content"的文本框中
	  				      });
			 }else{
				 return false;
			 }
		 }
	}
	$scope.updateyustzsq = function(){  
		$scope.updatetitle ="修改月度预算申请";
		$scope.search.id = $('input[name="checkId"]:checked').attr("id");
		$location.path('/yustzsqUpdate/'+$scope.search.id+'/'+$scope.updatetitle+'/'+$scope.search.zafid+'/'+$scope.search.diancid);
	}
	$scope.selectzaf = function(){
		 var zafid= $scope.search.zafid;
		 var dianc =  $scope.search.diancid;
		 if(zafid!=-1&&dianc!=-1){
			 $("#adddata").addClass("btn-primary");
			 $("#adddata").removeAttr("disabled");//移除disabled属性 
			 $("#copyniandcgjh").addClass("btn-primary");
			 $("#copyniandcgjh").removeAttr("disabled");//移除disabled属性 

		 }else{
			 $("#adddata").removeClass("btn-primary");
			 $("#adddata").attr('disabled',true);
			 $("#copyniandcgjh").removeClass("btn-primary");
			 $("#copyniandcgjh").attr('disabled',true);
		 }
		 $scope.refresh();
	}
	$scope.selectdianc = function(){
		var zafid= $scope.search.zafid;
		 var dianc =  $scope.search.diancid;
		 if(zafid!=-1 && dianc!=-1){
			 $("#adddata").addClass("btn-primary");
			 $("#adddata").removeAttr("disabled"); //移除disabled属性 
			 $("#copyniandcgjh").addClass("btn-primary");
			 $("#copyniandcgjh").removeAttr("disabled"); //移除disabled属性 
		 }else{
			 $("#adddata").removeClass("btn-primary");
			 $("#adddata").attr('disabled',true);
			 $("#copyniandcgjh").removeClass("btn-primary");
			 $("#copyniandcgjh").attr('disabled',true);
		 }
	}
})
.controller('addorupdateyuestzsqCtrl', function($scope, $rootScope,$routeParams, $http,$location) {
	$scope.addorupdateyustzsqTitle = $routeParams.title;
	$http.post('yuedyussq/getzaf').success(function(data){ $scope.zafList=data;});
	$scope.ystzbean = new Object();
	if($routeParams.id != 0){
		$http.post('yutzsq/getYustzById',{id:$routeParams.id}).success(function(data){
			$scope.ystzbean.ZAFID = data.CHANGNFYXM_ID;
			$scope.ystzbean.YUSED =data.YUSED;
			$scope.ystzbean.SHUOM =data.SHUOM;
			$scope.ystzbean.DIANCID =data.DIANCXXB_ID;
			$scope.ystzbean.ID =data.ID;
		});
	}else{
		$scope.ystzbean.ZAFID = $routeParams.zafid;
		$scope.ystzbean.DIANCID = $routeParams.diancid;
		$scope.ystzbean.ID = $routeParams.id;
	}
	$scope.cancel = function(){
		$location.path('/yustzsq/'+$routeParams.diancid+'/'+$routeParams.zafid); 
	}
	$scope.saveYustz = function(){
		if($scope.ystzbean.ID==0){
			$http.post('yutzsq/ystzadd',{info:angular.toJson($scope.ystzbean)}).success(function(data){
				alert(data);
				$location.path('/yustzsq/'+$routeParams.diancid+'/'+$routeParams.zafid); 
			});
		}else{
			$http.post('yutzsq/Yustzupdate',{info:angular.toJson($scope.ystzbean)}).success(function(data){
				alert(data);
				$location.path('/yustzsq/'+$routeParams.diancid+'/'+$routeParams.zafid); 
			});
		}
		
	}
	
});
