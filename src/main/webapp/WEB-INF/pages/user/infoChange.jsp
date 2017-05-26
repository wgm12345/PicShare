<%--
  Created by IntelliJ IDEA.
  User: wgm
  Date: 17/5/9
  Time: 上午12:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<title>个人基础信息修改</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/bootstrap-3.3.7/css/bootstrap.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/css/site.css">
  <link rel="stylesheet" type="text/css" href="/statics/webuploader/webuploader.css" />
  <link rel="stylesheet" type="text/css" href="/statics/webuploader/image-upload/style.css" />
  <script type="text/javascript" src="${pageContext.request.contextPath}/statics/js/jquery/jquery-1.10.2.min.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/statics/bootstrap-3.3.7/js/bootstrap.min.js"></script>
</head>
<body style="background-color: white">
<c:if test="${error != null}">
  访问错误
</c:if>
<c:if test="${error == null}">
  <jsp:include page="/layout/topNav" flush="true">
    <jsp:param name="user" value= "${sessionScope.user}" ></jsp:param>
  </jsp:include>
  <jsp:include page="/layout/user_header" flush="true"></jsp:include>
  <div class="user-info-wrap">

    <div class="row">
      <div class="col-md-4 col-md-offset-4">
        <img id="user-head-img" class="img-thumbnail" src="/statics/images/sculpture/${userInfo.headImgPath}">
      </div>
      <div class="col-md-4">
        <!-- 弹出上传头像图片模态框 -->
        <!-- Button trigger modal -->
        <button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal">
          上传自定义头像
        </button>

        <!-- Modal -->
        <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
          <div class="modal-dialog" role="document">
            <div class="modal-content">
              <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">头像上传</h4>
              </div>
              <div class="modal-body">
                <div id="wrapper">
                  <div id="container">
                    <!--头部，相册选择和格式选择-->

                    <div id="uploader">
                      <div class="queueList">
                        <div id="dndArea" class="placeholder">
                          <div id="filePicker"></div>
                          <p>请将头像照片拖到这里</p>
                        </div>
                      </div>
                      <%--<form action="" method="post" id="image-data">--%>
                        <%--<label>作品名称</label>--%>
                        <%--<input name="name" id="name" type="text">--%>
                        <%--<label>作品类型</label>--%>
                        <%--<input type="radio" name="genreId" value="1">--%>
                        <%--<label>动画</label>--%>
                        <%--<input type="radio" name="genreId" value="2">--%>
                        <%--<label>游戏</label>--%>
                        <%--<label>作品简介</label>--%>
                        <%--<input type="text" id="briefIntro" name="briefIntro" placeholder="简介">--%>
                      <%--</form>--%>
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
              </div>
              <div class="modal-footer">
                <button id="close-change-head" type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <%--<button type="button" class="btn btn-primary">修改</button>--%>
              </div>
            </div>
          </div>
        </div>

      </div>
    </div>

    <div class="row">
        邮箱:${userInfo.email}

        <c:if test="${userInfo.emailConfirmed == true}">
          "已认证"
        </c:if>
        <c:if test="${userInfo.emailConfirmed == false}">
          "未认证"
        </c:if>
      <c:if test="${userInfo.emailConfirmed == false}">
        <button>去认证</button>
      </c:if>
    </div>

    <div class="row">
      昵称:${userInfo.name}
    </div>

    <div class="row">
      性别:${userInfo.sex}
    </div>

    <div class="row">
      注册日期:${userInfo.signUpDateTime}
    </div>
  </div>
</c:if>

</body>
<script>
  $("#close-change-head").click(function(){
    window.location.reload();
  });
</script>
<script type="text/javascript" src="/statics/webuploader/image-upload/jquery.js"></script>
<script type="text/javascript" src="/statics/webuploader/webuploader.js"></script>
<script type="text/javascript" src="/statics/webuploader/image-upload/headImgUpload.js"></script>
</html>
