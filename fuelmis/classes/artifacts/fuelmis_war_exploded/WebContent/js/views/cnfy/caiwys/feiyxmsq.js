gddlapp.controller('feiyxmsqCtrl', function($scope, $rootScope,$routeParams, $http,$location) {
	$scope.yustzsqTitle = '费用项目申请';
	$http.post('feiyxmsq/getfeiyxmfl').success(function(data){ $scope.feiyxmflList=data;});
	$scope.search = new Object();
	$scope.search.feiyxmfl = -1;
	$scope.search.diancid = 515;
	if($routeParams.feiyxmfl != null){
		$scope.search.feiyxmfl = Number($routeParams.feiyxmfl);
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
              "targets": [2,3,4,5,6,7,8,10]      

          },
          {  
          	"targets": [1] ,
         	 "sClass": "right"      

         }
		]
	});
	oTable.fnReloadAjax('feiyxmsq/feiyxmcx?feiyxmfl='+$scope.search.feiyxmfl+'&diancid='+$scope.search.diancid);
	$scope.refresh = function(){
	    oTable.fnReloadAjax('feiyxmsq/feiyxmcx?feiyxmfl='+$scope.search.feiyxmfl+'&diancid='+$scope.search.diancid);
	}
	$scope.addfeiyxmsq = function(){
		console.log("添加费用项目申请");
		$scope.addtitle ="添加费用项目申请";
		$location.path('/feiyxmsqAdd/'+$scope.search.feiyxmfl+'/'+$scope.search.diancid+'/0/'+$scope.addtitle); 
	}
	$scope.submit = function(){
		var id = "";
		 id = $('input[name="checkId"]:checked').attr("id");
		 if(id==""){
			 alert("请选择一项提交！！！");
		 }else{
			 $http.post('feiyxmsq/submitToGuod',{id:id}).success(function(data){
					alert(data);
				});
		 }
	}
	$scope.delfeiyxm = function(){
		var id = "";
		 id = $('input[name="checkId"]:checked').attr("id");
		 if(id==""){
			 alert("请选择一项进行删除！！！");
		 }else{
			 var a =confirm("你确定要删除此条记录?");
			 if(a){
				 $.post("feiyxmsq/delFeiyxm",//异步处理动态页面
	  				     {id:id},//获取类名为"name"文本的值，以NAME异步传值
	  				     function(data){//data为反回值，function进行反回值处理
	  				    	$("#delfeiyxm").removeClass("btn-primary");
	  						$("#delfeiyxm").attr('disabled',true);
	  						$("#updatefeiyxm").removeClass("btn-primary");
	  						$("#updatefeiyxm").attr('disabled',true);
	  						var feiyxmfl = $scope.search.feiyxmfl;
				   	        var diancid= $scope.search.diancid;
				   	    	oTable.fnReloadAjax('feiyxmsq/feiyxmcx?feiyxmfl='+feiyxmfl+'&diancid='+diancid);
	  				        alert(data);//获得得反回值后，将其填入到类名为"content"的文本框中
	  				      });
			 }else{
				 return false;
			 }
		 }
	}
	$scope.updatefeiyxmsq = function(){
		$scope.search.id = $('input[name="checkId"]:checked').attr("id");
		$location.path('/feiyxmsqUpdate/'+$scope.search.id+'/'+$scope.updatetitle+'/'+$scope.search.feiyxmfl+'/'+$scope.search.diancid); 
	}
	$scope.selectfeiyxmfl = function(){
		 var feiyxmfl= $scope.search.feiyxmfl;
		 var dianc =  $scope.search.diancid;
		 if(feiyxmfl!=-1&&dianc!=-1){
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
		var feiyxmfl= $scope.search.feiyxmfl;
		 var dianc =  $scope.search.diancid;
		 if(feiyxmfl!=-1 && dianc!=-1){
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
.controller('addorupdatefeiyxmsqCtrl', function($scope, $rootScope,$routeParams, $http,$location) {
	$scope.addorupdatefeiyxmsqTitle = $routeParams.title;
	$http.post('feiyxmsq/getFeiyxmsx').success(function(data){ $scope.feiyxmsxList=data;});
	$scope.feiyxmbean = new Object();
	if($routeParams.id != 0){
		$http.post('feiyxmsq/getFeiyxmById',{id:$routeParams.id}).success(function(data){
			$scope.feiyxmbean.ID =data.ID;
			$scope.feiyxmbean.BIANM =data.BIANM;
			$scope.feiyxmbean.FEIYXMSX =data.FEIYXMSX_ID;
			$scope.feiyxmbean.GUANLFL = data.GUANLFL;
			$scope.feiyxmbean.MINGC =data.MINGC;
			$scope.feiyxmbean.SHUOM =data.SHUOM;
			$scope.feiyxmbean.CAIWFL =data.CAIWFL;
			$scope.feiyxmbean.FEIYXMFLID =data.FEIYXMFL_ID;
			$scope.feiyxmbean.DIANCID =data.DIANCXXB_ID;
		});
	}else{
		$scope.feiyxmbean.FEIYXMFLID = $routeParams.feiyxmfl;
		$scope.feiyxmbean.DIANCID = $routeParams.diancid;
		$scope.feiyxmbean.ID = $routeParams.id;
	}
	$scope.cancel = function(){
		$location.path('/feiyxmsq/'+$routeParams.feiyxmfl+'/'+$routeParams.diancid);
	}
	console.log('faxisa:'+$routeParams.diancid);
	console.log('feiyxmfl:'+$routeParams.feiyxmfl);
	$scope.saveYustz = function(){
		if($scope.feiyxmbean.ID==0){
			$http.post('feiyxmsq/feiyxmadd',{info:angular.toJson($scope.feiyxmbean)}).success(function(data){
				alert(data);
				$location.path('/feiyxmsq/'+$routeParams.feiyxmfl+'/'+$routeParams.diancid);
			});
		}else{
			$http.post('feiyxmsq/feiyxmupdate',{info:angular.toJson($scope.feiyxmbean)}).success(function(data){
				alert(data);
				$location.path('/feiyxmsq/'+$routeParams.feiyxmfl+'/'+$routeParams.diancid);
			});
		}
		
	}
	
});
