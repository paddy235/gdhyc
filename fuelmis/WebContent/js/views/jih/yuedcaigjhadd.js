gddlapp.controller('yuedcaigjhwhCtrl', function($scope, $rootScope,$routeParams, $http,$location) {
	$scope.yuedcaigTitle = '月度采购计划录入';
	$scope.search = new Object();
	$("#update").removeClass("btn-info");
	$("#delete").removeClass("btn-danger");
	$("#delete").attr('disabled',true);
	$("#update").attr('disabled',true);
	var d = new Date();
	var vYear = d.getFullYear();
	var vMon = d.getMonth() + 1;
	$scope.search.riq = vYear+"-"+(vMon<10 ? "0" + vMon : vMon);
	$scope.search.diancid = 515;
	if($routeParams.riq != null){
		$scope.search.riq = $routeParams.riq;
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
                return '<input type="checkbox" id="' + oObj + '" name="checkId" onclick="check(this)" />';
            },
            "bSortable": false,
            "aTargets": [0]
        },
        {
        	"aTargets": [2,3,4,5,6],
        	"sClass":"center"
        },
        {
        	"aTargets": [1,7,8,9,10,11,12,13,14,15,16,17,18,19,20],
        	"sClass":"right"
        }
		]
	});
	oTable.fnReloadAjax('yuedcaigjh/getCaigList?riq='+ $scope.search.riq+'-01&diancid='+$scope.search.diancid);
	//获取审批状态
	$http.post('yuedcaigjh/getshenpstate',{riq:$scope.search.riq,diancid:$scope.search.diancid}).success(function(data){
		$scope.search.state = data;
		if(data!='0'){
			$("#adddata").removeClass("btn-primary");
			 $("#adddata").attr('disabled',true);
			 $("#copyyuedcgjh").removeClass("btn-primary");
			 $("#copyyuedcgjh").attr('disabled',true);
		}
	});
	$scope.refresh = function(){
		$("#delete").removeClass("btn-danger");
		$("#delete").attr('disabled',true);
		$("#update").removeClass("btn-info");
		$("#update").attr('disabled',true);
		$http.post('yuedcaigjh/getshenpstate',{riq:$scope.search.riq,diancid:$scope.search.diancid}).success(function(data){
			$scope.search.state = data;
			if(data!='0'){
				$("#adddata").removeClass("btn-primary");
				 $("#adddata").attr('disabled',true);
				 $("#copyyuedcgjh").removeClass("btn-primary");
				 $("#copyyuedcgjh").attr('disabled',true);
			}
		});
    	riq = $scope.search.riq+"-01";
        diancid= $scope.search.diancid;
        oTable.fnReloadAjax('yuedcaigjh/getCaigList?riq='+riq+'&diancid='+diancid);
	}
	$scope.addyuedcgjh = function(){
		$scope.addtitle ="添加月度采购计划";
		$location.path('/yuedcgjhAdd/'+$scope.search.riq+'/'+$scope.search.diancid+'/0/'+$scope.addtitle); 
	}
	$scope.copyyuedcgjh = function(){
		$http.post('yuedcaigjh/getCaigList',{diancid:$scope.search.diancid,riq:$scope.search.riq+'-01',caoz:'check'}).success(function(data){
			if(data == "1"){
				var a =confirm($scope.search.riq+"月计划已经存在，你确定要替换吗？？？");
				if(a){
					$http.post('yuedcaigjh/yuedcaigjhcopy',{diancid:$scope.search.diancid,riq:$scope.search.riq+'-01',caoz:"replace"}).success(function(data){
						oTable.fnReloadAjax('yuedcaigjh/getCaigList?riq='+ $scope.search.riq+'-01&diancid='+$scope.search.diancid);
						alert(data);
					});
				}
			}else{
				$http.post('yuedcaigjh/yuedcaigjhcopy',{diancid:$scope.search.diancid,riq:$scope.search.riq+'-01'}).success(function(data){
					oTable.fnReloadAjax('yuedcaigjh/getCaigList?riq='+ $scope.search.riq+'-01&diancid='+$scope.search.diancid);
					alert(data);
				});
			}
		}); 
	}
	$scope.delyuedcaig = function(){
		 var id = "";
		 id = $('input[name="checkId"]:checked').attr("id");
		 if(id==""){
			 alert("请选择一项进行删除！！！");
		 }else{
			 var a =confirm("你确定要删除此条记录?");
			 if(a){
				 $.post("yuedcaigjh/Caigdel",//异步处理动态页面
	  				     {id:id},//获取类名为"name"文本的值，以NAME异步传值
	  				     function(data){//data为反回值，function进行反回值处理
	  				    	$("#delete").removeClass("btn-danger");
	  						$("#delete").attr('disabled',true);
	  						$("#update").removeClass("btn-info");
	  						$("#update").attr('disabled',true);
	  						riq = $scope.search.riq+"-01";
	  				        diancid= $scope.search.diancid;
	  				        oTable.fnReloadAjax('yuedcaigjh/getCaigList?riq='+ riq+'&diancid='+diancid);
	  				        alert(data);//获得得反回值后，将其填入到类名为"content"的文本框中
	  				      });
			 }else{
				 return false;
			 }
		 }
	}
	$scope.updateyuedcgjh = function(){
		$scope.updatetitle ="修改月度采购计划";
		$scope.search.id = $('input[name="checkId"]:checked').attr("id");
		$location.path('/yuedcgjhUpdate/'+$scope.search.id+'/'+$scope.updatetitle+'/'+$scope.search.riq); 
	}
	$scope.selectriq = function(){
		 riq = $scope.search.riq;
	     diancid= $scope.search.diancid;
		 if(riq!=""&&diancid!=-1){
			 $http.post('yuedcaigjh/getshenpstate',{riq:$scope.search.riq,diancid:$scope.search.diancid}).success(function(data){
					$scope.search.state = data;
					if(data=='0'){
						$("#adddata").addClass("btn-primary");
						 $("#adddata").removeAttr("disabled");//移除disabled属性 
						 $("#copyyuedcgjh").addClass("btn-primary");
						 $("#copyyuedcgjh").removeAttr("disabled");//移除disabled属性 
					}else{
						$("#adddata").removeClass("btn-primary");
						 $("#adddata").attr('disabled',true);
						 $("#copyyuedcgjh").removeClass("btn-primary");
						 $("#copyyuedcgjh").attr('disabled',true);
					}
			 });
		 }else{
			 $("#adddata").removeClass("btn-primary");
			 $("#adddata").attr('disabled',true);
			 $("#copyyuedcgjh").removeClass("btn-primary");
			 $("#copyyuedcgjh").attr('disabled',true);
		 }
		 $scope.refresh();
	 }
	$scope.selectdianc = function(){
		 riq = $scope.search.riq;
	     diancid= $scope.search.diancid;
		 if(riq!="" && diancid!=-1){
			 $http.post('yuedcaigjh/getshenpstate',{riq:$scope.search.riq,diancid:$scope.search.diancid}).success(function(data){
					$scope.search.state = data;
					if(data=='0'){
						$("#adddata").addClass("btn-primary");
						 $("#adddata").removeAttr("disabled");//移除disabled属性 
						 $("#copyyuedcgjh").addClass("btn-primary");
						 $("#copyyuedcgjh").removeAttr("disabled");//移除disabled属性 
					}else{
						$("#adddata").removeClass("btn-primary");
						 $("#adddata").attr('disabled',true);
						 $("#copyyuedcgjh").removeClass("btn-primary");
						 $("#copyyuedcgjh").attr('disabled',true);
					}
			}); 
		 }else{
			 $("#adddata").removeClass("btn-primary");
			 $("#adddata").attr('disabled',true);
			 $("#copyyuedcgjh").removeClass("btn-primary");
			 $("#copyyuedcgjh").attr('disabled',true);
		 }
	 }
	 $("#datepicker").datepicker({
			format : 'yyyy-mm',
			minViewMode : 1,
			language : "zh-CN",
			autoclose : true
		});
	
})
.controller('AddyuedcgjhCtrl', function($scope, $rootScope,$routeParams, $http,$location) {
	$scope.yuddcaigjhTitle = $routeParams.title;
	$http.post('yuedcaigjh/getPinz').success(function(data){ $scope.pinzList=data;});
	$http.post('yuedcaigjh/getgongys').success(function(data){ $scope.gongsyList=data;});
	$http.post('yuedcaigjh/getJihkj').success(function(data){ $scope.JihkjList=data;});
	$http.post('yuedcaigjh/getFaz',{riq:$routeParams.riq}).success(function(data){ $scope.fazList=data;});
	$scope.caigbean = new Object();
	if($routeParams.id != 0){
		$http.post('yuedcaigjh/getyuedcaigById',{id:$routeParams.id}).success(function(data){
			$scope.caigbean.GONGYSB_ID = data.GONGYSB_ID;
			$scope.caigbean.MEIKXXB_ID =data.MEIKXXB_ID;
			$scope.caigbean.JIHKJB_ID =data.JIHKJB_ID;
			$scope.caigbean.PINZB_ID =data.PINZB_ID;
			$scope.caigbean.FAZ_ID =data.FAZ_ID;
			$scope.caigbean.JIH_SL = data.JIH_SL;
			$scope.caigbean.JIH_REZ =data.JIH_REZ;
			$scope.caigbean.JIH_LIUF = data.JIH_LIUF;
			$scope.caigbean.JIH_HFF = data.JIH_HFF;
			$scope.caigbean.JIH_MEIJ = data.JIH_MEIJ;
			$scope.caigbean.JIH_MEIJBHS = data.JIH_MEIJBHS;
			$scope.caigbean.JIH_YUNJ = data.JIH_YUNJ;
			$scope.caigbean.JIH_YUNJBHS = data.JIH_YUNJBHS;
			$scope.caigbean.JIH_ZAF = data.JIH_ZAF;
			$scope.caigbean.JIH_ZAFBHS = data.JIH_ZAFBHS;
			$scope.caigbean.JIH_DAOCJ = data.JIH_DAOCJ;
			$scope.caigbean.JIH_DAOCJBHS = data.JIH_DAOCJBHS;
			$scope.caigbean.JIH_DAOCBMDJ = data.JIH_DAOCBMDJ;
			$scope.caigbean.JIH_DAOCBMDJBHS =data.JIH_DAOCBMDJBHS;
			$scope.caigbean.RIQ =data.RIQ;
			$scope.caigbean.DIANCID =data.DIANCXXB_ID;
			$scope.caigbean.ID =data.ID;
		});
	}else{
		$scope.caigbean.RIQ = $routeParams.riq+"-01";
		$scope.caigbean.DIANCID = $routeParams.diancid;
		$scope.caigbean.ID = $routeParams.id;
		$scope.caigbean.JIH_SL = 0;
		$scope.caigbean.JIH_REZ =0;
		$scope.caigbean.JIH_LIUF = 0;
		$scope.caigbean.JIH_HFF =0;
		$scope.caigbean.JIH_MEIJ = 0;
		$scope.caigbean.JIH_MEIJBHS = 0;
		$scope.caigbean.JIH_YUNJ = 0;
		$scope.caigbean.JIH_YUNJBHS = 0;
		$scope.caigbean.JIH_ZAF = 0;
		$scope.caigbean.JIH_ZAFBHS =0;
		$scope.caigbean.JIH_DAOCJ = 0;
		$scope.caigbean.JIH_DAOCJBHS = 0;
		$scope.caigbean.JIH_DAOCBMDJ = 0;
		$scope.caigbean.JIH_DAOCBMDJBHS =0;
	}
	$scope.cancel = function(){
		$location.path('/yuedcaigjhadd/'+$routeParams.riq); 
	}
	$scope.saveCaig = function(){
		if($scope.caigbean.ID==0){
			$http.post('yuedcaigjh/Caigadd',{info:angular.toJson($scope.caigbean)}).success(function(data){
				alert(data);
				$location.path('/yuedcaigjhadd/'+$routeParams.riq);
			});
		}else{
			$http.post('yuedcaigjh/Caigupdate',{info:angular.toJson($scope.caigbean)}).success(function(data){				
				alert(data);
				$location.path('/yuedcaigjhadd/'+$routeParams.riq);
			});
		}
	}
	
});
