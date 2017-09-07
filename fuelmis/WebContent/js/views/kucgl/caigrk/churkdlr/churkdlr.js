gddlapp.controller('churkdlrCtrl', function ($scope, $rootScope, $routeParams, $http, $location) {
    $scope.search = new Object();
    $scope.array = new Array();
    $scope.delArray = new Array();//保存需要删除的list的ID

    // ------------------------------------------日期定义-------------------------------------------
    var date = new Date();
    var year = date.getFullYear();
    var month = date.getMonth()+1<10?'0'+(date.getMonth()+1):date.getMonth()+1;
    var day = date.getDate()<10?'0'+date.getDate():date.getDate();
    $scope.search={
    		riq:year+"-"+month+"-"+day,
    		DIANCXXB_ID:515
    }
    //--------------------------------------------------------------------------------------------------------
    /*******************************************************************
     * 加载数据***************************************************
     * 向后台发送请求查询班组信息以jsonArray形式返回
     */
  
    //加载

    $scope.load = function () {
        $http.post('kucgl/rukgl/churkdlr/getChurkd', {search: angular.toJson($scope.search)}).success(function (data) {
            $scope.list = data;
        });
    }
    $scope.load();

    //添加
    $scope.add = function () {
        $http.post('kucgl/rukgl/churkdlr/getChukdbh',{search:angular.toJson($scope.search)}).success(function(data){
            var rukdbh=data;
            var obj = new Object();
            obj = {YUEJSB_ID:"1",ZHUANGT:"1",HANGH:rukdbh,RUKDBH:rukdbh,ZUZ:"",KUCZZ:"",KUCWZ:"",KUCWL:"",HUOZ:"",
                JINE:0,TIAOZJE:0,YEWLX:"",CHURKLX:"",YEWRQ:"",KUAIJRQ:"",JIZRQ:"",YUNDSL:"",RUKSL:"",JIESSL:0,
                JIZ_ID:"",BANZ_ID:"",JIESDRZRQ:"",GONGYSB_ID:""
            }
            $scope.list.push(obj);
        })

    }
    //给数组添加remove
    Array.prototype.remove = function (dx) {
        if (isNaN(dx) || dx > this.length) {
            return false;
        }
        this.splice(dx, 1);
    }
    //全选
    $scope.checkAllFun=function(){
    	if(!$scope.checkAll){
    		for(var i=0;i<$scope.list.length;i++){
    			$scope.list[i].CHECK=1;
    		}
    	}else{
    		for(var i=0;i<$scope.list.length;i++){
    			$scope.list[i].CHECK=0;
    		}
    	}
    }
    //删除选中
    $scope.del=function(){
    	var checks=$("tbody input[type='checkbox']:checked");
    	// console.log(checks);
        $scope.delArray=[];
    	for(var i=0;i<checks.length;i++){
    		if($scope.list[checks[i].id].ID){
    			$scope.delArray.push($scope.list[checks[i].id].ID)	
    		}
            $scope.list.remove(checks[i].id)
    	}
    }
    //保存
    $scope.save = function () {
    	console.log($scope.list);
        $http.post('kucgl/rukgl/churkdlr/delChurkd', {
        	data: angular.toJson($scope.delArray)
        }).success(function (data) {
            $http.post('kucgl/rukgl/churkdlr/saveChurkd', {
                data: angular.toJson($scope.list)
            }).success(function (data) {
                alert("保存成功");
                $scope.load();
            });
        });
    }
    //绑定日期插件
    $(".datepicker").live('focus', function() {
		$(this).datepicker({
			format : 'yyyy-mm-dd',
			minViewMode : 0,
			language : "zh-CN",
			autoclose : true
		})
	});
    //绑定日期插件
    $(".datepicker0").live('focus', function() {
        $(this).datepicker({
            format : 'yyyy-mm',
            minViewMode : 1,
            language : "zh-CN",
            autoclose : true
        })
    });
})
;
