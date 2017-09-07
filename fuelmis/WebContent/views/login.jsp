<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>系统登录</title>
<style type="text/css">
<!--
.bg {
	background-image: url(${ctx}/images/login/login_new_bg.jpg);
	background-repeat: repeat-x;
	background-color: #2A68AE;
}

.input_bg {
	background-image: url(${ctx}/images/login/login_new_inputBG.jpg);
	background-repeat: no-repeat;
	height: 223px;
}

.inputTxt {
	height: 19px;
	width: 84px;
	border-top: 1px solid #707070;
	border-right: 1px solid #FFFFFF;
	border-bottom: 1px solid #FFFFFF;
	border-left: 1px solid #707070;
	font-family: "Verdana", "Arial", "Helvetica", "sans-serif";
	font-size: 12px;
	color: #204162;
	text-decoration: none;
	clip:   rect(auto auto auto auto);
}
.inputPosition {
	padding-top: 3px;
	padding-left: 0px;
	clip: rect(12px,auto,auto,auto);
}
-->
</style>
</head>
<body id="body" class="bg" leftmargin="0" topmargin="0">
	<form method="post" action="/fuelmis/logon">
		<table width="100%" cellspacing="0" cellpadding="0">
			<tr>
				<td>
				<img src="${ctx}/images/login/login_new_logo.jpg" height="361"
					width="810"></img></td>
			</tr>
		</table>
		<table width="100%" cellpadding="0" cellspacing="0" class="input_bg">
			<tr>
				<td valign="top">
					<table width="50%" cellspacing="0" cellpadding="0">
						<tr>
							<td><!-- <img src="" width="1" height="67"> --><div style="display:inline-block;width:1px;height:67px;"></div></td>
						</tr>
					</table>
					<table width="99%" cellspacing="0" cellpadding="0">
						<tr>
							<td width="0%">
								<!-- <img src="" width="671" height="1"> -->
								<div style="display:inline-block;width:671px;height:1px;">
							</td>
							<td width="100%">
								<table width="38%" cellspacing="0" cellpadding="0">
									<tr>
										<td width="24%"><input type="text" name="username" value=""
											class="inputTxt" value="" style="margin-top: 3px;"/></td>
										<td width="14%">
											<!-- <img src="" width="52" height="1"> -->
											<div style="display:inline-block;width:52px;height:1px;">
										</td>
										<td width="24%"><input type="password" name="password" value=""
											class="inputTxt" value="" style="margin-top: 5px;"/></td>
										<td width="0%">
											<!-- <img src="" width="16" height="1"> -->
											<div style="display:inline-block;width:16px;height:1px;">
										</td>
										<td width="100%">
											<div class="inputPosition">
												<input src="${ctx}/images/login/login_new_bt.jpg" height="21"
													type="image" width="70"></input>
											</div>
										</td>
									</tr>
									<tr><td height="10px"></td></tr>
									<tr>
									<td colspan="5"><font style="color:red;" size="2px"><%=session.getAttribute("errorMsg")==null?"":session.getAttribute("errorMsg")%></font></td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<table width="1004" id="logofooter" cellpadding="0" cellspacing="0">
			<tr>
				<td align="right" valign="bottom"><a
					href="http://www.zhiren.net" target="_blank"><img
						src="${ctx}/images/login/login_new_zhirenlogo.jpg" width="125"
						height="20" border="0"></a></td>
			</tr>
		</table>
		<table>
			<tr>
				<td><!-- <img src="" width="1" height="20"> --><div style="display:inline-block;width:1px;height:20px;"></td>
			</tr>
		</table>
	</form>
</body>
</html>