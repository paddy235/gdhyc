gddlapp.controller('caigddbCtrl', function ($scope, $rootScope, $http, $log, $location) {
    $scope.caigddbTitle = '合同发货订单维护';
    $scope.search = new Object();
    $scope.search.diancid = 515;
    $scope.search.gongys = -1;
    
    //------------------------------------------------------------------------------------------------------------------
    var datetime = new Date();
    $scope.search.strdate = datetime.format('yyyy-MM')+'-01';
    $scope.search.enddate = datetime.format('yyyy-MM-dd');
//----------------------------------------------------------------------------------------------------------------------
    $scope.addCaigddb = function () {
        var flag = "Add";
        $location.path('/addCaigddb/' + flag);
    }

    $scope.editCaigddb = function () {
        if ($("#example input[type=checkbox]:checked").length < 1) {
            alert("请选择要修改的行！");
        } else {
            $("#example input[type=checkbox]").each(function () {
                if (this.checked) {
                    var flag = "Update";
                    $location.path('/addCaigddb/' + flag + "/" + $(this).attr("id"));
                }
            });
        }
    }

    $scope.showCaigddb = function () {
        if ($("#example input[type=checkbox]:checked").length < 1) {
            alert("请选择要查看的行！");
        } else {
            $("#example input[type=checkbox]").each(function () {
                if (this.checked) {
                    $location.path("/showCaigddb/" + $(this).attr("id"));
                }
            });
        }
    }


    $scope.refresh = function () {
        var diancid = $scope.search.diancid;
        var strdate = $scope.search.strdate;
        var enddate = $scope.search.enddate;
        var gongys = $scope.search.gongys;
        oTable.fnReloadAjax('hetgl/caigddb/searchCaigddbList/?diancid=' + diancid + '&strdate=' + strdate + '&enddate=' + enddate + '&gongys=' + gongys);
    }


    $scope.delCaigddb = function () {
        if ($("#example input[type=checkbox]:checked").length < 1) {
            alert("请选择要删除的行！");
        } else {
            $('#myModal_Del').modal('show');
        }
    }

    $scope.getJiag = function () {
        var id = "123";
        $http.post('hetgl/caigddb/testJij', {id: id}).success(function (data, status, headers, config) {
            if (data) {

            }
        });
    }

})
    .controller('caigddbShowCtrl', function ($scope, $rootScope, $http, $location, $routeParams) {
        $http.post('common/getComboFeiyxm').success(function (data, status, headers, config) {
            $scope.FeiyxmList = data;
        });
        $http.post('common/getComboChezXxb').success(function (data, status, headers, config) {
            $scope.chezList = data;
        });
        $scope.caigddbTitle = '采购订单发货信息';
        $http.post('hetgl/caigddb/editCaigddb', {id: $routeParams.id}).success(function (data, status, headers, config) {
            if (data) {
                $scope.caigddb = data.caigddb;
                $scope.caigddbsubList = data.ranlcginfo;
                $scope.caigyunfsubList = data.caigyunfsinfo;
                if ($scope.caigddb.caigddlx == '运费订单') {
                    document.getElementById("table1").style.display = "none";
                    document.getElementById("table2").style.display = "";
                }
            }
        });

        $scope.cancel = function () {
            $location.path('/hetgl/caigddb');
        };
    })
    .controller('caigddbAddCtrl', function ($scope, $rootScope, $http, $location, $routeParams) {
        $scope.caigddbTitle = '';
        $scope.search = new Object();
        $scope.caigddb = new Object();
        var datetime = new Date();
        $scope.search.year = datetime.format('yyyy');
        $scope.caigddb.bianh = 'GDHD-RM-'+$scope.search.year+'-';
        $scope.caigddb.caigddlx = '燃煤采购';
        $scope.caigddb.diancxxb_id = 910;
        $scope.caigddbsubList = new Array();
        $scope.caigyunfsubList = new Array();
        $http.post('hetgl/caigddb/getJiagtype').success(function (data) {
            $scope.jiagtypeList = data;
        });
        $http.post('common/getComboFeiyxm').success(function (data, status, headers, config) {
            $scope.FeiyxmList = data;
        });
        $http.post('common/getComboChezXxb').success(function (data, status, headers, config) {
            $scope.chezList = data;
        });
        $scope.typeselect = function () {
            if ($scope.caigddb.caigddlx == '燃煤采购') {
                document.getElementById("table1").style.display = "";
                document.getElementById("table2").style.display = "none";
            } else if ($scope.caigddb.caigddlx == '运费订单') {
                document.getElementById("table1").style.display = "none";
                document.getElementById("table2").style.display = "";
            }
        }
        $scope.addCaigddbsub = function () {
            var obj = new Object();
            obj.status = 0;
            obj.yunzf = 0;
            obj.id = 0;
            if ($scope.caigddb.caigddlx == '燃煤采购') {
                $scope.caigddbsubList.push(obj);
            } else if ($scope.caigddb.caigddlx == '运费订单') {
                $scope.caigyunfsubList.push(obj);
            }

        }

        $scope.deleteCaigyunfsub = function (yunf) {
            if (yunf.id != 0 && yunf.id != null && yunf.id != '' && yunf.id != undefined) {
                $http.post('hetgl/caigddb/delCaigddbyfsub', {id: yunf.id}).success(function (data, status, headers, config) {
                    alert("删除成功");
                });
            }
            $.each($scope.caigyunfsubList, function (i) {
                if (this == yunf) {
                    $scope.caigyunfsubList.splice(i, 1);
                    return false;
                }
            });
        }
        $scope.deleteCaigddbsub = function (fencsl) {
            if (fencsl.id != null && fencsl.id != '' && fencsl.id != 0) {
                $http.post('hetgl/caigddb/delCaigddbsub', {id: fencsl.id}).success(function (data, status, headers, config) {
                    alert('删除成功');
                });
            }
            $.each($scope.caigddbsubList, function (i) {
                if (this == fencsl) {
                    $scope.caigddbsubList.splice(i, 1);
                    return false;
                }
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
        $(".datepicker1").live('focus', function () {
            $(this).datepicker({

                format: 'yyyy-mm-dd',
                minViewMode: 0,
                language: "zh-CN",
                autoclose: true

            });

        });
        $scope.saveCaigddb = function () {
            if ($scope.caigddb.id == null && $scope.caigddb.id == undefined) {
                $http.post('hetgl/caigddb/addCaigddb', {
                    info: angular.toJson($scope.caigddb),
                    ranlcginfo: angular.toJson($scope.caigddbsubList),
                    caigyunfsinfo: angular.toJson($scope.caigyunfsubList)
                }).success(function (data, status, headers, config) {
                    console.log(data);
                    $scope.caigddb = data[0].info;
                    $scope.caigddbsubList = data[0].ranlcginfo;
                    $scope.caigyunfsubList = data[0].caigyunfsinfo;
                    if (data[0].info.id > 0) {
                        alert("新增合同发货订单信息成功！");
                    } else {
                        alert("新增合同发货订单信息失败！");
                    }
                });
            } else {
                if ($scope.caigddb.caigddlx == '燃煤采购') {
                    $scope.caigyunfsubList = new Array();
                } else if ($scope.caigddb.caigddlx == '运费订单') {
                    $scope.caigddbsubList = new Array();
                }
                $http.post('hetgl/caigddb/updates', {
                    info: angular.toJson($scope.caigddb),
                    ranlcginfo: angular.toJson($scope.caigddbsubList),
                    caigyunfsinfo: angular.toJson($scope.caigyunfsubList)
                }).success(function (data) {
                    $scope.caigddb = data[0].info;
                    $scope.caigddbsubList = data[0].ranlcginfo;
                    $scope.caigyunfsubList = data[0].caigyunfsinfo;
                    alert("更新成功");
                });
            }
        }
        if ($routeParams.flag == "Add") {
            $scope.caigddbTitle = '新增合同发货订单信息';
            $scope.caigddb.zhuangt = 1;
        } else if ($routeParams.flag == "Update") {
            $scope.caigddbTitle = '修改合同发货订单信息';
            $http.post('hetgl/caigddb/editCaigddb', {id: $routeParams.id}).success(function (data, status, headers, config) {
                if (data) {
                    $scope.caigddb = data.caigddb;
                    $scope.caigddbsubList = data.ranlcginfo;
                    $scope.caigyunfsubList = data.caigyunfsinfo;
                    if ($scope.caigddb.caigddlx == '运费订单') {
                        document.getElementById("table1").style.display = "none";
                        document.getElementById("table2").style.display = "";
                    }
                }
            });
        }
        $scope.fillRmData = function (index) {
            //var pingcj = isNaN(parseFloat($scope.caigddbsubList[index].pingcj))==true?0:parseFloat($scope.caigddbsubList[index].pingcj);
            var pingcj = parseFloat($scope.caigddbsubList[index].pingcj);
            $scope.caigddbsubList[index].pingcj_bhs = (pingcj / 1.17).toFixed(2);

            //var yunzf = isNaN(parseFloat($scope.caigddbsubList[index].yunzf))==true?0:parseFloat($scope.caigddbsubList[index].yunzf);
            var yunzf = parseFloat($scope.caigddbsubList[index].yunzf);
            $scope.caigddbsubList[index].yunzf_bhs = (yunzf / 1.11).toFixed(2);

            $scope.caigddbsubList[index].daocj = (yunzf + pingcj).toFixed(2);
            $scope.caigddbsubList[index].daocj_bhs = (parseFloat($scope.caigddbsubList[index].pingcj_bhs) + parseFloat($scope.caigddbsubList[index].yunzf_bhs)).toFixed(2);
        }
        $scope.fillRmData2 = function (index) {
            var pingcj_bhs = parseFloat($scope.caigddbsubList[index].pingcj_bhs);

            var yunzf_bhs = parseFloat($scope.caigddbsubList[index].yunzf_bhs);

            $scope.caigddbsubList[index].daocj_bhs = (pingcj_bhs + yunzf_bhs).toFixed(2);
        }
        $scope.fillYfData = function (index) {
            var hansyfdj = parseFloat($scope.caigyunfsubList[index].hansyfdj);

            var hanszfdj = parseFloat($scope.caigyunfsubList[index].hanszfdj);

            $scope.caigyunfsubList[index].buhsyfdj = (hansyfdj / 1.11).toFixed(2);
            $scope.caigyunfsubList[index].bhszfdj = (hanszfdj / 1.06).toFixed(2);
        }
//	$scope.getBianh = function(){
//		var dingdrq = $scope.caigddb.dingdrq;
//		$http.post('hetgl/caigddb/getBianh',{dingdrq:dingdrq}).
//	       success(function(data, status, headers, config) {
//	    	   if(data){
//	    		   $scope.caigddb.bianh = data.bianh;
//	    	   }
//	   });
//	}

        $scope.showKaohzbb = function () {
            $('#myModal').modal('show');
        }

        $scope.showPricSecheme = function (sub) {
            $location.path('/hetgl/jijfs/' + $scope.caigddb.id + '/' + sub.id);
        }


        $scope.cancel = function () {
            $location.path('/hetgl/caigddb');
        };

        $scope.addPricSecheme = function () {
            var obj = new Object();
            obj.status = 1;
            $scope.priceSechemeList.push(obj);
        }

        $scope.delPricSecheme = function (pricSecheme) {
            $.each($scope.priceSechemeList, function (i) {
                if (this == pricSecheme) {
                    $scope.priceSechemeList.splice(i, 1);
                    return false;
                }
            });
        }

        $scope.showRangeInfo = function (pricSecheme) {
            var modelId = pricSecheme.price_component_id;
            $.each($scope.priceComponetList1, function (i) {
                if (this.id == modelId) {
                    var modelUrl = "#" + this.url + "_Model";
                    $(modelUrl).modal('show');
                }
            });

        }


        //添加热值取值范围
        $scope.addQnetar1 = function () {
            $scope.qnetarList2 = new Array();
            var obj = new Object();
            obj.status = 1;
            $scope.qnetarList1.push(obj);
        }

        //删除热值取值范围
        $scope.delQnetar1 = function (qnetar) {
            $.each($scope.qnetarList1, function (i) {
                if (this == qnetar) {
                    $scope.qnetarList1.splice(i, 1);
                    return false;
                }
            });
        }


        //添加热值取值范围
        $scope.addQnetar2 = function () {
            $scope.qnetarList1 = new Array();
            var obj = new Object();
            obj.status = 1;
            $scope.qnetarList2.push(obj);
        }

        //删除热值取值范围
        $scope.delQnetar2 = function (qnetar) {
            $.each($scope.qnetarList2, function (i) {
                if (this == qnetar) {
                    $scope.qnetarList2.splice(i, 1);
                    return false;
                }
            });
        }

        //添加考核指标
        $scope.addKaohzbb = function () {
            var obj = new Object();
            obj.leix = '热值';
            $scope.kaohzbbList.push(obj);
        }

        //删除考核指标
        $scope.delKaohzbb = function (kaohzb) {
            $.each($scope.kaohzbbList, function (i) {
                if (this == kaohzb) {
                    $scope.kaohzbbList.splice(i, 1);
                    return false;
                }
            });
        };

        //添加硫分考核指标
        $scope.addKaohzbb1 = function () {
            var obj = new Object();
            obj.leix = '硫分';
            $scope.kaohzbbList1.push(obj);
        };

        //删除硫分考核指标
        $scope.delKaohzbb1 = function (kaohzb) {
            $.each($scope.kaohzbbList1, function (i) {
                if (this == kaohzb) {
                    $scope.kaohzbbList1.splice(i, 1);
                    return false;
                }
            });
        }

    })

    .controller('jijfsCtrl', function ($scope, $rootScope, $http, $location, $routeParams) {
        $scope.jijfsTitle = '合同发货订单计价方案';
        $scope.priceSechemeList = new Array();
        $scope.zihbList = new Array();
        /**
         * 根据采购订单子表查询计价方式
         *
         */
        $scope.load = function () {
            $http.post('hetgl/caigddb/getJijfsList', {caigddb_sub_id: $routeParams.caigddb_sub_id})
                .success(function (data, status, headers, config) {
                    $scope.priceSechemeList = data;
                }).error(function (data) {
                alert("查询出错!");
            });
        };
        $scope.load();
        //------------------------------------------计价方案--------------------------------------------------------------------
        //添加计价组件
        $scope.addPricSecheme = function () {
            var obj = {
                STATUS: 1,
                PO_SUB_ID: $routeParams.caigddb_sub_id
            };
            $scope.priceSechemeList.push(obj);
        };

        $scope.showzbwhfw = function(i){
            $scope.index = i;
            console.log($scope.priceSechemeList[i].ITEM.CODE);
            if($scope.priceSechemeList[i].ITEM.CODE=="JINGZ"){
            	$('#myModal_hetl').modal('show');
            }else{
            	$('#myModal').modal('show');
            }
        };
        $scope.savePricSecheme = function () {
            $http.post('hetgl/pricescheme/updatePriceScheme', {info: angular.toJson($scope.priceSechemeList)})
                .success(function (data, status, headers, config) {
                    alert("保存成功！");
                    $scope.load();
                }).error(function (data) {
                alert("保存失败！");
            });
        };
        $scope.delPricSecheme = function (priceSecheme, index) {
            if (priceSecheme.ID != undefined) {
                $http.post('hetgl/pricescheme/delPriceScheme', {id: priceSecheme.ID}).success(function (data, status, headers, config) {
                    alert("删除成功!!");
                }).error(function (data) {
                    alert("删除失败!!");
                });
            }
            $scope.priceSechemeList.splice(index, 1);
        };
        $scope.reback = function (priceSecheme) {
            $location.path('/addCaigddb/Update/' + $routeParams.caigddb_id);
        };

//--------------------------------------------计价指标--------------------------------------------------------------------------
        $scope.addzhib = function () {
            var obj = {
                SCHEME_ID: $scope.priceSechemeList[$scope.index].ID,
                XUH:$scope.priceSechemeList[$scope.index].KAOHZB.length+1
            };
            $scope.priceSechemeList[$scope.index].KAOHZB.push(obj);
        };
        $scope.savezhib = function () {
            $http.post('hetgl/hetkhzb/updateKaohzb', {info: angular.toJson($scope.priceSechemeList[$scope.index].KAOHZB),caigddb_id:$routeParams.caigddb_id}).success(function (data) {
                alert("保存成功!");
                $scope.load();
            }).error(function (data) {
                alert("保存失败!");
            });
        };
        $scope.delzhib = function (index) {
            var zhib = $scope.priceSechemeList[$scope.index].KAOHZB[index];
            if (zhib.ID != undefined) {
                $http.post('hetgl/hetkhzb/delKaohzb', {id: zhib.ID}).success(function (data, status, headers, config) {
                    alert("删除成功!");
                }).error(function (data) {
                    alert("删除失败!");
                });
            }
            $scope.priceSechemeList[$scope.index].KAOHZB.splice(index, 1);
        }
    });