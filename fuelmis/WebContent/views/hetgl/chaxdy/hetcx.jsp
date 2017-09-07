<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="tab-pane" ng-controller="hetcxCtrl">
	
	<div class="block-content collapse in ">
		<div class="span12">
			<div class="table-toolbar">
				<form class="form-horizontal ng-pristine ng-valid">
					<label style="width:40px;margin-right:5px;" class="control-label">单位:</label>
					<select ng-model="search.diancid"  style="float: left;width: 120px;"
						ng-options="option.value as option.name for option in diancList"></select>
					<label style="width: 80px;margin-right:5px;" class="control-label">启用日期:</label>
					<input id="datepicker1" type="text" style="float: left;width: 120px;" ng-model="search.sDate">
					<label style="width: 15px;margin:0 5px;" class="control-label">至</label>
					<input id="datepicker2" type="text" style="float: left;width: 120px;" ng-model="search.eDate">
					<button style="margin-left: 20px;" ng-click="searchData()" class="btn btn-success">
						<i class="icon-search icon-white"></i> 刷新
					</button>
					<button style="margin-left: 10px;" ng-click="printPage()" class="btn btn-primary">
						<i class="icon-print icon-white"></i> 打印
					</button>
					<button style="margin-left: 10px;" my-export="合同查询.xls" class="btn btn-primary">
						<i class="icon-download-alt icon-white"></i> 导出
					</button>
				</form>
			</div>
		</div>
		
		<div class="row-fluid" id="report" style="width:100%;margin-left: auto;margin-right: auto;overflow: auto;">
            <dy-compile    html="{{modalBody}}"></dy-compile>
        </div>
		<div id="pagination_box" class="pagination pagination-right" style="width: 90%;margin-left: auto;margin-right: auto;">
			<ul id="pagination_zc" ></ul>
		</div>
	</div>
    <style>
        .diss {
            display: block;
        }
        .modal_het {
            position: fixed;
            top: 5%;
            left: 20%;
            z-index: 1050;
            width: 90%;

            margin-left: -280px;
            background-color: #fff;
            border: 1px solid #999;
            border: 1px solid rgba(0, 0, 0, 0.3);
            border: 1px solid #999;
            -webkit-border-radius: 6px;
            -moz-border-radius: 6px;
            border-radius: 6px;
            outline: 0;
            -webkit-box-shadow: 0 3px 7px rgba(0, 0, 0, 0.3);
            -moz-box-shadow: 0 3px 7px rgba(0, 0, 0, 0.3);
            box-shadow: 0 3px 7px rgba(0, 0, 0, 0.3);
            -webkit-background-clip: padding-box;
            -moz-background-clip: padding-box;
            background-clip: padding-box
        }
        .modal-body-het {
            position: relative;
            padding: 15px;
            overflow-y: auto;
        }
    </style>
    <div ng-class="{in: myTost, diss: myTost}" data-backdrop="static" class="modal_het fade" id="myModal"
         tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" ng-show="myTost" style="  height:90%;" >
        <div class="modal-dialog" style="  height:90%;">
            <div class="modal-content" style="  height:100%;" >
                <div class="modal-header">
                    <a class="close" ng-click="close()" <%--data-dismiss="modal_het"--%>>×</a>
                    <h3>合同</h3>
                </div>
                <div class="modal-body-het" style="  height:100%;">
                    <div ng-include="mublj">
                    </div>
                </div>
                <%--<div class="modal-footer">
                    <button class="btn" aria-hidden="true" ng-click="close()"><i class="icon-remove-circle"></i> 关闭
                    </button>
                </div>--%>
            </div><!-- /.modal-content -->
        </div><!-- /.modal -->
    </div>

</div>

<script type="text/javascript">
	$(document).ready(function() {
		$("#datepicker1").datepicker({
			format : 'yyyy-mm-dd',
			language : "zh-CN",
			autoclose : true
		});
		
		$("#datepicker2").datepicker({
			format : 'yyyy-mm-dd',
			language : "zh-CN",
			autoclose : true
		});
	});
	
</script>