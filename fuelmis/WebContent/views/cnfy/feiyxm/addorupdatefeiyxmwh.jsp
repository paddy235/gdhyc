   <%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
    <div class="block" ng-controller="addorupdatefeiyxmwhCtrl">

    <div class="navbar navbar-inner block-header">
        <div class="muted pull-left">{{addorupdatefeiyxmwhTitle}}</div>
    </div>
    
    <div class="block-content collapse in">
        <div class="span11">
            <!-- BEGIN FORM-->
			<form class="form-horizontal">
        <div style="height:50px;"></div>
	      <div class="control-group">
	        <label class="control-label" >名称</label>
	        <div class="controls">
	        	<input type="text" ng-model="feiyxmwh.MINGC" id="MINGC" >
	        </div>
	      </div>
	      <div class="control-group">
	        <label class="control-label" >说明</label>
	        <div class="controls">
	        	<input type="text" ng-model="feiyxmwh.SHUOM" id="SHUOM" >
	        </div>
	      </div>
	      <div class="control-group">
	        <label class="control-label" >费用单位</label>
	        <div class="controls">
	        	<input type="text" ng-model="feiyxmwh.DANW" id="DANW" >
	        </div>
	      </div>
	      <div class="control-group">
	        <label class="control-label" >管理分类</label>
	        <div class="controls">
	        	<input type="text" ng-model="feiyxmwh.GUANLFL" value="" id="GUANLFL" >
	        </div>
	      </div>
	      <div class="control-group">
	        <label class="control-label" >财务分类</label>
	        <div class="controls">
	        	<input type="text" ng-model="feiyxmwh.CAIWFL" value=""  id="CAIWFL" >
	        </div>
	      </div>
	      <div class="control-group">
	        <label class="control-label" >属性</label>
	        <div class="controls">
	          <select id="selectType" ng_model="feiyxmwh.FEIYXMSX" >
						<option value="{{feiyxmsx.value}}"  ng-repeat="feiyxmsx in feiyxmsxList">{{feiyxmsx.name}}</option>
			  </select>
	        </div>
	      </div>
	      <div class="control-group">
	        <label class="control-label" >费用项目分类</label>
	        <div class="controls">
	          <select id="selectType" ng_model="feiyxmwh.FEIYXMFLID" >
						<option value="{{feiyxmfl.value}}"  ng-repeat="feiyxmfl in feiyxmflList">{{feiyxmfl.name}}</option>
			  </select>
	        </div>
	      </div>
	      <div class="control-group"  style="display:none">
	        <label class="control-label" >id</label>
	        <div class="controls">
	          <input type="text" ng-model="feiyxmwh.ID" />
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