gddlapp.controller('yijshCtrl', function($scope,$rootScope,$http,$log,$location) {
	//-----------------------------------初始化页面-----------------------------------------------------
	$scope.search = new Object();
	$scope.search.diancID=515; 
	$scope.search.zhuangt=0;
	$scope.search.condition="";
	$scope.search.page=1;
	$scope.pages=1;
	//------------------------------------加载数据-------------------------------------------------------- 
	//-------------------------------------------日期处理--------------------------------------------------
	var date = new Date();
	$scope.search.sDate=date.format('yyyy-MM')+'-01';
	$scope.search.eDate=date.format('yyyy-MM-dd');
	//--------------------------------------------分页--------------------------------------------------------
    $('#pagination_zc').remove();
    $("#pagination_box").append('<ul id="pagination_zc"></ul>');
    $scope.load = function() {
        //------------------根据条件获取总记录数--------------------//
        if($scope.search.zhuangt==1){//当为二级审核时去掉审核按钮
            $scope.dis=true;
        }else{
            $scope.dis=false;
        }
        $http({
            method : 'POST',
            params:{
                condition:angular.toJson($scope.search)
            },
            url:'shenh/getHuayd'
        }).success(function(data, status, headers, config) {
            $scope.list=data.list;
            $scope.pages=data.pages;
            $scope.count=data.count;
            $('#pagination_zc').remove();
            $("#pagination_box").append('<ul id="pagination_zc"></ul>');
            $("#pagination_zc").twbsPagination({
                first : '首页',
                prev : '前一页',
                next : '下一页',
                last : '尾页',
                totalPages :$scope.pages,
                visiblePages :5,
                onPageClick : function(event, page) {
                    $scope.search.page=page;	//当前页码
                    $http.post('shenh/getHuayd?condition='+angular.toJson($scope.search)).success(function(data, status, headers, config) {
                        $scope.list=data.list;
                        $scope.pages=data.pages;
                        $scope.count=data.count;
                    });
                }
            });
        });
    }
    $scope.load();
    //------------------------------------------------------------------------------------------------------------------
	$scope.shenhtg = function(target,id,zhuangt) {	
			// 更改当前审核状态
			$http.post('shenh/shenhHuaysj?id='+id+'&zhuangt='+zhuangt).
			success(function(data, status, headers, config) {
				alert("审核成功");
				$scope.load();				
			});
	}
	$scope.huit = function(huayd) {
		//查出月结单编号
		$http.post('shenh/getJiesxxList',{huaybm:huayd.HUAYBM}).success(function(data) {
			if(data[0].YUEJSDBM==undefined){
				$http.post('jiesgl/rijssc/delRijsd', {//删除日结算单
					list : angular.toJson(data)
				}).success(function(data) {
					$http.post('shenh/huitHuaysj?id='+huayd.ID+'&zhuangt='+huayd.ZHUANGT).success(function(data) {//删除化验单
						alert("回退成功!");
						$scope.load();
					}).error(function (data) {
						alert("回退失败!");
					});
				}).error(function (data) {
					alert("回退失败!");
				});
			}else {
				alert("已生成月结算单，不允许回退，结算单编号:" + data[0].YUEJSDBM)
			}
		});
	}
})
.controller('erjshCtrl', function($scope,$rootScope,$http,$log,$location) {
	//-----------------------------------初始化页面---------------------------------------------
	$scope.search = new Object();
	$scope.search.diancID=515;
	$scope.search.zhuangt=1;
	$scope.search.condition="";
	$scope.search.page=1;
	$scope.pages=1;
	//----------------------------------------------------------------------------------------
	//-------------------------------------------日期处理--------------------------------------------------
	var date = new Date();
	var year = date.getFullYear(); 
	var month = date.getMonth()+1<10?'0'+(date.getMonth()+1):date.getMonth()+1;
	var day = date.getDate()<10?'0'+date.getDate():date.getDate();

	$scope.search.sDate=year+'-'+month+'-01';
	$scope.search.eDate=year+'-'+month+'-'+day;
	//----------------------------------------------------------------------------------------------------
	//------------------------------------加载数据--------------------------------------------------------
	$scope.load = function() {
		//------------------根据条件获取总记录数--------------------//
		$http({
			method : 'POST',
			params:{
				condition:angular.toJson($scope.search)
			},
			url:'shenh/getHuayd'
		}).success(function(data, status, headers, config) {
			$scope.list=data.list;
			$scope.pages=data.pages;
			$('#pagination_zc').remove();
			$("#pagination_box").append('<ul id="pagination_zc"></ul>');
			$("#pagination_zc").twbsPagination({
				first : '首页',
				prev : '前一页',
				next : '下一页',
				last : '尾页',
				totalPages :$scope.pages,
				visiblePages :5,
				onPageClick : function(event, page) {	
					$scope.search.page=page;	
					$http.post('shenh/getHuayd?condition='+angular.toJson($scope.search)).success(function(data, status, headers, config) {
						$scope.list=data.list;
						$scope.pages=data.pages;
					});
				}
			});
		});
	}
	$scope.load();
	$scope.shenhtg = function(target,id,zhuangt) {	
			// 更改当前审核状态
			$http.post('shenh/shenhHuaysj?id='+id+'&zhuangt='+zhuangt).
			success(function(data, status, headers, config) {
				alert("审核成功");
				$scope.load();				
			});
	}
}).controller('huayzlrCtrl', function($scope,$rootScope,$http,$log,$location) {
	$scope.search = new Object();
	$scope.array = new Array();
	$scope.search.DIANCXXB_ID = 515;
	//------------------------------------------日期定义----------------------------------------------------
	var d = new Date();
	var vYear = d.getFullYear();
	var vMon = d.getMonth() + 1;
	var vDate = d.getDate();
	$scope.search.riq = vYear + "-"+ (vMon < 10 ? "0" + vMon : vMon) + "-"+ (vDate < 10 ? "0" + vDate : vDate);
/*******************************************************************************
 * 加载数据***************************************************
 * 向后台发送请求查询班组信息以jsonArray形式返回
 */
	$scope.load = function() {
		$http.post('shenh/getHuaydz?condition='+angular.toJson($scope.search)).success(function(data) {
			$scope.list = data;
			for(var i=0;i<$scope.list.length;i++){
				$scope.list[i].DANJLX='入厂化验';
			}
		});
	}

	$scope.load();
	$scope.empty = function(){}

	$scope.save = function() {
		//非空检查
		//保存按钮置灰
		$("#save").removeClass("btn-success");
		$("#save").attr('disabled', true);
		$http.post('shenh/update', {
			data : angular.toJson($scope.list)
		}).success(function(data) {
			alert("提交成功");
			$scope.load();
			$("#save").addClass("btn-success");
			$("#save").attr('disabled', false);
		}).error(function(data){
			alert("提交失败");
		});

	}
	
	$("#datepicker").datepicker({
		format : 'yyyy-mm-dd',
		minViewMode : 0,
		language : "zh-CN",
		autoclose : true
	});
}).controller('erjshhtCtrl', function($scope,$rootScope,$http,$log,$location) {
	//-----------------------------------初始化页面---------------------------------------------
	$scope.search = new Object();
	$scope.search.diancID=515;
	$scope.search.zhuangt=2;
	$scope.search.condition="";
	$scope.search.page=1;
	$scope.pages=1;
	//----------------------------------------------------------------------------------------
	//-------------------------------------------日期处理--------------------------------------------------
	var date = new Date();
	$scope.search.sDate=date.format('yyyy-MM')+'-01';
	$scope.search.eDate=date.format('yyyy-MM-dd');
	//----------------------------------------------------------------------------------------------------
	//------------------------------------加载数据--------------------------------------------------------
	$scope.load = function() {
		//------------------根据条件获取总记录数--------------------//
		$http({
			method : 'POST',
			params:{
				condition:angular.toJson($scope.search)
			},
			url:'shenh/getHuayd'
		}).success(function(data, status, headers, config) {
			$scope.list=data.list;
			$scope.pages=data.pages;
			$('#pagination_zc').remove();
			$("#pagination_box").append('<ul id="pagination_zc"></ul>');
			$("#pagination_zc").twbsPagination({
				first : '首页',
				prev : '前一页',
				next : '下一页',
				last : '尾页',
				totalPages :$scope.pages,
				visiblePages :5,
				onPageClick : function(event, page) {	
					$scope.search.page=page;	
					$http.post('shenh/getHuayd?condition='+angular.toJson($scope.search)).success(function(data, status, headers, config) {
						$scope.list=data.list;
						$scope.pages=data.pages;
					});
				}
			});
		});
	};
	$scope.load();
	$scope.huit = function(huayd) {
		//查出月结单编号
		$http.post('shenh/getJiesxxList',{huaybm:huayd.HUAYBM}).success(function(data) {
			if(data[0].YUEJSDBM==undefined){
				$http.post('jiesgl/rijssc/delRijsd', {//删除日结算单
					list : angular.toJson(data)
				}).success(function(data) {
					$http.post('shenh/huitHuaysj?id='+huayd.ID+'&zhuangt='+huayd.ZHUANGT).success(function(data) {//删除化验单
						alert("回退成功!");
						$scope.load();
					}).error(function (data) {
						alert("回退失败!");
					});
				}).error(function (data) {
					alert("回退失败!");
				});
			}else {
				alert("已生成月结算单，不允许回退，结算单编号:" + data[0].YUEJSDBM)
			}
		});
	}
});