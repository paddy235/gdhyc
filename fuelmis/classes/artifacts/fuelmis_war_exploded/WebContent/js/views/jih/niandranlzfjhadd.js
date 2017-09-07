gddlapp.controller('niandranlzfjhCtrl', function($scope, $rootScope,$routeParams, $http,$location) { 
	$scope.naindranlzfTitle = '年度燃料杂费计划';
	$scope.search = new Object();
	var d = new Date();
	var vYear = d.getFullYear();
	$scope.search.nianf = vYear;
	$scope.search.diancid = 515;
	if($routeParams.nianf != null){
		$scope.search.nianf = $routeParams.nianf;
	}
	oTable.fnReloadAjax('niandranlzfjh/getRanlzfList?riq='+$scope.search.nianf+'-01-01&diancid='+$scope.search.diancid);
	$http.post('niandranlzfjh/getshenpstate',{nianf:$scope.search.nianf,diancid:$scope.search.diancid}).success(function(data){
		$scope.search.state = data;
		if(data!='0'){
			$("#adddata").removeClass("btn-primary");
			 $("#adddata").attr('disabled',true);
			 $("#copyniandcaig").removeClass("btn-primary");
			 $("#copyniandcaig").attr('disabled',true);
		}
	});
	$scope.refresh = function(){
		$("#delniandcaig").removeClass("btn-primary");
		$("#delniandcaig").attr('disabled',true);
		$("#updateniandcaig").removeClass("btn-primary");
		$("#updateniandcaig").attr('disabled',true);
		$http.post('niandranlzfjh/getshenpstate',{nianf:$scope.search.nianf,diancid:$scope.search.diancid}).success(function(data){
			$scope.search.state = data;
			if(data!='0'){
				$("#adddata").removeClass("btn-primary");
				 $("#adddata").attr('disabled',true);
				 $("#copyniandcaig").removeClass("btn-primary");
				 $("#copyniandcaig").attr('disabled',true);
			}
		});
    	riq = $scope.search.nianf+'-01-01';
        diancid= $scope.search.diancid;
    	oTable.fnReloadAjax('niandranlzfjh/getRanlzfList?riq='+riq+'&diancid='+diancid);
	}
	$scope.addyuedranlzfjh = function(){
		$scope.addtitle ="添加年度燃料杂费计划";
		$location.path('/niandralzfjhAdd/'+$scope.search.nianf+'/'+$scope.search.diancid+'/0/'+$scope.addtitle); 
	}
	$scope.copyniandcaigjh = function(){
		$http.post('niandranlzfjh/getRanlzfList',{diancid:$scope.search.diancid,riq:$scope.search.nianf+'-01-01',caoz:'check'}).success(function(data){
			if(data == "1"){
				var a =confirm($scope.search.nianf+"年计划已经存在，你确定要替换吗？？？");
				if(a){
					$http.post('niandranlzfjh/niandralzfjhcopy',{diancid:$scope.search.diancid,nianf:$scope.search.nianf,caoz:"replace"}).success(function(data){
						oTable.fnReloadAjax('niandranlzfjh/getRanlzfList?riq='+$scope.search.nianf+'-01-01&diancid='+$scope.search.diancid);
						alert(data);
					});
				}
			}else{
				$http.post('niandranlzfjh/niandralzfjhcopy',{diancid:$scope.search.diancid,nianf:$scope.search.nianf}).success(function(data){
					oTable.fnReloadAjax('niandranlzfjh/getRanlzfList?riq='+$scope.search.nianf+'-01-01&diancid='+$scope.search.diancid);
					alert(data);
				});
			}
		}); 
	}
	$scope.delniandcaig = function(){
		 var id = "";
		 id = $('input[name="checkId"]:checked').attr("id");
		if(id==""){
			 alert("请选择一项进行删除！！！");
		 }else{
			 var a =confirm("你确定要删除此条记录?");
			 if(a){
				 $.post("niandranlzfjh/Ranlzfdel",//异步处理动态页面
	  				     {id:id},//获取类名为"name"文本的值，以NAME异步传值
	  				     function(data){//data为反回值，function进行反回值处理
	  				    	$("#delniandcaig").removeClass("btn-primary");
	  						$("#delniandcaig").attr('disabled',true);
	  						$("#updateniandcaig").removeClass("btn-primary");
	  						$("#updateniandcaig").attr('disabled',true);
	  						riq = $scope.search.nianf+'-01-01';
	  				        diancid= $scope.search.diancid;
				   	    	oTable.fnReloadAjax('niandranlzfjh/getRanlzfList?riq='+riq+'&diancid='+diancid);
	  				        alert(data);//获得得反回值后，将其填入到类名为"content"的文本框中
	  				      });
			 }else{
				 return false;
			 }
		 }
	}
	$scope.updateyuedcgjh = function(){
		$scope.updatetitle ="修改年度燃料杂费计划";
		$scope.search.id = $('input[name="checkId"]:checked').attr("id");
		$location.path('/niandranlzfjhUpdate/'+$scope.search.id+'/'+$scope.updatetitle+'/'+$scope.search.nianf); 
	}
	$scope.selectdianc= function(){
		 var riq= $scope.search.nianf;
		 var dianc =  $scope.search.diancid;
		 if(riq!=""&&dianc!=-1){
			 $http.post('niandranlzfjh/getshenpstate',{nianf:$scope.search.nianf,diancid:$scope.search.diancid}).success(function(data){
					$scope.search.state = data;
					if(data=='0'){
						$("#adddata").addClass("btn-primary");
						 $("#adddata").removeAttr("disabled"); //移除disabled属性 
						 $("#copyniandcaig").addClass("btn-primary");
						 $("#copyniandcaig").removeAttr("disabled"); //移除disabled属性 
					}else{
						$("#adddata").removeClass("btn-primary");
						$("#adddata").attr('disabled',true);
						$("#copyniandcaig").removeClass("btn-primary");
						$("#copyniandcaig").attr('disabled',true);
					}
				});
		 }else{
			 $("#adddata").removeClass("btn-primary");
			 $("#adddata").attr('disabled',true);
			 $("#copyniandcaig").removeClass("btn-primary");
			 $("#copyniandcaig").attr('disabled',true);
		 }
	 }
	 $scope.selectnianf = function(){
		 var riq= $scope.search.nianf;
		 var dianc = $scope.search.diancid;
		 if(riq!="" && dianc!=-1){
			 $http.post('niandranlzfjh/getshenpstate',{nianf:$scope.search.nianf,diancid:$scope.search.diancid}).success(function(data){
					$scope.search.state = data;
					if(data=='0'){
						$("#adddata").addClass("btn-primary");
						 $("#adddata").removeAttr("disabled"); //移除disabled属性 
						 $("#copyniandcaig").addClass("btn-primary");
						 $("#copyniandcaig").removeAttr("disabled"); //移除disabled属性 
					}else{
						$("#adddata").removeClass("btn-primary");
						$("#adddata").attr('disabled',true);
						$("#copyniandcaig").removeClass("btn-primary");
						$("#copyniandcaig").attr('disabled',true);
					}
				});
		 }else{
			 $("#adddata").removeClass("btn-primary");
			 $("#adddata").attr('disabled',true);
			 $("#copyniandcaig").removeClass("btn-primary");
			 $("#copyniandcaig").attr('disabled',true);
		 }
		 $scope.refresh();
	 }
	$("#datepicker").datepicker({
		format : 'yyyy',
		minViewMode : 2,
		language : "zh-CN",
		autoclose : true
	});
	
})

.controller('AddniandranlzfCtrl', function($scope, $rootScope,$routeParams, $http,$location) {
	$http.post('yuedranlzfjh/getZfmingcById').success(function(data){$scope.zafList=data});
	$scope.addranlzfTitle = $routeParams.title;
	$scope.nianf = $routeParams.nianf;
	$scope.ranlzfbean = new Object();
	if($routeParams.id != 0){
		$http.post('niandranlzfjh/getRanlzfById',{id:$routeParams.id}).success(function(data){
			$scope.ranlzfbean.ID = data.ID;
			$scope.ranlzfbean.DIANCID = data.DIANCXXB_ID;
			$scope.ranlzfbean.RIQ =  data.RIQ;
			$scope.ranlzfbean.ZAFMC = data.ZAFMC;
			$scope.ranlzfbean.YUCJE = data.YUCJE;
			$scope.ranlzfbean.YUCSM = data.YUCSM;
			$scope.ranlzfbean.SHIJWCJE = data.SHIJWCJE;
			$scope.ranlzfbean.YUJWCJE = data.YUJWCJE;
			$scope.ranlzfbean.YUJWCSM = data.YUJWCSM;
			$scope.ranlzfbean.SHNYJWCJE = Number(data.SHIJWCJE)+Number(data.YUJWCJE);
		});
	}else{
		$scope.ranlzfbean.RIQ = $routeParams.nianf+"-01-01";
		$scope.ranlzfbean.DIANCID = $routeParams.diancid;
		$scope.ranlzfbean.ID = $routeParams.id;

		$scope.ranlzfbean.YUCJE = 0;
		$scope.ranlzfbean.YUCSM = 0;
		$scope.ranlzfbean.SHIJWCJE = 0;
		$scope.ranlzfbean.YUJWCJE = 0;
		$scope.ranlzfbean.YUJWCSM = 0;
		$scope.ranlzfbean.SHNYJWCJE = 0;
	}
	$scope.cancel = function(){
		$location.path('/niandranlzfjhadd/'+$routeParams.nianf); 
	}
	$scope.saveRanlzf = function(){
		if($scope.ranlzfbean.ID==0){
			$http.post('niandranlzfjh/ranlzfadd',{info:angular.toJson($scope.ranlzfbean)}).success(function(data){
				alert(data);
				$location.path('/niandranlzfjhadd/'+$routeParams.nianf);
			});
		}else{
			$http.post('niandranlzfjh/Ranlzfupdate',{info:angular.toJson($scope.ranlzfbean)}).success(function(data){
				alert(data);
				$location.path('/niandranlzfjhadd/'+$routeParams.nianf);
			});
		}
	}
});
