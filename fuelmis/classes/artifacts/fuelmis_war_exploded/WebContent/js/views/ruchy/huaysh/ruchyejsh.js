gddlapp.controller('huayejshCtrl', function($scope,$rootScope,$http,$log,$location) {
	$scope.huayejshTitle='二级审核';
	
	var date = new Date();
	var year = date.getFullYear();
	var month = date.getMonth()+1<10?'0'+(date.getMonth()+1):date.getMonth()+1;
	var day = date.getDate()<10?'0'+date.getDate():date.getDate();
	
	$scope.search = {
		sDate : year+'-'+month+'-01',
		eDate : year+'-'+month+'-'+day,
		diancid : 515
	}
	
	$scope.searchData = function() {
		if($("#selJincpch").val()==null||$("#selJincpch").val()==""){
			alert("请选择进厂批次号！");
			return false;
		}
		oTable.fnReloadAjax('ruchy/huaysh/getTableData2?zhilb_id='+$("#selJincpch").val());
	}
	
	$scope.shenh=function(){
		if($("#example input[type=checkbox]:checked").length<1){
			alert("请选择要审核的数据！")
		}else{
			$("#example input[type=checkbox]").each(function(){
			    if(this.checked){
			    	$http.post('ruchy/huaysh/updateZT2?id='+$(this).attr("id")+"&zhuangt=7").
					 	success(function(data, status, headers, config) {
					 		 if(data[0]==1){
					    		   alert("审核成功！");
					    		   var diancid = $scope.search.diancid;
					    		   oTable.fnReloadAjax('ruchy/huaysh/getTableData2?diancid='+diancid);
					    	   }else{
					    		   alert("审核失败！");
					    	   }
					 });
			    }
			}); 
		}
	}
	
	$scope.huit=function(){
		if($("#example input[type=checkbox]:checked").length<1){
			alert("请选择要回退的数据！")
		}else{
			$("#example input[type=checkbox]").each(function(){
			    if(this.checked){
			    	$http.post('ruchy/huaysh/updateZT2?id='+$(this).attr("id")+"&zhuangt=5").
				 		success(function(data, status, headers, config) {
				 		 if(data[0]==1){
				    		   alert("回退成功！");
				    		   var diancid = $scope.search.diancid;
				    		   oTable.fnReloadAjax('ruchy/huaysh/getTableData2?diancid='+diancid);
				    	   }else{
				    		   alert("回退失败！");
				    	   }
				 	});
			    }
			}); 
		}
	}
})

function loadJincpch(){
	 $.post("ruchy/huaysh/getJincpch",{sDate:$("#datepicker1").val(),eDate:$("#datepicker2").val()},function(data){
		 var arry = eval(data);
		 for(var i=0;i<arry.length;i++){
			 var op = new Option(arry[i].name,arry[i].value);
			 document.getElementById("selJincpch").options.add(op);
		 }
	});
}