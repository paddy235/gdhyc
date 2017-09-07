gddlapp.controller('yuedranlzfjhCtrl', function($scope, $rootScope,$routeParams, $http,$location) {
	$scope.yuedranlzfTitle = '月度燃料杂费计划';
	$scope.search = new Object();
	$scope.search.diancid = 515;
	var d = new Date();
	var vYear = d.getFullYear();
	var vMon = d.getMonth() + 1;
	$scope.search.riq = vYear+"-"+(vMon<10 ? "0" + vMon : vMon);
	if($routeParams.riq != null){
		$scope.search.riq = $routeParams.riq;
	}
	$scope.search.titleriq = $scope.search.riq.replace('-','年');//表格标题里的日期
	oTable.fnReloadAjax('yuedranlzfjh/getRanlzfList?riq='+$scope.search.riq+'-01&diancid='+$scope.search.diancid);
	$http.post('yuedranlzfjh/getshenpstate',{riq:$scope.search.riq,diancid:$scope.search.diancid}).success(function(data){
		$scope.search.state = data;
		if(data!='0'){
			$("#adddata").removeClass("btn-primary");
			 $("#adddata").attr('disabled',true);
			 $("#copyranlzf").removeClass("btn-primary");
			 $("#copyranlzf").attr('disabled',true);
		}
	});
	$scope.refresh = function(){
		 //将删除和更新按钮置灰
		$("#delranlzf").removeClass("btn-primary");
		$("#delranlzf").attr('disabled',true);
		$("#updateranlzf").removeClass("btn-primary");
		$("#updateranlzf").attr('disabled',true);
		$http.post('yuedranlzfjh/getshenpstate',{riq:$scope.search.riq,diancid:$scope.search.diancid}).success(function(data){
			$scope.search.state = data;
			if(data!='0'){
				$("#adddata").removeClass("btn-primary");
				 $("#adddata").attr('disabled',true);
				 $("#copyranlzf").removeClass("btn-primary");
				 $("#copyranlzf").attr('disabled',true);
			}
		});
		//刷新表格
    	riq = $scope.search.riq+'-01';
        diancid= $scope.search.diancid;
    	oTable.fnReloadAjax('yuedranlzfjh/getRanlzfList?riq='+riq+'&diancid='+diancid);
	}
	$scope.addyuedranlzfjh = function(){
		$scope.addtitle ="添加月度燃料杂费计划";
		$location.path('/yuedralzfjhAdd/'+$scope.search.riq+'/'+$scope.search.diancid+'/0/'+$scope.addtitle); 
	}
	$scope.copyranlzfjh = function(){
		$http.post('yuedranlzfjh/getRanlzfList',{diancid:$scope.search.diancid,riq:$scope.search.riq+'-01',caoz:'check'}).success(function(data){
			if(data == "1"){
				var a =confirm($scope.search.riq+"月计划已经存在，你确定要替换吗？？？");
				if(a){
					$http.post('yuedranlzfjh/yuedralzfjhcopy',{diancid:$scope.search.diancid,riq:$scope.search.riq+'-01',caoz:"replace"}).success(function(data){
						oTable.fnReloadAjax('yuedranlzfjh/getRanlzfList?riq='+$scope.search.riq+'-01&diancid='+$scope.search.diancid);
						alert(data);
					});
				}
			}else{
				$http.post('yuedranlzfjh/yuedralzfjhcopy',{diancid:$scope.search.diancid,riq:$scope.search.riq+'-01'}).success(function(data){
					oTable.fnReloadAjax('yuedranlzfjh/getRanlzfList?riq='+$scope.search.riq+'-01&diancid='+$scope.search.diancid);
					alert(data);
				});
			}
		}); 
	}
	$scope.updateyuedcgjh = function(){
		$scope.updatetitle ="修改月度燃料杂费计划";
		$scope.search.id = $('input[name="checkId"]:checked').attr("id");
		$location.path('/yuedranlzfjhUpdate/'+$scope.search.id+'/'+$scope.updatetitle+'/'+$scope.search.riq); 
	}
	 $scope.delranlzf = function(){
		 var id = "";
		 id = $('input[name="checkId"]:checked').attr("id");
		if(id==""){
			 alert("请选择一项进行删除！！！");
		 }else{
			 var a =confirm("你确定要删除此条记录?");
			 if(a){
				 $.post("yuedranlzfjh/Ranlzfdel",//异步处理动态页面
	  				     {id:id},//获取类名为"name"文本的值，以NAME异步传值
	  				     function(data){//data为反回值，function进行反回值处理
	  				    	$("#delranlzf").removeClass("btn-primary");
	  						$("#delranlzf").attr('disabled',true);
	  						$("#updateranlzf").removeClass("btn-primary");
	  						$("#updateranlzf").attr('disabled',true);
	  						riq = $scope.search.riq+"-01";
				   	        diancid= $scope.search.diancid;
				   	    	oTable.fnReloadAjax('yuedranlzfjh/getRanlzfList?riq='+riq+'&diancid='+diancid);
	  				        alert(data);//获得得反回值后，将其填入到类名为"content"的文本框中
	  				      });
			 }else{
				 return false;
			 }
		 }
	    }
	$scope.selectriq = function(){
		$scope.search.titleriq = $scope.search.riq.replace('-','年');
		 var riq= $scope.search.riq;
		 var dianc =  $scope.search.diancid;
		 if(riq!=""&&dianc!=-1){
			 $http.post('yuedranlzfjh/getshenpstate',{riq:$scope.search.riq,diancid:$scope.search.diancid}).success(function(data){
					$scope.search.state = data;
					if(data=='0'){
						$("#adddata").addClass("btn-primary");
						 $("#adddata").removeAttr("disabled"); //移除disabled属性 
						 $("#copyranlzf").addClass("btn-primary");
						 $("#copyranlzf").removeAttr("disabled"); //移除disabled属性
					}else{
						 $("#adddata").removeClass("btn-primary");
						 $("#adddata").attr('disabled',true);
						 $("#copyranlzf").removeClass("btn-primary");
						 $("#copyranlzf").attr('disabled',true);
					}
				}); 

		 }else{
			 $("#adddata").removeClass("btn-primary");
			 $("#adddata").attr('disabled',true);
			 $("#copyranlzf").removeClass("btn-primary");
			 $("#copyranlzf").attr('disabled',true);
		 }
		 $scope.refresh();
	 }
	 $scope.selectdianc = function(){
		 var riq= $scope.search.riq;
		 var dianc =  $scope.search.diancid;
		 if(riq!="" && dianc!=-1){
			 $http.post('yuedranlzfjh/getshenpstate',{riq:$scope.search.riq,diancid:$scope.search.diancid}).success(function(data){
					$scope.search.state = data;
					if(data=='0'){
						$("#adddata").addClass("btn-primary");
						 $("#adddata").removeAttr("disabled"); //移除disabled属性 
						 $("#copyranlzf").addClass("btn-primary");
						 $("#copyranlzf").removeAttr("disabled"); //移除disabled属性
					}else{
						 $("#adddata").removeClass("btn-primary");
						 $("#adddata").attr('disabled',true);
						 $("#copyranlzf").removeClass("btn-primary");
						 $("#copyranlzf").attr('disabled',true);
					}
				});
			  
		 }else{
			 $("#adddata").removeClass("btn-primary");
			 $("#adddata").attr('disabled',true);
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

.controller('AddyuedranlzfCtrl', function($scope, $rootScope,$routeParams, $http,$location) {
	$http.post('yuedranlzfjh/getZfmingcById').success(function(data){ $scope.zafList=data});
	$scope.addranlzfTitle = $routeParams.title;
	$scope.ranlzfbean = new Object();
	if($routeParams.id != 0){
		$http.post('yuedranlzfjh/getRanlzfById',{id:$routeParams.id}).success(function(data){
			$scope.ranlzfbean.ID = data.ID;
			$scope.ranlzfbean.DIANCID = data.DIANCXXB_ID;
			$scope.ranlzfbean.RIQ =  data.RIQ;
			$scope.ranlzfbean.YUCSM = data.YUCSM;
			$scope.ranlzfbean.YUCJE = data.YUCJE;
			$scope.ranlzfbean.ZAFMC = data.ZAFMC;
		});
	}else{
		$scope.ranlzfbean.RIQ = $routeParams.riq+"-01";
		$scope.ranlzfbean.DIANCID = $routeParams.diancid;
		$scope.ranlzfbean.ID = $routeParams.id;
		$scope.ranlzfbean.YUCSM = 0;
		$scope.ranlzfbean.YUCJE = 0;
		$scope.ranlzfbean.ZAFMC = '';
	}
	
	$scope.cancel = function(){
		$location.path('/yuedranlzfjhadd/'+$routeParams.riq); 
	}
	$scope.saveRanlzf = function(){
		if($scope.ranlzfbean.ID==0){
			$http.post('yuedranlzfjh/ranlzfadd',{info:angular.toJson($scope.ranlzfbean)}).success(function(data){
				alert(data);
				$location.path('/yuedranlzfjhadd/'+$routeParams.riq);
			});
		}else{
			$http.post('yuedranlzfjh/Ranlzfupdate',{info:angular.toJson($scope.ranlzfbean)}).success(function(data){
				alert(data);
				$location.path('/yuedranlzfjhadd/'+$routeParams.riq);
			});
		}
	
	}
});
