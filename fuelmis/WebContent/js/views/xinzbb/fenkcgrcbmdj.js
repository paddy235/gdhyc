gddlapp.controller("fenkcgrcbmdj", function ($scope, $rootScope, $location, $http) {
    var d=new Date();
    $scope.search={
        sDate:d.format('yyyy-MM')
    };
    $scope.searchData = function() {
        $http.post('fenkcgrcbmdj/ribcx',{condition:angular.toJson($scope.search)}).success(function(data) {
            document.getElementById("report").innerHTML = data[0].html;

            var totalPage = data[0].pageCount;

            if(totalPage>1){
                for(var i = 2;i <= totalPage;i++){
                    $('#reportpage'+i).css('display','none');
                }
            }
            $('#pagination_zc').remove();
            $("#pagination_box").append('<ul id="pagination_zc"></ul>');
            $("#pagination_zc").twbsPagination({
                first : '首页',
                prev : '前一页',
                next : '下一页',
                last : '尾页',
                totalPages :totalPage,
                visiblePages :5,
                onPageClick : function(event, page) {
                    for(var i = 1;i <= totalPage;i++){
                        if(i==page){
                            $('#reportpage'+i).css('display','block');
                        }else{
                            $('#reportpage'+i).css('display','none');
                        }
                    }
                }
            });
        });

        //-------------------------------------------------------------------------------------------------------------------
        $.ajax({
            type : "get",
            url : 'fenkcgrcbmdj/getMeiHy',
            contentType : "application/json;charset=utf-8",
            data :{ condition:angular.toJson($scope.search)},
            dataType : "json",
            success: function(data){
                var arrData=data;
                require.config({
                    paths:{
                        'echarts' : 'js/echarts'
                    }
                });
                require(
                    [
                        'echarts',
                        'echarts/chart/line', // 使用折线图就加载line模块，按需加载
                        'echarts/chart/bar'
                    ],
                    function (ec) {
                        // 基于准备好的dom，初始化echarts图表
                        var myChart = ec.init(document.getElementById('rucbmdjline'));
                        var option = {
                            tooltip: {
                                trigger: 'axis',
                                axisPointer: {
                                    type: 'cross',
                                    crossStyle: {
                                        color: '#999'
                                    }
                                }
                            },
                            toolbox: {
                                feature: {
                                    dataView: {show: true, readOnly: false},
                                    magicType: {show: true, type: ['line', 'bar']},
                                    restore: {show: true},
                                    saveAsImage: {show: true}
                                }
                            },
                            legend: {
                                data:['实收数量','硫分','热值','入厂标煤单价']
                            },
                            xAxis: [
                                {
                                    type: 'category',
                                    data: arrData[0]

                                }
                            ],
                            yAxis: [
                                {
                                    type: 'value',
                                    name: '价格坐标轴:元/吨',
                                    min: 0,
                                    interval:5

                                },
                                {
                                    type: 'value',
                                    name: '数量坐标轴:万吨',
                                    min: 0,
                                    interval:5

                                }
                            ],
                            series: [
                                {
                                    name:'实收数量',
                                    type:'bar',
                                    yAxisIndex: 1,
                                    data:arrData[5]
                                },
                                {
                                    name:'硫分',
                                    type:'bar',
                                    yAxisIndex: 0,
                                    data:arrData[6]
                                },
                                {
                                    name:'热值',
                                    type:'line',
                                    yAxisIndex: 0,
                                    data:arrData[7]
                                },
                                {
                                    name:'入厂标煤单价',
                                    type:'line',
                                    yAxisIndex: 1,
                                    data:arrData[8]
                                },
                            ]
                        };
                        // 为echarts对象加载数据
                        myChart.setOption(option);
                    }
                );
            }
        });
    }
    $scope.searchData();

    $scope.printPage = function() {
        $("#report").jqprint();
    };
});
/*
gddlapp.controller("fenkcgrcbmdj", function ($scope, $rootScope, $location, $http) {
    var d=new Date();
    $scope.search={
        sDate:d.format('yyyy-MM'),
    };
    $scope.initTable=function (data) {
        document.getElementById("report").innerHTML = data[0].html;

        var totalPage = data[0].pageCount;

        if(totalPage>1){
            for(var i = 2;i <= totalPage;i++){
                $('#reportpage'+i).css('display','none');
            }
        }
        $('#pagination_zc').remove();
        $("#pagination_box").append('<ul id="pagination_zc"></ul>');
        $("#pagination_zc").twbsPagination({
            first : '首页',
            prev : '前一页',
            next : '下一页',
            last : '尾页',
            totalPages :totalPage,
            visiblePages :5,
            onPageClick : function(event, page) {
                for(var i = 1;i <= totalPage;i++){
                    if(i==page){
                        $('#reportpage'+i).css('display','block');
                    }else{
                        $('#reportpage'+i).css('display','none');
                    }
                }
            }
        });
    }
    $scope.searchData = function() {
        // $http.post('fenkcgrcbmdj/getRibcx',{condition:angular.toJson($scope.search)}).success(function(data) {
        //
        //     $scope.initTable(data);
        //
        // });
        //-------------------------------------------------------------------------------------------------------------------
        $.ajax({
            type : "get",
            url : 'fenkcgrcbmdj/getRibcx',
            contentType : "application/json;charset=utf-8",
            data :{ condition:angular.toJson($scope.search)},
            dataType : "json",
            success: function(data){
                var arrData=data;
                $scope.initTable(data);
                var arrData=data[0].arrData;
                require.config({
                    paths:{
                        'echarts' : 'js/echarts'
                    }
                });
                require(
                    [
                        'echarts',
                        'echarts/chart/line', // 使用折线图就加载line模块，按需加载
                        'echarts/chart/bar'
                    ],
                    function (ec) {
                        // 基于准备好的dom，初始化echarts图表
                        var myChart = ec.init(document.getElementById('rucbmdjline'));
                        var option = {
                            tooltip: {
                                trigger: 'axis',
                                axisPointer: {
                                    type: 'cross',
                                    crossStyle: {
                                        color: '#999'
                                    }
                                }
                            },
                            toolbox: {
                                feature: {
                                    dataView: {show: true, readOnly: false},
                                    magicType: {show: true, type: ['line', 'bar']},
                                    restore: {show: true},
                                    saveAsImage: {show: true}
                                }
                            },
                            legend: {
                                data:['实收数量','硫分','热值','入厂标煤单价']
                            },
                            grid: {
                                left: '5%',
                                right: '5%',
                                bottom: '5%',
                                containLabel: true
                            },
                            default: {
                                text: 'loading',
                                color: '#c23531',
                                textColor: '#000',
                                maskColor: 'rgba(255, 255, 255, 0.8)',
                                zlevel: 0
                            },
                            yAxis: [
                                {
                                    type: 'value',
                                    name: '单位：万吨、%、兆焦/千克',
                                    min: 0,
                                    interval:5

                                },
                                {
                                    type: 'value',
                                    min: 0,
                                    interval:50
                                }
                            ],
                            series : [
                                {
                                    name:'实收数量',
                                    type:'bar',
                                    yAxisIndex: 1,
                                    data:arrData[5]
                                },
                                {
                                    name:'硫分',
                                    type:'bar',
                                    yAxisIndex: 0,
                                    data:arrData[6]
                                },
                                {
                                    name:'热值',
                                    type:'line',
                                    yAxisIndex: 0,
                                    data:arrData[7]
                                },
                                {
                                    name:'入厂标煤单价',
                                    type:'line',
                                    yAxisIndex: 1,
                                    data:arrData[8]
                                },
                            ]
                        };
                        // 为echarts对象加载数据
                        myChart.setOption(option);
                    }
                );
            }
        });
    }
    $scope.searchData();

    $scope.printPage = function() {
        $("#report").jqprint();
    };
});*/
