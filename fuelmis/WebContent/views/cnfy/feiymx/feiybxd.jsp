   <%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
    <div class="block" ng-controller="feiybxdCtrl">
	    <div class="navbar navbar-inner block-header">
	        <div class="muted pull-left">{{cnfymxTitle}}</div>
	    </div>
    	<div class="block-content collapse in ">
			<div class="span12">
				<div class="table-toolbar">
					<button style="margin-left: 10px;"  class="btn btn-success" ng-click="reback()"><i class="icon-arrow-left"></i>返回</button>
					<button style="margin-left: 5px;" ng-click="printPage()" class="btn"><i class="icon-print"></i>打印</button>
					<button style="margin-left: 5px;" my-export="费用报销单查询.xls" class="btn"><i class="icon-download-alt"></i>导出</button>
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
							<span class="STYLE7">编号：</span> <span class="STYLE7">
							</span>
						</div></td>
					<td width="53%"><font size="+1" ></font><span class="STYLE7">  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></td>
				</tr> 
				<tr>
				  <td height="366" colspan="11" align="center">
						<table width="103%" height="362" border="2" cellpadding="0" cellspacing="0" bordercolor="#000000">
							<tr>
								<td width="8%" height="45" align="center" bordercolor="#000000"
									class="class">经办人</td>
								<td width="22%" bordercolor="#000000"><div align="center">{{username}}</div></td>
								<td width="8%" bordercolor="#000000" align="center">所属部门</td>
								<td width="40%"><div align="center">{{department}}</div></td>
								<td colspan="10" align="center" bordercolor="#000000"
									class="class"><div align="center">
										<span class="STYLE7">&nbsp;金&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;额</span>
									</div></td>
							</tr>
							<tr>
								<td height="40" align="center" bordercolor="#000000" class="class"><span
									class="STYLE7">序号</span></td>
								<td colspan="3" bordercolor="#000000"><div align="center">
										<span class="STYLE7">报&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;销&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;内&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;容&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;说&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;明</span>
									</div></td>
							  	<td width="2%" align="center" bordercolor="#000000"
									class="class"><div align="center"><span class="STYLE7">仟</span></div></td>
								<td width="2%" align="center" bordercolor="#000000"
									class="class"><div align="center"><span class="STYLE7">百</span></div></td>
								<td width="2%" align="center" bordercolor="#000000"
									class="class"><div align="center"><span class="STYLE7">十</span></div></td>
								<td width="2%" align="center" bordercolor="#000000"
									class="class"><div align="center"><span class="STYLE7">万</span></div></td>
								<td width="2%" align="center" bordercolor="#000000"
									class="class"><div align="center"><span class="STYLE7">仟</span></div></td>
								<td width="2%" align="center" bordercolor="#000000"
									class="class"><div align="center"><span class="STYLE7">百</span></div></td>
								<td width="2%" align="center" bordercolor="#000000"
									class="class"><div align="center"><span class="STYLE7">十</span></div></td>
								<td width="2%" align="center" bordercolor="#000000"
									class="class"><div align="center"><span class="STYLE7">元</span></div></td>
								<td width="2%" align="center" bordercolor="#000000"
									class="class"><div align="center"><span class="STYLE7">角</span></div></td>
								<td width="2%" align="center" bordercolor="#000000"
									class="class"><div align="center"><span class="STYLE7">分</span></div></td>
							</tr>
							<tr>
								<td height="40" align="center" bordercolor="#000000" class="class"><span
									class="STYLE7">1</span></td>
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
									class="STYLE7">2</span></td>
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
									class="STYLE7">3</span></td>
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
								<td height="40" align="center" bordercolor="#000000" class="class"><span class="STYLE7">4</span></td>
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
									class="STYLE7">5</span></td>
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
										<span class="STYLE7">合计</span>
							  </div></td>
								<td colspan="3" bordercolor="#000000"><div align="left"><span class="STYLE7" id="renminbidaxie" >人民币(大写)：{{arr[5][0]}}</span></div></td>
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
					<td colspan="11" align="left"><table width="103%" border="0" cellpadding="0" cellspacing="0">
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
					  <table width="103%" border="0" cellpadding="0" cellspacing="0">
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
		
</div>
