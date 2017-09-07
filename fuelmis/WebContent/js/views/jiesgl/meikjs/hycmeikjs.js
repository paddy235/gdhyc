gddlapp.controller('hycmeikjsCtrl', function ($scope, $rootScope, $routeParams, $http, $location, meikjsService) {
    var date = new Date();
//        初始化
    if (meikjsService.search == undefined) {
        $scope.search = {
            sDate: date.format('yyyy-MM') + '-01',
            eDate: date.format('yyyy-MM-dd'),
            diancid: 515,
            GONGYSB_ID: -1,
            MEIKID: -1,
            PINZB_ID: -1,
            HETB_ID: -1
        };
    } else {
        $scope.search = meikjsService.search;
    }

//        合计初始化
    $scope.hetj = {
        BIAOZ: 0,
        JINGZ: 0,
        QNETAR: 0,
        STD: 0,
        MT: 0,
        AAD: 0,
        VDAF: 0
    };

//        刷新
    $scope.refresh = function () {
        $http.post('jiesgl/meikjs/getjsdList', {
            condition: angular.toJson($scope.search)
        }).success(function (data) {
            $scope.yuejsList = data;
            $scope.getHetbhList();
        });
    };
    $scope.refresh();
//        供应商下拉框
    $scope.getGongysList = function () {
        $http.post('jiesgl/meikjs/getGongysList', {
            condition: angular.toJson($scope.search)
        }).success(function (data) {
            $scope.gongysList_meikjs = data;
            $scope.getMeikxxList();
            $scope.getHetbhList();
            $scope.getPinzList();
            // $scope.search.GONGYSB_ID = -1;
            // $scope.search.PINZB_ID = -1;
        });

    };
    $scope.getMeikxxList=function () {
        $http.post('jiesgl/meikjs/getMeikxxList', {
            condition: angular.toJson($scope.search)
        }).success(function (data) {
            data.unshift({ID:-1,MINGC:"全部"});
            $scope.meikxxList_meikjs = data;
            $scope.getPinzList();
            // $scope.search.GONGYSB_ID = -1;
            // $scope.search.PINZB_ID = -1;
        });
    }
//        合同编号下拉框
    $scope.getHetbhList = function () {
        $http.post('jiesgl/meikjs/gethetbhList', {
            condition: angular.toJson($scope.search)
        }).success(function (data) {
            $scope.hetbhlist = data;
        });
        // $scope.search.HETB_ID = -1;
    };
//        品种下拉框
    $scope.getPinzList = function () {
        $http.post('jiesgl/meikjs/getPinzList', {
            condition: angular.toJson($scope.search)
        }).success(function (data) {
            $scope.pinzlist = data;
            $scope.getHetbhList();
        });

    };
    $scope.getGongysList();

//        复选框单选
    $scope.selectlist = [];//已经选择的入库单编号
    $scope.selectList_sub = new Object();
    $scope.selectList_sub.data = [];//已经选择的入库单数据
    $scope.selectone = function (data, event) {
        var rukdbh = data.RUKDBH;
        var action = event.target;
        if (action.checked) {//选中，就添加
            if ($scope.selectList_sub.data.indexOf(data) == -1) {//不存在就添加
                $scope.selectList_sub.data.push(data);
            }
            if ($scope.selectlist.indexOf(rukdbh) == -1) {//不存在就添加
                $scope.selectlist.push(rukdbh);
            }
        } else {//去除就删除result里
            var rukdbh_x = $scope.selectlist.indexOf(rukdbh);
            if (rukdbh_x != -1) {//存在就剔除
                $scope.selectlist.splice(rukdbh_x, 1);
            }
            var subindex = $scope.selectList_sub.data.indexOf(data);
            if (subindex != -1) {//存在就剔除
                $scope.selectList_sub.data.splice(subindex, 1);
            }
        }
        $scope.calculatehej($scope.selectList_sub.data);
    };
//      复选框全选
    $scope.selectall = function (event) {
        var action = event.target;
        if (action.checked) {//选中，就添加
            $scope.selectlist = [];
            $scope.selectList_sub.data = $scope.yuejsList;
            angular.forEach($scope.yuejsList, function (data, index, array) {
                //data等价于array[index]
                var rukdbh = array[index].RUKDBH;
                $scope.selectlist.push(rukdbh);
            })
        } else {//去除就删除result里
            $scope.selectlist = [];
            $scope.selectList_sub.data = [];
        }
        $scope.calculatehej($scope.selectList_sub.data);
    };

    //计算合计值
    $scope.calculatehej = function (list) {
        //          var BIAOZ_HEJ = 0;
        var JINGZ_HEJ = 0;
        var QNETAR_HEJ = 0;
        var STD_HEJ = 0;
        var MT_HEJ = 0;
        var AD_HEJ = 0;
        var AAR_HEJ = 0;
        var VDAF_HEJ = 0;
        var CHES = 0;
        var SANFSHUL_HEJ = 0;
        angular.forEach(list, function (data, index, array) {
            //data等价于array[index]
            //     		BIAOZ_HEJ += array[index].JINGZ;
            JINGZ_HEJ += Number(array[index].JINGZ);
            SANFSHUL_HEJ += Number(array[index].SANFSL);
            CHES += Number(array[index].CHES);
            //     		加权
            QNETAR_HEJ += Number(array[index].QNET_AR) * Number(array[index].JINGZ);
            STD_HEJ += Number(array[index].STD) * Number(array[index].JINGZ);
            MT_HEJ += Number(array[index].MT) * Number(array[index].JINGZ);
            AD_HEJ += Number(array[index].AD) * Number(array[index].JINGZ);
            AAR_HEJ += Number(array[index].AAR) * Number(array[index].JINGZ);
            VDAF_HEJ += Number(array[index].VDAF) * Number(array[index].JINGZ);
        });
        //     	求平均
        QNETAR_HEJ = QNETAR_HEJ / JINGZ_HEJ;
        STD_HEJ = STD_HEJ / JINGZ_HEJ;
        MT_HEJ = MT_HEJ / JINGZ_HEJ;
        AD_HEJ = AD_HEJ / JINGZ_HEJ;
        AAR_HEJ = AAR_HEJ / JINGZ_HEJ;
        VDAF_HEJ = VDAF_HEJ / JINGZ_HEJ;
        $scope.hetj.JINGZ = JINGZ_HEJ.toFixed(2);
        $scope.hetj.SANFSL = SANFSHUL_HEJ;
        $scope.hetj.CHES = CHES;
        $scope.hetj.QNETAR = QNETAR_HEJ.toFixed(2);
        $scope.hetj.STD = STD_HEJ.toFixed(2);
        $scope.hetj.MT = MT_HEJ.toFixed(2);
        $scope.hetj.AD = AD_HEJ.toFixed(2);
        $scope.hetj.AAR = AAR_HEJ.toFixed(2);
        $scope.hetj.VDAF = VDAF_HEJ.toFixed(2);
    };
//	    结算
    $scope.jies = function () {
        if ($scope.selectList_sub.data.length == 0) {
            alert("请选择入库单！！！");
            return false;
        }
        if ($scope.search.HETB_ID == -1 || $scope.search.HETB_ID == null) {
            alert("请选择合同号！！！");
        } else {
            $scope.hetj.QNET_AR = $scope.hetj.QNETAR;
            $scope.hetj.CAIGDDSUBID = $scope.search.HETB_ID;
            $scope.hetj.STAR = $scope.hetj.STD;
            $http.post('jiesgl/meikjs/jies', {
                hetj: angular.toJson($scope.hetj),
                rukdbhs: angular.toJson($scope.selectlist)
            }).success(function (data) {
                meikjsService.jiesd = data;
                meikjsService.RUKDH_LIST = $scope.selectlist;
                meikjsService.search= $scope.search;
                $location.path('/hyccreatejiesd');
            }).error(function (data) {
                alert("结算失败！");
            });
        }
    };

}).controller('hycmeikjs_subCtrl', function ($scope, $rootScope, $routeParams, $http, $location, meikjsService) {
    $scope.jiesd = meikjsService.jiesd;
    $scope.jiesd.YANSKSRQ = $scope.jiesd.QINGCSJ;
    $scope.jiesd.MEIKHJ = (Number($scope.jiesd.JIESSL)*Number($scope.jiesd.JIESJG)).toFixed(2);
    $scope.jiesd.MEIKJE = ((Number($scope.jiesd.MEIKHJ)-Number($scope.jiesd.BUKK))/1.17).toFixed(2);
    $scope.jiesd.SHUIK = (Number($scope.jiesd.MEIKHJ)-Number($scope.jiesd.MEIKHJ)/1.17).toFixed(2);
    $scope.jiesd.BEIZ = "根据本企业资金管理制度权限履行会审及审批程序，会审部门及审批人写明意见、签名及日期。";
    $scope.reback = function () {
        $location.path("/hycmeikyjs");
    };
    $scope.jiesd.YUNZFHJ =0;
    $scope.jiesd.ZONGHJ = $scope.jiesd.JIAKHJ+$scope.jiesd.YUNZFHJ;
    /*
	 * 计算
	 */
    $scope.changefirst=  function(){
		$scope.jiesd.MEIKHJ =(Number($scope.jiesd.JIESSL) *Number($scope.jiesd.HETJG)).toFixed(2);
		$scope.jiesd.JIAKHJ=($scope.jiesd.MEIKHJ-$scope.jiesd.BUKK).toFixed(2);
		$scope.jiesd.ZONGHJ = $scope.jiesd.JIAKHJ+$scope.jiesd.YUNZFHJ;
		$scope.jiesd.MEIKJE=(Number($scope.jiesd.JIAKHJ)/1.17).toFixed(2);
		$scope.jiesd.SHUIK=(Number($scope.jiesd.JIAKHJ)-Number($scope.jiesd.MEIKJE)).toFixed(2);
		$scope.jiesd.JIESJG=(Number($scope.jiesd.ZONGHJ)/Number($scope.jiesd.JIESSL)).toFixed(2);//小计有待完善
	}
	$scope.changesecond=  function(){
		$scope.jiesd.JIAKHJ=($scope.jiesd.MEIKHJ-$scope.jiesd.BUKK).toFixed(2);
		$scope.jiesd.MEIKJE=(Number($scope.jiesd.JIAKHJ)/1.17).toFixed(2);
		$scope.jiesd.SHUIK=(Number($scope.jiesd.JIAKHJ)-Number($scope.jiesd.MEIKJE)).toFixed(2);
		$scope.jiesd.ZONGHJ = $scope.jiesd.JIAKHJ+$scope.jiesd.YUNZFHJ;
		$scope.jiesd.JIESJG=(Number($scope.jiesd.ZONGHJ)/Number($scope.jiesd.JIESSL)).toFixed(2);
	}
	$scope.changethird = function(){
		$scope.jiesd.SHUIK=($scope.jiesd.JIAKHJ-$scope.jiesd.MEIKJE).toFixed(2);
	}
	
	$http.post('jiesgl/meikjs/getJiesdbh').success(function(data) {
		$scope.jiesdbhList = data;
	});
//    //输入：含税单价、结算量、税率
//    $scope.changefirst = function () {
//        $scope.jiesd.MEIKJE = (Number($scope.jiesd.JIESJG) * Number($scope.jiesd.JIESSL) / (1 + Number($scope.jiesd.SHUIL))).toFixed(2);
//        $scope.jiesd.JIAKHJ = Number($scope.jiesd.BUKK) + Number($scope.jiesd.MEIKJE);
//        $scope.jiesd.BUHSDJ = (Number($scope.jiesd.MEIKJE) / (1 + Number($scope.jiesd.SHUIL))).toFixed(2);
//        if ($scope.jiesd.BUKK != 0) {
//            $scope.jiesd.SHUIK = (Number($scope.jiesd.JIAKHJ) * Number($scope.jiesd.SHUIL)).toFixed(2);
//            $scope.jiesd.MEIKHJ = Number($scope.jiesd.JIAKHJ) + Number($scope.jiesd.SHUIK);
//        } else {
//            $scope.jiesd.MEIKHJ = Number($scope.jiesd.JIESJG) * Number($scope.jiesd.JIESSL);
//            $scope.jiesd.SHUIK = Number($scope.jiesd.MEIKHJ) - Number($scope.jiesd.JIAKHJ).toFixed(2);
//        }
//    };
//    //输入金额、补扣款
//    $scope.changesecond = function () {
//        $scope.jiesd.JIAKHJ = Number($scope.jiesd.MEIKJE) + Number($scope.jiesd.BUKK);
//        $scope.jiesd.BUHSDJ = (Number($scope.jiesd.MEIKJE) / (1 + Number($scope.jiesd.SHUIL))).tofix(2);
//        $scope.jiesd.SHUIK = Number($scope.jiesd.JIAKHJ) * Number(Number($scope.jiesd.SHUIL));
//        $scope.jiesd.MEIKHJ = Number($scope.jiesd.JIAKHJ) + Number($scope.jiesd.SHUIK);
//    };
//    //输入税款
//    $scope.changethird = function () {
//        $scope.jiesd.MEIKHJ = Number($scope.jiesd.JIAKHJ) + Number($scope.jiesd.SHUIK);
//    };
//
//    $scope.calculateMEIKHJ = function () {
//        $scope.jiesd.MEIKHJ = (parseFloat($scope.jiesd.MEIKJE) + parseFloat($scope.jiesd.SHUIK)).toFixed(2)
//    };


    $scope.save = function () {
        $http.post('jiesgl/meikjs/hycSaveJiesd', {
            data: angular.toJson($scope.jiesd),
            rukdbh: angular.toJson(meikjsService.RUKDH_LIST)
        }).success(function (data) {
            alert("保存成功！");
        }).error(function (data) {
            alert("保存失败！");
        });
    }
}).service('meikjsService', function () {

}).filter('zhibShow',function(){
    return function(zhib){
       if(zhib=='JINGZ'){
           return '结算数量(吨)';
       }else if(zhib=='QNET_AR_DK' || zhib=='QNET_AR'){
           return 'QNET_AR(Kcal/kg)';
       }else if(zhib=='AD'|| zhib=='Mad'|| zhib=='AAR'|| zhib=='MT'|| zhib=='STD'||zhib=='STAR'){
           return zhib+'(%)';
       }else{
           return zhib;
       }
    }
});
;
