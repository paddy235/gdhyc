   <%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
    <div class="block" ng-controller="zafjsCtrl">
	    <div class="navbar navbar-inner block-header">
	        <div class="muted pull-left">{{zafjsTitle}}</div>
	    </div>
    	<div class="block-content collapse in ">
			<div class="span12">
				<div class="table-toolbar">
					<button style="margin-left: 10px;"  class="btn" ng-click="reback()">
						<i class="icon-repeat"></i> 返回
					</button>
					<button style="margin-left: 10px;"  class="btn btn-primary" ng-click="addzaf()">
						<i class="icon-plus icon-white"></i> 新增
					</button>
					<button style="margin-left: 10px;" id="savezaffybxd" class="btn btn-success" ng-click="savezaffybxd()">
						<i class="icon-check icon-white"></i> 保存
					</button>
					<button style="margin-left: 5px;" ng-click="printPage()" class="btn btn-primary">
						<i class="icon-print icon-white"></i> 打印
					</button>
					<button style="margin-left: 5px;" my-export="杂费结算.xls" class="btn btn-primary">
						<i class="icon-download-alt icon-white"></i> 导出
					</button>
			 	</div>
			</div>
		</div>
		
		
		<div style="position:relative" id="baoxiaodanneirong">
		<div id="Layer1" style="position:absolute;top:60px;left:100px;"><img src="/fuelmis/images/gdbxdlogo.png"  align="left"/></div>
		<form id="form1" name="form1" method="post" action="savebxd">
			<div id="report">
			<table id = "main-div" width="85%" height="100%" border="0" align="center">
				<tr> 
				  <td colspan="11"></td>
				</tr>
				<tr>
					
					<td height="4%" colspan="8"></td>
					<td width="3%" height="4%" colspan="2"></td>
				</tr>
				<tr>
					<td colspan="10" align="center">
						<h1 align="center">
							<span><u><font style="font-size: 20px"> 国电电力发展股份有限公司</font></u></span>
						</h1>
					</td>
				</tr>
				<tr>
					<td colspan="10" align="center">
						<h2 align="center">
							<span><font style="font-size: 24px">国电新疆红雁池发电有限公司</font></span>	
						</h2>
					</td>
				</tr>
				<tr>
					<td colspan="10" align="center">
						<h4 align="center"><font>费 用 报 销 单</font></h4>
					</td>
				</tr>
				
				<tr>
					<td width="44%" height="4%" align="center"><div align="left">
							<span >编号：</span> <span >
							</span>
						</div></td>
					<td width="53%"><font size="+1" ></font><span >  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></td>
				</tr> 
				<tr>
				  <td height="366" colspan="11" align="center">
						<table width="100%" height="362" border="2" cellpadding="0" cellspacing="0" bordercolor="#000000">
							<tr>
								<td width="8%" height="45" align="center" bordercolor="#000000"
									class="class">经办人</td>
								<td width="22%" bordercolor="#000000"><div align="center">{{search.xingm}}</div></td>
								<td width="8%" bordercolor="#000000" align="center">所属部门</td>
								<td width="42%"><div align="center">{{search.department}}</div></td>
								<td colspan="10" width="20%" align="center" bordercolor="#000000" class="class">
									<div align="center">
										<span>金额</span>
									</div>
								</td>
							</tr>
							<tr>
								<td height="40"  align="center" bordercolor="#000000" class="class"><span
									>序号</span></td>
								<td colspan="3" bordercolor="#000000"><div align="center">
										<span >报&nbsp;&nbsp;&nbsp;&nbsp;销&nbsp;&nbsp;&nbsp;内&nbsp;&nbsp;&nbsp;&nbsp;容&nbsp;&nbsp;&nbsp;说&nbsp;&nbsp;&nbsp;明</span>
									</div></td>
							  	<td align="center" style="width:2%" bordercolor="#000000"
									class="class"><div align="center"><span >仟</span></div></td>
								<td align="center" style="width:2%" bordercolor="#000000"
									class="class"><div align="center"><span >百</span></div></td>
								<td align="center" style="width:2%" bordercolor="#000000"
									class="class"><div align="center"><span >十</span></div></td>
								<td align="center" style="width:2%" bordercolor="#000000"
									class="class"><div align="center"><span >万</span></div></td>
								<td align="center" style="width:2%" bordercolor="#000000"
									class="class"><div align="center"><span >仟</span></div></td>
								<td align="center" style="width:2%" bordercolor="#000000"
									class="class"><div align="center"><span >百</span></div></td>
								<td align="center" style="width:2%" bordercolor="#000000"
									class="class"><div align="center"><span >十</span></div></td>
								<td align="center" style="width:2%" bordercolor="#000000"
									class="class"><div align="center"><span >元</span></div></td>
								<td align="center" style="width:2%" bordercolor="#000000"
									class="class"><div align="center"><span >角</span></div></td>
								<td align="center" style="width:2%" bordercolor="#000000"
									class="class"><div align="center"><span >分</span></div></td>
							</tr>
							<tr>
								<td height="40" align="center" bordercolor="#000000" class="class"><span
									>1</span></td>
								<td colspan="3" bordercolor="#000000" >
								<div id="baoxd_0"><div align="left">
								  <p><span ng-click="updatezaf(arr[0][11])" >{{arr[0][0]}}</span></p>
								 </div>
								</div></td>
								<td bordercolor="#000000"><div align="center">{{arr[0][1]}}</div></td>
								<td bordercolor="#000000"><div align="center">{{arr[0][2]}}</div></td>
								<td bordercolor="#000000"><div align="center">{{arr[0][3]}}</div></td>
								<td bordercolor="#000000"><div align="center">{{arr[0][4]}}</div></td>
								<td bordercolor="#000000"><div align="center">{{arr[0][5]}}</div></td>
								<td bordercolor="#000000"><div align="center">{{arr[0][6]}}</div></td>
								<td bordercolor="#000000"><div align="center">{{arr[0][7]}}</div></td>
								<td bordercolor="#000000"><div align="center">{{arr[0][8]}}</div></td>
								<td bordercolor="#000000"><div align="center">{{arr[0][9]}}</div></td>
								<td bordercolor="#000000"><div align="center">{{arr[0][10]}}</div></td>
							</tr>
							<tr>
								<td height="40" align="center" bordercolor="#000000" class="class"><span
									>2</span></td>
							  <td colspan="3" bordercolor="#000000" >
							    <div id="div">
							      <div align="left"><p><span ng-click="updatezaf(arr[1][11])" >{{arr[1][0]}}</span></p></div>
						      </div>
							  </td>
								<td bordercolor="#000000"><div align="center">{{arr[1][1]}}</div></td>
								<td bordercolor="#000000"><div align="center">{{arr[1][2]}}</div></td>
								<td bordercolor="#000000"><div align="center">{{arr[1][3]}}</div></td>
								<td bordercolor="#000000"><div align="center">{{arr[1][4]}}</div></td>
								<td bordercolor="#000000"><div align="center">{{arr[1][5]}}</div></td>
								<td bordercolor="#000000"><div align="center">{{arr[1][6]}}</div></td>
								<td bordercolor="#000000"><div align="center">{{arr[1][7]}}</div></td>
								<td bordercolor="#000000"><div align="center">{{arr[1][8]}}</div></td>
								<td bordercolor="#000000"><div align="center">{{arr[1][9]}}</div></td>
								<td bordercolor="#000000"><div align="center">{{arr[1][10]}}</div></td>
							</tr>
							<tr>
								<td height="40" align="center" bordercolor="#000000" class="class"><span
									>3</span></td>
								<td colspan="3" bordercolor="#000000" >
								<div id="baoxd_2"><div align="left"><p><span ng-click="updatezaf(arr[2][11])" >{{arr[2][0]}}</span></p></div></div></td>
								<td bordercolor="#000000"><div align="center">{{arr[2][1]}}</div></td>
								<td bordercolor="#000000"><div align="center">{{arr[2][2]}}</div></td>
								<td bordercolor="#000000"><div align="center">{{arr[2][3]}}</div></td>
								<td bordercolor="#000000"><div align="center">{{arr[2][4]}}</div></td>
								<td bordercolor="#000000"><div align="center">{{arr[2][5]}}</div></td>
								<td bordercolor="#000000"><div align="center">{{arr[2][6]}}</div></td>
								<td bordercolor="#000000"><div align="center">{{arr[2][7]}}</div></td>
								<td bordercolor="#000000"><div align="center">{{arr[2][8]}}</div></td>
								<td bordercolor="#000000"><div align="center">{{arr[2][9]}}</div></td>
								<td bordercolor="#000000"><div align="center">{{arr[2][10]}}</div></td>
							</tr>
							<tr>
								<td height="40" align="center" bordercolor="#000000" class="class"><span >4</span></td>
								<td colspan="3" bordercolor="#000000">
								<div id="baoxd_3"><div align="left"><p><span ng-click="updatezaf(arr[3][11])" >{{arr[3][0]}}</span></p></div></div></td>
								<td bordercolor="#000000"><div align="center">{{arr[3][1]}}</div></td>
								<td bordercolor="#000000"><div align="center">{{arr[3][2]}}</div></td>
								<td bordercolor="#000000"><div align="center">{{arr[3][3]}}</div></td>
								<td bordercolor="#000000"><div align="center">{{arr[3][4]}}</div></td>
								<td bordercolor="#000000"><div align="center">{{arr[3][5]}}</div></td>
								<td bordercolor="#000000"><div align="center">{{arr[3][6]}}</div></td>
								<td bordercolor="#000000"><div align="center">{{arr[3][7]}}</div></td>
								<td bordercolor="#000000"><div align="center">{{arr[3][8]}}</div></td>
								<td bordercolor="#000000"><div align="center">{{arr[3][9]}}</div></td>
								<td bordercolor="#000000"><div align="center">{{arr[3][10]}}</div></td>
							</tr>
							<tr>
								<td height="40" align="center" bordercolor="#000000" class="class"><span
									>5</span></td>
								<td colspan="3" bordercolor="#000000">
								<div id="baoxd_4"><div align="left"><p><span ng-click="updatezaf(arr[4][11])" >{{arr[4][0]}}</span></p></div></div></td>
								<td bordercolor="#000000"><div align="center">{{arr[4][1]}}</div></td>
								<td bordercolor="#000000"><div align="center">{{arr[4][2]}}</div></td>
								<td bordercolor="#000000"><div align="center">{{arr[4][3]}}</div></td>
								<td bordercolor="#000000"><div align="center">{{arr[4][4]}}</div></td>
								<td bordercolor="#000000"><div align="center">{{arr[4][5]}}</div></td>
								<td bordercolor="#000000"><div align="center">{{arr[4][6]}}</div></td>
								<td bordercolor="#000000"><div align="center">{{arr[4][7]}}</div></td>
								<td bordercolor="#000000"><div align="center">{{arr[4][8]}}</div></td>
								<td bordercolor="#000000"><div align="center">{{arr[4][9]}}</div></td>
								<td bordercolor="#000000"><div align="center">{{arr[4][10]}}</div></td>
							</tr>
							<tr>
								<td height="40" bordercolor="#000000" class="class"><div align="center">
										<span >合计</span>
							  </div></td>
								<td colspan="3" bordercolor="#000000"><div align="left"><span  id="renminbidaxie" >人民币(大写)：{{arr[5][0]}}</span></div></td>
								<td bordercolor="#000000"><div align="center">{{arr[5][1]}}</div></td>
								<td bordercolor="#000000"><div align="center">{{arr[5][2]}}</div></td>
								<td bordercolor="#000000"><div align="center">{{arr[5][3]}}</div></td>
								<td bordercolor="#000000"><div align="center">{{arr[5][4]}}</div></td>
								<td bordercolor="#000000"><div align="center">{{arr[5][5]}}</div></td>
								<td bordercolor="#000000"><div align="center">{{arr[5][6]}}</div></td>
								<td bordercolor="#000000"><div align="center">{{arr[5][7]}}</div></td>
								<td bordercolor="#000000"><div align="center">{{arr[5][8]}}</div></td>
								<td bordercolor="#000000"><div align="center">{{arr[5][9]}}</div></td>
								<td bordercolor="#000000"><div align="center">{{arr[5][10]}}</div></td>
							</tr>
			      </table>					</td>
				</tr>

				<tr>
					<td colspan="11" align="left">
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
	                      <tr>
	                        <td width="244">费用主管公司领导：</td>
	                        <td width="415"><g:if test=""><img src="../images/aaaaaa.gif"  width="100" height="40" /></g:if></td>
	                        <td width="324"><div align="right">费用主管部门负责人：</div></td>
	                        <td width="179"><g:if test=""><img src="../images/aaaaaaaa.gif"  width="100" height="40" /></g:if></td>
	                        <td width="483"><div align="right">经办部门负责人：</div></td>
	                        <td width="148"><div align="left"><g:if test=""><img src="../images/aaaaaaaa.gif"  width="100" height="40" /></g:if></div></td>
	                      </tr>
	                    </table>
					  	<br>
						  <table width="100%" border="0" cellpadding="0" cellspacing="0">
	                        <tr>
	                          <td width="8%">总会计师：</td>
	                          <td width="15%"><g:if test=""><img src="../images/aaaaaaaaaaa.gif"  width="100" height="40" /></g:if></td>
	                          <td width="11%"><div align="right">会计机构负责人：</div></td>
	                          <td width="23%"><g:if test=""><img src="../images/aaaaaaaaaaa.gif"  width="100" height="40" /></g:if></td>
	                          <td width="10%">会计审核：</td>
	                          <td width="21%"><div align="right">经办人：</div></td>
	                          <td width="12%" colspan="2"><g:if test=""><img src="../images/aaaaaa.gif"  width="100" height="40" /></g:if></td>
	                        </tr>
                      </table>
				  <p>&nbsp;</p></td>
				</tr> 
			</table>
			</div>
		</form>
</div>
		
			<div id="myModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">  
	            <div class="modal-header">  
	                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>  
	                <h5 id="myModalLabel">{{feiybxdtitle}}</h5>  
	            </div>  
	            <div class="modal-body">  
	                <div class="block-content collapse in">
			        <div class="span11">
			            <!-- BEGIN FORM-->
						<form id="zafform" class="form-horizontal">
						<div class="control-group">
				        <label class="control-label" >合同编号</label>
				         <div class="controls">
				          <select id="selectType" ng_model="zafjsbxdbean.HETBIANH" ng-click="selecthetbianh()">
									<option value="{{hetbianh.value}}"  ng-repeat="hetbianh in hetbianhlist">{{hetbianh.name}}</option>
						  </select>
				        </div>
				      </div>
				      <div class="control-group">
				        <label class="control-label" for="inputEmail">费用项目</label>
				        <div class="controls">
				          <select id="selectType" ng_model="zafjsbxdbean.FEIYXM"  ng-click="selectfeiyxm()">
									<option value="{{zaf.value}}"  ng-repeat="zaf in hetzaflist">{{zaf.name}}</option>
						  </select>
				        </div>
				      </div>
				      <div class="control-group">
				        <label class="control-label" >费用开始时间</label>
				        <div class="controls">
				        	<input type="text" class="datepicker" ng-model="zafjsbxdbean.KAISSJ"  >
				        </div>
				      </div>
				      <div class="control-group">
				        <label class="control-label" >费用结束时间</label>
				        <div class="controls">
				        	<input type="text" class="datepicker" ng-model="zafjsbxdbean.JIESSJ"  >
				        </div>
				      </div>
				      <div class="control-group">
				        <label class="control-label" >单价</label>
				        <div class="controls">
				        	<input type="text" disabled ng-model="zafjsbxdbean.DANJ" ng-change="danjchange()"  >
				        </div>
				      </div>
				      <div class="control-group">
				        <label class="control-label" >数量</label>
				        <div class="controls">
				        	<input type="text" ng-model="zafjsbxdbean.SHUL"  ng-change="shulchange()" >
				        </div>
				      </div>
				      <div class="control-group">
				        <label class="control-label" >金额（元）</label>
				        <div class="controls">
				        	<input type="text" disabled ng-model="zafjsbxdbean.JINE"  >
				        </div>
				      </div>
				      <div class="control-group" style="display:none">
				        <label class="control-label" >结算单id</label>
				      <div class="controls">
				          <input type="text" disabled ng-model="zafjsbxdbean.ZAFFYBXDID" />
				        </div>
				      </div>
				      <div class="control-group" style="display:none">
				        <label class="control-label" >杂费费用单价表id</label>
				      <div class="controls">
				          <input type="text" disabled ng-model="zafjsbxdbean.ZAFFYDJBID" />
				        </div>
				      </div>
			    	</form>
						<!-- END FORM-->
			        </div>
			    </div>
     
	            </div>  
	            <div class="modal-footer">  
	                <button class="btn" data-dismiss="modal" aria-hidden="true"><i class="icon-remove-circle"></i> 关闭</button>  
	                <button class="btn btn-primary" ng-click="savezafdjb()"><i class="icon-check icon-white"></i> 保存</button>  
	            </div>  
        	</div> 
</div>