   <%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
    <div class="block" ng-controller="addorupdateyuedyssqCtrl">

    <div class="navbar navbar-inner block-header">
        <div class="muted pull-left">{{addorupdateyudyussqTitle}}</div>
    </div>
    
    <div class="block-content collapse in">
        <div class="span11">
            <!-- BEGIN FORM-->
			<form class="form-horizontal">
        <div style="height:50px;"></div>
	      <div class="control-group">
	        <label class="control-label" for="inputEmail">厂内费用名称</label>
	        <div class="controls">
	          <select id="selectType" ng_model="yudysbean.FEIYMC" >
						<option value="{{zaf.value}}"  ng-repeat="zaf in zafList">{{zaf.name}}</option>
			  </select>
	        </div>
	      </div>
	      <div class="control-group">
	        <label class="control-label" for="inputPassword">预算额度（元）</label>
	        <div class="controls">
	          <input type="text" ng-model="yudysbean.YUSED" id="YUCJE" >
	        </div>
	      </div>
	      <div class="control-group">
	        <label class="control-label" for="inputPassword">说明</label>
	        <div class="controls">
	        	<input type="text" ng-model="yudysbean.SHUOM" id="YUCSM" >
	        </div>
	      </div>
	      <div class="control-group" style="display:none">
	        <label class="control-label" for="inputPassword">日期</label>
	        <div class="controls">
	          <input type="text" ng-model="yudysbean.RIQ"/>
	        </div>
	      </div>
	      <div class="control-group" style="display:none">
	        <label class="control-label" for="inputPassword">电厂id</label>
	        <div class="controls">
	          <input type="text" ng-model="yudysbean.DIANCID" />
	        </div>
	      </div>
	      <div class="control-group" style="display:none">
	        <label class="control-label" for="inputPassword">id</label>
	        <div class="controls">
	          <input type="text" ng-model="yudysbean.ID" />
	        </div>
	      </div>
	      <div class="control-group">
			<div class="controls">
				<button type="button" style="margin-left: 20px;" class="btn btn-primary" ng-click="saveYuedys();">
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