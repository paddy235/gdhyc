gddlapp.controller("rucbddb", function ($scope, $rootScope, $location, $http) {
    var d=new Date();
    $scope.search={
        sDate:d.format('yyyy-MM'),
        eDate:d.format('yyyy-MM')
    };
    $scope.searchData = function() {
        $http.post('rucbddb/ribcx',{condition:angular.toJson($scope.search)}).success(function(data) {
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
            url : 'rucbddb/getMeiHy',
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
                                data:['集团下发对标目标值','对标完成值','完成值与目标值之差','本月完成值','对标单位本月完成值']
                            },
                            xAxis: [
                                {
                                    type: 'category',
                                    data: arrData[0],//['16-01','16-02','16-03','16-04','16-05','16-06','16-07','16-08','16-09','16-10','16-11','16-12'],
                                    axisPointer: {
                                        type: 'shadow'
                                    }
                                }
                            ],
                            yAxis: [
                                {
                                    type: 'value',
                                    name: '单位:元/吨',
                                    min: 0,
                                    interval: 5,

                                },
                                {
                                    type: 'value',
                                   /* name: '数量坐标轴:万吨',*/
                                    min: -30,
                                    interval: 10,
                                }
                            ],
                            series: [
                                {
                                    name:'本月完成值',
                                    type:'line',
                                    yAxisIndex: 0,
                                    data:arrData[1]
                                },
                                {
                                    name:'对标单位本月完成值',
                                    type:'line',
                                   yAxisIndex: 0,
                                    data:arrData[2]
                                },
                                {
                                    name:'集团下发对标目标值',
                                    type:'bar',
                                    yAxisIndex: 1,
                                    data:arrData[3]
                                },
                                {
                                    name:'对标完成值',
                                    type:'bar',
                                    yAxisIndex: 1,
                                    data:arrData[4]
                                },
                                {
                                    name:'完成值与目标值之差',
                                    type:'bar',
                                    yAxisIndex: 1,
                                    data:arrData[5]
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