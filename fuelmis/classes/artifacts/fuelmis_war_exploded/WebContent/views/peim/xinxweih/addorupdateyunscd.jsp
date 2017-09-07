   <%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
    <div class="block" ng-controller="addorupdateyunscdCtrl">

    <div class="navbar navbar-inner block-header">
        <div class="muted pull-left">{{addorupdateyunscdTitle}}</div>
    </div>
    
    <div class="block-content collapse in">
        <div class="span11">
            <!-- BEGIN FORM-->
			<form class="form-horizontal">
        <div style="height:50px;"></div>
	      <div class="control-group">
	        <label class="control-label" for="inputPassword">运输车队名称</label>
	        <div class="controls">
	          <input type="text" ng-model="fenlkjbean.CHEDMC" >
	        </div>
	      </div>
	      <div class="control-group" style="display:none">
	        <label class="control-label" for="inputPassword">id</label>
	        <div class="controls">
	          <input type="text" ng-model="fenlkjbean.ID" />
	        </div>
	      </div>
	      <div class="control-group">
			<div class="controls">
				<button type="button" class="btn"  ng-click="cancel()">返回</button>
				<button type="button" style="margin-left: 20px;" class="btn btn-primary" ng-click="saveFenyxm();">保存</button>
			</div>
		  </div>
    	</form>
			<!-- END FORM-->
        </div>
    </div>
</div>