gddlapp.controller('zafhtCtrl', function($scope, $rootScope,$routeParams, $http,$location) {
	$scope.zafhtTitle = '合同维护';
	$scope.search = new Object(); 
	var date = new Date();
	var year = date.getFullYear(); 
	$scope.search.nianf = year;
    $("#delzafht").removeClass("btn-danger");
	$("#delzafht").attr('disabled',true);
	
    $("#updatezafht").removeClass("btn-info");
	$("#updatezafht").attr('disabled',true); 
	var oTable = $('#example').dataTable({
		"processing" : true,
		"language" : {
			"sLengthMenu" : "每页显示 _MENU_条",
			"sZeroRecords" : "没有找到符合条件的数据",
			"sProcessing" : "数据加载中...",
			"sInfo" : "当前第 _START_ - _END_ 条　共计 _TOTAL_ 条",
			"sInfoEmpty" : "没有记录",
			"sInfoFiltered" : "(从 _MAX_ 条记录中过滤)",
			"sSearch" : "搜索：",
			"oPaginate" : {
				"sFirst" : "首页",
				"sPrevious" : "前一页",
				"sNext" : "后一页",
				"sLast" : "尾页"
			}
		},
//		"sScrollX": "100%",
//		"sScrollXInner": "120%",
//		"bScrollCollapse": true,
		"aoColumnDefs": [{
            "sClass": "center",
            "mRender": function(oObj, sVal) {
            	 return '<input type="checkbox" id="' + oObj + '" name="checkId" onclick="check(this)" />';
            },
            "bSortable": false,
            "aTargets": [0]
        },
        {  
            "sClass": "center",
            "targets": [2,3,4,5,6,7]      

         },
         {  
            	"targets": [1] ,
           	 "sClass": "right"      

           }
		
		]
	});
	$("#datepicker").datepicker({
			format : 'yyyy',
			minViewMode : 2,
			language : "zh-CN",
			autoclose : true
	});
	oTable.fnReloadAjax('zafht/getZafhtByNianf?nianf='+$scope.search.nianf);
//	$scope.changestate = function(){
//			 $.post("zafht/changestate",//异步处理动态页面
// 				     {id:$('input[name="checkId"]:checked').attr("id")},
// 				     function(data){//data为反回值，function进行反回值处理
// 				    	$("#changestate").removeClass("btn-primary");
// 						$("#changestate").attr('disabled',true);
// 						$("#updatezafht").removeClass("btn-primary");
// 						$("#updatezafht").attr('disabled',true);
// 				    	oTable.fnReloadAjax('zafht/getAllData');
// 			});
//   }
	$scope.refresh = function(){
		$http.post('zafht/getzhuangt',{search:angular.toJson($scope.search)}).success(function(data){
			if(data=='1'||data=='2'){
				$("#submit").removeClass("btn-primary").attr('disabled',true);
				$('#adddata').removeClass('btn-primary').attr('disabled', true);
				$('#updatezafht').removeClass('btn-info').attr('disabled', true);
				$("#delzafht").removeClass("btn-danger").attr('disabled',true);
			}else{
				$("#submit").addClass("btn-primary").attr("disabled",false);
				$('#adddata').addClass('btn-primary').attr('disabled', false);
				$('#updatezafht').addClass('btn-info').attr('disabled', false);
				$('#delzafht').addClass('btn-danger').attr('disabled', false);
			}
		});
		oTable.fnReloadAjax('zafht/getZafhtByNianf?nianf='+$scope.search.nianf);
	}
	$scope.addzafht = function(){
		$scope.addtitle ="添加合同";
		$location.path('/zafhtAdd/'+$scope.addtitle); 
	}
	$scope.sumbmit =function () {
		$http.post('zafht/submit',{search:angular.toJson($scope.search)}).success(function(data){

				alert('提交成功！！！');
				//新增/修改/删除按钮置灰
				$('#adddata').removeClass('btn-primary').attr('disabled', true);
				$('#updatezafht').removeClass('btn-info').attr('disabled', true);
				$('#delzafht').removeClass('btn-danger').attr('disabled', true);
				$("#submit").removeClass('btn-primary').attr('disabled', true);
		}).error(function (data) {
				alert('提交失败！！！');
		});
	}
	$scope.delzafht = function(){
		var id = "";
		 id = $('input[name="checkId"]:checked').attr("id");
		 if(id==""){
			 alert("请选择一项进行删除！！！");
		 }else{
			 var a =confirm("你确定要删除此条记录?");
			 if(a){
				 $.post("zafht/delhetbyid",//异步处理动态页面
	  				     {id:id},//获取类名为"name"文本的值，以NAME异步传值
	  				     function(data){//data为反回值，function进行反回值处理
	  				    	$("#delzafht").removeClass("btn-danger");
	  						$("#delzafht").attr('disabled',true);
//	  						$("#updateyuedys").removeClass("btn-primary");
//	  						$("#updateyuedys").attr('disabled',true);
	  						oTable.fnReloadAjax('zafht/getZafhtByNianf?nianf='+$scope.search.nianf);
	  				        alert(data);//获得得反回值后，将其填入到类名为"content"的文本框中
	  				      });
			 }else{
				 return false;
			 }
		 }
	}
	$scope.updatezafht = function(){
		$scope.updatetitle ="修改合同";
		$scope.search.id = $('input[name="checkId"]:checked').attr("id");
		$location.path('/zafhtUpdate/'+$scope.search.id+'/'+$scope.updatetitle); 
	}
})
.controller('addzafhtCtrl', function($scope, $rootScope,$routeParams, $http,$location) {
	$scope.addzafhtTitle = $routeParams.title;
	$scope.hetbean = new Object();

	$scope.hetbean.JIAF=515;	
	$scope.hetbean.JIAYSFZR=null;
	$scope.hetbean.WEIYZR=null;
	$scope.hetbean.FUKFS=null;
	$scope.hetbean.ANQZAFMYD=null;
	$scope.hetbean.QISRQ=null;
	$scope.hetbean.JIESRQ=null;
	$scope.hetbean.QIT=null;
	$scope.hetbean.QIANDDD=null;
	$scope.hetbean.QIANZY=null;

	
	
	// 保存按钮置灰
	$("#save").removeClass("btn-primary");
	$("#save").attr('disabled', true);
//	$http.post('zafht/getfeilkj').success(function(data){ $scope.fenlkjList=data;});
//	$scope.hetbean = new Object();
//	if($routeParams.id != 0){
//		$http.post('zafht/getzafhtById',{id:$routeParams.id}).success(function(data){
//			$scope.fenlkjbean.FENLKJ = data.FENLKJ_ID;
//			$scope.fenlkjbean.MINGC =data.MINGC;
//			$scope.fenlkjbean.SHUOM =data.SHUOM;
//			$scope.fenlkjbean.ID =data.ID;
//		});
//	}else{
//		$scope.fenlkjbean.ID = $routeParams.id;
//	}
	$scope.cancel = function(){
		$location.path('/zafht');
	}
	$scope.check = function(target) {
		var mingc=$(target).parent().prev().text().split("：")[0];
		if($(target).val()==""){
			$(target).siblings("span").text(mingc+"不能为空!");
		}else{
			$(target).siblings("span").text("");
		}
		var flag=true;
		$(".must").each(function(){
			if($(this).val()==""){
				flag=false;
			}	
		});
		if(flag==true){
			$("#save").addClass("btn-primary");
			$("#save").attr('disabled', false);
		}
	}
	$.fn.serializeObject = function()
	{
	  var o = {};
	  var a = this.serializeArray();
	  $.each(a, function() {
	    if (o[this.name] !== undefined) {
	      if (!o[this.name].push) {
	        o[this.name] = [o[this.name]];
	      }
	      o[this.name].push(this.value || '');
	    } else {
	      o[this.name] = this.value || '';
	    }
	  });
	  return o;
	};
	
	
	$http.post('zafht/getcnfyxm').success(function(data){$scope.cnfylist = data});
	$scope.saveFenyxm = function(){
		var value = $(".cnfy_model").serializeObject();
			$http.post('zafht/zafhetadd',{info:angular.toJson($scope.hetbean),cnfy:angular.toJson(value)}).success(function(data){
				alert(data);
				$location.path('/zafht');
			});
		
	}
	$(".datepicker").datepicker({
		format : 'yyyy-mm-dd',
		minViewMode : 0,
		language : "zh-CN",
		autoclose : true
	}).on("hide", function(e) {
		$scope.check();
    });
})
.controller('updatezafhtCtrl', function($scope, $rootScope,$routeParams, $http,$location) {
	$scope.updatezafhtTitle = $routeParams.title;
	$http.post('zafht/getupdatecnfyxm',{id:$routeParams.id}).success(function(data){ $scope.cnfyxmList=data;});
	$scope.hetbean = new Object();
	$http.post('zafht/getzafhtById',{id:$routeParams.id}).success(function(data){
		$scope.hetbean.ID =data.ID;
		$scope.hetbean.MINGC =data.MINGC;
		$scope.hetbean.BIANH =data.BIANH;
		$scope.hetbean.JIAF =data.JIAF;
		$scope.hetbean.YIF =data.YIF;
		$scope.hetbean.QIANDRQ =data.QIANDRQ;
		$scope.hetbean.JIAYSFZR =data.JIAYSFZR;
		$scope.hetbean.WEIYZR =data.WEIYZR;
		$scope.hetbean.FUKFS =data.FUKFS;
		$scope.hetbean.ANQZAFMYD =data.ANQZAYD;
		$scope.hetbean.QISRQ =data.YOUXKSRQ;
		$scope.hetbean.JIESRQ =data.YOUXJSRQ;
		$scope.hetbean.QIT =data.QIT;
		$scope.hetbean.QIANDDD =data.QIANDDD;
		$scope.hetbean.QIANZY =data.QIANZY;
	});
	$scope.cancel = function(){
		$location.path('/zafht');
	}
	
	$.fn.serializeObject = function()
	{
	  var o = {};
	  var a = this.serializeArray();
	  $.each(a, function() {
	    if (o[this.name] !== undefined) {
	      if (!o[this.name].push) {
	        o[this.name] = [o[this.name]];
	      }
	      o[this.name].push(this.value || '');
	    } else {
	      o[this.name] = this.value || '';
	    }
	  });
	  return o;
	};
	
	
	
	$scope.udpateHet = function(){
		var value = $(".cnfy_model").serializeObject();
			$http.post('zafht/zafhetupdate',{info:angular.toJson($scope.hetbean),cnfy:angular.toJson(value)}).success(function(data){
				alert(data);
				$location.path('/zafht');
			});
		
	}
	$(".datepicker").datepicker({
		format : 'yyyy-mm-dd',
		minViewMode : 0,
		language : "zh-CN",
		autoclose : true
	});
});
