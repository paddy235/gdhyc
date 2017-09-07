<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     
<div class="block" ng-controller="huaybmCtrl">
	<div class="navbar navbar-inner block-header">
		<div class="muted pull-left">化样编码转换</div>
	</div>
	<div class="block-content collapse in">
        <div class="span24">
		        	<div class="control-group">
		                 <button style="margin-left: 20px;" ng-click="load()" class="btn btn-success">
		                 	<i class="icon-search icon-white"></i> 刷新
		                 </button>
		                 <button style="margin-left: 10px;" ng-click="printPage()" class="btn btn-primary">
		                 	<i class="icon-print icon-white"></i> 打印
		                 </button>
		                 <button style="margin-left: 10px;" ng-click="scan()" id="scan" class="btn btn-primary">
		                 	<i class="icon-download-alt icon-white"></i> 扫描
		                 </button>
		
				    </div>	    
		<div class="control-group">
		<label
					style="margin-left: 10px;width: 60px; height: 30px; line-height: 30px;"
					class="control-label span1">串口名称:</label> 
					<select style="margin-left: 10px;width: 100px; float: left;" id="meik" ng-model="port">
						<option ng-repeat="row in list track by $index" >{{row}}</option>
					</select>
		</div>
        </div>
		<form id="form1" runat="server">
		<img  ng-src="{{img}}"  />
			<div class="row-fluid" id="report" style="text-align: center;">
				
				<img  ng-src="{{hauybmBar}}"  />
			</div>
			<br>
			<div class="row-fluid">
				<table class="table table-striped table-bordered table-hover"
					id="example">
					<thead>
						<tr>
							<th>参数名称</th>
							<th>值</th>
						</tr>
					</thead>
					<tbody>
						<tr  class="option">
							<td><input type="text" value="高度" onfocus=this.blur() /></td>
							<td><input type="text" ng-model="parameter.height" ng-blur="load()" /></td>
						</tr>
						<tr  class="option">
							<td><input type="text" value="线条粗细" onfocus=this.blur() /></td>
							<td><input type="text" ng-model="parameter.dimension" ng-blur="load()" /></td>
						</tr>
						<tr  class="option">
							<td><input type="text" value="是否显示编号" onfocus=this.blur() /></td>
							<td>			
								<select style="width:180px; float:left;" ng-model="parameter.showText"
									ng-change="load()">
									<option value="true"  >显示</option>
									<option value="false"  >隐藏</option>	
								</select>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</form>
   </div>
</div>

