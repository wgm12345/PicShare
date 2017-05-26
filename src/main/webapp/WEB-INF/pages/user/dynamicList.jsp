<%--
  Created by IntelliJ IDEA.
  User: wgm
  Date: 17/5/6
  Time: 下午2:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/bootstrap-3.3.7/css/bootstrap.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/css/site.css">
  <script type="text/javascript" src="${pageContext.request.contextPath}/statics/bootstrap-3.3.7/js/bootstrap.min.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/statics/js/jquery/jquery-1.10.2.min.js"></script>
  <title>您的动态</title>
</head>
<body>

<jsp:include page="/layout/topNav" flush="true">
  <jsp:param name="user" value= "${sessionScope.user}" ></jsp:param>
</jsp:include>

<div class="i_body">
  <div class="main">
    <div class="container-fluid">
      <h3>您的动态</h3>
      <div class="row">
        <c:forEach var="dynamic" items="${dynamics}">
          <div class="col-md-12 i-box">
            <div class="col-md-10">
              <a href="/user/personalPage/uid=${dynamic.sender.id}">${dynamic.sender.name}</a><br>
              更新了作品: <a>${dynamic.content}</a>
            </div>
          </div>
        </c:forEach>
      </div>
    </div>
  </div>
</div>

</body>
</html>
