gddlapp.controller('meikrjsCtrl', function($scope, $rootScope,$routeParams, $http,$location) {
	$scope.meikrjsTitle = '煤款日结算';
	
	var date = new Date();
	var year = date.getFullYear();
	var month = date.getMonth()+1<10?'0'+(date.getMonth()+1):date.getMonth()+1;
	var day = date.getDate()<10?'0'+date.getDate():date.getDate();
	
	$scope.search = {
		sDate : year+'-'+month+'-01',
		eDate : year+'-'+month+'-'+day
		//gongys:-1,
		//caigdd:'-1',
		//hetbh:'-1'
	}
	
	$scope.refresh = function(){
		var sDate = $scope.search.sDate;
		var eDate = $scope.search.eDate;
		//var gongys = $scope.search.gongys;
		//var caigdd = $scope.search.caigdd;
		//var hetbh = $scope.search.hetbh;
        //oTable.fnReloadAjax(
       // 		'jiesgl/meikjs/getJiesList?sDate='+sDate+'&eDate='+eDate+'&gongys='+gongys+'&caigdd='+caigdd+'&hetbh='+hetbh);
        oTable.fnReloadAjax(
        		'jiesgl/meikjs/getJiesList?sDate='+sDate+'&eDate='+eDate);
	}
	
	$scope.jies = function(){
		// $http.post('test/rijsJob').success(function(data, status, headers, config) {
		// 	alert("ok！")
		// })
		if($("#example input[type=checkbox]:checked").length<1){
			alert("请选择要结算的记录！")
		}else{
			$("#example input[type=checkbox]").each(function(){
			    if(this.checked){
			    	$http.post('jiesgl/meikjs/jies',{rukd_id:$(this).attr("id")}).
				       success(function(data, status, headers, config) {
				    	   if(data[0]!=""&&data[0]!=null){
				    		   $location.path("/meikrjsdj/"+data[0]);
				    	   }else{
				    		   alert("结算失败！");
				    	   }
				    });
			    }
			});
		}
	}
})
