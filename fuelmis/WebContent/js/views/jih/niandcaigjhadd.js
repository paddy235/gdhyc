gddlapp.controller('niandcaigjhaddCtrl', function($scope, $rootScope,$routeParams, $http,$location) {
	$scope.niandcaigjhaddTitle = '年度采购计划';
	$scope.search = new Object();
	$scope.search.diancid = 515;
	$("#update").removeClass("btn-info");
	$("#update").attr('disabled', true);
	$("#delete").removeClass("btn-danger");
	$("#delete").attr('disabled', true);
	var d = new Date();
	var vYear = d.getFullYear();
	$scope.search.nianf = vYear;
	if($routeParams.nianf!=null){
		$scope.search.nianf = $routeParams.nianf;
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
		"sScrollX": "100%",
		"sScrollXInner": "2700px",
		"bScrollCollapse": true,
		"aoColumnDefs": [{
            "sClass": "center",
            "mRender": function(oObj, sVal) {
            	 return '<input type="checkbox" id="' + oObj + '" name="checkId" class="checkbox" onclick="check(this)" />';
            },
            "bSortable": false,
            "aTargets": [0]
        },
        {
        	"aTargets": [2,3],
        	"sClass":"center",
        },
        {
        	"aTargets": [1,4,5,6,7,8,9,10,11,12,13,14,15,16],
        	"sClass":"right",
        }
		
		
		
		]
	});
	//跳转进入页面刷新表格
	oTable.fnReloadAjax('niandcaigjh/getCaigjhList?nianf='+$scope.search.nianf+'&diancid='+$scope.search.diancid);
	//获取审批状态
	$http.post('niandcaigjh/getshenpstate',{nianf:$scope.search.nianf,diancid:$scope.search.diancid}).success(function(data){
		$scope.search.state = data;
		if(data!='0'){
			$("#adddata").removeClass("btn-primary");
			$("#adddata").attr('disabled',true);
			$("#copyniandcaigjh").removeClass("btn-primary");
			$("#copyniandcaigjh").attr('disabled',true);
		}
	});
	//刷新
	$scope.refresh = function(){
		//隐藏删除和修改按钮，并将按钮置灰。
		$("#delete").removeClass("btn-primary");
		$("#delete").attr('disabled',true);
		$("#update").removeClass("btn-info");
		$("#update").attr('disabled',true);
		$http.post('niandcaigjh/getshenpstate',{nianf:$scope.search.nianf,diancid:$scope.search.diancid}).success(function(data){
			$scope.search.state = data;
			if(data!='0'){
				$("#adddata").removeClass("btn-primary");
				$("#adddata").attr('disabled',true);
				$("#copyniandcaigjh").removeClass("btn-primary");
				$("#copyniandcaigjh").attr('disabled',true);
			}
		});
		//刷新表格
	    oTable.fnReloadAjax('niandcaigjh/getCaigjhList?nianf='+$scope.search.nianf+'&diancid='+$scope.search.diancid);
	}

	//添加
	$scope.addniandcaigjh = function(){
		$scope.addtitle ="添加年度采购计划";
		$location.path('/niandcaigAdd/'+$scope.search.nianf+'/'+$scope.search.diancid+'/0/'+$scope.addtitle); 
	}
	//更新
	$scope.updateniandcgjh = function(){
		$scope.updatetitle ="修改年度采购计划";
		$scope.search.id = $('input[name="checkId"]:checked').attr("id");
		$location.path('/niandcgjhUpdate/'+$scope.search.id+'/'+$scope.updatetitle+'/'+$scope.search.nianf); 
	}
	//删除，
	 $scope.delniandcaig = function(){
		 var id = "";
		 id = $('input[name="checkId"]:checked').attr("id");
		if(id==""){
			 alert("请选择一项进行删除！！！");
		 }else{
			 var a =confirm("你确定要删除此条记录?");
			 if(a){
				 $.post("niandcaigjh/Niandcaigjhdel",//异步处理动态页面
	  				     {id:id},//获取类名为"name"文本的值，以NAME异步传值
	  				     function(data){//data为反回值，function进行反回值处理
	  				    	 //删除成功将删除和修改按钮禁用并置灰
	  				    	$("#delete").removeClass("btn-danger");
	  						$("#delete").attr('disabled',true);
	  						$("#update").removeClass("btn-info");
	  						$("#update").attr('disabled',true);
				   	        oTable.fnReloadAjax('niandcaigjh/getCaigjhList?nianf='+$scope.search.nianf+'&diancid='+$scope.search.diancid);
	  				        alert(data);//获得得反回值后，将其填入到类名为"content"的文本框中
	  			});
			 }else{
				 return false;
			 }
		 }
	  }
	 //复制上年计划
	$scope.copyniandcgjh = function(){
		$http.post('niandcaigjh/getCaigjhList',{diancid:$scope.search.diancid,nianf:$scope.search.nianf,caoz:'check'}).success(function(data){
			if(data == "1"){//判断本月是否有数据，1表示有
				var a =confirm($scope.search.nianf+"年度采购计划已经存在，你确定要替换吗？？？");
				if(a){
					$http.post('niandcaigjh/niandcaigjhcopy',{diancid:$scope.search.diancid,nianf:$scope.search.nianf,caoz:"replace"}).success(function(data){
						oTable.fnReloadAjax('niandcaigjh/getCaigjhList?nianf='+$scope.search.nianf+'&diancid='+$scope.search.diancid);
						alert(data);
					});
				}
			}else{
				$http.post('niandcaigjh/niandcaigjhcopy',{diancid:$scope.search.diancid,nianf:$scope.search.nianf}).success(function(data){
				oTable.fnReloadAjax('niandcaigjh/getCaigjhList?nianf='+$scope.search.nianf+'&diancid='+$scope.search.diancid);
				alert(data);
			});
			}
		}); 
	}
	//日期选择框
	$scope.selectnianf= function(){
		 var riq= $scope.search.nianf;
		 var dianc =  $scope.search.diancid;
		 if(riq!=""&&dianc!=-1){
			 $http.post('niandcaigjh/getshenpstate',{nianf:$scope.search.nianf,diancid:$scope.search.diancid}).success(function(data){
					$scope.search.state = data;
					if(data=='0'){
						$("#adddata").addClass("btn-primary");
						 $("#adddata").removeAttr("disabled"); //移除disabled属性 
						 $("#copyniandcaigjh").addClass("btn-primary");
						 $("#copyniandcaigjh").removeAttr("disabled"); //移除disabled属性 
					}else{
						$("#adddata").removeClass("btn-primary");
						$("#adddata").attr('disabled',true);
						$("#copyniandcaigjh").removeClass("btn-primary");
						$("#copyniandcaigjh").attr('disabled',true);
					}
				});
		 }else{
			 $("#adddata").removeClass("btn-primary");
			 $("#adddata").attr('disabled',true);
			 $("#copyniandcaigjh").removeClass("btn-primary");
			 $("#copyniandcaigjh").attr('disabled',true);
		 }
		 $scope.refresh();
	 }
	//单位选择框
	 $scope.selectdianc = function(){
		 var riq= $scope.search.nianf;
		 var dianc =  $scope.search.diancid;
		 if(riq!="" && dianc!=-1){
			 $http.post('niandcaigjh/getshenpstate',{nianf:$scope.search.nianf,diancid:$scope.search.diancid}).success(function(data){
					$scope.search.state = data;
					if(data=='0'){
						$("#adddata").addClass("btn-primary");
						 $("#adddata").removeAttr("disabled"); //移除disabled属性 
						 $("#copyniandcaigjh").addClass("btn-primary");
						 $("#copyniandcaigjh").removeAttr("disabled"); //移除disabled属性 
					}else{
						$("#adddata").removeClass("btn-primary");
						$("#adddata").attr('disabled',true);
						$("#copyniandcaigjh").removeClass("btn-primary");
						$("#copyniandcaigjh").attr('disabled',true);
					}
				});
		 }else{
			 $("#adddata").removeClass("btn-primary");
			 $("#adddata").attr('disabled',true);
			 $("#copyniandcaigjh").removeClass("btn-primary");
			 $("#copyniandcaigjh").attr('disabled',true);
		 }
	 }
	 //添加日期控件
	 $("#datepicker").datepicker({
			format : 'yyyy',
			minViewMode : 2,
			language : "zh-CN",
			autoclose : true
		});
})
//添加或修改年度采购计划controller
.controller('AddniandcaigjhCtrl', function($scope, $rootScope,$routeParams, $http,$location) {
	$scope.addNiandCaigTitle = $routeParams.title;
	$http.post('niandcaigjh/getgongys').success(function(data){ $scope.gongsyList=data;});
	$http.post('yuedcaigjh/getJihkj').success(function(data){ $scope.JihkjList=data;});
	$scope.caigbean = new Object();
	if($routeParams.id != 0){//根据id判断是更新页面还是新增页面，id为0是新增；
		$http.post('niandcaigjh/getNiandCaigById',{id:$routeParams.id}).success(function(data){
			$scope.caigbean.ID = data.ID;
			$scope.caigbean.NIANF = data.RIQ;
			$scope.caigbean.DIANCID = data.DIANCXXB_ID;
			$scope.caigbean.GONGYSB_ID = data.GONGYSB_ID;
			$scope.caigbean.HET_REZ = data.HET_REZ;
			$scope.caigbean.HET_SL = data.HET_SL;
			$scope.caigbean.HET_MEIJ = data.HET_MEIJ;
			$scope.caigbean.HET_YUNJ = data.HET_YUNJ;
			$scope.caigbean.JIHKJB_ID = data.JIHKJB_ID;
			$scope.caigbean.JIH_SL = data.JIH_SL;
			$scope.caigbean.JIH_REZ = data.JIH_REZ;
			$scope.caigbean.JIH_MEIJ = data.JIH_MEIJ;
			$scope.caigbean.JIH_YUNJ = data.JIH_YUNJ;
			$scope.caigbean.JIH_QIT = data.JIH_QIT;
			$scope.caigbean.JIH_QITBHS = data.JIH_QITBHS;
			$scope.caigbean.JIH_DAOCJ = data.JIH_DAOCJ;
			$scope.caigbean.JIH_DAOCBMDJ = data.JIH_DAOCBMDJ;
		});
	}else{
		$scope.caigbean.NIANF = $routeParams.nianf;
		$scope.caigbean.DIANCID = $routeParams.diancid;
		$scope.caigbean.ID = $routeParams.id;
		$scope.caigbean.HET_REZ = 0;
		$scope.caigbean.HET_SL = 0;
		$scope.caigbean.HET_MEIJ = 0;
		$scope.caigbean.HET_YUNJ = 0;
		$scope.caigbean.JIH_SL = 0;
		$scope.caigbean.JIH_REZ = 0;
		$scope.caigbean.JIH_MEIJ = 0;
		$scope.caigbean.JIH_YUNJ = 0;
		$scope.caigbean.JIH_QIT = 0;
		$scope.caigbean.JIH_QITBHS = 0;
		$scope.caigbean.JIH_DAOCJ = 0;
		$scope.caigbean.JIH_DAOCBMDJ = 0;
	}
	//返回
	$scope.cancel = function(){
		$location.path('/niandcaigjhadd/'+$routeParams.nianf); 
	}
	//保存，根据ID判断是更新还是保存
	$scope.saveCaig = function(){
		if($scope.caigbean.ID==0){
			$http.post('niandcaigjh/caigjhadd',{info:angular.toJson($scope.caigbean)}).success(function(data){
				alert(data);
				$location.path('/niandcaigjhadd/'+$routeParams.nianf); 
			});
		}else{
			$http.post('niandcaigjh/caigjhupdate',{info:angular.toJson($scope.caigbean)}).success(function(data){
				alert(data);
				$location.path('/niandcaigjhadd/'+$routeParams.nianf); 
			});
		}
	}
	
});
