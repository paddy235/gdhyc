gddlapp.controller('niandyusspCtrl', function($scope, $rootScope,$routeParams, $http,$location) {
	$scope.niandyusspTitle = '年度预算申请';
	$scope.search = new Object();
	var d = new Date();
	$scope.search.nianf  = d.getFullYear(); 
	$scope.search.diancid = 515;
	if($routeParams.nianf != null){
		$scope.search.nianf = $routeParams.nianf;
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
        }]
	});
	$("#datepicker").datepicker({
			format : 'yyyy',
			minViewMode : 2,
			language : "zh-CN",
			autoclose : true
	});
/*	oTable.fnReloadAjax('niandyussq/yuscx?nianf='+$scope.search.nianf+'&diancid='+$scope.search.diancid);
	$http.post('niandyussq/getzhuangt',{nianf:$scope.search.nianf,diancid:$scope.search.diancid}).success(function(data){
		if(data=='1'||data=='2'){
			$("#submit").removeClass("btn-primary");
			$("#submit").attr('disabled',true);
		}
	});*/
	$scope.refresh = function(){
		//查询提交状态
		$http.post('niandyussq/getzhuangt',{nianf:$scope.search.nianf,diancid:$scope.search.diancid}).success(function(data){
			if(data=='1'||data=='2'){
				$("#submit").removeClass("btn-primary").attr('disabled',true);
				$('#adddata').removeClass('btn-primary').attr('disabled', true);
				$('#updateniandys').removeClass('btn-info').attr('disabled', true);
				$('#delniandys').removeClass('btn-danger').attr('disabled', true);
			}else{
				$("#submit").addClass("btn-primary").attr("disabled",false);
				$('#adddata').addClass('btn-primary').attr('disabled', false);
				$('#updateniandys').addClass('btn-info').attr('disabled', false);
				$('#delniandys').addClass('btn-danger').attr('disabled', false);
			}
		});
		var nianf = $scope.search.nianf;
	    oTable.fnReloadAjax('niandyussq/yuscx?nianf='+nianf+'&diancid='+$scope.search.diancid);
	}
	$scope.refresh();
	$scope.addniandcgjh = function(){
		$scope.addtitle ="添加年度预算申请";
		$location.path('/niandyusAdd/'+$scope.search.nianf+'/'+$scope.search.diancid+'/0/'+$scope.addtitle); 
	}
	//年度预算申请提交
	$scope.sumbmitniandyussq = function(){
		$http.post('niandyussq/submitToGuod',{nianf:$scope.search.nianf,diancid:$scope.search.diancid}).success(function(data){
			if(data=='success'){
				alert('提交成功！！！');
				//新增/修改/删除按钮置灰
				$('#adddata').removeClass('btn-primary').attr('disabled', true);
				$('#updateniandys').removeClass('btn-info').attr('disabled', true);
				$('#delniandys').removeClass('btn-danger').attr('disabled', true);
				$("#submit").removeClass('btn-primary').attr('disabled', true);
			}else{
				alert('提交失败！！！');
			}
		});
	}
	$scope.delniandys = function(){
		var id = "";
		 id = $('input[name="checkId"]:checked').attr("id");
		 if(id==""){
			 alert("请选择一项进行删除！！！");
		 }else{
			 var a =confirm("你确定要删除此条记录?");
			 if(a){
				 $.post("niandyussq/delniandys",//异步处理动态页面
	  				     {id:id},//获取类名为"name"文本的值，以NAME异步传值
	  				     function(data){//data为反回值，function进行反回值处理
	  				    	$("#delniandys").removeClass("btn-primary");
	  						$("#delniandys").attr('disabled',true);
	  						$("#updateniandys").removeClass("btn-primary");
	  						$("#updateniandys").attr('disabled',true);
				   	    	oTable.fnReloadAjax('niandyussq/yuscx?nianf='+$scope.search.nianf+'&diancid='+$scope.search.diancid);
	  				        alert(data);//获得得反回值后，将其填入到类名为"content"的文本框中
	  				      });
			 }else{
				 return false;
			 }
		 }
	}
	$scope.updateyuedcgjh = function(){
		$scope.updatetitle ="修改年度预算申请";
		$scope.search.id = $('input[name="checkId"]:checked').attr("id");
		$location.path('/niandyusUpdate/'+$scope.search.id+'/'+$scope.updatetitle+'/'+$scope.search.nianf+'/'+$scope.search.diancid); 
	}
	$scope.selectnianf = function(){
		 var nianf= $scope.search.nianf;
		 var dianc =  $scope.search.diancid;
		 if(nianf!=""&&dianc!=-1){
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
		var nianf= $scope.search.nianf;
		 var dianc =  $scope.search.diancid;
		 if(nianf!="" && dianc!=-1){
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
.controller('addorupdateniandyssqCtrl', function($scope, $rootScope,$routeParams, $http,$location) {
	$scope.addorupdateniandyussqTitle = $routeParams.title;
	$http.post('yuedyussq/getzaf').success(function(data){ $scope.zafList=data;});
	$scope.niandysbean = new Object();
	if($routeParams.id != 0){
		$http.post('niandyussq/getniandyusById',{id:$routeParams.id}).success(function(data){
			$scope.niandysbean.FEIYMC = data.CHANGNFYXM_ID;
			$scope.niandysbean.YUSED =data.YUSED;
			$scope.niandysbean.SHUOM =data.SHUOM;
			$scope.niandysbean.NIANF =data.NIANF;
			$scope.niandysbean.DIANCID =data.DIANCXXB_ID;
			$scope.niandysbean.ID =data.ID;
		});
	}else{
		$scope.niandysbean.NIANF = $routeParams.nianf;
		$scope.niandysbean.DIANCID = $routeParams.diancid;
		$scope.niandysbean.ID = $routeParams.id;
	}
	$scope.cancel = function(){
		$location.path('/niandyussq/'+$routeParams.nianf+'/'+$routeParams.diancid);
	}
	$scope.saveNiandys = function(){
		if($scope.niandysbean.ID==0){
			$http.post('niandyussq/niandysadd',{info:angular.toJson($scope.niandysbean)}).success(function(data){
				alert(data);
				$location.path('/niandyussq/'+$routeParams.nianf+'/'+$routeParams.diancid);
			});
		}else{
			$http.post('niandyussq/niandysupdate',{info:angular.toJson($scope.niandysbean)}).success(function(data){
				alert(data);
				$location.path('/niandyussq/'+$routeParams.nianf+'/'+$routeParams.diancid);
			});
		}
		
	}
});
