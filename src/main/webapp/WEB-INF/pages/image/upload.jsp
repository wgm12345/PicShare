<%--
  Created by IntelliJ IDEA.
  User: wgm
  Date: 17/3/23
  Time: 上午9:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>投稿</title>
    <link rel="stylesheet" href="/statics/webuploader-0.1.5/webuploader.css">
    <script src="/statics/js/jquery/jquery-1.10.2.min.js" type="text/javascript"></script>
    <script src="/statics/webuploader-0.1.5/webuploader.min.js" type="text/javascript"></script>
    <script type="text/javascript" src="/statics/webuploader-0.1.5/getting-started.js"></script>

</head>
<body>
    测试图片上传功能：
    <%--未使用插件实现--%>
     <%--<fieldset>--%>
        <%--<legend>图片上传</legend>--%>
        <%--<h2>只能上传单张10M以下的 PNG、JPG、GIF 格式的图片</h2>--%>
         <%--<form action="/image/uploadProcess" method="post" enctype="multipart/form-data">--%>
           <%--选择文件:<input type="file" name="file">--%>
           <%--<input type="submit" value="上传">--%>
       <%--</form>--%>
     <%--</fieldset>--%>
    <!--dom结构部分-->

    <%--使用插件--%>
    <div id="uploader-demo">
      <!--用来存放item-->
      <div id="fileList" class="uploader-list"></div>
      <div id="filePicker">选择图片</div>
    </div>


</body>
</html>
