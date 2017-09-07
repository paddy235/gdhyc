gddlapp.controller('riddshCtrl', function($scope,$rootScope,$http,$log,$location) {
	//------------------------------------------初始化页面---------------------------------------------
	$scope.search = new Object();
	$scope.search.diancid=515;
	$scope.search.zhuangt=0;
	//------------------------------------------日期定义---------------------------------------------------- 
	var d = new Date();
	var vYear = d.getFullYear(); 
	var vMon = d.getMonth() + 1;
	var vDate = d.getDate();
	$scope.search.strdate = vYear + "-"+ (vMon < 10 ? "0" + vMon : vMon) + "-"+ "01";
	$scope.search.enddate =vYear + "-"+ (vMon < 10 ? "0" + vMon : vMon) + "-"+ (vDate < 10 ? "0" + vDate : vDate)
//-------------------------------------------------------------------------------------------------------------
	 $scope.myCallback= function(nRow, aData, iDisplayIndex, iDisplayIndexFull) {
			var $b= $('td:eq(8)', nRow).children(".shenh") 
			var b=$b[0]
			//------------------根据条件获取总记录数--------------------// 
			if($scope.search.zhuangt==1){//当为二级审核时去掉审核按钮
				$b.attr('disabled',"disabled")
			}else{
				//$b.removeClass("disabled") 
				$b.removeAttr('disabled')
			} 
			return nRow; 
		}
	 $scope.columnDefs =  [
						{
							"sClass" : "center",
							"mRender" : function(oObj, sVal) {
								return '<input type="checkbox" id="'
										+ oObj
										+ '" name="checkId" onclick="check(this)" />';
							},
							"bSortable" : false,
							"aTargets" : [ 0 ]
						},
						{
							"sClass" : "center",
							"targets" : [ 1, 2, 3, 5, 6, 7 ]
						},
						{
							"targets" : [ 4 ],
							"sClass" : "right"
						},
						{
							"sClass" : "center",
							"mRender" : function(oObj, sVal) {
								 return  '<button id="'+oObj+'"class="btn btn-success opration shenh" name="shenh" style="margin-right: 1px;"><i class="icon-plus icon-white"></i>审核</button>'
										+ '<button id="'+oObj+'"class="btn btn-primary opration huit" name="huit" ><i class=" icon-refresh icon-white"></i> 回退</button>'
						},
							"bSortable" : false,
							"aTargets" : [ 8 ]
						} ]
   	//------------------------------------加载数据-------------------------------------------------------- 
	$scope.load=function() {
		 $http.post('diaoygl/diaodgl/getRiddsh?condition='+angular.toJson($scope.search)).success(function(data) {
  			$scope.diaodjh =data
 		}) 
	}
    $scope.load();   
    $('.opration').die()
  	$('.opration').live('click',function() { 
 		var $b=$(this)
 		var id=$b.attr("id")
 		var name=$b.attr("name")
 		// 更改当前审核状态
 		if(name=="shenh"){
			$http.post('diaoygl/diaodgl/shenh?id='+id).success(function(data, status, headers, config) {
				alert("审核成功");
				$scope.load();    				
			});
 		}else{
 			$http.post('diaoygl/diaodgl/huit?id='+id).success(function(data, status, headers, config) {
				alert("回退成功");
				$scope.load();    				
			});
 		}
 	});  
//	$('#b').click(function() {   
//	var b=this;
//	 alert("Live handler called.");
//	}); 
// 		var b=this;
//   alert("Live handler called.");  
// }); 
})
