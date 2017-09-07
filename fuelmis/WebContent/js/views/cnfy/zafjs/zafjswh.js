gddlapp.controller('zafjswhCtrl', function($scope, $rootScope,$routeParams, $http,$location) {
	$scope.zafjswhTitle = '杂费结算维护';
	$scope.search = new Object();
	var d = new Date();
	var vYear = d.getFullYear();
	var vMon = d.getMonth() + 1;
	var vDate = d.getDate();
	$scope.search.nianf = vYear+"-"+(vMon<10 ? "0" + vMon : vMon)+"-"+(vDate<10 ? "0" + vDate : vDate);
	if($routeParams.nianf!=null){
		$scope.search.nianf = $routeParams.nianf;
	}
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
            "targets": [2,3,4,6]      
         },
         {  
            	"targets": [1,5] ,
           	 "sClass": "right"      
           }
		]
	});
	//跳转进入页面刷新表格
	oTable.fnReloadAjax('zafjswh/getZafjsdata?nianf='+$scope.search.nianf);
	//刷新
	$scope.refresh = function(){
		//隐藏删除和修改按钮，并将按钮置灰。
		$("#delzafjswh").removeClass("btn-primary");
		$("#delzafjswh").attr('disabled',true);
		$("#updateniandcaig").removeClass("btn-primary");
		$("#updateniandcaig").attr('disabled',true);
		//刷新表格
		oTable.fnReloadAjax('zafjswh/getZafjsdata?nianf='+$scope.search.nianf);
	}
	$scope.refresh();
	//添加
	$scope.addzafjsbxd = function(){
		$scope.addtitle ="新增杂费结算";
		$location.path('/zafjsbxdAdd/0/'+$scope.addtitle+'/'+$scope.search.nianf); 
	}
	//修改
	$scope.updatezfjs = function(){
		$scope.updatetitle ="修改杂费结算";
		$scope.search.id = $('input[name="checkId"]:checked').attr("id");
		$location.path('/zafjsbxdUpdate/'+$scope.search.id+'/'+$scope.updatetitle+'/'+$scope.search.nianf); 
	}
	//删除，
	 $scope.delzafjswh = function(){
		 var id = "";
		 id = $('input[name="checkId"]:checked').attr("id");
		if(id==""){
			 alert("请选择一项进行删除！！！");
		 }else{
			 var a =confirm("你确定要删除此条记录?");
			 if(a){
				 $.post("zafjswh/delZafjswh",//异步处理动态页面
	  				     {id:id},//获取类名为"name"文本的值，以NAME异步传值
	  				     function(data){//data为反回值，function进行反回值处理
	  				    	 //删除成功将删除和修改按钮禁用并置灰
	  				    	$("#delzafjswh").removeClass("btn-primary");
	  						$("#delzafjswh").attr('disabled',true);
	  						$("#updateniandcaig").removeClass("btn-primary");
	  						$("#updateniandcaig").attr('disabled',true);
	  						oTable.fnReloadAjax('zafjswh/getZafjsdata?nianf='+$scope.search.nianf);
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
			 $("#adddata").addClass("btn-primary");
			 $("#adddata").removeAttr("disabled");//移除disabled属性 
			 $("#copyniandcaigjh").addClass("btn-primary");
			 $("#copyniandcaigjh").removeAttr("disabled");//移除disabled属性 

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
	 }
	 //添加日期控件
	 $("#datepicker").datepicker({
			format : 'yyyy-mm-dd',
			minViewMode : 0,
			language : "zh-CN",
			autoclose : true
		});

}).controller('zafjsCtrl', function($scope, $rootScope,$routeParams, $http,$location) {
	$scope.zafjsTitle = $routeParams.title;
	$scope.search = new Object();
	$scope.zafjsbxdbean = new Object();
	$scope.search.id = $routeParams.id;
	//通过判断传过来的id参数是否等于0来给zaffybxdid赋值
	if($scope.search.id==0){
		$http.post('zafjswh/getzaffybxdbidandrenyxx').success(function(data){
			$scope.search.id=data.zaffybxdid;
			$scope.search.xingm = data.xingm;
			$scope.search.department = data.department;
		});
	}else{
		$http.post('zafjswh/getzaffybxdbidandrenyxx').success(function(data){
			$scope.search.xingm = data.xingm;
			$scope.search.department = data.department;
		});
		$http.post('zafjswh/getbxddata',{zaffybxd_id:$scope.search.id}).success(function(data){
			$scope.arr = data;
		});
	}
	$(".datepicker").datepicker({
		format : 'yyyy-mm-dd',
		minViewMode : 0,
		language : "zh-CN",
		autoclose : true
	});
	$scope.feiybxdtitle ="费用报销单明细";
	$http.post('zafjswh/gethetBianh').success(function(data){
		$scope.hetbianhlist=data;
	});
	$scope.selecthetbianh = function(){
		$http.post('zafjswh/gethetzaf',{hetid:$scope.zafjsbxdbean.HETBIANH}).success(function(data){
			$scope.hetzaflist=data;
		});
	}
	$scope.selectfeiyxm = function(){
		$http.post('zafjswh/getdanj',{hetid:$scope.zafjsbxdbean.HETBIANH,feiyxmid:$scope.zafjsbxdbean.FEIYXM}).success(function(data){
			$scope.zafjsbxdbean.DANJ=data;
		});
	}
	$scope.danjchange = function(){
		$scope.zafjsbxdbean.JINE = $scope.zafjsbxdbean.SHUL*$scope.zafjsbxdbean.DANJ;
	}
	$scope.shulchange = function(){
		$scope.zafjsbxdbean.JINE = $scope.zafjsbxdbean.SHUL*$scope.zafjsbxdbean.DANJ;
	}
	$scope.reback = function(){
		$location.path('/zafjswh/'+$routeParams.nianf); 
	}
	
	$scope.addzaf = function(zafhtfydjb_id){
		$scope.zafjsbxdbean.ZAFFYBXDID = $scope.search.id;
		$scope.zafjsbxdbean.ZAFFYDJBID = 0;
		$("#myModal").modal('show');
	}
	$scope.updatezaf = function(zafhtfydjb_id){
		$scope.zafjsbxdbean.ZAFFYBXDID = $scope.search.id;
		$http.post('zafjswh/getzafhtfydjbById',{id:zafhtfydjb_id}).success(function(data){
			$scope.zafjsbxdbean.HETBIANH = data.ZAFHTB_ID;
			$scope.zafjsbxdbean.FEIYXM = data.CHANGNFYXMB_ID;
			$scope.zafjsbxdbean.KAISSJ = data.QISRQ;
			$scope.zafjsbxdbean.JIESSJ = data.JIESRQ;
			$scope.zafjsbxdbean.DANJ = data.DANJ;
			$scope.zafjsbxdbean.SHUL = data.SHUL;
			$scope.zafjsbxdbean.JINE = data.JINE;
			$scope.zafjsbxdbean.ZAFFYBXDID = $scope.search.id;
			$scope.zafjsbxdbean.ZAFFYDJBID = data.ID;
		});
		$("#myModal").modal('show');
	}
	$scope.savezafdjb = function(){
		if($scope.zafjsbxdbean.ZAFFYDJBID == '0'){
			$http.post('zafjswh/saveZafdjb',{info:angular.toJson($scope.zafjsbxdbean)}).success(function(data){
				alert(data);
				$("#myModal").modal('hide');
				$scope.zafjsbxdbean.HETBIANH = "";
				$scope.zafjsbxdbean.FEIYXM = "";
				$scope.zafjsbxdbean.KAISSJ = "";
				$scope.zafjsbxdbean.JIESSJ = "";
				$scope.zafjsbxdbean.DANJ = "";
				$scope.zafjsbxdbean.SHUL = "";
				$scope.zafjsbxdbean.JINE = "";
				$scope.zafjsbxdbean.ZAFFYDJBID = "";
				$http.post('zafjswh/getbxddata',{zaffybxd_id:$scope.search.id}).success(function(data){
					$scope.arr = data;
				});
			});
		}else{
			$http.post('zafjswh/updateZafdjb',{info:angular.toJson($scope.zafjsbxdbean)}).success(function(data){
				alert(data);
				$("#myModal").modal('hide');
				$scope.zafjsbxdbean.HETBIANH = "";
				$scope.zafjsbxdbean.FEIYXM = "";
				$scope.zafjsbxdbean.KAISSJ = "";
				$scope.zafjsbxdbean.JIESSJ = "";
				$scope.zafjsbxdbean.DANJ = "";
				$scope.zafjsbxdbean.SHUL = "";
				$scope.zafjsbxdbean.JINE = "";
				$scope.zafjsbxdbean.ZAFFYDJBID = "";
				$http.post('zafjswh/getbxddata',{zaffybxd_id:$scope.search.id}).success(function(data){
					$scope.arr = data;
				});
			});
		}
		
	}
	//需要优化，根据search.id是否为0来确定是保存还是修改
	$scope.savezaffybxd = function(){
		if($routeParams.id==0){//新增
			$http.post('zafjswh/savezaffybxd',{zaffybxdid:$scope.search.id,nianf:$routeParams.nianf}).success(function(data){
				alert(data);
				$("#savezaffybxd").removeClass("btn-primary");
				$("#savezaffybxd").attr('disabled',true);
			});
		}else{//更新
			$http.post('zafjswh/updatezaffybxd',{zaffybxdid:$scope.search.id,nianf:$routeParams.nianf}).success(function(data){
				alert(data);
				$("#savezaffybxd").removeClass("btn-primary");
				$("#savezaffybxd").attr('disabled',true);
			});
		}
	}
	$scope.printPage = function() {
		$("#report").jqprint();
	}

})


