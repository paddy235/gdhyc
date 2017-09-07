gddlapp.controller('yuedyusspCtrl', function($scope, $rootScope,$routeParams, $http,$location) {
	$scope.yuedyusspTitle = '月度预算申请';
	$scope.search = new Object();
	var d = new Date();
	var vYear = d.getFullYear();
	var vMon = d.getMonth() + 1;
	$scope.search.riq = vYear+"-"+(vMon<10 ? "0" + vMon : vMon);
	$scope.search.diancid = 515;
	if($routeParams.riq != null){
		$scope.search.riq = $routeParams.riq;
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
//                return '<input type="checkbox" id="' + oObj + '" name="checkId" ng-click="check($this)" />';
            	 return '<input type="checkbox" id="' + oObj + '" name="checkId" onclick="check(this)" />';
            },
            "bSortable": false,
            "aTargets": [0]
        },
        {  
       	 "sClass": "center",
           "targets": [2]      

       },
       {  
       	"targets": [1,3] ,
      	 "sClass": "right"      

      }
		
		
		]
	});
	$("#datepicker").datepicker({
			format : 'yyyy-mm',
			minViewMode : 1,
			language : "zh-CN", 
			autoclose : true
	});

	$scope.refresh = function(){
		$http.post('yuedyussq/getzhuangt',{riq:$scope.search.riq,diancid:$scope.search.diancid}).success(function(data){
			if(data=='1'||data=='2'){
				$("#submit").removeClass("btn-primary").attr('disabled',true);
				$('#adddata').removeClass('btn-primary').attr('disabled', true);
				$('#updateyuedys').removeClass('btn-info').attr('disabled', true);
				$('#delyuedys').removeClass('btn-danger').attr('disabled', true);
			}else{
				$("#submit").addClass("btn-primary").attr("disabled",false);
				$('#adddata').addClass('btn-primary').attr('disabled', false);
				$('#updateyuedys').addClass('btn-info').attr('disabled', false);
				$('#delyuedys').addClass('btn-danger').attr('disabled', false);
			}
		});
		var riq = $scope.search.riq;
	    oTable.fnReloadAjax('yuedyussq/yuscx?riq='+riq+'&diancid='+$scope.search.diancid);
	}
	$scope.refresh();
	$scope.addyuedcgjh = function(){
		$scope.addtitle ="添加月度预算申请";
		$location.path('/yuedyusAdd/'+$scope.search.riq+'/'+$scope.search.diancid+'/0/'+$scope.addtitle); 
	}
	$scope.sumbmit = function(){
		$http.post('yuedyussq/submitToGuod',{riq:$scope.search.riq,diancid:$scope.search.diancid}).success(function(data){
			if(data=='success'){
				alert('提交成功！！！');
				//新增/修改/删除按钮置灰
				$('#adddata').removeClass('btn-primary').attr('disabled', true);
				$('#updateyuedys').removeClass('btn-info').attr('disabled', true);
				$('#delyuedys').removeClass('btn-danger').attr('disabled', true);
				$("#submit").removeClass('btn-primary').attr('disabled', true);
			}else{
				alert('提交失败！！！');
			}
		});
	}
	$scope.delyuedys = function(){
		var id = "";
		 id = $('input[name="checkId"]:checked').attr("id");
		 if(id==""){
			 alert("请选择一项进行删除！！！");
		 }else{
			 var a =confirm("你确定要删除此条记录?");
			 if(a){
				 $.post("yuedyussq/delyuedys",//异步处理动态页面
	  				     {id:id},//获取类名为"name"文本的值，以NAME异步传值
	  				     function(data){//data为反回值，function进行反回值处理
	  				    	$("#delyuedys").removeClass("btn-primary");
	  						$("#delyuedys").attr('disabled',true);
	  						$("#updateyuedys").removeClass("btn-primary");
	  						$("#updateyuedys").attr('disabled',true);
	  						var riq = $scope.search.riq;
				   	        var diancid= $scope.search.diancid;
				   	    	oTable.fnReloadAjax('yuedyussq/yuscx?riq='+riq+'&diancid='+diancid);
	  				        alert(data);//获得得反回值后，将其填入到类名为"content"的文本框中
	  				      });
			 }else{
				 return false;
			 }
		 }
	}
	$scope.updateyuedcgjh = function(){
		$scope.updatetitle ="修改月度预算申请";
		$scope.search.id = $('input[name="checkId"]:checked').attr("id");
		$location.path('/yuedyusUpdate/'+$scope.search.id+'/'+$scope.updatetitle+'/'+$scope.search.riq+'/'+$scope.search.diancid); 
	}
	$scope.selectriq = function(){
		 var riq= $scope.search.riq;
		 var dianc =  $scope.search.diancid;
		 if(riq!=""&&dianc!=-1){
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
		var riq= $scope.search.riq;
		 var dianc =  $scope.search.diancid;
		 if(riq!="" && dianc!=-1){
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
.controller('addorupdateyuedyssqCtrl', function($scope, $rootScope,$routeParams, $http,$location) {
	$scope.addorupdateyudyussqTitle = $routeParams.title;
	$http.post('yuedyussq/getzaf').success(function(data){ $scope.zafList=data;});
	$scope.yudysbean = new Object();
	if($routeParams.id != 0){
		$http.post('yuedyussq/getyuedyusById',{id:$routeParams.id}).success(function(data){
			$scope.yudysbean.FEIYMC = data.CHANGNFYXM_ID;
			$scope.yudysbean.YUSED =data.YUSED;
			$scope.yudysbean.SHUOM =data.SHUOM;
			$scope.yudysbean.RIQ =data.RIQ;
			$scope.yudysbean.DIANCID =data.DIANCXXB_ID;
			$scope.yudysbean.ID =data.ID;
		});
	}else{
		$scope.yudysbean.RIQ = $routeParams.riq;
		$scope.yudysbean.DIANCID = $routeParams.diancid;
		$scope.yudysbean.ID = $routeParams.id;
	}
	$scope.cancel = function(){
		$location.path('/yuedyussq/'+$routeParams.riq+'/'+$routeParams.diancid);
	}
	$scope.saveYuedys = function(){
		if($scope.yudysbean.ID==0){
			$http.post('yuedyussq/yudysadd',{info:angular.toJson($scope.yudysbean)}).success(function(data){
				alert(data);
				$location.path('/yuedyussq/'+$routeParams.riq+'/'+$routeParams.diancid);
			});
		}else{
			$http.post('yuedyussq/Yuedysupdate',{info:angular.toJson($scope.yudysbean)}).success(function(data){
				alert(data);
				$location.path('/yuedyussq/'+$routeParams.riq+'/'+$routeParams.diancid);
			});
		}
		
	}
	
});
