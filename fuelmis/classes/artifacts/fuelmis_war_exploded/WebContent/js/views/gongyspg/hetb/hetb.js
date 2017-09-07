gddlapp.controller('hetbCtrl', function($scope,$rootScope,$http,$log,$location) {
    // $scope.hetbTitle='合同维护';
    //
    // $scope.search = [];
    // $scope.search.strdate = timeStamp2String(2);
    // $scope.search.enddate = timeStamp2String(1);
    // $scope.search.gongysb_id = -1;
    var date=new Date();
    $scope.search={date:date.format("yyyy-MM"),gongysb_id:-1};
    // function timeStamp2String(type){
    //     var datetime = new Date();
    //     var year = datetime.getFullYear();
    //     var month = datetime.getMonth() + 1 < 10 ? "0" + (datetime.getMonth() + 1) : datetime.getMonth() + 1;
    //     var date = datetime.getDate() < 10 ? "0" + datetime.getDate() : datetime.getDate();
    //     var hour = datetime.getHours()< 10 ? "0" + datetime.getHours() : datetime.getHours();
    //     var minute = datetime.getMinutes()< 10 ? "0" + datetime.getMinutes() : datetime.getMinutes();
    //     var second = datetime.getSeconds()< 10 ? "0" + datetime.getSeconds() : datetime.getSeconds();
    //     if(type == 1){
    //         return year + "-" + month + "-" + date;
    //     }else{
    //         return year + "-" + month + "-"+"01"
    //     }
    //
    // }

    $scope.addHetb=function(){
        var flag = "Add";
        $location.path('/addHetb/'+flag);
    };

    $scope.editHetb=function(){
        if($("#example input[type=checkbox]:checked").length<1){
            alert("请选择要修改的行！");
        }else{
            $("#example input[type=checkbox]").each(function(){
                if(this.checked){
                    var flag = "Update";
                    $location.path('/addHetb/'+flag+"/"+$(this).attr("id"));
                }
            });
        }
    };

    $scope.delHetb=function(){
        var $h=$("#example input[type=checkbox]:checked");
        if($h.length<1){
            alert("请选择要删除的行！")
        }else{
            $('#myModal_Del').modal('show');

        }
    };

    $scope.columnDefs = [{
        "sClass": "center",
        "mRender": function (oObj, sVal, row) {
            return '<input  type="checkbox" id="' + oObj + '" name="checkId"   />';
        },
        "bSortable": false,
        "aTargets": [0]
    }];
    $scope.refresh=function(){
        // oTable.fnReloadAjax('gongyspg/hetb/getHetbList?sdate='+$scope.search.strdate+'&edate='+$scope.search.enddate);
        $http.post('gongyspg/hetb/getHetbList',{search:angular.toJson($scope.search)})
            .success(function (data) {
                $scope.hetArray=data;
            }).error(function (data) {
            alert("数据查询失败!");
        });
    };
    $scope.refresh();
    $scope.deleteHetb=function(){
        var $h=$("#example input[type=checkbox]:checked");
        $http.post('gongyspg/hetb/delHetb',{id:($h[0]).id}).success(function (data) {
            $('#myModal_Del').modal('hide');
            alert("删除成功!");
            $scope.refresh();
        }).error(function (data) {
            alert("删除失败!");
        });
    };

}).controller('hetbAddCtrl', function($scope,$rootScope,$http,$location,$routeParams) {
        $scope.title='';
        $scope.hetb={zhuangt:1};
        $http.post('common/getComboPingffa').
        success(function(data, status, headers, config) {
            $scope.pingffaList = data;
        });

        if($routeParams.flag=="Add"){
            $scope.title='新增合同信息';
        }else if($routeParams.flag=="Update"){
            $scope.title='修改合同信息';
            $http.post('gongyspg/hetb/editHetb',{id:$routeParams.id}).
            success(function(data, status, headers, config) {
                if(data){
                    $scope.hetb=data;
                }
            });
        }
        $http.post('gongyspg/hetb/getZhibList').success(function (data) {
            $scope.zhibList=data;
        }).error(function (data) {
            alert("查询指标列表错误!")
        });
        $scope.deleteZhib=function (i) {
            if($scope.hetb.zhilList[i].ID!=undefined){
                $http.post('gongyspg/hetb/delZhib',{id:$scope.hetb.zhilList[i].ID}).success(function (data) {
                    alert("删除成功!");
                }).error(function (data) {
                    alert("删除失败!");
                });
            }
            $scope.hetb.zhilList.splice(i, 1);
        };
        $scope.addZhib=function () {
            if($scope.hetb.zhilList==undefined){
                $scope.hetb.zhilList=[];
            }
            $scope.hetb.zhilList.push({});
        };
        $scope.saveHetb=function(){
            if($scope.hetb.id==null){
                $http.post('gongyspg/hetb/addHetb',{info:angular.toJson($scope.hetb)}).
                success(function(data, status, headers, config) {
                    alert("新增合同信息成功！");
                    $location.path('/gongyspg/hetb');
                }).error(function (data) {
                    alert("新增合同信息失败！");
                });
            }else{
                $http.post('gongyspg/hetb/updateHetb',{info:angular.toJson($scope.hetb),star:$scope.hetb.star,qnet_ar:$scope.hetb.qnet_ar}).
                success(function(data, status, headers, config) {
                    if(data[0]==1){
                        alert("修改合同信息成功！");
                        $location.path('/gongyspg/hetb');
                    }else{
                        alert("修改合同信息失败！");
                        $location.path('/gongyspg/hetb');
                    }
                });
            }
        };

        $scope.cancel=function(){
            $location.path('/gongyspg/hetb');
        };
    });
