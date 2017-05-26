<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: wgm
  Date: 17/4/24
  Time: 下午3:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
  <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
  <title>投稿</title>

  <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/bootstrap-3.3.7/css/bootstrap.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/css/site.css">
  <link rel="stylesheet" type="text/css" href="/statics/webuploader/webuploader.css" />
  <link rel="stylesheet" type="text/css" href="/statics/webuploader/image-upload/style.css" />
  <script type="text/javascript" src="${pageContext.request.contextPath}/statics/js/jquery/jquery-1.10.2.min.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/statics/bootstrap-3.3.7/js/bootstrap.min.js"></script>

</head>
<body>
<c:if test="${sessionScope.user != null}">
  <jsp:include page="/layout/topNav" flush="true">
    <jsp:param name="user" value= "${sessionScope.user}" ></jsp:param>
  </jsp:include>
  <jsp:include page="/layout/header" flush="true"></jsp:include>
  <div id="wrapper">
    <div id="container">
      <!--头部，相册选择和格式选择-->

      <div id="uploader">
        <div class="queueList">
          <div id="dndArea" class="placeholder">
            <div id="filePicker"></div>
            <p>或将照片拖到这里</p>
          </div>
        </div>
        <form action="" method="post" id="image-data">
          <label>作品名称</label>
          <input name="name" id="name" type="text">
          <label>作品类型</label>
          <input type="radio" name="genreId" value="1">
          <label>动画</label>
          <input type="radio" name="genreId" value="2">
          <label>游戏</label>
          <label>作品简介</label>
          <input type="text" id="briefIntro" name="briefIntro" placeholder="简介">
        </form>
        <div class="statusBar">
          <div class="progress">
            <span class="text">0%</span>
            <span class="percentage"></span>
          </div><div class="info"></div>
          <div class="btns">
            <div id="filePicker2"></div><div class="uploadBtn">开始上传</div>
          </div>
        </div>
      </div>
    </div>
  </div>
</c:if>
<c:if test="${sessionScope.user == null}">
  <script>
    alert("请登录");
    window.location.href = "/";
  </script>
</c:if>

<script type="text/javascript" src="/statics/webuploader/image-upload/jquery.js"></script>
<script type="text/javascript" src="/statics/webuploader/webuploader.js"></script>
<script type="text/javascript" src="/statics/webuploader/image-upload/upload.js"></script>
</body>
</html>