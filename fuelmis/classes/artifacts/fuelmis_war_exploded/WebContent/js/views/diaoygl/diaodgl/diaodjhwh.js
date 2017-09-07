gddlapp.controller('diaodjhwhCtrl', function($scope,$rootScope,$http,$log,$location) {
	$scope.diaodjh = new Object()
	$scope.search = new Object();
	$scope.search.diancid = 515; 
	$scope.search.gongys = -1;
	$scope.title='添加调度计划'
	$scope.index=0;
	//-------------------------------------------日期处理--------------------------------------------------
	var date = new Date();
	var year = date.getFullYear(); 
	var month = date.getMonth()+1<10?'0'+(date.getMonth()+1):date.getMonth()+1;
	var day = date.getDate()<10?'0'+date.getDate():date.getDate();

	$scope.search = {
		sDate : year+'-'+month+'-01',
		eDate : year+'-'+month+'-'+day,
		diancid : 515,
		gongysid:-1
	};
	//----------------------------------------------------------------------------------------------------
	$scope.generateJihdh=function(diaodjh){
		$http.post('diaoygl/diaodgl/generateJihdh', {
			riq : diaodjh.RIQ
		}).success(function(data) {
			diaodjh.JIHDH=data
		});
	}
	//----------------------------------------------------------------------------------------------------
	 $scope.columnDefs =[{
         "sClass": "center",
         "mRender": function(oObj, sVal) {
             return '<input type="checkbox" id="' + oObj + '" name="checkId"  />';
         },
         "bSortable": false,
         "aTargets": [0]
     }]
	 $scope.myCallback= function(nRow, aData, iDisplayIndex, iDisplayIndexFull) {
			return nRow; 
	}
	//----------------------------------------------------------------------------------------------------
	$scope.load=function(){
		$http.post('diaoygl/diaodgl/getDiaodjhList', {
			condition : angular.toJson($scope.search)
		}).success(function(data) { 
			$scope.diaodjhList =data 
		});
	}
	$scope.load();
	//-----------------------------------------按钮功能-----------------------------------------------------------
	$scope.add=function(){
		$scope.title='添加调度计划' 
		$scope.diaodjh=new Object();
		$scope.diaodjh.diaodjhSubList=new Array()
		$scope.diaodjh.RIQ=year+'-'+month+'-'+day 
		$scope.diaodjh.diaodjhSubList=[]
		//生成计划单号
		$('#myModal_diaodjh').modal('show');
		$('#myModal_diaodjh input').attr("disabled",false)
		$http.post('diaoygl/diaodgl/generateJihdh', {
			riq : year+'-'+month+'-'+day
		}).success(function(data) {
			$scope.diaodjh.JIHDH=data
		});
	}
	$scope.edit=function(){
		var checkboxes=$("#example input:checked")
		var zhuangt=checkboxes.parent().siblings().eq(5).text()
		if(checkboxes.length==0){
			alert("请选择要修改的行")
		}if(zhuangt=='已审核'){
			alert("已经审核的数据不能进行修改！")
		}else{
			var id=checkboxes.attr("id")
			$http.post('diaoygl/diaodgl/getDiaodjh', {
				id : id
			}).success(function(data) {
				$scope.diaodjh=data
				$scope.title='编辑调度计划'
				$('#myModal_diaodjh').modal('show');
				$('#myModal_diaodjh input').attr("disabled",false)
			});
		}
	}	
	$scope.del=function(){
		var checkboxes=$("#example input:checked")
		if(checkboxes.length==0){
			alert("请选择要删除的行")
		}else{
			var id=checkboxes.attr("id")
			$http.post('diaoygl/diaodgl/deleteDiaodjh', {
				id : id
			}).success(function(data) {
				alert("删除成功！")
				$scope.load();
			});
		}
	}
	//查看详细
	$scope.show=function(){
		var checkboxes=$("#example input:checked")
		if(checkboxes.length==0){
			alert("请选择要查看的行！") 
		}else{
			var id=checkboxes.attr("id")
			$http.post('diaoygl/diaodgl/getDiaodjh', {
				id : id
			}).success(function(data) {
				$scope.diaodjh=data
				$('#myModal_diaodjh input').attr("disabled",true)
				$('#myModal_diaodjh').modal('show');
			});
		}
	}
	//-------------------------------------------弹出框按钮功能---------------------------------------------------------------------
	$scope.addDiaodjhSub=function(){
		$scope.diaodjh.diaodjhSubList.push(new Object()) 	
	}
	$scope.deleteDiaodjhSub=function(i){
		var id=$scope.diaodjh.diaodjhSubList[i].ID
		$scope.diaodjh.diaodjhSubList.splice(i, 1);
		if(id!=undefined){
			$http.post('diaoygl/diaodgl/deleteDiaodjhSub', {
				id : id
			}).success(function(data) {
				alert("删除成功！")
			});
		}
		
	}
//	$("input:text").click(function(){
//	    $(this).select();
//	});
	$scope.cancel=function(){
		$("div#diaodjhList").removeClass("hide")
		$("div#diaodjh").addClass("hide")
		$scope.load();
	}
	$scope.save=function(diaodjh){
		$http.post('diaoygl/diaodgl/insertDiaodjh', {
			data : angular.toJson($scope.diaodjh)
		}).success(function(data) {
			alert("保存成功");
			$('#myModal_diaodjh').modal('hide');
			$("div#diaodjhList").removeClass("hide")
			$("div#diaodjh").addClass("hide")
			$scope.load();
		});
	}

	//复制上个月计划
	$scope.copy=function(){
		
	}
	
})
