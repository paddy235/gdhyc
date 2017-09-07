gddlapp.controller('rucbmdjCtrl', function ($scope, $rootScope, $http, $log, $location) {
    //由于数据页面与月报上报页面的名称无法完全对应，因此设置页面ID来判断月报中上报的页面，请勿更换月报的信息顺序和该页面的顺序
    var ymId = 4;
    var date = new Date();
    date.setMonth(date.getMonth() - 1);
    $scope.search = {
        riq: date.format('yyyy-MM'),
        diancid: 515
    };

    // $http.post('yuebgl/yuebwh/rucbmdj/getAll').
    //     success(function(data, status, headers, config) {
    //     	$scope.rucbmdjList = data.data;
    // });

//	$http.post('yuebgl/yuebwh/rucbmdj/check').
//	    success(function(data, status, headers, config) {
//	    	if(data[0]==0){
//	    		$('#createBtn').hide();
//	    		$('#delBtn').hide();
//	    		$('#saveBtn').hide();
//	    	}
//	});
    $scope.searchData = function () {
        $http.post('yuebgl/yuebwh/rucbmdj/getRucbmdjList', {search: angular.toJson($scope.search)})
            .success(function (data) {
                $scope.rucbmdjList = data;
//             $http.post('yuebgl/yuebsc/getYueb', {riq: riq, dianc: dianc}).success(function (data) {
//                 //如果以后页面名称能对应上后即可取消掉页面id，循环data【object】中的MINGC是否等于页面名称即可
// //	    			for(var i=0;i<data.length;i++){
// //	    				data[i].MINGC
// //	    			}
//                 if (data[ymId].ZHUANGT == 1) {
//                     $(".table td input").prop("disabled", true);
//                     $(".table-toolbar button[id$='Btn']").prop("disabled", true);
//                 } else {
//                     $(".table-toolbar button[id$='Btn']").prop("disabled", false);
//                     $(".table input").prop("disabled", false);
//                 }
//             });
            }).error(function (data) {
            alert("查询出错!");
        });


//		$http.post('yuebgl/yuebwh/rucbmdj/check?riq='+riq+'&dianc='+dianc).
//		    success(function(data, status, headers, config) {
//		    	if(data[0]==0){
//		    		$('#createBtn').hide();
//		    		$('#delBtn').hide();
//		    		$('#saveBtn').hide();
//		    	}else{
//		    		$('#createBtn').show();
//		    		$('#delBtn').show();
//		    		$('#saveBtn').show();
//		    	}
//		});
    };
    $scope.searchData();

    $scope.createData_Win = function () {
        $('#myModal_Create').modal('show');
    };
    //
    // $scope.createData = function(){
    // 	var riq = $scope.search.riq;
    // 	var dianc = $scope.search.diancid;
    // 	$http.post('yuebgl/yuebwh/rucbmdj/createData',{riq:riq,dianc:dianc}).
    // 		success(function(data, status, headers, config) {
    // 		    if(data[0]==1){
    // 		    	$('#myModal_Create').modal('hide');
    // 		    	alert("生成数据成功！");
    // 		    	$http.post('yuebgl/yuebwh/rucbmdj/getAll?riq='+riq+'&dianc='+dianc).
    // 		    		success(function(data, status, headers, config) {
    // 		    			$scope.rucbmdjList = data.data;
    // 		    	});
    // 		    }else{
    // 		    	$('#myModal_Create').modal('hide');
    // 		    	alert("生成数据失败！");
    // 		    }
    // 	});
    // 	$('#myModal_Create').modal('hide');
    // }
    //
    // $scope.delData_Win = function(){
    // 	$('#myModal_Del').modal('show');
    // }

    $scope.delData = function () {
        $http.post('yuebgl/yuebwh/rucbmdj/delData', {search: angular.toJson($scope.search)})
            .success(function (data) {
                alert("数据删除成功！");
                $scope.searchData();
            }).error(function (data) {
            alert("数据删除失败！");
        });
    }
    $scope.createData = function () {
        $http.post('yuebgl/yuebwh/rucbmdj/createData', {search: angular.toJson($scope.search)})
            .success(function (data) {
                alert("生成数据成功!");
                $scope.searchData();
            })
            .error(function (data) {
                alert("生成数据失败!");
            })
    };
    $scope.saveData = function () {
        $http.post('yuebgl/yuebwh/rucbmdj/saveData', {
            rucbmdjList: angular.toJson($scope.rucbmdjList)
        }).success(function (data) {
            alert("保存成功!");
            $scope.searchData();
        }).error(function (data) {
            alert("保存失败!");
        });
    };

    $scope.printTable = function () {
        $("#example").jqprint();
    }
});