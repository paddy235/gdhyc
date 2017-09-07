gddlapp.controller('zafwhCtrl', function($scope,$http,$log,$location) {
	$scope.zafwhTitle='杂费维护';
	var date = new Date();
	var year = date.getFullYear();
	var month = date.getMonth();
	if(month<10){
		if(month==0){
			year-=1;
			month=12;
		}else{
			month='0'+date.getMonth();
		}	
	}
	var ymId=7;
	$scope.array = new Array();//新增数据存放
	$scope.delArray=new Array();//用于存放要删除行的id
	$scope.search = {
		riq : year+'-'+month,
		diancid : 515
	};
	
	$scope.load = function() {
		$http.post('yuebgl/yuebwh/zafwh/getZfmingc').success(function(data){ $scope.zfmingcList=data;});
		$http.post('yuebgl/yuebwh/zafwh/getAll?riq='+$scope.search.riq+'&dianc='+$scope.search.diancid).
	    success(function(data, status, headers, config) {
	    	$scope.list=data;
	    	$scope.hej=0;
	    	for(var i=0;i<data.length;i++){
	    		$scope.hej=eval($scope.hej+"+"+data[i].JINE)
	    	}
	    	
			//更新序号
			for(var i=0;i<$scope.list.length;i++){
				$scope.list[i].XUH=i+1;
			}
			//清空删除数组
			$scope.delArray=[];
			//禁用input输入功能
			$http.post('yuebgl/yuebsc/getYueb',{riq:$scope.search.riq,dianc:$scope.search.diancid}).success(function(data) {
				if(data[ymId].ZHUANGT==1){
    				$(".table td input").prop("disabled",true);
    				$(".table-toolbar button[id$='Btn']").prop("disabled",true);
    			}else{
    				$(".table-toolbar button[id$='Btn']").prop("disabled",false);
    				$(".table input").prop("disabled",false);
    			}
			});
		});
	}
	
	$scope.load();
	
	$scope.add = function() {
		var obj = new Object();
		obj={
				XUH : $scope.list.length+1,
				DIANCXXB_ID:515,
				riq:$scope.search.riq
		} 
		$scope.list.push(obj);
		}
	
	
	$scope.searchData = function(){
		$scope.load();
	}
		
	$scope.radioch=function(){
		$("table tr").each(function(){
			$(this).click(function(){
		        $("input").removeAttr("checked");
				$(this).find("input").attr("checked",true);
			});
		});
	}
	/*删除操作*/
	$scope.delSigle=function(){
		var $radio = $("#example input[type=radio]:checked");
		if($radio.length==0){
			alert("请选择要删除的行！");
		}else{
			 var r=confirm("确认要删除吗?");
			 var i=$radio.val()-1;
			 var zfmc=$scope.list[i];
			 $scope.del(zfmc); 
		}
	}
	//------------------------------------删除元素分析----------------------------------------------------
	Array.prototype.remove = function(dx) {
		if (isNaN(dx) || dx > this.length) {
			return false;
		}
		this.splice(dx, 1);
	}
	$scope.del = function(zfmc) {
		if (zfmc.ZFID != undefined) {
			$scope.delArray.push(zfmc);		
		}
		$scope.list.remove(zfmc.XUH-1);
		//更新序号
		for(var i=0;i<$scope.list.length;i++){
			$scope.list[i].XUH=i+1;
		}
	}

	//保存
	$scope.saveData = function() {
		//删除
		$http.post('yuebgl/yuebwh/zafwh/delZf', {
			data : angular.toJson($scope.delArray)
		}).success(function(data) {
			//更新
			$http.post('yuebgl/yuebwh/zafwh/saveZf', {
				data : angular.toJson($scope.list)
			}).success(function(data) {
				$scope.load();
				alert("保存成功")
			})
			
		})
	}
	
	$scope.copyzafei=function(){
		$http.post('yuebgl/yuebwh/zafwh/getAll',{riq:$scope.search.riq,dianc:$scope.search.diancid,caoz:'check'}).success(function(data){
			if(data == "1"){
				var a =confirm($scope.search.riq+"杂费统计已经存在，你确定要替换吗？？？");
				if(a){
					$http.post('yuebgl/yuebwh/zafwh/copyzafei',{riq:$scope.search.riq,dianc:$scope.search.diancid,caoz:"replace"}).success(function(data){
						$scope.load();
						alert(data);
					});
				}
			}else{
				$http.post('yuebgl/yuebwh/zafwh/copyzafei',{riq:$scope.search.riq,dianc:$scope.search.diancid}).success(function(data){
					oTable.fnReloadAjax('yuebgl/yuebwh/zafwh/getAll?riq='+$scope.search.riq+'&dianc='+$scope.search.diancid);
					$scope.load();
				});
			}
		}); 
	}
	
	$scope.printTable = function(){
		$("#example").jqprint();
	}
});