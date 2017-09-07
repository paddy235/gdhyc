<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<style>
    .diss{
        display: block;
    }
</style>

<div ng-class="{in: myProgress, diss: myProgress}" data-backdrop="static" class="modal fade" id="myModal"
     tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body" >
                <div class="span3"><img style="width:100px;height:100px" src="images/circle-bar.gif"/></div>
                <div class="span6" style="height:100px;"><br><h3>正在执行请稍后......</h3></div>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<div ng-show="myProgress" class="modal-backdrop fade in"></div>
