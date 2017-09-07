<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<table id="example"  class="table table-striped table-bordered table-hover">
    <thead>
    <tr>
        <th style="text-align: center;width: 2% "><input type="checkbox" id="selectAll"></th>
        <th ng-repeat="h in header track by $index">{{header[$index]}}</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <th style="text-align: center;width: 2% "><input type="checkbox" ></th>
        <th ng-repeat="h in header track by $index">{{header[$index]}}</th>
    </tr>
  <%--  <tr ng-repeat="row in data ">
        <td ng-repeat="d in row track by $index ">{{d}}</td>
    </tr>--%>
    </tbody>
</table>


