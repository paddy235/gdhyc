
gddlapp.directive('myTablePlus',function(){
    return {
        restrict: 'EA',
        scope:{/*data:'=',*/header:'='},
        controller:function ($scope) {
        },
        templateUrl: 'views/common/myTable.jsp',
        link: function (scope, element, attrs, myTablePlusCtrl) {//控制器和模板建立连接
            var table=element.children('table').dataTable(

                {
                    "processing": true,
                    // 'ajax' : 'diaodjh/getDiaodjhList',
                    "language": {
                        "sLengthMenu": "每页显示 _MENU_条",
                        "sZeroRecords": "没有找到符合条件的数据",
                        "sProcessing": "数据加载中...",
                        "sInfo": "当前第 _START_ - _END_ 条　共计 _TOTAL_ 条",
                        "sInfoEmpty": "没有记录",
                        "sInfoFiltered": "(从 _MAX_ 条记录中过滤)",
                        "sSearch": "搜索：",
                        "oPaginate": {
                            "sFirst": "首页",
                            "sPrevious": "前一页",
                            "sNext": "后一页",
                            "sLast": "尾页"
                        }
                    },
                    /* 'scrollX': "100%", */
                    'scrollCollapse': true/*,
                 "aoColumnDefs": scope.$eval(attrs.aoColumnDefs),
                 'fnRowCallback':scope.$eval(attrs.fnRowCallback),
                 "initComplete":scope.$eval(attrs.initComplete)*/
                }
            );
        },


    }
});
//-----------------------------表格--------------------------------------------------------------------------------------
gddlapp.directive('myTable', function () {
    return {
        // scope:{/*aadata:'='*/},
        controller: function ($scope) {
            this.tableConfig = {
                "processing": true,
                // 'ajax' : 'diaodjh/getDiaodjhList',
                "language": {
                    "sLengthMenu": "每页显示 _MENU_条",
                    "sZeroRecords": "没有找到符合条件的数据",
                    "sProcessing": "数据加载中...",
                    "sInfo": "当前第 _START_ - _END_ 条　共计 _TOTAL_ 条",
                    "sInfoEmpty": "没有记录",
                    "sInfoFiltered": "(从 _MAX_ 条记录中过滤)",
                    "sSearch": "搜索：",
                    "oPaginate": {
                        "sFirst": "首页",
                        "sPrevious": "前一页",
                        "sNext": "后一页",
                        "sLast": "尾页"
                    }
                },
                /* 'scrollX': "100%", */
                'scrollCollapse': true/*,
                 "aoColumnDefs": scope.$eval(attrs.aoColumnDefs),
                 'fnRowCallback':scope.$eval(attrs.fnRowCallback),
                 "initComplete":scope.$eval(attrs.initComplete)*/
            };
            this.dataChangeCallback = function (dataTable) {

                var api = dataTable.api();
                var selectAll = dataTable.children('thead').children('tr').children('th:eq(0)').children('input')[0]
                if (selectAll) {
                    selectAll.checked = false
                }
                api.$('tr').click(function () {//每一行绑定事件
                    // api.search( this.innerHTML ).draw();
                    if ($('td:eq(0) input', this)[0].checked) {
                        $('td', this).removeClass('bgRed');
                        $('td:eq(0) input', this)[0].checked = false;
                        if (selectAll) {
                            selectAll.checked = false
                        }

                        return;
                    } else {
                        $('td', this).addClass('bgRed');
                        $('td:eq(0) input', this)[0].checked = true;
                        return;
                    }
                });
                api.$('td input').click(function () {//checkbox绑定事件
                    if (this.checked) {
                        $(this).parent().siblings().andSelf().addClass('bgRed');
                    } else {
                        $(this).parent().siblings().andSelf().removeClass('bgRed');
                        if (selectAll) {
                            selectAll.checked = false
                        }
                    }
                    event.stopPropagation();
                });
                if (selectAll) {
                    $(selectAll).click(function () {//全选按钮绑定事件
                        if (this.checked) {
                            api.$('td').addClass('bgRed')
                        } else {
                            api.$('td').removeClass('bgRed')
                        }
                        api.$('td input').attr('checked', this.checked)
                    })
                }

            };
            this.dataChangeCallback_single = function (dataTable) {
                var api = dataTable.api();
                api.$('tr').click(function () {//每一行绑定事件

                    // api.search( this.innerHTML ).draw();
                    if ($('td:eq(0) input', this)[0].checked) {
                        $('td', this).removeClass('bgRed');
                        $('td:eq(0) input', this)[0].checked = false;
                        return;
                    } else {
                        if ($('.bgRed').length != 0) {
                            $('input', $('.bgRed:eq(0)'))[0].checked = false;
                            $('.bgRed').removeClass('bgRed')
                        }

                        $('td', this).addClass('bgRed');
                        $('td:eq(0) input', this)[0].checked = true;
                        return;
                    }
                });
                api.$('td input').click(function () {//checkbox绑定事件
                    if (this.checked) {
                        if ($('.bgRed').length != 0) {
                            $('input', $('.bgRed:eq(0)'))[0].checked = false;
                            $('.bgRed').removeClass('bgRed')
                        }
                        $(this).parent().siblings().andSelf().addClass('bgRed')
                    } else {

                        $(this).parent().siblings().andSelf().removeClass('bgRed')

                    }
                    event.stopPropagation()
                })
            }
        },
        link: function (scope, element, attrs, myTableCtrl) {//控制器和模板建立连接
            if (attrs.aoColumnDefs) {
                myTableCtrl.tableConfig.aoColumnDefs = scope.$eval(attrs.aoColumnDefs)//table列定义
            }
            element.dataTable(myTableCtrl.tableConfig)//装配table
            scope.$watch(attrs.aaData, function (value) {
                var val = value || null;
                if (val != undefined && val.length != 0) {
                    element.fnClearTable();
                    element.fnAddData(scope.$eval(attrs.aaData));//更新数据
                    if (attrs.mySelection == "true") {
                        myTableCtrl.dataChangeCallback(element)
                    }
                    if (attrs.mySelection == "single") {
                        myTableCtrl.dataChangeCallback_single(element)
                    }
                } else {
                    element.fnClearTable();
                }
            })
        }
    }
});
