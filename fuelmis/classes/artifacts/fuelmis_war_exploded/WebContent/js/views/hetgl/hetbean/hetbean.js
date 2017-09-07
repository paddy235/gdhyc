gddlapp.controller('hetbeanCtrl', function ($scope, $rootScope, $http, $log, $location) {
    $scope.hetbeanTitle = '合同维护';
    $scope.search = new Object();
    $scope.search.diancid = 515;
    var date = new Date();
    $scope.search.strdate = date.format('yyyy-MM') + '-01';
    $scope.search.enddate = date.format('yyyy-MM-dd');
    $scope.hetmbList = new Array();
    $scope.addHetmb = new Object();
    //----------------------------------------------------------------------------------------------------
    $scope.columnDefs = [{
        "sClass": "center",
        "mRender": function (oObj, sVal, row) {
            return '<input  type="checkbox" id="' + oObj + '" name="checkId"   />';
        },
        "bSortable": false,
        "aTargets": [0]
    }];
    $scope.refresh = function () {
        $http.post('hetgl/hetinfo/searchHetbeanList', {search: angular.toJson($scope.search)}).success(function (data) {

            $scope.hetbeanArray = data.data
        })
    };
    $scope.refresh();
    //----------------------------------------------------------------------------------------------------

    $scope.addHetbean = function () {
        var flag = "Add";
        $location.path('/addHetbean/' + flag);
    }

    $scope.addHetbean1 = function () {
        var flag = "Add";
        $location.path('/addHetbean1/' + flag);
    }

    $scope.editHetbean = function () {
        if ($("#example input[type=checkbox]:checked").length < 1) {
            alert("请选择要修改的行！");
        } else {
            $("#example input[type=checkbox]").each(function () {
                if (this.checked) {
                    var flag = "Update";
                    var id = $(this).attr("id");
                    $http.post('hetgl/rlhtmb/getHetmbByhtId', {id: id}).success(function (data, status, headers, config) {
                        if (data) {
                            var hetmb = data;
                            var luj = data.luj;
                            $location.path(luj + flag + "/" + id);
                        }
                    });
                }
            });
        }
    }
    $scope.createDoc = function () {
        if ($("#example input[type=checkbox]:checked").length < 1) {
            alert("请选择要生成合同的数据！");
        } else {
            $("#example input[type=checkbox]").each(function () {
                if (this.checked) {
                    var id = $(this).attr("id");
                    $http.post('hetgl/hetinfo/createDoc', {id: id}).success(function (data, status, headers, config) {
                    });
                }
            });
        }
    }

    $scope.uploadFuj = function () {
        var check = $("#example input[type=checkbox]:checked");
        if ($("#example input[type=checkbox]:checked").length < 1) {
            alert("请选择要添加附件的记录！");
        } else {
            $scope.isShow = true;
            $scope.swfUpload.setPostParams({'hetid': check[0].id});
            $http.post('file/getFileList', {guanlid: check[0].id, yewly: 'rl_ht_hetb'}).succeess(function (data) {
                $scope.fileList = data;
            }).error(function (data) {
                alert("查询附件失败!");
            });


        }
    };
    $scope.commitHt = function () {
        if ($("#example input[type=checkbox]:checked").length < 1) {
            alert("请选择要提交审批的合同！");
        } else {
            $("#example input[type=checkbox]").each(function () {
                if (this.checked) {
                    var id = $(this).attr("id");
                    $http.post('hetgl/hetinfo/commitDoc', {id: id}).success(function (data, status, headers, config) {
                        //alert(data);
                        alert('合同提交审批成功');
                    });
                }
            });
        }
    };

    $scope.showHetmb = function () {
        $http.post('hetgl/hetinfo/getRlhtmbList').success(function (data, status, headers, config) {
            $scope.hetmbList = data;
        });
        $('#myModal').modal('show');
    };

    $scope.addhetmb = function (sub) {
        $scope.addHetmb = sub;
    };

    $scope.toAddht = function () {
        var luj = $scope.addHetmb.luj;
        if (luj != null && luj != '') {
            var mbId = $scope.addHetmb.id;
            var flag = "Add";
            $location.path(luj + flag + "/" + mbId);
        } else {
            alert("请至少选择一个模板");
            return false;
        }
    }


    $scope.delHetbean = function () {
        if ($("#example input[type=checkbox]:checked").length < 1) {
            alert("请选择要删除的行！")
        } else {
            $('#myModal_Del').modal('show');
        }
    }
    $scope.fjUpload = function () {
        $.ajaxFileUpload({
            url: 'hetgl/hetinfo/uploadFile',
            secureuri: false,
            fileElementId: 'upFile',
            dataType: 'json',
            data: {
                id: $('#pandxx_id').val()
            },
            success: function (data, status) {
                alert(data[0].msg);
                $('#myModal_Doc').modal('hide');
                $scope.refresh()
            },
            error: function (data, status, e) {
                alert(e);
            }
        })
        return false;
    }
    $scope.deleteHetbean = function () {
        var id = $("#example input[type=checkbox]:checked")[0].id;
        $http.post("hetgl/hetinfo/delHetbean", {id: id}).success(function (data) {
            $('#myModal_Del').modal('hide');
            alert("删除成功！");
            $scope.refresh();
        }).error(function (data) {
            alert("删除失败！");
        });
    }
})
    .controller('hetbeanAddCtrl', function ($scope, $rootScope, $http, $location, $routeParams) {
        $scope.hetbeanTitle = '';
        $scope.hetbean = new Object();
        $scope.gongys = new Object();
        $scope.dainc = new Object();
        $scope.hetbeansubList = new Array();
        $scope.hetbeansubdeList = new Array();
        $scope.caigddbsubs = new Array();
        $scope.caigddbList = new Array();
        //选择的采购订单
        $scope.addcaigdd = new Object();
        $scope.jijfsList = new Array();


        $http.post('common/getComboHetmb', {info: 'HETMB'}).success(function (data, status, headers, config) {
            $scope.hetmbList = data;
        });
        if ($routeParams.flag == "Add") {
            $scope.hetbeanTitle = '新增合同信息';
            $scope.hetbean.zhuangt = 1;
        } else if ($routeParams.flag == "Update") {
            $scope.hetbeanTitle = '修改合同信息';
            $http.post('hetgl/hetinfo/editHetbean', {id: $routeParams.id}).success(function (data, status, headers, config) {
                if (data) {
                    $scope.hetbean = data.hetbean;
                    $scope.hetbeansubList = data.hetbeansub;
                    $scope.gongys = data.gongys;
                    $scope.caigddbsubs = data.caigddbsubs;
                    $scope.dianc = data.xuf;
                }
            });
        }

        $scope.changeGys = function () {
            var gysid = $scope.hetbean.gongf;
            $http.post('hetgl/hetinfo/getGongysById', {id: gysid}).success(function (data, status, headers, config) {
                if (data) {
                    $scope.gongys = data.gongys;
                }
            })
        }

        $scope.showCaigdd = function () {
            $http.post('hetgl/hetinfo/getCaigddbList').success(function (data, status, headers, config) {
                if (data) {
                    $scope.caigddbList = data;
                }
            })
            $('#myModal').modal('show');
        }

        $scope.changeDiancxx = function () {
            var diancid = $scope.hetbean.xuf;
            $http.post('hetgl/hetinfo/getDiancxxById', {id: diancid}).success(function (data, status, headers, config) {
                if (data) {
                    $scope.dianc = data.dianc;
                }
            })
        }

        $scope.saveHetbean = function () {
            if ($scope.hetbean.id == null) {
                $http.post('hetgl/hetinfo/addHetbean', {
                    info: angular.toJson($scope.hetbean),
                    subinfo: angular.toJson($scope.hetbeansubList)
                }).success(function (data, status, headers, config) {
                    if (data[0] == 1) {
                        alert("新增合同信息成功！");
                        $location.path('/hetgl/hetinfo');
                    } else {
                        alert("新增合同信息失败！");
                        $location.path('/hetgl/hetinfo');
                    }
                    $location.path('/hetgl/hetinfo');
                });
            } else {
                $http.post('hetgl/hetinfo/updateHetbean', {
                    info: angular.toJson($scope.hetbean),
                    subinfo: angular.toJson($scope.hetbeansubList),
                    delinfo: angular.toJson($scope.hetbeansubdeList)
                }).success(function (data, status, headers, config) {
                    if (data[0] == 1) {
                        alert("修改合同信息成功！");
                        $location.path('/hetgl/hetinfo');
                    } else {
                        alert("修改合同信息失败！");
                        $location.path('/hetgl/hetinfo');
                    }
                });
            }
        }

        $scope.cancel = function () {
            $location.path('/hetgl/hetinfo');
        };

        $scope.addHetbeansub = function () {
            var obj = new Object();
            $scope.hetbeansubList.push(obj);
        };

        /*删除*/
        $scope.deleteHetbeansub = function (fencsl) {
            $.each($scope.hetbeansubList, function (i) {
                if (this == fencsl) {
                    $scope.hetbeansubList.splice(i, 1);
                    return false;
                }
            });

            $http.post('hetgl/hetinfo/getCaigddbsub', {subinfo: angular.toJson($scope.hetbeansubList)}).success(function (data, status, headers, config) {
                $scope.caigddbsubs = data.caigddbsubs;
            });
            $scope.hetbeansubdeList.push(fencsl);
        }


        $scope.addinfo = function (sub) {
            $http.post('hetgl/hetinfo/getKaohzbbList', {id: sub.id}).success(function (data, status, headers, config) {
                $scope.jijfsList = data;
            });
            $scope.addcaigdd = sub;
        }

        $scope.changeCaigdd = function () {
            $http.post('hetgl/hetinfo/getCaigddbsub', {subinfo: angular.toJson($scope.hetbeansubList)}).success(function (data, status, headers, config) {
                $scope.caigddbsubs = data.caigddbsubs;
            });
        }
    })

    .controller('hetbeanAdd1Ctrl', function ($scope, $rootScope, $http, $location, $routeParams) {
        $scope.hetbeanTitle = '';
        $scope.hetbean = new Object();
        $scope.gongys = new Object();
        $scope.dainc = new Object();
        $scope.hetbeansubList = new Array();
        $scope.hetbeansubdeList = new Array();
        $scope.caigddbsubs = new Array();
        $scope.caigddbList = new Array();
        $scope.kaohzbbList = new Array();
        $scope.hetbean.mubid = $routeParams.mbid;

        //选择的采购订单
        $scope.addcaigdd = new Object();
        if ($routeParams.flag.replace("\n", "") == "Update") {
            $http.post('hetgl/hetinfo/editHetbean', {id: $routeParams.mbid}).success(function (data, status, headers, config) {
                if (data) {
                    $scope.hetbean = data.hetbean;
                    $scope.gongys = data.gongys;
                    $scope.dianc = data.xuf;
                    $scope.addcaigdd = data.caigdd;
//					$scope.gongys = data.gongys;
//					$scope.caigddbsubs = data.caigddbsubs;
//					$scope.dianc = data.xuf;

                    $('input').attr("readonly", false);
                    var len = $(":button").length;
                    $(":button").each(function (i, dom) {
                        $(this).attr("disabled", false);
                    });
                    /*$(':button').last().live('click', function() {
                     $location.path('/hetgl/hetinfo');
                     });*/
                }
            });


        } else if ($routeParams.flag.replace("\n", "") == "Show") {
            $http.post('hetgl/hetinfo/editHetbean', {id: $routeParams.mbid}).success(function (data, status, headers, config) {
                if (data) {
                    $scope.hetbean = data.hetbean;
                    $scope.gongys = data.gongys;
                    $scope.dianc = data.xuf;
                    $scope.addcaigdd = data.caigdd;
//				$scope.gongys = data.gongys;
//				$scope.caigddbsubs = data.caigddbsubs;
//				$scope.dianc = data.xuf;

                    $('input').attr("readonly", true);
                    var len = $(":button").length;
                    $(":button").each(function (i, dom) {

                        $(this).attr("disabled", true);
                    });
                    /*$(':button').last().live('click', function() {
                     $location.path('/hetcx');
                     });*/
                }
            });

        }
        $scope.showCaigdd = function () {
            $http.post('hetgl/hetinfo/getCaigddbList').success(function (data, status, headers, config) {
                if (data) {
                    $scope.caigddbList = data;
                }
            })
            $('#myModal').modal('show');
        }

        $scope.addinfo = function (sub) {
            $scope.addcaigdd = sub;
        }

        $scope.getJiagDx = function () {
            var n = $scope.hetbean.jiag;
            var dx = getDx(n);
            $scope.hetbean.jiag_dx = dx;
        }

        getDx = function (n) {
            if (!/^(0|[1-9]\d*)(\.\d+)?$/.test(n))
                return "数据非法";
            var unit = "千百拾亿千百拾万千百拾元角分", str = "";
            n += "00";
            var p = n.indexOf('.');
            if (p >= 0)
                n = n.substring(0, p) + n.substr(p + 1, 2);
            unit = unit.substr(unit.length - n.length);
            for (var i = 0; i < n.length; i++)
                str += '零壹贰叁肆伍陆柒捌玖'.charAt(n.charAt(i)) + unit.charAt(i);
            return str.replace(/零(千|百|拾|角)/g, "零").replace(/(零)+/g, "零").replace(/零(万|亿|元)/g, "$1").replace(/(亿)万|壹(拾)/g, "$1$2").replace(/^元零?|零分/g, "").replace(/元$/g, "元");
        }


        $http.post('common/getComboHetmb', {info: 'HETMB'}).success(function (data, status, headers, config) {
            $scope.hetmbList = data
        });

        $scope.getKaohzb = function () {
            id = $scope.addcaigdd.id;
            if (id != null && id != '') {
                $http.post('hetgl/hetinfo/getKaohzbbList', {id: $scope.addcaigdd.id}).success(function (data, status, headers, config) {
                    $scope.kaohzbbList = data;
                });
            }

        }


        $scope.changeGys = function () {
            var gysid = $scope.hetbean.gongf;
            $http.post('hetgl/hetinfo/getGongysById', {id: gysid}).success(function (data, status, headers, config) {
                if (data) {
                    $scope.gongys = data.gongys;
                }
            })
        }

        /*$scope.showCaigdd = function(){
         $http.post('hetgl/hetinfo/getCaigddbList').success(function(data,status,headers,config){
         if(data){
         $scope.caigddbList = data;
         }
         })
         $('#myModal').modal('show');
         }*/

        $scope.changeDiancxx = function () {
            var diancid = $scope.hetbean.xuf;
            $http.post('hetgl/hetinfo/getDiancxxById', {id: diancid}).success(function (data, status, headers, config) {
                if (data) {
                    $scope.dianc = data.dianc;
                }
            })
        }

        $scope.saveHetbean = function () {
            if ($scope.hetbean.id == null) {
                $http.post('hetgl/hetinfo/getHetbh', {hetbh: $scope.hetbean.hetbh}).success(function (data) {
                    if (data == "0") {
                        //-----------------------------------------------------
                        $scope.picture = $('div#picture')
                        html2canvas($scope.picture, {
                            onrendered: function (canvas) {
                                $scope.myImage = canvas.toDataURL();
                                $http.post('hetgl/hetinfo/addHetbean', {
                                    info: angular.toJson($scope.hetbean),
                                    subinfo: angular.toJson($scope.addcaigdd),
                                    data: angular.toJson($scope.myImage)
                                }).success(function (data, status, headers, config) {
                                    if (data[0] == 1) {
                                        alert("新增合同信息成功！");
                                        $location.path('/hetgl/hetinfo');
                                    } else {
                                        alert("新增合同信息失败！");
                                        // $location.path('/hetgl/hetinfo');
                                    }
                                    // $location.path('/hetgl/hetinfo');
                                });
                            }
                            //---------------------------------------------------------
                        });
                        ////-------------------------------------------------------------
                    } else {
                        alert("合同对应的合同编号已经存在!")
                    }
                })
            } else {
//		   $scope.picture=$('div#picture')
//		   html2canvas($scope.picture, {onrendered : function(canvas) { 
//			   $scope.myImage  = canvas.toDataURL();   
                $http.post('hetgl/hetinfo/updateHetbean', {
                    info: angular.toJson($scope.hetbean),
                    subinfo: angular.toJson($scope.addcaigdd),
                    data: angular.toJson($scope.myImage)
                }).success(function (data, status, headers, config) {
                    if (data[0] == 1) {
                        alert("修改合同信息成功！");
                        $location.path('/hetgl/hetinfo');
                    } else {
                        alert("修改合同信息失败！");
                        $location.path('/hetgl/hetinfo');
                    }
                });
//	   }
//		   });
            }
        }

        $scope.cancel = function () {
            $location.path('/hetgl/hetinfo');
        };

        $scope.addHetbeansub = function () {
            var obj = new Object();
            $scope.hetbeansubList.push(obj);
        };

        /*删除*/
        $scope.deleteHetbeansub = function (fencsl) {
            $.each($scope.hetbeansubList, function (i) {
                if (this == fencsl) {
                    $scope.hetbeansubList.splice(i, 1);
                    return false;
                }
            });

            $http.post('hetgl/hetinfo/getCaigddbsub', {subinfo: angular.toJson($scope.hetbeansubList)}).success(function (data, status, headers, config) {
                $scope.caigddbsubs = data.caigddbsubs;
            });
            $scope.hetbeansubdeList.push(fencsl);
        };

        $scope.changeCaigdd = function () {
            $http.post('hetgl/hetinfo/getCaigddbsub', {subinfo: angular.toJson($scope.hetbeansubList)}).success(function (data, status, headers, config) {
                $scope.caigddbsubs = data.caigddbsubs;
            });
        }

    })
    .controller('hetbeanShowCtrl', function ($scope, $rootScope, $http, $location, $routeParams) {
        $scope.hetbeanTitle = '';
        $scope.hetbean = new Object();
        $scope.gongys = new Object();
        $scope.dainc = new Object();
        $scope.hetbeansubList = new Array();
        $scope.hetbeansubdeList = new Array();
        $scope.caigddbsubs = new Array();
        $scope.caigddbList = new Array();


        $http.post('hetgl/hetinfo/editHetbean', {id: $routeParams.id}).success(function (data, status, headers, config) {
            if (data) {
                $scope.hetbean = data.hetbean;
                $scope.hetbeansubList = data.hetbeansub;
                $scope.gongys = data.gongys;
                $scope.caigddbsubs = data.caigddbsubs;
                $scope.dianc = data.xuf;
            }
        });

        $http.post('common/getComboHetmb', {info: 'HETMB'}).success(function (data, status, headers, config) {
            $scope.hetmbList = data
        });

        $scope.cancel = function () {
            $location.path('/hetgl/hetinfo');
        };

    })
