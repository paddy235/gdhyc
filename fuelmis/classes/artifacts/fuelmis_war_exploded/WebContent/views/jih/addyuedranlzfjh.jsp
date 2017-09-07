   <%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
    <div class="block" ng-controller="AddyuedranlzfCtrl">

    <div class="navbar navbar-inner block-header">
        <div class="muted pull-left">{{addranlzfTitle}}</div>
    </div>
        <form class="form-horizontal">
        <div style="height:50px;"></div>
	      <div class="control-group">
	        <label class="control-label" for="inputEmail">费用名称</label>
	        <div class="controls">
	          <select id="selectType" ng_model="ranlzfbean.ZAFMC" >
						<option value="{{zafmingc.value}}"  ng-repeat="zafmingc in zafList">{{zafmingc.name}}</option>
			  </select>
	        </div>
	      </div>
	      <div class="control-group">
	        <label class="control-label" for="inputPassword">预付金额（元）</label>
	        <div class="controls">
	          <input type="text" ng-model="ranlzfbean.YUCJE" id="YUCJE" >
	        </div>
	      </div>
	      <div class="control-group">
	        <label class="control-label" for="inputPassword">预算费用说明</label>
	        <div class="controls">
	        	<input type="text" ng-model="ranlzfbean.YUCSM" id="YUCSM" >
	        </div>
	      </div>
	      <div class="control-group" style="display:none">
	        <label class="control-label" for="inputPassword">日期</label>
	        <div class="controls">
	          <input type="text" ng-model="ranlzfbean.RIQ"/>
	        </div>
	      </div>
	      <div class="control-group" style="display:none">
	        <label class="control-label" for="inputPassword">电厂id</label>
	        <div class="controls">
	          <input type="text" ng-model="ranlzfbean.DIANCID" />
	        </div>
	      </div>
	      <div class="control-group" style="display:none">
	        <label class="control-label" for="inputPassword">id</label>
	        <div class="controls">
	          <input type="text" ng-model="ranlzfbean.ID" />
	        </div>
	      </div>
	      <div class="control-group">
			<div class="controls">
				<button type="button" style="margin-left: 20px;" class="btn btn-primary" ng-click="saveRanlzf();">
					<i class="icon-check icon-white"></i> 保存
				</button>
				<button type="button" style="margin-left:10px;" class="btn"  ng-click="cancel()">
					<i class="icon-repeat"></i> 返回
				</button>
			</div>
		  </div>
    	</form>
</div>