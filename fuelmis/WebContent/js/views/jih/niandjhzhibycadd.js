gddlapp.controller('niandjihzhibycCtrl', function($scope, $rootScope,$routeParams, $http,$location) {
	$scope.niandjihzhibTitle = '年度计划指标预测';
	$scope.search = new Object();
	$scope.search.diancid = 515;
	var d = new Date();
	var vYear = d.getFullYear();
	$scope.search.nianf = vYear;
	$http.post('niandjhzhibyc/getshenpstate',{nianf:$scope.search.nianf,diancid:$scope.search.diancid}).success(function(data){
		$scope.search.state = data;
		if(data!='0'){
			$("#copyranlzf").removeClass("btn-primary");
			 $("#copyranlzf").attr('disabled',true);
			$("#delranlzf").removeClass("btn-primary");
			$("#delranlzf").attr('disabled',true);
		}
		$http.post('niandjhzhibyc/save', {
			nianf : $scope.search.nianf,
			diancid:$scope.search.diancid
		}).success(function(data) {
			oTable.fnReloadAjax('niandjhzhibyc/getzhibycList?nianf='+$scope.search.nianf+'&diancid='+$scope.search.diancid);
		});
		
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
							if(iRow <8){
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
					$('#example tbody td.inputTd').editable('niandjhzhibyc/savezhibyc?nianf='+$scope.search.nianf+'&diancid='+$scope.search.diancid,{
						type : 'text',
						height : '100%',
						width:'100%',
						onblur : 'submit',
						callback : function(value, settings) {
							oTable.fnReloadAjax('niandjhzhibyc/getzhibycList?nianf='+$scope.search.nianf+'&diancid='+$scope.search.diancid);
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
        },
        { "bVisible": false, "aTargets": [0,2]}]
	});
	
	$scope.refresh = function(){
		$http.post('niandjhzhibyc/getshenpstate',{nianf:$scope.search.nianf,diancid:$scope.search.diancid}).success(function(data){
			$scope.search.state = data;
			if(data!='0'){
				$("#copyranlzf").removeClass("btn-primary");
				 $("#copyranlzf").attr('disabled',true);
				$("#delranlzf").removeClass("btn-primary");
				$("#delranlzf").attr('disabled',true);
			}
			 //将删除和更新按钮置灰
			//刷新表格
	    	nianf = $scope.search.nianf+'';
	        diancid= $scope.search.diancid;
			$http.post('niandjhzhibyc/save', {
				nianf : $scope.search.nianf,
				diancid:$scope.search.diancid
			}).success(function(data) {
				 oTable.fnReloadAjax('niandjhzhibyc/getzhibycList?nianf='+$scope.search.nianf+'&diancid='+$scope.search.diancid);
			});
	       

		});

	}
	$scope.copyranlzfjh = function(){
		$http.post('niandjhzhibyc/getzhibycCount',{diancid:$scope.search.diancid,nianf:$scope.search.nianf+'',caoz:'check'}).success(function(data){
			if(data == "1"){
				var a =confirm($scope.search.nianf+"年计划已经存在，你确定要替换吗？？？");
				if(a){
					$http.post('niandjhzhibyc/niandjihzhibyccopy',{diancid:$scope.search.diancid,nianf:$scope.search.nianf+'',caoz:"replace"}).success(function(data){
						oTable.fnReloadAjax('niandjhzhibyc/getzhibycList?nianf='+$scope.search.nianf+'&diancid='+$scope.search.diancid);
						alert(data);
					});
				}
			}else{
				$http.post('niandjhzhibyc/niandjihzhibyccopy',{diancid:$scope.search.diancid,nianf:$scope.search.nianf+''}).success(function(data){
					oTable.fnReloadAjax('niandjhzhibyc/getzhibycList?nianf='+$scope.search.nianf+'&diancid='+$scope.search.diancid);
					alert(data);
				});
			}
		}); 
	}
	 $scope.delranlzf = function(){
			 var a =confirm("你确定要删除此条记录?");
			 if(a){
				 $.post("niandjhzhibyc/zhibycdel",//异步处理动态页面
	  				     {diancid:$scope.search.diancid,nianf:$scope.search.nianf+''},//获取类名为"name"文本的值，以NAME异步传值
	  				     function(data){//data为反回值，function进行反回值处理
	  						oTable.fnReloadAjax('niandjhzhibyc/getzhibycList?nianf='+$scope.search.nianf+'&diancid='+$scope.search.diancid);
	  				        alert(data);//获得得反回值后，将其填入到类名为"content"的文本框中
	  				      });
			 }else{
				 return false;
			 }
	    }
	$scope.selectriq = function(){
		//$scope.search.titlenianf = $scope.search.nianf.replace('-','年');
		 var nianf= $scope.search.nianf;
		 var dianc =  $scope.search.diancid;
		 if(nianf!=""&&dianc!=-1){
			 $http.post('niandjhzhibyc/getshenpstate',{nianf:$scope.search.nianf,diancid:$scope.search.diancid}).success(function(data){
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
		 var nianf= $scope.search.nianf;
		 var dianc =  $scope.search.diancid;
		 if(nianf!="" && dianc!=-1){
			 $http.post('niandjhzhibyc/getshenpstate',{nianf:$scope.search.nianf,diancid:$scope.search.diancid}).success(function(data){
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
			format : 'yyyy',
			minViewMode : 2,
			language : "zh-CN",
			autoclose : true
	});
})

