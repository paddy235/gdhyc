<%@ page language="java" pageEncoding="UTF-8" %>

<script type="text/javascript" src="/fuelmis/js/swfupload/swfupload.js"></script>
<script type="text/javascript" src="/fuelmis/js/swfupload/handlers.js"></script>
<div class="tab-pane" ng-controller="jiesCtrl">
    <%--	<div class="navbar navbar-inner block-header">
            <div class="muted pull-left">{{jiesTitle}}</div>
        </div>--%>
    <div class="block-content collapse in ">
        <div class="span12">
            <div class="table-toolbar">
                <label style="width:60px;height:30px;line-height:30px;margin-right:5px; float: left;"
                       class="control-label">发票日期:</label>
                <input style="width: 80px; float: left;" id="datepicker"
                       ng-model="search.qisrq" ng-change="refresh()" type="text">

                <label style="width: 60px;height:30px;line-height:30px;margin-right:5px; float: left;"
                       class="control-label span1">煤矿单位:</label>
                <select style="width: 120px; float: left;" id="meik" ng-model="search.meikxxbid"
                        ng-change="load()"
                        ng-options="option.value as option.name for option in meikxxList">
                </select>

                <label style="width: 60px;height:30px;line-height:30px;margin-right:5px; float: left;"
                       class="control-label span1">结算单号:</label>
                <select style="width: 180px; float: left;" ng-model="search.jiesbid"
                        ng-change="refresh()"
                        ng-options="option.value as option.name for option in jiesdhList">
                </select>

                <button style="margin-left: 5px;" id="refresh" ng-click="refresh()"
                        class="btn btn-success">
                    <i class="icon-refresh icon-white"></i> 刷新
                </button>
                <button class="btn btn-primary" id="adddata" ng-click="addjies()">
                    <i class="icon-plus icon-white"></i> 添加
                </button>

                <!--
                <button disabled id="delfap" ng-click="delfap()" class="btn">
                    <i class="icon-trash"></i>删除
                </button>
                -->

                <button disabled id="updatefap" ng-click="updatefap()" class="btn btn-info">
                    <i class="icon-edit icon-white"></i> 修改
                </button>
                <button class="btn btn-inverse" id="fujsc" ng-click="uploadFuj()">
                    <i class="icon-file icon-white"></i> 附件上传
                </button>


            </div>
        </div>
        <div class="row-fluid">
            <table class="table table-striped table-bordered table-hover"
                   id="example">
                <thead>
                <tr>
                    <th style="width: 20px;"></th>
                    <!-- <th style="text-align: center; width: 50px;">序号</th> -->
                    <th style="text-align: center;">编码</th>
                    <th style="text-align: center;">性质</th>
                    <th style="text-align: center;">煤矿简称</th>
                    <th style="text-align: center; width: 160px;">结算单号</th>
                    <th style="text-align: center;">结算描述</th>
                    <th style="text-align: center;">发票起始编号</th>
                    <th style="text-align: center;">发票终止编号</th>
                    <th style="text-align: center;">发票数</th>
                    <th style="text-align: center;">发票总金额</th>
                    <th style="text-align: center;">结算煤款</th>
                    <th style="text-align: center;">发票日期</th>
                    <th style="text-align: center;">录入日期</th>
                    <th style="text-align: center;">录入人</th>
                    <th style="text-align: center;">支付条款</th>
                    <th style="text-align: center;">状态</th>
                    <th style="text-align: center;">附件</th>
                </tr>
                </thead>
            </table>
        </div>
    </div>
    <!-- END FORM-->
</div>

<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" style="display: none;">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-hidden="true">×
                </button>
                <h4 class="modal-title" id="myModalLabel">附件上传</h4>
            </div>
            <div class="modal-body">
                <div class="block-content collapse in">
                    <div class="span12">
                            <div class="control-group">


                                <div id="divFileProgressContainer"></div>
                                <div id="thumbnails">
                                    <table id="infoTable">
                                    </table>
                                </div>

                            </div>

                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <span id="spanButtonPlaceholder">加载文件</span>

                <button id="btnUpload" class="btn" type="button" onclick="startUploadFile();" ><i class="icon-upload"></i>上传</button>
                <button type="button" class="btn" data-dismiss="modal"><i class="icon-remove-circle"></i> 关闭</button>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    var oTable;
    $(document).ready(function () {
        oTable = $('#example').dataTable(
                {
                    "processing": true,
                    //'ajax' : 'yuedranlzfjh/getRanlzfList',
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
                    "sScrollX": "100%",
                    "sScrollXInner": "1800px",
                    "bScrollCollapse": true,

                    //"resizable":true,
                    //'scrollX': 200,
                    //"scrollCollapse": true,
                    "aoColumnDefs": [{
                        "sClass": "center",
                        "mRender": function (oObj, sVal) {
                            return '<input type="checkbox" id="'
                                    + oObj
                                    + '" name="checkId" onclick="check(this)" />';
                        },
                        "bSortable": false,
                        "aTargets": [0]
                    }]
                });
    });

    //设置选中表格数据，选中将删除和更新按钮置亮，取消按钮置灰
    function check(args) {
        if ($(args).attr("checked") != undefined) {
            $("#delfap").addClass("btn-primary");
            $("#delfap").removeAttr("disabled"); //移除disabled属性

            $("#updatefap").addClass("btn-primary");
            $("#updatefap").removeAttr("disabled"); //移除disabled属性

            $("#fujsc").addClass("btn-inverse");
            $("#fujsc").removeAttr("disabled"); //移除disabled属性

            $("input[type='checkbox']").attr("checked", false);
            $(args).attr("checked", true);
        } else {
            $("#delfap").removeClass("btn-primary");
            $("#delfap").attr('disabled', true);

            $("#updatefap").removeClass("btn-primary");
            $("#updatefap").attr('disabled', true);

            $("#fujsc").removeClass("btn-inverse");
            $("#fujsc").attr('disabled', true);
        }
    }

    var swfu;
    function load() {
        swfu = new SWFUpload({
            upload_url:'file/upload' ,
            post_params: {"name": "huliang"},

            // File Upload Settings
            file_size_limit: "10 MB",	// 1000MB
            file_types: "*.*",
            file_types_description: "所有文件",
            file_upload_limit: "0",

            file_queue_error_handler: fileQueueError,
            file_dialog_complete_handler: fileDialogComplete,//选择好文件后提交
            file_queued_handler: fileQueued,
            upload_progress_handler: uploadProgress,
            upload_error_handler: uploadError,
            upload_success_handler: uploadSuccess,
            upload_complete_handler: uploadComplete,

            // Button Settings
            button_image_url: "images/SmallSpyGlassWithTransperancy_17x18.png",
            button_placeholder_id: "spanButtonPlaceholder",
            button_width: 180,
            button_height: 18,
            button_text: '<span class="button">选择附件 <span class="buttonSmall">(10MB max)</span></span>',
            button_text_style: '.button { font-family: Helvetica, Arial, sans-serif; font-size: 12pt; } .buttonSmall { font-size: 10pt; }',
            button_text_top_padding: 0,
            button_text_left_padding: 18,
            button_window_mode: SWFUpload.WINDOW_MODE.TRANSPARENT,
            button_cursor: SWFUpload.CURSOR.HAND,

            // Flash Settings
            flash_url: "/fuelmis/js/swfupload/swfupload.swf",

            custom_settings: {
                upload_target: "divFileProgressContainer"
            },
            // Debug Settings
            debug: false  //是否显示调试窗口
        });

    }
    function startUploadFile() {
//        swfu.initSWFUpload()
        swfu.startUpload();
    }
    function initSWFUpload() {
        swfu.initSWFUpload()
    }


</script>