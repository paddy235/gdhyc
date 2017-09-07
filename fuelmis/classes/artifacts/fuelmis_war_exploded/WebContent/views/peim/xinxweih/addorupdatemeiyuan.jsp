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
	        <label class="control-label" for="inputPassword">煤源</label>
	        <div class="controls">
	          <input type="text" ng-model="fenlkjbean.MEIYMC"  >
	        </div>
	      </div>
	      <div class="control-group">
	        <label class="control-label" for="inputPassword">热值</label>
	        <div class="controls">
	        	<input type="text" ng-model="fenlkjbean.QNET_AR" id="YUCSM" >
	        </div>
	      </div>
	      <div class="control-group">
	        <label class="control-label" for="inputPassword">硫分</label>
	        <div class="controls">
	        	<input type="text" ng-model="fenlkjbean.S" id="YUCSM" >
	        </div>
	      </div>
	      <div class="control-group">
	        <label class="control-label" for="inputPassword">挥发分</label>
	        <div class="controls">
	        	<input type="text" ng-model="fenlkjbean.V" id="YUCSM" >
	        </div>
	      </div>
	      <div class="control-group">
	        <label class="control-label" for="inputPassword">煤价</label>
	        <div class="controls">
	        	<input type="text" ng-model="fenlkjbean.MEIJ" id="YUCSM" >
	        </div>
	      </div>
	      <div class="control-group">
	        <label class="control-label" for="inputPassword">运费</label>
	        <div class="controls">
	        	<input type="text" ng-model="fenlkjbean.YUNJ" id="YUCSM" >
	        </div>
	      </div>
	      <div class="control-group">
	        <label class="control-label" for="inputPassword">标单</label>
	        <div class="controls">
	        	<input type="text" ng-model="fenlkjbean.BIAOMDJ" id="YUCSM" >
	        </div>
	      </div>
	      <div class="control-group">
	        <label class="control-label" for="inputPassword">最大采购量</label>
	        <div class="controls">
	        	<input type="text" ng-model="fenlkjbean.SHUL_MAX" id="YUCSM" >
	        </div>
	      </div>
	      <div class="control-group">
	        <label class="control-label" for="inputPassword">最小采购量</label>
	        <div class="controls">
	        	<input type="text" ng-model="fenlkjbean.SHUL_MIN" id="YUCSM" >
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