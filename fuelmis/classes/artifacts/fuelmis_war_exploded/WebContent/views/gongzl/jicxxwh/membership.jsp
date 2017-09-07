<%@ page language="java" pageEncoding="UTF-8" %>
<style>
    .ui-datepicker-calendar {
        display: none;
    }

</style>
<div class="tab-pane" ng-controller="">
    <iframe style="height: 600px;" width="100%" height="100%" frameborder="no" border="0" marginwidth="0"
            marginheight="0" scrolling="auto" allowtransparency="yes"
            src="${sessionScope.sanjspUrl}/activiti/xitwh/membership/list?userId=${sessionScope.user.id}">
    </iframe>
    <!-- END FORM-->
</div>
