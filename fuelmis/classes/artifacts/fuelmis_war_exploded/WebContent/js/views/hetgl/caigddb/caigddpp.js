gddlapp.controller('caigddppCtrl', function($scope,$rootScope,$http,$log,$location) {
	$scope.caigddppTitle='采购订单匹配';
	$scope.search = new Object();
	$scope.search.diancid = 515; 
	$scope.search.meikid = -1; 
	$scope.search.pinzid = -1;
	$scope.datalist =[];
	$http.post('hetgl/caigddpp/getCaigdppList',{diancid :$scope.search.diancid,meikid:$scope.search.meikid,pinzid:$scope.search.pinzid}).success(function(data) {
		$scope.datalist=data;
	});
	$scope.add= function(){
		$scope.datalist.push({MEIKXXB_ID:0});
	}
	$scope.refresh=function(){
		$http.post('hetgl/caigddpp/getCaigdppList',{diancid :$scope.search.diancid,meikid:$scope.search.meikid,pinzid:$scope.search.pinzid}).success(function(data) {
			$scope.datalist=data;
		});
    }
	//排序功能
	$scope.mzsort=function(){		
		$scope.datalist.sort(compare("MEIZ_ID"));
		$(".glyphicon-arrow-down").removeClass("arrow");
		$("#mza").toggleClass("arrow");
	}
	$scope.dwsort=function(){
		$scope.datalist.sort(compare("MEIKXXB_ID"));
		$(".glyphicon-arrow-down").removeClass("arrow");
		$("#mka").toggleClass("arrow");
	}
	$scope.qsrqsort=function(){		
		$scope.datalist.sort(sortTime("QISRQ"));
		$(".glyphicon-arrow-down").removeClass("arrow");
		$("#qsa").toggleClass("arrow");
	}
	$scope.jzrqsort=function(){		
		$scope.datalist.sort(sortTime("JIESRQ"));
		$(".glyphicon-arrow-down").removeClass("arrow");
		$("#jza").toggleClass("arrow");
	}
	//比较器
	function compare(propertyName) {
		return function (object1, object2) {
			var value1 =parseInt(object1[propertyName]);
			var value2 = parseInt(object2[propertyName]);
			if (value2 < value1) {
				return -1;
			}
			else if (value2 > value1) {
				return 1;
			}
			else {
				return 0;
			}
		}
	}
	//比较器(时间)
	function sortTime(propertyName) {
		return function (object1, object2) {
			var value1 =new Date(object1[propertyName]).getTime();
			var value2 =new Date(object2[propertyName]).getTime();
			if (value2 < value1) {
				return -1;
			}
			else if (value2 > value1) {
				return 1;
			}
			else {
				return 0;
			}
		}
	}

	$http.post('hetgl/caigddpp/getCaigddxxcombox'). success(function(data) {
		 $scope.caigddxxlist=data//煤矿信息
	});
	$scope.save = function(){
		//判断时间是否重叠
		var localData=$scope.datalist;
		for(var i=0;i<localData.length;i++){
			for(var j=i+1;j<localData.length;j++){
				if(localData[i].MEIKXXB_ID==localData[j].MEIKXXB_ID){
					if(localData[i].MEIZ_ID==localData[j].MEIZ_ID){
						var qsrq1=new Date(localData[i].QISRQ);
						var jzrq1=new Date(localData[i].JIESRQ);
						var qsrq2=new Date(localData[j].QISRQ);
						var jzrq2=new Date(localData[j].JIESRQ);
						if(qsrq1>=qsrq2&&qsrq1<=jzrq2){
							alert("时间不符合要求，请检查同一单位同一煤种下的日期是否重叠包含")
							return
						}else if(jzrq1>=qsrq2&&jzrq1<=jzrq2){
							alert("时间不符合要求，请检查同一单位同一煤种下的日期是否重叠包含")
							return
						}else if(qsrq2>=qsrq1&&qsrq2<=jzrq1){
							alert("时间不符合要求，请检查同一单位同一煤种下的日期是否重叠包含")
							return
						}else if(jzrq2>=qsrq1&&jzrq2<=jzrq1){
							alert("时间不符合要求，请检查同一单位同一煤种下的日期是否重叠包含")
							return
						}
//						if((qsrq1<=qsrq2&&jzrq1>=jzrq2)||(qsrq1>=qsrq2&&jzrq1<=jzrq2)){
//							alert("时间不符合要求，请检查同一单位同一煤种下的日期是否重叠包含")
//							return
//						}
					}
				}
			}
		}
		$http.post('hetgl/caigddpp/saveCaigddpp',{info:angular.toJson($scope.datalist)}). success(function(data) {
			alert("保存成功！！！");
			$http.post('hetgl/caigddb/getCaigdppList',{diancid :$scope.search.diancid,meikid:$scope.search.meikid,pinzid:$scope.search.pinzid}).success(function(data) {
				$scope.datalist=data;
			});
		});
	}
	$scope.deleted = function(id){
		$http.post('hetgl/caigddpp/deleteCaigddpp',{id:id}). success(function(data) {
			$http.post('hetgl/caigddpp/getCaigdppList',{diancid :$scope.search.diancid,meikid:$scope.search.meikid,pinzid:$scope.search.pinzid}).success(function(data) {
				$scope.datalist=data;
			});
		});
	}
	$(".datepicker0").live('focus', function() {
		$(this).datepicker({

			format : 'yyyy-mm-dd',
			minViewMode : 0,
			language : "zh-CN",
			autoclose : true
		});

	});
})
