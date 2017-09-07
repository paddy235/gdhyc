   <%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
    <div class="block" ng-controller="addorupdatemeishanCtrl">

    <div class="navbar navbar-inner block-header">
        <div class="muted pull-left">{{addorupdatemeishanTitle}}</div>
    </div>
    
    <div class="block-content collapse in">
        <div class="span11">
            <!-- BEGIN FORM-->
			<form class="form-horizontal">
        <div style="height:50px;"></div>
          <div class="control-group">
	        <label class="control-label" for="inputPassword">煤山名称</label>
	        <div class="controls">
	          <input type="text" ng-model="fenlkjbean.MEISMC"  >
	        </div>
	      </div>
	      <div class="control-group">
	        <label class="control-label" for="inputEmail">所属单位</label>
	        <div class="controls">
	          <select id="selectType" ng_model="fenlkjbean.DIANCXXB_ID" >
						<option value="{{fenlkj.value}}"  ng-repeat="fenlkj in diancList">{{fenlkj.name}}</option>
			  </select>
	        </div>
	      </div>
	      
	      <div class="control-group">
	        <label class="control-label" for="inputPassword">煤场</label>
	        <div class="controls">
	        	<input type="text" ng-model="fenlkjbean.MEICMC" id="YUCSM" >
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