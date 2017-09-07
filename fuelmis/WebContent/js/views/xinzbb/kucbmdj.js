gddlapp.controller("kucbmdj", function ($scope, $rootScope, $location, $http) {
    var d=new Date();
    $scope.search={
        sDate:d.format('yyyy-MM'),
        eDate:d.format('yyyy-MM')
    };
    $scope.searchData = function() {
        $http.post('kucbmdj/ribcx',{condition:angular.toJson($scope.search)}).success(function(data) {
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
            url : 'kucbmdj/getMeiHy',
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
                                data:['库存较入炉升降','入炉较入厂升降','入厂标单','入炉标单','库存标单']
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
                                    max: 450,
                                    interval: 50
                                },
                                {
                                    type: 'value',
                                  /*  name: '数量坐标轴:万吨',*/
                                    min: 40,
                                    max: 50,
                                    interval: 10
                                    /*axisLabel: {
                                     formatter: '{value} °C'
                                     }*/
                                }
                            ],
                            series: [
                                {
                                    name:'入厂标单',
                                    type:'line',
                                    yAxisIndex: 1,
                                    data:arrData[1],//[2.0, 2.2, 3.3, 4.5, 6.3, 10.2, 20.3, 23.4, 23.0, 16.5, 12.0, 6.2]
                                },
                                {
                                    name:'入炉标单',
                                    type:'line',
                                    yAxisIndex: 1,
                                    data:arrData[2],//[2.0, 2.2, 3.3, 4.5, 6.3, 10.2, 20.3, 23.4, 23.0, 16.5, 12.0, 6.2]
                                },
                                {
                                    name:'库存标单',
                                    type:'line',
                                    yAxisIndex: 1,
                                    data:arrData[3],//[2.0, 2.2, 3.3, 4.5, 6.3, 10.2, 20.3, 23.4, 23.0, 16.5, 12.0, 6.2]
                                },
                                {
                                    name:'库存较入炉升降',
                                    type:'bar',
                                    data:arrData[4],//[2.0, 4.9, 7.0, 23.2, 25.6, 76.7, 135.6, 162.2, 32.6, 20.0, 6.4, 3.3]
                                },
                                {
                                    name:'入炉较入厂升降',
                                    type:'bar',
                                    data:arrData[5],//[2.6, 5.9, 9.0, 26.4, 28.7, 70.7, 175.6, 182.2, 48.7, 18.8, 6.0, 2.3]
                                }

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