gddlapp.controller('4000QnetCtrl', function ($scope, $rootScope, $http, $location, $routeParams,$compile) {
    var date = new Date();
    $scope.riq = date.format('yyyy');

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
                $('input').attr("readonly", false);
                var len = $(":button").length;
                $(":button").each(function (i, dom) {
                    $(this).attr("disabled", false);
                });
            }
        });
    } else if ($routeParams.flag.replace("\n", "") == "Show") {
        $http.post('hetgl/hetinfo/editHetbean', {id: $routeParams.mbid}).success(function (data, status, headers, config) {
            if (data) {
                $scope.hetbean = data.hetbean;
                $scope.gongys = data.gongys;
                $scope.dianc = data.xuf;
                $scope.addcaigdd = data.caigdd;
                $('#main-container button').css('display', 'none');
                $('#main-container input,#main-container select').attr('disabled','disabled');
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
//					$scope.picture = $('div#picture')
//					html2canvas($scope.picture, {
//						onrendered: function (canvas) {
//							$scope.myImage = canvas.toDataURL();
//							$http.post('hetgl/hetinfo/addHetbean', {
//								info: angular.toJson($scope.hetbean),
//								subinfo: angular.toJson($scope.addcaigdd),
//								data: angular.toJson($scope.myImage)
//							}).success(function (data, status, headers, config) {
//								if (data[0] == 1) {
//									alert("新增合同信息成功！");
//									$location.path('/hetgl/hetinfo');
//								} else {
//									alert("新增合同信息失败！");
//								}
//							});
//						}
//					});
                    $http.post('hetgl/hetinfo/addHetbean', {
                        info: angular.toJson($scope.hetbean),
                        subinfo: angular.toJson($scope.addcaigdd),
                        data: ''
                    }).success(function (data, status, headers, config) {
                        if (data[0] == 1) {
                            alert("新增合同信息成功！");
                            $location.path('/hetgl/hetinfo');
                        } else {
                            alert("新增合同信息失败！");
                        }
                    });
                } else {
                    alert("合同对应的合同编号已经存在!")
                }
            })
        } else {
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

}).controller('4500QnetCtrl', function ($scope, $rootScope, $http, $location, $routeParams) {
    var date = new Date();
    $scope.riq = date.format('yyyy');
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
                $('input').attr("readonly", false);
                var len = $(":button").length;
                $(":button").each(function (i, dom) {
                    $(this).attr("disabled", false);
                });
            }
        });
    } else if ($routeParams.flag.replace("\n", "") == "Show") {
        $http.post('hetgl/hetinfo/editHetbean', {id: $routeParams.mbid}).success(function (data, status, headers, config) {
            if (data) {
                $scope.hetbean = data.hetbean;
                $scope.gongys = data.gongys;
                $scope.dianc = data.xuf;
                $scope.addcaigdd = data.caigdd;
                $('#main-container button').css('display', 'none');
                $('#main-container input,#main-container select').attr('disabled','disabled');
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
//					$scope.picture = $('div#picture')
//					html2canvas($scope.picture, {
//						onrendered: function (canvas) {
//							$scope.myImage = canvas.toDataURL();
//							$http.post('hetgl/hetinfo/addHetbean', {
//								info: angular.toJson($scope.hetbean),
//								subinfo: angular.toJson($scope.addcaigdd),
//								data: angular.toJson($scope.myImage)
//							}).success(function (data, status, headers, config) {
//								if (data[0] == 1) {
//									alert("新增合同信息成功！");
//									$location.path('/hetgl/hetinfo');
//								} else {
//									alert("新增合同信息失败！");
//								}
//							});
//						}
//					});
                    $http.post('hetgl/hetinfo/addHetbean', {
                        info: angular.toJson($scope.hetbean),
                        subinfo: angular.toJson($scope.addcaigdd),
                        data: ''
                    }).success(function (data, status, headers, config) {
                        if (data[0] == 1) {
                            alert("新增合同信息成功！");
                            $location.path('/hetgl/hetinfo');
                        } else {
                            alert("新增合同信息失败！");
                        }
                    });
                } else {
                    alert("合同对应的合同编号已经存在!")
                }
            })
        } else {
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

}).controller('5000QnetCtrl', function ($scope, $rootScope, $http, $location, $routeParams) {
    var date = new Date();
    $scope.riq = date.format('yyyy');
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
    console.log($routeParams.flag);
    if ($routeParams.flag.replace("\n", "") == "Update") {
        $http.post('hetgl/hetinfo/editHetbean', {id: $routeParams.mbid}).success(function (data, status, headers, config) {
            if (data) {
                $scope.hetbean = data.hetbean;
                $scope.gongys = data.gongys;
                $scope.dianc = data.xuf;
                $scope.addcaigdd = data.caigdd;
                $('input').attr("readonly", false);
                var len = $(":button").length;
                $(":button").each(function (i, dom) {
                    $(this).attr("disabled", false);
                });
            }
        });
    } else if ($routeParams.flag.replace("\n", "") == "Show") {
        $http.post('hetgl/hetinfo/editHetbean', {id: $routeParams.mbid}).success(function (data, status, headers, config) {
            if (data) {
                $scope.hetbean = data.hetbean;
                $scope.gongys = data.gongys;
                $scope.dianc = data.xuf;
                $scope.addcaigdd = data.caigdd;
                $('#main-container button').css('display', 'none');
                $('#main-container input,#main-container select').attr('disabled','disabled');
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
                    /*$scope.picture = $('div#picture')
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
                     }
                     });
                     }
                     });*/
                    $http.post('hetgl/hetinfo/addHetbean', {
                        info: angular.toJson($scope.hetbean),
                        subinfo: angular.toJson($scope.addcaigdd),
                        data: ''
                    }).success(function (data, status, headers, config) {
                        if (data[0] == 1) {
                            alert("新增合同信息成功！");
                            $location.path('/hetgl/hetinfo');
                        } else {
                            alert("新增合同信息失败！");
                        }
                    });
                } else {
                    alert("合同对应的合同编号已经存在!")
                }
            })
        } else {
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
