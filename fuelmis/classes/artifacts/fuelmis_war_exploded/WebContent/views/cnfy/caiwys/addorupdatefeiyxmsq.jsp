   <%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
    <div class="block" ng-controller="addorupdatefeiyxmsqCtrl">

    <div class="navbar navbar-inner block-header">
        <div class="muted pull-left">{{addorupdatefeiyxmsqTitle}}</div>
    </div>
    
    <div class="block-content collapse in">
        <div class="span11">
            <!-- BEGIN FORM-->
			<form class="form-horizontal">
        <div style="height:50px;"></div>
	      <div class="control-group">
	        <label class="control-label" >费用名称</label>
	        <div class="controls">
	        	<input type="text" ng-model="feiyxmbean.MINGC" id="MINGC" >
	        </div>
	      </div>
	      <div class="control-group">
	        <label class="control-label" >编码</label>
	        <div class="controls">
	        	<input type="text" ng-model="feiyxmbean.BIANM" id="BIANM" >
	        </div>
	      </div>
	      <div class="control-group">
	        <label class="control-label" >说明</label>
	        <div class="controls">
	        	<input type="text" ng-model="feiyxmbean.SHUOM" id="SHUOM" >
	        </div>
	      </div>
	      <div class="control-group">
	        <label class="control-label" >管理分类</label>
	        <div class="controls">
	        	<input type="text" ng-model="feiyxmbean.GUANLFL" value="" id="GUANLFL" >
	        </div>
	      </div>
	      <div class="control-group">
	        <label class="control-label" >财务分类</label>
	        <div class="controls">
	        	<input type="text" ng-model="feiyxmbean.CAIWFL" value=""  id="CAIWFL" >
	        </div>
	      </div>
	      <div class="control-group">
	        <label class="control-label" >属性</label>
	        <div class="controls">
	          <select id="selectType" ng_model="feiyxmbean.FEIYXMSX" >
						<option value="{{feiyxmsx.value}}"  ng-repeat="feiyxmsx in feiyxmsxList">{{feiyxmsx.name}}</option>
			  </select>
	        </div>
	      </div>
	      <div class="control-group" style="display:none">
	        <label class="control-label" >费用项目分类</label>
	        <div class="controls">
	          <input type="text" ng-model="feiyxmbean.FEIYXMFLID" id="FEIYXMFLID" >
	        </div>
	      </div>
	      <div class="control-group" style="display:none">
	        <label class="control-label" >电厂id</label>
	        <div class="controls">
	          <input type="text" ng-model="feiyxmbean.DIANCID" />
	        </div>
	      </div>
	      <div class="control-group"  style="display:none">
	        <label class="control-label" >id</label>
	        <div class="controls">
	          <input type="text" ng-model="feiyxmbean.ID" />
	        </div>
	      </div>
	      <div class="control-group">
			<div class="controls">
				<button type="button" style="margin-left: 20px;" class="btn btn-primary" ng-click="saveYustz();">
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