gddlapp.controller('jiesdxgCtrl', function($scope, $rootScope,$routeParams, $http,$location) {
	$scope.jiesdxgTitle = '结算单修改';
	$scope.data= {
		MEIKJE:0, 
		JIESSL:0,
		JIESJG:0,
		SHUIK:0		
	}

	$scope.search = {
		diancid : 515,
		jiesbh : ''
	}
	
	$("#refresh").hide();
	$("#delete").hide(); 
	$("#reback").hide();
	
	$http.post('jiesgl/jiesdxg/getJiesdbh').success(function(data) {
		$scope.jiesdbhList = data;
	});
	$scope.calculateMEIKHJ=function(){
		$scope.data.MEIKHJ=	(parseFloat($scope.data.MEIKJE)+parseFloat($scope.data.SHUIK)).toFixed(2)
	}
	$scope.data.MEIKHJ=	(parseFloat($scope.data.MEIKJE)+parseFloat($scope.data.SHUIK)).toFixed(2)
	$scope.getJiesdbh = function(){ 
		$http.post('jiesgl/jiesdxg/getJiesdbh').success(function(data) {
			$scope.jiesdbhList = data; 
		});
	} 
	
	$scope.searchData = function(){
		if($scope.search.jiesdid==null||$scope.search.jiesdid==""){
			alert("请选择结算单编号！"); 
			return false;
		}
		$http.post('jiesgl/jiesdxg/getJiesd',{jiesdid:$scope.search.jiesdid}).success(function(data) {
			$scope.data = data;
			$http.post('jiesgl/jiesdxg/check',{jiesdid:$scope.search.jiesdid}).success(function(data) {
				if(data[0]!="1"){
					$("#refresh").show();
					$("#delete").show();
					$("#reback").show();
				}else{
					$("#refresh").hide();
					$("#delete").hide();
					$("#reback").hide();
				}
			});
// 		$scope.data.MEIKJE=(parseFloat($scope.data.JIESSL).toFixed(0)*(parseFloat($scope.data.JIESJG)/1.17)).toFixed(2)
		$scope.data.MEIKHJ=	(parseFloat($scope.data.MEIKJE)+parseFloat($scope.data.SHUIK)).toFixed(2)
		});
		
	}
	
	$scope.save = function(){
		$http.post('jiesgl/jiesdxg/save',{id:$scope.search.jiesdid,data:angular.toJson($scope.data)}).success(function(data) {
			if(data[0]==1){
				alert("保存成功！"); 
				$http.post('jiesgl/jiesdxg/getJiesdbh').success(function(data) {
					$scope.jiesdbhList = data;
					$scope.searchData();
				});
			}else{
				alert("保存失败！");
			}
		});
	}
	
	$scope.shanchu = function(){ 
		$http.post('jiesgl/jiesdxg/delete',{jiesdid:$scope.search.jiesdid}).success(function(data) {
			if(data[0]==1){ 
				alert("删除成功！"); 
				$http.post('jiesgl/jiesdxg/getJiesdbh').success(function(data) {
					$scope.jiesdbhList = data; 
				});
				$http.post('jiesgl/jiesdxg/getJiesd',{jiesdid:$scope.search.jiesdid}).success(function(data) {
					$scope.data = data;
					$("#refresh").hide();
					$("#delete").hide();
					$("#reback").hide();
				});
			}else{
				alert("删除失败！");
			}
		});
	}
	
	$scope.tijiao = function(){
		$scope.showProgress=true;
		$http.post('jiesgl/jiesdxg/submit',{jiesdid:$scope.search.jiesdid}).success(function(data) {
			if(data[0]==1){
				$http.post('jiesgl/jiesdxg/getJiesd',{jiesdid:$scope.search.jiesdid}).success(function(data) {
					$scope.data = data;
					$http.post('jiesgl/jiesdxg/check',{jiesdid:$scope.search.jiesdid}).success(function(data) {
						if(data[0]!="1"){
							$("#refresh").show();
							$("#delete").show();
							$("#reback").show();
						}else{
							$("#refresh").hide();
							$("#delete").hide();
							$("#reback").hide();
						}
					});
				});

				alert("提交成功！");
				$scope.showProgress=false;
				$http.post('jiesgl/jiesdxg/getJiesdbh').success(function(data) {
					$scope.jiesdbhList = data;
				});
			}else{

				alert("提交失败！");
				$scope.showProgress=false;
			}
		});
	}
})
