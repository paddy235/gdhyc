   <%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
    <div class="block" ng-controller="AddniandcaigjhCtrl">

    <div class="navbar navbar-inner block-header">
        <div class="muted pull-left">{{addNiandCaigTitle}}</div>
    </div>
    
    <div class="block-content collapse in">
        <div class="span11">
            <!-- BEGIN FORM-->
			<form action="#" id="form_sample_1" class="form-horizontal">
				<fieldset>
					<div class="control-group">
						<label class="control-label ">供货单位</label>
						<div class="controls ">
							<select id="selectType" ng-model="caigbean.GONGYSB_ID" >
								<option value="{{gongys.value}}"  ng-repeat="gongys in gongsyList">{{gongys.name}}</option>
				  			</select>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label ">计划口径</label>
						<div class="controls ">
							<select id="selectType" ng-model="caigbean.JIHKJB_ID" >
								<option value="{{jihkj.value}}"  ng-repeat="jihkj in JihkjList">{{jihkj.name}}</option>
				  			</select>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label ">合同数量</label>
						<div class="controls ">
							<input type="text"  ng-model="caigbean.HET_SL" />&nbsp; 吨
						</div>
					</div>
					<div class="control-group">
						<label class="control-label ">合同热值</label>
						<div class="controls ">
							<input type="text" placeholder="" ng-model="caigbean.HET_REZ"  />&nbsp;MJ/Kg
						</div>
					</div>
					<div class="control-group">
						<label class="control-label ">合同煤价</label>
						<div class="controls ">
							<input type="text" placeholder="" ng-model="caigbean.HET_MEIJ" />&nbsp;元/吨
						</div>
					</div>
					<div class="control-group">
						<label class="control-label ">合同运价</label>
						<div class="controls ">
							<input type="text" placeholder="" ng-model="caigbean.HET_YUNJ" />&nbsp;元/吨
						</div>
					</div>
					<div class="control-group">
						<label class="control-label ">预计到货量</label>
						<div class="controls ">
							<input type="text" placeholder="" ng-model="caigbean.JIH_SL" />&nbsp;吨
						</div>
					</div>
					<div class="control-group">
						<label class="control-label ">预计到货热值</label>
						<div class="controls ">
							<input type="text" placeholder="" ng-model="caigbean.JIH_REZ" />&nbsp;MJ/Kg
						</div>
					</div>
					<div class="control-group">
						<label class="control-label ">预计到货煤价</label>
						<div class="controls ">
							<input type="text" placeholder="" ng-model="caigbean.JIH_MEIJ" />&nbsp;元/吨
						</div>
					</div>
					<div class="control-group">
						<label class="control-label ">预计到货运价</label>
						<div class="controls ">
							<input type="text" placeholder="" ng-model="caigbean.JIH_YUNJ" />&nbsp;元/吨
						</div>
					</div>
					<div class="control-group">
						<label class="control-label ">预计其他费用</label>
						<div class="controls ">
							<input type="text" placeholder="" ng-model="caigbean.JIH_QIT"/>&nbsp;元/吨
						</div>
					</div>
					<div class="control-group" style="display:none">
						<label class="control-label ">预计其他费用(不含税)</label>
						<div class="controls ">
							<input type="text" placeholder="" ng-model="caigbean.JIH_QITBHS"/>&nbsp;元/吨
						</div>
						
					</div>
					<div class="control-group" style="display:none">
						<label class="control-label ">预计到厂价</label>
						<div class="controls ">
							<input type="text" placeholder="" ng-model="caigbean.JIH_DAOCJ" />&nbsp;元/吨
						</div>
					</div>
					<div class="control-group" style="display:none">
						<label class="control-label ">预计到厂标煤单价</label>
						<div class="controls ">
							<input type="text" placeholder="" ng-model="caigbean.JIH_DAOCBMDJ"/>&nbsp;元/吨
						</div>
					</div>	
					
					<div class="control-group" style="display:none">
						<label class="control-label ">日期</label>
						<div class="controls ">
							<input type="text" ng-model="caigbean.NIANF"/>
						</div>
						<label class="control-label ">电厂id</label>
						<div class="controls ">
							<input type="text" ng-model="caigbean.DIANCID" />
						</div>
						<label class="control-label ">主键id</label>
						<div class="controls ">
							<input type="text" ng-model="caigbean.ID" />
						</div>
					</div>
					<div class="control-group">
					<div class="span2"></div>
					<div class="span4">
						<button type="button" style="margin-left: 20px;" class="btn btn-primary" ng-click="saveCaig();">
							<i class="icon-check icon-white"></i> 保存
						</button>
						<button type="button" style="margin-left:10px;" class="btn"  ng-click="cancel()">
							<i class="icon-repeat"></i> 返回
						</button>
					</div>
					<div class="span6"></div>
					</div>
				</fieldset>
			</form>
			<!-- END FORM-->
        </div>
    </div>
</div>