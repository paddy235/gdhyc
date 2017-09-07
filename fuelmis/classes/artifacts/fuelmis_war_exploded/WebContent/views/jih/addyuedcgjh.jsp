   <%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
    <div class="block" ng-controller="AddyuedcgjhCtrl">
    <div class="navbar navbar-inner block-header">
        <div class="muted pull-left">{{yuddcaigjhTitle}}</div>
    </div>
    <div class="block-content collapse in">
        <div class="span12 ">
            <!-- BEGIN FORM-->
			<form action="#" id="form_sample_1" class="form-horizontal">
				<fieldset>
					<div class="control-group ">
						<label class="control-label ">供应商</label>
						<div class="controls ">
							<select  ng_model="caigbean.GONGYSB_ID" ng-options="option.value as option.name for option in gongsyList" >
			  				</select>
						</div>
					</div>
					<div class="control-group ">
						<label class="control-label ">煤矿单位</label>
						<div class="controls ">
							<select  ng_model="caigbean.MEIKXXB_ID" >
								<option value="{{meik.value}}"  ng-repeat="meik in meikxxList">{{meik.name}}</option>
			  				</select>
						</div>
					</div>
					<div class="control-group ">
						<label class="control-label ">计划口径</label>
						<div class="controls ">
							<select  ng_model="caigbean.JIHKJB_ID" >
								<option value="{{jihkj.value}}"  ng-repeat="jihkj in JihkjList">{{jihkj.name}}</option>
			  				</select>
						</div>
					</div>
					<div class="control-group ">
						<label class="control-label ">品种</label>
						<div class="controls ">
							<select id="selectType" ng_model="caigbean.PINZB_ID" >
								<option value="{{pinz.value}}"  ng-repeat="pinz in pinzList">{{pinz.name}}</option>
			  				</select>
						</div>
					</div>
					<div class="control-group ">
						<label class="control-label ">发站</label>
						<div class="controls ">
							<select  ng_model="caigbean.FAZ_ID" >
								<option value="{{faz.value}}"  ng-repeat="faz in fazList">{{faz.name}}</option>
			  				</select>
						</div>
					</div>
					<div class="control-group ">
						<label class="control-label ">采购量</label>
						<div class="controls ">
							<input type="text" placeholder="" ng-model="caigbean.JIH_SL" />&nbsp;吨
						</div>
					</div>
					<div class="control-group ">
						<label class="control-label ">热值</label>
						<div class="controls ">
							<input type="text" placeholder="" ng-model="caigbean.JIH_REZ" />&nbsp;MJ/Kg
						</div>
					</div>
					<div class="control-group ">
						<label class="control-label ">硫分</label>
						<div class="controls ">
							<input type="text" placeholder="" ng-model="caigbean.JIH_LIUF" />&nbsp;%
						</div>
					</div>
					<div class="control-group ">
						<label class="control-label ">挥发分</label>
						<div class="controls ">
							<input type="text" placeholder="" ng-model="caigbean.JIH_HFF" />&nbsp;%
						</div>
					</div>
					<div class="control-group ">
						<label class="control-label ">车板价</label>
						<div class="controls ">
							<input type="text" placeholder="" ng-model="caigbean.JIH_MEIJ"/>&nbsp;元/吨
						</div>
					</div>
					<div class="control-group " style="display:none">
						<label class="control-label ">车板价(不含税)</label>
						<div class="controls ">
							<input type="text" readonly="readonly" value ="{{caigbean.JIH_MEIJ/1.17}}" placeholder="" ng-model="caigbean.JIH_MEIJBHS"/>&nbsp;元/吨
						</div>
					</div>
					<div class="control-group ">
						<label class="control-label ">运费</label>
						<div class="controls ">
							<input type="text" placeholder="" ng-model="caigbean.JIH_YUNJ" />&nbsp;元/吨
						</div>
					</div>
					<div class="control-group " style="display:none">
						<label class="control-label " >运费(不含税)</label>
						<div class="controls ">
							<input type="text" readonly="readonly" value="{{caigbean.JIH_YUNJ/1.11*0.11}}" placeholder="" ng-model="caigbean.JIH_YUNJBHS"/>&nbsp;元/吨
						</div>
					</div>
					<div class="control-group ">
						<label class="control-label ">杂费</label>
						<div class="controls ">
							<input type="text" placeholder="" ng-model="caigbean.JIH_ZAF"  id="yewlx" />&nbsp;元/吨
						</div>
					</div>	
					<div class="control-group ">
						<label class="control-label ">杂费(不含税)</label>
						<div class="controls ">
							<input type="text" placeholder="" ng-model="caigbean.JIH_ZAFBHS" />&nbsp;元/吨
						</div>
					</div>
					<div class="control-group " style="display:none">
						<label class="control-label ">到厂价</label>
						<div class="controls ">
							<input type="text" readonly="readonly" value="{{caigbean.JIH_MEIJ+caigbean.JIH_YUNJ+caigbean.JIH_ZAF}}" placeholder="" ng-model="caigbean.JIH_DAOCJ" />&nbsp;元/吨
						</div>
					</div>
					<div class="control-group " style="display:none">
						<label class="control-label ">到厂价(不含税)</label>
						<div class="controls ">
							<input type="text" readonly="readonly" value="{{caigbean.JIH_MEIJ/1.17+caigbean.JIH_YUNJ/1.11*0.11+caigbean.JIH_ZAFBHS}}" placeholder="" ng-model="caigbean.JIH_DAOCJBHS" />&nbsp;元/吨
						</div>
					</div>
					<div class="control-group " style="display:none">
						<label class="control-label ">到厂标煤单价</label>
						<div class="controls ">
							<input type="text" readonly="readonly" value="{{(caigbean.JIH_MEIJ+caigbean.JIH_YUNJ+caigbean.JIH_ZAF)*29.271/caigbean.JIH_REZ}}" placeholder="" ng-model="caigbean.JIH_DAOCBMDJ" />&nbsp;元/吨
						</div>
					</div>
					<div class="control-group " style="display:none">
						<label class="control-label ">到厂标煤单价(不含税)</label>
						<div class="controls ">
							<input type="text" readonly="readonly" value="{{(caigbean.JIH_MEIJ+caigbean.JIH_YUNJ+caigbean.JIH_ZAF)*29.271/caigbean.JIH_REZ}}" placeholder="" ng-model="caigbean.JIH_DAOCBMDJBHS"/>&nbsp;元/吨
						</div>
					</div>
					<div class="control-group " style="display:none;">
						<label class="control-label ">日期</label>
						<div class="controls ">
							<input type="text" ng-model="caigbean.RIQ"/>
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
							<button type="button" style="margin-left: 10px;" class="btn"  ng-click="cancel()">
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