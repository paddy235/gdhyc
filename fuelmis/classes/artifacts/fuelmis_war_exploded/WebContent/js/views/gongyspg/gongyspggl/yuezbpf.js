gddlapp.controller('yuezbpfCtrl', function($scope,$rootScope,$http,$log,$location) {
    //日期插件数据
    var date = new Date();
    $scope.isshow=false;//用于是否显示表格数据
    $scope.search = {
        sDate : date.format('yyyy-MM')+'-01',
        eDate : date.format('yyyy-MM-dd'),
        rukdbh:'',
        gongysid:-1
    };
//	$http.post请求表格数据

    //根据冒泡原理实现点击一行中的任意位置选中当前行的check
    $scope.fun=function(){
        var e=window.event||arguments[0];
        var src=e.srcElement||e.target;
        if(src.nodeName=="TD"){
            var par=src.parentNode;
            var sd=par.getElementsByTagName("td")[0];
            if(sd.firstElementChild.checked==true){
                sd.firstElementChild.checked=false;
            }else{
                sd.firstElementChild.checked=true;
            }
        }
    };
    $scope.refresh=function(){//点击刷新按钮显示表格
        $http.post('gongyspg/pingggl/getYuezbpf',{condition:angular.toJson($scope.search)}).success(function(data) {
            $scope.datalist=data;
        });
    };
    $scope.computeScore=function(){
        $scope.cmptArray=[];
        $("input[type=checkbox]:checked").not("#selectAll").each(function(){
            $scope.cmptArray.push($scope.datalist[$(this).attr("id")])
        });
        $http.post('gongyspg/pingggl/computeScore',{data:angular.toJson($scope.cmptArray)}).success(function(data) {
            alert(data);
            $scope.refresh();
        });
    };
    $scope.fab=function(){
        $scope.fabArray=[];
        $("input[type=checkbox]:checked").not("#selectAll").each(function(){
            if($scope.datalist[$(this).attr("id")].JIFB_ID!=null){
                $scope.fabArray.push($scope.datalist[$(this).attr("id")].JIFB_ID)
            }
        });
        $http.post('gongyspg/pingggl/fab',{data:angular.toJson($scope.fabArray)}).success(function(data) {
            alert("发布成功！");
        });
    };
    $scope.fabCancel=function(){
        $scope.fabCancelArray=[];
        $("input[type=checkbox]:checked").not("#selectAll").each(function(){
            if($scope.datalist[$(this).attr("id")].JIFB_ID!=null){
                $scope.fabCancelArray.push($scope.datalist[$(this).attr("id")].JIFB_ID)
            }
        });
        $http.post('gongyspg/pingggl/fabCancel',{data:angular.toJson($scope.fabCancelArray)}).success(function(data) {
            alert("发布取消成功！");
        });
    }
});