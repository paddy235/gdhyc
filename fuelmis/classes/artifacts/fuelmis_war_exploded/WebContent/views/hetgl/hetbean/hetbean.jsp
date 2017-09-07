<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<div class="tab-pane" ng-controller="hetbeanCtrl">
    <div class="block-content collapse in ">
        <div class="span12">
            <div class="table-toolbar">
                <form class="form-horizontal ng-pristine ng-valid">
                    <fieldset>
                        <div class="row-fluid" style="margin-bottom:20px;">
                            <label class="control-label" style="width: 80px;margin-right:5px;">开始时间:</label>
                            <input id="datepicker1" ng-model="search.strdate" type="text"
                                   style="width:120px;float: left" ng-change="refresh()">
                            <label class="control-label" style="width: 80px;margin-right:5px;">结束时间:</label>
                            <input id="datepicker2" ng-model="search.enddate" type="text"
                                   style="width:120px;float: left" ng-change="refresh()">
                            <label class="control-label" style="width: 50px;margin-right:5px;">电厂:</label>
                            <select id="selectType" ng-model="search.diancid" style="width:120px;"
                                    ng-options="option.value as option.name for option in diancList"></select>
                            <button style="margin-left: 10px;" ng-click="refresh()" class="btn btn-primary">
                                <i class="icon-search icon-white"></i> 刷新
                            </button>
                            <button style="margin-left: 10px;" ng-click="showHetmb()" class="btn btn-primary">
                                <i class="icon-plus icon-white"></i> 添加
                            </button>
                            <button style="margin-left: 10px;" ng-click="editHetbean()" class="btn btn-info">
                                <i class="icon-edit icon-white"></i> 修改
                            </button>
                            <button style="margin-left: 10px;" ng-click="delHetbean()" class="btn btn-danger">
                                <i class="icon-trash icon-white"></i> 删除
                            </button>
                            <button style="margin-left: 10px;" ng-click="uploadFuj()" class="btn btn-primary">
                                <i class="icon-file icon-white"></i> 上传附件
                            </button>
                            <button style="margin-left: 10px;" ng-click="commitHt()" class="btn btn-primary">
                                <i class="icon-file icon-white"></i> 提交合同
                            </button>
                        </div>
                    </fieldset>
                </form>
            </div>
        </div>
        <div class="row-fluid">
            <table class="table table-striped table-bordered table-hover"
                   id="example"
                   my-table="overrideOptions"
                   aa-data="hetbeanArray"
                   ao-column-defs="columnDefs"
                   my-selection=single
            >
                <thead>
                <tr>
                    <th style="text-align: center;width: 2% "></th>
                    <th style="text-align: center;width:15%">合同编号</th>
                    <th style="text-align: center;width: 10%">供应商</th>
                    <th style="text-align: center;width:10%">签订日期</th>
                    <th style="text-align: center;width:10%">状态</th>
                    <th style="text-align: center;width:10%">录入员</th>
                    <th style="text-align: center;width:43%">附件</th>
                </tr>
                </thead>
            </table>
        </div>
    </div>

    <!--弹出窗口 -->
    <div class="modal hide fade" id="myModal" style="width: auto;">
        <div class="modal-header">
            <a class="close" data-dismiss="modal">×</a>
            <h4>选择合同模板</h4>
        </div>
        <div class="modal-body">
            <table cellpadding="0" cellspacing="0" border="0"
                   class="table table-striped table-bordered">
                <thead>
                <tr>
                    <th style="text-align: center; width: 300px;">模板名称</th>
                    <th style="text-align: center; width: 150px;">模板路径</th>
                    <th style="text-align: center;">操作</th>
                </tr>
                </thead>

                <tbody>
                <tr ng-repeat="hetmb in hetmbList">
                    <td class="center">{{hetmb.mingc}}</td>
                    <td class="center">{{hetmb.luj}}</td>
                    <td style="text-align: center;">
                        <a href="{{hetmb.mublj}}" target="_blank">
                            <button class="btn btn-success btn-mini">
                                <i class="icon-zoom-in icon-white" title="预览"></i>
                            </button>
                        </a>
                        <button class="btn btn-success btn-mini" ng-click="addhetmb(hetmb);"
                                ng-if="hetmb.id != addHetmb.id">
                            <i class="icon-ok icon-white" title="选择" ng-if="hetmb.id != addHetmb.id"></i>
                        </button>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="modal-footer">
            <button ng-click="toAddht()" class="btn" data-dismiss="modal"><i class="icon-remove"></i> 确认</button>
        </div>
    </div>
    <div my-upload="isShow" swf-upload="swfUpload" complete="refresh()" file-list="fileList" ></div>
    <div class="modal fade" id="myModal_Del" tabindex="-1" role="dialog" style="display:none;"
         aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"
                            aria-hidden="true">×
                    </button>
                    <h4 class="modal-title" id="myModalLabel">删除合同</h4>
                </div>
                <div class="modal-body">
                    <div class="block-content collapse in">
                        <div class="span12">
                            确认删除？
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-danger" ng-click="deleteHetbean()"><i
                            class="icon-ok-sign icon-white"></i> 确认
                    </button>
                    <button type="button" class="btn" data-dismiss="modal"><i class="icon-remove-circle"></i> 取消
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>






<script type="text/javascript">
    var oTable;
    $(document).ready(function () {
        $("#datepicker1").datepicker({
            format: 'yyyy-mm-dd',
            minViewMode: 0,
            language: "zh-CN",
            autoclose: true
        });
        $("#datepicker2").datepicker({
            format: 'yyyy-mm-dd',
            minViewMode: 0,
            language: "zh-CN",
            autoclose: true
        });
    });


</script>