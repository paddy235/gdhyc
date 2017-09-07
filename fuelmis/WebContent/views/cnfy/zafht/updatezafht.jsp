   <%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
    <div class="block" ng-controller="updatezafhtCtrl">
    <div class="navbar navbar-inner block-header">
        <div class="muted pull-left">{{updatezafhtTitle}}</div>
    </div>
    <div class="block-content collapse in">
        <div class="span11">
            <!-- BEGIN FORM-->
			<form class="form-horizontal">
        <div style="height:50px;"></div>
        <div class="control-group">
	        <label class="control-label" >合同名称：</label>
	        <div class="controls">
	          <input type="text" ng-model="hetbean.MINGC">
	        </div>
	      </div>
	      <div class="control-group">
	        <label class="control-label" >合同编号：</label>
	        <div class="controls">
	          <input type="text" ng-model="hetbean.BIANH">
	        </div>
	      </div>
	      <div class="control-group">
	        <label class="control-label" for="inputEmail">甲方：</label>
	        <div class="controls">
	          <select id="selectType" ng_model="hetbean.JIAF">
						<option value="{{dianc.value}}"  ng-repeat="dianc in diancList">{{dianc.name}}</option>
			  </select>
	        </div>
	      </div>
	      <div class="control-group">
	        <label class="control-label" >乙方：</label>
	        <div class="controls">
	          <input type="text" ng-model="hetbean.YIF">
	        </div>
	      </div>
	      <div class="control-group">
	        <label class="control-label" >签订日期：</label>
	        <div class="controls">
	          <input  class="datepicker" ng-model="hetbean.QIANDRQ"  type="text">
	        </div>
	      </div>
	      <div class="control-group">
	        <label class="control-label" >一、甲乙双方责任：</label>
	        <div class="controls">
	          <textarea ng-model="hetbean.JIAYSFZR"  ></textarea>
	        </div>
	      </div>
	      
	      <div  class="control-group">
	      <label class="control-label" >二、费用：</label>
	      	<br/>
	      	<div class="controls">
		      	<div class="control-group"  ng-repeat="cnfyxm in cnfyxmList">
		        	<label class="control-label" >{{cnfyxm.MINGC}}</label>
		        	<div class="controls">
		          		<input class="cnfy_model" type="text" name="{{cnfyxm.ID}}"  value="{{cnfyxm.FEIYJE}}"><span>{{cnfyxm.DANW}}</span>
		       		</div>
		      	</div> 
		      </div>
	      </div> 
	      
	      <div class="control-group">
	        <label class="control-label" >三、违约责任：</label>
	        <div class="controls">
	          <textarea ng-model="hetbean.WEIYZR"  ></textarea>
	        </div>
	      </div>
	      <div class="control-group">
	        <label class="control-label" >四、付款方式：</label>
	        <div class="controls">
	          <textarea ng-model="hetbean.FUKFS"  ></textarea>
	        </div>
	      </div>
	      <div class="control-group">
	        <label class="control-label" >五、安全治安方面的约定：</label>
	        <div class="controls">
	          <textarea ng-model="hetbean.ANQZAFMYD"></textarea>
	        </div>
	      </div>
	      <div class="control-group">
	        <label class="control-label" >六、合同有效期：</label>
	        <div class="controls">
	          <input  class="datepicker" ng-model="hetbean.QISRQ"  type="text">至 <input  class="datepicker" ng-model="hetbean.JIESRQ"  type="text">
	        </div>
	      </div>
	      <div class="control-group">
	        <label class="control-label" >七、其他事宜：</label>
	        <div class="controls">
	          <textarea ng-model="hetbean.QIT"  ></textarea>
	        </div>
	      </div>
	      <div class="control-group">
	        <label class="control-label" >八、签订地点：</label>
	        <div class="controls">
	          <textarea ng-model="hetbean.QIANDDD"  ></textarea>
	        </div>
	      </div>
	      <div class="control-group">
	        <label class="control-label" >九、签字页：</label>
	        <div class="controls">
	          <textarea ng-model="hetbean.QIANZY"  ></textarea>
	        </div>
	      </div>
	      <div class="control-group">
			<div class="controls">
				<button type="button" style="margin-left: 20px;" class="btn btn-primary" ng-click="udpateHet();">
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