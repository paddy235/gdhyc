gddlapp.controller('shujlrCtrl', function($scope,$rootScope,$http,$log,$location) {
	$scope.shujlrTitle='数据录入';
	
	var date = new Date();
	var year = date.getFullYear();
	var month = date.getMonth()+1<10?'0'+(date.getMonth()+1):date.getMonth()+1;
	
	$scope.search = {
		riq : year+'-'+month
	};
	$scope.saveData=function () {
        $http.post('kucgl/shujlr/savePandxx',{data:angular.toJson($scope.pandxxList)}).success(function(data) {
       alert("保存成功！");
            $scope.searchData;
        }).error(function () {
			alert("保存失败");
        });
    }
/*	$scope.createData = function() {
		var riq = $scope.search.riq;
		$http.post('kucgl/shujlr/createPandxx',{riq:riq}).success(function(data) {
			var riq = $scope.search.riq;
			var diancid = $('#dianc_id').val();
			oTable.fnReloadAjax('kucgl/shujlr/getPandxx?riq='+riq+'&dianc='+diancid);
			$("#createBtn").hide();
			$("#fujBtn").show();
			$("#submitBtn").show();
		});
	}*/
	
	$scope.searchData = function() {
		var riq = $scope.search.riq;
		var diancid = $('#dianc_id').val();
		$("#submitBtn").attr("disabled",false);
		// oTable.fnReloadAjax('kucgl/shujlr/getPandxx?riq='+riq+'&dianc='+diancid);
		$http.post('kucgl/shujlr/getPandxx?riq='+riq+'&dianc='+diancid)
			.success(function (data) {
			$scope.pandxxList=data;
        }).error(function (data) {

        });
		$.post('kucgl/shujlr/checkCount?riq='+riq+'&dianc='+diancid,function(data){
			if(eval(data)[0]==0){
				$("#createBtn").show();
				$("#fujBtn").hide();
				$("#submitBtn").hide();
			}else{
				$("#createBtn").hide();
				$("#fujBtn").show();
				$("#submitBtn").show();
			}
		});
	}
	
	$scope.uploadFuj = function() {
		if($("#example input[type=checkbox]:checked").length<1){
			alert("请选择要添加附件的记录！");
		}else{
			$("#example input[type=checkbox]").each(function(){
			    if(this.checked){
			    	var id=$(this).attr("id");
				    $('#pandxx_id').val(id);
					$('#myModal').modal('show');
			    }
			});  
		}
	}
    $scope.fjUpload =function (){
        $.ajaxFileUpload({
            url: 'kucgl/shujlr/uploadFile',
            secureuri: false,
            fileElementId: 'upFile',
            dataType: 'json',
            data:{
                id : $('#pandxx_id').val()
            },
            success: function (data, status){
                alert(data[0].msg);
                $('#myModal').modal('hide');
                var riq = $('#datepicker').val();
                var diancid = $('#dianc_id').val();
//				oTable.fnReloadAjax('kucgl/shujlr/getPandxx?riq='+riq+'&dianc='+diancid);

                $scope.pandxxList[0].FUJMC=data[0].fujmc;
                $scope.saveData();

                $("#createBtn").hide();
                $("#fujBtn").show();
                $("#submitBtn").show();
            },
            error: function (data, status, e){
                alert(e);
            }
        });
        return false;
    }
	$scope.submitData = function(){
    	//根据dianc获取 ID

            if($("#example input[type=checkbox]:checked").length<1){
                alert("请选择要提交的记录！");
            }else{
                $("#example input[type=checkbox]").each(function(){
                    if(this.checked){
                        var id =$scope.pandxxList[0].ID;
                      // var id = $('#ID').val();
                        $http.post('kucgl/shujlr/submitData?id='+id).
                        success(function(data, status, headers, config) {
                            if(data>=1){
                                alert("提交成功！");
                                $("#submitBtn").attr("disabled",true);
                                var riq = $scope.search.riq;
                                var diancid = $('#dianc_id').val();
                                oTable.fnReloadAjax('kucgl/shujlr/getPandxx?riq='+riq+'&dianc='+diancid);
                                $.post('kucgl/shujlr/checkCount?riq='+riq+'&dianc='+diancid,function(data){
                                    if(eval(data)[0]==0){
                                        $("#createBtn").show();
                                        $("#fujBtn").hide();
                                        $("#submitBtn").hide();
                                    }else{
                                        $("#createBtn").hide();
                                        $("#fujBtn").show();
                                        $("#submitBtn").show();
                                    }
                                });
                            }else{
                                alert("提交失败！");
                            }
                        });
                    }
                });
            }
	}
});