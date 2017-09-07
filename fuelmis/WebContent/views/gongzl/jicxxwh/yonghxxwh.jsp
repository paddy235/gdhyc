<%@ page language="java" pageEncoding="UTF-8" %>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> --%>

<style>
    .ui-datepicker-calendar {
        display: none;
    }
</style>
<div class="tab-pane" ng-controller="yonghxxwhCtrl">
    <iframe style="height: 600px;" width="100%" height="100%" frameborder="no" border="0" marginwidth="0"
            marginheight="0" scrolling="auto" allowtransparency="yes"
            src="${sessionScope.sanjspUrl}/activiti/xitwh/dianc/list?userId=${sessionScope.user.id}"/>
    <!-- END FORM-->
</div>

