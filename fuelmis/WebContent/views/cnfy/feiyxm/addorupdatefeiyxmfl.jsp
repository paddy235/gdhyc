   <%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
    <div class="block" ng-controller="addorupdatefeiyxmflCtrl">

    <div class="navbar navbar-inner block-header">
        <div class="muted pull-left">{{addorupdatefeiyxmflTitle}}</div>
    </div>
    
    <div class="block-content collapse in">
        <div class="span11">
            <!-- BEGIN FORM-->
			<form class="form-horizontal">
        <div style="height:50px;"></div>
	      <div class="control-group">
	        <label class="control-label" for="inputEmail">分类口径</label>
	        <div class="controls">
	          <select id="selectType" ng_model="fenlkjbean.FENLKJ" >
						<option value="{{fenlkj.value}}"  ng-repeat="fenlkj in fenlkjList">{{fenlkj.name}}</option>
			  </select>
	        </div>
	      </div>
	      <div class="control-group">
	        <label class="control-label" for="inputPassword">名称</label>
	        <div class="controls">
	          <input type="text" ng-model="fenlkjbean.MINGC"  >
	        </div>
	      </div>
	      <div class="control-group">
	        <label class="control-label" for="inputPassword">说明</label>
	        <div class="controls">
	        	<input type="text" ng-model="fenlkjbean.SHUOM" id="YUCSM" >
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
				<button type="button" style="margin-left: 20px;" class="btn btn-primary" ng-click="saveFenyxm();">
					<i class="icon-check icon-white"></i> 保存
				</button>
				<button type="button" style="margin-left: 10px;" class="btn"  ng-click="cancel()">
					<i class="icon-repeat"></i> 返回
				</button>
			</div>
		  </div>
    	</form>
			<!-- END FORM-->
        </div>
    </div>
</div>