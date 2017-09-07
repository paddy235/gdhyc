gddlapp.controller('peimeiCtrl', function($scope,$rootScope,$http,$log,$location) {
	$scope.peimeiTitle='配煤';
	$scope.search = new Object();
	$scope.search.flag =0;//checkbox选中计数器
	var d = new Date();
	var vYear = d.getFullYear();
	var vMon = d.getMonth() + 1;
	var vDay = d.getDate();
	$scope.setImgHeight4Row = function(tagName, index){
		document.getElementById('img'+index).style.height = document.getElementById(tagName+index).offsetHeight + "px";;
	}
	$scope.setImgHeightAll = function(tagName){
		for(var index=0; $scope.items.data.length; index++){
			$scope.setImgHeight4Row(tagName, index);
		}
	}
	$scope.search.riq = vYear+"-"+(vMon<10 ? "0" + vMon : vMon)+"-"+(vDay<10 ? "0" + vDay : vDay);
	$http.post('peimei/peimeisearch',{riq:$scope.search.riq}).success(function(data) {
		$scope.items = data;
		$scope.search.size = $scope.items.data.length;//数据条数，供checkbox选中事件中的判断条件使用
//		for(var k=0;k<$scope.items.data.length;k++){
//			$scope.setImgHeight4Row('initHtable', k);
//		}
//		$scope.setImgHeightAll('httd');
	});
	$scope.refresh = function(){
		$http.post('peimei/peimeisearch',{riq:$scope.search.riq}).success(function(data) {//刷新界面时要充值checkbox计数器和数据条数，重置checkbox
			$scope.search.flag =0;
			$scope.items = data;
			$("#checkedall").removeAttr("checked");
			$scope.search.size = $scope.items.data.length;
//			for(var k=0;k<$scope.items.data.length;k++){
//				$scope.setImgHeight4Row('initHtable', k);
//			}
//			$scope.setImgHeightAll('httd');
		});
	}
	$scope.calculate = function(){
		//将选中的记录以json格式保存到rebackdata
		var rebackdata = {"data":[]};
		 $('input[name="subBox"]').each(function (i) {
             if ($(this).attr("checked")) {
            	 var ids = $(this).val();
            	 var k =0;
            	 for(var i =0;i<$scope.items.data.length;i++){
            		 if($scope.items.data[i].rowData.ID == ids){
            			 rebackdata.data[k] = $scope.items.data[i];
            			 k=k+1;
            		 }
            	 }
            	 
             }
         });
		$http.post('peimei/peimeisjis',{riq:$scope.search.riq,info:angular.toJson(rebackdata)}).success(function(data) {
			alert(data);
			if(data != ""){
				$http.post('peimei/peimeisearch',{riq:$scope.search.riq}).success(function(data) {
					$scope.search.flag =0;
					$scope.items = data;
					$("#checkedall").removeAttr("checked");
					$scope.search.size = $scope.items.data.length;
//					$scope.setImgHeightAll('initHtable');
				});
			}
		});
	}
	$scope.save = function(){
		var rebackdata = {"data":[]};
		$('input[name="subBox"]').each(function (i) {
            if ($(this).attr("checked")) {
            	var ids = $(this).val();
            	var k =0;
            	for(var i =0;i<$scope.items.data.length;i++){
            		 if($scope.items.data[i].rowData.ID == ids){
            			 rebackdata.data[k] = $scope.items.data[i];
            			 k=k+1;
            		 }
            	}
            }
        });
		$http.post('peimei/peimeisave',{riq:$scope.search.riq,info:angular.toJson($scope.items)}).success(function(data) {
			alert(data);
			if(data != ""){
				$http.post('peimei/peimeisearch',{riq:$scope.search.riq}).success(function(data) {
					$scope.search.flag =0;
					$scope.items = data;
					$("#checkedall").removeAttr("checked");
					$scope.search.size = $scope.items.data.length;
//					$scope.setImgHeightAll('initHtable');
				});
			}
		});
	}
	$scope.Diaoylchange = function(value){//页面表格里修改调用量时计算出表格列中的车数和表格下面的热值、硫分、数量、标单的值
		for(var i =0;i<$scope.items.data.length;i++){
			var shul = 0;
			var rez = 0;
			var liuf= 0;
			var biaod =0;
			for(var j=0;j<$scope.items.data[i].rowDetails.length;j++){
				if(value == $scope.items.data[i].rowDetails[j].ID){
					//车数
					$scope.items.data[i].rowDetails[j].CHES = ($scope.items.data[i].rowDetails[j].SHUL_DY/39).toFixed(0);
				}
				//数量
				shul = shul + Number($scope.items.data[i].rowDetails[j].SHUL_DY);
				//热值
				rez = rez + Number(Number($scope.items.data[i].rowDetails[j].SHUL_DY)*Number($scope.items.data[i].rowDetails[j].QNET_AR));
				//硫分
				liuf = liuf + Number(Number($scope.items.data[i].rowDetails[j].S)*Number($scope.items.data[i].rowDetails[j].SHUL_DY));
				//标单
				biaod = biaod + Number(Number($scope.items.data[i].rowDetails[j].BIAOMDJ)*Number($scope.items.data[i].rowDetails[j].SHUL_DY))
			}
			$scope.items.data[i].rowData.SHUL_JG = shul;
			$scope.items.data[i].rowData.QNET_AR_JG = (rez/shul).toFixed(0);
			$scope.items.data[i].rowData.S_JG = (liuf/shul).toFixed(1);
			$scope.items.data[i].rowData.BIAOMDJ = (biaod/shul).toFixed(2);
	 }
	}
	//全选框控制
	$scope.checkall = function(){
		if($("#checkedall").attr("checked")=="checked"){
//			$("div.all_td").show();
			$("#calculate").removeAttr("disabled");
			$("#calculate").addClass("btn-primary");
			$("#save").addClass("btn-primary");
			$("#save").removeAttr("disabled");
			$scope.search.flag =$scope.items.data.length;
//			$scope.setImgHeightAll('httd');
		}else{
//			$("div.all_td").hide();
			$("#calculate").attr('disabled',true);
			$("#calculate").removeClass("btn-primary");
			$("#save").removeClass("btn-primary");
			$("#save").attr('disabled',true);
			$scope.search.flag =0;
//			$scope.setImgHeightAll('initHtable');
		}
	}
	//单选框控制
	$scope.check_each = function(target, index){
		var checkid = target.getAttribute("id");
//		var tdid = "check"+checkid;
		if(document.getElementById(checkid).checked){
			$scope.search.flag = $scope.search.flag+1;
//			document.getElementById(tdid).style.display="";
//			$scope.setImgHeight4Row('httd', index);
		}else{
			$scope.search.flag = $scope.search.flag-1;
//			document.getElementById(tdid).style.display="none";
//			$scope.setImgHeight4Row('initHtable', index);
		}
		if($scope.search.flag>0){//判断选中的条数大于0就显示计算和保存的按钮
			$("#calculate").removeAttr("disabled");
			$("#calculate").addClass("btn-primary");
			$("#save").addClass("btn-primary");
			$("#save").removeAttr("disabled");
		}else{
			$("#calculate").attr('disabled',true);
			$("#calculate").removeClass("btn-primary");
			$("#save").removeClass("btn-primary");
			$("#save").attr('disabled',true);
		}
		if($scope.search.size==$scope.search.flag){//如果复选框全选中就将全选的复选框选中，如果不是就将全选的复选框不选中
			$("#checkedall").attr("checked",'true');
		}else{
			$("#checkedall").removeAttr("checked");
		}
	}
})