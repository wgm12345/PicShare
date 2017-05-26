<%--
  Created by IntelliJ IDEA.
  User: wgm
  Date: 17/3/22
  Time: 下午1:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<title>登录</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/bootstrap-3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/css/site.css">
</head>

<c:if test="${sessionScope.user == null}">
  <body class="sign-bg">
  <div id = "main">
    <div class = "sign-box-wrap">
      <form class="sign-box" action="/user/signIn" method="post">
        <div class="logo">Picshare</div>
        <c:if test="${error != null}">
          <label class="label-danger sign-error-box">${error}</label>
        </c:if>
        <div class="login-list form-horizontal">
          <div class="form-group">
            <label class="col-md-3" >邮箱账号</label>
            <div class="col-md-9">
              <input class="form-control" type="text" name="email">
            </div>
          </div>
          <div class="form-group">
            <label class="col-md-3">密码</label>
            <div class="col-md-9">
              <input class="form-control" type="password" name="password">
            </div>
          </div>
          <div class="form-group">
            <input class="col-md-offset-8 col-md-2 btn-submit" type="submit" value="提交">
          </div>
        </div>
      </form>
    </div>
  </div>
  </body>
</c:if>
<c:if test="${sessionScope.user != null}">
  <script>
    window.location.href = "/index";
  </script>
</c:if>
</html>
