<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link type="text/css" rel="stylesheet" href="/fuelmis/css/upload.css"/>
<link href="/fuelmis/js/ligerUI/skins/Aqua/css/ligerui-all.css" type="text/css" rel="stylesheet"/>

<style>
    .diss{
        display: block;
    }
</style>

<div ng-class="{in: myUpload, diss: myUpload}" data-backdrop="static" class="modal fade" id="myModal"
     tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body" >
                <div id="fileList">
                    <table>
                        <tbody>
                            <tr ng-repeat="file in fileList">
                                <td>{{file.NAME}}</td>
                                <td>
                                    <button style="margin-left: 10px;" ng-click="delFile(file.ID)" class="btn btn-danger">
                                        <i class="icon-trash icon-white"></i> 删除
                                    </button>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
               <%-- <div class="span3"><img style="width:100px;height:100px" src="images/circle-bar.gif"/></div>
                <div class="span6" style="height:100px;"><br><h3>正在执行请稍后......</h3></div>--%>
                   <input type="hidden" id="contextPath" value="/fuelmis"  />
                   <div id="swfupload">
                       <p id="queueStatus"></p>
                       <ol id="logList"></ol>
                   </div>
            </div>
            <div class="modal-footer">
                <button id="spanButtonPlaceholder"></button>
                <button type="button" class="btn" ng-click="close()"><i class="icon-remove-circle"></i> 关闭</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<div ng-show="myUpload" class="modal-backdrop fade in"></div>







<%--

<div class="modal fade" id="fujModal" tabindex="-1" role="dialog" 
     aria-labelledby="myModalLabel" style="display: none;">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×
                </button>
                <h4 class="modal-title" id="">附件上传</h4>
            </div>
            <div class="modal-body">
                <div class="block-content collapse in">
                    <div class="span12">
                        <div class="control-group">

                            <input type="hidden" id="contextPath" value="/fuelmis"  />
                            <div id="swfupload">
                                <button id="spanButtonPlaceholder"></button>
                                <p id="queueStatus"></p>
                                <ol id="logList"></ol>
                            </div>

                        </div>

                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn" data-dismiss="modal"><i class="icon-remove-circle"></i> 关闭</button>
            </div>
        </div>
    </div>
</div>--%>
