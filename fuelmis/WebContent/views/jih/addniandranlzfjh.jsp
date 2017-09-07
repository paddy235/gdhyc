   <%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
    <div class="block" ng-controller="AddniandranlzfCtrl">

    <div class="navbar navbar-inner block-header">
        <div class="muted pull-left">{{addranlzfTitle}}</div>
    </div>
        <form class="form-horizontal">
        <div style="height:50px;"></div>
	      <div class="control-group">
	        <label class="control-label" for="inputEmail">费用名称</label>
	        <div class="controls">
	          <select id="selectType" ng_model="ranlzfbean.ZAFMC" ng-options="option.value as option.name for option in zafList" >
						<!-- <option value="{{zafmingc.value}}"  ng-repeat="zafmingc in zafList">{{zafmingc.name}}</option> -->
					</select>
	        </div>
	      </div>
	      <div class="control-group">
	        <label class="control-label" >{{nianf}}年预付金额（元）</label>
	        <div class="controls">
	          <input type="text" ng-model="ranlzfbean.YUCJE" id="YUCJE" >
	        </div>
	      </div>
	      <div class="control-group">
	        <label class="control-label" >预算年度费用说明</label>
	        <div class="controls">
	        	<input type="text" ng-model="ranlzfbean.YUCSM" id="YUCSM" >
	        </div>
	      </div>

	      <div class="control-group">
	        <label class="control-label" >上一年已完成金额</label>
	        <div class="controls">
	        	<input type="text" ng-model="ranlzfbean.SHIJWCJE" id="SHIJWCJE" >
	        </div>
	      </div>
	      <div class="control-group">
	        <label class="control-label" >上一年未完成金额</label>
	        <div class="controls">
	        	<input type="text" ng-model="ranlzfbean.YUJWCJE" id="YUJWCJE" >
	        </div>
	      </div>
	      <div class="control-group">
	        <label class="control-label" >上一年预计完成金额</label>
	        <div class="controls">
	        	<input type="text" readonly="readonly" ng-model="ranlzfbean.SHNYJWCJE" value="" id="SHNYJWCJE" >
	        </div>
	      </div>
	      <div class="control-group">
	        <label class="control-label" >上一年费用情况说明</label>
	        <div class="controls">
	        	<input type="text" ng-model="ranlzfbean.YUJWCSM" id="YUJWCSM" >
	        </div>
	      </div>
	      <div class="control-group" style="display:none" >
	        <label class="control-label" >日期</label>
	        <div class="controls">
	          <input type="text" ng-model="ranlzfbean.RIQ"/>
	        </div>
	      </div>
	      <div class="control-group" style="display:none">
	        <label class="control-label" >电厂id</label>
	        <div class="controls">
	          <input type="text" ng-model="ranlzfbean.DIANCID" />
	        </div>
	      </div>
	      <div class="control-group" style="display:none">
	        <label class="control-label" >id</label>
	        <div class="controls">
	          <input type="text" ng-model="ranlzfbean.ID" />
	        </div>
	      </div>
	      
	      <div class="control-group">
			<div class="controls">
				<button type="button" style="margin-left: 20px;" class="btn btn-primary" ng-click="saveRanlzf();">
					<i class="icon-check icon-white"></i> 保存
				</button>
				<button type="button" style="margin-left: 10px;" class="btn"  ng-click="cancel()">
					<i class="icon-repeat"></i> 返回
				</button>
			</div>
		  </div>
    	</form>
</div>
<script type="text/javascript">
	$("#SHIJWCJE").change(function(){
		$("#SHNYJWCJE").val(Number($("#SHIJWCJE").val())+Number($("#YUJWCJE").val())) ;
	});
	$("#YUJWCJE").change(function(){
		$("#SHNYJWCJE").val(Number($("#SHIJWCJE").val())+Number($("#YUJWCJE").val())) ;
	});
</script>