<%--
  Created by IntelliJ IDEA.
  User: wgm
  Date: 17/5/9
  Time: 上午12:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<title>你的收藏夹</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/statics/js/jquery/jquery-1.10.2.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/statics/bootstrap-3.3.7/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/bootstrap-3.3.7/css/bootstrap.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/css/site.css">
</head>
<body style="background: white">
<jsp:include page="/layout/topNav" flush="true">
  <jsp:param name="user" value= "${sessionScope.user}" ></jsp:param>
</jsp:include>
<jsp:include page="/layout/header" flush="true"></jsp:include>
<%--借用search page的样式--%>
<div class="search-page-wrap">
  <h3>${user.name}的收藏夹</h3>
  <hr>
  <div class="control-bar">
    <ul>
      <li></li>
    </ul>
  </div>

  <div class="search-list">
    <div class="row">
      <c:forEach var="image" items="${collections}">
        <div class="col-md-3">
          <div class="img-box img-box-collectionList">
            <img class="img-wrap" src="/statics/images/master/${image.imgPath}">
            <div class="img-name">
              <a href="/image/id=${image.id}">${image.name}</a>
            </div>
            <div class="img-info">
              点击量：${image.totalHits}<br>
              日期：${image.createDateTime}<br>
              作者：${image.uploader.name}
            </div>
              <button class="btn-info cancel-collect" data-user-id="${user.id}" data-image-id="${image.id}">取消收藏</button>
            </div>
        </div>
      </c:forEach>
    </div>
  </div>
</div>
</body>
<script>
    $(".cancel-collect").click(function(){
      $.ajax({
        url:"/user/cancelCollect",
        type:"POST",
        data:{
          userId:$(this).data("userId"),
          imageId:$(this).data("imageId")
        },
        success:function(data){
          if(data["success"] != null){
            alert(data["success"]);
          }
          if(data["error"] != null){
            alert(data["error"]);
          }
          window.location.reload();

        }
      });
    });
</script>
</html>
