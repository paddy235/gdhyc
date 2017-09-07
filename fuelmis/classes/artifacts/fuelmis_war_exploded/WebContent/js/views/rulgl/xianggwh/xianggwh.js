gddlapp.controller('meihyCtrl', function ($scope, $rootScope, $routeParams, $http, $location) {
        $scope.search = new Object();
        $scope.search.meikxxbid = -1;
        $scope.array = new Array();
        $scope.delArray = new Array();
        $scope.DIANCXXB_ID = 515;
        // ------------------------------------------日期定义-------------------------------------------
        function addDate(dd, dadd) {
            var a = new Date(dd)
            a = a.valueOf()
            a = a + dadd * 24 * 60 * 60 * 1000
            a = new Date(a)
            return a;
        }

        //抓取现在日期
        var now = new Date();
        var years = now.getFullYear();
        var months = now.getMonth() + 1;
        var days = now.getDate();
        var hours = now.getHours();
        //抓取前一天日期
        NextNow = addDate(years + "-" + months + "-" + days, -1);
        vYear = NextNow.getFullYear();
        vMon = NextNow.getMonth() + 1;
        vDate = NextNow.getDate();
        $scope.riq = vYear + "-" + (vMon < 10 ? "0" + vMon : vMon) + "-" + (vDate < 10 ? "0" + vDate : vDate);
        //--------------------------------------------------------------------------------------------------------
        /*******************************************************************
         * 加载数据***************************************************
         * 向后台发送请求查询班组信息以jsonArray形式返回
         */
        $scope.load = function () {
            $http.post('rulgl/xianggwh/getMeiHy', {
                riq: $scope.riq,
                diancxxb_id: $scope.DIANCXXB_ID
            }).success(function (data) {
                $scope.list = data;
                $scope.delArray=[]
            });
        }

        $scope.load();
        $scope.selectriq = function () {
            $scope.load();
        }
        $scope.add = function () {
            var obj = new Object();
            obj = {
                DIANCXXB_ID: $scope.DIANCXXB_ID,
                HAOYRQ: $scope.riq,
                GONGRHY: 0,
                QITY: 0,
                FEISCY: 0
            }
            $scope.list.meihylist.push(obj);
        }
        Array.prototype.remove = function (dx) {
            if (isNaN(dx) || dx > this.length) {
                return false;
            }
            this.splice(dx, 1);
        }
        $scope.del = function (target) {
            var $td = $(target).parent();
            id = $td.attr("id");
            var meihy=$scope.list.meihylist[id]
            if (meihy) {
                $scope.delArray.push(meihy.ID);
            }
            $scope.list.meihylist.remove(id);
        }

        // 查重
        $scope.save = function () {
            $http.post('rulgl/xianggwh/delMeiHy', {
                ids: angular.toJson($scope.delArray)
            }).success(function (data) {
                $http.post('rulgl/xianggwh/updateMeihy', {
                    data: angular.toJson($scope.list.meihylist)
                }).success(function (data) {
                    alert("保存成功");
                    $scope.load();
                });
            });
        }
        $(".datepicker0").live('focus', function () {
            $(this).datepicker({
                format: 'yyyy-mm-dd',
                minViewMode: 0,
                language: "zh-CN",
                autoclose: true

            });

        });
        $("#datepicker").datepicker({
            format: 'yyyy-mm-dd',
            minViewMode: 0,
            language: "zh-CN",
            autoclose: true
        });


    })
    .controller('rulmppCtrl', function ($scope, $rootScope, $routeParams, $http, $location) {
        $scope.search = new Object();
        $scope.search.meikxxbid = -1;
        $scope.array = new Array();
        $scope.DIANCXXB_ID = 515;
        // ------------------------------------------日期定义-------------------------------------------
        // ------------------------------------------日期定义-------------------------------------------
        function addDate(dd, dadd) {
            var a = new Date(dd)
            a = a.valueOf()
            a = a + dadd * 24 * 60 * 60 * 1000
            a = new Date(a)
            return a;
        }

        //抓取现在日期
        var now = new Date();
        var years = now.getFullYear();
        var months = now.getMonth() + 1;
        var days = now.getDate();
        var hours = now.getHours();
        //抓取前一天日期
        NextNow = addDate(years + "-" + months + "-" + days, -1);
        vYear = NextNow.getFullYear();
        vMon = NextNow.getMonth() + 1;
        vDate = NextNow.getDate();
        $scope.riq = vYear + "-" + (vMon < 10 ? "0" + vMon : vMon) + "-" + (vDate < 10 ? "0" + vDate : vDate);
        //--------------------------------------------------------------------------------------------------------
        /*******************************************************************
         * 加载数据***************************************************
         * 向后台发送请求查询班组信息以jsonArray形式返回
         */
        $scope.load = function () {
            $http.post('rulgl/xianggwh/getRulmpp', {
                riq: $scope.riq,
                diancxxbID: $scope.DIANCXXB_ID
            }).success(function (data) {
                $scope.list = data;
                $scope.FADHYHJ = 0;
                $scope.GONGRHYHJ = 0;
                $scope.QITYHJ = 0;
                $scope.FEISCYHJ = 0;
                for (var i = 0; i < $scope.list.haoylist.length; i++) {
                    $scope.FADHYHJ += $scope.list.haoylist[i].FADHY;
                    $scope.GONGRHYHJ += $scope.list.haoylist[i].GONGRHY;
                    $scope.QITYHJ += $scope.list.haoylist[i].QITY;
                    $scope.FEISCYHJ += $scope.list.haoylist[i].FEISCY;
                }
                $scope.array = [];
            });
        }

        $scope.load();
        $scope.selectriq = function () {
            $scope.load();
        }
        $scope.add = function () {
            $scope.max = $scope.max + 1;
            var obj = new Object();
            obj = {
                ZHUANGT: "停用",
                XUH: $scope.max
            }
            $scope.array.push(obj);
        }
        $scope.del = function () {
            $scope.max = $scope.max + 1;
            var obj = new Object();
            obj = {
                ZHUANGT: "停用",
                XUH: $scope.max
            }
            $scope.array.push(obj);
        }

        // 查重
        $scope.save = function () {

            $http.post('rulgl/xianggwh/updateHuaybm', {
                data: angular.toJson($scope.list.haoylist)
            }).success(function (data) {
                alert("保存成功");
                $scope.load();
            });

//		for ( var i = 0; i < $scope.array.length; i++) {
//			$http.post('baseSet/insertBanz', {
//				data : angular.toJson($scope.array[i])
//			}).success(function(data) {
//				// alert("保存成功");
//
//				$scope.load();
//
//			});
//		}


        }
        $(".datepicker0").live('focus', function () {
            $(this).datepicker({

                format: 'yyyy-mm-dd',
                minViewMode: 0,
                language: "zh-CN",
                autoclose: true

            });

        });
        $("#datepicker").datepicker({
            format: 'yyyy-mm-dd',
            minViewMode: 0,
            language: "zh-CN",
            autoclose: true
        });
    }).controller('meihysisCtrl', function ($scope, $rootScope, $routeParams, $http, $location) {
    $scope.search = new Object();
    $scope.search.meikxxbid = -1;
    $scope.array = new Array();
    $scope.DIANCXXB_ID = 515;
    // ------------------------------------------日期定义-------------------------------------------
    var d = new Date();
    var vYear = d.getFullYear();
    var vMon = d.getMonth() + 1;
    var vDate = d.getDate();

    $scope.riq = vYear + "-" + (vMon < 10 ? "0" + vMon : vMon) + "-"
        + (vDate < 10 ? "0" + vDate : vDate);
    /*******************************************************************
     * 加载数据***************************************************
     * 向后台发送请求查询班组信息以jsonArray形式返回
     */
    
    $scope.load=function(){
        $http.post('rulgl/xianggwh/getMeiHysis', {riq: $scope.riq}).success(function (data) {
            $scope.list = data;
        })
    }
    $scope.del = function () {
    }

    // 查重
    $scope.save = function () {
        
    }

    $(".datepicker0").live('focus', function () {
        $(this).datepicker({

            format: 'yyyy-mm',
            minViewMode: 1,
            viewMode: 2,
            language: "zh-CN",
            autoclose: true

        });

    });
    $("#datepicker").datepicker({
        format: 'yyyy-mm-dd',
        minViewMode: 0,
        language: "zh-CN",
        autoclose: true
    });

    // $scope.list

    $scope.getMeihyAll = function () {
        $http.post('rulgl/xianggwh/getMeihyAll', {Date: $scope.riq}).success(function (data) {
            $scope.list = data;
        })
    }

    $scope.MeihyAdd = function () {
        $http.post('rulgl/xianggwh/MeihyAdd', {data: angular.toJson($scope.list)}).success(function (data) {
            alert("保存成功！");
        })
    }

    $scope.DelMeihy = function () {
        if ($("#meihy input[type=checkbox]:checked").length < 1) {
            alert("请选择要删除的行");
        } else {
            $('#myModal_Del').modal('show');
        }
    }

    $scope.DelMeihysis = function () {
        $("#meihy input[type=checkbox]").each(function () {
            if (this.checked) {
                $http.post("rulgl/xianggwh/DelMeihy", {id: $(this).attr("id")}, function (data) {
                    $("#myModal_Del").modal('hide');
                    alert("删除成功！");
                    $("input[type=checkbox]:checked").prop("checked", false);
                    $scope.load();

                })
            }
        })
    }

});

