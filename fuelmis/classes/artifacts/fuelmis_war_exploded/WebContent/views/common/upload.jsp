<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link rel="stylesheet" href="/fuelmis/css/common.css" type="text/css">
    <script type="text/javascript" src="/fuelmis/js/jquery/jquery.tools.js"></script>
    <script type="text/javascript" src="/fuelmis/js/myUpload/common.js"></script>
    <title>Insert title here</title>
    <script type="text/javascript">
        $(function(){

            $("input.browserButton").browser({
                title:"规格图片上传",
                browserUrl: "specificationGroup/browser",
                uploadUrl:"specificationGroup/uploadImage",
            });



        });
    </script>
</head>
<body>
<span class="fieldSet">
		<input type="text" name="speciItem[0].imgUrl" class="text specificationValuesImage" maxlength="200" disabled="disabled">
		<input type="button" class="button browserButton" value="选择文件"/>
	</span>
</body>
</html>