gddlapp.controller('yuedjihzhibycCtrl', function($scope, $rootScope,$routeParams, $http,$location) {
	$scope.yuedjihzhibTitle = '月度计划指标预测';
	$scope.search = new Object();
	$scope.search.diancid = 515;
	var d = new Date();
	var vYear = d.getFullYear();
	var vMon = d.getMonth() + 1;
	$scope.search.riq = vYear+"-"+(vMon<10 ? "0" + vMon : vMon);
	$http.post('yuedjhzhibyc/getshenpstate',{riq:$scope.search.riq,diancid:$scope.search.diancid}).success(function(data){
		$scope.search.state = data;
		if(data!='0'){
			$("#copyranlzf").removeClass("btn-primary");
			 $("#copyranlzf").attr('disabled',true);
			$("#delranlzf").removeClass("btn-primary");
			$("#delranlzf").attr('disabled',true);
		}
//		$http.post('yuedjhzhibyc/save', {
//			riq : $scope.search.riq,
//			diancid:$scope.search.diancid
//		}).success(function(data) {
//			oTable.fnReloadAjax('yuedjhzhibyc/getzhibycList?riq='+$scope.search.riq+'&diancid='+$scope.search.diancid);
//		});
		oTable.fnReloadAjax('yuedjhzhibyc/getzhibycList?riq='+$scope.search.riq+'&diancid='+$scope.search.diancid);
	});
	var oTable  = $('#example').dataTable({
		"processing" : true,
		"ordering" : false,
		'bPaginate' : false,
		'bAutoWidth' : false,
		'bFilter' : false,
		'bInfo' : false,
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
		"columns": [{"data":"ID"},
					{"data":"XUH"},
				    {"data":"ZIDM"},
					{"data": "MINGC"},
					{"data": "DANW"},
					{
						"data": "ZHI",
						"fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
							if(iRow !=19&&iRow !=6 &&iRow !=17&&iRow !=15&&iRow !=14 &&iRow !=12&&iRow !=13&&iRow !=20&&iRow !=21 ){
								if($scope.search.state =='0'){
									$(nTd).addClass('inputTd').attr('id', oData.ZIDM);
								}
							}else{
								$(nTd).attr('style', 'background-color:#d0d0d0;');
							}
						}
					}
				],
				"fnDrawCallback": function (data, x) {
					//console.log("fnDrawCallback");
					$('#example tbody td.inputTd').editable('yuedjhzhibyc/savezhibyc?riq='+$scope.search.riq+'&diancid='+$scope.search.diancid,{
						type : 'text',
						height : '100%',
						width:'100%',
						onblur : 'submit',
						callback : function(value, settings) {
							oTable.fnReloadAjax('yuedjhzhibyc/getzhibycList?riq='+$scope.search.riq+'-01&diancid='+$scope.search.diancid);
					    }
					});
				},
		"aoColumnDefs": [{
            "sClass": "center",
            "mRender": function(oObj, sVal) {
                return '<input type="checkbox" id="' + oObj + '" name="checkId" onclick="check(this)" />';
            },
            "bSortable": false,
            "aTargets": [0]
        },{ "bVisible": false, "aTargets": [0,2]}]
	});
	oTable.fnReloadAjax('yuedjhzhibyc/getzhibycList?riq='+$scope.search.riq+'-01&diancid='+$scope.search.diancid);
	$scope.refresh = function(){
		$http.post('yuedjhzhibyc/getshenpstate',{riq:$scope.search.riq,diancid:$scope.search.diancid}).success(function(data){
			$scope.search.state = data;
			if(data!='0'){
				$("#copyranlzf").removeClass("btn-primary");
				 $("#copyranlzf").attr('disabled',true);
				$("#delranlzf").removeClass("btn-primary");
				$("#delranlzf").attr('disabled',true);
			}
//			$http.post('yuedjhzhibyc/save', {
//				riq : $scope.search.riq,
//				diancid:$scope.search.diancid
//			}).success(function(data) {
//				//刷新表格
//		    	riq = $scope.search.riq+'-01';
//		        diancid= $scope.search.diancid;
//				oTable.fnReloadAjax('yuedjhzhibyc/getzhibycList?riq='+$scope.search.riq+'&diancid='+$scope.search.diancid);
//			});
			oTable.fnReloadAjax('yuedjhzhibyc/getzhibycList?riq='+$scope.search.riq+'&diancid='+$scope.search.diancid);
		});
	}
	$scope.copyranlzfjh = function(){
		$http.post('yuedjhzhibyc/getzhibycCount',{diancid:$scope.search.diancid,riq:$scope.search.riq+'-01',caoz:'check'}).success(function(data){
			if(data == "1"){
				var a =confirm($scope.search.riq+"月计划已经存在，你确定要替换吗？？？");
				if(a){
					$http.post('yuedjhzhibyc/yuedjihzhibyccopy',{diancid:$scope.search.diancid,riq:$scope.search.riq+'-01',caoz:"replace"}).success(function(data){
						oTable.fnReloadAjax('yuedjhzhibyc/getzhibycList?riq='+$scope.search.riq+'-01&diancid='+$scope.search.diancid);
						alert(data);
					});
				}
			}else{
				$http.post('yuedjhzhibyc/yuedjihzhibyccopy',{diancid:$scope.search.diancid,riq:$scope.search.riq+'-01'}).success(function(data){
					oTable.fnReloadAjax('yuedjhzhibyc/getzhibycList?riq='+$scope.search.riq+'-01&diancid='+$scope.search.diancid);
					alert(data);
				});
			}
		}); 
	}
	 $scope.delranlzf = function(){
			 var a =confirm("你确定要删除此条记录?");
			 if(a){
				 $.post("yuedjhzhibyc/zhibycdel",//异步处理动态页面
	  				     {diancid:$scope.search.diancid,riq:$scope.search.riq+'-01'},//获取类名为"name"文本的值，以NAME异步传值
	  				     function(data){//data为反回值，function进行反回值处理
	  						oTable.fnReloadAjax('yuedjhzhibyc/getzhibycList?riq='+$scope.search.riq+'-01&diancid='+$scope.search.diancid);
	  				        alert(data);//获得得反回值后，将其填入到类名为"content"的文本框中
	  				      });
			 }else{
				 return false;
			 }
	    }
	$scope.selectriq = function(){
		 var riq= $scope.search.riq;
		 var dianc =  $scope.search.diancid;
		 if(riq!=""&&dianc!=-1){
			 $http.post('yuedjhzhibyc/getshenpstate',{riq:$scope.search.riq,diancid:$scope.search.diancid}).success(function(data){
					$scope.search.state = data;
					if(data!='0'){
						$("#copyranlzf").removeClass("btn-primary");
						 $("#copyranlzf").attr('disabled',true);
						$("#delranlzf").removeClass("btn-primary");
						$("#delranlzf").attr('disabled',true);
					}else{
						 $("#copyranlzf").addClass("btn-primary");
						 $("#copyranlzf").removeAttr("disabled");//移除disabled属性 
						 $("#delranlzf").addClass("btn-primary");
						 $("#delranlzf").removeAttr("disabled");//移除disabled属性 
					}
				});

		 }else{
			 $("#copyranlzf").removeClass("btn-primary");
			 $("#copyranlzf").attr('disabled',true);
		 }
		 $scope.refresh();
	 }
	 $scope.selectdianc = function(){
		 var riq= $scope.search.riq;
		 var dianc =  $scope.search.diancid;
		 if(riq!="" && dianc!=-1){
			 $http.post('yuedjhzhibyc/getshenpstate',{riq:$scope.search.riq,diancid:$scope.search.diancid}).success(function(data){
					$scope.search.state = data;
					if(data!='0'){
						$("#copyranlzf").removeClass("btn-primary");
						 $("#copyranlzf").attr('disabled',true);
						$("#delranlzf").removeClass("btn-primary");
						$("#delranlzf").attr('disabled',true);
					}else{
						 $("#copyranlzf").addClass("btn-primary");
						 $("#copyranlzf").removeAttr("disabled");//移除disabled属性 
						 $("#delranlzf").addClass("btn-primary");
						 $("#delranlzf").removeAttr("disabled");//移除disabled属性 
					}
				});
		 }else{
			 $("#copyranlzf").removeClass("btn-primary");
			 $("#copyranlzf").attr('disabled',true);
		 }
	 }
	 $("#datepicker").datepicker({
			format : 'yyyy-mm',
			minViewMode : 1,
			language : "zh-CN",
			autoclose : true
	});
})

