<%--
  Created by IntelliJ IDEA.
  User: wgm
  Date: 17/3/22
  Time: 下午1:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <meta charset="UTF-8">
  <title>用户登陆/注册</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/css/site.css">
</head>
<c:if test="${sessionScope.user == null}">
  <body class="sign-bg">
  <div id = "main">
    <div class = "sign-box-wrap">
      <form class="sign-box">
        <div class="logo">Picshare</div>
        <div class="btns-wrap">
          <a href="${pageContext.request.contextPath}/user/signIn" class="sign-btn">登录</a>
          <a href="${pageContext.request.contextPath}/user/register" class="register-btn">注册</a>
            <%--<div class="other-btns-wrap">--%>
            <%--&lt;%&ndash;实现外部登陆后面弄&ndash;%&gt;--%>
            <%--<div class="icon-sina">新浪</div>--%>
            <%--<div class="icon-QQ">QQ</div>--%>
            <%--</div>--%>
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

<%--<footer class="sign-footer">--%>
  <%--<button class="about-us">关于我们</button>--%>
<%--</footer>--%>
</html>
