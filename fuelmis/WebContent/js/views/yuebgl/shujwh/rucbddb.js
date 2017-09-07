gddlapp.controller('rucbddb1', function ($scope, $rootScope, $routeParams, $http, $location) {
        $scope.search={DIANCXXB_ID :515,riq:(new Date()).format('yyyy-MM-dd')};
        $scope.array = new Array();
        $scope.delArray = new Array();

        $scope.add = function () {
            var obj = {
                DIANCXXB_ID: 515,
                RIQ:$scope.search.riq
            };
            $scope.list.push(obj);
        };
        Array.prototype.remove = function (dx) {
            if (isNaN(dx) || dx > this.length) {
                return false;
            }
            this.splice(dx, 1);
        };
        $scope.del = function (target) {
            var $td = $(target).parent();
            id = $td.attr("id");
            var meihy=$scope.list[id];
            if (meihy) {
                $scope.delArray.push(meihy.ID);
            }
            $scope.list.remove(id);
        };
        // 查重
        $scope.save = function () {
            $http.post('rucbddb/xianggwh/delMeiHy', {
                ids: angular.toJson($scope.delArray)
            }).success(function (data) {
                $http.post('rucbddb/xianggwh/updateMeihy', {
                    data: angular.toJson($scope.list)
                }).success(function (data) {
                    alert("保存成功");
                    $scope.searchData();
                }).error();
            });
        };
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
    $scope.searchData = function() {
        $http.post('rucbddb/xianggwh/getMeiHy',{condition:angular.toJson($scope.search)}).success(function(data) {
            $scope.list=data;
           /* document.getElementById("report").innerHTML = data[0].html;*/
            /*var totalPage = data[0].pageCount;

            if(totalPage>1){
                for(var i = 2;i <= totalPage;i++){
                    $('#reportpage'+i).css('display','none');
                }
            }*/
        });
    }
    $scope.searchData();
});