<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<div class="block" ng-controller="testCtrl">
	<a href="javascript:void(0);" ng-click="getJij_Qnetar()">热值阶梯计价测试</a>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;||&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<a href="javascript:void(0);" ng-click="getJij_Star()">硫分阶梯计价测试</a>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;||&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<a href="javascript:void(0);" ng-click="test()">测试</a>
<div div class="row-fluid" style="width: 100%;height: 100%;overflow: auto;">
	<my-table-plus data="testData" header="testHead"></my-table-plus>
</div>

</div>