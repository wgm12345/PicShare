<%--
  Created by IntelliJ IDEA.
  User: wgm
  Date: 17/5/2
  Time: 下午5:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
	<title>搜索</title>
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
<div class="search-page-wrap">
  <h3>搜索结果</h3>
  <hr>
  <div class="control-bar">
    <ul>
      <li></li>
    </ul>
  </div>

  <div class="search-list">
    <div class="row">
      <c:forEach var="image" items="${relatedImages}">
        <div class="col-md-3">
          <div class="img-box">
            <img class="img-wrap" src="/statics/images/master/${image.imgPath}">
            <div class="img-name">
              <a href="/image/id=${image.id}">${image.name}</a>
            </div>
            <div class="img-info">
              点击量：${image.totalHits}<br>
              日期：${image.createDateTime}<br>
              作者：${image.uploader.name}
            </div>
          </div>
        </div>
      </c:forEach>
    </div>
  </div>
</div>
<script>
  var url = window.location.href;

  var startIndex = url.indexOf("key=");
  if(startIndex > 0){
    startIndex += 4;
  }
  //获取 url 的 value值
  var value = url.substring(startIndex,url.length);
  //将搜索表中img-name中的有关字符变成value
  // alert(imageId);
</script>


</body>
</html>
