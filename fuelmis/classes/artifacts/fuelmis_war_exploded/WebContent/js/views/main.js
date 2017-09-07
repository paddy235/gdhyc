//--------------------------------------日期定义--------------------------------------------------------------------------
/**
 * var begin=new Date();
 var sDate= begin.format("yyyy-MM"+"-01");
 var end=new Date();
 new Date(end.setDate((new Date().getDate()-1)));
 var eDate=end.format("yyyy-MM-dd");
 * @param format
 * @returns {*}
 */
Date.prototype.format = function (format) {
    var o = {
        "M+": this.getMonth() + 1, // month
        "d+": this.getDate(), // day
        "h+": this.getHours(), // hour
        "m+": this.getMinutes(), // minute
        "s+": this.getSeconds(), // second
        "q+": Math.floor((this.getMonth() + 3) / 3), // quarter
        "S": this.getMilliseconds()
        // millisecond
    };
    if (/(y+)/.test(format))
        format = format.replace(RegExp.$1, (this.getFullYear() + "")
            .substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(format))
            format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k]
                : ("00" + o[k]).substr(("" + o[k]).length));
    return format;
};
//----------------------------------------------------------------------------------------------------------------------
var gddlapp = angular.module("gddlMain", ['ngRoute', 'ngResource']);
gddlapp.filter("subArray", function () {
    return function (input, start, end) {
        var out = [];
        if (input) {
            if (end == undefined) {
                end = input.length
            }
            out = input.slice(start, end)
        }

        return out;
    }
});
gddlapp.directive('myDateYearMonthDay', function () {
    return {
        restrict: 'A',
        link: function (scope, element, attrs) {
            var format = attrs.myDatepicker;
            $(element).datepicker({
                format: format == null ? 'yyyy-mm-dd' : format,
                minViewMode: 0,
                language: "zh-CN",
                autoclose: true
            });
        }
    }
});
gddlapp.directive('myDateYearMonth', function () {
    return {
        restrict: 'A',
        link: function (scope, element, attrs) {
            var format = attrs.myDatepicker;
            $(element).datepicker({
                format: format == null ? 'yyyy-mm' : format,
                minViewMode: 1,
                language: "zh-CN",
                autoclose: true
            });
        }
    }
});
//日期yyyy-mm-dd
gddlapp.directive('datepicker0', function () {
    return {
        restrict: 'A',
        link: function (scope, element, attrs) {
            $(element).datepicker({
                format:'yyyy-mm-dd',
                minViewMode: 0,
                language: "zh-CN",
                autoclose: true
            });
        }
    }
});
//---------------------------------------导出----------------------------------------------------------------------------
gddlapp.directive('myExport', function () {
    return {
        restrict: 'A',
        controller: function ($scope, $rootScope, $http, $log, $location) {
            this.export = function (r) {
                $http.post('export/toExcel', {r: angular.toJson(r)}).success(function (data) {
                    window.location.href = 'common/downloadFile?fileId=' + encodeURI(r.name);
                })
            }
        },
        link: function (scope, element, attrs, myExportCtrl) {
            var name = attrs.myExport
            element.click(function () {
//                var r = {'html': $('#report').html().toString(), 'name': name};
                var r = {'html': '', 'name': name};
                // window.location.href = 'export/myExcel?r=' + angular.toJson(r);
                myExportCtrl.export(r)
            })
        }
    }

});
//-----------------------------------------------进度环------------------------------------------------------------------
gddlapp.directive('myProgress', function () {
    return {
        restrict: 'A',
        scope: {              // 设置指令对于的scope
            // isDisplay: "@",          // name 值传递 （字符串，单向绑定）
            myProgress: "=",        // amount 引用传递（双向绑定）
            // save: "&"           // 保存操作
        },
        templateUrl: 'views/common/progressBar.jsp',
    }
});
//-------------------------------------------打印------------------------------------------------------------------------
gddlapp.directive('myPrint', function () {
    return {
        restrict: 'A',
        link: function (scope, element, attrs) {
            element.click(function () {
                $("#report").jqprint()
            })
        }
    }


});
//------------------------------------------分页-------------------------------------------------------------------------
gddlapp.directive('myPage', function () {
    return {
        restrict: 'EA',
        replace: true,
        transclude: true,
        /*	        scope : {
         title : '=expanderTitle'
         },*/
        template: '<div class="pagination"  >'
        + '<ul style="float:right">'
        + '<li id="first" style="float:left"><a href="">首页</a></li>'
        + '<li id="previous" style="float:left"><a href="">上一页</a></li>'
        + '<li>'
        + '<ul id="page_num_all" style="float:left">'
        + '</ul>'
        + '</li>'
        + '<li id="next"><a href="" style="float:left" >下一页</a></li>'
        + '<li id="last"><a href="" style="float:left" >尾页</a></li>'
        + '</ul>'
        + '<span>当前为第<span class="num" id="current_page"></span>页，'
        + '总共<span class="num" id="page_all"></span>页'
        + '</span>'
        + '</div>',
        link: function (scope, element, attrs) {
            $("tbody").attr("id", "page")
            var show_page = 15;
            var cont = 0;

            function table_page() {

                var page_all = $("#page").children().size();
                var current_page = 1;
                var page_num = Math.ceil(page_all / show_page);
                var current_num = 0;
                var li = "";

                while (page_num > current_num) {
                    li += '<li class="page_num"><a href="javascript:void(0)">' + (current_num + 1) + '</a></li>';
                    current_num++;
                }
                $("#page_num_all").html(li);
                $('#page tr').css('display', 'none');
                $('#page tr').slice(0, show_page).css('display', '');
                $("#current_page").html("&nbsp;" + current_page + "&nbsp;");
                $("#page_all").html("&nbsp;" + page_num + "&nbsp;");
                //点击上一页
                $("#previous").click(function () {
                    var new_page = parseInt($("#current_page").text()) - 1;
                    cont++;
                    if (cont == 3) {
                        if (new_page > 0) {
                            $("#current_page").html("&nbsp;" + new_page + "&nbsp;");
                            tab_page(new_page);
                            show_pageNum(new_page);
                        }
                        cont = 0;
                    }
                });
                //点击下一页
                $("#next").click(function () {
                    var new_page = parseInt($("#current_page").text()) + 1;
                    if (new_page <= page_num) {
                        $("#current_page").html("&nbsp;" + new_page + "&nbsp;");
                        tab_page(new_page);
                        show_pageNum(new_page);
                    }
                });
                //点击页码
                $(".page_num").click(function () {
                    var new_page = parseInt($(this).text());
                    tab_page(new_page);
                    show_pageNum(new_page);
                });
                //点击首页
                $("#first").click(function () {
                    $("#current_page").html("&nbsp;" + "1" + "&nbsp;");
                    tab_page(1);
                    show_pageNum(1);
                });
                //点击尾页
                $("#last").click(function () {
                    $("#current_page").html("&nbsp;" + page_num + "&nbsp;");
                    tab_page(page_num);
                    show_pageNum(page_num);
                });
                function tab_page(index) {
                    var start = (index - 1) * show_page;
                    var end = start + show_page;
                    $('#page').children().css('display', 'none').slice(start, end).css('display', '');
                    current_page = index;
                    $("#current_page").html("&nbsp;" + current_page + "&nbsp;");
                }

                function show_pageNum(index) {
                    var start = 0;
                    var end = 5;
                    if (index > 4) {
                        start = index - 3;
                        end = index + 2;
                    }
                    $('.page_num').css('display', 'none');
                    $('.page_num').slice(start, end).css('display', '');
                }

                show_pageNum(1);
            }

            scope.$watch(attrs.data, function () {
                table_page();
            });
        }
    }
});
gddlapp.directive('myPage2', function () {
    return {
        restrict: 'EA',
        replace: true,
        transclude: true,
        /*	        scope : {
         title : '=expanderTitle'
         },*/
        template: '<div class="pagination"  >'
        + '<ul style="float:right">'
        + '<li id="first" style="float:left"><a href="">首页</a></li>'
        + '<li id="previous" style="float:left"><a href="">上一页</a></li>'
        + '<li>'
        + '<ul id="page_num_all" style="float:left">'
        + '</ul>'
        + '</li>'
        + '<li id="next"><a href="" style="float:left" >下一页</a></li>'
        + '<li id="last"><a href="" style="float:left" >尾页</a></li>'
        + '</ul>'
        + '<span>当前为第<span class="num" id="current_page"></span>页，'
        + '总共<span class="num" id="page_all"></span>页'
        + '</span>'
        + '</div>',
        link: function (scope, element, attrs) {
            $("table").attr("id", "page")
            var show_page = 10;
            var cont = 0;

            function table_page() {

                var page_all = $("#page").children('tbody').size();
                var current_page = 1;
                var page_num = Math.ceil(page_all / show_page);
                var current_num = 0;
                var li = "";

                while (page_num > current_num) {
                    li += '<li class="page_num"><a href="javascript:void(0)">' + (current_num + 1) + '</a></li>';
                    current_num++;
                }
                $("#page_num_all").html(li);
                $('#page tbody').css('display', 'none');
                $('#page tbody').slice(0, show_page).css('display', '');
                $("#current_page").html("&nbsp;" + current_page + "&nbsp;");
                $("#page_all").html("&nbsp;" + page_num + "&nbsp;");
                //点击上一页
                $("#previous").click(function () {
                    var new_page = parseInt($("#current_page").text()) - 1;
                    cont++;
                    if (cont == 3) {
                        if (new_page > 0) {
                            $("#current_page").html("&nbsp;" + new_page + "&nbsp;");
                            tab_page(new_page);
                            show_pageNum(new_page);
                        }
                        cont = 0;
                    }
                });
                //点击下一页
                $("#next").click(function () {
                    var new_page = parseInt($("#current_page").text()) + 1;
                    if (new_page <= page_num) {
                        $("#current_page").html("&nbsp;" + new_page + "&nbsp;");
                        tab_page(new_page);
                        show_pageNum(new_page);
                    }
                });
                //点击页码
                $(".page_num").click(function () {
                    var new_page = parseInt($(this).text());
                    tab_page(new_page);
                    show_pageNum(new_page);
                });
                //点击首页
                $("#first").click(function () {
                    $("#current_page").html("&nbsp;" + "1" + "&nbsp;");
                    tab_page(1);
                    show_pageNum(1);
                });
                //点击尾页
                $("#last").click(function () {
                    $("#current_page").html("&nbsp;" + page_num + "&nbsp;");
                    tab_page(page_num);
                    show_pageNum(page_num);
                });
                function tab_page(index) {
                    var start = (index - 1) * show_page;
                    var end = start + show_page;
                    $('#page').children('tbody').css('display', 'none').slice(start, end).css('display', '');
                    current_page = index;
                    $("#current_page").html("&nbsp;" + current_page + "&nbsp;");
                }

                function show_pageNum(index) {
                    var start = 0;
                    var end = 5;
                    if (index > 4) {
                        start = index - 3;
                        end = index + 2;
                    }
                    $('.page_num').css('display', 'none');
                    $('.page_num').slice(start, end).css('display', '');
                }

                show_pageNum(1);
            }

            scope.$watch(attrs.data, function () {
                table_page();
            });
        }
    }
}).directive("dyCompile", ["$compile", function ($compile) {
        return {
            replace: true,
            restrict: 'EA',
            link: function (scope, elm, iAttrs) {
                var DUMMY_SCOPE = {
                        $destroy: angular.noop
                    },
                    root = elm,
                    childScope,
                    destroyChildScope = function () {
                        (childScope || DUMMY_SCOPE).$destroy();
                    };

                iAttrs.$observe("html", function (html) {
                    if (html) {
                        destroyChildScope();
                        childScope = scope.$new(false);
                        var content = $compile(html)(childScope);
                        root.replaceWith(content);
                        root = content;
                    }

                    scope.$on("$destroy", destroyChildScope);
                });
            }
        };
    }]);
//修改angularjs中httpAjax的请求的方式，
//修改原因：用post方式提交数据的时候在后台不能用request.getParams的方法获得
gddlapp.config(function ($httpProvider) {
    $httpProvider.defaults.headers.put['Content-Type'] = 'application/x-www-form-urlencoded';
    $httpProvider.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded';
    // Override $http service's default transformRequest
    $httpProvider.defaults.transformRequest = [function (data) {
        var param = function (obj) {
            var query = '';
            var name, value, fullSubName, subName, subValue, innerObj, i;
            for (name in obj) {
                value = obj[name];
                if (value instanceof Array) {
                    for (i = 0; i < value.length; ++i) {
                        subValue = value[i];
                        fullSubName = name;
                        innerObj = {};
                        innerObj[fullSubName] = subValue;
                        query += param(innerObj) + '&';
                    }
                } else if (value instanceof Object) {
                    for (subName in value) {
                        subValue = value[subName];
                        fullSubName = name + '[' + subName + ']';
                        innerObj = {};
                        innerObj[fullSubName] = subValue;
                        query += param(innerObj) + '&';
                    }
                } else if (value !== undefined && value !== null) {
                    query += encodeURIComponent(name) + '=' + encodeURIComponent(value) + '&';
                }
            }
            return query.length ? query.substr(0, query.length - 1) : query;
        };
        return angular.isObject(data) && String(data) !== '[object File]' ? param(data) : data;
    }];
});
gddlapp.controller("bodyCtrl", function ($scope, $http, $rootScope, $location) {
    $scope.changePassword = function (id) {
        $location.path('/changePassword/' + id);
    }
    $scope.$on('changeLeftStart', function (event, data) {
        $scope.$broadcast('changeLeftEnd', data);
    });

    //上传附件
    $scope.ajaxFileUpload = function (uploadPath) {
        if (!$("#fileToUpload").val()) {
            alert("请选择文件!");
            return null;
        }
        $.ajaxFileUpload
        (
            {
                url: 'common/upload',
                secureuri: false,
                fileElementId: 'fileToUpload',
                dataType: 'json',
                data: {path: uploadPath},
                success: function (data, status) {
                    var d = eval(data);
                    $scope.$broadcast('fileUploadSuccess', d);
                },
                error: function (data, status, e) {
                    alert("文件上传失败");
                    $scope.msg = "文件上传失败！";
                    return null;
                }
            }
        )
    };

//	$scope.$on('navbarFromTopChange', function(event,data) {
//		if($scope.navbars){
//			if(data.text=='首页'){
//				$location.path("/");
//				$scope.navbars=[data];
//			}else{
//				$scope.tempnavbars=[data];
//			}
//		}else{
//			$scope.navbars=[data];
//		}
//	});
//	$scope.$on('navbarFromLeftChange', function(event,data) {
//		$scope.navbars=angular.copy($scope.tempnavbars);
//		$scope.navbars.push(data);
//	});	

    $http.post('common/getCurrentUser').success(function (data, status, headers, config) {
        $scope.dangqyh = data//读取当前登陆用户
    });

    $http.post('common/getComboDianc').success(function (data, status, headers, config) {
        $scope.diancList = data//电厂
    });
    
   $http.post('common/getComboDianc_QUANC').success(function (data, status, headers, config) {
        $scope.diancList_QUANC = data//电厂
    });

    $http.post('common/getComboShengf').success(function (data, status, headers, config) {
        $scope.shengfList = data//省份
    });

    $http.post('common/getComboChez').success(function (data, status, headers, config) {
        $scope.chezList = data//车站
    });

    $http.post('common/getComboGangk').success(function (data, status, headers, config) {
        $scope.gangkList = data//港口
    });

    $http.post('common/getComboDianclb').success(function (data, status, headers, config) {
        $scope.dianclbList = data//电厂类别
    });

    $http.post('common/getComboLiclx').success(function (data, status, headers, config) {
        $scope.liclxList = data;//里程类型
    });

    $http.post('common/getComboMeikxx').success(function (data, status, headers, config) {
        $scope.meikxxList = data;//煤矿信息
    });
    $http.post('common/getComboMeikxx2').success(function (data, status, headers, config) {
        $scope.meikxxList2 = data;//煤矿信息
    });
    $http.post('common/getComboLuj').success(function (data, status, headers, config) {
        $scope.lujList = data;//陆局
    });

    $http.post('common/getComboPinz').success(function (data, status, headers, config) {
        $scope.pinzList = data;
    });
    //数量台账--时间条件
    $scope.shijtjList=[{value:0,name:'轻车时间'},{value:1,name:'重车时间'},{value:2,name:"采样时间"}];
    $http.post('common/getComboPinz2').success(function (data, status, headers, config) {
        $scope.pinzList2 = data;
    });

    $http.post('common/getComboJihkj').success(function (data, status, headers, config) {
        $scope.jihkjList = data;
    });

    $http.post('common/getComboJihkj2').success(function (data, status, headers, config) {
        $scope.jihkjList2 = data;
    });

    $http.post('common/getComboMeikdq').success(function (data, status, headers, config) {
        $scope.meikdqList = data;
    });

    $http.post('common/getComboGongys').success(function (data, status, headers, config) {
        $scope.gongysList = data;
    });

    $http.post('common/getComboGongys2').success(function (data, status, headers, config) {
        $scope.gongysList2 = data;
    });
    $http.post('common/getComboGongys_quanc').success(function (data, status, headers, config) {
        $scope.gongysList_QUANC = data;
    });
    
    $http.post('common/getComboRulbz').success(function (data, status, headers, config) {
        $scope.rulbzList = data;
    });

    $http.post('common/getComboYunsfs').success(function (data, status, headers, config) {
        $scope.yunsfsList = data;
        data.shift();
        $scope.yunsfsList2=data;
    });

    $http.post('common/getComboRenyxx').success(function (data, status, headers, config) {
        $scope.renyxxList = data
    });

    $http.post('common/getComboPingffa').success(function (data, status, headers, config) {
        $scope.pingffaList = data;
    });

    $http.post('common/getComboCaigddb').success(function (data, status, headers, config) {
        $scope.caigddbList = data;
    });

    $http.post('common/getComboCaigddb2').success(function (data, status, headers, config) {
        $scope.caigddbList2 = data;
    });

    $http.post('common/getComboKuczz').success(function (data, status, headers, config) {
        $scope.kuczzList = data;
    });

    $http.post('common/getComboKucwz').success(function (data, status, headers, config) {
        $scope.kucwzList = data;
    });

    $http.post('common/getComboKucwl').success(function (data, status, headers, config) {
        $scope.kucwlList = data;
    });

    $http.post('common/getAllChurkywlx').success(function (data, status, headers, config) {
        $scope.churkywlxList = data;
    });

    $http.post('common/getAllChurkywlx_fdck').success(function (data, status, headers, config) {
        $scope.churkywlx_fdckList = data;
    });

    $http.post('common/getJijfs', {type: 'star'}).success(function (data, status, headers, config) {
        $scope.starjjfsList = data;
    });

    $http.post('common/getJijfs', {type: 'qnetar'}).success(function (data, status, headers, config) {
        $scope.qnetarjjfsList = data
    });
    $http.post('sanjsp/url').success(function (data) {
        $scope.sanjspUrl = data;
    });

    $http.post('common/getAllKaohzb').success(function (data, status, headers, config) {
        $scope.kaohzbList = data
    });

    $http.post('common/getAllPriceComponet').success(function (data, status, headers, config) {
        $scope.priceComponetList = data
    });

    $http.post('common/getAllPriceComponet1').success(function (data, status, headers, config) {
        $scope.priceComponetList1 = data
    });

    $http.post('common/getAllPriceItem').success(function (data, status, headers, config) {
        $scope.priceItemList = data
    });

    $http.post('common/getAllPriceItem1').success(function (data, status, headers, config) {
        $scope.priceItemList1 = data
    });
    //topCtrl begin
    $http.post('common/getComboHetbh').success(function (data, status, headers, config) {
        $scope.hetbhList = data
    });

    $http.post('common/getComboRuljz').success(function (data, status, headers, config) {
        $scope.ruljzList = data
    });
    // $http.post('common/getJizList').success(function(data){
    //     $scope.jizList=data
    // })
    // $http.post('common/getBanzList').success(function(data){
    //     $scope.banzList=data
    // })
    $http.post('ziyxx/getTopMenu').success(function (data) {
        $scope.topMenuxx = data
        $scope.activeItem = data[0];
        $scope.$emit('navbarFromTopChange', $scope.activeItem);
    });

    $scope.changeLeftPage = function (cdm) {
        $scope.$emit('changeLeftStart', cdm.children);
        $scope.activeItem = cdm;
        $scope.$emit('navbarFromTopChange', $scope.activeItem);
    }

    $scope.ajaxExcelExport = function (tableId, fileName, sheetName) {
        $.ajaxExcelExport({
            url: 'export/excel',
            tableId: tableId,
            secureuri: false,
            fileElementId: 'exportExcel',
            dataType: 'json',
            data: {fileName: fileName, sheetName: sheetName},
            success: function (data, status) {
                console.log("导出成功");
            },
            error: function (data, status, e) {
                console.log(e);
                return null;
            }
        })
    };
    // $scope.excelExport = function (report, name) {
    //     var r = {'html': report.html().toString(), 'name': name}
    //     $http.post('export/toExcel', {r: angular.toJson(r)}).success(function (data) {
    //         window.location.href = 'common/downloadFile?fileId=' + r.name;
    //     })
    // }
    $scope.changePage = function (menu) {
        if (menu.name == '首页') {
            $scope.leftMenuxx = [];
            $location.path("/");
        }
    }
    //topCtrl end
    //leftCtrl begin
    $scope.leftMenuxx = [];
    $scope.$on('changeLeftEnd', function (event, data) {
        var lis = $("#my-nav").children();
        $scope.leftMenuxx = data;
        var reg = new RegExp("#", "g"); //创建正则RegExp对象
        var path = null;
        if (lis.length != 0) {
            for (var i = 0; i < data.length; i++) {
                g = $scope.leftMenuxx[i].wenjwz.replace(reg, "");
                $scope.leftMenuxx[i].wenjwz = "\#" + g;
                if (i == 0) {
                    path = g;
                    $scope.leftMenuxx[i].status = true
                } else {
                    $scope.leftMenuxx[i].status = false
                }
            }
        } else {
            for (var i = 0; i < data.length; i++) {
                g = $scope.leftMenuxx[i].wenjwz.replace(reg, "");
                $scope.leftMenuxx[i].wenjwz = "\#" + g;
                if (i == 0) {
                    path = g;
                    $scope.leftMenuxx[i].status = true;
                } else {
                    $scope.leftMenuxx[i].status = false;
                }
            }
        }
        $location.path(path);
    });
    $scope.changeRightPage = function (parent, caidModel, target, index) {
        var lis = $("#my-nav").children();
        if (lis.length != 0) {
            $scope.activeItem = caidModel;
            $scope.leftMenuxx = parent;
            var reg = new RegExp("#", "g"); //创建正则RegExp对象
            for (var i = 0; i < parent.length; i++) {
                var g = parent[i].wenjwz.replace(reg, "");
                $scope.leftMenuxx[i].wenjwz = "\#" + g;
                if (i == index) {
                    $scope.leftMenuxx[i].status = true
                } else {
                    $scope.leftMenuxx[i].status = false
                }
            }
            var f = caidModel.wenjwz.replace(reg, "");
            $location.path(f);
            $scope.$emit('navbarFromLeftChange', $scope.activeItem);
        } else {
            $scope.activeItem = caidModel;
            $scope.leftMenuxx = parent;
            var reg = new RegExp("#", "g"); //创建正则RegExp对象
            for (var i = 0; i < parent.length; i++) {
                var g = parent[i].wenjwz.replace(reg, "");
                $scope.leftMenuxx[i].wenjwz = "\#" + g;
                if (i == index) {
                    $scope.leftMenuxx[i].status = true;
                } else {
                    $scope.leftMenuxx[i].status = false;
                }
            }
            var f = caidModel.wenjwz.replace(reg, "");
            $location.path(f);
            //$scope.$emit('navbarFromLeftChange',$scope.activeItem);
        }

    }
    //leftCtrl end
// 	$(document).ready(function() {  
// 		// put all your jQuery goodness in here. 
// 		if($scope.currentPath!=undefined){
// 			console.log($("#"+$scope.currentPath)[0])	
// 		}

// 		//$scope.currentPath= 
// 	});
})
    .controller("topCtrl", function ($scope, $rootScope, $location, $http) {
    })
    // .controller("leftCtrl", function($scope,$rootScope,$location,$http){
    // 	$scope.leftMenuxx=[];
    // 	$scope.$on('changeLeftEnd', function(event,data) {
    // 		if(data.length>0){
    // 			$scope.leftMenuxx= data;
    //  			for(var i=0;i<data.length;i++){
    //  				$scope.leftMenuxx[i].wenjwz= "\#"+data[i].wenjwz;
    //  			}
    // 		}
    // 	});
    // 	$scope.changeRightPage = function(caidModel) {
    //    		$scope.activeItem=caidModel;
    //     	$location.path(caidModel.wenjwz);
    //    		$scope.$emit('navbarFromLeftChange',$scope.activeItem);
    // 	}
    // })
    .controller("homeCtrl", function ($scope, $rootScope, $location, $http) {
        $scope.showView = function () {
            $location.path("/portal");
        }
    }).controller("testCtrl", function ($scope, $rootScope, $location, $http) {
        $scope.testHead=['w','x','y','z'];
        $scope.testData=[[1,1,1,1],[2,2,2,2]];
    $scope.getJij_Qnetar = function () {
        $http.post('test/getJij_Qnetar').success(function (data, status, headers, config) {
        });
    }

    $scope.getJij_Star = function () {
        $http.post('test/getJij_Star').success(function (data, status, headers, config) {
        });
    }
    $scope.test = function () {
        $http.post('test/model').success(function (data, status, headers, config) {
        });
    }

});
/*
angular.element(document).ready(function(){
    angular.bootstrap(document,['gddlMain']);
});*/
