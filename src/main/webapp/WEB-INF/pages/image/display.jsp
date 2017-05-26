<%--
  Created by IntelliJ IDEA.
  uploader: wgm
  Date: 17/3/24
  Time: 下午1:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>${uploader.name}的作品</title>
  <link href="${pageContext.request.contextPath}/statics/webuploader/webuploader.css" rel="stylesheet" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/bootstrap-3.3.7/css/bootstrap.css" >
  <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/css/site.css">
  <%--<link rel="stylesheet" href="Content/pager.css">--%>
</head>
<script type="text/javascript" src="${pageContext.request.contextPath}/statics/js/jquery/jquery-1.10.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/statics/bootstrap-3.3.7/js/bootstrap.min.js"></script>
<%--<script type="text/javascript" src="pager.js"></script>--%>
<body>
  <jsp:include page="/layout/topNav" flush="true">
      <jsp:param name="user" value= "${sessionScope.user}" ></jsp:param>
  </jsp:include>
  <jsp:include page="/layout/header" flush="true"></jsp:include>
  <div class="container body-content">
  <div class="container-fluid image">
    <div class="row">
      <div class="col-md-7">
        <div class="image_msg i-box">
          <h3>${image.name}</h3>
          <hr>
          <div class="row">
            <div class="col-md-3">投稿时间<br>${image.createDateTime}</div>
            <div class="col-md-3"><span class="glyphicon glyphicon-hd-video">浏览量<br>${image.totalHits}</span></div>
            <div class="col-md-3"><span class="glyphicon glyphicon-list-alt">评论数<br>${image.commentsSum}</span></div>
            <div class="collect-wrap"><i class="icon icon-collect"></i><button class="btn-collect">收藏</button></div>
            <div class="like-wrap"><i class="icon icon-like"></i><button class="btn-like">点赞</button></div>
          </div>
          <script>
            var userId = null;
            var imageId = ${image.id};
            <c:if test="${user != null}">
            var userId = ${user.id};
            </c:if>

          </script>
          <div class="row">
            <div class="col-md-8">
              <br>
              作品简介：<br>
              ${image.briefIntro}
            </div>
          </div>
        </div>
        <div class="image_show i-box">
          <img class="img-responsive" src="${pageContext.request.contextPath}/statics/images/master/${image.imgPath}">
        </div>
      </div>
      <div class="col-md-3">
        <div class="user_msg i-box">
          <div class="user_h_img">
            <img class="img-thumbnail" src="${pageContext.request.contextPath}/statics/images/sculpture/${uploader.headImgPath}">
          </div>
          <script>
            var followUserId = ${uploader.id};
            var fanId = null;
            <c:if test="${user.id != null}">
                var fanId = ${user.id};
            </c:if>
          </script>
          <div class="msg-wrap">
            <h>${uploader.name}</h>
            <button class="follow">关注</button>
            <p> 简介:<br>${image.briefIntro}</p><br>
            <a href="${pageContext.request.contextPath}/user/personalPage/uid=${uploader.id}" class="to-space">TA的空间>></a>
          </div>
        </div>
      </div>
    </div>
    <br/>

    <h4 class="i-box">评论区</h4>
    <hr />
    <c:if test="${user != null}">
      <div class="form-horizontal i-box">
          <form id="comment-data" class="form-group" method="post" action="" >
          <div class="row">
            <div class="col-md-2 ">
              <img  class="img-circle img-thumbnail" style="height:120px;width:120px" src="${pageContext.request.contextPath}/statics/images/sculpture/${user.headImgPath}" />
            </div>
            <div class="col-md-9">
              <textarea rows="4" cols="500" name="content" id="content" class="form-control"placeholder="请评论" ></textarea>
            </div>
          </div>
          <input type="hidden" name="imageId" value="${image.id}">
        </form>
        <div class="row">
            <div class="col-md-2 col-md-offset-9">
                  <%--<input type="submit" value="发表" id="btn-submit" class="btn btn-default"  />--%>
              <button id="btn-submit" class="btn btn-default">发表</button>
            </div>
        </div>
      </div>
    </c:if>
    <c:if test="${user == null}">
    <div class="form-horizontal i-box">
      <a href="${pageContext.request.contextPath}/user/signIn">评论请登录>></a>
    </div>
    </c:if>
    <hr/>

    <div id="com-floors">
      <ul>
      </ul>
    </div>
    <script>

    </script>
  </div>
  <hr />
</div>
  <jsp:include page="/layout/footer" flush="true"></jsp:include>
</body>

<script src="${pageContext.request.contextPath}/statics/js/display.js"></script>
</html>
