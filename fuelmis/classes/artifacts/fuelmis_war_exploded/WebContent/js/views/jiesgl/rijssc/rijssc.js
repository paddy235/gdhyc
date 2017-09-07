gddlapp.controller('RijsscCtrl',['$scope','$http',function ($scope,$http) {
    $scope.RijsscTitle = '日结算删除';


    //--------------------------------------日期定义--------------------------------------------------------------------------
    var begin = new Date();
    var sDate = begin.format("yyyy-MM" + "-01");
    var end = new Date();
    new Date(end.setDate((new Date().getDate())));
    var eDate = end.format("yyyy-MM-dd");

    /*   $scope.myHead=["供应商", "品种", "车数", "到货日期", "三方数量", "净重", "采样编码", "化验编码", "化验状态", "QNET_AR",
     "入库单编号", "入库金额", "编号", "开始时间", "结算时间", "日结算编号", "日结算数量", "日结算热值", "日结算硫分",
     "日结算含税金额", "日结算不含税金额", "日结算含税单价", "日结算热值增扣", "日结算硫分增扣", "日结算公式", "日结算合同编号",
     "月结算编号", "结算单价"];*/
    $scope.search = {
        sDate: sDate,
        eDate: eDate,
        gongys: -1
    };
    $scope.isDisplay = false;
    //------------------------------------------初始化table插件------------------------------------------------------------------
    $scope.columnDefs = [{
        "sClass": "center",
        "mRender": function (oObj, sVal) {
            return '<input  type="checkbox" id="' + oObj + '" name="checkId"  />';
        },
        "bSortable": false,
        "aTargets": [0]

    }];

    //---------------------------------------------------------------------------------------------------------------------------
    $scope.getRijscx = function () {
        $http.post('jiesgl/rijssc/getRijscx', {search: angular.toJson($scope.search)}).success(function (data) {
            $scope.myArray = data;
        }).error(function (data) {
            alert("查询数据失败！");
        });
    };
    $scope.getRijscx();
    $scope.array = [];

    /*    $scope.delRijssc = function () {
     if ($("#example input[type=checkbox]:checked").length < 1) {
     alert("请选择数据！");
     } else {
     $scope.array = [];
     $("#example input[type=checkbox]:checked").each(function () {
     $scope.array.push(this.id)
     });

     $http.post('jiesgl/rijssc/delRijssc', {id: angular.toJson($scope.array)}).success(function (data) {
     alert("日结算删除成功!");
     $scope.getRijscx();
     }).error(function (data) {
     alert("日结算删除失败");
     });
     }
     };*/


    /*    $scope.delRuksc = function () {
     var checks=$("#example input[type=checkbox]:checked");
     if (checks.length < 1) {
     alert("请选择数据！");
     } else {
     $scope.array = [];
     checks.each(function () {
     $scope.array.push(this.id);
     });
     $http.post('jiesgl/rijssc/delRuksc', {id: angular.toJson($scope.array)}).success(function (data) {
     alert("入库单删除成功!");
     $scope.getRijscx();
     }).error(function () {
     alert("入库单删除失败!");
     });
     }
     };*/
    $scope.del = function () {
        var checks = $("#example input[type=checkbox]:checked");
        if (checks.length < 1) {
            alert("请选择数据！");
        } else {
            $scope.array = [];
            checks.each(function () {
                var yuejsdbh = $(this).parents('td').siblings()[26].innerText;
                if (yuejsdbh != "") {
                    alert("月结算已经生成无法删除!");
                } else {
                    $scope.array.push(this.id);
                }
            });
            if ($scope.array.length == 0)return;
            $http.post('jiesgl/rijssc/del', {id: angular.toJson($scope.array)}).success(function (data) {
                alert("删除成功!");
                $scope.getRijscx();
            }).error(function () {
                alert("删除失败!");
            });
        }
    };
    $scope.ruk = function () {
        var checks = $("#example input[type=checkbox]:checked");
         if (checks.length < 1) {
         alert("请选择数据！");
         } else {
         $scope.array = [];
         checks.each(function () {
         $scope.array.push(this.id);
         });
         $scope.isDisplay = true;
         $http.post('jiesgl/rijssc/ruk', {id: angular.toJson($scope.array)}).success(function (data) {
         alert("入库成功！");
         $scope.getRijscx();
         $scope.isDisplay = false;
         }).error(function (data) {
         alert("入库失败！");
         $scope.isDisplay = false;
         })
         }
    };
    $scope.rijs = function () {
        var checks = $("#example input[type=checkbox]:checked");
        if (checks.length < 1) {
            alert("请选择数据！");
        } else {
            $scope.array = [];
            checks.each(function () {
                $scope.array.push(this.id);
            });
            $scope.isDisplay = true;
            $http.post('jiesgl/rijssc/rijs', {id: angular.toJson($scope.array)}).success(function (data) {
                $scope.isDisplay = false;
                alert("日结算成功！");
                $scope.getRijscx();

            }).error(function (data) {
                $scope.isDisplay = false;
                alert("日结算失败！");

            })
        }
    }
}] );